<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

<div id="header">
<div id="logo"><a href="http://www.104china.com" target="_blank"><img
	src="images/logo.gif" /></a></div>

<div id="topbar">
<ul>
	<li><a href="<%=request.getContextPath() %>/help/question.html"
		target="_blank" class="agray48">常见问题</a></li>
	<li><a href="mailto:service@104china.com" class="agray48">联系我们</a></li>
	<li><a href="#"
		onclick="return addFavorite('http://www.104china.com','104人力银行');"
		class="agray48">加入收藏</a></li>
</ul>
</div>
</div>
<div id="maintab">
<ul>
	<li class="tabactive">首 页</li>
	<li class="tabinactive"><a href="#" onclick="javascript:;">求购</a></li>
	<li class="tabinactive"><a href="#" onclick="javascript:;">供应</a></li>
	<li class="tabinactive"><a href="#" onclick="javascript:;">产品</a></li>
	<li class="tabinactive"><a href="#" onclick="javascript:;">产品</a></li>
	<li class="tabinactive"><a href="http://vip.104china.com/" target="_blank">企业服务</a></li>
	<li class="tabinactive"><a href="http://hunter.104china.com/"
		target="_blank">供应商</a></li>
	<li class="tabinactive"><a href="http://ehrweb.104china.com/"
		target="_blank">采购商</a></li>
</ul>
</div>
<div class="redline"></div>
</body>
</html>