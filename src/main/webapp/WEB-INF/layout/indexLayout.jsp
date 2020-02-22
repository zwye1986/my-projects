<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>




<tiles:insertAttribute name="Header" />
<link href="${ctx }/css/home.css" rel="stylesheet" />
	<div id="cntwrapper"> 
			<tiles:insertAttribute name="Content" />
	</div>
<tiles:insertAttribute name="Footer" />
		

