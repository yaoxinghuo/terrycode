package com.terry.costnote.data.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.costnote.data.dao.intf.IScheduleDao;
import com.terry.costnote.data.model.Schedule;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 8:21:27 AM
 */

@Component("scheduleDao")
public class ScheduleDaolmpl implements IScheduleDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean deleteSchedule(Schedule schedule) {
		try {
			em.remove(schedule);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> getSchedulesByEmail(String email, Date sfrom,
			Date sto, int start, int limit) {
		StringBuffer sb = new StringBuffer("SELECT s FROM ");
		sb.append(Schedule.class.getName());
		sb
				.append(" s where s.email = :email and s.adate>=:sfrom and s.adate<:sto");
		sb.append(" order by s.adate desc, s.cdate desc");
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
		query.setFirstResult(start);
		if (limit != 0)
			query.setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public boolean saveSchedule(Schedule schedule) {
		try {
			em.persist(schedule);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Schedule getScheduleById(String scheduleId) {
		Key key = KeyFactory.stringToKey(scheduleId);
		if (key == null || !key.isComplete())
			return null;

		return em.find(Schedule.class, key);
	}

	@Override
	public long getSchedulesCountByEmail(String email, Date sfrom, Date sto) {
		StringBuffer sb = new StringBuffer("SELECT count(s) FROM ");
		sb.append(Schedule.class.getName());
		sb
				.append(" s where s.email = :email and s.adate>=:sfrom and s.adate<:sto");
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
		query.setHint("datanucleus.query.resultSizeMethod", "count");
		return (Integer) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> getSchedulesByEmail(String email, int start, int limit) {
		StringBuffer sb = new StringBuffer("SELECT s FROM ");
		sb.append(Schedule.class.getName());
		sb.append(" s where s.email = :email");
		sb.append(" order by s.adate desc, s.cdate desc");
		Query query = em.createQuery(sb.toString());
		query.setParameter("email", email);
		query.setFirstResult(start);
		if (limit != 0)
			query.setMaxResults(limit);
		List<Schedule> schedules = query.getResultList();
		return schedules;
	}
}