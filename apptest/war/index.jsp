<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
</head>
<body>
	Index.jsp HelloWorld.<br/>
	<s:property value="message"/>
	<br/>
	<table>
		<tr>
			<td>ID</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Hire Date</td>
		</tr>
		<s:iterator value="employees">
			<tr>
				<td><a href="index!detail.action?id=<s:property value="id"/>"><s:property value="id"/></a></td>
				<td><s:property value="firstName"/></td>
				<td><s:property value="lastName"/></td>
				<td><s:property value="hireDate"/></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>