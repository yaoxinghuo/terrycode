function gfw(s) {
	var form = document.f;
	var str = form.r.value;
	if (str == "") {
		showMsg("请输入正确的网址或Google搜索的关键词！");
		document.f.r.focus();
		return;
	}
	form.s.value = s;
	form.action = "router";
	form.method = "post";
	form.submit();
	form.r.value = str;
}

document.onkeydown = keydown;
function keydown(ev) {
	ev = ev || window.event;
	if (ev.keyCode == 13) {
		gfw();
	}
}

var timeoutID = null;
function showMsg(msg) {
	if (!msg)
		return;
	document.getElementById("msg_content").innerHTML = msg;
	var l = msg.length;
	if (msg.indexOf("<a") != -1)
		l = l - 47;
	document.getElementById("msg").style.left = (document.body.clientWidth - l * 10)
			/ 2 + "px";
	document.getElementById("msg").style.visibility = "visible";
	if (timeoutID != null)
		clearTimeout(timeoutID);
	timeoutID = setTimeout("clearMsg()", 30000);
}
function clearMsg() {
	document.getElementById("msg").style.visibility = "hidden";
	document.getElementById("msg_content").innerHTML = "";
}