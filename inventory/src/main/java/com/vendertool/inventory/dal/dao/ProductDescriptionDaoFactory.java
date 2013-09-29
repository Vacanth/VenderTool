package com.vendertool.inventory.dal.dao;

import com.vendertool.common.dal.dao.BaseDaoFactory;
import com.vendertool.common.dal.exception.DatabaseException;

public class ProductDescriptionDaoFactory extends BaseDaoFactory {

	private ProductDescriptionDaoFactory() {
		super();
	}
	
	private static class ProductDescriptionDaoFactoryHolder {
		private static final ProductDescriptionDaoFactory INSTANCE = new ProductDescriptionDaoFactory();
	}
	
	public static ProductDescriptionDaoFactory getInstance() {
		return ProductDescriptionDaoFactoryHolder.INSTANCE;
	}
	
	public ProductDescriptionDao getProductDescriptionDao() {
		return (ProductDescriptionDao) getDao(ProductDescriptionDao.class);
	}
	
	@Override
	protected void register() throws DatabaseException {
		add(ProductDescriptionDao.class, getBean("productDescriptionDAO"));
	}
}
