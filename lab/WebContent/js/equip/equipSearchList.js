function initEquipSearchList(B,F){Ext.getDom("eq-search-content").innerTHML="";var J="";var D="name";var H=new Ext.data.DWRJsonReader({totalProperty:"results",root:"rows"},new Ext.data.Record.create([{name:"id",mapping:"id"},{name:"no",mapping:"no"},{name:"name",mapping:"name"},{name:"admin",mapping:"admin"},{name:"model",mapping:"model"},{name:"company",mapping:"company"},{name:"type",mapping:"type"},{name:"status",mapping:"status"},{name:"charge",mapping:"charge"},{name:"pub",mapping:"pub"}]));var L=new Ext.data.GroupingStore({proxy:new Ext.data.DWRProxy({dwrFunction:Equip.getEquipsInfoBySearch,listeners:{beforeload:function(O,P){P[O.loadArgsKey]=[P.start,P.limit,J,D,false]}}}),groupField:"name",groupOnSort:true,sortInfo:{field:"type",direction:"ASC"},reader:H});var C=new Ext.grid.CheckboxSelectionModel();var M=new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),C,{header:"设备编号",width:100,sortable:true,dataIndex:"no"},{header:"设备名称",width:150,sortable:true,dataIndex:"name"},{header:"型号",width:150,sortable:true,dataIndex:"model"},{header:"负责人",width:100,sortable:true,dataIndex:"admin"},{header:"生产厂商",width:150,sortable:true,dataIndex:"company"},{header:"分类",width:100,sortable:true,dataIndex:"type",renderer:function(O){return equipArray[O]}},{header:"是否公用",width:80,sortable:true,dataIndex:"pub",renderer:function(O){if(O){return"<span style='color:green;'>是</span>"}else{return"<span style='color:red;'>否</span>"}}},{header:"是否收费",width:80,sortable:true,dataIndex:"charge",renderer:function(O){if(O){return"<span style='color:red;'>是</span>"}else{return"<span style='color:green;'>否</span>"}}},{header:"状态",width:100,sortable:true,dataIndex:"status",renderer:function(O){if(O){return"<span style='color:green;'>接受预约</span>"}else{return"<span style='color:red;'>暂不可用</span>"}}}]);var G=new Ext.form.ComboBox({store:new Ext.data.SimpleStore({fields:["returnValue","displayValue"],data:equipColumns}),valueField:"returnValue",displayField:"displayValue",allowBlank:false,typeAhead:true,mode:"local",triggerAction:"all",value:"name",width:100,editable:false,selectOnFocus:true});var A=new Ext.grid.GridPanel({id:B,title:F,border:false,renderTo:"eq-search-content",header:false,store:L,cm:M,sm:C,view:new Ext.grid.GroupingView({forceFit:true,sortAscText:"正序",sortDescText:"倒序",columnsText:"列显示/隐藏",groupByText:"依本列分组",showGroupsText:"分组显示",groupTextTpl:"{text} ({[values.rs.length]} 条记录)"}),height:500,iconCls:"search",loadMask:true,closable:true,tbar:[{text:"新增设备",tooltip:"新增设备记录",iconCls:"add",onClick:function(){currentStore=L;showAddWin()}},"-",{text:"批量删除",tooltip:"批量删除",iconCls:"remove",onClick:function(){if(C.hasSelection()){var Q=C.getSelections();if(Q.length==1){E();return }var P=[];for(var O=0;O<Q.length;O++){P[O]=Q[O].data.id}K(P)}else{showMsg("请至少选择一条记录!")}}},"-",{id:"keyword",xtype:"textfield",emptyText:emptySearchText,width:100,listeners:{specialkey:function(P,O){if(O.getKey()==Ext.EventObject.ENTER){J=Ext.getCmp("keyword").getEl().getValue();if(J==emptySearchText){J=""}D=G.getValue();L.reload({params:{start:0,limit:pageSize}})}}}},{text:"搜索字段:",xtype:"tbtext"},G,{text:"搜索",tooltip:"搜索",iconCls:"search",onClick:function(){J=Ext.getCmp("keyword").getEl().getValue();if(J==emptySearchText){J=""}D=G.getValue();L.reload({params:{start:0,limit:pageSize}})}},"-",{text:"导出为Excel",tooltip:"导出为Excel并下载到本地",iconCls:"excel",onClick:function(){window.location.href="equipdownload?type=3&arg1="+encodeURI(J)+"&arg2="+D}},{text:"导入Excel",tooltip:"把本地Excel文件导入到设备库",iconCls:"excel",onClick:function(){currentStore=L;showUploadWin()}},"-",{text:"Tip:请选择设备记录操作或单击记录的右键菜单",xtype:"tbtext"}],bbar:new Ext.PagingToolbar({pageSize:pageSize,store:L,displayInfo:true})});var I=new Ext.menu.Menu({id:"equipsearchlistrightClickCont",items:[{id:"equipsearchlistDetailMenu",handler:N,iconCls:"detail",text:"查看/修改详情"},{id:"equipsearchlistRemoveMenu",handler:E,iconCls:"remove",text:"删除设备"}]});A.addListener("rowcontextmenu",function(P,O,Q){Q.preventDefault();P.getSelectionModel().selectRow(O);I.showAt(Q.getXY())});tabPanel.add(A).show();function K(O){Ext.MessageBox.confirm("确认批量删除设备记录","是否确认批量删除设备记录？",function(P){if("yes"!=P){return }DWRUtil.useLoadingMessage("处理中...");Equip.batchRemoveEquip(O,{callback:function(R){cancelLoadingMessage();var Q=Ext.decode(R);if(Q.result){L.reload();showMsg(Q.message)}else{Ext.Msg.alert("报告",Q.message)}},errorHandler:function(Q){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})})}function E(){var O=A.getSelectionModel().getSelected();if(!O){return }Ext.MessageBox.confirm("确认删除设备记录","是否确认删除设备记录？",function(P){if("yes"!=P){return }DWRUtil.useLoadingMessage("处理中...");Equip.removeEquip(O.data.id,{callback:function(R){cancelLoadingMessage();var Q=Ext.decode(R);if(Q.result){L.remove(O);showMsg(Q.message)}else{Ext.Msg.alert("报告",Q.message)}},errorHandler:function(Q){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})})}function N(){var O=A.getSelectionModel().getSelected();if(!O){return }currentStore=L;showEditWin(O.data.id)}};