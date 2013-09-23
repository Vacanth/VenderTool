package com.vendertool.common.test.dal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BaseTest {

	public ApplicationContext appContext;
	
	public BaseTest() {
		String ctxFileName = getApplicationContextFileName();
		if(ctxFileName != null) {
			new ClassPathXmlApplicationContext(ctxFileName);
		}
	}
	
	public abstract String getApplicationContextFileName();
	
	public ApplicationContext getApplicationContext() {
		return appContext;
	}
	
	public MessageSource getMessageSource() {
		return (MessageSource) appContext.getBean("messageSource");
	}
	
	protected void log(String msg) {
		System.err.println(msg);
	}
	
	protected void log(Throwable t) {
		t.printStackTrace();
	}
}
