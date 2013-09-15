package com.vendertool.common.dal.dao;

import java.util.List;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.Address.AddressStatusEnum;

public interface AddressDao extends BaseDao {
	public Long insert(Long accountId, Address addr) throws DBConnectionException,
			InsertException, DatabaseException;
	
	public Address findById(Long id) throws DBConnectionException, FinderException,
			DatabaseException;
	
	public void deleteById(Long id, boolean force) throws DBConnectionException, DeleteException,
			DatabaseException;
	
	public void deleteByAccountId(Long accountId, boolean force) throws DBConnectionException, DeleteException,
			DatabaseException;
	
	public void updateAddressMetadata(Address addr)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void updateStatusById(Long id, AddressStatusEnum status)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void updateStatusByAccountId(Long accountId, AddressStatusEnum status)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public List<Address> findByAccountId(Long accountId) throws DBConnectionException, FinderException,
			DatabaseException;
}
