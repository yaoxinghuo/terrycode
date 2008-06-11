<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head />
<title>Register</title>
</head>
<body>
	<s:form action="Register">
		<s:textfield label="Enter your username" name="username"></s:textfield>
		<s:textfield label="Enter your password" name="password"></s:textfield>
		<s:textfield label="Enter your nickname" name="nickname"></s:textfield>
		<s:textfield label="Enter your email" name="email"></s:textfield>
		<s:textfield label="Enter your activity" name="activity"></s:textfield>
		<s:textfield label="Enter your service" name="service"></s:textfield>
		<s:textfield label="Enter your industry" name="industry"></s:textfield>
		<s:textfield label="Enter your tag" name="tag"></s:textfield>
		<s:textfield label="Enter your province" name="province"></s:textfield>
		<s:textfield label="Enter your city" name="city"></s:textfield>
		<s:textfield label="Enter your remark" name="remark"></s:textfield>
		<s:submit label="Register"></s:submit>
	</s:form>
</body>
</html>