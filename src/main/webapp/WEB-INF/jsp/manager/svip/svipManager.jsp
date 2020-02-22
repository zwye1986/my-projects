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
<title>创建svip</title>
<script type="text/javascript" src="${ctx}/js/uuid.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/user.js"></script>
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
	background: url(${ctx}/images/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}

.cx {
	width: 59px;
	height: 22px;
	background: url(${ctx}/images/cx_btn.png) no-repeat;
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
	background: url(${ctx}/images/del.png);
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
	background: url(${ctx}/images/qx_btn.png) no-repeat;
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
	width: 800px;
	height: 500px;
}

.kv_item {
	line-height: 36px;
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
	width: 235px;
	height: 24px;
	line-height: 36px;
	background: #fff;
	border: none;
	border: 1px solid #cacaca;
	float: left;
}

.txt_area {
	width: 700px;
	height: 120px;
	background: #fff;
	border: 1px solid #ccccca;
	margin-left: 110px;
	margin-top: -30px;
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
	width: 700px;
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

<script type="text/javascript">
$(document).ready(function() {
	$("#prefix").click(function(){
		$("#a").hide();
		$("#p").show();
	});
	
	$("#areaCode").click(function(){
		$("#p").hide();
		$("#a").show();
	});
});

	function dealAdd() {
		var nickName = $("#nickName").val();
		if (nickName == null || "" == $.trim(nickName)) {
			alert("请填写昵称！");
			return;
		}
		var liveProvince = $("#liveProvince").val();
		var liveCity = $("#liveCity").val();
		var liveArea = $("#liveArea").val();
		var prefix = $("#prefixNumber").val();
		//alert(document.getElementById("areaCode").checked == true);
		//alert(document.getElementById("prefix").checked == true);
		if(document.getElementById("areaCode").checked == true){

			if (liveProvince == null || "" == $.trim(liveProvince)
					|| $.trim(liveProvince) == 0) {
				alert("请选择省份／直辖市！");
				return;
			}

			if (liveCity == null || "" == $.trim(liveCity) || $.trim(liveCity) == 0) {
				alert("请选择城市！");
				return;
			}

			if (liveArea == null || "" == $.trim(liveArea) || $.trim(liveArea) == 0) {
				alert("请选择地区！");
				return;
			}
		}
		
		if(document.getElementById("prefix").checked == true){
			
			if (isNaN(prefix)) {
				alert("帐号前缀是4位数字！");
				return;
			}
		}
		
		var subAccount = $("#subAccount").val();
		if (isNaN(subAccount)) {
			alert("子账号个数请输入数字！");
			return;
		}

		var withdrawalRate = $("#withdrawalRate").val();
		if (isNaN(withdrawalRate)) {
			alert("请输入正确的提现手续费率！");
			return;
		}

		var myRate = $("#myRate").val();
		if (isNaN(myRate)) {
			alert("请输入正确的提现手续费率！");
			return;
		}
		/* alert("prefix : " + prefix); */
		/* $("#createSvipForm").submit(); */
		var params = {
			nickName : nickName,
			liveProvince : liveProvince,
			liveCity : liveCity,
			liveArea : liveArea,
			subAccount : subAccount,
			withdrawalRate : withdrawalRate,
			myRate : myRate,
			prefix:prefix
		};
		$.ajax({
			type : "POST",
			async : true,
			data : params,
			cache : false,
			url : "${ctx }/manager/svip/createSvip",
			success : function(data) {
				if(data.resCode == 0){
					alert(data.resMsg);
					return;
				}else{
					window.location.href = "${ctx}/manager/svip/getSubs";
				}
			}
		});
	}
</script>


</head>
<body>

	<table style="padding: 25px;" align="center" border="0" cellpadding="0"
		cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td height="30"><table border="0" cellpadding="0"
						cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td style="border-bottom: 2px solid #d2d2d2; padding: 0 20px;"
									height="62"><table border="0" cellpadding="0"
										cellspacing="0" width="100%">
										<tbody>
											<tr>
												<td height="39"><table border="0" cellpadding="0"
														cellspacing="0" width="100%">
													</table></td>
												<td class="news_add_t">创建svip</td>
											</tr>
										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>

			<tr>
				<td height="500"><div class="news_c" align="center">
						<form name="createSvip" id="createSvipForm" method="post"
							action="">
							<div class="kv_item">
								<label class="news_add_list">svip昵称:</label> <input
									class="news_txt" type="text" name="nickName" id="nickName"
									value="" maxlength="10" />
							</div>
							<div class="kv_item" style="text-align: left" >
							<span style="margin-left: 20px"><input type="radio" name="type"  id="areaCode" value="" checked="checked" /></span><span>指定帐号区号</span>
									<span><input type="radio" name="type"  id="prefix"  /></span><span>指定帐号前缀</span>
									
							</div>
							<div class="kv_item" style="display: none;" id="p">
								<label class="news_add_list">帐号前缀:</label> <input
									class="news_txt" type="text" name="prefixNumber" id="prefixNumber"
									style="width: 50px"
									value="" maxlength="4" />
							</div>
							<div class="kv_item" id="a">
								<label class="news_add_list">地区:</label>
								<div style="margin-left: -10px; text-align: left">
									<select id="liveProvince" name="liveProvince"
										style="text-align: center;"
										onchange="moveCity('${ctx}/user/manager/getCityJson');">
										<option value="0">选择省／直辖市</option>
										<c:forEach var="item" items="${liveProvince }">
											<option value="${item.provinceid }">${item.province }</option>
										</c:forEach>
									</select>&nbsp;&nbsp;&nbsp;&nbsp; <select id="liveCity" name="liveCity"
										style="text-align: center;"
										onchange="moveArea('${ctx}/user/manager/getAreaJson');">
										<option value="0">选择城市</option>
										<c:choose>
											<c:when test="${not empty liveCity }">
												<c:forEach var="item" items="${liveCity}">
													<option value="${item.cityid }">${item.city }</option>
												</c:forEach>
											</c:when>
										</c:choose>
									</select>&nbsp;&nbsp;&nbsp;&nbsp; <select id="liveArea" name="liveArea"
										style="text-align: center;">
										<option value="0">选择区县</option>
										<c:choose>

											<c:when test="${not empty liveArea }">
												<c:forEach var="item" items="${liveArea}">
													<option value="${item.areaid }">${item.area }</option>
												</c:forEach>
											</c:when>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="kv_item" style="padding-top: 20px">
								<label class="news_add_list">子账号个数:</label> <input
									class="news_txt" type="text" name="subAccount" id="subAccount"
									value="0" />
							</div>

							<div class="kv_item">
								<label class="news_add_list">提现费率:</label> <input
									class="news_txt" type="text" name="withdrawalRate"
									id="withdrawalRate" value="0.00" />
							</div>
							<div class="kv_item">
								<label class="news_add_list">利润分成:</label> <input
									class="news_txt" type="text" name="myRate" id="myRate"
									value="0.00" />
							</div>
							<div class="news_add_btn">
								<!-- 隐藏提交按钮 -->
								<!-- <input type="submit" id="addSvipButton"
						style="display: none" /> -->
								<input class="news_add_tj" value="::提交::" onclick="dealAdd();"
									type="button" /> <input class="news_add_qx" value="取消"
									type="reset" />
							</div>
						</form>
					</div></td>

			</tr>
		</tbody>
	</table>
	<table style="line-height: 48px;" width="100%">
		<tbody>
			<tr>
				<td style="color: #666;" align="center">苏ICP备13017605号
					2013江苏维纳达软件技术有限公司</td>
			</tr>
		</tbody>
	</table>



</body>
</html>