package com.vendertool.mercadolibreadapter;

import com.vendertool.mercadolibreadapter.factory.IBaseMercadolibreOperationAdapter;
import com.vendertool.mercadolibreadapter.factory.MercadolibreAdapterFactory;
import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.DuplicateListingResponse;
import com.vendertool.sharedtypes.rnr.EndListingResponse;
import com.vendertool.sharedtypes.rnr.GetListingRequest;
import com.vendertool.sharedtypes.rnr.GetListingResponse;
import com.vendertool.sharedtypes.rnr.UpdateListingRequest;
import com.vendertool.sharedtypes.rnr.UpdateListingResponse;
import com.vendertool.sharedtypes.rnr.VerifyListingRequest;
import com.vendertool.sharedtypes.rnr.VerifyListingResponse;

public class MarketListingAdapterImpl implements IMarketListingAdapter {

	public void addListing(AddListingRequest request, AddListingResponse response) {
		IBaseMercadolibreOperationAdapter adapter = MercadolibreAdapterFactory
				.getInstance().getOperationAdapter(request);
		adapter.execute(request, response);
	}

	public GetListingResponse  getListing(String marketListingId) {
		GetListingRequest request = new GetListingRequest();
		request.setListingId(marketListingId);
		IBaseMercadolibreOperationAdapter adapter = MercadolibreAdapterFactory
				.getInstance().getOperationAdapter(request);
		GetListingResponse response = new GetListingResponse();
		adapter.execute(request, response);
		return response;
	}

	public EndListingResponse endListing(String listingId) {
		return null;
	}

	public UpdateListingResponse updateListing(UpdateListingRequest request) {
		return null;
	}

	public DuplicateListingResponse duplicateListing(String listingId) {
		return null;
	}

	public VerifyListingResponse verifyListing(VerifyListingRequest request) {
		return null;
	}
}
