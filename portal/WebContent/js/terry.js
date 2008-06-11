var _currentPosition=0;
var _lastPosition=0;
var _currentTipDivParentId="";
var tabTypeArray=new Array();

String.prototype.trim = function() {
	var reExtraSpace = /^\s*(.*?)\s+$/;
	return this.replace(reExtraSpace,"$1");
}

function doOnload(){
	// Create our helper object that will show the item while dragging
	dragHelper = document.createElement('DIV');
	dragHelper.style.cssText = 'position:absolute;display:none;width:32%';
	document.body.appendChild(dragHelper);
}

function doOnunload(){
	updateDefaultTabPosition(_currentPosition);
}

// 隐藏被ID为objID的对象（层）遮挡的所有select
function HideOverSels(objID) {
    var sels = document.getElementsByTagName('select'); 
    for (var i = 0; i < sels.length; i++) 
      if (Obj1OverObj2($(objID), sels[i]))
        sels[i].style.visibility = 'hidden';          
}

//判断obj1是否遮挡了obj2
function Obj1OverObj2(obj1, obj2){
  var pos1 = getCompPos(obj1);
  var pos2 = getCompPos(obj2); 
  var result = true; 
  var obj1Left = pos1.left - window.document.body.scrollLeft; 
  var obj1Top = pos1.top - window.document.body.scrollTop; 
  var obj1Right = obj1Left + obj1.offsetWidth; 
  var obj1Bottom = obj1Top + obj1.offsetHeight;
  var obj2Left = pos2.left - window.document.body.scrollLeft; 
  var obj2Top = pos2.top - window.document.body.scrollTop; 
  var obj2Right = obj2Left + obj2.offsetWidth; 
  var obj2Bottom = obj2Top + obj2.offsetHeight;
  
  if (obj1Right <= obj2Left || obj1Bottom <= obj2Top || 
      obj1Left >= obj2Right || obj1Top >= obj2Bottom) 
    result = false; 
  return result; 
}

function showTipDiv(){
	var tl=getCompPos($(_currentTipDivParentId));
	$("tip_div").style.top=tl.top+"px";
	if(tl.left+220>getViewportInfo().width)
		$("tip_div").style.left=tl.left-220+"px";
	else
		$("tip_div").style.left=tl.left+"px";
	$("tip_div").style.visibility = "visible";
	HideOverSels("tip_div");
}

function hideTipDiv(){
	$("tip_div").style.visibility = "hidden";
	var sels = document.getElementsByTagName('select'); 
    for (var i = 0; i < sels.length; i++) 
      if (sels[i].style.visibility=='hidden')
        sels[i].style.visibility = 'visible';
}

function getCompPos(comp){
	var t=comp.offsetTop;
	var l=comp.offsetLeft;
	while(comp=comp.offsetParent){
		t+=comp.offsetTop;
		l+=comp.offsetLeft;
	}
	return {"top":t,"left":l};
}

function cancelLoadingMessage(){
	var disabledZone=$("disabledZone");
	if(disabledZone) {
		$("disabledZone").style.visibility = 'hidden';
	}
	DWREngine.setPreHook(function(){/*Ignore*/});
	DWREngine.setPostHook(function(){/*Ignore*/});
}

function initTemplate() {
	DWRUtil.useLoadingMessage("正在加载...");
	Profile.getProfile({
        	callback:function(data) {
        		cancelLoadingMessage();
        		initTemplateCallback(data);
			},
			timeout:8000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
				cancelLoadingMessage();
			}
        });
}

