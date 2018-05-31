<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.gdcp.pas.score.vo.ScoreRuleDetailVO"%>
<%@ page import="com.gdcp.pas.score.vo.EvaluateScoreRuleVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>各类绩效考核表</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript"  type="text/javascript" src="<%=request.getContextPath()%>/js/scorerule-table.js" charset="UTF-8"></script>

<style type="text/css">
#Title {
	position:relative;
	width:800px;
	z-index:2;
	margin:0 auto;
	text-align:center;
}
.title {
	font-weight: bold;
	font-family: "黑体";
	font-size:15px;
}
#Table {
	position:relative;
	width:800px;
	z-index:1;
	margin:0 auto;
       }
td{
	height:30px;
	font-size:16px;
	padding-top:5px;
	padding-left:5px;
	line-height:130%;
	padding-right:5px;
	}

.Biaotou {
	font-weight: bold;
	font-size:18px;
	text-align:center;
	padding-top:5px;
	padding-bottom:5px;
}

.Class {
	text-align: center;
	padding:5px
}
.b {
	float:left
}
.b1 {
	float:right
	
}
.textfield1
	{
	height: 25px;
	width: 60px;
	border-top-color: #FFF;
	border-right-color: #FFF;
	border-bottom-color: #000;
	border-left-color: #FFF;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	text-align:center
	}
	
#textfield23{
	height: 30px;
	width: 120px;
	border-top-color: #FFF;
	border-right-color: #FFF;
	border-bottom-color: #000;
	border-left-color: #FFF;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	text-align:center
	}
#textfield24{
	height: 30px;
	width: 120px;
	border-top-color: #FFF;
	border-right-color: #FFF;
	border-bottom-color: #000;
	border-left-color: #FFF;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 2px;
	border-left-width: 0px;
	text-align:center;
	}
#textfield25{
	height: 30px;
	width: 70px;
	border-top-color: #FFF;
	border-right-color: #FFF;
	border-bottom-color: #000;
	border-left-color: #FFF;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	text-align:center
	}
#textfield26{
	height: 30px;
	width: 45px;
	border-top-color: #FFF;
	border-right-color: #FFF;
	border-bottom-color: #000;
	border-left-color: #FFF;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	text-align:center
	}
#textfield27{
	height: 30px;
	width: 45px;
	border-top-color: #FFF;
	border-right-color: #FFF;
	border-bottom-color: #000;
	border-left-color: #FFF;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	text-align:center
	}
	
#button{
	position:relative;
	width:50px;
	height:50px;
	z-index:3;
	text-align:center;
	margin:0 auto;
}
</style>

</head>

<body>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" class="td06">
    <table width="98%" height="10" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15" height="10"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td class="title" height="10">绩效评分版块</td>
      </tr>
    </table></td>
  </tr>
</table>

<form id="form23" name="form23" method="get" action="<%=request.getContextPath()%>/scoreresult_saveAndcommit.action">
<p align="center" class="title" style='font-size: 25;'>
${requestScope.scoreRuleVo.ruleName }
</p>

<c:if test="${requestScope.scoreResultVo!=null }">

<div id="Title">
    <label for="textfield24" style="font-size: 20;">评价对象：</label>
    <label id="textfield24" style="font-size: 20;">${requestScope.scoreResultVo.objectName }</label> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <label for="textfield24" style="font-size: 20;">评价主体类别: </label>
    <font style="font-size: 20;">${requestScope.scorerTypeName }</font>
</div>
<br>

