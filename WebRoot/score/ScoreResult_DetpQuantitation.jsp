<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gdcp.common.Page"%>
<%@ page import="com.gdcp.pas.manage.vo.DeptVO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title> </title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>

<script language="JavaScript" type="text/javascript"
	src="<%=request.getContextPath()%>/js/dept.js" charset="GBK"></script>
<script>
	function save(){
		var objArr = document.getElementsByName("deptScore");
		var scoreValues = "";
		for(var i=0;i<objArr.length;i++){
			if(objArr[i].value==""){
				alert("还有一些评分项的分数未输入\n操作失败~");
				return false;
			}
		}
		for(var i=0;i<objArr.length;i++){
			scoreValues  = scoreValues + objArr[i].getAttribute("id")+"b"+objArr[i].value+"a";
		}
		scoreValues = scoreValues.slice(0,-1);
		document.getElementById("scores").value=scoreValues;
		return true;
	}
	function issuccess()
	{
	if (document.getElementById("saveSuccess").value==1)
		{
		alert("保存成功");
		}
	 else if(document.getElementById("saveSuccess").value==2)
		{
		alert("保存失败");
		}
	}
	//输入框获取焦点时，清空内容
	function setNull(obj){
		if(obj.value!=""){
			obj.value = "";
		}
	}
</script>
</head>
<%
	List list = (List) request.getAttribute("DeptScoreQuantitationVO");
	String Conditions_deptName = (String) request.getAttribute("Conditions_deptName");
	if(Conditions_deptName==null){
		Conditions_deptName = "";
	}
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="issuccess()">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center" valign="bottom" class="td06"><table
					width="98%" border="0" cellspacing="3" cellpadding="0">
					<tr>
						<td width="15"><img
							src="<%=request.getContextPath()%>/images/index/index_32.gif"
							width="9" height="9"></td>
						<td valign="bottom" class="title">教学部门定量评分表</td>
					</tr>
				</table></td>
		</tr>
	</table>
	<table width="95%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="table01">
		<tr>
			<td nowrap class="td_top">序号</td>
			<td nowrap class="td_top">部门名字</td>
			<td nowrap class="td_top">部门类型</td>
			<td nowrap class="td_top">备注</td>
			<td nowrap class="td_top" align="right">部门定量分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<%int i = 1;%>
		<form name="form1" onSubmit="return save()" action="${pageContext.request.contextPath}/DeptScoreQuantitation_saveScore.action" method="post">
			<c:forEach var="DeptQVO" items="${requestScope.deptScoreQuantitationVO}" varStatus="status">
				<tr>
					<td class="td_02"><%=i%><%i++;%></td>
					<td class="td_02">${DeptQVO.deptName}</td>
					<td class="td_02">
					<c:if test="${DeptQVO.deptType==1}"><c:out value="教学部门"/></c:if>
					<c:if test="${DeptQVO.deptType==2}"><c:out value="非教学部门"/></c:if>
					</td>
					<td class="td_02">${DeptQVO.remark}</td>
					<td class="td_02" align="right">
						<input name="deptScore" id="${DeptQVO.deptId}" type="text" value="${DeptQVO.quantitationScore}" class="input5"  onfocus="setNull(this)">
					</td>
				</tr>
	
			</c:forEach>
			<tr>
				<td align="center" colspan="5" height="100" >
					<input type=hidden name='scores' id="scores">
					<input  name="submit" type="submit" class="buttonface"  value="提交"  >
				</td>
			</tr>
		</form>
		<input type=hidden  id="saveSuccess" value="${requestScope.saveSuccess}">
	</table>
</body>
</html>