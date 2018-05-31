<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.gdcp.pas.manage.vo.UserRoleVO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/check.js"></script>

<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
 
</head>
<%
	List<UserRoleVO> userRoleList = (List<UserRoleVO>) request.getAttribute("userRoleList");
%>
<body>
<script type="text/javascript">

function delcfm() {
    if (!confirm("确认要修改？")) {
        window.event.returnValue = false;
    }
}
dd</script>
<form action="userRole_updateConfi.action" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
        <tr>
          <td width="15"><img src="../../images/index_32.gif" width="9" height="9"></td>
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
    <td align="right" class="td_form01">用户名字</td>
    <td class="td_form02">
     <select style="width: 170px;" name="userName">
        <option value="<%=userRoleList.get(0).getUserName()%>">
							<%=userRoleList.get(0).getUserName()%>
							</option>
							<c:forEach var="uName" items="${requestScope.userName}">
								<option value="${uName.userName}" <c:if test="${userName eq uName.userName}">selected</c:if>>
									${uName.userName}
								</option>
							</c:forEach>
						</select>
    <!--
      <input name="userName" type="text" class="input2" size="90" value="<%=userRoleList.get(0).getUserName()%>" readOnly>
  --><input type="hidden" name="oldUserName"   value="<%=userRoleList.get(0).getUserName() %>"></td>
  </tr>
  
  <tr>
    <td width="90" align="right" class="td_form01">角色名称</td>
    <td class="td_form02">
    <select style="width: 170px;" name="roleName">
          <option value="<%=userRoleList.get(0).getRoleName()%>">
								<%=userRoleList.get(0).getRoleName()%>
							</option>
							<c:forEach var="rName" items="${requestScope.roleName}">
								<option value="${rName.roleName}" <c:if test="${roleName eq rName.roleName}">selected</c:if>>
									${rName.roleName}
								</option>
							</c:forEach>
						</select>
    
    <!--<input name="roleName" type="text" class="input" id="textName" size="90" value="<%=userRoleList.get(0).getRoleName()%>">
    
     --><input type="hidden" name="oldRoleName"   value="<%=userRoleList.get(0).getRoleName() %>">
     </td>
  </tr>
</table>
<br>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input name="UpdataDEPT" type="submit" class="buttonface" onClick="delcfm()" value="修改" >
    <input type="button" name="Submit" onclick="javascript:history.back(-1);" class="buttonface" value="返回">
    </td>
  </tr>
</table>
<br>
</form>
</body>
</html>
