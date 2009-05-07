package com.terry.data.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.terry.data.dao.intf.IEmployeeDao;
import com.terry.data.model.Employee;
import com.terry.data.service.intf.IEmployeeService;

@Service("employeeService")
@Repository
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	public String getEmployeeNameById(String id) {
		Employee employee = employeeDao.getEmplyeeById(id);
		if (employee != null)
			return employee.getFirstName() + " " + employee.getLastName();
		else
			return "Cannot find the employee!";
	}

	@Override
	public boolean saveEmployee(String fname, String lname, Date hdate) {
		Employee employee = new Employee(fname, lname, hdate);
		return employeeDao.saveEmplyee(employee);
	}

	@Override
	public String getFirstEmployeeName() {
		List<Employee> employees = employeeDao.getEmployees(0, 10);
		if (employees != null && employees.size() != 0)
			return employees.get(0).getFirstName() + " "
					+ employees.get(0).getLastName();
		else
			return "Cannot find the employee!";
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeDao.getEmployees(0, 20);
	}

}
