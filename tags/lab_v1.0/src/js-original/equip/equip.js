var timeoutID = null;
var islogin = true;
var pageSize = 20;
var currentStore = null;
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
				width : 378,
				maxLength : 150
			}), new Ext.form.TextArea({
				id : 'equip_tab_2',
				title : '操作规程',
				name : 'caution',
				width : 378,
				maxLength : 1000
			}), new Ext.form.TextArea({
				id : 'equip_tab_3',
				title : '收费方式',
				name : 'remark',
				width : 378,
				maxLength : 1000
			})]
});

var a_equipPanel = new Ext.TabPanel({
	activeTab : 0,
	width : 482,
	height : 200,
	cls : 'comp-left2',
	items : [{
		id : 'a_equip_tab_0',
		title : '设备图片',
		xtype : 'panel',
		html : '<div style="padding:10px;">&nbsp;提示：<br/>&nbsp;请保存好设备信息后，用修改设备的方式添加图片。</div>'
	}, new Ext.form.TextArea({
				id : 'a_equip_tab_1',
				title : '性能参数',
				name : 'specification',
				width : 378,
				maxLength : 150
			}), new Ext.form.TextArea({
				id : 'a_equip_tab_2',
				title : '操作规程',
				name : 'caution',
				width : 378,
				maxLength : 1000
			}), new Ext.form.TextArea({
				id : 'a_equip_tab_3',
				title : '收费方式',
				name : 'remark',
				width : 378,
				maxLength : 1000
			})]
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
equipManager.appendChild([equipCategoryList1, equipCategoryList2,
		equipCategoryList3]);
root.appendChild([equipManager, equipSearchList]);

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
				editable : false,
				mode : 'local',
				triggerAction : 'all',
				selectOnFocus : true,
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
					height : 310
				},
				items : [{
							title : '使用说明',
							colspan : 2,
							collapsible : true,
							contentEl : 'home'
						}]
			});
});

function onClickMenuItem(el) {
	updateTab(el.id, el.innerHTML);
}

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

var addWin;
var editWin;
var uploadWin;
var imageUploadWin;
Ext.getDom("equip-edit-win").innerHTML = "";
Ext.getDom("equip-add-win").innerHTML = "";
Ext.getDom("equip-upload-win").innerHTML = "";
Ext.getDom("image-upload-win").innerHTML = "";

