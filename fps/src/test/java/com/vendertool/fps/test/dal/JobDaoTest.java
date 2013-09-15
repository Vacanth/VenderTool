package com.vendertool.fps.test.dal;

import java.sql.SQLException;
import java.util.Date;

import junit.framework.Assert;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.test.dal.BaseDaoTest;
import com.vendertool.fps.dal.dao.FpsDaoFactory;
import com.vendertool.fps.dal.dao.JobDao;
import com.vendertool.fps.dal.dao.codegen.QJob;
import com.vendertool.fps.dal.fieldset.JobReadSet;
import com.vendertool.fps.dal.fieldset.JobUpdateSet;
import com.vendertool.sharedtypes.core.fps.FPSJobStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSUsecaseEnum;
import com.vendertool.sharedtypes.core.fps.Job;

public class JobDaoTest extends BaseDaoTest{
	private static final int JOB_COUNT = 10;
	Job[] jobs;
	JobDao dao;
	QJob qa;
	long lastCount = 0;
	
	protected JobDaoTest() {
		super();
	}
	
	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, DeleteException, FinderException, UpdateException {
		JobDaoTest test = new JobDaoTest();
		test.testCRUD();
		test.cleanup();
	}
	
	@Override
	protected void setupTestData() {
		qa = QJob.job;
		dao = (JobDao) getDao();
		
		jobs = new Job[JOB_COUNT];
		for(int idx = 0; idx < JOB_COUNT; idx++) {
			jobs[idx] = createJob(idx);
		}
	}
	

	private Job createJob(int idx) {
		Date curDate = new Date();
		Job job = new Job();	
		
		job.setJobId(200000L+idx);
		job.setReqFileGroupId("request "+idx);
		job.setResFileGroupId("response "+idx);
		job.setRequestFileSize(1000L);
		job.setResponseFileSize(1000L);
		job.setError("testing the error field");
		job.setAccountId(100000L);
		job.setCreatedDate(curDate);
		job.setLastModifiedDate(curDate);
		job.setStatus(FPSJobStatusEnum.CREATED);
		job.setIsoCountryCode("USA");
		job.setTitle("testing title");
		job.setUsecase(FPSUsecaseEnum.ADD_LISTING);
		
		return job;
	}
	
	private void insert() throws DBConnectionException, InsertException,
			DatabaseException {
		for(Job job : jobs) {
			lastCount = dao.insert(job);
		}
	}
	
	public void update() throws DBConnectionException,UpdateException,DatabaseException,FinderException {
		for (int i=0; i<JOB_COUNT; i++) {
			long cnt = lastCount-i;
			Job job = dao.findByJobId(cnt, JobReadSet.getInstance().FULL);
			job.setReqFileGroupId("new group Id"+ i);
			log("Updating Record"+ cnt);
			dao.update(job,JobUpdateSet.getInstance().FULL);
		}
	}
	
	public void testCRUD() throws DBConnectionException, InsertException,
			DatabaseException, SQLException, DeleteException, FinderException, UpdateException {
		
		//DAL insert
		log("======== INSERT TEST =======");
		setupTestData();
		insert();
		
		//DAL find & JUnit assert
		log("======== FIND AND ASSERT TEST =======");
		for(Job job : jobs) {
			Job t = dao.findByJobId(job.getJobId(), JobReadSet.getInstance().FULL);
			logJob("find by FileId ****\n", t);
			assertJob(job, t);
		}
		
		//DAL update
		log("======== UPDATE ACCOUNT TO THE DATABASE =======");
		update();
		
		//DAL delete
		log("======== DELETE TEST =======");
		delete();
		log("******   TEST DONE!!!   ******");
	}
	
	private void delete() throws DBConnectionException, 
				DatabaseException, SQLException, DeleteException{
		for (int i=0; i<JOB_COUNT; i++) {
			Job job = new Job();
			job.setJobId(lastCount-i);
			dao.delete(job);
		}
	}

	private void assertJob(Job expected, Job actual) {
		Assert.assertNotNull(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getJobId(), actual.getJobId());
		Assert.assertEquals(expected.getReqFileGroupId(), actual.getReqFileGroupId());
		Assert.assertEquals(expected.getResFileGroupId(), actual.getResFileGroupId());
		Assert.assertEquals(expected.getStatus(), actual.getStatus());
	}
	
	private void logJob(String msg, Job job) {
		log(msg);
		log(job.toString());
	}
	
	@Override
	protected void cleanup() {
		dao.cleanup();
	}

	@Override
	protected BaseDao getDao() {
		if(dao == null) {
			FpsDaoFactory factory = FpsDaoFactory.getInstance();
			dao = factory.getJobDao();
		}
		
		return dao;
	}
}