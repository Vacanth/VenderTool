package com.vendertool.registration.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysema.query.Tuple;
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
import com.vendertool.registration.dal.dao.codegen.QBeanForgotPassword;
import com.vendertool.registration.dal.dao.codegen.QForgotPassword;
import com.vendertool.sharedtypes.core.ForgotPassword;
import com.vendertool.sharedtypes.core.ForgotPassword.ForgotPasswordStatusEnum;

public class ForgotPasswordDaoImpl extends BaseDaoImpl implements
		ForgotPasswordDao {
	
	private static final Logger logger = Logger.getLogger(ForgotPasswordDaoImpl.class);
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
	public void insert(ForgotPassword fp) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(fp)) {
			InsertException ie = new InsertException("Cannot insert null forgot password entity");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QForgotPassword f = QForgotPassword.forgotPassword;
			ForgotPasswordMapper mapper = new ForgotPasswordMapper(f.all());
			QBeanForgotPassword fpbean = mapper.populateBean(fp);
			
	    	SQLInsertClause s = insert(con, f).populate(fpbean);
	    	
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
	public ForgotPassword findById(Long id) throws DBConnectionException,
			FinderException, DatabaseException {
		
		if(VUTIL.isNull(id)) {
			FinderException fe = new FinderException("Cannot find forgot password with null id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QForgotPassword f = QForgotPassword.forgotPassword;
			
			SQLQuery query = from(con, f)
					.where(f.forgotPasswordId.eq(id));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<Tuple> rows = query.list(f.all());
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
	    	return new ForgotPasswordMapper(f.all()).convert(rows.get(0), f.all());
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
	public long findTotalByDateRange(String email, Date startDate, Date endDate,
			ForgotPasswordStatusEnum status) throws DBConnectionException,
			FinderException, DatabaseException {
		
		if (VUTIL.isEmpty(email) || VUTIL.isNull(startDate)
				|| VUTIL.isNull(endDate) || VUTIL.isNull(status)) {
			FinderException fe = new FinderException("Null data passed to get forgot password");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QForgotPassword f = QForgotPassword.forgotPassword;
			
			SQLQuery query = from(con, f)
					.where(f.emailAddr.eq(email).and(
							f.createdDate.after(
									new Timestamp(startDate.getTime())).or(
									f.createdDate.before(new Timestamp(endDate
											.getTime())))).and(
							f.status.eq(new Byte(status.getId()+""))));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<Long> totals = query.list(f.forgotPasswordId.count());
	    	
	    	if((totals == null) || (totals.isEmpty())) {
	    		return 0;
	    	}
	    	
	    	return totals.get(0);
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
	public void deleteByAccountId(Long accountId) throws DBConnectionException,
			DeleteException, DatabaseException {
		
		if(VUTIL.isNull(accountId)) {
			DeleteException fe = new DeleteException("Cannot delete forgot password with null accountId");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QForgotPassword f = QForgotPassword.forgotPassword;
			
			SQLDeleteClause s = delete(con, f)
				.where(f.accountId.eq(accountId));
			
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
	public void deleteByEmail(String email) throws DBConnectionException,
			DeleteException, DatabaseException {
		
		if(VUTIL.isEmpty(email)) {
			DeleteException fe = new DeleteException("Cannot delete forgot password with null email");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QForgotPassword f = QForgotPassword.forgotPassword;
			
			SQLDeleteClause s = delete(con, f)
				.where(f.emailAddr.eq(email));
			
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
