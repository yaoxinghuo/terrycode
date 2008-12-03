<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	response.setHeader("Pragma","No-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Cache-Control","no-cache"); 
%>
<link href="resources/css/style.css" media="all" type="text/css"
	rel="stylesheet" />
<title>江南大学食品学院实验室--公告</title>
</head>
<body>
<div class="content_center">
<jsp:include page="header.jsp"></jsp:include>
<table width="960" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="223" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center"><br>
				<br>
				<table border="0" cellpadding="0" cellspacing="0" width="158">
					<tr>
						<td height="3"></td>
					</tr>
					<tr>
						<td height="26" align="center"><img src="resources/images/list_main_icon.gif" width="12"
							height="12"> <font class="main">
							<s:if test="type==1">通知公告</s:if>
							<s:elseif test="type==2">设备使用公告</s:elseif>
							<s:elseif test="type==3">管理制度</s:elseif>
							<s:else>实验室概况</s:else>
							</font></td>
					</tr>
					<tr>
						<td height="3"></td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="24"><img src="resources/images/left_img1.gif"
							width="223" height="431" border="0"></td>
					</tr>
				</table>
				<br>
				</td>
			</tr>
		</table>
		</td>
		<td width="737" valign="top" align="center" bgcolor="#ffffff">

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="24"><img src="resources/images/list_main_left.gif"
					width="24" height="24"></td>
				<td width="689"></td>
				<td width="24"><img src="resources/images/list_main_right.gif"
					width="24" height="24"></td>
			</tr>
			<tr>
				<td></td>
				<td>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="2"></td>
						<td align="right" class="news"><a href="<%=request.getContextPath() %>"
							class="news">首页</a> &raquo; <s:if test="type==1">通知公告</s:if>
							<s:elseif test="type==2">设备使用公告</s:elseif>
							<s:elseif test="type==3">管理制度</s:elseif>
							<s:else>实验室概况</s:else>
					</tr>
					<tr align="center">
						<td colspan="2"><font class="main"><s:property value="title" escape="false"/></font></td>
						<td></td>
					</tr>
					<tr align="center">
						<td height="8"></td>
						<td>&nbsp;&nbsp;&nbsp;<s:property value="date"/></td>
						<td></td>
					</tr>

					<tr>
						<td height="1" background="resources/images/list_main_line.gif"
							colspan="3"></td>
					</tr>
				</table>
				</td>
				<td></td>
			</tr>
			<tr align="left">
				<td></td>
				<td valign="top" class="underline"><br>
				<br>

				<table width="96%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="6"></td>
					</tr>
				</table>
				<s:property value="content" escape="false"/>
				</td>

			</tr>
		</table>
		</td>

	</tr>
	<tr>
		<td bgcolor=#dedede height="1" colspan="2"></td>
	</tr>
</table>
<jsp:include page="footer.jsp"></jsp:include></div>
</body>
</html>
