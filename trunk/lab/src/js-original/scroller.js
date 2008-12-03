var speed = 60;
var refreshSpeed = 300000;// 自动刷新5min
var counter = 0;
var MarqueeDiv0 = document.getElementById('marqueeBox0');
var MarqueeDiv0_1 = document.getElementById('marqueeBox0_1');
var MarqueeDiv0_2 = document.getElementById('marqueeBox0_2');

var MarqueeDiv1 = document.getElementById('marqueeBox1');
var MarqueeDiv1_1 = document.getElementById('marqueeBox1_1');
var MarqueeDiv1_2 = document.getElementById('marqueeBox1_2');

var Marquee0 = null;
var Marquee1 = null;

function initMarquee() {
	Message.getMessages({
		callback : function(data) {
			processMessages(data);
			setInterval(refreshMessages, refreshSpeed);
		},
		errorHandler : function(message) {
			showMsg("公告加载失败！");
		}
	});
}

function refreshMessages() {
	Message.getMessages({
		callback : function(data) {
			processMessages(data);
		},
		errorHandler : function(message) {
		}
	});
}

function refreshMarquee(type) {
	DWRUtil.useLoadingMessage("处理中...");
	Message.getMessagesByType(type, {
		callback : function(data) {
			cancelLoadingMessage();
			if (data != "null" && data != null) {
				var messages = Ext.decode(data);
				renderMessages(messages, type);
			}
		},
		errorHandler : function(message) {
			cancelLoadingMessage();
			showMsg("公告加载失败！");
		}
	});
}

function processMessages(data) {
	if (data != "null" && data != null) {
		var messages = Ext.decode(data);
		var m0 = messages.type0;
		var m1 = messages.type1;
		renderMessages(m0, 0);
		renderMessages(m1, 1);
	}
}

function renderMessages(messages, type) {
	if (type == 0)
		Ext.getDom("marqueeBox0_ul").innerHTML = "<li></li>";
	if (type == 1)
		Ext.getDom("marqueeBox1_ul").innerHTML = "<li></li>";
	for (var i = 0; i < messages.length; i++) {
		var li = document.createElement("li");
		var content = document.createElement("DIV");
		content.innerHTML = messages[i];
		li.appendChild(content);
		document.getElementById("marqueeBox" + type + "_ul").appendChild(li);
	}
	if (type == 0 && messages.length > 17) {
		MarqueeDiv0_2.innerHTML = MarqueeDiv0_1.innerHTML;
		if (Marquee0)
			clearInterval(Marquee0);
		MarqueeDiv0.scrollTop = 0;
		Marquee0 = setInterval(startMarquee0, speed);
	} else if (type == 1 && messages.length > 17) {
		MarqueeDiv1_2.innerHTML = MarqueeDiv1_1.innerHTML;
		if (Marquee1)
			clearInterval(Marquee1);
		MarqueeDiv1.scrollTop = 0;
		Marquee1 = setInterval(startMarquee1, speed);
	}
}

function startMarquee0() {
	if (MarqueeDiv0_2.offsetHeight - MarqueeDiv0.scrollTop <= 0)
		MarqueeDiv0.scrollTop -= MarqueeDiv0_1.offsetHeight;
	else
		MarqueeDiv0.scrollTop++;
}

function startMarquee1() {
	if (MarqueeDiv1_2.offsetHeight - MarqueeDiv1.scrollTop <= 0)
		MarqueeDiv1.scrollTop -= MarqueeDiv1_1.offsetHeight;
	else
		MarqueeDiv1.scrollTop++;
}

MarqueeDiv0.onmouseover = function() {
	clearInterval(Marquee0);
}
MarqueeDiv0.onmouseout = function() {
	Marquee0 = setInterval(startMarquee0, speed);
}
MarqueeDiv1.onmouseover = function() {
	clearInterval(Marquee1);
}
MarqueeDiv1.onmouseout = function() {
	Marquee1 = setInterval(startMarquee1, speed);
}