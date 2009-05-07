package com.terry.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.terry.data.model.Employee;
import com.terry.data.service.intf.IEmployeeService;

@Scope("prototype")
@Component("indexAction")
public class IndexAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4575781393306576158L;

	private String message;
	private String id;
	private List<Employee> employees;

	@Autowired
	private IEmployeeService employeeService;

	@Override
	public String execute() {
		employees = employeeService.getEmployees();
		setMessage("struts2 action 传过来的中文！");
		return SUCCESS;
	}

	public String detail() {
		message = employeeService.getEmployeeNameById(id);
		return "detail";
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
