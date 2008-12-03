Ext.data.DWRProxy = function(config) {
	Ext.apply(this, config);
	Ext.data.DWRProxy.superclass.constructor.call(this);
};

Ext.extend(Ext.data.DWRProxy, Ext.data.DataProxy, {
	dwrFunction : null,
	loadArgsKey : 'dwrFunctionArgs',
	load : function(params, reader, loadCallback, scope, arg) {
		var dataProxy = this;
		if (dataProxy.fireEvent("beforeload", dataProxy, params) !== false) {
			var loadArgs = params[this.loadArgsKey] || params;
			var dwrFunctionArgs = [];
			if (loadArgs instanceof Array) {
				for (var i = 0; i < loadArgs.length; i++) {
					dwrFunctionArgs.push(loadArgs[i]);
				}
			} else {
				for (var loadArgName in loadArgs) {
					dwrFunctionArgs.push(loadArgs[loadArgName]);
				}
			}
			dwrFunctionArgs.push({
				callback : function(response) {
					// var records = reader.readRecords(response);
					var records = reader.read(response);
					dataProxy.fireEvent("load", dataProxy, response,
							loadCallback);
					loadCallback.call(scope, records, arg, true);
				},
				exceptionHandler : function(message, exception) {
					dataProxy.fireEvent("loadexception", dataProxy, message,
							loadCallback, exception);
					loadCallback.call(scope, null, arg, false);
				}
			});
			this.dwrFunction.apply(Object, dwrFunctionArgs);
		} else {
			callback.call(scope || this, null, arg, false);
		}
	}
});

Ext.data.DWRJsonReader = function(meta, recordType) {
	Ext.data.DWRJsonReader.superclass.constructor.call(this, meta, recordType);
};

Ext.extend(Ext.data.DWRJsonReader, Ext.data.JsonReader, {
	read : function(response) {
		if (typeof response == 'object') {
			Ext.Msg.alert("错误信息", "您可能没有适当的权限进行该操作！");
			return;
		}
		var o = eval("(" + response + ")");
		if (!o) {
			throw {
				message : "JsonReader.read: Json object not found"
			};
		}
		if (o.metaData) {
			delete this.ef;
			this.meta = o.metaData;
			this.recordType = Ext.data.Record.create(o.metaData.fields);
			this.onMetaChange(this.meta, this.recordType, o);
		}
		return this.readRecords(o);
	}
});

Ext.apply(Ext.form.VTypes, {

	password : function(val, field) {
		if (field.initialPassField) {
			var pwd = Ext.getCmp(field.initialPassField);
			return (val == pwd.getValue());
		}
		return true;
	},

	passwordText : '两次密码输入不一致！'
});

function cancelLoadingMessage() {
	var disabledZone = $("disabledZone");
	if (disabledZone) {
		$("disabledZone").style.visibility = 'hidden';
	}
	DWREngine.setPreHook(function() {/* Ignore */
	});
	DWREngine.setPostHook(function() {/* Ignore */
	});
}