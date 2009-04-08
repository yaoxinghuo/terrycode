var timeoutID = null;
var islogin = true;
var introduceMessageLoaded = false;
var adminLogNoticeLoaded = false;
var pageSize = 20;
var currentStore = null;
var af_refresh = null;
var pr_refresh = null;
var emptySearchText="输入关键词";
function showMsg(msg) {
	if (!msg)
		return;
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
tabPanel.addListener('beforeremove', function(c, comp) {
	if (comp.getId() == "pr_equipManager") {
		clearInterval(pr_refresh);
		pr_refresh = null;
	} else if (comp.getId() == "af_equipManager") {
		clearInterval(af_refresh);
		af_refresh = null;
	}
});// 关闭Tab前把自动刷新关掉

var deleteWin = null;
function deleteFileFromServer() {
	if(!isAdmin) {
		alertOnlyAdminDo();
		return;
	}
	if (!deleteWin) {
		var form = new Ext.form.FormPanel({
			labelWidth : 100,
			labelAlign : 'right',
			frame : true,
			items : [new Ext.form.ComboBox({
				fieldLabel : '资源类别',
				hiddenName : 'type',
				store : ['File', 'Image', 'Flash', 'Media'],
				width : 125,
				typeAhead : true,
				allowBlank : false,
				mode : 'local',
				triggerAction : 'all',
				selectOnFocus : true,
				value : 'File',
				editable : false
			}), new Ext.form.TextField({
				fieldLabel : '要删除的文件(夹)',
				name : 'file',
				width : 190,
				allowBlank : false
			}), {
				html : '<font color=green>请确保要删除的文件(夹)的路径正确,可<a href="#" onclick="openBrowseServerWin();return false;">浏览服务器文件</a>确认路径后再填写,'
						+ '如： /download/test.doc 或 test2.jpg</font>'
						+ '<br/><font color=red>只有组别为的管理员的用户能进行文件删除操作。</font>'
			}],
			buttons : [{
				text : '删除',
				handler : function() {
					if (form.form.isValid()) {
						Ext.MessageBox.confirm('确认从服务器删除文件(夹)', '是否确认删除 /'
								+ form.getForm().findField("type").getValue()
								+ "/"
								+ form.getForm().findField("file").getValue()
								+ " ?", function(btn) {
							if ("yes" != btn)
								return;
							DWRUtil.useLoadingMessage("处理中...");
							Notice.deleteFile(form.getForm().findField("type")
									.getValue(), form.getForm()
									.findField("file").getValue(), {
								callback : function(value) {
									cancelLoadingMessage();
									var result = Ext.decode(value);
									if (result.result) {
										deleteWin.hide();
										showMsg(result.message);
										if (browseServerWin
												&& !browseServerWin.closed)
											browseServerWin.location.reload();
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
				}
			}, {
				text : '重置',
				handler : function() {
					form.form.reset();
				}
			}, {
				text : '取消',
				handler : function() {
					deleteWin.hide();
				}
			}]
		});
		deleteWin = new Ext.Window({
			el : 'file-delete-win',
			title : '从服务器上删除文件(夹)',
			modal : true,
			layout : 'fit',
			closeAction : 'hide',
			width : 350,
			height : 180,
			resizable : false,
			items : [form]
		});
	}
	deleteWin.show();
}

Ext.onReady(function() {
	DWREngine.setTimeout(30000);
	Ext.BLANK_IMAGE_URL = 'resources/images/default/s.gif';
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
		}), {
			region : 'west',
			id : 'west-panel',
			title : '菜单栏',
			split : true,
			width : 200,
			minSize : 175,
			maxSize : 400,
			collapsible : true,
			margins : '0 0 5 5',
			cmargins : '0 5 5 5',
			layout : 'accordion',
			layoutConfig : {
				animate : true
			},
			items : [{
				title : '设备预约管理',
				html : Ext.getDom('equipMenus').innerHTML,
				border : false,
				autoScroll : true,
				iconCls : 'equip'
			}, {
				title : '预约审批日志',
				html : Ext.getDom('logMenus').innerHTML,
				border : false,
				autoScroll : true,
				iconCls : 'unit'
			}, {
				title : '用户管理',
				html : Ext.getDom('userMenus').innerHTML,
				autoScroll : true,
				border : false,
				iconCls : 'user'
			}, {
				title : '公告反馈管理',
				html : Ext.getDom('noticeMenus').innerHTML,
				autoScroll : true,
				border : false,
				iconCls : 'notice'
			}]
		}, tabPanel]
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
			height : 365
		},
		items : [{
			title : '使用说明',
			colspan : 2,
			height : 480,
			collapsible : true,
			collapsed : true,
			contentEl : 'introduceDiv',
			listeners : {
				"beforeexpand" : {
					fn : function() {
						if (introduceMessageLoaded)
							return;
						DWRUtil.useLoadingMessage("加载中...");
						Message.getIntroduceMessagesByType(1, {
							callback : function(value) {
								cancelLoadingMessage();
								$("introduceDiv").innerHTML = value;
								introduceMessageLoaded = true;
							},
							errorHandler : function(message) {
								cancelLoadingMessage();
								showMsg("对不起，加载使用说明失败!");
								$("introduceDiv").innerHTML = "对不起，加载使用说明失败!";
							}
						});
					},
					scope : this
				}
			}
		}, {
			title : '预约信息',
			colspan : 2,
			collapsible : true,
			contentEl : 'messageDiv0'

		}, {
			title : '审批信息',
			colspan : 2,
			collapsible : true,
			collapsed : true,
			contentEl : 'messageDiv1',
			listeners : {
				"beforeexpand" : {
					fn : function() {
						if (adminLogNoticeLoaded)
							return;
						initAdminLogNotice();
						adminLogNoticeLoaded = true;
					},
					scope : this
				}
			}
		}]
	});
	Ext.getDom("menus").innerHTML = "";
	checkUndoTasks();
	// initMarquee();
	initUserLogNotice();
});

function checkUndoTasks() {
	Notice.getUndoTasks({
		callback : function(value) {
			if (value != null && value != "") {
				$("msg_content").innerHTML = "<img style='width:14px;height:14px;' src='resources/icons/info.png' alt='To Do'/>&nbsp;"
						+ value;
				$("msg").style.display = "block";
			}
		},
		errorHandler : function(message) {
		}
	});
}

function onClickMenuItem(el) {
	updateTab(el.id, el.innerHTML);
}

function addTab(id, tabTitle) {
	switch (id) {
		case 'pr_equipManager' :
			initPrEquipManager(id, tabTitle);
			break;
		case 'af_equipManager' :
			initAfEquipManager(id, tabTitle);
			break;
		case 'equipManager' :
			initEquipManager(id, tabTitle);
			break;
		case 'all_equipManager' :
			initAllEquipManager(id, tabTitle);
			break;
		case 'userManager' :
			if(!isAdmin) {
				alertOnlyAdminDo();
			} else
				initUserManager(id, tabTitle);
			break;
		case 'adminManager' :
			if(!isAdmin) {
				alertOnlyAdminDo();
			} else
				initAdminManager(id, tabTitle);
			break;
		case 'userSearch' :
			if(!isAdmin) {
				alertOnlyAdminDo();
			} else
				initUserSearch(id, tabTitle);
			break;
		case 'user_logManager' :
			initUserLogManager(id, tabTitle);
			break;
		case 'admin_logManager' :
			initAdminLogManager(id, tabTitle);
			break;
		case 'noticeManager' :
			if(!isAdmin) {
				alertOnlyAdminDo();
			} else
				initNoticeManager(id, tabTitle);
			break;
		case 'feedbackManager' :
			if(!isAdmin) {
				alertOnlyAdminDo();
			} else
				initFeedbackManager(id, tabTitle);
			break;
		case 'browseServer' :
			if(!isAdmin) {
				alertOnlyAdminDo();
			} else
				initBrowseServer(id, tabTitle);
			break;
		default :
			showMsg("此功能需要实现！");
	}
}

function addQueueTab(equip_id, equip_name) {
	initQueueEquipManager(equip_id, equip_name);
}

function updateTab(id, title) {
	var tab = tabPanel.getItem(id);
	if (tab)
		tabPanel.remove(tab);
	tab = addTab(id, title);
	tabPanel.setActiveTab(tab);
}

var editWin;
Ext.getDom("equip-edit-win").innerHTML = "";
var equiptip = null;
function showEquipEditWin(id) {
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
								disabled : true,
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
			title : '修改设备状态',
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
					if(form.getForm().findField("appt1").getValue()>=form.getForm().findField("appt2").getValue())
						form.getForm().findField("appt2").markInvalid("预约最晚时间应比最早时间晚！");
					else if (form.form.isValid()) {
						var equip = new Object();
						equip.id = form.getForm().findField("id").getValue();
						equip.status = form.getForm().findField("status")
								.getValue();
						var appd=new Array();
						for(var i=0;i<7;i++){
							if(form.getForm().findField("appd"+i)
								.getValue()==false)
								appd.push(i);
						}
						equip.appd=appd;
						equip.appt1 = form.getForm().findField("appt1")
								.getValue();
						equip.appt2 = form.getForm().findField("appt2")
								.getValue();
						equip.sampletime = form.getForm().findField("sampletime")
								.getValue();
						equip.fee = form.getForm().findField("fee")
								.getValue();
						equip.checkd = form.getForm().findField("checkd")
								.getValue();
						equip.charge = form.getForm().findField("charge")
								.getValue();
						editWin.hide();
						DWRUtil.useLoadingMessage("处理中...");
						Equip.updateEquipStatus(Ext.encode(equip), {
							callback : function(value) {
								cancelLoadingMessage();
								var result = Ext.decode(value);
								if (result.result) {
									form.form.reset();
									showMsg(result.message);
									if (currentStore != null)
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

var feeWin;
Ext.getDom("fee-win").innerHTML = "";
function showFeeWin(bookid,fee) {
	if (!feeWin) {
		var form = new Ext.form.FormPanel({
			labelWidth : 85,
			labelAlign : 'right',
			frame : true,
			items : [new Ext.form.Hidden({
				name : 'id'
			}),new Ext.form.NumberField({
				fieldLabel : '应收费用(元)*',
				name : 'fee',
				width : 200,
				minValue : 0,
				maxLength : 8,
				allowBlank : false
			}), new Ext.form.TextArea({
				fieldLabel : '备注*',
				name : 'remark',
				maxLength : 300,
				height : 80,
				width : 200,
				allowBlank : false
			})]
		});
		feeWin = new Ext.Window({
			el : 'fee-win',
			title : '修改实验费用',
			modal : true,
			layout : 'fit',
			closeAction : 'hide',
			width : 330,
			height : 200,
			resizable : false,
			items : [form],
			buttons : [{
				text : '提交修改',
				handler : function() {
					if (form.form.isValid()) {
						var fee = form.getForm().findField("fee").getValue();
						var remark = form.getForm()
								.findField("remark").getValue();
						var id = form.getForm().findField("id").getValue();
						DWRUtil.useLoadingMessage("处理中...");
						feeWin.hide();
						Book.changeFee(id, fee, remark, {
									callback : function(value) {
										cancelLoadingMessage();
										var result = Ext.decode(value);
										if (result.result) {
											showMsg(result.message);
											currentStore.reload();
										} else
											currentMsgBox = Ext.Msg.alert(
													"报告: ", result.message);
									},
									errorHandler : function(message) {
										cancelLoadingMessage();
										Ext.Msg.alert("错误", "对不起，程序出现错误!");
									}
								});
					}
				}
			}, {
				text : '重置',
				handler : function() {
					form.form.reset();
				}
			}, {
				text : '取消',
				handler : function() {
					feeWin.hide();
				}
			}]
		});
	}
	feeWin.show();
	feeWin.items.last().getForm().findField("id").setValue(bookid);
	feeWin.items.last().getForm().findField("fee").setValue(fee);
}

var uploadWin;
Ext.getDom("account-upload-win").innerHTML = "";
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
				html : '<font color=green>请确保您上传的是Excel2003格式的文件，且格式符合导入规则;'
						+ '<br/>如果不了解格式要求，请先导出为Excel修改后再导入；</font>'
						+ '<br/><font color=red>为确保安全，Excel中密码请填写加密后的密码，123456加密后对应为e10adc3949ba59abbe56e057f20f883e</font>'
			}],
			buttons : [{
				text : '上传并导入',
				handler : function() {
					var value = form.getForm().findField("file").getValue();
					var ext = value.substring(value.lastIndexOf(".")+1,value.length).toLowerCase();
					if (ext == "xls") {
						uploadWin.hide();
						form.getForm().submit({
							url : 'userupload',
							success : function(form, action) {
								if (action.result.result) {
									showMsg(action.result.msg);
									currentStore.reload();
								} else
									Ext.Msg.alert('错误', action.result.msg);
							},
							failure : function() {
								Ext.Msg.alert('错误',
										'导入失败，请检查文件是否为Excel且格式正确且用户登录号没有冲突！');
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
			el : 'account-upload-win',
			title : '导入Excel文件到用户库',
			modal : true,
			layout : 'fit',
			closeAction : 'hide',
			width : 410,
			height : 175,
			resizable : false,
			items : [form]
		});
	}
	uploadWin.show();

}