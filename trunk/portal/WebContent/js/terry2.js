function link_showAddContentPanel(tab){ 
	showById(tab+"_addContentPanel");
	hideById(tab+"_addContentButton");
	if($(tab+"_selectType1").innerHTML.trim()=="") 
		link_showSelectOptions1(tab);
}

function link_showSelectOptions1(id){
	DWRUtil.removeAllOptions(id+"_selectType1");
	DWRUtil.addOptions(id+"_selectType1",[{"id":"","value":"--加载中--"}],"id","value");
	DWRTemplate.getSelectOptions("0",{
		callback:function(data) {
		    DWRUtil.removeAllOptions(id+"_selectType1");
		    DWRUtil.addOptions(id+"_selectType1",data);
		    link_showSelectOptions2(id);
		},
		timeout:8000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
		}
	});
}

function link_showSelectOptions2(id){
	DWRUtil.removeAllOptions(id+"_selectType2");
	DWRUtil.addOptions(id+"_selectType2",[{"id":"","value":"--加载中--"}],"id","value");
	$(id+"_addButton").disabled=true;
	$(id+"_linksPreviousButton").disabled=true;
	var lastValue=DWRUtil.getValue(id+"_selectType1");
	DWRTemplate.getSelectOptions(lastValue,{
		callback:function(data) {
	    	DWRUtil.removeAllOptions(id+"_selectType2");
	        DWRUtil.addOptions(id+"_selectType2",data);
	        if(DWRUtil.getValue(id+"_selectType2")!=""){
	        	$(id+"_linksPreviousButton").disabled=false;
	        }
			$(id+"_addButton").disabled=false;
			if(!$(id+"_changeTitleDefault").checked)
		        $(id+"_titleText").value=DWRUtil.getText(id+"_selectType1");
	    },
		timeout:8000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
		}
	});
}

function link_showSelectOptions3(tab){
}

function link_addModuleToTab(position){
	showMsg("有待实现。");
	return;
	var tab = "tab"+position;
	var title = $(tab+"_titleText").value;
	var showNumber = parseInt($(tab+"_selectShowNumber").value);
	var type1 = $(tab+"_selectType1").value;
	var type2 = $(tab+"_selectType2").value;
	var col = $(tab+"_addColPosition").value;
	var settings = {
		"title":"搜索","showNumber":showNumber,
		"position":position,"type1":type1,
		"type2":type2,"type":2,"share":true
	};
	DWRUtil.useLoadingMessage("正在加载...");
	DWRTemplate.getNewLinkModuleString(position,col,Object.toJSON(settings),{
        	callback:function(data) {
        		cancelLoadingMessage();
        		if(data==null){
        			showMsg("对不起，出现错误!");
        		} else {
	        		var d = data.evalJSON();
	        		$(tab+"_col"+col).innerHTML=d.content+$(tab+"_col"+col).innerHTML;
	        		initTabDrag(position);
	        		showMsg("模块已成功添加！");
	        		renderTabLinks(d.row_id,DWRUtil.getValue(id+"_settings"));
        		}
			},
			timeout:8000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
				cancelLoadingMessage();
			}
        });
}

function link_addcModuleToTab(position){
	var tab = "tab"+position;
	var title = $(tab+"_ctitleText").value;
	if(title.trim()=="") {
		showMsg("对不起,标题不能为空！");
		return;
	}
	var show_tip = $(tab+"_checkcShowTip").checked;
	var col = $(tab+"_addcColPosition").value;
	var settings = {
		"title":title,"position":position,"type":3,
		"share":$(tab+"_checkcShare").checked,
		"show_tip":show_tip
	};
	DWRUtil.useLoadingMessage("正在加载...");
	DWRTemplate.getcNewLinkModuleString(position,col,Object.toJSON(settings),{
        	callback:function(data) {
        		cancelLoadingMessage();
        		if(data==null){
        			showMsg("对不起，出现错误!");
        		} else {
        			var d = data.evalJSON();
	        		$(tab+"_col"+col).innerHTML=d.content+$(tab+"_col"+col).innerHTML;
	        		initTabDrag(position);
	        		blankLinkList(d.row_id);
	        		showMsg("模块已成功添加！");
        		}
			},
			timeout:8000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
				cancelLoadingMessage();
			}
        });
}

function linksPreview(position){
	showMsg("有待实现。");
}

function renderLinks(position){
	for(var tabI=0;tabI<3;tabI++){
		var j=0;
		var id=null;
		for(i=0;i<$("tab"+position+"_col"+tabI).childNodes.length-1;i++){
			if($("tab"+position+"_col"+tabI).childNodes[i].nodeType==1) {
				id=$("tab"+position+"_col"+tabI).childNodes[i].getAttribute("id");
				if(id!=null&&id!=""){
					renderTabLinks(id,DWRUtil.getValue(id+"_settings"));
				};
				j++;
				}
			}
	}
}