function initTemplateCallback(data){
	var profile = data.evalJSON();
	$("account_title").innerHTML='<span id="edit_title" class="editText">'+
		profile.title+'</span>';
    editbox_init();
    $("_nickname").innerHTML=profile.nickname+'&nbsp;';
    document.title=profile.title;
    
    var tabTitleArray = new Array();
    var tabClosableArray = new Array();
    for(i=0;i<profile.tabs;i++){
    	tabTitleArray[i]=profile["tab"+i].title;
    	tabTypeArray[i]=profile["tab"+i].type;
    	tabClosableArray[i]=profile["tab"+i].closable;
    }
   	var defaultTab=profile.defaultTab;
	_currentPosition=defaultTab;
	_lastPosition=defaultTab;
	addTemplateToTab(defaultTab);
    showById('dhtmlgoodies_tabView1');
	initTabs('dhtmlgoodies_tabView1',tabTitleArray,defaultTab,"100%","100%",tabClosableArray,false);
}

function addTemplateToTab(position){
	document.getElementById("tab"+position).innerHTML = "<img src='images/indicator.gif' alt='Loading'><font color=green>加载中...</font>";
	DWRTemplate.getTemplate(position,{
    	callback:function(data) {
    		if(data=="null"||data==null){
    			document.getElementById("tab"+position).innerHTML = "";
				showMsg("对不起，出现错误!");
				return;
    		}
    		var d = data.evalJSON();
    		document.getElementById("tab"+position).innerHTML=d.content;
    		initTabDrag(d.position);
    		initMarquee(d.position);
    		switch (d.type){
    			case 1:
    				renderNews(d.position);
    				break;
    			case 2:
    				renderLinks(d.position);
    				break;
    			case 3:
    				showMsg("还没有实现。");
    				break;
    			default:
    				showMsg("对不起，出现错误!");
    		}
		},
		timeout:8000,
		errorHandler:function(message){
			document.getElementById("tab"+position).innerHTML = "";
			showMsg("对不起，出现错误!"+message);
		}
    });
}

function renderNews(position){
	for(var tabI=0;tabI<3;tabI++){
		var j=0;
		var id=null;
		for(i=0;i<$("tab"+position+"_col"+tabI).childNodes.length-1;i++){
			if($("tab"+position+"_col"+tabI).childNodes[i].nodeType==1) {
				id=$("tab"+position+"_col"+tabI).childNodes[i].getAttribute("id");
				if(id!=null&&id!=""){
					renderTabNews(id,DWRUtil.getValue(id+"_settings"));
				};
				j++;
			}
		}
	}
}

function renderContents(id,settings){
	var oSettings = settings.evalJSON();
	switch(oSettings.type){
		case 1:
		case 2:
			renderTabNews(id,settings);
			break;
		case 3:
			renderTabLinks(id,settings);
			break;
	}
}

function renderTabNews(id,settings){
	hideById(id+"_message");
	$(id+"_indicator").style.visibility="visible";
	DWRTemplate.getNews(settings,{
		callback:function(data) {
			var o = data.evalJSON();
			var oSettings = settings.evalJSON();
			oSettings.totalResult = o.totalResult;
			$(id+"_settings").value=settings;
			newsList(id,o.news,oSettings.show_tip,oSettings.show_from,oSettings.show_time);
			if(o.totalResult!=0) {
				var firstResult = (oSettings.firstResult+1);
				if(firstResult<=1) {
					firstResult = 1;
					$(id+"_pagerSplit_p").style.visibility="hidden";
				} else 
					$(id+"_pagerSplit_p").style.visibility="visible";
				var lastResult =firstResult+oSettings.showNumber-1;
				if(lastResult>=o.totalResult) {
					lastResult = o.totalResult;
					$(id+"_pagerSplit_n").style.visibility="hidden";
				} else 
					$(id+"_pagerSplit_n").style.visibility="visible";
				
				$(id+"_pageInfo").innerHTML=firstResult+"-"+lastResult+"/"+o.totalResult;
			} else {
				$(id+"_pagerSplit_p").style.visibility="hidden";
				$(id+"_pagerSplit_n").style.visibility="hidden";
				$(id+"_pageInfo").innerHTML="";
			}
			$(id+"_indicator").style.visibility="hidden";
		},
		timeout:5000,
		errorHandler:function(message){
			$(id+"_indicator").style.visibility="hidden";
			$(id+"_message").innerHTML="<font color='red'>对不起，出现错误!"+message+"</font>";
			showById(id+"_message");
		}
	});
}

