<%@ page contentType="text/html; charset=UTF-8"%>

<div id="login_box">
	<div class="login_message">
		<div id="login_header" class="login_header">
			<div style="left: 130px; position: relative; float: left;top:3px;">登录到Auto</div>
			<span style="float: right; display: inline; cursor: pointer;">
				<img src="images/x.gif" style="cursor: pointer;right:3px;top:2px;position:relative" onclick="hideLoginBox();return false;" alt="关闭" title="关闭">
			</span>
		</div>
		<span id="login_error_msg"></span><br>
		<form id="login_form">
			<table align="center">
				<tr>
					<td>请输入用户名：</td>
					<td><input type="text" name="username" id="login_username"
						style="width: 150px"></td>
				</tr>
				<tr>
					<td>请输入密码：</td>
					<td><input type="password" name="password" id="login_password"
						style="width: 150px"></td>
				</tr>
				<tr>
					<td align="right"><input type="checkbox" id="login_saveCookie"
						checked onclick="rememberSaveCookie();"></td>
					<td>在此计算机上保存我的信息</td>
				</tr>
				<tr>
					<td><input type="button" value="登录" onclick="userlogin();" /></td>
					<td><input type="reset" value="重置" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>

<div id="login_mask"></div>