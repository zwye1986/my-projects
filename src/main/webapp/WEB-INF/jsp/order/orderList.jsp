<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<script type="text/javascript" src="${ctx }/js/user.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
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
</script>
</head>

<div class="contentR fr">
	<div class="RTitle"></div>
	<div class="RC">
		<form id="queryListForm" name="queryListForm" target="_self"
			action="${ctx }/order/manager/orderlist" method="post">
			<div class="order_c">
						<h5>我的订单</h5>
						<table style="width: 900px">
							<input type="hidden" name="pageSize" id="page.pageSize"
								value="${page.pageSize }" />
							<input type="hidden" name="currentPage" id="page.currentPage"
								value="${page.currentPage }" />
							<thead>
								<tr>
									<th>名称</th>
									<th>数量</th>
									<th>总价</th>
									<th>订单时间</th>
									<th>付款时间</th>
									<th>订单状态</th>
								</tr>
							</thead>
							<tbody id="tab">
								<c:forEach items="${orderList}" var="order">
									<tr>
										<td><img src="${ctx}/images/user/coolpos.png"
											style="width: 42px; height: 42px; display: inline-block; float: left; margin-left: 60px;" />
											<p
												style="display: inline-block; float: left; margin-left: 5px; line-height: 45px;">${order.goodsName}</p></td>
										<td style="width: 60px">${order.num}</td>
										<td style="width: 70px"><c:if test="${order.totalPrice eq 0.0 }"> 面议</c:if>
											<c:if test="${order.totalPrice > 0.0 }"> ${order.totalPrice}元</c:if>
										     </td>
										
										<td><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
										<td><fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
										<c:set var="tradeStatus" value="${order.tradeStatus}" />
										
										<c:if test="${not empty tradeStatus }">
										
											<c:choose>
												<c:when test="${tradeStatus eq 0}">
													<td style="color: #f16244;">未形成订单</td>
												</c:when>
												<c:when test="${tradeStatus eq 1 }">
													<c:if test="${order.totalPrice eq 0.0 }"> <td style="color: #f16244;">面议</td></c:if>
													<c:if test="${order.totalPrice > 0.0 }">
														<td style="color: #f16244;">
														<a href ="${ctx}/order/manager/orderDetailPayment?id=${order.id}">支付 </a>|
														<a href ="${ctx}/order/manager/orderDetailCancel?Id=${order.id}">取消订单 </a>
														</td>
													</c:if>
												</c:when>
												<c:when test="${tradeStatus eq 2 }">
												<td style="color: #f16244;">完成交易</td>
												
												</c:when>
												
												<c:when test="${tradeStatus eq 3 }">
												  <td style="color: #f16244;">订单取消</td>
												</c:when>
												<c:when test="${tradeStatus eq 4 }">
												  <td style="color: #f16244;">支付中</td>
												</c:when>
												<c:when test="${tradeStatus eq 5 }">
												  <td style="color: #f16244;">支付失败</td>
												</c:when>
												<c:when test="${tradeStatus eq 6 }">
												  <td style="color: #f16244;">支付超时</td>
												</c:when>
												<c:otherwise></c:otherwise>
											</c:choose>
									
										</c:if>
									
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
