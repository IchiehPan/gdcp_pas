<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript"  type="text/javascript" src="<%=request.getContextPath()%>/js/userlevel.js" charset="GBK"></script>
</head>
<body>
	<form name="myform" action="userlevel_addUserlevel.action"
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
				<td class="td_title">增添用户身份</td>
			</tr>
		</table>
		<table width="95%" border="0" align="center" cellpadding="2"
			cellspacing="0">
			<tr>
				<td width="90" align="right" class="td_form01"><font
					color="#ff0000">*</font>用户身份</td>
				<td class="td_form02"><input name="userLevelName" type="text"
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
				<td align="center"><input name="AddUserLevtel" type="submit"
					class="buttonface" value="添加" onclick="return checkForm();">
					<input type="button" name="Submit"
					onclick="javascript:history.back(-1);" class="buttonface"
					value="返回"> <input type="reset" class="buttonface">
					<!-- <input name="Submit2" type="submit" class="buttonface" value="  é¢è§  ">
    <input name="Submit22" type="submit" class="buttonface" value="  æ¸é¤  ">  -->
				</td>
			</tr>
		</table>
		<br>
	</form>
</body>
</html>
