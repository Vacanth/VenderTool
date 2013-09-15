package com.vendertool.registration.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
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
	public AccountConfirmation findLatestActive(Long accountId)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isNull(accountId)) {
			FinderException fe = new FinderException("Cannot find account confirmation data with null account id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QAccountConfirmation ac = QAccountConfirmation.accountConfirmation;
			
			SQLQuery query = from(con, ac)
					.groupBy(ac.accountId)
					.innerJoin(new SQLSubQuery().from(ac).where(ac.accountId.eq(accountId)).list(ac.createdDate.max()), ac)
					.where(ac.accountId.eq(accountId));
			
//			SQLQuery query = from(con, ac)
//					.where(ac.accountId.eq(accountId));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<Tuple> rows = query.list(ac.all());
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
	    	return new AccountConfirmationMapper(ac.all()).convert(rows.get(0), ac.all());
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
