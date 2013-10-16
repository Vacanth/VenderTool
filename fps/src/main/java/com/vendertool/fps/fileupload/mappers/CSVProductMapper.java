package com.vendertool.fps.fileupload.mappers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CSVProductMapper {
	public static String PRODUCT_VARIATION_TOKEN = "_variation_";
	public static String PRODUCT_SPECIFICATION_TOKEN = "_specification_";

	private static CSVProductMapper s_self = new CSVProductMapper();

	public static CSVProductMapper getInstance() {
		return s_self;
	}

	public Map<String, String> getProductFeedMapper() {
		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("SKU", "sku");
		columnMapping.put("Product-code", "productCode");
		columnMapping.put("product-code-type", "productCodeType");
		columnMapping.put("Category-id", "categoryId");
		columnMapping.put("title", "title");
		columnMapping.put("listing-type-id", "listingTypeId");
		columnMapping.put("description", "description");
		columnMapping.put("price", "price");
		columnMapping.put("CurrencyCode", "currencyCode");
		columnMapping.put("quantity", "quantity");
		columnMapping.put("item-condition", "itemCondition");
		columnMapping.put("weight", "weight");
		columnMapping.put("dimension", "dimension");
		columnMapping.put("images-url", "imageURL");
		return columnMapping;
	}

	// TODO this method can be generalized!
	public List<ProductBean> getProductBeans(String batchReqFileLocation)
			throws IOException {
		HeaderColumnNameTranslateMappingStrategy<ProductBean> strategy = new HeaderColumnNameTranslateMappingStrategy<ProductBean>();
		strategy.setType(ProductBean.class);
		strategy.setColumnMapping(CSVProductMapper.getInstance()
				.getProductFeedMapper());
		CSVReader reader = null;
		List<ProductBean> list = null;
		CsvToBean<ProductBean> csvToBean = new CsvToBean<ProductBean>();
//		InputStream input = AwsFileUtils.getInstance().downloadFile(
//				"testfile_dynamic_variations.csv", "seller_data");
    	InputStream input = new BufferedInputStream(new FileInputStream(
    			new File("C:\\Users\\Gnanasekar\\Downloads\\testfile_dynamic_variations.csv")));
 		
		reader = new CSVReader(new InputStreamReader(input));
		if (reader != null) {
			list = csvToBean.parse(strategy, reader);
		}
		// ReadHeader again
		reader = new CSVReader(new InputStreamReader(input));
		processorHeaders(reader, list);
		input.close();
		return list;
	}

	private void processorHeaders(CSVReader reader, List<ProductBean> beans)
			throws IOException {
		Map<Integer, String> rowPSMapper = null;
		Map<Integer, String> rowVSMapper = null;
		int skuPosition = -1;
		if (reader == null || beans == null) {
			return;
		}
		List<String[]> allRows = reader.readAll();
		if (allRows != null && allRows.size() > 1) {
			String[] header = allRows.get(0);
			for (int location = 0; location < header.length; location++) {
				String columnName = header[location];
				String mapPSColumn = getProductSpecificationMappedColumn(columnName);
				if (mapPSColumn != null) {
					if (rowPSMapper == null) {
						rowPSMapper = new HashMap<Integer, String>();
					}
					rowPSMapper.put(Integer.valueOf(location), mapPSColumn);
					continue;
				}
				String mapVSColumn = getProductVariationMappedColumn(columnName);
				if (mapVSColumn != null) {
					if (rowVSMapper == null) {
						rowVSMapper = new HashMap<Integer, String>();
					}
					rowVSMapper.put(Integer.valueOf(location), mapVSColumn);
					continue;
				}
				if (skuPosition == -1 && "sku".equalsIgnoreCase(columnName)) {
					skuPosition = location;
				}
			}

			// Add Product Variations and Specifications to the ProductBean.
			Map<String, Map<String, String>> vsMap = new HashMap<String, Map<String, String>>();
			Map<String, Map<String, String>> psMap = new HashMap<String, Map<String, String>>();
			if (rowPSMapper != null || rowVSMapper != null) {
				for (int location = 1; location < allRows.size(); location++) {
					String[] row = allRows.get(location);
					if (rowPSMapper != null) {
						Map<String, String> specificationsMap = extractMap(
								rowPSMapper, row);
						String skuValue = row[skuPosition];
						psMap.put(skuValue, specificationsMap);
					}

					if (rowVSMapper != null) {
						Map<String, String> variationsMap = extractMap(
								rowVSMapper, row);
						String skuValue = row[skuPosition];
						vsMap.put(skuValue, variationsMap);
					}
				}
				// Now set it back to beanList
				for (ProductBean bean : beans) {
					if (bean == null) {
						continue;
					}
					String sku = bean.getSKU();
					bean.setVariations(vsMap.get(sku));
					bean.setSpecifications(psMap.get(sku));
				}
			}

		}
	}

	private Map<String, String> extractMap(Map<Integer, String> rowMapper,
			String[] row) {
		if (rowMapper == null || row == null) {
			return null;
		}
		Set<Integer> set = rowMapper.keySet();
		Iterator<Integer> iterator = set.iterator();
		Map<String, String> result = new HashMap<String, String>();
		while (iterator.hasNext()) {
			Integer location = iterator.next();
			String value = row[location];
			if (value != null && value.length() > 0) {
				result.put(rowMapper.get(location), value);
			}
		}
		return result;
	}

	private String getProductSpecificationMappedColumn(String columnName) {
		if (columnName == null) {
			return null;
		}
		if (columnName.length() == 0) {
			return null;
		}
		if (columnName.startsWith(PRODUCT_SPECIFICATION_TOKEN)) {
			return columnName.replaceFirst(PRODUCT_SPECIFICATION_TOKEN, "");
		}
		return null;
	}

	private String getProductVariationMappedColumn(String columnName) {
		if (columnName == null) {
			return null;
		}
		if (columnName.length() == 0) {
			return null;
		}
		if (columnName.startsWith(PRODUCT_VARIATION_TOKEN)) {
			return columnName.replaceFirst(PRODUCT_VARIATION_TOKEN, "");
		}
		return null;
	}
}
