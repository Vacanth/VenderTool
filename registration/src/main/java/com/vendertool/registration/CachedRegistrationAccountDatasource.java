package com.vendertool.registration;

import java.util.HashMap;
import java.util.Map;

import com.vendertool.sharedtypes.core.Account;

//NOTE:
//This is meant for test purpose only during development phase. 
//Once we have the database connection
//made available, please remove this class.
//TODO: remove this once we hook up to DB

public class CachedRegistrationAccountDatasource {
	public enum Status {
		NEW, EXISTING, INVALID;
	}
	
	private static Map<String, Account> ACCOUNT_DIRECTORY = null;
	
	private static class CachedRegistrationAccountDatasourceHolder {
		private static final CachedRegistrationAccountDatasource INSTANCE = new CachedRegistrationAccountDatasource();
	}
	
	private CachedRegistrationAccountDatasource() {
		ACCOUNT_DIRECTORY = new HashMap<String, Account>();
	}
	
	public static CachedRegistrationAccountDatasource getInstance() {
		return CachedRegistrationAccountDatasourceHolder.INSTANCE;
	}
	
	public Status addAccount(Account account) {
		if((account == null) || (account.getEmail() == null) || (account.getPassword() == null)) {
			return Status.INVALID;
		}
		
		String username = account.getEmail();
		if(ACCOUNT_DIRECTORY.containsKey(username)){
			return Status.EXISTING;
		}
		
		ACCOUNT_DIRECTORY.put(username, account);
		return Status.NEW;
	}
	
	public Account getAccount(String usename) {
		return ACCOUNT_DIRECTORY.get(usename);
	}
	
	public boolean removeAccount(String username) {
		return (ACCOUNT_DIRECTORY.remove(username) != null) ? true : false;
	}
	
	public void clearAllAccounts() {
		ACCOUNT_DIRECTORY.clear();
	}
	
	public boolean updateAccount(Account account) {
		if((account == null) || (account.getEmail() == null)) {
			return false;
		}
		
		String username = account.getEmail();
		if(ACCOUNT_DIRECTORY.containsKey(username)){
			removeAccount(username);
			ACCOUNT_DIRECTORY.put(username, account);
			return true;
		}
		
		return false;
	}
}
