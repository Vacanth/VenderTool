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
import com.vendertool.sharedtypes.core.fps.Task;

public interface TaskDao extends BaseDao {
	public long insert (Task task) throws DBConnectionException,
			InsertException, DatabaseException;
	
	public void update(Task task, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException;
	
	public void delete(Task task) 
		throws DBConnectionException, DeleteException, DatabaseException;
	
	public void delete(long taskId)
		throws DBConnectionException, DeleteException, DatabaseException;
		
	public Task findByTaskId(long taskId, Path<?>[] readSet)
		throws DBConnectionException, FinderException, DatabaseException;
	
	public List<Task> findByJobId(long taskId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
		
	public List<Task> findByReqGroupId(String reqGroupId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public List<Task> findByAccountId(long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException;
	
	public void insertTasksWithEvents(List<Task> lTask) throws DBConnectionException,
					InsertException, DatabaseException;
	
}