function initTabDrag(position){
	var _table=document.getElementById("tab_"+position);
	_IG_initDrag(_table);
}

function updateDefaultTabPosition(position){
	if(_lastPosition!=_currentPosition)
		Profile.updateDefaultTabPosition(position);
}

function saveModuleSettings(position,baseid,type,msg){
	var needRefresh=false;
	var oSetting = $(baseid+"_settings").value.evalJSON();
	var settings=null;
	switch (type){
		case 1:
			if(parseInt(DWRUtil.getValue(baseid+"_selectShowNumber"))!=oSetting.showNumber||
				$(baseid+"_checkShowTime").checked!=oSetting.show_time||
				$(baseid+"_checkShowTip").checked!=oSetting.show_tip)
				needRefresh=true;
			if($(baseid+"_checkShowFrom").checked!=oSetting.show_from||
				$(baseid+"_exSelectType3")!=oSetting.ex_type3)
				needRefresh=true;
			settings = {
				"id":oSetting.id,
				"firstResult":oSetting.firstResult,
				"totalResult":oSetting.totalResult,
				"title":DWRUtil.getValue(baseid+"_titleText"),
				"type1":oSetting.type1,
				"type2":oSetting.type2,
				"ex_type3":$(baseid+"_exSelectType3").value,
				"showNumber":parseInt(DWRUtil.getValue(baseid+"_selectShowNumber")),
				"show_from":$(baseid+"_checkShowFrom").checked,
				"show_tip":$(baseid+"_checkShowTip").checked,
				"show_time":$(baseid+"_checkShowTime").checked,
				"type":1
			};
			break;
		case 2:
			if(parseInt(DWRUtil.getValue(baseid+"_selectShowNumber"))!=oSetting.showNumber||
				$(baseid+"_checkShowTime").checked!=oSetting.show_time||
				$(baseid+"_checkShowTip").checked!=oSetting.show_tip)
				needRefresh=true;
			if($(baseid+"_rssAddress").value!=oSetting.rssAddress){
				needRefresh=true;
				oSetting.firstResult=0;
				oSetting.totalResult=-1;
			}
			settings = {
				"id":oSetting.id,
				"firstResult":oSetting.firstResult,
				"totalResult":oSetting.totalResult,
				"title":DWRUtil.getValue(baseid+"_titleText"),
				"showNumber":parseInt(DWRUtil.getValue(baseid+"_selectShowNumber")),
				"rssAddress":DWRUtil.getValue(baseid+"_rssAddress"),
				"share":$(baseid+"_checkShare").checked,
				"show_tip":$(baseid+"_checkShowTip").checked,
				"show_time":$(baseid+"_checkShowTime").checked,
				"type":2
			}; 
			break;
		case 3:
			if($(baseid+"_checkShowTip").checked!=oSetting.show_tip)
				needRefresh=true;
			settings = {
				"id":oSetting.id,
				"row_id":oSetting.row_id,
				"total":oSetting.total,
				"order":oSetting.order,
				"title":DWRUtil.getValue(baseid+"_titleText"),
				"share":$(baseid+"_checkShare").checked,
				"show_tip":$(baseid+"_checkShowTip").checked,
				"type":3
			};
			break;
		default:
			settings = {};
	}
	$(baseid+"_settings").value=Object.toJSON(settings);
	Profile.saveModuleSettings(position,Object.toJSON(settings),{
    	callback:function(saved) {
    		if(saved) {
    			showMsg(msg);
    			$(baseid+"_titleContent").innerHTML=settings.title;
    			module_edit(baseid);
    			if(needRefresh)
    				renderContents(baseid,Object.toJSON(settings));
    		} else
    			showMsg("出现错误，没有保存!");
		},
		timeout:8000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
		}
    });
}

