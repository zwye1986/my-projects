<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${ctx }/css/footer.css" rel="stylesheet" />
<link href="${ctx }/css/agreement.css" rel="stylesheet" />
<link rel="icon" href="${ctx }/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">
<title>用户注册协议</title>
</head>
<body>
	<div class="cntwrappe">
			<tiles:insertAttribute name="Content" />
	</div>
<tiles:insertAttribute name="Footer" />	