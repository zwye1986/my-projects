<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回复用户反馈</title>
<script type="text/javascript" src="${ctx}/js/uuid.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-1.9.1.min.js"></script>
<!-- uploadify -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/upload/uploadify.css"></link>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
	background-color: #f1f1f2;
	color: #5c5c5c;
}

em {
	font-style: normal;
	color: #fff;
}

.txt {
	color: #fff;
}

.STYLE1 {
	color: #fff;
	font-size: 16px;
}

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE10 {
	color: #000000;
	font-size: 12px;
}

.STYLE19 {
	color: #344b50;
	font-size: 12px;
}

.STYLE21 {
	font-size: 12px;
	color: #3b6375;
	position: relative;
}

.STYLE22 {
	font-size: 12px;
	color: #295568;
}

.id,.phone,.user {
	width: 45%;
	height: 22px;
}

.date {
	height: 22px;
	width: 15%;
}

.money {
	height: 22px;
	width: 15%;
}

#btn {
	width: 86px;
	height: 31px;
	background: url(images/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}

.cx {
	width: 59px;
	height: 22px;
	background: url(images/cx_btn.png) no-repeat;
	cursor: pointer;
	border: none;
	color: #fff;
}

a.jsxg {
	width: 15px;
	text-align: center;
	cursor: pointer;
	color: #0cfec2;
	margin-left: 5px;
}

.selected td {
	background: #e1f6f1;
}

.tck {
	width: 363px;
	height: 268px;
	position: absolute;
	background: #fff;
	right: 180px;
	border: 1px solid #cacaca;
	z-index: 2;
	top: 265px;
}

.tck .tck_t {
	width: 363px;
	height: 40px;
	background: #3b3a3f;
	margin-top: 0px;
	float: left;
}

.tck_xx {
	width: 120px;
	color: #fff;
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	float: left;
}

.del {
	float: right;
	height: 40px;
	width: 40px;
	background: url(images/del.png);
	cursor: pointer;
}

.tck form {
	width: 343px;
	text-align: left;
	color: #3b3a3f;
	font-size: 12px;
}

.tck_list {
	width: 343px;
	line-height: 18px;
}

.tck_la {
	width: 80px;
	text-align: right;
	float: left;
	height: 18px;
	line-height: 18px;
	margin-right: 5px;
}

.qx_btn {
	width: 55px;
	height: 34px;
	background: url(images/qx_btn.png) no-repeat;
	color: #00ce9b;
	border: none;
	cursor: pointer;
	font-size: 16px;
}

.chao_z_btn input {
	background: #4797f1;
	color: #fff;
	width: 59px;
	height: 22px;
	border: none;
	margin-right: 5px;
	cursor: pointer;
}

.chao_z_btn input:hover {
	background: #3b3a3f;
}

.news_add_t {
	color: #3c3c3c;
	font-size: 18px;
}

.news_c {
	width: 100%;
}

.news_c form {
	width: 600px;
	height: 350px;
}

.kv_item {
	line-height: 33px;
	padding: 20px;
}

.news_add_list {
	line-height: 36px;
	width: 85px;
	text-align: right;
	float: left;
	margin-right: 5px;
	color: #3c3c3c;
	font-size: 16px;
}

.news_txt {
	width: 135px;
	height: 24px;
	line-height: 36px;
	background: #fff;
	border: none;
	border: 1px solid #cacaca;
	float: left;
}

.txt_area {
	width: 470px;
	height: 80px;
	background: #fff;
	border: 1px solid #ccccca
}

.news_checkbox {
	float: left;
	margin-top: 10px;
}

.news_check {
	float: left;
	color: #3b3a3f;
	float: left;
}

.news_add_btn {
	width: 600px;
	height: 24px;
	line-height: 48px;
	margin-top: 10px;
	float: left;
}

.news_add_tj,.news_add_qx {
	width: 59px;
	height: 22px;
	border: none;
	background: #4797f1;
	cursor: pointer;
	margin-right: 5px;
	color: #fff;
}

.news_add_tj:hover,.news_add_qx:hover {
	width: 59px;
	height: 22px;
	background: #3b3a3f;
}

.news_select {
	width: 235px;
	height: 24px;
	line-height: 36px;
	background: #fff;
	border: none;
	border: 1px solid #cacaca;
	float: left;
}
-->
</style>
<script type="text/javascript"
	src="${ctx}/js/upload/jquery.uploadify.min.js"></script>
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});

	function submitSendMessage() {
        
		var replyid = $("#replyid").val();
		
		var replycontext = $("#replycontext").val();
		
		$.ajax({
			type : "POST",
			async : false,
			cache : false,
			dataType : "json",
			data : {
				id : replyid,
				replyContent : replycontext
			},
			url : "${ctx}/manager/updateFeedBack",
			success : function(result) {
				if (result.msgcode == 'true') {
					window.parent.$.closePanel();
					 window.parent.location.href = "${pageContext.request.contextPath}/manager/feedBackList";
				} else {
					$.msgbox(result.msg);
				}
			}
		});
	}
	
	function colsepanel(){
		 window.parent.location.href = "${pageContext.request.contextPath}/manager/feedBackList";
		 window.parent.$.closePanel();
	}
</script>


</head>
<body>

	<table align="center" border="0" cellpadding="0" cellspacing="0"
		width="100%">
		<tbody>
			<tr>
				<td ><div class="news_c" align="center">
						<form name="addProjectInvest" id="addProjectInvestForm" method="post">
							<input type="hidden" id="replyid"  value="${advice.id}"/>
							<div class="kv_item">
								<label class="news_add_list">姓;&nbsp;名:</label><input
									class="news_txt" type="text" id="replyname" readonly="readonly"
									name="replyname" value="${advice.name}" />
							</div>
							<div class="kv_item">
								<label class="news_add_list">手机号码:</label><input
									class="news_txt" type="text" readonly="readonly"
									id="replycontact" value="${advice.contact}" />
							</div>
							
							<div class="kv_item">
								<label class="news_add_list">反馈内容:</label>
							</div>
							<div>
								<textarea class="txt_area" id="advice" readonly="readonly" name="advice">${advice.advice }</textarea>
							</div>
							
							<div class="kv_item">
								<label class="news_add_list">回复内容:</label>
							</div>
							<div>
								<textarea class="txt_area" id="replycontext" name="replycontext">${advice.replyContent }</textarea>
							</div>
                            <c:choose>
                                <c:when test="${advice.replayStatus  eq 0 }">
	                                <div class="news_add_btn">
										<!-- 隐藏提交按钮 -->
										<input type="submit" id="SubmitButton" style="display: none" />
										<input class="news_add_tj" value="::提交::" onclick="submitSendMessage();"
											type="button" />
									</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="news_add_btn">
										<!-- 隐藏提交按钮 -->
										<input type="submit" id="colseButton" style="display: none" />
										<input class="news_add_tj" value="关闭" onclick="colsepanel();"
											type="button" />
									</div>
                                </c:otherwise>
                            </c:choose>
                            
						</form>
					</div></td>

			</tr>
		</tbody>
	</table>




</body>
</html>