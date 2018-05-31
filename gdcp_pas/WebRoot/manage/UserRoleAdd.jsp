<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.gdcp.pas.manage.vo.UserRoleVO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户角色增加</title>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/check.js"></script>

<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
</head>
<SCRIPT LANGUAGE="JavaScript">
function formsubmit(){
		form1.submit();
}
</SCRIPT>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="form1" action="userRole_insertRec.action"  method="post" >

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">用户角色增加</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<table align="center" width="95%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="90" height="24" class="td_form01"><font color="red">*</font>用户名字</td>
        <td nowrap class=td_form02>
        <select style="width: 170px;" name="userName">
        <option value="-1">
								请选择用户名字
							</option>
							<c:forEach var="uName" items="${requestScope.userName}">
								<option value="${uName.userName}" <c:if test="${userName eq uName.userName}">selected</c:if>>
									${uName.userName}
								</option>
							</c:forEach>
						</select><!--
			<input name="userName" onblur="checkStrLen(this,20,1)" id="角色Id" type="text" class="input">
		--></td>
        <td width="90" class="td_form01"><font color="red">*</font>角色名称</td>
        <td nowrap class=td_form02>
          <select style="width: 170px;" name="roleName">
          <option value="-1">
								请选择角色名字
							</option>
							<c:forEach var="rName" items="${requestScope.roleName}">
								<option value="${rName.roleName}" <c:if test="${roleName eq rName.roleName}">selected</c:if>>
									${rName.roleName}
								</option>
							</c:forEach>
						</select>
        
        <!--
			<input name="roleName"  onblur="checkStrLen(this,20,1)" id="角色名字" type="text" class="input">
		--></td>
      </tr>
       
    </table>
 
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input name="Submit2" type="button" class="buttonface" value="  提交  " onClick="formsubmit()">
     <input type="button" name="Submit" onclick="javascript:history.back(-1);" class="buttonface" value="返回">
</td>
  </tr>
</table>
<br>
</form>
</body>
</html>
