<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
-->
</style></head>

<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>

    <td width="225" valign="top"><iframe height="100%" width="100%"  border="0" frameborder="0" src="${ctx }/manager/left"></iframe></td>
   
    <td valign="top"><iframe name="rightFrame"  height="100%" width="100%" border="0" frameborder="0" src="${ctx }/manager/welcome"></iframe></td>

  </tr>
</table>
</body>
</html>
