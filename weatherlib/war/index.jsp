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
<link rel="stylesheet" type="text/css" href="css/jNice.css" />

<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/flexigrid.pack.js"></script>
<script type="text/javascript" src="js/thickbox.js"></script>
<script type="text/javascript" src="js/jquery.jNice.js"></script>

<title>天气预报邮件定制机器人</title>
<%
	UserService userService = UserServiceFactory.getUserService();
%>
</head>
<body topmargin=3 marginheight=3>
<textarea id=csi style="display: none"></textarea>
<div id=gbar><b class=gb1>天气预报</b> <a
	href="http://fetion.xinghuo.org.ru/" target="_blank" class=gb1>网页飞信</a>
<a href="http://websms.org.ru/" target="_blank" class=gb1>GV网页短信</a></div>
<div id=guser width=100%><%=userService.isUserLoggedIn() ? userService
					.getCurrentUser().getEmail()
					+ " | " : ""%><a
	href="<%=userService.isUserLoggedIn() ? userService
					.createLogoutURL("/index.jsp") : userService
					.createLoginURL("/index.jsp")%>"><%=userService.isUserLoggedIn() ? "退出" : "登录"%></a>
</div>
<div class=gbh style="left: 0"></div>
<div class=gbh style="right: 0"></div>


<div class="bborderx">
<table id="flex1" style="display: none"></table>
</div>

<%
	if (userService.isUserLoggedIn()) {
%>
<script type="text/javascript" src="js/weather.js"></script>
<%
	}
%>

<div style="display: none;"><input id="newSchedule"
	alt="#TB_inline?height=160&width=360&inlineId=hiddenModalContent&;modal=false"
	title="<b>新建天气预报提醒</b>" class="thickbox" type="button" value="Show" /></div>

<div id="hiddenModalContent" style="visibility: hidden;">
<form class="jNice">
<table border="0">
	<tr>
		<td style="width: 100px;">发送时间：</td>
		<td align="left" style="width: 20px;"><select name="sdate_hour" id="sdate_hour"
			style="width: 70px;">
			<%
				for (int i = 0; i < 24; i++) {
			%>
			<option <%=i == 20 ? "selected" : ""%> value="<%=i < 10 ? ("0" + i) : i%>"><%=i < 10 ? ("0" + i) : i%>
			时</option>
			<%
				}
			%>
		</select></td><td align="left"><select name="sdate_minute" id="sdate_minute"
			style="width: 70px;">
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
		<td>接受邮箱：</td>
		<td colspan="2"><input name="email" id="email" value="@139.com" style="width:180px;"/></td>
	</tr>
	<tr>
		<td>定制的城市：</td>
		<td colspan="2"><input name="city" id="city" style="width:180px;"/></td>
	</tr>
	<tr>
		<td>状态：</td>
		<td colspan="2"><select name="type" id="type" style="width:150px;">
			<option value="1" selected="selected">天气内容放正文</option>
			<option value="2">天气内容放主题</option>
			<option value="0">暂时停用</option>
		</select></td>
	</tr>
	<tr>
		<td>备注：</td>
		<td colspan="2"><input name="remark" id="remark" style="width:180px;"/></td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2"><input type="button" value="保存"
			id="newScheduleSave" /></td>
	</tr>
</table>
</form>
</div>

<p align="center"><font size="6">天气预报邮件定制机器人<br />
</font></p>
<p align="left"><strong><font size="4">功能：</font></strong><br />
&nbsp; &nbsp; 定时每天向指定邮箱发送天气预报邮件，可借助手机邮箱（<a href="http://mail.139.com/"
	target="_blank">139邮箱</a>，<a href="http://mail.wo.com.cn"
	target="_blank">联通邮箱</a>，<a href="http://www.189.cn/webmail/"
	target="_blank">189邮箱</a>）实现手机天气预报定制<br />
<strong><font size="4">特点：<br />
</font></strong>&nbsp; &nbsp; 简单，直观，通过Gmail(或者Gtalk)直接发送命令。<br />
&nbsp; &nbsp; 一个Gtalk帐号可定制多个城市的天气预报或多个好友的邮箱<br />
&nbsp; &nbsp; 可自定义每天发送时间<br />
&nbsp; &nbsp; 可发送3天的天气情况<br />
&nbsp; &nbsp; 定时期限无限长（只要gae没倒闭）<br />
&nbsp; &nbsp; 只适合习惯用gmail的人群<br />
<strong><font size="4">使用方法：</font></strong><br />
&nbsp; &nbsp; 用Gmail邀请“机器人”<a href="mailto:weatherlib@appspot.com"><font
	color="#0000ff">weatherlib@appspot.com</font></a>，向其发送定时命令即可<br />
<strong><font size="4">命令说明：</font></strong>所有操作机器人均有提示<br />
&nbsp; &nbsp; 基本格式：<font face="楷体_GB2312 "><font color="green">每天发送时间[空格]手机号或邮箱[空格]城市名称</font></font>，例如：<font
	color="seagreen">20:00 13812345678@139.com 上海</font><br />
&nbsp; &nbsp; 或者直接输入手机号，默认会发往手机号对应的139邮箱，如：<font color="seagreen">20:00
13812345678 上海</font><br />
&nbsp; &nbsp; 直接输入tq+城市可查询该城市的天气，直接由机器人输出结果，如发送命令：tq上海<br />
<strong><font size="4">其他说明：<br />
</font></strong>&nbsp; &nbsp; 1.定时只能精确到5分钟<br />
&nbsp; &nbsp; 2.暂时只能制定10条定时命令，有需要可以联系作者<br />
&nbsp; &nbsp; 3.天气数据来自Google，Google每天<b>8:00</b>和<b>17:00</b>点更新数据，请把提醒时间适当设置在这两个时间点之后<br />
&nbsp; &nbsp; <font color='red'>4.因预报3天，“天气内容”较长所以放在正文中，请在139邮箱中设置为“长短信”的手机接收方式</font><br />
&nbsp; &nbsp; 5.输入list查看定时列表，可根据提示进行管理操作<br />
&nbsp; &nbsp; 6.输入account查看当前账户，可根据提示修改邮件发送人昵称<br />
&nbsp; &nbsp; 7.139邮箱地址可以省略为手机号<br />
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
