package com.terry.data.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.data.dao.intf.IEmployeeDao;
import com.terry.data.model.Employee;
import com.terry.data.util.EMF;

@Component("employeeDao")
public class EmployeeDaoImpl implements IEmployeeDao {

	EntityManager em = EMF.get().createEntityManager();

	@Override
	public Employee getEmplyeeById(String id) {
		Key key = KeyFactory.stringToKey(id);
		return em.find(Employee.class, key);
	}

	@Override
	public boolean saveEmplyee(Employee employee) {
		try {
			em.persist(employee);
		} catch (Exception e) {
			e.printStackTrace();
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
