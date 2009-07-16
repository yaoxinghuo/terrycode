<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%><html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet" href="css/Costnote.css">
<link rel="stylesheet" type="text/css" href="css/gxt-all.css" />

<title>记账系统</title>
<script language='javascript' src='chart/swfobject.js'></script>
<script type="text/javascript" language="javascript"
	src="costnote/costnote.nocache.js"></script>
</head>

<body>

<%
UserService userService = UserServiceFactory.getUserService();
String logoutURL = userService.createLogoutURL("/");
String nickname = userService.getCurrentUser().getNickname();
%>
<jsp:include page="message.jsp"></jsp:include>
<div id="loading_div" style="visibility: visible;">
<table align="center">
	<tr>
		<td height="200"></td>
	</tr>
</table>
<table align="center"
	style="background: #ff7e02; border: 2; solid: #000000; color: #416f02;">
	<tr height="25">
		<td bgColor="#FFFFFF"><span style="padding: 6px;"><img
			style='vertical-align: middle; width: 16px; height: 16px;'
			src='icons/indicator.gif' alt='Loading' />&nbsp;页面加载中,请稍候...</span></td>
	</tr>
</table>
</div>

<div id="north" style="visibility: hidden;">
<table width="100%">
	<tr>
		<td align="left">
		<div style="z-index: 1000; position: absolute;">
		<table align="left" border='0' cellspacing='0' cellpadding='0'>
			<tr>
				<td align="left"></td>
			</tr>
		</table>
		</div>
		</td>

		<td>
		<div style="z-index: 10; position: absolute; right: 2px;"
			id="account_div">
		<table align="right" border='0' cellspacing='0' cellpadding='0'>
			<tr>
				<td align="center"><font size='-1'>&nbsp;<span id="account_content"><span id="nickname"><%=nickname %></span>&nbsp;|&nbsp;<a
					href="<%=logoutURL %>">退出</a></span></font></td>
			</tr>
		</table>
		</div>
		</td>

	</tr>
</table>


<div id="north-div">
<div class="layout-title"><img src="images/logo.png" /><span>&nbsp;</span>记账系统</div>

</div>
</div>

</body>
</html>
