package com.vendertool.listing.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysema.query.sql.dml.SQLInsertClause;
import com.vendertool.common.dal.dao.BaseDaoImpl;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.listing.dal.dao.codegen.QListing;
import com.vendertool.sharedtypes.core.Listing;

public class ListingDaoImpl extends BaseDaoImpl implements ListingDao {
	private static final Logger logger = Logger.getLogger(ListingDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	@Override
	public boolean hasSequenceGenerator() {
		return true;
	}

	@Override
	public String getSequenceProcedureName() {
		return "nextvalForListing()";
	}

	@Override
	public void insertAccount(Listing listing) throws DBConnectionException,
			InsertException, DatabaseException {
		if (VUTIL.isNull(listing)) {
			InsertException ie = new InsertException(
					"Cannot insert null listing");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QListing a = QListing.listing;
			
			Long accountId = listing.getListingId();
			if(VUTIL.isNull(accountId) || (accountId.longValue() <= 0)) {
				Long seq = generateNextSequence(con);
				if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
		    		InsertException ie = new InsertException("Unable to generate valid sequence");
					logger.debug(ie.getMessage(), ie);
					throw ie;
				}
				listing.setListingId(seq);
			}
	
	    	// YOU CAN DO THIS...
	//		SQLInsertClause s = insert(con, a)
	//				.populate(account, new AccountMapper(a.all()));
	    	
	    	//OR YOU CAN DO THIS...
	    	SQLInsertClause s = insert(con, a)
	    				.populate(new ListingMapper(a.all()).populateBean(listing));
	    	
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
}