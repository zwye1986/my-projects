<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><head><c:set var="ctx" value="${pageContext.request.contextPath }"	scope="request" /><script type="text/javascript" src="${ctx }/js/user.js"></script><script type="text/javascript" src="${ctx }/js/ec_alert.js"></script><script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script><link href="${ctx}/css/base.css" rel="stylesheet" /><link href="${ctx}/css/coolpos.css" rel="stylesheet" /><link href="${ctx}/css/user.css" rel="stylesheet" /><script> function onPageSizeChange(value){	document.getElementById("page.pageSize").value=value;	document.all.queryListForm.submit();}//分页验证function paginate(forward){	if(forward)	{		document.getElementById("page.currentPage").value=forward;	}else{		var temp=document.getElementById("forwardPage").value;		 		if(temp=="") 		{ 			top.jQuery.alerts.alert('输入的页数为空。','提示'); 			return false; 		}     	var reg = /^[0-9]\d*$/;     	if(!reg.test(temp)) {     		top.jQuery.alerts.alert('输入的页数必须为数字。','提示');     		return false;     	}      	if( (temp < 1)||(temp >${page.totalPages}))     	{     		top.jQuery.alerts.alert('输入的页数超过了显示的范围。','提示');     		return false;     	}		document.getElementById("page.currentPage").value=document.getElementById("forwardPage").value;	}	document.all.queryListForm.submit();}</script></head><div class="contentR fr">	<div class="RTitle"></div>	<div class="RC">		<form id="queryListForm" name="queryListForm" target="_self"			action="${ctx }/user/project/manager" method="post">			<div class="order_c">						<h5>我支持的众筹项目</h5>						<table style="width: 900px">							<input type="hidden" name="pageSize" id="page.pageSize"								value="${page.pageSize }" />							<input type="hidden" name="currentPage" id="page.currentPage"								value="${page.currentPage }" />							<thead>								<tr>									<th>项目名称</th>									<th>套餐</th>									<th>单价</th>									<th  width="5%">份数</th>									<th>总金额</th>									<th>筹款截止日期</th>									<th>项目结束时间</th>									<th  width="15%">筹款失败预计收益</th>									<th width="15%">项目成功预计收益</th>								</tr>							</thead>							<tbody id="tab">								<c:forEach items="${res}" var="item">									<tr>										<td>${item.projectname }</td>										<td>${item.investname}</td>										<td>${item.price }</td>										<td>${item.count }</td>										<td>${item.realAmount }</td>										<td><fmt:formatDate value="${item.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>										<td><fmt:formatDate value="${item.daysdate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>										<td>${item.mAmount }</td>										<td>${item.bAmount }</td>																		</tr>								</c:forEach>							</tbody>							<tfoot>								<tr>									<td colspan="9"><%@include file="../PaginationMore.inc"%>									</td>								</tr>							</tfoot>						</table>					</div>		</form>	</div>	<div class="RB"></div></div>