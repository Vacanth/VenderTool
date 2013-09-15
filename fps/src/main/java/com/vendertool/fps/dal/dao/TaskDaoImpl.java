package com.vendertool.fps.dal.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.BaseDaoImpl;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.fps.dal.dao.codegen.QTask;
import com.vendertool.sharedtypes.core.fps.Task;

public class TaskDaoImpl extends BaseDaoImpl implements TaskDao {
	private static final Logger logger = Logger.getLogger(TaskDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
		
	@Override
	public long insert(Task task) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(task)) {
			InsertException ie = new InsertException("Cannot insert null Task");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QTask t = QTask.task;
			Long seq = generateNextSequence(con);
			if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
	    		InsertException ie = new InsertException("Unable to generate valid sequence");
				logger.debug(ie.getMessage(), ie);
				throw ie;
			}
			task.setTaskId(seq);

	    	SQLInsertClause s = insert(con, t)
	    				.populate(new TaskMapper(t.all()).populateBean(task));
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		InsertException ie = new InsertException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
	    	}
	    	return seq;
		} finally {
			closeConnection(con);
		}
	}
	
	@Override
	public void update(Task task, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(task) || VUTIL.isNull(updateSet)){
			UpdateException ue = new UpdateException("Cannot update null task");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QTask t = QTask.task;
	
	    	SQLUpdateClause s = update(con, t)
					.populate(task, new TaskMapper(updateSet));

	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		UpdateException ue = new UpdateException(e);
				logger.debug(ue.getMessage(), ue);
				throw ue;
	    	}
		} finally {
			closeConnection(con);
		}
	}
		
	@Override
	public void delete(Task task) 
			throws DBConnectionException, DeleteException, DatabaseException{
		delete(task.getTaskId());
	}
		
	
	@Override
	public void delete(long taskId) throws DBConnectionException,
			DeleteException {
		Connection con = null;
		
		try {
			con = getConnection();
			
			QTask t = QTask.task;
			
			SQLDeleteClause s = delete(con, t)
				.where(t.taskId.eq(taskId));
	    	logger.info("DAL QUERY: " + s.toString());
	    	
	    	try {
	    		s.execute();
	    	} catch (Exception e) {
	    		DeleteException ie = new DeleteException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
	    	}
		} finally {
			closeConnection(con);
		}
	}
	
	@Override
	public Task findByTaskId(long taskId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QTask t = QTask.task;
			
			SQLQuery query = from(con, t)
					.where(t.taskId.eq(taskId));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			Task task = new TaskMapper(readSet).convert(rows.get(0), readSet);
			if(task == null) {
				FinderException fe = new FinderException("Cannot find task");
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return task;
		} finally {
			closeConnection(con);
		}
	}
	
	public List<Task> findByReqGroupId(String groupId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QTask t = QTask.task;
			
			SQLQuery query = from(con, t)
					.where(t.requestGroupId.eq(groupId));

	    	logger.info("DAL QUERY: " + query.toString());	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
			return convertTupleToTask(rows, readSet);
			
		} finally {
			closeConnection(con);
		}
	}
	
	public List<Task> findByAccountId(long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QTask t = QTask.task;
			
			SQLQuery query = from(con, t)
					.where(t.accountId.eq(accountId));

	    	logger.info("DAL QUERY: " + query.toString());	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
			return convertTupleToTask(rows, readSet);
			
		} finally {
			closeConnection(con);
		}
		
	}
	
	public List<Task> findByJobId(long jobId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QTask t = QTask.task;
			
			SQLQuery query = from(con, t)
					.where(t.jobId.eq(jobId));

	    	logger.info("DAL QUERY: " + query.toString());	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
			return convertTupleToTask(rows, readSet);
			
		} finally {
			closeConnection(con);
		}
		
	}
	
	private List<Task> convertTupleToTask(List<Tuple> rows, Path<?>[] readSet) 
		throws DatabaseException {

		if((rows == null) || (rows.isEmpty())) {
			return null;
		}
	
		List<Task> taskList = new ArrayList<Task>(rows.size());
		ListIterator<Tuple> iter = rows.listIterator();
		Task task = null;
	
		while (iter.hasNext()) {
			task = new TaskMapper(readSet).convert(iter.next(), readSet);
			if(task != null) {
				taskList.add(task);
			}
		}
		
		return taskList;
	}
	
	@Override
	public boolean hasSequenceGenerator() {
		return true;
	}

	@Override
	public String getSequenceProcedureName() {
		return "nextvalForTask()";
	}
	
	private void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e){
			logger.debug(e.getMessage(), e);
		}
	}
}
