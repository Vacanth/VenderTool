package com.vendertool.registration.dal.dao;

import java.util.Date;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.sharedtypes.core.ForgotPassword;
import com.vendertool.sharedtypes.core.ForgotPassword.ForgotPasswordStatusEnum;

public interface ForgotPasswordDao extends BaseDao {
	public void insert(ForgotPassword fp) throws DBConnectionException,
			InsertException, DatabaseException;

	public ForgotPassword findById(Long id) throws DBConnectionException,
			FinderException, DatabaseException;

	public long findTotalByDateRange(String email, Date startDate, Date endDate,
			ForgotPasswordStatusEnum status) throws DBConnectionException,
			FinderException, DatabaseException;
	
	public void deleteByAccountId(Long accountId)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public void deleteByEmail(String email)
			throws DBConnectionException, DeleteException, DatabaseException;
}