function saveTabSettings(position,msg){	
	var col= new Array();
	for(var tabI=0;tabI<3;tabI++){
		col[tabI] = new Array();
		var id=null;
		for(i=0;i<$("tab"+position+"_col"+tabI).childNodes.length-1;i++){
			if($("tab"+position+"_col"+tabI).childNodes[i].nodeType==1) {
				id=$("tab"+position+"_col"+tabI).childNodes[i].getAttribute("id");
				if(id!=null&&id!=""){
					var settings = DWRUtil.getValue(id+"_settings").evalJSON();
					var module = {
						"id":settings.id
					};
					if(tabTypeArray[position]==2){
						module.order=settings.order;
					}
					col[tabI].push(module);
				};
			}
		}
	}
	Profile.saveTabSettings(position,col[0].toJSON(),col[1].toJSON(),col[2].toJSON(),{
        	callback:function(saved) {
        		if(saved) {
        			changeSaveTabButton(position,true);
        			if(tabTypeArray[position]==2){
						link_clearOrderSettings(position);
					}
        			if(msg)
        				showMsg(msg);
        		} else {
        			showMsg("出现错误，您的布局没有更改!");
        			$("tab"+position+"_saveTabButton").disabled=false;
					$("tab"+position+"_saveTabButton").value="保存本页";
        		}
			},
			timeout:8000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
			}
        });
}

function showAddContentPanel(tab){ 
	showById(tab+"_addContentPanel");
	hideById(tab+"_addContentButton");
	if($(tab+"_selectType1").innerHTML.trim()=="") 
		showSelectOptions1(tab,0);
}

function hideAddContentPanel(tab){
	showById(tab+"_addContentButton");
	hideById(tab+"_addContentPanel");
}

function showSelectOptions1(id,type){
	DWRUtil.removeAllOptions(id+"_selectType1");
	DWRUtil.addOptions(id+"_selectType1",[{"id":"","value":"--加载中--"}],"id","value");
	DWRTemplate.getSelectOptions("0",{
		callback:function(data) {
		    DWRUtil.removeAllOptions(id+"_selectType1");
		    DWRUtil.addOptions(id+"_selectType1",data);
		    showSelectOptions2(id,type);
		},
		timeout:8000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
		}
	});
}

function showSelectOptions2(id,type){
	DWRUtil.removeAllOptions(id+"_selectType2");
	DWRUtil.addOptions(id+"_selectType2",[{"id":"","value":"--加载中--"}],"id","value");
	$(id+"_selectType3").disabled=true;
	$(id+"_newsPreviousButton").disabled=true;
	if(type==0) {
		resetSelectOption2(id);
		$(id+"_addButton").disabled=true;
	} else {
		$(id+"_resubscribeButton").disabled=true;
	}
	var lastValue=DWRUtil.getValue(id+"_selectType1");
	DWRTemplate.getSelectOptions(lastValue,{
		callback:function(data) {
	    	DWRUtil.removeAllOptions(id+"_selectType2");
	        DWRUtil.addOptions(id+"_selectType2",data);
	        if(DWRUtil.getValue(id+"_selectType2")!=""){
	        	$(id+"_selectType3").disabled=false;
	        	$(id+"_newsPreviousButton").disabled=false;
	        }
	        if(type==0) {
				$(id+"_addButton").disabled=false;
				if(!$(id+"_changeTitleDefault").checked)
		        	$(id+"_titleText").value=DWRUtil.getText(id+"_selectType1");
	        } else {
	        	$(id+"_resubscribeButton").disabled=false;
	        	$(id+"_titleText").value=DWRUtil.getText(id+"_selectType1");
	        }
	    },
		timeout:8000,
		errorHandler:function(message){
			showMsg("对不起，出现错误!"+message);
		}
	});
}

