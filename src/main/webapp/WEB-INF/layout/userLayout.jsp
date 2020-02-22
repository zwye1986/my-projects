<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>



<tiles:insertAttribute name="Header" />
<link href="${ctx }/css/user.css" rel="stylesheet" />
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" />
	<div id="cntwrapper"> 
		<div id="content">
			<tiles:insertAttribute name="Sidebar" />
			<tiles:insertAttribute name="Content" />
		</div>
	</div>
<tiles:insertAttribute name="Footer" />
		

