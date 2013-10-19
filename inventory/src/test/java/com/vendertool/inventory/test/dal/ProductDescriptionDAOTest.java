package com.vendertool.inventory.test.dal;

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
import com.vendertool.inventory.dal.dao.ProductDescriptionDao;
import com.vendertool.inventory.dal.dao.ProductDescriptionDaoFactory;
import com.vendertool.sharedtypes.core.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dal/dal-module.xml",
		"classpath:dal/InventoryDAL.xml" })
public class ProductDescriptionDAOTest implements ApplicationContextAware {
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException { 
		context = applicationContext;
	}
	@Test
	public void testInsert() {
		ProductDescriptionDao productDescriptionDao = ProductDescriptionDaoFactory.getInstance().getProductDescriptionDao();
		Product productDescription = new Product();
		productDescription.setAccountId(1234l);
		productDescription.setTitle("This productDescription is by GK");
		productDescription.setDescription("Well-designed appearance and structure There is a 0.3-megapixel front camera sitting in the middle of the top bezel,useful for video chat. On the top right isthe power/lock button, closely followed bya DC jack, a Mini-USB port and the MICinterface. On the right edge toward the top is a headphone jack, following down with the volume rocker, and a TF Card  slot");
			try {
			  productDescriptionDao.insert(productDescription,12342L);
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

		/*try {
			productDescriptionDao.delete(productDescriptionId);
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DeleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}