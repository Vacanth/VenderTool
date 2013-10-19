package com.vendertool.listing.processor.helper;

import com.vendertool.sharedtypes.core.Product;

public class ListingHelper {

	private ListingHelper() {

	}

	private static class ListingHelperHolder {
		private static final ListingHelper INSTANCE = new ListingHelper();
	}
	
	public static ListingHelper getInstance(){
		return ListingHelperHolder.INSTANCE;
	}
	
}