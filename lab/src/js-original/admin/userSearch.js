function initUserSearch(id, tabTitle) {
	Ext.getDom("search-content").innerTHML = "";
	Ext.getDom("search-edit-win").innerHTML = "";
	
	var keyword = "";
	var column = "no";

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
		name : 'mobile',
		mapping : 'mobile'
	}, {
		name : 'type',
		mapping : 'type'
	}, {
		name : 'changed',
		mapping : 'changed'
	}, {
		name : 'disabled',
		mapping : 'disabled'
	}, {
		name : 'teacher',
		mapping : 'teacher'
	}, {
		name : 'admin',
		mapping : 'admin'
	}]));

	var store = new Ext.data.GroupingStore({
		reader : reader,
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Account.getAccountsBySearch,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, keyword, column]
				}
			}
		}),
		groupField : 'name',
		groupOnSort : true,
		sortInfo : {
			field : 'admin',
			direction : "ASC"
		}
	});

	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), sm, {
		header : '用户名',
		width : 100,
		sortable : true,
		dataIndex : 'no'
	}, {
		header : '姓名',
		width : 100,
		sortable : true,
		dataIndex : 'name'
	}, {
		header : '导师',
		width : 100,
		sortable : true,
		dataIndex : 'teacher'
	}, {
		header : '联系方式',
		width : 120,
		sortable : true,
		dataIndex : 'mobile'
	}, {
		header : '用户已修改密码',
		width : 100,
		sortable : true,
		dataIndex : 'changed',
		renderer : function(value) {
			if (value)
				return "<font color='green'>是</font>";
			else
				return "<font color='red'>否</font>";
		}
	}, {
		header : '禁用预约',
		width : 100,
		sortable : true,
		dataIndex : 'disabled',
		renderer : function(value) {
			if (value)
				return "<font color='red'>是</font>";
			else
				return "<font color='green'>否</font>";
		}
	}, {
				header : '帐户类型',
				width : 100,
				sortable : true,
				dataIndex : 'admin',
				renderer : function(value) {
					if (value == 1)
						return "学生";
					else if (value == 2)
						return "设备管理员";
					else if (value == 4)
						return "老师";
					else
						return "系统管理员";
				}
			}, {
				header : '管理设备组别',
				width : 110,
				sortable : true,
				dataIndex : 'type',
				renderer : function(value, cellmeta, record) {
					if (record.data["admin"] == 1 || record.data["admin"] == 4)
						return "<i>不可用</i>";
					else if (value == 0)
						return "所有设备";
					else if (equipArray[value])
						return equipArray[value];
					else
						return "其他";
				}
	}]);
	
	var searchColumn = new Ext.form.ComboBox({
		store : new Ext.data.SimpleStore({
			fields : ['returnValue', 'displayValue'],
			data : [['username','用户姓名'],['no','登录用户名'],['teacher','导师'],['mobile','联系方式']]
		}),
		valueField : 'returnValue',
		displayField : 'displayValue',
		allowBlank : false,
		typeAhead : true,
		editable : false,
		mode : 'local',
		triggerAction : 'all',
		value : 'username',
		width : 100,
		editable : false,
		selectOnFocus : true
	});

	var grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		store : store,
		renderTo : 'search-content',
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
		collapsible : true,
		loadMask : true,
		iconCls : 'user',
		closable : true,
		tbar : [{
			text : '批量删除',
			tooltip : '批量删除',
			iconCls : 'userdel',
			onClick : function() {
				if (sm.hasSelection()) {
					var list = sm.getSelections();
					if (list.length == 1) {
						removeAccount();
						return;
					}
					var rList = [];
					for (var i = 0; i < list.length; i++) {
						rList[i] = list[i].data["id"];
					}
					batchRemoveAccount(rList);
				} else {
					showMsg("请至少选择一条记录!");
				}
			}
		}, '-', {
			id : 'keyword',
			emptyText : emptySearchText,
			xtype : 'textfield',
			width : 100,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						keyword = Ext.getCmp("keyword").getEl().getValue();
						if(keyword==emptySearchText) keyword="";
						column = searchColumn.getValue();
						store.reload({
							params : {
								start : 0,
								limit : pageSize
							}
						});
					}
				}
			}
		}, {
			text : '搜索字段:',
			xtype : 'tbtext'
		}, searchColumn, {
			text : '搜索',
			tooltip : '搜索',
			iconCls : 'search',
			onClick : function() {
				keyword = Ext.getCmp("keyword").getEl().getValue();
				if(keyword==emptySearchText) keyword="";
				column = searchColumn.getValue();
				store.reload({
					params : {
						start : 0,
						limit : pageSize
					}
				});
			}
		}, '-', {
			text : 'Tip:请选择一条或多条用户记录操作或单击记录的右键菜单',
			xtype : 'tbtext'
		}],
		bbar : new Ext.PagingToolbar({
			pageSize : pageSize,
			store : store,
			displayInfo : true
		})
	});

	var rightClick = new Ext.menu.Menu({
		id : 'searchaccountrightClickCont',
		items : [{
			id : 'searchaccountEditMenu',
			handler : editAccount,
			iconCls : 'modify',
			text : '修改详情'
		}, {
			id : 'searchaccountRemoveMenu',
			handler : removeAccount,
			iconCls : 'userdel',
			text : '删除用户'
		}]
	});

	grid.addListener('rowcontextmenu', function(grid, rowindex, e) {
		e.preventDefault();
		grid.getSelectionModel().selectRow(rowindex);
		rightClick.showAt(e.getXY());
	});

	tabPanel.add(grid).show();

	function batchRemoveAccount(list) {
		Ext.MessageBox.confirm('确认批量删除用户', '是否确认批量删除用户？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Account.batchRemoveAccount(list, {
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

	function removeAccount() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		Ext.MessageBox.confirm('确认删除用户', '是否确认删除用户？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Account.removeAccount(record.data["id"], {
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

	function editAccount() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		showEditWin(record.data["id"]);
	}

	var editWin;

	function showEditWin(id) {
		if (!editWin) {
			var form = new Ext.FormPanel({
				labelWidth : 80,
				frame : true,
				defaultType : 'textfield',
				items : [new Ext.form.Hidden({
					name : 'id'
				}), new Ext.form.TextField({
					fieldLabel : '工号/用户名*',
					name : 'no',
					width : 180,
					maxLength : 50,
					allowBlank : false
				}), new Ext.form.TextField({
					fieldLabel : '姓名*',
					name : 'name',
					width : 180,
					maxLength : 50,
					allowBlank : false
				}), new Ext.form.TextField({
					fieldLabel : '导师',
					name : 'teacher',
					width : 180,
					maxLength : 50
				}), new Ext.form.TextField({
					fieldLabel : '联系方式',
					name : 'mobile',
					width : 180,
					maxLength : 50
				}), new Ext.form.TextField({
					fieldLabel : '修改密码',
					inputType : 'password',
					name : 'password',
					width : 180,
					maxLength : 50
				}), new Ext.form.TextField({
					fieldLabel : '确认密码',
					name : 'repassword',
					inputType : 'password',
					width : 180,
					maxLength : 50
				}), {
					xtype : 'checkbox',
					fieldLabel : '密码状态',
					boxLabel : '用户已修改密码',
					checked : true,
					name : 'changed'
				}, {
					xtype : 'checkbox',
					fieldLabel : '预约状态',
					boxLabel : '禁用预约',
					checked : true,
					name : 'disabled'
				}, new Ext.form.ComboBox({
					fieldLabel : '账户类别*',
					hiddenName : 'admin',
					store : new Ext.data.SimpleStore({
						fields : ['returnValue', 'displayValue'],
						data : [[1, '学生'], [4, '老师'],[2, '设备管理员'], [3, '系统管理员']]
					}),
					valueField : 'returnValue',
					displayField : 'displayValue',
					typeAhead : true,
					allowBlank : false,
					width : 180,
					mode : 'local',
					triggerAction : 'all',
					emptyText : '选择账户类别...',
					selectOnFocus : true,
					editable : false,
					listeners : {
						"select" : {
							fn : function(combo, record, index) {
								if (index > 1)
									Ext.getCmp("searchaccountTypeCombo")
											.setDisabled(false);
								else
									Ext.getCmp("searchaccountTypeCombo")
											.setDisabled(true);
							},
							scope : this
						}
					}
				}), new Ext.form.ComboBox({
					fieldLabel : '所属组别*',
					hiddenName : 'type',
					id : 'searchaccountTypeCombo',
					store : new Ext.data.SimpleStore({
						fields : ['returnValue', 'displayValue'],
						data : groupArray
					}),
					valueField : 'returnValue',
					displayField : 'displayValue',
					allowBlank : false,
					width : 180,
					typeAhead : true,
					mode : 'local',
					triggerAction : 'all',
					emptyText : '选择组别...',
					selectOnFocus : true,
					editable : false
				})]
			});

			editWin = new Ext.Window({
				el : 'search-edit-win',
				title : '修改账户记录',
				modal : true,
				layout : 'fit',
				closeAction : 'hide',
				width : 305,
				height : 335,
				resizable : false,
				items : [form],
				buttons : [{
					text : '保存',
					handler : function() {
						if (form.form.isValid()) {
							if (form.getForm().findField("password").getValue() != form
									.getForm().findField("repassword")
									.getValue()) {
								form.getForm().findField("repassword")
										.markInvalid("两次密码输入不一致！");
								return;
							}
							var account = new Object();
							account.id = form.getForm().findField("id")
									.getValue();
							account.name = form.getForm().findField("name")
									.getValue();
							account.teacher = form.getForm().findField("teacher")
									.getValue();
							account.no = form.getForm().findField("no")
									.getValue();
							account.mobile = form.getForm().findField("mobile")
									.getValue();
							account.admin = form.getForm().findField("admin")
									.getValue();
							account.changed = form.getForm()
									.findField("changed").getValue();
							account.disabled = form.getForm()
									.findField("disabled").getValue();
							account.type = form.getForm().findField("type")
									.getValue();
							account.password = form.getForm()
									.findField("password").getValue();
							editWin.hide();
							DWRUtil.useLoadingMessage("处理中...");
							Account.updateAccount(Ext.encode(account), {
								callback : function(value) {
									cancelLoadingMessage();
									var result = Ext.decode(value);
									if (result.result) {
										form.form.reset();
										showMsg(result.message);
										store.reload();
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
		Account.getAccountDetailInfoById(id, {
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
				var formRecord = new TopicRecord(Ext.decode(value));
				editWin.show();
				editWin.items.last().getForm().loadRecord(formRecord);
				if(Ext.decode(value).admin==1||Ext.decode(value).admin==4)
					Ext.getCmp("searchaccountTypeCombo").setDisabled(true);
				else
					Ext.getCmp("searchaccountTypeCombo").setDisabled(false);
			},
			errorHandler : function(message) {
				Ext.Msg.alert("错误", "对不起，程序出现错误!");
				cancelLoadingMessage();
			}
		});

	}

}
