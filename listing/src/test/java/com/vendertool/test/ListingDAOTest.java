package com.vendertool.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.listing.dal.dao.ListingDAOFactory;
import com.vendertool.listing.dal.dao.ListingDao;
import com.vendertool.sharedtypes.core.Listing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dal/dal-module.xml","classpath:dal/ListingDAL.xml" })
public class ListingDAOTest implements ApplicationContextAware {
	private ApplicationContext context;
	private ListingDao listingDAO;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	@Test
	public void testContext() {
		listingDAO  = ListingDAOFactory.getInstance()
		.getListingDao();
//		Assert.notNull(context.getBean("test"));
		Listing listing = new Listing();
		try {
			listing.setCreateOwnerId(1234L);
			listingDAO.insertAccount(listing);
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}