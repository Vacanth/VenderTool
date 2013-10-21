package com.vendertool.sharedapp;

public interface URLConstants {
	public static final String QUERY_PARAM_SEPARATOR = "&";
	public static final String QUERY_START = "?";
	public static final String PARAM_KEY_VALUE_SEPARATOR = "=";
	public static final String HTTP = "http://";
	public static final String PORT_SEPERATOR = ":";
	
	public static final String USERNAME_KEY = "email";
	public static final String WEB_SERVICE_PATH = "/services";
	public static final String WS_REGISTRATION_GET_ACCOUNT_PATH = "/registration/getAccount";
	public static final String WS_REGISTRATION_GET_ACCOUNT_PWD_PATH = "/registration/getAccountPassword";
	public static final String WS_REGISTRATION_REGISTER_PATH = "/registration/register";
	public static final String WS_REGISTRATION_CONFIRM_PATH = "/registration/confirmRegistration";
	public static final String WS_CONFIRM_EMAIL_PATH = "/registration/confirmEmail";
	public static final String CONFIRM_ACCOUNT_PATH = "/confirmaccount";
	public static final String FILE_UPLOAD_PATH = "/fps/upload";
	public static final String JOB_CREATE_PATH = "/fps/uploadDone";
	public static final String JOB_PROCESS_PATH = "/fps/processJob";
	public static final String JOB_DETAILS_PATH = "/fps/uploadedJobs";
	public static final String WS_METADATA_GET_LANGUAGES_PATH = "/metadata/getSupportedLanguages";
	public static final String WS_REGISTRATION_UPDATE_PROFILE_PATH = "/registration/updateAccount";
	public static final String WS_REGISTRATION_CHANGE_EMAIL_PATH = "/registration/changeEmail";
	public static final String WS_REGISTRATION_CHANGE_PASSWORD_PATH = "/registration/changePassword";
	public static final String WS_METADATA_GET_SEC_QUESTIONS_PATH = "/metadata/getSecurityQuestions";
	public static final String WS_REGISTRATION_UPDATE_SEC_QUESTIONS_PATH = "/registration/updateSecurityQuestions";
}