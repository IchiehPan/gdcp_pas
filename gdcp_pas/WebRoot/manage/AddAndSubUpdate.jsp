<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.gdcp.pas.manage.vo.AddAndSubVO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>加减分项修改</title>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/check.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/xmlhttp.js"></script>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
</head>
<SCRIPT LANGUAGE="JavaScript">
	function changeF()  
    {  
    /*    document.getElementById('userName').value=  
       document.getElementById('username').options[document.getElementById('username').selectedIndex].value;   */
    }  
     function check(){
   	var userName=document.getElementById('userName').value;
    var url="addAndSub_queryName.action?userName="+userName;
    var strObj=postStr(url);
    var objectArr=strObj.split("@#");
	var optionStr = "";
		for(var i=0;i<objectArr.length;i++){
		optionStr = optionStr +  "<option value=\"" + objectArr[i] + "\">" +  objectArr[i]+ "</option>";
		}
		var userObj=document.getElementById("username");
		var selectStr="<select name=\"username\" id=\"username\" style=\"width:170px;height:20px;\" onChange=\"changeF();\">" + optionStr + "</select>";
		userObj.outerHTML =  selectStr;
    }
	function formsubmit(){
	var userName = form1.userName.value;
	var score = form1.score.value;
	var describe = form1.describe.value;
		if (userName == "")
			{
				alert("对象名称不能为空！");
				form1.userName.focus();
				return false;
			}
		if (score == "")
			{
				alert("分值不能为空！");
				form1.score.focus();
				return false;
			}
		if (describe == "")
			{
				alert("描述不能为空！");
				form1.describe.vfocus();
				return false;
			}
		form1.submit();
	}
</SCRIPT>
<%
	List<AddAndSubVO> addAndSubList = (List<AddAndSubVO>) request.getAttribute("addAndSubList1");
	AddAndSubVO aasVO=addAndSubList.get(0);
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="form1" action="addAndSub_AddOrSubUpdate.action"  method="post" >

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">加减分项修改</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<table align="center" width="95%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td width="95" height="24" class="td_form01"><font color="red">*</font>加减分对象名称</td>
        <td nowrap class=td_form02>
	<select name="userName" id="userName" style="width:170px;height:20px;" onChange="changeF();">  
   	 	<c:forEach var="userName" items="${requestScope.addAndSubList}">
								<option value="${userName.userName}" <c:if test="${username eq userName.userName}">selected</c:if>>
									${userName.userName}
								</option>
		</c:forEach>
	</select>  
	 <input type="hidden" name="objectId" id="objectId" value="<%=aasVO.getObjectId() %>" />
	 <input type="hidden" name="id" id="id" value="<%=aasVO.getId() %>" />
		</td>
		<tr>
		 <td width="90" height="24" class="td_form01"><font color="red">*</font>加减分项</td>
		 <td nowrap class=td_form02>
		 <input type="hidden" name="oldstatus" id="oldstatus" value="<%=aasVO.getStatus() %>" />
		 <select style="width: 170px;" name="status" id="status">
        	<%if(aasVO.getStatus()==0){%>
        	<option value="0">
								加分
			</option>
			<option value="1">
								减分
			</option>
			<option value="2">
								鼓励加分项
			</option>
        	<%}else if(aasVO.getStatus()==1){%>
        	<option value="1">
								减分
			</option>
			<option value="0">
								加分
			</option>
			<option value="2">
								鼓励加分项
			</option>
        	<%}else if(aasVO.getStatus()==2){%>
        	<option value="2">
								鼓励加分项
			</option>
        	<option value="1">
								减分
			</option>
			<option value="0">
								加分
			</option>
			
        	<%}%>
		</select>
		</tr>
		<tr>		
		 <td width="90" height="24" class="td_form01"><font color="red">*</font>分值</td>
		 <td nowrap class=td_form02>
		 <input type="text" name="score" id="score" style="width: 170px;" value="<%=aasVO.getScore()%>"/>
		 </td>
		</tr>
		<tr>
        <td width="90" class="td_form01"><font color="red">*</font>描述</td>
        <td nowrap class=td_form02>
        <textarea rows="10" cols="31" name="describe" id="describe"><%=aasVO.getDescribe() %></textarea>
      	</td>
      	</tr>
    </table>
 
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input name="Submit2" type="button" class="buttonface" value=" 修改  " onClick="formsubmit()">
     <input type="button" name="Submit" onclick="javascript:history.back(-1);" class="buttonface" value="返回">
</td>
  </tr>
</table>
<br>
</form>
</body>
</html>
