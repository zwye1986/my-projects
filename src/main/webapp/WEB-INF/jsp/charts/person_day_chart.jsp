<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理</title>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript"
	src="${ctx}/js/fusionCharts/FusionCharts.js"></script>
<script type="text/javascript"
	src="${ctx}/js/fusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" />

<script type="text/javascript">
	    <!--         
	    $(document).ready(function(){
	    	$("#chartContainer").insertFusionCharts({
                swfUrl: "${ctx }/js/fusionCharts/${chart_swf}", 
                dataSource: '${chart_data}', 
                dataFormat: "json", 
                width: "800", 
                height: "400", 
                id: "myChartId"
            }); 
	    	
	    	
	    	
	    	var date1 = $("#startDate").val();
		    var date2 = $("#endDate").val();
		    
		 
		    
	    	$("#startDate").datepicker({
				changeMonth : true,
				changeYear : true
			});
			$("#startDate").datepicker("option", "dateFormat", "yy-mm-dd");
			$("#endDate").datepicker({
				changeMonth : true,
				changeYear : true
			});
			$("#endDate").datepicker("option", "dateFormat", "yy-mm-dd");
				$("#startDate").val(date1);
				$("#endDate").val(date2);
			
	    	$("#searchBtn").click(function(){
	    		var href = window.location.href ;
	    		$("#mainForm").attr("action",href).submit();
	    	});
        });
	    // -->
	    </script>
</head>
<body style="text-align: center;">
	<div id="condition" style="width: 100%; text-align: center;">
		<form action="" method="get" target="_self" name="mainForm"
			id="mainForm">
			<table border="0" width="800px">
				<tr>
					<td width="20" height="45"></td>
					<td width="300">起始时间:<input type="text"  name="$.startDate"
						value="${startDate }" id="startDate"
						readonly="readonly" />
					</td>
					<td width="300">结束时间:<input type="text" name="$.endDate"
						value="${endDate }" id="endDate"
						readonly="readonly" />
					</td>
					<td width="300">查询用户:<input type="text" id="moblieNumber" name="$.moblieNumber"
						value="${moblieNumber}" />
					</td>
					<td width="300">报表类型:<select style="width: 100px"
						name="$.chart_swf">
							<option value="Column3D.swf"
								${chart_swf == 'Column3D.swf' ? 'selected' : '' }>柱状图</option>
							<option value="Line.swf"
								${chart_swf == 'Line.swf' ? 'selected' : '' }>曲线图</option>
							<option value="Pie3D.swf"
								${chart_swf == 'Pie3D.swf' ? 'selected' : '' }>饼状图</option>
					</select>
					</td>
					<td width="*" align="right"><input type="button" value=" 查 询 "
						id="searchBtn" /></td>
					<td width="20"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="chartContainer"></div>
</body>
</html>