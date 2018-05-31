<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.gdcp.pas.score.vo.AssessQueryVO" %>
<%@ page import="com.gdcp.pas.manage.vo.ScoreResultVO" %>
<%@ page import="com.gdcp.pas.manage.vo.DeptVO" %>
<%@ page import="com.gdcp.common.Page"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>
<script type="text/javascript">
	function formsubmit(){
	var deptId=document.getElementById("deptId").value;
	var objectType=document.getElementById("objectTypeId").value;
	
		form1.submit();
	
	}
	function queryPage() {
		form1.pageNo.value = "1";
		form1.submit();

	}
	function queryNotSubmitScoreUser(){
		submitToUrl("<%=request.getContextPath()%>/assessQuery_queryNotSubmitScoreUser.action");
	}
	function finishAssess(){
		alert("由于数据量庞大，所以计数成绩需要较长的时间！\n请耐心等候(^_^)~\n（注意！！点击本提示窗之后请不要对本系统再进行任何操作，直到出现“计算完毕的提示窗”）");
		
		submitToUrl("<%=request.getContextPath()%>/averageScore_calculateScore.action");
	}
	function outputWeightScore(){
		submitToUrl("<%=request.getContextPath()%>/averageScore_outputWeightScore.action");
	}
	function outputCommitRate(){
		submitToUrl("<%=request.getContextPath()%>/averageScore_outputCommitRate.action");
	}
</script>
<%
	if(request.getAttribute("message")!=null){
		out.write("<script language=\"JavaScript\" type=\"text/javascript\" >alert(\""+request.getAttribute("message")+"\");</script>");
	}
%>
</head>
<%
	List<AssessQueryVO> assessList = (List<AssessQueryVO>) request.getAttribute("assessList");
	Page pager = (Page) request.getAttribute("page");
	
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">考核结果查询
        </td>
      </tr>
    </table></td>
  </tr>
</table>
<form name="form1" action="assessQuery_queryPage.action" method="post">
		<input type="hidden" name="pageSize" value="<%=pager.getPageSize()%>">
		<input type="hidden" name="pageNo" value="<%=pager.getPageNo()%>">
		<input type="hidden" name="totalPage" value="<%=pager.getTotalPage()%>">
		<input type="hidden" name="total" value="<%=pager.getTotal()%>">
 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
 <tr>
   <td class="td_page" width="100%">请选择部门名称：
     <select style="width: 170px;" name="deptId" id="deptId">
     	<option value="-1">部门名称列表</option>    	
								<c:forEach var="dName" items="${requestScope.deptList}">
							
									<option value="${dName.deptId}" <c:if test="${deptId eq dName.deptId}">selected</c:if>>
										${dName.deptName}
									</option>
								</c:forEach>
						</select>
					
   请选择考核对象类型：
     <select style="width: 170px;" name="objectTypeId"  id="objectTypeId">
     	<option value="0">考核对象类型列表</option>
							<c:forEach var="oType" items="${requestScope.objectTypeList}">
						
								<option value="${oType.objectTypeId}" <c:if test="${objectTypeId eq oType.objectTypeId}">selected</c:if>>
									${oType.obecjtType}
								</option>
							</c:forEach>
						</select>
						<input type="button" value="查询" onclick="queryPage()">
						
		<input type="button" onclick="queryNotSubmitScoreUser()" value="查询未提交">
	<div style="float: right;">
		&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="finishAssess()" value="考核结束，计算分数">
	</div>
	<div style="float: right;">
		&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="outputCommitRate()" value="导出提交明细">
	</div>
	<div style="float: right;">
		<input type="button" onclick="outputWeightScore()" value="导出成绩">
	</div>
  </td>
  </form>
  </tr></table>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
				<tr>
					<td nowrap class="td_top">
						序号
					</td>
					<td nowrap class="td_top">
						部门名
					</td>
					<td nowrap class="td_top">
						被考核对象名称
					</td>
					<td nowrap class="td_top">
						考核规则年度
					</td>
					<td nowrap class="td_top">
						考核规则名称
					</td>
					<td nowrap class="td_top">
						评价进度
					</td>
					<td nowrap class="td_top">
						得分
					</td>
					<td nowrap class="td_top">
						等级
					</td>
					<td nowrap class="td_top" align="right">
						操作&nbsp;&nbsp;&nbsp;
					</td>
			<%
				int rowCount = 0 + pager.getPageSize() * (pager.getPageNo()-1);
			%>
				</tr>
	<%
	for(int i=0;i<assessList.size();i++){
		AssessQueryVO assessQueryVO = assessList.get(i);
	    rowCount++;
    %>
  <tr> 
  	 <td class="td_02"><%=rowCount%></td>
 	<td class="td_02"><%=assessQueryVO.getDeptName()%></td>
 	<td class="td_02"><%=assessQueryVO.getUserName()%></td>
 	<td class="td_02"><%=assessQueryVO.getRuleYear()%></td>
 	<td class="td_02"><%=assessQueryVO.getRuleName()%></td>
 	<td class="td_02">
 	<%if(assessQueryVO.getSubmittdNum()==0){ %>
 	  <font color="red">
 		<%=assessQueryVO.getSubmittdNum()%>/<%=assessQueryVO.getAllResultNum() %>
 	  </font>
 	<%}else if(assessQueryVO.getSubmittdNum()<assessQueryVO.getAllResultNum()){ %>
	  <font color="blue">
		<%=assessQueryVO.getSubmittdNum()%>/<%=assessQueryVO.getAllResultNum() %>
	  </font>
	<%}else  if(assessQueryVO.getSubmittdNum()==assessQueryVO.getAllResultNum()){ %>
 	  <font color="green">
 		<%=assessQueryVO.getSubmittdNum()%>/<%=assessQueryVO.getAllResultNum() %>
 	  </font>
 	<%} %>
 	</td>
 	<td class="td_02"><%=new DecimalFormat("#0.00").format(assessQueryVO.getScore())%></td>
 	<td class="td_02">-</td>
    <td class="td_02" align="right">
		<input name="button" type="button" class="buttonface" value="查看" 
		onClick="gotoUrl('<%=request.getContextPath()%>/scoreresult_readCommitOrNot.action?teacherId=<%=assessQueryVO.getTeacherID()%>&scoreRuleId=<%=assessQueryVO.getScoreRuleId()%>&SubmittdNum=<%=assessQueryVO.getSubmittdNum() %>&AllResultNum=<%=assessQueryVO.getAllResultNum() %>');">
	</td>  
  </tr>
  <%
		}		
  %>
 
</table>
<table width="95%"  border="0" cellpadding="0" cellspacing="0" class="table02" align="center">
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
</body>
</html>