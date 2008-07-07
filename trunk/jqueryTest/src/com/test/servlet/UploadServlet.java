package com.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7707832521557161172L;

	// private static final String CONTENT_TYPE = "text/html; charset=GB2312";

	// Process the HTTP Post request
	@SuppressWarnings("unchecked")
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String path = request.getSession().getServletContext().getRealPath(
					"/upload/upload");
			factory.setRepository(new File(path));// 设置setSizeThreshold方法中提到的临时文件的存放目录
			factory.setSizeThreshold(1024);// 用于设置是否使用临时文件保存解析出的数据的那个临界值
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = (List<FileItem>) upload
					.parseRequest(request);
			System.out.println("Item Size" + items.size());
			for (FileItem item : items) {// 循环列出 items 中的属性
				System.out.println("********cicle1111" + item.isFormField());
				if (!item.isFormField()) {// 忽略其他不是文件域的所有表单信息
					String name1 = item.getName();
					long size = item.getSize();
					if ((name1 == null || name1.equals("")) && size == 0) {
						continue;
					} else {
						// String name = item.getFieldName();
						String value = item.getName();
						int start = value.lastIndexOf("\\");
						String filename = value.substring(start + 1);
						item.write(new File(path, filename));// 文件存放的地址
					}
				}
			}
			System.out.println("********here");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/upload/index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
}
