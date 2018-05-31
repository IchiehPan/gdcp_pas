<%@page import="com.gdcp.pas.manage.vo.UserVO"%>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="com.gdcp.pas.permission.PermissionUtil" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Untitled Document</title>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tree.js"></script>
</head>
<script>
var contextPath = "<%=request.getContextPath()%>";
</script>

<%
	UserVO userVO = (UserVO)session.getAttribute("userVO"); 
%>

<body style=" overflow-y: scroll;">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top" class="td03"><br>
      <table width="90%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="25" class="F01"><p><img src="<%=request.getContextPath()%>/images/index/index_35.gif" width="12" height="16" align="absmiddle"><strong>广东交通职业技术学院</strong></p>
          </td>
        </tr>
        <tr>
          <td height="2" background="<%=request.getContextPath()%>/images/index/index_39.gif"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="1"></td>
        </tr>
      </table>
        <table width="90%" border="0" cellpadding="0" cellspacing="0" class="td01">
          <tr>
            <td width="12">&nbsp;</td>
            <td width="628"><p><img src="images/spacer.gif" width="1" height="1"><img src="<%=request.getContextPath()%>/images/index/index_35.gif" width="12" height="16" align="absmiddle"><strong>快速通道</strong></p>
			
          <tr>
            <td background="<%=request.getContextPath()%>/images/index/index_41.gif">&nbsp;</td>
            <td>
              
            	
            	
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>
                	               
                <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermissionLike(userVO,"10")){out.print("none");}%>">
                    <tr>
                      <td width="19"><img id="top_1" onClick="menu('menu_1','top_1');" src="<%=request.getContextPath()%>/images/tree/tree_2_11.gif" width="19" height="20"></td>
                      <td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_27.gif" width="19" height="20"></td>
                      <td nowrap>基础数据管理</td>
                    </tr>
                  </table>
                  <div id="menu_1" style="DISPLAY: ">
				    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"101")){out.print("none");}%>">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/user_query.action" target="mainFrame" class="a03">用户管理</td>
						  </tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"102")){out.print("none");}%>">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/role_queryPage.action" target="mainFrame" class="a03">角色管理</td>
						  </tr>
					</table>
					<!-- 
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/userlevel_qurery.action" target="mainFrame" class="a03">用户身份管理</td>
						  </tr>
					</table>
					 -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"104")){out.print("none");}%>">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/dept_query.action" target="mainFrame" class="a03">部门管理</td>
						  </tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"105")){out.print("none");}%>">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_23.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/scoreResult_queryPage.action" target="mainFrame" class="a03">评价关系管理</a></td>
						  </tr>
					 </table>
					 <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"105")){out.print("none");}%>">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_23.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/addAndSub_queryPage.action" target="mainFrame" class="a03">加减分管理</a></td>
						  </tr>
					 </table>

				  </div>  
				  
					<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermissionLike(userVO,"20")){out.print("none");}%>">
                        <tr>
                          <td width="19"><img id="top_2" onClick="menu('menu_2','top_2');" src="<%=request.getContextPath()%>/images/tree/tree_2_11.gif" width="19" height="20"></td>
                          <td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_27.gif" width="19" height="20"></td>
                          <td nowrap>统计查询</td>
                        </tr>
                      </table>
					  <div id="menu_2" style="DISPLAY:">
					  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"201")){out.print("none");}%>">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/assessQuery_queryPage.action" target="mainFrame" class="a03">考核查询</a></td>
						  </tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"202")){out.print("none");}%>">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/show_queryResule.action" target="mainFrame" class="a03">考核统计</a></td>
						  </tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"202")){out.print("none");}%>">
						  <tr>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_06.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_23.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/score/PartyCommittee.jsp" target="mainFrame" class="a03">党委</a></td>
						  </tr>
						</table>
						</div>
                      <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermissionLike(userVO,"30")){out.print("none");}%>">
                        <tr>
                          <td width="19"><img id="top_3" onClick="third('menu_3','top_3');" src="<%=request.getContextPath()%>/images/tree/tree_2_17.gif" width="19" height="20"></td>
                          <td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_27.gif" width="19" height="20"></td>
                          <td nowrap>评分考核</td>
                        </tr>
                      </table>
					  <div id="menu_3" style="DISPLAY: ">
					  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"301")){out.print("none");}%>">
						  <tr>
							<td width="19"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/NotEvaluateRule_getRuleList.action" target="mainFrame" class="a03">未考核对象</a></td>
						</tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"302")){out.print("none");}%>">
						  <tr>
							<td width="19"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_07.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/EvaluatedRule_getRuleList.action" target="mainFrame" class="a03">已考核对象</a></td>
						  </tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"303")){out.print("none");}%>">
						  <tr>
							<td width="19"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_23.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/AllEvaluatedRule_getRuleList.action" target="mainFrame" class="a03">我的考核对象</a></td>
						  </tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:<%if(!PermissionUtil.isPermission(userVO,"304")){out.print("none");}%>">
						  <tr>
							<td width="19"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_23.gif" width="19" height="20"></td>
							<td width="19"><img src="<%=request.getContextPath()%>/images/tree/tree_08.gif" width="19" height="20"></td>
							<td nowrap><a href="<%=request.getContextPath()%>/DeptScoreQuantitation_query.action" target="mainFrame" class="a03">各部门的定量分</a></td>
						  </tr>
						</table>
					  </div>
      
            </table></td>
            <td width="67" background="<%=request.getContextPath()%>/images/index/index_43.gif">&nbsp;</td>
          </tr>
          <tr>
            <td><img src="<%=request.getContextPath()%>/images/index/index_50.gif" width="12" height="12"></td>
            <td background="<%=request.getContextPath()%>/images/index/index_51.gif"><img src="images/spacer.gif" width="1" height="1"></td>
            <td><img src="<%=request.getContextPath()%>/images/index/index_53.gif" width="12" height="12"></td>
          </tr>
      </table>
      <br>
      <br>
    </td>
  </tr>
</table>
</body>
</html>
