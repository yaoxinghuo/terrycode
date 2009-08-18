function gfw() {
	var form = document.f;
	var str = form.r.value;
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
	form.r.value = "http://www.google.com/search?hl=en&q="
			+ str.replace(/ /g, "+") + "&aq=f&oq=&aqi=";
	form.action = "router";
	form.method = "post";
	form.submit();
	form.r.value = str;
}

function IsURL(str_url) {
	if (str_url.indexOf(" ") != -1)
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