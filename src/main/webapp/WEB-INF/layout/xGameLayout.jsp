<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>


<tiles:insertAttribute name="Header" />
<script type="text/javascript" src="${ctx }/js/game.js"></script>
	<div id="cntwrapper" style="width:100%; background:#f6f6f6;"> 
	 <div class="gTitlewrapper">
    <p>排行榜 ><em style="font-size:14px; color:#000;">${rankname }排行榜</em></p>
    </div>
     <div id="content">
		<tiles:insertAttribute name="Sidebar"></tiles:insertAttribute>
		<tiles:insertAttribute name="Content"></tiles:insertAttribute>
	</div>
	</div>
	<tiles:insertAttribute name="Footer"></tiles:insertAttribute>
	<script type="text/javascript">
$(function(){ 
	 $("body").css("background","#f6f6f6 center 0");
});
</script>
		

