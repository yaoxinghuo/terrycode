<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
function clearMsg() {
	document.getElementById("msg").style.visibility = "hidden";
	document.getElementById("msg_content").innerHTML = "";
}
</script>
<div id="msg"
	style="visibility: hidden; z-index: 1000; position: absolute; left: 300px; top: 20px">
<table border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td style="padding: 4px; background-color: #fad163;"><font
			size="-1"><span id="msg_content"></span>&nbsp;<a href="#"
			onclick="clearMsg();return false;"><img id="close"
			src='images/close.gif' /></a></font></td>
	</tr>
</table>
</div>