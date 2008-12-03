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
<title>江南大学食品学院实验室--文档下载</title>
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
							文档下载
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
						<td align="right" class="news"><a href="index.action"
							class="news">首页</a> &raquo; 文档下载</td>
					</tr>
					<tr>
						<td colspan="2"><font class="main"></font></td>
						<td></td>
					</tr>
					<tr>
						<td height="8"></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
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
				<td width="70"></td>
				<td valign="top">

				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="6"></td>
						<td>
							<table width='100%' border='0' cellspacing='0' cellpadding='0'>
								<tr><td height='8'></td></tr>
								
								<s:iterator value="files">
									<tr><td height='26'>
										<s:url action="download" id="fname">
											<s:param name="fileName">
												<s:property value='name'/>
											</s:param>
										</s:url>
										<img src='resources/images/list_icon.gif' width='7' height='7'>
										&nbsp;<s:a href="%{fname}"><s:property value="name"/></s:a>
										</td><td width='80' align='right'><font class='eng'><s:property value="size"/></font>
									</td></tr>
									<tr><td height='1' background='resources/images/index_news_line.gif' colspan='2'></td></tr>
								</s:iterator>
								
							</table>
						</td>
					</tr>
				</table>
				
				</td>

			</tr>
			
		</table>
		</td>	

	</tr>
	<tr>
		<td bgcolor="#dedede" height="1" colspan="2"></td>
	</tr>
</table>
<jsp:include page="footer.jsp"></jsp:include></div>
</body>
</html>
