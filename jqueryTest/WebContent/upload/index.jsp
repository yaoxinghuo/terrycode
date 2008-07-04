<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
    <form name="upload" method="POST" action="upload" enctype="multipart/form-data">

        <table border="1" width="450" cellpadding="4" cellspacing="2" bordercolor="#9BD7FF">
        <tr><td width="100%" colspan="2">
                        文件1：<input name="x" size="40" type="file">
        </td></tr>
        <tr><td width="100%" colspan="2">
                        文件2：<input name="y" size="40" type="file">
        </td></tr>
        <tr><td width="100%" colspan="2">
                        文件3：<input name="z" size="40" type="file">
        </td></tr>
        </table>
        <br/><br/>

        <table>

        <tr><td align="center"><input name="upload" type="submit" value="开始上传"/></td></tr>

       </table>

</form>


  </body>
</html>
