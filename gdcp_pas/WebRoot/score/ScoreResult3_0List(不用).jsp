<%@page import="com.gdcp.common.extend.ExtendCodeUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.gdcp.common.Page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>我的未考核</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>
<%
	if(request.getAttribute("message")!=null){
		out.write("<script>alert(\""+request.getAttribute("message")+"\")</script>");
		request.removeAttribute("message");
	}
	Page pager = (Page) request.getAttribute("page");
%>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">所有的评分考核表_列表</td>
      </tr>
    </table></td>
  </tr>
</table>

<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr>
    <td class="td_page">
  		<font size="2">（注意：此列表仅供查看所有您需要填写的考核表的概况，填写和查看需要前往“我的未考核”和“我的已考核”列表区）</font>
    	
    	<input type="button" value="导出" onclick="gotoUrl('<%=request.getContextPath() %>/scoreresult_outputCsv.action')" style="float: right;"/> 
    </td>
  </tr>
</table>
<br>

<form name="form1" action="scoreresult_readAll" method="post">
		<input type="hidden" name="pageSize" value="<%=pager.getPageSize()%>">
		<input type="hidden" name="pageNo" value="<%=pager.getPageNo()%>">
		<input type="hidden" name="totalPage" value="<%=pager.getTotalPage()%>">
		<input type="hidden" name="total" value="<%=pager.getTotal()%>">
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr> 
  	<td nowrap class="td_top">序号</td>
	<td nowrap class="td_top">考核对象部门</td>
    <td nowrap class="td_top">考核对象名字</td>
    <td nowrap class="td_top">考核规则表</td>
    <td nowrap class="td_top">分数</td>
    <td nowrap class="td_top">状态</td>
     <%
				int rowCount = 0 + pager.getPageSize() * (pager.getPageNo()-1);
	%>
  </tr>

<c:if test="${requestScope.ScoreResultVOs!=null}">
<c:forEach var="scoreResultVO" items="${requestScope.ScoreResultVOs}" >
	<% 
		rowCount++;  
	%>
  <tr> 
    <td class="td_02"><%=rowCount%></td>
	<td class="td_02">${scoreResultVO.objectDeptName }</td>
	<td class="td_02">${scoreResultVO.objectName }</td>
	<td class="td_02">${scoreResultVO.scoreRuleTable }</td>
	<c:if test="${scoreResultVO.status==0}">
		<td class="td_02"></td>
		<td class="td_02" style="color: red;"><%=ExtendCodeUtil.getInstance().getValue("scoreResultStatus", "1") %></td>
	</c:if>
	<c:if test="${scoreResultVO.status==1}">
		<td class="td_02">${scoreResultVO.scoreResult }</td>
		<td class="td_02" style="color:blue;"><%=ExtendCodeUtil.getInstance().getValue("scoreResultStatus", "2") %></td>
	</c:if>
	<c:if test="${scoreResultVO.status==2}">
		<td class="td_02">${scoreResultVO.scoreResult }</td>
		<td class="td_02" style="color:green;"><%=ExtendCodeUtil.getInstance().getValue("scoreResultStatus", "3") %></td>
	</c:if>
  </tr>
</c:forEach>
</c:if>
<c:if test="${requestScope.ScoreResultVOs==null}">
  <tr> 
	<td class="td_02" colspan="6" align="center">没有可查看的考核表！(PS：你是管理员吧？)</td>
  </tr>
</c:if>


</table>
<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="table02" align="center">
  <tr>
  <td height="30" align="right">
		<span onclick=" firstPage()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/1.gif" width="4" height="5" align="absmiddle"> 首页 </span>
		<span onclick=" pre()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/2.gif" width="3" height="5" align="absmiddle"> 上一页  </span>
		<span onclick=" next()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/2-2.gif" width="3" height="5" align="absmiddle"> 下一页 </span> 
		<span onclick=" endPage()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/3.gif" width="4" height="5" align="absmiddle"> 末页  </span>&nbsp;&nbsp;
		<span onclick=" gotoPage(document.getElementById('gotoPageNo'))" style="cursor:hand">goto </span><input type="text" value="" style="width:30px;height:18px" id="gotoPageNo"> 
					当前第<%=pager.getPageNo()%>页 共 <%=pager.getTotalPage()%> 页 <%=pager.getTotal()%> 条记录
 </td>
  </tr>
</table>
</form>
</body>
</html>