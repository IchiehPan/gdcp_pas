<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.gdcp.common.Page"%>
<%@ page import="com.gdcp.pas.manage.vo.UserVO"%>
<%@ page import="com.gdcp.common.extend.ExtendCodeUtil"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>用户信息中心</title>
		<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/user.js" charset="GBK"></script>
	
	</head>
	
	<%
	List list = (List) request.getAttribute("userList");
	Page pager = (Page) request.getAttribute("page");
	String Conditions_userName = (String) request.getAttribute("Conditions_userName");
	if(Conditions_userName==null){
		Conditions_userName = "";
	}
%>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
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
								用户列表
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<form name="form1" action="user_query.action" method="post">
	
	<input type="hidden" name="pageSize" value="<%=pager.getPageSize()%>">
		<input type="hidden" name="pageNo" value="<%=pager.getPageNo()%>">
		<input type="hidden" name="totalPage" value="<%=pager.getTotalPage()%>">
		<input type="hidden" name="total" value="<%=pager.getTotal()%>">
		
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="td_page">
						用户名字：
						<input type="text"  name="Conditions_userName" value="<%=Conditions_userName%>">
						<input type="button" onclick="queryPage()" value="查询">
						<input type="button" value="添加"
							onclick="window.location='user_queryAddInfo.action'">
						<input type="button" value="批量删除" onclick="getChkValues()">
				</td>
			</tr>
		</table>
		<table width="95%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="table01">
			<tr>
				<td class="td_top">
					<input name="chk" type="checkbox" id="chk"
						onClick="selectAllByChk(chk, checkbox)" value="checkbox0">
				</td>
				<td width="10%" nowrap class="td_top" style="display: none;">
					教工号
				</td>
				<td width="10%" nowrap class="td_top" >
					序号
				</td>
				<td width="10%" nowrap class="td_top">
					用户名字
				</td>
				<td width="10%" nowrap class="td_top">
					性别
				</td>
				<td width="10%" nowrap class="td_top">
					用户所属部门
				</td>
				<td width="10%" nowrap class="td_top">
					学历/学位
				</td>
				<td width="10%" nowrap class="td_top">
					现聘岗位
				</td>
				<td width="10%" nowrap class="td_top">
					级别
				</td>
				<td width="10%" nowrap class="td_top">
					查看详细/修改
				</td>
				<td width="20%" nowrap class="td_top"  align="center">
					操作
				</td>
			</tr>
			
				<%
				int rowCount = 0 + pager.getPageSize() * (pager.getPageNo()-1);
			%>

			<c:if test="${not empty requestScope.userList}">
				<% int index = 0; %>
				<c:forEach var="user" items="${requestScope.userList}" varStatus="status">
				
					<%
					rowCount++;
				%>
					<tr>
						<td nowrap  class="td_02">
							<input type="checkbox" name="checkbox" id="checkbox"
								value="${user.teacherId}">
						</td>
						
						<td  class="td_02" style="display: none">
							&nbsp;${user.teacherId}
						</td>
						
						<td  class="td_02" >
							&nbsp;<%=rowCount%>
						</td>
						
						<td nowrap class="td_02">
							&nbsp;${user.userName}
						</td>
						<td class="td_02">
							&nbsp;${user.sex}
						</td>
						
						<td class="td_02" style="display:none">
							&nbsp;${user.deptId}
						</td>
						
			
						<td nowrap class="td_02">
							&nbsp;${user.deptName}
						</td>
						
						<td class="td_02">
							&nbsp;${user.degree}
						</td>
						<td nowrap class="td_02">
						<%
						String objectType="";
						if(ExtendCodeUtil.getInstance().getValue("objectType", ((List<UserVO>)request.getAttribute("userList")).get(index).getEvalPosition()+"")==null){
							objectType="";
						}else{
							objectType=ExtendCodeUtil.getInstance().getValue("objectType", ((List<UserVO>)request.getAttribute("userList")).get(index).getEvalPosition()+"");
						}
						%>
						
							&nbsp;<%=objectType%>
						</td>
						
						<td class="td_02" style="display: none;">
							&nbsp;${user.userCharacter}
						</td>
						
						<td nowrap class="td_02">
						
						<%
						String userlevel="";
						if(ExtendCodeUtil.getInstance().getValue("userlevel", ((List<UserVO>)request.getAttribute("userList")).get(index).getUserCharacter()+"")==null){
							userlevel="";
						}else{
							userlevel=ExtendCodeUtil.getInstance().getValue("userlevel", ((List<UserVO>)request.getAttribute("userList")).get(index).getUserCharacter()+"");
						}
						%>
						
							&nbsp;<%=userlevel%>
						</td>
						<td class="td_02">
						<input type="button" value="修改" class="buttonface"
								onclick="window.location='user_queryById.action?teacherId=${user.teacherId}'">
						</td>
						<td class="td_02" align="center">
							<input type="button" value="删除" class="buttonface"
								onclick="javascript:if(confirm('是否确定删除用户：${user.userName}？\n删除后将无法恢复！')) window.location='user_delUser.action?teacherId=${user.teacherId}'">
						</td>
					</tr>
					<% index++; %>
				</c:forEach>
			</c:if>
		</table>
		<table width="95%" border="0" cellpadding="0" cellspacing="0"
			class="table02" align="center">
			<tr>
				 <td height="30" align="right">
					<span onclick="firstPage()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/1.gif" width="4" height="5" align="absmiddle"> 首页 </span>
					<span onclick="pre()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/2.gif" width="3" height="5" align="absmiddle"> 上一页  </span>
					<span onclick="next()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/2-2.gif" width="3" height="5" align="absmiddle"> 下一页 </span> 
					<span onclick="endPage()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/3.gif" width="4" height="5" align="absmiddle"> 末页  </span>&nbsp;&nbsp;
					<span onclick="gotoPage(document.getElementById('gotoPageNo'))" style="cursor:hand">goto</span><input type="text" value="" style="width:30px;height:18px" id="gotoPageNo"> 
					当前第<%=pager.getPageNo()%>页 共 <%=pager.getTotalPage()%> 页 <%=pager.getTotal()%> 条记录
 				</td>
			</tr>
		</table>
	</form>
	</body>
</html>