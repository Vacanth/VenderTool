package com.vendertool.registration.test.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.test.dal.BaseDaoTest;
import com.vendertool.registration.dal.dao.AccountSecurityQuestionDao;
import com.vendertool.registration.dal.dao.RegistrationDaoFactory;
import com.vendertool.registration.dal.dao.codegen.QAccountSecurityQuestion;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.core.SecurityQuestion;
import com.vendertool.sharedtypes.core.SecurityQuestionCodeEnum;

public class AccountSecurityQuestionDaoTest extends BaseDaoTest{
	private static final int QUESTIONS_COUNT = 10;
	private static final long ACCOUNT_ID = 600;
	
	List<AccountSecurityQuestion> questions;
	AccountSecurityQuestionDao dao;
	QAccountSecurityQuestion as;

	protected AccountSecurityQuestionDaoTest() {
		super();
	}
	
	public static void main(String[] args) throws DBConnectionException,
			InsertException, DatabaseException, SQLException, DeleteException,
			FinderException, UpdateException {
		
		AccountSecurityQuestionDaoTest test = new AccountSecurityQuestionDaoTest();
		test.testCRUD();
		test.cleanup();
	}
	
	private void testCRUD() throws DBConnectionException, InsertException,
			DatabaseException, FinderException, DeleteException {
		//DAL insert
		log("======== Insert Test =======");
		dao.insert(ACCOUNT_ID, questions);
		
		log("======== Finder Test =======");
		List<AccountSecurityQuestion> dbQuestions = 
				dao.findAllByAccountId(ACCOUNT_ID);
		Assert.assertNotNull(dbQuestions);
		log("DB AccountSecurityQuestion:\n" + dbQuestions.toString());
		
		log("======== Delete Test =======");
		dao.deleteByAccountId(ACCOUNT_ID);
		
		log("======== Finder Test =======");
		dbQuestions = dao.findAllByAccountId(ACCOUNT_ID);
		Assert.assertNull(dbQuestions);
		log("DB AccountSecurityQuestion(s) not found");
	}

	private AccountSecurityQuestion createAccountSecurityQuestion(int idx) {
		AccountSecurityQuestion q = new AccountSecurityQuestion();
		q.setId(idx + 0L);
		SecurityQuestion sq = new SecurityQuestion();
		sq.setQuestionCode(SecurityQuestionCodeEnum.getByIndex(idx % 10));
		q.setQuestion(sq);
		q.setAnswer("MyAnswer"+idx);
		q.setCreatedDate(new Date());
		
		return q;
	}

	@Override
	protected void setupTestData() {
		as = QAccountSecurityQuestion.accountSecurityQuestion;
		dao = (AccountSecurityQuestionDao) getDao();
		
		questions = new ArrayList<AccountSecurityQuestion>();
		for(int idx = 0; idx < QUESTIONS_COUNT; idx++) {
			AccountSecurityQuestion q = createAccountSecurityQuestion(idx);
			if(!questions.contains(q)) {
				questions.add(q);
			} else {
				log("setupTestData: security question already in the list: " + q);
			}
		}
	}

	@Override
	protected void cleanup() {
		// XXX Auto-generated method stub
		
	}

	@Override
	protected BaseDao getDao() {
		if(dao == null) {
			RegistrationDaoFactory factory = RegistrationDaoFactory.getInstance();
			dao = factory.getAccountSecurityQuestionDao();
		}
		
		return dao;
	}
	
	@Override
	public String getApplicationContextFileName() {
		return "test-app-context.xml";
	}
}