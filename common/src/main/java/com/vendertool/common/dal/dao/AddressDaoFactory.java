package com.vendertool.common.dal.dao;

import com.vendertool.common.dal.exception.DatabaseException;

public class AddressDaoFactory extends BaseDaoFactory {

	private AddressDaoFactory() {
		super();
	}
	
	private static class AddressDaoFactoryHolder {
		private static final AddressDaoFactory INSTANCE = new AddressDaoFactory();
	}
	
	public static AddressDaoFactory getInstance() {
		return AddressDaoFactoryHolder.INSTANCE;
	}
	
	public AddressDao getAddressDao() {
		return (AddressDao) getDao(AddressDao.class);
	}
	
	@Override
	protected void register() throws DatabaseException {
		add(AddressDao.class, getBean("addressDAO"));
	}
}