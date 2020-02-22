<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Untitled Document</title>
<style type="text/css">
<!--
* {
	margin: 0;
	padding: 0;
}

body {
	color: #333;
	background: #3b3a3f;
}

ul {
	list-style: none;
	width: 210px;
	padding: 0;
	margin: 0;
}

ul li .icon {
	margin-left: 20px;
	margin-top: 6px;
	width: 24px;
	height: 24px;
	display: inline-block;
	float: left;
	margin-top: 12px;
}

.manager {
	width: 155px;
	height: 50px;
	line-height: 50px;
	display: inline-block;
	float: left;
	text-align: left;
	cursor: pointer;
}

ul li {
	display: inline-block;
	width: 210px;
	height: 50px;
	line-height: 50px;
	text-align: left;
	color: #fff;
	cursor: pointer;
	float: left;
}

ul li:hover {
	background: url(${ctx}/images/admin/lefthover_bg.png) no-repeat;
	color: #03fcbe;
}

.now {
	background: url(${ctx}/images/admin/lefthover_bg.png) no-repeat;
	color: #03fcbe;
}
-->
</style>

</head>

<body>
	<table width="225" height="100%" border="0" cellpadding="0"
		cellspacing="0" style="overflow-y: scroll; background: #3b3a3f;scrollbar-3d-light-color">
		<tr>
			<td height="50" bgcolor="#302f34"
				style="color: #fff; text-align: center; font-size: 18px;">项目名称</td>
		</tr>
		<tr>
			<td width="100%">
				<ul>
					<c:forEach items="${reslist}" var="resource">
						<li><img class="icon" src="${ctx}/images/admin/icon02.png" /><label
							class="manager" style="cursor: pointer;"
							onclick="javascript:window.open('${ctx}${resource.value}','rightFrame')">
								${resource.modelName}</label></li>
					</c:forEach>
				</ul>
			</td>
		</tr>
		<tr>
			<td height="*">&nbsp;</td>
		</tr>
	</table>



</body>
</html>
