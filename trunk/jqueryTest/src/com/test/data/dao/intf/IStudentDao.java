package com.test.data.dao.intf;

import java.util.List;

import com.test.data.model.Student;

public interface IStudentDao {
	public void saveStudent(Student student);

	public void deleteStudent(Student student);

	public Student getStudentById(String id);

	public long getStudentTotalCount(String column, String keyword);

	// 得到某一页所有的Students
	public List<Student> findPagedAll(long currentPage, long pageSize,
			String column, String keyword, String sortname, String order);
}
