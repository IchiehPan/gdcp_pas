<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gdcp.pas.manage.vo.DeptVO"%>
<%@ page import="com.gdcp.pas.manage.vo.ScoreResultVO"%>
<%@ page import="com.gdcp.pas.score.vo.AssessQueryVO"%>
<%@ page import="com.gdcp.common.extend.ExtendCode"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考核关系批量添加</title>
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" type="text/css" />
<%
	if(request.getAttribute("message")!=null){
		out.write("<script>alert(\""+request.getAttribute("message")+"\")</script>");
	}
%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/public.js"></script>
<script type="text/javascript">
	function stateChange(obj, scorerType) {
		if (obj.checked){
			if(obj==document.getElementById("scorerTypeChectkBox"+scorerType)) {
				document.getElementById("checkDiv"+scorerType).style.display = 'inline';
				
			}else if(obj==document.getElementById("deptAll")){
				var deptChectkBox = document.getElementsByName("dept100312");
				for (i = 0; i < deptChectkBox.length; i++) {
					if (document.getElementsByName("dept100312")[i].checked == false) {
						document.getElementsByName("dept100312")[i].checked = true;
					}
				}
			}else{
				var deptChectkBox = document.getElementsByName("dept100312");
				document.getElementById("deptAll").checked = true;
				for (i = 0; i < deptChectkBox.length; i++) {
					if (document.getElementsByName("dept100312")[i].checked == false) {
						document.getElementById("deptAll").checked = false;
					}
				}
			}
		}
		
		else{
			if(obj==document.getElementById("scorerTypeChectkBox"+scorerType)){
				var deptChectkBox = document.getElementsByName("dept"+scorerType);
				for (i = 0; i < deptChectkBox.length; i++) {
					if (document.getElementsByName("dept"+scorerType)[i].checked == true) {
						document.getElementsByName("dept"+scorerType)[i].checked = false;
					}
				}
				document.getElementById("checkDiv"+scorerType).style.display = 'none';
			}else if(obj==document.getElementById("deptAll")){
				var deptChectkBox = document.getElementsByName("dept100312");
				for (i = 0; i < deptChectkBox.length; i++) {
					if (document.getElementsByName("dept100312")[i].checked == true) {
						document.getElementsByName("dept100312")[i].checked = false;
					}
					
				}
			}else{
				document.getElementById("deptAll").checked = false;
			}
		}
		
		
	}
	
	function check(){
		
		var canGotoUrl = 1;
		if(document.getElementById("objectTypeSelect").value==-1){
			alert("请选取考核对象！");
			canGotoUrl = 0;
		}else{
			document.getElementById("objectType").value = document.getElementById("objectTypeSelect").value;
		}
		
		var scoreRules = document.getElementsByName("scoreRule");
		var isCheckdRules = 1;
		for(i=0; i<scoreRules.length; i++){
			if(scoreRules[i].checked){
				document.getElementById("scoreRuleId").value = scoreRules[i].value;
				isCheckdRules = 0;
			}
		}
		if(isCheckdRules==1){
			alert("请选取考核规则！");
			canGotoUrl = 0;
		}
		
		var scorerTypes = document.getElementsByName("scorerType");
		var isCheckdScorer = 1;
		for(i=0; i<scorerTypes.length; i++){
			if(scorerTypes[i].checked){
				isCheckdScorer = 0;
			}
		}
		if(isCheckdScorer==1){
			alert("请选取考核主体！");
			canGotoUrl = 0;
		}
		
		if(scorerTypes[1].checked){
			var dept100312 = document.getElementsByName("dept100312");
			var isCheckddept100312 = 1;
			for(i=0; i<dept100312.length; i++){
				if(dept100312[i].checked){
					isCheckddept100312 = 0;
				}
			}
			if(isCheckddept100312==1){
				alert("请选取“其他部门”具体指哪些部门！");
				canGotoUrl = 0;
			}
		}
		
		if(scorerTypes[7].checked){
			var dept100318 = document.getElementsByName("dept100318");
			var isCheckddept100318 = 1;
			for(i=0; i<dept100318.length; i++){
				if(dept100318[i].checked){
					isCheckddept100318 = 0;
				}
			}
			if(isCheckddept100318==1){
				alert("请选取“二级部门”具体指哪些部门！");
				canGotoUrl = 0;
			}
		}
		// 宜杰追加 scorerTypes[8].checked
		if(scorerTypes[8].checked){
			var dept100319 = document.getElementsByName("dept100319");
			var isCheckddept100319 = 1;
			for(i=0; i<dept100319.length; i++){
				if(dept100319[i].checked){
					isCheckddept100319 = 0;
				}
			}
			if(isCheckddept100319==1){
				alert("请选取“相关部门全体职工”具体指哪些部门！");
				canGotoUrl = 0;
			}
		}
		if(canGotoUrl==1){
			submitToUrl('<%=request.getContextPath()%>/scoreresult_scoreResultBatchAdd.action');
		}
		
	}
