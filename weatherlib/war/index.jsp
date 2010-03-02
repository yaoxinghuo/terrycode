<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/core.css" />
<link rel="stylesheet" type="text/css" href="css/flexigrid.css" />
<link rel="stylesheet" type="text/css" href="css/thickbox.css" />

<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/flexigrid.pack.js"></script>
<script type="text/javascript" src="js/thickbox.js"></script>

<title>天气预报邮件定制</title>
<%
	UserService userService = UserServiceFactory.getUserService();
%>
</head>
<body topmargin=3 marginheight=3>

<div id="msg"
	style="visibility: hidden; z-index: 1000; position: absolute; left: 300px;">
<table border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td style="padding: 4px; background-color: #fad163;"><font
			size="-1"><span id="msg_content"></span>&nbsp;<a href="#"
			onclick="clearMsg();return false;"><img id="close" alt="关闭"
			title="关闭" style="border: none;" src='images/close.gif' /></a></font></td>
	</tr>
</table>
</div>

<textarea id=csi style="display: none"></textarea>
<div id=gbar><b class=gb1>天气预报</b> <a
	href="http://fetion.xinghuo.org.ru/" target="_blank" class=gb1>网页飞信</a>
<a href="http://websms.org.ru/" target="_blank" class=gb1>GV网页短信</a></div>
<div id=guser width=100%><%=userService.isUserLoggedIn() ? userService
					.getCurrentUser().getEmail()
					+ " | " : ""%><a
	href="<%=userService.isUserLoggedIn() ? userService
					.createLogoutURL("/") : userService.createLoginURL("/")%>"><%=userService.isUserLoggedIn() ? "退出"
					: "<strong style='COLOR: blue'>登录</strong>"%></a></div>
<div class=gbh style="left: 0"></div>
<div class=gbh style="right: 0"></div>


<div class="bborderx">
<table id="flex1" style="display: none"></table>
</div>

<%
	if (userService.isUserLoggedIn()) {
%>
<script type="text/javascript" src="js/weather.js"></script>

<div style="display: none;"><input id="newSchedule"
	alt="#TB_inline?height=166&width=336&inlineId=hiddenModalContent&;modal=false"
	title="<b>新建天气预报提醒</b>" class="thickbox" type="button" value="Show" /></div>

<div id="hiddenModalContent" style="display: none;">
<form id="scheduleForm">
<table border="0">
	<tr>
		<td style="width: 100px;">*发送时间：</td>
		<td align="left"><select name="sdate_hour" id="sdate_hour">
			<%
				for (int i = 0; i < 24; i++) {
			%>
			<option <%=i == 20 ? "selected" : ""%>
				value="<%=i < 10 ? ("0" + i) : i%>"><%=i < 10 ? ("0" + i) : i%>
			时</option>
			<%
				}
			%>
		</select></td>
		<td align="left"><select name="sdate_minute" id="sdate_minute">
			<%
				for (int i = 0; i < 12; i++) {
			%>
			<option value="<%=i < 2 ? ("0" + i * 5) : i * 5%>"><%=i < 2 ? ("0" + i * 5) : i * 5%>
			分</option>
			<%
				}
			%>
		</select></td>
	</tr>
	<tr>
		<td>*发送状态：</td>
		<td colspan="2"><select name="type" id="type"
			style="width: 150px;">
			<option value="1" selected="selected">天气内容放正文</option>
			<option value="2">天气内容放主题</option>
			<option value="0">暂时停用</option>
		</select></td>
	</tr>
	<tr>
		<td>*接收邮箱：</td>
		<td colspan="2"><input name="email" id="email" value="@139.com"
			style="width: 180px;" /></td>
	</tr>
	<tr>
		<td>*定制城市：</td>
		<td colspan="2"><input name="city" id="city"
			style="width: 180px;" maxlength="12" /></td>
	</tr>
	<tr>
		<td>备注：</td>
		<td colspan="2"><input name="remark" id="remark" maxlength="100"
			style="width: 180px;" /></td>
	</tr>
	<tr>
		<td colspan="3"><input type="hidden" id="sid" value="" /></td>
	</tr>
	<tr>
		<td width="100px;"></td>
		<td><input type="button" value="保存" id="scheduleSave"
			style="width: 60px;" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center">&nbsp;<span id="message"
			style="display: none; color: red; font-weight: bold;"></span></td>
	</tr>
</table>
</form>
</div>
<div>
您最多可以定制
<span id="slimit" style="color: blue; font-weight: bold;">10</span>
个邮件提醒，已定制
<span id="count" style="color: blue; font-weight: bold;">0</span>
个邮件提醒，设置发送邮件时的昵称：
<input type="text" id="nickname" maxlength="12" />
<input id="updateNickname" type="button" value="更改" />
</div>
<br />
<br />
<%
	}else {
%>
	<br/><p align="center"><font size="5">天气预报邮件定制<br /></font>
	<font color='green'>提示：需要<a href="<%=userService.createLoginURL("/") %>">登录</a>才能进行天气预报邮件定制</font></p>
<%} %>
<p align="left"><strong><font size="4">功能：</font></strong><br />
&nbsp; &nbsp; 定时每天向指定邮箱发送天气预报邮件，可借助手机邮箱（<a href="http://mail.139.com/"
	target="_blank">139邮箱</a>，<a href="http://mail.wo.com.cn"
	target="_blank">联通邮箱</a>，<a href="http://www.189.cn/webmail/"
	target="_blank">189邮箱</a>）实现手机天气预报定制<br />
<strong><font size="4">特点：<br />
</font></strong> &nbsp; &nbsp; 一个Gtalk帐号可定制多个城市的天气预报或多个好友的邮箱<br />
&nbsp; &nbsp; 可自定义每天发送时间、可选择天气预报内容放入邮件正文或主题<br />
&nbsp; &nbsp; 可发送3天的天气情况<br />
&nbsp; &nbsp; 定时期限无限长（只要gae没倒闭）<br />
<strong><font size="4">说明：<br />
</font></strong>&nbsp; &nbsp; 1.定时只能精确到5分钟<br />
&nbsp; &nbsp; 2.暂时只能制定10条定时命令，有需要可以联系作者<br />
&nbsp; &nbsp; 3.天气数据来自Google，Google每天<b>8:35</b>和<b>17:35</b>点更新数据，请把提醒时间适当设置在这两个时间点之后<br />
&nbsp; &nbsp; 4.为了方便您今后管理和取消订阅，需要登录到您的Google帐号<br />
<br />
程序设计：<a href="http://xinghuo.org.ru/" target="_blank"><font
	color="#800080">Terry</font></a> <a
	href="http://code.google.com/p/terrycode/source/browse/#svn/trunk/weatherlib"
	target="_blank"><font color="#800080">源代码</font></a> 企划：<a
	href="http://liming.net.ru/" target="_blank"><font color="#800080">黎明破晓</font></a><br />
其他作品： <a href="http://fetion.xinghuo.org.ru/" target="_blank"><font
	color="#800080">网页飞信</font></a> <a href="http://feix.org.ru/"
	target="_blank"><font color="#800080">网页飞信</font></a></p>


<p align="center"><img
	src="http://code.google.com/appengine/images/appengine-silver-120x30.gif" /></p>
</body>
</html>
