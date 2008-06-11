var marqueeContent=new Array();   //滚动新闻
var marqueeBox=null;
var marqueeInterval=new Array();  //定义一些常用而且要经常用到的变量
var marqueeId=0;
var marqueeDelay=2000;
var marqueeRefreshInterval=10*60*1000;//每多少分钟检查是否更新消息 10min
var marqueeHeight=20;
//接下来的是定义一些要使用到的函数
function initMarquee(position) {
	Message.getMessages(tabTypeArray[position],{
    	callback:function(data) {
    		if(data!="null"&&data!=null) {
    			var messages = data.evalJSON();
    			if(messages.length==0)
    				return;
    			marqueeContent = messages;
    			var str=marqueeContent[0];
				marqueeId=0;
				marqueeBox = null;
				marqueeBox = $("marqueeBox_"+position);
				if(marqueeInterval[0])
					clearInterval(marqueeInterval[0]);
				if(marqueeInterval[1])
					clearInterval(marqueeInterval[1]);
				if(marqueeInterval[2])
					clearInterval(marqueeInterval[2]);
				marqueeInterval[0]=setInterval("startMarquee()",marqueeDelay);
				marqueeInterval[2]=setInterval("marqueeRefreshContent("+position+")",marqueeRefreshInterval);
    		}
		},
		timeout:8000,
		errorHandler:function(message){
		}
    });
}
function startMarquee() {
  var str=marqueeContent[marqueeId];
    marqueeId++;
  if(marqueeId>=marqueeContent.length) marqueeId=0;
  if(marqueeBox.childNodes.length==1) {
    var nextLine=document.createElement('DIV');
    nextLine.innerHTML=str;
    marqueeBox.appendChild(nextLine);
    }
  else {
    marqueeBox.childNodes[0].innerHTML=str;
    marqueeBox.appendChild(marqueeBox.childNodes[0]);
    marqueeBox.scrollTop=0;
    }
  clearInterval(marqueeInterval[1]);
  marqueeInterval[1]=setInterval("scrollMarquee()",20);
  }
function scrollMarquee() {
  marqueeBox.scrollTop++;
  if(marqueeBox.scrollTop%marqueeHeight==(marqueeHeight-1)){
    clearInterval(marqueeInterval[1]);
    }
  }
  
function marqueeRefreshContent(position){
	Message.getMessages(tabTypeArray[position],{
    	callback:function(data) {
    		if(data!="null"&&data!=null) {
    			var messages = data.evalJSON();
    			if(messages.length==0)
    				return;
    			marqueeContent = messages;
    		}
		},
		timeout:8000,
		errorHandler:function(message){
		}
    });
}