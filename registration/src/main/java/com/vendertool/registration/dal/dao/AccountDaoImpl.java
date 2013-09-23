package com.vendertool.registration.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.BaseDaoImpl;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.registration.dal.dao.codegen.QAccount;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountStatusEnum;

public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {
	private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
			
	@Override
	public void insert(Account account) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(account)) {
			InsertException ie = new InsertException("Cannot insert null account");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccount a = QAccount.account;
			
			Long accountId = account.getId();
			if(VUTIL.isNull(accountId) || (accountId.longValue() <= 0)) {
				Long seq = generateNextSequence(con);
				if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
		    		InsertException ie = new InsertException("Unable to generate valid sequence");
					logger.debug(ie.getMessage(), ie);
					throw ie;
				}
				account.setId(seq);
			}
	
	    	// YOU CAN DO THIS...
	//		SQLInsertClause s = insert(con, a)
	//				.populate(account, new AccountMapper(a.all()));
	    	
	    	//OR YOU CAN DO THIS...
	    	SQLInsertClause s = insert(con, a)
	    				.populate(new AccountMapper(a.all()).populateBean(account));
	    	
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		InsertException ie = new InsertException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}
	
	@Override
	public Account findByEmail(String email, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		
		return findAccountByReadSet(email, readSet);
	}

	private Account findAccountByReadSet(String email, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isEmpty(email)) {
			FinderException fe = new FinderException("Cannot find account with null email");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QAccount a = QAccount.account;
			
			SQLQuery query = from(con, a)
					.where(a.emailAddr.eq(email));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			Account account = new AccountMapper(readSet).convert(rows.get(0), readSet);
			if(account == null) {
				FinderException fe = new FinderException("Cannot find account");
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return account;
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}
	

	@Override
	public void update(Account account, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(account) || VUTIL.isNull(updateSet)){
			UpdateException ue = new UpdateException("Cannot update null account");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccount a = QAccount.account;
	
	    	SQLUpdateClause s = update(con, a)
					.populate(account, new AccountMapper(updateSet));
	    	
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		UpdateException ue = new UpdateException(e);
				logger.debug(ue.getMessage(), ue);
				throw ue;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public void updateEmail(String oldEmail, String newEmail, AccountStatusEnum status)
			throws DBConnectionException, UpdateException {
		if(VUTIL.isNull(oldEmail) || VUTIL.isNull(newEmail) || VUTIL.isNull(status)) {
			UpdateException ue = new UpdateException("Cannot update null account");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccount a = QAccount.account;
			SQLUpdateClause s = update(con, a)
					.set(a.emailAddr, newEmail)
					.set(a.status, new Byte(status.getId()+""))
					.where(a.emailAddr.eq(oldEmail));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		UpdateException ue = new UpdateException(e);
				logger.debug(ue.getMessage(), ue);
				throw ue;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public void updatePassword(String email, String password)
			throws DBConnectionException, UpdateException {
		if(VUTIL.isNull(email) || VUTIL.isNull(password)) {
			UpdateException ue = new UpdateException("Cannot update null account");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccount a = QAccount.account;
			SQLUpdateClause s = update(con, a)
					.set(a.password, password)
					.where(a.emailAddr.eq(email));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		UpdateException ue = new UpdateException(e);
				logger.debug(ue.getMessage(), ue);
				throw ue;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}


	@Override
	public void delete(String email) throws DBConnectionException,
			DeleteException {
		
		if(VUTIL.isEmpty(email)) {
			DeleteException fe = new DeleteException("Cannot delete null email");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccount a = QAccount.account;
			
			SQLDeleteClause s = delete(con, a)
				.where(a.emailAddr.eq(email));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		DeleteException ie = new DeleteException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
	    	}
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public Long findAccountId(String email) throws DBConnectionException,
			FinderException {
		if(VUTIL.isEmpty(email)) {
			FinderException fe = new FinderException("Cannot find account with null email");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QAccount a = QAccount.account;
			
			SQLQuery query = from(con, a)
					.where(a.emailAddr.eq(email));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<Long> rows = query.list(a.accountId);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
	    	return rows.get(0);
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public boolean hasSequenceGenerator() {
		return true;
	}

	@Override
	public String getSequenceProcedureName() {
		return "nextvalForAccount()";
	}
}