function showSelectOptions3(id){
	if($(id+"_changeTitleDefault").checked)
		return;
	if(DWRUtil.getValue(id+"_selectType2").endsWith("00"))
		$(id+"_titleText").value=DWRUtil.getText(id+"_selectType1");
	else
		$(id+"_titleText").value=DWRUtil.getText(id+"_selectType1")+"-"+DWRUtil.getText(id+"_selectType2");
}

function resetSelectOption2(id){
	$(id+"_selectType3").value='点击(可选)';
	$(id+"_exSelectType3").value="";
}

function showById(id) {
	$(id).style.display="block";
}

function hideById(id){
	$(id).style.display="none";
}
	
function clearMsg(){
	$("msg_content").innerHTML="";
	hideById("msg");
}
	
var timeoutID=null;	
function showMsg(msg){
	showById("msg");
	$("msg_content").innerHTML=msg;
	if(timeoutID!=null)
		clearTimeout(timeoutID);
	timeoutID=setTimeout("clearMsg()",5000);
}

function module_refresh(id){
	var settings=$(id+"_settings").value.evalJSON();
	settings.firstResult=0;
	settings.totalResult=-1;
	var settingsString = Object.toJSON(settings);
	$(id+"_settings").value=settingsString;
	renderTabNews(id,settingsString);
}

function module_resubscribe(baseid,position){
	var oSettings=$(baseid+"_settings").value.evalJSON();
	oSettings.type1=$(baseid+"_selectType1").value;
	oSettings.type2=$(baseid+"_selectType2").value;
	oSettings.firstResult = 0;
	oSettings.totalResult = -1;
	var settingsString = Object.toJSON(oSettings);
	$(baseid+"_settings").value=settingsString;
	saveModuleSettings(position,baseid,1,"你已经重新订阅！")
	showResubscribeDiv(baseid);
	$(baseid+"_currentSubcribeType").innerHTML=DWRUtil.getText(baseid+"_selectType1")+"--"+
		DWRUtil.getText(baseid+"_selectType2");
}

function module_edit(id){
	if($(id+"_editContent").style.display=="none"){
	    showById(id+"_editContent");
		$(id+"_editText").innerHTML="取消";
		
	} else {
	    hideById(id+"_editContent");
		$(id+"_editText").innerHTML="编辑";
	}	    
}

function showResubscribeDiv(id){
	if($(id+"_resubscribeDiv").style.display=="none"){
		showById(id+"_resubscribeDiv");
		showById(id+"_resubscribeButtonDiv");
		hideById(id+"_saveSettingsButtonDiv");
		$(id+"_resubscribeText").innerHTML="取消";
		if($(id+"_selectType1").innerHTML=="")
			showSelectOptions1(id,1);
	} else {
		hideById(id+"_resubscribeDiv");
		hideById(id+"_resubscribeButtonDiv");
		showById(id+"_saveSettingsButtonDiv");
		$(id+"_selectType3").disabled=false;
		$(id+"_resubscribeText").innerHTML="修改";
	}
}
    
function module_del(position,id){
	if(confirm("您确定要删除该模块？")){
		var del_node = $(id);
		Profile.deleteModule(position,del_node.parentNode.id,$(id+"_settings").value.evalJSON().id,{
        	callback:function(deleted) {
        		if(deleted) {
        			showMsg("您已经成功删除模块!");
					del_node.parentNode.removeChild(del_node);
        		}
        		else
        			showMsg("出现错误，没有保存!");
			},
			timeout:8000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
			}
        });
	}
}

function module_pageSplit(id,page){
	var settings = DWRUtil.getValue(id+"_settings").evalJSON();
	settings.firstResult = settings.firstResult+settings.showNumber*page;
	if(settings.firstResult<0)
		settings.firstResult=0;
	var settingsString = Object.toJSON(settings);
	$(id+"_settings").value=settingsString;
	renderTabNews(id,settingsString);
}
    
