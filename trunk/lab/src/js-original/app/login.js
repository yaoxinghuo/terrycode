var islogin = false;
var timeoutID = null;
function showMsg(msg) {
	$("msg_content").innerHTML = msg
			+ " <a href=# onclick='clearMsg();return false;'><img src='resources/images/close.gif'/></a>";
	$("msg").style.display = "block";
	if (timeoutID != null)
		clearTimeout(timeoutID);
	timeoutID = setTimeout("clearMsg()", 30000);
}
function clearMsg() {
	$("msg").style.display = "none";
	$("msg_content").innerHTML = "";
}

function userlogout() {
	DWREngine.setAsync(false);
	Account.logout();
	cp.set("login_cookie",null);
	DWREngine.setAsync(true);
	$("account_content").innerHTML = "<a href='#' onclick='showLoginWin();return false;'>登录</a>&nbsp;|&nbsp;<a href='index.action'>转到首页</a>&nbsp;|&nbsp;<a href='equipview.action'>设备查询系统</a>";
	showMsg("您已经成功注销，再次登录请<a href='javascript:;' onclick='showLoginWin();'>单击这里</a></span>");
	islogin = false;
	$("validate_img").src = "image?" + Math.random();
	$("my-yuyue").style.visibility = "hidden";
	$("my-shengpi").style.visibility = "hidden";
	for (var i = tabPanel.items.length - 1; i >= 6; i--) {
		var tab = tabPanel.getItem(tabPanel.items.get(i).id);
		if (tab)
			tabPanel.remove(tab);
	}
	tabPanel.setActiveTab(tabPanel.items.get(0));
}

function getWelcomeMessage(username) {
	islogin = true;
	return accountHTML = '欢迎 '
			+ username
			+ '&nbsp;|&nbsp;<a '
			+
//			 'href="#" onclick="Account.invalidateSession();return false;">Invalid Session</a>&nbsp;|&nbsp;<a ' +
			'href="#" onclick="initUserAccount();return false;">我的帐户</a>&nbsp;|&nbsp;<a href="index.action">转到首页</a>&nbsp;|&nbsp;<a '
			+ 'href="equipview.action">设备查询系统</a>&nbsp;|&nbsp;<a href="#" onclick="userlogout();return false;">退出</a>';
}

window.onresize = function() {
	if ($("login_mask").style.visibility == 'visible') {
		var wh = getViewportInfo();
		$("login_mask").style.width = wh.width + "px";
		$("login_mask").style.height = wh.height + "px";
		$("login_box").style.left = (wh.width - 350) / 2 + "px";
		$("login_box").style.top = (wh.height - 350) / 2 + "px";
	}
};

function getViewportInfo() {
	var w = (window.innerWidth)
			? window.innerWidth
			: (document.documentElement && document.documentElement.clientWidth)
					? document.documentElement.clientWidth
					: document.body.offsetWidth;
	var h = (window.innerHeight)
			? window.innerHeight
			: (document.documentElement && document.documentElement.clientHeight)
					? document.documentElement.clientHeight
					: document.body.offsetHeight;
	return {
		"width" : w,
		"height" : h
	};
}

function showLoginWin() {
	var wh = getViewportInfo();
	$("login_mask").style.width = wh.width + "px";
	$("login_mask").style.height = wh.height + "px";
	$("login_mask").style.visibility = 'visible';
	$("login_box").style.left = (wh.width - 350) / 2 + "px";
	$("login_box").style.top = (wh.height - 350) / 2 + "px";
	$("login_message").innerHTML = "&nbsp;";
	$("login_box").style.visibility = 'visible';
	$("login_form").no.focus();
	$("cookie_saveCookie").checked = loadSaveCookie();
	$("login_box").onkeydown = keydown;
}

function keydown(ev) {
	ev = ev || window.event;
	if (ev.keyCode == 13) {
		userlogin();
	}
}

