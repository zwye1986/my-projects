function onPageSizeChange(value)
{
	document.getElementById("page.pageSize").value=value;
	$("#mainForm").submit();
}

//分页验证
function paginate(forward){
	if(forward)	{
		document.getElementById("page.currentPage").value=forward;
	}else{
		var temp=document.getElementById("forwardPage").value;		
 		if(temp=="")
 		{
 			top.jQuery.alerts.alert('输入的页数为空。','提示');
 			return false;
 		}
     	var reg = /^[0-9]\d*$/;
     	if(!reg.test(temp)) {
     		top.jQuery.alerts.alert('输入的页数必须为数字。','提示');
     		return false;
     	} 
     	if( temp < 1 || temp > parseInt('${page.totalPages}'))
     	{
     		top.jQuery.alerts.alert('输入的页数超过了显示的范围。','提示');
     		return false;
     	}
		document.getElementById("page.currentPage").value=document.getElementById("forwardPage").value;
	}
	$("#mainForm").submit();
}