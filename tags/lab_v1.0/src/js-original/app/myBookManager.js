function initMyBookManager() {
	Ext.getDom("my-book-content").innerTHML = "";
	var grid = null;
	var store = null;
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
		name : 'type',
		mapping : 'type'
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
		name : 'expired',
		mapping : 'expired'
	}, {
		name : 'charge',
		mapping : 'charge'
	}, {
		name : 'action',
		mapping : 'action'
	}, {
		name : 'teacher',
		mapping : 'teacher'
	}, {
		name : 'content',
		mapping : 'content'
	}, {
		name : 'sample',
		mapping : 'sample'
	}, {
		name : 'fee',
		mapping : 'fee'
	}, {
		name : 'exp_time',
		mapping : 'exp_time'
	}, {
		name : 'remark',
		mapping : 'remark'
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

	store = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Book.getBooksInfoByUserId,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start, params.limit]
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
		header : '设备名称',
		width : 100,
		sortable : true,
		dataIndex : 'equip_name'
	}, {
		header : '设备编号',
		width : 100,
		hidden : true,
		sortable : true,
		dataIndex : 'equip_no'
	}, {
		header : '是否收费',
		width : 70,
		sortable : true,
		hidden : true,
		dataIndex : 'charge',
		renderer : function(value) {
			if (value)
				return "<font color='red'>是</font>";
			else
				return "<font color='green'>否</font>";
		}
	}, {
		header : '类别',
		width : 100,
		hidden : true,
		sortable : true,
		dataIndex : 'type',
		renderer : function(value) {
			if (equipArray[value])
				return equipArray[value];
			else
				return "其他";
		}
	}, {
		header : '申请人',
		width : 80,
		sortable : true,
		dataIndex : 'user_name'
	}, {
		header : '导师',
		width : 80,
		sortable : true,
		dataIndex : 'teacher'
	}, {
		header : '样品/数量',
		width : 120,
		sortable : true,
		dataIndex : 'sample'
	}, {
		header : '实验时长',
		width : 60,
		sortable : true,
		dataIndex : 'exp_time'
	}, {
		header : '实验/应收费用',
		width : 100,
		sortable : true,
		dataIndex : 'fee'
	}, {
		header : '审批情况',
		width : 80,
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
		header : '是否过期',
		width : 70,
		sortable : true,
		dataIndex : 'expired',
		renderer : function(value) {
			if (value)
				return "<font color='red'>是</font>";
			else
				return "<font color='green'>否</font>";
		}
	}, {
		header : '预约内容',
		width : 150,
		sortable : true,
		dataIndex : 'content'
	}, {
		header : '收费备注',
		width : 150,
		sortable : true,
		dataIndex : 'remark'
	}]);

	grid = new Ext.grid.GridPanel({
		id : "my-book",
		title : "我的预约记录",
		border : false,
		store : store,
		renderTo : 'my-book-content',
		header : false,
		cm : cm,
		loadMask : true,
		iconCls : 'yuyue',
		closable : true,
		listeners : {
			'rowcontextmenu' : {
				fn : function(grid, rowindex, e) {
					e.preventDefault();
					grid.getSelectionModel().selectRow(rowindex);
					rightClick.showAt(e.getXY());
				},
				scope : this
			},
			'rowdblclick' : {
				fn : function(grid, rowindex, e) {
					editBook();
				},
				scope : this
			}
		},
		tbar : [{
			text : '刷新',
			tooltip : '刷新',
			iconCls : 'table_refresh',
			onClick : function() {
				store.reload();
			}
		}, '-', {
			text : "以下显示我的预约情况(Tip:请双击选择编辑预约信息或按鼠标右键菜单操作)",
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
		id : 'mybookequiprightClickCont',
		items : [{
			id : 'mybookequipEditMenu',
			handler : editBook,
			iconCls : 'edit',
			text : '编辑预约信息'
		}, {
			id : 'mybookequipBookMenu',
			handler : book,
			iconCls : 'yuyue',
			text : '重新预约该设备'
		}, {
			id : 'mybookequipDeleteMenu',
			handler : deleteBook,
			iconCls : 'undo',
			text : '撤销预约'
		}, {
			id : 'mybookequipMessageMenu',
			handler : messageBook,
			iconCls : 'view',
			text : '查看预约内容'
		}, {
			id : 'mybookequipQueueMenu',
			handler : queueEquip,
			iconCls : 'tabs',
			text : '设备预约列表'
		}, {
			id : 'mybookequiplistDetailMenu',
			handler : detailEquip,
			iconCls : 'detail',
			text : '查看设备详情'
		}]
	});

	tabPanel.add(grid).show();

	function detailEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		showDetailWin(record.data["equip_id"]);
	}

	function book() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		showBookWin(record.data["equip_id"], record.data["equip_name"],record.data["appd"], record.data["appt1"], record.data["appt2"]);
	}

	function queueEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;

		var tab = tabPanel.getItem("queue-eq-content");
		if (tab) {
			tabPanel.remove(tab);
		}
		tab = addQueueTab(record.data["equip_id"], record.data["equip_name"]);
		tabPanel.setActiveTab(tab);
	}

	var myBookWin;

	function deleteBook() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		if (record.data["action"] == 1) {
			showMsg("已批准的预约记录不能撤销！");
			return;
		}
		Ext.MessageBox.confirm('确认删除预约申请记录', '是否确认删除预约申请记录？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Book.deleteBook(record.data["id"], {
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

	function messageBook() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
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

	function editBook() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		if (record.data["action"] != 0) {
			showMsg("只有待批准的预约记录才能编辑！");
			return;
		}
		showEditBookWin(record.data["id"], record.data["equip_name"],
				record.data["appd"], record.data["appt1"], record.data["appt2"]);
	}

	function showEditBookWin(book_id, equip_name, appd, appt1, appt2) {
		
		Ext.getDom("my-book-win").innerHTML = "";
		bookWin=null;
			
		var form = new Ext.FormPanel({
					labelAlign : 'right',
					buttonAlign : 'left',
					frame : true,
					labelWidth : 83,
					waitMsgTarget : true,
					items : [new Ext.form.Hidden({
										name : 'id'
									}), new Ext.form.Hidden({
										name : 'bookid'
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
													form.getForm()
															.findField("end")
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
											form.getForm()
													.findField("compute_fee")
													.setValue("");
											form.getForm()
													.findField("exp_time")
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
		myBookWin = new Ext.Window({
			el : 'my-book-win',
			modal : true,
			layout : 'fit',
			closeAction : 'hide',
			width : 400,
			height : 330,
			items : [form],
			buttons : [{
				text : '计算',
				tooltip : '根据样品数计算预约结束日期和预期费用',
				handler : function() {
					form.getForm().findField("start").updateValue();
					if (form.form.isValid()) {
						var startString = form.getForm().findField("start")
								.getValue();
						var sample_mount = form.getForm()
								.findField("sample_mount").getValue();
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
											form
													.getForm()
													.findField("compute_fee")
													.setValue(result.compute_fee);
											form
													.getForm()
													.findField("exp_time")
													.setValue(result.exp_time);
											if (result.invalid == true) {
											form
													.getForm()
													.findField("end")
													.markInvalid(result.invalidmsg);
										}
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
			}, {
				text : '更新预约',
				handler : function() {
					form.getForm().findField("start").updateValue();
					if (form.form.isValid()) {
						var startString = form.getForm().findField("start")
								.getValue();
						var endString = form.getForm().findField("end")
								.getValue();
						var sample_mount = form.getForm()
								.findField("sample_mount").getValue();
						var sample_name = form.getForm()
								.findField("sample_name").getValue();
						var compute_fee = form.getForm()
								.findField("compute_fee").getValue();
						var content = form.getForm().findField("content")
								.getValue();
						var bookid = form.getForm().findField("bookid").getValue();
						if (endString == '' || compute_fee == '') {
							Ext.Msg.alert("预约报告", "请先按“计算”确定预约结束日期和费用!");
						} else {
							myBookWin.hide();
							DWRUtil.useLoadingMessage("处理中...");
							Book.updateBook(bookid, startString.format("Y-m-d H:i"),
									endString,sample_name,
									sample_mount, compute_fee, content, {
										callback : function(value) {
											cancelLoadingMessage();
											var result = Ext.decode(value);
											if (result.result) {
												store.reload();
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
					myBookWin.hide();
				}
			}]
		});

		DWRUtil.useLoadingMessage("处理中...");
		Book.getBookDetailById(book_id, {
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
				myBookWin.show();
				myBookWin.setTitle("设备预约: " + equip_name);
				myBookWin.items.last().getForm().loadRecord(formRecord);
			},
			errorHandler : function(message) {
				Ext.Msg.alert("错误", "对不起，程序出现错误!");
				cancelLoadingMessage();
			}
		});

	}

}
