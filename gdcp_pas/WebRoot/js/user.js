	function windowOpen(theURL, winName, features, width, hight, scrollbars,
			top, left) {
		var parameter = "top=" + top + ",left=" + left + ",width=" + width
				+ ",height=" + hight;
		if (scrollbars == "no") {
			parameter += ",scrollbars=no";
		} else {
			parameter += ",scrollbars=yes";
		}
		window.open(theURL, winName, parameter);
	}
	
	function selectAllByChk(chk,checkbox) {
		var size = checkbox.length;
   	 	if(size == null)
        	checkbox.checked =chk.checked;
    	else{
       		for (i = 0; i < checkbox.length; i++) {
            	checkbox[i].checked =chk.checked;
        	}
		}
	}
	
	function getChkValues() {
		var checkValues = document.getElementsByName("checkbox");
        var checkStr = "";
        var j=0;
        for(var i=0; i<checkValues.length; i++){
        	if (checkValues[i].checked) {
        		checkStr += checkValues[i].value + ",";
        		j++;
        	}
        }
        if(j == 0){
			alert("�빴ѡ��Ҫɾ�����û���");
			return;
		}
        if(confirm("ȷ��ɾ������ѡ��" +j + "���û���\nɾ�����޷��ָ���")){
        	window.location="user_delUsers.action?usersStr=" + checkStr;
        	}
	}	
	
	function queryPage() {
 		form1.action = "user_query.action";
		form1.pageNo.value = "1";
		form1.submit();

	}
	