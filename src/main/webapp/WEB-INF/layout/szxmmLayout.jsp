<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/szxmm.css" rel="stylesheet" />
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<script type="text/javascript">
var imgPath_session = '${ctx}'+'/images/alerts';
</script>
<title>设置新密码</title>
</head>
<body>
<tiles:insertAttribute name="Content" />
	
<tiles:insertAttribute name="Footer" />