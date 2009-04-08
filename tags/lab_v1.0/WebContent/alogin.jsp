<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
.input {
	border: 1px solid #006;
	background: #ffc;
}

.input_hover {
	border: 1px solid #f00;
	background: #ff6;
}

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

#login_box{ 
	position:absolute;  
	width:350px; 
	height:200px;
	filter:dropshadow(color=#666666,offx=3,offy=3,positive=2); 
	z-index:1002; 
	visibility:hidden
}
#login_mask{ 
	position:absolute; 
	top:0; 
	left:0; 
	background:#c9cacc; 
	filter:ALPHA(opacity=40); 
	opacity:0.4;
	z-index:1001; 
	visibility:hidden
}
</style>

<div id="login_box" style="FONT-SIZE: 12px;">
<center>
<table border="0" width="300" cellspacing="1" cellpadding="0"
	bgcolor="#0000C0">
	<tr>
		<td align="center"><b>&nbsp;<span style="color:#EFFFFF">设备预约系统登录</span></b></td>
		<td align="right"><img src="resources/images/default/qtip/close.gif" style="cursor:pointer;width:15px;height:15px;"
						onclick="hideLoginBox();" alt="关闭"/></td>
	</tr>
	<tr>
		<td height="130" bgcolor="#FFFFFF" colspan="2" valign="top"
			style="padding: 10px;" colspan="2">
			<form id="login_form" onkeydown="if(event.keyCode==13){return false;}">
			<table align="center">
				<tr>
					<td colspan="2" align="center"><span id="login_message"></span></td>
				</tr>
				<tr>
					<td align="right">学号/用户名*：</td>
					<td align="left"><input type="text" name="no" style="width: 150px"
						class="input"></td>
				</tr>
				<tr>
					<td align="right">密码*：</td>
					<td align="left"><input type="password" name="password"
						style="width: 150px" class="input"></td>
				</tr>
				<tr>
					<td align="right">验证码*：</td>
					<td align="left"><input type="text" name="validate" style="width: 85px"
						class="input">&nbsp;&nbsp;<img id="validate_img" style="vertical-align:middle;" border=0 src="image" alt="加载中..."></td>
				</tr>
				<tr>
					<td align="right"><input type="checkbox" name="saveCookie" id="cookie_saveCookie"
						 onclick="rememberSaveCookie();" checked></td>
					<td align="left">在本机记住我的登录信息</td>
				</tr>
				<tr align="center">
					<td><input type="button" value="登录" onclick="userlogin();"
						class="button" onMouseOver="mouseOver(this,'button_hover')"
						onMouseOut="mouseOut(this,'button')" /></td>
					<td><input type="reset" value="重置" class="button"
						onMouseOver="mouseOver(this,'button_hover')"
						onMouseOut="mouseOut(this,'button')" /></td>
				</tr>
			</table>
		</form>
	</tr>
</table>
</center>
</div>

<div id="login_mask"></div>
