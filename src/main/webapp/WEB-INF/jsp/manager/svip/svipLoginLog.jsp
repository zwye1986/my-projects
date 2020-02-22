<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx }/css/manager/main.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<link href="${ctx }/css/jquery.msgbox.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="${ctx }/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.msgbox.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/js/page.js"></script>
<script type="text/javascript"
	src="${ctx }/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" />
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
	background-color: #f1f1f2;
	color: #5c5c5c;
}

.cx {
	width: 59px;
	height: 22px;
	background: url(${ctx}/images/admin/cx_btn.png) no-repeat;
	cursor: pointer;
	border: none;
	color: #fff;
}

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

.selected td {
	background: #e1f6f1;
}

#del td{
	background: #D6614D;
}

.date {
	height: 22px;
	width: 25%;
}

.money {
	height: 22px;
	width: 15%;
}

#btn {
	width: 86px;
	height: 31px;
	background: url(${ctx}/images/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}
-->
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#sb").click(function(){
			 $("input[name='currentPage']").val(1);
			 $("#mainForm").attr("method","post").submit();
		 });
	});
</script>
</head>

<body style="padding: 25px;">
	<form action="${ctx }/manager/svip/getLoginLog" method="get"
		target="_self" id="mainForm" name="mainForm">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td height="62" bgcolor="#3b3a3f"
								style="border-bottom: 1px solid #d5d1c8; border-top: 1px solid #d5d1c8; padding: 0 20px;"><table
									width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="39"><table width="100%" border="0"
												cellspacing="0" cellpadding="0"><tr><td><input type="hidden" name="$.type" value="${condition.type }" /></td></tr>
											</table></td>
										<td style="width: 15%;"><label class="txt">SVIP帐号：</label>
														<input type="text" name="$.mobileNumber" class="phone"
														value="${condition.mobileNumber}"></td>
													<td><input type="button" class="cx" id="sb" value="查询" /></td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			<tr>
				<td><table id="data" width="100%" border="0" cellpadding="0"
						cellspacing="1" bgcolor="#e2e2e2">
						<tr>
							
							<td width="3%" bgcolor="e5e6e6" class="STYLE6" height="40">序号
							<input type="hidden" name="pageSize" id="page.pageSize"
								value="${page.pageSize }" />
							<input type="hidden" name="currentPage" id="page.currentPage"
								value="${page.currentPage }" /></td>
								<td bgcolor="e5e6e6" class="STYLE6">SVIP帐号</td>
								<td bgcolor="e5e6e6" class="STYLE6">登录次数</td>
								<td bgcolor="e5e6e6" class="STYLE6">最近登录时间</td>
						</tr>
						<tbody>
							<c:forEach items="${list }" var="item"
								varStatus="s" begin="0">
								<tr >
									<td height="40" bgcolor="#FFFFFF" class="STYLE6">${(page.currentPage-1)*page.pageSize+(s.index+1) }</td>
									<td bgcolor="#FFFFFF" class="STYLE6">${item.mobileNumber }</td>
									<td bgcolor="#FFFFFF" class="STYLE6" >${item.count }</td>
									<td bgcolor="#FFFFFF" class="STYLE6"><fmt:formatDate
											value="${item.lastestTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</c:forEach>
						</tbody>
						<tr bgcolor="#FFFFFF">
							<td colspan="4" height="30"><%@include
									file="../../../PaginationMore.inc"%></td>
						</tr>
					</table>
					<table width="100%" style="line-height: 48px;">
						<tr>
							<td align="center" style="color: #666;" >苏ICP备13017605号
								2013江苏维纳达软件技术有限公司</td>
						</tr>
					</table>

					</form>
</body>
</html>
