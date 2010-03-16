$(function() {
	$("#photoInputs").uploadify(
			{
				'uploader' : '../uploadify.swf',
				'script' : 'photoUpload',
				'cancelImg' : 'images/cancel.png',
				'folder' : 'uploads',
				'multi' : true,
				'fileDesc' : 'Image Files',
				'fileExt' : '*.jpg;*.jpeg;*.png;*.gif',
				'multi' : true,
				'simUploadLimit' : 5,
				'sizeLimit' : 1048576,
				onError : function(event, queueID, fileObj, errorObj) {
					var msg;
					if (errorObj.status == 404) {
						msg = '无法找到上传程序';
					} else if (errorObj.type === "HTTP")
						msg = errorObj.type + ": " + errorObj.status;
					else if (errorObj.type === "File Size")
						msg = fileObj.name + '<br>' + errorObj.type
								+ ' Limit: '
								+ Math.round(errorObj.sizeLimit / 1024) + 'KB';
					else
						msg = errorObj.type + ": " + errorObj.text;
					$.jGrowl('<p></p>' + msg, {
						theme : 'error',
						header : '错误',
						sticky : true
					});
					$("#fileUploadgrowl" + queueID).fadeOut(250, function() {
						$("#fileUploadgrowl" + queueID).remove()
					});
					return false;
				},
				onCancel : function(a, b, c, d) {
					var msg = "取消上传: " + c.name;
					$.jGrowl('<p></p>' + msg, {
						theme : 'warning',
						header : '已取消上传',
						life : 4000,
						sticky : false
					});
				},
				onClearQueue : function(a, b) {
					var msg = "从列队中清除 " + b.fileCount + " 个图片文件";
					$.jGrowl('<p></p>' + msg, {
						theme : 'warning',
						header : '已清除列队',
						life : 4000,
						sticky : false
					});
				},
				onComplete : function(a, b, c, d, e) {
					var size = Math.round(c.size / 1024);
					var data = eval("(" + d + ")");
					if (data.result) {
						$.jGrowl('<p></p>' + data.message, {
							theme : 'success',
							header : '图片文件上传成功',
							life : 4000,
							sticky : false
						});
					} else {
						$.jGrowl('<p></p>' + data.message, {
							theme : 'error',
							header : '错误',
							sticky : true
						});
						return false;
					}
				}

			});
});
