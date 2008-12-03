function initFeedbackManager(B,D){Ext.getDom("feedback-content").innerTHML="";Ext.getDom("feedback-edit-win").innerHTML="";var A=null;var L=null;var N=false;var E=new Ext.data.DWRJsonReader({totalProperty:"results",root:"rows"},new Ext.data.Record.create([{name:"id",mapping:"id"},{name:"name",mapping:"name"},{name:"input",mapping:"input"},{name:"title",mapping:"title"},{name:"contact",mapping:"contact"},{name:"content",mapping:"content"},{name:"pub",mapping:"pub"},{name:"comment",mapping:"comment"}]));L=new Ext.data.Store({proxy:new Ext.data.DWRProxy({dwrFunction:Notice.getFeedbacks,listeners:{beforeload:function(O,P){P[O.loadArgsKey]=[P.start,P.limit,N]}}}),reader:E});var C=new Ext.grid.CheckboxSelectionModel();var M=new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),C,{header:"填写日期",width:100,sortable:true,dataIndex:"input"},{header:"反馈人",width:100,sortable:true,dataIndex:"name"},{header:"联系方式",width:180,sortable:true,dataIndex:"contact"},{header:"主题",width:180,sortable:true,dataIndex:"title"},{header:"内容",width:250,sortable:true,dataIndex:"content"},{header:"前台显示",width:70,sortable:true,dataIndex:"pub",renderer:function(O){if(O){return"<font color='red'>是</font>"}else{return"<font color='green'>否</font>"}}},{header:"管理员留言",width:235,sortable:true,dataIndex:"comment"}]);A=new Ext.grid.GridPanel({id:B,title:D,border:false,store:L,renderTo:"feedback-content",header:false,cm:M,sm:C,loadMask:true,iconCls:"notice",closable:true,tbar:[{text:"批量删除",tooltip:"批量删除",iconCls:"remove",onClick:function(){if(C.hasSelection()){var Q=C.getSelections();if(Q.length==1){F();return }var P=[];for(var O=0;O<Q.length;O++){P[O]=Q[O].data.id}J(P)}else{showMsg("请至少选择一条记录!")}}},"-",{text:"刷新",tooltip:"刷新",iconCls:"refresh",onClick:function(){L.reload()}},"-",{xtype:"checkbox",boxLabel:"<b>前台显示的记录</b>",id:"checkbox",checked:false,onClick:function(){if(N!=this.getValue()){N=this.getValue();L.reload({params:{start:0,limit:pageSize}})}}},"-",{text:"Tip:请选择一条或多条反馈记录操作或单击记录的右键菜单",xtype:"tbtext"}],bbar:new Ext.PagingToolbar({pageSize:pageSize,store:L,displayInfo:true})});L.load({params:{start:0,limit:pageSize}});var G=new Ext.menu.Menu({id:"noticerightClickCont",items:[{id:"feedbackEditMenu",handler:H,iconCls:"view",text:"查看/编辑反馈信息"},{id:"feedbackRemoveMenu",handler:F,iconCls:"remove",text:"删除"}]});A.addListener("rowcontextmenu",function(P,O,Q){Q.preventDefault();P.getSelectionModel().selectRow(O);G.showAt(Q.getXY())});tabPanel.add(A).show();function H(){var O=A.getSelectionModel().getSelected();if(!O){return }I(O.data.id)}function J(O){Ext.MessageBox.confirm("确认批量删除反馈信息","是否确认批量删除反馈信息？",function(P){if("yes"!=P){return }DWRUtil.useLoadingMessage("处理中...");Notice.batchRemoveFeedback(O,{callback:function(R){cancelLoadingMessage();var Q=Ext.decode(R);if(Q.result){L.reload();showMsg(Q.message)}else{Ext.Msg.alert("报告",Q.message)}},errorHandler:function(Q){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})})}function F(){var O=A.getSelectionModel().getSelected();if(!O){return }Ext.MessageBox.confirm("确认删除反馈信息","是否确认删除反馈信息？",function(P){if("yes"!=P){return }DWRUtil.useLoadingMessage("处理中...");Notice.removeFeedback(O.data.id,{callback:function(R){cancelLoadingMessage();var Q=Ext.decode(R);if(Q.result){L.remove(O);showMsg(Q.message)}else{Ext.Msg.alert("报告",Q.message)}},errorHandler:function(Q){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})})}var K=null;function I(P){currentInstance=1;if(!K){var O=new Ext.FormPanel({labelWidth:75,labelAlign:"left",frame:true,items:[new Ext.form.Hidden({name:"id"}),new Ext.form.TextField({fieldLabel:"主题*",name:"title",width:280,maxLength:30,allowBlank:false}),new Ext.form.TextField({fieldLabel:"填写人*",name:"name",width:280,maxLength:30,allowBlank:false}),new Ext.form.TextField({fieldLabel:"联系方式",name:"contact",width:280,maxLength:50,editable:false}),{xtype:"datefield",fieldLabel:"填写日期*",name:"input",width:280,format:"Y-m-d",allowBlank:false},{xtype:"textarea",fieldLabel:"反馈正文*",name:"content",height:100,width:280,maxLength:500,allowBlank:false},{xtype:"textarea",fieldLabel:"管理员答复",name:"comment",height:100,maxLength:500,width:280},{xtype:"checkbox",fieldLabel:"显示状态",boxLabel:"前台显示",checked:true,name:"pub"}]});K=new Ext.Window({el:"feedback-edit-win",title:"编辑反馈信息",modal:true,layout:"fit",closeAction:"hide",width:405,height:430,resizable:false,items:[O],buttons:[{text:"保存",handler:function(){if(O.form.isValid()){var Q=new Object();Q.id=O.getForm().findField("id").getValue();Q.title=O.getForm().findField("title").getValue();Q.input=O.getForm().findField("input").getValue();Q.content=O.getForm().findField("content").getValue();Q.contact=O.getForm().findField("contact").getValue();Q.name=O.getForm().findField("name").getValue();Q.pub=O.getForm().findField("pub").getValue();Q.comment=O.getForm().findField("comment").getValue();K.hide();DWRUtil.useLoadingMessage("处理中...");Notice.updateFeedback(Ext.encode(Q),{callback:function(S){cancelLoadingMessage();var R=Ext.decode(S);if(R.result){showMsg(R.message);L.reload();O.form.reset()}else{Ext.Msg.alert("报告",R.message)}},errorHandler:function(R){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})}}},{text:"取消",handler:function(){K.hide()}}]})}DWRUtil.useLoadingMessage("处理中...");Notice.getFeedbackById(P,{callback:function(S){cancelLoadingMessage();if(!S){Ext.Msg.alert("错误","对不起，程序出现错误!");return }var Q=Ext.data.Record.create([{name:"id",mapping:"id"},{name:"name",mapping:"name"},{name:"input",mapping:"input"},{name:"title",mapping:"title"},{name:"contact",mapping:"contact"},{name:"content",mapping:"content"},{name:"pub",mapping:"pub"},{name:"comment",mapping:"comment"}]);var R=new Q(Ext.decode(S));noticeContent=Ext.decode(S).content;K.show();K.items.last().getForm().loadRecord(R)},errorHandler:function(Q){Ext.Msg.alert("错误","对不起，程序出现错误!");cancelLoadingMessage()}})}};