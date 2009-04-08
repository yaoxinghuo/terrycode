<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>江南大学食品学院实验室--错误404</title>
</head>
<body>
	<%response.sendRedirect(request.getRequestURI().replace("error404","index_error404")); %>
</body>
</html>
