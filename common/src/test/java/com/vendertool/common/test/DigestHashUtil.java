package com.vendertool.common.test;

import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.vendertool.common.SessionIdGenerator;

/**
 * This is just a convenience class to run as java/main
 */
public class DigestHashUtil {

	public static void main(String[] args) {
//        URL location = DigestHashUtil.class.getProtectionDomain().getCodeSource().getLocation();
//        String beanFilePath = location.getFile();
//        log("Current class file execution location: " + beanFilePath);
//        
//		String salt = SessionIdGenerator.getInstance().generateSessionId(false);
//		log("Salt (secure random id): '" + salt + "'.");
//		
//		String password = SessionIdGenerator.getInstance().generateSecureRandomId(256, 32);
//		log("Password (secure random id): '" + password + "'.");
//		
//		String hashedPassword = saltHashPassword(salt, password, beanFilePath);
//		log("Hashed password (secure random id): '" + hashedPassword + "'.");
		
		byte[] bytes = Base64.encodeBase64("7jgkcg5tts5fjp11j4e0vi2u1u:vubunbrgubb95f844qmmutqogr9b3bl426ove5rjvk45aq57d5q"
				.getBytes());
		
		log("base64 encoded: ");
		log(new String(bytes));
		
	}
	
	private static String saltHashPassword(String salt, String pwd, String beanFilePath) {
		ApplicationContext context = new 
				FileSystemXmlApplicationContext(beanFilePath + "TestSecurityBean.xml");
		MessageDigestPasswordEncoder encoder = (MessageDigestPasswordEncoder) context
				.getBean("testPasswordEncoder");
		
		String hashedPwd = encoder.encodePassword(pwd, salt);
		return hashedPwd;
	}
	
	private static void log(String msg) {
		System.err.println(msg);
	}
}
