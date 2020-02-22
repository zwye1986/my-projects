<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css"
	rel="stylesheet" />
<link href="${ctx}/css/account.css" type="text/css" rel="stylesheet">
<link href="${ctx}/css/vipcenter.css" type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/js/i18N/CN/common.i18n_resource_zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/vldt/vldt.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />

<script type="text/javascript">
	var imgPath_session = '${ctx}' + '/images/alerts';
	$(document)
			.ready(
					function() {
						$("#mailcolse").click(function() {
							$("#setmail").hide();
						});
						$("#setemail").click(function() {
							$("#setmail").show();
						});
						$("#passwordcolse").click(function() {
							$("#setpassword").hide();
						});
						$("#setpaswordbutton").click(function() {
							$("#setpassword").show();
						});
						$("#qusetionclose").click(function() {
							$("#setquestion").hide();
						});
						$("#setquestionbutton").click(function() {
							$("#setquestion").show();
						});

						$("#mobileclose").click(function() {
							$("#setmobile").hide();
						});
						$("#setmobilebutton").click(function() {
							$("#setmobile").show();
						});

						$("#open_security").click(function() {
							$("#confirmvip").show();
						});

						$("#confirmvipcolse").click(function() {
							$("#confirmvip").hide();
						});
						$("#confirmvipcancel").click(function() {
							$("#confirmvip").hide();
						});
						$("#confirmvipaffirm")
								.click(
										function() {
											var isAutoRenew = $(
													"input[name='isAutoRenew']:checked")
													.val() || 1;
											$
													.ajax({
														type : "POST",
														async : false,
														cache : false,
														dataType : "json",
														data : {
															isAutoRenew : isAutoRenew
														},
														url : "${ctx }/user/security/center/open",
														success : function(data) {
															if (data.resCode == 0) {
																window.location.href = "${ctx}/user/security/center";
															} else {
																$.alerts
																		.alert(data.resMsg);
															}
														}
													});
										});

						vadty('', 'N');/*加载验证框架*/
						$(".reset").click(
								function() {
									$("a#fancyBox")
											.attr('href', '#' + this.alt)
											.fancybox({
												'scrolling' : 'no'
											}).click();
								});

						$(".reset1").click(
								function() {
									$("a#fancyBox")
											.attr('href', '#' + this.alt)
											.fancybox({
												'scrolling' : 'no'
											}).click();
								});

						$("#btnQuestion")
								.click(
										function() {
											if (document.getElementById(
													"question_form").onsubmit()) {
												$
														.ajax({
															type : "POST",
															async : false,
															cache : false,
															dataType : "json",
															data : {
																id : $(
																		"#securityCenterId")
																		.val(),
																question : $(
																		"#problem")
																		.val(),
																answer : $(
																		"#answer")
																		.val()
															},
															url : "${ctx }/user/security/center/set",
															success : function(
																	data) {
																$(
																		"#setquestion")
																		.hide();
																window.location.href = "${ctx}/user/security/center";
															}
														});
											}
										});

						$("#btnPassword")
								.click(
										function() {
											if (document.getElementById(
													"password_form").onsubmit()) {
												$
														.ajax({
															type : "POST",
															async : false,
															cache : false,
															dataType : "json",
															data : {
																id : $(
																		"#securityCenterId")
																		.val(),
																password : $(
																		"#payment")
																		.val()
															},
															url : "${ctx }/user/security/center/set",
															success : function(
																	data) {
																if (data == 0) {
																	$(
																			"#setpassword")
																			.hide();
																	window.location.href = "${ctx}/user/security/center";
																}
															}
														});
											}
											;
										});

						checkRePassword = function() {
							var showId = document
									.getElementById("confirm_password");
							var _showId = document.getElementById("payment");
							var _showId2 = document
									.getElementById("oldPayment");
							if ($("#payment").val() != $("#confirm_password")
									.val()) {
								showMsg(showId, '两次密码输入不一致');
								return false;
							} else {
								var s = 0;
								$
										.ajax({
											type : "POST",
											async : false,
											cache : false,
											dataType : "json",
											data : {
												password : $("#payment").val(),
												oldPassword : $("#oldPayment")
														.val()
											},
											url : "${ctx }/user/security/center/set/checkPassword",
											success : function(data) {
												if (data == 0) {
													s = 1;
												} else if (data == 2) {
													s = 2;
												} else if (data == 3) {
													s = 3;
												}
											}
										});
								if (s == 1) {
									showMsg(_showId, '密码长度为8～20位,且必须包含数字、字母。');
									return false;
								} else if (s == 2) {
									showMsg(_showId, '支付密码不能和登录密码相同！');
									return false;
								} else if (s == 3) {
									showMsg(_showId2, '原密码输入错误！');
									return false;
								}
							}
							hideMsg(showId);
							return true;
						}

						$("#btnMail")
								.click(
										function() {
											if (document.getElementById(
													"mail_form").onsubmit()) {
												$
														.ajax({
															type : "POST",
															async : false,
															cache : false,
															dataType : "json",
															data : {
																id : $(
																		"#securityCenterId")
																		.val(),
																mail : $(
																		"#mail_")
																		.val()
															},
															url : "${ctx }/user/security/center/set",
															success : function(
																	data) {
																if (data == 0) {
																	$(
																			"#setmail")
																			.hide();
																	window.location.href = "${ctx}/user/security/center";
																}
															}
														});
											}
										});

						$("#btnMobile")
								.click(
										function() {
											if (document.getElementById(
													"mobile_form").onsubmit()) {
												$
														.ajax({
															type : "POST",
															async : false,
															cache : false,
															dataType : "json",
															data : {
																id : $(
																		"#securityCenterId")
																		.val(),
																mobile : $(
																		"#mobile_")
																		.val()
															},
															url : "${ctx }/user/security/center/set",
															success : function(
																	data) {
																window.location.href = "${ctx}/user/security/center";
															}
														});
											}
										});

						checkCode = function() {
							var code;
							$.ajax({
										type : "POST",
										async : false,
										cache : false,
										url : "${ctx }/user/security/center/generate/code",
										success : function(data) {
											code = data;
										}
									});
							var validateCode = document
									.getElementById("validate_code");
							if ($("#validate_code").val() != code) {
								showMsg(validateCode, '验证码输入不正确');
								return false;
							}
							hideMsg(validateCode);
							return true;
						}
						var i=0;
						var count = 60;
						var timer;
						$("#hqyzm").click(function() {
							         if(i==0){
							        	 var mobileNumber = $.trim($("#mobile_").val().toString());
											if (mobileNumber == ''|| mobileNumber.length < 11) {
												showMsg(document.getElementById('mobile_'),'手机号码输入有误');
												return false;
											} else {
												$.ajax({
															type : "GET",
															async : false,
															cache : false,
															data : {
																mobileNumber : mobileNumber
															},
															url : "${ctx }/user/security/center/generate/code",
															success : function(data) {
																if (data == 0) {
																	i++;
																	$(this).attr('disabled',true);
																	timer = setInterval(function() {
																		$("#hqyzm").hide();
																		$("#hqyzmtxt").show();
																		$("#hqyzmtxt").val("获取验证码(" + count + ")");
																		count--;
																		if (count == 0) {
																			clearInterval(timer);
																			count = 60;
																			$("#hqyzm").show();
																			$("#hqyzmtxt").hide();
																			i=0;
																		}
																	},1000);
																} else if (data == 1) {
																	showMsg(document.getElementById('mobile_'),'手机号码有误！');
																} else if (data == 2) {
																	showMsg(document.getElementById('mobile_'),'手机号码不能与账户号码一致！');
																}
															}
														});

											}
							         }else{
							        	 
							         }
										});

						

						$("#closeSecurityCenter")
								.click(
										function() {
											top.jQuery.alerts
													.confirm(
															"确定关闭会员中心吗？关闭后将不享有会员中心提供的优惠和保证服务!",
															"警告",
															function(r) {
																dynamicPost(
																		"${ctx }/user/security/center/close",
																		{});
															});
										});

						$("input[name=pwd]").focus(function() {
							$("input[name=pwd]").hide();
							$("input[name=password]").show().focus();

							$("input[name=password]").css("color", "#000");

						});
						$("input[name=password]").blur(function() {

							if ($(this).val() == "") {
								$("input[name=pwd]").show();
								$("input[name=password]").hide();
							}
						});
					});
