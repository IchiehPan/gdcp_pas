<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FusionCharts</title>

</head>
<body>
	<center>
			<div id="divBarChart">
			</div>
			<div id="divPieChart">
			</div>		
	</center>		
			<script language="JavaScript" type="text/javascript"
				src="<%=request.getContextPath()%>/js/FusionCharts.js"></script>

			    <script type="text/javascript">
			        //画图 (以指定 xml格式字符串为数据源)   
			        function DrawChart2(divId, flashFileName, width, height) {
			            var myChartId = new Date().getTime();
			            var myChart = new FusionCharts("FusionCharts/" + flashFileName, myChartId, width, height);
			            myChart.setDataXML('<%=session.getAttribute("dataXml")%>');
			            myChart.addParam("wmode", "Opaque");
			            myChart.render(divId);
			        }
			 
			       DrawChart2("divPieChart", "Column3D.swf", "850", "500");
			       DrawChart2("divBarChart", "Pie3D.swf", "850", "500");
			//两种方法可以显示饼图
			    </script>
			</body>
</html>

<!-- <chart caption="逗你玩"  subcaption="For the year 2015" xAxisName="Dtpt" yAxisName="Score" ><set value="60" label="Item A" color="AFD8F8" /><set value="17" label="Item B" color="F6BD0F" /><set value="23" label="Item C" color="8BBA00" isSliced="1" /><set value="33" label="Item D" color="8BBA00" isSliced="1" /><set value="43" label="Item E" color="8BBA00" isSliced="1" /></chart> -->
