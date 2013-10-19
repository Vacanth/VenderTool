package com.vendertool.sharedapp.security;

import org.springframework.context.ApplicationContext;

import com.vendertool.sharedapp.SpringApplicationContextUtils;

public class CleintCredentialsUtil {
	private static ApplicationContext ctx = SpringApplicationContextUtils
			.getApplicationContext();
	private static CleintCredentials credentials = (CleintCredentials) ctx
			.getBean("webserviceClientCredentials");

	public static CleintCredentials getWebServiceCredentials() {
		return credentials;
	}
}