function module_fold(id,fold){
	if(fold) {
		showById(id+"_fold");
		hideById(id+"_unfold");
	} else {
		showById(id+"_unfold");
		hideById(id+"_fold");
	}
}

function updateTabProfile(position){
	var settings = {
		"share":$("tab"+position+"_settingCheckShare").checked,
		"title":$("tab"+position+"_settingTitle").value
	};
	hideTipDiv();
	Profile.updateTabProfile(position,Object.toJSON(settings),{
        	callback:function(updated) {
        		if(updated) {
        			$("tabTabdhtmlgoodies_tabView1_"+position+"_span").innerHTML=settings.title;
        			showMsg("已经保存您的设置!");
        		}
        		else
        			showMsg("出现错误，没有保存!");
			},
			timeout:8000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
			}
        });
}

function showTabSettingsLayer(position){
	DWRUtil.useLoadingMessage("正在加载...");
	Profile.getTabSettingsFrameString(position,{
        	callback:function(data) {
        		cancelLoadingMessage();
        		if(data!="null"&&data!=null) {
        			$("tip_div_content").innerHTML=data;
        			$("tip_div_title").innerHTML="Tab页面设置";
        			_currentTipDivParentId = "tab"+position+"_tabSettingsButton";
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

function showCheckNewsLayer(tab){
	DWRUtil.useLoadingMessage("正在加载...");
	DWRTemplate.getCheckNewsString(tab,DWRUtil.getValue(tab+"_selectType1"),DWRUtil.getValue(tab+"_selectType2"),{
        	callback:function(data) {
        		cancelLoadingMessage();
        		if(data!="null"&&data!=null) {
        			$("tip_div_content").innerHTML=data;
        			$("tip_div_title").innerHTML="选择来源";
        			_currentTipDivParentId = tab+"_selectType3";
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

function showModuleCheckNewsLayer(baseid){
	DWRUtil.useLoadingMessage("正在加载...");
	var type1 = null;
	var type2 = null;
	if($(baseid+"_resubscribeDiv").style.display=="none"){
		var settings = $(baseid+"_settings").value.evalJSON();
		type1 = settings.type1;
		type2 = settings.type2;
	} else {
		type1 = $(baseid+"_selectType1").value;
		type2 = $(baseid+"_selectType2").value;
	}
	DWRTemplate.getModuleCheckNewsString(baseid,type1,type2,$(baseid+"_exSelectType3").value,{
        	callback:function(data) {
        		cancelLoadingMessage();
        		if(data!="null"&&data!=null) {
        			$("tip_div_content").innerHTML=data;
        			$("tip_div_title").innerHTML="选择来源";
        			_currentTipDivParentId = baseid+"_selectType3";
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

function newsPreview(position,baseid){
	var add = false;
	if(baseid!=null) {
		$(baseid+"_pagerSplit_p").style.visibility="hidden";
		$(baseid+"_pagerSplit_n").style.visibility="hidden";
		$(baseid+"_pageInfo").innerHTML="";
	} else {
		baseid = "tab"+position;
		add = true;
	}
	var showNumber = parseInt($(baseid+"_selectShowNumber").value);
	var show_tip = $(baseid+"_checkShowTip").checked;
	var show_time = $(baseid+"_checkShowTime").checked;
	var show_from = $(baseid+"_checkShowFrom").checked;
	var type1 = $(baseid+"_selectType1").value;
	var type2 = $(baseid+"_selectType2").value;
	var settings = {
		"type":1,
		"showNumber":showNumber,"firstResult":0,"totalResult":showNumber,
		"show_tip":show_tip,"show_time":show_time,"show_from":show_from,
		"type1":type1,"type2":type2,"ex_type3":""
	};
	$(baseid+"_indicator").style.visibility="visible";
	DWRTemplate.getNews(Object.toJSON(settings),{
		callback:function(data) {
			var o = data.evalJSON();
			$(baseid+"_indicator").style.visibility="hidden";
			if(add==false&&o.news.length!=0) {
				$(baseid+"_pageInfo").innerHTML="<font color=green>预览状态，请重新订阅！</font>";
			}
			newsList(baseid,o.news,show_tip,show_from,show_time);			
		},
		timeout:5000,
		errorHandler:function(message){
			$(baseid+"_indicator").style.visibility="hidden";
			showMsg("对不起，出现错误!"+message);
		}
	});
}

function saveCheckNews(tab){
	hideTipDiv();
	var settings = $(tab+"_settings").value.evalJSON();
	settings.totalResult = -1;
	settings.firstResult = 0;
	$(tab+"_settings").value = Object.toJSON(settings);
	var checkbox=$(tab+"_checkNewsForm").newscheckbox;
	var exType3 = "";
	if(checkbox.length) {
		for(var i=0;i<checkbox.length;i++){
			if(!checkbox[i].checked) 
				exType3+=checkbox[i].value+",";
		}
	} else {
		if(!checkbox.checked)
			exType3=checkbox.value+",";
	}
	if(exType3!="")
		exType3 = exType3.substring(0,exType3.length-1);
	if($(tab+"_exSelectType3").value!=exType3)
		$(tab+"_selectType3").value="已设置";
	$(tab+"_exSelectType3").value = exType3;
}	

function addModuleToTab(position){
	var tab = "tab"+position;
	var title = $(tab+"_titleText").value;
	var showNumber = parseInt($(tab+"_selectShowNumber").value);
	var type1 = $(tab+"_selectType1").value;
	var type2 = $(tab+"_selectType2").value;
	var ex_type3 = $(tab+"_exSelectType3").value;
	var show_tip = $(tab+"_checkShowTip").checked;
	var show_time = $(tab+"_checkShowTime").checked;
	var show_from = $(tab+"_checkShowFrom").checked;
	var col = $(tab+"_addColPosition").value;
	var settings = {
		"title":title,"showNumber":showNumber,
		"position":position,"type1":type1,
		"type2":type2,"ex_type3":ex_type3,"type":1,
		"show_tip":show_tip,"show_time":show_time,"show_from":show_from
	};
	DWRUtil.useLoadingMessage("正在加载...");
	DWRTemplate.getNewRssModuleString(position,col,Object.toJSON(settings),{
        	callback:function(data) {
        		cancelLoadingMessage();
        		if(data=="null"||data==null){
        			showMsg("对不起，出现错误!");
        		} else {
        			var d= data.evalJSON();
	        		$(tab+"_col"+col).innerHTML=d.content+$(tab+"_col"+col).innerHTML;
	        		initTabDrag(position);
	        		showMsg("模块已成功添加！");
	        		renderTabNews(d.row_id,DWRUtil.getValue(d.row_id+"_settings"));
        		}
			},
			timeout:8000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
				cancelLoadingMessage();
			}
        });
}

function validateModuleRss(position,baseid) {
	if($(baseid+"_saveModuleButton").value=="验证") {
		$(baseid+"_pagerSplit_p").style.visibility="hidden";
	    $(baseid+"_pagerSplit_n").style.visibility="hidden";
		hideById(baseid+"_message");
		$(baseid+"_indicator").style.visibility="visible";
		DWRTemplate.validateRss($(baseid+"_rssAddress").value,parseInt($(baseid+"_selectShowNumber").value),{
        	callback:function(data) {
        		$(baseid+"_indicator").style.visibility="hidden";
        		DWRUtil.removeAllRows(baseid+"_table"); 
        		if(data=="null"||data==null){
        			showMsg("对不起，您输入的Rss不可用!");
        		} else {
	        		$(baseid+"_saveModuleButton").value="保存设置";
	        		var d = data.evalJSON();
	        		if(d.title!="")
	        			$(baseid+"_titleText").value=d.title;
	        		$(baseid+"_pageInfo").innerHTML="<font color=green>预览状态，请保存！</font>";
	        		newsList(baseid,d.news,$(baseid+"_checkShowTip").checked,false,$(baseid+"_checkShowTime").checked);
        		}
			},
			timeout:10000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
				$(baseid+"_indicator").style.visibility="hidden";
			}
        });
	}
	else
		saveModuleSettings(position,baseid,2,"您的设置已成功保存！");
}	

function validateAddRss(position){
	var tab = "tab"+position;
	if($(tab+"_addcButton").value=="验证") {
		$(tab+"_indicator").style.visibility="visible";
		DWRTemplate.validateRss($(tab+"_crssAddress").value,parseInt($(tab+"_selectcShowNumber").value),{
        	callback:function(data) {
        		$(tab+"_indicator").style.visibility="hidden";
        		DWRUtil.removeAllRows(tab+"_table"); 
        		if(data=="null"||data==null){
        			showMsg("对不起，您输入的Rss不可用!");
        		} else {
	        		$(tab+"_addcButton").value="添加 »";
	        		var d = data.evalJSON();
	        		if(d.title!="")
	        			$(tab+"_ctitleText").value=d.title;
	        		newsList(tab,d.news,$(tab+"_checkcShowTip").checked,false,$(tab+"_checkcShowTime").checked);
        		}
			},
			timeout:30000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
				$(tab+"_indicator").style.visibility="hidden";
			}
        });
	}
	else
		addcModuleToTab(position);
}

function changeValidateButton(id,add){
	$(id).value="验证";
}

function addcModuleToTab(position){
	var tab = "tab"+position;
	var title = $(tab+"_ctitleText").value;
	var rssAddress = $(tab+"_crssAddress").value;
	var showNumber = parseInt($(tab+"_selectcShowNumber").value);
	var show_tip = $(tab+"_checkcShowTip").checked;
	var show_time = $(tab+"_checkcShowTime").checked;
	var col = $(tab+"_addcColPosition").value;
	var settings = {
		"title":title,"showNumber":showNumber,
		"position":position,"rssAddress":rssAddress,"type":2,
		"share":$(tab+"_checkcShare").checked,
		"show_tip":show_tip,"show_time":show_time
	};
	DWRUtil.useLoadingMessage("正在加载...");
	DWRTemplate.getcNewRssModuleString(position,col,Object.toJSON(settings),{
        	callback:function(data) {
        		cancelLoadingMessage();
        		if(data=="null"||data==null){
        			showMsg("对不起，出现错误!");
        		} else {
        			var d = data.evalJSON();
	        		$(tab+"_col"+col).innerHTML=d.content+$(tab+"_col"+col).innerHTML;
	        		initTabDrag(position);
	        		showMsg("模块已成功添加！");
	        		renderTabNews(d.row_id,DWRUtil.getValue(d.row_id+"_settings"));
        		}
			},
			timeout:8000,
			errorHandler:function(message){
				showMsg("对不起，出现错误!"+message);
				cancelLoadingMessage();
			}
        });
}

function changeTitleDefault(tab){
	if($(tab+"_changeTitleDefault").checked) {
		$(tab+"_titleText").disabled=false;
	} else {
		$(tab+"_titleText").disabled=true;
	}
}

function changeSaveTabButton(position,disabled){
	var id = "tab"+position+"_saveTabButton";
	if(disabled){
		$(id).disabled=true;
		$(id).style.background="white";
		$(id).value="已经保存";
	} else {
		showMsg("页面布局已更改，请<a href=# onclick=saveTabSettings("+position+
		",'您的页面已成功保存！')>保存</a>，或按右上角“保存本页”按钮。");
		$(id).disabled=false;
		$(id).style.background="#fad163";
		$(id).value="保存本页";
	}
}