package com.vendertool.registration.dal.dao;

import com.vendertool.common.dal.dao.BaseDaoFactory;
import com.vendertool.common.dal.exception.DatabaseException;

public class RegistrationDaoFactory extends BaseDaoFactory {
	
	private RegistrationDaoFactory() {
		super();
	}
	
	private static class RegistrationDaoFactoryHolder {
		private static final RegistrationDaoFactory INSTANCE = new RegistrationDaoFactory();
	}
	
	public static RegistrationDaoFactory getInstance() {
		return RegistrationDaoFactoryHolder.INSTANCE;
	}
	
	public AccountDao getAccountDao() {
		return (AccountDao) getDao(AccountDao.class);
	}
	
	public AccountConfirmationDao getAccountConfirmationDao() {
		return (AccountConfirmationDao) getDao(AccountConfirmationDao.class);
	}
	
	public PasswordHistoryDao getPasswordHistoryDao() {
		return (PasswordHistoryDao) getDao(PasswordHistoryDao.class);
	}
	
	public AccountSecurityQuestionDao getAccountSecurityQuestionDao() {
		return (AccountSecurityQuestionDao) getDao(AccountSecurityQuestionDao.class);
	}

	@Override
	protected void register() throws DatabaseException {
		add(AccountDao.class, getBean("accountDAO"));
		add(AccountConfirmationDao.class, getBean("accountConfirmationDAO"));
		add(PasswordHistoryDao.class, getBean("passwordHistoryDAO"));
		add(AccountSecurityQuestionDao.class, getBean("accountSecurityQuestionDAO"));
	}
}
