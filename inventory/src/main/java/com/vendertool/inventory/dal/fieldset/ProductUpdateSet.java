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
			PRODUCT.accountId,
			PRODUCT.createdDate,
			PRODUCT.currencyCodeIso3,
			PRODUCT.dimensionUnit,
			PRODUCT.height,
			PRODUCT.lastModifiedApp,
			PRODUCT.lastModifiedDate,
			PRODUCT.length,
			PRODUCT.price,
			PRODUCT.productCode,
			PRODUCT.productCodeType,
			PRODUCT.productId,
			PRODUCT.productUrl,
			PRODUCT.quantity,
			PRODUCT.sku,
			PRODUCT.title,
			PRODUCT.weight,
			PRODUCT.weightUnit,
			PRODUCT.width
	};
	

}
