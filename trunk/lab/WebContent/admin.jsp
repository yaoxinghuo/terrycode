<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/lab" prefix="lab"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	response.setHeader("Pragma","No-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Cache-Control","no-cache"); 
%>
<link rel="shortcut icon" href="favicon.ico" />
<title>江南大学食品学院设备预约系统后台</title>
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
}  /* 允许该元素浮动 */
.stop-float {
	clear: both !important;
}  /* 阻止该元素浮动 */
.notice-left {
	float: left;
	padding: 0 0 0 10px;
}

.notice-left0 {
	float: left;
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
	padding: 0 0 0 30px;
}

.user {
	background-image: url(resources/icons/user.gif) !important;
}

.useradd {
	background-image: url(resources/icons/user_add.gif) !important;
}

.userdel {
	background-image: url(resources/icons/user_delete.gif) !important;
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

.undo {
	background-image: url(resources/icons/undo.png ) !important;
}

.refresh {
	background-image: url(resources/icons/refresh.png ) !important;
}

.add {
	background-image: url(resources/icons/add.gif) !important;
}

.option {
	background-image: url(resources/icons/plugin.gif) !important;
}

.pass {
	background-image: url(resources/icons/pass.png) !important;
}

.liuyan {
	background-image: url(resources/icons/liuyan.png) !important;
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

.notice {
	background-image: url(resources/icons/notice.png) !important;
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

#main-panel td {
	padding: 2px;
}
</style>

</head>
<body>
<lab:authority />

<div id="loading_div" style="visibility: visible;">
	<table align="center">
		<tr><td height="200"></td></tr>
	</table>
	<table align="center" style="background:#ff7e02;border:2;solid:#000000;color: #416f02;">
		<tr height="25">
			<td bgColor="#FFFFFF"><span style="padding: 6px;"><img style='vertical-align:middle;width:16px;height:16px;' src='resources/images/indicator.gif' alt='Loading'/>&nbsp;页面加载中,请稍候...</span></td>
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
		<div style="z-index: 10; position: absolute; right: 2px; visibility: hidden;" id="account_div">
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
					href="<%=request.getContextPath() %>">首页</a>&nbsp;|&nbsp;<a
					href="equip.action">设备管理系统</a>&nbsp;|&nbsp;<a
					href="logout.action?type=1">退出</a> </span></font></td>
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

<jsp:include page="analytics.jsp"></jsp:include>

<script type="text/javascript">
	var isAdmin = false;
	if(<%=(session.getAttribute(cn.edu.jiangnan.lab.data.service.comm.Constants.SESSION_SUPER_ADMIN)!=null)%>)
		isAdmin = true;
	var username = '<%=(String)session.getAttribute(cn.edu.jiangnan.lab.data.service.comm.Constants.SESSION_NAME)%>';
	var browseServerWin=null;
	var browseServerContentPath = "<%=request.getContextPath() %>";
	var browseServerUrl = browseServerContentPath +
		'/fckeditor/editor/filemanager/browser/default/browser.html'+
		'?Connector='+browseServerContentPath+
		'/fckeditor/editor/filemanager/connectors/php/connector.php';
	function openBrowseServerWin(){
		if(!isAdmin) {
			alertOnlyAdminDo();
			return;
		}
		browseServerWin=window.open(browseServerUrl,'FCKBrowseWindow',null);
	}
	function alertOnlyAdminDo(){
		Ext.Msg.alert("未授权的操作", "只有权限为‘系统管理员’的用户才能进行该操作，如果您确定已授权，请注销后再登录！");
	}
</script>

<div style="visibility: hidden;">

<div id="menus">
<div id="equipMenus">
<ul class="munuList">
	<li><span id="pr_equipManager" onclick="onClickMenuItem(this)">待批准预约</span></li>
	<li><span id="af_equipManager" onclick="onClickMenuItem(this)">已批准预约</span></li>
	<li><span id="equipManager" onclick="onClickMenuItem(this)">我的设备信息</span></li>
	<li><span id="all_equipManager" onclick="onClickMenuItem(this)">所有设备信息</span></li>
</ul>
</div>
<div id="logMenus">
<ul class="munuList">
	<li><span id="user_logManager" onclick="onClickMenuItem(this)">预约日志管理</span></li>
	<li><span id="admin_logManager" onclick="onClickMenuItem(this)">审批日志管理</span></li>
</ul>
</div>
<div id="userMenus">
<ul class="munuList">
	<li><span id="userManager" onclick="onClickMenuItem(this)">用户信息</span></li>
	<li><span id="adminManager" onclick="onClickMenuItem(this)">管理员信息</span></li>
	<li><span id="userSearch" onclick="onClickMenuItem(this)">搜索用户</span></li>
</ul>
</div>

<div id="noticeMenus">
<ul class="munuList">
	<li><span id="noticeManager" onclick="onClickMenuItem(this)">公告管理</span></li>
	<li><span id="feedbackManager" onclick="onClickMenuItem(this)">用户反馈管理</span></li>
	<li><span id="browseServer" onclick="openBrowseServerWin();">浏览/上传文件至服务器</span></li>
	<li><span id="deleteServer" onclick="deleteFileFromServer();">从服务器删除文件(夹)</span></li>
</ul>
</div>
</div>

<div id="center"></div>

<div id="north-div">
<div class="layout-title"><img src='resources/images/logo.gif' /><span>食品学院&nbsp;</span>设备预约系统后台</div>
</div>
<div id="af-eq-content"></div>
<div id="pr-eq-content"></div>
<div id="user-content"></div>
<div id="account-upload-win"></div>
<div id="fee-win"></div>
<div id="admin-content"></div>
<div id="search-content"></div>
<div id="eq-content"></div>
<div id="all-eq-content"></div>
<div id="queue-eq-content"></div>
<div id="user-log-content"></div>
<div id="admin-log-content"></div>
<div id="notice-content"></div>
<div id="feedback-content"></div>
<div id="equip-edit-win"></div>
<div id="user-account-add-win"></div>
<div id="user-account-edit-win"></div>
<div id="admin-account-add-win"></div>
<div id="admin-account-edit-win"></div>
<div id="search-edit-win"></div>
<div id="notice-add-win"></div>
<div id="notice-edit-win"></div>
<div id="file-delete-win"></div>
<div id="feedback-edit-win"></div>
<div id="myaccount-edit-win"></div>

<div id="introduceDiv"><img src="resources/images/indicator.gif"
	alt="Loading..."></img>使用说明加载中...</div>

<div id="messageDiv0"></div>

<div id="messageDiv1"></div>
</div>

<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/Account.js'></script>
<script type='text/javascript' src='dwr/interface/Book.js'></script>
<script type='text/javascript' src='dwr/interface/Equip.js'></script>
<script type='text/javascript' src='dwr/interface/Message.js'></script>
<script type='text/javascript' src='dwr/interface/Notice.js'></script>

<script type="text/javascript" src="js/ext-base.js"></script>
<script type="text/javascript" src="js/ext-all.js"></script>
<script type="text/javascript" src="js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/ext-extend.js"></script>
<script type="text/javascript" src="js/category.js"></script>
<script type="text/javascript" src="js/admin/prEquipManager.js"></script>
<script type="text/javascript" src="js/admin/afEquipManager.js"></script>
<script type="text/javascript" src="js/admin/equipManager.js"></script>
<script type="text/javascript" src="js/admin/allEquipManager.js"></script>
<script type="text/javascript" src="js/queueEquipManager.js"></script>
<script type="text/javascript" src="js/admin/userManager.js"></script>
<script type="text/javascript" src="js/admin/adminManager.js"></script>
<script type="text/javascript" src="js/admin/userSearch.js"></script>
<script type="text/javascript" src="js/adminAccount.js"></script>
<script type="text/javascript" src="js/admin/adminLogManager.js"></script>
<script type="text/javascript" src="js/admin/userLogManager.js"></script>
<script type="text/javascript" src="js/notice.js"></script>
<script type="text/javascript" src="js/admin/noticeManager.js"></script>
<script type="text/javascript" src="js/admin/feedbackManager.js"></script>
<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
<script type="text/javascript" src="js/admin/manager.js"></script>

</body>
</html>
