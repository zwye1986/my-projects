<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />

<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>

<script>

$(document).ready(function(){
        var linkList = window.parent.document.getElementsByTagName("link"); //获取父窗口link标签对象列表
        var head = document.getElementsByTagName("head").item(0);
        //外联样式
        for (var i = 0; i < linkList.length; i++) {
            var l = document.createElement("link");
            l.rel = 'stylesheet';
            l.type = 'text/css';
            l.href = linkList[i].href;
            head.appendChild(l);
        }
});


function onPageSizeChange(value)
{
	document.getElementById("page.pageSize").value=value;
	document.all.queryListForm.submit();
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
	document.all.queryListForm.submit();
}
</script>
<form id="queryListForm" name="queryListForm" target="_self" action="${ctx }/user/account/withdraw/list" method="post">
<table class="table">
  <thead>
    <tr>
    <th>交易号<input type="hidden" name="pageSize" id="page.pageSize" value="${page.pageSize }" />
	<input type="hidden" name="currentPage" id="page.currentPage" value="${page.currentPage }" /></th>
      <th>提现时间</th>
      <th>支出</th>
      <th>预计到账时间</th>
      <th>VIP预计到账时间</th>
      <th>状态</th>
    </tr>
  </thead>
   <tbody id="tab">
   		<c:forEach var="data" items="${withdraw}">
	        <tr>
	        <td>${data.serialNumber }</td>
	            <td><fmt:formatDate value="${data.time}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            <td><c:if test="${data.type == 2 }">-${data.amount } 纳币</c:if></td>
	            <td><fmt:formatDate value="${data.cashDay}"  pattern="yyyy-MM-dd"/></td>
	            <td><fmt:formatDate value="${data.vipCashDay}"  pattern="yyyy-MM-dd"/></td>
	            <td><c:choose><c:when test="${data.status == 0 }">交易成功</c:when>
	            <c:when test="${data.status == 1 }">交易失败</c:when>
	            <c:when test="${data.status == 2 }">正在处理</c:when></c:choose></td>
	        </tr>
        </c:forEach>
    </tbody>
  <tfoot>
	    <tr>
	      <td colspan="8">
	      	 <%@include file="../PaginationMore.inc"%>
	   	  </td>
	    </tr>
    </tfoot>
</table></form>