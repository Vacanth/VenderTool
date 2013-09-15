package com.vendertool.fps.dal.dao;

import com.vendertool.common.dal.dao.BaseDaoFactory;
import com.vendertool.common.dal.exception.DatabaseException;


public class FpsDaoFactory extends BaseDaoFactory {
	
	private FpsDaoFactory() {
		super();
	}
	
	private static class FpsDaoFactoryHolder {
		private static final FpsDaoFactory INSTANCE = new FpsDaoFactory();
	}
	
	public static FpsDaoFactory getInstance() {
		return FpsDaoFactoryHolder.INSTANCE;
	}
	
	public FileDao getFileDao() {
		return (FileDao) getDao(FileDao.class);
	}
	
	public TaskDao getTaskDao() {
		return (TaskDao) getDao(TaskDao.class);
	}
	
	public JobDao getJobDao() {
		return (JobDao) getDao(JobDao.class);
	}

	@Override
	protected void register() throws DatabaseException {
		add(JobDao.class, getBean("jobDao"));
		add(TaskDao.class, getBean("taskDao"));
		add(FileDao.class, getBean("fileDao"));
	}
}
