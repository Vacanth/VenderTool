package com.vendertool.registration.test.email;

import java.util.Date;
import java.util.Locale;

import com.vendertool.common.SessionIdGenerator;
import com.vendertool.common.test.dal.BaseTest;
import com.vendertool.registration.email.RegistrationEmailSender;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.AccountRoleEnum;
import com.vendertool.sharedtypes.core.ContactDetails;


/**
 * Unit test for simple App.
 */
public class RegistrationEmailTest extends BaseTest
{
	private RegistrationEmailSender emailSender;
	private Account account;
	private static final String TEST_EMAIL = "madhusudan.varadan@gmail.com";
	private static final String TEST_OLD_EMAIL = "mvaradan@yahoo.com";
	
	
	public RegistrationEmailTest() {
		emailSender = RegistrationEmailSender.getInstance();
		account = createLocalAccount();
	}
	
    private Account createLocalAccount() {
		Account a = new Account();
		a.setEmailId(TEST_EMAIL);
		a.setPassword("TestPassword");
		a.setPasswordSalt("passwordsalt");
		a.addRole(AccountRoleEnum.ROLE_USER);
		
		ContactDetails cd = new ContactDetails();
		cd.setFirstName("TestFirstName");
		cd.setMiddleName("M");
		cd.setLastName("TestLastName");
		a.setContactDetails(cd);
		
		Date now = new Date();
		a.setCreateDate(now);
		a.setLastModifiedDate(now);
		
		AccountConfirmation accConf = new AccountConfirmation();
		accConf.setConfirmationAttempts(0);
		accConf.setCreateDate(new Date());
		accConf.setConfirmCode(SessionIdGenerator.getInstance().getRandomNumber(5));
		accConf.setConfirmSessionId("2347234jlswnwei879807sd832nk9wfw");
		
		a.setAccountConf(accConf);
		
		return a;
	}
    
	public static void main(String args[]){
		RegistrationEmailTest test = new RegistrationEmailTest();
		
		Locale locale = Locale.US;
		String baseurl = "http://localhost:8080";
		
		test.log("**** Begin Email Test ******");
		
		test.log("Confirm Registration Email Test");
    	test.testConfirmRegistrationEmail(locale, baseurl);
    	test.log("Email Sent");
    	
    	test.log("Registration Complete Email Test");
    	test.testRegistrationCompleteEmail(locale, baseurl);
    	test.log("Email Sent");
    	
    	
    	test.log("Account Email Change Email Test");
    	test.testEmailChangeEmail(locale, baseurl, TEST_OLD_EMAIL);
    	test.log("Email Sent");
    	
    	
    	test.log("Password Change Email Test");
    	test.testPasswordChangeEmail(locale, baseurl);
    	test.log("Email Sent");
    	
    	test.log("**** Email Test Complete ******");
    }

	private void testPasswordChangeEmail(Locale locale, String baseurl) {
		emailSender.sendPasswordChangeEmail(account, baseurl, locale);
	}

	private void testEmailChangeEmail(Locale locale, String baseurl, String oldemail) {
		emailSender.sendAccountEmailChangeEmail(account, oldemail, baseurl, locale);
	}

	private void testRegistrationCompleteEmail(Locale locale, String baseurl) {
		emailSender.sendRegistrationCompleteEmail(account, baseurl, locale);
	}

	private void testConfirmRegistrationEmail(Locale locale, String baseurl) {
		emailSender.sendConfirmRegistrationEmail(account, baseurl, locale);
	}

	@Override
	public String getApplicationContextFileName() {
		return "test-app-context.xml";
	}
}
