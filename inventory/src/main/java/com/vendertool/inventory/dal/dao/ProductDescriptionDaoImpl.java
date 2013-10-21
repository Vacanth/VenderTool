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
import com.vendertool.inventory.dal.dao.codegen.QBeanProductDescription;
import com.vendertool.inventory.dal.dao.codegen.QBeanProductVariation;
import com.vendertool.inventory.dal.dao.codegen.QProductDescription;
import com.vendertool.sharedtypes.core.Product;

public class ProductDescriptionDaoImpl extends BaseDaoImpl implements ProductDescriptionDao {
	private static final Logger logger = Logger.getLogger(ProductDescriptionDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
			
	@Override
	public void insert(Product productDescription,Long productId) throws DBConnectionException,
			InsertException, DatabaseException {
		
		if(VUTIL.isNull(productDescription)) {
			InsertException ie = new InsertException("Cannot insert null productDescription");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QProductDescription a = QProductDescription.productDescription;
			ProductDescriptionMapper productDescriptionMapper = new ProductDescriptionMapper(a.all());
			QBeanProductDescription qBeanProductDescription = productDescriptionMapper.populateBean(productDescription);
			qBeanProductDescription.setProductId(productId);
			qBeanProductDescription.setProductDescriptionId(generateNextSequence(con));
			SQLInsertClause s = insert(con, a).populate(qBeanProductDescription);
	    	
    	logger.info("DAL QUERY: " + s.toString());
    	
    	try {
    		s.execute();
    	} catch (Exception e) {
    		InsertException ie = new InsertException(e);
			logger.debug(ie.getMessage(), ie);
			throw ie;
    	}
    	
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
		return "nextvalForProductDescription()";
	}

	
	@Override
	public void delete(long productId) throws DBConnectionException,
			DeleteException {
		Connection con = null;
		
		try {
			con = getConnection();
			QProductDescription a = QProductDescription.productDescription;
			SQLDeleteClause s = delete(con, a)
				.where(a.productId.eq(productId));
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
	public void update(Product productDescription, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException {
		if(VUTIL.isNull(productDescription) || VUTIL.isNull(updateSet)){
			UpdateException ue = new UpdateException("Cannot update null productDescription");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		
		Connection con = null;
		
		try {
			con = getConnection();
			QProductDescription a = QProductDescription.productDescription;
	    	SQLUpdateClause s = update(con, a)
					.populate(productDescription, new ProductDescriptionMapper(updateSet));

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
	public List<Product> findByProductId(Long productId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
	Connection con = null;
		
		try { 
			con = getConnection();
			QProductDescription a = QProductDescription.productDescription;
			SQLQuery query = from(con, a)
					.where(a.productId.eq(productId.longValue()));

	    	logger.info("DAL QUERY: " + query.toString());
	    	
	    	
	    	List<Tuple> rows = query.list(readSet);
	    	
	    	if((rows == null) || (rows.isEmpty())) {
	    		return null;
	    	}
	    	
			List<Product> productDescription = (List<Product>) new ProductDescriptionMapper(readSet).convert(rows.get(0), readSet);
			if(productDescription == null) {
				FinderException fe = new FinderException("Cannot find productDescription for given productId : "+productId.longValue());
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}
			
			return productDescription;
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

	
	@Override
	public Product findByProductIdAndAccountId(Long productId, Long accountId,
			Path<?>[] readSet) throws DBConnectionException, FinderException,
			DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}
}
