<%@ page contentType="text/html;charset=UTF-8"
	import="java.util.*,org.springside.extremeweb.util.WebUtil,org.springside.extremeweb.entity.User,org.springside.extremeweb.service.UserService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//使用Spring ApplicationContext中的UserService查询用户列表.
	UserService service = (UserService) WebUtil.getBean(application,"userService");
	Collection<User> userList = service.getAllUser();
	//将查询结果放入pageContext(page scope),供JSTL访问.
	pageContext.setAttribute("userList", userList);
%>

<html>
	<head>
		<title>用户管理</title>
		<%@ include file="/meta.jsp"%>
	</head>

	<body>
		<div id="content">
			<h2>
				用户列表
			</h2>
			<div id="list">
				<table cellSpacing=0 cellPadding=1 border="1px">
					<tr>
						<th>
							<b>登录名</b>
						</th>
						<th>
							<b>姓名</b>
						</th>
						<th>
							<b>密码</b>
						</th>
						<th>
							<b>操作</b>
						</th>
					</tr>

					<c:forEach items="${userList}" var="user">
						<tr>
							<td>
								${user.loginName} &nbsp;
							</td>
							<td>
								${user.name}&nbsp;
							</td>
							<td>
								${user.password}&nbsp;
							</td>
							<td>
								<a
									href="userAction.jsp?action=delete&loginName=${user.loginName}">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div id="input">
				<h2>
					增加新用户
				</h2>
				<form name="mainform" action="userAction.jsp" method="post">
					<input type="hidden" name="action" value="create">
					<p>
						登录名:
						<input type="text" name="loginName">
					</p>
					<p>
						姓名:
						<input type="text" name="name">
					</p>
					<p>
						密码:
						<input type="text" name="password">
					</p>
					<p>
						<input type="submit" value="提交" />
					</p>
				</form>
			</div>
		</div>
	</body>
</html>