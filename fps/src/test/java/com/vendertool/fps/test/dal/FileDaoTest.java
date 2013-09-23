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
import com.vendertool.fps.dal.dao.FileDao;
import com.vendertool.fps.dal.dao.FpsDaoFactory;
import com.vendertool.fps.dal.dao.codegen.QFile;
import com.vendertool.fps.dal.fieldset.FileReadSet;
import com.vendertool.fps.dal.fieldset.FileUpdateSet;
import com.vendertool.sharedtypes.core.fps.FPSFileStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSStorageSourceEnum;
import com.vendertool.sharedtypes.core.fps.FPSUsecaseEnum;
import com.vendertool.sharedtypes.core.fps.File;

public class FileDaoTest extends BaseDaoTest{
	private static final int FILE_COUNT = 10;
	File[] files;
	FileDao dao;
	QFile qa;
	long lastCount = 0;

	protected FileDaoTest() {
		super();
	}
	
	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, DeleteException, FinderException, UpdateException {
		FileDaoTest test = new FileDaoTest();
		test.testCRUD();
		test.cleanup();
	}
	
	@Override
	protected void setupTestData() {
		qa = QFile.file;
		dao = (FileDao) getDao();
		
		files = new File[FILE_COUNT];
		for(int idx = 0; idx < FILE_COUNT; idx++) {
			files[idx] = createFile(idx);
		}
	}
	

	private File createFile(int idx) {
		Date curDate = new Date();
	
		File file = new File();
		file.setFileGroupId("200000"+idx);
		file.setAccountId(Long.valueOf(100000));
		file.setFileName("FileName");
		file.setCreatedDate(curDate);
		file.setLastModifiedDate(curDate);
		file.setStatus(FPSFileStatusEnum.CREATED);
		file.setUseCase(FPSUsecaseEnum.ADD_LISTING);
		file.setStorageSource(new Byte(FPSStorageSourceEnum.AWS_CLOUD.getId()+""));
		file.setRefUrl("testing url " + idx);
		file.setFilesCountInGroup(new Byte(15+""));
		
		return file;
	}
	
	private void insert() throws DBConnectionException, InsertException,
			DatabaseException {
		for(File file : files) {
			lastCount = dao.insert(file);
		}
	}
	
	public void update() throws DBConnectionException,UpdateException,DatabaseException,FinderException {
		for (int i=0; i<FILE_COUNT; i++) {
			File file = dao.findByFileId(lastCount-i, FileReadSet.getInstance().FULL);
			System.out.println(file.getLastModifiedDate() + ","+file.getStatus() + ","+
							file.getRefUrl() +","+ file.getAccountId()+ "," +
							file.getStorageSource()+ ","+ file.getFileGroupId()+","+
							file.getUseCase() +","+file.getFilesCountInGroup());
			file.setFileGroupId("new group Id"+ i);
			dao.update(file,FileUpdateSet.getInstance().FULL);
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
		for(File file : files) {
			File f = dao.findByFileId(file.getFileId(), FileReadSet.getInstance().FULL);
			logFile("find by FileId ****\n", f);
			assertFile(file, f);
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
		for (int i=0; i<FILE_COUNT; i++) {
			File file = new File();
			file.setFileId(lastCount-i);
			dao.delete(file);
		}
	}

	private void assertFile(File expected, File actual) {
		Assert.assertNotNull(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getFileId(), actual.getFileId());
		Assert.assertEquals(expected.getRefUrl(), actual.getRefUrl());
	}
	
	private void logFile(String msg, File file) {
		log(msg);
		log(file.toString());
	}
	
	@Override
	protected void cleanup() {
		dao.cleanup();
	}

	@Override
	protected BaseDao getDao() {
		if(dao == null) {
			FpsDaoFactory factory = FpsDaoFactory.getInstance();
			dao = factory.getFileDao();
		}
		
		return dao;
	}
}