package com.vendertool.registration.test.dal;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.test.dal.BaseDaoTest;
import com.vendertool.registration.dal.dao.PasswordHistoryDao;
import com.vendertool.registration.dal.dao.RegistrationDaoFactory;
import com.vendertool.registration.dal.dao.codegen.QPasswordHistory;

public class PasswordHistoryDaoTest extends BaseDaoTest {

	private static final int PWD_COUNT = 3;
	private static final long ACCOUNT_ID = 600;

	String[] passwords;
	String[] salts;
	PasswordHistoryDao dao;
	QPasswordHistory ph;

	protected PasswordHistoryDaoTest() {
		super();
	}

	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, FinderException,
			DeleteException {

		new PasswordHistoryDaoTest().testCRUD();
	}

	private void testCRUD() throws DBConnectionException, InsertException,
			DatabaseException, FinderException, DeleteException {
		
		// dal insert
		log("======== DAL insert =======");
		insert();

		log("======== DAL find all passwords inserted =======");
		List<String> dbpwds = dao.findAllByAccountId(ACCOUNT_ID);
		Assert.assertNotNull(dbpwds);
		for (String pwd : passwords) {
			Assert.assertTrue(dbpwds.contains(pwd));
			log("Password = " + pwd);
		}

		log("======== DAL find exact matching pwd =======");
		String dbpwd = dao.findByPassword(ACCOUNT_ID, passwords[0]);
		Assert.assertEquals(passwords[0], dbpwd);
		log("Find by password = " + dbpwd);

		log("======== DAL delete pwds =======");
		dao.deleteByAccountId(ACCOUNT_ID, null);

		dbpwds = dao.findAllByAccountId(ACCOUNT_ID);
		Assert.assertNull(dbpwds);

		log("******   TEST DONE!!!   ******");
	}

	private void insert() throws DBConnectionException, InsertException,
			DatabaseException {
		
		for (int i = 0; i < PWD_COUNT; i++) {
			dao.insert(ACCOUNT_ID, passwords[i], salts[i]);
		}
	}

	@Override
	protected void setupTestData() {
		dao = (PasswordHistoryDao) getDao();
		ph = QPasswordHistory.passwordHistory;
		passwords = new String[PWD_COUNT];
		salts = new String[PWD_COUNT];

		for (int idx = 0; idx < PWD_COUNT; idx++) {
			passwords[idx] = "testpasswordnothashed" + idx;
			salts[idx] = "slat" + idx;
		}
	}

	@Override
	protected void cleanup() {
		dao.cleanup();
	}

	@Override
	protected BaseDao getDao() {
		if (dao == null) {
			dao = RegistrationDaoFactory.getInstance().getPasswordHistoryDao();
		}

		return dao;
	}

	@Override
	public String getApplicationContextFileName() {
		return "test-app-context.xml";
	}
}
