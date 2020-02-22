<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${ctx }/css/account.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/level.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
function gamePagination(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page
	};
	$.get("${ctx}/user/account/getLotteryList", param, function(data) {
				$("#con_one_11").html(data);
		});
} 

function gamePaginationNext(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page + 1
	};
	$.get("${ctx}/user/account/getLotteryList", param, function(data) {
		$("#con_one_11").html(data);
});
}

function gamePaginationPrev(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page - 1
	};
	$.get("${ctx}/user/account/getLotteryList", param, function(data) {
		$("#con_one_11").html(data);
});
}

$(function(){
	gamePagination("lottery",1,"","");
	});
</script>

	<!--main-->
	<div id="main">
	<div class="title-items">
			<h2>抽奖记录</a> </h2>
			<b class="line"></b>
		</div>
		
		   <div  id="con_one_11" style="display:block">
            
          </div>
	</div>
	<!--/main-->
