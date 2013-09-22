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
import com.vendertool.sharedtypes.core.ProductVariation;

public interface ProductVariationDao extends BaseDao {
	
	public long insert(ProductVariation productVariation,Long productId) throws DBConnectionException,
			InsertException, DatabaseException;
	
	public void delete(ProductVariation productVariation)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public void delete(long productVariationId)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public void update(ProductVariation productVariation, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void updateHostedUrl(Long productId,Long productVariationId,String hostedUrl)
			throws DBConnectionException, UpdateException, DatabaseException;

	public List<ProductVariation> findByProductId(Long productId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public ProductVariation findByProductIdAndProductVariationId(Long productId,Long productVariationId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;


	

		

	
	
}
