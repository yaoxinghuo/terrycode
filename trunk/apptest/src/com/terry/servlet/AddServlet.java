package com.terry.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terry.data.model.Company;
import com.terry.data.model.Department;
import com.terry.data.model.Employee;
import com.terry.data.model.Passport;
import com.terry.data.util.EMF;
import com.terry.data.util.MD5;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Apr 8, 2009 10:51:31 PM
 */
public class AddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8570165605274565616L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = EMF.get().createEntityManager();

		try {
			Company company = new Company();
			company.setName("Google");
			
			ArrayList<Employee> employees = new ArrayList<Employee>(); 
			Employee employee = new Employee("Alfred", "Smith", new Date());
			employee.setCompany(company);
			Passport passport = new Passport();
			passport.setUsername("terry");
			passport.setPassword(MD5.compute("123456"));
			employee.setPassport(passport);
			
			employees.add(employee);
			company.setEmployees(employees);
			
			ArrayList<Department> departments = new ArrayList<Department>();
			Department department = new Department();
			department.setName("IT");
			departments.add(department);
			company.setDepartments(departments);

			em.persist(company);

			response.setContentType("text/plain");
			response.getWriter().println("Success!");

		} catch (Exception e) {
			response.setContentType("text/plain");
			response.getWriter().println("Error! " + e.getMessage());
		} finally {
			em.close();
		}
	}

}