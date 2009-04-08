<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<title>江南大学食品学院实验室</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	response.setHeader("Pragma","No-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Cache-Control","no-cache"); 
%>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='js/MSClass.js'></script>
<script type='text/javascript' src='dwr/interface/Notice.js'></script>
<script type='text/javascript' src='dwr/interface/Equip.js'></script>
<link href="resources/css/style.css" media="all" type="text/css"
	rel="stylesheet" />
</head>
<body>
<div class="content_center">
<jsp:include page="header.jsp"/>
<table width="980" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td width="440" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left">
				<table border="0" cellpadding="0" cellspacing="0" width="98%">
					<tr>
						<td height=""></td>
					</tr>
					<tr>
						<td width="36"></td>
						<td height="24"><strong><img src="resources/images/list_main_icon.gif"/><a href='notice!list.action?type=1'>通知公告</a></strong></td>
					</tr>
					<tr>
						<td bgcolor=#dedede height="1" colspan="2"></td>
					</tr>
					<tr>
						<td height="8"></td>
					</tr>
				</table>
				<div id="table1"><img src="resources/images/indicator.gif"
					alt="Loading..."></img>公告加载中...</div>
				</td>
			</tr>
		</table>
		</td>
		<td width="2"></td>
		<td width="440" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left">
				<table border="0" cellpadding="0" cellspacing="0" width="98%">
					<tr>
						<td height=""></td>
					</tr>
					<tr>
						<td width="36"></td>
						<td height="24"><strong><img src="resources/images/list_main_icon.gif"/><a href='notice!list.action?type=2'>设备使用公告</a></strong></td>
					</tr>
					<tr>
						<td bgcolor=#dedede height="1" colspan="2"></td>
					</tr>
					<tr>
						<td height="8"></td>
					</tr>
				</table>
				<div id="table2"><img src="resources/images/indicator.gif"
					alt="Loading..."></img>公告加载中...</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td width="440" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left">
				<table border="0" cellpadding="0" cellspacing="0" width="98%">
					<tr>
						<td height=""></td>
					</tr>
					<tr>
						<td width="36"></td>
						<td height="24"><strong><img src="resources/images/list_main_icon.gif"/><a href='log!list.action'>预约审批信息</a></strong></td>
					</tr>
					<tr>
						<td bgcolor=#dedede height="1" colspan="2"></td>
					</tr>
					<tr>
						<td height="8"></td>
					</tr>
				</table>
				<div id="table3"><img src="resources/images/indicator.gif"
					alt="Loading..."></img>预约审批信息加载中...</div>
				</td>
			</tr>
		</table>
		</td>
		<td width="2"></td>
		<td width="440" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left">
				<table border="0" cellpadding="0" cellspacing="0" width="98%">
					<tr>
						<td height=""></td>
					</tr>
					<tr>
						<td width="36"></td>
						<td height="24"><strong><img src="resources/images/list_main_icon.gif"/><a href='feedback!list.action'>投诉与反馈</a></strong></td>
					</tr>
					<tr>
						<td bgcolor=#dedede height="1" colspan="2"></td>
					</tr>
					<tr>
						<td height="8"></td>
					</tr>
				</table>
				<div id="table4"><img src="resources/images/indicator.gif"
					alt="Loading..."></img>用户反馈信息加载中...</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	
</table>

<jsp:include page="marquee.jsp"></jsp:include>

<jsp:include page="footer.jsp"></jsp:include>

</div>
<script type="text/javascript">
	DWRUtil.useLoadingMessage("加载中...");
    Notice.getIndexNotices(6,1,{//第一个参数代表取得信息数量,下同
    	callback:function(value) {
    		if(value)
    			$("table1").innerHTML=value;
    		else
    			$("table1").innerHTML="加载公告出错！";
		},
		errorHandler:function(message){
			$("table1").innerHTML="加载公告出错！";
		}
    });
    Notice.getIndexNotices(6,2,{
    	callback:function(value) {
    		if(value)
    			$("table2").innerHTML=value;
    		else
    			$("table2").innerHTML="加载公告出错！";
		},
		errorHandler:function(message){
			$("table2").innerHTML="加载公告出错！";
		}
    });
    Notice.getIndexLogs(6,{
    	callback:function(value) {
    		if(value)
    			$("table3").innerHTML=value;
    		else
    			$("table3").innerHTML="加载预约审批信息出错！";
		},
		errorHandler:function(message){
			$("table3").innerHTML="加载预约审批信息出错！";
		}
    });
    Notice.getIndexFeedbacks(6,{
    	callback:function(value) {
    		if(value)
    			$("table4").innerHTML=value;
    		else
    			$("table4").innerHTML="加载反馈信息出错！";
		},
		errorHandler:function(message){
			$("table4").innerHTML="加载反馈信息出错！";
		}
    });
</script>
</body>
</html>