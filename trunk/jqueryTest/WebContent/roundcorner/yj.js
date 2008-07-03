// JavaScript Document
jQuery.fn.yjcolor= function(a, b,c,d,e) {
	$('.content',this).wrap('<div class="xboxcontent"></div>')
	var ai='<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>'
var bi='<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>'
var ci='<div style=" border-top:1px '+a+' solid;"></div>'
switch(d){
	case "1":
	$(this).prepend(ai);
	$(this).append(ci);
	break;
	case "2":
	$(this).prepend(ci);
	$(this).append(bi);
	break;
	default:
	$(this).prepend(ai);
	$(this).append(bi);
break;
	};
	
$(this).addClass("xsnazzy");
var str="progid:DXImageTransform.Microsoft.Gradient(startColorStr='"+b.toUpperCase()+"',endColorStr='"+c.toUpperCase()+"',gradientType='0' );"
 $(".xboxcontent,.xb2,.xb3,.xb4,.title",this).css("border-color",a);
$(".xb1",this).css("background",a);
$(".xb2,.xb3,.xb4",this).css("background",b);
$(".title",this).css("filter",str)
if (e){
	$(".content",this).css("background-color",e)}
  }
$(function(){
		   $("div.yj").yjcolor("#d19b5f","#fde2b7","#ffd187","1");
			$(".title","div.yj").css("color","#7B2E00")	
			$(".title","div.yj1").css("color","#0C2D55")
$("div.yj1").yjcolor("#bac3c8","#f5fcff","#d9e1ee","1");
//$("div.yj2").yjcolor("#bf686e","#fd8d89","#fd3134","1");
//$("div.yj3").yjcolor("#d19b5f","#fde2b7","#ffd187");
})