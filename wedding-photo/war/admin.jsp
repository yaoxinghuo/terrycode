<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>图片文件上传</title>
<link rel="stylesheet" href="css/admin.css" type="text/css" />
<link rel="stylesheet" href="css/uploadify.jGrowl.css" type="text/css" />
<link rel="stylesheet" href="css/uploadify.css" type="text/css" />

<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.v2.1.0.min.js"></script>
<script type="text/javascript" src="js/jquery.jgrowl_minimized.js"></script>
<script type="text/javascript" src="js/upload.js"></script>
</head>
<body>
<%
UserService userService = UserServiceFactory.getUserService();
%>
<textarea id=csi style="display: none"></textarea>
<div id=gbar><b class=gb1><a href="/">相册首页</a></b></div>
<div id=guser width=100%><%=userService.getCurrentUser().getEmail()
					+ " | "%><a
	href="<%=userService.createLogoutURL("/")
					%>">退出</a></div>
<div class=gbh style="left: 0"></div>
<div class=gbh style="right: 0"></div>


<div class="bborderx">
<table id="flex1" style="display: none"></table>
</div>
<div align="center">
<p><strong>选择多个图片文件上传</strong></p>
<input id="photoInputs" type="file" name="file"></input>
<br /><div id="fileQueue"></div><br/>
<a href="javascript:$('#photoInputs').uploadifyUpload();">开始上传</a>
|
<a href="javascript:$('#photoInputs').uploadifyClearQueue();">清除列队</a>
<script type="text/javascript">

</script>
</div>
</body>
</html>