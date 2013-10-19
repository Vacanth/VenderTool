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
import com.vendertool.inventory.dal.dao.ProductDao;
import com.vendertool.inventory.dal.dao.ProductDaoFactory;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dal/dal-module.xml",
		"classpath:dal/InventoryDAL.xml" })
public class InventoryDAOTest implements ApplicationContextAware {
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException { 
		context = applicationContext;
	}
	@Test
	public void testInsert() {
		ProductDao productDao = ProductDaoFactory.getInstance().getProductDao();
		Product product = new Product();
		product.setAccountId(1234l);
		product.setTitle("This product is by GK");
		Date now = new Date();
		product.setCreateDate(now);
		product.setLastModifiedDate(now);
		product.setSku("R21344");
		Amount amount = new Amount();
		amount.setCurrency(Currency.getInstance("ARS"));
		amount.setValue(new BigDecimal("123.56"));
		product.setPrice(amount);
		product.setProductCode("code123");
		product.setQuantity(1);
		Long productId = 0l;
		try {
			productId = productDao.insert(product);
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
			productDao.delete(productId);
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