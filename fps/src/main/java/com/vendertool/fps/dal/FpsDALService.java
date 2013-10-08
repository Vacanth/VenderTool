package com.vendertool.fps.dal;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.fps.dal.dao.FileDao;
import com.vendertool.fps.dal.dao.FpsDaoFactory;
import com.vendertool.fps.dal.dao.JobDao;
import com.vendertool.fps.dal.dao.TaskDao;
import com.vendertool.sharedtypes.core.fps.FPSJobStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSUsecaseEnum;
import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.core.fps.Job;

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
	
	public void uploadFiles(List<File> files) {
		try {
			fileDao.insertFiles(files);
		} catch (DBConnectionException e) {
		} catch (InsertException e) {
		} catch (DatabaseException e) {
		}
	}
	
	public void createJob(Job job) {
		try {
			jobDao.insert(job);
		} catch (DBConnectionException e) {
		} catch (InsertException e) {
		} catch (DatabaseException e) {
		}
	}
}
