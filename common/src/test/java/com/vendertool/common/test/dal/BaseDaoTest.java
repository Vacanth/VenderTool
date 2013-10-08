package com.vendertool.common.test.dal;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;

public abstract class BaseDaoTest extends BaseTest {

	public ApplicationContext context;
	
	protected BaseDaoTest() {
		super();
		try {
			initialize();
		} catch (DBConnectionException e) {
			log(e);
		} catch (SQLException e) {
			log(e);
		}
	}
	
	public String getApplicationContextFileName() {
		return null;
	}
	
	public void initialize() throws DBConnectionException, SQLException {
		log("======== load the dal-module from classpath =======");
    	this.context = new ClassPathXmlApplicationContext("dal-module.xml");
    	
    	log("======== set up test data =======");
    	setupTestData();
    	
		log("======== connection pool init =======");
		initConnPool();
	}

	protected abstract void setupTestData();
	
	protected abstract void cleanup();

	protected abstract BaseDao getDao();
	
	protected void initConnPool() throws DBConnectionException, SQLException {
		//Connection pool set up
		Connection c = null;
		try {
			long start = System.currentTimeMillis();
			c = getDao().getConnection();
			long end = System.currentTimeMillis();
			log("Connection pool set up time: " + (end - start));
		} finally {
			c.close();
		}
	}
}
