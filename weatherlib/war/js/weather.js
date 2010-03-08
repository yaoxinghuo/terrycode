var timeoutID = null;
function showMsg(type, msg) {
	if (!msg)
		return;
	if (type == "error")
		msg = "<img src=\"images/fail.png\"/>&nbsp;" + msg;
	else if (type = "pass")
		msg = "<img src=\"images/pass.png\"/>&nbsp;" + msg;
	else
		msg = "<img src=\"images/info.png\"/>&nbsp;" + msg;
	document.getElementById("msg_content").innerHTML = msg;
	var l = msg.length;
	if (msg.indexOf("<a") != -1)
		l = l - 47;
	document.getElementById("msg").style.left = (document.body.clientWidth - l * 10)
			/ 2 + "px";
	document.getElementById("msg").style.visibility = "visible";
	if (timeoutID != null)
		clearTimeout(timeoutID);
	timeoutID = setTimeout("clearMsg()", 30000);
}
function clearMsg() {
	document.getElementById("msg").style.visibility = "hidden";
	document.getElementById("msg_content").innerHTML = "";
}

var slimit = 10;
var count = 0;
var total = 0;

var errorMsg = "对不起，服务器错误，请稍候再试";

$("#flex1").flexigrid( {
	url : 'webManager?action=schedulesList',
	dataType : 'json',
	colModel : [ {
		display : '创建时间',
		name : 'cdate',
		width : 120,
		sortable : false,
		align : 'left'
	}, {
		display : '定制城市',
		name : 'city',
		width : 110,
		sortable : false,
		align : 'left'
	}, {
		display : '每天发送时间',
		name : 'sdate',
		width : 100,
		sortable : false,
		align : 'left'
	}, {
		display : '接收邮箱',
		name : 'email',
		width : 170,
		sortable : false,
		align : 'left'
	}, {
		display : '定制内容状态',
		name : 'type',
		width : 120,
		sortable : false,
		align : 'left'
	}, {
		display : '最近发送时间',
		name : 'adate',
		width : 120,
		sortable : false,
		align : 'left',
		hide : false
	}, {
		display : '备注',
		name : 'remark',
		width : 150,
		sortable : false,
		align : 'left'
	} ],
	buttons : [ {
		name : '新建',
		bclass : 'add',
		onpress : scheduleAction
	}, {
		name : '删除',
		bclass : 'delete',
		onpress : scheduleAction
	}, {
		name : '修改',
		bclass : 'modify',
		onpress : scheduleAction
	} ],
	title : '定制天气预报列表',
	usepager : true,
	rp : 10,
	useRp : false,
	pagestat : '显示 第 {from} 到 {to} 条 , 总共  {total} 条记录',
	procmsg : '加载中, 请稍候 ...',
	height : 278
});
function scheduleAction(com, grid) {
	if (com == '删除') {
		var items = $('.trSelected', grid);
		if (items.length > 0) {
			if (confirm('确认删除选中的 ' + items.length + ' 条记录吗？' + '如果只是暂时不想收到邮件，只需设置状态为“暂时停用”即可')) {
				var itemlist = '';
				for (i = 0; i < items.length; i++) {
					itemlist += items[i].id.substr(3) + ",";
				}
				$.ajax( {
					type : "POST",
					cache : false,
					dataType : "json",
					url : "webManager",
					data : {
						"action" : "deleteSchedules",
						"ids" : itemlist
					},
					success : function(data) {
						showMsg(data.result ? "pass" : "error", data.message);
						if (data.result) {
							$("#flex1").flexReload();
							updateCountAndTotal(data);
						}
					},
					complete : function(req) {
						var code = req.status;
						if (code < 200 || code > 299)
							showMsg("error", errorMsg);
					}
				});
			}
		} else {
			showMsg("error", "请至少选中一行删除！");
		}
	} else if (com == '新建') {
		if (total >= 2000) {
			showMsg("error", "设置的定制数目已经达到上限:2000，后续会通过开分站的形式为您提供服务。");
			return;
		}
		if (count >= slimit) {
			showMsg("error", "设置的定制数目已经达到上限:" + slimit + "，请删除一些定制设置后再试，或联系站长");
			return;
		}
		$("#sid").val("");
		$("#message").html("").hide();
		if ($("#newSchedule").attr("title").indexOf("新建") == -1) {
			$("#newSchedule").attr("title", "<b>新建天气预报定制</b>");
			resetForm();
		}
		$('#newSchedule').trigger("click");
	} else if (com == '修改') {
		if ($('.trSelected', grid).length == 1) {
			var cell = $('.trSelected', grid);
			var sdate = cell.find("td:eq(2)").eq(0).text();
			var hour = sdate.substring(0, 2);
			var minute = sdate.substring(3, 5);
			$("#sdate_hour").attr("value", hour);
			$("#sdate_minute").attr("value", minute);
			$("#email").val(cell.find("td:eq(3)").eq(0).text());
			$("#city").val(cell.find("td:eq(1)").eq(0).text());
			var remark = cell.find("td:eq(6)").eq(0).text();
			if (remark != "[无]")
				$("#remark").val(remark);
			var type = cell.find("td:eq(4)").eq(0).text();
			if (type == "天气内容放正文")
				$("#type").attr("value", "1");
			else if (type == "天气内容放主题")
				$("#type").attr("value", "2");
			else
				$("#type").attr("value", "0");
			$("#sid").val(cell[0].id.substr(3));
		} else {
			showMsg("error", "请选中一行修改！");
			return;
		}
		$("#message").html("").hide();
		$("#newSchedule").attr("title", "<b>修改天气预报定制</b>");
		$('#newSchedule').trigger("click");
	}
}

