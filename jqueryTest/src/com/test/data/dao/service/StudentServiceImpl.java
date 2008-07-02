package com.test.data.dao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.data.dao.intf.IStudentDao;
import com.test.data.model.Student;

@Service("studentService")
@Transactional(readOnly = true)
@Repository
public class StudentServiceImpl implements IStudentService {

	@Resource(name="studentDao")
	private IStudentDao studentDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Student> findPagedAll(int currentPage, int pageSize,
			String data, String username, String order) {
		return studentDao.findPagedAll(currentPage, pageSize, data, username,
				order);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public long getStudentTotalCount(String data, String username, String order) {
		return studentDao.getStudentTotalCount(data, username, order);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public long getTotalPage(int pageSize, String data, String username,
			String order) {
		long totalCount = studentDao
				.getStudentTotalCount(data, username, order);
		long totalPageCount = ((totalCount + pageSize) - 1) / pageSize;
		return totalPageCount;
	}

}
