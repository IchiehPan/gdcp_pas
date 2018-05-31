<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.gdcp.pas.manage.vo.UserVO"%>
<%@ page import="com.gdcp.common.FunctionListUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gdcp.common.extend.ExtendCodeUtil"%>
<%@page import="com.gdcp.common.extend.ExtendCode"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<SCRIPT language="JavaScript">
	function ChkClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var functionGroupId = new Array();
		functionGroupId = ${requestScope.functionIds};
		for (i = 0; i < arrSon.length; i++) {
			for (j = 0; j < functionGroupId.length; j++) {
				if (arrSon[i].value == functionGroupId[j]) {
					arrSon[i].click();
				}
			}
		}
	}

	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState) {
				arrSon[i].click();
			}
		}
	}

	function ChkSonClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		for (var i = 0; i < arrSon.length; i++) {
			if (!arrSon[i].checked) {
				cbAll.checked = false;
				return;
			}
		}
		cbAll.checked = true;
	}

	function getChkValues() {
		var checks = userform.chkSon;
		var userFunctionGroupTypeId = new Array();
		for (i = 0; i < checks.length; i++) {
			var obj = checks[i];
			if (obj.checked == true) {
				userFunctionGroupTypeId[i] = obj.value;
			}
		}

		var checks2 = userform.chkSon2;
		var baseFunctionGroupTypeId = new Array();
		for (i = 0; i < checks2.length; i++) {
			var obj = checks2[i];
			if (obj.checked == true) {
				baseFunctionGroupTypeId[i] = obj.value;
			}
		}

		var checks3 = userform.chkSon3;
		var messageFunctionGroupTypeId = new Array();
		for (i = 0; i < checks3.length; i++) {
			var obj = checks3[i];
			if (obj.checked == true) {
				messageFunctionGroupTypeId[i] = obj.value;
			}
		}

		userform.action = "user_updateUser.action?userFunctionList="
				+ userFunctionGroupTypeId + "&baseFunctionList="
				+ baseFunctionGroupTypeId + "&messageFunctionList="
				+ messageFunctionGroupTypeId;
		userform.submit();
	}

	var $ = function(obj) {
		return document.getElementById(obj);
	};

	function check() {
		if ($("name").value == "") {
			alert("用户名称不能为空！");
			$("name").focus();
			return false;
		}
		if ($("password").value == "") {
			alert("密码不能为空！");
			$("password").focus();
			return false;
		}
		//			var filter = /^[\w]{6,12}$/;
		//			alert($("password").value);
		//			if (!$("password").value.match(filter)) {
		//				alert("密码不合规定！");
		//				$("password").value.focus();
		//				$("password").value.select();
		//				return false;
		//			}
		if ($("password").value != $("repassword").value) {
			alert("两次填写的密码不一致，请重新填写！");
			$("repassword").focus();
			$("repassword").select();
			return false;
		}

		if ($("dept").value == -1) {
			alert("请选择部门！");
			return false;
		}

		if ($("position").value == -1) {
			alert("请选择岗位！");
			return false;
		}

		getChkValues();
	}
</script>

