<%@ page contentType="text/html; charset=UTF-8"%>

<div id="searchbar">
<form name="form1" id="form1" method="post" action="search.action">
	<strong>搜索：</strong>
		<select name="type" style="width: 100px">
			<option>配件</option>
			<option>求购</option>
			<option selected="selected">厂商</option>
		</select>&nbsp;<input id="searchkey" name="searchKey" type="text" size="40"/> <input type="button"
			name="Submit" id="searchsubmit" value="搜 索" onmouseover="changebg('searchsubmit','white')" onmouseout="changebg('searchsubmit','#2F8FEA')"/>
</form>
</div>