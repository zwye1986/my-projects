<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>报表管理</title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 30px;
	background-color: #f1f1f2;
	color: #3b3a3f;
}

table {
	padding-bottom: 30px;
	padding-left: 15px;
	margin-top: 50px;
}

table td {
	color: #3b3a3f;
	text-align: center;
}

input {
	height: 33px;
	width: 100%;
}

textarea {
	height: 300px;
	width: 100%;
}

a{
	        	text-decoration: none;
	        	color: black;
	    	}
-->
</style>

</head>

<body>
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr height="90">
			<td><a href="${ctx }/manager/chart/account" target="_self" ><img src="${ctx}/images/admin/pic05.png"  title="用户每日实际充值金额,用户每日实际提现金额" /></a></td>
			<td><a href="${ctx }/manager/chart/recharge" target="_self"><img src="${ctx}/images/admin/pic02.png" title="查询当月用户每日实际充值金额"/></a></td>
			<td><a href="${ctx }/manager/chart/withdrawal" target="_self"><img src="${ctx}/images/admin/pic03.png" title="查询当月用户每日实际提现金额"/></a></td>
			<td><a href="${ctx }/manager/chart/estimated/cost" target="_self"><img src="${ctx}/images/admin/pic04.png" title="查询用户在指定时间段内预计提现金额"/></a></td>
		</tr>
		<tr height="45">
			<td><a href="${ctx }/manager/chart/account" target="_self" >日充值、提现对比</a></td>
			<td><a href="${ctx }/manager/chart/recharge" target="_self">月充值曲线图</a></td>
			<td><a href="${ctx }/manager/chart/withdrawal" target="_self">月提现曲线图</a></td>
			<td><a href="${ctx }/manager/chart/estimated/cost" target="_self">指定时间段内提现金额趋势图</a></td>
		</tr>
		<tr height="90">
			<td><a href="${ctx }/manager/chart/withdrawalsuccess" target="_self"><img src="${ctx}/images/admin/pic04.png" title="统计每天财务实际打款成功金额"/></a></td>
			<td><a href="${ctx }/manager/chart/users/hour" target="_self"><img src="${ctx}/images/admin/pic06.png" /></a></td>
			<td><a href="${ctx }/manager/chart/users/day" target="_self"><img src="${ctx}/images/admin/pic07.png" /></a></td>
			<td><a href="${ctx }/manager/chart/age/ratio" target="_self"><img src="${ctx}/images/admin/pic08.png" /></a></td>
		</tr>
		<tr height="45">
			<td><a href="${ctx }/manager/chart/withdrawalsuccess" target="_self">财务月打款成功曲线图</a></td>
			<td><a href="${ctx }/manager/chart/users/hour" target="_self">用户当日新增统计</a></td>
			<td><a href="${ctx }/manager/chart/users/day" target="_self">用户月新增统计</a></td>
			<td><a href="${ctx }/manager/chart/age/ratio" target="_self">用户年龄段统计</a></td>
		</tr>
		<tr height="90">
 			<td><a href="${ctx }/manager/chart/users/getAllInfo" target="_self"><img src="${ctx}/images/admin/pic04.png" /></a></td>
       <%--  <td><a href="${ctx }/manager/chart/users/getAllCapitalInfo" target="_self"><img src="${ctx}/images/admin/pic04.png" /></a></td> --%>
      	<td><a href="${ctx }/manager/chart/income/ratio" target="_self"><img src="${ctx}/images/admin/pic10.png" /></a></td>
			<td><a href="${ctx }/manager/chart/game/count" target="_self"><img src="${ctx}/images/admin/pic11.png" /></a></td>
			<td><a href="${ctx }/manager/chart/depost/geter" target="_self"><img src="${ctx}/images/admin/pic12.png" /></a></td>
		</tr>
		<tr height="45">
			 <td><a href="${ctx }/manager/chart/users/getAllInfo" target="_self">用户资金总览</a></td>
			<%-- <td><a href="${ctx }/manager/chart/users/getAllCapitalInfo" target="_self">用户资金总览</a></td> --%>
			<td><a href="${ctx }/manager/chart/income/ratio" target="_self">用户收入段统计</a></td>
			<td><a href="${ctx }/manager/chart/game/count" target="_self">游戏点击量前十名</a></td>
			<td><a href="${ctx }/manager/chart/depost/geter" target="_self">各保证金领取人数</a></td>
		</tr>
		<tr height="90">
 			<td><a href="${ctx }/manager/chart/sex/ratio" target="_self"><img src="${ctx}/images/admin/pic09.png" /></a></td>
		</tr>
		<tr height="45">
			 <td><a href="${ctx }/manager/chart/sex/ratio" target="_self">用户性别比</a></td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td align="center" style="color: #666;">苏ICP备13017605号
				2013江苏维纳达软件技术有限公司</td>
		</tr>
	</table>
</body>
</html>
