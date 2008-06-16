<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

<div id="header">
<div id="logo"><a href="http://www.104china.com" target="_blank"><img
	src="images/logo.gif" /></a></div>

<div id="topbar">
<div id="topbarb">
<div style="float: left; margin-top: 10px;"><a href="#"
	onclick="javascript:;">[登录]</a>|<a href="#" onclick="javascript:;">[注册]</a>
</div>
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
<ul>
	<li class="dqy">首 页</li>
	<li class="xqy"><a href="#" onclick="javascript:;">求购</a></li>
	<li class="xqy"><a href="#" onclick="javascript:;">供应</a></li>
	<li class="xqy"><a href="#" onclick="javascript:;">产品</a></li>
	<li class="xqy"><a href="#" onclick="javascript:;">产品</a></li>
	<li class="qy"><a href="http://vip.104china.com/" target="_blank">企业服务</a></li>
	<li class="qy"><a href="http://hunter.104china.com/"
		target="_blank">供应商</a></li>
	<li class="qy"><a href="http://ehrweb.104china.com/"
		target="_blank">采购商</a></li>
</ul>
</div>
</div>
</div>

<div id="searchbar">
<form name="form1" id="form1" method="post" action="search.action">
<table width="98%" border="0" cellspacing="0" cellpadding="0"
	class="ml10 ">
	<tr>
		<td height="46" colspan="1" class="white pl5"><strong>搜索：</strong>
		<select name="type" style="width: 100px">
			<option>配件</option>
			<option>厂商</option>
		</select>&nbsp;<input id="searchkey" name="searchKey" type="text" class="input" size="40"/> <input type="button"
			name="Submit" value=" 搜 索 " onclick="goSearch();return false;" />
		&nbsp;</td>
		<td>
		<div><a href="#" onclick="javascript:;">测试公司广告1</a>&nbsp;&nbsp;<a
			href="#" onclick="javascript:;">测试公司广告2</a>&nbsp;&nbsp;<a href="#"
			onclick="javascript:;">测试公司广告3</a></div>
		</td>
	</tr>
	<tr>
		<td height="30" colspan="2" class="pl5"><img
			src="images/index_icon1.gif"/> 热门关键词： <a
			href="#" onclick="return searchSeaKey('工程师')">传感器</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('项目经理')">轮胎</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('人事 人力资源');">雨刮器紧固件</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('销售经理')">点火线圈</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('广告 销售')">密封件</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('市场 营销')">轮辋</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('客户服务')">变速箱</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('财务 会计')">火花塞</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('企划 策划')">发电机</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('游戏 动画')">水泵奇瑞</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('软件工程师')">大众</a></td>
	</tr>
</table>
</form>
</div>
</body>
</html>