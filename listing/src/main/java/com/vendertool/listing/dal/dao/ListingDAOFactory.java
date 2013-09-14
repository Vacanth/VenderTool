package com.vendertool.listing.dal.dao;

import com.vendertool.common.dal.dao.BaseDaoFactory;
import com.vendertool.common.dal.exception.DatabaseException;

public class ListingDAOFactory extends BaseDaoFactory {

	private ListingDAOFactory() {
		super();
	}

	private static class ListingDAOFactoryyHolder {
		private static final ListingDAOFactory INSTANCE = new ListingDAOFactory();
	}

	public static ListingDAOFactory getInstance() {
		return ListingDAOFactoryyHolder.INSTANCE;
	}

	public ListingDao getListingDao() {
		return (ListingDao) getDao(ListingDao.class);
	}
	
	public ListingVariationDao getListingVariationDao() {
		return (ListingVariationDao) getDao(ListingVariationDao.class);
	}
	
	@Override
	protected void register() throws DatabaseException {
		add(ListingDao.class, getBean("listingDAO"));
		add(ListingVariationDao.class, getBean("listingVariationDAO"));
	}
}