function initPrEquipManager(id, tabTitle) {
	Ext.getDom("pr-eq-content").innerTHML = "";
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
		name : 'admin_name',
		mapping : 'admin_name'
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
		name : 'charge',
		mapping : 'charge'
	}, {
		name : 'remark',
		mapping : 'remark'
	}]));

	var store = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Book.getPrBooksInfo,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start, params.limit]
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
		header : '负责人',
		width : 80,
		sortable : true,
		dataIndex : 'admin_name'
	}, {
		header : '样品/数量',
		width : 120,
		sortable : true,
		dataIndex : 'sample'
	}, {
		header : '实验费用',
		width : 60,
		sortable : true,
		dataIndex : 'fee'
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
		header : '费用备注',
		width : 150,
		sortable : true,
		dataIndex : 'remark',
		hidden : true
	}]);

	var grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		store : store,
		renderTo : 'pr-eq-content',
		header : false,
		cm : cm,
		sm : sm,
		loadMask : true,
		iconCls : 'equip',
		closable : true,
		tbar : [{
			text : '批量批准',
			tooltip : '批量批准',
			iconCls : 'pass',
			onClick : function() {
				if (sm.hasSelection()) {
					var list = sm.getSelections();
					if (list.length == 1) {
						confirmBook();
						return;
					}
					var rList = [];
					for (var i = 0; i < list.length; i++) {
						rList[i] = list[i].data["id"];
					}
					batchConfirmBook(rList);
				} else {
					showMsg("请至少选择一条记录!");
				}
			}
		}, '-', {
			text : '批量删除',
			tooltip : '批量删除',
			iconCls : 'remove',
			onClick : function() {
				if (sm.hasSelection()) {
					var list = sm.getSelections();
					if (list.length == 1) {
						removeBook();
						return;
					}
					var rList = [];
					for (var i = 0; i < list.length; i++) {
						rList[i] = list[i].data["id"];
					}
					batchRemoveBook(rList);
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
			text : 'Tip:请选择一条或多条设备记录操作或单击记录的右键菜单',
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
	if (pr_refresh == null)
		pr_refresh = setInterval(function() {
			store.reload();
		}, 600000);// 自动每10min刷新

	var rightClick = new Ext.menu.Menu({
		id : 'prequiprightClickCont',
		items : [{
			id : 'prequipConfirmMenu',
			handler : confirmBook,
			iconCls : 'pass',
			text : '批准'
		}, {
			id : 'preequipDetailMenu',
			handler : detailEquip,
			iconCls : 'detail',
			text : '查看/修改设备详情'
		}, {
			id : 'prequipQueueMenu',
			handler : queueEquip,
			iconCls : 'tabs',
			text : '设备预约信息列表'
		}, {
			id : 'prequipMessageMenu',
			handler : messageBook,
			iconCls : 'view',
			text : '查看预约内容'
		}, {
			id : 'prequipRemarkMenu',
			handler : remarkBook,
			iconCls : 'liuyan',
			text : '给学生留言'
		}, {
			id : 'prequipRemoveMenu',
			handler : removeBook,
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

	function detailEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		currentStore = null;
		showEquipEditWin(record.data["equip_id"]);
	}

	function queueEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;

		var tab = tabPanel.getItem("queue-equip");
		if (tab) {
			tabPanel.remove(tab);
		}
		tab = addQueueTab(record.data["equip_id"], record.data["equip_name"]);
		tabPanel.setActiveTab(tab);
	}

	function batchConfirmBook(list) {
		DWRUtil.useLoadingMessage("处理中...");
		Book.batchConfirmBook(list, {
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
	}

	function confirmBook() {
		var record = grid.getSelectionModel().getSelected();
		if (record.data["expired"]) {
			showMsg("该预约的结束日期已经过期！");
			return;
		}
		Ext.MessageBox.show({
			title : '确认批准预约',
			msg : '确认批准预约？您也可为预约人添加留言:<br/><i>若要更改实验应收费用，请到“已批准预约”</i>',
			width : 300,
			buttons : Ext.MessageBox.OKCANCEL,
			multiline : true,
			fn : function(btn, text) {
				if ("ok" != btn)
					return;
				DWRUtil.useLoadingMessage("处理中...");
				Book.confirmBook(record.data["id"], text, {
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
			}
		});
	}

	function remarkBook() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		Ext.MessageBox.show({
			title : '给学生留言',
			msg : '未批准该预约，给学生留言:',
			width : 300,
			buttons : Ext.MessageBox.OKCANCEL,
			multiline : true,
			fn : function(btn, text) {
				if ("ok" != btn || text == null || text == "")
					return;
				DWRUtil.useLoadingMessage("处理中...");
				Book.remarkBook(record.data["id"], text, {
					callback : function(value) {
						cancelLoadingMessage();
						var result = Ext.decode(value);
						if (result.result) {
							showMsg(result.message);
						} else
							Ext.Msg.alert("报告", result.message);
					},
					errorHandler : function(message) {
						Ext.Msg.alert("错误", "对不起，程序出现错误!");
						cancelLoadingMessage();
					}
				});
			}
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

	function batchRemoveBook(list) {
		Ext.MessageBox.confirm('确认批量删除预约信息', '是否确认批量删除预约信息？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Book.batchRemoveBook(list, {
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

	function removeBook() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		Ext.MessageBox.show({
			title : '确认删除预约',
			msg : '是否确认删除预约？您也可为预约人添加留言:',
			width : 300,
			buttons : Ext.MessageBox.OKCANCEL,
			multiline : true,
			fn : function(btn, text) {
				if ("ok" != btn)
					return;
				DWRUtil.useLoadingMessage("处理中...");
				Book.removeBook(record.data["id"], text, {
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
			}
		});
	}

}
