package com.test.data.dao.intf;

import java.util.List;

import com.test.data.model.Student;

public interface IStudentDao {
	public void saveStudent(Student student);
	
	public void deleteStudent(Student student);
	
	public Student getStudentById(String id);
	
	public long getStudentTotalCount(String data, String username, String order);
	
	public List<Student> findPagedAll(int currentPage, int pageSize,String data , String username,String order);
}
