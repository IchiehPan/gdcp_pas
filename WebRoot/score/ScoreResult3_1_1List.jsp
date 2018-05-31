﻿<%@page import="com.gdcp.common.extend.ExtendCodeUtil"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.gdcp.common.Page"%>
<% 
	HashMap secondIndexMap = (HashMap)request.getAttribute("secondIndexMap");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的未考核</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>
<script language="JavaScript"  type="text/javascript" src="<%=request.getContextPath()%>/js/scorerule-table.js" charset="UTF-8"></script>
<script type="text/javascript">
	function playSum(inputId){
		var scoreValues = 0;
		var DetailIdScores = "";
		var objArr = document.getElementsByName("secondIndexScore"); //总成绩纪录的id数组
		var DetailIds = document.getElementsByName("scoreDetailIds");//得到一级指标的id数组
		for(var i=0;i<objArr.length;i++){
			if(objArr[i].getAttribute("id")==inputId){
				for(var j=0;j<DetailIds.length;j++){
					DetailIdScores = document.getElementsByName("score_"+DetailIds[j].getAttribute("id"));//一级指标的分数的数组
					var detailSV = (document.getElementById("detailSV"+DetailIds[j].getAttribute("id")).value)*1; //每个框可填的最大分值
					var valueS = DetailIdScores[i].value*1;//每个框的分值
					if(valueS <= detailSV && valueS>=0){
						scoreValues +=valueS;
					}else{
						alert("分值不能超过"+detailSV);
					}
					
				}
			}
		}
 	
		document.getElementById(inputId).value = scoreValues;
		var obj = document.getElementById(inputId);
 		checkSumScore(obj);
	}
	// 检查总得分合法性 
	function checkSumScore(obj){
		var scoreMax = 100;
		if(obj.value*1 >scoreMax && obj!=""){
			alert("输入分值不合法：\n不能大于该评分项分值!\n请重新输入");
			obj.value = "";
			obj.focus();
		}else{
			
		}

	}
	//检查总分数,是否提交
	function checkFormB(obj, ty){
		for(var i=0; i<document.getElementsByName("secondIndexScore").length; i++){
		var allScore = document.getElementsByName("secondIndexScore").item(i).value*1
		var nowrule = document.getElementById("nowRule").value*1;
		if((allScore>=95 || allScore<=40)&&nowrule!=1007){
			alert("总分值应在40(不含)~95(不含)分之间！\n操作失败~");
			return false;
		}
		if((allScore>=57 || allScore<=24)&&nowrule==1007){
			alert("总分值应在24(不含)~57(不含)分之间！\n操作失败~");
			return false;
		}
		}
		for(var i=0; i<document.getElementsByName("secondIndexScore").length; i++){
			if(document.getElementsByName("secondIndexScore").item(i).value==''){
				alert("还有一些评分项的分数未输入\n操作失败~");
				return false;
			}
		}  
		//得到一个关于得分的String
		var scoreValues = "";
		var DetailIdScores = "";
		var objArr = document.getElementsByName("secondIndexScore"); //总成绩纪录的id数组
		var DetailIds = document.getElementsByName("scoreDetailIds");//得到一级指标的id数组
		for(var i=0;i<objArr.length;i++){
			scoreValues +=  objArr[i].getAttribute("id");
			for(var j=0;j<DetailIds.length;j++){
				DetailIdScores = document.getElementsByName("score_"+DetailIds[j].getAttribute("id"));//一级指标的分数的数组
				scoreValues +="secondSign"+DetailIds[j].getAttribute("id")+"thirdSign"+DetailIdScores[i].value;
			}
			scoreValues +="firstSign";
		}
	
		scoreValues = scoreValues.slice(0,-9);
		document.getElementById("scores").value=scoreValues;
		document.getElementById("changeTo").value = ty;
		return true;
		
	}
	function sunScore(){
		var DetailIdScores = "";
		var objA = document.getElementsByName("sonscores");
		var objArr = document.getElementsByName("secondIndexScore"); //总成绩纪录的id数组
		var DetailIds = document.getElementsByName("scoreDetailIds");//得到一级指标的id数组
		for(var i=0;i<objArr.length;i++){
			objA[i] = objA[i].slice(0,-2);
			var objA1 =  objA[i].split("sc");
			for(var j=0;j<DetailIds.length;j++){
				DetailIdScores = document.getElementsByName("score_"+DetailIds[j].getAttribute("id"));//一级指标的分数的数组
				DetailIdScores[i].value= objA1[j];
			}
			
		}
		
	}

