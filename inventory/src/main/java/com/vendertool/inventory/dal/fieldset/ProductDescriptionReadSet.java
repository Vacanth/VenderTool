package com.vendertool.inventory.dal.fieldset;


import com.mysema.query.types.Path;
import com.vendertool.inventory.dal.dao.codegen.QProductDescription;

public class ProductDescriptionReadSet {
	private static final QProductDescription PRODUCTDESCRIPTION = QProductDescription.productDescription;
	
	private static class ProductReadSetHolder {
		private static final ProductDescriptionReadSet INSTANCE = new ProductDescriptionReadSet();
	}
	
	public static ProductDescriptionReadSet getInstance() {
		return ProductReadSetHolder.INSTANCE;
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