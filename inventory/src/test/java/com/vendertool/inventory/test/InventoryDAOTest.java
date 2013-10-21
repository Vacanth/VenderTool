package com.vendertool.inventory.test;

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
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.inventory.dal.dao.ProductDao;
import com.vendertool.inventory.dal.dao.ProductDaoFactory;
import com.vendertool.inventory.dal.fieldset.ProductReadSet;
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
		product.setAccountId(123534l);
		product.setTitle("This product is by GK");
		Date now = new Date();
		product.setCreateDate(now);
		product.setLastModifiedDate(now);
		product.setSku("R213445");
		Amount amount = new Amount();
		amount.setCurrency(Currency.getInstance("ARS"));
		amount.setValue(new BigDecimal("123.56"));
		product.setPrice(amount);
		product.setProductCode("code123");
		product.setQuantity(1);
		Long productId = 14l;
		try {
//			productId = productDao.insert(product);
//			Product product3 =productDao.findByAccountIdAndProductId(1234l, productId, ProductReadSet.getInstance().ALL);
			Product product2 = productDao.findBySKU(123534l,"R21344",
					ProductReadSet.getInstance().ALL);
			System.out.println("");
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FinderException e) {
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