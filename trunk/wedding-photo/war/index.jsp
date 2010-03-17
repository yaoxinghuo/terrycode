<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.terry.weddingphoto.data.intf.IPhotoDao"%>
<%@page import="com.terry.weddingphoto.data.impl.PhotoDaoImpl"%>
<%@page import="java.util.List"%>

<%@page import="com.terry.weddingphoto.model.Photo"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.TimeZone"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>照片测试</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link type="text/css" rel="stylesheet" href="css/wbox.css" />

<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.galleryview-1.1.js"></script>
<script type="text/javascript" src="js/jquery.timers-1.1.2.js"></script>
<script type="text/javascript" src="js/wbox-min.js"></script>

<style type="text/css">
body {
	background: #444;
	color: white;
}

#gallery_wrap { /*width: 820px;
	height: 368px;
	padding: 25px;
	background: url(images/border.png) top left no-repeat;*/
	
}

a:link,a:visited {
	color: #ddd !important;
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

h3 {
	border-bottom-color: white;
}

.filmstripd {
	display: table-cell;
	vertical-align: middle;
	width: 100px;
	height: 80px;
}

.filmstripd img {
	vertical-align: middle;
	display: none;
}

.commentcount {
	color: blue;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#photos').galleryView({
			panel_width: document.documentElement.clientWidth-76,
			panel_height: 10,
			frame_width: 100,
			frame_height: 80,
			auto_transition: true,
			transition_interval: 0,
			transition_speed: 'slow',
			background_color: '#333',
			background_fill_color: '#444',
			border: 'none',
			easing: 'easeInOutBack',
			nav_theme: 'custom',
			overlay_height: 52,
			filmstrip_position: 'top',
			overlay_position: 'top',
			pause_on_hover: true
		});
		$(".wbox").wBox({
			isFrame:true,
			drag:true,
			title:'评论'
		});
	});
</script>
</head>

<body>
<h3>照片测试
<div align="right" style="font-size: 11px;">程序设计<a target="_blank"
	href="http://xinghuo.org.ru/">Terry</a>&nbsp;<a target="_blank"
	href="http://code.google.com/p/terrycode/source/browse/#svn/trunk/wedding-photo">源码</a>&nbsp;<a
	href="admin">后台管理</a></div>
</h3>

<div id="gallery_wrap" align="center">
<div id="photos" class="galleryview">
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
		Locale.CHINA);
sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
UserService userService = UserServiceFactory.getUserService();
IPhotoDao photoDao = new PhotoDaoImpl();
List<Photo> photos = photoDao.getPhotos(0, 150);
int iter = 0;
for (Photo photo : photos) {
	String desc = "创建时间:"+sdf.format(photo.getCdate());
	int comment = photo.getComment();
	if(comment==0)
		desc+=", 还没有评论, <a class='wbox' href='comment.jsp'>抢沙发</a>";
	else if(comment>0){
		desc+=", 有 <span class='commentcount'>"+comment+"</span> 条评论,<a class='wbox' href='comment.jsp'>我也要评论</a>";
	}
%>
<div class="panel"><img style="display: none;"
	pid="<%=photo.getId()%>" iter="<%=iter++%>" />
<div class="panel-overlay">
<h2><%=photo.getRemark() == null ? photo.getFilename()
						: photo.getRemark()%></h2>
<p><%=desc %></p>
</div>
</div>
<%
	}
%>

<ul class="filmstrip">
	<%
		iter = 0;
		for (Photo photo : photos) {
	%>
	<li iter="<%=iter%>">
	<div class="filmstripd"><img pid="<%=photo.getId()%>"
		iter="<%=iter++%>" alt="<%=photo.getFilename()%>"
		title="<%=photo.getRemark() == null ? photo.getFilename()
						: photo.getRemark()%>" /></div>
	</li>
	<%
		}
	%>
</ul>
</div>
</div>
<%
	if (iter == 0) {
%>
相册中还没有图片,
<a href="admin">登录后台</a>
以上传图片（请在web.xml中将canUploadPhotos开启）!
<%
	}
%>

</body>
</html>
