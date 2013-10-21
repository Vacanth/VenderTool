package com.vendertool.inventory.dal;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.inventory.dal.dao.ProductDao;
import com.vendertool.inventory.dal.dao.ProductDaoFactory;
import com.vendertool.inventory.dal.dao.ProductDescriptionDao;
import com.vendertool.inventory.dal.dao.ProductDescriptionDaoFactory;
import com.vendertool.inventory.dal.dao.ProductVariationDao;
import com.vendertool.inventory.dal.dao.ProductVariationDaoFactory;
import com.vendertool.inventory.dal.fieldset.ProductReadSet;
import com.vendertool.sharedtypes.core.Product;

public class InventoryDALService {
	private static final Logger logger = Logger
			.getLogger(InventoryDALService.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
	ProductDao productDao;
	ProductDescriptionDao productDescriptionDao;
	ProductVariationDao productVariationDao;

	private InventoryDALService() {
		productDao = ProductDaoFactory.getInstance().getProductDao();
		productDescriptionDao = ProductDescriptionDaoFactory.getInstance().getProductDescriptionDao();
		productVariationDao = ProductVariationDaoFactory.getInstance()
				.getProductVariationDao();

	}

	private static class InventoryDALServiceHolder {
		private static final InventoryDALService INSTANCE = new InventoryDALService();
	}

	public static InventoryDALService getInstance() {
		return InventoryDALServiceHolder.INSTANCE;
	}

	public Long createProduct(Product product) throws DBConnectionException,
			FinderException, InsertException, DatabaseException,
			UpdateException {

		if (VUTIL.isNull(product)) {
			return null;
		}

		Long productId = productDao.generateNextSequence();
		product.setProductId(productId);

		try {
			productDao.insert(product);
			} catch (InsertException e) {
			UpdateException ue = new UpdateException(
					"Unable to add new productId : "
							+ product.getProductId(), e);
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}
		//set description
		//Description ID is not being set so it is failing.
		/*try {
			productDescriptionDao.insert(product,productId);
		} catch (InsertException e) {
			UpdateException ue = new UpdateException(
					"Unable to add new description for productId : "
							+ product.getProductId(), e);
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}*/

		return productId;
	}

	public void removeListing(Long productId) throws DeleteException,
			DBConnectionException, DatabaseException, FinderException {
		productDao.delete(productId);
		productDescriptionDao.delete(productId);
	}

	public Product findBySKU(Long accountId, String sku) {
		Product product = null;
		try {
			product = productDao.findBySKU(accountId, sku,
					ProductReadSet.getInstance().ALL);
		} catch (DBConnectionException e) {

		} catch (FinderException e) {

		} catch (DatabaseException e) {

		}
		return product;
	}

	public Product findByProductId(Long accountId, Long productId) {
		Product product = null;
		try {
			product = productDao.findByAccountIdAndProductId(accountId,
					productId, ProductReadSet.getInstance().ALL);
		} catch (DBConnectionException e) {

		} catch (FinderException e) {

		} catch (DatabaseException e) {

		}
		return product;
	}
}