package com.vendertool.registration.test.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.core.AccountStatusEnum;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.SecurityQuestion;
import com.vendertool.sharedtypes.core.SecurityQuestionCodeEnum;

public class RegistrationDALServiceTest extends BaseDALService {
	private static final int SEC_QUESTION_COUNT = 2;
	
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
		a.setEmail("testemail@vendertooltest.com");
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
		
		List<AccountSecurityQuestion> questions = new ArrayList<AccountSecurityQuestion>();
		for(int i=0; i<SEC_QUESTION_COUNT; i++) {
			AccountSecurityQuestion q = new AccountSecurityQuestion();
			q.setId(i + 0L);
			SecurityQuestion sq = new SecurityQuestion();
			sq.setQuestionCode(SecurityQuestionCodeEnum.getByIndex(i % 10));
			q.setQuestion(sq);
			q.setAnswer("MyAnswer"+i);
			q.setCreatedDate(now);
			
			questions.add(q);
		}
		a.setSecurityQuestions(questions);
		
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
		
		findAccountProfile(account.getEmail(), false);
		
		log("Update account profile");
		account.getContactDetails().setFirstName("UpdatedTestFirstName");
		account.getContactDetails().setLastName("UpdatedTestLastName");
		dalservice.updateAccountProfile(account);
		
		findAccountProfile(account.getEmail(), false);
		
		log("Update email");
		String updatedEmail = "Updated"+account.getEmail();
		account.setAccountStatus(AccountStatusEnum.EMAIL_CHANGE_NOT_VERIFIED);
		dalservice.updateEmail(account.getEmail(), account);
		
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
		
		log("Add security questions");
		dalservice.updateAccountSecurityQuestions(updatedEmail, account.getSecurityQuestions());
		
		log("Find security questions");
		List<AccountSecurityQuestion> dbsecQues = dalservice.getAccountSecurityQuestions(updatedEmail);
		Assert.assertNotNull(dbsecQues);
		log(dbsecQues.toString());
		
		log("****** Remove account *******");
		dalservice.removeAccount(updatedEmail);
		
		findAccountProfile(updatedEmail, true);
		
		boolean exceptionThrown = false;
		try {
			dbsecQues = dalservice.getAccountSecurityQuestions(updatedEmail);
		} catch (FinderException fe) {
			exceptionThrown = true;
			log("AccountSecurityQuestions not found (as expected) after deleting security questions");
		}
		Assert.assertEquals(exceptionThrown, true);
		
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
	
	@Override
	public String getApplicationContextFileName() {
		return "test-app-context.xml";
	}
}
