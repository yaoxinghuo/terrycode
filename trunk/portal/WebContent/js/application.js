var Application =  {
  lastId: 0,
  currentSampleNb: 0,

  getNewId: function() {
    Application.lastId++;
    return "window_id_" + Application.lastId;
  },
  
  showCode: function(a, id) {
    a.innerHTML = $(id + "_codediv").visible() ? "View source" : "Hide source"
    $(id + "_codediv").toggle();
  },
  
  evalCode: function(id) {
    var pre = $(id);
    var code;
    if (pre.textarea && pre.textarea.visible)
      code = pre.textarea.value;
    else
      code = pre.innerHTML;
    
    code = code.gsub("&lt;", "<");
    code = code.gsub("&gt;", ">");
    
    try {
      eval(code);
    }
    catch (error) {
      Dialog.alert(" error accurs while interprating your javascript code <br/>" + error, {windowParameters: {width:300, showEffect:Element.show}, okLabel: "close"});
    }
  },

	addTitle: function(title, id) {
	  Application.currentSampleNb++;
	  idButton = id + '_click_button';
	  
	  document.write("<h2>" + Application.currentSampleNb + ". " + title + " (<span class='title'  id='" + idButton + "' href='#' onmouseover=\"$('" + idButton + "').addClassName('selected')\" onmouseout=\"$('" + idButton + "').removeClassName('selected')\" onclick=\"Application.evalCode('" + id + "', true)\">click here</span>)</h2>")
	}
}