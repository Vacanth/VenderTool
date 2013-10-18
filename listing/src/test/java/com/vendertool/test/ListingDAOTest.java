package com.vendertool.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.listing.dal.ListingDALService;
import com.vendertool.sharedtypes.core.Classification;
import com.vendertool.sharedtypes.core.Classification.ClassificationTypeEnum;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.core.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dal/dal-module.xml","classpath:dal/ListingDAL.xml" })
public class ListingDAOTest implements ApplicationContextAware {
	private ApplicationContext context;
	private ListingDALService listingDalService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	@Test
	public void testContext() {
		listingDalService  = ListingDALService.getInstance();
//		Assert.notNull(context.getBean("test"));
		Listing listing = new Listing();
		try {
			listing.setCreateOwnerId(1234L);
			listing.setQuantity(2);
			
			Classification c = new Classification();
			c.setClassificationType(ClassificationTypeEnum.CATEGORY);
			c.setClassifierId("T1234");
			List<Classification> classifications = new ArrayList<Classification>();
			classifications.add(c);
			listing.setClassifications(classifications);
			
			Product pro = new Product();
			pro.setProductId(123l);
			listing.setProduct(pro);
			
			listing.setMarket(MarketEnum.MERCADO_LIBRE);
			
			listing.setMasterTemplateId(234l);
			
			System.err.println("inserting listing");
			Long listingId = listingDalService.createListing(listing);
			System.err.println("inserted listing id:" + listingId);
			
			System.err.println("deleting listing");
			listingDalService.removeListing(listingId);
			
			System.err.println("Test Complete");
		} catch (DBConnectionException e) {
			e.printStackTrace();
		} catch (InsertException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		} catch (DeleteException e) {
			e.printStackTrace();
		}
	}
}