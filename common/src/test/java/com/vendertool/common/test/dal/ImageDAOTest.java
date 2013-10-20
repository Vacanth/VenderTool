package com.vendertool.common.test.dal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vendertool.common.dal.dao.ImageDao;
import com.vendertool.common.dal.dao.ImageDaoFactory;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.sharedtypes.core.Image;
import com.vendertool.sharedtypes.core.Image.ImageFormatEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dal/dal-module.xml",
		"classpath:dal/CommonDAL.xml" })
public class ImageDAOTest implements ApplicationContextAware {
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException { 
		context = applicationContext;
	}
	@Test
	public void testInsert() {
		ImageDao imageDao = ImageDaoFactory.getInstance().getImageDao();
		Image image = new Image();
		image.setAccountId(1234l);
		image.setImageId(123L);
		image.setImgurl("test url ");
		image.setName("first image");
		image.setSize("1024");
		image.setFormat(ImageFormatEnum.JPEG);
		image.setSortOrderId((byte) 1);
		try {
			  imageDao.insert(image,123L,(byte) 123);
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
			imageDao.delete(imageId);
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