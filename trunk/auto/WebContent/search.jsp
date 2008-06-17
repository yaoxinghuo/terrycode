<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

<div id="searchbar">
<form name="form1" id="form1" method="post" action="search.action">
	<strong>搜索：</strong>
		<select name="type" style="width: 100px">
			<option>配件</option>
			<option>厂商</option>
		</select>&nbsp;<input id="searchkey" name="searchKey" type="text" size="40"/> <input type="button"
			name="Submit" id="searchsubmit" value=" 搜 索 " onmouseover="changebg('searchsubmit','#FB8C31')" onmouseout="changebg('searchsubmit','#F26C2C')"/>
</form>
</div>
</body>
</html>