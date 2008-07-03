package com.test.data.service.intf;

import java.util.List;

import com.test.data.model.Student;

public interface IStudentService {
	public long getStudentTotalCount(String data, String username);

	public List<Student> findPagedAll(long currentPage, long pageSize,
			String column, String keyword, String sortname, String order);

	public long getTotalPage(long pageSize, String column, String keyword);
}
