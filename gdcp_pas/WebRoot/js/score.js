var xmlHttp;
function createXmlHttpRequest() {
	if (window.ActiveObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		}
	}
}

function Show(){
  var check;
  var scoreLeve;
  for(var i=0;i<form1.scoreLeve.length;i++){
    if (form1.scoreLeve[i].checked){
    	scoreLeve=form1.scoreLeve[i].value;
    	check= true;
    }       
  }
  
  if(!check){
	  alert("请选定人群");
	 return false;
  }
  
    var obj=document.getElementById('select_template');
    var text=obj.options[obj.selectedIndex].text;
     
    var ch1 = encodeURI(encodeURI(text));
    var ch2 = encodeURI(encodeURI(scoreLeve));
	xmlHttp = createXmlHttpRequest();
	xmlHttp.onreadystatechange = go;
	var url = "show_Look.action?scoreLeve="+ch2+"&DeptList="+ch1 ;
	xmlHttp.open("POST", url, true);
	xmlHttp.send(null);
}

var dataXml;
function toshow(){  
	document.getElementById("T").value=dataXml;
	window.frames["frame"].location.reload();
	} 

function go() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			dataXml=xmlHttp.responseText; 
			toshow();  
		} else {
			alert("数据有误" + xmlHttp.status);
		}

	}

}

function radioChange(){
	var test=false;
	 var topWin = window.document.getElementById("frame").contentWindow;
	
		    if (form1.checkRadio[0].checked ==true ){
		    	test=true;
		    }
		    
		    if(test){
		    	topWin.document.getElementById("divPieChart").style.display="none";
		    	topWin.document.getElementById("divBarChart").style.display="block";
		    }else{
		    	topWin.document.getElementById("divBarChart").style.display="none";
		    	topWin.document.getElementById("divPieChart").style.display="block";
		    } 
		  
}