function renderTabLinks(id,settings){
	hideById(id+"_message");
	var oSettings = settings.evalJSON();
	DWRTemplate.getLinks(oSettings.id,{
		callback:function(data) {
			var o = data.evalJSON();
			$(id+"_settings").value=settings;
			linksList(id,o.links);
			reDragMouseRegister();
		},
		timeout:5000,
		errorHandler:function(message){
			$(id+"_message").innerHTML="<font color='red'>对不起，出现错误!"+message+"</font>";
			showById(id+"_message");
		}
	});
}

function blankLinkList(id){
	var table = $(id+"_table");
	table.innerHTML="";
	var div=document.createElement("DIV");
	div.setAttribute("id","DragContainer_"+id);
	DragDrops[0].push(div);
	div.setAttribute('DropObj', 0);
	div.className="DragContainer";
	table.appendChild(div);
}

function linksList(id,data){
	var table = $(id+"_table");
	table.innerHTML="";
	var settings = $(id+"_settings").value.evalJSON();
	settings.total=data.length;
	$(id+"_settings").value=Object.toJSON(settings);
	var div=document.createElement("DIV");
	div.setAttribute("id","DragContainer_"+id);
	DragDrops[0].push(div);
	div.setAttribute('DropObj', 0);
	div.className="DragContainer";
	for(var i=0;i<data.length;i++){
		var tipString = "<table><tr><td>标题：</td><td>"+data[i].title+"</td></tr>"+
		"<tr><td>链接：</td><td>"+data[i].link+"</td></tr>"+
		"<tr><td>备注：</td><td>"+data[i].desc+"</td></tr>"+
		"<tr><td>修改日期：</td><td>"+data[i].time+"</td></tr></table>";
		var html='<DIV class="DragBox" linkid="'+data[i].id+'" id=Item_'+ id + i
		+' overclass="OverDragBox" DragObj="0" dragclass="DragDragBox" onmouseOver=link_showEditFuc(this,"'+id+i+'"); onmouseOut=link_hideEditFuc(this,"'+id+i+'");>'
		+'<span ';
		if(settings.show_tip)
			html+='onmouseover="Tip(\''+tipString+'\',SHADOW, true, FADEIN, 300, FADEOUT, 300, TITLE,\''+
		data[i].title+'\', BGCOLOR, \'#ffcccc\', FONTCOLOR, \'#800000\', FONTSIZE, \'9pt\', FONTFACE, \'Courier New, Courier, mono\', BORDERCOLOR, \'#c00000\',STICKY, 1, OFFSETX, 20, CLOSEBTN, true, CLICKCLOSE, true)"; ';
		html+='id="link_show_'+
		id+i+'"><a target="about:blank" href="'+data[i].link+'">'+data[i].title+'</a></span>--'+link_trimDescString(data[i].desc)+
		' <span style="visibility:hidden" id="edit_'+id+i+'" >'+
		'<a onclick=link_edit("'+id+i+'","'+data[i].id+'");>Edit</a> '+
		'<a onclick=link_delete("'+id+'","'+i+'","'+data[i].id+'","'+data[i].title+'");>Delete</a> '+
		'</span></DIV>';
		div.innerHTML+=html;
	}
	table.appendChild(div);
}

function link_edit(compid,id){
	DWRUtil.useLoadingMessage("正在加载...");
	DWRTemplate.getLinkEditString(compid,id,{
    	callback:function(data) {
    		cancelLoadingMessage();
    		if(data!="null"&&data!=null) {
    			$("tip_div_content").innerHTML=data;
    			$("tip_div_title").innerHTML="编辑链接";
    			_currentTipDivParentId = "edit_"+compid;
    			showTipDiv();
    		}
		},
		timeout:8000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
			cancelLoadingMessage();
		}
    });
}

function link_updateLink(compid,id,comp,olddesc){
	hideTipDiv();
	var formValue = DWRUtil.getValues(comp.form);
	DWRTemplate.updateLink(id,Object.toJSON(formValue),{
		callback:function(updated) {
			if(updated!="null"||updated!=null){
				$("Item_"+compid).innerHTML=$("Item_"+compid).innerHTML.replace("--"+olddesc,"--"+link_trimDescString(formValue.desc));
				$("link_show_"+compid).innerHTML='<a href="'+formValue.link+'">'+formValue.title+'</a>';
				$("link_show_"+compid).onmouseover=function(){
					var tipString = "<table><tr><td>标题：</td><td>"+formValue.title+"</td></tr>"+
						"<tr><td>链接：</td><td>"+formValue.link+"</td></tr>"+
						"<tr><td>备注：</td><td>"+formValue.desc+"</td></tr>"+
						"<tr><td>修改日期：</td><td>"+updated+"</td></tr></table>";
					Tip(tipString, SHADOW, true, FADEIN, 300, FADEOUT, 300, TITLE, formValue.title, BGCOLOR, '#ffcccc', FONTCOLOR, '#800000', FONTSIZE, '9pt', FONTFACE, 'Courier New, Courier, mono', BORDERCOLOR, '#c00000',STICKY, 1, OFFSETX, 20, CLOSEBTN, true, CLICKCLOSE, true);
				}
				showMsg("您已经成功更新: "+formValue.title+" !");
			}
		},
		timeout:5000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
		}
	});
}

