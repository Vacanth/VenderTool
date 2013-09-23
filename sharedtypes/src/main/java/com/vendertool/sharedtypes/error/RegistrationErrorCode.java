package com.vendertool.sharedtypes.error;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrationErrorCode extends VTErrorCode implements Serializable{

	private static final long serialVersionUID = 6659050189299043583L;
	
	private static Set<String> ALL_ERROR_CODES = new HashSet<String>();
	
	public RegistrationErrorCode(){}
	
	private RegistrationErrorCode(String errorCode) {
		super(errorCode);
	}
	
	@Override
	public Set<String> getCachedErrorCodes() {
		return ALL_ERROR_CODES;
	}

	public static VTErrorCode UNABLE_TO_REGISTER = new RegistrationErrorCode(
			"UNABLE_TO_REGISTER");
	public static VTErrorCode EMAIL_MISSING = new RegistrationErrorCode(
			"EMAIL_REQUIRED");
	public static VTErrorCode INVALID_EMAIL_ID = new RegistrationErrorCode(
			"INVALID_EMAIL_ID");
	public static VTErrorCode INVALID_LASTNAME = new RegistrationErrorCode(
			"INVALID_LASTNAME");
	public static VTErrorCode INVALID_FIRSTNAME = new RegistrationErrorCode(
			"INVALID_FIRSTNAME");
	public static VTErrorCode INVALID_PHONE_NUMBER = new RegistrationErrorCode(
			"INVALID_PHONE_NUMBER");
	public static VTErrorCode MISSING_ADDRESS_FIRSTLINE = new RegistrationErrorCode(
			"MISSING_ADDRESS_FIRSTLINE");
	public static VTErrorCode INVALID_ADDRESS_COUNTRY_ZIP = new RegistrationErrorCode(
			"INVALID_ADDRESS_COUNTRY_ZIP");
	public static VTErrorCode INVALID_ADDRESS_STATE_PROVINCE = new RegistrationErrorCode(
			"INVALID_ADDRESS_STATE_PROVINCE");
	public static VTErrorCode INVALID_ADDRESS_CITY_PROVINCE = new RegistrationErrorCode(
			"INVALID_ADDRESS_CITY_PROVINCE");
	public static VTErrorCode EMAIL_ALREADY_REGISTERED = new RegistrationErrorCode(
			"EMAIL_ALREADY_REGISTERED");
	public static VTErrorCode MISSING_PASSWORD = new RegistrationErrorCode(
			"MISSING_PASSWORD");
	public static VTErrorCode PASSWORD_LENGTH_INCORRECT = new RegistrationErrorCode(
			"PASSWORD_LENGTH_INCORRECT");
	public static VTErrorCode MISSING_CONFIRM_PASSWORD = new RegistrationErrorCode(
			"MISSING_CONFIRM_PASSWORD");
	public static VTErrorCode INVALID_PASSWORD = new RegistrationErrorCode(
			"INVALID_PASSWORD");
	public static VTErrorCode PASSWORD_CONFIRM_PASSWORD_MISMATCH = new RegistrationErrorCode(
			"PASSWORD_CONFIRM_PASSWORD_MISMATCH");
	public static VTErrorCode MISSING_FIRSTNAME = new RegistrationErrorCode(
			"MISSING_FIRSTNAME");
	public static VTErrorCode MISSING_LASTNAME = new RegistrationErrorCode(
			"MISSING_LASTNAME");
	public static VTErrorCode ACCOUNT_NOT_FOUND = new RegistrationErrorCode(
			"ACCOUNT_NOT_FOUND");
	public static VTErrorCode MAX_ACCOUNT_RECONFIRM_ATTEMPTS_REACHED = new RegistrationErrorCode(
			"MAX_ACCOUNT_RECONFIRM_ATTEMPTS_REACHED");
	public static VTErrorCode UNAUTHORIZED_ACCOUNT_CONFIRMATION = new RegistrationErrorCode(
			"UNAUTHORIZED_ACCOUNT_CONFIRMATION");
	public static VTErrorCode MISSING_SECURITY_QUESTION = new RegistrationErrorCode(
			"MISSING_SECURITY_QUESTION");
	public static VTErrorCode MISSING_SECURITY_ANSWER = new RegistrationErrorCode(
			"MISSING_SECURITY_ANSWER");
	public static VTErrorCode LANGUAGE_NOT_SUPPORTED = new RegistrationErrorCode(
			"LANGUAGE_NOT_SUPPORTED");
	public static VTErrorCode PWD_CHANGE_MISSING_OLD_PWD = new RegistrationErrorCode(
			"PWD_CHANGE_MISSING_OLD_PWD");
	public static VTErrorCode UNABLE_TO_CHANGE_PASSWORD = new RegistrationErrorCode(
			"UNABLE_TO_CHANGE_PASSWORD");
	public static VTErrorCode UNABLE_TO_CHANGE_EMAIL = new RegistrationErrorCode(
			"UNABLE_TO_CHANGE_EMAIL");
	public static VTErrorCode INVALID_OLD_PASSWORD = new RegistrationErrorCode(
			"INVALID_OLD_PASSWORD");
	public static VTErrorCode MISSING_OLD_EMAIL = new RegistrationErrorCode(
			"MISSING_OLD_EMAIL");
	public static VTErrorCode MISSING_CONFIRM_EMAIL = new RegistrationErrorCode(
			"MISSING_CONFIRM_EMAIL");
	public static VTErrorCode CONFIRM_EMAIL_MISMATCH = new RegistrationErrorCode(
			"CONFIRM_EMAIL_MISMATCH");
	public static VTErrorCode UNABLE_TO_RETRIEVE_ACCOUNT_INFORMATION = new RegistrationErrorCode(
			"CONFIRM_EMAIL_MISMATCH");
	public static VTErrorCode UNABLE_TO_CLOSE_ACCOUNT = new RegistrationErrorCode(
			"UNABLE_TO_CLOSE_ACCOUNT");
	public static VTErrorCode UNABLE_TO_UPDATE_SEC_QUESTIONS = new RegistrationErrorCode(
			"UNABLE_TO_UPDATE_SEC_QUESTIONS");
	public static VTErrorCode UNABLE_TO_GET_SEC_QUESTIONS = new RegistrationErrorCode(
			"UNABLE_TO_GET_SEC_QUESTIONS");
}
