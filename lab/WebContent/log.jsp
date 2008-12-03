<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
%>
<link href="resources/css/style.css" media="all" type="text/css"
	rel="stylesheet" />
<title>江南大学食品学院实验室--审批记录</title>
</head>
<body>
<div class="content_center"><jsp:include page="header.jsp"></jsp:include>
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
						<td height="26" align="center"><img
							src="resources/images/list_main_icon.gif" width="12" height="12">
						<font class="main"> 审批记录 </font></td>
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
						<td align="right" class="news"><a
							href="<%=request.getContextPath()%>" class="news">首页</a> &raquo;
						审批记录</td>
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
				<td valign="top"><br>
				<br>

				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td height="6" colspan="2"></td>
					</tr>
					<tr>
						<td><b>填写时间</b></td>
						<td><s:property value="input" /></td>
					</tr>
					<tr>
						<td><b>预约人</b></td>
						<td><s:property value="log.user_name" /></td>
					</tr>
					<tr>
						<td><b>导师</b></td>
						<td><s:property value="log.teacher" /></td>
					</tr>
					<tr>
						<td><b>设备</b></td>
						<td><s:property value="log.equip_name" /></td>
					</tr>
					<tr>
						<td><b>设备编号</b></td>
						<td><s:property value="log.equip_no" /></td>
					</tr>
					<tr>
						<td><b>预约起始时间</b></td>
						<td><s:property value="start" /></td>
					</tr>
					<tr>
						<td><b>预约结束时间</b></td>
						<td><s:property value="end" /></td>
					</tr>
					<tr>
						<td><b>实际实验时长</b></td>
						<td><s:property value="exp_time" />H</td>
					</tr>
					<tr>
						<td><b>样品</b></td>
						<td><s:property value="log.sample_name" /></td>
					</tr>
					<tr>
						<td><b>样品总数</b></td>
						<td><s:property value="log.sample_mount" /></td>
					</tr>
					<tr>
						<td><b>预约内容</b></td>
						<td><s:property value="log.content" /></td>
					</tr>
					<tr>
						<td><b>实验费用</b></td>
						<td><s:property value="log.compute_fee" />元</td>
					</tr>
					<tr>
						<td><b>实际费用</b></td>
						<td><s:property value="log.actual_fee" />元</td>
					</tr>
					<tr>
						<td><b>费用备注</b></td>
						<td><s:property value="log.remark" /></td>
					</tr>
					<tr>
						<td><b>审批情况</b></td>
						<td><s:if test="log.action==0"><font color="red">未批准</font></s:if> <s:elseif
						test="log.action==1">已批准</s:elseif> <s:elseif
						test="log.action==2">已删除</s:elseif><s:elseif
						test="log.action==3">改费用</s:elseif></td>
					</tr>
					<tr>
						<td><b>管理员附言</b></td>
						<td><s:property value="log.admin_remark" /></td>
					</tr>


				</table>

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
