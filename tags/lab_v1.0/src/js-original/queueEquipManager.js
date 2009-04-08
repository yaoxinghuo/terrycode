function initQueueEquipManager(equip_id, equip_name) {
	Ext.getDom("queue-eq-content").innerTHML = "";
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
						name : 'user_name',
						mapping : 'user_name'
					}, {
						name : 'user_id',
						mapping : 'user_id'
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
						name : 'action',
						mapping : 'action'
					}, {
						name : 'content',
						mapping : 'content'
					}, {
						name : 'remark',
						mapping : 'remark'
					}, {
						name : 'sample',
						mapping : 'sample'
					}, {
						name : 'teacher',
						mapping : 'teacher'
					}, {
						name : 'exp_time',
						mapping : 'exp_time'
					}, {
						name : 'fee',
						mapping : 'fee'
					}]));

	store = new Ext.data.Store({
				proxy : new Ext.data.DWRProxy({
							dwrFunction : Book.getBooksInfoByEquipId,
							listeners : {
								'beforeload' : function(dataProxy, params) {
									params[dataProxy.loadArgsKey] = [
											params.start, params.limit,
											equip_id]
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
				width : 100,
				sortable : true,
				hidden : true,
				dataIndex : 'equip_name'
			}, {
				header : '设备编号',
				width : 100,
				sortable : true,
				hidden : true,
				dataIndex : 'equip_no'
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
				header : '审批情况',
				width : 80,
				sortable : true,
				dataIndex : 'action',
				renderer : function(value) {
					if (value == 0)
						return "<font color=blue>待批准</font>";
					else if (value == 2)
						return "<font color=red>已删除</font>";
					else
						return "<font color=green>已批准</font>";
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
				header : '样品/数量',
				width : 105,
				sortable : true,
				dataIndex : 'sample'
			}, {
				header : '预约内容',
				width : 150,
				sortable : true,
				dataIndex : 'content'
			}, {
				header : '实验/应收费用',
				width : 150,
				sortable : true,
				dataIndex : 'fee'
			}, {
				header : '费用备注',
				width : 150,
				sortable : true,
				dataIndex : 'remark'
			}]);

	grid = new Ext.grid.GridPanel({
				id : "queue-equip",
				title : "设备预约列表",
				border : false,
				store : store,
				renderTo : 'queue-eq-content',
				header : false,
				cm : cm,
				loadMask : true,
				iconCls : 'tabs',
				closable : true,
				tbar : [{
							text : '刷新',
							tooltip : '刷新',
							iconCls : 'refresh',
							onClick : function() {
								store.reload();
							}
						}, '-', {
							text : "显示设备'<font color=red>" + equip_name
									+ "</font>'的预约列表",
							xtype : 'tbtext'
						}],
				bbar : new Ext.PagingToolbar({
							pageSize : pageSize,
							store : store,
							displayInfo : true
						})
			});

	var rightClick = new Ext.menu.Menu({
				id : 'queueequiprightClickCont',
				items : [{
							id : 'queueequipMessageMenu',
							handler : messageBook,
							iconCls : 'view',
							text : '查看预约内容'
						}]
			});

	grid.addListener('rowcontextmenu', function(grid, rowindex, e) {
				e.preventDefault();
				grid.getSelectionModel().selectRow(rowindex);
				rightClick.showAt(e.getXY());
			});
	store.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});

	tabPanel.add(grid).show();

	function messageBook() {
		var record = grid.getSelectionModel().getSelected();
		if (!record)
			return;
		if (!islogin) {
			showMsg("该操作需要<a href='#' onclick='showLoginWin();return false;'>登录</a>后才能继续！");
			return;
		}
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

}

function showQueueEquip(id, name) {
	var tab = tabPanel.getItem("queue-eq-content");
	if (tab) {
		tabPanel.remove(tab);
	}
	tab = addQueueTab(id, name);
	tabPanel.setActiveTab(tab);
	if (currentMsgBox != null)
		currentMsgBox.hide();
}
