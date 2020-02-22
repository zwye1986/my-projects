<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript">
$(".hotBank-list a").click(function() {
	$("#banktext").val($(this).attr("title"));
	$("#_banktext").val($(this).attr("bankid"));
	$.ajax({
		type : "POST",
		async : false,
		cache : false,
		dataType : "json",
		url : "${ctx}/"
				+ $(this).attr("bankid")+"/"
				+ $("#city").val()
				+ "/getSubBankJson",
		success : function(data) {
			$("#subBank option")
					.remove();
			$("#subBank")
					.append(
							"<option value='0'>请选择开户支行</option>");
			for ( var i = 0; i < data.length; i++) {
				$("#subBank")
						.append(
								"<option value="+data[i].id+">"
										+ data[i].name
										+ "</option>");
			}
		}
	});
	
});
</script>
<c:forEach var="banks" items="${banks }">
<li class='hotBank-list'><a href='###' bankid='${banks.bankId }' class='hotBank-list-ico ${banks.shortName }' title='${banks.bankName }'></a></li>
</c:forEach>