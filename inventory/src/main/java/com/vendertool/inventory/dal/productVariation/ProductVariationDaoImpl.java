/**
 * 
 */
package com.vendertool.inventory.dal.productVariation;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendertool.common.dal.BaseDAO;

/**
 * @author murali HibernateDaoSupport
 */

public class ProductVariationDaoImpl extends BaseDAO implements ProductVariationDao {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#save(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void insert(ProductVariation productVariation) {
		// TODO Auto-generated method stub
		Session session = getDalSession();
		Transaction trans = session.beginTransaction();
		session.save(productVariation);
		trans.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#update(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void update(ProductVariation productVariation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(productVariation);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#delete(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void delete(ProductVariation productVariation) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(productVariation);

	}

	public List<ProductVariation> findByProductId(long productId) {
		// TODO Auto-generated method stub
		String sql = "select * from product_variation where product_id = :product_id";
		SQLQuery query=getSession().createSQLQuery(sql);
		query.setParameter("product_id", productId);
		query.addEntity(ProductVariation.class);
		List<ProductVariation> results = query.list();
		return  results;
	}

	public List<ProductVariation> findByProductVariationId(long productVariationId) {
		// TODO Auto-generated method stub
		String sql = "select * from product_variation where product_variations_id = :product_variations_id";
		SQLQuery query=getSession().createSQLQuery(sql);
		query.setParameter("product_variations_id", productVariationId);
		query.addEntity(ProductVariation.class);
		List<ProductVariation> results = query.list();
		return  results;
	}

	public List<ProductVariation> findByProductIdAndVariationName(long productId,
			String variationName) {
		String sql = "select * from product_variation where product_id = :product_id and variation_name= :variation_name";
		SQLQuery query=getSession().createSQLQuery(sql);
		query.setParameter("product_id", productId);
		query.setParameter("variation_name", variationName);
		query.addEntity(ProductVariation.class);
		List<ProductVariation> results = query.list();
		return  results;
	}

	
}