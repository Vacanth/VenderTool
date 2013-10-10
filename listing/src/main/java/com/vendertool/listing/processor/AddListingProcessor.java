package com.vendertool.listing.processor;

import org.apache.log4j.Logger;

import com.vendertool.common.MarketCountryKey;
import com.vendertool.common.marketplace.IMarketListingAdapter;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.listing.ListingMarketAdapterRegistry;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;

public class AddListingProcessor extends BaseListingProcessor {

	private static final Logger logger = Logger
			.getLogger(AddListingProcessor.class);
	private static ValidationUtil s_validationUtil = ValidationUtil
			.getInstance();

	private static class AddListingHelperHolder {
		private static final AddListingProcessor INSTANCE = new AddListingProcessor();
	}

	/**
	 * Get the singleton instance of the class
	 * 
	 * @return
	 */
	public static AddListingProcessor getInstance() {
		return AddListingHelperHolder.INSTANCE;
	}

	private AddListingProcessor() {
		super(ListingProcessorTypeEnum.ADD_LISTING);
	}

	@Override
	public void validate(BaseRequest request, BaseResponse response) {
		if (s_validationUtil.isNull(request)
				|| s_validationUtil.isNull(response)) {
			VTRuntimeException ex = new VTRuntimeException(
					"NULL value passed to validate in AddListingProcessor");
			logger.debug("NULL value passed to validate in AddListingProcessor",
					ex);
			throw ex;
		}
		AddListingRequest addListingRequest = (AddListingRequest) request;
		AddListingResponse addListingResponse = (AddListingResponse) response;
		Listing listing = addListingRequest.getListing();
		if (s_validationUtil.isNull(listing)) {
			addListingResponse.addFieldBindingError(
					Errors.LISTING.LISTING_CONTAINER_IS_EMPTY,
					addListingRequest.getClass().getName(), "listing");
			//If am here, I don't have anything to process. So am going back.
			return;
		}
		Product product = listing.getProduct();
		if (s_validationUtil.isNull(product)) {
			addListingResponse.addFieldBindingError(
					Errors.LISTING.LISTING_CONTAINER_IS_EMPTY,
					addListingRequest.getClass().getName(), "product");
			//If am here, I don't have anything to process. So am going back.
			return;
		}
		// TODO start basic validation
		//We can skip this since we are gona relay on MarketPlace validations.
	}

	@Override
	public void performOperation(BaseRequest request, BaseResponse response) {
		if (request == null || response == null) {
			VTRuntimeException ex = new VTRuntimeException(
					"NULL value passed to performOperation in AddListingProcessor");
			logger.debug(
					"NULL value passed to performOperation in AddListingProcessor",
					ex);
			throw ex;
		}
		AddListingRequest addListingRequest = (AddListingRequest) request;
		AddListingResponse addListingResponse = (AddListingResponse) response;
		// TODO Do operation

		// Make call to Mer
		Listing listing = addListingRequest.getListing();
		IMarketListingAdapter adapter = ListingMarketAdapterRegistry
				.getInstance().getMarketListingAdapter(
						new MarketCountryKey(listing.getCountry(), listing
								.getMarket()));
		adapter.addListing(addListingRequest, addListingResponse);
		// TODO update DB
	}
}