<%@page import="com.venada.efinance.security.SpringSecurityUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/index.js"></script>
<script type="text/javascript" src="${ctx }/js/regist.js"></script>
<link href="${ctx}/css/login.css" rel="stylesheet" />
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<link rel="icon" href="${ctx }/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="cntwrappe" >
			<tiles:insertAttribute name="Content" />
	</div>
<tiles:insertAttribute name="Footer" />