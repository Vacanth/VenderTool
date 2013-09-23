package com.vendertool.inventory.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.inventory.dal.dao.codegen.QProduct;

public class ProductUpdateSet {
	private static final QProduct PRODUCT = QProduct.product;
	
	private static class ProductUpdateSetHolder {
		private static final ProductUpdateSet INSTANCE = new ProductUpdateSet();
	}
	
	public static ProductUpdateSet getInstance() {
		return ProductUpdateSetHolder.INSTANCE;
	}
	
	public final Path<?>[] ALL = {
		
	};
	

}
