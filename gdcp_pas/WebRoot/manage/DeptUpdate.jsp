<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<script type="text/javascript">
		function delcfm() {
			if (!confirm("确认要修改？")) {
				window.event.returnValue = false;
			}
		}
	</script>
	<form action="dept_updateRec.action" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="25" align="center" valign="bottom" class="td06"><table
						width="98%" border="0" cellspacing="3" cellpadding="0">
						<tr>
							<td width="15"><img src="../../images/index_32.gif"
								width="9" height="9">
							</td>
							<td valign="bottom" class="title">修改中心</td>
						</tr>
					</table></td>
			</tr>
		</table>
		<br>
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="td_title">部门修改</td>
			</tr>
		</table>
		<table width="95%" border="0" align="center" cellpadding="2"
			cellspacing="0">

			<tr>
				<input name="deptId" type="text" style="display:none" 
				class="input2" size="90" 
				value="<%=session.getAttribute("deptId")%>" readOnly="readonly">
				</td>

			</tr>

			<tr>
				<td width="90" align="right" class="td_form01">部门名字：</td>
				<td class="td_form02"><input name="deptName" type="text"
					class="input" id="textName" size="90" maxlength="40"
					value="<%if (session.getAttribute("deptName") == null) {
			} else {%><%=session.getAttribute("deptName").toString().trim()%><%}
			;%>">
					</td>
			</tr>
			
			<tr>
				<td width="90" align="right" class="td_form01">部门类型</td>
				<td class="td_form02">
				<c:choose>
				  <c:when test="${sessionScope.deptType==1 }">
				  	<input type="radio" name="deptType" value="1" checked="checked">教学部门
					<input type="radio" name="deptType" value="2">非教学部门
				  </c:when>
				  <c:otherwise>
				  	<input type="radio" name="deptType" value="1">教学部门
					<input type="radio" name="deptType" value="2" checked="checked">非教学部门
				  </c:otherwise>
				</c:choose>
				</td>
			</tr>

			<tr>
				<td align="right" class="td_form01">备注<br></td>
				<td class="td_form02"><textarea name="remark" cols="90"
						rows="8" 
						onpropertychange="if(value.length>400) value=value.substr(0,400)"><%if(session.getAttribute("remark")==null){
}else{%><%=session.getAttribute("remark").toString() %><% };%>
</textarea></td>
			</tr>
		</table>
		<br>
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center"><input name="UpdateDept" type="submit"
					class="buttonface" onClick="delcfm()" value="修改" > <input
					type="button" name="Submit" onclick="javascript:history.back(-1);"
					class="buttonface" value="返回"></td>
			</tr>
		</table>
		<br>
	</form>
</body>
</html>
