package com.vendertool.registration.test.dal;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import com.vendertool.common.SessionIdGenerator;
import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.test.dal.BaseDaoTest;
import com.vendertool.registration.dal.dao.AccountConfirmationDao;
import com.vendertool.registration.dal.dao.RegistrationDaoFactory;
import com.vendertool.registration.dal.dao.codegen.QAccountConfirmation;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.AccountConfirmation.AccountConfirmationStatusEnum;

public class AccountConfirmationDaoTest extends BaseDaoTest{

	private static final int ACCOUNT_COUNT = 2;
	private static final long ACCOUNT_ID = 600;
	private static final String DEFAULT_EMAIL = "testemail@test.com";
	
	AccountConfirmation[] accConfs;
	AccountConfirmationDao dao;
	QAccountConfirmation ac;
	
	protected AccountConfirmationDaoTest() {
		super();
	}
	
	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, FinderException, UpdateException, DeleteException {
		new AccountConfirmationDaoTest().testCRUD();
	}
	
	@Override
	protected void setupTestData() {
		ac = QAccountConfirmation.accountConfirmation;
		dao = (AccountConfirmationDao) getDao();
		
		accConfs = new AccountConfirmation[ACCOUNT_COUNT];
		for(int idx = 0; idx < ACCOUNT_COUNT; idx++) {
			accConfs[idx] = createAccountConf(idx);
		}
	}

	private AccountConfirmation createAccountConf(int idx) {
		AccountConfirmation accConf = new AccountConfirmation();
		accConf.setConfirmationAttempts(0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, idx); 
		
		accConf.setCreateDate(cal.getTime());
		cal.add(Calendar.DATE, 7);
		accConf.setExpiryDate(cal.getTime());
		
		accConf.setConfirmCode(SessionIdGenerator.getInstance().getRandomNumber(5));
		accConf.setConfirmSessionId(idx+"0002347234jlswnwei879807sd832nk9wfw");
		
		accConf.setEmail(DEFAULT_EMAIL);
		accConf.setStatus(AccountConfirmationStatusEnum.NOT_VERIFIED);
		
		return accConf;
	}
	
	public void testCRUD() throws DBConnectionException, InsertException,
			DatabaseException, SQLException, FinderException, UpdateException, DeleteException {
		
		//dal insert
		log("======== DAL insert =======");
		insert();
		
		log("======== DAL find latest account confirmation =======");
		AccountConfirmation dbaccConf = 
				dao.findLatestActive(ACCOUNT_ID, DEFAULT_EMAIL);
		Assert.assertNotNull(dbaccConf);
		log(dbaccConf.toString());
		Long id1 = dbaccConf.getId();
		
		log("======== DAL update attempts for one account =======");
		dao.updateConfirmationAttempts(ACCOUNT_ID, id1, 2);
		dbaccConf = dao.findLatestActive(ACCOUNT_ID, DEFAULT_EMAIL);
		Assert.assertNotNull(dbaccConf);
		Long id2 = dbaccConf.getId();
		Assert.assertEquals(id1, id2);
		log(dbaccConf.toString());
		
		
		log("======== DAL insert one more for first account id =======");
		AccountConfirmation nac = accConfs[0];
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1); //add  by 1 day
        nac.setCreateDate(cal.getTime());
        log("New date added is = " + cal.getTime());
		dao.insert(ACCOUNT_ID, accConfs[0]);
		
		log("======== DAL find latest account confirmation after new insert =======");
		dbaccConf = dao.findLatestActive(ACCOUNT_ID, DEFAULT_EMAIL);
		Assert.assertNotNull(dbaccConf);
		Long id3 = dbaccConf.getId();
		Assert.assertNotSame(id1, id3);
		log(dbaccConf.toString());
		
		
		log("======== DAL delete =======");
		long accNo = ACCOUNT_ID;
		int count = accConfs.length;
		for(int i=0; i<count; i++) {
			dao.delete(accNo++);
		}
		dbaccConf = dao.findLatestActive(ACCOUNT_ID, DEFAULT_EMAIL);
		Assert.assertNull(dbaccConf);
		
		log("******   TEST COMPLETE   ******");
	}

	private void insert() throws DBConnectionException, InsertException,
			DatabaseException {
		
		long aid = ACCOUNT_ID;
		for (AccountConfirmation aconf : accConfs) {
			dao.insert(aid, aconf);
			//So that the creation dates are different
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				log(e);
			}
		}
	}
	
	@Override
	protected void cleanup() {
		dao.cleanup();
	}

	@Override
	protected BaseDao getDao() {
		if(dao == null) {
			RegistrationDaoFactory factory = RegistrationDaoFactory.getInstance();
			dao = factory.getAccountConfirmationDao();
		}
		
		return dao;
	}
	
	@Override
	public String getApplicationContextFileName() {
		return "test-app-context.xml";
	}
}
