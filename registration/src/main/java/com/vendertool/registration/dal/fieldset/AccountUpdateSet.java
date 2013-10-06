package com.vendertool.registration.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.registration.dal.dao.codegen.QAccount;

public class AccountUpdateSet {
	private static final QAccount ACCOUNT = QAccount.account;
	
	private static class AccountUpdateSetHolder {
		private static final AccountUpdateSet INSTANCE = new AccountUpdateSet();
	}
	
	public static AccountUpdateSet getInstance() {
		return AccountUpdateSetHolder.INSTANCE;
	}
	
	public final Path<?>[] PROFILE = {
		ACCOUNT.alternateEmail,
		ACCOUNT.billingAddrId, 
		ACCOUNT.currencyCodeIso3, 
		ACCOUNT.firstName, 
		ACCOUNT.language, 
		ACCOUNT.lastModifiedDate, 
		ACCOUNT.lastName, 
		ACCOUNT.middleName, 
		ACCOUNT.picture, 
		ACCOUNT.registrationAddrId,
		ACCOUNT.contactPhoneHome,
		ACCOUNT.contactPhoneMobile,
		ACCOUNT.contactPhoneWork
	};
	
	public final Path<?>[] PASSWORD = {
		ACCOUNT.password, 
		ACCOUNT.lastModifiedDate
	};
	
	public final Path<?>[] EMAIL = {
		ACCOUNT.emailAddr, 
		ACCOUNT.lastModifiedDate
	};
	
	public final Path<?>[] ROLES = {
		ACCOUNT.roles, 
		ACCOUNT.lastModifiedDate
	};
	
	public final Path<?>[] STATUS = {
			ACCOUNT.status, 
			ACCOUNT.lastModifiedDate
		};
}
