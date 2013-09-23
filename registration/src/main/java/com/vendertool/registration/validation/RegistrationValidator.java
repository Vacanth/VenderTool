package com.vendertool.registration.validation;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.validation.EmailRegexValidator;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.registration.dal.RegistrationDALService;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.RegisterAccountRequest;

public class RegistrationValidator implements Validator {
	
	private static final Logger logger = Logger.getLogger(RegistrationValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	public static int MIN_PASSWORD_LENGTH = 8;
	public static int MAX_PASSWORD_LENGTH = 25;
//	private static String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	//keep it simple for now (1 upper, 1 lower & a digit)
	public static String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{7,24}$";
	private RegistrationDALService dalservice;
	
	
	public RegistrationValidator() {
		dalservice = RegistrationDALService.getInstance();
	}
	
	public void validate(BaseRequest _request, BaseResponse response) {
		logger.info(RegistrationValidator.class.getName() + ".validate()");
		
		RegisterAccountRequest request = (RegisterAccountRequest) _request;
		
		if (VUTIL.isNull(request)
				|| VUTIL.isNull(response)
				|| VUTIL.isNull(request.getAccount())
				|| VUTIL.isNull(request.getAccount()
						.getContactDetails())) {
			logger.debug("NULL value passed to register an account");
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
		
		
		Account account = request.getAccount();
		validateName(account, response);
		validateEmail(account, response);
		validatePassword(account, response);
	}

	private void validateName(Account account, BaseResponse response) {
		//combine both errors together before returning
		if(VUTIL.isNullOrEmpty(account.getContactDetails().getFirstName())) {
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_FIRSTNAME, 
					account.getContactDetails().getClass().getName(),
					"firstName");
		}
		
		if(VUTIL.isNullOrEmpty(account.getContactDetails().getLastName())) {
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_LASTNAME,
					account.getContactDetails().getClass().getName(),
					"lastName");
		}
		
		return;
	}

	private void validatePassword(Account account, BaseResponse response) {
		String password = account.getPassword();
		String confirmPassword = account.getConfirmPassword();
		
		//First validate password field & then the confirm password
		if(VUTIL.isNullOrEmpty(password)) {
			response.addFieldBindingError(Errors.REGISTRATION.MISSING_PASSWORD, account.getClass().getName(), "password");
			return;
		}
		
		if(!VUTIL.checkStringSize(password, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH)) {
			response.addFieldBindingError(Errors.REGISTRATION.PASSWORD_LENGTH_INCORRECT, account.getClass().getName(), "password");
			return;
		}
		
		if(!VUTIL.matchesPattern(PASSWORD_REGEX, password)) {
			response.addFieldBindingError(Errors.REGISTRATION.INVALID_PASSWORD, account.getClass().getName(), "password");
			return;
		}
		
		//Now validate confirm password
		if(VUTIL.isNullOrEmpty(confirmPassword)) {
			response.addFieldBindingError(Errors.REGISTRATION.MISSING_CONFIRM_PASSWORD, account.getClass().getName(), "confirmPassword");
			return;
		}
		
		if(!password.equals(confirmPassword)) {
			response.addFieldBindingError(Errors.REGISTRATION.PASSWORD_CONFIRM_PASSWORD_MISMATCH, account.getClass().getName(), "confirmPassword");
			return;
		}
	}

	private void validateEmail(Account account, BaseResponse response) {
		if(VUTIL.isNullOrEmpty(account.getEmailId())) {
			response.addFieldBindingError(Errors.REGISTRATION.EMAIL_MISSING, account.getClass().getName(), "emailId");
			return;
		}
		
		if(!VUTIL.matchesPattern(EmailRegexValidator.SIMPLE_EMAIL_PATTERN, account.getEmailId())) {
			response.addFieldBindingError(Errors.REGISTRATION.INVALID_EMAIL_ID, account.getClass().getName(), "emailId");
		}
		
		try {
			Long id = dalservice.getAccountId(account.getEmailId());
			if(id != null) {
				logger.debug("Username: '" + account.getEmailId()
						+ "' already exists");
				response.addFieldBindingError(
						Errors.REGISTRATION.EMAIL_ALREADY_REGISTERED, account
								.getClass().getName(), "emailId");
			}
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_REGISTER, null, (String[])null);
		}
	}
}
