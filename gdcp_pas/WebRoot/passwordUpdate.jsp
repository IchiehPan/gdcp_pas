<%@ page contentType="text/html;charset=GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="com.gdcp.pas.manage.vo.UserVO" %>

<%
	 UserVO userVO = (UserVO)session.getAttribute("userVO");
%> 
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>修改密码</title>
<LINK href="<%=request.getContextPath()%>/css/tmpstyle.css" type=text/css rel=stylesheet>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/js/xmlhttp.js"></script>
<script src="<%=request.getContextPath()%>/js/public.js"></script>


<script language="JavaScript" type="text/JavaScript">

var xmlHttp;
function createXmlHttpRequest() {
	if (window.ActiveObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		}
	}
}


 function updatePassword(contextPath){
	if(form1.newPassword.value!=form1.newPassword2.value){
		alert("两次输入的新密码不一致！请重新输入");
		return;
	}
	if(trim(form1.newPassword.value)=="" || trim(form1.oldPassword.value)==""){
		alert("密码不能为空");
		return;
	}

	
    var newPassWord=form1.newPassword.value;
    var oldPassWord=form1.oldPassword.value;
	xmlHttp = createXmlHttpRequest();
	xmlHttp.onreadystatechange = go;
    var ch = encodeURI(encodeURI(<%=userVO.getTeacherId()%>));
	var ch2 = encodeURI(encodeURI(newPassWord));
	var ch3 = encodeURI(encodeURI(oldPassWord)); 
	
	<%-- var ch = encodeURIComponent(encodeURIComponent(<%=userVO.getTeacherId()%>));
	var ch2 = encodeURIComponent(encodeURIComponent(newPassWord));
	var ch3 = encodeURIComponent(encodeURIComponent(oldPassWord)); --%>
	var url = "user_updatePassword.action?teacherId=" + ch +"&newPassword="+ch2+"&oldPassword="+ ch3;
	xmlHttp.open("POST", url, true);
	xmlHttp.send(null);

 }
 
 function go() {
		if (xmlHttp.readyState == 4) {

			if (xmlHttp.status == 200) {
				if (xmlHttp.responseText == "true") {
					alert("密码修改成功，请记住新密码!")
					window.close();
					top.closePassword();
				} else {
					alert("旧密码错误，请重新输入!");
					return;
				}
			} else {
				alert("数据有误" + xmlHttp.status);

			}

		}

	}


		
		

</script>
<style>

.Grid_FrameStyle {
    margin: 0px 0 0px 0;
    border-width:0px;
	cursor:hand;
}
</style>
</head>

<BODY scroll="no" background="<%=request.getContextPath()%>/images/password/background.jpg">
<form name="form1">
<table id="__01" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="3">
			<img src="<%=request.getContextPath()%>/images/password/cpass_01.gif" width="350" height="61" alt="">adf</td>
	</tr>
	<tr>
 
		
    <td width="100%" rowspan="2" background="<%=request.getContextPath()%>/images/password/cpass_03.gif"><form name="form1" method="post" action="">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="35%"><div align="center"><font size="2">旧密码: </font></div></td>
            <td width="65%"><input type="password" name="oldPassword"  class="input"></td>
          </tr>
          <tr> 
            <td><div align="center"><font size="2">新密码: </font></div></td>
            <td><input  type="password" name="newPassword" class="input"></td>
          </tr>
          <tr> 
            <td><div align="center"><font size="2">新密码: </font></div></td>
            <td><input  type="password" name="newPassword2" class="input"></td>
          </tr>
          <tr> 
            <td height="80" colspan="2">
				<div align="center">
                <input type="button" value=" 修改 " onclick="updatePassword('<%=request.getContextPath()%>')" class="buttonface">
                &nbsp;&nbsp; 
                <input type="button" value=" 取消 " onclick="window.close();top.closePassword()" class="buttonface">
              </div></td>
          </tr>
        </table>
      </form> </td>
		<td width="8" rowspan="2">
			<img src="<%=request.getContextPath()%>/images/password/cpass_04.gif" width="8" height="100%" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="<%=request.getContextPath()%>/images/password/cpass_05.gif" width="7" height="100%" alt=""></td>
	</tr>
</table>
</form>
</body>

</html>