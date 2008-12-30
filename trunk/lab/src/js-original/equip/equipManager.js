﻿function initEquipManager(id, tabTitle) {
	Ext.getDom("eq-content").innerTHML = "";

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
		name : 'admin',
		mapping : 'admin'
	}, {
		name : 'model',
		mapping : 'model'
	}, {
		name : 'company',
		mapping : 'company'
	}, {
		name : 'type',
		mapping : 'type'
	}, {
		name : 'status',
		mapping : 'status'
	}, {
		name : 'charge',
		mapping : 'charge'
	}, {
		name : 'pub',
		mapping : 'pub'
	}]));

	var store = new Ext.data.GroupingStore({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Equip.getEquipsInfo,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, false]
				}
			}
		}),
		reader : reader,
		groupField : 'name',
		groupOnSort : true,
		sortInfo : {
			field : 'type',
			direction : "ASC"
		}
	});

	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), sm, {
		header : '设备编号',
		width : 100,
		sortable : true,
		dataIndex : 'no'
	}, {
		header : '设备名称',
		width : 150,
		sortable : true,
		dataIndex : 'name'
	}, {
		header : '型号',
		width : 150,
		sortable : true,
		dataIndex : 'model'
	}, {
		header : '负责人',
		width : 100,
		sortable : true,
		dataIndex : 'admin'
	}, {
		header : '生产厂商',
		width : 150,
		sortable : true,
		dataIndex : 'company'
	}, {
		header : '分类',
		width : 100,
		sortable : true,
		dataIndex : 'type',
		renderer : function(value) {
			return equipArray[value];
		}
	}, {
		header : '是否公用',
		width : 80,
		sortable : true,
		dataIndex : 'pub',
		renderer : function(value) {
			if (value)
				return "<span style='color:green;'>是</span>";
			else
				return "<span style='color:red;'>否</span>";
		}
	}, {
		header : '是否收费',
		width : 80,
		sortable : true,
		dataIndex : 'charge',
		renderer : function(value) {
			if (value)
				return "<span style='color:red;'>是</span>";
			else
				return "<span style='color:green;'>否</span>";
		}
	}, {
		header : '状态',
		width : 100,
		sortable : true,
		dataIndex : 'status',
		renderer : function(value) {
			if (value)
				return "<span style='color:green;'>接受预约</span>";
			else
				return "<span style='color:red;'>暂不可用</span>";
		}
	}]);

	var grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		store : store,
		renderTo : 'eq-content',
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
		iconCls : 'equip',
		closable : true,
		tbar : [{
			text : '新增设备',
			tooltip : '新增设备记录',
			iconCls : 'add',
			onClick : function() {
				currentStore = store;
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
						removeEquip();
						return;
					}
					var rList = [];
					for (var i = 0; i < list.length; i++) {
						rList[i] = list[i].data["id"];
					}
					batchRemoveEquip(rList);
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
				window.location.href = "equipdownload?type=1&arg1=null&arg2=null";
			}
		}, {
			text : '导入Excel',
			tooltip : '把本地Excel文件导入到设备库',
			iconCls : 'excel',
			onClick : function() {
				currentStore = store;
				showUploadWin();
			}
		}, '-', {
			text : 'Tip:请选择设备记录操作或单击记录的右键菜单',
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
		id : 'equiprightClickCont',
		items : [{
			id : 'equipDetailMenu',
			handler : detailEquip,
			iconCls : 'detail',
			text : '查看/修改详情'
		}, {
			id : 'equipRemoveMenu',
			handler : removeEquip,
			iconCls : 'remove',
			text : '删除设备'
		}]
	});

	grid.addListener('rowcontextmenu', function(grid, rowindex, e) {
		e.preventDefault();
		grid.getSelectionModel().selectRow(rowindex);
		rightClick.showAt(e.getXY());
	});

	tabPanel.add(grid).show();

	function batchRemoveEquip(list) {
		Ext.MessageBox.confirm('确认批量删除设备记录', '是否确认批量删除设备记录？<br><b>建议删除设备之前先导出该设备的预约记录！</b>', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Equip.batchRemoveEquip(list, {
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

	function removeEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		Ext.MessageBox.confirm('确认删除设备记录', '是否确认删除设备记录？<br><b>建议删除设备之前先导出该设备的预约记录！</b>', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Equip.removeEquip(record.data["id"], {
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

	function detailEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		currentStore = store;
		showEditWin(record.data["id"]);
	}

}