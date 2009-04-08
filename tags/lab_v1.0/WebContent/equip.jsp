<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/lab" prefix="lab"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<title>江南大学食品学院设备管理系统</title>
<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
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
	float: left;
	padding: 0 0 0 10px;
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
	padding: 0 0 0 33px;
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

.add {
	background-image: url(resources/icons/add.gif) !important;
}

.remove {
	background-image: url(resources/icons/delete.gif) !important;
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

.excel {
	background-image: url(resources/icons/excel.png ) !important;
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
<lab:authority />

<div id="loading_div" style="visibility: visible;">
<table align="center">
	<tr>
		<td height="200"></td>
	</tr>
</table>
<table align="center"
	style="background: #00B2BF; border: 2; solid: #000000; color: #426EB4;">
	<tr height="25">
		<td bgColor="#FFFAB3"><span style="padding: 6px;"><img
			style='vertical-align:middle;width: 16px; height: 16px;'
			src='resources/images/indicator.gif' alt='Loading' />&nbsp;页面加载中,请稍候...</span></td>
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
		<div
			style="z-index: 10; position: absolute; right: 2px; visibility: hidden;"
			id="account_div">
		<table align="right" border='0' cellspacing='0' cellpadding='0'>
			<tr>
				<td align="center" nowrap><font size='-1'>&nbsp;<span
					id="account_content"><s:property
					value="#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_NAME]" />&nbsp;<s:if
					test="null==#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_SUPER_ADMIN]">设备管理员</s:if><s:else>系统管理员</s:else><s:if
					test="0==#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_GROUP_TYPE]">(所有设备)</s:if><s:elseif
					test="1==#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_GROUP_TYPE]">(工艺大厅设备)</s:elseif><s:elseif
					test="2==#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_GROUP_TYPE]">(物化设备)</s:elseif><s:elseif
					test="3==#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_GROUP_TYPE]">(方向托管设备)</s:elseif><s:else></s:else>&nbsp;|&nbsp;<a
					href="#" onclick="initAdminAccount();return false;" ; />我的帐户</a>&nbsp;|&nbsp;<a
					href="<%=request.getContextPath()%>">首页</a>&nbsp;|&nbsp;<a
					href="admin.action">设备预约系统后台</a>&nbsp;|&nbsp;<a
					href="logout.action?type=2">退出</a> </span></font></td>
			</tr>
			<tr>
				<td height="40"></td>
			</tr>
			<tr align="right">
				<td>
				<div style="margin-right: 15px; visibility: hidden"
					id="theme-selector"></div>
				</td>
			</tr>
		</table>
		</div>
		</td>

	</tr>
</table>

<div style="visibility: hidden;">

<div id="center"></div>

<div id="north-div">
<div class="layout-title"><img src=resources/images/logo.gif /><span>食品学院&nbsp;</span>设备管理系统</div>
</div>
<div id="myaccount-edit-win"></div>
<div id="eq-content"></div>
<div id="equip-edit-win"></div>
<div id="equip-add-win"></div>
<div id="equip-upload-win"></div>
<div id="image-upload-win"></div>
<div id="eq-search-content"></div>
<div id="eq-category-content1"></div>
<div id="eq-category-content2"></div>
<div id="eq-category-content3"></div>
<div id="eq-category-content4"></div>
<div id="eq-category-content5"></div>
<div id="home"><br />
<h2 style='font-size: small' align='center'>江南大学食品学院设备管理系统 使用简要说明</h2>
<br />
<img src="resources/icons/note.png">点击“设备管理”--“所有设备”：
查看在线登记的所有设备，每页显示20条记录，请按“向前”或“向后”按钮翻页查看；<br />
<br />
&nbsp;点击单条设备记录的鼠标右键，可以调出“查看、修改设备记录”和“删除设备”的菜单，也可以选择多条设备记录，按工具栏的按钮进行批量删除；<br />
<br />
&nbsp;详细信息中“是否公开”显示为“是”且“状态”为“接受预约”的设备，才能在《设备预约系统》接受学生预约；
&nbsp;点击工具栏的“新增设备”进行设备录入操作；<br />
<br />
&nbsp;设备的修改、新增和删除功能只有管理员才能操作，普通的老师、负责人只能进行查看操作；<br />
<br />
<img src="resources/icons/note.png">点击“设备管理”--“搜索设备”：
工具栏输入关键词搜索设备。默认搜索的是设备的名字，如需要搜索其他的字段，请选择工具栏的相应字段；<br />
<br />
&nbsp;搜索后的操作同上。<br />
<br />
<img src="resources/icons/note.png"><b>若使用中画面出现错误，请尝试清空浏览器缓存，并刷新整个页面；</b>
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
<script type="text/javascript" src="js/equip/equipManager.js"></script>
<script type="text/javascript" src="js/equip/equipSearchList.js"></script>
<script type="text/javascript" src="js/equip/equipCategoryList.js"></script>
<script type="text/javascript" src="js/adminAccount.js"></script>
<script type="text/javascript" src="js/category.js"></script>
<script type="text/javascript" src="js/equip/equip.js"></script>

<script type="text/javascript">
var username = '<%=(String)session.getAttribute(cn.edu.jiangnan.lab.data.service.comm.Constants.SESSION_NAME)%>';
</script>

<jsp:include page="analytics.jsp"></jsp:include>

</body>
</html>
