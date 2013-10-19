package com.vendertool.sharedtypes.error;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListingErrorCode extends VTErrorCode implements Serializable {
	private static final long serialVersionUID = -6991602080050196818L;

	private static Set<String> ALL_ERROR_CODES = new HashSet<String>();

	public ListingErrorCode() {
		super("UNKNOWN");
	}

	private ListingErrorCode(String errorCode) {
		super(errorCode);
	}

	@Override
	public Set<String> getCachedErrorCodes() {
		return ALL_ERROR_CODES;
	}

	public static VTErrorCode UNABLE_TO_LIST = new ListingErrorCode(
			"UNABLE_TO_LIST");
	
	public static VTErrorCode LISTING_HAS_MARKET_ERRORS = new ListingErrorCode(
			"INVALID_PRODUCT");
	
	public static VTErrorCode LISTING_CONTAINER_IS_EMPTY = new ListingErrorCode(
			"LISTING_CONTAINER_IS_EMPTY");
	
	public static VTErrorCode ACCOUNT_ID_REQUIRED = new ListingErrorCode(
			"ACCOUNT_ID_REQUIRED");
	
	public static VTErrorCode TITLE_REQUIRED = new ListingErrorCode(
			"TITLE_REQUIRED");

	public static VTErrorCode PRODUCT_SKU_REQUIRED = new ListingErrorCode(
			"PRODUCT_SKU_REQUIRED");
	
	public static VTErrorCode PRODUCT_PRICE_REQUIRED = new ListingErrorCode(
			"PRODUCT_PRICE_REQUIRED");

	public static VTErrorCode PRODUCT_CODE_REQUIRED = new ListingErrorCode(
			"PRODUCT_CODE_REQUIRED");
	
	public static VTErrorCode PRODUCT_CONTAINER_IS_EMPTY = new ListingErrorCode(
			"PRODUCT_CONTAINER_IS_EMPTY");

	public static VTErrorCode START_TIME_INVALID = new ListingErrorCode(
			"START_TIME_INVALID");

	public static VTErrorCode CATEGORY_ID_INVALID = new ListingErrorCode(
			"CATEGORY_ID_INVALID");

	public static VTErrorCode BUYING_MODE_INVALID = new ListingErrorCode(
			"BUYING_MODE_INVALID");

	public static VTErrorCode AVAILABLE_QUANTITY_INVALID = new ListingErrorCode(
			"AVAILABLE_QUANTITY_INVALID");

	public static VTErrorCode ATTRIBUTES_INVALID = new ListingErrorCode(
			"ATTRIBUTES_INVALID");

	public static VTErrorCode VARIATION_ATTRIBUTE_COMBINATION_INVALID = new ListingErrorCode(
			"VARIATION_ATTRIBUTE_COMBINATION_INVALID");

	public static VTErrorCode REQUIRED_ATTIBUTES_MISSING_INVALID = new ListingErrorCode(
			"REQUIRED_ATTIBUTES_MISSING_INVALID");

	public static VTErrorCode LISTING_TYPE_ID_INVALID = new ListingErrorCode(
			"LISTING_TYPE_ID_INVALID");

	public static VTErrorCode LISTING_TYPE_ID_REQUIRES_PICTURES_INVALID = new ListingErrorCode(
			"LISTING_TYPE_ID_REQUIRES_PICTURES_INVALID");

	public static VTErrorCode SITE_ID_INVALID = new ListingErrorCode(
			"SITE_ID_INVALID");

	public static VTErrorCode SHOPPING_MODE_INVALID = new ListingErrorCode(
			"SHOPPING_MODE_INVALID");

	public static VTErrorCode ITEM_DESCRIPTION_MAX_EXCEEDED = new ListingErrorCode(
			"ITEM_DESCRIPTION_MAX_EXCEEDED");

	public static VTErrorCode MAX_PICTURE_COUNT_EXCEEDED = new ListingErrorCode(
			"MAX_PICTURE_COUNT_EXCEEDED");
}
