package com.vendertool.listing.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.BaseDaoImpl;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.listing.dal.dao.codegen.QListing;
import com.vendertool.listing.dal.dao.codegen.QListingVariation;
import com.vendertool.sharedtypes.core.ListingVariation;

public class ListingVariationDaoImpl extends BaseDaoImpl implements ListingVariationDao{

	private static final Logger logger = Logger.getLogger(ListingVariationDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
			
	@Override
	public void insertListingVariation(ListingVariation listingVariation) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(listingVariation)) {
			InsertException ie = new InsertException("Cannot insert null account");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QListingVariation a = QListingVariation.listingVariation;
			
			Long listingVariationId = listingVariation.getListingVariationId();
			if(VUTIL.isNull(listingVariationId) || (listingVariationId.longValue() <= 0)) {
				Long seq = generateNextSequence(con);
				if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
		    		InsertException ie = new InsertException("Unable to generate valid sequence");
					logger.debug(ie.getMessage(), ie);
					throw ie;
				}
				listingVariation.setListingVariationId(seq);
			}
	
	    	// YOU CAN DO THIS...
	//		SQLInsertClause s = insert(con, a)
	//				.populate(account, new AccountMapper(a.all()));
	    	
	    	//OR YOU CAN DO THIS...
	    	SQLInsertClause s = insert(con, a)
	    				.populate(new ListingVariationMapper(a.all()).populateBean(listingVariation));
	    	
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
	public boolean hasSequenceGenerator() {
		return true;
	}

	@Override
	public String getSequenceProcedureName() {
		return "nextvalForListingVariation()";
	}

	@Override
	public void updateListing(ListingVariation listingVariation,
			Path<?>[] updateSet) throws DBConnectionException, UpdateException,
			DatabaseException {

		if (VUTIL.isNull(listingVariation) || VUTIL.isNull(updateSet)) {
			UpdateException ue = new UpdateException(
					"Cannot update null listingVariation");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}

		Connection con = null;

		try {
			con = getConnection();

			QListing l = QListing.listing;

			SQLUpdateClause s = update(con, l).populate(listingVariation,
					new ListingVariationMapper(updateSet));

			// Always log the query before executing it
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
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
		}
	
	}

	@Override
	public void deleteListing(Long listingVariationId)
			throws DBConnectionException, DeleteException, DatabaseException {
		if (listingVariationId != null && listingVariationId <= 0) {
			DeleteException fe = new DeleteException(
					"Cannot delete null listingVariationId");
			logger.debug(fe.getMessage(), fe);
			throw fe;
		}

		Connection con = null;

		try {
			con = getConnection();

			QListingVariation l = QListingVariation.listingVariation;

			SQLDeleteClause s = delete(con, l).where(l.listingVariationId.eq(listingVariationId));

			// Always log the query before executing it
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
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug(e.getMessage(), e);
			}
		}
	}
}