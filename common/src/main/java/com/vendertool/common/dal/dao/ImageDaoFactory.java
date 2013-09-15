package com.vendertool.common.dal.dao;

import com.vendertool.common.dal.exception.DatabaseException;

public class ImageDaoFactory extends BaseDaoFactory {

	private ImageDaoFactory() {
		super();
	}
	
	private static class ImageDaoFactoryHolder {
		private static final ImageDaoFactory INSTANCE = new ImageDaoFactory();
	}
	
	public static ImageDaoFactory getInstance() {
		return ImageDaoFactoryHolder.INSTANCE;
	}
	
	public ImageDao getImageDao() {
		return (ImageDao) getDao(ImageDao.class);
	}
	
	@Override
	protected void register() throws DatabaseException {
		add(ImageDao.class, getBean("imageDAO"));
	}
}
