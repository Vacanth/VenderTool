package com.vendertool.mercadolibreadapter;

import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.DuplicateListingResponse;
import com.vendertool.sharedtypes.rnr.EndListingResponse;
import com.vendertool.sharedtypes.rnr.GetListingResponse;
import com.vendertool.sharedtypes.rnr.UpdateListingRequest;
import com.vendertool.sharedtypes.rnr.UpdateListingResponse;
import com.vendertool.sharedtypes.rnr.VerifyListingRequest;
import com.vendertool.sharedtypes.rnr.VerifyListingResponse;


public interface IMarketListingAdapter {
	public void addListing(AddListingRequest request, AddListingResponse response);
	
	public GetListingResponse getListing(String marketListingId);
	
	public EndListingResponse endListing(String listingId);
	
	public UpdateListingResponse updateListing(UpdateListingRequest request);
	
	public DuplicateListingResponse duplicateListing(String listingId);
	
	public VerifyListingResponse verifyListing(VerifyListingRequest request);
}