//-->
</script>
<div id="main">
	
	<div class="model-box">
		<!--选项卡开始-->
		<div class="tab_box">
			<div class="lyz_tab_left">
				<div class="pro_con111">
					<ul>
						<li
							onclick="window.location.href='${ctx}/user/manager/userDetail'">基本资料</li>
						<li onclick="window.location.href='${ctx}/user/manager/bindCard'">银行卡绑定</li>
						<li
							onclick="window.location.href='${ctx}/user/manager/resetPassword'">修改密码</li>
						<li
							onclick="window.location.href='${ctx}/user/manager/setMessageRule'">短信提醒</li>
						<li onclick="window.location.href='${ctx}/user/manager/upload'">上传头像</li>
						<li class="hover"
							onclick="window.location.href='${ctx}/user/security/center'">会员中心</li>
					</ul>
				</div>
			</div>
			<div class="lyz_tab_right">
				<a style="display: none" id="fancyBox"></a>
				<div class="hover" id="con_one_6" style="display: block;">
					<div class="model-box">
						<c:choose>
							<c:when test="${isvip eq 'false'}">
								<div class="user_hy_enter" style="display: block;">
									<div class="hy_banner"></div>
									<ul class="hy_list">
										<li class="hy_txt">会员中心能够使您拥有更多特权；您的账户资金安全保障更上一层！</li>
										<li class="hy_btn" id="open_security"></li>
										<li><a style="color: #fff;">【月费${month_cost}元】</a> <a
											target="_blank" style="color: #fff;"> <input
												type="checkbox" name="isAutoRenew" checked="checked"
												id="isAutoRenew" value="0" class="recommend_check">到期自动续费
										</a></li>
									</ul>
								</div>
							</c:when>
							<c:otherwise>
								<div class="sec">
									<div class="sec-title">
										<c:if test="${count >0}">
											<a class="icon-bd"> </a>
										</c:if>
										<h2 style="width: 67%; float: left;">
											<input type="hidden" value="${securityCenter.id }"
												id="securityCenterId" /> 您的帐户存在<em class="txt-2">
												${count }</em>项风险
										</h2>
										<h2>
											会员有效期至：<em class="txt-2"><fmt:formatDate
													value="${securityCenter.expiryDate}" pattern="yyyy.MM.dd" /></em>
										</h2>
									</div>
									<ul class="ul-table bod-none">
										<li class="col_2">安全邮箱:</li>
										<li class="col_33">忘记密码时，安全邮箱可以帮您找回您的信息。</li>
										<li class="col_4"><a href="javascript:void(0);"
											id="setemail"
											${securityCenter.mail == null ? "class='set-btn'" : "class='per-btn'" }>${securityCenter.mail == null ? "设置" : "已完善" }</a></li>
									</ul>
									<ul class="ul-table bod-none">
										<li class="col_2">支付密码：</li>
										<li class="col_33">设置支付密码能够很好的保障你的资金安全</li>
										<li class="col_4"><a href="javascript:void(0);"
											id="setpaswordbutton"
											${securityCenter.password == null ? "class='set-btn'" : "class='per-btn'" }>${securityCenter.password == null ? "设置" : "已完善" }</a></li>
									</ul>
									<ul class="ul-table bod-none">
										<li class="col_2">安全问题:</li>
										<li class="col_33">完善您的安全问题，保证您的资料不外泄。</li>
										<li class="col_4"><a href="javascript:void(0);"
											id="setquestionbutton"
											${securityCenter.answer == null ? "class='set-btn'" : "class='per-btn'" }>${securityCenter.answer == null ? "设置" : "已完善" }</a></li>
									</ul>
									<ul class="ul-table bod-none">
										<li class="col_2">绑定手机NO.2：</li>
										<li class="col_33">如果您的手机号码丢失，绑定的第二个手机号将会接管您的账户。</li>
										<li class="col_4"><a href="javascript:void(0);"
											id="setmobilebutton"
											${securityCenter.mobile == null ? "class='set-btn'" : "class='per-btn'" }>${securityCenter.mobile == null ? "设置" : "已完善" }</a></li>
									</ul>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>
