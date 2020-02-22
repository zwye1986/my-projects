<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description"
	content="蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以拿到轻松的返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。" />
<meta name="keywords"
	content="蛙宝网,蛙宝,赚钱,注册返现,wowpower,挖宝,网赚,注册提现,	理财,返利,积分活动,打码,网上挣零花钱,赚钱,点击游戏,网赚项目,注册网赚" />

<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<script type="text/javascript" src="${ctx }/js/user.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>
<script type="text/javascript" src="${ctx }/js/signin20120413.js"></script>
<link href="${ctx}/css/home.css" type="text/css" rel="stylesheet">
<link href="${ctx}/css/mod_sign.css" type="text/css" rel="stylesheet">
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$.post("${ctx}/getSignInByAllCount", {},
								function(data) {
									$("#signinnum").html(data.dayHasSignIn);
								});

						$
								.post("${ctx}/getcustomerSignInMonthCount", {},
										function(data) {
											$("#signincount").html(
													data.monthHasSignIn);
										});

						$.post("${ctx}/issignIn", {}, function(data) {
							if (data.flag == "havesignIn"
									|| data.flag == "signInsuccess") {
								$("#ysigninstate1").show();
							} else {
								$("#nsigninstate1").show();
							}
						});

						var myDate = new Date;
						var year = myDate.getFullYear();
						$("#year").html(year);
						var month=myDate.getMonth()+1;
						$("#month").html(month);
						$("#nsigninstate1").click(function() {
											$.post("${ctx}/user/account/signIn",{},
															function(data) {
																if (data.flag == "havesignIn"|| data.flag == "signInsuccess") {
																	$("#nsigninstate1").hide();
																	$("#ysigninstate1").show();
																	$.post("${ctx}/getSignInByAllCount",{},
																					function(data) {
																						$("#signinnum").html(data.dayHasSignIn);
																					});

																	$.post("${ctx}/getcustomerSignInMonthCount",{},
																					function(data) {
																						$("#signincount")
																								.html(data.monthHasSignIn);
																					});
																	var date = new Date;
																	var month = date.getMonth() + 1;
																	changeMonth(month);
																}else if(data.flag=="signInnotTime"){
																	layer.msg(data.msg);
																} else {
																	$("#ysigninstate1").hide();
																	$("#nsigninstate1").show();
																}
															});
										});

						$.post("${ctx}/issignIn", {}, function(data) {

							if (data.flag == "havesignIn") {
								$("#ysigninstate1").show();
							} else {
								$("#nsigninstate1").show();
							}
						});
						//天气
						var param = {
							type : 'roleplay',
							page : '1'
						};
						$.get("${ctx}/getWeather", param, function(data) {
							//	$("#myweather").html(data);
						});

					});
</script>

