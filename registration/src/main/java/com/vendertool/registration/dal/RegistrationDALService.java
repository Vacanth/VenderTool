package com.vendertool.registration.dal;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.dao.AddressDao;
import com.vendertool.common.dal.dao.AddressDaoFactory;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.registration.dal.dao.AccountConfirmationDao;
import com.vendertool.registration.dal.dao.AccountDao;
import com.vendertool.registration.dal.dao.PasswordHistoryDao;
import com.vendertool.registration.dal.dao.RegistrationDaoFactory;
import com.vendertool.registration.dal.fieldset.FieldSets;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;

public class RegistrationDALService {
	private static final Logger logger = Logger.getLogger(RegistrationDALService.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
	AccountDao accountDao;
	AccountConfirmationDao accountConfDao;
	PasswordHistoryDao pwdHistoryDao;
	AddressDao addrDao;
	
	private RegistrationDALService() {
		accountDao = RegistrationDaoFactory.getInstance().getAccountDao();
		accountConfDao = RegistrationDaoFactory.getInstance().getAccountConfirmationDao();
		pwdHistoryDao = RegistrationDaoFactory.getInstance().getPasswordHistoryDao();
		addrDao = AddressDaoFactory.getInstance().getAddressDao();
	}
	
	private static class RegistrationDALServiceHolder {
		private static final RegistrationDALService INSTANCE = new RegistrationDALService();
	}
	
	public static RegistrationDALService getInstance() {
		return RegistrationDALServiceHolder.INSTANCE;
	}
	
	
	public Long registerAccount(Account account) throws DBConnectionException,
			FinderException, InsertException, DatabaseException {
		
		if(VUTIL.isNull(account)) {
			return null;
		}
		
		AccountConfirmation accConf = account.getAccountConf();
		if(accConf == null) {
			throw new InsertException(
					"Session tokens are not generated for account confirmation");
		}
		
		Long accountId = accountDao.generateNextSequence();
		account.setId(accountId);
		accountConfDao.insert(accountId, accConf);
		
		try {
			accountDao.insert(account);
		} catch (Exception e) {
			//manually rollback
			Long aid = accountDao.findAccountId(account.getEmailId());
			if(aid == null) { //if not null just return the response, because the account is already created
				try {
					accountConfDao.delete(accountId);
					return null;
				} catch (DeleteException de) {
					//don't throw rollback exceptions, just log it
					logger.debug(de.getMessage(), de);
				}
			}
		}
		
		return accountId;
	}
	
	
	public void updateAccountProfile(Account account)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(account)) {
			return;
		}
		
		updateAddress(account);
		
		accountDao.update(account, FieldSets.ACCOUNT_UPDATESET.PROFILE);
	}


	private boolean updateAddress(Account account) throws DBConnectionException,
			DatabaseException, UpdateException {
		
		ContactDetails cd = account.getContactDetails();
		Address address = cd.getAddress();
		
		if(address == null) {
			return false;
		}
		
		Long id = address.getId();
		if((address.getId() == null) || (address.getId().longValue() <= 0)) {
			Long addrId = insertAddress(account, address);
			address.setId(addrId);
			return true;
		}
		
		try {
			Address dbAddress = addrDao.findById(id);
			if((dbAddress == null) || (!address.locationEquals(dbAddress))) {
				Long addrId = insertAddress(account, address);
				address.setId(addrId);
				return true;
			}
			
			addrDao.updateAddressMetadata(address);
			
		} catch (FinderException e) {
			UpdateException ue = new UpdateException(
					"Unable to update account profile for "
							+ account.getEmailId(), e);
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		return true;
	}


	private Long insertAddress(Account account, Address address)
			throws DBConnectionException, DatabaseException, UpdateException {
		try {
			return addrDao.insert(account.getId(), address);
		} catch (InsertException e) {
			UpdateException ue = new UpdateException(
					"Unable to update account profile for "
							+ account.getEmailId(), e);
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
	}
	
	
	public boolean updatePassword(String email, String oldPassword,
			String newPassword) throws FinderException, DBConnectionException,
			DatabaseException, InsertException {
		
		if (VUTIL.isNull(email) || VUTIL.isNull(oldPassword)
				|| VUTIL.isNull(newPassword)) {
			return false;
		}
		
		Account account = accountDao.findByEmail(email, FieldSets.ACCOUNT_READSET.PASSWORD);
		
		if(VUTIL.isNull(account)) {
			throw new FinderException("Unable to find the account");
		}
		
		Long id = pwdHistoryDao.insert(account.getId(),
				account.getPassword(), account.getPasswordSalt());
		
		try {
			accountDao.updatePassword(email, newPassword);
		} catch (Exception e) {
			//Check if the update was good, but the exception was somewhere upstream
			Account changedAcc = accountDao.findByEmail(email,
					FieldSets.ACCOUNT_READSET.PASSWORD);
			
			if(VUTIL.isNull(changedAcc) || (!changedAcc.getPassword().equals(newPassword))) {
				try {
					pwdHistoryDao.delete(id);
				} catch (Exception ex) {
					//Don't throw exception during rollback, just log it
					logger.debug(ex.getMessage(), ex);
				}
			}
			
			return false;
		}
		
		return true;
	}
	
	
	public boolean updateEmail(String oldEmail, String newEmail)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(oldEmail) || VUTIL.isNull(newEmail)) {
			return false;
		}
		
		accountDao.updateEmail(oldEmail, newEmail);
		return true;
	}
	
	
	public Long getAccountId(String email) throws DBConnectionException,
			FinderException, DatabaseException {
		
		if(VUTIL.isNull(email)) {
			return null;
		}
		
		return accountDao.findAccountId(email);
	}
	
	
	public Account getAccountProfile(String email)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isNull(email)) {
			return null;
		}
		
		return accountDao.findByEmail(email, FieldSets.ACCOUNT_READSET.PROFILE);
	}
	
	public Account getAccountPassword(String email)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isNull(email)) {
			return null;
		}
		
		return accountDao.findByEmail(email, FieldSets.ACCOUNT_READSET.PASSWORD);
	}
	
	
	public boolean isPreviouslyUsedPassword(String email, String newPassword)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isNull(newPassword) || VUTIL.isNull(email)) {
			return false;
		}
		
		Account account = accountDao.findByEmail(email, FieldSets.ACCOUNT_READSET.PASSWORD);
		
		if(VUTIL.isNull(account)) {
			throw new FinderException("Unable to find the account");
		}
		
		if(newPassword.equals(account.getPassword())) {
			return true;
		}
		
		String pwd = pwdHistoryDao.findByPassword(account.getId(), newPassword);
		return (!VUTIL.isEmpty(pwd));
		
	}
	
	public void removeAccount(String email) throws DeleteException,
			DBConnectionException, DatabaseException, FinderException {
		
		if(VUTIL.isEmpty(email)) {
			throw new DeleteException("Cannot delete null account");
		}
		
		Long accId = accountDao.findAccountId(email);
		
		if(VUTIL.isNull(accId) || (accId.longValue() <= 0)) {
			throw new DeleteException("Cannot find account information for email " + email);
		}
		
		accountDao.delete(email);
		
		pwdHistoryDao.deleteByAccountId(accId, null);
		
		accountConfDao.delete(accId);
		
		addrDao.deleteByAccountId(accId, true);
	}
	
	
//	public List<AccountSecurityQuestion> getAccountSecurityQuestions(String email) {
//		
//	}
//	
//	public void updateAccountSecurityQuestions(String email, List<AccountSecurityQuestion>) {
//		
//	}
}