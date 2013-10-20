package com.vendertool.sitewebapp.common;

import com.vendertool.sharedapp.URLConstants;

public interface WebURLConstants extends URLConstants{

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
