package com.vendertool.inventory.dal.dao;

import com.vendertool.common.dal.dao.BaseDaoFactory;
import com.vendertool.common.dal.exception.DatabaseException;

public class ProductVariationDaoFactory extends BaseDaoFactory {

	private ProductVariationDaoFactory() {
		super();
	}
	
	private static class ProductVariationDaoFactoryHolder {
		private static final ProductVariationDaoFactory INSTANCE = new ProductVariationDaoFactory();
	}
	
	public static ProductVariationDaoFactory getInstance() {
		return ProductVariationDaoFactoryHolder.INSTANCE;
	}
	
	public ProductVariationDao getProductVariationDao() {
		return (ProductVariationDao) getDao(ProductVariationDao.class);
	}
	
	@Override
	protected void register() throws DatabaseException {
		add(ProductVariationDao.class, getBean("productVariationDAO"));
	}
}
