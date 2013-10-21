package com.vendertool.inventory.dal.fieldset;


import com.mysema.query.types.Path;
import com.vendertool.inventory.dal.dao.codegen.QProductVariation;

public class ProductVariationReadSet {
	private static final QProductVariation PRODUCTVARIATION = QProductVariation.productVariation;
	
	private static class ProductVariationReadSetHolder {
		private static final ProductVariationReadSet INSTANCE = new ProductVariationReadSet();
	}
	
	public static ProductVariationReadSet getInstance() {
		return ProductVariationReadSetHolder.INSTANCE;
	}
	
	
	public final Path<?>[] ALL = {
			PRODUCTVARIATION.createdDate,
			PRODUCTVARIATION.currencyCodeIso3,
			PRODUCTVARIATION.lastModifiedDate,
			PRODUCTVARIATION.price,
			PRODUCTVARIATION.productId,
			PRODUCTVARIATION.productVariationId,
			PRODUCTVARIATION.quantity,
			PRODUCTVARIATION.sku,
			PRODUCTVARIATION.title,
			PRODUCTVARIATION.url,
			};
	
	
}
