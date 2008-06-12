<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>汽配网</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function showQiyeLogin(){
	 	document.getElementById("qiyelogin").style.display="block";
	    document.getElementById("userlogin").style.display="none";
	 }
	 function showUserLogin(){
	    document.getElementById("userlogin").style.display="block";
	    document.getElementById("qiyelogin").style.display="none";
	 }
</script>

</head>

<body style="margin-right:auto;margin-left:auto;width:935px">
<jsp:include page="header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="content1.jsp"/>

</body>
</html>