function initUserLogNotice() {
	Ext.getDom("messageDiv0").innerTHML = "";
	var grid = null;
	var store = null;
	var nowDate = new Date();
	var endDate = nowDate.format('Y-m-d');
	nowDate.setMonth(new Date().getMonth() - 1);
	var startDate = nowDate.format('Y-m-d');

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
		name : 'start',
		mapping : 'start'
	}, {
		name : 'end',
		mapping : 'end'
	}, {
		name : 'action',
		mapping : 'action'
	}, {
		name : 'content',
		mapping : 'content'
	}]));

	store = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Book.getBooksLogInfo,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,//改成获取所有的日志
							params.limit, -1, null, endDate, "", "id"]
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
		width : 140,
		sortable : true,
		dataIndex : 'equip_name'
	}, {
		header : '设备编号',
		width : 100,
		sortable : true,
		dataIndex : 'equip_no',
		hidden : true
	}, {
		header : '类别',
		width : 100,
		sortable : true,
		dataIndex : 'type',
		hidden : true,
		renderer : function(value) {
			if (equipArray[value])
				return equipArray[value];
			else
				return "其他";
		}
	}, {
		header : '预约人',
		width : 60,
		sortable : true,
		dataIndex : 'user_name'
	}, {
		header : '预约内容',
		width : 150,
		sortable : true,
		dataIndex : 'content'
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
		header : '审批情况',
		width : 80,
		sortable : true,
		dataIndex : 'action',
		renderer : function(value) {
			if (value == 0)
				return "&nbsp;&nbsp;&nbsp;<font color=blue>待批准</font>";
			else if (value == 2)
				return "&nbsp;&nbsp;&nbsp;<font color=red>已删除</font>";
			else
				return "&nbsp;&nbsp;&nbsp;<font color=green>已批准</font>";
		}
	}]);

	grid = new Ext.grid.GridPanel({
		height : 315,
		store : store,
		renderTo : 'messageDiv0',
		header : false,
		cm : cm,
		loadMask : true,
		iconCls : 'unit',
		closable : true,
		bbar : new Ext.PagingToolbar({
			pageSize : 10,
			store : store,
			displayInfo : true
		})
	});
	store.load({
		params : {
			start : 0,
			limit : 10
		}
	});

}

function initAdminLogNotice() {
	Ext.getDom("messageDiv1").innerTHML = "";
	var grid = null;
	var store = null;
	var action = -1;
	var nowDate = new Date();
	var endDate = nowDate.format('Y-m-d');
	nowDate.setMonth(new Date().getMonth() - 1);
	var startDate = nowDate.format('Y-m-d');

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
		name : 'user_name',
		mapping : 'user_name'
	}, {
		name : 'user_id',
		mapping : 'user_id'
	}, {
		name : 'action',
		mapping : 'action'
	}, {
		name : 'admin_name',
		mapping : 'admin_name'
	}, {
		name : 'admin_remark',
		mapping : 'admin_remark'
	}, {
		name : 'start',
		mapping : 'start'
	}, {
		name : 'end',
		mapping : 'end'
	}]));

	store = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Book.getLogsInfo,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,//改成获取所有的日志
							params.limit, -1, null, endDate, '', 'id']
				}
			}
		}),
		reader : reader
	});

	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
		header : '审批日期',
		width : 100,
		sortable : true,
		dataIndex : 'input'
	}, {
		header : '设备名称',
		width : 140,
		sortable : true,
		dataIndex : 'equip_name'
	}, {
		header : '设备编号',
		width : 100,
		sortable : true,
		dataIndex : 'equip_no',
		hidden : true
	}, {
		header : '预约人',
		width : 60,
		sortable : true,
		dataIndex : 'user_name'
	},
			{
				header : '审批人/留言',
				width : 150,
				sortable : true,
				dataIndex : 'admin_remark',
				renderer : function(value, cellmeta, record) {
					if(value=="")
						return record.data["admin_name"];
					else
						return record.data["admin_name"] + "/" + value;
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
				header : '审批情况',
				width : 80,
				sortable : true,
				dataIndex : 'action',
				renderer : function(value) {
					if (value == 0)
						return "&nbsp;&nbsp;&nbsp;<font color=blue>未批准</font>";
					else if (value == 2)
						return "&nbsp;&nbsp;&nbsp;<font color=red>已删除</font>";
					else if (value == 3)
						return "&nbsp;&nbsp;&nbsp;改费用";
					else
						return "&nbsp;&nbsp;&nbsp;<font color=green>已批准</font>";
				}
			}]);

	grid = new Ext.grid.GridPanel({
		ds : store,
		renderTo : 'messageDiv1',
		header : false,
		height : 285,
		cm : cm,
		loadMask : true,
		iconCls : 'unit',
		closable : true,
		bbar : new Ext.PagingToolbar({
			pageSize : 10,
			store : store,
			displayInfo : true
		})

	});

	store.load({
		params : {
			start : 0,
			limit : 10
		}
	});
}