package com.vendertool.registration.test.dal;

import java.sql.SQLException;
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
import com.vendertool.registration.dal.dao.AccountDao;
import com.vendertool.registration.dal.dao.RegistrationDaoFactory;
import com.vendertool.registration.dal.dao.codegen.QAccount;
import com.vendertool.registration.dal.fieldset.FieldSets;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountRoleEnum;
import com.vendertool.sharedtypes.core.ContactDetails;

public class AccountDaoTest extends BaseDaoTest {
	private static final int ACCOUNT_COUNT = 2;
	Account[] accounts;
	AccountDao dao;
	QAccount qa;
	
	protected AccountDaoTest() {
		super();
	}
	
	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, DeleteException, FinderException, UpdateException {
		AccountDaoTest test = new AccountDaoTest();
		test.testCRUD();
		test.cleanup();
	}
	
	@Override
	protected void setupTestData() {
		qa = QAccount.account;
		dao = (AccountDao) getDao();
		
		accounts = new Account[ACCOUNT_COUNT];
		for(int idx = 0; idx < ACCOUNT_COUNT; idx++) {
			accounts[idx] = createAccount(idx);
		}
	}

	private Account createAccount(int idx) {
		Account a = new Account();
		a.setEmail("testemail"+idx+"@vendertooltest.com");
		a.setPassword("TestPassword"+idx);
		a.setPasswordSalt("passwordsalt"+idx);
		a.addRole(AccountRoleEnum.ROLE_USER);
		
		ContactDetails cd = new ContactDetails();
		cd.setFirstName("TestFirstName"+idx);
		cd.setMiddleName("M");
		cd.setLastName("TestLastName"+idx);
		a.setContactDetails(cd);
		
		Date now = new Date();
		a.setCreateDate(now);
		a.setLastModifiedDate(now);
		
		return a;
	}
	
	public void testCRUD() throws DBConnectionException, InsertException,
			DatabaseException, SQLException, DeleteException, FinderException, UpdateException {
		
		//DAL insert
		log("======== INSERT TEST =======");
		insert();
		
		//DAL find & JUnit assert
		log("======== FIND AND ASSERT TEST =======");
		for(Account account : accounts) {
			Account dbaccount = dao.findByEmail(account.getEmail(),
					FieldSets.ACCOUNT_READSET.PROFILE);
			assertAccount(account, dbaccount);
			log(dbaccount.toString());
		}
		
		//local update (non-dal)
		log("======== UPDATE ACCOUNT LOCALLY =======");
		updateLocalAccount(0);
		
		//DAL update
		log("======== UPDATE ACCOUNT TO THE DATABASE =======");
		dao.update(accounts[0], FieldSets.ACCOUNT_UPDATESET.PROFILE);
		
		//DAL find
		log("======== FIND PROFILE TEST =======");
		Account dbaccount = dao.findByEmail(accounts[0].getEmail(), FieldSets.ACCOUNT_READSET.PROFILE);
		assertAccount(accounts[0], dbaccount);
		log(dbaccount.toString());
		
		
		//DAL find PK
		log("======== FIND BY PK TEST =======");
		Long pk = dao.findAccountId(accounts[0].getEmail());
		Assert.assertEquals(dbaccount.getId(), pk);
		log("find by id=" + pk);
		
		//DAL find by readset
		log("======== FIND WITH READ SET TEST =======");
		dbaccount = dao.findByEmail(accounts[0].getEmail(), FieldSets.ACCOUNT_READSET.PASSWORD);
		
		//JUnit assert read set
		Assert.assertNull(dbaccount.getContactDetails());
		Assert.assertNotNull(dbaccount.getEmail());
		Assert.assertNotNull(dbaccount.getPassword());
		Assert.assertNotNull(dbaccount.getRoles());
		log("Account info after find with sign in readset");
		log(dbaccount.toString());
		
		//DAL delete
		log("======== DELETE TEST =======");
		for(int idx = 0; idx < ACCOUNT_COUNT; idx++) {
			dao.delete(accounts[idx].getEmail());
		}
		
		//DAL find
		log("======== FIND AFTER DELETE TEST =======");
		for(int idx = 0; idx < ACCOUNT_COUNT; idx++) {
			pk = dao.findAccountId(accounts[idx].getEmail());
			Assert.assertNull(pk);
		}
		
		log("******   TEST DONE!!!   ******");
	}
	
	private void updateLocalAccount(int idx) {
		Account account = accounts[idx];
		ContactDetails cd = account.getContactDetails();
		cd.setFirstName("TestUpdatedFirstName"+idx);
		cd.setMiddleName("MU");
		cd.setLastName("TestUpdatedLastName"+idx);
	}

	private void assertAccount(Account expected, Account actual) {
		Assert.assertNotNull(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getEmail(), actual.getEmail());
		Assert.assertEquals(expected.getContactDetails().getFirstName(), actual.getContactDetails().getFirstName());
	}
	
	private void insert() throws DBConnectionException, InsertException,
			DatabaseException {
		for(Account account : accounts) {
			dao.insert(account);
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
			dao = factory.getAccountDao();
		}
		
		return dao;
	}
	
	@Override
	public String getApplicationContextFileName() {
		return "test-app-context.xml";
	}
}
