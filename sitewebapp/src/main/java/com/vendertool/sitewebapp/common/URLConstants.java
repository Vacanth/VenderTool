package com.vendertool.sitewebapp.common;

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
	public static final String CONFIRM_ACCOUNT_PATH = "/confirmaccount";
	public static final String FILE_UPLOAD_PATH = "/fps/upload";
	public static final String JOB_CREATE_PATH = "/fps/uploadDone";
	public static final String WS_METADATA_GET_LANGUAGES_PATH = "/metadata/getSupportedLanguages";
	public static final String WS_REGISTRATION_UPDATE_PROFILE_PATH = "/registration/updateAccount";
	public static final String WS_REGISTRATION_CHANGE_EMAIL_PATH = "/registration/changeEmail";
	public static final String WS_REGISTRATION_CHANGE_PASSWORD_PATH = "/registration/changePassword";
	public static final String WS_METADATA_GET_SEC_QUESTIONS_PATH = "/metadata/getSecurityQuestions";
	public static final String WS_REGISTRATION_UPDATE_SEC_QUESTIONS_PATH = "/registration/updateSecurityQuestions";
	
	/**
	 * Don't start these urls with "/"
	 */
	public static final String HOME = "home";
	public static final String PROFILE = "profile";
	public static final String PROFILE_SAVE = "profile/save";
	public static final String PROFILE_EMAIL = "profile/email";
	public static final String PROFILE_EMAIL_SAVE = "profile/email/save";
	public static final String PROFILE_PASSWORD = "profile/password";
	public static final String PROFILE_PASSWORD_SAVE = "profile/password/save";
	public static final String ACCOUNT_HUB = "accounthub";
	public static final String UPLOADS = "accounthub/uploads";
	public static final String UPLOADS_RESPONSE = "accounthub/uploadsResponse";
	public static final String UPLOADER = "uploader";
}