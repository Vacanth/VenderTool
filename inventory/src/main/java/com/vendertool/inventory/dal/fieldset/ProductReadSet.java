package com.vendertool.inventory.dal.fieldset;


import com.mysema.query.types.Path;
import com.vendertool.inventory.dal.dao.codegen.QProduct;

public class ProductReadSet {
	private static final QProduct PRODUCT = QProduct.product;
	
	private static class ProductReadSetHolder {
		private static final ProductReadSet INSTANCE = new ProductReadSet();
	}
	
	public static ProductReadSet getInstance() {
		return ProductReadSetHolder.INSTANCE;
	}
	
	
	public final Path<?>[] ALL = {
		
	};
	
	
}
