<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.gdcp.pas.manage.vo.ScoreResultVO" %>
<%@ page import="com.gdcp.pas.manage.vo.DeptVO" %>
<%@ page import="com.gdcp.common.Page"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>我的成果</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/public.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/page.js"></script>
 <script type="text/javascript">
function formsubmit(){
	var userName=document.getElementById("userName").value;
	var ouserName=document.getElementById("ouserName");
	var suserName=document.getElementById("suserName");
		if(userName==""||userName==null){
			alert("输入不能为空!");
		}else if(!ouserName.checked&&!suserName.checked){
			alert("至少选择一个查询条件!");
		}else{
			form1.submit();
		}
		
	
}
	function queryPage() {
		form1.pageNo.value = "1";
		form1.submit();

	}
</script>
</head>
<%
	List<ScoreResultVO> scoreResultList = (List<ScoreResultVO>) request.getAttribute("scoreResultList");
	Page pager = (Page) request.getAttribute("page");
	String userType=(String)request.getAttribute("userType");
	String userName=(String)request.getAttribute("userName");
	if(userName==null){
		userName="";
	}
	boolean flag=true;
	if("1".equals(userType)){
		flag=false;
	}
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">考核关系列表
        </td>
      </tr>
    </table></td>
  </tr>
</table>
<form name="form1" action="scoreResult_queryPage.action" method="post">
		<input type="hidden" name="pageSize" value="<%=pager.getPageSize()%>">
		<input type="hidden" name="pageNo" value="<%=pager.getPageNo()%>">
		<input type="hidden" name="totalPage" value="<%=pager.getTotalPage()%>">
		<input type="hidden" name="total" value="<%=pager.getTotal()%>">

 <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
 <tr class="td_page">
	<td nowrap>用户名：<input type="text" name="userName" id="userName" value="<%=userName%>" >
	<input type="radio" name="userType"  <%if(flag) out.print("checked");%> value="0" id="objectId">考核对象
	<input type="radio" name="userType"  <%if(!flag) out.print("checked");%>  value="1" id="scorerId">考核主体
	<input type="button" value="查询" class="td_page" onclick="queryPage()">
	</td>
	<td align="right">
	<input name="add" type="button" value="添加" onClick="gotoUrl('<%=request.getContextPath()%>/scoreResult_queryDept.action')" >
	<input name="batchAdd"  type="button" value="批量添加" onclick="gotoUrl('<%=request.getContextPath()%>/scoreResult_getBatchAddMessage.action')">
	</td>
 </tr>
 </table>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
  <tr> 
    <td nowrap class="td_top">序号</td>
	<td nowrap class="td_top">考核对象所属部门</td>
    <td nowrap class="td_top">考核对象名字</td>
    <td nowrap class="td_top">考核主体所属部门</td>
    <td nowrap class="td_top">考核主体名字</td>
    <td nowrap class="td_top">考核规则名称</td>
    <td  nowrap class="td_top" align="right">操作&nbsp;&nbsp;&nbsp;</td>
    	<%
				int rowCount = 0 + pager.getPageSize() * (pager.getPageNo()-1);
			%>
  </tr>
  <%
	for(int i=0;i<scoreResultList.size();i++){
		ScoreResultVO scoreResultVO = scoreResultList.get(i);
	    rowCount++;
  %>
  <tr> 
    <td class="td_02"><%=rowCount%></td>
	<td class="td_02"><%=scoreResultVO.getOdeptName()%></td>
  	<td class="td_02"><%=scoreResultVO.getOuserName()%></td>
 	<td class="td_02"><%=scoreResultVO.getSdeptName()%></td>
 	<td class="td_02"><%=scoreResultVO.getSuserName()%></td>
 	<td class="td_02"><%=scoreResultVO.getRuleName()%></td>
    <td class="td_02" align="right">
		<input name="button" type="button" class="buttonface" value="删除" onClick="javascript:if(deleteConfirm()) gotoUrl('<%=request.getContextPath()%>/scoreResult_deleteRec.action?objectId=<%=scoreResultVO.getObjectId()%>&&scorerId=<%=scoreResultVO.getScorerId()%>');">
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
</form>
</body>
</html>