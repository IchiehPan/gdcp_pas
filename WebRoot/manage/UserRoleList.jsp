<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.gdcp.pas.manage.vo.UserRoleVO" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>我的成果</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
 <script type="text/javascript">
function actionFunction(){	
	var checks = form1.chkSon;
	var checks1=form1.chkSon1;
	var groupTypeId= new Array();
	var groupTypeId1= new Array();
	for(i=0; i<checks.length; i++){
	    var obj = checks[i];
	    var obj1=checks1[i];
	    if(obj.checked == true){
	    	groupTypeId[i]= obj.value;
	    	groupTypeId1[i]=obj1.value;
	    }
	}
	
	 if (!confirm("您确认要删除？")) {
	        window.event.returnValue = false;
	        return false;
	    }
		 
	 form1.action="userRole_deleteList.action?Arr="+groupTypeId+","+groupTypeId1;
	 form1.submit();
	 
	 }



function ChkAllClick(sonName, cbAllId){
	 var arrSon = document.getElementsByName(sonName);
	 var cbAll = document.getElementById(cbAllId);
	 var tempState=cbAll.checked;
	 for(i=0;i<arrSon.length;i++) {
	  if(arrSon[i].checked!=tempState)
	           arrSon[i].click();
	 }
	}

	// --子项复选框被单击---
	function ChkSonClick(sonName, cbAllId) {
	 var arrSon = document.getElementsByName(sonName);
	 var cbAll = document.getElementById(cbAllId);
	 for(var i=0; i<arrSon.length; i++) {
	     if(!arrSon[i].checked) {
	     cbAll.checked = false;
	     return;
	     }
	 }
	 cbAll.checked = true;
	}

	// --反选被单击---
	function ChkOppClick(sonName){
	 var arrSon = document.getElementsByName(sonName);
	 for(i=0;i<arrSon.length;i++) {
	  arrSon[i].click();
	 }
	}

</script>
</head>
<%
	List<UserRoleVO> userRoleList = (List<UserRoleVO>) request.getAttribute("userRoleList");
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">用户角色列表
        </td>
      </tr>
    </table></td>
  </tr>
</table>
<form name="form1" onsubmit="actionFunction()" method="post">
 <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="2">
  <tr>
    <td align="right"><input name="add" type="button" class="buttonface" value="  新 建  " onClick="gotoUrl('<%=request.getContextPath()%>/userRole_queryUserRole.action')" >
      <input name="del" type="button" class="buttonface" value="  批量删除  "
					onclick="actionFunction()">&nbsp;&nbsp;</td>&nbsp;&nbsp;
     <td></td>
  </tr>
</table>

 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr> 
    <td  nowrap class="td_top"><INPUT name="chkAll" id="chkAll"
					onClick="ChkAllClick('chkSon','chkAll')" type="checkbox" />
					</td>
	<td nowrap class="td_top"></td>
    <td nowrap class="td_top">用户名字</td>
    <td  nowrap class="td_top">角色名字</td>
    <td  nowrap class="td_top" align="right">操作&nbsp;&nbsp;&nbsp;</td>
  </tr>
  <%
	for(int i=0;i<userRoleList.size();i++){
		UserRoleVO userRoleVO = userRoleList.get(i);
	
  %>
  <tr> 
    <td  nowrap class="td_02"><INPUT name="chkSon"
						id="<%=userRoleVO.getUserName()%>" value='<%=userRoleVO.getUserName() %>' type="checkbox"
						onclick="ChkSonClick('chkSon','chkAll')" />
						<input type="hidden" name="chkSon1" id="<%=userRoleVO.getRoleName()%>" value="<%=userRoleVO.getRoleName() %>" >
						</td>
	<td class="td_02"></td>
    <td class="td_02"><%=userRoleVO.getUserName()%></td>
    <td class="td_02"><%=userRoleVO.getRoleName()%></td>
    <td class="td_02" align="right">
		<input name="button" type="button" class="buttonface" value="修改" onClick="gotoUrl('<%=request.getContextPath()%>/userRole_update.action?roleName=<%=userRoleVO.getRoleName()%>&&userName=<%=userRoleVO.getUserName() %>');">
		<input name="button" type="button" class="buttonface" value="删除" onClick="javascript:if(deleteConfirm()) gotoUrl('<%=request.getContextPath()%>/userRole_deleteRec.action?roleName=<%=userRoleVO.getRoleName()%>&&userName=<%=userRoleVO.getUserName() %>');">
	</td>  
  </tr>
  <%
		}		
  %>
 
  <tr> 
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
  </tr>
  <tr> 
    <td class="td_02">&nbsp;</td>
	<td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
  </tr>
  <tr> 
        <td class="td_02">&nbsp;</td>
<td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
  </tr>
  <tr> 
        <td class="td_02">&nbsp;</td>
<td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
  </tr>
  <tr> 
       <td class="td_02">&nbsp;</td>
 <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
  </tr>
  <tr> 
        <td class="td_02">&nbsp;</td>
<td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
  </tr>
  <tr> 
        <td class="td_02">&nbsp;</td>
<td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
    <td class="td_02">&nbsp;</td>
  </tr>
</table>
<table width="95%"  border="0" cellpadding="0" cellspacing="0" class="table02" align="center">
  <tr>
    <td height="30" align="right"><img src="<%=request.getContextPath()%>/images/1.gif" width="4" height="5" align="absmiddle"> 首页　 <img src="<%=request.getContextPath()%>/images/2.gif" width="3" height="5" align="absmiddle"> 上一页　 <img src="<%=request.getContextPath()%>/images/2-2.gif" width="3" height="5" align="absmiddle"> 下一页　 <img src="<%=request.getContextPath()%>/images/3.gif" width="4" height="5" align="absmiddle"> 末页　　共 1 页 1 条记录</td>
  </tr>
</table>
</form>
</body>
</html>