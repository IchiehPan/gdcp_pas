<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.gdcp.common.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript"  type="text/javascript" src="<%=request.getContextPath()%>/js/role.js" charset="GBK"></script>
<script type="text/javascript">
function ChkClick(sonName, cbAllId) {
	var arrSon = document.getElementsByName(sonName);
	var cbAll = document.getElementById(cbAllId);
	
	var functionGroupId = new Array();
	functionGroupId=<%=request.getAttribute("functionIdList")%>;
	var tempState = cbAll.checked;
	for (i = 0; i < arrSon.length; i++) {
		for(j=0;j<functionGroupId.length;j++){
			
			if (arrSon[i].value == functionGroupId[j])
				arrSon[i].click();
			
		}
		
	}
}


function updataFunction(){
	var checks = myform.chkSon;
	var userFunctionGroupTypeId = new Array();
	for (i = 0; i < checks.length; i++) {
		var obj = checks[i];

		if (obj.checked == true) {
			userFunctionGroupTypeId[i] = obj.value;
		}
	}

	var checks2 = myform.chkSon2;
	var baseFunctionGroupTypeId = new Array();
	for (i = 0; i < checks2.length; i++) {
		var obj = checks2[i];

		if (obj.checked == true) {
			baseFunctionGroupTypeId[i] = obj.value;
		}
	}

	var checks3 = myform.chkSon3;
	var messageFunctionGroupTypeId = new Array();
	for (i = 0; i < checks3.length; i++) {
		var obj = checks3[i];

		if (obj.checked == true) {
			messageFunctionGroupTypeId[i] = obj.value;
		}
	}

	if (!confirm("确认要修改吗？")) {
		window.event.returnValue = false;
		return false;
	}

	myform.action = "role_trueupdate.action?userFunctionList="
			+ userFunctionGroupTypeId + "&baseFunctionList="
			+ baseFunctionGroupTypeId + "&messageFunctionList="
			+ messageFunctionGroupTypeId;

	myform.submit();

}



</script>

</head>
<body onload="ChkClick('chkSon','chkAll');ChkClick('chkSon2','chkAll2');ChkClick('chkSon3','chkAll3')">
<form name="myform" action="role_trueupdate.action" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
        <tr>
          <td width="15"><img src="<%=request.getContextPath()%>/images/index_32.gif" width="9" height="9"></td>
          <td valign="bottom" class="title">修改中心</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="td_title">用户角色修改</td>
  </tr>
</table>
<table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">

<tr>
       <input name="roleId" type="text" style="display:none" style="background-color:#E0E0E0"  class="input2" size="90" value="<%=request.getAttribute("roleId") %>" readonly="readonly"></td>
  </tr>
  
 <tr>
    <td width="90" align="right" class="td_form01">用户角色</td>
    <td class="td_form02"><input name="roleName" type="text" class="input" id="textName" size="90" value="<%if(request.getAttribute("roleName")==null){
}else{%><%=request.getAttribute("roleName").toString().trim() %><% };%>"></td>
</tr>
  <tr>
    <td align="right" class="td_form01">备注<br></td>
    <td class="td_form02"><textarea name="remark" onpropertychange="if(value.length>400) value=value.substr(0,400)" cols="90" rows="8"><%if(request.getAttribute("remark")==null){}else{%><%=request.getAttribute("remark").toString() %><% };%></textarea></td>
  </tr>
</table>
<br>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
	
			<tr>
				<td colspan="4">
					<%=FunctionListUtil.rightsCheckboxHtml()%>
					<br>
				</td>
			</tr>
	
  <tr>
    <td align="center"><input name="UpdataUserLevtel" type="button" class="buttonface" onClick="updataFunction()" value="修改" >
    <input type="button" name="Submit" onclick="javascript:history.back(-1);" class="buttonface" value="返回">
         </td>
  </tr>
</table>
<br>
</form>
</body>
</html>
