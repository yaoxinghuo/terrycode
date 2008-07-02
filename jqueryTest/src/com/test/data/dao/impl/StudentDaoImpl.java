package com.test.data.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.data.dao.intf.IStudentDao;
import com.test.data.model.Student;

@Component("studentDao")
public class StudentDaoImpl implements IStudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteStudent(Student student) {
		sessionFactory.getCurrentSession().delete(student);
	}

	@Override
	public Student getStudentById(String id) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class,
				id);
	}

	@Override
	public void saveStudent(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}

	@Override
	public long getStudentTotalCount(String data, String username, String order) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select count(*) from Student s where s." + data + " like '%"
						+ username + "%'  order by s.id " + order + "");
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findPagedAll(int currentPage, int pageSize,
			String data, String username, String order) {
		if (currentPage == 0) {
			currentPage = 1;
		}
		String queryString = "from Student s where s." + data + " like '%"
				+ username + "%'  order by s.id " + order + "";
		Query queryObject = sessionFactory.getCurrentSession().createQuery(
				queryString);
		queryObject.setFirstResult((currentPage - 1) * pageSize);
		queryObject.setMaxResults(pageSize);
		return queryObject.list();
	}

}
