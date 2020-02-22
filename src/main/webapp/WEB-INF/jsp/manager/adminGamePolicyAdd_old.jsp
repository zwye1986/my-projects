<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>wan商城</title>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>

<script type="text/javascript">
$(function(){
	$("#tj").click(function(){
		$("#gameForm").attr("action","${ctx}/manager/adminGamePolicyDealAdd");
		$("#gameForm").submit();
	});
});
</script>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 30px;
	background-color:#3a3a3a;
}
table{padding-bottom:30px; padding-left:15px;}
input{ height:33px; width:100%;}
textarea{height:100px;width:100%;}
-->
</style>
</head>
<body >
<form name="gameForm" id="gameForm" action="" method="post">
<input type="hidden" name="gameId" value="${gamePolicy.gameId }" />
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="${ctx }/images/xzbg.jpg">
<tr height="45" ><td width="100"><img src="${ctx }/images/xzbtn.png"/></td><td width="100%"></td><td><img src="${ctx }/images/tjbtn.png" id="tj" style="cursor:pointer;"/></td></tr>
<tr height="45" ><td width="100">点击次数/天</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input type="text" name="clicks" value="${gamePolicy.clicks }"/></td></tr>
<tr height="45"  ><td width="100">游戏押金</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
<input type="text" name="deposit" value="${gamePolicy.deposit }"/>
</td></tr>
<tr height="45"  ><td width="100">游戏返利</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
<input type="text" name="reward" value="${gamePolicy.reward }"/>
</td></tr>
<tr height="45" ><td width="100">游戏惩罚</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input type="text" name="punish" value="${gamePolicy.punish }"/></td></tr>

</table>
</form>
<table width="100%"><tr><td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td></tr></table>
</body>
</html>
