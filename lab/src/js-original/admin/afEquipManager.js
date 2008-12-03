﻿function initAfEquipManager(id, tabTitle) {
	Ext.getDom("af-eq-content").innerTHML = "";
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
		name : 'compute_fee',
		mapping : 'compute_fee'
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

	store = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Book.getAfBooksInfo,
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
		header : '实验/应收费用',
		width : 100,
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
		dataIndex : 'remark'
	}]);

	grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		store : store,
		renderTo : 'af-eq-content',
		header : false,
		cm : cm,
		sm : sm,
		loadMask : true,
		iconCls : 'equip',
		closable : true,
		tbar : [{
			text : '批量撤销',
			tooltip : '批量撤销',
			iconCls : 'undo',
			onClick : function() {
				if (sm.hasSelection()) {
					var list = sm.getSelections();
					if (list.length == 1) {
						cancelBook();
						return;
					}
					var rList = [];
					for (var i = 0; i < list.length; i++) {
						rList[i] = list[i].data["id"];
					}
					batchCancelBook(rList);
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
	if (af_refresh == null)
		af_refresh = setInterval(function() {
			store.reload();
		}, 900000);// 自动每15min刷新

	var rightClick = new Ext.menu.Menu({
		id : 'afequiprightClickCont',
		items : [{
			id : 'afequipFeeMenu',
			handler : feeEquip,
			iconCls : 'liuyan',
			text : '修改实验费用'
		}, {
			id : 'afequipDetailMenu',
			handler : detailEquip,
			iconCls : 'detail',
			text : '查看/修改设备详情'
		}, {
			id : 'afequipQueueMenu',
			handler : queueEquip,
			iconCls : 'tabs',
			text : '设备预约信息列表'
		}, {
			id : 'afequipMessageMenu',
			handler : messageBook,
			iconCls : 'view',
			text : '查看预约内容'
		}, {
			id : 'afequipCancelMenu',
			handler : cancelBook,
			iconCls : 'undo',
			text : '撤销批准'
		}]
	});

	grid.addListener('rowcontextmenu', function(grid, rowindex, e) {
		e.preventDefault();
		grid.getSelectionModel().selectRow(rowindex);
		rightClick.showAt(e.getXY());
	});

	tabPanel.add(grid).show();
	
	function feeEquip(){
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		currentStore = store;
		showFeeWin(record.data["id"],record.data["compute_fee"]);
	}

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

	function batchCancelBook(list) {
		DWRUtil.useLoadingMessage("处理中...");
		Book.batchCancelBook(list, {
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

	function cancelBook() {
		var record = grid.getSelectionModel().getSelected();
		if (record.data["expired"]) {
			showMsg("该预约的结束日期已经过期！");
			return;
		}
		Ext.MessageBox.show({
			title : '确认取消预约',
			msg : '是否确认取消预约？您也可为预约人添加留言:',
			width : 300,
			buttons : Ext.MessageBox.OKCANCEL,
			multiline : true,
			fn : function(btn, text) {
				if ("ok" != btn)
					return;
				DWRUtil.useLoadingMessage("处理中...");
				Book.cancelBook(record.data["id"], text, {
					callback : function(value) {
						cancelLoadingMessage();
						var result = Ext.decode(value);
						if (result.result) {
							store
									.remove(grid.getSelectionModel()
											.getSelected());
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
