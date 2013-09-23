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
import com.vendertool.fps.dal.dao.codegen.QFile;
import com.vendertool.sharedtypes.core.fps.File;

public class FileDaoImpl extends BaseDaoImpl implements FileDao {
	private static final Logger logger = Logger.getLogger(FileDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
		
	@Override
	public long insert(File file) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(file)) {
			InsertException ie = new InsertException("Cannot insert null File");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QFile f = QFile.file;
			Long seq = generateNextSequence(con);
			if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
	    		InsertException ie = new InsertException("Unable to generate valid sequence");
				logger.debug(ie.getMessage(), ie);
				throw ie;
			}
			file.setFileId(seq);

	    	SQLInsertClause s = insert(con, f)
	    				.populate(new FileMapper(f.all()).populateBean(file));
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
	public void update(File file, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException {
		
		if(VUTIL.isNull(file) || VUTIL.isNull(updateSet)){
			UpdateException ue = new UpdateException("Cannot update null file");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			
			QFile f = QFile.file;
	
	    	SQLUpdateClause s = update(con, f)
					.populate(file, new FileMapper(updateSet));

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
	
	//Need to optimize with batch insert, single connection
	public void insertFiles(List<File> files) throws DBConnectionException,
		InsertException, DatabaseException{
		
		for (File file : files) {
			insert(file);
		}
	}
	
	@Override
	public void delete(File file) 
			throws DBConnectionException, DeleteException, DatabaseException{
		delete(file.getFileId());
	}
		
	
	@Override
	public void delete(long fileId) throws DBConnectionException,
			DeleteException {
		Connection con = null;
		
		try {
			con = getConnection();
			
			QFile f = QFile.file;
			
			SQLDeleteClause s = delete(con, f)
				.where(f.fileId.eq(fileId));
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

	public File findByFileId(long fileId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QFile f = QFile.file;
			
			SQLQuery query = from(con, f)
					.where(f.fileId.eq(fileId));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			File file = new FileMapper(readSet).convert(rows.get(0), readSet);
			if(file == null) {
				FinderException fe = new FinderException("Cannot find file");
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return file;
		} finally {
			closeConnection(con);
		}
	}
	
	public List<File> findByGroupId(String groupId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QFile f = QFile.file;
			
			SQLQuery query = from(con, f)
					.where(f.fileGroupId.eq(groupId));

	    	logger.info("DAL QUERY: " + query.toString());	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
			return convertTupleToFile(rows, readSet);
			
		} finally {
			closeConnection(con);
		}
	}
	
	public List<File> findByAccountId(long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		
		Connection con = null;
		
		try { 
			con = getConnection();
			
			QFile f = QFile.file;
			
			SQLQuery query = from(con, f)
					.where(f.accountId.eq(accountId));

	    	logger.info("DAL QUERY: " + query.toString());	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
			return convertTupleToFile(rows, readSet);
			
		} finally {
			closeConnection(con);
		}
		
	}
	
	private List<File> convertTupleToFile(List<Tuple> rows, Path<?>[] readSet) 
		throws DatabaseException {

		if((rows == null) || (rows.isEmpty())) {
			return null;
		}
	
		List<File> fileList = new ArrayList<File>(rows.size());
		ListIterator<Tuple> iter = rows.listIterator();
		File file = null;
	
		while (iter.hasNext()) {
			file = new FileMapper(readSet).convert(iter.next(), readSet);
			if(file != null) {
				fileList.add(file);
			}
		}
		
		return fileList;
	}
	
	@Override
	public boolean hasSequenceGenerator() {
		return true;
	}

	@Override
	public String getSequenceProcedureName() {
		return "nextvalForFile()";
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
