package com.vendertool.mercadolibreadapter.utils;

import java.util.HashMap;
import java.util.Map;

import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.error.VTError;

public class ErrorMapper {

	private Map<String, VTError> ML_TO_VT_ERROR_MAP;

	private ErrorMapper() {
		ML_TO_VT_ERROR_MAP = new HashMap<String, VTError>();
		init();
	}

	private static class ErrorMapperHolder {
		private final static ErrorMapper INSTANCE = new ErrorMapper();
	}

	public static ErrorMapper getInstance() {
		return ErrorMapperHolder.INSTANCE;
	}

	private void init() {

		// *** Listing Errors ***
		ML_TO_VT_ERROR_MAP.put("item.start_time.invalid",
				Errors.LISTING.START_TIME_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.category_id.invalid",
				Errors.LISTING.CATEGORY_ID_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.buying_mode.invalid",
				Errors.LISTING.BUYING_MODE_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.available_quantity.invalid",
				Errors.LISTING.AVAILABLE_QUANTITY_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.attributes.invalid",
				Errors.LISTING.ATTRIBUTES_INVALID);
		ML_TO_VT_ERROR_MAP.put(
				"item.variations.attribute_combinations.invalid",
				Errors.LISTING.VARIATION_ATTRIBUTE_COMBINATION_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.attributes.missing_required",
				Errors.LISTING.REQUIRED_ATTIBUTES_MISSING_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.listing_type_id.invalid",
				Errors.LISTING.LISTING_TYPE_ID_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.listing_type_id.requiresPictures",
				Errors.LISTING.LISTING_TYPE_ID_REQUIRES_PICTURES_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.site_id.invalid",
				Errors.LISTING.SITE_ID_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.shipping.mode.invalid",
				Errors.LISTING.SHOPPING_MODE_INVALID);
		ML_TO_VT_ERROR_MAP.put("item.description.max",
				Errors.LISTING.ITEM_DESCRIPTION_MAX_EXCEEDED);
		ML_TO_VT_ERROR_MAP.put("item.pictures.max",
				Errors.LISTING.MAX_PICTURE_COUNT_EXCEEDED);
		ML_TO_VT_ERROR_MAP.put("min.notmet",
				Errors.LISTING.AVAILABLE_QUANTITY_INVALID);
		// **** Other Error Mappers
	}

	public VTError getVTError(String mlErrorCode) {
		VTError vtError = ML_TO_VT_ERROR_MAP.get(mlErrorCode);
		return vtError == null ? Errors.COMMON.UNMAPPED_ERROR : vtError;
	}
}