//author  like 
// date 2008.1.3
//
	var filehtmls = new Array();
	$().ready(function(){
		var filenum;
		filenum=$("input[type='file']").length;

	$("input[type='file']").eq(0).show();//.inputfile();//;
	$("input[type='file']").each(function(i){ if(i!==0){$("input[type='file']").eq(i).hide();} });

		for (i = 0; i < filenum; i++)
		   {
			filehtmls[i] = $("input[type='file']").get(i).outerHTML;
		   $("input[type='file']").get(i).outerHTML= filehtmls[i];
		   }

	$("input[type='file']").change(function(){
	 addfile(this,$("input[type='file']").index(this));
	 });
});
function addfile(obj,index){
	
		var filenum;
		filenum=$("input[type='file']").length;

	$("span[name='filelist']").html($("span[name='filelist']").html()+obj.value+"[<a href='javascript:DELFILE("+index+");'>删除</a>]<br>");
	$("input[type='file']").eq(index).hide();
	for(i=0;i<filenum;i++)
	{
	if($("input[type='file']").get(i).value.length==0){
		$("input[type='file']").eq(i).show();//.inputfile();
		i=filenum;
		}
	}

}


		  function DELFILE(index){
	var ss,s,sss;
	s="";
	ss=$("span[name='filelist']").html().split("<BR>");
	for(i=0;i<ss.length-1 ;i++)
	{
	if(ss[i].indexOf("DELFILE("+index+")")==-1){s=s+ss[i]+"<br>";}
	}
	$("span[name='filelist']").html(s);
	$("input[type='file']").each(function(i){ if(i!==index){$("input[type='file']").eq(i).hide();} });

   $("input[type='file']").get(index).outerHTML= filehtmls[index];
	$("input[type='file']").eq(index).show();
	$("input[type='file']").eq(index).change(function(){
	 addfile(this,$("input[type='file']").index(this));	
	 });
	
	}
