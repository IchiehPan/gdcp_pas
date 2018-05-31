<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>考核统计</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/FusionCharts.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/score.js" charset="GBK"></script>

</head>
<form name="form1" id="form1" action="show_Look.action" method="post">
<body onload="radioChange();">

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="td_form01">
			<tr>
				<td height="25" align="center" valign="bottom" class="td06"><table
						width="98%" border="0" cellspacing="3" cellpadding="0">
						<tr>
							<td width="15"><img src="images/index_32.gif"
								width="9" height="9"></td>
							<td valign="bottom" class="title">统计中心</td>
						</tr>
					</table>
&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;

<input type="text" id="T" style="display:none">

<TABLE  width="100%" border="0" align="center" cellpadding="0" cellspacing="3">
 <TR>
 <TD nowrap  height="20" ><font size="4"><b>部门:</b></font>
 <select name="DeptList"  id="select_template"  style="font-size:14px;font-family:'微软雅黑';font-style:italic;">
<option>
所有部门
</option>
<c:forEach var="DeptVO" items="${requestScope.deptVO}" >
<option>
${DeptVO.deptName}
</option>
</c:forEach>
</select></TD>
   <TD  nowrap style="margin-left: 10px">
		<INPUT TYPE="radio" NAME="scoreLeve" value="1001" ><font size="3"><b>内设机构领导绩效考核统计&nbsp;&nbsp;<INPUT TYPE="radio" NAME="scoreLeve" value="1002">二级教学部门综合办主任、教务员岗绩效考核统计&nbsp;&nbsp; </b> </font><br>  <br> 
		<INPUT TYPE="radio" NAME="scoreLeve" value="1003"><font size="3"><b>辅导员岗绩效考核统计&nbsp;&nbsp;<INPUT TYPE="radio" NAME="scoreLeve" value="1004">非教学管理岗绩效考核统计&nbsp;&nbsp;<INPUT TYPE="radio" NAME="scoreLeve" value="1005">工勤技能岗绩效考核统计</b></font>
	</TD>
	
	<TD  nowrap style="width: 40%">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<INPUT TYPE="radio" NAME="checkRadio" value="1" id="checkRadio" onchange="radioChange()" checked><font size="3"><b>饼状图</b> </font><br>  <br> 
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<INPUT TYPE="radio" NAME="checkRadio" id="checkRadio" onchange="radioChange()"  value="2"><font size="3"><b>柱状图</b></font>	
	</TD>
	
 </TR>
 <TR>
 	<TD  nowrap colspan="3">
 	<br><br>
 	<center><input name="AddDept" type="button" class="buttonface"  style="width:60px;height:30px;" value="查看" onclick="return Show();">   </TD>
 
 </TR>
 <TR>
 	<TD colspan="3">
	  <iframe src="<%=request.getContextPath()%>/score/ScoreResult.jsp" name="frame"  id="frame"  width="100%" height="400"></iframe>
	</TD>
 
 </TR>
 </TABLE>


<div>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    
    <!--  <input type="button" name="Submit" onclick="javascript:history.back(-1);" class="buttonface" value="返回"> -->
  </td>
  </tr>
</table>

</body>
</form>
</html>
