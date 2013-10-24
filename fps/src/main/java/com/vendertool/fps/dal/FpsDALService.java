package com.vendertool.fps.dal;

import java.util.List;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.fps.dal.dao.FileDao;
import com.vendertool.fps.dal.dao.FpsDaoFactory;
import com.vendertool.fps.dal.dao.JobDao;
import com.vendertool.fps.dal.dao.TaskDao;
import com.vendertool.fps.dal.fieldset.FileReadSet;
import com.vendertool.fps.dal.fieldset.JobReadSet;
import com.vendertool.fps.dal.fieldset.JobUpdateSet;
import com.vendertool.fps.dal.fieldset.TaskReadSet;
import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.core.fps.Job;
import com.vendertool.sharedtypes.core.fps.Task;

public class FpsDALService {
	private static final Logger logger = Logger.getLogger(FpsDALService.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
	JobDao 	jobDao;
	FileDao fileDao;
	TaskDao taskDao;
	
	private FpsDALService() {
		jobDao 	= FpsDaoFactory.getInstance().getJobDao();
		fileDao = FpsDaoFactory.getInstance().getFileDao();
		taskDao = FpsDaoFactory.getInstance().getTaskDao();
	}
	
	private static class FpsDALServiceHolder {
		private static final FpsDALService INSTANCE = new FpsDALService();
	}
	
	public static FpsDALService getInstance() {
		return FpsDALServiceHolder.INSTANCE;
	}
	
	public void uploadFiles(List<File> files)  throws DBConnectionException,
			InsertException, DatabaseException{
	
		if(VUTIL.isEmptyList(files)) {
			return;
		}

		fileDao.insertFiles(files);
	}
	
	public Long createJob(Job job) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if (VUTIL.isNull(job)) {
			return null;
		}
		return jobDao.insert(job);
	}
	
	public Job getJob(Long jobId) throws DBConnectionException,
			FinderException, DatabaseException {
		
		if (VUTIL.isNull(jobId)) {
			return null;
		}

		return jobDao.findByJobId(jobId, JobReadSet.getInstance().FULL);
	}
	
	public List<File> getFiles(String fileGroupId) throws DBConnectionException,
			FinderException, DatabaseException {
		
		if (VUTIL.isNull(fileGroupId) || VUTIL.isEmpty(fileGroupId)) {
			return null;
		}
		return fileDao.findByGroupId(fileGroupId, FileReadSet.getInstance().FULL);

	}
	
	public void updateFileStatus(List<File> files)  throws DBConnectionException,
			UpdateException, DatabaseException{

		if(VUTIL.isEmptyList(files)) {
			return;
		}
		
		fileDao.updateFileStatus(files);

	}
	
	public void updateJobStatus(Job job)  throws DBConnectionException,
			UpdateException, DatabaseException{

		if(VUTIL.isNull(job)) {
			return;
		}
		
		jobDao.update(job, JobUpdateSet.getInstance().STATUS);
		
	}
		
	public Task getTask(Long taskId) throws DBConnectionException,
			FinderException, DatabaseException{
		
		if (VUTIL.isNull(taskId)) {
			return null;
		}
		
		return taskDao.findByTaskId(taskId, TaskReadSet.getInstance().FULL);
	}

	public List<Job> getJobs(Long accountId) throws DBConnectionException,
			FinderException, DatabaseException{
	
		if (VUTIL.isNull(accountId)) {
			return null;
		}
		return jobDao.findByAccountId(accountId, JobReadSet.getInstance().FULL);
	}
	
	public void insertTaskWithEvents(List<Task> lTask) {
		if (lTask != null && !lTask.isEmpty()) {
			try {
				taskDao.insertTasksWithEvents(lTask);
			} catch (DBConnectionException e) {
			} catch (InsertException e) {
			} catch (DatabaseException e) {
			}
		}
	}
}
