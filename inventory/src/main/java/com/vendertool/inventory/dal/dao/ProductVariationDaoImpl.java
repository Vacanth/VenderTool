package com.vendertool.inventory.dal.dao;


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
import com.vendertool.common.dal.dao.BaseDaoImpl;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.inventory.dal.dao.codegen.QProductVariation;
import com.vendertool.sharedtypes.core.ProductVariation;

public class ProductVariationDaoImpl extends BaseDaoImpl implements ProductVariationDao {
	private static final Logger logger = Logger.getLogger(ProductVariationDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
			
	@Override
	public long insert(ProductVariation productVariation,Long productId) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(productVariation)) {
			InsertException ie = new InsertException("Cannot insert null productVariation");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QProductVariation a = QProductVariation.productVariation;
			Long seq = generateNextSequence(con);
			if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
				
				if(VUTIL.isNull(seq) || (seq.longValue() <= 0)) {
		    		InsertException ie = new InsertException("Unable to generate valid sequence");
					logger.debug(ie.getMessage(), ie);
					throw ie;
				}
				productVariation.setProductVariationId(seq);
			}
			
	    	SQLInsertClause s = insert(con, a)
    				.populate(new ProductVariationMapper(a.all()).populateBean(productVariation));
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
		return true;
	}

	@Override
	public String getSequenceProcedureName() {
		// TODO Auto-generated method stub
		return "nextvalForProductVariation()";
	}

	@Override
	public void delete(ProductVariation productVariation) throws DBConnectionException,
			DeleteException, DatabaseException {
		delete(productVariation.getProductVariationId());
		
	}
	@Override
	public void delete(long productVariationId) throws DBConnectionException,
			DeleteException {
		Connection con = null;
		
		try {
			con = getConnection();
			QProductVariation a = QProductVariation.productVariation;
			SQLDeleteClause s = delete(con, a)
				.where(a.productVariationId.eq(productVariationId));
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
	public void update(ProductVariation productVariation, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException {
		if(VUTIL.isNull(productVariation) || VUTIL.isNull(updateSet)){
			UpdateException ue = new UpdateException("Cannot update null productVariation");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QProductVariation a = QProductVariation.productVariation;
	    	SQLUpdateClause s = update(con, a)
					.populate(productVariation, new ProductVariationMapper(updateSet));

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
	public void updateHostedUrl(Long productId, Long productVariationId,String hostedUrl)
			throws DBConnectionException, UpdateException, DatabaseException {
		if(VUTIL.isNull(productId) || VUTIL.isNull(productVariationId) || VUTIL.isNull(hostedUrl)){
			UpdateException ue = new UpdateException("Cannot update null productVariation");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QProductVariation a = QProductVariation.productVariation;
			SQLUpdateClause s = update(con, a)
					.set(a.url, hostedUrl)
					.where(a.productId.eq(productId),a.productVariationId.eq(productVariationId));

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
	public List<ProductVariation> findByProductId(Long productId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	Connection con = null;
		
		try { 
			con = getConnection();
			QProductVariation a = QProductVariation.productVariation;
			SQLQuery query = from(con, a)
					.where(a.productId.eq(productId.longValue()));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			List<ProductVariation> productVariation = (List<ProductVariation>) new ProductVariationMapper(readSet).convert(rows.get(0), readSet);
			if(productVariation == null) {
				FinderException fe = new FinderException("Cannot find productVariation for given productId : "+productId.longValue());
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return productVariation;
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public ProductVariation findByProductIdAndProductVariationId(Long productId, Long productVariationId,
			Path<?>[] readSet) throws DBConnectionException, FinderException,
			DatabaseException {
	Connection con = null;
		
		try { 
			con = getConnection();
			QProductVariation a = QProductVariation.productVariation;
			SQLQuery query = from(con, a)
					.where(a.productId.eq(productId.longValue()) , a.productVariationId.eq(productVariationId.longValue()));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			ProductVariation productVariation =  new ProductVariationMapper(readSet).convert(rows.get(0), readSet);
			if(productVariation == null) {
				FinderException fe = new FinderException("Cannot find productVariation for given product  "+productId.longValue()+" and variation  : " +productId.longValue());
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return productVariation;
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
