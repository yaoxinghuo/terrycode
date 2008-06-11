<!--
//script by http://www.yvoschaap.com

var changing = false;
var tempString = "";

function fieldEnter(campo,evt,idfld) {
	evt = (evt) ? evt : window.event;
	if (evt.keyCode == 13 && campo.value!="") {
		elem = document.getElementById( idfld );
		noLight(elem);
			Profile.changeTitle(campo.value,{
	        	callback:function(data) {
	        		if(data) {
		        		elem.innerHTML=campo.value;
						document.title=campo.value;
					} else {
						elem.innerHTML=tempString;
						showMsg("出现错误，没有更改!");
					}
				},
				timeout:8000,
				errorHandler:function(message){
					elem.innerHTML=tempString;
					showMsg("对不起，出现错误!");
				}
	        });
		
		changing = false;
		return false;
	} else {
		return true;
	}

}

function fieldBlur(campo,idfld) {
	if (campo.value!="") {
		elem = document.getElementById( idfld );
			Profile.changeTitle(campo.value,{
	        	callback:function(data) {
	        		if(data) {
		        		elem.innerHTML=campo.value;
						document.title=campo.value;
					} else {
						elem.innerHTML=tempString;
						showMsg("出现错误，没有更改!");
					}
				},
				timeout:8000,
				errorHandler:function(message){
					elem.innerHTML=tempString;
					showMsg("对不起，出现错误!");
				}
	        });
		changing = false;
		return false;
	}
}

//edit field created
function editBox(actual) {
	tempString=actual.innerHTML;
	if(!changing){
		width = widthEl(actual.id) + 20;
		//height =heightEl(actual.id) + 2;
		height =heightEl(actual.id);

		if(height < 40){
			if(width < 100)	width = 150;
			actual.innerHTML = "<input id=\""+ actual.id +"_field\" style=\"width: "+width+"px; height: "+height+"px;\" maxlength=\"254\" type=\"text\" value=\"" + actual.innerHTML + "\" onkeypress=\"return fieldEnter(this,event,'" + actual.id + "')\" onfocus=\"highLight(this);\" onblur=\"noLight(this); return fieldBlur(this,'" + actual.id + "');\" />";
		}else{
			if(width < 70) width = 90;
			if(height < 50) height = 50;
			actual.innerHTML = "<textarea name=\"textarea\" id=\""+ actual.id +"_field\" style=\"width: "+width+"px; height: "+height+"px;\" onfocus=\"highLight(this);\" onblur=\"noLight(this); return fieldBlur(this,'" + actual.id + "');\">" + actual.innerHTML + "</textarea>";
		}
		DWRUtil.selectRange(actual.id+"_field",0,actual.innerHTML.length);
		changing = true;
	}

		actual.firstChild.focus();
}



//find all span tags with class editText and id as fieldname parsed to update script. add onclick function
function editbox_init(){
	if (!document.getElementsByTagName){ return; }
	var spans = document.getElementsByTagName("span");

	// loop through all span tags
	for (var i=0; i<spans.length; i++){
		var spn = spans[i];

        	if (((' '+spn.className+' ').indexOf("editText") != -1) && (spn.id)) {
			spn.onclick = function () { editBox(this); }
			spn.style.cursor = "pointer";
			spn.onmouseover = function () { this.style.background = "rgb(100%,100%,50%)"; };
			spn.onmouseout = function () { this.style.background = "rgb(255,255,255)"; };
			spn.title = "请点击修改!";	
       		}

	}


}

//crossbrowser load function
function addEvent(elm, evType, fn, useCapture)
{
  if (elm.addEventListener){
    elm.addEventListener(evType, fn, useCapture);
    return true;
  } else if (elm.attachEvent){
    var r = elm.attachEvent("on"+evType, fn);
    return r;
  } else {
    alert("Please upgrade your browser to use full functionality on this page");
  }
}

//get width of text element
function widthEl(span){

	if (document.layers){
	  w=document.layers[span].clip.width;
	} else if (document.all && !document.getElementById){
	  w=document.all[span].offsetWidth;
	} else if(document.getElementById){
	  w=document.getElementById(span).offsetWidth;
	}
return w;
}

//get height of text element
function heightEl(span){

	if (document.layers){
	  h=document.layers[span].clip.height;
	} else if (document.all && !document.getElementById){
	  h=document.all[span].offsetHeight;
	} else if(document.getElementById){
	  h=document.getElementById(span).offsetHeight;
	}
return h;
}

function highLight(span){
            //span.parentNode.style.border = "2px solid #D1FDCD";
            //span.parentNode.style.padding = "0";
            span.style.border = "1px solid #54CE43";        
}

function noLight(span){
        //span.parentNode.style.border = "0px";
        //span.parentNode.style.padding = "2px";
        span.style.border = "0px";   


}

//addEvent(window, "load", editbox_init);
-->