</script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<form name="form1" action="scoreResult_insertRec.action" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="25" align="center" valign="bottom" class="td06">
					<table width="98%" border="0" cellspacing="3" cellpadding="0">
						<tr>
							<td width="15"><img
								src="<%=request.getContextPath()%>/images/index/index_32.gif"
								width="9" height="9"></td>
							<td valign="bottom" class="title">考核关系批量添加</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br>

		<table width="95%" border="0" align="center" cellpadding="2"
			cellspacing="0">
			<tr>
				<td width="40%" align="right" class="td_form01"
					style="background-color: #ACF;"><input type="hidden"
					name="objectType" id="objectType" value="-1"> <select
					name="objectTypeSelect" id="objectTypeSelect"
					style="font-size: 14px; margin: 6px; font-family: '微软雅黑'; font-style: italic;">
						<option value="-1">请选择考核对象</option>
						<c:forEach var="objectType" items="${requestScope.objectTypes}">
							<option value="${objectType.numKey }">
								${objectType.value}</option>
						</c:forEach>
				</select></td>


				<td class="td_form02" rowspan="2" nowrap><br> <br> <%
 	int index = 0;
 %> <c:forEach var="scorerType" items="${requestScope.scorerTypes}">
						<%
							if (index < 9) {
									request.setAttribute("is100312", ((ArrayList<ExtendCode>) request.getAttribute("scorerTypes"))
											.get(index).getNumKey().equals("100312"));
									request.setAttribute("is100318", ((ArrayList<ExtendCode>) request.getAttribute("scorerTypes"))
											.get(index).getNumKey().equals("100318"));
									request.setAttribute("is100319", ((ArrayList<ExtendCode>) request.getAttribute("scorerTypes"))
											.get(index).getNumKey().equals("100319"));
								}
						%>

						<c:if test='${requestScope.is100312}'>
							<div style="height: 180px;">
								<input name="scorerType" type="checkbox"
									value="${scorerType.numKey}"
									id="scorerTypeChectkBox${scorerType.numKey}"
									onChange="stateChange(this, <%if(index<9){ %><%=((ArrayList<ExtendCode>)request.getAttribute("scorerTypes")).get(index).getNumKey()%><%} %>)" />
								<font size="3"><b>${scorerType.value}</b></font> <br>
								<div style="overflow: auto; height: 158px;">
									<table width="98%"
										style="display: none; background-color: #DDF; margin: 3px;"
										border="1px;" id="checkDiv${scorerType.numKey}">
										<tr>
											<td align="center" colspan="5"><input name="deptAll"
												id="deptAll" type="checkbox" value="all"
												onChange="stateChange(this, null)" /><font size="2">全选&nbsp;&nbsp;</font>
											</td>
										</tr>
										<tr>
											<c:forEach var="dept" items="${requestScope.depts}"
												varStatus="i">
												<td><input name="dept${scorerType.numKey}"
													type="checkbox" value="${dept.deptId }"
													onChange="stateChange(this, null)" />${dept.deptName }&nbsp;&nbsp;
												</td>
												<c:if test="${(i.index+1)%5==0}">
										</tr>
										<tr>
											</c:if>
											</c:forEach>
										</tr>
									</table>
								</div>
								<br> <br>
							</div>
						</c:if>


						<c:if test='${requestScope.is100318}'>
							<div style="height: 60px;">
								<input name="scorerType" type="checkbox"
									value="${scorerType.numKey}"
									id="scorerTypeChectkBox${scorerType.numKey}"
									onChange="stateChange(this, <%if(index<9){ %><%=((ArrayList<ExtendCode>)request.getAttribute("scorerTypes")).get(index).getNumKey()%><%} %>)" />
								<font size="3"><b>${scorerType.value}</b></font> <br>
								<table width="98%"
									style="display: none; background-color: #DDF; margin: 3px;"
									border="1px;" id="checkDiv${scorerType.numKey}">
									<tr>
										<c:forEach var="deptS" items="${requestScope.deptSs}">
											<td><input name="dept${scorerType.numKey}"
												type="checkbox" value="${deptS.deptId }" />${deptS.deptName }&nbsp;
											</td>
										</c:forEach>
									</tr>
								</table>
								<br> <br>
							</div>
						</c:if>


						<!-- 宜杰1002追加 c:if test='${requestScope.is100319} -->
						<c:if test='${requestScope.is100319}'>
							<div style="height:;">
								<input name="scorerType" type="checkbox"
									value="${scorerType.numKey}"
									id="scorerTypeChectkBox${scorerType.numKey}"
									onChange="stateChange(this, <%if(index<9){ %><%=((ArrayList<ExtendCode>)request.getAttribute("scorerTypes")).get(index).getNumKey()%><%} %>)" />
								<font size="3"><b>${scorerType.value}</b></font> <br>
								<div style="overflow: auto; height: 100px;">
									<table width="98%"
										style="display: none; background-color: #DDF; margin: 3px;"
										border="1px;" id="checkDiv${scorerType.numKey}">
										<tr>
											<c:forEach var="deptA" items="${requestScope.deptAa}"
												varStatus="i">
												<td><input name="dept${scorerType.numKey}"
													type="checkbox" value="${deptA.deptId }" />${deptA.deptName }&nbsp;&nbsp;
												</td>
												<c:if test="${(i.index+1)%5==0}">
										</tr>
										<tr>
											</c:if>
											</c:forEach>
										</tr>
									</table>
								</div>
								<br> <br>
							</div>
						</c:if>

						<c:if
							test='${ !requestScope.is100312 && !requestScope.is100318  && !requestScope.is100319 }'>
							<div style="height: 50px;">
								<input name="scorerType" type="checkbox"
									value="${scorerType.numKey }" /> <font size="3"><b>${scorerType.value}</b></font>
								<br> <br>
							</div>
						</c:if>
						<%
							index++;
						%>
					</c:forEach>
			</tr>

			<tr>
				<td align="right" class="td_form0001"><br> <br> <input
					type="hidden" name="scoreRuleId" id="scoreRuleId" value="-1">
					<c:forEach var="scoreRule" items="${requestScope.scoreRules}">
						<INPUT TYPE="radio" name="scoreRule"
							value="${scoreRule.scoreruleId }" id="scoreRules">
						<font size="3"><b>${scoreRule.ruleName}</b></font>
						<br>
						<br>
						<br>
						<br>
					</c:forEach></td>
			</tr>
		</table>

		<br>
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center"><input name="Submit2" type="button"
					class="buttonface" style="width: 60px; height: 30px;" value="确定"
					onClick="check()"> &nbsp;&nbsp;&nbsp;&nbsp; <input
					type="button" name="Submit" style="width: 60px; height: 30px;"
					onclick="javascript:history.back(-1);" class="buttonface"
					value="返回"></td>
			</tr>
		</table>
		<br>
	</form>
</body>
</html>
