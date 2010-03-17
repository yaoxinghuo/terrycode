<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.TimeZone"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.terry.weddingphoto.data.impl.PhotoDaoImpl"%>
<%@page import="com.terry.weddingphoto.data.intf.IPhotoDao"%>
<%@page import="java.util.List"%>
<%@page import="com.terry.weddingphoto.model.Comment"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>照片测试</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/comment.js"></script>

<style type="text/css">
body {
	background: #444;
	color: white;
}

a:link,a:visited {
	color: #ddd !important;
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

.cnick {
	color : #f05d0c;
	min-width: 300px;
}

.ccdate {
	color : #ddd;
}

.ccont {
	color : #ee07fc;
	font-weight: bold;
}

</style>
</head>

<body>
<div id="msg"
	style="visibility: hidden; z-index: 1000; position: absolute; left: 300px;">
<table border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td style="padding: 4px; background-color: #fad163;color: black;"><font
			size="-1"><span id="msg_content"></span>&nbsp;<a href="#"
			onclick="clearMsg();return false;"><img id="close" alt="关闭"
			title="关闭" style="border: none;" src='images/close.gif' /></a></font></td>
	</tr>
</table>
</div>
<div align="right" style="font-size: 11px;">程序设计<a target="_blank"
	href="http://xinghuo.org.ru/">Terry</a>&nbsp;<a target="_blank"
	href="http://code.google.com/p/terrycode/source/browse/#svn/trunk/wedding-photo">源码</a>&nbsp;<a
	href="admin" target="_blank">后台管理</a></div>
<div id="cc">
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
			Locale.CHINA);
	sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
	UserService userService = UserServiceFactory.getUserService();
	boolean admin = userService.isUserLoggedIn()&&userService.isUserAdmin();
	IPhotoDao photoDao = new PhotoDaoImpl();
	String pid = request.getParameter("pid");
	List<Comment> comments = photoDao.getCommentsByPhotoId(pid, 0, 0);
	for (Comment c : comments) {
		String cid = c.getId();
%>
<li id="li-<%=cid %>"><p><span class="cnick"><%=c.getName()%></span>&nbsp;<span class="ccdate"><%=sdf.format(c.getCdate())%></span>&nbsp;说：</p><span class="ccont"><%=c.getContent()%></span>
	<%=admin?"<a href=# onclick='deleteComment(\""+cid+"\");return false;'>[删除]</a>":"" %>
<br/></li>
</div>
<%
	}
%>
<script type="text/javascript">
	var admin = <%=admin %>;
</script>
<div>
<form>
<table>
	<tr>
		<td colspan="3" align="center">&nbsp;<span id="message"
			style="display: none; color: red; font-weight: bold;"></span></td>
	</tr>
	<tr>
		<td>*您的昵称</td>
		<td><input type="text" id="nickname" maxlength="12"/></td>
		<td rowspan="2">
			<img src="view?id=<%=pid %>&w=100&h=80"/>
		</td>
	</tr>
	<tr>
		<td>Email(选填)</td>
		<td><input type="text" id="email" /></td>
	</tr>
	<tr>
		<td>*评论内容</td>
		<td colspan="2"><textarea rows="6" cols="35" id="ccontent"></textarea> </td>
	</tr>
	<tr>
		<td><input type="hidden" value="<%=pid %>" id="pid"/> </td>
		<td colspan="2"><input type="button" value="发布评论" id="commentSave"
			style="width: 80px;"/> </td>
	</tr>
</table>
</form>
</div>
</body>
</html>
