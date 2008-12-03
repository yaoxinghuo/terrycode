<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Pragma","No-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Cache-Control","no-cache"); 
%>

<jsp:include page="analytics.jsp"></jsp:include>

<table width="1000" border="0" cellspacing="0" bgcolor="#ffffff">
  <tr>
    <td height="72" width="580" align="left"><img src="resources/images/logo_index.gif"  border="0" ></td>
	<td align="right" valign="top">
		<s:if test="null!=#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_ADMIN_ID]">
			欢迎&nbsp;<s:property value="#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_NAME]" />&nbsp;|&nbsp;<a
					href="admin.action">设备预约系统后台</a>&nbsp;|&nbsp;<a
					href="equip.action">设备管理系统</a>&nbsp;|&nbsp;<a
					href="logout.action?type=0">退出</a>
		</s:if>
	</td>
	<td width="2"></td>
  </tr>
</table>
<table width="1000" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="45" bgcolor="#009966">
      <table width="960" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="88" align="center"><a href="<%=request.getContextPath() %>" class="menu">网站首页<br><font  class="menu2">home</font></a></td>
          <td width="10" background="resources/images/top_menu_line.gif"></td>
          <td width="88" align="center"><a href="notice!detail.action?id=" class="menu">实验室概况<br><font  class="menu2">introduce</font></a></td>
          <td width="10" background="resources/images/top_menu_line.gif"></td>
          <td width="88" align="center"><a href="notice!list.action?type=3" class="menu">管理制度<br><font  class="menu2">rules</font></a></td>
          <td width="10" background="resources/images/top_menu_line.gif"></td>
          <td width="88" align="center"><a href="notice!list.action?type=1" class="menu">通知公告<br><font  class="menu2">notice</font></a></td>
          <td width="10" background="resources/images/top_menu_line.gif"></td>
          <td width="88" align="center"><a href="notice!list.action?type=2" class="menu">设备使用公告<br><font  class="menu2">notice</font></a></td>
          <td width="10" background="resources/images/top_menu_line.gif"></td>
          <td width="88" align="center"><a href="download!list.action" class="menu">文档下载<br><font  class="menu2">download</font></a></td>
          <td width="10" background="resources/images/top_menu_line.gif"></td>
          <td width="110" align="center"><a href="app.action" class="menu">设备管理与使用系统<br><font  class="menu2">appointment</font></a></td>
          <td width="10" background="resources/images/top_menu_line.gif"></td>
          <td width="88" align="center"><a href="feedback!list.action" class="menu">投诉反馈<br><font  class="menu2">feedback</font></a></td>
       </tr>
      </table></td>
  </tr>
</table>
<table width="1000" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="211" background="resources/images/bg_banner.gif" align="right">
</td>
  </tr>
  <tr><td height="18"></td></tr>
</table>