<div id="Table">
    <table width="800" border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td width="168" height="60" class="Biaotou">考核指标</td>
        <td width="478" height="60" class="Biaotou">考核观测点</td>
        <td width="72" class="Biaotou">得分</td>
      </tr>
     <%
	 int lv1Index = 0;
     int rowspanNum=0;
     %>
     <c:forEach var="scoreRuleDrtailLV1" items="${requestScope.scoreRuleDrtailLV1s}"  varStatus="status1">
       	<tr align="center">
       	<%
       	ArrayList<ScoreRuleDetailVO> scoreRuleDrtailLV2s = ((ArrayList<ScoreRuleDetailVO>)request.getAttribute("scoreRuleDrtailLV1s")).get(lv1Index).getChilds();
       	request.setAttribute("scoreRuleDrtailLV2s", scoreRuleDrtailLV2s);
        rowspanNum=scoreRuleDrtailLV2s.size(); 
       	%>
         <td class="Class" rowspan="<%=rowspanNum %>">
         	${scoreRuleDrtailLV1.descRiption}(${scoreRuleDrtailLV1.detailScore }分)
         </td>
		
     	 <c:forEach var="scoreRuleDrtailLV2" items="${requestScope.scoreRuleDrtailLV2s}" varStatus="status2">
     		<input type="hidden" id="LV1Id" name="LV1Id" value="${scoreRuleDrtailLV2.parentId }">
     		<input type="hidden" id="drtailId" name="drtailId" value="${scoreRuleDrtailLV2.scoreDetailId }">

     		<c:choose>
     		<c:when test="${status2.index>0}">
      	      <tr align="center">
      	        <td align="left">${scoreRuleDrtailLV2.descRiption }(0~${scoreRuleDrtailLV2.detailScore }分)</td>
      	        <td>
      	        <label for="textfield20"></label>
      	        <c:if test="${requestScope.scoreResultVo.status==0 }">
      	        	<input name="fz" id="textfield20" type="text" class="textfield1" onkeyup="juasNum(this)" onblur="check(this, ${scoreRuleDrtailLV2.detailScore })" onfocus="setNull(this)" >
      	        </c:if>
      	        <c:if test="${requestScope.scoreResultVo.status==1 }">
      	        	<input name="fz" id="textfield20" value="${scoreRuleDrtailLV2.score }" type="text" class="textfield1" onkeyup="juasNum(this)" onblur="check(this, ${scoreRuleDrtailLV2.detailScore })" onfocus="setNull(this)" >
      	        </c:if>
      	        <c:if test="${requestScope.scoreResultVo.status==2 }">
      	        	<font  id="textfield20">${scoreRuleDrtailLV2.score }</font>
      	        </c:if>
      	        </td>
      	      </tr>
      	    </c:when>
      	    <c:otherwise>
  	            <td align="left">${scoreRuleDrtailLV2.descRiption }(0~${scoreRuleDrtailLV2.detailScore }分)</td>
      	        <td>
      	        <label for="textfield20"></label>
      	        <c:if test="${requestScope.scoreResultVo.status==0 }">
      	        	<input name="fz" id="textfield20" type="text" class="textfield1" onkeyup="juasNum(this)" onblur="check(this, ${scoreRuleDrtailLV2.detailScore })" onfocus="setNull(this)" >
      	        </c:if>
      	        <c:if test="${requestScope.scoreResultVo.status==1 }">
      	        	<input name="fz" id="textfield20" value="${scoreRuleDrtailLV2.score }" type="text" class="textfield1" onkeyup="juasNum(this)" onblur="check(this, ${scoreRuleDrtailLV2.detailScore })" onfocus="setNull(this)" >
      	        </c:if>
      	        <c:if test="${requestScope.scoreResultVo.status==2 }">
      	        	<font  id="textfield20">${scoreRuleDrtailLV2.score }</font>
      	        </c:if>
      	        </td>
      	    </c:otherwise>
      	    </c:choose>
         </c:forEach>
   	 	</tr>      
		<%lv1Index++; %>
   		</c:forEach>
     
      <tr>
        <td colspan="2" class="Biaotou">合计</td>
        <td style="text-align: center;">
          <label for="textfield22"></label>
          <label class="textfield1" for="textfield22" id="hejiLabel" name="heji">${requestScope.scoreResultVo.scoreResult }</label>
        </td>
      </tr>
    </table>
</div>

</c:if>

<div align="center"><br />
  <c:choose>
	<c:when test="${requestScope.scoreResultVo.status==2 || requestScope.scoreResultVo==null }">
		<input type="button" name="button" onClick="javascript:history.back(-1);" class="button" value="确定">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
	</c:when>
	<c:otherwise>
		
		<input type="hidden" name="changeTo" id="changeTo" value="0" />
		<input type="hidden" name="scoreResultId" id="scoreResultId" value="${requestScope.scoreResultVo.id }" />
    	<input type="submit" name="button" id="save" onClick="return checkForm(this, 1)" class="button" value="保存">
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="submit" name="button" id="commit" onClick="return checkForm(this, 2)" class="button" value="提交" >
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
    	<input type="button" name="button" onClick="javascript:history.back(-1);" class="button" value="取消"> 
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    </c:otherwise>
  </c:choose>  
</div>
</form>
</body>
</html>
