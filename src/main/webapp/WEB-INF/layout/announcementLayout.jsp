<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>



<tiles:insertAttribute name="Header" />
<link href="${ctx }/css/home.css" rel="stylesheet" />
	<div id="cntwrapper"> 
	      <div id="content">
			<div class="contentL fl">
						<tiles:insertAttribute name="FastTrack" />
						<tiles:insertAttribute name="GameRank" />
				</div>
			<tiles:insertAttribute name="Content" />
		</div>	
	</div>
<tiles:insertAttribute name="Footer" />
		


