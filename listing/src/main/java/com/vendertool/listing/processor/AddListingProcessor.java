package com.vendertool.listing.processor;

import org.apache.log4j.Logger;

import com.vendertool.common.MarketCountryKey;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.marketplace.IMarketListingAdapter;
import com.vendertool.common.utils.StringUtils;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.inventory.dal.InventoryDALService;
import com.vendertool.inventory.processor.helper.InventoryHelper;
import com.vendertool.listing.ListingMarketAdapterRegistry;
import com.vendertool.listing.dal.ListingDALService;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;

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
		if (product.getAccountId() == null || product.getAccountId() <= 0) {
			// Add AccountID required
			addListingResponse.addFieldBindingError(
					Errors.LISTING.ACCOUNT_ID_REQUIRED, product.getClass()
							.getName(), "product");
		}

		if (product.getSku() == null || product.getSku().length() == 0) {
			// Add Sku required error
			addListingResponse.addFieldBindingError(
					Errors.LISTING.SKU_REQUIRED, product.getClass().getName(),
					"product");
		}

		if (InventoryHelper.getInstance().copyFromProductRequest(product)) {
			// Below validations are not required, since product contains only
			// SKU.
			return;
		}

		if (product.getTitle() == null || product.getTitle().length() == 0) {
			addListingResponse.addFieldBindingError(
					Errors.LISTING.TITLE_REQUIRED,
					product.getClass().getName(), "product");
		}

		if (product.getPrice() == null) {
			addListingResponse.addFieldBindingError(
					Errors.LISTING.PRODUCT_PRICE_REQUIRED, product.getClass()
							.getName(), "product");
		}

		if (product.getProductCode() == null
				|| product.getProductCode().length() == 0) {
			addListingResponse.addFieldBindingError(
					Errors.LISTING.PRODUCT_CODE_REQUIRED, product.getClass()
							.getName(), "product");
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
		Product product = addListingRequest.getListing().getProduct();
		boolean productExist = false;
		if (InventoryHelper.getInstance().copyFromProductRequest(product)) {
			normalizeProduct(addListingRequest, addListingResponse);
			if (addListingResponse.hasErrors()) {
				response.setStatus(ResponseAckStatusEnum.FAILURE);
				return;
			}
			productExist = true;
		}
		// Make call to Mer
		Listing listing = addListingRequest.getListing();
		listing.setListingId(0L);// Always setListingId to 0.
		IMarketListingAdapter adapter = ListingMarketAdapterRegistry
				.getInstance().getMarketListingAdapter(
						new MarketCountryKey(listing.getCountry(), listing
								.getMarket()));
		adapter.addListing(addListingRequest, addListingResponse);
		if (hasErrors(addListingResponse)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return;
		}

		// TODO update DB
		try {
			Long productId = null;
			if (productExist) {
				productId = product.getProductId();
			} else {
				productId = InventoryDALService.getInstance().createProduct(
						addListingResponse.getListing().getProduct());
			}

			listing.getProduct().setProductId(productId);

			ListingDALService.getInstance().createListing(
					addListingResponse.getListing());
		} catch (DBConnectionException e) {

		} catch (FinderException e) {

		} catch (InsertException e) {

		} catch (DatabaseException e) {

		} catch (UpdateException e) {

		}
	}

	private void normalizeProduct(AddListingRequest addListingRequest,
			AddListingResponse addListingResponse) {
		Listing listing = addListingRequest.getListing();
		Product product = listing.getProduct();
		Product productFromDB = null;
		if (StringUtils.getInstance().isEmpty(product.getSku())) {
			productFromDB = InventoryDALService.getInstance().findBySKU(
					listing.getCreateOwnerId(), product.getSku());
			if (productFromDB == null) {
				addListingResponse.addFieldBindingError(
						Errors.LISTING.NO_MATCHING_PRODUCT_FOUND, product
								.getClass().getName(), "product");
				return;
			}
		} else if (product.getProductId() != null) {
			productFromDB = InventoryDALService.getInstance().findByProductId(
					listing.getCreateOwnerId(), product.getProductId());
		}

		// Now set the product which we find from the DB.
		listing.setProduct(productFromDB);
		listing.setListingId(0L);
	}
}