var Drag={dragged:false,
		ao:null,
		tdiv:null,
dragStart:function(){
	Drag.ao=event.srcElement;
	if((Drag.ao.tagName=="TD")||(Drag.ao.tagName=="TR")){
		Drag.ao=Drag.ao.offsetParent;
		Drag.ao.style.zIndex=100;
	}else
		return;
	Drag.dragged=true;
	Drag.tdiv=document.createElement("div");
	Drag.tdiv.innerHTML=Drag.ao.outerHTML;
	Drag.ao.style.border="1px dashed red";
	Drag.tdiv.style.display="block";
	Drag.tdiv.style.position="absolute";
	Drag.tdiv.style.filter="alpha(opacity=70)";
	Drag.tdiv.style.cursor="move";
	Drag.tdiv.style.border="1px solid #000000";
	Drag.tdiv.style.width=Drag.ao.offsetWidth;
	Drag.tdiv.style.height=Drag.ao.offsetHeight;
	Drag.tdiv.style.top=Drag.getInfo(Drag.ao).top;
	Drag.tdiv.style.left=Drag.getInfo(Drag.ao).left;
	document.body.appendChild(Drag.tdiv);
	Drag.lastX=event.clientX;
	Drag.lastY=event.clientY;
	Drag.lastLeft=Drag.tdiv.style.left;
	Drag.lastTop=Drag.tdiv.style.top;
},

 draging:function(){//重要:判断MOUSE的位置
	if(!Drag.dragged||Drag.ao==null)return;
	var tX=event.clientX;
	var tY=event.clientY;
	Drag.tdiv.style.left=parseInt(Drag.lastLeft)+tX-Drag.lastX;
	Drag.tdiv.style.top=parseInt(Drag.lastTop)+tY-Drag.lastY;
	for(var i=0;i<parentTable.cells.length;i++){
		var parentCell=Drag.getInfo(parentTable.cells[i]);
		if(tX>=parentCell.left&&tX<=parentCell.right&&tY>=parentCell.top&&tY<=parentCell.bottom){
			var subTables=parentTable.cells[i].getElementsByTagName("table");
			if(subTables.length==0){
				if(tX>=parentCell.left&&tX<=parentCell.right&&tY>=parentCell.top&&tY<=parentCell.bottom){
					parentTable.cells[i].appendChild(Drag.ao);
				}
				break;
			}
			for(var j=0;j<subTables.length;j++){
				var subTable=Drag.getInfo(subTables[j]);
				if(tX>=subTable.left&&tX<=subTable.right&&tY>=subTable.top&&tY<=subTable.bottom){
					parentTable.cells[i].insertBefore(Drag.ao,subTables[j]);
					break;
				}else{
					parentTable.cells[i].appendChild(Drag.ao);
				}	
			}
		}
	}
}
,
 dragEnd:function(){
	if(!Drag.dragged)return;
	Drag.dragged=false;
	Drag.mm=Drag.repos(150,15);
	Drag.ao.style.borderWidth="0px";
	Drag.ao.style.borderTop="1px solid #3366cc";
	Drag.tdiv.style.borderWidth="0px";
	Drag.ao.style.zIndex=1;
},
getInfo:function(o){//取得坐标
	var to=new Object();
	to.left=to.right=to.top=to.bottom=0;
	var twidth=o.offsetWidth;
	var theight=o.offsetHeight;
	while(o!=document.body){
		to.left+=o.offsetLeft;
		to.top+=o.offsetTop;
		o=o.offsetParent;
	}
		to.right=to.left+twidth;
		to.bottom=to.top+theight;
	return to;
},
repos:function(aa,ab){
	var f=Drag.tdiv.filters.alpha.opacity;
	var tl=parseInt(Drag.getInfo(Drag.tdiv).left);
	var tt=parseInt(Drag.getInfo(Drag.tdiv).top);
	var kl=(tl-Drag.getInfo(Drag.ao).left)/ab;
	var kt=(tt-Drag.getInfo(Drag.ao).top)/ab;
	var kf=f/ab;
	return setInterval(function(){if(ab<1){
							clearInterval(Drag.mm);
							Drag.tdiv.removeNode(true);
							Drag.ao=null;
							return;
						}
					ab--;
					tl-=kl;
					tt-=kt;
					f-=kf;
					Drag.tdiv.style.left=parseInt(tl)+"px";
					Drag.tdiv.style.top=parseInt(tt)+"px";
					Drag.tdiv.filters.alpha.opacity=f;
				}
,aa/ab)
},
 inint:function(){//初始化
	for(var i=0;i<parentTable.cells.length;i++){
		var subTables=parentTable.cells[i].getElementsByTagName("table");
		for(var j=0;j<subTables.length;j++){
			if(subTables[j].className!="dragTable")break;
			subTables[j].rows[0].className="dragTR";
			subTables[j].rows[0].attachEvent("onmousedown",Drag.dragStart);
		}
	}
	document.onmousemove=Drag.draging;
	document.onmouseup=Drag.dragEnd;
}
//end of Object Drag
}
Drag.inint();

function _show(str){
	var w=window.open('','');
	var d=w.document;
	d.open();
	str=str.replace(/=(?!")(.*?)(?!")( |>)/g,"=\"$1\"$2");
	str=str.replace(/(<)(.*?)(>)/g,"<span style='color:red;'><$2></span><br />");
	str=str.replace(/\r/g,"<br />\n");
	d.write(str);
}