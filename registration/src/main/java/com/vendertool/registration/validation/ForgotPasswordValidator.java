package com.vendertool.registration.validation;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.EmailRegexValidator;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.ForgotPasswordRequest;

public class ForgotPasswordValidator implements Validator {

	private static final Logger logger = Logger.getLogger(ForgotPasswordValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	@Override
	public void validate(BaseRequest _request, BaseResponse response) {
		ForgotPasswordRequest request = (ForgotPasswordRequest) _request;
		
		if (VUTIL.isNull(request)) {
			logger.debug("NULL value passed to processForgotPasswordEmail");
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
		
		validateEmail(request.getEmail(), response);
	}

	private void validateEmail(String email, BaseResponse response) {
		if(VUTIL.isNullOrEmpty(email)) {
			response.addFieldBindingError(Errors.REGISTRATION.EMAIL_MISSING, null, (String[])null);
			return;
		}
		
		if(!VUTIL.matchesPattern(EmailRegexValidator.SIMPLE_EMAIL_PATTERN, email)) {
			response.addFieldBindingError(Errors.REGISTRATION.INVALID_EMAIL_ID, null, (String[])null);
		}
	}
}
