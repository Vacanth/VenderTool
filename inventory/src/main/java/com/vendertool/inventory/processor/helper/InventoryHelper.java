package com.vendertool.inventory.processor.helper;

import com.vendertool.common.utils.StringUtils;
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

	/**
	 * This method returns true if only SKU is present in the product.
	 * 
	 * Note : For simple validation, only Title check is being performed. If the
	 * title is exist, this method assumes that user is trying to add new SKU to
	 * the system.
	 * 
	 * @param product
	 * @return
	 */
	public boolean copyFromProductRequest(Product product) {
		if (product == null) {
			return false;
		}
		String sku = product.getSku();
		if (StringUtils.getInstance().isEmpty(sku)
				|| (product.getProductId() != null && product.getProductId() <= 0)) {
			return false;
		}

		if (StringUtils.getInstance().isEmpty(product.getTitle())) {
			return true;
		}
		return false;
	}
}