<!--选项卡结束-->
<!-- 设置安全邮箱 -->
<div class="sec-box" style="display: none;" id="setmail">
	<div class="sec-box-title">
		<h2>设置安全邮箱</h2>
		<span class="sec-close" id="mailcolse">关闭</span>
	</div>
	<div class="sec-box-c">
		<form method="post" id="mail_form">
			<div class="sec-group">
				<label class="sec-label" style="float: left">邮箱地址：</label> <input
					type="text" id="mail_" vr="0&&11"><span id="span_mail_">
				</span>
			</div>
			<div class="sec-save">
				<a id="btnMail">保存</a>
			</div>
		</form>

	</div>
</div>
<!-- 设置安全问题 -->
<div class="sec-box" style="display: none;" id="setquestion">
	<div class="sec-box-title">
		<h2>设置安全问题</h2>
		<span class="sec-close" id="qusetionclose">关闭</span>
	</div>
	<div class="sec-box-c">
		<form method="post" id="question_form">
			<div class="sec-group">
				<label class="sec-label" style="float: left">问题：</label> <select
					name="problem" id="problem">
					<c:forEach var="data" items="${securityQuestions }">
						<option value="${data.name }"
							${data.name == securityCenter.question ? 'selected' : '' }>${data.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="sec-group">
				<label class="sec-label" style="float: left">答案：</label> <input
					type="text" name="answer" value="${securityCenter.answer }"
					class="text" id="answer" vr="0&&2" lth="40" style="width: 180px">
				<span id="span_answer"> </span>
			</div>


			<div class="sec-save">
				<a id="btnQuestion">保存</a>
			</div>
		</form>

	</div>
</div>

<!-- 设置支付密码 -->
<!--弹出框开始-->
<div class="sec-box" style="display: none;"
	style="display:block; top:600px;" id="setpassword">
	<form method="post" id="password_form">
		<div class="sec-box-title">
			<h2>设置支付密码</h2>
			<span class="sec-close" id="passwordcolse">关闭</span>
		</div>
		<div class="sec-box-c">
			<div class="ts-title">
				设置支付密码：<em>(提示：密码长度为8-20位，且必须包含数字，字母)</em>
			</div>
			<div class="sec-group">
				<label class="sec-label" style="float: left">原始支付密码:</label> <input
					type="text" style="width: 180px" id="oldPaymentText" name="pwd"
					value="初次设置不需要填写该项！"> <input type="password"
					id="oldPayment" name="password" vr="0"
					style="display: none; width: 180px"> <span
					id="span_oldPayment"> </span>
			</div>
			<div class="sec-group">
				<label class="sec-label" style="float: left">支付密码:</label> <input
					type="password" style="width: 180px" id="payment" vr="0"><span
					id="span_payment"> </span>
			</div>
			<div class="sec-group">
				<label class="sec-label" style="float: left">重复密码:</label> <input
					type="password" id="confirm_password" vr="0&&1&&23" lth="24"
					style="width: 180px" code="checkRePassword();"><span
					id="span_confirm_password"></span>
			</div>
			<div class="sec-save">
				<a id="btnPassword">保存</a>
			</div>
		</div>
	</form>
</div>
<!--弹出框结束-->


<!-- 设置第二个手机号码 -->
<div class="sec-box" style="display: none;" id="setmobile">
	<div class="sec-box-title">
		<h2>绑定第二个手机</h2>
		<span class="sec-close" id="mobileclose">关闭</span>
	</div>
	<div class="sec-box-c">
		<form method="post" id="mobile_form">
			<div class="sec-group">
				<label class="sec-label" style="float: left">手机号：</label> <input
					type="text" id="mobile_" style="width: 180px" vr="0&&6"><span
					id="span_mobile_""> </span>
			</div>
			<div class="sec-group">
				<label class="sec-label" style="float: left">验证码：</label> <input
					type="text" style="width: 150px" id="validate_code" vr="0&&23"
					code="checkCode();" size="6"> <input type="button"
					id="hqyzm" style="width: 120px; height: 40px" class="hqyzm"
					value="获取验证码" /> <input type="button" id="hqyzmtxt"
					style="width: 120px; height: 40px; display: none" class="hqyzm"
					value="获取验证码" /><span id="span_validate_code"> </span>
			</div>


			<div class="sec-save">
				<a id="btnMobile">保存</a>
			</div>
		</form>

	</div>
</div>

<!--弹出框开始-->
<div class="sec-box" style="display: none; top: 200px;" id="confirmvip">
	<div class="sec-box-title">
		<h2>提示</h2>
		<span id="confirmvipcolse" class="sec-close">关闭</span>
	</div>
	<div class="sec-box-c">
		<div class="ts-title" style="text-align: center">
			确定开启会员中心吗？</em>
		</div>
		<div class="sec-save"
			style="width: 40%; float: left; margin-top: 30px; margin-left: 10%;">
			<a id="confirmvipaffirm">确认</a>
		</div>
		<div class="sec-save"
			style="width: 30%; float: left; margin-top: 30px;">
			<a id="confirmvipcancel">取消</a>
		</div>
	</div>
</div>
<!--弹出框结束-->