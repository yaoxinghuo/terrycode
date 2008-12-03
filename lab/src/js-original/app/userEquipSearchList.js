function initUserEquipSearchList() {
	var grid = null;
	var store = null;
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
		name : 'model',
		mapping : 'model'
	}, {
		name : 'admin',
		mapping : 'admin'
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
	store = new Ext.data.GroupingStore({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Equip.getEquipsInfoBySearch,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, keyword, column, true]
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
		width : 130,
		sortable : true,
		dataIndex : 'no'
	}, {
		header : '设备名称',
		width : 150,
		sortable : true,
		dataIndex : 'name',
		renderer : function(value, cellmeta, record) {
			return "<span class='nounderline'><a href=# onclick=\"showDetailWin('"+record.data["id"]+"','"+record.data["name"]+"','"+record.data["appd"]+"','"+record.data["appt1"]+"','"+record.data["appt2"]+"',"+record.data["status"]+");return false;\">"+value+"</a></span>";
		}
	}, {
		header : '型号',
		width : 120,
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
		header : '预约次数',
		width : 60,
		sortable : true,
		dataIndex : 'counter'
	}, {
		header : '分类',
		width : 100,
		sortable : true,
		dataIndex : 'type',
		renderer : function(value) {
			return equipArray[value];
		}
	}, {
		header : '是否收费',
		hidden : true,
		width : 60,
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
		renderer : function(value, cellmeta, record) {
			if (value)
				return "<span class='nounderline'><a href='javascript:;' onclick=\"showBookWin('"
						+ record.data["id"]
						+ "','"
						+ record.data["name"]
						+ "','"
						+ record.data["appd"]
						+ "','"
						+ record.data["appt1"]
						+ "','"
						+ record.data["appt2"]
						+ "',"
						+ record.data["status"]
						//+ "');\"><img style='vertical-align:middle;width:11px;height:11px;' src='resources/icons/app.png'/>接受预约</a></span>";
						+ ");\">接受预约</a></span>";
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
		editable : false,
		mode : 'local',
		triggerAction : 'all',
		value : 'name',
		width : 100,
		editable : false,
		selectOnFocus : true
	});

	grid = new Ext.grid.GridPanel({
		id : 'tab_grid_search',
		border : false,
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
		title : '搜索设备',
		iconCls : 'search',
		loadMask : true,
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
					book();
				},
				scope : this
			}
		},
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
			text : 'Tip:请选择一条记录双击预约或按鼠标右键菜单操作',
			xtype : 'tbtext'
		}],
		bbar : new Ext.PagingToolbar({
			pageSize : pageSize,
			store : store,
			displayInfo : true
		})
	});

	var rightClick = new Ext.menu.Menu({
		id : 'userequipsearchlistrightClickCont',
		items : [{
			id : 'userequipsearchlistBookMenu',
			handler : book,
			iconCls : 'yuyue',
			text : '预约该设备'
		}, {
			id : 'userequipsearchlistQueueMenu',
			handler : queueEquip,
			iconCls : 'tabs',
			text : '设备预约列表'
		}, {
			id : 'userequipsearchlistDetailMenu',
			handler : detailEquip,
			iconCls : 'detail',
			text : '查看设备详情'
		}]
	});

	tabPanel.add(grid);

	function book() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		if (!record.data["status"])
			showMsg("该设备暂时不接受预约！");
		else
			showBookWin(record.data["id"], record.data["name"], record.data["appd"], record.data["appt1"], record.data["appt2"], record.data["status"]);
	}

	function queueEquip() {
		if (!islogin) {
			showMsg("该操作需要<a href='#' onclick='showLoginWin();return false;'>登录</a>后才能继续！");
			return;
		}
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;

		var tab = tabPanel.getItem("queue-eq-content");
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
		showDetailWin(record.data["id"]);
	}

}
