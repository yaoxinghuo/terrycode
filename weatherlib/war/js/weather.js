$("#flex1").flexigrid( {
	url : 'webManager',
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
		width : 150,
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
		name : '增加',
		bclass : 'add',
		onpress : test
	}, {
		name : '删除',
		bclass : 'delete',
		onpress : test
	}, {
		name : '修改',
		bclass : 'modify',
		onpress : test
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
function test(com, grid) {
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
					url : "webManager?action=delete",
					data : "items=" + itemlist,
					success : function(data) {
						alert("Query: " + data.query
								+ " - Total affected rows: " + data.total);
						$("#flex1").flexReload();
					}
				});
			}
		} else {
			alert("请至少选择一行删除！");
		}
	} else if (com == '增加') {
		$('#newSchedule').trigger("click");
	}
}
/*
 * $('b.top').click (function (){ $(this).parent().toggleClass('fh'); });
 */

$(function() {
	$('#sdate').timepickr();
	$("#sdate").focus(function() {
		$('#sdate_blank').attr("height","70px");
	});
	$("#sdate").blur(function() {
		$('#sdate_blank').attr("height","1px");
	});
});