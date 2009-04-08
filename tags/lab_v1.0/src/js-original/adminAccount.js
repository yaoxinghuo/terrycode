﻿var myEditWin;
function initAdminAccount() {
	Ext.QuickTips.init();

	if (!myEditWin) {
		var form = new Ext.FormPanel({
			labelWidth : 80,
			frame : true,
			defaultType : 'textfield',
			items : [{
				xtype : 'tbtext',
				//text : "(请输入当前密码后修改资料，‘修改密码’栏位为加密后的密码，<font color=red>如不需修改密码请不要更改其内容！</font>)"
				text : "(请输入当前密码后修改资料)"
			}, new Ext.form.Hidden({
				name : 'id'
			}), new Ext.form.TextField({
				fieldLabel : '工号/用户名*',
				name : 'no',
				width : 180,
				maxLength : 50,
				disabled : true,
				allowBlank : false
			}), new Ext.form.TextField({
				fieldLabel : '姓名*',
				name : 'name',
				width : 180,
				maxLength : 50,
				disabled : true,
				allowBlank : false
			}), new Ext.form.TextField({
				fieldLabel : '联系方式',
				name : 'mobile',
				emptyText : '请填写手机或Email等',
				width : 180,
				maxLength : 50
			}), new Ext.form.TextField({
				fieldLabel : '当前密码*',
				inputType : 'password',
				name : 'cupassword',
				width : 180,
				maxLength : 50,
				allowBlank : false
			}), new Ext.form.TextField({
				fieldLabel : '修改密码',
				inputType : 'password',
				name : 'password',
				width : 180,
				//allowBlank : false,
				maxLength : 50
			}), new Ext.form.TextField({
				fieldLabel : '确认密码',
				name : 'repassword',
				inputType : 'password',
				width : 180,
				//allowBlank : false,
				maxLength : 50
			}),{
				xtype : 'checkbox',
				fieldLabel : '密码状态',
				boxLabel : '已更改过密码',
				checked : true,
				disabled : true,
				name : 'changed'
			},{
				xtype : 'checkbox',
				fieldLabel : '预约状态',
				boxLabel : '禁用预约',
				checked : false,
				disabled : true,
				name : 'disabled'
			}, new Ext.form.ComboBox({
				fieldLabel : '账户类别*',
				hiddenName : 'admin',
				store : new Ext.data.SimpleStore({
					fields : ['returnValue', 'displayValue'],
					data : [[1, '学生'], [4, '老师'],[2, '设备管理员'], [3, '系统管理员']]
				}),
				valueField : 'returnValue',
				displayField : 'displayValue',
				allowBlank : false,
				width : 180,
				disabled : true,
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : '选择账户类别...',
				selectOnFocus : true,
				editable : false
			}), new Ext.form.ComboBox({
				fieldLabel : '所属组别*',
				hiddenName : 'type',
				store : new Ext.data.SimpleStore({
					fields : ['returnValue', 'displayValue'],
					data : groupArray
				}),
				valueField : 'returnValue',
				displayField : 'displayValue',
				allowBlank : false,
				disabled : true,
				width : 180,
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				emptyText : '选择组别...',
				selectOnFocus : true,
				editable : false
			})]
		});

		myEditWin = new Ext.Window({
			el : 'myaccount-edit-win',
			title : '修改账户记录',
			modal : true,
			layout : 'fit',
			closeAction : 'hide',
			width : 310,
			height : 370,
			resizable : false,
			items : [form],
			buttons : [{
				text : '保存',
				handler : function() {
					if (form.form.isValid()) {
						if (form.getForm().findField("password").getValue() != form
								.getForm().findField("repassword").getValue()) {
							form.getForm().findField("repassword")
									.markInvalid("两次密码输入不一致！");
							return;
						}
						var account = new Object();
						account.id = form.getForm().findField("id").getValue();
						account.name = form.getForm().findField("name")
								.getValue();
						account.no = form.getForm().findField("no").getValue();
						account.mobile = form.getForm().findField("mobile")
								.getValue();
						account.type = form.getForm().findField("type")
								.getValue();
						account.admin = form.getForm().findField("admin")
								.getValue();
						account.cupassword = form.getForm()
								.findField("cupassword").getValue();
						account.password = form.getForm().findField("password")
								.getValue();
						myEditWin.hide();
						DWRUtil.useLoadingMessage("处理中...");
						Account.updateAccountBySession(Ext.encode(account),
								true, {
									callback : function(value) {
										cancelLoadingMessage();
										var result = Ext.decode(value);
										if (result.result) {
											form.form.reset();
											showMsg(result.message);
										} else
											Ext.Msg.alert("报告", result.message);
									},
									errorHandler : function(message) {
										Ext.Msg.alert("错误", "对不起，程序出现错误!");
										cancelLoadingMessage();
									}
								});
					}
				}
			}, {
				text : '取消',
				handler : function() {
					myEditWin.hide();
				}
			}]
		});
	}

	DWRUtil.useLoadingMessage("处理中...");
	Account.getAccountDetailInfoBySession(true, {
		callback : function(value) {
			cancelLoadingMessage();
			if (!value) {
				Ext.Msg.alert("错误", "对不起，程序出现错误!");
				return;
			}
			var TopicRecord = Ext.data.Record.create([{
				name : 'id',
				mapping : 'id'
			}, {
				name : 'no',
				mapping : 'no'
			}, {
				name : 'name',
				mapping : 'name'
			}, {
				name : 'mobile',
				mapping : 'mobile'
			}, {
				name : 'admin',
				mapping : 'admin'
			}, {
				name : 'type',
				mapping : 'type'
			}, {
				name : 'disabled',
				mapping : 'disabled'
			}, {
				name : 'password',
				mapping : 'password'
			}, {
				name : 'repassword',
				mapping : 'repassword'
			}]);
			var formRecord = new TopicRecord(Ext.decode(value));
			myEditWin.show();
			myEditWin.items.last().getForm().loadRecord(formRecord);
		},
		errorHandler : function(message) {
			Ext.Msg.alert("错误", "对不起，程序出现错误!");
			cancelLoadingMessage();
		}
	});

}
