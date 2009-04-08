<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="cn.edu.jiangnan.lab.data.service.comm.Constants"%>
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<title>江南大学食品学院设备预约系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	response.setHeader("Pragma","No-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Cache-Control","no-cache"); 
%>
<link rel="stylesheet" type="text/css" href="resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<style type="text/css">

.allow-float {
	clear: none !important;
}
.stop-float {
	clear: both !important;
}
.comp-left {
	float: left;padding:0 0 0 50px;
}

.app-left {
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

.equip {
	background-image: url(resources/icons/plugin.gif) !important;
}

.normal {
	background-image: url(resources/icons/mormal.png) !important;
}

.home {
	background-image: url(resources/icons/home.png ) !important;
}

.tabs {
	background-image: url(resources/icons/tabs.gif ) !important;
}

.search {
	background-image: url(resources/icons/search.png ) !important;
}

.refresh {
	background-image: url(resources/icons/refresh.png ) !important;
}

.table_refresh {
	background-image: url(resources/icons/table_refresh.png ) !important;
}

.shengpi {
	background-image: url(resources/icons/shengpi.png ) !important;
}

.yuyue {
	background-image: url(resources/icons/yuyue.png ) !important;
}

.undo {
	background-image: url(resources/icons/undo.png ) !important;
}

.view {
	background-image: url(resources/icons/view.png ) !important;
}

.edit {
	background-image: url(resources/icons/edit.png ) !important;
}

.detail {
	background-image: url(resources/icons/detail.png ) !important;
}

#tab-panel td {
	padding: 2px;
}
</style>
</head>
<body>

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

<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/Equip.js'></script>
<script type='text/javascript' src='dwr/interface/Account.js'></script>
<script type='text/javascript' src='dwr/interface/Book.js'></script>
<script type='text/javascript' src='dwr/interface/Message.js'></script>
<script type='text/javascript' src='dwr/interface/Notice.js'></script>

<script type="text/javascript" src="js/ext-base.js"></script>
<script type="text/javascript" src="js/ext-all.js"></script>
<script type="text/javascript" src="js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/ext-datetime.js"></script>
<script type="text/javascript" src="js/ext-extend.js"></script>
<script type='text/javascript' src='js/app/login.js'></script>
<script type="text/javascript" src="js/category.js"></script>
<script type="text/javascript" src="js/notice.js"></script>
<script type="text/javascript" src="js/app/userAccount.js"></script>
<script type="text/javascript" src="js/queueEquipManager.js"></script>
<script type="text/javascript" src="js/app/myBookManager.js"></script>
<script type="text/javascript" src="js/app/myAdminBookManager.js"></script>
<script type="text/javascript" src="js/app/userEquipList.js"></script>
<script type="text/javascript" src="js/app/userEquipSearchList.js"></script>
<script type="text/javascript" src="js/app/user.js"></script>

<jsp:include page="analytics.jsp"></jsp:include>

<script type="text/javascript">	
	var welcomeHTML = null;
	if(<%=(session.getAttribute(Constants.SESSION_ID)==null)%>)
		welcomeHTML = '<a href="javascript:;" onclick="showLoginWin();">登录</a>&nbsp;|&nbsp;<a href="<%=request.getContextPath() %>">首页</a>&nbsp;|&nbsp;<a href="equipview.action">设备查询系统</a>';
	else {
		//islogin = true;
		welcomeHTML = getWelcomeMessage("<%=session.getAttribute(Constants.SESSION_NAME)%>");
	}
</script>

<jsp:include page="message.jsp"></jsp:include>
<jsp:include page="alogin.jsp"></jsp:include>

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
				<td align="right" nowrap><font size='-1'>&nbsp;<span
					id="account_content"> </span></font></td>
			</tr>
			<tr>
				<td height="39"></td>
			</tr>
			<tr>
				<td align="right">
					<table align="right">
						<tr>
							<td align="right"><span id="my-yuyue" style="visibility:hidden"></span></td>
							<td width="1px;"></td>
							<td align="left"><span id="my-shengpi" style="visibility:hidden"></span></td>
							<td width="3px;"></td>
							<td><span style="visibility:hidden" id="theme-selector"></span></td>
							<td width="15px"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
		</td>

	</tr>
</table>

<div style="visibility: hidden;">

<div id="north-div"><div class="layout-title"><img src=resources/images/logo.gif /><span>食品学院&nbsp;</span>设备预约系统</div></div>
<div id="tabs"></div>
<div id="center"></div>
<div id="content"></div>
<div id="queue-eq-content"></div>
<div id="equip-queue-panel"></div>
<div id="rightClickCont"></div>
<div id="book-win"></div>
<div id="my-book-win"></div>
<div id="equip-edit-win"></div>
<div id="my-book-content"></div>
<div id="my-admin-book-content"></div>
<div id="useraccount-edit-win"></div>

<div id="introduceDiv"><img src="resources/images/indicator.gif"
	alt="Loading..."></img>使用说明加载中...</div>

<div id="messageDiv0"></div>

<div id="messageDiv1"></div>
</div>

<script type="text/javascript">
	$("account_content").innerHTML=welcomeHTML;
</script>

</body>
</html>