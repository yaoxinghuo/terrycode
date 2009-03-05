var $j=jQuery.noConflict();
$j(function(){	
	$j('.RoundedCorner').corner('round top 5px');
	$j('.RoundedCorner').gradient({
		from:      'E1EBF4',
		to:        '2F8FEA',
		direction: 'horizontal'
	});
//	$j('#maintab span.tabinactive').gradient({
//		from:      'F99A46',
//		to:        'ED5929',
//		direction: 'horizontal'
//	});
//	$j('#maintab span.qy').corner('round top 5px');
//	$j('#maintab span.xqy').corner('round top 5px');
	
	$j('#maintab span.tabinactive').hover(function(){
		$j(this).css("background","#5B9BCB");
	},function(){
		$j(this).css("background","#C0D9FB");
	});
//	$j("#searchsubmit").hover(function(){
//		alert("Hover");
//		$j(this).css("background","#FB8C31");
//	},function(){
//		$j(this).css("background","#2F8FEA");
//	});

	//$j('#searchbar').corner('round top 5px');
	//$j('#searchbar').corner('round bottom 5px');
//	$j("#header").gradient({
//		from:      'F8F8F8',
//		to:        'E1EBF4',
//		direction: 'horizontal'
//	});
	$j('#searchbar').gradient({
		from:      'E1EBF4',
		to:        '2F8FEA',
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
	$j("#search_advance_button").toggle(function(){
		$j("#searchbar").animate({ height: 50, opacity: 'slow' }); 
		$j("#search_advance").show("slow");
		$j("#search_advance_button > img").attr({ src: "images/arrow-up.gif", alt: "收起" }); 
	},function(){
		$j("#search_advance").hide();
		$j("#searchbar").animate({ height: 27, opacity: 'slow' });
		$j("#search_advance_button img").attr({ src: "images/arrow-down.gif", alt: "展开" }); 
	});
	
//	if(jQuery.browser.msie) {
//		window.onresize=function(){window.location.reload();}
//	}
});

function changebg(id,color){
	$j("#"+id).css("border","1px solid "+color);
}