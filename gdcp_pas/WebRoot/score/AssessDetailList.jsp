<%@page import="com.gdcp.pas.score.vo.ScoreResultVO"%>
<%@page import="com.gdcp.common.extend.ExtendCodeUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>我的未评价</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">评分考核表_列表</td>
      </tr>
    </table></td>
  </tr>
</table>
<form name="form1" action="scoreresult_showDetailTable" method="post">

<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr>
    <td class="td_page">
 	  <c:if test="${requestScope.SubmittdNum==0 }">
  		<font size="2" color="red">
  		已提交人数：
  		${requestScope.SubmittdNum }/
  		需提交总数：
  		${requestScope.AllResultNum }
  		</font>
      </c:if>
 	  <c:if test="${requestScope.SubmittdNum!=0 && requestScope.SubmittdNum<requestScope.AllResultNum }">
  		<font size="2" color="blue">
  		已提交人数：
  		${requestScope.SubmittdNum }/
  		需提交总数：
  		${requestScope.AllResultNum }
  		</font>
      </c:if>
 	  <c:if test="${requestScope.SubmittdNum==requestScope.AllResultNum }">
  		<font size="2" color="green">
  		已提交人数：
  		${requestScope.SubmittdNum }/
  		需提交总数：
  		${requestScope.AllResultNum }
  		</font>
      </c:if>
  	<font size="2" style="float: right;">对象：${requestScope.objectName}&nbsp;&nbsp;|&nbsp;&nbsp;部门：${requestScope.objectDeptName}</font>
    </td>
  </tr>
</table>
<br>

 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr> 
	<td nowrap class="td_top">序号</td>
	<td nowrap class="td_top">评价主体部门</td>
    <td nowrap class="td_top">评价主体名字</td>
    <td nowrap class="td_top">主体对象类型</td>
    <!-- <td nowrap class="td_top">分数</td> -->
    <td nowrap class="td_top">状态</td>
  </tr>

<c:if test="${requestScope.commitOrNotScoreResultVOs!=null}">
<%int i=0; %>
<c:forEach var="commitOrNotScoreResultVO" items="${requestScope.commitOrNotScoreResultVOs}" >
  <tr> 
	<td class="td_02"><%=i %></td>
	<td class="td_02">${commitOrNotScoreResultVO.scorerDeptName }</td>
	<td class="td_02">${commitOrNotScoreResultVO.scorerName }</td>
	<%request.setAttribute("scorerType", ""+((ArrayList<ScoreResultVO>)request.getAttribute("commitOrNotScoreResultVOs")).get(i).getScorerType() ); %>
	<td class="td_02"><%=ExtendCodeUtil.getInstance().getValue("scorerType", (String)request.getAttribute("scorerType")) %></td>
	<!-- <td class="td_02">${commitOrNotScoreResultVO.scoreResult }</td> -->
	<c:choose>
	  <c:when test="${commitOrNotScoreResultVO.status!=2 }">
		<td class="td_02" style="color: red;"><%=ExtendCodeUtil.getInstance().getValue("scoreResultStatus", "4") %></td>
	  </c:when>
	  <c:otherwise>
		<td class="td_02" style="color: green;"><%=ExtendCodeUtil.getInstance().getValue("scoreResultStatus", "3") %></td>	
	  </c:otherwise>
	</c:choose>
  </tr>
  <%i++; %>
</c:forEach>	
</c:if>
<c:if test="${requestScope.commitOrNotScoreResultVOs==null}">
  <tr> 
	<td class="td_02" colspan="6" align="center">该对象没有被考核！</td>
  </tr>
</c:if>

</table>

</form>
</body>
</html>