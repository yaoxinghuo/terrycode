<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Test</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
body {margin:0px;font-size:12px; height:100%;}
button {background:url(img/buttonbg.gif);width:65px;heigt:20px;border:none;padding-top:3px;font-size:12px;}
</style>
<script type='text/javascript' src='msg.js'></script>
</head>
<body>
<script language="javascript">
<!--
msg("默认提示框标题",1,"250","默认内容,在window加载的时候显示","");
//-->
</script>
<div id="bgs" style="padding:10px"><br/>首先在head里引用<font color="red">&lt;script type='text/javascript' src='msg.js'&gt;&lt;/script&gt;</font><p/>
	调用：<font color="red">onclick=msg("提示标题","提示格式","提示宽度","提示内容","点击确定后执行的JS操作");</font><p/>
	提示格式：1,只有确定按钮   2,有确定和取消   3,没有按钮<br/><br/>
	
	     <button onclick='msg("提示框标题",1,"250","这里是内容","");'>先看看</button>&nbsp;
         <button onclick='msg("这里是标题",1,"240","可以多次反复调用！","");'>再试试</button>&nbsp;
         <button onclick='msg("没有按纽",3,"340","无选择","");'>无选择</button>&nbsp;
         <button onclick='msg("一个带有",2,"180","按确定后跳转！","window.location.href=http://www.cssrain.cn")'>选择提示</button>&nbsp;
         <button onclick='msg("系统信息",1,"400","基本没有BUG","");'>几个问题</button>&nbsp;
         <button onclick='msg("温馨提示",1,"280","无选择无选择无选择无选择无选择","");'>关于作者</button>
         <br />
         <select id="">
		<option value="" selected="selected"></option>
		<option value=""></option>
         </select>
   </div>
</body>
</html>