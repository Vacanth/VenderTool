package com.vendertool.registration.test.dal;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.test.dal.BaseDaoTest;
import com.vendertool.registration.dal.dao.ForgotPasswordDao;
import com.vendertool.registration.dal.dao.RegistrationDaoFactory;
import com.vendertool.registration.dal.dao.codegen.QForgotPassword;
import com.vendertool.sharedtypes.core.ForgotPassword;
import com.vendertool.sharedtypes.core.ForgotPassword.ForgotPasswordStatusEnum;

public class ForgotPasswordDaoTest extends BaseDaoTest {

	private static final int COUNT = 10;
	private static final long ACCOUNT_ID = 600;
	private static final String DEFAULT_EMAIL = "testemail@test.com";
	
	ForgotPassword[] fps;
	ForgotPasswordDao dao;
	QForgotPassword f;
	
	
	protected ForgotPasswordDaoTest() {
		super();
	}
	
	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, FinderException,
			UpdateException, DeleteException {
		
		new ForgotPasswordDaoTest().testCRUD();
	}
	
	private void testCRUD() throws DBConnectionException, InsertException,
			DatabaseException, FinderException, DeleteException {
		//dal insert
		log("======== DAL insert =======");
		insert();
		
		log("======== DAL find count =======");
		long count = fetchCountFromDB();
		Assert.assertEquals(count, COUNT);
		log("Total count from DB: " + count);
		log("Inserted count: " + COUNT);
		
		log("======== DAL delete =======");
		dao.deleteByAccountId(ACCOUNT_ID);
		count = fetchCountFromDB();
		Assert.assertEquals(count, 0);
		log("Total count from DB: " + count);
		log("Inserted count: " + COUNT);
		
		log("******   TEST COMPLETE   ******");
	}

	private long fetchCountFromDB() throws DBConnectionException,
			FinderException, DatabaseException {
		Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -1);
        Date stdt = cal.getTime();
        
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 1);
        Date eddt = cal.getTime();
        
		long count = dao.findTotalByDateRange(DEFAULT_EMAIL, stdt, eddt,
				ForgotPasswordStatusEnum.ATTEMPTED);
		return count;
	}

	private void insert() throws DBConnectionException, InsertException, DatabaseException {
		for (ForgotPassword fp : fps) {
			dao.insert(fp);
		}
	}

	@Override
	protected void setupTestData() {
		f = QForgotPassword.forgotPassword;
		dao = (ForgotPasswordDao) getDao();
		
		fps = new ForgotPassword[COUNT];
		for(int idx = 0; idx < COUNT; idx++) {
			fps[idx] = createForgotPassword(idx);
		}
	}

	private ForgotPassword createForgotPassword(int idx) {
		ForgotPassword fp = new ForgotPassword();
		fp.setAccountId(ACCOUNT_ID);
		fp.setCreatedDate(new Date());
		fp.setEmail(DEFAULT_EMAIL);
		fp.setIpAddress("127.0.0.1");
		fp.setStatus(ForgotPasswordStatusEnum.ATTEMPTED);
		
		return fp;
	}

	@Override
	protected void cleanup() {
		// XXX Auto-generated method stub

	}

	@Override
	protected BaseDao getDao() {
		if(dao == null) {
			RegistrationDaoFactory factory = RegistrationDaoFactory.getInstance();
			dao = factory.getForgotPasswordDao();
		}
		
		return dao;
	}

}
