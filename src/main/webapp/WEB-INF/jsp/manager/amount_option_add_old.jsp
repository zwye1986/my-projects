<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title></title>
	    <link href="${ctx }/css/ec_alerts.css" rel="stylesheet" />
	    <script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
	    <script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
	    <style type="text/css">
	    <!-- body {
	        margin-left: 3px;
	        margin-top: 0px;
	        margin-right: 3px;
	        margin-bottom: 30px;
	        text-align: center;
	        width: 99%;
	        height: 100%;
	    }
	    table {
	    	width: 300px;;
	    	height: 50%;
	    	margin-top: 20%;
	    }
	    -->
		</style>
	</head>

	<body>
		<form action="${ctx }/manager/amount/option/save" method="post" target="_self">
			<input type="hidden" name = "id" value="${amountOption.id }" />
		    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
		        <tr>
		        	<td width="50%" align="right">输入金额:</td>
		        	<td width="*" align="left"><input type="text" name="optionValue"  value="${amountOption.optionValue }" /></td>
		        </tr>
		        <tr height="50px;">
		        	<td  colspan="2"><input type="submit" value=" 保 存 " /></td>
		        </tr>
		    </table>
	    </form>
    </body>
</html>