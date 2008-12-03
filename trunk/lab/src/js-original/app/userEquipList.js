function initUserEquipList(index) {
	var grid = null;
	storeArray[index] = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Equip.getEquipsInfoByType,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, index, true]
				}
			}
		}),
		reader : reader
	});

	var sm = new Ext.grid.CheckboxSelectionModel();
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

	grid = new Ext.grid.GridPanel({
		id : '_tab_grid_' + index,
		store : storeArray[index],
		border : false,
		cm : cm,
		height : 500,
		title : (index == 0) ? '常用设备' : equipArray[index],
		iconCls : (index == 0) ? 'normal' : 'equip',
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
			text : '刷新',
			tooltip : '刷新',
			iconCls : 'refresh',
			onClick : function() {
				equipRefresh(index);
			}
		}, '-', {
			text : 'Tip:请选择一条记录双击编辑预约或按鼠标右键菜单操作',
			xtype : 'tbtext'
		}],
		bbar : new Ext.PagingToolbar({
			pageSize : pageSize,
			store : storeArray[index],
			displayInfo : true
		})
	});

	var rightClick = new Ext.menu.Menu({
		id : 'userequiplistrightClickCont' + index,
		items : [{
			id : 'userequiplistBookMenu' + index,
			handler : book,
			iconCls : 'yuyue',
			text : '预约该设备'
		}, {
			id : 'userequiplistQueueMenu' + index,
			handler : queueEquip,
			iconCls : 'tabs',
			text : '设备预约列表'
		}, {
			id : 'userequiplistDetailMenu' + index,
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
		showDetailWin(record.data["id"], record.data["name"], record.data["appd"], record.data["appt1"], record.data["appt2"]);
	}

}
