//说明：公共脚本文件，主要提供一些公用脚本函数,执行页面跳转功能。
//Athour:刘伯睿
//日期：2004-1-6


//分页跳转函数
function goto(pageNo){
    document.form1.pageNo.value=pageNo;
    form1.submit();
}

//跳转到url所指的文件
function gotoUrl(url){
   window.location.href = url;

}

//submit到url所指的文件
function submitToUrl(url){
    document.form1.action = url ;
    document.form1.submit() ;
}

function checkAll(checkboxName,obj){
	var flag = obj.checked;
	objArr = document.getElementsByName(checkboxName);
	if(objArr!=null){
		for(var i=0;i<objArr.length;i++){
			objArr[i].checked = flag;
		}
	}
}

/**
 * 在屏幕中间打开新窗口
 * @param {} url
 * @param {} name
 * @param {} width
 * @param {} height
 */
function openWin(url, name, width, height, scrollbars, resizable) {
	var lpos = (screen.availWidth - width) / 2;
	var tpos = (screen.availHeight - height) / 2;
	if(!resizable) {
		resizable = "yes";
	}	
	var feature = "resizable=" + resizable + ",width=" + width + ",height=" + height + ",left=" + lpos + ",top=" + tpos;
	if(scrollbars) {
		feature += ",scrollbars=yes";
	}
	window.open(url, name, feature);
}
/**
 * 打开全屏窗口
 * @param {} name
 * @param {} url
 */
function openFullWin(url, name, scrollbars) {
	var width = screen.availWidth - 10;
	var height = screen.availHeight - 35;
	var feature = "left=0,top=0,width=" + width + ",height=" + height;
	if(scrollbars) {
		feature += ",scrollbars=yes";
	}
	window.open(url, name, feature);
}


function deleteSingle(checkboxName,url){
	if(deleteConfirm()){
		objArr = document.getElementsByName(checkboxName);
		for(var i=0;i<objArr.length;i++){
			objArr[i].checked = false;
		}
		gotoUrl(url);
	}
 }

//删除确认，如果确认则跳转到url
function deleteConfirm(){
    if (confirm('你确定要删除吗?')){
        //window.open(url,"_self");
        //submitToUrl(url) ;
        return true;
    }else {
        return false;
    }
}

function deleteSelected(url){
	if(deleteConfirm()){
		gotoUrl(url);
	}
 }
/*function deleteConfirm(url){
    if (confirm('你确定要删除吗?')){
        //window.open(url,"_self");
        submitAll(url) ;
    }else {
        return false;
    }
}*/
//修改确认
function updateConfirm(){
    if (confirm('你确定要修改吗?')){
        return true;
    }else {
        return false;
    }
}
function updateConfirm(url){

        submitSingal(url) ;

}

//树形结构的复选框选中取消功能，即选中取消父checkbox，则其子checkbox也相应的选中取消
function checkboxTree(){
    source = event.srcElement;
	chkboxID = source.id;

    var iT = document.all.tags("INPUT");

    for (i=0;i<iT.length;i++){
      if (iT(i).id.substring(1,chkboxID.length)==chkboxID.substring(1,chkboxID.length)){
        iT(i).checked = source.checked;
      }
    }
  }

//打开模式对话框，主要提供给查询记录细节情况使用。
function showModalDialogDetail(url,arg,flag)
{
  if(flag=="1")
  {
   returnValue = window.showModalDialog(url,arg,"dialogLeft:5em\;dialogWidth:51em\;dialogHeight:27em\;center:yes\;help:no\;resizable:yes\;status:no"); 
  //window.open(url)
   return returnValue;
  }
}
function openSelect(url)
{
  returnValue = showModalDialogDetail(url,"",1);
  return returnValue;
}


function selectChange(item,itemChange)
{
    if(item.options[item.selectedIndex].value=="")
	{
	  itemChange.disabled= true;
      itemChange.value= "";
	}
	else
	  itemChange.disabled= "";



}

function addRec(url)
{
   if(checkNoNull())
   {
      gotoUrl(url)
   }
}
function updateRec(url)
{
   if(checkNoNull())
   {   
	  if (confirm('你确定要修改吗?')){
         gotoUrl(url)
      }else {
        return false;
      }
   }
}
function initFocus(name)
{ 
  try{
    var obj = eval("form1." + name);
    obj.focus();	
  }catch(e){}


}
function setClass(obj,classValue)
{
  obj.className = classValue;
  return true;
}
function resetListPage()
{
  var obj = document.all.tags("INPUT");
  for (var i=0;i<obj.length ;i++ )
  {
	  try
	  {
	    if(obj.item(i).type="TEXT")
	    {
	   	  obj.item(i).value = "";
	    }
	  }
	  catch(e){}
  }
  obj = document.all.tags("SELECT");
  for (var i=0;i<obj.length ;i++ )
  {
	  try
	  {
	   	  obj.item(i).options[0].selected = true;
	  }
	  catch(e){}
  }
}

function trim(str)
{
  var strArr = str.split("");
  var str1="";
  
  //alert(strArr)
  flag1=false;
  for (var i=0;i<strArr.length;i++)
  {
    if(strArr[i] != " ")
	{
	   flag1 = true;
	}
	if(flag1)
	{
	  str1 = str1+strArr[i];
	}
  }
  strArr = str1.split("");
  flag1=false;
  str1="";
  for (var i=strArr.length-1;i>-1;i--)
  {
    if(strArr[i] != " ")
	{
	   flag1 = true;
	}
	if(flag1)
	{
	  str1 = strArr[i]+str1;
	}
  }
  return str1;
}