//说明： 前台页面中 数据合法性校验的公用函数
//Athour:刘伯睿
//日期：2004-8-27
/**
* 说明：
* 假定页面中如果只有一个form，则该form的名字是form1
  如果有多个form，采用getFromName(obj) 方法获得form的名字
*/



/*checkFormNoNull(formName)*/
//函数说明  参数： form的名字  功能： 检查该form内id属性不为空的控件value是否全部不为空 
//这些控件包括text,select,hidden,textarea,checkbox
// 返回值：返回值类型为bollean 如果全部不为空返回 true 否则返回 false
//Example:在提交按钮中加 onclick="return checkFormNoNull('form1')"
//注意事项： 如果为空，给出相应提示。id属性值为需要提示的内容例如： nuNull="姓名"
/**********************************************************************/

/*checkDocumentNoNull()*/
//函数说明  参数： 无  功能： 检查整个页面内noNull属性不为空的控件value是否全部不为空 
//这些控件包括text,select,hidden,textarea,checkbox
//返回值：返回值类型为bollean 如果全部不为空返回 true 否则返回 false
//Example:在提交按钮中加 onclick="return checkDocumentNoNull()"
//注意事项： 如果为空，给出相应提示。noNull属性值为需要提示的内容例如： nuNull="姓名"
/**********************************************************************/

/*checkDate(obj)*/
//函数说明  参数： 传入需要检查的控件  功能： 检查传入控件的value 是否为规定格式 YYYYMMDD
//返回值：返回值类型为bollean 如果是规定格式返回 true 否则返回false
//Example:在控件失去焦点的时候检查onblur="return checkDate(this)"
//注意事项：如果格式错误给出相应提示，并返回焦点到该控件。obj.select()
/**********************************************************************/

/*checkNum(obj,s,f)*/
//函数说明  参数： obj是传入需要检查的控件，第二个参数为数值总长度(不包括小数点)， 第三个参数为小数点后面的位数, 当第三个参数为0时,只能输入最长为第二个参数的整数。
//功能： 检查传入控件的value 是否为数值
//返回值：返回值类型为bollean 如果是规定格式返回 true 否则返回false
//Example:在控件失去焦点的时候检查onblur="return checkNum (this,10,2)"这样是检查控件的值是必须整数位小于8，小数位小于二的有理数。
//注意事项：如果格式错误给出相应提示，并返回焦点到该控件。obj.select()
/**********************************************************************/

/*checkStrLen(Obj,strLen,flag)*/
//函数说明  参数：obj是传入需要检查的控件，第二个参数为字符串总长度，第三个参数为小数点后面的位数,第三个参数为0表示字符串总长度必须为第二个参数指定的定长，否则表示字符串总长度不能超过第二个参数指定的长度
//功能： 检查传入控件的value 的字符串长度是否符合要求
//返回值：返回值类型为bollean 如果是规定格式返回 true 否则返回false
//Example:在控件失去焦点的时候onblur="return checkStrLen(this,10,0)"这样是检查控件的值是字符串长度必须是等于10。
//注意事项：如果格式错误给出相应提示，并返回焦点到该控件。obj.select()
/**********************************************************************/
/*checkSpecialCharacters(Obj)*/
//函数说明  参数：obj是传入需要检查的控件
//功能： 检查传入控件的value 的字符串是否包含<、>、'、"等特殊字符
//返回值：返回值类型为bollean 如果是规定格式返回 true 否则返回false
//Example:在控件失去焦点的时候onblur="return checkSpecialCharacters(this)"检查控件是否包含特殊字符。
//注意事项：如果格式错误给出相应提示，并返回焦点到该控件。obj.select()
/**********************************************************************/
function checkFormNoNull(formName)
{
	alert(132)
  var flag=true;
  var item = eval("document." + formName).tags("INPUT");
  var nullID ="";
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).id && (item.item(i).type=="text" || item.item(i).type=="password" || item.item(i).type=="hidden"))
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).id) + ",";
			 flag = false;
		 }
	  }
  }
  item = eval("document." + formName).tags("SELECT");
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).id)
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).id) + ",";
			 flag = false;
		 }
	  }
  }
  item = eval("document." + formName).tags("TEXTAREA");
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).id)
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).id) + ",";
			 flag = false;
		 }
	  }
  }
  if(!flag)
  {
     nullID = nullID.substr(0,nullID.length-1);
	 alert(nullID + " 不能为空！")  ;
  }
  return flag;
}
function checkNullById()
{
  var flag=true;
  var item = document.all.tags("INPUT");
  var nullID ="";
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).id && (item.item(i).type=="text" || item.item(i).type=="password" ) && (!item.item(i).disabled))
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).id) + ",";
			 flag = false;
		 }
	  }
  }
  item = document.all.tags("SELECT");
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).id)
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).id) + ",";
			 flag = false;
		 }
	  }
  }
  item = document.all.tags("TEXTAREA");
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).id)
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).id) + ",";
			 flag = false;
		 }
	  }
  }
  if(!flag)
  {
     nullID = nullID.substr(0,nullID.length-1);
	 alert(nullID + " 不能为空！")  ;
  }
  return flag;
}

