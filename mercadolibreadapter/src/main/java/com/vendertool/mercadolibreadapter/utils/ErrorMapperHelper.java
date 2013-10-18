package com.vendertool.mercadolibreadapter.utils;

import java.util.List;

import com.vendertool.mercadolibreadapter.add.ErrorDetail;
import com.vendertool.mercadolibreadapter.add.ErrorResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse;

public class ErrorMapperHelper {

	private ErrorMapperHelper() {

	}

	private static class ErrorMapperHelperHolder {
		private final static ErrorMapperHelper INSTANCE = new ErrorMapperHelper();
	}

	public static ErrorMapperHelper getInstance() {
		return ErrorMapperHelperHolder.INSTANCE;
	}
	private static ErrorMapper mapper = ErrorMapper.getInstance();

	public void populateErrors(BaseResponse response, ErrorResponse vtErrorResp) {
		if (response == null || vtErrorResp == null) {
			// Throw exception
		}
		List<ErrorDetail> errorDetails = vtErrorResp.getCause();

		if (errorDetails == null
				|| (errorDetails != null && errorDetails.size() == 0)) {
			response.addFieldBindingError(mapper.getVTError(vtErrorResp.getError()),
					"", "");
			return;
		}


		for (ErrorDetail error : errorDetails) {
			//TODO set field class names?
			response.addFieldBindingError(mapper.getVTError(error.getCode()),
					"", "");
		}
	}
}