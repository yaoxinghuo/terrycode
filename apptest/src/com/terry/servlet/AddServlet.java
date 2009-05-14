package com.terry.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terry.data.model.Company;
import com.terry.data.model.Employee;
import com.terry.data.util.EMF;

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

		Employee employee = new Employee("Alfred", "Smith", new Date());

		Company company = new Company();
		company.setName("Google");

		List<Employee> employees = new ArrayList<Employee>();
		employee.setCompany(company);
		employees.add(employee);
		company.setEmployees(employees);
		try {
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