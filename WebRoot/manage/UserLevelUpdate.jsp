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
<form action="userlevel_trueupdate.action" method="post">
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
    <td class="td_title">用户身份修改</td>
  </tr>
</table>
<table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">

<tr>
       <input name="userLevelId" type="text" style="display:none" style="background-color:#E0E0E0"  class="input2" size="90" value="<%=session.getAttribute("userLevelId") %>" readonly="readonly"></td>
  </tr>
  
 <tr>
    <td width="90" align="right" class="td_form01">用户身份</td>
    <td class="td_form02"><input name="userLevelName" type="text" class="input" id="textName" size="90" value="<%if(session.getAttribute("userLevelName")==null){
}else{%><%=session.getAttribute("userLevelName").toString().trim() %><% };%>"></td>
</tr>
  <tr>
    <td align="right" class="td_form01">备注<br></td>
    <td class="td_form02"><textarea name="remark" onpropertychange="if(value.length>400) value=value.substr(0,400)" cols="90" rows="8"><%if(session.getAttribute("remark")==null){}else{%><%=session.getAttribute("remark").toString() %><% };%></textarea></td>
  </tr>
</table>
<br>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
   <!--  <td align="center"><input name="AddUserLevtel" type="submit" class="buttonface" value="添加"> -->
    <td align="center"><input name="UpdataUserLevtel" type="submit" class="buttonface" onClick="delcfm()" value="修改" >
    <input type="button" name="Submit" onclick="javascript:history.back(-1);" class="buttonface" value="返回">
        <!-- <input name="Submit2" type="submit" class="buttonface" value="  é¢è§  ">
    <input name="Submit22" type="submit" class="buttonface" value="  æ¸é¤  ">  -->   </td>
  </tr>
</table>
<br>
</form>
</body>
</html>
