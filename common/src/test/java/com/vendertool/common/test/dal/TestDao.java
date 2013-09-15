package com.vendertool.common.test.dal;

import java.sql.Connection;

import javax.sql.DataSource;

import com.vendertool.common.dal.dao.BaseDao;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;

public class TestDao implements BaseDao {

	@Override
	public DataSource getDataSource() {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() throws DBConnectionException {
		// XXX Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSequenceGenerator() {
		return false;
	}

	@Override
	public Long generateNextSequence(Connection connection)
			throws DBConnectionException, DatabaseException {
		return null;
	}

	@Override
	public Long generateNextSequence() throws DBConnectionException,
			DatabaseException {
		return null;
	}

	@Override
	public String getSequenceProcedureName() {
		return null;
	}

	@Override
	public void cleanup() {
		// XXX Auto-generated method stub

	}

}
