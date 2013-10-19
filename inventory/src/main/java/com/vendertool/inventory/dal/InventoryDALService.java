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
import com.vendertool.inventory.dal.dao.ProductVariationDao;
import com.vendertool.inventory.dal.dao.ProductVariationDaoFactory;
import com.vendertool.inventory.dal.fieldset.ProductReadSet;
import com.vendertool.sharedtypes.core.Product;

public class InventoryDALService {
	private static final Logger logger = Logger
			.getLogger(InventoryDALService.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
	ProductDao productDao;
	ProductVariationDao productVariationDao;

	private InventoryDALService() {
		productDao = ProductDaoFactory.getInstance().getProductDao();
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
					"Unable to update account profile for "
							+ product.getProductId(), e);
			logger.debug(ue.getMessage(), ue);
			throw ue;
		}

		return productId;
	}

	public void removeListing(Long productId) throws DeleteException,
			DBConnectionException, DatabaseException, FinderException {
		productDao.delete(productId);
	}

	public Product findBySKU(String sku) {
		Product product = null;
		try {
			product = productDao.findBySKU(sku,
					ProductReadSet.getInstance().ALL);
		} catch (DBConnectionException e) {
			
		} catch (FinderException e) {
			
		} catch (DatabaseException e) {
			
		}
		return product;
	}
}