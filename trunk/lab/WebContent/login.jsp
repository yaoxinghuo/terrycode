<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<title>设备预约系统后台登录</title>
<link href="resources/css/style.css" media="all" type="text/css"
	rel="stylesheet" />

<style type="text/css">
BODY {
	background-image: url(resources/images/login_background.png);
	TEXT-ALIGN: center;
	background-repeat: repeat-x;
	background-color: #fff;
	padding: 0px;
	margin: 0px;
}

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

A:link {
	COLOR: blue;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: blue;
	TEXT-DECORATION: none
}

A:active {
	COLOR: blue;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: blue;
	TEXT-DECORATION: underline
}
</style>

</head>
<body>
<div style="height: 100px;"></div>

<div>
<center>
<table border="0" width="350" cellspacing="1" cellpadding="0"
	bgcolor="#A095C4">
	<tr>
		<td align="center"><b>&nbsp;<span>设备预约系统后台登录</span></b></td>
	</tr>
	<tr>
		<td height="130" bgcolor="#FFFFFF" colspan="2" valign="top"
			style="padding: 10px;">
			<form action="login.action" method="post" id="login_form" onkeydown="if(event.keyCode==13){return false;}">
			<table align="center">
				<tr>
					<td colspan="2" align="center"><span id="message"><%=(request.getAttribute("_tip") == null) ? "&nbsp;"
								: request.getAttribute("_tip")%></span></td>
				</tr>
				<tr>
					<td align="right">工号/用户名*：</td>
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
						class="input">&nbsp;&nbsp;<img border=0 src=image alt="加载中..."></td>
				</tr>
				<tr>
					<td align="right"><input type="checkbox" name="saveCookie" id="login_saveCookie"
						value="saveCookie" onclick="rememberSaveCookie();" checked></td>
					<td align="left">保持浏览器登录状态会话</td>
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

<script>
   document.onkeydown=keydown;
   function keydown(ev){
	ev = ev || window.event;
	if(ev.keyCode==13){
		userlogin();
	}
   }
	function userlogin(){
		var form = document.getElementById("login_form");
		var message = document.getElementById("message");
		var no = form.no.value;
		var password=form.password.value;
		var validate=form.validate.value;
		if(!no) {
			message.innerHTML="<font color=red>用户名 不能为空！</font>";
			form.no.focus();
			return;
		}
		if(!password) {
			message.innerHTML="<font color=red>密码 不能为空！</font>";
			form.password.focus();
			return;
		}
		if(!validate) {
			message.innerHTML="<font color=red>验证码 不能为空！</font>";
			form.validate.focus();
			return;
		}
		message.innerHTML="<font color=green>登录中,请稍候...</font>";
		form.submit();
	}
	
	function mouseOver(theTD,className) {
		oldClass = theTD.className;
		theTD.className =className;
	}
	function mouseOut(theTD,className) {
		theTD.className = className;
	}
	
	function getCookie( name ) { 
		var start = document.cookie.indexOf( name + "=" ); 
		var len = start + name.length + 1; 
		if ( ( !start ) && ( name != document.cookie.substring( 0, name.length ) ) ) { 
			return null; 
		} 
		if ( start == -1 ) 
			return null; 
		var end = document.cookie.indexOf( ';', len ); 
		if ( end == -1 ) 
			end = document.cookie.length; 
		return unescape( document.cookie.substring( len, end ) ); 
	}
	
	function setCookie( name, value, expires, path, domain, secure ) { 
		var today = new Date(); 
		today.setTime( today.getTime() ); 
		if ( expires ) { 
			expires = expires * 1000 * 60 * 60 * 24; 
		} 
		var expires_date = new Date( today.getTime() + (expires) ); 
		document.cookie = name+'='+escape( value ) + 
			( ( expires ) ? ';expires='+expires_date.toGMTString() : '' ) +
			( ( path ) ? ';path=' + path : '' ) + 
			( ( domain ) ? ';domain=' + domain : '' ) + 
			( ( secure ) ? ';secure' : '' ); 
	}
	
	function rememberSaveCookie(){
		var saveCookie = document.getElementById("login_saveCookie").checked;
		setCookie("login_saveCookie",saveCookie,30);
	}
	
	function loadSaveCookie(){
		var saveCookie = getCookie("login_saveCookie");
		if(saveCookie==null||saveCookie=="true")
			return true;
		return false;
	}
	
	document.getElementById("login_form").no.focus();
	document.getElementById("login_saveCookie").checked=loadSaveCookie();
   </script>
</body>
</html>
