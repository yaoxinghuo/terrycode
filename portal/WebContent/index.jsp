<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Portal</title>
<link rel="stylesheet" href="css/google.css">
<link rel="stylesheet" href="css/login.css">
<link href="css/tab-view.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/Account.js'></script>
<script type='text/javascript' src='dwr/interface/Profile.js'></script>
<script type='text/javascript' src='dwr/interface/DWRTemplate.js'></script>
<script type='text/javascript' src='dwr/interface/Message.js'></script>

<script type="text/javascript" src="js/prototype.js"></script>
<script type='text/javascript' src="js/google_drag.js"></script>
<script type='text/javascript' src="js/cookie.js"></script>
<script type='text/javascript' src='js/news.js'></script>
<script type='text/javascript' src='js/rss.js'></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/terry.js"></script>
<script type="text/javascript" src="js/terry2.js"></script>
<script type="text/javascript" src="js/tab-view.js"></script>
<script type='text/javascript' src="js/instantedit.js"></script>
<script type='text/javascript' src="js/scroller.js"></script>

<script type='text/javascript' src="js/drag_drop.js"></script>
<link href="css/drag_drop.css" rel="stylesheet" type="text/css">
<style type="text/css">
div.RoundedCorner {
	background: #fad163;
}

b.rtop,b.rbottom {
	display: block;
	background: #FFF
}

b.rtop b,b.rbottom b {
	display: block;
	height: 1px;
	overflow: hidden;
	background: #fad163;
}

b.r1 {
	margin: 0 5px
}

b.r2 {
	margin: 0 3px
}

b.r3 {
	margin: 0 2px
}

b.rtop b.r4,b.rbottom b.r4 {
	margin: 0 1px;
	height: 2px
}
</style>
</head>

<body onunload="doOnunload();" onload="doOnload();">

<script type='text/javascript' src='js/wz_tooltip.js'></script>
<script type="text/javascript">
	var accountHTML = '<div id="account_template">'+
		'<font size=-1>欢迎 <span id="_nickname"></span><a '+ 
		'href="#" onclick=showMsg("有待实现!"); />我的帐户</a> <a '+
		'href="#" onclick="userlogout();">退出</a>'+
		'</font></div>';
	if(<%=(session.getAttribute("_username")==null)%>) {
		//设置成同步
		DWREngine.setAsync(false);
		DWRUtil.useLoadingMessage("正在加载...");
		Account.cookieLogin({
			callback:function(islogin) {
				if(islogin)
					window.location.reload();
				else
					showMsg("No Cookie!");
			},
			timeout:3000,
			errorHandler:function(message){
				DWREngine.setAsync(true);
			}
		});
		//重新设置为异步方式
		DWREngine.setAsync(true);
	}
		function testTerry(){
			Message.retrieveMessages();
		}
</script>

<div id="msg" style="display: none;z-index: 1000; position: absolute; left: 300px; top: 20px"
	class="RoundedCorner">
<table align="center" border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td align="center" nowrap><font size='-1'> <b class="rtop"><b
			class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
		<span style="padding: 4px;" id="msg_content"></span> <b
			class="rbottom"><b class="r4"></b><b class="r3"></b><b class="r2"></b><b
			class="r1"></b></b></font></td>
	</tr>
</table>
</div>

<div id="login_box">
<div class="login_message">
<div class="login_header">
<div style="display: inline; width: 150px; position: absolute">登录到Portal</div>
<span style="float: right; display: inline; cursor: pointer"> <img
	src="images/x.gif" style="cursor: pointer"
	onclick=javascript:hideLoginBox() alt="关闭" title="关闭" width=11
	height=11 border=0> </span></div>
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
		<td><input type="reset" value="重置" /></td>
		<td><input type="button" value="登录" onclick="userlogin();" /></td>
	</tr>
</table>
</form>
</div>
</div>

<div id="login_mask"></div>

<div id="tip_div"
	style='position: absolute; visibility: hidden; z-index: 100'>
<table id="tip_table" style="background-color: #9A9A9A;" border="0"
	cellspacing="1" cellpadding="0">
	<tr>
		<td>
		<table width=100% border=0 cellpadding=0 cellspacing=0>
			<tr valign=middle style="background-color: #212121;">
				<td style="font-size: 12px; color: #F4F4F4; font-weight: bold"
					width=100% align=left><span id="tip_div_title"></span></td>
				<td align=right valign=top><img src="images/x.gif"
					style="cursor: pointer" onclick=javascript:hideTipDiv() alt="关闭"
					title="关闭" width=11 height=11 border=0></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td id=tip_div_content
			style='background-color: #eeeeee; color: black; font-size: 12px; font-family: Courier New;'
			align=center></td>
	</tr>
</table>
</div>

<table width="100%">
	<tr>
		<!-- <td>
			<input type="button" value="添加Tab" onclick="createNewTab('dhtmlgoodies_tabView1','A dynamic tab','','externalfile.html',true);return false"/>
		</td> -->
		<td id="account_title"><span id="edit_title">Portal</span></td>

		<td></td>

		<s:if test="${sessionScope._username==null}">
			<td align='right' nowrap id="account_content"><font size='-1'><a
				href="#" onclick="showLoginBox();">登录</a></font></td>
		</s:if>
		<s:else>
			<td align='right' nowrap id="account_content"><script>	
					$("account_content").innerHTML=accountHTML;
				</script></td>
		</s:else>
	</tr>
</table>

<s:if test="${sessionScope._username==null}">
	<div id="no_default">无默认网页，请先<a href="#"
		onclick="showLoginBox();">登录</a></div>
</s:if>

<div id="dhtmlgoodies_tabView1" style="display: none">
<div class="dhtmlgoodies_aTab">
<div id="tab0"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab1"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab2"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab3"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab4"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab5"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab6"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab7"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab8"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab9"></div>
</div>
<div class="dhtmlgoodies_aTab">
<div id="tab10"></div>
</div>
</div>
<center><br>
<div id=footer align=center style="white-space: nowrap"><font
	size=-1><a href="/intl/zh-CN/ads/">广告计划</a> - <a
	href="/intl/zh-CN/help/privacy_fusionph.html">隐私政策</a> - <a
	href="/support/bin/topic.py?topic=1592&hl=zh-CN">帮助</a> - <a
	href="/intl/zh-CN/about.html">星火</a></font>
<p><font size='-2'>&copy;2007 星火</font>
</div>
</center>

<script>
	if(<%=(session.getAttribute("_username")!=null)%>) {
		initTemplate();
	} 
</script>
</body>
</html>