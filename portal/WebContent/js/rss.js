function renderByNews(address,limit,id) {
		/*var myAjax = new Ajax.Request (
			address,{
				method:'get',onComplete:
					function(originalRequest){
						var root = originalRequest.responseXML.getElementsByTagName("channel")[0];
						var link = root.getElementsByTagName("link")[0].childNodes[0].nodeValue;
						var title = root.getElementsByTagName("title")[0].childNodes[0].nodeValue;
						var items = root.getElementsByTagName("item");
						if(items.length<limit)
							limit = items.length;
						var data = new Array();
						for(i=0;i<limit;i++){
							var item = items[i];
							var title0 = item.getElementsByTagName("title")[0].childNodes[0].nodeValue;
							var desc0 = item.getElementsByTagName("description")[0].childNodes[0].nodeValue;
							var link0 = item.getElementsByTagName("link")[0].childNodes[0].nodeValue;
							var date0 = item.getElementsByTagName("pubDate")[0].childNodes[0].nodeValue;
							data[i]={"title":title0,"desc":desc0,"link":link0,"date":date0};
						}
						userList(id,data);
					}
				}
		);*/
		DWRUtil.removeAllRows("m_"+id+"_table");
		var tr = document.createElement("tr");
	    var td = document.createElement("td");
	    td.style.color='green';
	    tr.appendChild(td);
	    var oText = document.createTextNode("请稍候...");
	    td.setAttribute("id","m_"+id+"_td");
	    td.appendChild(oText);
    	$("m_"+id+"_table").appendChild(tr);
    	
		getFromOtherServer(address,id,limit);
		//getFromMyServer(id,limit);
		
}

/*function renderByNews(address,limit,id){
	DWRUtil.removeAllRows("m_"+id+"_table");
	var tr = document.createElement("tr");
    var td = document.createElement("td");
    td.style.color='green';
    tr.appendChild(td);
    var oText = document.createTextNode("请稍候...");
    td.setAttribute("id","m_"+id+"_td");
    td.appendChild(oText);
    $("m_"+id+"_table").appendChild(tr);
   	
	var xmlDoc;
	var counter=0;
	
	if (window.ActiveXObject){
  		xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
 	} else if (document.implementation && document.implementation.createDocument) {
  		xmlDoc= document.implementation.createDocument("","doc",null);
 	}
 	
 	if (typeof xmlDoc!="undefined") {
  		xmlDoc.load(address);
 	} 
 	
 	var intervalID=window.setInterval(function(){
 		if(xmlDoc.readyState==4){
 			var root = xmlDoc.getElementsByTagName("channel")[0];
			var link = root.getElementsByTagName("link")[0].childNodes[0].nodeValue;
			var title = root.getElementsByTagName("title")[0].childNodes[0].nodeValue;
			var items = root.getElementsByTagName("item");
			if(items.length<limit)
				limit = items.length;
			var data = new Array();
			for(i=0;i<limit;i++){
				var item = items[i];
				var title0 = item.getElementsByTagName("title")[0].childNodes[0].nodeValue;
				var desc0 = item.getElementsByTagName("description")[0].childNodes[0].nodeValue;
				var link0 = item.getElementsByTagName("link")[0].childNodes[0].nodeValue;
				var date0 = item.getElementsByTagName("pubDate")[0].childNodes[0].nodeValue;
				data[i]={"title":title0,"desc":desc0,"link":link0,"date":date0};
			}
			userList(id,data);
			window.clearInterval(intervalID);
		}
		counter++;
		if(counter==10) {
	    	$("m_"+id+"_td").innerHTML = "请稍候...是否太慢？<a href='#' onclick=getFromServer('"+id+"','"+limit+"');>从本站服务器取得数据</a>";
		}
		if(counter==120) {
			window.clearInterval(intervalID);
			if($("m_"+id+"_td")!=null) {
				$("m_"+id+"_td").style.color='red';
		    	$("m_"+id+"_td").innerHTML = "超时或暂时不可用.<a href='#' onclick=getFromServer('"+id+"','"+limit+"');>从本站服务器取得数据</a>";
	    	}
		}
 	},500);   
 	
}*/

function getFromOwnServer(id,limit){
	if($("m_"+id+"_td")!=null)
		$("m_"+id+"_td").innerHTML = "请稍候...";
	RssReader.rssshow(id,limit, {
		callback:function(data) {
			callbackrssshow(id,data);
		},
		timeout:8000,
		errorHandler:function(errorString){
			$("m_"+id+"_td").style.color='red';
		    $("m_"+id+"_td").innerHTML = "对不起，出现错误！"+errorString;
		}
	}
	);
}

function callbackrssshow(id,data){
	if(data==null) {
		DWRUtil.removeAllRows("m_"+id+"_table");
		var tr = document.createElement("tr");
	    var td = document.createElement("td");
	    td.style.color='red';
	    tr.appendChild(td);
	    var oText = document.createTextNode("对不起，出现错误！");
	    td.appendChild(oText);
    	$("m_"+id+"_table").appendChild(tr);
	} else if (data.length==0) {
		DWRUtil.removeAllRows("m_"+id+"_table");
		var tr = document.createElement("tr");
	    var td = document.createElement("td");
	    td.style.color='green';
	    tr.appendChild(td);
	    var oText = document.createTextNode("暂无内容。");
	    td.appendChild(oText);
    	$("m_"+id+"_table").appendChild(tr);
	} else
		userList(id,data);
}

function getFromOtherServer(address,id,limit) {
	var counter=0;
	var intervalID=window.setInterval(function(){
		counter++;
		if(counter==2&& $("m_"+id+"_td")!=null) {
	    	$("m_"+id+"_td").innerHTML = "请稍候...是否太慢？<a href='#' onclick=getFromOwnServer('"+id+"','"+limit+"');>从本站服务器取得数据</a>";
		}
		else if(counter>=60) {
			window.clearInterval(intervalID);
			if($("m_"+id+"_td")!=null) {
				$("m_"+id+"_td").style.color='red';
		    	$("m_"+id+"_td").innerHTML = "超时或暂时不可用.<a href='#' onclick=getFromOwnServer('"+id+"','"+limit+"');>从本站服务器取得数据</a>";
	    	}
		}
	},1000);
	RssReader.rssshow2(address,id,limit, {
		callback:function(data) {
			callbackrssshow(id,data);
		},
		errorHandler:function(errorString){
			if($("m_"+id+"_td")!=null) {
				$("m_"+id+"_td").style.color='red';
			    $("m_"+id+"_td").innerHTML = "对不起，出现错误！"+errorString;
		    }
		}
	}
	);
}


