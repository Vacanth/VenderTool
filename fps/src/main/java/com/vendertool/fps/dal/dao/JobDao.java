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
import com.vendertool.sharedtypes.core.fps.Job;

public interface JobDao extends BaseDao {
	public long insert (Job job) throws DBConnectionException,
			InsertException, DatabaseException;
	
	public void update(Job job, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void delete(Job job) 
		throws DBConnectionException, DeleteException, DatabaseException;
	
	public void delete(long jobId)
		throws DBConnectionException, DeleteException, DatabaseException;
		
	public Job findByJobId(long jobId, Path<?>[] readSet)
		throws DBConnectionException, FinderException, DatabaseException;
	
	public List<Job> findByReqGroupId(String reqGroupId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public List<Job> findByAccountId(long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
}