function showAddWin() {
	if (!addWin) {
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
										maxLength : 50,
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '编号*',
										name : 'no',
										width : 132,
										maxLength : 50,
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '型号*',
										name : 'model',
										width : 132,
										maxLength : 50,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '单价',
										name : 'price',
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										width : 132,
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '存放位置',
										name : 'location',
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										width : 132,
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '国别',
										name : 'country',
										width : 132,
										maxLength : 50,
										itemCls : 'comp-left0',
										clearCls : 'allow-float'
									}), new Ext.form.TextField({
										fieldLabel : '生产厂商',
										name : 'company',
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										width : 132,
										maxLength : 50
									}), {
								xtype : 'datefield',
								fieldLabel : '出厂日期',
								name : 'year1',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								width : 132,
								format : 'Y-m-d'
							}, {
								xtype : 'datefield',
								fieldLabel : '购置日期',
								name : 'year2',
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
										triggerAction : 'all',
										emptyText : '选择设备类别...',
										selectOnFocus : true,
										editable : false
									}), new Ext.form.TextField({
										fieldLabel : '负责人',
										name : 'admin',
										width : 132,
										value : username,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '联系方式',
										name : 'mobile',
										width : 378,
										itemCls : 'comp-left0',
										clearCls : 'stop-float',
										maxLength : 150
									}), a_equipPanel, new Ext.form.NumberField({
										fieldLabel : '处理时间(分/样)*',
										name : 'sampletime',
										width : 132,
										minValue : 1,
										allowDecimals : false,
										maxLength : 8,
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										allowBlank : false
									}), new Ext.form.NumberField({
										fieldLabel : '费用(元/分)*',
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
								name : 'pub'
							}, {
								xtype : 'checkbox',
								fieldLabel : '是否收费',
								boxLabel : '收费',
								itemCls : 'comp-left1',
								clearCls : 'stop-float',
								checked : true,
								name : 'charge'
							}, {
								xtype : 'checkbox',
								fieldLabel : '使用状态',
								boxLabel : '允许预约',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								checked : false,
								name : 'status'
							}, {
								xtype : 'checkbox',
								fieldLabel : '预约冲突',
								boxLabel : '检查',
								itemCls : 'comp-left1',
								clearCls : 'stop-float',
								checked : true,
								name : 'checkd'
							}, {
								xtype : 'checkbox',
								fieldLabel : '允许预约日期',
								boxLabel : '周一',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								checked : true,
								name : 'appd1'
							}, {
								xtype : 'checkbox',
								boxLabel : '周二',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								name : 'appd2'
							}, {
								xtype : 'checkbox',
								boxLabel : '周三',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								name : 'appd3'
							}, {
								xtype : 'checkbox',
								boxLabel : '周四',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								name : 'appd4'
							}, {
								xtype : 'checkbox',
								boxLabel : '周五',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'stop-float',
								checked : true,
								name : 'appd5'
							}, {
								xtype : 'checkbox',
								boxLabel : '周六',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : false,
								width : 150,
								name : 'appd6'
							}, {
								xtype : 'checkbox',
								boxLabel : '周日',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'stop-float',
								checked : false,
								width : 150,
								name : 'appd0'
							}, {
								xtype : 'timefield',
								fieldLabel : '预约起始时间*',
								name : 'appt1',
								allowBlank : false,
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								increment : 30,
								width : 132,
								format : 'H:i'
							}, {
								xtype : 'timefield',
								fieldLabel : '预约结束时间*',
								name : 'appt2',
								allowBlank : false,
								itemCls : 'comp-left',
								clearCls : 'stop-float',
								increment : 30,
								width : 132,
								format : 'H:i'
							}]
				});

		addWin = new Ext.Window({
			el : 'equip-add-win',
			title : '新增设备记录',
			modal : true,
			layout : 'fit',
			closeAction : 'hide',
			width : 520,
			height : 620,
			resizable : false,
			items : [form],
			buttons : [{
				text : '保存',
				handler : function() {
					if (form.getForm().findField("appt1").getValue() >= form
							.getForm().findField("appt2").getValue())
						form.getForm().findField("appt2")
								.markInvalid("预约最晚时间应比最早时间晚！");
					else if (form.form.isValid()) {
						var equip = new Object();
						equip.id = form.getForm().findField("id").getValue();
						equip.name = form.getForm().findField("name")
								.getValue();
						equip.no = form.getForm().findField("no").getValue();
						equip.model = form.getForm().findField("model")
								.getValue();
						equip.price = form.getForm().findField("price")
								.getValue();
						equip.specification = form.getForm()
								.findField("specification").getValue();
						equip.country = form.getForm().findField("country")
								.getValue();
						var year1 = form.getForm().findField("year1")
								.getValue();
						if (year1 != "")
							year1 = year1.format('Y-m-d');
						equip.year1 = year1;
						year2 = form.getForm().findField("year2").getValue();
						if (year2 != "")
							year2 = year2.format('Y-m-d');
						equip.year2 = year2;
						equip.type = form.getForm().findField("type")
								.getValue();
						equip.location = form.getForm().findField("location")
								.getValue();
						equip.admin = form.getForm().findField("admin")
								.getValue();
						equip.mobile = form.getForm().findField("mobile")
								.getValue();
						equip.company = form.getForm().findField("company")
								.getValue();
						equip.remark = form.getForm().findField("remark")
								.getValue();
						equip.caution = form.getForm().findField("caution")
								.getValue();
						equip.pub = form.getForm().findField("pub").getValue();
						equip.charge = form.getForm().findField("charge")
								.getValue();
						equip.status = form.getForm().findField("status")
								.getValue();
						equip.sampletime = form.getForm().findField("sampletime")
								.getValue();
						equip.fee = form.getForm().findField("fee")
								.getValue();
						equip.checkd = form.getForm().findField("checkd")
								.getValue();
						var appd = new Array();
						for (var i = 0; i < 7; i++) {
							if (form.getForm().findField("appd" + i).getValue() == false)
								appd.push(i);
						}
						if(appd.length==7){
							Ext.Msg.alert("错误", "允许预约日期至少要选一天！如果要禁止预约可以把“使用状态”取消勾选！");
							return;
						}
						equip.appd = appd;
						equip.appt1 = form.getForm().findField("appt1")
								.getValue();
						equip.appt2 = form.getForm().findField("appt2")
								.getValue();
						addWin.hide();
						DWRUtil.useLoadingMessage("处理中...");
						Equip.saveEquip(Ext.encode(equip), {
									callback : function(value) {
										cancelLoadingMessage();
										var result = Ext.decode(value);
										if (result.result) {
											form.form.reset();
											showMsg(result.message);
											currentStore.reload();
										} else
											Ext.Msg.alert("报告", result.message);
									},
									errorHandler : function(message) {
										Ext.Msg.alert("错误", "对不起，程序出现错误!");
										cancelLoadingMessage();
									}
								});
					}
				}
			}, {
				text : '取消',
				handler : function() {
					addWin.hide();
				}
			}]
		});
	}
	addWin.show();
	for (var i = 0; i < 4; i++) {
		a_equipPanel.setActiveTab('a_equip_tab_' + i);
	}
	a_equipPanel.setActiveTab('a_equip_tab_1');
}

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
										maxLength : 50,
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '编号*',
										name : 'no',
										width : 132,
										maxLength : 50,
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '型号*',
										name : 'model',
										width : 132,
										maxLength : 50,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										allowBlank : false
									}), new Ext.form.TextField({
										fieldLabel : '单价',
										name : 'price',
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										width : 132,
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '存放位置',
										name : 'location',
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										width : 132,
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '国别',
										name : 'country',
										width : 132,
										maxLength : 50,
										itemCls : 'comp-left0',
										clearCls : 'allow-float'
									}), new Ext.form.TextField({
										fieldLabel : '生产厂商',
										name : 'company',
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										width : 132,
										maxLength : 50
									}), {
								xtype : 'datefield',
								fieldLabel : '出厂日期',
								name : 'year1',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								width : 132,
								format : 'Y-m-d'
							}, {
								xtype : 'datefield',
								fieldLabel : '购置日期',
								name : 'year2',
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
										triggerAction : 'all',
										emptyText : '选择设备类别...',
										selectOnFocus : true,
										editable : false
									}), new Ext.form.TextField({
										fieldLabel : '负责人',
										name : 'admin',
										width : 132,
										itemCls : 'comp-left',
										clearCls : 'stop-float',
										maxLength : 50
									}), new Ext.form.TextField({
										fieldLabel : '联系方式',
										name : 'mobile',
										width : 378,
										itemCls : 'comp-left0',
										clearCls : 'stop-float',
										maxLength : 150
									}), equipPanel, new Ext.form.NumberField({
										fieldLabel : '处理时间(分/样)*',
										name : 'sampletime',
										width : 132,
										minValue : 1,
										allowDecimals : false,
										maxLength : 8,
										itemCls : 'comp-left0',
										clearCls : 'allow-float',
										allowBlank : false
									}), new Ext.form.NumberField({
										fieldLabel : '费用(元/分)*',
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
								name : 'pub'
							}, {
								xtype : 'checkbox',
								fieldLabel : '是否收费',
								boxLabel : '收费',
								itemCls : 'comp-left1',
								clearCls : 'stop-float',
								checked : false,
								name : 'charge'
							}, {
								xtype : 'checkbox',
								fieldLabel : '使用状态',
								boxLabel : '允许预约',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								checked : false,
								name : 'status'
							}, {
								xtype : 'checkbox',
								fieldLabel : '预约冲突',
								boxLabel : '检查',
								itemCls : 'comp-left1',
								clearCls : 'stop-float',
								checked : true,
								name : 'checkd'
							}, {
								xtype : 'checkbox',
								fieldLabel : '允许预约日期',
								boxLabel : '周一',
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								checked : true,
								name : 'appd1'
							}, {
								xtype : 'checkbox',
								boxLabel : '周二',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								name : 'appd2'
							}, {
								xtype : 'checkbox',
								boxLabel : '周三',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								name : 'appd3'
							}, {
								xtype : 'checkbox',
								boxLabel : '周四',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : true,
								name : 'appd4'
							}, {
								xtype : 'checkbox',
								boxLabel : '周五',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'stop-float',
								checked : true,
								name : 'appd5'
							}, {
								xtype : 'checkbox',
								boxLabel : '周六',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'allow-float',
								checked : false,
								width : 150,
								name : 'appd6'
							}, {
								xtype : 'checkbox',
								boxLabel : '周日',
								hideLabel : true,
								itemCls : 'comp-left2',
								clearCls : 'stop-float',
								checked : false,
								width : 150,
								name : 'appd0'
							}, {
								xtype : 'timefield',
								fieldLabel : '预约起始时间*',
								name : 'appt1',
								allowBlank : false,
								itemCls : 'comp-left0',
								clearCls : 'allow-float',
								increment : 30,
								width : 132,
								format : 'H:i'
							}, {
								xtype : 'timefield',
								fieldLabel : '预约结束时间*',
								name : 'appt2',
								allowBlank : false,
								itemCls : 'comp-left',
								clearCls : 'stop-float',
								increment : 30,
								width : 132,
								format : 'H:i'
							}]
				});

		editWin = new Ext.Window({
			el : 'equip-edit-win',
			title : '修改设备记录',
			modal : true,
			layout : 'fit',
			closeAction : 'hide',
			width : 520,
			height : 620,
			resizable : false,
			items : [form],
			buttons : [{
				text : '保存',
				handler : function() {
					if (form.getForm().findField("appt1").getValue() >= form
							.getForm().findField("appt2").getValue())
						form.getForm().findField("appt2")
								.markInvalid("预约最晚时间应比最早时间晚！");
					else if (form.form.isValid()) {
						var equip = new Object();
						equip.id = form.getForm().findField("id").getValue();
						equip.name = form.getForm().findField("name")
								.getValue();
						equip.no = form.getForm().findField("no").getValue();
						equip.model = form.getForm().findField("model")
								.getValue();
						equip.price = form.getForm().findField("price")
								.getValue();
						equip.specification = form.getForm()
								.findField("specification").getValue();
						equip.country = form.getForm().findField("country")
								.getValue();
						var year1 = form.getForm().findField("year1")
								.getValue();
						if (year1 != "")
							year1 = year1.format('Y-m-d');
						equip.year1 = year1;
						year2 = form.getForm().findField("year2").getValue();
						if (year2 != "")
							year2 = year2.format('Y-m-d');
						equip.year2 = year2;
						equip.type = form.getForm().findField("type")
								.getValue();
						equip.location = form.getForm().findField("location")
								.getValue();
						equip.admin = form.getForm().findField("admin")
								.getValue();
						equip.mobile = form.getForm().findField("mobile")
								.getValue();
						equip.company = form.getForm().findField("company")
								.getValue();
						equip.remark = form.getForm().findField("remark")
								.getValue();
						equip.caution = form.getForm().findField("caution")
								.getValue();
						equip.pub = form.getForm().findField("pub").getValue();
						equip.charge = form.getForm().findField("charge")
								.getValue();
						equip.status = form.getForm().findField("status")
								.getValue();
						equip.sampletime = form.getForm().findField("sampletime")
								.getValue();
						equip.fee = form.getForm().findField("fee")
								.getValue();
						equip.checkd = form.getForm().findField("checkd")
								.getValue();
						var appd = new Array();
						for (var i = 0; i < 7; i++) {
							if (form.getForm().findField("appd" + i).getValue() == false)
								appd.push(i);
						}
						if(appd.length==7){
							Ext.Msg.alert("错误", "允许预约日期至少要选一天！如果要禁止预约可以把“使用状态”取消勾选！");
							return;
						}
						equip.appd = appd;
						equip.appt1 = form.getForm().findField("appt1")
								.getValue();
						equip.appt2 = form.getForm().findField("appt2")
								.getValue();
						editWin.hide();
						DWRUtil.useLoadingMessage("处理中...");
						Equip.updateEquip(Ext.encode(equip), {
									callback : function(value) {
										cancelLoadingMessage();
										var result = Ext.decode(value);
										if (result.result) {
											form.form.reset();
											showMsg(result.message);
											currentStore.reload();
										} else
											Ext.Msg.alert("报告", result.message);
									},
									errorHandler : function(message) {
										Ext.Msg.alert("错误", "对不起，程序出现错误!");
										cancelLoadingMessage();
									}
								});
					}
				}
			}, {
				text : '取消',
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
				document.getElementById("equip_tip").innerHTML = '&nbsp;提示：<br/>&nbsp;<li>该设备暂时未添加图片</li><br/>&nbsp;<li><a href="#" onclick=\'showImageUploadWin("'
						+ formRecord.data.id
						+ '");return false;\'>上传新图片</a></li>';
			} else {
				document.getElementById("equip_image").src = "resources/equip/"
						+ formRecord.data.image + "?" + Math.random();
				document.getElementById("equip_tip").innerHTML = '&nbsp;提示：<br/>&nbsp;<li><a href="#" onclick=\'showImageUploadWin("'
						+ formRecord.data.id
						+ '");return false;\'>重新上传图片</a></li><br/>&nbsp;<li><a href="#" onclick=\'deleteEquipImage("'
						+ formRecord.data.id
						+ '");return false;\'>删除该图片</a></li><br/>';
			}
			equipPanel.setActiveTab('equip_tab_0');
			editWin.items.last().getForm().loadRecord(formRecord);
			if (equiptip != null) {
				equiptip.destroy();
			}
			if (formRecord.data.image != '')
//				equiptip = new Ext.ToolTip({
//							target : Ext.get("equip_image"),
//							html : '<img src='
//									+ document.getElementById("equip_image").src
//									+ ' width="400" height="300"></img>',
//							title : formRecord.data.name,
//							width : 415,
//							frame : true,
//							dismissDelay : 0,
//							showDelay : 0,
//							hideDelay : 200
//						});
			;
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

function showUploadWin() {
	if (!uploadWin) {
		var form = new Ext.form.FormPanel({
			labelWidth : 85,
			labelAlign : 'right',
			frame : true,
			fileUpload : true,
			items : [{
						xtype : 'textfield',
						fieldLabel : '选择Excel文件',
						name : 'file',
						height : 25,
						width : 270,
						inputType : 'file'// 文件类型
					}, {
						xtype : 'checkbox',
						fieldLabel : '',
						labelSeparator : '',
						boxLabel : '在‘说明描述’栏附加时间戳和签名',
						//checked : true,
						name : 'remark'
					}, {
						html : '<font color=green>请确保您上传的是Excel2003格式的文件，且格式符合导入规则;'
								+ '<br/>如果不了解格式要求，请先导出为Excel修改后再导入；</font>'
								+ '<br/><font color=red>只有组别为‘管理所有设备’的系统管理员才能进行导入操作。</font>'
					}],
			buttons : [{
				text : '上传并导入',
				handler : function() {
					var value = form.getForm().findField("file").getValue();
					var ext = value.substring(value.lastIndexOf(".") + 1,
							value.length).toLowerCase();
					if (ext == "xls") {
						uploadWin.hide();
						form.getForm().submit({
							url : 'equipupload?remark='
									+ form.getForm().findField("remark")
											.getValue(),
							success : function(form, action) {
								if (action.result.result) {
									showMsg(action.result.msg);
									currentStore.reload();
									uploadWin=null;
									Ext.getDom("equip-upload-win").innerHTML = "";
								} else
									Ext.Msg.alert('错误', action.result.msg);
							},
							failure : function() {
								Ext.Msg.alert('错误',
										'导入失败，请检查文件是否为Excel且格式正确且设备编号没有冲突！');
							},
							waitMsg : '正在进行上传处理，请稍候...'
						});
					} else
						form.getForm().findField("file")
								.markInvalid("请选择要导入的文件（扩展名为xls）！");
				}
			}, {
				text : '取消',
				handler : function() {
					uploadWin.hide();
				}
			}]
		});
		uploadWin = new Ext.Window({
					el : 'equip-upload-win',
					title : '导入Excel文件到设备库',
					modal : true,
					layout : 'fit',
					closeAction : 'hide',
					width : 410,
					height : 180,
					resizable : false,
					items : [form]
				});
	}
	uploadWin.show();

}

function showImageUploadWin(equipid) {
	if (!imageUploadWin) {
		var form = new Ext.form.FormPanel({
			labelWidth : 85,
			labelAlign : 'right',
			frame : true,
			fileUpload : true,
			items : [{
						xtype : 'textfield',
						fieldLabel : '选择图片文件',
						name : 'file',
						height : 25,
						width : 270,
						inputType : 'file'// 文件类型
					}, {
						html : '请确保您上传的图片文件为<font color=red>*.jpg</font>，没有损坏，且您有权限。'
								+ '建议的图片长宽为600×450，请不要上传低于90×50的图片！'
					}],
			buttons : [{
				text : '上传',
				handler : function() {
					var value = form.getForm().findField("file").getValue();
					var ext = value.substring(value.lastIndexOf(".") + 1,
							value.length).toLowerCase();
					if (ext == "jpg") {
						imageUploadWin.hide();
						form.getForm().submit({
							url : 'imageupload?equipid=' + equipid,
							success : function(form, action) {
								if (action.result.result) {
									showMsg(action.result.msg);
									document.getElementById("equip_image").src = "resources/equip/"
											+ equipid + ".jpg?" + Math.random();
									document.getElementById("equip_tip").innerHTML = '&nbsp;提示：<br/>&nbsp;<font color="green">'
											+ action.result.msg
											+ '</font><br/>&nbsp;<li><a href="#" onclick=\'showImageUploadWin("'
											+ equipid
											+ '");return false;\'>重新上传图片</a></li><br/>&nbsp;<li><a href="#" onclick=\'deleteEquipImage("'
											+ equipid
											+ '");return false;\'>删除该图片</a></li><br/>';
									if (equiptip != null) {
										equiptip.destroy();
									}
//									equiptip = new Ext.ToolTip({
//										target : Ext.get("equip_image"),
//										html : '<img src='
//												+ document
//														.getElementById("equip_image").src
//												+ ' width="400" height="300"></img>',
//										title : action.result.name,
//										width : 415,
//										frame : true,
//										dismissDelay : 0,
//										showDelay : 0,
//										hideDelay : 200
//									});
									imageUploadWin=null;
									Ext.getDom("image-upload-win").innerHTML = "";
								} else
									Ext.Msg.alert('错误', action.result.msg);
							},
							failure : function() {
								Ext.Msg.alert('错误',
										'导入失败，请检查上传的图片文件是jpg格式，符合规格，且没有损坏！');
							},
							waitMsg : '正在进行上传处理，请稍候...'
						});
					} else
						form.getForm().findField("file")
								.markInvalid("请选择要上传的图片文件（扩展名jpg）！");
				}
			}, {
				text : '取消',
				handler : function() {
					imageUploadWin.hide();
				}
			}]
		});
		imageUploadWin = new Ext.Window({
					el : 'image-upload-win',
					title : '上传图片',
					modal : true,
					layout : 'fit',
					closeAction : 'hide',
					width : 410,
					height : 150,
					resizable : false,
					items : [form]
				});
	}
	imageUploadWin.show();
}

function deleteEquipImage(equipid) {
	Ext.MessageBox.confirm('确认删除该设备的图片', '确认删除该设备的图片？', function(btn) {
		if ("yes" != btn)
			return;
		DWRUtil.useLoadingMessage("处理中...");
		Equip.removeEquipImage(equipid, {
			callback : function(value) {
				cancelLoadingMessage();
				var result = Ext.decode(value);
				if (result.result) {
					showMsg(result.message);
					document.getElementById("equip_image").src = "resources/images/unuploaded.jpg";
					document.getElementById("equip_tip").innerHTML = '&nbsp;提示：<br/>&nbsp;<li>该设备暂时未添加图片</li><br/>&nbsp;<li><a href="#" onclick=showImageUploadWin("'
							+ equipid + '");return false;>上传新图片</a></li>';
					if (equiptip != null) {
						equiptip.destroy();
					}
					equiptip = new Ext.ToolTip({
								target : Ext.get("equip_image"),
								html : '<font color="red">该设备暂时无图片！</font>',
								frame : true,
								dismissDelay : 0,
								showDelay : 0,
								hideDelay : 200
							});
				} else
					Ext.Msg.alert("报告", result.message);
			},
			errorHandler : function(message) {
				Ext.Msg.alert("错误", "对不起，程序出现错误!");
				cancelLoadingMessage();
			}
		});
	});
}