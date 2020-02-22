<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>



<tiles:insertAttribute name="Header" />
<link href="${ctx }/css/user.css" rel="stylesheet" />
	<div id="cntwrapper"> 
		<div id="content">
			<tiles:insertAttribute name="Sidebar" />
			<tiles:insertAttribute name="Content" />
		</div>
		<!--
		<div id="BgDiv"></div>
		  
		<div id="lp_container" style="position: absolute; z-index: 99999; padding: 0px; margin: 0px; top: 50px; left: 562.5px;display: none;">
<a href="${ctx }/lp.html"><img src="${ctx }/images/tc/lp_02.png"/></a>
<span id="lp_close" style="background: url('${ctx}/images/tc/del01.png') no-repeat scroll 0 0 transparent"></span>
</div>
-->
	</div>
<tiles:insertAttribute name="Footer" />
		

