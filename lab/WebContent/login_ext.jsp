<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<title>设备预约系统后台登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<link rel="stylesheet" type="text/css" href="resources/css/ext-all.css" />

<script type="text/javascript" src="adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="js/ext-all.js"></script>

<style type="text/css">
BODY {
	background-image: url(resources/images/login_background.png);
	TEXT-ALIGN: center;
	background-repeat: repeat-x;
	background-color: #fff;
	padding: 0px;
	margin: 0px;
}

A:link {
	COLOR: blue;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: blue;
	TEXT-DECORATION: none
}

A:active {
	COLOR: blue;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: blue;
	TEXT-DECORATION: underline
}
</style>

</head>
<body>
<div style="height: 100px;"></div>
<div id="login" align="center"></div>
<script type="text/javascript">
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	var loginForm = new Ext.FormPanel({
	        labelWidth: 110,
	        width:300,
	        title:"设备预约系统后台登录",
	        labelAlign:'right',
	        frame:true,
	        items: [
	        	{
	        		id:"message",
	            	name:'message',
	            	html:"<%=(request.getAttribute("_tip")==null)?"&nbsp;":request.getAttribute("_tip")%>"
	            },
	            new Ext.form.TextField({
	                fieldLabel:'输入工号',
	                name:'no',
	                allowBlank:false,
	                blankText : "工号不能为空"
	            }),new Ext.form.TextField({
	            	inputType:'password',
	                fieldLabel: '密码',
	                name: 'password',
	                allowBlank:false,
	                blankText : "密码不能为空",
	                id:'password'
	         	}),new Ext.form.TextField({
	                fieldLabel: "<img border=0 src=image>&nbsp验证码",
	                name: 'validate',
	                allowBlank:false,
	                blankText : "验证码不能为空",
	                id:'validate'
	         	})
	            ],
	            buttons: [{
			        text: '登录',
			        handler:loginFn   
			    },{
			        text: '重置',
			        handler:function(){loginForm.form.reset();}
			    }],
			    onSubmit:Ext.emptyFn,
			    submit:function(){
			        this.getForm().getEl().dom.action ='login.action';
			        this.getForm().getEl().dom.method = 'post';   
			        this.getForm().getEl().dom.submit();
			    },
			    keys:[{
  					key:Ext.EventObject.ENTER,  
					fn:loginFn,  
					scope:this  
				}]
	        });
	loginForm.render("login");
	function loginFn(){
		if(loginForm.form.isValid()){
	        loginForm.disabled=true;
	        Ext.getDom("message").innerHTML="<font color=green>登录中,请稍候...</font>";
	        loginForm.submit({
	           params:{action:'submit'}
	        });
		}
	}
</script>
</body>
</html>
