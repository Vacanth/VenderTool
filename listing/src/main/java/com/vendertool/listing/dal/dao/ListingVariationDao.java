package com.vendertool.listing.dal.dao;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.sharedtypes.core.ListingVariation;

public interface ListingVariationDao extends BaseDao {

	void insertListingVariation(ListingVariation account)
			throws DBConnectionException, InsertException, DatabaseException;

}