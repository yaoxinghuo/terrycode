package com.terry.data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.data.dao.intf.IEmployeeDao;
import com.terry.data.model.Employee;

@Component("employeeDao")
public class EmployeeDaoImpl implements IEmployeeDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Employee getEmplyeeById(String id) {
		Key key = KeyFactory.stringToKey(id);
		if (key == null || !key.isComplete())
			return null;

		return em.find(Employee.class, key);
	}

	@Override
	public boolean saveEmplyee(Employee employee) {
		try {
			em.persist(employee);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmployees(int start, int limit) {
		Query query = em
				.createQuery("SELECT e FROM com.terry.data.model.Employee e");
		query.setFirstResult(start);
		if (limit != 0)
			query.setMaxResults(limit);
		return query.getResultList();
	}

}
