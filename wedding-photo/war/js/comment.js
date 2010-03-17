$(function() {
	$("#commentSave")
			.click(
					function() {
						var pid = $("#pid").val();
						var nickname = $("#nickname").val();
						if (nickname == "") {
							$("#message").html("昵称不能为空！").show();
							return;
						}
						var email = $("#email").val();
						if (email != "" && !validateEmail(email)) {
							$("#message").html("Email格式不正确！").show();
							resetForm();
							return;
						}
						var content = $("#ccontent").val();
						if (content == "") {
							$("#message").html("评论内容不能为空！").show();
							return;
						}
						$("#message").html("").hide();
						$("#commentSave").attr("disabled", true).attr("value",
								"请稍候");
						$
								.ajax( {
									url : "comment",
									type : "POST",
									cache : false,
									data : {
										"action" : "saveComment",
										"email" : email,
										"nickname" : nickname,
										"content" : content,
										"pid" : pid
									},
									dataType : "json",
									success : function(data) {
										if (!data.result)
											$("#message").html(data.message)
													.show();
										else {
											$("#ccontent").val("");
											$("#cc")
													.append(
															'<li><p><span class="cnick">'
																	+ data.nickname
																	+ '</span>&nbsp;<span class="ccdate">'
																	+ data.cdate
																	+ '</span>&nbsp;说：</p><span class="ccont">'
																	+ data.content
																	+ '</span><br/></li>')
											$("#message").html(data.message)
													.show();
											window.parent.document
													.getElementById("c-" + pid).innerHTML = "共有 <span class='commentcount'>"
													+ data.count + "</span> 条";
										}
									},
									complete : function(req) {
										$("#commentSave").attr("disabled",
												false).attr("value", "发布评论");
										var code = req.status;
										if (code < 200 || code > 299)
											$("#message").html(errorMsg).show();
									}
								});
					});
});

function validateEmail(input) {
	var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (input.match(email)) {
		return true;
	} else {
		return false;
	}
}
