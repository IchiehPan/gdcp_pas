<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gdcp.common.Page"%>
<%@ page import="com.gdcp.pas.manage.vo.DeptVO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title> </title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>

<script language="JavaScript" type="text/javascript"
	src="<%=request.getContextPath()%>/js/dept.js" charset="GBK"></script>
</head>
<%
	List list = (List) request.getAttribute("deptVO");
	Page pager = (Page) request.getAttribute("page");
	String Conditions_deptName = (String) request.getAttribute("Conditions_deptName");
	if(Conditions_deptName==null){
		Conditions_deptName = "";
	}
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="25" align="center" valign="bottom" class="td06"><table
					width="98%" border="0" cellspacing="3" cellpadding="0">
					<tr>
						<td width="15"><img
							src="<%=request.getContextPath()%>/images/index/index_32.gif"
							width="9" height="9"></td>
						<td valign="bottom" class="title">部门列表</td>
					</tr>
				</table></td>
		</tr>
	</table>
	<form name="form1" action="dept_query.action" method="post">
		<input type="hidden" name="pageSize" value="<%=pager.getPageSize()%>">
		<input type="hidden" name="pageNo" value="<%=pager.getPageNo()%>">
		<input type="hidden" name="totalPage" value="<%=pager.getTotalPage()%>">
		<input type="hidden" name="total" value="<%=pager.getTotal()%>">

		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="2">
			<tr>
				<td align="left" class="td_page">
					部门名字：
						<input type="text" name="Conditions_deptName" value="<%=Conditions_deptName%>">
						<input type="button" onclick="queryPage()" value="查询">
				<input name="add" type="button" value="  新 建  " onClick="gotoUrl('<%=request.getContextPath()%>/manage/DeptAdd.jsp')">
				&nbsp;&nbsp;
				<input name="del" type="button" value="  批量删除  " onclick="actionFunction()">&nbsp;&nbsp;</td>
			</tr>
		</table>

		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="table01">
			<tr>
				<td nowrap class="td_top"><INPUT name="chkAll" id="chkAll"
					onClick="ChkAllClick('chkSon','chkAll')" type="checkbox" /></td>

				<td nowrap class="td_top">序号</td>
				<td nowrap class="td_top">部门名字</td>
				<td nowrap class="td_top">部门类型</td>
				<td nowrap class="td_top">备注</td>
				<td nowrap class="td_top" align="right">操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<%
				int rowCount = 0 + pager.getPageSize() * (pager.getPageNo()-1);
			%>
			<c:forEach var="DeptVO" items="${requestScope.deptVO}" varStatus="status">
				
				<%
					rowCount++;
				%>
				<tr>
					<td nowrap class="td_02"><INPUT name="chkSon"
						id="${DeptVO.deptId}" value='${DeptVO.deptId}' type="checkbox"
						onclick="ChkSonClick('chkSon','chkAll')" /></td>

					<td class="td_02"><%=rowCount%></td>
					<td class="td_02">${DeptVO.deptName}</td>
					<td class="td_02">
					<c:if test="${DeptVO.deptType==1}"><c:out value="教学部门"/></c:if>
					<c:if test="${DeptVO.deptType==2}"><c:out value="非教学部门"/></c:if>
					</td>
					<td class="td_02">${DeptVO.remark}</td>
					<td class="td_02" align="right"><input name="button"
						type="button" class="buttonface" value="修改"
						onClick="gotoUrl('${pageContext.request.contextPath}/dept_update.action?deptId=${DeptVO.deptId}&deptName=${DeptVO.deptName}&deptType=${DeptVO.deptType}&remark=${DeptVO.remark}');">

						<input name="button" type="button" class="buttonface" value="删除"
						onClick="javascript:if(deleteConfirm()) gotoUrl('${pageContext.request.contextPath}/dept_deleteRec.action?deptId=${DeptVO.deptId}');">

					</td>
				</tr>

				
			</c:forEach>
 
			
		</table>
		<table width="95%" border="0" cellpadding="0" cellspacing="0"
			class="table02" align="center">
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