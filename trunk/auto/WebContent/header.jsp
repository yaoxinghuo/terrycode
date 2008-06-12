<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

<div id="header">
<div id="logo"><a href="http://www.104china.com" target="_blank"><img
	src="images/logo.gif" /></a></div>
<div id="topbar">
<div id="topbarb">
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
	<li><a href="#" onclick="javascript:;">求购</a></li>
	<li><a href="#" onclick="javascript:;">供应</a></li>
	<li><a href="#" onclick="javascript:;">产品</a></li>
	<li><a href="#" onclick="javascript:;">产品</a></li>
	<li class="qy"><a href="http://vip.104china.com/" target="_blank">企业服务</a></li>
	<li class="qy"><a href="http://hunter.104china.com/"
		target="_blank">供应商</a></li>
	<li class="qy"><a href="http://ehrweb.104china.com/"
		target="_blank">采购商</a></li>
</ul>
</div>
</div>
</div>

<div id="mainmenubg">
<form name="form1" id="form1" method="post" action="">
<table width="99%" border="0" cellspacing="0" cellpadding="0"
	class="ml10 ">
	<tr>
		<td height="46" colspan="2" class="white pl5"><strong>找配件：</strong>
		<select name="cityB" style="width: 100px">
		</select>&nbsp;<select name="jobCatB" style="width: 200px">
		</select>&nbsp;<input name="searchKey" type="text" class="input"
			value="填写职位名称关键字" size="40" onfocus="this.value=''"
			onblur="checkKeyValue(this,'填写职位名称关键字');" /> <input type="button"
			name="Submit" value=" 搜 索 " onclick="goSearch();return false;" />
		&nbsp;<a href="http://www.104china.com/help/question_4.html"
			target="_blank" class="ff">帮 助</a> [<a
			href="http://www.104china.com/AdvanceSearchJobAction.do"
			target="_blank" class="ff">高级搜索</a>] [<a
			href="http://www.104china.com/AdvanceSearchJobAction.do"
			target="_blank" class="ff">分类搜索</a>] [<a
			href="http://www.104china.com/SearchCompanyAction.do" target="_blank"
			class="ff">公司搜索</a>]</td>
	</tr>
	<tr>
		<td height="19" class="pl5"><img src="images/index_icon1.gif"
			width="9" height="9" /> 热门供应商： <a
			href="http://www.104china.com/SearchCompanyAction.do" target="_blank">知名企业</a>&nbsp;
		<a href="http://www.104china.com/SearchCompanyAction.do?go=1"
			target="_blank">IT名企</a>&nbsp; <a
			href="http://www.104china.com/SearchCompanyAction.do?go=5"
			target="_blank">电子微电子</a>&nbsp; <a
			href="http://www.104china.com/SearchCompanyAction.do?go=2"
			target="_blank">贸易物流</a>&nbsp; <a
			href="http://www.104china.com/SearchCompanyAction.do?go=4"
			target="_blank">服 务</a> &nbsp;&nbsp;&nbsp; <a href="#"
			onclick="searchMoreCompany();" class="orangel">更多行业&gt;&gt;</a></td>
	</tr>
	<tr>
		<td height="23" colspan="2" class="pl5"><img
			src="images/index_icon1.gif" width="9" height="9" /> 热门关键词： <a
			href="#" onclick="return searchSeaKey('工程师')">工程师</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('项目经理')">项目经理</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('人事 人力资源');">人事 人力资源</a>&nbsp;
		<a href="#" onclick="return searchSeaKey('销售经理')">销售经理</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('广告 销售')">广告 销售</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('市场 营销')">市场 营销</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('客户服务')">客户服务</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('财务 会计')">财务 会计</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('企划 策划')">企划 策划</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('游戏 动画')">游戏 动画</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('软件工程师')">软件工程师</a>&nbsp; <a
			href="#" onclick="return searchSeaKey('软件开发')">软件开发</a>&nbsp; <a
			href="http://www.104china.com/QuickSearchJobAction.do"
			target="_blank" class="orangel">更多&gt;&gt;</a></td>
	</tr>
</table>
</form>
<div></div>
</div>
</body>
</html>