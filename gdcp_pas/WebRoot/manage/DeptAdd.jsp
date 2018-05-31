<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript"  type="text/javascript" src="<%=request.getContextPath()%>/js/dept.js" charset="GBK"></script>
</head>
<body>

<form name="myform" action="dept_insertRec.action" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
        <tr>
          <td width="15"><img src="../../images/index_32.gif" width="9" height="9"></td>
          <td valign="bottom" class="title">添加部门板块</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td class="td_title">添加新部门</td>
  </tr>
</table>
<table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">
  <tr>
    <td width="90" align="right" class="td_form01">部门名字</td>
    <td class="td_form02"><input name="deptName" type="text" class="input"  size="75" maxlength="40" onblur="check(this)" ><font color="#ff0000">*</font><span id="text"></span></td>
  </tr>
   
   <tr>
    <td width="90" align="right" class="td_form01">部门类型</td>
    <td class="td_form02">	
		<input type="radio" name="deptType" value="1">教学部门
		<input type="radio" name="deptType" value="2">非教学部门
	</td>
  </tr>
  
  <tr>
    <td align="right" class="td_form01">备注<br></td>
    <td class="td_form02"><textarea name="remark" cols="75" rows="8" maxlength="400"
						onchange="this.value=this.value.substring(0, 400)"
						onkeydown="this.value=this.value.substring(0, 400)"
						onkeyup="this.value=this.value.substring(0, 400)"></textarea></td>
  </tr>
</table>
<br>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><input name="AddDept" type="submit" class="buttonface" value="添加" onclick="return checkForm();" >   
    <input type="reset" class="buttonface">
    <input type="button" name="Submit" onclick="javascript:history.back(-1);" class="buttonface" value="返回">
  </td>
  </tr>
</table>
<br>
</form>
</body>
</html>
