package com.vendertool.registration.validation;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.validation.EmailRegexValidator;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.registration.dal.RegistrationDALService;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.ChangeEmailRequest;
import com.vendertool.sharedtypes.rnr.ChangeEmailResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;

public class ChangeEmailValidator implements Validator {

	private static final Logger logger = Logger.getLogger(ChangeEmailValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	private RegistrationDALService dalservice;
	
	public ChangeEmailValidator() {
		dalservice = RegistrationDALService.getInstance();
	}
	
	@Override
	public void validate(BaseRequest _request, BaseResponse _response) {
		logger.info(ChangeEmailValidator.class.getName() + ".validate()");
		
		ChangeEmailResponse response = (ChangeEmailResponse) _response;
		ChangeEmailRequest request = (ChangeEmailRequest) _request;
		
		if (VUTIL.isNull(request) || VUTIL.isNull(response)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
		
		String oldemail = request.getOldEmailId();
		if (VUTIL.isEmpty(oldemail)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_OLD_EMAIL, null,
					(String[]) null);
			return;
		}
		
		String newemail = request.getNewEmail();
		if(VUTIL.isEmpty(newemail)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.EMAIL_MISSING, null,
					(String[]) null);
			return;
		}
		
		if(!VUTIL.matchesPattern(EmailRegexValidator.SIMPLE_EMAIL_PATTERN, newemail)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.INVALID_EMAIL_ID, null,
					(String[]) null);
			return;
		}
		
		try {
			Long id = dalservice.getAccountId(newemail);
			if(id != null) {
				logger.debug("Username: '" + newemail
						+ "' already exists");
				response.addFieldBindingError(
						Errors.REGISTRATION.EMAIL_ALREADY_REGISTERED, null,
						(String[]) null);
				return;
			}
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_CHANGE_EMAIL, null, (String[])null);
			return;
		}
		
		String confirmEmail = request.getConfirmEmail();
		if(VUTIL.isEmpty(confirmEmail)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_CONFIRM_EMAIL, null,
					(String[]) null);
			return;
		}
		
		if(!newemail.equals(confirmEmail)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.CONFIRM_EMAIL_MISMATCH, null,
					(String[]) null);
			return;
		}
	}

}
