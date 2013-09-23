package com.vendertool.inventory.dal;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.dao.AddressDaoFactory;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.dal.fieldset.FieldSets;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.inventory.dal.dao.ProductDao;
import com.vendertool.inventory.dal.dao.ProductDaoFactory;
import com.vendertool.inventory.dal.dao.ProductVariationDao;
import com.vendertool.inventory.dal.dao.ProductVariationDaoFactory;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.core.ProductVariation;

public class InventoryDALService {
	private static final Logger logger = Logger.getLogger(InventoryDALService.class);
	ValidationUtil VUTIL = ValidationUtil.getInstance();
	ProductDao productDao;
	ProductVariationDao productVariationDao;
	
	
	private InventoryDALService() {
		productDao = ProductDaoFactory.getInstance().getProductDao();
		productVariationDao = ProductVariationDaoFactory.getInstance().getProductVariationDao();

	}
	
	private static class InventoryDALServiceHolder {
		private static final InventoryDALService INSTANCE = new InventoryDALService();
	}
	
	public static InventoryDALService getInstance() {
		return InventoryDALServiceHolder.INSTANCE;
	}
	
	
	public Long createProduct(Product product) throws DBConnectionException,
			FinderException, InsertException, DatabaseException, UpdateException {
		
		if(VUTIL.isNull(product)) {
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
	
}
