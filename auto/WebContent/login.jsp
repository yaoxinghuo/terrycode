<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

<div class="w935 fl h255 mt5">
<div id="userlogin" style="display:block;">
<div class="logintitle"><img src="images/login_gr.gif"/></div>
<div class="logintitle"><img src="images/login_qy.gif" border="0" onmouseover="showQiyeLogin();" style="CURSOR:pointer"/></div>
<div id="loginimg">
  <div id="logint">
  <form name="LoginForm" id="LoginForm" action="" method="post">
    <table width="95%" border="0" cellspacing="0" cellpadding="0" class="ml10 mt5">
      <tr>
        <td width="75%">帐 号：
          <input name="login_id" id="login_id" type="text" class="inp" style="width:118px"/>
        </td>
        <td width="25%" rowspan="2" align="center"><img src="images/login_button.gif" width="45" height="45" border="0" onclick="doLogin();" style="CURSOR:pointer"/></td>
      </tr>
      <tr>
        <td>密 码：
          <input name="login_password" id="login_password" type="password" class="inp" style="width:118px"/>
        </td>
      </tr>
    </table>
  </form>
    <div align="center" class="mt5"><a href="http://pda.104china.com/resume_new/ResumeNewStep0Action.do" target="_blank" class="red">新会员注册</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="http://pda.104china.com/login/ForgetPasswordAction.do" target="_blank" class="bu">忘记密码</a> </div>
  </div>
  
  <div style="width:242px" >
  <div class="pl5"><img src="images/login_title.gif" /></div>
  <div class="ml10 pl5 mt5"><img src="images/index_icon6.gif" width="11" height="11" /> <span style="color:#C87B01">工作信用卡--</span>帮你找工作</div>
  <div class="mt5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="http://jobbank.104china.com/login/login.jsp?login_from=pda&login_to=http://pda.104china.com/login/Controller.do&login_product=PDA&%23login_func%23=redirect&%23login_redirectUrl%23=/resume_update/ResumeUpdateDataAction.do" class="red" target="_blank">填写简历</a> &nbsp;&nbsp;&nbsp;
  <a href="http://jobbank.104china.com/login/login.jsp?login_from=pda&login_to=http://pda.104china.com/login/Controller.do&login_product=PDA&%23login_func%23=redirect&%23login_redirectUrl%23=/work/MyMatchJobAction.do"  class="bu" target="_blank">查看速配职位</a></div>
  <div class="ml10 pl5 mt5"><img src="images/index_icon6.gif" width="11" height="11" /> <span style="color:#C87B01">职涯储蓄卡--</span>为你找方向</div>
  <div class="mt5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="http://skill.104china.com/testing/skill_index.jsp" target="_blank" class="bu">自我测评</a> &nbsp; 
  <a href="http://www.104china.com/careerdiary/career_goodjob.jsp" target="_blank" class="bu">简历秘籍</a> &nbsp;   
  <a href="http://www.104china.com/careerdiary/career_goodjob.jsp" target="_blank" class="bu">面试宝典</a></div>
  </div>
  </div>
</div>
<div id="qiyelogin" style="display:none;">
<div class="logintitle"><img src="images/login_grb.gif"  border="0" onmouseover="showUserLogin();" style="CURSOR:pointer" /></div>
<div class="logintitle"><img src="images/login_qyb.gif" /></div>
<div id="loginimgb">
  <div id="logintb">
  <form name="VipAccountForm" id="VipAccountForm" method="post" action="http://vip.104china.com/VipLogin.do">
    <table width="95%" border="0" cellspacing="0" cellpadding="0" class="ml10 mt5">
      <tr>
        <td width="75%">帐 号：
          <input name="account" id="vipAccount" type="text"  style="width:118px"/></td>
        <td width="25%" rowspan="2" align="center"><img src="images/login_buttonb.gif" width="45" height="45" onclick="qiyeLogin();" style="CURSOR:pointer"/></td>
      </tr>
      <tr>
        <td>密 码：
          <input name="password" id="vipPassword" type="password"  style="width:118px"/></td>
      </tr>
    </table>
  </form>
    <div align="center" class="mt5"><a href="http://vip.104china.com/VipLogin.do" target="_blank" class="red">服务介绍</a>&nbsp;&nbsp;&nbsp;&nbsp;  <a href="http://vip.104china.com/vip_forgetpw.jsp?rad=1198898817781969969" target="_blank" class="bu">忘记密码</a></div>
  </div>
  
    <div style="width:242px" class="lh24">
  <div class="pl5"><img src="images/login_titleb.gif" width="211" height="21" /></div>
  <div class="mt5 mb5" style="margin-left:35px"><a href="http://vip.104china.com/main/vip_join.jsp?rad=1198900596938547547" target="_blank"><img src="images/login_button2.gif" width="150" height="29" border="0" /></a></div>
  <div>&nbsp;&nbsp;全国招聘专线：+86-21-50277104转分机1
  </div>
  </div>
  </div>
