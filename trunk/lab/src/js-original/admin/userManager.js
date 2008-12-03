function initUserManager(id, tabTitle) {
	Ext.getDom("user-content").innerTHML = "";
	Ext.getDom("user-account-edit-win").innerHTML = "";
	Ext.getDom("user-account-add-win").innerHTML = "";

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
						name : 'teacher',
						mapping : 'teacher'
					}, {
						name : 'changed',
						mapping : 'changed'
					}, {
						name : 'disabled',
						mapping : 'disabled'
					}, {
						name : 'admin',
						mapping : 'admin'
					}]));

	var store = new Ext.data.GroupingStore({
				reader : reader,
				proxy : new Ext.data.DWRProxy({
							dwrFunction : Account.getAccounts,
							listeners : {
								'beforeload' : function(dataProxy, params) {
									params[dataProxy.loadArgsKey] = [
											params.start, params.limit, 1]
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
				header : '学号/用户名',
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
				width : 100,
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
			}]);

	var grid = new Ext.grid.GridPanel({
				id : id,
				title : tabTitle,
				border : false,
				store : store,
				renderTo : 'user-content',
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
							text : '新增用户',
							tooltip : '新增用户记录',
							iconCls : 'useradd',
							onClick : function() {
								showAddWin();
							}
						}, '-', {
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
							text : '刷新',
							tooltip : '刷新',
							iconCls : 'refresh',
							onClick : function() {
								store.reload();
							}
						}, '-', {
							text : '导出为Excel',
							tooltip : '导出为Excel并下载到本地',
							iconCls : 'excel',
							onClick : function() {
								window.location.href = "userdownload?type=1";
							}
						}, {
							text : '导入Excel',
							tooltip : '把本地Excel文件导入到用户库',
							iconCls : 'excel',
							onClick : function() {
								currentStore = store;
								showUploadWin();
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
	store.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	var rightClick = new Ext.menu.Menu({
				id : 'useraccountrightClickCont',
				items : [{
							id : 'useraccountEditMenu',
							handler : editAccount,
							iconCls : 'modify',
							text : '修改详情'
						}, {
							id : 'useraccountRemoveMenu',
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

	var addWin;
	var editWin;

	function showAddWin() {
		if (!addWin) {
			var form = new Ext.FormPanel({
				labelWidth : 80,
				frame : true,
				defaultType : 'textfield',
				items : [new Ext.form.TextField({
									fieldLabel : '学号/用户名*',
									emptyText : '确保该用户名不与其他用户重复',
									name : 'no',
									width : 180,
									maxLength : 50,
									allowBlank : false
								}), new Ext.form.TextField({
									fieldLabel : '姓名*',
									emptyText : '请填写用户真实姓名',
									name : 'name',
									width : 180,
									maxLength : 50,
									allowBlank : false
								}), new Ext.form.TextField({
									fieldLabel : '导师',
									emptyText : '请填写导师姓名（可选）',
									name : 'teacher',
									width : 180,
									maxLength : 50
								}), new Ext.form.TextField({
									fieldLabel : '联系方式',
									emptyText : '请填写手机或Email等',
									name : 'mobile',
									width : 180,
									maxLength : 50
								}), new Ext.form.TextField({
									fieldLabel : '初始密码*',
									inputType : 'password',
									name : 'password',
									id : 'user_password',
									allowBlank : false,
									width : 180,
									maxLength : 50
								}), new Ext.form.TextField({
									fieldLabel : '确认密码*',
									name : 'repassword',
									inputType : 'password',
									vtype : 'password',
									initialPassField : 'user_password',
									allowBlank : false,
									width : 180,
									maxLength : 50
								}), new Ext.form.ComboBox({
							fieldLabel : '账户类别*',
							hiddenName : 'admin',
							store : new Ext.data.SimpleStore({
										fields : ['returnValue', 'displayValue'],
										data : [[1, '学生'], [4, '老师']]
									}),
							valueField : 'returnValue',
							displayField : 'displayValue',
							allowBlank : false,
							width : 180,
							typeAhead : true,
							mode : 'local',
							triggerAction : 'all',
							emptyText : '选择账户类别...',
							selectOnFocus : true,
							editable : false
						})]
			});
			addWin = new Ext.Window({
						el : 'user-account-add-win',
						title : '增加用户记录',
						modal : true,
						layout : 'fit',
						closeAction : 'hide',
						width : 325,
						height : 270,
						resizable : false,
						items : [form],
						buttons : [{
							text : '保存',
							handler : function() {
								if (form.form.isValid()) {
									var equip = new Object();
									var account = new Object();
									account.name = form.getForm()
											.findField("name").getValue();
									account.no = form.getForm().findField("no")
											.getValue();
									account.teacher = form.getForm()
											.findField("teacher").getValue();
									account.mobile = form.getForm()
											.findField("mobile").getValue();
									account.admin = form.getForm()
											.findField("admin").getValue();
									account.type = 1;
									account.password = form.getForm()
											.findField("password").getValue();
									addWin.hide();
									DWRUtil.useLoadingMessage("处理中...");
									Account.saveAccount(Ext.encode(account), {
												callback : function(value) {
													cancelLoadingMessage();
													var result = Ext
															.decode(value);
													if (result.result) {
														form.form.reset();
														showMsg(result.message);
														store.reload();
													} else
														Ext.Msg.alert("报告",
																result.message);
												},
												errorHandler : function(message) {
													Ext.Msg.alert("错误",
															"对不起，程序出现错误!");
													cancelLoadingMessage();
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
								addWin.hide();
							}
						}]
					});
		}
		addWin.show();

	}

	function showEditWin(id) {
		if (!editWin) {
			var form = new Ext.FormPanel({
				labelWidth : 80,
				frame : true,
				defaultType : 'textfield',
				items : [new Ext.form.Hidden({
									name : 'id'
								}), new Ext.form.TextField({
									fieldLabel : '学号/用户名*',
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
									// allowBlank : false,
									maxLength : 50
								}), new Ext.form.TextField({
									fieldLabel : '确认密码',
									name : 'repassword',
									inputType : 'password',
									width : 180,
									// allowBlank : false,
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
							checked : false,
							name : 'disabled'
						}, new Ext.form.ComboBox({
							fieldLabel : '账户类别*',
							hiddenName : 'admin',
							store : new Ext.data.SimpleStore({
										fields : ['returnValue', 'displayValue'],
										data : [[1, '学生'], [4, '老师'],
												[2, '设备管理员'], [3, '系统管理员']]
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
											Ext.getCmp("useraccountTypeCombo")
													.setDisabled(false);
										else
											Ext.getCmp("useraccountTypeCombo")
													.setDisabled(true);
									},
									scope : this
								}
							}
						}), new Ext.form.ComboBox({
							fieldLabel : '所属组别*',
							hiddenName : 'type',
							id : 'useraccountTypeCombo',
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
				el : 'user-account-edit-win',
				title : '修改账户记录',
				modal : true,
				layout : 'fit',
				closeAction : 'hide',
				width : 325,
				height : 340,
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
							account.teacher = form.getForm()
									.findField("teacher").getValue();
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
										if (account.admin != 1
												&& account.admin != 4)
											Ext.MessageBox
													.alert("更改用户类别",
															"您已经选择提升该用户权限，该用户将显示在‘管理员信息’里。");
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
									name : 'admin',
									mapping : 'admin'
								}, {
									name : 'password',
									mapping : 'password'
								}, {
									name : 'repassword',
									mapping : 'repassword'
								}, {
									name : 'changed',
									mapping : 'changed'
								}, {
									name : 'disabled',
									mapping : 'disabled'
								}]);
						var formRecord = new TopicRecord(Ext.decode(value));
						editWin.show();
						editWin.items.last().getForm().loadRecord(formRecord);
						Ext.getCmp("useraccountTypeCombo").setDisabled(true);
					},
					errorHandler : function(message) {
						Ext.Msg.alert("错误", "对不起，程序出现错误!");
						cancelLoadingMessage();
					}
				});

	}

}
