var speed=60;var refreshSpeed=300000;var counter=0;var MarqueeDiv0=document.getElementById("marqueeBox0");var MarqueeDiv0_1=document.getElementById("marqueeBox0_1");var MarqueeDiv0_2=document.getElementById("marqueeBox0_2");var MarqueeDiv1=document.getElementById("marqueeBox1");var MarqueeDiv1_1=document.getElementById("marqueeBox1_1");var MarqueeDiv1_2=document.getElementById("marqueeBox1_2");var Marquee0=null;var Marquee1=null;function initMarquee(){Message.getMessages({callback:function(A){processMessages(A);setInterval(refreshMessages,refreshSpeed)},errorHandler:function(A){showMsg("公告加载失败！")}})}function refreshMessages(){Message.getMessages({callback:function(A){processMessages(A)},errorHandler:function(A){}})}function refreshMarquee(A){DWRUtil.useLoadingMessage("处理中...");Message.getMessagesByType(A,{callback:function(C){cancelLoadingMessage();if(C!="null"&&C!=null){var B=Ext.decode(C);renderMessages(B,A)}},errorHandler:function(B){cancelLoadingMessage();showMsg("公告加载失败！")}})}function processMessages(D){if(D!="null"&&D!=null){var C=Ext.decode(D);var B=C.type0;var A=C.type1;renderMessages(B,0);renderMessages(A,1)}}function renderMessages(E,C){if(C==0){Ext.getDom("marqueeBox0_ul").innerHTML="<li></li>"}if(C==1){Ext.getDom("marqueeBox1_ul").innerHTML="<li></li>"}for(var B=0;B<E.length;B++){var A=document.createElement("li");var D=document.createElement("DIV");D.innerHTML=E[B];A.appendChild(D);document.getElementById("marqueeBox"+C+"_ul").appendChild(A)}if(C==0&&E.length>17){MarqueeDiv0_2.innerHTML=MarqueeDiv0_1.innerHTML;if(Marquee0){clearInterval(Marquee0)}MarqueeDiv0.scrollTop=0;Marquee0=setInterval(startMarquee0,speed)}else{if(C==1&&E.length>17){MarqueeDiv1_2.innerHTML=MarqueeDiv1_1.innerHTML;if(Marquee1){clearInterval(Marquee1)}MarqueeDiv1.scrollTop=0;Marquee1=setInterval(startMarquee1,speed)}}}function startMarquee0(){if(MarqueeDiv0_2.offsetHeight-MarqueeDiv0.scrollTop<=0){MarqueeDiv0.scrollTop-=MarqueeDiv0_1.offsetHeight}else{MarqueeDiv0.scrollTop++}}function startMarquee1(){if(MarqueeDiv1_2.offsetHeight-MarqueeDiv1.scrollTop<=0){MarqueeDiv1.scrollTop-=MarqueeDiv1_1.offsetHeight}else{MarqueeDiv1.scrollTop++}}MarqueeDiv0.onmouseover=function(){clearInterval(Marquee0)};MarqueeDiv0.onmouseout=function(){Marquee0=setInterval(startMarquee0,speed)};MarqueeDiv1.onmouseover=function(){clearInterval(Marquee1)};MarqueeDiv1.onmouseout=function(){Marquee1=setInterval(startMarquee1,speed)};