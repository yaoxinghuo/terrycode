function initEquipManager(H,E){Ext.getDom("eq-content").innerTHML="";var B=new Ext.data.DWRJsonReader({totalProperty:"results",root:"rows"},new Ext.data.Record.create([{name:"id",mapping:"id"},{name:"no",mapping:"no"},{name:"name",mapping:"name"},{name:"admin",mapping:"admin"},{name:"model",mapping:"model"},{name:"company",mapping:"company"},{name:"type",mapping:"type"},{name:"status",mapping:"status"},{name:"charge",mapping:"charge"},{name:"pub",mapping:"pub"}]));var C=new Ext.data.GroupingStore({proxy:new Ext.data.DWRProxy({dwrFunction:Equip.getEquipsInfo,listeners:{beforeload:function(I,J){J[I.loadArgsKey]=[J.start,J.limit,false]}}}),reader:B,groupField:"name",groupOnSort:true,sortInfo:{field:"type",direction:"ASC"}});var A=new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),{header:"设备编号",width:100,sortable:true,dataIndex:"no"},{header:"设备名称",width:150,sortable:true,dataIndex:"name",renderer:function(K,J,I){return"<span class='nounderline'><a href=# onclick=\"showEditWin('"+I.data.id+"');return false;\">"+K+"</a></span>"}},{header:"型号",width:150,sortable:true,dataIndex:"model"},{header:"负责人",width:100,sortable:true,dataIndex:"admin"},{header:"生产厂商",width:150,sortable:true,dataIndex:"company"},{header:"分类",width:100,sortable:true,dataIndex:"type",renderer:function(I){return equipArray[I]}},{header:"是否公用",width:80,sortable:true,dataIndex:"pub",renderer:function(I){if(I){return"<span style='color:green;'>是</span>"}else{return"<span style='color:red;'>否</span>"}}},{header:"是否收费",width:80,sortable:true,dataIndex:"charge",renderer:function(I){if(I){return"<span style='color:red;'>是</span>"}else{return"<span style='color:green;'>否</span>"}}},{header:"状态",width:100,sortable:true,dataIndex:"status",renderer:function(I){if(I){return"<span style='color:green;'>接受预约</span>"}else{return"<span style='color:red;'>暂不可用</span>"}}}]);var D=new Ext.grid.GridPanel({id:H,title:E,border:false,store:C,renderTo:"eq-content",header:false,cm:A,view:new Ext.grid.GroupingView({forceFit:true,sortAscText:"正序",sortDescText:"倒序",columnsText:"列显示/隐藏",groupByText:"依本列分组",showGroupsText:"分组显示",groupTextTpl:"{text} ({[values.rs.length]} 条记录)"}),loadMask:true,iconCls:"equip",closable:true,tbar:[{text:"刷新",tooltip:"刷新",iconCls:"refresh",onClick:function(){C.reload()}},"-",{text:"Tip:请选择设备记录右键菜单操作",xtype:"tbtext"}],bbar:new Ext.PagingToolbar({pageSize:pageSize,store:C,displayInfo:true})});C.load({params:{start:0,limit:pageSize}});var G=new Ext.menu.Menu({id:"equiprightClickCont",items:[{id:"equipDetailMenu",handler:F,iconCls:"detail",text:"查看详情"}]});D.addListener("rowcontextmenu",function(J,I,K){K.preventDefault();J.getSelectionModel().selectRow(I);G.showAt(K.getXY())});tabPanel.add(D).show();function F(){var I=D.getSelectionModel().getSelected();if(!I){return }showEditWin(I.data.id)}};