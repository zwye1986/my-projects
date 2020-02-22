<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<html>
<head>
<meta charset="UTF-8" />
<title>wan商城</title>
<link href="${ctx }/css/qr.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript">
function closeDialog(){
	parent.$.fancybox.close();
}

$(document).ready(function() {
	$(".qr").click(function(){	
	//	parent.$.fancybox.close(); 
	});
	
	$(".tc").click(function(){	
		top.location.href="${ctx }/usertaskDetail.html";
		/*  $("#form").attr("action","${ctx }/user/account/manager?cate=task");
		 $("#form").attr("method","get");
		 $("#form").submit();
		parent.$.fancybox.close(); */
	});
});
</script>

</head>
<body style="width:815px; height:400px;padding: 0;margin: 0;">
	 <div class="wrap"> 
<ul style="float:left;">
<li style=" font-size:16px;">你好！${user.nickName }</li>
<li>已扣除:<em>${deposit }</em>纳币</li>
<li>余额:<em>${banance }</em>纳币</li>

</ul>
<form action="${ctx }/startGame" id="form" target="_blank" method="post">
<input type = "hidden" name="ids" value="${ids }">
<input type = "hidden" name="gameUrl" value="${game.gameUrl }">
</form>

<div class="game_btn"><a class="tc">开始游戏</a><a class="tc" >查看我的任务</a></div>
 </div>
</body>
</html>
