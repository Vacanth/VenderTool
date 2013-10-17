package com.vendertool.sharedtypes.error;

public class RegistrationErrors {
	private static final VTErrorDomainEnum DOMAIN = VTErrorDomainEnum.REGISTRATION;
	
	private static class RegistrationErrorsHolder {
		private static final RegistrationErrors INSTANCE = new RegistrationErrors();
	}
	
	public static RegistrationErrors getInstance(){
		return RegistrationErrorsHolder.INSTANCE;
	}
	
	private RegistrationErrors(){}
	
	//Define errors here
	public VTError UNABLE_TO_REGISTER = new VTError(
			RegistrationErrorCode.UNABLE_TO_REGISTER, 
			"Unable to register", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError EMAIL_MISSING = new VTError(
			RegistrationErrorCode.EMAIL_MISSING, 
			"Email is required", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_EMAIL_ID = new VTError(
			RegistrationErrorCode.INVALID_EMAIL_ID, 
			"Email id is not in a valid format", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_LASTNAME = new VTError(
			RegistrationErrorCode.INVALID_LASTNAME, 
			"Last name has invalid characters", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_FIRSTNAME = new VTError(
			RegistrationErrorCode.INVALID_FIRSTNAME, 
			"First name has invalid characters", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_PHONE_NUMBER = new VTError(
			RegistrationErrorCode.INVALID_PHONE_NUMBER, 
			"Invalid phone number", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_ADDRESS_FIRSTLINE = new VTError(
			RegistrationErrorCode.MISSING_ADDRESS_FIRSTLINE, 
			"Address line one is missing", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_ADDRESS_COUNTRY_ZIP = new VTError(
			RegistrationErrorCode.INVALID_ADDRESS_COUNTRY_ZIP, 
			"Invalid address country and zip combination", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_ADDRESS_STATE_PROVINCE = new VTError(
			RegistrationErrorCode.INVALID_ADDRESS_STATE_PROVINCE, 
			"Invalid address state or province", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_ADDRESS_CITY_PROVINCE = new VTError(
			RegistrationErrorCode.INVALID_ADDRESS_CITY_PROVINCE, 
			"Invalid address city or province", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError EMAIL_ALREADY_REGISTERED = new VTError(
			RegistrationErrorCode.EMAIL_ALREADY_REGISTERED, 
			"Email address already exists", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_PASSWORD = new VTError(
			RegistrationErrorCode.MISSING_PASSWORD, 
			"Password is required", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError PASSWORD_LENGTH_INCORRECT = new VTError(
			RegistrationErrorCode.PASSWORD_LENGTH_INCORRECT, 
			"Password length is incorrect", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_CONFIRM_PASSWORD = new VTError(
			RegistrationErrorCode.MISSING_CONFIRM_PASSWORD, 
			"Confirm password is required", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_PASSWORD = new VTError(
			RegistrationErrorCode.INVALID_PASSWORD, 
			"Invalid password", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError PASSWORD_CONFIRM_PASSWORD_MISMATCH = new VTError(
			RegistrationErrorCode.PASSWORD_CONFIRM_PASSWORD_MISMATCH, 
			"Password Field and confirm password fields don't match", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError ACCOUNT_NOT_FOUND = new VTError(
			RegistrationErrorCode.ACCOUNT_NOT_FOUND, 
			"Account not found", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MAX_ACCOUNT_RECONFIRM_ATTEMPTS_REACHED = new VTError(
			RegistrationErrorCode.MAX_ACCOUNT_RECONFIRM_ATTEMPTS_REACHED, 
			"Max attempts reach to confirm the account", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNAUTHORIZED_ACCOUNT_CONFIRMATION = new VTError(
			RegistrationErrorCode.UNAUTHORIZED_ACCOUNT_CONFIRMATION, 
			"Unauthorized account confirmation attempt", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_FIRSTNAME = new VTError(
			RegistrationErrorCode.MISSING_FIRSTNAME, 
			"First name is required", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_LASTNAME = new VTError(
			RegistrationErrorCode.MISSING_LASTNAME, 
			"Last name is required", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_SECURITY_QUESTION = new VTError(
			RegistrationErrorCode.MISSING_SECURITY_QUESTION, 
			"Please select a question", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_SECURITY_ANSWER = new VTError(
			RegistrationErrorCode.MISSING_SECURITY_ANSWER, 
			"Answer is required", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError LANGUAGE_NOT_SUPPORTED = new VTError(
			RegistrationErrorCode.LANGUAGE_NOT_SUPPORTED, 
			"Language is not supported", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError PWD_CHANGE_MISSING_OLD_PWD = new VTError(
			RegistrationErrorCode.PWD_CHANGE_MISSING_OLD_PWD, 
			"Missing old password", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_CHANGE_PASSWORD = new VTError(
			RegistrationErrorCode.UNABLE_TO_CHANGE_PASSWORD, 
			"Unable to change password", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_VERIFY_PASSWORD = new VTError(
			RegistrationErrorCode.UNABLE_TO_VERIFY_PASSWORD, 
			"Unable to verify password", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_CHANGE_EMAIL = new VTError(
			RegistrationErrorCode.UNABLE_TO_CHANGE_EMAIL, 
			"Unable to change email", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_OLD_PASSWORD = new VTError(
			RegistrationErrorCode.INVALID_OLD_PASSWORD, 
			"Invalid old password", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_OLD_EMAIL = new VTError(
			RegistrationErrorCode.MISSING_OLD_EMAIL, 
			"Missing old email", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError MISSING_CONFIRM_EMAIL = new VTError(
			RegistrationErrorCode.MISSING_CONFIRM_EMAIL, 
			"Missing confirm email", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError CONFIRM_EMAIL_MISMATCH = new VTError(
			RegistrationErrorCode.CONFIRM_EMAIL_MISMATCH, 
			"Email and confirm email are not matching", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_RETRIEVE_ACCOUNT_INFORMATION = new VTError(
			RegistrationErrorCode.UNABLE_TO_RETRIEVE_ACCOUNT_INFORMATION, 
			"Email and confirm email are not matching", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_CLOSE_ACCOUNT = new VTError(
			RegistrationErrorCode.UNABLE_TO_CLOSE_ACCOUNT, 
			"Unable to close account", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_UPDATE_SEC_QUESTIONS = new VTError(
			RegistrationErrorCode.UNABLE_TO_UPDATE_SEC_QUESTIONS, 
			"Unable to update the security questions", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_GET_SEC_QUESTIONS = new VTError(
			RegistrationErrorCode.UNABLE_TO_GET_SEC_QUESTIONS, 
			"Unable to get security questions", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_UPDATE_ACCOUNT = new VTError(
			RegistrationErrorCode.UNABLE_TO_UPDATE_ACCOUNT, 
			"Unable to update account", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_CONFIRM_EMAIL = new VTError(
			RegistrationErrorCode.UNABLE_TO_CONFIRM_EMAIL, 
			"Unable to confirm email", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError UNABLE_TO_VERIFY_SECURITY_ANSWERS = new VTError(
			RegistrationErrorCode.UNABLE_TO_VERIFY_SECURITY_ANSWERS, 
			"Unable to confirm email", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError INVALID_SECURITY_QUESTIONS_ANSWERS = new VTError(
			RegistrationErrorCode.INVALID_SECURITY_QUESTIONS_ANSWERS, 
			"Invalid security questions and answers", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError TOO_MANY_PWD_ATTEMPTS_IN_ONE_HOUR = new VTError(
			RegistrationErrorCode.TOO_MANY_PWD_ATTEMPTS_IN_ONE_HOUR, 
			"Too many security question answer failure attempts in one hour", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError TOO_MANY_ACCOUNT_PWD_ATTEMPTS = new VTError(
			RegistrationErrorCode.TOO_MANY_ACCOUNT_PWD_ATTEMPTS, 
			"Too many security question answer failure attempts to your account", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
	
	public VTError SUSPENDED = new VTError(
			RegistrationErrorCode.SUSPENDED, 
			"Your account is suspended", 
			VTErrorSeverityEnum.ERROR, 
			DOMAIN);
}
