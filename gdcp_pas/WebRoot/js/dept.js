
function contain(str,charset){
    var i;
    for(i=0;i<charset.length;i++)
    	if(str.indexOf(charset.charAt(i))>0)
    		return true;
    	return false;
}

function checkForm(){
    if(document.myform.deptName.value.length==0){
        document.myform.deptName.focus;
        alert("�����벿��");
        return false;
    }
    if(contain(document.myform.deptName.value,"%()!@")){
		alert("�����˷Ƿ��ַ�");
		document.myform.deptName.focus;
		return false;	
	}
    
}

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

	function check(ojb) {
		var userName = document.myform.deptName.value;
		
		xmlHttp = createXmlHttpRequest();

		xmlHttp.onreadystatechange = go;
		var ch = encodeURI(encodeURI(userName));
		var url ="dept_checkName.action?checkDeptName="+ch;

		xmlHttp.open("POST", url, true);
		xmlHttp.send(null);

	}

	function go() {
		if (xmlHttp.readyState == 4) {

			if (xmlHttp.status == 200) {

				if (xmlHttp.responseText == "false") {
					text.innerHTML = "������������"
						document.myform.deptName.select();
					checkDeptName.select();
				} else {
					text.innerHTML = "����������"
				}

			} else {
				alert("��������"+xmlHttp.status);

			}

		}

	}

	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState)
				arrSon[i].click();
		}
	}

	// --���ѡ�򱻵���---
	function ChkSonClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		for ( var i = 0; i < arrSon.length; i++) {
			if (!arrSon[i].checked) {
				cbAll.checked = false;
				return;
			}
		}
		cbAll.checked = true;
	}

	function actionFunction() {
		var checks = form1.chkSon;
		var groupTypeId = new Array();
		for (i = 0; i < checks.length; i++) {
			var obj = checks[i];
			if (obj.checked == true) {
				groupTypeId[i] = obj.value;
			}
		}

		if (!confirm("��ȷ��Ҫɾ����")) {
			window.event.returnValue = false;
			return false;
		}

		form1.action = "dept_deleteBatchRec.action?deletelist=" + groupTypeId;
		form1.submit();

	}

	function queryPage() {
 		form1.action = "dept_query.action";
		form1.pageNo.value = "1";
		form1.submit();

	}
	
	