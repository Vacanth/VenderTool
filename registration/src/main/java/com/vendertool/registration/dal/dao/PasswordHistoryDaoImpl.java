package com.vendertool.registration.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.vendertool.common.dal.dao.BaseDaoImpl;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.registration.dal.dao.codegen.QPasswordHistory;

public class PasswordHistoryDaoImpl extends BaseDaoImpl implements
		PasswordHistoryDao {
	
	private static final Logger logger = Logger.getLogger(PasswordHistoryDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	@Override
	public boolean hasSequenceGenerator() {
		return false;
	}

	@Override
	public String getSequenceProcedureName() {
		return null;
	}

	@Override
	public Long insert(Long accountId, String password,
			String salt) throws DBConnectionException, InsertException,
			DatabaseException {
		
		if(VUTIL.isNull(accountId) || VUTIL.isNull(password)) {
			InsertException ie = new InsertException("Cannot insert null password");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			Timestamp ts = new Timestamp(new Date().getTime());
			QPasswordHistory ph = QPasswordHistory.passwordHistory;
			SQLInsertClause s = insert(con, ph)
					.columns(ph.accountId, ph.createdDate, ph.lastModifiedDate, ph.password, ph.salt)
					.values(accountId, ts, ts, password, salt);
	    	
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		return s.executeWithKey(ph.passwordHistoryId);
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
	public String findByPassword(Long accountId, String newpassword)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isNull(accountId) || VUTIL.isNull(newpassword)) {
			FinderException fe = new FinderException("Cannot find records with null account id or password");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QPasswordHistory ph = QPasswordHistory.passwordHistory;
			
			SQLQuery query = from(con, ph)
					.where(ph.accountId.eq(accountId).and(ph.password.eq(newpassword)));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<String> rows = query.list(ph.password);
	    	
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
	public void deleteByAccountId(Long accountId, Date olderthan)
			throws DBConnectionException, DeleteException, DatabaseException {
		
		if(VUTIL.isNull(accountId)) {
			DeleteException fe = new DeleteException("Cannot delete with null accountId");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QPasswordHistory ph = QPasswordHistory.passwordHistory;
			
			SQLDeleteClause s = null;
			
			if (olderthan == null) { // delete all records
				s = delete(con, ph).where(ph.accountId.eq(accountId));
			} else { //delete only older passwords
				s = delete(con, ph).where(
						ph.accountId.eq(accountId).and(
								ph.createdDate.before(new Timestamp(olderthan
										.getTime()))));
			}
			
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
	public List<String> findAllByAccountId(Long accountId)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isNull(accountId)) {
			FinderException fe = new FinderException("Cannot find records with null account id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QPasswordHistory ph = QPasswordHistory.passwordHistory;
			
			SQLQuery query = from(con, ph)
					.where(ph.accountId.eq(accountId));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<String> rows = query.list(ph.password);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
	    	return rows;
	    	
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
	public void delete(Long pwdHistoryId)
			throws DBConnectionException, DeleteException, DatabaseException {
		
		if(VUTIL.isNull(pwdHistoryId)) {
			DeleteException fe = new DeleteException("Cannot delete with null id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QPasswordHistory ph = QPasswordHistory.passwordHistory;
			
			SQLDeleteClause s = delete(con, ph).where(
					ph.passwordHistoryId.eq(pwdHistoryId));
			
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
}
