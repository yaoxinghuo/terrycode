﻿var timeoutID = null;
var pageSize = 20;
var emptySearchText = "输入关键词";
Ext.BLANK_IMAGE_URL = 'resources/images/default/s.gif';
function showMsg(msg) {
	$("msg_content").innerHTML = msg
			+ " <a href=# onclick='clearMsg();return false;'><img src='resources/images/close.gif'/></a>";
	$("msg").style.display = "block";
	if (timeoutID != null)
		clearTimeout(timeoutID);
	timeoutID = setTimeout("clearMsg()", 30000);
}
function clearMsg() {
	$("msg").style.display = "none";
	$("msg_content").innerHTML = "";
}
var equipPanel = new Ext.TabPanel({
	activeTab : 0,
	width : 482,
	height : 200,
	cls : 'comp-left2',
	items : [{
		id : 'equip_tab_0',
		title : '设备图片',
		xtype : 'panel',
		html : '<div style="float:left;"><img id="equip_image" src="resources/images/indicator.gif"/></div>'
				+ '<div style="padding:10px;" id="equip_tip">&nbsp;提示：<br/>&nbsp;图片正在加载中...</div>'
	}, new Ext.form.TextArea({
				id : 'equip_tab_1',
				title : '性能参数',
				name : 'specification',
				readOnly : true,
				width : 378,
				maxLength : 150
			}), new Ext.form.TextArea({
				id : 'equip_tab_2',
				title : '操作规程',
				readOnly : true,
				name : 'caution',
				width : 378,
				maxLength : 1000
			}), new Ext.form.TextArea({
				id : 'equip_tab_3',
				readOnly : true,
				title : '收费方式',
				name : 'remark',
				width : 378,
				maxLength : 1000
			})]
});

var tabPanel = new Ext.TabPanel({
			id : "tabPanel",
			region : 'center',
			enableTabScroll : true,
			deferredRender : false,
			activeTab : 0,
			items : [{
						contentEl : 'center',
						title : '欢迎使用',
						autoScroll : true,
						iconCls : 'home'
					}]
		});

var root = new Ext.tree.TreeNode({
			text : '设备查询',
			allowDrag : true,
			allowDrop : false,
			expanded : true
		});

var equipManager = new Ext.tree.TreeNode({
			text : '所有设备',
			allowDrag : false,
			iconCls : 'equip',
			expanded : true
		});
equipManager.on("click", function() {
			updateTab("equipManager", "所有设备");
		});
var equipSearchList = new Ext.tree.TreeNode({
			text : '搜索设备',
			allowDrag : false,
			iconCls : 'search'
		});
equipSearchList.on("click", function() {
			updateTab("equipSearchList", "搜索设备");
		});
var equipCategoryList1 = new Ext.tree.TreeNode({
			text : equipArray[1],
			allowDrag : false,
			iconCls : 'equip'
		});
var equipCategoryList2 = new Ext.tree.TreeNode({
			text : equipArray[2],
			allowDrag : false,
			iconCls : 'equip'
		});
var equipCategoryList3 = new Ext.tree.TreeNode({
			text : equipArray[3],
			allowDrag : false,
			iconCls : 'equip'
		});
equipCategoryList1.on("click", function() {
			updateTab("equipCategoryList1", equipArray[1]);
		});
equipCategoryList2.on("click", function() {
			updateTab("equipCategoryList2", equipArray[2]);
		});
equipCategoryList3.on("click", function() {
			updateTab("equipCategoryList3", equipArray[3]);
		});
equipManager.appendChild([equipCategoryList1,equipCategoryList2,equipCategoryList3]);
root.appendChild([equipManager,equipSearchList]);

Ext.onReady(function() {
	DWREngine.setTimeout(30000);
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';
	var cp = new Ext.state.CookieProvider();
	var cookie_theme = cp.get("cookie_theme");
	if (cookie_theme == null || cookie_theme == "")
		cookie_theme = "resources/css/ext-all.css";
	Ext.util.CSS.swapStyleSheet('theme', cookie_theme);
	Ext.state.Manager.setProvider(cp);

	var myThemeChanger = new Ext.form.ComboBox({
				store : new Ext.data.SimpleStore({
							fields : ['display', 'value'],
							data : themeArray
						}),
				renderTo : 'theme-selector',
				displayField : 'display',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				selectOnFocus : true,
				editable : false,
				resizable : false,
				listWidth : 100,
				width : 100,
				valueField : 'value',
				value : cookie_theme
			});
	myThemeChanger.on('select', function(combo) {
				Ext.util.CSS.swapStyleSheet('theme', combo.getValue());
				cp.set("cookie_theme", combo.getValue());
			}, this);

	$("loading_div").parentNode.removeChild($("loading_div"));
	Ext.getDom("theme-selector").style.visibility = "visible";
	$("account_div").style.visibility = "visible";

	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [new Ext.BoxComponent({
									region : 'north',
									el : 'north-div',
									height : 82
								}), new Ext.tree.TreePanel({
									region : 'west',
									id : 'west-panel',
									title : '菜单栏',
									split : true,
									width : 200,
									minSize : 175,
									maxSize : 400,
									collapsible : true,
									margins : '0 0 0 0',
									root : root
								}), tabPanel]
			});

	var w = (window.innerWidth)
			? window.innerWidth
			: (document.documentElement && document.documentElement.clientWidth)
					? document.documentElement.clientWidth
					: document.body.offsetWidth;
	if (w - 265 < 800)
		w = 1050;

	var panel = new Ext.Panel({
				id : 'main-panel',
				baseCls : 'x-plain',
				renderTo : Ext.get("center"),
				layout : 'table',
				layoutConfig : {
					columns : 2
				},
				defaults : {
					frame : true,
					width : w - 265,
					height : 320
				},
				items : [{
							title : '使用说明',
							colspan : 2,
							collapsible : true,
							contentEl : 'home'

						}]
			});
});

