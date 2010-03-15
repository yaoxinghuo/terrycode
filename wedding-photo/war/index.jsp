<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.terry.weddingphoto.data.intf.IPhotoDao"%>
<%@page import="com.terry.weddingphoto.data.impl.PhotoDaoImpl"%>
<%@page import="java.util.List"%>

<%@page import="com.terry.weddingphoto.model.Photo"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jQuery GalleryView</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.galleryview-1.1.js"></script>
<script type="text/javascript" src="js/jquery.timers-1.1.2.js"></script>
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
	text-decoration: underline;
}

a:hover {
	text-decoration: none;
}

h3 {
	border-bottom-color: white;
}
</style>
<script type="text/javascript"> 
	$(document).ready(function(){		
		$('#photos').galleryView({
			panel_width: document.body.clientWidth-30,
			panel_height: 533,
			frame_width: 100,
			frame_height: 80,
			transition_speed: 1200,
			background_color: '#333',
			background_fill_color: '#444',
			border: 'none',
			easing: 'easeInOutBack',
			nav_theme: 'custom',
			overlay_height: 52,
			filmstrip_position: 'top',
			overlay_position: 'bottom',
			pause_on_hover: true
		});
	});
</script>
</head>

<body>

<div id="gallery_wrap" align="center">
<div id="photos" class="galleryview">
<%
	IPhotoDao photoDao = new PhotoDaoImpl();
	List<Photo> photos = photoDao.getPhotos(0, 20);
	for (Photo photo : photos) {
%>
<div class="panel" style="background-color: red"><img src="view?id=<%=photo.getId()%>" />
<div class="panel-overlay">
<h2>Effet du soleil sur le paysage</h2>
<p><%=photo.getCdate()%></p>
</div>
</div>
<%
	}
%>

<ul class="filmstrip">
	<%
		for (Photo photo : photos) {
	%>
	<li><img src="view?w=100&h=80&id=<%=photo.getId()%>"
		alt="Effet du soleil" title="Effet du soleil" /></li>
	<%
		}
	%>
</ul>
</div>
</div>
</body>
</html>
