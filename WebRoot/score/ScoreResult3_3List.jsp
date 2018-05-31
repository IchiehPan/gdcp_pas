<%@page import="com.gdcp.common.extend.ExtendCodeUtil"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.gdcp.common.Page"%>
<%@ page import="com.gdcp.pas.manage.vo.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>我的已考核</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06">
    <table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">所有的评分考核表_列表</td>
      </tr>
    </table>
    </td>
  </tr>
</table>

<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr>
    <td class="td_page">
  		<font size="2" >（注意：如果填写完可直接提交，也可以先保存; 保存之后还可做修改，一旦提交则不可更改）
  		<% UserVO userVO = (UserVO)session.getAttribute("userVO");
  		   int isProfessional = userVO.getIsProfessional();
  		   if(isProfessional==1){
  			  %><br/><font size="3" style="color: red;"><b> 您已经被选为教职工代表，需要对非教学部门领导和非教学部门进行评分，请配合评价 ！</b></font><%
  		   }%></font>
    </td>
  </tr>
</table>
<br>

 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr> 
  	<td nowrap class="td_top">序号</td>
    <td nowrap class="td_top">考核规则类型</td>
    <td nowrap class="td_top">评分状态</td>
    <td  nowrap class="td_top" align="right">操作&nbsp;&nbsp;&nbsp;</td>
  </tr>
<%int i=1; %>
<c:forEach var="ScoreRuleVO" items="${requestScope.soreRVList}" >
  <tr> 
   <td class="td_02"><%=i%><%i++;%></td>
	<td class="td_02">${ScoreRuleVO.ruleName}</td>
	<td class="td_02" style="color: red;">
	<c:if test="${ScoreRuleVO.status==0}">未考核(未填)</c:if>
	<c:if test="${ScoreRuleVO.status==1}">未考核(已填)</c:if>
	<c:if test="${ScoreRuleVO.status==2}">已考核</c:if>
	</td>
	<form name="formn" action="${pageContext.request.contextPath}/AllEvaluatedRule_getScoreResultAndSecondIndex.action?scoreRuleId=${ScoreRuleVO.scoreRuleId}" method="post">
		<td class="td_02" align="right">
			<input type=hidden name='ruleName' id="ruleName" value ='${ScoreRuleVO.ruleName}'>
			<input name="button" type="submit" class="buttonface" value="查看" >
		</td>
	</form>
  </tr>
</c:forEach>
<c:if test="${requestScope.soreRVList==null}">
  <tr> 
	<td class="td_02" colspan="6" align="center">没有可查看的考核表！(PS：你是管理员吧？)</td>
  </tr>
</c:if>
</table>
</body>
</html>