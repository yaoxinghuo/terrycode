<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<title>Test</title>
</head>

<html xmlns="http://www.w3.org/1999/xhtml">
<body>
Test

<script type="text/javascript">
$(function() {
  alert("Javascript loaded...");
});
</script>
<a href="#" onclick="parent.$.nyroModalRemove(); return false;">Close From Iframe</a>
<a href="#" onclick="self.parent.reload();">Parent Reload</a>
</body>
</html>