function link_trimDescString(oStr){
	oStr = oStr.replace(new RegExp("&nbsp;","gm"),"").replace(new RegExp(" ","gm"),"").replace(new RegExp(" ","gm"),"");
	if(oStr.length>16)
		oStr = oStr.substring(0,13)+"...";
	return oStr;
}

function link_add(baseid,row_id){
	DWRUtil.useLoadingMessage("正在加载...");
	DWRTemplate.getLinkAddString(baseid, row_id,{
    	callback:function(data) {
    		cancelLoadingMessage();
    		if(data!="null"&&data!=null) {
    			$("tip_div_content").innerHTML=data;
    			$("tip_div_title").innerHTML="添加链接";
    			_currentTipDivParentId = "link_add_"+baseid;
    			showTipDiv();
    		}
		},
		timeout:8000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
			cancelLoadingMessage();
		}
    });
}

function link_addLink(baseid, comp){
	hideTipDiv();
	var settings = $(baseid+"_settings").value.evalJSON();
	var formValue = DWRUtil.getValues(comp.form);
	formValue.i=settings.total+1;
	DWRTemplate.addLink(Object.toJSON(formValue),{
		callback:function(data) {
			if(data!="null"&&data!=null){
				var d = data.evalJSON();
				var i = settings.total+1;
				settings.total=i;
				var tipString = "<table><tr><td>标题：</td><td>"+formValue.title+"</td></tr>"+
					"<tr><td>链接：</td><td>"+formValue.link+"</td></tr>"+
					"<tr><td>备注：</td><td>"+formValue.desc+"</td></tr>"+
					"<tr><td>修改日期：</td><td>"+d.time+"</td></tr></table>";
				var html='<DIV class="DragBox" linkid="'+d.id+'" id=Item_'+ baseid + i
				+' overclass="OverDragBox" DragObj="0" dragclass="DragDragBox" onmouseOver=link_showEditFuc(this,"'+baseid+i+'"); onmouseOut=link_hideEditFuc(this,"'+baseid+i+'");>'
				+'<span ';
				if(settings.show_tip)
					html+='onmouseover="Tip(\''+tipString+'\',SHADOW, true, FADEIN, 300, FADEOUT, 300, TITLE,\''+
					formValue.title+'\', BGCOLOR, \'#ffcccc\', FONTCOLOR, \'#800000\', FONTSIZE, \'9pt\', FONTFACE, \'Courier New, Courier, mono\', BORDERCOLOR, \'#c00000\',STICKY, 1, OFFSETX, 20, CLOSEBTN, true, CLICKCLOSE, true)"; ';
				html+='id="link_show_'+
				baseid+i+'"><a target="about:blank" href="'+formValue.link+'">'+formValue.title+'</a></span>--'+formValue.desc+
				' <span style="visibility:hidden" id="edit_'+baseid+i+'" >'+
				'<a onclick=link_edit("'+baseid+i+'","'+d.id+'");>Edit</a> '+
				'<a onclick=link_delete("'+baseid+'","'+i+'","'+d.id+'","'+formValue.title+'");>Delete</a> '+
				'</span></DIV>';
				$("DragContainer_"+baseid).innerHTML+=html;
				$(baseid+"_settings").value=Object.toJSON(settings);
				showMsg("您已经成功添加: "+formValue.title+" !");
			}
		},
		timeout:5000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
		}
	});
}


function link_delete(baseid,i,id,title){
	if(confirm("您确定要删除: "+title+" ?")){
		DWRTemplate.removeLink(id,{
			callback:function(removed) {
				if(removed){
					$("Item_"+baseid+i).parentNode.removeChild($("Item_"+baseid+i));
					var settings = $(baseid+"_settings").value.evalJSON();
					settings.total--;
					$(baseid+"_settings").value=Object.toJSON(settings);
					showMsg("您已经成功删除: "+title+" !");
				}
			},
			timeout:5000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
			}
		});
	}
}

function link_showEditFuc(comp,id){
	comp.style.background='#ffff99';
	$("edit_"+id).style.visibility='inherit';
}

function link_hideEditFuc(comp,id){
	comp.style.background='white';
	$("edit_"+id).style.visibility='hidden';
}

function link_clearOrderSettings(position){
	for(var tabI=0;tabI<3;tabI++){
		var id=null;
		for(i=0;i<$("tab"+position+"_col"+tabI).childNodes.length-1;i++){
			if($("tab"+position+"_col"+tabI).childNodes[i].nodeType==1) {
				id=$("tab"+position+"_col"+tabI).childNodes[i].getAttribute("id");
				if(id!=null&&id!=""){
					var settings = DWRUtil.getValue(id+"_settings").evalJSON();
					if(settings.order!=""){
						settings.order="";
						$(id+"_settings").value=Object.toJSON(settings);
					}
				};
			}
		}
	}
}