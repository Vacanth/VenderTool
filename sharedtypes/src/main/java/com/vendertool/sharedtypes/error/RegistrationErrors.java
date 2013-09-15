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
	
	public VTError INVALID_PHONE_FORMAT = new VTError(
			RegistrationErrorCode.INVALID_PHONE_FORMAT, 
			"Invalid phone number fomat", 
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
	
}
