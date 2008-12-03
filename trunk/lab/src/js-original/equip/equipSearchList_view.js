function initEquipSearchList(id, tabTitle) {
	Ext.getDom("eq-search-content").innerTHML = "";
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
			dwrFunction : Equip.getEquipsInfoBySearch,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, keyword, column, false]
				}
			}
		}),
		groupField : 'name',
		groupOnSort : true,
		sortInfo : {
			field : 'type',
			direction : "ASC"
		},
		reader : reader
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
		renderTo : 'eq-search-content',
		header : false,
		store : store,
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
		height : 500,
		iconCls : 'search',
		loadMask : true,
		closable : true,
		tbar : [{
			id : 'keyword',
			xtype : 'textfield',
			emptyText : emptySearchText,
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
			text : 'Tip:请选择一条记录的右键菜单操作',
			xtype : 'tbtext'
		}],
		bbar : new Ext.PagingToolbar({
			pageSize : pageSize,
			store : store,
			displayInfo : true
		})
	});

	var rightClick = new Ext.menu.Menu({
		id : 'equipsearchlistrightClickCont',
		items : [{
			id : 'equipDetailMenu',
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

	function detailEquip() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		showEditWin(record.data["id"]);
	}

}
