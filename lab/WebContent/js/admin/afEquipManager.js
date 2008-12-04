function initAfEquipManager(C,F){Ext.getDom("af-eq-content").innerTHML="";var A=null;var K=null;var G=new Ext.data.DWRJsonReader({totalProperty:"results",root:"rows"},new Ext.data.Record.create([{name:"id",mapping:"id"},{name:"input",mapping:"input"},{name:"equip_id",mapping:"equip_id"},{name:"equip_name",mapping:"equip_name"},{name:"equip_no",mapping:"equip_no"},{name:"type",mapping:"type"},{name:"user_name",mapping:"user_name"},{name:"user_id",mapping:"user_id"},{name:"admin_name",mapping:"admin_name"},{name:"start",mapping:"start"},{name:"end",mapping:"end"},{name:"expired",mapping:"expired"},{name:"teacher",mapping:"teacher"},{name:"content",mapping:"content"},{name:"sample",mapping:"sample"},{name:"compute_fee",mapping:"compute_fee"},{name:"fee",mapping:"fee"},{name:"exp_time",mapping:"exp_time"},{name:"charge",mapping:"charge"},{name:"remark",mapping:"remark"}]));K=new Ext.data.Store({proxy:new Ext.data.DWRProxy({dwrFunction:Book.getAfBooksInfo,listeners:{beforeload:function(O,P){P[O.loadArgsKey]=[P.start,P.limit]}}}),reader:G});var D=new Ext.grid.CheckboxSelectionModel();var L=new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),D,{header:"填写日期",width:100,sortable:true,dataIndex:"input"},{header:"设备名称",width:100,sortable:true,dataIndex:"equip_name"},{header:"设备编号",width:100,sortable:true,dataIndex:"equip_no"},{header:"是否收费",width:70,sortable:true,hidden:true,dataIndex:"charge",renderer:function(O){if(O){return"<font color='red'>是</font>"}else{return"<font color='green'>否</font>"}}},{header:"类别",width:100,sortable:true,dataIndex:"type",renderer:function(O){if(equipArray[O]){return equipArray[O]}else{return"其他"}}},{header:"申请人",width:80,sortable:true,dataIndex:"user_name"},{header:"导师",width:80,sortable:true,dataIndex:"teacher"},{header:"负责人",width:80,sortable:true,dataIndex:"admin_name"},{header:"样品/数量",width:120,sortable:true,dataIndex:"sample"},{header:"实验/应收费用",width:100,sortable:true,dataIndex:"fee"},{header:"起始日期",width:100,sortable:true,dataIndex:"start"},{header:"中止日期",width:100,sortable:true,dataIndex:"end"},{header:"实验时长",width:60,sortable:true,dataIndex:"exp_time"},{header:"是否过期",width:70,sortable:true,dataIndex:"expired",renderer:function(O){if(O){return"<font color='red'>是</font>"}else{return"<font color='green'>否</font>"}}},{header:"预约内容",width:150,sortable:true,dataIndex:"content"},{header:"费用备注",width:150,sortable:true,dataIndex:"remark"}]);A=new Ext.grid.GridPanel({id:C,title:F,border:false,store:K,renderTo:"af-eq-content",header:false,cm:L,sm:D,loadMask:true,iconCls:"equip",closable:true,tbar:[{text:"批量撤销",tooltip:"批量撤销",iconCls:"undo",onClick:function(){if(D.hasSelection()){var Q=D.getSelections();if(Q.length==1){I();return }var P=[];for(var O=0;O<Q.length;O++){P[O]=Q[O].data.id}E(P)}else{showMsg("请至少选择一条记录!")}}},"-",{text:"刷新",tooltip:"刷新",iconCls:"refresh",onClick:function(){K.reload()}},"-",{text:"Tip:请选择一条或多条设备记录操作或单击记录的右键菜单",xtype:"tbtext"}],bbar:new Ext.PagingToolbar({pageSize:pageSize,store:K,displayInfo:true})});K.load({params:{start:0,limit:pageSize}});if(af_refresh==null){af_refresh=setInterval(function(){K.reload()},900000)}var H=new Ext.menu.Menu({id:"afequiprightClickCont",items:[{id:"afequipFeeMenu",handler:N,iconCls:"liuyan",text:"修改实验费用"},{id:"afequipDetailMenu",handler:M,iconCls:"detail",text:"查看/修改设备详情"},{id:"afequipQueueMenu",handler:J,iconCls:"tabs",text:"设备预约信息列表"},{id:"afequipMessageMenu",handler:B,iconCls:"view",text:"查看预约内容"},{id:"afequipCancelMenu",handler:I,iconCls:"undo",text:"撤销批准"}]});A.addListener("rowcontextmenu",function(P,O,Q){Q.preventDefault();P.getSelectionModel().selectRow(O);H.showAt(Q.getXY())});tabPanel.add(A).show();function N(){var O=A.getSelectionModel().getSelected();if(!O){return }currentStore=K;showFeeWin(O.data.id,O.data.compute_fee)}function M(){var O=A.getSelectionModel().getSelected();if(!O){return }currentStore=null;showEquipEditWin(O.data.equip_id)}function J(){var O=A.getSelectionModel().getSelected();if(!O){return }var P=tabPanel.getItem("queue-equip");if(P){tabPanel.remove(P)}P=addQueueTab(O.data.equip_id,O.data.equip_name);tabPanel.setActiveTab(P)}function B(){var O=A.getSelectionModel().getSelected();if(!O){return }DWRUtil.useLoadingMessage("处理中...");Book.messageBook(O.data.id,{callback:function(P){cancelLoadingMessage();Ext.MessageBox.show({title:"查看预约内容",msg:"以下显示预约内容",width:350,buttons:Ext.MessageBox.OK,multiline:true,value:P})},errorHandler:function(P){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})}function E(O){DWRUtil.useLoadingMessage("处理中...");Book.batchCancelBook(O,{callback:function(Q){cancelLoadingMessage();var P=Ext.decode(Q);if(P.result){K.reload();showMsg(P.message)}else{Ext.Msg.alert("报告",P.message)}},errorHandler:function(P){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})}function I(){var O=A.getSelectionModel().getSelected();if(O.data.expired){showMsg("该预约的结束日期已经过期！");return }Ext.MessageBox.show({title:"确认取消预约",msg:"是否确认取消预约？您也可为预约人添加留言:",width:300,buttons:Ext.MessageBox.OKCANCEL,multiline:true,fn:function(P,Q){if("ok"!=P){return }DWRUtil.useLoadingMessage("处理中...");Book.cancelBook(O.data.id,Q,{callback:function(S){cancelLoadingMessage();var R=Ext.decode(S);if(R.result){K.remove(A.getSelectionModel().getSelected());showMsg(R.message)}else{Ext.Msg.alert("报告",R.message)}},errorHandler:function(R){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})}})}};