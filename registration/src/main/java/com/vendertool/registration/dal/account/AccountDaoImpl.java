/**
 * 
 */
package com.vendertool.registration.dal.account;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendertool.common.dal.BaseDAO;

/**
 * @author murali HibernateDaoSupport
 */

public class AccountDaoImpl extends BaseDAO implements AccountDao {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#save(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void insert(Account account) {
		// TODO Auto-generated method stub
		Session session = getDalSession();
		Transaction trans = session.beginTransaction();
		session.save(account);
		trans.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#update(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void update(Account account) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(account);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#delete(com.vendertool
	 * .inventory.DBL.BO.MerchantProduct)
	 */
	public void delete(Account account) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(account);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vendertool.inventory.DBL.BO.MerchantProductDao#findByStockCode(com
	 * .vendertool.inventory.DBL.BO.MerchantProduct)
	 */
	public List<Account> findByAccountId(Account account) {
		// TODO Auto-generated method stub
		String sql = "select * from account where account_id = :account_id";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter("account_id", account.getAccountId());
		query.addEntity(Account.class);
		List<Account> results = query.list();
		return results;
	}
	
	public long getNextValue(){
		Session session = getDalSession();
		 SQLQuery query = session.createSQLQuery("VALUES NEXTVAL FOR <sequence_name>");
		 query.addEntity(Long.class);
		 List<Long> nextValue = query.list();
		 return nextValue.get(0);
	}
}