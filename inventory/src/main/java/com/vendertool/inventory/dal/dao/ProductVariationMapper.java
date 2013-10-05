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
import com.vendertool.inventory.dal.dao.codegen.QBeanProductVariation;
import com.vendertool.inventory.dal.dao.codegen.QProductVariation;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.ProductVariation;

public class ProductVariationMapper implements DALMapper<ProductVariation> {
//	private static final Logger logger = Logger.getLogger(ProductVariationMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	public ProductVariationMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}

	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			ProductVariation productVariation) {
		if(VUTIL.isNull(productVariation)) {
			return null;
		}
		
		QProductVariation a = QProductVariation.productVariation;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(a.productVariationId.equals(rpath)) {
				map.put(a.productVariationId, productVariation.getProductVariationId());
			}
			
			if(a.title.equals(rpath)) {
				if (productVariation.getTitle() != null) 
				map.put(a.title, productVariation.getTitle());
			}
			if(a.quantity.equals(rpath)) {
				if (productVariation.getTitle() != null) 
				map.put(a.quantity, productVariation.getQuantity());
			}
			
			if(a.price.equals(rpath)) {
				if (productVariation.getPrice() != null) 
				map.put(a.price, productVariation.getPrice());
				map.put(a.currencyCodeIso3, productVariation.getPrice().getCurrency());
			}
		
			if(a.url.equals(rpath)) {
				if (productVariation.getProductUrl() != null) 
				map.put(a.url, productVariation.getProductUrl());
			}
			if(a.sku.equals(rpath)) {
				if (productVariation.getSku() != null) 
				map.put(a.sku, productVariation.getSku());
			}		

		}
		
		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}
	
	public QBeanProductVariation populateBean(ProductVariation productVariation) {
		if(VUTIL.isNull(productVariation)) {
			return null;
		}
		
		QBeanProductVariation bean = new QBeanProductVariation();
		bean.setProductVariationId(productVariation.getProductVariationId());
			
		if(productVariation.getTitle() != null) {
			bean.setTitle(productVariation.getTitle());
		}
		if(productVariation.getSku() != null) {
			bean.setSku(productVariation.getSku());
		}
		if(productVariation.getPrice().getValue() != null) {
			bean.setPrice(productVariation.getPrice().getValue());
		}
		if(productVariation.getPrice().getCurrency() != null) {
			bean.setCurrencyCodeIso3(productVariation.getPrice().getCurrency().toString());
		}
		if(productVariation.getQuantity() != null) {
			bean.setQuantity(productVariation.getQuantity());
		}
		
		if(productVariation.getProductUrl() != null) {
			bean.setUrl(productVariation.getProductUrl());
		}
		
		
		
		
		return bean;
		
	}
	
	public ProductVariation convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QProductVariation a = QProductVariation.productVariation;
		ProductVariation productVariation = new ProductVariation();
		
		for(Path<?> rpath : paths) {
			if(a.productVariationId.equals(rpath)) {
				productVariation.setProductVariationId(row.get(a.productVariationId));
			}
		
			if(a.title.equals(rpath)) {
				productVariation.setTitle(row.get(a.title));
			}
			if(a.sku.equals(rpath)) {
				productVariation.setSku(row.get(a.sku));
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
				productVariation.setPrice(productPrice);
			}
			
			if(a.quantity.equals(rpath)) {
				productVariation.setQuantity(row.get(a.quantity));
			}
		
			if(a.url.equals(rpath)) {
				productVariation.setProductUrl(row.get(a.url));
			}
		
		}
		
		return productVariation;
	}
}
