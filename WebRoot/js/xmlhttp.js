//说明：公共脚本文件，主要提执行页面跳转功能。
//Athour:刘伯睿
//日期：2007-9-27
//入口函数  postXml  说明:以xml方式提交，获得xml返回
//入口函数  postXmlX  说明:以xml方式提交，获得字符串返回
//入口函数  postStrX  说明:以字符串方式提交，获得xml返回
//入口函数  postStr  说明:以字符串方式提交，获得字符串返回

//以xml方式提交，获得xml返回
function postXml(xmlDoc,url)
{
	var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.Open("POST",url,false);
	xmlhttp.Send(xmlDoc);
	return xmlhttp.responseXML;
}
//以xml方式提交，获得字符串返回
function postXmlX(xmlDoc,url)
{
	var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	xmlhttp.Open("POST",url,false);
	xmlhttp.Send(xmlDoc);
   if(xmlhttp.status == "200")
      back=xmlhttp.responseText;
   else
      back="";
   xmlhttp = null;
   delete xmlhttp;
   return back;
}
//以字符串方式提交，获得xml返回
function postStrX(url)
{
   var back;
   var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
   xmlhttp.open("POST",url,false);
   xmlhttp.send();
   back=xmlhttp.responseXML;
   xmlhttp = null;
   delete xmlhttp;
   return back;
}

//以字符串方式提交，获得字符串返回
function postStr(url)
{
   var back;
   var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
   xmlhttp.open("POST",url,false);
   xmlhttp.send();
   if(xmlhttp.status == "200")
      back=xmlhttp.responseText;
   else
      back="";
   xmlhttp = null;
   delete xmlhttp;
   return back;
}



function public_Init_keepTrack(str)// 判断iframe 是否完全下载完成,如果没有等待10毫秒,递归
{
  try
  {
   if (document.readyState == "complete")
     {
	    eval(str);
     }
   else 
     {
        setTimeout("public_Init_keepTrack('"+str+"')",100);
     }
  }
  catch(e)
  {
     alert("递归错误,主文档没有下载完全!");
  }
}