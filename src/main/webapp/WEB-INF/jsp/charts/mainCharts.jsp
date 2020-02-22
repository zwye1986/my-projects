<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>后台管理</title>
   		<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
	    <script type="text/javascript" src="${ctx}/js/fusionCharts/FusionCharts.js"></script>
	    <script type="text/javascript" src="${ctx}/js/fusionCharts/FusionCharts.jqueryplugin.js"></script>
	    <script type="text/javascript">
	    <!--         
	    $(document).ready(function(){
	    	$("#chartContainer").insertFusionCharts({
                swfUrl: "${ctx }/js/fusionCharts/${chart_swf}", 
                dataSource: '${chart_data}', 
                dataFormat: "json", 
                width: "800", 
                height: "500", 
                id: "myChartId"
          }); 
        });
	    // -->
	    </script>
	</head>
	<body style="text-align: center;">
	    <div id="chartContainer"></div>
	</body>
</html>