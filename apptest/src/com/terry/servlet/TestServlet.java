package com.terry.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terry.data.model.Department;
import com.terry.data.model.Employee;
import com.terry.data.util.EMF;

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
		EntityManager em = EMF.get().createEntityManager();

		try {
			// request.setAttribute("employee",
			// employees.get(0).getFirstName());
			List<Employee> employees = (List<Employee>) em.createQuery(
					"SELECT e FROM com.terry.data.model.Employee e")
					.getResultList();

			List<Department> departments = (List<Department>) em.createQuery(
					"SELECT d FROM com.terry.data.model.Department d")
					.getResultList();

			String company = "null";
			String password = "null";
			String dname = "null";
			if (employees != null && employees.size() != 0) {
				company = employees.get(0).getCompany().getName();
				password = employees.get(0).getPassport().getPassword();
			}
			if (departments != null && departments.size() != 0) {
				dname = departments.get(0).getName() + "("
						+ departments.get(0).getCompany().getName() + ")";
			}
			response.setContentType("text/plain");
			response.getWriter().println(
					"Test Query Result Company:" + company + "\nPassword:"
							+ password + "\nDepartment:" + dname);

			// request.getRequestDispatcher("/success.jsp").forward(request,
			// response);
		} finally {
			em.close();
		}
	}
}