package com.vendertool.registration.validation;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.ChangePasswordRequest;
import com.vendertool.sharedtypes.rnr.ChangePasswordResponse;

public class ChangePasswordValidator implements Validator {

	private static final Logger logger = Logger.getLogger(ChangePasswordValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	@Override
	public void validate(BaseRequest _request, BaseResponse _response) {
		logger.info(ChangePasswordValidator.class.getName() + ".validate()");
		
		ChangePasswordResponse response = (ChangePasswordResponse) _response;
		ChangePasswordRequest request = (ChangePasswordRequest) _request;
		
		if (VUTIL.isNull(request) || VUTIL.isNull(response)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
		
		String email = request.getEmail();
		if(VUTIL.isEmpty(email)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.INVALID_EMAIL_ID, 
					null, (String[])null);
			return;
		}
		
		String oldpassword = request.getOldPassword();
		if(VUTIL.isEmpty(oldpassword)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.PWD_CHANGE_MISSING_OLD_PWD, 
					null, (String[])null);
			return;
		}
		
		String password = request.getNewPassword();
		String confirmPassword = request.getConfirmPassword();
		
		// First validate password field & then the confirm password
		if (VUTIL.isNullOrEmpty(password)) {
			response.addFieldBindingError(Errors.REGISTRATION.MISSING_PASSWORD,
					null, (String[]) null);
			return;
		}

		if (!VUTIL.checkStringSize(password,
				RegistrationValidator.MIN_PASSWORD_LENGTH,
				RegistrationValidator.MAX_PASSWORD_LENGTH)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.PASSWORD_LENGTH_INCORRECT, null,
					(String[]) null);
			return;
		}

		if (!VUTIL.matchesPattern(RegistrationValidator.PASSWORD_REGEX,
				password)) {
			response.addFieldBindingError(Errors.REGISTRATION.INVALID_PASSWORD,
					null, (String[]) null);
			return;
		}

		// Now validate confirm password
		if (VUTIL.isNullOrEmpty(confirmPassword)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_CONFIRM_PASSWORD, null,
					(String[]) null);
			return;
		}

		if (!password.equals(confirmPassword)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.PASSWORD_CONFIRM_PASSWORD_MISMATCH,
					null, (String[]) null);
			return;
		}
	}

}
