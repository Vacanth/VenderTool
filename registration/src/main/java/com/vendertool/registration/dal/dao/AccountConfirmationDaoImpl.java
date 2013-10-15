package com.vendertool.registration.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.vendertool.common.dal.dao.BaseDaoImpl;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.registration.dal.dao.codegen.QAccountConfirmation;
import com.vendertool.registration.dal.dao.codegen.QBeanAccountConfirmation;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.AccountConfirmation.AccountConfirmationStatusEnum;

public class AccountConfirmationDaoImpl extends BaseDaoImpl implements
		AccountConfirmationDao {
	
	private static final Logger logger = Logger.getLogger(AccountConfirmationDaoImpl.class);
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
	public void insert(Long accountId,
			AccountConfirmation accountConf) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(accountConf)) {
			InsertException ie = new InsertException("Cannot insert null account confirmation");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccountConfirmation ac = QAccountConfirmation.accountConfirmation;
			AccountConfirmationMapper mapper = new AccountConfirmationMapper(ac.all());
			QBeanAccountConfirmation acbean = mapper.populateBean(accountConf);
			acbean.setAccountId(accountId);
			
	    	// YOU CAN DO THIS...
	//		SQLInsertClause s = insert(con, ac)
	//				.populate(accountConf, new AccountConfirmationMapper(ac.all()));
	    	
	    	//OR YOU CAN DO THIS...
	    	SQLInsertClause s = insert(con, ac).populate(acbean);
	    	
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
	public void updateConfirmationAttempts(Long accountId, Long pkId,
			int attempts) throws DBConnectionException, UpdateException,
			DatabaseException {

		if(VUTIL.isNull(accountId) || VUTIL.isNull(pkId)) {
			UpdateException ue = new UpdateException("Cannot update null account confirmation");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccountConfirmation ac = QAccountConfirmation.accountConfirmation;
			
			SQLUpdateClause s = update(con, ac)
					.set(ac.numberOfAttempts, new Byte(attempts+""))
					.where(ac.accountConfirmationId.eq(pkId).and(ac.accountId.eq(accountId)));
			
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
	public void delete(Long accountId)
			throws DBConnectionException, DeleteException, DatabaseException {
		
		if(VUTIL.isNull(accountId)) {
			DeleteException fe = new DeleteException("Cannot delete with null accountId");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccountConfirmation ac = QAccountConfirmation.accountConfirmation;
			
			SQLDeleteClause s = delete(con, ac)
				.where(ac.accountId.eq(accountId));
			
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
	public AccountConfirmation findLatestActive(Long accountId, String email)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isNull(accountId) || VUTIL.isEmpty(email)) {
			FinderException fe = new FinderException("Cannot find account confirmation data with null account id or email");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		QAccountConfirmation ac = QAccountConfirmation.accountConfirmation;
		
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try { 
			con = getConnection();
			
			String sql = "select * from account_confirmation as AC " +
							"INNER JOIN (select account_id, status, email_addr, max(created_date) as maxc from account_confirmation " +
							"where account_id = ? and email_addr = ? and expiry_date > now() and status = ? " +
											"GROUP BY account_id) IAC " +
							"ON AC.account_id = IAC.account_id and AC.created_date = IAC.maxc and " +
							"AC.email_addr = IAC.email_addr and AC.status = IAC.status";
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + sql);
			
			statement = con.prepareStatement(sql);
			statement.setLong(1, accountId);
			statement.setString(2, email);
			statement.setInt(3, AccountConfirmationStatusEnum.NOT_VERIFIED.getId());
			
			rs = statement.executeQuery();
			
			if(rs.next()) {
				return new AccountConfirmationMapper(ac.all()).convertRestultSet(rs);
			}
			
			return null;
		} catch (SQLException e) {
			logger.debug(e.getMessage(), e);
			throw new FinderException(e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(statement != null) {
					statement.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}

	@Override
	public void updateStatus(Long id, AccountConfirmationStatusEnum status)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(id) || VUTIL.isNull(status)) {
			UpdateException ue = new UpdateException("Cannot update null status or null account");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccountConfirmation ac = QAccountConfirmation.accountConfirmation;
			
			SQLUpdateClause s = update(con, ac)
					.set(ac.status, new Byte(status.getId()+""))
					.where(ac.accountConfirmationId.eq(id));
			
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
}
