<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>nyroModal :: Demo</title>
	<link rel="stylesheet" href="styles/nyroModal.css" type="text/css" media="screen" />
	<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.nyroModal-1.4.2.pack.js"></script>
	<script type="text/javascript">
	//<![CDATA[
	// Demo NyroModal
	$(function() {
		$.nyroModalSettings({
			debug: false,
			processHandler: function(settings) {
				var url = settings.url;
				if (url && url.indexOf('http://www.youtube.com/watch?v=') == 0) {
					$.nyroModalSettings({
						type: 'swf',
						height: 355,
						width: 425,
						url: url.replace(new RegExp("watch\\?v=", "i"), 'v/')
					});
				}
			},
			endShowContent: function(elts, settings) {
				$('.resizeLink', elts.contentWrapper).click(function(e) {
					e.preventDefault();
					$.nyroModalSettings({
						width: Math.random()*1000,
						height: Math.random()*1000
					});
					return false;
				});
				$('.bgLink', elts.contentWrapper).click(function(e) {
					e.preventDefault();
					$.nyroModalSettings({
						bgColor: '#'+parseInt(255*Math.random()).toString(16)+parseInt(255*Math.random()).toString(16)+parseInt(255*Math.random()).toString(16)
					});
					return false;
				});
			}
		});

		$('#manual').click(function(e) {
		    e.preventDefault();
		    var content = 'Content wrote in JavaScript<br />';
		    jQuery.each(jQuery.browser, function(i, val) {
		      content+= i + " : " + val+'<br />';
		    });
		    $.nyroModalManual({
		      bgColor: '#3333cc',
		      content: content
		    });
		    return false;
		  });
		  $('#manual2').click(function(e) {
		    e.preventDefault();
		    $.nyroModalManual({
		      url: 'demoSent.jsp'
		    });
		    return false;
		  });
		  $('#manual3').click(function(e) {
		    e.preventDefault();
		    $('#imgFiche').nyroModalManual({
		      bgColor: '#cc3333'
		    });
		    return false;
		  });
		  $('#myValidForm').submit(function(e) {
		    e.preventDefault();
		    if ($("#myValidForm :text").val() != '') {
		      $('#myValidForm').nyroModalManual();
		    } else {
		      alert("Enter a value before going to " + $('#myValidForm').attr("action"));
		    }
		    return false;
		  });
		
		function preloadImg(image) {
			var img = new Image();
			img.src = image;
		}
		
		preloadImg('img/ajaxLoader.gif');
		preloadImg('img/prev.gif');
		preloadImg('img/next.gif');

	});
	
	function reload(){
		window.location.href = "index.jsp";
	}
	
	//]]>
	</script>
	<style type="text/css">
		#blocker {
			width: 300px;
			height: 300px;
			background: red;
			padding: 30px;
			border: 5px solid green;
		}
	</style>
</head>
<body>

<h1>Demos</h1>
<p>
	<a href="demoSent.jsp" class="nyroModal">Ajax</a><br />
	<a href="demoSent.jsp#test" class="nyroModal">Ajax Filtering Content #test</a><br />
	<a href="demoSent.jsp#blabla" class="nyroModal">Ajax Filtering Content #blabla</a>
</p>

<br/>
<a href="#test" class="nyroModal">DOM Element (hidden div)</a>
<div id="test" style="display: none; width: 600px;">
  <a href="demoSent.jsp" class="nyroModal">Open a new modal</a><br />
  Test
</div>


<br/>
<a id="manual" href="#">Manual Call</a>
<a id="manual2" href="#">Manual Call to get an ajax content</a>
<a id="manual3" href="#">Manual Call calling through an other link</a>
<form id="myValidForm" method="post" action="demoSent.php">
  <input type="text" name="wouhou" />
  <input type="submit" value="simple form with validation" />
</form>


<br/>
<a href="http://www.google.com/" class="nyroModal">Iframe Automatique via other hostname</a>
<a href="demoSent.jsp" target="_blank" class="nyroModal">Iframe Automatique via target=_blank</a>


<script type="text/javascript">
$(function() {
  $.fn.nyroModal.settings.processHandler = function(settings) {
    var from = settings.from;
    if (from && from.href && from.href.indexOf('http://www.youtube.com/watch?v=') == 0) {
      $.nyroModalSettings({
        type: 'swf',
        height: 355,
        width: 425,
        url: from.href.replace(new RegExp("watch\\?v=", "i"), 'v/')
      });
    }
  };
});
</script>
<a href="http://www.youtube.com/watch?v=nTasT5h0LEg" class="nyroModal">Youtube Via Process Handler</a>

</body>
</html>