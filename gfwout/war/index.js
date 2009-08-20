function gfw() {
	var form = document.f;
	var str = form.r.value;
	if (str == "") {
		showMsg("请输入正确的网址或Google搜索的关键词！");
		document.f.r.focus();
		return;
	}
	if (!IsURL(str)) {
		google(str);
	} else {
		form.action = "router";
		form.method = "post";
		form.submit();
	}
}

function google(str) {
	var form = document.f;
	if (!str)
		str = form.r.value;
	if (str == "") {
		showMsg("请输入正确的网址或Google搜索的关键词！");
		document.f.r.focus();
		return;
	}
	form.r.value = "http://www.google.com/search?hl=en&q="
			+ str.replace(/ /g, "+") + "&aq=f&oq=&aqi=";// 在Google搜索后面加
	// &btnI=745 表示手气不错
	form.action = "router";
	form.method = "post";
	form.submit();
	form.r.value = str;
}

function IsURL(str_url) {
	if (str_url.indexOf(" ") != -1 || str_url.indexOf(".") == -1)
		return false;
	var strRegex = "^((https|http)?://)" // |ftp|rtsp|mms
			+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
			+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			+ "|" // 允许IP和DOMAIN（域名）
			+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
			+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
			+ "[a-z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // 端口- :80
			+ "((/?)|" // a slash isn't required if there is no file name
			+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	var re = new RegExp(strRegex);
	// re.test()
	if (re.test(str_url))
		return true;
	else
		return false;

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