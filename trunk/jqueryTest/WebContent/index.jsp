<html>
	<head>
		<script src="js/jquery-1.2.6.min.js"></script>
		<style>
			.test{color:red}
		</style>
		<title>Test</title>
	</head>
	<body>
		<script>
			$(document).ready(function(){
				$('#a').mousedown(function(e){
					alert(e.which);
				});
				$('#b').click(function(e){
					alert("B")
					e.stopPropagation();//阻止冒泡， 从来不输出 “A" 。 可以去掉 ，试试对比效果。
				});
				$('#c').click(function(){
					$.getScript("js/test.js",function(){
						//alert("Script loaded");
					});
				});
				$("#msg").ajaxError(function(event, request, settings, error){
					$(this).html("Error requesting page " + settings.url);
				});
				$("#msg").ajaxSuccess(
					function(request, settings){
						$(this).html("Successful Request!");
					}
				); 
			});
		</script>
		<button>Test</button>
		
		<div id="a">aaaaa</div>
		<div id="b">bbbbb</div>
		<div id="c">ccccc</div>
		Msg:<span class="test" id="msg"></span>
	</body>
</html>
