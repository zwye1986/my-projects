<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <link href="${ctx }/css/manager/main.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/page.js"></script>
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<style type="text/css">
<!--


em {
	font-style: normal;
	color: #fff;
}

.txt {
	color: #fff;
}

.STYLE1 {
	color: #fff;
	font-size: 16px;
}

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE10 {
	color: #000000;
	font-size: 12px;
}

.STYLE19 {
	color: #344b50;
	font-size: 12px;
}

.STYLE21 {
	font-size: 12px;
	color: #3b6375;
	position: relative;
}

.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.id,.phone,.user {
	width: 45%;
	height: 22px;
}

.date {
	height: 22px;
	width: 15%;
}

.money {
	height: 22px;
	width: 15%;
}

#btn {
	width: 86px;
	height: 31px;
	background: url(${ctx}/images/admin/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}

.cx {
	width: 59px;
	height: 22px;
	background: url(${ctx}/images/admin/cx_btn.png) no-repeat;
	cursor: pointer;
	border: none;
	color: #fff;
}

a.jsxg {
	width: 15px;
	text-align: center;
	cursor: pointer;
	color: #0cfec2;
	margin-left: 5px;
}

.selected td {
	background: #e1f6f1;
}

.tck {
	width: 363px;
	height: 268px;
	position: absolute;
	background: #fff;
	right: 180px;
	border: 1px solid #cacaca;
	z-index: 2;
	top: 265px;
}

.tck .tck_t {
	width: 363px;
	height: 40px;
	background: #3b3a3f;
	margin-top: 0px;
	float: left;
}

.tck_xx {
	width: 120px;
	color: #fff;
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	float: left;
}

.del {
	float: right;
	height: 40px;
	width: 40px;
	background: url(${ctx}/images/admin/del.png);
	cursor: pointer;
}

.tck form {
	width: 343px;
	text-align: left;
	color: #3b3a3f;
	font-size: 12px;
}

.tck_list {
	width: 343px;
	line-height: 18px;
}

.tck_la {
	width: 80px;
	text-align: right;
	float: left;
	height: 18px;
	line-height: 18px;
	margin-right: 5px;
}

.qx_btn {
	width: 55px;
	height: 34px;
	background: url(${ctx}/images/admin/qx_btn.png) no-repeat;
	color: #00ce9b;
	border: none;
	cursor: pointer;
	font-size: 16px;
}

.chao_z_btn input {
	background: #4797f1;
	color: #fff;
	width: 65px;
	height: 22px;
	border: none;
	margin-right: 5px;
	cursor: pointer;
}

.chao_z_btn input:hover {
	background: #3b3a3f;
}
-->
</style>
	</head>

	<body >
	<table width="100%" cellspacing="0" cellpadding="0" border="0"
		align="center">
		<tbody>
			<tr>
				<td>
					<form action="${ctx }/manager/svip/${fatherid }/getSubDetails" method="post" target="_self"
						id="mainForm" name="mainForm">
						<input type="hidden" name="pageSize" id="page.pageSize"
							value="${page.pageSize }" /> <input type="hidden"
							name="currentPage" id="page.currentPage"
							value="${page.currentPage }" /></form>
						<table width="100%" cellspacing="1" cellpadding="0" border="0" id="resList"
							bgcolor="#e2e2e2">
							<thead>
								<tr>
									<td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div
											align="center">
											<span class="STYLE10">用户帐号</span>
										</div></td>
									<td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div
											align="center">
											<span class="STYLE10">昵称</span>
										</div></td>
									<td width="5%" height="40" bgcolor="e5e6e6" class="STYLE6"><div
											align="center">
											<span class="STYLE10">初始密码</span>
										</div></td>
										
										<td width="5%" height="40" bgcolor="e5e6e6" class="STYLE6"><div
											align="center">
											<span class="STYLE10">提现费率</span>
										</div></td>
										
										<td width="5%" height="40" bgcolor="e5e6e6" class="STYLE6"><div
											align="center">
											<span class="STYLE10">累计提现金额</span>
										</div></td>
										
										<td width="5%" height="40" bgcolor="e5e6e6" class="STYLE6"><div
											align="center">
											<span class="STYLE10">累计扣除手续费</span>
										</div></td>
										

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="item">

									<tr >
										<td height="40" bgcolor="#FFFFFF" class="STYLE6"><div
												align="center">
												<span class="STYLE19">${item.mobileNumber }</span>
											</div></td>
										<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div
												align="center">${item.nickName }</div></td>
										<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div
												align="center">${item.vipInitPassword }</div></td>
												<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div
												align="center">${item.svipRate }</div></td>
												
												<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div
												align="center">${item.totalWithdrawal }</div></td>
												
												<td height="40" bgcolor="#FFFFFF" class="STYLE19"><div
												align="center">${item.totalWithdrawalFee }</div></td>
									</tr>
								</c:forEach>



							</tbody>
						</table>
					</td>
			</tr>
			<tr>
				<td height="50"
					style="background: #fafafb; border: 1px solid #e5e6e6; border-top: none; padding-left: 20px;"><div
						class="footer">
						<%@include file="../../../PaginationMore.inc"%>
					</div></td>
			</tr>
		</tbody>
	</table>


</body>
</html>