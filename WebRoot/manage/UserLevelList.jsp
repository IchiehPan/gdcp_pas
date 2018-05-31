<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的成果</title>

<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript"  type="text/javascript" src="<%=request.getContextPath()%>/js/userlevel.js" charset="GBK"></script>

 </head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">用户身份列表</td>
      </tr>
    </table></td>
  </tr>
</table>
<form name="form1" onsubmit="actionFunction()" method="post" >
 <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="2">
  <tr>
    <td align="right"><input name="add" type="button" class="buttonface" value="  新 建  " onClick="gotoUrl('<%=request.getContextPath()%>/manage/UserLevelAdd.jsp')" >
      &nbsp;&nbsp;
      <input name="del" type="button" class="buttonface" value="  批量删除  " onclick="actionFunction()" >&nbsp;&nbsp;
     </td>
  </tr>
</table>

 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr> 
    <td  nowrap class="td_top"><INPUT name="chkAll" id="chkAll"  onClick="ChkAllClick('chkSon','chkAll')" type="checkbox" /></td>

    <td  nowrap class="td_top">序号</td>
    <td nowrap class="td_top">用户身份名字</td>
    <td  nowrap class="td_top">备注</td>
    <td  nowrap class="td_top" align="right">操作&nbsp;&nbsp;&nbsp;</td>
  </tr>
<c:forEach var="userlevel" items="${sessionScope.userlevlet}"  varStatus="status">
  <tr> 
    <td  nowrap class="td_02"><INPUT name="chkSon" id="${userlevel.userLevelId}" value='${userlevel.userLevelId}' type="checkbox" onclick="ChkSonClick('chkSon','chkAll')" /></td>

    <td class="td_02">${status.index+1}</td>
    <td class="td_02">${userlevel.userLevelName}</td>
    <td class="td_02"><div class="box">${userlevel.remark}</div></td>
    <td class="td_02" align="right">
		<input name="button" type="button" class="buttonface" value="修改" onClick="gotoUrl('${pageContext.request.contextPath}/userlevel_updata.action?userLevelId=${userlevel.userLevelId}&userLevelName=${userlevel.userLevelName}&remark=${userlevel.remark}');">
		<input name="button" type="button" class="buttonface" value="删除" onClick="javascript:if(deleteConfirm()) gotoUrl('${pageContext.request.contextPath}/userlevel_delete.action?userLevelId=${userlevel.userLevelId}');">

	</td>  
  </tr>
 
 </c:forEach>
 
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