package com.vendertool.registration.dal.dao;

import java.util.List;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;

public interface AccountSecurityQuestionDao extends BaseDao {
	public List<Long> insert(Long accountId, List<AccountSecurityQuestion> questions)
			throws DBConnectionException, InsertException, DatabaseException;
	
	public List<AccountSecurityQuestion> findAllByAccountId(Long accountId)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public void deleteByAccountId(Long accountId)
			throws DBConnectionException, DeleteException, DatabaseException;
}
