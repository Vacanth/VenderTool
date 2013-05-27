/**
 * 
 */
package com.dal.dao;
import java.util.List;

import org.hibernate.SQLQuery;


import com.vendertool.common.dal.BaseDaoImpl;

/**
 * @author murali
 *HibernateDaoSupport
 */

public class BatchWorkLogDaoImpl extends BaseDaoImpl implements BatchWorkLogDao {

	/* (non-Javadoc)
	 * @see com.vendertool.inventory.DBL.BO.MerchantProductDao#save(com.vendertool.inventory.DBL.BO.MerchantProduct)
	 */
	public void insert (BatchWorkLog batchWorkLog) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(batchWorkLog);
		
		
	}

	/* (non-Javadoc)
	 * @see com.vendertool.inventory.DBL.BO.MerchantProductDao#update(com.vendertool.inventory.DBL.BO.MerchantProduct)
	 */
	public void update(BatchWorkLog batchWorkLog) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(batchWorkLog);

	}

	/* (non-Javadoc)
	 * @see com.vendertool.inventory.DBL.BO.MerchantProductDao#delete(com.vendertool.inventory.DBL.BO.MerchantProduct)
	 */
	public void delete(BatchWorkLog batchWorkLog) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(batchWorkLog);

	}

	/* (non-Javadoc)
	 * @see com.vendertool.inventory.DBL.BO.MerchantProductDao#findByStockCode(com.vendertool.inventory.DBL.BO.MerchantProduct)
	 */
	public List<BatchWorkLog> findByBatchJobId(long batchJobId) {
		// TODO Auto-generated method stub
		String sql = "select * from batch_work_log where batch_id = :batch_job_id";
		SQLQuery query=getSession().createSQLQuery(sql);
		query.setParameter("batch_job_id", batchJobId);
		query.addEntity(BatchWorkLog.class);
		List<BatchWorkLog> results = query.list();
		return  results;
	}

	
	/* (non-Javadoc)
	 * @see com.vendertool.inventory.DBL.BO.MerchantProductDao#findByStockCode(com.vendertool.inventory.DBL.BO.MerchantProduct)
	 */
	public List<BatchWorkLog> findByBatchWorkLogId(long batchWorkLogId) {
		// TODO Auto-generated method stub
		String sql = "select * from batch_work_log where batch_work_log_id = :batch_work_log_id";
		SQLQuery query=getSession().createSQLQuery(sql);
		query.setParameter("batch_work_log_id", batchWorkLogId);
		query.addEntity(BatchWorkLog.class);
		List<BatchWorkLog> results = query.list();
		return  results;
	}
	
}