<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/ext-all.css" />
<script type="text/javascript" src="js/ext-base.js"></script>
<script type="text/javascript" src="js/ext-all.js"></script>

<title>Success</title>
</head>
<body>

<input type="button" id="myButton" value="Button"/>

<script type="text/javascript">
	Ext.onReady(function(){
		Ext.get("myButton").on("click",c);
	});
	
	function c(e){
		Ext.get(e.target).highlight();
		Ext.MessageBox.show({
			title:'Button Clicked!',
			msg:'You have clicked Button',
			width:400,
			buttons:Ext.MessageBox.OK,
			animEL:Ext.get(e.target)
		});		
	}
</script>

</body>
</html>