package com.vendertool.inventory.processor.helper;

import com.vendertool.sharedtypes.core.Product;

public class InventoryHelper {

	private InventoryHelper() {

	}

	private static class InventoryHelperHolder {
		private static final InventoryHelper INSTANCE = new InventoryHelper();
	}

	public static InventoryHelper getInstance() {
		return InventoryHelperHolder.INSTANCE;
	}
	
	public boolean skuExist(Product product){
		if(product == null){
			return false;
		}
		String sku = product.getSku();
		
		return false;
	}
}