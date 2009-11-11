<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.Calendar"%><html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>GFWout 代理上网</title>
<style>
input {
	font-size: 14px;
	height: 28px
}

body,td,a,p,.h {
	font-family: arial, sans-serif
}

.h {
	color: #36c;
	font-size: 20px
}

.q {
	color: #00c
}

.ts td {
	padding: 0
}

.ts {
	border-collapse: collapse
}

#gbar {
	height: 22px
}

.gbh,.gbd {
	border-top: 1px solid #c9d7f1;
	font-size: 1px
}

.gbh {
	height: 0;
	position: absolute;
	top: 24px;
	width: 100%
}

#gbi,#gbs {
	background: #fff;
	left: 0;
	position: absolute;
	top: 24px;
	visibility: hidden;
	z-index: 1000
}

#gbi {
	border: 1px solid;
	border-color: #c9d7f1 #36c #36c #a2bae7;
	z-index: 1001
}

#guser {
	padding-bottom: 7px !important;
	text-align: right
}

#gbar,#guser {
	font-size: 13px;
	padding-top: 1px !important
}

@media all {
	.gb1,.gb3 {
		height: 22px;
		margin-right: .5em;
		vertical-align: top
	}
	#gbar {
		float: left
	}
}

.gb2 {
	display: block;
	padding: .2em .5em
}

a.gb1,a.gb2,a.gb3 {
	color: #00c !important
}

.gb2,.gb3 {
	text-decoration: none
}

a.gb2:hover {
	background: #36c;
	color: #fff !important
}
</style>
</head>
<body bgcolor=#ffffff text=#000000 link=#0000cc vlink=#551a8b
	alink=#ff0000 onload="document.f.r.focus();" topmargin=3 marginheight=3>
<div id="msg"
	style="visibility: hidden; z-index: 1000; position: absolute; left: 300px;">
<table border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td style="padding: 4px; background-color: #fad163;"><font
			size="-1"><span id="msg_content"></span>&nbsp;<a href="#"
			onclick="clearMsg();return false;"><img id="close" alt="关闭"
			title="关闭" style="border: none;" src='close.gif' /></a></font></td>
	</tr>
</table>
</div>
<textarea id=csi style="display: none"></textarea>
<div id=gbar><nobr><b class=gb1>GFWout</b> <a
	href="http://fetionlib.appspot.com" onclick=gbar.qs(this) class=gb1>网页飞信</a>
<a href="http://costnote.appspot.com/" onclick=gbar.qs(this) class=gb1>网络账本</a>
</nobr></div>
<div id=guser width=100%><nobr><a
	href="http://www.google.cn/">Google 中国</a></nobr></div>
<div class=gbh style="left: 0"></div>
<div class=gbh style="right: 0"></div>
<center><br clear=all id=lgpd>
<img src=/logo.png border=0 alt="GFW 代理上网" title="GFW 代理上网" id=logo><br>
<br>
<form name="f" method="post"
	onkeydown="if(event.keyCode==13){return false;}">
<table cellpadding=0 cellspacing=0>
	<tr valign=top>
		<td width=25%>&nbsp;</td>
		<td align=center nowrap><input maxlength=2048 size=55 name="r"
			style="height: 28px; padding-top: 4px; font-size: 16px; margin-bottom: 2px; width: 28em"
			title="GFW 代理上网" value=""><br>
		<input type=submit value="GFW 代理上网" onclick="gfw();return false;" />
		<input type=submit value="Google 搜索" onclick="google();return false;" /></td>
		<td nowrap width=25% align=left><span style="font-size: 13px">&nbsp;&nbsp;<a
			href=/help.html>郑重声明</a><br>
		&nbsp;&nbsp;<a href=/help.html>使用说明</a><br>
		</span></td>
	</tr>
</table>
</form>
<br>
<p><span style="font-size: 13px;color: blue;">有时候着急上某个网站，却因为某些原因不让访问，把网址输进去，试试这个网站吧？本站只限于浏览网页内容。</span></p>
<br>
<font size=-1></font>
<p><span style="font-size: 13px;color: green;">小提示:您可以输入想要代理上网的网址或输入关键词搜索后再点击！</span></p>
<p><span style="font-size: 13px">&copy;<%=Calendar.getInstance().get(Calendar.YEAR)%>
- <a href="/help.html">隐私权政策</a> - <a
	href="http://spreadsheets.google.com/viewform?key=0AkW7RjU4e3Q3cF9XQWJkZTQzOHg1dnpGSmppYVo4ZkE&hl=en&cfg=true">意见反映</a> - <img
	style="vertical-align: middle; align: left; border: none"
	src="http://code.google.com/appengine/images/appengine-noborder-120x30.gif"
	alt="Powered by Google App Engine" /></span></p>
<p><jsp:include page="ads.html"></jsp:include></p>
</center>
<script language='javascript' src='index.js'></script>
<script type="text/javascript">
	var message='<%=request.getAttribute("message")%>';
	if(message!=null&&message!="null"){
		showMsg(message);
	}
</script>
<jsp:include page="analytics.html"></jsp:include>
</body>
</html>