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

$("#flex1").flexigrid( {
	url : 'webManager?action=schedulesList',
	dataType : 'json',
	colModel : [ {
		display : '创建时间',
		name : 'cdate',
		width : 160,
		sortable : false,
		align : 'left'
	}, {
		display : '定制城市',
		name : 'city',
		width : 120,
		sortable : false,
		align : 'left'
	}, {
		display : '发送时间',
		name : 'sdate',
		width : 120,
		sortable : false,
		align : 'left'
	}, {
		display : '接受邮箱',
		name : 'email',
		width : 170,
		sortable : false,
		align : 'left'
	}, {
		display : '状态',
		name : 'type',
		width : 120,
		sortable : false,
		align : 'left'
	}, {
		display : '备注',
		name : 'remark',
		width : 150,
		sortable : false,
		align : 'left'
	}, {
		display : '上次发送时间',
		name : 'adate',
		width : 160,
		sortable : false,
		align : 'left',
		hide : true
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
	}, {
		separator : true
	} ],
	sortname : "id",
	sortorder : "asc",
	title : '定制天气预报列表',
	useRp : true,
	rp : 10,
	showTableToggleBtn : true,
	height : 300
});
function scheduleAction(com, grid) {
	if (com == '删除') {
		if ($('.trSelected', grid).length > 0) {
			if (confirm('是否删除这 ' + $('.trSelected', grid).length + ' 条记录吗?')) {
				var items = $('.trSelected', grid);
				var itemlist = '';
				for (i = 0; i < items.length; i++) {
					itemlist += items[i].id.substr(3) + ",";
				}
				$.ajax( {
					type : "POST",
					dataType : "json",
					url : "webManager?action=deleteSchedules",
					data : "ids=" + itemlist,
					success : function(data) {
						showMsg(data.result ? "pass" : "error", data.message);
						if (data.result) {
							$("#flex1").flexReload();
						}
					}
				});
			}
		} else {
			showMsg("error", "请至少选中一行删除！");
		}
	} else if (com == '新建') {
		$("#sid").val("");
		$("#newSchedule").attr("title", "<b>新建天气预报提醒</b>");
		$('#newSchedule').trigger("click");
	} else if (com == '修改') {
		if ($('.trSelected', grid).length > 0) {
			var cell = $('.trSelected', grid);
			var sdate = cell.find("td:eq(2)").eq(0).text();
			var hour = sdate.substring(0, 2);
			var minute = sdate.substring(3, 5);
			$("#sdate_hour").attr("value", hour);
			$("#sdate_minute").attr("value", minute);
			$("#email").val(cell.find("td:eq(3)").eq(0).text());
			$("#city").val(cell.find("td:eq(1)").eq(0).text());
			$("#remark").val(cell.find("td:eq(5)").eq(0).text());
			var type = cell.find("td:eq(4)").eq(0).text();
			if (type == "天气内容放正文")
				$("#type").attr("value", "1");
			else if (type = "天气内容放主题")
				$("#type").attr("value", "2");
			else
				$("#type").attr("value", "0");
			$("#sid").val(cell[0].id.substr(3));
		} else {
			showMsg("error", "请选中一行修改！");
			return;
		}
		$("#newSchedule").attr("title", "<b>修改天气预报提醒</b>");
		$('#newSchedule').trigger("click");
	}
}
/*
 * $('b.top').click (function (){ $(this).parent().toggleClass('fh'); });
 */

$(function() {
	$("#scheduleSave").click(function() {
		var email = $("#email").val();
		if (!validateEmail(email)) {
			$("#message").html("Email格式不正确！").show();
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
		$("#message").html("").hide();
		$("#scheduleSave").attr("disabled", "true").attr("value", "请稍候");
		$.getJSON("webManager", {
			"action" : "saveSchedule",
			"email" : email,
			"city" : city,
			"remark" : remark,
			"sdate" : sdate,
			"type" : type,
			"sid" : $("#sid").val()
		}, function(data) {
			$("#scheduleSave").attr("disabled", "").attr("value", "发送");
			if (!data.result)
				$("#message").html(data.message).show();
			else {
				tb_remove();
				resetForm();
				$("#flex1").flexReload();
				showMsg("pass", data.message);
			}
		});
	});
});

function resetForm() {
	document.getElementById("scheduleForm").reset();
}

function validateEmail(input) {
	var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (input.match(email)) {
		return true;
	} else {
		return false;
	}
}