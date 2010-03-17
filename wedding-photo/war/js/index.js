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
		title : '评论'
	});
	$(".wbox2").each(function() {
		$(this).wBox( {
			images : [ 'view?id=' + $(this).attr('pid') ],
			isImage : true
		});
	});
});