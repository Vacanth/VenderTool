package com.vendertool.common.test.dal;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vendertool.common.dal.exception.DBConnectionException;

public class BaseDALService {
	public ApplicationContext context;
	
	protected BaseDALService() {
		try {
			initialize();
		} catch (DBConnectionException e) {
			log(e);
		} catch (SQLException e) {
			log(e);
		}
	}
	
	public void initialize() throws DBConnectionException, SQLException {
		log("======== load the dal-module from classpath =======");
    	this.context = new ClassPathXmlApplicationContext("dal-module.xml");
	}
	
	protected void log(String msg) {
		System.err.println(msg);
	}
	
	protected void log(Throwable t) {
		t.printStackTrace();
	}
}
