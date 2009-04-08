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
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-cache");
%>
</head>
<body>
<s:if test="equip==null">
	<i>对不起，加载设备详情出现错误！</i>
</s:if>
<s:else>
	<table>
		<tr>
			<td colspan="2">
			<h1 style="font-size: 16px; text-align: center;"><s:property
				value="equip.name" /></h1>
			</td>
		</tr>
		<tr>
			<td><s:if test="equip.image!=null">
				<img src='resources/equip/<s:property value="equip.image"/>'
					width="227" height="170" />
			</s:if> <s:else>
				<i>暂无设备图片信息</i>
			</s:else></td>
			<td valign="top" style="padding-left: 30px;">
			<fieldset id="set1"><legend>基本信息</legend>
			<table>
				<tr>
					<td><b>编号：</b><s:property value="equip.no" /></td>
					<td><b>型号：</b><s:property value="equip.model" /></td>
				</tr>
				<tr>
					<td><b>单价：</b><s:property value="equip.price" /></td>
					<td><b>设备类别：</b> <s:if test="equip.type==1">工艺大厅设备</s:if> <s:elseif
						test="equip.type==2">物化设备</s:elseif> <s:elseif
						test="equip.type==3">方向托管设备</s:elseif> <s:else>其他设备</s:else></td>
				</tr>
				<tr>
					<td><b>存放位置：</b><s:property value="equip.location" /></td>
					<td><b>国别：</b><s:property value="equip.country" /></td>
				</tr>
				<tr>
					<td colspan="2"><b>生产厂商：</b><s:property value="equip.company" /></td>
				</tr>
				<tr>
					<td><b>出厂日期：</b><s:property value="equip.year1" /></td>
					<td><b>购置日期：</b><s:property value="equip.year2" /></td>
				</tr>
				<tr>
					<td><b>负责人：</b><s:property value="equip.admin" /></td>
					<td><b>联系方式：</b> <s:if
						test="null!=#session[@cn.edu.jiangnan.lab.data.service.comm.Constants@SESSION_ID]">
						<s:property value="equip.mobile" />
					</s:if> <s:else>
						<i>您未登录，不能查看联系方式</i>
					</s:else></td>
				</tr>
				<tr>
					<td><b>是否共用：</b> <s:if test="equip.pub==true">
						<font color="green">是</font>
					</s:if> <s:else>
						<font color="red">否</font>
					</s:else>
					<td><b>是否收费：</b><s:if test="equip.charge==true">
						<font color="green">是</font>
					</s:if> <s:else>
						<font color="red">否</font>
					</s:else></td>
				</tr>
				<tr>
					<td><b>使用状态：</b><s:if test="equip.status==true">
						<font color="green">接受预约</font>
					</s:if> <s:else>
						<font color="red">暂不可用</font>
					</s:else></td>
					<td><b>预约冲突：</b><s:if test="equip.checkd==true">检查</s:if> <s:else>不检查</s:else></td>
				</tr>
				<tr>
					<td><b>预约周期：</b><s:property value="appd" /></td>
					<td><b>预约时间：</b><s:property value="equip.appt1" />--<s:property
						value="equip.appt2" /></td>
				</tr>
				<tr>
					<td><b>样品处理时间：</b><s:property value="equip.sampletime" />分/样品</td>
					<td><b>实验费用：</b><s:property value="equip.fee" />元/分钟</td>
				</tr>

			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	<div style="line-height: 180%; width: 700px;"><b>主要性能参数：</b><br />
	<s:property value="equip.specification" /><br />
	<b>操作规程：</b><br />
	<s:property value="equip.caution" /><br />
	<b>收费方式：</b><br />
	<s:property value="equip.remark" /><br />
	</div>
</s:else>
</body>
</html>