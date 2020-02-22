<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/user.css" rel="stylesheet" />
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/feedback.css" rel="stylesheet" />

<tiles:insertAttribute name="Header" />
	<div id="cntwrapper"> 
			<div id="content">
					<tiles:insertAttribute name="Content" />
			</div>
		</div>
<tiles:insertAttribute name="Footer" />