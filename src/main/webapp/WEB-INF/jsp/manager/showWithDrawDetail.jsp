<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js"></script>

<style type="text/css">
.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>

</head>
<body>
		<table class="table">
			<tbody>
				<tr></tr>
			</tbody>
		</table>
		<table class="table">
			<tbody>
			<c:forEach items="${res }" var="item"
								varStatus="s" begin="0">
				<tr>
					<td class="" style="text-align: right" width="45%">交易流水号：</td>
					<td class="" style="text-align: left">${item.serialNumber }</td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">姓名：</td>
					<td class="" style="text-align: left">${item.userName }</td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">手机号码：</td>
					<td class="" style="text-align: left">${item.mobileNumber }</td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">提现金额：</td>
					<td class="" style="text-align: left">${item.amount }</td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">提现时间：</td>
					<td class="" style="text-align: left"><fmt:formatDate
											value="${item.dateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">状态：</td>
					<td class="" style="text-align: left"><c:choose>
											<c:when test="${item.status eq 0 }">成功</c:when>
											<c:when test="${item.status eq 1 }">失败</c:when>
											<c:when test="${item.status eq 2 }">处理中</c:when>
											<c:when test="${item.status eq 3 }">冻结中</c:when>
											<c:otherwise>未知状态</c:otherwise></c:choose></td>
				</tr>
				<c:if test="${item.status eq 0 }">
				<tr>
					<td style="text-align: right">到账成功时间：</td>
					<td style="text-align: left"><fmt:formatDate value="${item.withdrawalSuccessTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="" style="text-align: right" width="45%">卡号：</td>
					<td class="" style="text-align: left">${item.cardNumber }</td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">银行卡状态：</td>
					<td class="" style="text-align: left"><c:choose>
											<c:when test="${item.cardStatus eq 0 }">已删除</c:when>
											<c:when test="${item.cardStatus eq 1 }">正常</c:when>
											<c:otherwise>未知状态</c:otherwise></c:choose></td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">银行名称：</td>
					<td class="" style="text-align: left">${item.bankName }</td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">支行：</td>
					<td class="" style="text-align: left">${item.subBankName }</td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">支行所在省／直辖市：</td>
					<td class="" style="text-align: left">${item.province }</td>
				</tr>
				<tr>
					<td class="" style="text-align: right" width="45%">支行所在城市：</td>
					<td class="" style="text-align: left">${item.cityName }</td>
				</tr>
				
</c:forEach>
<%-- <tr>
					<td class="" style="text-align: right" width="45%">用户余额状况：</td>
					<td class="" style="text-align: left">${msg }</td>
				</tr> --%>
			</tbody>
		</table>
</body>
</html>