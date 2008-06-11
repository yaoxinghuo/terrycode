Ext.onReady(function(){
	Ext.get("button").on("click",test);
});

function test(){

	var reader = new Ext.data.DWRJsonReader({ 
          	totalProperty: "results",     
		    root: "rows"             
         },
         new Ext.data.Record.create([ 
         {name: 'id', mapping: 'id'},      
         {name: 'name',mapping:'name'},
         {name: 'sex',mapping:'sex'}                
       ]));
	 var store=new Ext.data.Store({ 
         proxy:new Ext.data.DWRProxy(DwrTest.getJSON,10,6), 
         reader:reader,
         autoLoad:true 
     }); 
	var cm = new Ext.grid.ColumnModel([
		{id:'id',header:'id',width:100,dataIndex:'id',sortable:true},
		{header:'name',width:100,dataIndex:'name'},
		{header:'sex',width:100,renderer:function(value){
			if(value=='male')
				return '<span style="color:green;">' + value + '</span>';
			else
				return '<span style="color:red;">' + value + '</span>';
		}, dataIndex:'sex'}
	]);
	var grid = new Ext.grid.GridPanel({
		store:store,
		cm:cm,
		height:500,
		title:'Grid Test'
	});
	grid.render(Ext.getBody());
}

 Ext.data.DWRProxy = function(dwrCall){  
   Ext.data.DWRProxy.superclass.constructor.call(this);  
   this.dwrCall = dwrCall;  
   /** 传入多个输入参数的处理**/  
    this.callParams = new Array();  
   for( i=1;i<arguments.length;i++){  
     this.callParams.push(arguments[i]);  
   }  
 };  
   
 Ext.extend(Ext.data.DWRProxy, Ext.data.DataProxy, {  
   load : function(params, reader, callback, scope, arg) {  
     if(this.fireEvent("beforeload", this, params) !== false) {  
       var delegate = this.loadResponse.createDelegate(this, [reader, callback, scope, arg], 1);  
       this.callParams.push(delegate);  
       this.dwrCall.apply(this, this.callParams);  
     } else {  
       callback.call(scope || this, null, arg, false);  
     }  
   },  
   
   loadResponse : function(dwrResult, reader, callback, scope, arg) {  
     var result;  
     try {  
       result = reader.read(dwrResult);  
     } catch(e) {  
       this.fireEvent("loadexception", this, null, dwrResult, e);  
       callback.call(scope, null, arg, false);  
       return;  
     }  
     callback.call(scope, result, arg, true);  
   }  
 });
 
  Ext.data.DWRJsonReader=function(meta,recordType){     
   Ext.data.DWRJsonReader.superclass.constructor.call(this,meta,recordType);     
 };     
     
 Ext.extend(Ext.data.DWRJsonReader,Ext.data.JsonReader,{     
   read : function(response){  
         if(typeof response == 'object')  
           alert("object");  
         var o = eval("("+response+")");  
         if(!o) {  
             throw {message: "JsonReader.read: Json object not found"};  
         }  
         if(o.metaData){  
             delete this.ef;  
             this.meta = o.metaData;  
             this.recordType = Ext.data.Record.create(o.metaData.fields);  
             this.onMetaChange(this.meta, this.recordType, o);  
         }  
         return this.readRecords(o);  
     }    
 });