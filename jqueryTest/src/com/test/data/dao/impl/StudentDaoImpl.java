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
	public long getStudentTotalCount(String column, String keyword) {
		StringBuffer sql = new StringBuffer("select count(*) from Student s ");
		if (keyword != null && !keyword.equals("")) {
			sql.append("where s.").append(column);
			sql.append(" like '%").append(keyword).append("%'");
		}
		Query query = sessionFactory.getCurrentSession().createQuery(
				sql.toString());
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findPagedAll(long currentPage, long pageSize,
			String column, String keyword, String sortname, String order) {
		if (currentPage == 0)
			currentPage = 1;
		StringBuffer sql = new StringBuffer("from Student s ");
		if (keyword != null && !keyword.equals("")) {
			sql.append("where s.").append(column);
			sql.append(" like '%").append(keyword).append("%'");
		}
		sql.append(" order by s.").append(sortname).append(" ").append(order);
		Query queryObject = sessionFactory.getCurrentSession().createQuery(
				sql.toString());
		queryObject.setFirstResult(((int) currentPage - 1) * (int) pageSize);
		queryObject.setMaxResults((int) pageSize);
		return queryObject.list();
	}

}
