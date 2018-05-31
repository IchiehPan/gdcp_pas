<%@page import="com.gdcp.common.extend.ExtendCodeUtil"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.gdcp.common.Page"%>
<% 
	HashMap secondIndexMap = (HashMap)request.getAttribute("secondIndexMap");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>我的已考核</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>
<script language="JavaScript"  type="text/javascript" src="<%=request.getContextPath()%>/js/scorerule-table.js" charset="UTF-8"></script>

</head>
<body  id='body' leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06">
    <table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">评分考核表_列表</td>
        <td align="right"><input type="button" name="button" onClick="javascript:history.back(-1);"  value="返回"></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr>
    <td valign="bottom" class="title" align="center"> <b>${requestScope.ruleName}</b>
     <a  href="<%=request.getContextPath()%>/doc/table/doc${requestScope.scoreRuleId}.htm" target="blank"><font color=red > (点击此处查看表格详情)</font></a></td>
  </tr>
</table>
<br>

<%int numColumn=0, i=1; %>
<table id="tableHead" width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr> 
  	<td  class="td_top">序号</td>
    <td  class="td_top">所在部门</td>
    <td  class="td_top">姓名</td>
    <td  class="td_top" align="center">总分</td>
    <c:forEach var="secondIndexL" items="${requestScope.secondIndexL}" >
    	<td   class="td_top" align="center" >${secondIndexL.secondIndex}(${secondIndexL.detailSV}分)</td>
    	<input type=hidden name='scoreDetailIds' id ="${secondIndexL.ruleDetailId}" value="${secondIndexL.ruleDetailId}">
    	<%numColumn++; %>
    </c:forEach>
  </tr>
 </table>
 
<div id="divId" style="height:200px;width:100%;overflow:auto;">
 <table id="tableBody" width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
<c:forEach var="ScoreResultVO" items="${requestScope.soreRVList}" >
  <tr> 
   <td class="td_02" ><%=i%><%i++;%></td>
	<td class="td_02" >${ScoreResultVO.objectDept}</td>
	<td class="td_02" >${ScoreResultVO.objectName}</td>
	<td class="td_02" align="center">
		<input name="secondIndexScore" id="${ScoreResultVO.resultsId}" type="text" value="${ScoreResultVO.score}" class="input4" disabled>
	</td>
	 <c:forEach var="secondIndexL" items="${requestScope.secondIndexL}" >
	<td class="td_02" align="center">
		   <input name="${secondIndexL.ruleDetailId}" id="${ScoreResultVO.objectId}_${secondIndexL.ruleDetailId}" type="text" value="" class="input4" disabled >
	</td>
	</c:forEach>
	
  </tr>
</c:forEach>
</table>
</div>
</body>
</html>
<script>
<% 
Iterator it =  secondIndexMap.keySet().iterator();
   while(it.hasNext()){	   
	  String key = (String)it.next();
	  String value =  (String)secondIndexMap.get(key);
	  out.println("document.getElementById('"+ key +"').value = '"+value+"'");
  }
  
%>
//alert(document.getElementById("aa").clientHeight)
document.getElementById("divId").style.height= document.getElementById("body").clientHeight-120;
//alert(document.getElementById("table2").offsetWidth )
var tableHeadObj = document.getElementById("tableHead");
var tableBodyObj = document.getElementById("tableBody");
	//alert(tableBodyObj.offsetWidth)
var abc = tableBodyObj.offsetWidth
tableHeadObj.style.width =tableBodyObj.offsetWidth;


for(var i=0;i<tableHeadObj.rows[0].cells.length;i++){
	tableHeadObj.rows[0].cells[i].style.width = tableBodyObj.rows[0].cells[i].offsetWidth;
}
for(var i=0;i<tableHeadObj.rows[0].cells.length;i++){
	tableBodyObj.rows[0].cells[i].style.width = tableHeadObj.rows[0].cells[i].style.width;
}
  </script>