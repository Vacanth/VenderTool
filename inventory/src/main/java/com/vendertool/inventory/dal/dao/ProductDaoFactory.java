package com.vendertool.inventory.dal.dao;

import com.vendertool.common.dal.dao.BaseDaoFactory;
import com.vendertool.common.dal.exception.DatabaseException;

public class ProductDaoFactory extends BaseDaoFactory {

	private ProductDaoFactory() {
		super();
	}
	
	private static class ProductDaoFactoryHolder {
		private static final ProductDaoFactory INSTANCE = new ProductDaoFactory();
	}
	
	public static ProductDaoFactory getInstance() {
		return ProductDaoFactoryHolder.INSTANCE;
	}
	
	public ProductDao getProductDao() {
		return (ProductDao) getDao(ProductDao.class);
	}
	
	@Override
	protected void register() throws DatabaseException {
		add(ProductDao.class, getBean("productDAO"));
	}
}
