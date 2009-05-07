package com.terry.data.dao.intf;

import java.util.List;

import com.terry.data.model.Employee;

public interface IEmployeeDao {
	public boolean saveEmplyee(Employee employee);

	public Employee getEmplyeeById(String id);

	public List<Employee> getEmployees(int start, int limit);
}
