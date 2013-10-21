package com.vendertool.registration.validation;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.ConfirmForgotPasswordEmailRequest;

public class ConfirmForgotPasswordEmailValidator implements Validator {

	private static final Logger logger = Logger.getLogger(ConfirmForgotPasswordEmailValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	@Override
	public void validate(BaseRequest _request, BaseResponse response) {
		logger.info(ConfirmForgotPasswordEmailValidator.class.getName() + ".validate()");
		
		ConfirmForgotPasswordEmailRequest request = (ConfirmForgotPasswordEmailRequest) _request;
		
		if (VUTIL.isNull(request) || VUTIL.isNull(response)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
		
		if (VUTIL.isEmpty(request.getEmail())
				|| VUTIL.isEmpty(request.getConfirmSessionId())
				|| VUTIL.isNull(request.getConfirmCode())) {
			
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.UNAUTHORIZED_ACCOUNT_CONFIRMATION,
					null, (String[]) null);
			return;
		}
	}
}
