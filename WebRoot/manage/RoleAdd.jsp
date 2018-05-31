<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.gdcp.common.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript"  type="text/javascript" src="<%=request.getContextPath()%>/js/role.js" charset="GBK"></script>
<script type="text/javascript">
function addFunction() {
	if (document.myform.roleName.value.length == 0) {
		document.myform.roleName.focus;
		alert("请输入角色名");
		return false;
	} else if (contain(document.myform.roleName.value, "%()!@！#￥%*&^%?}[';'`~]……&*（）‘；、。，；")) {
		alert("输入了非法字符");
		document.myform.roleName.focus;
		return false;
	} else {

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

		if (!confirm("确认要新增吗？")) {
			window.event.returnValue = false;
			return false;
		}

		myform.action = "role_addRole.action?userFunctionList="
				+ userFunctionGroupTypeId + "&baseFunctionList="
				+ baseFunctionGroupTypeId + "&messageFunctionList="
				+ messageFunctionGroupTypeId;

		myform.submit();

	}
}


</script>

</head>
<body>
	<form name="myform" action="role_addRole.action"
		method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="25" align="center" valign="bottom" class="td06"><table
						width="98%" border="0" cellspacing="3" cellpadding="0">
						<tr>
							<td width="15"><img src="../../images/index_32.gif"
								width="9" height="9"></td>
							<td valign="bottom" class="title">新建中心</td>
						</tr>
					</table></td>
			</tr>
		</table>
		<br>
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="td_title">增添用户角色</td>
			</tr>
		</table>
		<table width="95%" border="0" align="center" cellpadding="2"
			cellspacing="0">
			<tr>
				<td width="90" align="right" class="td_form01"><font
					color="#ff0000">*</font>用户角色名</td>
				<td class="td_form02"><input name="roleName" type="text"
					class="input" size="90" maxlength="40" onblur="check(this)"><font
					color="#ff0000"><span
					id="text"></span></font></td>
			</tr>
			<tr>
				<td align="right" class="td_form01">备注<br></td>
				<td class="td_form02"><textarea
						onpropertychange="if(value.length>400) value=value.substr(0,400)"
						name="remark" cols="90" rows="8"></textarea></td>
			</tr>
		</table>
		<br>
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			
			<tr>
				<td colspan="4">
					<%=FunctionListUtil.rightsCheckboxHtml()%>
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
				<br>
				</td>
			</tr>
			
			<tr>
				<td align="center"><input name="AddRole" type="button"
					class="buttonface" value="添加" onclick="addFunction();">
					<input type="button" name="Submit"
					onclick="javascript:history.back(-1);" class="buttonface"
					value="返回"> <input type="reset" class="buttonface">
				</td>
			</tr>
		</table>
		<br>
	</form>
</body>
</html>
