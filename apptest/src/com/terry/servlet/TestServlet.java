package com.terry.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			String s = "null";
			if (employees != null && employees.size() != 0)
				s = employees.get(0).getCompany().getName();

			response.setContentType("text/plain");
			response.getWriter().println("Many-to-one Test Query Result:"+s);

			// request.getRequestDispatcher("/success.jsp").forward(request,
			// response);
		} finally {
			em.close();
		}
	}

}