</script>
</head>
<body  id = "body" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="sunScore()">


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
	<a  href="<%=request.getContextPath()%>/doc/table/doc${requestScope.scoreRuleId}.htm" target="blank"><font color=red size="+1"> (点击此处查看表格详情)</font></a></td>
     <input type=hidden name='nowRule' id="nowRule" value ='${requestScope.scoreRuleId}'>
  </tr>
</table>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr>
    <td class="td_page">
  		<font size="2" style="color: red;">个人与非教学部门的定性评价总分（满分为100分）范围为40（不含）-95（不含）之间；二级教学部门定性评价总分（满分为60分）范围为24（不含）-57（不含）之间。<br />
在评价时如果对评价对象的情况不够了解，建议按照中等水平评分，不宜过高或过低。</font>
    </td>
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
   <td class="td_02" align="center"><%=i%><%i++;%></td>
	<td class="td_02" >${ScoreResultVO.objectDept}</td>
	<td class="td_02" >${ScoreResultVO.objectName}</td>
	<td class="td_02" align="center">
		<input name="secondIndexScore" id="${ScoreResultVO.resultsId}" type="text" value="${ScoreResultVO.score}" class="input4" disabled>
		<input type=hidden name='sonscores' id="${ScoreResultVO.sonScore}">
	</td>
	 <c:forEach var="secondIndexL" items="${requestScope.secondIndexL}" >
	<td class="td_02" align="center">
		  <input name="score_${secondIndexL.ruleDetailId}" id="${ScoreResultVO.objectId}_${secondIndexL.ruleDetailId}" type="text" class="input5" onblur ='playSum("${ScoreResultVO.resultsId}")'  onkeyup="juasNum(this)"  onfocus="setNull(this)">
		  <input type=hidden name='detailSV' id ='detailSV${secondIndexL.ruleDetailId}' value='${secondIndexL.detailSV}'/> <!-- 存放每个框可填的最大分值 -->
	</td>
	</c:forEach>
	
  </tr>
</c:forEach>
<tr>
	<td align="center" colspan="<%=numColumn+4%>" height="100" >
	<form name="form1" action="${pageContext.request.contextPath}/NotEvaluateRule_SaveScoreResult.action" method="post">
		<input type=hidden name='scores' id="scores">
		<input type="hidden" name="changeTo" id="changeTo" value="0" />
		<input type="submit" name="button" id="save" onClick="return checkFormB(this, 1)" class="button" value="保存">
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="submit" name="button" id="commit" onClick="return checkFormB(this, 2)" class="button" value="提交" >
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
    	<input type="button" name="button" onClick="javascript:history.back(-1);" class="button" value="取消"> 
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    </form>
	</td>
</tr>
</table>
</div>
</body>
</html>
<script>
<% 
Iterator it =  secondIndexMap.keySet().iterator();
   while(it.hasNext()){	   
	  String key = (String)it.next();
	  System.out.println("key:" + key);
	  String value =  (String)secondIndexMap.get(key);
	  out.println("document.getElementById('"+ key +"').value = '"+value+"'");
  }
  
%>
//alert(document.getElementById("aa").clientHeight)
document.getElementById("divId").style.height= document.getElementById("body").clientHeight-150;
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