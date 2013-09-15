package com.vendertool.registration.dal.dao;

import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.sharedtypes.core.Account;

public interface AccountDao extends BaseDao {
	
	public void insert(Account account) throws DBConnectionException,
			InsertException, DatabaseException;
	
	public Account findByEmail(String email, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;

	public void update(Account account, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void updateEmail(String oldEmail, String newEmail)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void updatePassword(String email, String password) 
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void delete(String email)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public Long findAccountId(String email) 
			throws DBConnectionException, FinderException, DatabaseException;
}
