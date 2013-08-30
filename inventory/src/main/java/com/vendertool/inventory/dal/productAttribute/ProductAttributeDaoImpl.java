/**
 * 
 */
package com.vendertool.inventory.dal.productAttribute;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendertool.common.dal.BaseDAO;

/**
 * @author murali HibernateDaoSupport
 */

public class ProductAttributeDaoImpl extends BaseDAO implements ProductAttributeDao {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#save(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void insert(ProductAttribute productAttribute) {
		// TODO Auto-generated method stub
		Session session = getDalSession();
		Transaction trans = session.beginTransaction();
		session.save(productAttribute);
		trans.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#update(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void update(ProductAttribute productAttribute) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(productAttribute);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#delete(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void delete(ProductAttribute productAttribute) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(productAttribute);

	}

	/*public List<ProductAttribute> findByProductAttributeId(long productAttributeId) {
		// TODO Auto-generated method stub
		String sql = "select * from product_attribute where product_attribute_id = :productAttributeId";
		SQLQuery query=getSession().createSQLQuery(sql);
		query.setParameter("productAttributeId", productAttributeId);
		query.addEntity(ProductAttribute.class);
		List<ProductAttribute> results = query.list();
		return  results;
	}*/

	
}