<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="com.portal.test.LogBean"%>
<HTML>
<HEAD>
<TITLE>Mail Log Search Engine Client</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./scripts/calendar.js"></script>
<style type="text/css">
	<!--
	body{margin:0;
	font-family: Arial, Helvetica, sans-serif;
		font-size: 0.8em;
	}
	td,th,div{
			font-size: 0.8em;
	}
	a{
		color: #0066FF;
	}
	a:hover{
		color: #666;
		text-decoration: none;
	}
	
	h1{
	margin:0px;
	height:70px;
	line-height:70px;
	background: #6699CC;
	color: #fff;
	border-bottom:solid 1px #006699;
	}
	
	.search{
	background:#ddeeff;
	padding-top:5px;
	padding-bottom:5px;
	border-bottom:solid 1px #9ABBCB;
	}
	form{margin:0;
	}
	.result{
	width:100%;
		clear:both;
	margin-bottom:20px;
	margin-top:20px;}
	.result h3{
			margin:0px;
			line-height: 25px;
			font-size: 1.3em;
	}
	.linked {
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.linked a{
	
	margin-right: 10px;
	border:solid 1px #CCCCCC;
	padding:3px 10px 3px 10px;
	text-decoration: none;
	}
	.linked a:hover{
	
	margin-right: 10px;
	border:solid 1px #0066FF;
		background: #0066FF;
		color: #fff;
	
	}
	hr{
	
		border: dashed 1px #ddd;
			display: block;
			background:#fff;
			height: 1px;
	}
	.footer{
		font-size:0.8em;
		border-top:solid 1px #ddd;
		padding-top:10px;
	}
	.footer a{color:#666;
	text-decoration: none;}
	.footer a:hover{
		text-decoration: underline;
	}
	-->
	</style>
</HEAD>
<BODY>

<CENTER>
<h1>Mail Log Search Engine Client</h1>
<div class="search">
<FORM name="searchForm" method="post" action="search.do">
<div id="overDiv"
	style="position:absolute; visibility:hidden; z-index:1000;"></div>
<TABLE>
	<TR>
		<td>Choose Date:<input type="text" readOnly="true" name="date" value="2008-05-27"
			size="10" onclick="javascript:show_calendar('searchForm.date');"
			onMouseOut="window.status=''; nd(); return true;" /></td>
		<TD colspan="3">Type Keywords:<INPUT name=searchWord type=text size="40">
		<INPUT type=submit value=search></TD>
	</TR>
</TABLE>
</FORM>
<table class="result" border="1" bgColor="#E6zf2E7" borderColorDark="#FFFFFF"
		borderColorLight="008000" cellPadding="2" cellSpacing="0">

	<TBODY>
			<tr bgColor="#ddeeff">
				<th>Date</th>
				<th>Priority</th>
				<th>Email</th>
				<th>Message</th>
			</tr>
			<%
						ArrayList searchResult = new ArrayList<com.portal.test.LogBean>();
						LogBean log1 = new LogBean();
						log1.setDate("2007-12-01 12:28:31");
						log1.setDir("/etc/log/");
						log1.setEmail("xinghuo.yao@104china.com");
						log1.setPriority("DELIVERED");
						searchResult.add(log1);
						LogBean log2 = new LogBean();
						log2.setDate("2007-12-01 13:11:12");
						log2.setDir("/etc/log/");
						log2.setEmail("xinghuo.yao@104china.com");
						log2.setPriority("ERROR");
						searchResult.add(log2);
				if (searchResult != null && searchResult.size() != 0) {
					for (int i = 0; i < searchResult.size(); i++) {
						com.portal.test.LogBean log = (com.portal.test.LogBean) searchResult.get(i);
						if(log.getPriority().equals("ERROR")) {
			%>
			<tr bgColor="#ddeeff">
				<td><font color="red"><%=log.getDate()%></font></td>
				<td><font color="red"><%=log.getPriority()%></font></td>
				<td><font color="red"><%=log.getEmail()%></font></td>
				
				<%
					if(log.getMessage()==null) {
				%>
				
				<td><font color="red"><a href="viewMessage.do?path='<%=log.getDir() %>'">View Message</a></font></td>
				
				<%
					} else {
				%>
				<td><font color="red"><%=log.getMessage()%></font></td>
				<%
					}
				%>
			</tr>
			<%
						} else {
			%>
			<tr bgColor=#ddeeff>
				<td><%=log.getDate()%></td>
				<td><%=log.getPriority()%></td>
				<td><%=log.getEmail()%></td>
				
				<%
					if(log.getMessage()==null) {
				%>
				
				<td><a href="viewMessage.do?path='<%=log.getDir() %>'">View Message</a></td>
				
				<%
					} else {
				%>
				<td><%=log.getMessage()%></td>
				<%
					}
				%>
				
			</tr>
			<%
						}
				}
				}
			%>
	</TBODY>
</TABLE>
</div>
</CENTER>
</BODY>
</HTML>
