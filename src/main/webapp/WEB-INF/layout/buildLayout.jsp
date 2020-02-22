<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>


<tiles:insertAttribute name="Header" />
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/build.css" rel="stylesheet" /><style>
body {
background:url(images/build.png);
 background-position: center 0;
 }
</style>
	<div id="cntwrapper"> 
		<div id="content">
			<tiles:insertAttribute name="Content" />
		</div>
	</div>
	<div style="position: absolute;bottom: 0px; width: 99%" >
<tiles:insertAttribute name="Footer" /></div>