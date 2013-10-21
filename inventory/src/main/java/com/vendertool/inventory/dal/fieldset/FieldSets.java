package com.vendertool.inventory.dal.fieldset;


public interface FieldSets {

	public static ProductReadSet PRODUCT_READSET = ProductReadSet.getInstance();
	public static ProductUpdateSet PRODUCT_UPDATESET = ProductUpdateSet.getInstance();
	
	public static ProductDescriptionReadSet PRODUCT_DESCRIPTION_READSET = ProductDescriptionReadSet.getInstance();
	public static ProductDescriptionUpdateSet PRODUCT_DESCRIPTION_UPDATESET = ProductDescriptionUpdateSet.getInstance();
	
	public static ProductVariationReadSet PRODUCT_VARIATION_READSET = ProductVariationReadSet.getInstance();
	public static ProductVariationReadSet PRODUCT_VARIATION_UPDATESET = ProductVariationReadSet.getInstance();
}
