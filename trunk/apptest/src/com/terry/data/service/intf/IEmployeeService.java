package com.terry.data.service.intf;

import java.util.Date;
import java.util.List;

import com.terry.data.model.Employee;

public interface IEmployeeService {

	public String getEmployeeNameById(String id);

	public boolean saveEmployee(String fname, String lname, Date hdate);

	public String getFirstEmployeeName();

	public List<Employee> getEmployees();
}
