function newsList(id,data,show_tip,show_from,show_time){ 
    	
    var cellfuncs = [    
    	 function(data){
    	 	var span = document.createElement("span");
    	 	if(show_from)
    	 		span.appendChild(document.createTextNode("["+data.from+"] "));
    	 	var a = document.createElement("a");   
    	 	a.setAttribute("href",data.link);
    	 	a.setAttribute("target","_blank");
    	 	if(show_tip) {
    	 		var descString=data.desc;
	    	 	if(descString==null)
	    	 		descString = '<font color=green>（没有描述）</font>';
	    	 	var tooltipString=descString+" <a href="+data.link+" target=_blank>点击查看详情</a>";
	        	a.onmouseover = function(){
	        		Tip(tooltipString, WIDTH, 500, SHADOW, true, FADEIN, 300, FADEOUT, 300, TITLE, data.date+"--"+data.title, BGCOLOR, '#ffcccc', FONTCOLOR, '#800000', FONTSIZE, '9pt', FONTFACE, 'Courier New, Courier, mono', BORDERCOLOR, '#c00000',STICKY, 1, OFFSETX, 20, CLOSEBTN, true, CLICKCLOSE, true);
	        	};
    	 	}
    	 	var text = document.createTextNode(data.title);
    	 	a.appendChild(text);
    	 	span.appendChild(a);
			return span;       
         },
         function(data){
         	if(show_time)
         		return getFormatDate(new Date(Date.parse(data.date.replace(/-/g,   "/"))),"hh:ii");
         }
    ];  
    
    DWRUtil.removeAllRows(id+"_table"); 
        
    DWRUtil.addRows(id+"_table", data,cellfuncs,{    
    rowCreator:function(options) {    
        var row = document.createElement("tr");    
        return row;    
    },    
    cellCreator:function(options) {    
        var td = document.createElement("td");    
        td.setAttribute("align","left");
        return td; 
    }
    });
    
    if(data==null||data.length==0){
    	 var tr = document.createElement("tr");
    	 var td = document.createElement("td");
    	 td.style.color='green';
    	 td.setAttribute("align","center");
    	 tr.appendChild(td);
         var oText = document.createTextNode("对不起，暂无内容。");
         td.appendChild(oText);
    	document.getElementById(id+"_table").appendChild(tr);
    }
    
   }
   
   function convertString(str){
   	return str.replace(new RegExp("'","gm"),"").replace(new RegExp('"',"gm"),"").replace(new RegExp('\n',"gm"),"");
   }
   
   function getFormatDate(date_obj,date_templet){
	  var year,month,day,hour,minutes,seconds,short_year,full_month,full_day,full_day,full_hour,full_minutes,full_seconds;
	  if(!date_templet)date_templet = "yyyy-mm-dd hh:ii:ss";
	  year = date_obj.getFullYear().toString();
	  short_year = year.substring(2,4);
	  month = (date_obj.getMonth()+1).toString();
	  month.length == 1 ? full_month = "0"+month : full_month = month;
	  day = date_obj.getDate().toString();
	  day.length == 1 ? full_day = "0"+day : full_day = day;
	  hour = date_obj.getHours().toString();
	  hour.length == 1 ? full_hour = "0"+hour : full_hour = hour;
	  minutes = date_obj.getMinutes().toString();
	  minutes.length == 1 ? full_minutes = "0"+minutes : full_minutes = minutes;
	  seconds = date_obj.getSeconds().toString();
	  seconds.length == 1 ? full_seconds = "0"+seconds : full_seconds = seconds;
	  return date_templet.replace("yyyy",year).replace("mm",full_month).replace("dd",full_day).replace("yy",short_year).replace("m",month).replace("d",day).replace("hh",full_hour).replace("ii",full_minutes).replace("ss",full_seconds).replace("h",hour).replace("i",minutes).replace("s",seconds);
}