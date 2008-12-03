var currentMsgBox = null;
var introduceMessageLoaded = false;
var adminLogNoticeLoaded = false;
var emptySearchText = "输入关键词";
var pageSize = 20;
var yuyue = new Object();
var tabPanel = new Ext.TabPanel({
			id : "tabPanel",
			region : 'center',
			enableTabScroll : true,
			deferredRender : false,
			activeTab : 0,
			items : [{
						contentEl : 'center',
						id : '',
						title : '欢迎使用',
						autoScroll : true,
						iconCls : 'home'
					}]
		});
var reader = new Ext.data.DWRJsonReader({
			totalProperty : "results",
			root : "rows"
		}, new Ext.data.Record.create([{
					name : 'id',
					mapping : 'id'
				}, {
					name : 'no',
					mapping : 'no'
				}, {
					name : 'name',
					mapping : 'name'
				}, {
					name : 'model',
					mapping : 'model'
				}, {
					name : 'admin',
					mapping : 'admin'
				}, {
					name : 'company',
					mapping : 'company'
				}, {
					name : 'counter',
					mapping : 'counter'
				}, {
					name : 'type',
					mapping : 'type'
				}, {
					name : 'charge',
					mapping : 'charge'
				}, {
					name : 'status',
					mapping : 'status'
				}, {
					name : 'appd',
					mapping : 'appd'
				}, {
					name : 'appt1',
					mapping : 'appt1'
				}, {
					name : 'appt2',
					mapping : 'appt2'
				}]));

var storeArray = new Array()
function MyArray(size) {
	for (var i = 0; i <= size; i++)
		this[i] = false;
	this.length = size;
	return this;
};
var tabInited = new MyArray(4);
var cp = new Ext.state.CookieProvider();

