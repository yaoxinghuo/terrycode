package com.terry.costnote.data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.terry.costnote.data.dao.intf.ITemplateDao;
import com.terry.costnote.data.model.Template;
import com.terry.costnote.data.util.EMF;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 16, 2009 11:45:13 AM
 */

@Component("templateDao")
public class TemplateDaoImpl implements ITemplateDao {

	EntityManager em = EMF.get().createEntityManager();

	@SuppressWarnings("unchecked")
	@Override
	public List<Template> getTemplatesByEmail(String email, int limit) {
		Query query = em.createQuery("SELECT t FROM "
				+ Template.class.getName()
				+ " t where t.email = :email ORDER BY t.cdate desc");
		query.setParameter("email", email);
		query.setFirstResult(0);
		if (limit != 0)
			query.setMaxResults(limit);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addTemplate(Template template, int limit) {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			String email = template.getEmail();
			Query query = em.createQuery("SELECT t FROM "
					+ Template.class.getName()
					+ " t where t.email = :email ORDER BY t.cdate");
			query.setParameter("email", email);
			List<Template> templates = query.getResultList();
			if (templates.size() >= limit) {
				Template old = templates.get(0);
				em.remove(old);
			}
			em.persist(template);
			tx.commit();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
