package com.vendertool.common.test.dal;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import com.vendertool.common.dal.dao.AddressDao;
import com.vendertool.common.dal.dao.AddressDaoFactory;
import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.dao.codegen.QAddress;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.Address.AddressStatusEnum;
import com.vendertool.sharedtypes.core.Address.AddressTypeEnum;
import com.vendertool.sharedtypes.core.Address.AddressUsecaseEnum;
import com.vendertool.sharedtypes.core.CountryEnum;

public class AddressDaoTest extends BaseDaoTest {

	private static final int ADDRESS_COUNT = 10;
	private static final Long ACCOUNTID = 600L;
	private static final int SLEEP_INTERVAL_BWT_INSERTS = 1000;
	
	Address[] addresses;
	AddressDao dao;
	QAddress a;
	
	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, DeleteException,
			FinderException, UpdateException {
		
		AddressDaoTest test = new AddressDaoTest();
		test.testCRUD();
		test.cleanup();
	}
	
	private void testCRUD() throws DBConnectionException, InsertException,
			DatabaseException, FinderException, UpdateException, DeleteException {
		
		log("******* Address DAOImpl test started *********");
		
		insertAddresses();
		
		findAndAssertAddresses();
		
		updateAddressMetadata();
		
		findAndAssertAddresses();
		
		updateAddressStatuses();
		
		findAndAssertAccountAddresses(false);
		
		log("***** soft delete *****");
		dao.deleteByAccountId(ACCOUNTID, false);
		
		findAndAssertAccountAddresses(false);
		
		log("***** hard delete *****");
		dao.deleteByAccountId(ACCOUNTID, true);
		
		findAndAssertAccountAddresses(true);
		
		log("******* Tests Done! ********");
	}

	private void findAndAssertAccountAddresses(boolean nullexpected) throws DBConnectionException,
			FinderException, DatabaseException {
		
		log("***** find by account id which is sorted by creation date ********");
		
		List<Address> dbAddrs = dao.findByAccountId(ACCOUNTID);
		if(nullexpected) {
			Assert.assertNull(dbAddrs);
			return;
		}
		
		Assert.assertNotNull(dbAddrs);
		int idx = ADDRESS_COUNT-1;
		for(Address dbaddr : dbAddrs) {
			Assert.assertNotNull(dbaddr);
			Address local = addresses[idx--];
			Assert.assertEquals(local.locationEquals(dbaddr), true);
			log("DB Address: " + dbaddr.toString());
		}
	}

	private void updateAddressStatuses() throws DBConnectionException,
			UpdateException, DatabaseException {
		
		log("***** Dal update statuses ******");
		for(int idx = 0; idx < ADDRESS_COUNT; idx++) {
			Address addr = addresses[idx];
			Long addrId = addr.getId();
			dao.updateStatusById(addrId, AddressStatusEnum.VERIFIED);
		}
	}

	@SuppressWarnings("static-access")
	private void insertAddresses() throws DBConnectionException,
			InsertException, DatabaseException {
		log("***** Dal insert ******");
		for(int idx = 0; idx < ADDRESS_COUNT; idx++) {
			Address addr = addresses[idx];
			addr.setCreatedDate(new Date());
			Long addrId = dao.insert(ACCOUNTID, addr);
			addr.setId(addrId);
			try {
				//This is to ensure create date is distinct & we can use the order by query for the test
				log("sleep " + SLEEP_INTERVAL_BWT_INSERTS + " ms");
				Thread.currentThread().sleep(SLEEP_INTERVAL_BWT_INSERTS);
			} catch (InterruptedException e) {
				log(e);
			}
		}
	}

	private void updateAddressMetadata() throws DBConnectionException,
			UpdateException, DatabaseException {
		log("***** Dal update ******");
		for(int idx = 0; idx < ADDRESS_COUNT; idx++) {
			Address addr = addresses[idx];

			addr.setAddressType(AddressTypeEnum.BUSINESS);
			addr.setStatus(null);
			addr.setAddressUseCase(AddressUsecaseEnum.SHIPPING);
			
			dao.updateAddressMetadata(addr);
		}
	}

	private void findAndAssertAddresses() throws DBConnectionException,
			FinderException, DatabaseException {
		
		log("***** Dal find & assertions ******");
		for(int idx = 0; idx < ADDRESS_COUNT; idx++) {
			Address addr = addresses[idx];
			Address dbAddr = dao.findById(addr.getId());
			Assert.assertNotNull(dbAddr);
			Assert.assertEquals(addr.locationEquals(dbAddr), true);
			log(dbAddr.toString());
		}
	}

	protected AddressDaoTest() {
		super();
	}
	
	@Override
	protected void setupTestData() {
		a = QAddress.address;
		dao = (AddressDao) getDao();
		
		addresses = new Address[ADDRESS_COUNT];
		for(int idx = 0; idx < ADDRESS_COUNT; idx++) {
			addresses[idx] = createAddress(idx);
		}
	}

	private Address createAddress(int idx) {
		Address addr = new Address();
		addr.setAddressLine1("400 MyStreet, B" + idx);
		addr.setAddressLine2("Next to moon"+idx);
		addr.setAddressType(AddressTypeEnum.RESENTIAL);
		addr.setAddressUseCase(AddressUsecaseEnum.REGISTRATION);
		addr.setCity("MyCity"+idx);
		addr.setCompany("MyCompany"+idx);
		addr.setCountry(CountryEnum.UNITED_STATES);
		addr.setPostalCode("95125");
		addr.setState("California");
		addr.setStatus(AddressStatusEnum.VERIFIED);
		
		return addr;
	}

	@Override
	protected void cleanup() {
		dao.cleanup();
	}

	@Override
	protected BaseDao getDao() {
		if(dao == null) {
			AddressDaoFactory factory = AddressDaoFactory.getInstance();
			dao = factory.getAddressDao();
		}
		
		return dao;
	}
}