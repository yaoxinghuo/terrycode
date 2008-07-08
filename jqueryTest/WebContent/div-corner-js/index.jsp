<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="cb.js"></script>
<link type="text/css" rel="stylesheet" href="corner.css" />
<title>JavaScipt实现的圆角DIV</title>
</head>

<body>

<script>
	var abc;

	function k1(event) {
		abc = new createCornerDiv(200, null, null, 'test', event);
		out_biaozhi = true;
	}

	function k2(event) {
		abc = new createCornerDiv(
				350,
				null,
				null,
				'<div style="font-size:12px;">事件名：<input type="text" value="test" id="" /><br/>' + '事件开始时间：<input type="text" value="test" id="" /><br/>' + '事件结束时间：<input type="text" value="test" id="" /><br/>' + '<input type="button" value="创建活动" />&nbsp;&nbsp;<a href="#" >修改活动详细信息 >></a><br/></div>',
				event);
		out_biaozhi = true;
	}

	function k3(event) {
		abc = new createCornerDiv(
				400,
				null,
				null,
				'TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest',
				event);
		out_biaozhi = true;
	}

	function k4(event) {
		abc = new createCornerDiv(
				500,
				null,
				null,
				'中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文中文stTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest',
				event);
		out_biaozhi = true;
	}

	//关闭事件
	function clo() {
		if (abc) {
			abc.closeDiv();
			abc = "";
		}
	}
</SCRIPT>


<br />
<div style="font-size: 12px;">程序解释：<br />
每次只打开一个div。<br />
点击任何地方关闭div。<br />
高度自适应。<br />
调用： new createCornerDiv(500, null , null ,'内容' , event );<br />
参数1：高度<br />
参数2,参数3：控制div在网页中的位置。（top,left）<br />
参数3：内容<br />
参数4: 兼容ff。<br />
</div>
<br />




<input type="text" value="" onclick="k1(event)" />
<br />
<br />
<br />

<input type="" onclick="k2(event)" />

<br />
<br />
<br />
<br />
<br />
<input type="" onclick="k3(event)" />

<br />
<br />
<br />
<br />
<br />
<input type="" onclick="k4(event)" />

<br />
<br />
<br />
<input type="button" value="关闭" onclick="clo()" />

</body>
</html>