</div>
<script language="javascript">
	var nn;
	nn=1;
	setTimeout('change_qiye()',2500);
	function change_qiye(){
	if(nn>4) nn=1
	 setTimeout('setFocus1('+nn+')',2500);
	 nn++;
	tt=setTimeout('change_qiye()',2500);
	}
	function setFocus1(i)
	{
	 selectLayer1(i);
	}
	function selectLayer1(i){
	 switch(i)
	 {
	 case 1:
	 document.getElementById("focusPic1").style.display="block";
	 document.getElementById("focusPic2").style.display="none";
	 document.getElementById("focusPic3").style.display="none";
	 document.getElementById("focusPic4").style.display="none";
	break;
	 case 2:
	 document.getElementById("focusPic1").style.display="none";
	 document.getElementById("focusPic2").style.display="block";
	 document.getElementById("focusPic3").style.display="none";
	 document.getElementById("focusPic4").style.display="none";
	break;
	 case 3:
	 document.getElementById("focusPic1").style.display="none";
	 document.getElementById("focusPic2").style.display="none";
	 document.getElementById("focusPic3").style.display="block";
	 document.getElementById("focusPic4").style.display="none";
	break;
	 case 4:
	 document.getElementById("focusPic1").style.display="none";
	 document.getElementById("focusPic2").style.display="none";
	 document.getElementById("focusPic3").style.display="none";
	 document.getElementById("focusPic4").style.display="block";
	break;}}
</script>
<div id="mbox">
<div><img src="images/box_title.gif" width="431" height="27" /></div>
<div id="boxxi"> 
<ul id="boxi">
<li class=mbu id="focusPic1" style="display:block;">
  <div id="boxii"><h2><a href="http://www.104china.com/CompanyAction.do?custNo=20071205184753"  target="_blank">联想(上海)有限公司</a></h2>
<div class="dashed mt5 mb10" style="height:106px"><div class="fl mr10" style="width:205px"><img src="images/mingqi_01.gif" width="205" height="100" /></div>
<div class="fr mr5 lh20" style="width:185px;text-indent:2em">新联想是一家极富创新性的国际化的科技公司，由联想及原IBM个人电脑事业部所组成。作为全球个人电脑市场的领导企业，联想从…	</div></div>
<div class="w200">[上海市] <a href="http://www.104china.com/JobAction.do?jobNo=186279&tag=0" target="_blank" class="blue">政府关系经理 </a></div>
<div class="w200 ml10">[上海市] <a href="http://www.104china.com/JobAction.do?jobNo=186281&tag=0" target="_blank" class="blue">专业会计 </a></div>
</div>
  <h3 style="left: 8px;">联   想</h3>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 139px;"><a href="#" onmouseover="javascript:setFocus1(2);">马士基物流</a></div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 251px;"><a href="#" onmouseover="javascript:setFocus1(3);">联邦快递</a></div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 352px;"><a href="#" onmouseover="javascript:setFocus1(4);">长安福特</a></div>
</li>
<li class=mbu  id="focusPic2" style="display:none;">
  <div id="boxii"><h2><a href="http://www.104china.com/CompanyAction.do?custNo=20070809149341"  target="_blank">马士基(中国)有限公司</a></h2>
<div class="dashed mt5 mb10" style="height:106px"><div class="fl mr10" style="width:205px"><img src="images/mingqi_02.gif" width="205" height="100" /></div>
<div class="fr mr5 lh20" style="width:185px;text-indent:2em">A.P. Moller - Maersk is in constant movement – with activities around the world, day and night. Every day new services …</div></div>
<div class="w200">[上海市] <a href="http://www.104china.com/JobAction.do?jobNo=181642&tag=0" target="_blank" class="blue">采购经理</a> </div>
<div class="w200 ml10">[中山市] <a href="http://www.104china.com/JobAction.do?jobNo=182625&tag=0" target="_blank" class="blue">Sales Support </a></div>
</div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 40px;"><a href="#" onmouseover="javascript:setFocus1(1);">联   想</a></div>
  <h3 style="left: 109px;">马士基物流</h3>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 251px;"><a href="#" onmouseover="javascript:setFocus1(3);">联邦快递</a></div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 352px;"><a href="#" onmouseover="javascript:setFocus1(4);">长安福特</a></div>
</li>
<li class=mbu  id="focusPic3" style="display:none;">
  <div id="boxii"><h2><a href="http://jobbank.104china.com/CompanyAction.do?custNo=20070326113623"  target="_blank">联邦快递(中国)有限公司上海分公司</a></h2>
