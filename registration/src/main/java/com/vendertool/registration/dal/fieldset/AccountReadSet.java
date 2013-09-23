package com.vendertool.registration.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.registration.dal.dao.codegen.QAccount;

public class AccountReadSet {
	private static final QAccount ACCOUNT = QAccount.account;
	
	private static class AccountReadSetHolder {
		private static final AccountReadSet INSTANCE = new AccountReadSet();
	}
	
	public static AccountReadSet getInstance() {
		return AccountReadSetHolder.INSTANCE;
	}
	
	public final Path<?>[] PROFILE = {
		ACCOUNT.accountId, 
		ACCOUNT.emailAddr, 
		ACCOUNT.alternateEmail,
		ACCOUNT.billingAddrId, 
		ACCOUNT.currencyCodeIso3, 
		ACCOUNT.firstName, 
		ACCOUNT.language, 
		ACCOUNT.lastModifiedDate, 
		ACCOUNT.createdDate, 
		ACCOUNT.lastName, 
		ACCOUNT.middleName, 
		ACCOUNT.picture, 
		ACCOUNT.registrationAddrId, 
		ACCOUNT.roles, 
		ACCOUNT.status,
		ACCOUNT.contactPhoneHome,
		ACCOUNT.contactPhoneMobile,
		ACCOUNT.contactPhoneWork
	};
	
	public final Path<?>[] EMAIL = {
		ACCOUNT.accountId, 
		ACCOUNT.emailAddr
	};
	
	public final Path<?>[] PASSWORD = {
		ACCOUNT.accountId,
		ACCOUNT.emailAddr,
		ACCOUNT.password,
		ACCOUNT.salt,
		ACCOUNT.roles,
		ACCOUNT.status,
		ACCOUNT.firstName
	};
}
