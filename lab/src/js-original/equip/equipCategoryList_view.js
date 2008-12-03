function initEquipCategoryList(id, tabTitle) {
	// http://www.cnblogs.com/tishifu/archive/2007/08/03/841429.html
	// substr和substring的区别
	var index = parseInt(id.substr(17));
	Ext.getDom("eq-category-content" + index).innerTHML = "";
	var keyword = "";
	var column = "name";

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
		name : 'status',
		mapping : 'status'
	}, {
		name : 'charge',
		mapping : 'charge'
	}, {
		name : 'pub',
		mapping : 'pub'
	}]));
	var store = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Equip.getEquipsInfoByType,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, index, false]
				}
			}
		}),
		reader : reader
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

	var searchColumn = new Ext.form.ComboBox({
		store : new Ext.data.SimpleStore({
			fields : ['returnValue', 'displayValue'],
			data : equipColumns
		}),
		valueField : 'returnValue',
		displayField : 'displayValue',
		allowBlank : false,
		typeAhead : true,
		mode : 'local',
		triggerAction : 'all',
		value : 'name',
		width : 100,
		editable : false,
		selectOnFocus : true
	});

	var grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		renderTo : 'eq-category-content' + index,
		header : false,
		store : store,
		cm : cm,
		sm : sm,
		height : 500,
		iconCls : 'equip',
		loadMask : true,
		closable : true,
		tbar : [{
			text : '刷新',
			tooltip : '刷新',
			iconCls : 'refresh',
			onClick : function() {
				store.reload();
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
		id : 'equipcategorylistrightClickCont',
		items : [{
			id : 'equipcategorylistDetailMenu',
			handler : detailEquip,
			iconCls : 'detail',
			text : '查看详情'
		}]
	});

	grid.addListener('rowcontextmenu', function(grid, rowindex, e) {
		e.preventDefault();
		grid.getSelectionModel().selectRow(rowindex);
		rightClick.showAt(e.getXY());
	});

	tabPanel.add(grid).show();

	function batchRemoveEquip(list) {
		Ext.MessageBox.confirm('确认批量删除设备记录', '是否确认批量删除设备记录？', function(btn) {
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
		Ext.MessageBox.confirm('确认删除设备记录', '是否确认删除设备记录？', function(btn) {
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
