<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 TRANSITIONAL//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<head>
<title>Error</title>
</head>

<body>
对不起，程序错误，我们对此非常抱歉，请稍候再试！
<br>
<s:property value="exception.message" />
</body>
</html>