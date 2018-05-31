//scoreRuleTableLV*的js

//输入框获取焦点时，清空内容
function setNull(obj){
	if(obj.value!=""){
		obj.value = "";
	}
}

// 检查分数合法性，并计算总得分 
function check(obj, scoreMax){

	if(obj.value>scoreMax && obj!=""){
		alert("输入分值不合法：\n不能大于该评分项分值!\n请重新输入");
		obj.value = "";
		obj.focus();
	}else{
		var allScore = 0; 
		for(var i=0; i<document.getElementsByName("fz").length; i++){
			if(document.getElementsByName("fz").item(i).value==''){
				
			}else{
				allScore = allScore + document.getElementsByName("fz").item(i).value*1;
			}
		}
		//document.getElementsByName("heji").item(0).innerHTML = allScore;
		document.getElementById("hejiLabel").innerHTML = allScore;
	}
}
// 检查总得分合法性 
function checkSumScore(obj){
	var scoreMax = 100;
	if(obj.value*1 >scoreMax && obj!=""){
		alert("输入分值不合法：\n不能大于该评分项分值!\n请重新输入");
		obj.value = "";
		obj.focus();
	}else{
		
	}

}

//确保分数输入框只能输入数字
function juasNum(obj){
	obj.value=obj.value.replace(/[^\d]/g,'');
}

//检查总分数是否及格，及格就提交
function checkForm(obj, ty){
	var allScore = document.getElementById("hejiLabel").value*1;
	if(allScore>100 || allScore<=0){
		alert("总分值不符合要求！\n操作失败~");
		return false;
	}

	for(var i=0; i<document.getElementsByName("fz").length; i++){
		if(document.getElementsByName("fz").item(i).value==''){
			alert("还有一些评分项的分数未输入\n操作失败~");
			return false;
		}
	}
	
	document.getElementById("changeTo").value = ty;
	return true;
	
}