<!--wrapper start-->
<div id="wrapper">
	<div class="mod-sign-top"></div>
	<div class="mod-sign-bottom">
		<div class="sign-bottom-inner">
			<div class="sign-left">
				<h2>签到说明</h2>
				<ul>
					<li>
						<dl>
							<dt>
								<h4>任务简介：</h4>
							</dt>
							<dd>用户登录后可每天签到一次</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<h4>玩法简介：</h4>
							</dt>
							<dd>点击“立即签到”按钮即可</dd>
						</dl>
					</li>
					<li>
						<dl>
							<dt>
								<h4>任务奖励：</h4>
							</dt>
							<dd>
								<table style="width: 100%;" cellpadding="2" cellspacing="0"
									border="1" bordercolor="#595959">
									<tbody>
										<tr>
											<td><strong><span style="font-size: 16px;">账户总资产</span></strong>
											</td>
											<td><strong><span style="font-size: 16px;">签到奖励（%）</span></strong>
											</td>
										</tr>
										<tr>
											<td>总资产≤1万纳币</td>
											<td>&nbsp;&nbsp;0.067</td>
										</tr>
										<tr>
											<td>1万纳币&lt;总资产<span>≤2万纳币</span>
											</td>
											<td>&nbsp;&nbsp;0.073</td>
										</tr>
										<tr>
											<td>2万纳币&lt;总资产<span>≤6万纳币</span>
											</td>
											<td>&nbsp;&nbsp;0.085</td>
										</tr>
										<tr>
											<td>6万纳币&lt;总资产<span>≤10万纳币</span>
											</td>
											<td>&nbsp;&nbsp;0.094</td>
										</tr>
										<tr>
											<td>总资产&gt;10万纳币</td>
											<td>&nbsp;&nbsp;0.100</td>
										</tr>
									</tbody>
								</table>
							</dd>
						</dl>
					</li>
				</ul>
				<div class="bd-button">
					<c:choose>
						<c:when test="${ empty user }">
							<input type="button" value="我要签到" id="nsigninstate1"
								onclick="window.location.href='${ctx}/login.html'"
								style="display: none" />

						</c:when>
						<c:otherwise>
							<input type="button" value="立即签到" id="nsigninstate1"
								style="display: none" />
						</c:otherwise>
					</c:choose>
					<input type="button" value="已经签到" id="ysigninstate1"
						style="display: none" />
				</div>

			</div>
			<div class="sign-right">
				<!--share start-->
				<div class="notice_rt">
					<div style="display: block;" id="shareshell" class="jiathis_style">
						<span
							style="float: left; display: inline; font-size: 14px; padding-top: 1px; height: 14px; line-height: 16px">分享：</span><a
							class="jiathis_button_tsina" title="分享到新浪微博"><span
							class="jiathis_txt jtico jtico_tsina"></span></a><a
							class="jiathis_button_weixin" title="分享到微信"><span
							class="jiathis_txt jtico jtico_weixin"></span></a> <a
							class="jiathis_button_qzone" title="分享到QQ空间"><span
							class="jiathis_txt jtico jtico_qzone"></span></a> <a
							class="jiathis_button_tqq" title="分享到腾讯微博"><span
							class="jiathis_txt jtico jtico_tqq"></span></a> <a
							class="jiathis_button_renren" title="分享到人人网"><span
							class="jiathis_txt jtico jtico_renren"></span></a> <a target="_blank"
							class="jiathis jiathis_txt jtico jtico_jiathis"
							href="http://www.jiathis.com/share" style=""></a>
					</div>
					<script charset="utf-8" id="shareshell_js" type="text/javascript"
						src="http://v3.jiathis.com/code/jia.js?var=388279"></script>
					<script type="text/javascript">
						setTimeout(
								function() {
									document.getElementById("shareshell_js").src = "http://v3.jiathis.com/code/jia.js?var="
											+ Math.ceil(new Date() / 3600000);
									document.getElementById("shareshell").style.display = 'block';
								}, 1000)
					</script>
				</div>
				<!--share end-->
				<div class="sign-total">
					今天已有<em> <span id="signinnum"></span>
					</em>人签到！您本月签到次数为：<em><span id="signincount"></span></em>
				</div>
				<!--sign-wDay start-->
				<div class="sign-wDay">
					<div class="sign-wDay-top">
						<span class="title">签到情况</span><span class="sign-date"><span
							id="year"></span>年<span id="month"></span>月</span>
					</div>
					<div class="sign-wDay-panel">
						<div class="week">
							<span class="spe">日</span> <span>一</span> <span>二</span> <span>三</span>
							<span>四</span> <span>五</span> <span class="last">六</span>
						</div>
						<div class="date" style="clear: both">
							<span class="off" id="date1">30</span> <span class="off"
								id="date2">31</span> <span class="" id="date3">1</span> <span
								class="" id="date4">2</span> <span class="" id="date5">3</span>
							<span class="" id="date6">4</span> <span class="" id="date7">5</span>
							<span class="" id="date8">6</span> <span class="" id="date9">7</span>
							<span class="" id="date10">8</span> <span class="" id="date11">9</span>
							<span class="" id="date12">10</span> <span class="" id="date13">11</span>
							<span class="" id="date14">12</span> <span class="" id="date15">13</span>
							<span class="" id="date16">14</span> <span class="" id="date17">15</span>
							<span class="" id="date18">16</span> <span class="" id="date19">17</span>
							<span class="" id="date20">18</span> <span class="" id="date21">19</span>
							<span class="" id="date22">20</span> <span class="" id="date23">21</span>
							<span class="" id="date24">22</span> <span class="" id="date25">23</span>
							<span class="" id="date26">24</span> <span class="" id="date27">25</span>
							<span class="" id="date28">26</span> <span class="" id="date29">27</span>
							<span class="" id="date30">28</span> <span class="" id="date31">29</span>
							<span class="" id="date32">30</span> <span class="off"
								id="date33">1</span> <span class="off" id="date34">2</span> <span
								class="off" id="date35">3</span> <span class="off" id="date36">4</span>
							<span class="off" id="date37">5</span> <span class="off"
								id="date38">6</span> <span class="off" id="date39">7</span> <span
								class="off" id="date40">8</span> <span class="off" id="date41">9</span>
							<span class="off" id="date42">10</span>
						</div>
					</div>
				</div>
				<!--sign-wDay end-->
			</div>
		</div>
	</div>
</div>
<!--wrapper end-->