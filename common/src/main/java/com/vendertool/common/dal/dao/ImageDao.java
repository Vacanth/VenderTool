package com.vendertool.common.dal.dao;


import java.util.List;

import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.sharedtypes.core.Image;

public interface ImageDao extends BaseDao {
	
	public long insert(Image image) throws DBConnectionException,
			InsertException, DatabaseException;
	
	public void delete(Image image)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public void delete(long imageId)
			throws DBConnectionException, DeleteException, DatabaseException;
	
	public void update(Image image, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void updateHostedUrl(Long accountId,Long imageId,String hostedUrl)
			throws DBConnectionException, UpdateException, DatabaseException;

	public List<Image> findByAccountId(Long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public List<Image> findByAccountIdRefIdAndRefType(Long accountId, Long refId, Byte refType, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public Image findByAccountIdAndImageId(Long accountId,Long imageId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;


	

		

	
	
}
