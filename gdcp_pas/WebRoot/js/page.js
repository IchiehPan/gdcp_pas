function pre(){
	if(form1.pageNo.value!=1){
		form1.pageNo.value = parseInt(form1.pageNo.value) - 1;
	}
	pageQuery();
}

function next(){
	if(form1.pageNo.value!=form1.totalPage.value){
		form1.pageNo.value = parseInt(form1.pageNo.value) + 1;
	}
	pageQuery();
}

function firstPage(){
	form1.pageNo.value = 1;
	pageQuery();
}

function endPage(){
	form1.pageNo.value = form1.totalPage.value;
	pageQuery();
}

function gotoPage(obj){
	//form1.pageNo.value = obj.options[obj.selectedIndex].value;
	if(parseInt(obj.value)>parseInt(form1.totalPage.value)){
		obj.value=form1.totalPage.value;
	}
	form1.pageNo.value=obj.value;
	//alert(obj.value);
	pageQuery();
}

function pageQuery(){
	form1.submit();
}