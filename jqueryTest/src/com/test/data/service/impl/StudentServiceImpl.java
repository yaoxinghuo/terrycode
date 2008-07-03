package com.test.data.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.data.dao.intf.IStudentDao;
import com.test.data.model.Student;
import com.test.data.service.intf.IStudentService;

@Service("studentService")
@Transactional(readOnly = true)
@Repository
public class StudentServiceImpl implements IStudentService {

	@Resource(name = "studentDao")
	private IStudentDao studentDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Student> findPagedAll(long currentPage, long pageSize,
			String column, String keyword, String sortname, String order) {
		return studentDao.findPagedAll(currentPage, pageSize, column, keyword,
				sortname, order);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public long getStudentTotalCount(String column, String keyword) {
		return studentDao.getStudentTotalCount(column, keyword);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public long getTotalPage(long pageSize, String column, String keyword) {
		long totalCount = studentDao.getStudentTotalCount(column, keyword);
		long totalPageCount = ((totalCount + pageSize) - 1) / pageSize;
		return totalPageCount;
	}

}
