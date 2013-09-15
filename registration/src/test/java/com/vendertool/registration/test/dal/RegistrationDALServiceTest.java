package com.vendertool.registration.test.dal;

import java.sql.SQLException;
import java.util.Date;

import junit.framework.Assert;

import com.vendertool.common.SessionIdGenerator;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.test.dal.BaseDALService;
import com.vendertool.registration.dal.RegistrationDALService;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.AccountRoleEnum;
import com.vendertool.sharedtypes.core.ContactDetails;

public class RegistrationDALServiceTest extends BaseDALService{
	Account account;
	RegistrationDALService dalservice;
	
	public RegistrationDALServiceTest() {
		super();
	}
	
	public void initialize() throws DBConnectionException, SQLException {
		super.initialize();
		account = createAccount();
		dalservice = RegistrationDALService.getInstance();
	}

	private Account createAccount() {
		Account a = new Account();
		a.setEmailId("testemail@vendertooltest.com");
		a.setPassword("TestPassword");
		a.setPasswordSalt("passwordsalt");
		a.addRole(AccountRoleEnum.ROLE_USER);
		
		ContactDetails cd = new ContactDetails();
		cd.setFirstName("TestFirstName");
		cd.setMiddleName("M");
		cd.setLastName("TestLastName");
		a.setContactDetails(cd);
		
		AccountConfirmation accConf = new AccountConfirmation();
		accConf.setConfirmationAttempts(0);
		accConf.setCreateDate(new Date());
		accConf.setConfirmCode(SessionIdGenerator.getInstance().getRandomNumber(5));
		accConf.setConfirmSessionId("2347234jlswnwei879807sd832nk9wfw");
		
		a.setAccountConf(accConf);
		
		Date now = new Date();
		a.setCreateDate(now);
		a.setLastModifiedDate(now);
		
		return a;
	}
	
	public static void main(String[] args) throws DBConnectionException,
			FinderException, InsertException, DatabaseException, UpdateException, DeleteException {
		
		new RegistrationDALServiceTest().run();
	}

	private void run() throws DBConnectionException, FinderException,
			InsertException, DatabaseException, UpdateException, DeleteException {
		
		log("********* START REGISTRATION DAL SERVICE TEST *********");
		
		log("Register account");
		dalservice.registerAccount(account);
		
		findAccountProfile(account.getEmailId(), false);
		
		log("Update account profile");
		account.getContactDetails().setFirstName("UpdatedTestFirstName");
		account.getContactDetails().setLastName("UpdatedTestLastName");
		dalservice.updateAccountProfile(account);
		
		findAccountProfile(account.getEmailId(), false);
		
		log("Update email");
		String updatedEmail = "Updated"+account.getEmailId();
		dalservice.updateEmail(account.getEmailId(), updatedEmail);
		
		findAccountProfile(updatedEmail, false);
		
		log("Get email id=");
		log(dalservice.getAccountId(updatedEmail).toString());
		
		log("Get account pwd=");
		Account dbacc = dalservice.getAccountPassword(updatedEmail);
		log(dbacc.toString());
		
		log("Update Password");
		dalservice.updatePassword(updatedEmail, dbacc.getPassword(), "UpdatedTestPassword");
		dbacc = dalservice.getAccountPassword(updatedEmail);
		log(dbacc.toString());
		
		boolean val = 
			dalservice.isPreviouslyUsedPassword(updatedEmail, account.getPassword());
		log("Is previously used password = " + val);
		
		
		log("****** Remove account *******");
		dalservice.removeAccount(updatedEmail);
		
		findAccountProfile(updatedEmail, true);
		
		log("********* TEST COMPLETE **********");
	}

	private void findAccountProfile(String email, boolean nullexpected) throws DBConnectionException,
			FinderException, DatabaseException {
		log("Get account profile");
		Account dbaccount = dalservice.getAccountProfile(email);
		
		if(nullexpected) {
			Assert.assertNull(dbaccount);
			log("Account not found for email: " + email);
			return;
		}
		
		Assert.assertNotNull(dbaccount);
		log("ACCOUNT PROFILE: ");
		log(dbaccount.toString());
	}
}
