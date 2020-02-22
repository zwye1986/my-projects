<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>




<tiles:insertAttribute name="Header" />
<link href="${ctx }/css/home.css" rel="stylesheet" />
	<div id="cntwrapper" style=" width:100%; background:#fff;border-bottom: 3px solid #f16244; height:55px;"> 
		<div id="content">
			<div class="gTitlewrapper">
				<tiles:insertAttribute name="Title" />
			</div>
			<div id="content">
				<div class="contentL fl">
						<tiles:insertAttribute name="FastTrack" />
						<tiles:insertAttribute name="GameRank" />
				</div>
					<tiles:insertAttribute name="Content" />
			</div>
		</div>
	</div>
<tiles:insertAttribute name="Footer" />
		

