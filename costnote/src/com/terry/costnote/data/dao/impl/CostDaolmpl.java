package com.terry.costnote.data.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.costnote.data.dao.intf.ICostDao;
import com.terry.costnote.data.model.Cost;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:21:27 AM
 */

@Component("costDao")
public class CostDaolmpl implements ICostDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean deleteCost(Cost cost) {
		try {
			em.remove(cost);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getCostsByEmail(String email, Date sfrom, Date sto,
			int stype, int start, int limit) {
		StringBuffer sb = new StringBuffer("SELECT c FROM ");
		sb.append(Cost.class.getName());
		sb
				.append(" c where c.email = :email and c.adate>=:sfrom and c.adate<:sto");
		if (stype != 0)
			sb.append(" and c.type=:type");
		sb.append(" order by c.adate desc, c.cdate desc");
		Query query = em.createQuery(sb.toString());
		query.setParameter("email", email);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(sfrom);
		c1.set(Calendar.HOUR_OF_DAY, 23);
		c1.set(Calendar.MINUTE, 59);
		c1.set(Calendar.SECOND, 59);
		c1.add(Calendar.DAY_OF_MONTH, -1);
		query.setParameter("sfrom", c1.getTime(), TemporalType.DATE);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(sto);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.add(Calendar.DAY_OF_MONTH, 1);
		query.setParameter("sto", c2, TemporalType.DATE);
		if (stype != 0)
			query.setParameter("type", stype);
		query.setFirstResult(start);
		if (limit != 0)
			query.setMaxResults(limit);
		return query.getResultList();
	}

	@Transactional
	@Override
	public boolean saveCost(Cost cost) {
		try {
			em.persist(cost);
		} catch (Exception e) {
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
	public long getCostsCountByEmail(String email, Date sfrom, Date sto,
			int stype) {
		StringBuffer sb = new StringBuffer("SELECT c FROM ");
		sb.append(Cost.class.getName());
		sb
				.append(" c where c.email = :email and c.adate>=:sfrom and c.adate<:sto");
		if (stype != 0)
			sb.append(" and c.type=:type");
		Query query = em.createQuery(sb.toString());
		query.setParameter("email", email);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(sfrom);
		c1.set(Calendar.HOUR_OF_DAY, 23);
		c1.set(Calendar.MINUTE, 59);
		c1.set(Calendar.SECOND, 59);
		c1.add(Calendar.DAY_OF_MONTH, -1);
		query.setParameter("sfrom", c1.getTime(), TemporalType.DATE);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(sto);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.add(Calendar.DAY_OF_MONTH, 1);
		query.setParameter("sto", c2, TemporalType.DATE);
		if (stype != 0)
			query.setParameter("type", stype);
		query.setHint("datanucleus.query.resultSizeMethod", "count");
		return query.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getCostsByEmail(String email, int start, int limit) {
		StringBuffer sb = new StringBuffer("SELECT c FROM ");
		sb.append(Cost.class.getName());
		sb.append(" c where c.email = :email");
		sb.append(" order by c.adate desc, c.cdate desc");
		Query query = em.createQuery(sb.toString());
		query.setParameter("email", email);
		query.setFirstResult(start);
		if (limit != 0)
			query.setMaxResults(limit);
		List<Cost> costs = query.getResultList();
		return costs;
	}
}
