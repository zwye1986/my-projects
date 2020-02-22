<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<script type="text/javascript" src="${ctx }/js/user.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>
<link href="${ctx}/css/base.css" rel="stylesheet" />
<link href="${ctx}/css/coolpos.css" rel="stylesheet" />
<link href="${ctx}/css/user.css" rel="stylesheet" />
<script> 




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
     	if( (temp < 1)||(temp >${page.totalPages}))
     	{
     		top.jQuery.alerts.alert('输入的页数超过了显示的范围。','提示');
     		return false;
     	}
		document.getElementById("page.currentPage").value=document.getElementById("forwardPage").value;
	}
	document.all.queryListForm.submit();
}

function viewAdvice(id){
	
	$.get('${ctx}/user/manager/getUserAdviceById', {'id' : id }, 
	function(data) {
		re = data;
		if(re.errorcode=="1"){
			top.jQuery.alerts.alert(re.errormsg); 	
		}else{
		
			top.jQuery.alerts.alert(re.advice);
		}
	});
	
}

function viewReplay(id){
	$.get('${ctx}/user/manager/getUserAdviceById', {'id' : id }, 
			function(data) {
		     re = data;
		if(re.errorcode=="1"){
				top.jQuery.alerts.alert(re.errormsg); 	
			}else{
				re = data;
				top.jQuery.alerts.alert(re.replyContent);
			}
		});
}

</script>
</head>

<div class="contentR fr">
	<div class="RTitle"></div>
	<div class="RC">
		<form id="queryListForm" name="queryListForm" target="_self"
			action="${ctx }/user/feedbackList" method="post">
			<div class="order_c">
						<h5>用户反馈</h5>
						<!-- <h5 style="color: #f16244;">当前积分：${user.level}</h5> -->
						<table style="width: 900px">
							<input type="hidden" name="pageSize" id="page.pageSize"
								value="${page.pageSize }" />
							<input type="hidden" name="currentPage" id="page.currentPage"
								value="${page.currentPage }" />
							<thead>
								<tr>
									<th>姓名</th>
									<th>联系方式</th>
									<th>反馈内容</th>
									<th>反馈时间</th>
									<th>回复时间</th>
									<th>回复内容</th>
									<th>是否回复</th>
								</tr>
							</thead>
							<tbody id="tab">
								<c:forEach items="${list}" var="advice">
									<tr>
									    <td>${advice.name}  </td>
									     <td>${advice.contact}  </td>
									     <td><a href="###" onclick="viewAdvice('${advice.id}');">${fn:substring(advice.advice,0,10)}  </a> </td>
									     <td><fmt:formatDate value="${advice.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
									     <td><fmt:formatDate value="${advice.replytime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
									     <td><a href="###" onclick="viewReplay('${advice.id}');">${fn:substring(advice.replyContent,0,10)}</a> </td>
									     <td> <c:choose>
									             <c:when test="${advice.replayStatus eq 0}">否</c:when>
									             <c:otherwise>是</c:otherwise>
									         </c:choose>  </td>
									    </tr>
								</c:forEach> 
								
							</tbody>
							<tfoot>
								<tr>
									<td colspan="8"><%@include file="../../PaginationMore.inc"%>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
		</form>
	</div>
	<div class="RB"></div>
</div>
