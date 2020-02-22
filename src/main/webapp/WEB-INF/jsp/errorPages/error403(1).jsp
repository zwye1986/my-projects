<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/error.css" type="text/css" rel="stylesheet">
<div class="content"><div class="error403"> <img src="${ctx }/images/other/e403.png"/><a class="back" href="${ctx }/index.html" style=" right:5px; bottom:28px;"><img src="${ctx }/images/other/back.png"/></a></div> </div>