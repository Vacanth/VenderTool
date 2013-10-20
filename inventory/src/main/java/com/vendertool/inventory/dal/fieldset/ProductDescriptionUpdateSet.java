package com.vendertool.inventory.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.inventory.dal.dao.codegen.QProductDescription;

public class ProductDescriptionUpdateSet {
	private static final QProductDescription PRODUCTDESCRIPTION = QProductDescription.productDescription;
	
	private static class ProductUpdateSetHolder {
		private static final ProductDescriptionUpdateSet INSTANCE = new ProductDescriptionUpdateSet();
	}
	
	public static ProductDescriptionUpdateSet getInstance() {
		return ProductUpdateSetHolder.INSTANCE;
	}
	
	public final Path<?>[] ALL = {
			PRODUCTDESCRIPTION.accountId,
			PRODUCTDESCRIPTION.createdDate,
			PRODUCTDESCRIPTION.accountId,
			PRODUCTDESCRIPTION.productDescriptionId,
			PRODUCTDESCRIPTION.productDescriptionText,
			PRODUCTDESCRIPTION.productDescriptionTitle,
			PRODUCTDESCRIPTION.productId,
			PRODUCTDESCRIPTION.lastModifiedDate
	};
	

}
