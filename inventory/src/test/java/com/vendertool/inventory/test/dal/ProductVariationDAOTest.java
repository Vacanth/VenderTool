package com.vendertool.inventory.test.dal;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

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
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.inventory.dal.dao.ProductVariationDao;
import com.vendertool.inventory.dal.dao.ProductVariationDaoFactory;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.ProductVariation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dal/dal-module.xml",
		"classpath:dal/InventoryDAL.xml" })
public class ProductVariationDAOTest implements ApplicationContextAware {
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException { 
		context = applicationContext;
	}
	@Test
	public void testInsert() {
		ProductVariationDao productVariationDao = ProductVariationDaoFactory.getInstance().getProductVariationDao();
		ProductVariation productVariation = new ProductVariation();
		productVariation.setTitle("This productVariation is by GK");
		productVariation.setSku("R21344");
		Amount amount = new Amount();
		amount.setCurrency(Currency.getInstance("ARS"));
		amount.setValue(new BigDecimal("123.56"));
		productVariation.setPrice(amount);
		productVariation.setQuantity(1);
		Long productVariationId = 0l;
		try {
			productVariationId = productVariationDao.insert(productVariation,12345L);
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

		try {
			productVariationDao.delete(productVariationId);
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DeleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}