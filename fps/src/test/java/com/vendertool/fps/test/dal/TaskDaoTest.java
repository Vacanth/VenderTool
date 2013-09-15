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
import com.vendertool.fps.dal.dao.TaskDao;
import com.vendertool.fps.dal.dao.codegen.QTask;
import com.vendertool.fps.dal.fieldset.TaskReadSet;
import com.vendertool.fps.dal.fieldset.TaskUpdateSet;
import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.core.fps.Task;

public class TaskDaoTest extends BaseDaoTest{
	private static final int TASK_COUNT = 100;
	Task[] tasks;
	TaskDao dao;
	QTask qa;
	long lastCount = 0;
	
	protected TaskDaoTest() {
		super();
	}
	
	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, DeleteException, FinderException, UpdateException {
		TaskDaoTest test = new TaskDaoTest();
		test.testCRUD();
		test.cleanup();
	}
	
	@Override
	protected void setupTestData() {
		qa = QTask.task;
		dao = (TaskDao) getDao();
		
		tasks = new Task[TASK_COUNT];
		for(int idx = 0; idx < TASK_COUNT; idx++) {
			tasks[idx] = createTask(idx);
		}
	}
	

	private Task createTask(int idx) {
		Date curDate = new Date();
		Task task = new Task();	
		
		task.setJobId(200000L+idx);
		task.setRequestGroupId("request "+idx);
		task.setRequest(new String("String test").getBytes());
		task.setResponse(new String("String test").getBytes());
		task.setRecordId(1000L);
		task.setRequestFileId(1000L);
		task.setRequestGroupId("string request Id");
		task.setAccountId(100000L);
		task.setCreatedDate(curDate);
		task.setLastModifiedDate(curDate);
		task.setStatus(FPSTaskStatusEnum.CREATED);
		task.setIsoCountryCode("USA");
		
		return task;
	}
	
	private void insert() throws DBConnectionException, InsertException,
			DatabaseException {
		for(Task task : tasks) {
			lastCount = dao.insert(task);
		}
	}
	
	public void update() throws DBConnectionException,UpdateException,DatabaseException,FinderException {
		for (int i=0; i<TASK_COUNT; i++) {
			Task task = dao.findByTaskId(lastCount-i, TaskReadSet.getInstance().FULL);
			task.setRequestGroupId("new group Id"+ i);
			dao.update(task,TaskUpdateSet.getInstance().FULL);
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
		for(Task task : tasks) {
			Task t = dao.findByTaskId(task.getTaskId(), TaskReadSet.getInstance().FULL);
			logTask("find by FileId ****\n", t);
			assertTask(task, t);
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
		for (int i=0; i<TASK_COUNT; i++) {
			Task task = new Task();
			task.setTaskId(lastCount-i);
			dao.delete(task);
		}
	}

	private void assertTask(Task expected, Task actual) {
		Assert.assertNotNull(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getTaskId(), actual.getTaskId());
		Assert.assertEquals(expected.getRequestGroupId(), actual.getRequestGroupId());
		Assert.assertEquals(expected.getRequestFileId(), actual.getRequestFileId());
		Assert.assertEquals(expected.getStatus(), actual.getStatus());
	}
	
	private void logTask(String msg, Task task) {
		log(msg);
		log(task.toString());
	}
	
	@Override
	protected void cleanup() {
		dao.cleanup();
	}

	@Override
	protected BaseDao getDao() {
		if(dao == null) {
			FpsDaoFactory factory = FpsDaoFactory.getInstance();
			dao = factory.getTaskDao();
		}
		
		return dao;
	}
}