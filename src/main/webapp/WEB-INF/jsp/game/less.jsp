<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<html>
<head>
<meta charset="UTF-8" />
<title>wan商城</title>
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<link href="${ctx }/css/qr.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>

</head>
 <body>
 <div class="wrap"> 
<div class="yebz">对不起！您的余额不足。。。。。。</div>
<div class="game_btn" ><a class="qr" href="${ctx }/user/account/recharge" target="_blank" style="font-size:28px;" >充&nbsp;&nbsp;&nbsp;&nbsp;值</a></div>
 </div>
</body>
</html>