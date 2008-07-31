<%@ page contentType="text/html;charset=UTF-8"
	import="org.springside.extremeweb.util.WebUtil,org.springside.extremeweb.entity.User,org.springside.extremeweb.service.UserService"%>

<%
	UserService service = (UserService) WebUtil.getBean(application,
			"userService");

	String action = request.getParameter("action");

	if ("create".equals(action)) {
		//使用Spring的DataBinder 将request参数绑定到DTO.
		User user = new User();
		WebUtil.bind(request, user);

		//使用Spring ApplicationContext中的UserService添加用户.
		service.createUser(user);

		//跳转到展示页面.
		response.sendRedirect("user.jsp");
	}

	if ("delete".equals(action)) {
		service.deleteUser(request.getParameter("loginName"));

		response.sendRedirect("user.jsp");
	}
%>