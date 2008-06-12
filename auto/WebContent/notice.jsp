<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>

<div id="notice">
<div><img src="images/notice_title.gif" /></div>
<div class="mt5"><script type="text/javascript">
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
</script></div>
<ul>
	<li><a href="http://it.104china.com" target="_blank" class="redl">IT人才网新年火热上线！</a></li>
	<li><a
		href="http://www.104china.com/footerhtml/about/mkt1229/index.html"
		target="_blank" class="blue">104人力银行董事长接受网易独家采访</a></li>
	<li><a
		href="http://www.104china.com/tw/080122/index.html?utm_source=104indexnotice&utm_medium=test3&utm_content=testlink&utm_campaign=tt_080122_airticket"
		target="_blank" class="redl">沪港往返机票新年大抽奖！</a></li>
	<li><a href="http://hunter.104china.com/" target="_blank"
		class="blue">猎头服务：帮你找人才，为你赢未来</a></li>
</ul>
</div>
</body>
</html>