package com.vendertool.listing.dal.dao;

import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.sharedtypes.core.Listing;

public interface ListingDao extends BaseDao {

	public void insertAccount(Listing listing) throws DBConnectionException,
	InsertException, DatabaseException;
	
	public void updateListing(Listing listing, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void deleteListing(Long listingId)
			throws DBConnectionException, DeleteException, DatabaseException;
}