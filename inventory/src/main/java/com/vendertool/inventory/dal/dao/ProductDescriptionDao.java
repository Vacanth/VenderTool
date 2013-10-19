package com.vendertool.inventory.dal.dao;


import java.util.List;

import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.sharedtypes.core.Product;

public interface ProductDescriptionDao extends BaseDao {
	
	public void insert(Product productDescription,Long productId) throws DBConnectionException,
			InsertException, DatabaseException;
	
	public void delete(long productId)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public void update(Product productDescription, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public List<Product> findByProductId(Long productId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public Product findByProductIdAndAccountId(Long productId,Long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;


	

		

	
	
}
