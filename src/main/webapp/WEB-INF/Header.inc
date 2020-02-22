<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<c:set var="cssPath" value="${pageContext.request.contextPath }" scope="request"></c:set>
<c:set var="jsPath" value="${pageContext.request.contextPath }" scope="request"></c:set>

<script type="text/javascript">
	var imgPath_session = '${ctx}'+'/images/alerts';
</script>