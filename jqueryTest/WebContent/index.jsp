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
				$('#a').click(function(){
					alert("A")
				});
				$('#b').click(function(e){
					alert("B")
					e.stopPropagation();//阻止冒泡， 从来不输出 “A" 。 可以去掉 ，试试对比效果。
				});
				
			});
		</script>
		<button>Test</button>
		
		<div id="a">aaaaa</div>
		<div id="b">bbbbb</div>
		<div>ccccc</div>
		<div>ddddd</div>
	</body>
</html>
