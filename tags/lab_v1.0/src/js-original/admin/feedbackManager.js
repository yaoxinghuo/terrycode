function initFeedbackManager(id, tabTitle) {
	Ext.getDom("feedback-content").innerTHML = "";
	Ext.getDom("feedback-edit-win").innerHTML = "";

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
		name : 'name',
		mapping : 'name'
	}, {
		name : 'input',
		mapping : 'input'
	}, {
		name : 'title',
		mapping : 'title'
	}, {
		name : 'contact',
		mapping : 'contact'
	}, {
		name : 'content',
		mapping : 'content'
	}, {
		name : 'pub',
		mapping : 'pub'
	}, {
		name : 'comment',
		mapping : 'comment'
	}]));

	store = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Notice.getFeedbacks,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, lastChecked]
				}
			}
		}),
		reader : reader
	});

	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), sm, {
		header : '填写日期',
		width : 100,
		sortable : true,
		dataIndex : 'input'
	}, {
		header : '反馈人',
		width : 100,
		sortable : true,
		dataIndex : 'name'
	}, {
		header : '联系方式',
		width : 180,
		sortable : true,
		dataIndex : 'contact'
	}, {
		header : '主题',
		width : 180,
		sortable : true,
		dataIndex : 'title'
	}, {
		header : '内容',
		width : 250,
		sortable : true,
		dataIndex : 'content'
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
	}, {
		header : '管理员留言',
		width : 235,
		sortable : true,
		dataIndex : 'comment'
	}]);

	grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		store : store,
		renderTo : 'feedback-content',
		header : false,
		cm : cm,
		sm : sm,
		loadMask : true,
		iconCls : 'notice',
		closable : true,
		tbar : [{
			text : '批量删除',
			tooltip : '批量删除',
			iconCls : 'remove',
			onClick : function() {
				if (sm.hasSelection()) {
					var list = sm.getSelections();
					if (list.length == 1) {
						removeFeedback();
						return;
					}
					var rList = [];
					for (var i = 0; i < list.length; i++) {
						rList[i] = list[i].data["id"];
					}
					batchRemoveFeedbacks(rList);
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
			id : 'checkbox',
			checked : false,
			listeners : {
				"check" : {
					fn : function(obj,checked) {
						if (lastChecked != checked) {
							lastChecked = checked;
							store.reload({
								params : {
									start : 0,
									limit : pageSize
								}
							});
						}
					},
					scope : this
				}
			}
		}, '-', {
			text : 'Tip:请选择一条或多条反馈记录操作或单击记录的右键菜单',
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
			id : 'feedbackEditMenu',
			handler : editFeedback,
			iconCls : 'view',
			text : '查看/编辑反馈信息'
		}, {
			id : 'feedbackRemoveMenu',
			handler : removeFeedback,
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

	function editFeedback() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		showEditWin(record.data["id"]);
	}

	function batchRemoveFeedbacks(list) {
		Ext.MessageBox.confirm('确认批量删除反馈信息', '是否确认批量删除反馈信息？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Notice.batchRemoveFeedback(list, {
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

	function removeFeedback() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		Ext.MessageBox.confirm('确认删除反馈信息', '是否确认删除反馈信息？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Notice.removeFeedback(record.data["id"], {
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

	var editWin = null;

	function showEditWin(id) {
		currentInstance = 1;
		if (!editWin) {
			var form = new Ext.FormPanel({
				labelWidth : 75,
				labelAlign : 'left',
				frame : true,
				items : [new Ext.form.Hidden({
					name : 'id'
				}), new Ext.form.TextField({
					fieldLabel : '主题*',
					name : 'title',
					width : 280,
					maxLength : 30,
					allowBlank : false
				}), new Ext.form.TextField({
					fieldLabel : '填写人*',
					name : 'name',
					width : 280,
					maxLength : 30,
					allowBlank : false
				}), new Ext.form.TextField({
					fieldLabel : '联系方式',
					name : 'contact',
					width : 280,
					maxLength : 50,
					editable : false
				}), {
					xtype : 'datefield',
					fieldLabel : '填写日期*',
					name : 'input',
					width : 280,
					format : 'Y-m-d',
					allowBlank : false
				}, {
					xtype : 'textarea',
					fieldLabel : '反馈正文*',
					name : 'content',
					height : 100,
					width : 280,
					maxLength : 500,
					allowBlank : false
				}, {
					xtype : 'textarea',
					fieldLabel : '管理员答复',
					name : 'comment',
					height : 100,
					maxLength : 500,
					width : 280
				}, {
					xtype : 'checkbox',
					fieldLabel : '显示状态',
					boxLabel : '前台显示',
					checked : true,
					name : 'pub'
				}]
			});

			editWin = new Ext.Window({
				el : 'feedback-edit-win',
				title : '编辑反馈信息',
				modal : true,
				layout : 'fit',
				closeAction : 'hide',
				width : 405,
				height : 430,
				resizable : false,
				items : [form],
				buttons : [{
					text : '保存',
					handler : function() {
						if (form.form.isValid()) {
							var feedback = new Object();
							feedback.id = form.getForm().findField("id")
									.getValue();
							feedback.title = form.getForm().findField("title")
									.getValue();
							feedback.input = form.getForm().findField("input")
									.getValue();
							feedback.content = form.getForm()
									.findField("content").getValue();
							feedback.contact = form.getForm()
									.findField("contact").getValue();
							feedback.name = form.getForm().findField("name")
									.getValue();
							feedback.pub = form.getForm().findField("pub")
									.getValue();
							feedback.comment = form.getForm()
									.findField("comment").getValue();
							editWin.hide();
							DWRUtil.useLoadingMessage("处理中...");
							Notice.updateFeedback(Ext.encode(feedback), {
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
		Notice.getFeedbackById(id, {
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
					name : 'name',
					mapping : 'name'
				}, {
					name : 'input',
					mapping : 'input'
				}, {
					name : 'title',
					mapping : 'title'
				}, {
					name : 'contact',
					mapping : 'contact'
				}, {
					name : 'content',
					mapping : 'content'
				}, {
					name : 'pub',
					mapping : 'pub'
				}, {
					name : 'comment',
					mapping : 'comment'
				}]);
				var formRecord = new TopicRecord(Ext.decode(value));
				noticeContent = Ext.decode(value).content;
				editWin.show();
				editWin.items.last().getForm().loadRecord(formRecord);
			},
			errorHandler : function(message) {
				Ext.Msg.alert("错误", "对不起，程序出现错误!");
				cancelLoadingMessage();
			}
		});
	}

}
