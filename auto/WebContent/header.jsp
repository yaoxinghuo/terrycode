<%@ page contentType="text/html; charset=UTF-8"%>

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

<div id="maintab">
	<span class="tabactive">首 页</span>
	<span class="tabinactive"><a href="#" onclick="javascript:;">求购</a></span>
	<span class="tabinactive"><a href="#" onclick="javascript:;">供应</a></span>
	<span class="tabinactive"><a href="#" onclick="javascript:;">产品</a></span>
	<span class="tabinactive"><a href="#" onclick="javascript:;">产品</a></span>
	<span class="tabinactive"><a href="http://vip.104china.com/" target="_blank">企业服务</a></span>
	<span class="tabinactive"><a href="http://hunter.104china.com/"
		target="_blank">供应商</a></span>
	<span class="tabinactive"><a href="http://ehrweb.104china.com/"
		target="_blank">采购商</a></span>
	<div>
		<a href="#" onclick="javascript:;">[登录]</a>&nbsp;|&nbsp;<a href="#" onclick="javascript:;">[注册]</a>
	</div>
</div>

</div>
<div class="redline"></div>