Ext.onReady(function() {
	DWREngine.setTimeout(30000);
	Ext.BLANK_IMAGE_URL = 'resources/images/default/s.gif';
	var cookie_theme = cp.get("cookie_theme");
	if (cookie_theme == null || cookie_theme == "")
		cookie_theme = "resources/css/ext-all.css";
	Ext.util.CSS.swapStyleSheet('theme', cookie_theme);
	Ext.state.Manager.setProvider(cp);
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

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

	var myYuyueButton = new Ext.Button({
				text : '预约记录',
				tooltip : '我的预约历史记录',
				iconCls : 'yuyue',
				renderTo : 'my-yuyue'
			});
	myYuyueButton.on("click", myBook);
	var myShengpiButton = new Ext.Button({
				text : '审批记录',
				tooltip : '我的审批历史记录',
				iconCls : 'shengpi',
				renderTo : 'my-shengpi'
			});
	myShengpiButton.on("click", myAdminBook);
	if (islogin) {
		$("my-yuyue").style.visibility = "visible";
		$("my-shengpi").style.visibility = "visible";
	}

	$("loading_div").parentNode.removeChild($("loading_div"));
	Ext.getDom("theme-selector").style.visibility = "visible";
	$("account_div").style.visibility = "visible";

	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [new Ext.BoxComponent({
									region : 'north',
									el : 'north-div',
									height : 82
								}), tabPanel]
			});

	var w = (window.innerWidth)
			? window.innerWidth
			: (document.documentElement && document.documentElement.clientWidth)
					? document.documentElement.clientWidth
					: document.body.offsetWidth;
	if (w - 25 < 900)
		w = 925;

	var panel = new Ext.Panel({
		id : 'tab-panel',
		baseCls : 'x-plain',
		renderTo : Ext.get("center"),
		iconCls : 'home',
		layout : 'table',
		layoutConfig : {
			columns : 2
		},
		defaults : {
			frame : true,
			width : w - 25,
			height : 355
		},
		items : [{
			title : '使用说明',
			colspan : 2,
			height : 370,
			collapsible : true,
			collapsed : true,
			contentEl : 'introduceDiv',
			listeners : {
				"beforeexpand" : {
					fn : function() {
						if (introduceMessageLoaded)
							return;
						DWRUtil.useLoadingMessage("加载中...");
						Message.getIntroduceMessagesByType(0, {
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
			title : '公共预约信息',
			colspan : 2,
			collapsible : true,
			contentEl : 'messageDiv0'
		}, {
			title : '公共审批信息',
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

	for (var i = 0; i < equipArray.length; i++) {
		initUserEquipList(i);
	}
	initUserEquipSearchList();

	tabPanel.addListener('tabchange', function(tp, tab) {
				if (tab.id.indexOf("_") == 0) {
					var index = parseInt(tab.id.substring(10));
					if (!tabInited[index]) {
						storeArray[index].load({
									params : {
										start : 0,
										limit : pageSize
									}
								});
						tabInited[index] = true;
					}
				}
			});

	initUserLogNotice();
	if(!islogin&&cp.get("login_cookie")){
		DWRUtil.useLoadingMessage("正在加载...");
		$("account_content").innerHTML = "自动登录中,请稍候...";
		Account.cookieLogin(cp.get("login_cookie"),{
			callback:function(value) {
				cancelLoadingMessage();
				var result = Ext.decode(value);
				if(result.type==0){
					$("account_content").innerHTML = getWelcomeMessage(result.message);
					$("my-yuyue").style.visibility = "visible";
					$("my-shengpi").style.visibility = "visible";
					getRecentLogsNotice();
				}else
				$("account_content").innerHTML = "<a href='#' onclick='showLoginWin();return false;'>登录</a>&nbsp;|&nbsp;<a href='index.action'>转到首页</a>&nbsp;|&nbsp;<a href='equipview.action'>设备查询系统</a>";
			},
			timeout:8000,
			errorHandler:function(message){
				$("account_content").innerHTML = "<a href='#' onclick='showLoginWin();return false;'>登录</a>&nbsp;|&nbsp;<a href='index.action'>转到首页</a>&nbsp;|&nbsp;<a href='equipview.action'>设备查询系统</a>";
				cancelLoadingMessage();
			}
		});
	}
});

function getRecentLogsNotice() {
	if (!islogin)
		return;
	Notice.getRecentLogsNotice({
		callback : function(value) {
			if (value != null && value != "") {
				if (timeoutID != null)
					clearTimeout(timeoutID);
				$("msg_content").innerHTML = "<img style='width:14px;height:14px;' src='resources/icons/info.png' alt='To Do'/>&nbsp;"
						+ value;
				$("msg").style.display = "block";
			}
		},
		errorHandler : function(message) {
		}
	});
}

function equipRefresh(index) {
	storeArray[index].reload();
}

function myAdminBook() {
	if (!islogin) {
		showMsg("该操作需要<a href='#' onclick='showLoginWin();return false;'>登录</a>后才能继续！");
		return;
	}
	var tab = tabPanel.getItem("my-admin-book");
	if (tab)
		tabPanel.remove(tab);
	tab = addMyAdminBookTab();
	tabPanel.setActiveTab(tab);
}

function myBook() {
	if (!islogin) {
		showMsg("该操作需要<a href='#' onclick='showLoginWin();return false;'>登录</a>后才能继续！");
		return;
	}
	var tab = tabPanel.getItem("my-book");
	if (tab)
		tabPanel.remove(tab);
	tab = addMyBookTab();
	tabPanel.setActiveTab(tab);
}

function addMyBookTab() {
	initMyBookManager();
}

function addMyAdminBookTab() {
	initMyAdminBookManager();
}

function addQueueTab(equip_id, equip_name) {
	initQueueEquipManager(equip_id, equip_name);
}

var detailWin;
var bookWin;

function showBookWin(equip_id, equip_name, appds, appt1, appt2, status) {
	if (!islogin) {
		showMsg("该操作需要<a href='#' onclick='showLoginWin();return false;'>登录</a>后才能继续！");
		return;
	}
	if (!status) {
		showMsg("该设备暂时不接受预约！");
		return;
	}
	var appd = eval("([" + appds + "])");

	Ext.getDom("book-win").innerHTML = "";
	bookWin = null;

	var form = new Ext.FormPanel({
				labelAlign : 'right',
				buttonAlign : 'left',
				frame : true,
				labelWidth : 83,
				waitMsgTarget : true,
				items : [new Ext.form.Hidden({
									name : 'id'
								}), new Ext.form.TextField({
									fieldLabel : '样品名称*',
									name : 'sample_name',
									width : 250,
									maxLength : 100,
									allowBlank : false,
									itemCls : 'stop-float'
								}), new Ext.form.NumberField({
									fieldLabel : '样品总数*',
									allowDecimals : false,
									emptyText : '输入数字，样品数量会决定实验时间段',
									name : 'sample_mount',
									width : 250,
									minValue : 1,
									maxLength : 5,
									allowBlank : false,
									listeners : {
										"focus" : {
											fn : function() {
												form.getForm().findField("end")
														.setValue("");
												form
														.getForm()
														.findField("compute_fee")
														.setValue("");
												form.getForm()
														.findField("exp_time")
														.setValue("");
											},
											scope : this
										}
									},
									itemCls : 'stop-float'
								}), {
							xtype : 'xdatetime',
							name : 'start',
							fieldLabel : '预约起始时间*',
							anchor : '-35',
							timeWidth : 105,
							timeFormat : 'H:i',
							listeners : {
								"focus" : {
									fn : function() {
										form.getForm().findField("end")
												.setValue("");
										form.getForm().findField("compute_fee")
												.setValue("");
										form.getForm().findField("exp_time")
												.setValue("");
									},
									scope : this
								}
							},
							timeConfig : {
								allowBlank : false,
								minValue : appt1,
								maxValue : appt2,
								increment : 30
							},
							dateFormat : 'Y-m-d',
							dateConfig : {
								minValue : new Date().format('Y-m-d'),
								disabledDays : appd,
								allowBlank : false
							}
						}, new Ext.form.TextField({
									fieldLabel : '预约结束时间',
									name : 'end',
									emptyText : '结束时间由系统自动计算出,按“计算”',
									readOnly : true,
									width : 250
								}), new Ext.form.TextField({
									fieldLabel : '实验时长',
									name : 'exp_time',
									emptyText : '如果太短，请适当修改样品数量',
									readOnly : true,
									width : 250
								}), new Ext.form.TextField({
									fieldLabel : '预期实验费用',
									name : 'compute_fee',
									emptyText : '实验费用由系统自动计算出,供参考',
									readOnly : true,
									width : 250,
									maxLength : 10
								}), new Ext.form.TextArea({
									fieldLabel : '预约内容',
									name : 'content',
									maxLength : 300,
									height : 100,
									width : 250
								})]
			});
	bookWin = new Ext.Window({
		el : 'book-win',
		modal : true,
		layout : 'fit',
		width : 400,
		height : 330,
		items : [form],
		closeAction : 'hide',
		listeners : {
			"beforehide" : {
				fn : function() {
					yuyue.start = form.getForm().findField("start").getValue();
					yuyue.sample_mount = form.getForm()
							.findField("sample_mount").getValue();
					yuyue.sample_name = form.getForm().findField("sample_name")
							.getValue();
					yuyue.content = form.getForm().findField("content")
							.getValue();
				},
				scope : this
			}
		},
		buttons : [{
			text : '计算',
			tooltip : '根据样品数计算预约结束日期和预期费用',
			handler : function() {
				form.getForm().findField("start").updateValue();
				if (form.form.isValid()) {
					var startString = form.getForm().findField("start")
							.getValue();
					var sample_mount = form.getForm().findField("sample_mount")
							.getValue();
					var id = form.getForm().findField("id").getValue();
					DWRUtil.useLoadingMessage("处理中...");
					Book.computerEndDateAndFee(id, startString
									.format("Y-m-d H:i"), sample_mount, {
								callback : function(value) {
									cancelLoadingMessage();
									var result = Ext.decode(value);
									if (result.result) {
										form.getForm().findField("end")
												.setValue(result.endDate);
										form.getForm().findField("compute_fee")
												.setValue(result.compute_fee);
										form.getForm().findField("exp_time")
												.setValue(result.exp_time);
										if (result.invalid == true) {
											form
													.getForm()
													.findField("end")
													.markInvalid(result.invalidmsg);
										}
									} else
										currentMsgBox = Ext.Msg.alert("预约报告: ",
												result.message);
								},
								errorHandler : function(message) {
									cancelLoadingMessage();
									Ext.Msg.alert("错误", "对不起，程序出现错误!");
								}
							});

				}
			}
		}, {
			text : '预约',
			handler : function() {
				form.getForm().findField("start").updateValue();
				if (form.form.isValid()) {
					var startString = form.getForm().findField("start")
							.getValue();
					var endString = form.getForm().findField("end").getValue();
					var sample_mount = form.getForm().findField("sample_mount")
							.getValue();
					var sample_name = form.getForm().findField("sample_name")
							.getValue();
					var compute_fee = form.getForm().findField("compute_fee")
							.getValue();
					var content = form.getForm().findField("content")
							.getValue();
					var id = form.getForm().findField("id").getValue();
					if (endString == '' || compute_fee == '') {
						Ext.Msg.alert("预约报告", "请先按“计算”确定预约结束日期和费用!");
					} else {
						bookWin.hide();
						DWRUtil.useLoadingMessage("处理中...");
						Book.saveBook(id, startString.format("Y-m-d H:i"),
								endString, sample_name, sample_mount,
								compute_fee, content, {
									callback : function(value) {
										cancelLoadingMessage();
										var result = Ext.decode(value);
										if (result.result) {
											showMsg(result.message);
										} else
											currentMsgBox = Ext.Msg.alert(
													"预约报告: ", result.message);
									},
									errorHandler : function(message) {
										cancelLoadingMessage();
										Ext.Msg.alert("错误", "对不起，程序出现错误!");
									}
								});
					}
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
				bookWin.hide();
			}
		}]
	});
	bookWin.show();
	bookWin.items.last().getForm().findField("id").setValue(equip_id);
	bookWin.setTitle("设备预约: " + equip_name);
	var TopicRecord = Ext.data.Record.create([{
		name : 'id',
		mapping : 'id'
	}]);
	var formRecord = new TopicRecord(yuyue);
	bookWin.items.last().getForm().loadRecord(formRecord);

}

var store = null;
var params = new Object();
function showDetailWin(id, name, appd, appt1, appt2, status) {
	params.id = id;
	params.name = name;
	params.appd = appd;
	params.appt1 = appt1;
	params.appt2 = appt2;
	params.status = status;
	if (!detailWin) {
		detailWin = new Ext.Window({
					el : 'equip-edit-win',
					title : '查看设备记录',
					modal : true,
					autoScroll : true,
					layout : 'fit',
					closeAction : 'hide',
					width : 780,
					height : 565,
					resizable : false,
					items : [new Ext.Panel({
								id : 'equippanel0',
								collapsible : true,
								autoScroll : true,
								frame : true,
								items : [new Ext.Panel({
													id : 'equippanel',
													collapsible : true,
													autoHeight : true,
													border : false
												}), new Ext.Panel({
													contentEl : 'equip-queue-panel',
													collapsible : true,
													autoScroll : true,
													border : false
												})],
								border : false
							})],
					buttons : [{
						text : '预约该设备',
						handler : function() {
							detailWin.hide();
							showBookWin(params.id, params.name, params.appd,
									params.appt1, params.appt2, params.status);
						}
					}, {
						text : '取消',
						handler : function() {
							detailWin.hide();
						}
					}]
				});
		var grid = null;
		var reader = new Ext.data.DWRJsonReader({
					totalProperty : "results",
					root : "rows"
				}, new Ext.data.Record.create([{
							name : 'id',
							mapping : 'id'
						}, {
							name : 'input',
							mapping : 'input'
						}, {
							name : 'equip_id',
							mapping : 'equip_id'
						}, {
							name : 'equip_name',
							mapping : 'equip_name'
						}, {
							name : 'equip_no',
							mapping : 'equip_no'
						}, {
							name : 'user_name',
							mapping : 'user_name'
						}, {
							name : 'user_id',
							mapping : 'user_id'
						}, {
							name : 'start',
							mapping : 'start'
						}, {
							name : 'end',
							mapping : 'end'
						}, {
							name : 'exp_time',
							mapping : 'exp_time'
						}, {
							name : 'expired',
							mapping : 'expired'
						}, {
							name : 'action',
							mapping : 'action'
						}, {
							name : 'content',
							mapping : 'content'
						}, {
							name : 'remark',
							mapping : 'remark'
						}, {
							name : 'sample',
							mapping : 'sample'
						}, {
							name : 'teacher',
							mapping : 'teacher'
						}]));

		store = new Ext.data.Store({
					proxy : new Ext.data.DWRProxy({
								dwrFunction : Book.getBooksInfoByEquipId,
								listeners : {
									'beforeload' : function(dataProxy, params) {
										params[dataProxy.loadArgsKey] = [
												params.start, params.limit,
												params.id]
									}
								}
							}),
					reader : reader
				});

		var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
					header : '填写日期',
					width : 100,
					sortable : true,
					dataIndex : 'input'
				}, {
					header : '申请人',
					width : 65,
					sortable : true,
					dataIndex : 'user_name'
				}, {
					header : '导师',
					width : 65,
					sortable : true,
					dataIndex : 'teacher'
				}, {
					header : '审批情况',
					width : 65,
					sortable : true,
					dataIndex : 'action',
					renderer : function(value) {
						if (value == 0)
							return "<font color=blue>待批准</font>";
						else if (value == 2)
							return "<font color=red>已删除</font>";
						else
							return "<font color=green>已批准</font>";
					}
				}, {
					header : '起始日期',
					width : 100,
					sortable : true,
					dataIndex : 'start'
				}, {
					header : '中止日期',
					width : 100,
					sortable : true,
					dataIndex : 'end'
				}, {
					header : '实验时长',
					width : 60,
					sortable : true,
					dataIndex : 'exp_time'
				}, {
					header : '是否过期',
					width : 65,
					sortable : true,
					dataIndex : 'expired',
					renderer : function(value) {
						if (value)
							return "<font color='red'>是</font>";
						else
							return "<font color='green'>否</font>";
					}
				}, {
					header : '样品/数量',
					width : 105,
					sortable : true,
					dataIndex : 'sample'
				}]);

		grid = new Ext.grid.GridPanel({
					height : 315,
					title : "设备预约列表",
					border : false,
					store : store,
					iconCls : 'tabs',
					renderTo : 'equip-queue-panel',
					cm : cm,
					loadMask : true,
					closable : true,
					tbar : new Ext.PagingToolbar({
								pageSize : pageSize,
								store : store,
								displayInfo : true
							})
				});

		var rightClick = new Ext.menu.Menu({
					id : 'queueequiprightClickCont',
					items : [{
								id : 'queueequipMessageMenu',
								handler : messageBook,
								iconCls : 'view',
								text : '查看预约内容'
							}]
				});

		grid.addListener('rowcontextmenu', function(grid, rowindex, e) {
					e.preventDefault();
					grid.getSelectionModel().selectRow(rowindex);
					rightClick.showAt(e.getXY());
				});
	}

	detailWin.show();
	detailWin.setTitle("查看设备记录: " + name);

	Ext.get("equippanel").load({
				url : "detail.action?id=" + id,
				text : "设备信息加载中...",
				scripts : true
			});

	store.load({
				params : {
					start : 0,
					limit : pageSize,
					id : id
				}
			});

	function messageBook() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		if (!islogin) {
			detailWin.hide();
			showMsg("该操作需要<a href='#' onclick='showLoginWin();return false;'>登录</a>后才能继续！");
			return;
		}
		DWRUtil.useLoadingMessage("处理中...");
		Book.messageBook(record.data["id"], {
					callback : function(value) {
						cancelLoadingMessage();
						Ext.MessageBox.show({
									title : '查看预约内容',
									msg : '以下显示预约内容',
									width : 350,
									buttons : Ext.MessageBox.OK,
									multiline : true,
									value : value
								});

					},
					errorHandler : function(message) {
						Ext.Msg.alert("错误", "对不起，程序出现错误!");
						cancelLoadingMessage();
					}
				});
	}
}
