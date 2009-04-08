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
<style type="text/css">
.button {
	border: none;
	background: url(resources/images/up.png) no-repeat left top;
	padding: 2px 6px;
}

.button_hover {
	border: none;
	background: url('resources/images/down.png') no-repeat top left;
	padding: 2px 6px;
}
</style>
<script type="text/javascript">
function mouseOver(theTD, className) {
	oldClass = theTD.className;
	theTD.className = className;
}
function mouseOut(theTD, className) {
	theTD.className = className;
}
</script>
<title>江南大学食品学院实验室--投诉和建议反馈</title>
<s:head/>
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
							投诉和建议反馈
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
							class="news">首页</a> &raquo; 投诉和建议反馈</td>
					</tr>
					<tr align="center">
						<td colspan="2"><font class="main">填写投诉和反馈建议</font></td>
						<td></td>
					</tr>
					<tr>
						<td height="8"></td>
						<td>&nbsp;&nbsp;&nbsp;<s:property value="message" escape="false"/></td>
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
				<td valign="top" align="left" width="550px"><br>
				<br>

				<s:form action="feedbacksave" method="post" validate="true">
					<table align="left">
						<tr>
							<td><s:textfield name="no" label="学号/用户名" required="true" size="50"></s:textfield></td>
						</tr>
						<tr>
							<td><s:password name="password" label="登录密码" required="true"  size="50"></s:password></td>
						</tr>
						<tr>
							<td><s:textfield name="contact" label="联系方式" size="50"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textfield name="title" label="主题" required="true"  size="50"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textarea name="content" label="描述内容" required="true" cols="40" rows="10"></s:textarea></td>
						</tr>
						<tr><td align="right"><i>验证码:</i></td><td><input name="validate" label="验证码" required="true"></input><img border="0" style="vertical-align:middle;" src="image" alt="加载中..."></td></tr>
						<tr>
							<td></td><td align="right"><INPUT type="submit" value="提交" class="button" onMouseOver="mouseOver(this,'button_hover')"
					onMouseOut="mouseOut(this,'button')"/>&nbsp;&nbsp;&nbsp;<INPUT type="reset" value="重置" class="button" onMouseOver="mouseOver(this,'button_hover')"
					onMouseOut="mouseOut(this,'button')"/></td>
						</tr>
						
					</table>
				</s:form>
	
				</td>

			</tr>
		</table>
		</td>

	</tr>
	<tr>
		<td bgcolor=#dedede height="1" colspan="3"></td>
	</tr>
</table>
<jsp:include page="footer.jsp"></jsp:include></div>
</body>
</html>
