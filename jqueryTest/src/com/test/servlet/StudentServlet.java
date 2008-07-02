package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.test.data.dao.service.IStudentService;
import com.test.data.model.Student;

public class StudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9071299765104670593L;

	private IStudentService studentService;

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		studentService = (IStudentService) wac.getBean("studentService");
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String rp = request.getParameter("rp"); // 每页显示的数据数
		String pageString = request.getParameter("page");// 获取
		// flexgrid传过来的页数。
		String query = request.getParameter("query");// 获取 查询的条件
		query = java.net.URLDecoder.decode(query, "UTF-8");// 解码

		String qtype = request.getParameter("qtype");// 获取 查询的类型
		String sortorder = request.getParameter("sortorder");// 获取 查询的类型
		if (pageString == null || pageString.length() == 0) {
			pageString = "1";
		}
		List<Student> custList = new ArrayList<Student>();// 定义一个list
		custList = studentService.findPagedAll(Integer.parseInt(pageString),
				Integer.parseInt(rp), qtype, query, sortorder);// 根据页数和每页显示数量
		// 查出数据集合
//		long tatolPage = studentService.getTotalPage(Integer.parseInt(rp),
//				qtype, query, sortorder); // 总页数
		long totalCount = studentService.getStudentTotalCount(qtype, query,
				sortorder); // 记录总数
		JSONObject jo = new JSONObject();
		jo.put("page", Integer.parseInt(pageString));
		jo.put("total", totalCount);
		
		JSONArray rows = new JSONArray();
		
		int list_size = custList.size();

		if (list_size > 0) {
			for (int i = 0; i < list_size; i++) {
				Student student = custList.get(i);
				JSONObject stu = new JSONObject();
				stu.put("id", "ZW");
				JSONArray ja = new JSONArray();
				ja.add(student.getId());
				ja.add(student.getUsername());
				ja.add(student.getPassword());
				ja.add(student.getAge());
				ja.add(student.getAddress());
				stu.put("cell", ja);
				rows.add(stu);
			}
		} 
		jo.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jo.toString());
		out.flush();
		out.close();
	}

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
}
