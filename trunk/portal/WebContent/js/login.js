﻿window.onresize=function(){
	if($("login_mask").style.visibility=='visible') {
		var wh=getViewportInfo();
		$("login_mask").style.width=wh.width+"px";
		$("login_mask").style.height=wh.height+"px";
		$("login_box").style.left=(wh.width-350)/2+"px";
		$("login_box").style.top=(wh.height-350)/2+"px";
	} else if ($("tip_div").style.visibility=='visible') {
		var tl = getCompPos($(_currentTipDivParentId));
		$("tip_div").style.left=tl.left+"px";
		$("tip_div").style.top=tl.top+"px";
	}
};
	
function getViewportInfo() {
	var w = (window.innerWidth) ? window.innerWidth : (document.documentElement && document.documentElement.clientWidth) ? document.documentElement.clientWidth : document.body.offsetWidth;
	var h = (window.innerHeight) ? window.innerHeight : (document.documentElement && document.documentElement.clientHeight) ? document.documentElement.clientHeight : document.body.offsetHeight;
	return {"width":w,"height":h};
}
	 
function showLoginBox(){
	var wh=getViewportInfo();
	$("login_mask").style.width=wh.width+"px";
	$("login_mask").style.height=wh.height+"px";
	$("login_mask").style.visibility='visible';
	$("login_box").style.left=(wh.width-350)/2+"px";
	$("login_box").style.top=(wh.height-350)/2+"px";
	$("login_box").style.visibility='visible';
	$('login_error_msg').innerHTML=''; 
	$('login_saveCookie').checked = loadSaveCookie();
	Event.observe(document, "keypress", observerKeyDown);
	$('login_username').focus();
}
	
function hideLoginBox(){
	$("login_box").style.visibility='hidden'; 
	$("login_mask").style.visibility='hidden';
	Event.stopObserving(document, "keypress", observerKeyDown);
}
	
function userlogin(){
	var username = $F('login_username');
	var password = $F('login_password');
	var saveCookie = $("login_saveCookie").checked;
	if(username==""||password==""||!loginValidator(username,password,saveCookie)) {
		$('login_error_msg').innerHTML='<font color=red>用户名或密码错误！</font>'; 
		$('login_error_msg').show();
		$('login_password').value="";
		return; 
	}
	$("login_box").style.visibility='hidden'; 
	$("login_mask").style.visibility='hidden';
	$('login_error_msg').hide();
	$("no_default").parentNode.removeChild($("no_default"));
	$("account_content").innerHTML=accountHTML;
	initTemplate();
}

function loginValidator(username,password,saveCookie) {
    	var login=false;
    	$('login_error_msg').innerHTML='<font color=green>处理中...</font>'; 
    	//设置成同步
        DWREngine.setAsync(false);
        Account.login(username,password,saveCookie,{
        	callback:function(islogin) {
				login=islogin;
			},
			timeout:8000,
			errorHandler:function(message){
				$('login_error_msg').innerHTML='对不起，出现错误:'+message; 
				$('login_error_msg').show(); 
				DWREngine.setAsync(true);
			}
        });
        //重新设置为异步方式
        DWREngine.setAsync(true);
    	return login;
}
	
function userlogout(){
        DWREngine.setAsync(false);
		Account.logout();
		DWREngine.setAsync(true);
		window.location.reload();
}

function rememberSaveCookie(){
	var saveCookie = $("login_saveCookie").checked;
	setCookie("login_saveCookie",saveCookie);
}

function loadSaveCookie(){
	var saveCookie = getCookie("login_saveCookie");
	if(saveCookie==null||saveCookie=="true")
		return true;
	return false;
}

function observerKeyDown(event){
	var e = Event.element(event);
	if (event.keyCode == 13) {
		userlogin();
	}
}