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
import com.vendertool.inventory.dal.dao.codegen.QProduct;
import com.vendertool.sharedtypes.core.Product;

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
	private static final Logger logger = Logger.getLogger(ProductDaoImpl.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	@Override
	public long insert(Product product) throws DBConnectionException,
			InsertException, DatabaseException {

		if (VUTIL.isNull(product)) {
			InsertException ie = new InsertException(
					"Cannot insert null product");
			logger.debug(ie.getMessage(), ie);
			throw ie;
		}

		Connection con = null;

		try {
			con = getConnection();
			QProduct a = QProduct.product;
			Long productId = product.getProductId();
			if (VUTIL.isNull(productId) || (productId.longValue() <= 0)) {
				productId = generateNextSequence(con);
				if (VUTIL.isNull(productId) || (productId.longValue() <= 0)) {
					InsertException ie = new InsertException(
							"Unable to generate valid sequence");
					logger.debug(ie.getMessage(), ie);
					throw ie;
				}
				product.setProductId(productId);
			}

			SQLInsertClause s = insert(con, a).populate(
					new ProductMapper(a.all()).populateBean(product));
			logger.info("DAL QUERY: " + s.toString());

			try {
				s.execute();
			} catch (Exception e) {
				InsertException ie = new InsertException(e);
				logger.debug(ie.getMessage(), ie);
				throw ie;
			}
			return productId;
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
		return "nextvalForProduct()";
	}

	@Override
	public void delete(Product product) throws DBConnectionException,
			DeleteException, DatabaseException {
		delete(product.getProductId());

	}

	@Override
	public void delete(long productId) throws DBConnectionException,
			DeleteException {
		Connection con = null;

		try {
			con = getConnection();
			QProduct a = QProduct.product;
			SQLDeleteClause s = delete(con, a).where(a.productId.eq(productId));
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
	public void update(Product product, Path<?>[] updateSet)
			throws DBConnectionException, UpdateException, DatabaseException {
		if (VUTIL.isNull(product) || VUTIL.isNull(updateSet)) {
			UpdateException ue = new UpdateException(
					"Cannot update null product");
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}

		Connection con = null;

		try {
			con = getConnection();
			QProduct a = QProduct.product;
			SQLUpdateClause s = update(con, a).populate(product,
					new ProductMapper(updateSet));

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
	public List<Product> findByAccountId(Long accountId, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		Connection con = null;

		try {
			con = getConnection();
			QProduct a = QProduct.product;
			SQLQuery query = from(con, a).where(
					a.accountId.eq(accountId.longValue()));

			logger.info("DAL QUERY: " + query.toString());

			List<Tuple> rows = query.list(readSet);

			if ((rows == null) || (rows.isEmpty())) {
				return null;
			}

			List<Product> product = (List<Product>) new ProductMapper(readSet)
					.convert(rows.get(0), readSet);
			if (product == null) {
				FinderException fe = new FinderException(
						"Cannot find product for given account : "
								+ accountId.longValue());
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}

			return product;
		} finally {
			closeConnection(con);
		}
	}

	@Override
	public Product findByAccountIdAndProductId(Long accountId, Long productId,
			Path<?>[] readSet) throws DBConnectionException, FinderException,
			DatabaseException {
		Connection con = null;

		try {
			con = getConnection();
			QProduct a = QProduct.product;
			SQLQuery query = from(con, a).where(a.accountId.eq(accountId),
					a.productId.eq(productId));

			logger.info("DAL QUERY: " + query.toString());

			List<Tuple> rows = query.list(readSet);

			if ((rows == null) || (rows.isEmpty())) {
				return null;
			}

			Product product = new ProductMapper(readSet).convert(rows.get(0),
					readSet);
			if (product == null) {
				FinderException fe = new FinderException(
						"Cannot find product for given account : "
								+ accountId.longValue());
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}

			return product;
		} finally {
			closeConnection(con);
		}
	}

	private void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			logger.debug(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product findBySKU(Long accountId, String sku, Path<?>[] readSet)
			throws DBConnectionException, FinderException, DatabaseException {
		Connection con = null;

		try {
			con = getConnection();
			QProduct a = QProduct.product;
			SQLQuery query = from(con, a).where(a.accountId.eq(accountId),
					a.sku.eq(sku));

			logger.info("DAL QUERY: " + query.toString());

			List<Tuple> rows = query.list(readSet);

			if ((rows == null) || (rows.isEmpty())) {
				return null;
			}

			Product product = (Product) new ProductMapper(readSet).convert(
					rows.get(0), readSet);
			if (product == null) {
				FinderException fe = new FinderException(
						"Cannot find product for given sku : " + sku);
				logger.debug(fe.getMessage(), fe);
				throw fe;
			}

			return product;
		} finally {
			closeConnection(con);
		}
	}
}
