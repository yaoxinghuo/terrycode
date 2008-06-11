<%@ page contentType="text/html; charset=UTF-8"%>				
				<div class=modbox style="position: relative">
				<h2 class=modtitle>
				<table class=mhdr cellspacing=0 cellpadding=0>
					<tr>
						<td id=s_1_h class=mttl><a class=mttli id=s_1_url
							href="http://news.google.com/news?hl=zh-CN"
							style="color: #aa0033;">Search</a>
					    </td>
					</tr>
					<tr>
					  <td bgcolor="#EEF3F8" >
		               <a href="#" style="CURSOR: hand"  onclick="secBoard(1)"><b>google</b></a>
					   <a href="#" style="CURSOR: hand"  onclick="secBoard(2)"><b>baidu</b></a>
					   <a href="#" style="CURSOR: hand"  onclick="secBoard(3)"><b>yahoo</b></a>
					   <a href="#" style="CURSOR: hand"  onclick="secBoard(4)"><b>s</b></a>				
					  </td>
					</tr>
					<SCRIPT>
					
					function secBoard(n)
					{
					
						for(i=1;i<5;i++)
							document.getElementById("td0"+i).style.display='none';
						document.getElementById("td0"+n).style.display='block';

					}
					function qs(va)
					{
					  document.gform.str.value = va;

					}
					
					
					function gss(){
					
					  if(document.getElementById('gform').str.value == ''){
					     document.getElementById('gform').str.value = 1;
					  }
					  if(document.getElementById('gform').str.value == 1){
					    gform.action = "http://www.google.cn/search?ie=gb&oe=utf8&hl=zh-CN&q="+ document.getElementById('gform').q.value;
					  }else if(document.getElementById('gform').str.value == 2){
					    gform.action = "http://images.google.cn/images?ie=gb&oe=utf8&hl=zh-CN&q="+ document.getElementById('gform').q.value;
					  }else if(document.getElementById('gform').str.value == 3){
					  	//alert(encodeURIComponent(document.getElementById('gform').q.value));
					    gform.action = "http://news.google.cn/news?ie=gb&oe=utf8&hl=zh-CN&ned=ccn&q="+ encodeURIComponent(document.getElementById('gform').q.value);
					  }else if(document.getElementById('gform').str.value == 4){
					    alert(decodeURIComponent(encodeURI(document.getElementById('gform').q.value)));
					    gform.action = "http://video.google.cn/videosearch?ie=gb&oe=utf8&hl=zh-CN&q="+ decodeURIComponent(encodeURI(document.getElementById('gform').q.value));
					  }else{
					    gform.action = "http://ditu.google.cn/maps?ie=gb&oe=utf8&hl=zh-CN&q="+ document.getElementById('gform').q.value;
					  }
					    gform.submit();
					}
					
					
					function goSearch(){

						if(!haoValidate(1))
						{
						  return false;
						}else{
						  return false;
						}
					}
					
					function trimt(str){
						return str.replace(/(^\s*)|(\s*$)/g, "");
					}
					
					function haoValidate(source){  
					wval = trimt(f.word.value);
					if (f.sc.value=="post")
					{ 
					    f.kw.value=f.word.value
						f.ct.value=""
						f.tn.value=""
						f.rn.value=""
						f.pn.value=""
						f.lm.value=""
						f.rs2.value=0
						f.myselectvalue.value=1
						f.ie.value=""
						f.cl.value=""
						f.z.value=""
						f.from.value=""		
					  }
					if (f.sc.value=="img")
					{
						f.ct.value=201326592
						f.lm.value=-1
						f.cl.value=2
						f.tn.value="baiduimage"
						f.pv.value=""
						f.rs2.value=""
						f.myselectvalue.value=""
						f.ie.value=""
						f.z.value=0
						f.rn.value=""
						f.pn.value=""
						f.from.value=""
						f.f.value=""
						}
					if (f.sc.value=="zhidao")
					{
						f.ct.value=17
						f.tn.value="ikaslist"
						f.pn.value=0
						f.rn.value=10
						f.lm.value=""
						f.cl.value=""
						f.ie.value=""
						f.rs2.value=""
						f.f.value=""
						f.myselectvalue.value=""
						f.z.value=""
						f.from.value=""
						}
					if (f.sc.value=="web")
					{
					    f.cl.value=3
						f.tn.value="ipages"
						f.ct.value=0
						f.ie.value="gb2312"
						f.myselectvalue.value=""
						f.rn.value=""
						f.rs2.value=""
						f.pn.value=""
						f.z.value=""
						f.from.value=""
						f.lm.value=""
						}
					if (f.sc.value=="mp3")
					{
					    f.ct.value=134217728
						f.lm.value=-1
						f.rn.value=""
						f.cl.value=""
						f.tn.value="baidump3"
						f.f.value="ms"
						f.myselectvalue.value=""
						f.pn.value=""
						f.ie.value="gb2312"
						f.z.value=""
						f.from.value=""
						}	
					   return true;    
				    }
					
					
					function haoSubSearch(sc){
					//changePage(sc);
					if(sc != "baidu"){
						if(sc == "post")
						{			
						    f.action = "http://post.baidu.com/f";
	
						}
						else if(sc == "zhidao")
						{
							f.action = "http://zhidao.baidu.com/q";

						}
						else if(sc == "mp3")
						{
							f.action = "http://mp3.baidu.com/m";

						}
						else if(sc == "img")
						{
							f.action = "http://image.baidu.com/i";

						}
						else if(sc == "Video")
						{
							f.action = "http://video.baidu.com/v";

						}
						else 
						{
							f.action = "http://www.baidu.com/s";

						}
						f.sc.value = sc;
					}
					else{
						f.sc.value = "post";
						haoGoSoba();
					  }
				    }
					
					function haoGoSoba(){
	
						f.action = "http://www.baidu.com/s";
						f.kw.value = f.word.value;
					}
					

					</SCRIPT>
					<tr >
					  <td>
					    <table id="td01" style="DISPLAY: block" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF3F8">
		                   <tr>
		                    <td class="l">
		                    <div id="w750">
		                     <table border=0 cellspacing=0 cellpadding=4>
								<tr>
								 <td nowrap>
									&nbsp;&nbsp;&nbsp;&nbsp;
								        <a class=active id=lt1 target=_self href="javascript:qs(1)">网页</a>
										<a id=lt3 target=_self href="javascript:qs(2)">图片</a>
										<a id=lt4 target=_self href="javascript:qs(3)">资讯</a>
										<a id=lt4 target=_self href="javascript:qs(4)">视频</a>
										<a id=lt5 target=_self href="javascript:qs(5)">地图</a>
									    <br/>
										<form id="gform" name="gform" method="get" action="" target="_blank">
										<input type="text" name="q" size="30"/>
										<input type="hidden" name="client" value="ipages"/>
										<input type="button" name="sa" value="搜 索" style="height:24px;width:55px;"  onclick="return gss()"/>
										<input type="hidden" name="str" />
									</form>
								  </td>
								 </tr>
							 </table>
		                    </div></td>
		                 </tr>
		                </table>
		                <table  id="td02" style="DISPLAY: none" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF3F8">
		                   <tr>
		                    <td class="l">
		                    <div id="w750">
                               <table border=0 cellspacing=0 cellpadding=4>
								<tr>
								 <td nowrap>
									&nbsp;&nbsp;&nbsp;&nbsp;
								        <a class=active id=lt1 target=_self href="#" onClick="haoSubSearch('web');goSearch()">网 页</a>
										<a id=lt4 target=_self href="#" onClick="haoSubSearch('post');goSearch()">贴 吧</a>
										<a id=lt4 target=_self href="#" onClick="haoSubSearch('zhidao');goSearch()">知 道</a>
										<a id=lt5 target=_self href="#" onClick="haoSubSearch('mp3');goSearch()">MP3</a>
										<a id=lt5 target=_self href="#" onClick="haoSubSearch('img');goSearch()">图 片</a>
										<a id=lt5 target=_self href="#" onClick="haoSubSearch('Video');goSearch()">视 频</a>
									    <br/>
										<form id="f" onsubmit="return haoValidate();" action="http://www.baidu.com/s" name=f target="_blank">
										<input type="text" name="word" size="30"/>
										<INPUT type=hidden name=kw>
										<INPUT type=hidden name=sc>
										<input type=hidden name=cl value=3>
										<input type=hidden name=tn value=ipages>
										<input type=hidden name=ct>
										<input type=hidden name=pn>
										<input type=hidden name=rn>
										<input type=hidden name=lm>
										<input type=hidden name=ie>
										<input type=hidden name=rs2>
										<input type=hidden name=myselectvalue>
										<input type=hidden name=f>
										<input type=hidden name=pv>
										<input type=hidden name=z>
										<input type=hidden name=from>
										<input type="submit" name="sa" value="搜 索" style="height:24px;width:55px;"/>
									</form>
								  </td>
								 </tr>
							 </table>
                            </div>
                           </td>
		                 </tr>
		                </table>
		                <table  id="td03"  style="DISPLAY: none" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF3F8">
		                   <tr>
		                    <td class="l"><div id="w750">333</div></td>
		                 </tr>
		                </table>
		                <table  id="td04" style="DISPLAY: none" width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF3F8">
		                   <tr>
		                    <td class="l"><div id="w750">444</div></td>
		                 </tr>
		                </table>
					    </td>
					</tr>
				</table>
				</h2>
				</div>
				<div class=dm></div>