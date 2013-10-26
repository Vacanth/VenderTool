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
import com.vendertool.fps.dal.dao.codegen.QJob;
import com.vendertool.sharedtypes.core.fps.Job;

public class JobDaoImpl extends BaseDaoImpl implements JobDao {
	private static final Logger logger = Logger.getLogger(JobDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
		
	@Override
	public long insert(Job job) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(job)) {
			InsertException ie = new InsertException("Cannot insert null Job");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QJob j = QJob.job;
			Long seq = generateNextSequence(con);
			if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
	    		InsertException ie = new InsertException("Unable to generate valid sequence");
				logger.debug(ie.getMessage(), ie);
				throw ie;
			}
			job.setJobId(seq);

	    	SQLInsertClause s = insert(con, j)
	    				.populate(new JobMapper(j.all()).populateBean(job));
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
	public void update(Job job, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(job) || VUTIL.isNull(updateSet)){
			UpdateException ue = new UpdateException("Cannot update null job");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QJob j = QJob.job;
	
	    	SQLUpdateClause s = update(con, j)
					.populate(job, new JobMapper(updateSet));

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
	public void delete(Job job) 
			throws DBConnectionException, DeleteException, DatabaseException{
		delete(job.getJobId());
	}
		
	
	@Override
	public void delete(long jobId) throws DBConnectionException,
			DeleteException {
		Connection con = null;
		
		try {
			con = getConnection();
			
			QJob j = QJob.job;
			
			SQLDeleteClause s = delete(con, j)
				.where(j.jobId.eq(jobId));
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
	public Job findByJobId(long jobId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QJob j = QJob.job;
			
			SQLQuery query = from(con, j)
					.where(j.jobId.eq(jobId));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			Job job = new JobMapper(readSet).convert(rows.get(0), readSet);
			if(job == null) {
				FinderException fe = new FinderException("Cannot find job");
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return job;
		} finally {
			closeConnection(con);
		}
	}
	
	public List<Job> findByReqGroupId(String groupId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QJob j = QJob.job;
			
			SQLQuery query = from(con, j)
					.where(j.requestFileGroupId.eq(groupId));

	    	logger.info("DAL QUERY: " + query.toString());	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
			return convertTupleToJob(rows, readSet);
			
		} finally {
			closeConnection(con);
		}
	}
	
	public List<Job> findByAccountId(long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QJob f = QJob.job;
			
			SQLQuery query = from(con, f)
					.where(f.accountId.eq(accountId));

	    	logger.info("DAL QUERY: " + query.toString());	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
			return convertTupleToJob(rows, readSet);
			
		} finally {
			closeConnection(con);
		}
		
	}
	
	public List<Job> findAllJobs( Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QJob f = QJob.job;
			
			SQLQuery query = from(con, f);

	    	logger.info("DAL QUERY: " + query.toString());	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
			return convertTupleToJob(rows, readSet);
			
		} finally {
			closeConnection(con);
		}
		
	}
	
	private List<Job> convertTupleToJob(List<Tuple> rows, Path<?>[] readSet) 
		throws DatabaseException {

		if((rows == null) || (rows.isEmpty())) {
			return null;
		}
	
		List<Job> jobList = new ArrayList<Job>(rows.size());
		ListIterator<Tuple> iter = rows.listIterator();
		Job job = null;
	
		while (iter.hasNext()) {
			job = new JobMapper(readSet).convert(iter.next(), readSet);
			if(job != null) {
				jobList.add(job);
			}
		}
		
		return jobList;
	}
	
	@Override
	public boolean hasSequenceGenerator() {
		return true;
	}

	@Override
	public String getSequenceProcedureName() {
		return "nextvalForJob()";
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
