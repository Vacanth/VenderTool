package com.vendertool.listing.dal.dao.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.listing.dal.dao.codegen.QListing;

public class ListingReadSet {

	private static final QListing LISTING = QListing.listing;

	private static class ListingReadSetHolder {
		private static final ListingReadSet INSTANCE = new ListingReadSet();
	}

	public static ListingReadSet getInstance() {
		return ListingReadSetHolder.INSTANCE;
	}

	public final Path<?>[] MEDIUM = { 
			LISTING.accountId, 
			LISTING.categoryId,
			LISTING.condition,
			LISTING.quantity,
			LISTING.listingId,
			LISTING.marketplaceItemId,
			LISTING.categoryId};
}