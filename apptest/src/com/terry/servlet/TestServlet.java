package com.terry.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terry.data.model.Employee;
import com.terry.data.util.PMF;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Apr 8, 2009 10:51:31 PM
 */
public class TestServlet extends HttpServlet {

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

	@SuppressWarnings("unchecked")
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.setContentType("text/plain");
		// response.getWriter().println("Hello, world");
		Employee employee = new Employee("Alfred", "Smith", new Date());

		PersistenceManager pm = PMF.get().getPersistenceManager();

		List<Employee> employees;
		try {
			pm.makePersistent(employee);

			String query = "select from " + Employee.class.getName()
					+ " where lastName == 'Smith'";
			employees = (List<Employee>) pm.newQuery(query).execute();

		} finally {
			pm.close();
		}
		if (employees != null && employees.size() != 0)
			request.setAttribute("employee", employees.get(0).getFirstName());
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

}
