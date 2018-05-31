<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.gdcp.pas.manage.vo.DeptVO" %>
<%@ page import="com.gdcp.pas.manage.vo.ScoreResultVO" %>
<%@ page import="com.gdcp.pas.score.vo.AssessQueryVO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>考核关系添加</title>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/check.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/xmlhttp.js"></script>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
</head>
<SCRIPT LANGUAGE="JavaScript">
function formsubmit(){
		form1.submit();
}
function selectDept(){
	var odeptId=document.getElementById("odeptId").value;
	var sdeptId=document.getElementById("sdeptId").value;
	var objectId=document.getElementById("objectId").value;
	var scorerId=document.getElementById("scorerId").value;
	if(sdeptId==-1&&odeptId!=-1||odeptId!=-1&&sdeptId!=-1&&objectId==null){
	var url="scoreResult_queryName.action?odeptId="+odeptId;
	var strObj=postStr(url);
	var objectObj=document.getElementById("objectId");
	
	var objectArr=strObj.split(",");
	var optionStr = "";
	for(var i=0;i<objectArr.length;i++){
		var userArr = objectArr[i].split("@#")
		optionStr = optionStr +  "<option value=\"" + userArr[1] + "\">" +  userArr[0]+ "</option>";
	}
	var selectStr="<select style=\"width: 170px;\" name=\"objectId\" id=\"objectId\"><option value=\"-1\">请选择考核对象名字</option>" + optionStr + "</select>";
	objectObj.outerHTML =  selectStr;
	}
	
	if(odeptId!=-1&&sdeptId!=-1||odeptId==-1&&sdeptId!=-1){
	var url="scoreResult_queryName.action?sdeptId="+sdeptId;
	var strObj=postStr(url);
	var scorerObj=document.getElementById("scorerId");

	var scorerArr=strObj.split(",");
	var optionStr = "";
	for(var i=0;i<scorerArr.length;i++){
		var userArr = scorerArr[i].split("@#")
		optionStr = optionStr +  "<option value=\"" + userArr[1] + "\">" +  userArr[0]+ "</option>";
	}
	var selectStr="<select style=\"width: 170px;\" name=\"scorerId\" id=\"scorerId\"><option value=\"-1\">请选择考核主体名字</option>" + optionStr + "</select>";
	scorerObj.outerHTML =  selectStr;
	}
	
}

</SCRIPT>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="form1" action="scoreResult_insertRec.action"  method="post" >

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">考核关系添加</td>
      </tr>
    </table></td>
  </tr>
</table>
<br>
<table align="center" width="95%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="95" height="24" class="td_form01"><font color="red">*</font>考核对象部门名称</td>
        <td nowrap class=td_form02>
        <select style="width: 170px;" name="odeptId" onchange="selectDept()" id="odeptId">
        <option value="-1">
								请选择部门名称
							</option>
						<c:forEach var="deptName" items="${requestScope.deptList}">
								<option value="${deptName.deptId}" <c:if test="${odeptId eq deptName.deptId}">selected</c:if>>
									${deptName.deptName}
								</option>
						</c:forEach>
						</select>
		</td>
		
		 <td width="90" height="24" class="td_form01"><font color="red">*</font>考核对象名字</td>
		 <td nowrap class=td_form02>
		 <select style="width: 170px;" name="objectId" id="objectId">
        	<option value="-1">
								请选择考核对象名字
							</option>
					
						</select>
		 </td>
		
		 <td width="90" height="24" class="td_form01"><font color="red">*</font>考核对象类型</td>
		 <td nowrap class=td_form02>
		 <select style="width: 170px;" name="objectTypeId">
        	<option value="-1">
								请选择考核对象类型
							</option>
							<c:forEach var="oType" items="${requestScope.objectTypeList}">
						
								<option value="${oType.objectTypeId}" <c:if test="${objectType eq oType.objectTypeId}">selected</c:if>>
									${oType.obecjtType}
								</option>
							</c:forEach>
						</select>
		 </td>
		</tr>
		<tr>
        <td width="90" class="td_form01"><font color="red">*</font>考核主体部门名称</td>
        <td nowrap class=td_form02>
          <select style="width: 170px;" name="sdeptId" onchange="selectDept()" id="sdeptId">
          <option value="-1">
								请选择部门名称
							</option>
							<c:forEach var="dName" items="${requestScope.deptList}">
						
								<option value="${dName.deptId}" <c:if test="${sdeptId eq dName.deptId}">selected</c:if>>
									${dName.deptName}
								</option>
							</c:forEach>
						</select>
      	</td>
     
        <td width="90" class="td_form01"><font color="red">*</font>考核主体名称</td>
        <td nowrap class=td_form02>
          <select style="width: 170px;" name="scorerId" id="scorerId">
          <option value="-1">
								请选择考核主体名称
							</option>
						</select>
      	</td>
     
        <td width="90" class="td_form01"><font color="red">*</font>考核主体类型</td>
        <td nowrap class=td_form02>
          <select style="width: 170px;" name="scorerTypeId">
          <option value="-1">
								请选择考核主体类型
							</option>
							<c:forEach var="sType" items="${requestScope.scoreTypeList}">
						
								<option value="${sType.scorerTypeId}" <c:if test="${scorerType eq sType.scorerTypeId}">selected</c:if>>
									${sType.scorerType}
								</option>
							</c:forEach>
			</select>
      	</td>
      </tr>
      <tr>
       <td width="90" height="24" class="td_form01"><font color="red">*</font>考核规则名称</td>
        <td nowrap class=td_form02>
        <select style="width: 170px;" name="scoreRuleId">
        <option value="-1">
								请选择考核规则名称
							</option>
						<c:forEach var="scoreResult" items="${requestScope.scoreResultList}">
								<option value="${scoreResult.scoreruleId}" <c:if test="${scoreRuleId eq scoreResult.scoreruleId}">selected</c:if>>
									${scoreResult.ruleName}
								</option>
							</c:forEach>
						</select>
		</td>
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
