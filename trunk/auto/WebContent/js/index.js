var $j=jQuery.noConflict();
$j(function(){
	$j('.RoundedCorner').corner('round top 5px');
	$j('.RoundedCorner').gradient({
		from:      'E1EBF4',
		to:        '2F8FEA',
		direction: 'horizontal'
	});
	$j('#maintab li.dqy').corner('round top 5px');
	$j('#maintab li.dqy').gradient({
		from:      'D5E1F4',
		to:        '6AA9E4',
		direction: 'horizontal'
	});
	$j('#maintab li.qy').corner('round top 5px');
	$j('#maintab li.qy').gradient({
		from:      'FCDCBA',
		to:        'FFA648',
		direction: 'horizontal'
	});
	$j('#maintab li.xqy').corner('round top 5px');
	$j('#maintab li.xqy').gradient({
		from:      'D5E1F4',
		to:        '9DBCED',
		direction: 'horizontal'
	});
	$j('#searchbar').corner('round top 5px');
	$j('#searchbar').corner('round bottom 5px');
	
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
		$j("#boxxi").html("测试内容"+(boxpos-1));
	},function(){
		intervalID=window.setInterval(function(){
			if(boxpos>5) boxpos=1;
			for(var i=1;i<=5;i++){
			 	if(i==boxpos) {
			 		$j("#boxtitle"+i).addClass("boxxt2");
			 		$j("#boxxi").html("测试内容"+i);
			 	} else 
			 		$j("#boxtitle"+i).removeClass("boxxt2");
			 }
			boxpos++;
		},2500);
	});
	
	intervalID=window.setInterval(function(){
		if(boxpos>5) boxpos=1;
		for(var i=1;i<=5;i++){
		 	if(i==boxpos) {
		 		$j("#boxtitle"+i).addClass("boxxt2");
		 		$j("#boxxi").html("测试内容"+i);
		 	} else
		 		$j("#boxtitle"+i).removeClass("boxxt2");
		 }
		boxpos++;
	},2500);
	
	$j("#searchkey").Watermark("填写关键字");
	
	if(jQuery.browser.msie) {
		window.onresize=function(){window.location.reload();}
	}
});

