<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
	<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" />
<style type="text/css">
	.query ul li {
    list-style: none outside none;
    padding-bottom: 15px;
    padding-top: 15px;
}
</style>
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
    $("#btnSearch").click(function(){
    	$("#queryListForm").submit();
    });
    var date1 = $("#startTime").val();
    var date2 = $("#endTime").val();
    $("#startTime").datepicker({
   	 changeMonth: true,
   	 changeYear: true
   	     });
	
	$("#endTime").datepicker({
	   	 changeMonth: true,
	   	 changeYear: true
	   	     });
	$("#endTime" ).datepicker("option","dateFormat","yy-mm-dd");
		$("#startTime" ).datepicker("option","dateFormat","yy-mm-dd");
		
		$("#startTime").val(date1);
		
		$("#endTime").val(date2);
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
<form id="queryListForm" name="queryListForm" target="_self" action="${ctx }/user/account/target/list" method="post">
<div class="query">
	<ul>
		<li class="fl">资金流向：
			<input type="radio" class="checkbox" name="$.type" value="1" <c:if test="${condition.type == 1 }"> checked="checked" </c:if>  >收入
			<input type="radio" class="checkbox" name="$.type" value="2" <c:if test="${condition.type == 2 }"> checked="checked" </c:if>  >支出
		</li>
		<li class="fl">类型：
			<input type="radio" class="checkbox" name="$.dealType" value="6" <c:if test="${condition.dealType == 6 }"> checked="checked" </c:if> >充值
			<input type="radio" class="checkbox" name="$.dealType" value="7" <c:if test="${condition.dealType == 7 }"> checked="checked" </c:if> >提现
			<input type="radio" class="checkbox" name="$.dealType" value="0" <c:if test="${condition.dealType == 0 }"> checked="checked" </c:if> >其他
		</li>
		<li class="fl">金额区间：<input type="text" class="text" style="width: 100px" name="$.minAmount" value="${condition.minAmount }" id="minAmount" >--<input type="text" style="width: 100px" class="text" name="$.maxAmount" id="maxAmount" value="${condition.maxAmount }"></li>
		<li class="fl">关键字：<input type="text" class="keyword" name="$.keyword" value="<c:out value="${condition.keyword }" />" ></li>
		<li class="fl">起止日期：<input type="text" class="text" name="$.startTime" id="startTime"  value="${fn:substring(condition.startTime,0,10) }" style="width: 100px" />--<input style="width: 100px" type="text" class="text" id="endTime" name="$.endTime" value="${fn:substring(condition.endTime,0,10) }"  /></li>
		<li class="fl"><input type="button" class="btn" name="test" id="btnSearch"></li>
	</ul>
</div>

<table class="table">
	<thead>
    <tr>
     <th>交易号</th>
      <th>时间</th>
      <th>名称</th>
      <th>收入</th>
      <th>支出</th>
      <th>状态</th>
    </tr>
  </thead>
    <tbody id="tab">
   		<c:forEach var="data" items="${target}">
	        <tr>
	         <td>${data.serialNumber }</td>
	            <td><fmt:formatDate value="${data.time}"  pattern="yyyy-MM-dd HH:mm:ss"/>
	            <input type="hidden" name="pageSize" id="page.pageSize" value="${page.pageSize }" />
				<input type="hidden" name="currentPage" id="page.currentPage" value="${page.currentPage }" /></td>
	            <td>${data.description }</td>
	            <td><c:if test="${data.type == 1 }">+${data.amount } 纳币</c:if></td>
	            <td><c:if test="${data.type == 2 }">-${data.amount } 纳币</c:if></td>
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
</table>
</form>