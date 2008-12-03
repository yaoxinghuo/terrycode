function initMyAdminBookManager(){Ext.getDom("my-admin-book-content").innerTHML="";var A=null;var G=null;var D=new Ext.data.DWRJsonReader({totalProperty:"results",root:"rows"},new Ext.data.Record.create([{name:"id",mapping:"id"},{name:"input",mapping:"input"},{name:"equip_id",mapping:"equip_id"},{name:"equip_name",mapping:"equip_name"},{name:"equip_no",mapping:"equip_no"},{name:"user_name",mapping:"user_name"},{name:"user_id",mapping:"user_id"},{name:"admin_id",mapping:"admin_id"},{name:"admin_name",mapping:"admin_name"},{name:"start",mapping:"start"},{name:"end",mapping:"end"},{name:"fee",mapping:"fee"},{name:"exp_time",mapping:"exp_time"},{name:"sample",mapping:"sample"},{name:"action",mapping:"action"},{name:"teacher",mapping:"teacher"},{name:"content",mapping:"content"},{name:"remark",mapping:"remark"},{name:"charge",mapping:"charge"},{name:"admin_remark",mapping:"admin_remark"}]));G=new Ext.data.Store({proxy:new Ext.data.DWRProxy({dwrFunction:Book.getLogsByUserId,listeners:{beforeload:function(J,K){K[J.loadArgsKey]=[K.start,K.limit]}}}),reader:D});var H=new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),{header:"审批日期",width:100,sortable:true,dataIndex:"input"},{header:"设备名称",width:100,sortable:true,dataIndex:"equip_name"},{header:"设备编号",width:100,sortable:true,dataIndex:"equip_no"},{header:"是否收费",width:70,sortable:true,hidden:true,dataIndex:"charge",renderer:function(J){if(J){return"<font color='red'>是</font>"}else{return"<font color='green'>否</font>"}}},{header:"申请人",width:80,sortable:true,hidden:true,dataIndex:"user_name"},{header:"审批人",width:80,sortable:true,dataIndex:"admin_name"},{header:"审批情况",width:80,sortable:true,dataIndex:"action",renderer:function(J){if(J==0){return"<font color=blue>未批准</font>"}else{if(J==2){return"<font color=red>已删除</font>"}else{if(J==3){return"改费用"}else{return"<font color=green>已批准</font>"}}}}},{header:"样品/数量",width:120,sortable:true,dataIndex:"sample"},{header:"实验时长",width:60,sortable:true,dataIndex:"exp_time"},{header:"实验/应收费用",width:100,sortable:true,dataIndex:"fee"},{header:"起始日期",width:100,sortable:true,dataIndex:"start"},{header:"中止日期",width:100,sortable:true,dataIndex:"end"},{header:"导师",width:80,sortable:true,dataIndex:"teacher"},{header:"预约内容",width:150,sortable:true,dataIndex:"content"},{header:"费用备注",width:150,sortable:true,dataIndex:"remark"},{header:"负责人附言",width:150,sortable:true,renderer:function(J){return"<b>"+J+"</b>"},dataIndex:"admin_remark"}]);A=new Ext.grid.GridPanel({id:"my-admin-book",title:"我的审批记录",border:false,store:G,renderTo:"my-admin-book-content",header:false,cm:H,loadMask:true,iconCls:"shengpi",closable:true,tbar:[{text:"刷新",tooltip:"刷新",iconCls:"table_refresh",onClick:function(){G.reload()}},"-",{text:"以下显示我的被审批情况(Tip:选中一条记录鼠标右键操作)",xtype:"tbtext"}],bbar:new Ext.PagingToolbar({pageSize:pageSize,store:G,displayInfo:true})});var E=new Ext.menu.Menu({id:"myadminbookequiprightClickCont",items:[{id:"myadminbookcancelequipEditMenu",handler:myBook,iconCls:"edit",text:"(到“预约记录”修改预约)"},{id:"myadminbookequipMessageMenu",handler:B,iconCls:"view",text:"查看预约/审批内容"},{id:"myadminbookequipBookMenu",handler:C,iconCls:"yuyue",text:"重新预约该设备"},{id:"myadminbookequipQueueMenu",handler:F,iconCls:"tabs",text:"设备预约列表"},{id:"myadminbookequiplistDetailMenu",handler:I,iconCls:"detail",text:"查看设备详情"}]});A.addListener("rowcontextmenu",function(K,J,L){L.preventDefault();K.getSelectionModel().selectRow(J);E.showAt(L.getXY())});G.load({params:{start:0,limit:pageSize}});tabPanel.add(A).show();function I(){var J=A.getSelectionModel().getSelected();if(!J){return }showDetailWin(J.data.equip_id)}function C(){var J=A.getSelectionModel().getSelected();if(!J){return }else{showBookWin(J.data.equip_id,J.data.equip_name)}}function F(){var J=A.getSelectionModel().getSelected();if(!J){return }var K=tabPanel.getItem("queue-eq-content");if(K){tabPanel.remove(K)}K=addQueueTab(J.data.equip_id,J.data.equip_name);tabPanel.setActiveTab(K)}function B(){var J=A.getSelectionModel().getSelected();if(!J){return }DWRUtil.useLoadingMessage("处理中...");Book.adminMessageBook(J.data.id,{callback:function(K){cancelLoadingMessage();Ext.MessageBox.show({title:"查看预约内容",msg:"以下显示预约内容",width:350,buttons:Ext.MessageBox.OK,multiline:true,value:K})},errorHandler:function(K){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})}};