<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script>
	$(document).ready(function() {
			$.post("${ctx}/christmasActive.html", null, function(data) {
				  var str = '';
			      if (data != "") {
				  $.each(data.data, function(i, item) {
					   str+="<label>"+item.id+"</label><br>";
					 $("#result1").html(str);
			         });
			      } 

		});
		
			$.post("${ctx}/christmasActiveInvite.html", null, function(data) {
				  var str = '';
			      if (data != "") {
				  $.each(data.data, function(i, item) {
					   str+="<label>"+item.id+"</label><br>";
					 $("#result2").html(str);
			         });
			      } 

		});
		
	});
</script>
<input type="button" id="active" value="活动"/>
<input type="button"id="inviteuser" value="推荐人"/>
<input type="text" value="" id="result">
<div id="result1">
</div>

<div id="result2">
</div>

