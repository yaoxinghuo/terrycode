package com.test.data.dao.service;

import java.util.List;

import com.test.data.model.Student;

public interface IStudentService {
	public long getStudentTotalCount(String data, String username, String order);

	public List<Student> findPagedAll(int currentPage, int pageSize,
			String data, String username, String order);

	public long getTotalPage(int pageSize, String data, String username,
			String order);
}
