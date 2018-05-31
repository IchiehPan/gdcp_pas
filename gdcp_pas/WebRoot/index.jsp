<%@ page contentType="text/html; charset=gb2312" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/diag/images/blue/css.css" rel="stylesheet" type="text/css" id="showdialogcss" />
<script src="<%=request.getContextPath()%>/diag/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/diag/js/dialog.js"></script>

<title>广东交通职业技术学院绩效考核系统</title>
<script>

function showDialogX (arg,url,width,height)
{
	return;
	if(navigator.userAgent.indexOf("Chrome")>0){
		var winOption = "Width=" + width + ",Height=" + height + ",top=200,left=500,center=yes,help=no,resizable=yes,status=1,toolbar=1";
		return window.open(url,window,winOption);
	}else{
	    returnValue = window.showModalDialog(url,arg,"dialogWidth:" + width + "px;dialogHeight:" + height + "px;center:yes;help:no;resizable:yes;status:no"); 
	    return returnValue;
		
	}
}

$(document).ready(function(){
$('#diagx').ShowDialog({
					Width:"350",
					Height:"270",
					Title:"模式对话框",
					skin:"blue",
					FrameURL:"<%=request.getContextPath()%>/passwordUpdate.jsp",
					ContentFlag:"0",
					Contents:"<div style='position:absolute;top:20px'>测试数据</div>"
					});
});

function closePassword(){
	document.getElementById("diagclose").click();

}
</script>

</head>

<body id="bodyId" style="overflow: hidden" >
<table width="100%" height="98%" border="0" cellpadding="0" cellspacing="0">
	<tr height="78">
		<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/images/index/index_02.gif">
			  <tr>
				<td width="40"><img src="<%=request.getContextPath()%>/images/index/index_01.gif" width="260" height="40"></td>
				<td align="right" valign="bottom"><table border="0" cellspacing="7" cellpadding="0">
				  <tr>
					<td width="50" valign="bottom"><img src="<%=request.getContextPath()%>/images/index/index_09.gif" style="cursor:hand" width="20" height="16" align="absbottom" /><a href="<%=request.getContextPath()%>/scoreresult_readAll.action" target="mainFrame" class="a02">首页</a></td>
					<td width="70" valign="bottom"> <div id="diagx"> <img src="<%=request.getContextPath()%>/images/index/index_09.gif" width="20" height="16" align="absbottom" /><a  href="javascript:void(0);" onclick="showDialogX ('','<%=request.getContextPath()%>/passwordUpdate.jsp',350,240)"  target="mainFrame" class="a02">修改密码</a></div></td>
					<td width="50" valign="bottom"><img src="<%=request.getContextPath()%>/images/index/index_09.gif" style="cursor:hand" width="20" height="16" align="absbottom"/><a href="#" class="a02"  onclick="javascript:if(confirm('是否注销？')){top.window.location='logout.jsp';}">注销</a></td>
					<td width="50" valign="bottom"><img src="<%=request.getContextPath()%>/images/index/index_11.gif" style="cursor:hand" width="20" height="16" align="absbottom"/><a href="#" class="a02" onclick="javascript:if(confirm('是否要退出系统？')){top.window.close();top.window.location='login.jsp';}">退出</a></td>
					</tr>
				</table></td>
			  </tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td height="1"><img src="images/spacer.gif" width="1" height="1"></td>
			  </tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/images/index/index_21.gif">
				<form name="form1" method="post" action="">
				  <tr>
					<td width="20" height="30">&nbsp;</td>
					<td class="F02"><img src="<%=request.getContextPath()%>/images/index/index_23.gif" width="26" height="30" align="absmiddle" />${sessionScope.userVO.userName},欢迎你 </td>

					<td width="14" class="F02"></td>
				  </tr>
				</form>
			</table>
		</td>
	</tr>
	<tr>
		<td width="250"><iframe src="<%=request.getContextPath()%>/left.jsp" width="100%" height="100%" frameborder="0"></iframe></td>
		<td width="10">
			<table width="10" height="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					 <td class="td02">
						<div id=oa_tree onClick="oa_tool();" title=隐藏工具栏>
							<img src="<%=request.getContextPath()%>/images/index/index_48.gif" name="frameshow" width="10" height="53" id=frameshow style="cursor:hand" />
						</div>
					</td>
				</tr>
			</table>
		</td>
		<td id="mainTd"><iframe name="mainFrame" id="mainFrame" width="100%" height="100%"  frameborder="1" src="<%=request.getContextPath()%>/AllEvaluatedRule_getRuleList.action"></iframe></td>
	</tr>
</table>
</body>
 </html>
<script>
	//alert(bodyId.clientWidth)
	mainTd.style.width = parseInt(bodyId.clientWidth) - 260;
</script>