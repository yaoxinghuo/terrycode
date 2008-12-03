﻿function initAdminLogManager(id, tabTitle) {
	Ext.getDom("admin-log-content").innerTHML = "";
	var grid = null;
	var store = null;
	var action = -1;
	var nowDate = new Date();
	var endDate = nowDate.format('Y-m-d');
	nowDate.setMonth(new Date().getMonth() - 1);
	var startDate = nowDate.format('Y-m-d');
	var keyword = "";
	var column = "no";

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
		name : 'admin_id',
		mapping : 'admin_id'
	}, {
		name : 'action',
		mapping : 'action'
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
		name : 'teacher',
		mapping : 'teacher'
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
		name : 'content',
		mapping : 'content'
	}, {
		name : 'remark',
		mapping : 'remark'
	}, {
		name : 'charge',
		mapping : 'charge'
	}, {
		name : 'admin_remark',
		mapping : 'admin_remark'
	}]));

	store = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy({
			dwrFunction : Book.getLogsInfo,
			listeners : {
				'beforeload' : function(dataProxy, params) {
					params[dataProxy.loadArgsKey] = [params.start,
							params.limit, action, startDate, endDate, keyword, column]
				}
			}
		}),
		reader : reader
	});

	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), sm, {
		header : '审批日期',
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
		header : '审批人',
		width : 80,
		sortable : true,
		dataIndex : 'admin_name'
	}, {
		header : '审批情况',
		width : 80,
		sortable : true,
		dataIndex : 'action',
		renderer : function(value) {
			if (value == 0)
				return "<font color=blue>未批准</font>";
			else if (value == 2)
				return "<font color=red>已删除</font>";
			else if (value == 3)
				return "改费用";
			else
				return "<font color=green>已批准</font>";
		}
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
		header : '预约内容',
		width : 150,
		sortable : true,
		dataIndex : 'content'
	}, {
		header : '费用备注',
		width : 150,
		sortable : true,
		dataIndex : 'remark'
	}, {
		header : '负责人附言',
		width : 150,
		sortable : true,
		dataIndex : 'admin_remark'
	}]);

	var actionColumn = new Ext.form.ComboBox({
		store : new Ext.data.SimpleStore({
			fields : ['returnValue', 'displayValue'],
			data : [[-1, "所有类型"], [0, '未批准'], [1, '已批准'], [3, '改费用'], [2, '已删除']]
		}),
		valueField : 'returnValue',
		displayField : 'displayValue',
		allowBlank : false,
		typeAhead : true,
		mode : 'local',
		triggerAction : 'all',
		value : -1,
		width : 78,
		editable : false,
		selectOnFocus : true
	});
	var searchColumn = new Ext.form.ComboBox({
		store : new Ext.data.SimpleStore({
			fields : ['returnValue', 'displayValue'],
			data : [['equip_name','设备名称'],['equip_no','设备编号'],['user_name','申请人'],['admin_name','负责人'],['teacher','导师姓名'],['sample_name','样品名称'],['content','预约内容'],['remark','费用备注'],['admin_remark','负责人附言']]
		}),
		valueField : 'returnValue',
		displayField : 'displayValue',
		allowBlank : false,
		typeAhead : true,
		editable : false,
		mode : 'local',
		triggerAction : 'all',
		value : 'equip_name',
		width : 85,
		editable : false,
		selectOnFocus : true
	});

	grid = new Ext.grid.GridPanel({
		id : id,
		title : tabTitle,
		border : false,
		ds : store,
		renderTo : 'admin-log-content',
		header : false,
		cm : cm,
		sm : sm,
		loadMask : true,
		iconCls : 'unit',
		closable : true,
		bbar : new Ext.PagingToolbar({
			pageSize : pageSize,
			store : store,
			displayInfo : true
		}),
		tbar : [{
			text : '批量删除',
			tooltip : '批量删除',
			iconCls : 'remove',
			onClick : function() {
				if (sm.hasSelection()) {
					var list = sm.getSelections();
					if (list.length == 1) {
						deleteLog();
						return;
					}
					var rList = [];
					for (var i = 0; i < list.length; i++) {
						rList[i] = list[i].data["id"];
					}
					batchDeleteLogs(rList);
				} else {
					showMsg("请至少选择一条记录!");
				}
			}
		}, '-', {
			text : '审批类型:',
			xtype : 'tbtext'
		}, actionColumn, {
			text : '日期从',
			xtype : 'tbtext'
		}, {
			xtype : 'datefield',
			format : 'Y-m-d',
			id : 'a_startDate',
			width : 90,
			value : startDate
		}, {
			text : '到',
			xtype : 'tbtext'
		}, {
			xtype : 'datefield',
			format : 'Y-m-d',
			id : 'a_endDate',
			width : 90,
			value : endDate
		}, {
			id : 'keyword',
			xtype : 'textfield',
			emptyText : emptySearchText,
			width : 80,
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						if (Ext.getCmp("a_startDate").isValid()
								&& Ext.getCmp("a_endDate").isValid()) {
							action = actionColumn.getValue();
							startDate = Ext.getCmp("a_startDate").getEl().getValue();
							endDate = Ext.getCmp("a_endDate").getEl().getValue();
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
			}
		}, {
			text : '搜索字段:',
			xtype : 'tbtext'
		}, searchColumn, {
			text : '查询',
			tooltip : '请输入前面的条件后查询',
			iconCls : 'search',
			onClick : function() {
				if (Ext.getCmp("a_startDate").isValid()
						&& Ext.getCmp("a_endDate").isValid()) {
					action = actionColumn.getValue();
					startDate = Ext.getCmp("a_startDate").getEl().getValue();
					endDate = Ext.getCmp("a_endDate").getEl().getValue();
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
		}, {
			text : '导出为Excel',
			tooltip : '导出为成Excel并下载到本地',
			iconCls : 'excel',
			onClick : function() {
				window.location.href = "report?type=2&action=" + action
						+ "&startDate=" + startDate + "&endDate=" + endDate
						+ "&keyword=" + keyword + "&column=" + column;
			}
		}]

	});

	store.load({
		params : {
			start : 0,
			limit : pageSize
		}
	});

	var rightClick = new Ext.menu.Menu({
		id : 'adminlogrightClickCont',
		items : [{
			id : 'adminlogDetailMenu',
			handler : detailEquip,
			iconCls : 'detail',
			text : '查看/修改设备详情'
		}, {
			id : 'adminlogMessageMenu',
			handler : messageBook,
			iconCls : 'view',
			text : '查看预约内容'
		}, {
			id : 'adminlogDeleteMenu',
			handler : deleteLog,
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

	function deleteLog() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		Ext.MessageBox.confirm('确认删除审批日志', '是否确认删除审批日志？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Book.deleteLog(record.data["id"], {
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

	function batchDeleteLogs(list) {
		Ext.MessageBox.confirm('确认批量删除审批日志', '是否确认批量删除审批日志？', function(btn) {
			if ("yes" != btn)
				return;
			DWRUtil.useLoadingMessage("处理中...");
			Book.batchDeleteLogs(list, {
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

	function messageBook() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		DWRUtil.useLoadingMessage("处理中...");
		Book.adminMessageBook(record.data["id"], {
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

}
