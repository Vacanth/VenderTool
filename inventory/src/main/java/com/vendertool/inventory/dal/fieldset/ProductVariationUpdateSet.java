package com.vendertool.inventory.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.inventory.dal.dao.codegen.QProduct;
import com.vendertool.inventory.dal.dao.codegen.QProductVariation;

public class ProductVariationUpdateSet {
	private static final QProductVariation PRODUCTVARIATION = QProductVariation.productVariation;
	
	private static class ProductUpdateSetHolder {
		private static final ProductVariationUpdateSet INSTANCE = new ProductVariationUpdateSet();
	}
	
	public static ProductVariationUpdateSet getInstance() {
		return ProductUpdateSetHolder.INSTANCE;
	}
	
	public final Path<?>[] ALL = {
		
	};
	

}
