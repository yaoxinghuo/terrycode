<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="equip_show" style="visibility: hidden;">
<table border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="11%" height="43" align="center"><strong>设备展示</strong></td>
		<td width="2%"><img src="resources/images/left.gif" alt="向左移"
			width="9" height="49" id="left1" style="CURSOR: pointer" /></td>
		<td>
		<div id="marqueedivcontrol"
			style="width: 800px; height: 50px; overflow: auto">
		
		</div>
		</td>
		<td width="2%" align="right"><img
			src="resources/images/right.gif" alt="向右移" width="9" height="49"
			id="right1" style="CURSOR: pointer" /></td>
		<td width="4%"></td>
	</tr>
	<tr>
		<td height="6"></td>
	</tr>
	<tr>
		<td bgcolor=#dedede height="1" colspan="4"></td>
	</tr>
</table>
</div>
<script type="text/javascript">
Equip.getRadomEquipImages(12,{
	callback:function(value) {
			var result = eval('('+value+')');;
			if(result.length==0)
				return;
			else
				document.getElementById("equip_show").style.visibility="visible";
			var html="";
			for(var i=0;i<result.length;i++){
				html=html+'<a href="equipview.action" target="_blank"><img src="resources/equip/'+
					result[i]+'" width="90" height="50" border="0" /></a>';
			}
			document.getElementById("marqueedivcontrol").innerHTML=html;
			var marquee2=new Marquee("marqueedivcontrol");
			marquee2.Direction="left";
			marquee2.Step=1;
			marquee2.Width=800;
			marquee2.Height=50;
			marquee2.Timer=20;
			document.getElementById('left1').onclick=function(){marquee2.Direction=2};
			document.getElementById('right1').onclick=function(){marquee2.Direction=3};
			marquee2.Start();
	},
	errorHandler:function(message){
	}
});
</script>