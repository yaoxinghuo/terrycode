<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Message</title>
</head>

<body>
<form action='msgin' method="post">
<table>
	<tr>
		<td colspan="2"><span style="font-weight: bold; color: red"><%=request.getAttribute("message")==null?"":request.getAttribute("message") %></span></td>
	</tr>
	<tr>
		<td>From:</td>
		<td><input type='text' name='from' /></td>
	</tr>
	<tr>
		<td>Content:</td>
		<td><textarea name='content' cols='36' rows='8'></textarea></td>
	</tr>
	<tr>
		<td></td>
		<td><input type='submit' value='Submit' /></td>
	</tr>
</table>
</form>

</body>
</html>
