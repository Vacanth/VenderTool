package dal.dao;

// Generated May 10, 2013 11:09:54 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProductAttribute.
 * @see dal.dao.ProductAttribute
 * @author Hibernate Tools
 */
@Stateless
public class ProductAttributeHome {

	private static final Log log = LogFactory
			.getLog(ProductAttributeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ProductAttribute transientInstance) {
		log.debug("persisting ProductAttribute instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ProductAttribute persistentInstance) {
		log.debug("removing ProductAttribute instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ProductAttribute merge(ProductAttribute detachedInstance) {
		log.debug("merging ProductAttribute instance");
		try {
			ProductAttribute result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProductAttribute findById(int id) {
		log.debug("getting ProductAttribute instance with id: " + id);
		try {
			ProductAttribute instance = entityManager.find(
					ProductAttribute.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}