$(function() {
	getAccountInfo();
	$("#scheduleSave").click(function() {
		var email = $("#email").val();
		if (!validateEmail(email)) {
			$("#message").html("接收邮箱不是有效的Email格式！").show();
			resetForm();
			return;
		}
		var city = $("#city").val();
		if (city == "") {
			$("#message").html("定制城市不能为空！").show();
			return;
		}
		var remark = $("#remark").val();
		var sdate = $("#sdate_hour").val() + ":" + $("#sdate_minute").val();
		var type = $("#type").val();
		var sid = $("#sid").val();
		$("#message").html("").hide();
		$("#scheduleSave").attr("disabled", true).attr("value", "请稍候");
		$.ajax( {
			url : "webManager",
			type : "POST",
			cache : false,
			data : {
				"action" : "saveSchedule",
				"email" : email,
				"city" : city,
				"remark" : remark,
				"sdate" : sdate,
				"type" : type,
				"sid" : $("#sid").val(),
				"test" : $("#test").attr("checked") == true ? "true" : "false"
			},
			dataType : "json",
			success : function(data) {
				if (!data.result)
					$("#message").html(data.message).show();
				else {
					tb_remove();
					resetForm();
					$("#flex1").flexReload();
					showMsg("pass", data.message);
					updateCountAndTotal(data);
				}
			},
			complete : function(req) {
				$("#scheduleSave").attr("disabled", false).attr("value", "保存");
				var code = req.status;
				if (code < 200 || code > 299)
					$("#message").html(errorMsg).show();
			}
		});
	});

	$("#updateNickname").click(function() {
		var nickname = $("#nickname").val();
		if (nickname == "") {
			showMsg("error", "请输入昵称");
			return;
		}
		$("#updateNickname").attr("disabled", true).attr("value", "请稍候");
		$.ajax( {
			url : "webManager",
			type : "POST",
			cache : false,
			data : {
				"action" : "updateNickname",
				"nickname" : nickname
			},
			dataType : "json",
			success : function(data) {
				showMsg(data.result ? "pass" : "error", data.message);
				updateCountAndTotal(data);
			},
			complete : function(req) {
				$("#updateNickname").attr("disabled", false).attr("value", "更改");
				var code = req.status;
				if (code < 200 || code > 299)
					showMsg("error", errorMsg);
			}
		});
	});
	
	$("#refreshCount").click(function(){
		getTotalCount();
	});
	
	$("#type").change(function(){
		if($("#type").val()=="0"){
			$("#test").attr("disabled", true).attr("checked", false);
		} else {
			$("#test").attr("disabled", false);
		}
	});
});

function resetForm() {
	document.getElementById("scheduleForm").reset();
}

function getAccountInfo() {
	$("#refreshCount").attr("disabled", true).attr("value", "请稍候");
	$("#count_loading").show();
	$.ajax( {
		url : "webManager",
		type : "POST",
		cache : false,
		data : {
			"action" : "getAccountInfo"
		},
		dataType : "json",
		success : function(data) {
			if (data.result) {
				$("#nickname").val(data.nickname);
				slimit = data.slimit;
				$("#slimit").html(slimit);
				updateCountAndTotal(data);
			}
		},
		complete : function(req) {
			$("#refreshCount").attr("disabled", false).attr("value", "刷新");
			$("#count_loading").hide();
			var code = req.status;
			if (code < 200 || code > 299)
				showMsg("error", errorMsg);
		}
	});
}

function getTotalCount() {
	$("#refreshCount").attr("disabled", true).attr("value", "请稍候");
	$("#count_loading").show();
	$.ajax( {
		url : "webManager",
		type : "POST",
		cache : false,
		data : {
			"action" : "getTotalCount"
		},
		dataType : "json",
		success : function(data) {
			if (data.result) {
				total = data.total;
				$("#total").html(total);
			}
		},
		complete : function(req) {
			$("#refreshCount").attr("disabled", false).attr("value", "刷新");
			$("#count_loading").hide();
			var code = req.status;
			if (code < 200 || code > 299)
				showMsg("error", errorMsg);
		}
	});
}

function updateCountAndTotal(data) {
	total = data.total;
	count = data.count;
	$("#count").html(count);
	$("#total").html(total);
}

function validateEmail(input) {
	var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (input.match(email)) {
		return true;
	} else {
		return false;
	}
}