package com.vendertool.sitewebapp.common;

import java.util.Locale;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/**
 * This is how you get access to resource like messages.properties 
 * from Java
 *
 */
public class MsgSource {
	@Autowired
	private MessageSource messageSource;
	
	private static final Logger logger = Logger.getLogger(MsgSource.class);
	
	private MessageSource getMessageSource() {
//		WebApplicationContext webAppContext = ContextLoader.getCurrentWebApplicationContext();
		ApplicationContext ctx = SpringApplicationContextUtils.getApplicationContext();
		MessageSource ms = (MessageSource) ctx.getBean("messageSource");
		return ms;
	}
	
	/**
	 * This reads the message property value from messages.properties files (or any property file that is in the classpath)
	 * @param propertyName
	 */
	public String getMessage(String propertyName, Object[] args, Locale locale){
		if((propertyName == null) || (propertyName.trim().isEmpty())) {
			return null;
		}
		try {
			MessageSource ms = getMessageSource();
			return (ms != null) ? ms.getMessage(propertyName, args, locale) : null;
		} catch (NoSuchMessageException nsmex) {
			logger.log(Level.DEBUG, "No message found for property: " + propertyName, nsmex);
		}
		return null;
	}
}
