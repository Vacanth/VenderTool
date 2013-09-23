package com.vendertool.common.dal.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.codegen.QImage;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.sharedtypes.core.Image;

public class ImageDaoImpl extends BaseDaoImpl implements ImageDao {
	private static final Logger logger = Logger.getLogger(ImageDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
			
	@Override
	public long insert(Image image) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(image)) {
			InsertException ie = new InsertException("Cannot insert null image");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QImage a = QImage.image;
			Long seq = generateNextSequence(con);
			if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
				
				if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
		    		InsertException ie = new InsertException("Unable to generate valid sequence");
					logger.debug(ie.getMessage(), ie);
					throw ie;
				}
				image.setImageId(seq);
			}
	
	    	SQLInsertClause s = insert(con, a)
    				.populate(new ImageMapper(a.all()).populateBean(image));
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
	public boolean hasSequenceGenerator() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSequenceProcedureName() {
		// TODO Auto-generated method stub
		return "nextvalForImage()";
	}

	@Override
	public void delete(Image image) throws DBConnectionException,
			DeleteException, DatabaseException {
		delete(image.getImageId());
		
	}
	@Override
	public void delete(long imageId) throws DBConnectionException,
			DeleteException {
		Connection con = null;
		
		try {
			con = getConnection();
			QImage a = QImage.image;
			SQLDeleteClause s = delete(con, a)
				.where(a.imageId.eq(imageId));
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

	@Override
	public void update(Image image, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException {
		if(VUTIL.isNull(image) || VUTIL.isNull(updateSet)){
			UpdateException ue = new UpdateException("Cannot update null image");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QImage a = QImage.image;
	    	SQLUpdateClause s = update(con, a)
					.populate(image, new ImageMapper(updateSet));

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

	@Override
	public void updateHostedUrl(Long accountId, Long imageId,String hostedUrl)
			throws DBConnectionException, UpdateException, DatabaseException {
		if(VUTIL.isNull(accountId) || VUTIL.isNull(imageId) || VUTIL.isNull(hostedUrl)){
			UpdateException ue = new UpdateException("Cannot update null image");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QImage a = QImage.image;
			SQLUpdateClause s = update(con, a)
					.set(a.hostedUrl, hostedUrl)
					.where(a.accountId.eq(accountId),a.imageId.eq(imageId));

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

	@Override
	public List<Image> findByAccountId(Long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	Connection con = null;
		
		try { 
			con = getConnection();
			QImage a = QImage.image;
			SQLQuery query = from(con, a)
					.where(a.accountId.eq(accountId.longValue()));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			List<Image> image = (List<Image>) new ImageMapper(readSet).convert(rows.get(0), readSet);
			if(image == null) {
				FinderException fe = new FinderException("Cannot find image for given account : "+accountId.longValue());
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return image;
		} finally {
			closeConnection(con);
		}
	}
	
	@Override
	public List<Image> findByAccountIdRefIdAndRefType(Long accountId,Long refId, Byte refType,
			Path<?>[] readSet) throws DBConnectionException, FinderException,
			DatabaseException {
Connection con = null;
		
		try { 
			con = getConnection();
			QImage a = QImage.image;
			SQLQuery query = from(con, a)
					.where(a.accountId.eq(accountId.longValue()) , a.refId.eq(refId.longValue()) , a.refType.eq(refType));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			List<Image> image = (List<Image>) new ImageMapper(readSet).convert(rows.get(0), readSet);
			if(image == null) {
				FinderException fe = new FinderException("Cannot find image for given account : "+accountId.longValue());
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return image;
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public Image findByAccountIdAndImageId(Long accountId, Long imageId,
			Path<?>[] readSet) throws DBConnectionException, FinderException,
			DatabaseException {
	Connection con = null;
		
		try { 
			con = getConnection();
			QImage a = QImage.image;
			SQLQuery query = from(con, a)
					.where(a.accountId.eq(accountId.longValue()) , a.imageId.eq(imageId.longValue()));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			Image image =  new ImageMapper(readSet).convert(rows.get(0), readSet);
			if(image == null) {
				FinderException fe = new FinderException("Cannot find image for given account : "+accountId.longValue());
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return image;
		} finally {
			closeConnection(con);
		}
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
