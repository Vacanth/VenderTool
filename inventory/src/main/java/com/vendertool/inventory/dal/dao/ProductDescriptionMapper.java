package com.vendertool.inventory.dal.dao;


import java.util.HashMap;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.DALMapper;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.inventory.dal.dao.codegen.QBeanProductDescription;
import com.vendertool.inventory.dal.dao.codegen.QProductDescription;
import com.vendertool.sharedtypes.core.Product;

public class ProductDescriptionMapper implements DALMapper<Product> {
//	private static final Logger logger = Logger.getLogger(ProductMapper.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	public ProductDescriptionMapper(Path<?>[] paths) throws DatabaseException {
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
		
		QProductDescription a = QProductDescription.productDescription;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
		
			if(a.productId.equals(rpath)) {
				if (product.getProductId() != null) 
				map.put(a.productId, product.getProductId());
			}
			if(a.accountId.equals(rpath)) {
				if (product.getAccountId() != null) 
				map.put(a.accountId, product.getAccountId());
			}
			
			if(a.productDescriptionTitle.equals(rpath)) {
				if (product.getTitle() != null) 
				map.put(a.productDescriptionTitle, product.getTitle());
				
			}
			if(a.productDescriptionText.equals(rpath)) {
				if (product.getDescription() != null) 
				map.put(a.productDescriptionText, product.getDescription());
				
			}
		
			
		}
		
		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}
	
	public QBeanProductDescription populateBean(Product product) {
		if(VUTIL.isNull(product)) {
			return null;
		}
		
		QBeanProductDescription bean = new QBeanProductDescription();
		bean.setProductId(product.getProductId());
			
		if(product.getAccountId() != null) {
			bean.setAccountId(product.getAccountId());
		}
		if(product.getTitle() != null) {
			bean.setProductDescriptionTitle(product.getTitle());
		}
		
		if(product.getDescription() != null) {
			bean.setProductDescriptionText(product.getDescription());
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
		
		QProductDescription a = QProductDescription.productDescription;
		Product product = new Product();
		
		for(Path<?> rpath : paths) {
			if(a.productId.equals(rpath)) {
				product.setProductId(row.get(a.productId));
			}
		
			if(a.accountId.equals(rpath)) {
				product.setAccountId(row.get(a.accountId));
			}
			if(a.productDescriptionTitle.equals(rpath)) {
				product.setTitle(row.get(a.productDescriptionTitle));
			}
			if(a.productDescriptionText.equals(rpath)) {
				product.setDescription(row.get(a.productDescriptionText));
			}
		
		}
		
		return product;
	}
}