function addTab(id, tabTitle) {
	if (id.substring(0, 17) == "equipCategoryList")
		initEquipCategoryList(id, tabTitle);
	else {
		switch (id) {
			case 'equipManager' :
				initEquipManager(id, tabTitle);
				break;
			case 'equipSearchList' :
				initEquipSearchList(id, tabTitle);
				break;
			default :
				showMsg("此功能需要实现！");
		}
	}
}

function updateTab(id, title) {
	var tab = tabPanel.getItem(id);
	if (tab) {
		tabPanel.remove(tab);
	}
	tab = addTab(id, title);
	tabPanel.setActiveTab(tab);
}

var editWin;
Ext.getDom("equip-edit-win").innerHTML = "";
var equiptip = null;
function showEditWin(id) {
	if (!editWin) {
		var form = new Ext.FormPanel({
					labelWidth : 100,
					frame : true,
					labelAlign : 'right',
					buttonAlign : 'left',
					defaultType : 'textfield',
					items : [new Ext.form.Hidden({
										name : 'id'
									}), new Ext.form.TextField({
										fieldLabel : '设备名称*',
										name : 'name',
										width : 378,
										readOnly : true,
										maxLength : 50,
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '编号*',
										name : 'no',
										readOnly : true,
										width : 132,
										maxLength : 50,
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '型号*',
										name : 'model',
										width : 132,
										readOnly : true,
										maxLength : 50,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '单价',
										name : 'price',
										readOnly : true,
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										width : 132,
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '存放位置',
										name : 'location',
										readOnly : true,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										width : 132,
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '国别',
										name : 'country',
										readOnly : true,
										width : 132,
										maxLength : 50,
										itemCls : 'comp-left0',
										clearCls : 'allow-float'
									}), new Ext.form.TextField({
										fieldLabel : '生产厂商',
										name : 'company',
										readOnly : true,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										width : 132,
										maxLength : 50
									}), {
								xtype : 'textfield',
								fieldLabel : '出厂日期',
								name : 'year1',
								readOnly : true,
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								width : 132,
								format : 'Y-m-d'
							}, {
								xtype : 'textfield',
								fieldLabel : '购置日期',
								name : 'year2',
								readOnly : true,
								itemCls : 'comp-left',
								clearCls : 'stop-float',
								width : 132,
								format : 'Y-m-d'
							}, new Ext.form.ComboBox({
										fieldLabel : '设备类别*',
										hiddenName : 'type',
										store : new Ext.data.SimpleStore({
													fields : ['returnValue',
															'displayValue'],
													data : equipArray2
												}),
										valueField : 'returnValue',
										displayField : 'displayValue',
										typeAhead : true,
										allowBlank : false,
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										width : 132,
										mode : 'local',
										emptyText : '选择设备类别...',
										selectOnFocus : true,
										editable : false
									}), new Ext.form.TextField({
										fieldLabel : '负责人',
										name : 'admin',
										readOnly : true,
										width : 132,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '联系方式',
										name : 'mobile',
										readOnly : true,
										width : 378,
										itemCls : 'comp-left0',
										clearCls : 'stop-float',
										maxLength : 150
									}), equipPanel, new Ext.form.NumberField({
										fieldLabel : '处理时间(分/样)*',
										name : 'sampletime',
										readOnly : true,
										width : 132,
										minValue : 1,
										allowDecimals : false,
										maxLength : 8,
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										allowBlank : false
									}), new Ext.form.NumberField({
										fieldLabel : '费用(元/分)*',
										readOnly : true,
										name : 'fee',
										width : 132,
										decimalPrecision : 2,
										minValue : 0,
										maxLength : 8,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										allowBlank : false
									}), {
								xtype : 'checkbox',
								fieldLabel : '是否公用',
								boxLabel : '允许公用',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								checked : false,
								disabled : true,
								name : 'pub'
							}, {
								xtype : 'checkbox',
								fieldLabel : '是否收费',
								boxLabel : '收费',
								itemCls : 'comp-left1',
								clearCls : 'stop-float',
								checked : false,
								disabled : true,
								name : 'charge'
							}, {
								xtype : 'checkbox',
								fieldLabel : '使用状态',
								boxLabel : '允许预约',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								checked : false,
								disabled : true,
								name : 'status'
							}, {
								xtype : 'checkbox',
								fieldLabel : '预约冲突',
								boxLabel : '检查',
								itemCls : 'comp-left1',
								clearCls : 'stop-float',
								checked : true,
								disabled : true,
								name : 'checkd'
							}, {
								xtype : 'checkbox',
								fieldLabel : '允许预约日期',
								boxLabel : '周一',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								checked : true,
								disabled : true,
								name : 'appd1'
							}, {
								xtype : 'checkbox',
								boxLabel : '周二',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								disabled : true,
								name : 'appd2'
							}, {
								xtype : 'checkbox',
								boxLabel : '周三',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								disabled : true,
								name : 'appd3'
							}, {
								xtype : 'checkbox',
								boxLabel : '周四',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								disabled : true,
								name : 'appd4'
							}, {
								xtype : 'checkbox',
								boxLabel : '周五',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'stop-float',
								checked : true,
								disabled : true,
								name : 'appd5'
							}, {
								xtype : 'checkbox',
								boxLabel : '周六',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : false,
								disabled : true,
								width : 150,
								name : 'appd6'
							}, {
								xtype : 'checkbox',
								boxLabel : '周日',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'stop-float',
								checked : false,
								disabled : true,
								width : 150,
								name : 'appd0'
							}, {
								xtype : 'textfield',
								fieldLabel : '预约起始时间*',
								name : 'appt1',
								allowBlank : false,
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								increment : 30,
								width : 132,
								readOnly : true,
								format : 'H:i'
							}, {
								xtype : 'textfield',
								fieldLabel : '预约结束时间*',
								name : 'appt2',
								allowBlank : false,
								itemCls : 'comp-left',
								clearCls : 'stop-float',
								increment : 30,
								width : 132,
								readOnly : true,
								format : 'H:i'
							}]
				});

		editWin = new Ext.Window({
			el : 'equip-edit-win',
			title : '查看设备记录',
			modal : true,
			layout : 'fit',
			closeAction : 'hide',
			width : 520,
			height : 620,
			resizable : false,
			items : [form],
			buttons : [{
				text : '关闭',
				handler : function() {
					editWin.hide();
				}
			}]
		});
	}

	DWRUtil.useLoadingMessage("处理中...");
	Equip.getEquipDetailInfoById(id, {
		callback : function(value) {
			cancelLoadingMessage();
			if (!value) {
				Ext.Msg.alert("错误", "对不起，程序出现错误!");
				return;
			}
			var TopicRecord = Ext.data.Record.create([{
						name : 'id',
						mapping : 'id'
					}]);
			var v = Ext.decode(value);
			var formRecord = new TopicRecord(v);
			var appd = formRecord.data.appd;
			var appdarray = [true, true, true, true, true, true, true];
			for (var i = 0; i < appd.length; i++) {
				appdarray[appd[i]] = false;
			}
			for (var i = 0; i < appdarray.length; i++) {
				formRecord.data["appd" + i] = appdarray[i];
			}
			editWin.show();
			for (var i = 0; i < 4; i++) {
				equipPanel.setActiveTab('equip_tab_' + i);
			}
			document.getElementById("equip_image").style.width = 227 + "px";
			document.getElementById("equip_image").style.height = 170 + "px";
			if (formRecord.data.image == '') {
				document.getElementById("equip_image").src = "resources/images/unuploaded.jpg";
				document.getElementById("equip_tip").innerHTML = '&nbsp;提示：<br/>&nbsp;<li>该设备暂时未添加图片</li><br/>';
			} else {
				document.getElementById("equip_image").src = "resources/equip/"
						+ formRecord.data.image + "?" + Math.random();
				document.getElementById("equip_tip").innerHTML = '&nbsp;提示：<br/>&nbsp;<li>您可以将鼠标移至缩略图查看大图</li><br/>';
			}
			equipPanel.setActiveTab('equip_tab_0');
			editWin.items.last().getForm().loadRecord(formRecord);
			if (equiptip != null) {
				equiptip.destroy();
			}
			if (formRecord.data.image != '')
				equiptip = new Ext.ToolTip({
							target : Ext.get("equip_image"),
							html : '<img src='
									+ document.getElementById("equip_image").src
									+ ' width="400" height="300"></img>',
							title : formRecord.data.name,
							width : 415,
							frame : true,
							dismissDelay : 0,
							showDelay : 0,
							hideDelay : 200
						});
			else
				equiptip = new Ext.ToolTip({
							target : Ext.get("equip_image"),
							html : '<font color="red">该设备暂时无图片！</font>',
							frame : true,
							dismissDelay : 0,
							showDelay : 0,
							hideDelay : 200
						});
		},
		errorHandler : function(message) {
			Ext.Msg.alert("错误", "对不起，程序出现错误!");
			cancelLoadingMessage();
		}
	});

}