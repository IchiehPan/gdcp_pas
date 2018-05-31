<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FusionCharts</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/FusionCharts.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/score.js" charset="GBK"></script>

</head>
<body onload="doparent();">
	
	<center>
	<table>
	<tr>
		<td nowrap>	<div id="divBarChart" style="width: 100%">
			</div>
		<td nowrap>	<div id="divPieChart" style="width: 100%">
			</div>
	<tr>
	</table>
	</center>	
		
			<script language="JavaScript" type="text/javascript"
				src="<%=request.getContextPath()%>/js/FusionCharts.js"></script>

			    <script type="text/javascript">
			    
			    function DrawChart2(divId, flashFileName, width, height) {
		            var myChartId = new Date().getTime();
		            var myChart = new FusionCharts("../FusionCharts/" + flashFileName, myChartId, width, height);
		            myChart.setDataXML("'"+dataXml+"'");
		            myChart.addParam("wmode", "Opaque");
		            myChart.render(divId);
		        }
			    
			     var dataXml;
			     function doparent(){  
			        	dataXml=window.parent.document.getElementById('T').value;
			        	
			        	
			        	   var test=false;
			        	   
			        	  if( window.parent.document.getElementById('form1').checkRadio[0].checked == true){
			        
			        		  DrawChart2('divBarChart', 'Pie3D.swf', '1000', '400');
			        		  DrawChart2('divPieChart', 'Column3D.swf', '1000', '400'); 
			        		  divPieChart.style.display="none";
			        		  
			        	  } 
			        	  
			        	  if( window.parent.document.getElementById('form1').checkRadio[1].checked == true){
			        	
			        		  DrawChart2('divPieChart', 'Column3D.swf', '1000', '400'); 
			        		  DrawChart2('divBarChart', 'Pie3D.swf', '1000', '400'); 
			        		  divBarChart.style.display="none";
			        	  }
			        	  
			       
			        
                  }  
			        
			        
			      
			 
			     
			     
			//两种方法可以显示饼图
			    </script>
			</body>
</html>

<!-- <chart caption="逗你玩"  subcaption="For the year 2015" xAxisName="Dtpt" yAxisName="Score" ><set value="60" label="Item A" color="AFD8F8" /><set value="17" label="Item B" color="F6BD0F" /><set value="23" label="Item C" color="8BBA00" isSliced="1" /><set value="33" label="Item D" color="8BBA00" isSliced="1" /><set value="43" label="Item E" color="8BBA00" isSliced="1" /></chart> -->