function hideLoginBox() {
	$("login_box").style.visibility = 'hidden';
	$("login_mask").style.visibility = 'hidden';
}

function observerKeyDown(event) {
	var e = Event.element(event);
	if (event.keyCode == 13) {
		userlogin();
	}
}

function userlogin() {
	var form = $("login_form");
	var message = $("login_message");
	var no = form.no.value;
	var password = form.password.value;
	var validate = form.validate.value;
	var saveCookie = form.saveCookie.checked;
	if (!no) {
		showLoginMsg(true,"用户名 不能为空！");
		form.no.focus();
		return;
	}
	if (!password) {
		showLoginMsg(true,"密码 不能为空！");
		form.password.focus();
		return;
	}
	if (!validate) {
		showLoginMsg(true,"验证码 不能为空！");
		form.validate.focus();
		return;
	}
	showLoginMsg(false,"登录中,请稍候...");
	var login = loginValidator(validate, no, password, saveCookie);
	$("validate_img").src = "image?" + Math.random();
	if (login == null || login == "") {
		form.validate.value="";
		showLoginMsg(true,"对不起，程序出现错误!");
		return;
	}
	var result = Ext.decode(login);
	if (result.type == 0) {
		hideLoginBox();
		form.reset();
		if(saveCookie)
			cp.set("login_cookie",result.cookievalue);
		else
			cp.set("login_cookie",null);
		$("account_content").innerHTML = getWelcomeMessage(result.message);
		$("my-yuyue").style.visibility = "visible";
		$("my-shengpi").style.visibility = "visible";
		//showMsg("您已经成功登录！");
		getRecentLogsNotice();
	} else {
		form.validate.value="";
		showLoginMsg(true,result.message);
	}
}

function loginValidator(validate, no, password, saveCookie) {
	var login = null;
	DWRUtil.useLoadingMessage("处理中...");
	DWREngine.setAsync(false);
	Account.login(validate, no, password, saveCookie, {
		callback : function(value) {
			cancelLoadingMessage();
			login = value;
		},
		errorHandler : function(message) {
			cancelLoadingMessage();
			showLoginMsg(true,"对不起，程序出现错误!");
			DWREngine.setAsync(true);
		}
	});
	DWREngine.setAsync(true);
	return login;
}

function showLoginMsg(red, msg) {
	var message = $("login_message");
	if (red)
		message.innerHTML = "<font color=red>" + msg + "</font>";
	else
		message.innerHTML = "<font color=green>" + msg + "</font>";

}

function mouseOver(theTD, className) {
	oldClass = theTD.className;
	theTD.className = className;
}
function mouseOut(theTD, className) {
	theTD.className = className;
}

function getCookie(name) {
	var start = document.cookie.indexOf(name + "=");
	var len = start + name.length + 1;
	if ((!start) && (name != document.cookie.substring(0, name.length))) {
		return null;
	}
	if (start == -1)
		return null;
	var end = document.cookie.indexOf(';', len);
	if (end == -1)
		end = document.cookie.length;
	return unescape(document.cookie.substring(len, end));
}

function setCookie(name, value, expires, path, domain, secure) {
	var today = new Date();
	today.setTime(today.getTime());
	if (expires) {
		expires = expires * 1000 * 60 * 60 * 24;
	}
	var expires_date = new Date(today.getTime() + (expires));
	document.cookie = name + '=' + escape(value)
			+ ((expires) ? ';expires=' + expires_date.toGMTString() : '')
			+ ((path) ? ';path=' + path : '')
			+ ((domain) ? ';domain=' + domain : '')
			+ ((secure) ? ';secure' : '');
}

function rememberSaveCookie() {
	var saveCookie = $("cookie_saveCookie").checked;
	setCookie("cookie_saveCookie", saveCookie, 30);
}

function loadSaveCookie() {
	var saveCookie = getCookie("cookie_saveCookie");
	if (saveCookie == null || saveCookie == "true")
		return true;
	return false;
}
