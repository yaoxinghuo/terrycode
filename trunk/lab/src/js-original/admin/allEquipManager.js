function initAllEquipManager(id, tabTitle) {
	Ext.getDom("all-eq-content").innerTHML = "";

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
	}]));

	store = new Ext.data.GroupingStore({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Equip.getEquipsInfo,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, true]
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

	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
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
		header : '生产厂商',
		width : 150,
		sortable : true,
		dataIndex : 'company'
	}, {
		header : '使用次数',
		width : 80,
		sortable : true,
		dataIndex : 'counter'
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
				return "<span style='color:green;'>可用</span>";
			else
				return "<span style='color:red;'>暂不可用</span>";
		}
	}]);

	var grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		store : store,
		renderTo : 'all-eq-content',
		header : false,
		cm : cm,
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
			text : '刷新',
			tooltip : '刷新',
			iconCls : 'refresh',
			onClick : function() {
				store.reload();
			}
		}, '-', {
			text : 'Tip:请选择一条设备记录鼠标右键菜单操作',
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
		id : 'allequiprightClickCont',
		items : [{
			id : 'allequipDetailMenu',
			handler : detailEquip,
			iconCls : 'detail',
			text : '查看/修改详情'
		}, {
			id : 'allequipQueueMenu',
			handler : queueEquip,
			iconCls : 'tabs',
			text : '预约列表'
		}]
	});

	grid.addListener('rowcontextmenu', function(grid, rowindex, e) {
		e.preventDefault();
		grid.getSelectionModel().selectRow(rowindex);
		rightClick.showAt(e.getXY());
	});

	tabPanel.add(grid).show();

	function queueEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;

		var tab = tabPanel.getItem("queue-equip");
		if (tab) {
			tabPanel.remove(tab);
		}
		tab = addQueueTab(record.data["id"], record.data["name"]);
		tabPanel.setActiveTab(tab);
	}

	function detailEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		currentStore = store;
		showEquipEditWin(record.data["id"]);
	}

}