function checkDocumentNoNull()
{
  var flag=true;
  var item = document.all.tags("INPUT");
  var nullID ="";
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).noNull && (item.item(i).type=="text" || item.item(i).type=="password" || item.item(i).type=="hidden"))
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).noNull) + ",";
			 flag = false;
		 }
	  }
  }
  item = document.all.tags("SELECT");
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).noNull)
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).noNull) + ",";
			 flag = false;
		 }
	  }
  }
  item = document.all.tags("TEXTAREA");
  for (var i=0;i<item.length ;i++ )
  {
	  if(item.item(i).noNull)
	  {
 	     if(trim(item.item(i).value)=="")
		 {
 			 //alert(item.item(i).id + "不能为空！");
			 nullID = nullID + trim(item.item(i).noNull) + ",";
			 flag = false;
		 }
	  }
  }
  if(!flag)
  {
     nullID = nullID.substr(0,nullID.length-1);
	 alert(nullID + " 不能为空！")  ;
  }
  return flag;
}


function checkDate(obj)
{
    var thisdate=trim(obj.value);
    var str="格式错误!\n正确的格式如：\n20030908";
    //var reg = /^(\d{4})-(\d{2})-(\d{2})$/;//正则表达式
    var reg = /^(\d{4})(\d{2})(\d{2})$/;//正则表达式
   reg.exec(thisdate);
    if(thisdate=="") return true;
    if(reg.test(thisdate)&&RegExp.$2<=12&&RegExp.$3<=31)
        return true;
    else
    {
        alert(str);
        obj.select();
        return false;
    }
}
function checkTime(obj)
{
   var thisTime = trim(obj.value);
   if (thisTime == "")
   {
	   return true;
   }

   var str="格式错误!\n正确的格式如：\n12:05:20";
  
   if(thisTime.length != 8)
   {
	  alert(str);
	  obj.select();
	  return false;
   }
   if (thisTime.indexOf(".") != -1)
   {
	  alert(str);
	  obj.select();
	  return false;
   }
   var arr = thisTime.split(":");
   if(arr.length != 3)
   {
	  alert(str);
	  obj.select();
	  return false;
   }
   if(parseInt(arr[0])>23 ||  parseInt(arr[0]) < 0 ||( isNaN(parseInt(arr[0]))))
   {
	  alert(str);
	  obj.select();
	  return false;
   }
   if(parseInt(arr[1])>59 ||  parseInt(arr[1]) < 0 ||( isNaN(parseInt(arr[1]))))
   {
	  alert(str);
	  obj.select();
	  return false;
   }
   if(parseInt(arr[2])>59 ||  parseInt(arr[2]) < 0 ||( isNaN(parseInt(arr[2]))))
   {
	  alert(str);
	  obj.select();
	  return false;
   }
   return true;

}
function checkNumX(Obj,s,f,max,min)
{

    var thisnum=trim(Obj.value);
    if(thisnum=="") return true;
    var n=s-f;//整数位数
    var i;
    if(isNaN(thisnum)!=true)//当输入为数值
    {
			  if(arguments[5] != true){
					if(parseFloat(thisnum) >max)
					{
						alert("值不能大于" + max);
						Obj.select();
						return false;
					}
					if(parseFloat(thisnum) < min)
					{
						alert("值不能小于" + min);
						Obj.select();
						return false;
					}
				}else{
				
					if(parseFloat(thisnum) >=max)
					{
						alert("值不能大于等于" + max);
						Obj.select();
						return false;
					}
					if(parseFloat(thisnum) <= min)
					{
						alert("值不能小于等于" + min);
						Obj.select();
						return false;
					}
			
				}
        if(f>0)
        {
            i=thisnum.indexOf(".");
            if(i==-1)//当找不到小数,可以输入整数
            {
                if(thisnum.length>n)
                {
                    Obj.select();
                    alert("输入的 整数位 应小于"+n+"位，请重输！");
                    return false;
                }
                else return true;
            }

            if(i==0 || i==thisnum.length-1)
            {
                Obj.select();
                alert("输入错误,请重输!");
                return false;
            }

            if(i>0)
            {
                var floatnum=thisnum.substr(i+1);//小数部分
                var plusnum=thisnum.length-floatnum.length-1;//整数部分
                if(plusnum>n)
                {
                    Obj.select();
                    alert("输入的整数位应小于或等于"+n+"位，请重输！");
                    return false;
                }
                if (floatnum.length>f)
                {
                    Obj.select();
                    alert("输入的小数位应小于或等于"+f+"位，请重输！");
                    return false;
                }
            }

        }

        if(f==0)
        {
            i=thisnum.indexOf(".");
            if(i!=-1)
            {
                Obj.select();
                alert("只能输入整数，且小于或等于"+n+"位，请重输！");
                return false;
            }

            if(thisnum.length>n)
            {
                Obj.select();
                alert("只能输入整数，且小于或等于"+n+"位，请重输！");
                return false;
            }
        }

        return true;
    }
    else
    {
        Obj.select();
        alert("请输入一个数值！");
        return false;
    }

}
function checkNum(Obj,s,f)
{

    var thisnum=trim(Obj.value);
    if(thisnum=="") return true;
    var n=s-f;//整数位数
    var i;
    if(isNaN(thisnum)!=true)//当输入为数值
    {
        if(f>0)
        {
            i=thisnum.indexOf(".");
            if(i==-1)//当找不到小数,可以输入整数
            {
                if(thisnum.length>n)
                {
                    Obj.select();
                    alert("输入的 整数位 应小于"+n+"位，请重输！");
                    return false;
                }
                else return true;
            }

            if(i==0 || i==thisnum.length-1)
            {
                Obj.select();
                alert("输入错误,请重输!");
                return false;
            }

            if(i>0)
            {
                var floatnum=thisnum.substr(i+1);//小数部分
                var plusnum=thisnum.length-floatnum.length-1;//整数部分
                if(plusnum>n)
                {
                    Obj.select();
                    alert("输入的整数位应小于或等于"+n+"位，请重输！");
                    return false;
                }
                if (floatnum.length>f)
                {
                    Obj.select();
                    alert("输入的小数位应小于或等于"+f+"位，请重输！");
                    return false;
                }
            }

        }

        if(f==0)
        {
            i=thisnum.indexOf(".");
            if(i!=-1)
            {
                Obj.select();
                alert("只能输入整数，且小于或等于"+n+"位，请重输！");
                return false;
            }

            if(thisnum.length>n)
            {
                Obj.select();
                alert("只能输入整数，且小于或等于"+n+"位，请重输！");
                return false;
            }
        }

        return true;
    }
    else
    {
        Obj.select();
        alert("请输入一个数值！");
        return false;
    }

}

