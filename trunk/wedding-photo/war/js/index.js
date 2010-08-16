$(document).ready(function() {
	$('#photos').galleryView( {
		panel_width : document.documentElement.clientWidth - 76,
		panel_height : 10,
		frame_width : 100,
		frame_height : 80,
		auto_transition : true,
		transition_interval : 0,
		transition_speed : 'slow',
		background_color : '#333',
		background_fill_color : '#444',
		border : 'none',
		easing : 'easeInOutBack',
		nav_theme : 'custom',
		overlay_height : 52,
		filmstrip_position : 'top',
		overlay_position : 'top',
		pause_on_hover : true
	});
	$(".wbox").wBox( {
		isFrame : true,
		drag : true,
		iframeWH : {
			width : 700,
			height : 580
		},
		title : '评论|详情'
	});
	$(".wbox2").each(function() {
		$(this).wBox( {
			images : [ 'view?id=' + $(this).attr('pid') ],
			isImage : true
		});
	});
});

function checkSize(img) {
	if(typeof(img)!='object')
        img=document.getElementById(img);
    if(img==null)
        return;
    var image=document.createElement("img");
    image.onload=function (){
        var width=this.width;
        var height=this.height;
        if(width%2==1)
        	width=width+1;
        if(height%2==1)
        	height=height+1;
        $("#photonav1").attr("coords", "0,0," + width / 2 + "," + height);
    	$("#photonav2").attr("coords", width / 2 + ",0," + width + "," + height);
    };
    image.src=img.src;
}