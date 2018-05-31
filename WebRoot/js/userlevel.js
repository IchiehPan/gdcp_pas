function ChkAllClick(sonName, cbAllId) {
	var arrSon = document.getElementsByName(sonName);
	var cbAll = document.getElementById(cbAllId);
	var tempState = cbAll.checked;
	for (i = 0; i < arrSon.length; i++) {
		if (arrSon[i].checked != tempState)
			arrSon[i].click();
	}
}

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

	if (!confirm("确认要删除？")) {
		window.event.returnValue = false;
		return false;
	}

	form1.action = "userlevel_delUserlevelList.action?UserlevelList="
			+ groupTypeId;
	form1.submit();
}

function delcfm() {
	if (!confirm("确认要修改？")) {
		window.event.returnValue = false;
	}
}

function contain(str, charset) {
	var i;
	for (i = 0; i < charset.length; i++)
		if (str.indexOf(charset.charAt(i)) > 0)
			return true;
	return false;
}

function checkForm() {
	if (document.myform.userLevelName.value.length == 0) {
		document.myform.userLevelName.focus;
		alert("请输入用户身份");
		return false;
	}
	if (contain(document.myform.userLevelName.value, "%()!@！#￥%%……&*（）‘；、。，；")) {
		alert("输入了非法字符");
		document.myform.userLevelName.focus;
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
	var userName = document.myform.userLevelName.value;

	xmlHttp = createXmlHttpRequest();
	xmlHttp.onreadystatechange = go;
	var ch = encodeURI(encodeURI(userName));
	var url = "userlevel_Check.action?userName=" + ch;
	xmlHttp.open("POST", url, true);
	xmlHttp.send(null);

}

function go() {
	if (xmlHttp.readyState == 4) {

		if (xmlHttp.status == 200) {
			if (xmlHttp.responseText == "false") {
				text.innerHTML = "用户身份名不可用"
				document.myform.userLevelName.select();
				username.select();
			} else {
				text.innerHTML = ""
			}

		} else {
			alert("数据有误" + xmlHttp.status);

		}

	}

}

function test() {
	alert("操作无效");
	history.back();
}
