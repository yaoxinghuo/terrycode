var $j=jQuery.noConflict();
$j(function(){
	$j('.RoundedCorner').corner('round top 5px');
	$j('.RoundedCorner').gradient({
		from:      'E6F1D8',
		to:        '5B9BCB',
		direction: 'horizontal'
	});
	
	$j("#topic span").hover(function(){
		$j("#topic span").each(function(){
			$j(this).css("background","");
			$j("#"+this.id+"c").css("display","none");
		});
		$j(this).css("background","#FFF");
		$j("#"+this.id+"c").css("display","block");
	},function(){});
	
	var boxpos=1;
	var intervalID=null;
	$j("#boxxt span").hover(function(){
		if(intervalID)
			window.clearInterval(intervalID);
		$j("#boxxt span").each(function(){
			this.className="boxxt2b";
		});
		this.className="boxxt2";
		boxpos=(parseInt(this.id.substring(8)))+1;
	},function(){
		intervalID=window.setInterval(function(){
			if(boxpos>5) boxpos=1;
			for(var i=1;i<=5;i++){
			 	if(i==boxpos) {
			 		document.getElementById("boxtitle"+i).className="boxxt2";
			 		$j("#boxxi").html("测试内容"+i);
			 	} else 
			 		document.getElementById("boxtitle"+i).className="boxxt2b";
			 }
			boxpos++;
		},2500);
	});
	
	intervalID=window.setInterval(function(){
		if(boxpos>5) boxpos=1;
		for(var i=1;i<=5;i++){
		 	if(i==boxpos) {
		 		$j("#boxtitle"+i).removeClass("boxxt2c");
		 		$j("#boxtitle"+i).addClass("boxxt2");
		 		$j("#boxxi").html("测试内容"+i);
		 	} else {
		 		$j("#boxtitle"+i).removeClass("boxxt2");
		 		$j("#boxtitle"+i).addClass("boxxt2c");
		 	}
		 }
		boxpos++;
	},2500);
	
});