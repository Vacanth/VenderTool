package com.vendertool.registration.dal.dao;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.AccountConfirmation.AccountConfirmationStatusEnum;

public interface AccountConfirmationDao extends BaseDao {
	public void insert(Long accountId, AccountConfirmation accountConf)
			throws DBConnectionException, InsertException, DatabaseException;
	
	public void updateConfirmationAttempts(Long accountId, Long pkId, int attempts)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void delete(Long accountId)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public AccountConfirmation findLatestActive(Long accountId, String email)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public void updateStatus(Long id, AccountConfirmationStatusEnum status)
			throws DBConnectionException, UpdateException, DatabaseException;
}
