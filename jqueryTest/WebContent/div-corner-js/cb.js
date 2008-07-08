var cbb = {
	init : function() {
	// 判断浏览器是否支持dom
		if (!document.getElementById || !document.createElement || !document.appendChild) return false;
		var oElement, oOuter, oI1, oI2, tempId;
	// 查找所有的 classname 为cbb 的元素
		var arrElements = document.getElementsByTagName('*');
		var oRegExp = new RegExp("(^|\\s)cbb(\\s|$)");
		for (var i=0; i<arrElements.length; i++) {
	//  保存原来的外因素,为稍后调用
			oElement = arrElements[i];
			if (oRegExp.test(oElement.className)) {
	// 创建一个新的元素，并给它原来的元素的class（或名称） ，用cb代替cbb,
			oOuter = document.createElement('div');
				oOuter.className = oElement.className.replace(oRegExp, '$1cb$2');
	// 创建一个div 。 
				if (oElement.getAttribute("id")) {
					tempId = oElement.id;
					oElement.removeAttribute('id');
					oOuter.setAttribute('id', '');
					oOuter.id = tempId;
				}
	// 改变原来的元素的类别名称 ,用一个新的取代它。
				oElement.className = 'i3';
                oElement.innerHTML = "<div class=\'corner_close\'><span  id=\'corner_close\' onclick=\'document.body.removeChild( document.getElementById(\"corner_div_id\") );out_biaozhi=false;abc = \"\";\'><img src=\"title_close_image.gif\"/></span></div><div>"+oElement.innerHTML+"</div>";

				oElement.parentNode.replaceChild(oOuter, oElement);
	// 创建两个新的div，并插入到最外层
				oI1 = document.createElement('div');
				oI1.className = 'i1';
				oOuter.appendChild(oI1);
				oI2 = document.createElement('div');
				oI2.className = 'i2';
				oI1.appendChild(oI2);
	//   插入原来的元素 
				oI2.appendChild(oElement);
	//    插入 头部和底部 2个div .
				cbb.insertTop(oOuter);
				cbb.insertBottom(oOuter);
			}
		}
	},
	insertTop : function(obj) {
		var oOuter, oInner;
	// 创建一个头部div元素
		oOuter=document.createElement("div");
		oOuter.className="bt"; // The outer div needs a class name
	    oInner=document.createElement("div");
	    oOuter.appendChild(oInner);
		obj.insertBefore(oOuter,obj.firstChild);
	},
	

	insertBottom : function(obj) {
		var oOuter, oInner;
	// 创建一个底部div元素
		oOuter=document.createElement("div");
		oOuter.className="bb"; // The outer div needs a class name
	    oInner=document.createElement("div");
	    oOuter.appendChild(oInner);
		obj.appendChild(oOuter);
	},
	// addEvent function 
	addEvent : function(obj, type, fn) {
		if (obj.addEventListener)
			obj.addEventListener(type, fn, false);
		else if (obj.attachEvent) {
			obj["e"+type+fn] = fn;
			obj[type+fn] = function() { obj["e"+type+fn]( window.event ); }
			obj.attachEvent("on"+type, obj[type+fn]);
		}
	}
};


	
	function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {  
    window.onload = function() {
      oldonload();
      func();
    }
  }
}


 var out_biaozhi=true;
 addLoadEvent(function(){
	 document.onclick = function(e){
	      var e = window.event || e;
	      var src = e.srcElement || e.target; // src 就是事件的触发源
          if(document.getElementById("corner_div_id")){
	            if(out_biaozhi && src != document.getElementById("corner_div_id")&& "INPUT"!=src.tagName){   
				    document.body.removeChild( document.getElementById("corner_div_id") );
					abc = "";
	            }
	      }
		}
});




function initDiv( width  ,top , left , details , event){
	  if(document.getElementById("corner_div_id"))
	 {
        document.body.removeChild( document.getElementById("corner_div_id") );
	 }
	 	var  div_a  = document.createElement("div");
	    div_a.id = "corner_div_id";
		div_a.style.position = "absolute";
        div_a.onmouseover = function(){
            out_biaozhi = false;
		}
        div_a.onmouseout = function(){
            out_biaozhi = true;
		}
		var event =  event  || window.event;
		var mX = event.x ? event.x : event.pageX ;
		var mY = event.y ? event.y : event.pageY ;
		var top = top || mY ;
		var left = left || mX ; 

		div_a.style.top = top+"px";
		div_a.style.left = left+"px";
		div_a.style.width =  width ;
		div_a.style.maxWidth = width ;
		div_a.style.margin = "0 auto";
		div_a.style.wordBreak = "break-all";
		div_a.style.overflow = "hidden";

		var  div_b  = document.createElement("div");
		div_b.className = "cbb";
		div_b.innerHTML = details ;
		div_a.appendChild(div_b);
		document.body.appendChild(div_a);

		
}

function createCornerDiv( width , top , left , details  , event)
{
  initDiv(width , top , left , details , event);
  cbb.init();
}

createCornerDiv.prototype.closeDiv = function(){
    document.body.removeChild( document.getElementById("corner_div_id") );
}