function checkStrLen(Obj,strLen,flag)
{
    var str = new String(Obj.value);
    if(str==""){
        return true;
    }
    if (flag==0 && getByte(str) != strLen){
        alert("只能输入"+strLen+"位字符(一个汉字为两位长度)。");
        Obj.select();
        return false;
    }
    if (getByte(str) > strLen)
    {
        alert("输入值长度不能超过"+strLen+"位(一个汉字为两位长度)。");
        Obj.select();
        return false;
    }
	return true;
}
//得到字符串的字节数，如：getByte("test测试")返回8
function getByte(s)
{
    var intCount = 0;
    for(var i = 0;i < s.length;i ++)
    {
        // Ascii码大于255是双字节的字符
        if(s.charCodeAt(i) > 255)intCount += 2;
        else intCount += 1;
    }
    return intCount;
}
function checkIsValidDate(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    var pattern = /^((\d{4})|(\d{2}))-(\d{1,2})-(\d{1,2})$/g;
    if(!pattern.test(str))
        return false;
    var arrDate = str.split("-");
    if(parseInt(arrDate[0],10) < 100)
        arrDate[0] = 2000 + parseInt(arrDate[0],10) + "";
    var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
    if(date.getYear() == arrDate[0]
       && date.getMonth() == (parseInt(arrDate[1],10) -1)+""
       && date.getDate() == arrDate[2])
        return true;
    else
        return false;
}
function checkSpecialCharacters(obj){
	var str=obj.value;
	if(str.indexOf('>')!=-1||str.indexOf('<')!=-1
			||str.indexOf('\'')!=-1||str.indexOf("\"")!=-1){
		 alert("不能输入(<、>、\'、\")等特殊字符");
          obj.select();
          return false;
	}
}

