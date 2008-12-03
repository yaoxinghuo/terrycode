<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/lab" prefix="lab"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<title>江南大学食品学院设备查询系统</title>
<%
	response.setHeader("Pragma","No-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Cache-Control","no-cache"); 
%>
<link rel="stylesheet" type="text/css" href="resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<style type="text/css">
.x-panel-body p {
	margin: 5px;
}

.x-column-layout-ct .x-panel {
	margin-bottom: 5px;
}

.x-column-layout-ct .x-panel-dd-spacer {
	margin-bottom: 5px;
}

.allow-float {
	clear: none !important;
}
.stop-float {
	clear: both !important;
}
.comp-left {
	float: left;padding:0 0 0 10px;
}

.comp-left0 {
	float: left;
}

.comp-left1 {
	float: left;
	padding: 0 0 0 70px;
}

.comp-left2 {
	float: left;
	padding: 0 0 0 30px;
}

.unit {
	background-image: url(resources/icons/application_view_list.png)
		!important;
}

.equip {
	background-image: url(resources/icons/plugin.gif) !important;
}

.tabs {
	background-image: url(resources/icons/tabs.gif ) !important;
}

.home {
	background-image: url(resources/icons/home.png ) !important;
}

.view {
	background-image: url(resources/icons/view.png ) !important;
}

.refresh {
	background-image: url(resources/icons/refresh.png ) !important;
}

.save {
	background-image: url(resources/icons/save.gif) !important;
}

.detail {
	background-image: url(resources/icons/detail.png) !important;
}

.modify {
	background-image: url(resources/icons/modify.png) !important;
}

.search {
	background-image: url(resources/icons/search.png ) !important;
}

.munuList {
	list-style: square;
	padding-left: 30px;
	margin-top: 10px;
	color: #000000;
	font-size: 12px;
	text-decoration: underline;
	cursor: hand;
}
</style>

</head>
<body>

<div id="loading_div" style="visibility: visible;">
	<table align="center">
		<tr><td height="200"></td></tr>
	</table>
	<table align="center" style="background:#00B2BF;border:2;solid:#000000;color: #426EB4;">
		<tr height="25">
			<td bgColor="#FFFAB3"><span style="padding: 6px;"><img style='vertical-align:middle;width:16px;height:16px;' src='resources/images/indicator.gif' alt='Loading'/>&nbsp;页面加载中,请稍候...</span></td>
		</tr>
	</table>
</div>

<jsp:include page="message.jsp"></jsp:include>

<table width="100%">
	<tr>
		<td align="left">
		<div style="z-index: 1000; position: absolute;">
		<table align="left" border='0' cellspacing='0' cellpadding='0'>
			<tr>
				<td align="left" nowrap></td>
			</tr>
		</table>
		</div>
		</td>

		<td>
		<div style="z-index: 10; position: absolute; right:2px; visibility: hidden;" id="account_div">
		<table align="right" border='0' cellspacing='0' cellpadding='0'>
			<tr>
				<td align="center" nowrap><font size='-1'>&nbsp;<span
					id="account_content"> 欢迎 <s:property value="#session._userName" default="访客"/> |&nbsp;<a
					href="<%=request.getContextPath() %>">转到首页</a>&nbsp;|&nbsp;<a
					href="app.action">设备预约系统</a>
				</span></font></td>
			</tr>
			<tr>
				<td height="40"></td>
			</tr>
			<tr align="right">
				<td><div style="margin-right:15px;visibility:hidden" id="theme-selector"></div></td>
			</tr>
		</table>
		</div>
		</td>

	</tr>
</table>

<div style="visibility: hidden;">

<div id="center"></div>

<div id="north-div"><div class="layout-title"><img src=resources/images/logo.gif /><span>食品学院&nbsp;</span>设备查询系统</div></div>
<div id="myaccount-edit-win"></div>
<div id="eq-content"></div>
<div id="equip-edit-win"></div>
<div id="eq-search-content"></div>
<div id="eq-category-content1"></div>
<div id="eq-category-content2"></div>
<div id="eq-category-content3"></div>
<div id="eq-category-content4"></div>
<div id="eq-category-content5"></div>
<div id="home">
	<br/><h2 style='font-size:small' align='center'>江南大学食品学院设备查询系统 使用简要说明</h2><br/>
	<img src="resources/icons/note.png">点击“设备查询”--“所有设备”： 查看在线登记的所有设备，每页显示20条记录，请按“向前”或“向后”按钮翻页查看;<br/><br/>
	&nbsp;点击单条设备记录的鼠标右键，可以调出“查看详情”菜单，点击查看设备详细信息；<br/><br/>
	&nbsp;详细信息中“是否公开”显示为“是”且“状态”为“接受预约”的设备，才能在<a href='app.action'>设备预约系统</a>接受学生预约；<br/><br/>
	<img src="resources/icons/note.png">点击“设备管理”--“搜索设备”： 工具栏输入关键词搜索设备。默认搜索的是设备的名字，如需要搜索其他的字段，请选择工具栏的相应字段;<br/><br/>
	&nbsp;搜索后的操作同上；<br/><br/>
	<img src="resources/icons/note.png">本系统不提供修改新增和删除设备的功能；<br/><br/>
	<img src="resources/icons/note.png"><b>若使用中画面出现错误，请尝试清空浏览器缓存，并刷新整个页面；</b><br/><br/>
	<img src="resources/icons/note.png">有任何意见和建议请到首页<a href='form_feedback.jsp' target='_blank'>投诉与反馈</a>，谢谢！<br/>
</div>

</div>

<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/Account.js'></script>
<script type='text/javascript' src='dwr/interface/Equip.js'></script>

<script type="text/javascript" src="js/ext-base.js"></script>
<script type="text/javascript" src="js/ext-all.js"></script>
<script type="text/javascript" src="js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/ext-extend.js"></script>
<script type="text/javascript" src="js/equip/equipManager_view.js"></script>
<script type="text/javascript" src="js/equip/equipSearchList_view.js"></script>
<script type="text/javascript" src="js/equip/equipCategoryList_view.js"></script>
<script type="text/javascript" src="js/category.js"></script>
<script type="text/javascript" src="js/equip/equip_view.js"></script>

<jsp:include page="analytics.jsp"></jsp:include>

</body>
</html>
