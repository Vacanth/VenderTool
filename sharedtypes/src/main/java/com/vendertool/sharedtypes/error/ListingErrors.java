package com.vendertool.sharedtypes.error;

public class ListingErrors {
	private static final VTErrorDomainEnum DOMAIN = VTErrorDomainEnum.LISTING;
	private static ListingErrors INSTANCE = null;

	public static synchronized ListingErrors getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ListingErrors();
		}
		return INSTANCE;
	}

	private ListingErrors() {
	}

	// Define errors here
	public VTError LISTING_HAS_MARKET_ERRORS = new VTError(
			ListingErrorCode.LISTING_HAS_MARKET_ERRORS,
			"Unbale to list due to market errors", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError LISTING_CONTAINER_IS_EMPTY = new VTError(
			ListingErrorCode.LISTING_CONTAINER_IS_EMPTY,
			"Provided Listing Container is Empty", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError ACCOUNT_ID_REQUIRED = new VTError(
			ListingErrorCode.ACCOUNT_ID_REQUIRED,
			"Account Id Required", VTErrorSeverityEnum.ERROR,
			DOMAIN);
	
	public VTError TITLE_REQUIRED = new VTError(
			ListingErrorCode.TITLE_REQUIRED,
			"Title Required", VTErrorSeverityEnum.ERROR,
			DOMAIN);
	
	public VTError NO_MATCHING_PRODUCT_FOUND = new VTError(
			ListingErrorCode.NO_MATCHING_PRODUCT_FOUND,
			"No Matching Product Found", VTErrorSeverityEnum.ERROR,
			DOMAIN);
	
	public VTError SKU_REQUIRED = new VTError(
			ListingErrorCode.TITLE_REQUIRED,
			"Title Required", VTErrorSeverityEnum.ERROR,
			DOMAIN);
	
	public VTError PRODUCT_PRICE_REQUIRED = new VTError(
			ListingErrorCode.PRODUCT_PRICE_REQUIRED,
			"Product Price Required", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError PRODUCT_CODE_REQUIRED = new VTError(
			ListingErrorCode.PRODUCT_CODE_REQUIRED,
			"Product code is required", VTErrorSeverityEnum.ERROR,
			DOMAIN);
	
	public VTError PRODUCT_SKU_REQUIRED = new VTError(
			ListingErrorCode.PRODUCT_SKU_REQUIRED,
			"Product SKU Required", VTErrorSeverityEnum.ERROR,
			DOMAIN);
	
	public VTError PRODUCT_CONTAINER_IS_EMPTY = new VTError(
			ListingErrorCode.PRODUCT_CONTAINER_IS_EMPTY,
			"Provided Product Container is Empty", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError START_TIME_INVALID = new VTError(
			ListingErrorCode.START_TIME_INVALID, "Start time is invalid",
			VTErrorSeverityEnum.ERROR, DOMAIN);

	public VTError CATEGORY_ID_INVALID = new VTError(
			ListingErrorCode.CATEGORY_ID_INVALID, "Category ID invalid.",
			VTErrorSeverityEnum.ERROR, DOMAIN);

	public VTError BUYING_MODE_INVALID = new VTError(
			ListingErrorCode.BUYING_MODE_INVALID,
			"Buying mode is invalid.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError AVAILABLE_QUANTITY_INVALID = new VTError(
			ListingErrorCode.AVAILABLE_QUANTITY_INVALID,
			"Provided available quantity is invalid.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError ATTRIBUTES_INVALID = new VTError(
			ListingErrorCode.ATTRIBUTES_INVALID,
			"Item attribute is invalid.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError VARIATION_ATTRIBUTE_COMBINATION_INVALID = new VTError(
			ListingErrorCode.VARIATION_ATTRIBUTE_COMBINATION_INVALID,
			"Item Attribute Combinations are invalid.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError REQUIRED_ATTIBUTES_MISSING_INVALID = new VTError(
			ListingErrorCode.REQUIRED_ATTIBUTES_MISSING_INVALID,
			"Category is required atribute.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError LISTING_TYPE_ID_INVALID = new VTError(
			ListingErrorCode.LISTING_TYPE_ID_INVALID,
			"Provided Listing Type Id is invalid.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError LISTING_TYPE_ID_REQUIRES_PICTURES_INVALID = new VTError(
			ListingErrorCode.LISTING_TYPE_ID_REQUIRES_PICTURES_INVALID,
			"Pictures is required.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError SITE_ID_INVALID = new VTError(
			ListingErrorCode.SITE_ID_INVALID,
			"Provided SiteId is invalid.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError SHOPPING_MODE_INVALID = new VTError(
			ListingErrorCode.SHOPPING_MODE_INVALID,
			"Shipping mode is invalid.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError ITEM_DESCRIPTION_MAX_EXCEEDED = new VTError(
			ListingErrorCode.ITEM_DESCRIPTION_MAX_EXCEEDED,
			"Number of characters exceeded.", VTErrorSeverityEnum.ERROR,
			DOMAIN);

	public VTError MAX_PICTURE_COUNT_EXCEEDED = new VTError(
			ListingErrorCode.MAX_PICTURE_COUNT_EXCEEDED,
			"Number of images exceeded.", VTErrorSeverityEnum.ERROR,
			DOMAIN);
}
