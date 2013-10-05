package com.vendertool.inventory.dal.dao;


import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.DALMapper;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.inventory.dal.dao.codegen.QBeanProduct;
import com.vendertool.inventory.dal.dao.codegen.QProduct;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.Dimension;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.core.ProductCodeTypeEnum;
import com.vendertool.sharedtypes.core.Weight;
import com.vendertool.sharedtypes.core.Weight.WeightUnitEnum;

public class ProductMapper implements DALMapper<Product> {
	//	private static final Logger logger = Logger.getLogger(ProductMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	public ProductMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}

		this.paths = paths;
	}

	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			Product product) {
		if(VUTIL.isNull(product)) {
			return null;
		}

		QProduct a = QProduct.product;

		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();

		for(Path<?> rpath : paths) {
			if(a.productId.equals(rpath)) {
				if (product.getProductId() != null) 
				map.put(a.productId, product.getProductId());
			}
			if(a.title.equals(rpath)) {
				if (product.getTitle() != null) 
				map.put(a.title, product.getTitle());
			}
			if(a.sku.equals(rpath)) {
				if (product.getSku() != null) 
				map.put(a.sku, product.getSku());
			}
			if(a.price.equals(rpath)) {
				if (product.getPrice().getValue() != null) 
				map.put(a.price, product.getPrice().getValue());
			}
			if(a.currencyCodeIso3.equals(rpath)) {
				if (product.getPrice().getCurrency() != null) 
				map.put(a.currencyCodeIso3, product.getPrice().getCurrency());
			}
			if(a.quantity.equals(rpath)) {
				if (product.getQuantity() != null) 
				map.put(a.quantity, product.getQuantity());
			}
			if(a.accountId.equals(rpath)) {
				if (product.getAccountId() != null) 
				map.put(a.accountId, product.getAccountId());
			}
			if(a.productCode.equals(rpath)) {
				if (product.getProductCode() != null) 
				map.put(a.productCode, product.getProductCode());
			}
			if(a.productCodeType.equals(rpath)) {
				if (product.getProductCodeType() != null) 
				map.put(a.productCodeType, product.getProductCodeType());
			}
			if(a.dimensionUnit.equals(rpath)) {
				if (product.getDimension().getDimensionUnit() != null) 
				map.put(a.dimensionUnit, product.getDimension().getDimensionUnit());
			}
			if(a.height.equals(rpath)) {
				if (product.getDimension().getHeight() != null) 
				map.put(a.height, product.getDimension().getHeight());
			}
			if(a.length.equals(rpath)) {
				if (product.getDimension().getLength() != null) 
				map.put(a.length, product.getDimension().getLength());
			}
			if(a.width.equals(rpath)) {
				if (product.getDimension().getWidth() != null) 
				map.put(a.width, product.getDimension().getWidth());
			}
			if(a.weight.equals(rpath)) {
				if (product.getWeight().getValue() != null) 
				map.put(a.weight, product.getWeight().getValue());
			}

			if(a.weightUnit.equals(rpath)) {
				if (product.getWeight().getWeightUnit() != null) 
				map.put(a.weightUnit, product.getWeight().getWeightUnit());
			}

			if(a.productUrl.equals(rpath)) {
				if (product.getProductUrl() != null) 
				map.put(a.productUrl, product.getProductUrl());
			}
			if(a.createdDate.equals(rpath)) {
				if (product.getCreateDate() != null) 
				map.put(a.createdDate, product.getCreateDate());
			}

			if(a.lastModifiedDate.equals(rpath)) {
				if (product.getLastModifiedDate() != null) 
				map.put(a.lastModifiedDate, product.getLastModifiedDate());
			}
		}

		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}

	public QBeanProduct populateBean(Product product) {
		if(VUTIL.isNull(product)) {
			return null;
		}

		QBeanProduct bean = new QBeanProduct();
		bean.setProductId(product.getProductId());

		if(product.getTitle() != null) {
			bean.setTitle( product.getTitle());
		}
		if(product.getSku() != null) {
			bean.setSku( product.getSku());
		}

		if(product.getPrice().getValue() != null) {
			bean.setPrice(product.getPrice().getValue());

		}
		if(product.getPrice().getCurrency() != null) {
			bean.setCurrencyCodeIso3(product.getPrice().getCurrency().toString());
		}
		if(product.getQuantity() != null) {
			bean.setQuantity(product.getQuantity());
		}
		bean.setAccountId(product.getAccountId());

		if(product.getProductCode() != null) {
			bean.setProductCode(product.getProductCode());
		}	
		if(product.getProductCodeType() != null) {
			bean.setProductCodeType((byte) product.getProductCodeType().getId());
		}

		if(product.getDimension().getDimensionUnit() != null) {
			bean.setDimensionUnit((byte) product.getDimension().getDimensionUnit().getId());
		}
		if(product.getDimension().getHeight() != null) {
			bean.setHeight( product.getDimension().getHeight());
		}
		if(product.getDimension().getLength() != null) {
			bean.setHeight( product.getDimension().getLength());
		}
		if(product.getDimension().getWidth() != null) {
			bean.setHeight( product.getDimension().getWidth());
		}
		if(product.getWeight().getWeightUnit() != null) {
			bean.setWeightUnit((byte) product.getWeight().getWeightUnit().getId());
		}
		if(product.getWeight().getValue() != null) {
			bean.setWeightUnit((byte) product.getWeight().getWeightUnit().getId());
		}
		if(product.getProductUrl() != null) {
			bean.setProductUrl(product.getProductUrl());
		}

		return bean;

	}

	public Product convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}

		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}

		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}

		QProduct a = QProduct.product;
		Product product = new Product();

		for(Path<?> rpath : paths) {
			if(a.productId.equals(rpath)) {
				product.setProductId(row.get(a.productId));
			}

			if(a.title.equals(rpath)) {
				product.setTitle(row.get(a.title));
			}
			if(a.sku.equals(rpath)) {
				product.setSku(row.get(a.sku));
			}
			if(a.price.equals(rpath)) {
				Amount productPrice = new Amount();
				productPrice.setValue(row.get(a.price));
				if(a.currencyCodeIso3.equals(rpath)) {
					try {
						Currency currency = Currency.getInstance(row.get(a.currencyCodeIso3));
						if(currency != null) {
							productPrice.setCurrency(currency);
						}
					} catch (Exception e) {
						productPrice.setCurrency(null);
					}
				}
				product.setPrice(productPrice);
			}

			if(a.quantity.equals(rpath)) {
				product.setQuantity(row.get(a.quantity));
			}
			if(a.productCode.equals(rpath)) {
				product.setProductCode(row.get(a.productCode));
			}
			if(a.productCodeType.equals(rpath)) {
				product.setProductCodeType(ProductCodeTypeEnum.valueOf(row.get(a.productCodeType).toString()));
			}
			Weight weight = new Weight();
			if(a.weightUnit.equals(rpath)) {
				weight.setWeightUnit(WeightUnitEnum.valueOf(row.get(a.weightUnit).toString()));
				product.setWeight(weight);
			}
			if(a.weight.equals(rpath)) {
				weight.setValue(row.get(a.weight));
				product.setWeight(weight);
			}
			Dimension dimension = new Dimension();
			if(a.dimensionUnit.equals(rpath)) {
				dimension.setDimensionUnit(Dimension.DimensionUnitEnum.valueOf(row.get(a.dimensionUnit).toString()));
				product.setDimension(dimension);
			}
			if(a.height.equals(rpath)) {
				dimension.setHeight(row.get(a.height));
				product.setDimension(dimension);
			}
			if(a.length.equals(rpath)) {
				dimension.setLength(row.get(a.length));
				product.setDimension(dimension);
			}
			if(a.width.equals(rpath)) {
				dimension.setWidth(row.get(a.width));
				product.setDimension(dimension);
			}
			if(a.accountId.equals(rpath)) {
				product.setAccountId(row.get(a.accountId));
			}
			if(a.productUrl.equals(rpath)) {
				product.setProductUrl(row.get(a.productUrl));
			}
			if(a.createdDate.equals(rpath)) {
				product.setCreateDate(row.get(a.createdDate));
			}
			if(a.lastModifiedDate.equals(rpath)) {
				product.setLastModifiedDate(row.get(a.lastModifiedDate));
			}

		}

		return product;
	}
}
