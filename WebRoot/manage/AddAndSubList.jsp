<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.gdcp.pas.manage.vo.AddAndSubVO" %>
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
	function check()
	{
	  		var userName=form2.userName.value;
	  			if(userName==null || userName == ""){
	  				gotoUrl('addAndSub_queryPage.action');
	  			}else{
	  				form2.submit();
	  			}
			
	}
</script>
</head>
<%
	List<AddAndSubVO> addAndSubList = (List<AddAndSubVO>) request.getAttribute("addAndSubList");
	Page pager = (Page) request.getAttribute("page");
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="25" align="center" valign="bottom" class="td06"><table width="98%" border="0" cellspacing="3" cellpadding="0">
      <tr>
        <td width="15"><img src="<%=request.getContextPath()%>/images/index/index_32.gif" width="9" height="9"></td>
        <td valign="bottom" class="title">加减分管理
        </td>
      </tr>
    </table></td>
  </tr>
</table>

<form name="form2" action="addAndSub_queryByObjectId.action" method="post">
		<input type="hidden" name="pageSize" value="<%=pager.getPageSize()%>">
		<input type="hidden" name="pageNo" value="<%=pager.getPageNo()%>">
		<input type="hidden" name="totalPage" value="<%=pager.getTotalPage()%>">
		<input type="hidden" name="total" value="<%=pager.getTotal()%>">
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
<tr>
   <td class="td_page" width="100%">对象名称：
   <input type="text" name="userName" id="userName"/>
   <input type="button" value="查询" onclick="check()">
   <div style="float: right;">
		&nbsp;&nbsp;&nbsp;
		<input name="add" type="button" value="添加" onClick="gotoUrl('<%=request.getContextPath()%>/addAndSub_AddAndSubTo.action')" >
	</div>
  </td>
  </tr>
</table>
</form>

<form name="form1" action="addAndSub_deleteScore.action" method="post">
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
				<tr>
					<td nowrap class="td_top">
						序号
					</td>
					<td nowrap class="td_top">
						对象名称
					</td>
					<td nowrap class="td_top">
						加减分项
					</td>
					<td nowrap class="td_top">
						分值
					</td>
					<td nowrap class="td_top">
						描述
					</td>
					<td nowrap class="td_top" align="right">
						操作
					</td>
			<%
				int rowCount = 0 + pager.getPageSize() * (pager.getPageNo()-1);
			%>
				</tr>
	<%
	for(int i=0;i<addAndSubList.size();i++){
		AddAndSubVO addAndSubVO = addAndSubList.get(i);
	    rowCount++;
    %>
  <tr> 
  	 <td class="td_02"><%=rowCount%></td>
  	 <td class="td_02"><%=addAndSubVO.getUserName()%></td>
  	 <td class="td_02">
  	 <%if(addAndSubVO.getStatus()==0){%>
  	 	<%="加分项" %>
  	 <% }else if(addAndSubVO.getStatus()==1) {%>
  	 	<%="减分项" %>
  	 <%} else if(addAndSubVO.getStatus()==2){%>
  	 	<%="鼓励加分项" %>
  	 	<%} %>
  	 </td>
  	 <td class="td_02"><%=addAndSubVO.getScore()%></td>
  	 <td class="td_02">
  	 <%
  	 if(addAndSubVO.getDescribe().length()>8){%>
  	 	<%=addAndSubVO.getDescribe().substring(0,8) %>...
  	 <%}else{%>
  	  <%=addAndSubVO.getDescribe()%>
  	 <% } %>
  	 </td>
  	 <td class="td_02" align="right">
  		 <input name="button" type="button" class="buttonface" value="查看" onClick="gotoUrl('<%=request.getContextPath()%>/addAndSub_queryByObjectIdTo.action?id=<%=addAndSubVO.getId()%>');">
  	 	<input name="button" type="button" class="buttonface" value="删除" onClick="javascript:if(deleteConfirm()) gotoUrl('<%=request.getContextPath()%>/addAndSub_deleteScore.action?id=<%=addAndSubVO.getId()%>');">
  	 </td>
  </tr>
  <%
		}		
  %>
 
</table>
</form>
<table width="95%"  border="0" cellpadding="0" cellspacing="0" class="table02" align="center">
  <tr>
  <%if(pager.getTotal()!=0){%>
    <td height="30" align="right">
		<span onclick=" firstPage()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/1.gif" width="4" height="5" align="absmiddle"> 首页 </span>
		<span onclick=" pre()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/2.gif" width="3" height="5" align="absmiddle"> 上一页  </span>
		<span onclick=" next()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/2-2.gif" width="3" height="5" align="absmiddle"> 下一页 </span> 
		<span onclick=" endPage()" style="cursor:hand"><img src="<%=request.getContextPath()%>/images/3.gif" width="4" height="5" align="absmiddle"> 末页  </span>&nbsp;&nbsp;
		<span onclick=" gotoPage(document.getElementById('gotoPageNo'))" style="cursor:hand">goto </span><input type="text" value="" style="width:30px;height:18px" id="gotoPageNo"> 
					当前第<%=pager.getPageNo()%>页 共 <%=pager.getTotalPage()%> 页 <%=pager.getTotal()%> 条记录
  </td>
  <%}else{%>
  <td></td>
  <% }%>
  </tr>
</table>
</body>
</html>