package com.vendertool.registration.validation;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.ConfirmEmailRequest;
import com.vendertool.sharedtypes.rnr.ConfirmEmailResponse;

public class ConfirmEmailValidator implements Validator {

	private static final Logger logger = Logger.getLogger(ConfirmEmailValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	@Override
	public void validate(BaseRequest _request, BaseResponse _response) {
		logger.info(ChangeEmailValidator.class.getName() + ".validate()");
		
		ConfirmEmailResponse response = (ConfirmEmailResponse) _response;
		ConfirmEmailRequest request = (ConfirmEmailRequest) _request;
		
		if (VUTIL.isNull(request) || VUTIL.isNull(response)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
		
		if (VUTIL.isEmpty(request.getEmail())
				|| VUTIL.isEmpty(request.getOldEmail())
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
