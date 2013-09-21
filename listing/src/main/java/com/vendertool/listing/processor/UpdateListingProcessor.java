package com.vendertool.listing.processor;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.listing.dal.dao.ListingDAOFactory;
import com.vendertool.listing.dal.dao.ListingDao;
import com.vendertool.listing.dal.dao.fieldset.FieldSets;
import com.vendertool.listing.dal.dao.fieldset.ListingReadSet;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.UpdateListingRequest;
import com.vendertool.sharedtypes.rnr.UpdateListingResponse;

public class UpdateListingProcessor extends BaseListingProcessor {

	private static final Logger logger = Logger
			.getLogger(UpdateListingProcessor.class);
	private static ValidationUtil s_validationUtil = ValidationUtil
			.getInstance();

	private static ListingDao s_listingDao = ListingDAOFactory.getInstance()
			.getListingDao();

	private static class UpdateListingHelperHolder {
		private static final UpdateListingProcessor INSTANCE = new UpdateListingProcessor();
	}

	public static UpdateListingProcessor getInstance() {
		return UpdateListingHelperHolder.INSTANCE;
	}

	private UpdateListingProcessor() {
		super(ListingProcessorTypeEnum.UPDATE_LISTING);
	}

	@Override
	public void validate(BaseRequest request, BaseResponse response) {
		if (request == null || response == null) {
			VTRuntimeException ex = new VTRuntimeException(
					"NULL value passed to validate in UpdateListingHelper");
			logger.debug(
					"NULL value passed to validate in UpdateListingHelper", ex);
			throw ex;
		}
		UpdateListingRequest updateListingRequest = (UpdateListingRequest) request;
		UpdateListingResponse updateListingResponse = (UpdateListingResponse) response;
		Listing listing = updateListingRequest.getListing();

		if (s_validationUtil.isNull(listing)) {
			updateListingResponse.addFieldBindingError(
					Errors.LISTING.LISTING_CONTAINER_IS_EMPTY,
					updateListingRequest.getClass().getName(), "listing");
			// If am here, I don't have anything to process. So am going back.
			return;
		}
		// TODO start basic validation
	}

	@Override
	public void performOperation(BaseRequest request, BaseResponse response) {
		if (request == null || response == null) {
			VTRuntimeException ex = new VTRuntimeException(
					"NULL value passed to performOperation in UpdateListingHelper");
			logger.debug(
					"NULL value passed to performOperation in UpdateListingHelper",
					ex);
			throw ex;
		}

		UpdateListingRequest updateListingRequest = (UpdateListingRequest) request;
		UpdateListingResponse updateListingResponse = (UpdateListingResponse) response;
		Listing listingFromDB = null;
		Long listingId = updateListingRequest.getListing()
				.getListingId();
		try {
			listingFromDB = s_listingDao.getListing(listingId, FieldSets.ACCOUNT_READSET.MEDIUM);
		} catch (DBConnectionException e) {

		} catch (FinderException e) {

		} catch (DatabaseException e) {

		}
		if(!listingFromDB.getListingId().equals(listingId) ){
			VTRuntimeException ex = new VTRuntimeException(
					"Listing ID didn't match with DB finder!");
			logger.debug(
					"Listing ID didn't match with DB finder!",
					ex);
			throw ex;
		}
		//Step : 1
		//TODO Make sync call on MarketPlace listing in order to update the 
		//current status of the provided item.
		
		//Step : 2
		//We can update only:
		//1. Qty.
		//2. title.
		//3. pictures.
		//4. price.
		
		//Check Qty 
		Listing listingReq = updateListingRequest.getListing();
		Listing listingRevision = new Listing();
		//Qty
		if(listingReq.getQuantity() > 0){
			listingRevision.setQuantity(listingReq.getQuantity());
		}
		Product productReq = listingReq.getProduct();
		//Title
		String title = productReq.getTitle();
		if(title != null && title.length() > 0){
			Product product = new Product();
			product.setTitle(title);
			listingRevision.setProduct(product);
		}
	}
}