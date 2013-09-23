package com.vendertool.registration.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.vendertool.registration.dal.dao.codegen.QAccountSecurityQuestion;
import com.vendertool.registration.dal.dao.codegen.QBeanAccountSecurityQuestion;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;

public class AccountSecurityQuestionDaoImpl extends BaseDaoImpl implements
		AccountSecurityQuestionDao {
	
	private static final Logger logger = Logger.getLogger(AccountSecurityQuestionDaoImpl.class);
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
	public List<Long> insert(Long accountId, List<AccountSecurityQuestion> questions)
			throws DBConnectionException, InsertException, DatabaseException {
		
		if(VUTIL.isNull(accountId) || VUTIL.isNull(questions) || (questions.isEmpty())) {
			InsertException ie = new InsertException("Cannot insert null questions");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QAccountSecurityQuestion as = QAccountSecurityQuestion.accountSecurityQuestion;
			AccountSecurityQuestionMapper mapper = new AccountSecurityQuestionMapper(as.all());
			
			SQLInsertClause s = insert(con, as);
			
			for(AccountSecurityQuestion question : questions) {
				QBeanAccountSecurityQuestion bean = mapper.populateBean(question);
				bean.setAccountId(accountId);
				
				s.populate(bean).addBatch();
			}
	    	
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		return s.executeWithKeys(as.accountSecurityQuestionId);
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
	public List<AccountSecurityQuestion> findAllByAccountId(Long accountId)
			throws DBConnectionException, FinderException, DatabaseException {
		
		if(VUTIL.isNull(accountId)) {
			FinderException fe = new FinderException("Cannot find account with null account id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QAccountSecurityQuestion as = QAccountSecurityQuestion.accountSecurityQuestion;
			
			SQLQuery query = from(con, as)
					.where(as.accountId.eq(accountId));
			
	    	//Always log the query before executing it
	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	List<Tuple> rows = query.list(as.all());
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
	    	List<AccountSecurityQuestion> questions = new ArrayList<AccountSecurityQuestion>();
	    	for(Tuple row : rows) {
				AccountSecurityQuestion question = new AccountSecurityQuestionMapper(
						as.all()).convert(row, as.all());
				questions.add(question);
	    	}
			
			return questions;
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
			DeleteException fe = new DeleteException("Cannot delete with null account id");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QAccountSecurityQuestion as = QAccountSecurityQuestion.accountSecurityQuestion;
			
			SQLDeleteClause s = delete(con, as)
				.where(as.accountId.eq(accountId));
			
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
