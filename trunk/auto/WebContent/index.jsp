<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>汽配网(JSP)</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<!-- DWR -->
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/Account.js'></script>
<!-- jQuery -->
<script type='text/javascript' src='js/jquery-1.2.6.min.js'></script>
<script type="text/javascript" src="js/jquery.dimensions.js"></script>
<script type="text/javascript" src="js/jquery.gradient.js"></script>
<script type="text/javascript" src="js/jquery.roundcorner.js"></script>
<script type="text/javascript" src="js/jquery.watermarkinput.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<!-- Customize -->
<script type='text/javascript' src='js/MSClass.js'></script>
<script type='text/javascript' src='js/index.js'></script>
<script type='text/javascript' src='js/login.js'></script>
</head>

<body>
<jsp:include page="header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="search.jsp"/>
<div style="margin-right:auto;margin-left:auto;width:1000px;text-align:center">
<jsp:include page="content3.jsp"/>
<jsp:include page="content2.jsp"/>
<jsp:include page="content1.jsp"/>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>