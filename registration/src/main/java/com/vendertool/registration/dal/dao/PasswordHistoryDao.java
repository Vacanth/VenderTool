package com.vendertool.registration.dal.dao;

import java.util.Date;
import java.util.List;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;

public interface PasswordHistoryDao extends BaseDao {
	
	public Long insert(Long accountId, String password, String salt)
			throws DBConnectionException, InsertException, DatabaseException;
	
	public String findByPassword(Long accountId, String newpassword)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public List<String> findAllByAccountId(Long accountId)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public void deleteByAccountId(Long accountId, Date olderthan)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public void delete(Long pwdHistoryId)
			throws DBConnectionException, DeleteException, DatabaseException;
}
