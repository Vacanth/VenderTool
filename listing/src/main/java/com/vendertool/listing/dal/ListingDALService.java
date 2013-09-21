package com.vendertool.listing.dal;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.listing.dal.dao.ListingDAOFactory;
import com.vendertool.listing.dal.dao.ListingDao;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.Product;

public class ListingDALService {

	private static final Logger logger = Logger
			.getLogger(ListingDALService.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
	private static final String LOG_STRING = "@ListingDALService : ";

	private ListingDao listingDAO;

	private ListingDALService() {
		listingDAO = ListingDAOFactory.getInstance().getListingDao();
	}

	private static class ListingDALServiceHolder {
		private static final ListingDALService INSTANCEHOLDER = new ListingDALService();
	}

	public static ListingDALService getInstance() {
		return ListingDALServiceHolder.INSTANCEHOLDER;
	}

	public Long createListing(Listing listing) throws DBConnectionException,
			FinderException, InsertException, DatabaseException {

		if (VUTIL.isNull(listing)) {
			return null;
		}
		Product product = listing.getProduct();

		if (VUTIL.isNull(product)) {
			throw new InsertException(LOG_STRING + " createListing() :"
					+ "Product is required while inserting Listing.");
		}
		//TODO insert product first.
//		Product
		Long listingID = listingDAO.generateNextSequence();
		listing.setListingId(listingID);
		listingDAO.insertAccount(listing);//TODO if there are any failures, delete the previous product entry.
		return listingID;
	}

	public void removeListing(Long listingId) throws DeleteException,
			DBConnectionException, DatabaseException, FinderException {
		listingDAO.deleteListing(listingId);
	}
}