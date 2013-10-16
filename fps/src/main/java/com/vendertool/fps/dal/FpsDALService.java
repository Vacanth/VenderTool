package com.vendertool.fps.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.fps.dal.dao.FileDao;
import com.vendertool.fps.dal.dao.FpsDaoFactory;
import com.vendertool.fps.dal.dao.JobDao;
import com.vendertool.fps.dal.dao.TaskDao;
import com.vendertool.fps.dal.fieldset.FileReadSet;
import com.vendertool.fps.dal.fieldset.JobReadSet;
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
	
	public boolean uploadFiles(List<File> files) {
		boolean uploadSuccess = false;
		try {
			fileDao.insertFiles(files);
			uploadSuccess = true;
		} catch (DBConnectionException e) {
		} catch (InsertException e) {
		} catch (DatabaseException e) {
		}
		return uploadSuccess;
	}
	
	public long createJob(Job job) {
		long jobId = -1;   //Need to change this as constant
		
		try {
			jobId = jobDao.insert(job);
		} catch (DBConnectionException e) {
		} catch (InsertException e) {
		} catch (DatabaseException e) {
		}
		return jobId;
	}
	
	public Job getJob(long jobId) {
		Job job= null;
		
		if (jobId > 0) {
			try {
				job = jobDao.findByJobId(jobId, JobReadSet.getInstance().FULL);
			} catch (DBConnectionException e) {
			} catch (FinderException e) {
			} catch (DatabaseException e) {
			}
		}
		return job;
	}
	
	public List<File> getFiles(String fileGroupId) {
		List<File> files = new ArrayList<File> ();
		
		if (fileGroupId != null && !fileGroupId.isEmpty()) {
			try {
				files.addAll(fileDao.findByGroupId(fileGroupId, FileReadSet.getInstance().FULL));
			} catch (DBConnectionException e) {
			} catch (FinderException e) {
			} catch (DatabaseException e) {
			}
		}
		return files;
	}
	
	public Task getTask(long taskId) {
		Task task= null;
		
		if (taskId > 0) {
			try {
				task = taskDao.findByTaskId(taskId, JobReadSet.getInstance().FULL);
			} catch (DBConnectionException e) {
			} catch (FinderException e) {
			} catch (DatabaseException e) {
			}
		}
		return task;
	}

	public List<Job> getJobs(long accountId) {
		List<Job> jobs = new ArrayList<Job>(); 
		
		if (accountId > 0) {
			try {
				jobs.addAll(jobDao.findByAccountId(accountId, JobReadSet.getInstance().FULL));
			} catch (DBConnectionException e) {
			} catch (FinderException e) {
			} catch (DatabaseException e) {
			}
		}
		return jobs;
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
