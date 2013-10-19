package com.vendertool.listing.processor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import org.apache.log4j.Logger;

import com.vendertool.common.MarketCountryKey;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.marketplace.IMarketListingAdapter;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.inventory.dal.InventoryDALService;
import com.vendertool.listing.ListingMarketAdapterRegistry;
import com.vendertool.listing.dal.ListingDALService;
import com.vendertool.sharedtypes.core.Amount;
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
			logger.debug(
					"NULL value passed to validate in AddListingProcessor", ex);
			throw ex;
		}
		AddListingRequest addListingRequest = (AddListingRequest) request;
		AddListingResponse addListingResponse = (AddListingResponse) response;
		Listing listing = addListingRequest.getListing();
		if (s_validationUtil.isNull(listing)) {
			addListingResponse.addFieldBindingError(
					Errors.LISTING.LISTING_CONTAINER_IS_EMPTY,
					addListingRequest.getClass().getName(), "listing");
			// If am here, I don't have anything to process. So am going back.
			return;
		}
		Product product = listing.getProduct();
		if (s_validationUtil.isNull(product)) {
			addListingResponse.addFieldBindingError(
					Errors.LISTING.LISTING_CONTAINER_IS_EMPTY,
					addListingRequest.getClass().getName(), "product");
			// If am here, I don't have anything to process. So am going back.
			return;
		}
		// TODO start basic validation
		// We can skip this since we are gona relay on MarketPlace validations.
		if(product.getAccountId() == null || product.getAccountId() <= 0){
			//Add AccountID required
			addListingResponse.addFieldBindingError(
					Errors.LISTING.ACCOUNT_ID_REQUIRED,
					product.getClass().getName(), "product");
		}
		
		if(product.getTitle() == null || product.getTitle().length() == 0){
			//Add Title required error
			addListingResponse.addFieldBindingError(
					Errors.LISTING.TITLE_REQUIRED,
					product.getClass().getName(), "product");
		}
		
		if(product.getSku() == null || product.getSku().length() == 0){
			//Add Sku required error
			addListingResponse.addFieldBindingError(
					Errors.LISTING.SKU_REQUIRED,
					product.getClass().getName(), "product");
		}
		
		if(product.getPrice() == null){
			//Add price required error
			addListingResponse.addFieldBindingError(
					Errors.LISTING.PRODUCT_PRICE_REQUIRED,
					product.getClass().getName(), "product");
		}
		
		if(product.getProductCode() == null || product.getProductCode().length() == 0){
			//Add Product code required error
			addListingResponse.addFieldBindingError(
					Errors.LISTING.PRODUCT_CODE_REQUIRED,
					product.getClass().getName(), "product");
		}
		
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
		normalizeProduct(addListingRequest);
		// Make call to Mer
		Listing listing = addListingRequest.getListing();
		IMarketListingAdapter adapter = ListingMarketAdapterRegistry
				.getInstance().getMarketListingAdapter(
						new MarketCountryKey(listing.getCountry(), listing
								.getMarket()));
		adapter.addListing(addListingRequest, addListingResponse);
		if (hasErrors(addListingResponse)) {
			return;
		}

		// TODO update DB
		try {
			Long productId = InventoryDALService.getInstance().createProduct(
					addListingResponse.getListing().getProduct());
			addListingResponse.getListing().getProduct().setProductId(productId);
			ListingDALService.getInstance().createListing(
					addListingResponse.getListing());
		} catch (DBConnectionException e) {

		} catch (FinderException e) {

		} catch (InsertException e) {

		} catch (DatabaseException e) {

		} catch (UpdateException e) {

		}

	}

	private void normalizeProduct(AddListingRequest addListingRequest) {
		Listing listing = addListingRequest.getListing();
		Product product = listing.getProduct();
		if(product != null){
			//Request already has product details so return as is.
			return;
		}
		/*product = new Product();
		listing.setProduct(product);
		product.setTitle();
		product.s*/
	}
}