package com.terry.costnote.data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.costnote.data.dao.intf.ICostDao;
import com.terry.costnote.data.model.Cost;
import com.terry.costnote.data.util.EMF;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:21:27 AM
 */

@Component("costDao")
public class CostDaolmpl implements ICostDao {

	EntityManager em = EMF.get().createEntityManager();

	@Override
	public boolean deleteCost(Cost cost) {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(cost);
			tx.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getCostsByEmail(String email, int start, int limit) {
		Query query = em.createQuery("SELECT c FROM " + Cost.class.getName()
				+ " c where c.email = :email");
		query.setFirstResult(start);
		if (limit != 0)
			query.setMaxResults(limit);
		query.setParameter("email", email);
		return query.getResultList();
	}

	@Override
	public boolean saveCost(Cost cost) {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(cost);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Cost getCostById(String costId) {
		Key key = KeyFactory.stringToKey(costId);
		if (key == null || !key.isComplete())
			return null;

		return em.find(Cost.class, key);
	}

	@Override
	public long getCostsCountByEmail(String email) {
		Query query = em.createQuery("SELECT c FROM " + Cost.class.getName()
				+ " c where c.email = ?1");
		query.setParameter(1, email);
		query.setHint("datanucleus.query.resultSizeMethod", "count");
		return query.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getCosts(int start, int limit) {
		Query query = em.createQuery("SELECT c FROM " + Cost.class.getName()
				+ " c");
		query.setFirstResult(start);
		if (limit != 0)
			query.setMaxResults(limit);
		List<Cost> costs = query.getResultList();
		return costs;
	}
}
