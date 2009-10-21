<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%><html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta equiv="X-UA-Compatible" content="chrome=1">
<meta name="gwt:property" content="locale=zh_CN">

<link type="text/css" rel="stylesheet" href="css/Costnote.css">
<link rel="stylesheet" type="text/css" href="css/gxt-all.css" />

<title>网络记帐本</title>
<script language='javascript' src='chart/swfobject.js'></script>
<script type="text/javascript" language="javascript"
	src="costnote/costnote.nocache.js"></script>
</head>

<body>

<%
	UserService userService = UserServiceFactory.getUserService();
	String logoutURL = userService.createLogoutURL("/");
	// String nickname = userService.getCurrentUser().getNickname();
%>
<jsp:include page="message.html"></jsp:include>
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
				<td align="center"><font size='-1'>&nbsp;<span
					id="account_content"><span id="nickname"></span>&nbsp;|&nbsp;<a
					href="https://fetionlib.appspot.com/" target="_blank">网页飞信</a>&nbsp;|&nbsp;<a
					href="http://gfwout.appspot.com/" target="_blank">代理上网</a>&nbsp;|&nbsp;<a
					href="<%=logoutURL%>">退出</a></span></font></td>
			</tr>
			<tr>
				<td align="right" height="40"></td>
			</tr>
			<tr>
				<td align="right"><font size='-1'><img
					src="http://code.google.com/appengine/images/appengine-silver-120x30.gif"
					alt="Powered by Google App Engine" /></font></td>
			</tr>
		</table>
		</div>
		</td>

	</tr>
</table>


<div id="north-div">
<div class="layout-title"><img src="images/logo.png" /><span>&nbsp;</span>网络记帐本</div>

</div>
</div>
<jsp:include page="analytics.html"></jsp:include>
</body>
</html>