<title>用户个人信息操作</title>
		<link href="${pageContext.request.contextPath}/css/style.css"
			rel="stylesheet" type="text/css" />
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/css/calendar.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/calendar.js"
			charset="utf-8"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/calendar-zh.js"
			charset="utf-8"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/calendar-setup.js"
			charset="utf-8"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/check.js"></script>
	</head>

	<body
		onload="ChkClick('chkSon','chkAll');ChkClick('chkSon2','chkAll2');ChkClick('chkSon3','chkAll3')">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="25" align="center" valign="bottom" class="td06">
					<table width="98%" border="0" cellspacing="3" cellpadding="0">
						<tr>
							<td width="15">
								<img
									src="${pageContext.request.contextPath}/images/index_32.gif"
									width="9" height="9">
							</td>
							<td valign="bottom" class="title">
								用户个人信息操作
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<br>
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="td_title">
					<h2>
						用户个人信息操作（带
						<font color="#FF0000">*</font>号的为必填项）
					</h2>
				</td>
			</tr>
		</table>

		<form name="userform" method="post">

			<table width="95%" border="0" align="center" cellpadding="2"
				cellspacing="0">

				<tr>
					<td width="150" align="right" class="td_form01">
						教工号
					</td>
					<td class="td_form02">
						<input name="userVO.teacherId" type="text" onblur="this.value=this.value.replace(/[^0-9]+/,'');" class="input"
							id="textName" size="60" maxlength="20" value="${userVO.teacherId}"
							onblur="checkDocumentNoNull()">
						<font color="#FF0000">*</font>请输入教工号
					</td>
				</tr>

				<tr>
					<td align="right" class="td_form01">
						用户名字
					</td>
					<td class="td_form02">
						<input name="userVO.userName" type="text" class="input"
							id="name" size="60" maxlength="10" value="${userVO.userName}">
						<font color="#FF0000">*</font>请输入真实姓名
					</td>
				</tr>

				<tr>
					<td align="right" class="td_form01">
						用户密码
					</td>
					<td class="td_form02">
						<input name="userVO.password" type="password" class="input"
							id="password" size="60" maxlength="40" value="${userVO.password}">
						<font color="#FF0000">*</font>输入6-30个字符的密码，可使用的字符为：A-Z a-z 0-9 _
						-，注意不要使用空格
					</td>
				</tr>

				<tr>
					<td align="right" class="td_form01">
						确认密码
					</td>
					<td class="td_form02">
						<input name="textfield" type="password" class="input"
							id="repassword" size="60" maxlength="40" value="${userVO.password}">
						<font color="#FF0000">*</font>
					</td>
				</tr>

				<tr>
					<td align="right" class="td_form01">
						所属角色
					</td>
					<td class="td_form02">
						<c:forEach var="role" items="${requestScope.roleList}">
							<input type="radio" name="userVO.roleId" value="${role.roleId}"
								<c:if test="${userVO.roleId eq role.roleId}">checked</c:if> />${role.roleName}
						</c:forEach>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						请勾选所属角色
					</td>
				</tr>

				<tr>
					<td align="right" class="td_form01">
						用户身份
					</td>
					<td class="td_form02">
						<select style="width: 170px;" name="userVO.userCharacter">
						<%request.setAttribute("userlevel", ((ArrayList<ExtendCode>)ExtendCodeUtil.getInstance().getExtendCodeListByType("userlevel"))); %>
							<c:forEach var="level" items="${requestScope.userlevel}">
								<option value="${level.numKey}"
									<c:if test="${level.numKey eq userVO.userCharacter}">selected</c:if>>
									${level.value}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						用户所属部门
					</td>
					<td class="td_form02">
						<select style="width: 170px;" name="userVO.deptId" id="dept">
							<c:forEach var="dept" items="${requestScope.deptList}">
								<option value="${dept.deptId}"
								
									<c:if test="${userVO.deptId eq dept.deptId}">selected</c:if>>
									${dept.deptName}
								</option>
							</c:forEach>
						</select>
						<font color="#FF0000">*</font>
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						用户专业技术部门1
					</td>
					<td class="td_form02">
						<select style="width: 170px;" name="userVO.prodeptId1">
							<option value="-1">
								请选择用户专业技术部门1
							</option>
							<c:forEach var="dept" items="${requestScope.deptList}">
								<option value="${dept.deptId}"
									<c:if test="${userVO.prodeptId2 eq dept.deptId}">selected</c:if>>
									${dept.deptName}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						用户专业技术部门2
					</td>
					<td class="td_form02">
						<select style="width: 170px;" name="userVO.prodeptId2">
							<option value="-1">
								请选择用户专业技术部门2
							</option>
							<c:forEach var="dept" items="${requestScope.deptList}">
								<option value="${dept.deptId}"
									<c:if test="${userVO.prodeptId2 eq dept.deptId}">selected</c:if>>
									${dept.deptName}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						个人考核岗位
					</td>
					<td class="td_form02">
						<select style="width: 170px;" name="userVO.evalPosition" id="position">
							<%request.setAttribute("objectTypes", ((ArrayList<ExtendCode>)ExtendCodeUtil.getInstance().getExtendCodeListByType("objectType"))); %>
							<c:forEach var="objectType" items="${requestScope.objectTypes}">
								<option value="${objectType.numKey}"
									<c:if test="${objectType.numKey eq userVO.evalPosition}">selected</c:if>>
									${objectType.value}
								</option>
							</c:forEach>
						</select>
						<font color="#FF0000">*</font>
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						性别
					</td>
					<td class="td_form02">
						<input type="radio" name="userVO.sex" value="男"
							<c:if test="${userVO.sex eq '男'}">checked</c:if>>
						男
						<input type="radio" name="userVO.sex" value="女"
							<c:if test="${userVO.sex eq '女'}">checked</c:if>>
						女
					</td>
				</tr>
				
				<tr>
					<td align="right" class="td_form01">
						教职工代表
					</td>
					<td class="td_form02">
						<input type="radio" name="userVO.isProfessional" value="0"
							<c:if test="${userVO.isProfessional == 0}">checked</c:if>>
						否
						<input type="radio" name="userVO.isProfessional" value="1"
							<c:if test="${userVO.isProfessional == 1}">checked</c:if>>
						是
					</td>
				</tr>
				
				
				<tr>
					<td align="right" class="td_form01">
						出生年月
					</td>
					<td class="td_form02">
						<input type="text" id="EntTime" size="60" name="userVO.birthday"
							class="input" onclick="return showCalendar('EntTime', 'y-mm-dd')"
							value="${userVO.birthday}">
						&nbsp;&nbsp;请输入出生年月日
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						现任专业技术资格
					</td>
					<td class="td_form02">
						<input name="userVO.technicaltitle" type="text" class="input"
							id="textName" size="60" value="${userVO.technicaltitle}">
						&nbsp;&nbsp;请输入现任专业技术资格
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						现任职务
					</td>
					<td class="td_form02">
						<input name="userVO.job" type="text" class="input" id="textName"
							size="60" value="${userVO.job}">
						&nbsp;&nbsp;请输入现任职务
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						学历/学位
					</td>
					<td class="td_form02">
						<input name="userVO.degree" type="text" class="input"
							id="textName" size="60" value="${userVO.degree}">
						&nbsp;&nbsp;请输入学历/学位
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						现聘岗位
					</td>
					<td class="td_form02">
						<input name="userVO.presentPosition" type="text" class="input"
							id="textName" size="60" value="${userVO.presentPosition}">
						&nbsp;&nbsp;请输入现聘岗位
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						岗位类别
					</td>
					<td class="td_form02">
						<input name="userVO.positionKind" type="text" class="input"
							id="textName" size="60" value="${userVO.positionKind}">
						&nbsp;&nbsp;请输入岗位类别
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						级别
					</td>
					<td class="td_form02">
						<input name="userVO.userLevel" type="text" class="input"
							id="textName" size="60" value="${userVO.userLevel}">
						&nbsp;&nbsp;请输入级别
					</td>
				</tr>
				<tr>
					<td align="right" class="td_form01">
						备注
					</td>
					<td class="td_form02">
						<input name="userVO.remark" type="text" class="input"
							id="textName" size="60" value="${userVO.remark}">
						&nbsp;&nbsp;如有备注，请输入
					</td>
				</tr>

				<tr>
					<td align="right" class="td_form01">
						用户功能
					</td>
					<td colspan="4"><%=FunctionListUtil.rightsCheckboxHtml()%></td>
				</tr>

			</table>

			<br>
			<table width="95%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td align="center">
						<input type="button" class="buttonface" value=" 保存 "
							onclick="check()">
						<input type="button" class="buttonface"
							onclick="javascript:history.back(-1);" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
		<br>
	</body>
</html>
