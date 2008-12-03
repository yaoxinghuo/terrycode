var noticeContent = "";
var editorInstance0;
var editorInstance1;
var currentInstance = 0;
function initNoticeManager(id, tabTitle) {
	Ext.getDom("notice-content").innerTHML = "";
	Ext.getDom("notice-add-win").innerHTML = "";
	Ext.getDom("notice-edit-win").innerHTML = "";

	var grid = null;
	var store = null;
	var lastChecked = false;
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
		name : 'title',
		mapping : 'title'
	}, {
		name : 'type',
		mapping : 'type'
	}, {
		name : 'date',
		mapping : 'date'
	}, {
		name : 'pub',
		mapping : 'pub'
	}]));

	store = new Ext.data.GroupingStore({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Notice.getNotices,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, lastChecked]
				}
			}
		}),
		reader : reader,
		groupField : 'title',
		groupOnSort : true,
		sortInfo : {
			field : 'type',
			direction : "ASC"
		}
	});

	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), sm, {
		header : '填写日期',
		width : 100,
		sortable : true,
		dataIndex : 'input'
	}, {
		header : '公告类型',
		width : 100,
		sortable : true,
		dataIndex : 'type',
		renderer : function(value) {
			if (value == 1)
				return "通知公告";
			else if (value == 2)
				return "设备使用公告";
			else if (value == 3)
				return "管理制度";
			else
				return "实验室概况";
		}
	}, {
		header : '公告标题',
		width : 300,
		sortable : true,
		dataIndex : 'title'
	}, {
		header : '显示日期',
		width : 100,
		sortable : true,
		dataIndex : 'date'
	}, {
		header : '前台显示',
		width : 70,
		sortable : true,
		dataIndex : 'pub',
		renderer : function(value) {
			if (value)
				return "<font color='red'>是</font>";
			else
				return "<font color='green'>否</font>";
		}
	}]);

	grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		store : store,
		renderTo : 'notice-content',
		header : false,
		cm : cm,
		sm : sm,
		view : new Ext.grid.GroupingView({
			forceFit : true,
			sortAscText : '正序',
			sortDescText : '倒序',
			columnsText : '列显示/隐藏',
			groupByText : '依本列分组',
			showGroupsText : '分组显示',
			groupTextTpl : '{text} ({[values.rs.length]} 条记录)'
		}),
		loadMask : true,
		iconCls : 'notice',
		closable : true,
		tbar : [{
			text : '新建公告',
			tooltip : '新建公告',
			iconCls : 'add',
			onClick : function() {
				noticeContent = "";
				showAddWin();
			}
		}, '-', {
			text : '批量删除',
			tooltip : '批量删除',
			iconCls : 'remove',
			onClick : function() {
				if (sm.hasSelection()) {
					var list = sm.getSelections();
					if (list.length == 1) {
						removeNotice();
						return;
					}
					var rList = [];
					for (var i = 0; i < list.length; i++) {
						rList[i] = list[i].data["id"];
					}
					batchRemoveNotices(rList);
				} else {
					showMsg("请至少选择一条记录!");
				}
			}
		}, '-', {
			text : '刷新',
			tooltip : '刷新',
			iconCls : 'refresh',
			onClick : function() {
				store.reload();
			}
		}, '-', {
			xtype : 'checkbox',
			boxLabel : '<b>前台显示的记录</b>',
			checked : false,
			onClick : function() {
				if (lastChecked != this.getValue()) {
					lastChecked = this.getValue();
					store.reload({
						params : {
							start : 0,
							limit : pageSize
						}
					});
				}
			}
		}, '-', {
			text : 'Tip:请选择一条或多条公告记录操作或单击记录的右键菜单',
			xtype : 'tbtext'
		}],
		bbar : new Ext.PagingToolbar({
			pageSize : pageSize,
			store : store,
			displayInfo : true
		})
	});
	store.load({
		params : {
			start : 0,
			limit : pageSize
		}
	});

	var rightClick = new Ext.menu.Menu({
		id : 'noticerightClickCont',
		items : [{
			id : 'noticeEditMenu',
			handler : editNotice,
			iconCls : 'view',
			text : '查看/编辑公告'
		}, {
			id : 'noticeRemoveMenu',
			handler : removeNotice,
			iconCls : 'remove',
			text : '删除'
		}]
	});

	grid.addListener('rowcontextmenu', function(grid, rowindex, e) {
		e.preventDefault();
		grid.getSelectionModel().selectRow(rowindex);
		rightClick.showAt(e.getXY());
	});

	tabPanel.add(grid).show();

	function editNotice() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		showEditWin(record.data["id"]);
	}

	function batchRemoveNotices(list) {
		Ext.MessageBox.confirm('确认批量删除公告', '是否确认批量删除公告？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Notice.batchRemoveNotice(list, {
				callback : function(value) {
					cancelLoadingMessage();
					var result = Ext.decode(value);
					if (result.result) {
						store.reload();
						showMsg(result.message);
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

	function removeNotice() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		Ext.MessageBox.confirm('确认删除公告', '是否确认删除公告？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Notice.removeNotice(record.data["id"], {
				callback : function(value) {
					cancelLoadingMessage();
					var result = Ext.decode(value);
					if (result.result) {
						store.remove(record);
						showMsg(result.message);
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

	var addWin = null;
	var editWin = null;
	var oFCKeditor;
	var e_oFCKeditor;

	function showAddWin() {
		currentInstance = 0;
		if (!addWin) {
			var form = new Ext.FormPanel({
				labelWidth : 75,
				labelAlign : 'right',
				frame : true,
				items : [{
					xtype : 'checkbox',
					fieldLabel : '标题样式',
					boxLabel : '粗体',
					itemCls : 'notice-left0',
					clearCls : 'allow-float',
					name : 'bold'
				}, {
					xtype : 'checkbox',
					fieldLabel : '',
					hideLabel : true,
					boxLabel : '红色',
					itemCls : 'notice-left',
					clearCls : 'allow-float',
					name : 'red'
				}, new Ext.form.TextField({
					fieldLabel : '公告标题*',
					name : 'title',
					width : 350,
					maxLength : 50,
					itemCls : 'notice-left',
					clearCls : 'stop-float',
					allowBlank : false
				}), new Ext.form.ComboBox({
					fieldLabel : '公告类别*',
					hiddenName : 'type',
					store : new Ext.data.SimpleStore({
						fields : ['returnValue', 'displayValue'],
						data : [[1, '通知公告'], [2, '设备使用公告'], [3, '管理制度'],
								[4, '实验室概况']]
					}),
					valueField : 'returnValue',
					displayField : 'displayValue',
					typeAhead : true,
					allowBlank : false,
					itemCls : 'comp-left0',
					clearCls : 'allow-float',
					width : 180,
					mode : 'local',
					triggerAction : 'all',
					emptyText : '选择公告类别...',
					selectOnFocus : true,
					editable : false
				}), {
					xtype : 'datefield',
					fieldLabel : '显示日期*',
					name : 'date',
					width : 100,
					format : 'Y-m-d',
					itemCls : 'notice-left',
					clearCls : 'allow-float',
					allowBlank : false
				}, {
					xtype : 'checkbox',
					fieldLabel : '显示状态',
					boxLabel : '前台显示',
					itemCls : 'notice-left',
					clearCls : 'stop-float',
					name : 'pub'
				}, {
					xtype : 'textarea',
					fieldLabel : '公告正文*',
					id : 'content',
					name : 'content',
					itemCls : 'notice-left0',
					height : 200,
					width : 300
				}]
			});

			addWin = new Ext.Window({
				el : 'notice-add-win',
				title : '新增公告',
				modal : true,
				layout : 'fit',
				closeAction : 'hide',
				width : 800,
				height : 500,
				resizable : false,
				items : [form],
				buttons : [{
					text : '保存',
					handler : function() {
						Ext.get('content').dom.value = editorInstance0
								.GetXHTML(true);
						if (form.form.isValid()) {
							var notice = new Object();
							notice.title = form.getForm().findField("title")
									.getValue();
							notice.bold = form.getForm().findField("bold")
									.getValue();
							notice.red = form.getForm().findField("red")
									.getValue();
							notice.content = form.getForm()
									.findField("content").getValue();
							notice.type = form.getForm().findField("type")
									.getValue();
							notice.pub = form.getForm().findField("pub")
									.getValue();
							notice.date = form.getForm().findField("date")
									.getValue();
							addWin.hide();
							DWRUtil.useLoadingMessage("处理中...");
							Notice.saveNotice(Ext.encode(notice), {
								callback : function(value) {
									cancelLoadingMessage();
									var result = Ext.decode(value);
									if (result.result) {
										editorInstance0.SetHTML("");
										showMsg(result.message);
										store.reload();
										form.form.reset();
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
		if (!oFCKeditor) {
			oFCKeditor = new FCKeditor('content', 680, 350);
			oFCKeditor.BasePath = "/lab/fckeditor/";
			oFCKeditor.ToolbarSet = 'Basic';
			oFCKeditor.ReplaceTextarea();
		}
	}

	function showEditWin(id) {
		currentInstance = 1;
		if (!editWin) {
			var form = new Ext.FormPanel({
				labelWidth : 75,
				labelAlign : 'right',
				frame : true,
				items : [new Ext.form.Hidden({
					name : 'id'
				}), {
					xtype : 'checkbox',
					fieldLabel : '标题样式',
					boxLabel : '粗体',
					itemCls : 'notice-left0',
					clearCls : 'allow-float',
					name : 'bold'
				}, {
					xtype : 'checkbox',
					fieldLabel : '',
					hideLabel : true,
					boxLabel : '红色',
					itemCls : 'notice-left',
					clearCls : 'allow-float',
					name : 'red'
				}, new Ext.form.TextField({
					fieldLabel : '公告标题*',
					name : 'title',
					width : 350,
					maxLength : 50,
					itemCls : 'notice-left0',
					clearCls : 'stop-float',
					allowBlank : false
				}), new Ext.form.ComboBox({
					fieldLabel : '公告类别*',
					hiddenName : 'type',
					store : new Ext.data.SimpleStore({
						fields : ['returnValue', 'displayValue'],
						data : [[1, '通知公告'], [2, '设备使用公告'], [3, '管理制度'],
								[4, '实验室概况']]
					}),
					valueField : 'returnValue',
					displayField : 'displayValue',
					typeAhead : true,
					allowBlank : false,
					itemCls : 'notice-left0',
					clearCls : 'allow-float',
					width : 180,
					mode : 'local',
					triggerAction : 'all',
					emptyText : '选择公告类别...',
					selectOnFocus : true,
					editable : false
				}), {
					xtype : 'datefield',
					fieldLabel : '显示日期*',
					name : 'date',
					width : 100,
					format : 'Y-m-d',
					itemCls : 'notice-left',
					clearCls : 'allow-float',
					allowBlank : false
				}, {
					xtype : 'checkbox',
					fieldLabel : '显示状态',
					boxLabel : '前台显示',
					itemCls : 'notice-left',
					clearCls : 'stop-float',
					name : 'pub'
				}, {
					xtype : 'textarea',
					fieldLabel : '公告正文*',
					id : 'e_content',
					name : 'e_content',
					itemCls : 'notice-left0',
					height : 200,
					width : 300
				}]
			});

			editWin = new Ext.Window({
				el : 'notice-edit-win',
				title : '编辑公告',
				modal : true,
				layout : 'fit',
				closeAction : 'hide',
				width : 800,
				height : 500,
				resizable : false,
				items : [form],
				buttons : [{
					text : '保存',
					handler : function() {
						Ext.get('e_content').dom.value = editorInstance1
								.GetXHTML(true);
						if (form.form.isValid()) {
							var notice = new Object();
							notice.id = form.getForm().findField("id")
									.getValue();
							notice.bold = form.getForm().findField("bold")
									.getValue();
							notice.red = form.getForm().findField("red")
									.getValue();
							notice.title = form.getForm().findField("title")
									.getValue();
							notice.content = form.getForm()
									.findField("e_content").getValue();
							notice.type = form.getForm().findField("type")
									.getValue();
							notice.pub = form.getForm().findField("pub")
									.getValue();
							notice.date = form.getForm().findField("date")
									.getValue();
							editWin.hide();
							DWRUtil.useLoadingMessage("处理中...");
							Notice.updateNotice(Ext.encode(notice), {
								callback : function(value) {
									cancelLoadingMessage();
									var result = Ext.decode(value);
									if (result.result) {
										showMsg(result.message);
										store.reload();
										form.form.reset();
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
		Notice.getNoticeById(id, {
			callback : function(value) {
				cancelLoadingMessage();
				if (!value) {
					Ext.Msg.alert("错误", "对不起，程序出现错误!");
					return;
				}
				var TopicRecord = Ext.data.Record.create([{
					name : 'id',
					mapping : 'id'
				}, {
					name : 'title',
					mapping : 'title'
				}, {
					name : 'bold',
					mapping : 'bold'
				}, {
					name : 'red',
					mapping : 'red'
				}, {
					name : 'date',
					mapping : 'date'
				}, {
					name : 'type',
					mapping : 'type'
				}, {
					name : 'content',
					mapping : 'e_content'
				}, {
					name : 'pub',
					mapping : 'pub'
				},]);
				var formRecord = new TopicRecord(Ext.decode(value));
				noticeContent = Ext.decode(value).content;
				editWin.show();
				editWin.items.last().getForm().loadRecord(formRecord);
				if (!e_oFCKeditor) {
					e_oFCKeditor = new FCKeditor('e_content', 680, 350);
					e_oFCKeditor.BasePath = "/lab/fckeditor/";
					e_oFCKeditor.ToolbarSet = 'Basic';
					e_oFCKeditor.ReplaceTextarea();
				}
				if (editorInstance1)
					editorInstance1.SetHTML(noticeContent);
			},
			errorHandler : function(message) {
				// Ext.Msg.alert("错误","对不起，程序出现错误!"+message);
				cancelLoadingMessage();
			}
		});
	}

}

/**
 * FCKEditor初始化完成将调用此方法
 * 
 * @param {Object}
 *            editorInstance
 */
function FCKeditor_OnComplete(instance) {
	if (currentInstance == 0)
		editorInstance0 = instance;
	else {
		editorInstance1 = instance;
		editorInstance1.SetHTML(noticeContent);
	}
};
