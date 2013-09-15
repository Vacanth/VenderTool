package com.vendertool.fps.dal.dao;

import java.util.List;

import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.sharedtypes.core.fps.File;

public interface FileDao extends BaseDao {
	public long insert (File file) throws DBConnectionException,
			InsertException, DatabaseException;
	
	public void update(File file, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void delete(File file) 
		throws DBConnectionException, DeleteException, DatabaseException;
	
	public void delete(long fileId)
		throws DBConnectionException, DeleteException, DatabaseException;
		
	public File findByFileId(long fileId, Path<?>[] readSet)
		throws DBConnectionException, FinderException, DatabaseException;
	
	public List<File> findByGroupId(String groupId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public List<File> findByAccountId(long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
}
