$(document).ready(function() {
	var wh = getPageSize();
	$('#photos').galleryView( {
		panel_width : wh[0] - 76,
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
			height : wh[1]-200<=0?400:wh[1]-200
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

function getPageSize() {
	var xScroll, yScroll;
	if (window.innerHeight && window.scrollMaxY) {
		xScroll = window.innerWidth + window.scrollMaxX;
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight) { // all
		// but
		// Explorer
		// Mac
		xScroll = document.body.scrollWidth;
		yScroll = document.body.scrollHeight;
	} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla
		// and Safari
		xScroll = document.body.offsetWidth;
		yScroll = document.body.offsetHeight;
	}
	var windowWidth, windowHeight;
	if (self.innerHeight) { // all except Explorer
		if (document.documentElement.clientWidth) {
			windowWidth = document.documentElement.clientWidth;
		} else {
			windowWidth = self.innerWidth;
		}
		windowHeight = self.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) { // Explorer 6 Strict
		// Mode
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	} else if (document.body) { // other Explorers
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}
	// for small pages with total height less then height of the viewport
	if (yScroll < windowHeight) {
		pageHeight = windowHeight;
	} else {
		pageHeight = yScroll;
	}
	// for small pages with total width less then width of the viewport
	if (xScroll < windowWidth) {
		pageWidth = xScroll;
	} else {
		pageWidth = windowWidth;
	}
	pageWidth = Math.max(pageWidth, windowWidth);
	pageHeight = Math.max(pageHeight, windowHeight);
	arrayPageSize = new Array(pageWidth, pageHeight);
	return arrayPageSize;
};