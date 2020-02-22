<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<html>
<head>
<meta charset="UTF-8" />
<title>wan商城</title>
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/tc_login.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript">
function closeDialog(){
	parent.$.fancybox.close();
}

$(document).ready(function() {
	parent.location.href = "${ctx }/login.html";
	//  $("#form").submit();
	//	parent.$.fancybox.close();
});
</script>

</head>
<body >
<div class="wrap"> 
<div class="panelForm">
            <div class="form">
              <label class="iphoneform">手机号/用户名：
                <input type="text"  name="username" id="username">
              </label>
            </div>
            <div class="form">
              <label class="iphoneform">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
                <input type="text"  name="username" id="username">
              </label>
            </div>
 </div>
 <div class="panelB"><div class="panelB_L">忘记密码？</div><div class="panelB_R">登陆</div></div>
 <div class="no_register"><a href="#">还没有注册？</a></div>
 <form action="${ctx }/login.html" id="form" target="_blank" method="get">

</form>
</body>
</html>