<div class="dashed mt5 mb10" style="height:106px"><div class="fl mr10" style="width:205px"><img src="images/mingqi_03.gif" width="205" height="100" /></div>
<div class="fr mr5 lh20" style="width:185px;text-indent:2em">FedEx is one of the world's largest express transportation company, providing fast and reliable delivery service to…</div></div>
<div class="w200">[上海市] <a href="http://jobbank.104china.com/JobAction.do?jobNo=181225&tag=0" target="_blank" class="blue">市场专员</a> </div>
<div class="w200 ml10">[上海市] <a href="http://jobbank.104china.com/JobAction.do?jobNo=181182&tag=0" target="_blank" class="blue">客户主任 </a></div>
</div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 40px;"><a href="#" onmouseover="javascript:setFocus1(1);">联   想</a></div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 130px;"><a href="#" onmouseover="javascript:setFocus1(2);">马士基物流</a></div>
  <h3 style="left: 210px;">联邦快递</h3>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 335px;"><a href="#" onmouseover="javascript:setFocus1(4);">长安福特</a></div>
</li>
<li class=mbu id="focusPic4" style="display:none;">
  <div id="boxii"><h2><a href="http://jobbank.104china.com/CompanyAction.do?custNo=20070206110647"  target="_blank">长安福特马自达汽车有限公司</a></h2>
<div class="dashed mt5 mb10" style="height:106px"><div class="fl mr10" style="width:205px"><img src="images/mingqi_04.gif" width="205" height="100" /></div>
<div class="fr mr5 lh20" style="width:185px;text-indent:2em">2001年4月25日，世界领先的汽车公司—福特汽车公司和中国汽车的百年企业—长安汽车集团共同投资并签约成立了位于重庆的…</div></div>
<div class="w200">[重庆市] <a href="http://jobbank.104china.com/JobAction.do?jobNo=168376&tag=0" target="_blank" class="blue">行政助理</a> </div>
<div class="w200 ml10">[重庆市] <a href="http://jobbank.104china.com/JobAction.do?jobNo=181068&tag=0" target="_blank" class="blue">设施审核专员 </a></div>
</div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 40px;"><a href="#" onmouseover="javascript:setFocus1(1);">联   想</a></div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 130px;"><a href="#" onmouseover="javascript:setFocus1(2);">马士基物流</a></div>
  <div style="POSITION: absolute;TOP: 200px;HEIGHT: 33px;TEXT-ALIGN: center;left: 251px;"><a href="#" onmouseover="javascript:setFocus1(3);">联邦快递</a></div>
  <h3 style="left: 321px;">长安福特</h3>
</li>
</ul>
</div>
  </div>
<div id="notice">
<div><img src="images/notice_title.gif" /></div>
<div class="mt5">
<script type="text/javascript">
imgUrl1="images/flash_01.jpg";
imgLink1=escape("http://twrencai.104china.com");
imgUrl2="images/flash_02.jpg";
imgLink2=escape("http://www.informationweek.com.cn/research/08salary/");
imgUrl3="images/flash_03.jpg";
imgLink3=escape("http://www.104china.com/tw/080122/index.html?utm_source=104indexnotice&utm_medium=banner3&utm_content=logolink&utm_campaign=tt_080122_airticket");
imgUrl4="images/flash_04.jpg";
imgLink4=escape("http://www.104china.com/footerhtml/about/mkt0121/index.html");
imgUrl5="images/flash_05.jpg";
imgLink5=escape("http://hunter.104china.com");

 var focus_width=227
 var focus_height=133
 var swf_height = focus_height
 
 var pics=imgUrl1+"|"+imgUrl2+"|"+imgUrl3+"|"+imgUrl4+"|"+imgUrl5
 var links=imgLink1+"|"+imgLink2+"|"+imgLink3+"|"+imgLink4+"|"+imgLink5
 
 document.write('<object ID="focus_flash" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
 document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="http://www.104china.com/images/focus2.swf"><param name="quality" value="high"><param name="bgcolor" value="#FFFFFF">');
 document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
 document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&borderwidth='+focus_width+'&borderheight='+focus_height+'">');
 document.write('<embed ID="focus_flash" src="http://www.104china.com/images/focus2.swf" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&borderwidth='+focus_width+'&borderheight='+focus_height+'" menu="false" bgcolor="#C5C5C5" quality="high" width="'+ focus_width +'" height="'+ swf_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');  document.write('</object>');
</script>

</div>
<ul><li><a href="http://it.104china.com" target="_blank" class="redl">IT人才网新年火热上线！</a></li>
<li><a href="http://www.104china.com/footerhtml/about/mkt1229/index.html" target="_blank" class="blue">104人力银行董事长接受网易独家采访</a></li>
<li><a href="http://www.104china.com/tw/080122/index.html?utm_source=104indexnotice&utm_medium=test3&utm_content=testlink&utm_campaign=tt_080122_airticket" target="_blank" class="redl">沪港往返机票新年大抽奖！</a></li>
<li><a href="http://hunter.104china.com/" target="_blank" class="blue">猎头服务：帮你找人才，为你赢未来</a></li>
</ul>
</div>  
</div>
</body>
</html>