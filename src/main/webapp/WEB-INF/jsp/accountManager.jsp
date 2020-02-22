<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yma" uri="/yma"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<script type="text/javascript">
	$(document).ready(function() {
		$(".tab").click(function() {
			initTabClass();
			$("#mainForm").attr("action", this.id).submit();
			$(this).addClass("active");
		});

		$("#closeDiv").click(function() {
			$(".zshy").hide();
		});

		var windowWidth = $(window).width();
		var windowHeight = $(window).height();
		var top = windowHeight * 0.2;

		var popupWidth = $("#lp_container").width();

		$("#lp_container").css({

			"left" : (windowWidth - popupWidth) / 2,
			"top" : top
		});

		$(window).scroll(function() {
			var vtop = $(document).scrollTop();
			$("#lp_container").css({
				"top" : vtop + top
			});
		});

		$(window).resize(function() {
			var windowWidth = $(window).width();

			var popupWidth = $("#lp_container").width();

			$("#lp_container").css({

				"left" : (windowWidth - popupWidth) / 2,
				"top" : top
			});
		});

		var activeReward = "${activeReward }";

		if (activeReward == 1) {

			$("#lp_container").slideUp();
		} else {
			$("#BgDiv").css({
				display : "block",
				height : $(document).height()
			});
			$("#lp_container").slideDown();
		}

		$("#lp_close").click(function() {
			$("#BgDiv").css("display", "none");
			$("#lp_container").slideUp();
		});

		var cate = '${cate}';
		if (cate == 'task') {
			taskDetal();
		} else {

			$("#mainForm").submit();

		}
		
		$("#mode_month").click(function(){
			$("#mode_year").removeClass("selected");
			$("#mode_month").addClass("selected");
			$("#payMode").val(1);
			$("#amount_unit").empty();
			$("#amount_unit").html("月");
			var times = $("#amount_input").val();
			if(!isNaN(times)){
				countMoney();
			}
		});
		$("#mode_year").click(function(){
			$("#mode_month").removeClass("selected");
			$("#mode_year").addClass("selected");
			$("#payMode").val(2);
			$("#amount_unit").empty();
			$("#amount_unit").html("年");
			var times = $("#amount_input").val();
			if(!isNaN(times)){
				countMoney();
			}
		});
		
		$("#amount_input").focus(function(){
			$("#amountError").hide();
		});
		
		$("#amount_input").mouseout(function(){
			countMoney();
		});
		
		$("#submitGive").click(function(){
			
			var times = $("#amount_input").val();
			if(times == ""){
				$("#amountError").show();
				return false;
			}
			
			if(isNaN(times)){
				$("#amountError").show();
				return false;
			}
			
			var mode = $("#payMode").val();
			$.post("${ctx}/"+mode+"/"+times+"/"+"/countAmountGivenVip", {}, function(data) {
				$("#pay_fee").empty();
				$("#pay_fee").html(data);
			});
			$(".zshy").hide();
			$.showPanelVip("操作提示", "${ctx }/user/vip/"+mode+"/"+times+"/payForRenewal", 600,
					300, true, "${ctx}");
			return;
		});

	});
	
	function countMoney(){
		var times = $("#amount_input").val();
		if(times == ""){
			$("#amountError").show();
			return false;
		}
		
		if(isNaN(times)){
			$("#amountError").show();
			return false;
		}
		
		var mode = $("#payMode").val();
		$.post("${ctx}/"+mode+"/"+times+"/"+"/countAmountGivenVip", {}, function(data) {
			$("#pay_fee").empty();
			$("#pay_fee").html(data);
		});
	}

	function taskDetal() {
		var str = "<li style='display:none;'><a class='tab ' onclick=\"taskDetail('${ctx }/taskDetail?type=all','content_1')\"  title='content_1' id='${ctx }/user/account/incomeAndPay/list' >所有任务操作</a></li>"
				+ "<li><a class='tab active' title='content_2' onclick=\"taskDetail('${ctx }/taskDetail?type=ing','content_2')\"  id='${ctx }/taskDetail?type=ing'>进行中的任务</a></li>"
				+ "<li><a class='tab' title='content_3' onclick=\"taskDetail('${ctx }/taskDetail?type=over','content_3')\"  id='${ctx }/user/account/withdraw/list'>已完成的任务</a></li>"
				+ "<li><a class='tab' title='content_4' onclick=\"taskDetail('${ctx }/taskDetail?type=record','content_4')\" id='${ctx }/user/account/target/list'>任务惩罚记录</a></li>";
		$(".tabs").html(str);
		$("#mainForm").attr("action", "${ctx }/taskDetail?type=ing").submit();
		$("#taskDetail").addClass("selected");
		$("#accountDetail").removeClass("selected");
	}

	function taskDetail(a, b) {
		$(".tab").each(function() {
			$(this).removeClass("active");
		});
		$("a[title=" + b + "]").addClass("active");
		$("#mainForm").attr("action", a).submit();
	}

	function swich(action, title) {
		var str = " <li><a class='tab active'  title='content_1' id='${ctx }/user/account/incomeAndPay/list' >收支明细</a></li>"
				+ "<li><a class='tab' title='content_2' id='${ctx }/user/account/recharge/list'>充值记录</a></li>"
				+ "<li><a class='tab' title='content_3' id='${ctx }/user/account/withdraw/list'>提现记录</a></li>"
				+ "<li><a class='tab' title='content_4' id='${ctx }/user/account/target/list'>目标查询</a></li>";
		$(".tabs").html(str);
		initTabClass();
		$("#mainForm").attr("action", action).submit();
		$("a[title='" + title + "']").addClass("active");
		$("#taskDetail").removeClass("selected");
		$("#accountDetail").addClass("selected");
		$(".tab").click(function() {
			initTabClass();
			$("#mainForm").attr("action", this.id).submit();
			$(this).addClass("active");
		});
	}

	function initTabClass() {
		$(".tab").each(function() {
			$(this).removeClass("active");
		});
	}

	function renewal() {
		$(".zshy").show();
	}
</script>




<div class="contentR fr">
	<div class="RTitle"></div>
	<div class="RC">
		<div class="RC_top">
			<div class="RC_topTitle">帐户管理</div>
			<!--帐户管理start-->
			<div class="zh_c">
				<div class="zh_top">
					<div class="zh_top_l fl">
						<div class="zh_top_ll fl">
							<p>
								帐户余额:<em>${walletAmount.amount}</em>纳币
							</p>
							<p>
								<label style="float: left;">用户等级：</label>
								<yma:LevelConvertIcoTag level="${level}" vipTag="${vipTag}" />
							</p>
						</div>
						<div class="zh_top_lr fr">
							<input type="button" value="" class="cz_btn"  onclick="javascript:window.location.href='${ctx }/user/account/recharge'"/> <input
								type="button" value="" class="tx_btn" onclick="javascript:window.location.href='${ctx }/user/account/withdrawal'"/>
						</div>
					</div>
					<div class="zh_top_r fr">
						<div class="zh_top_rc fr">
							<c:choose>
								<c:when test="${vipTag eq 1}">
									<p>
										<input type="button" value="" class="vip_hy" />会员到期日：
										<fmt:formatDate value="${securityCenter.expiryDate}"
											pattern="yyyy-MM-dd" />
										<a href="###" onclick="renewal()">会员续费</a>
									</p>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${isExpire eq 1}">
											<p>
												<input type="button" value="" class="vip_hy_dq" />会员已到期<a
													href="${ctx}/vip_index.html">会员续费</a>
											</p>
										</c:when>
										<c:when test="${isExpire eq 0}">
											<p>
												<input type="button" value="" class="pt_hy" /> <a
													href="${ctx}/vip_index.html">升级VIP会员享更多特权</a>
											</p>
										</c:when>
									</c:choose>
								</c:otherwise>

								<%-- <c:when test="${vipTag eq 0}">
									
								</c:when> --%>

							</c:choose>
							<p>
                                                                                <label style="float: left;">注册日期：</label>
                                                                                <fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd" />
                                                        </p>
							<p>
                                <c:choose>
                                   <c:when test="${nextlevel gt 97029901}">
                                      您已经满级
                                   </c:when>
                                   <c:otherwise>
                                     <label style="float: left;">您还差<em>${nextLevelCredits}</em>积分就可以升级到</label>
									 <yma:LevelConvertIcoTag level="${nextlevel}" vipTag="${vipTag}" />
                                   </c:otherwise>
                                </c:choose>							
								
							</p>
						</div>
					</div>
				</div>
				<div class="zh_bottom">
					<ul>
						<li><a>
								<dl>
									<dt>帐户充值额</dt>
									<dd class="acc_icon">
										<img width="65" height="65"
											src="${ctx}/images/user/icon_01.png" />
									</dd>
									<dd class="acc_money">
										<em>${rechargeAmount== null ? '0' : rechargeAmount }</em>纳币
									</dd>
								</dl>
						</a></li>
						<li><a>
								<dl>
									<dt>用户当前投资金额（押金）</dt>
									<dd class="acc_icon">
										<img width="65" height="65"
											src="${ctx}/images/user/icon_02.png" />
									</dd>
									<dd class="acc_money">
										<em>${gameBenefit.deposit}</em>纳币
									</dd>
								</dl>
						</a></li>
						<li><a>
								<dl>
									<dt>帐户收入</dt>
									<dd class="acc_icon">
										<img width="65" height="65"
											src="${ctx}/images/user/icon_03.png" />
									</dd>
									<dd class="acc_money">
										<em>${dealDetailAmount== null ? '0' : dealDetailAmount }</em>纳币
									</dd>
								</dl>
						</a></li>
						<li><a>
								<dl>
									<dt>邀请收入</dt>
									<dd class="acc_icon">
										<img width="65" height="65"
											src="${ctx}/images/user/icon_04.png" />
									</dd>
									<dd class="acc_money">
										<em>${total}</em>纳币
									</dd>
								</dl>
						</a></li>
						<li><a>
								<dl>
									<dt>签到收入</dt>
									<dd class="acc_icon">
										<img width="65" height="65"
											src="${ctx}/images/user/icon_05.png" />
									</dd>
									<dd class="acc_money">
										<em>${signTotal}</em>纳币
									</dd>
								</dl>
						</a></li>
						<li><a>
								<dl>
									<dt>预计收入</dt>
									<dd class="acc_icon">
										<img width="65" height="65"
											src="${ctx}/images/user/icon_06.png" />
									</dd>
									<dd class="acc_money">
										<em>${gameBenefit.reward}</em>纳币
									</dd>
								</dl>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="RC_topaccount">
		<a href="###" id="taskDetail" onclick="taskDetal()">任务明细</a> | <a
			class="selected" id="accountDetail"
			href="${ctx }/user/account/manager"> 帐户明细</a>
	</div>
	<form action="${ctx }/user/account/incomeAndPay/list" method="post"
		target="queryListFrame" name="mainForm" id="mainForm">
		<input type="hidden" name="pageSize" value="10" /> <input
			type="hidden" name="currentPage" value="1" />
	</form>
	<div id="tabbed_box_1" class="tabbed_box">
		<div class="tabbed_area">
			<ul class="tabs doit_bd">
				<li><a class="tab active" title="content_1"
					id="${ctx }/user/account/incomeAndPay/list">收支明细</a></li>
				<li><a class="tab" title="content_2"
					id="${ctx }/user/account/recharge/list">充值记录</a></li>
				<li><a class="tab" title="content_3"
					id="${ctx }/user/account/withdraw/list">提现记录</a></li>
				<li><a class="tab" title="content_4"
					id="${ctx }/user/account/target/list">目标查询</a></li>
			</ul>
			<div class="content" style="height: 500px;">
				<iframe name="queryListFrame" id="queryListFrame"
					style="width: 98%; height: 99%; background-color: transparent"
					allowTransparency="true" frameborder="0" scrolling="auto">
				</iframe>
			</div>
		</div>
	</div>
	<div class="RB"></div>
</div>
<!--zshy start-->
<div class="zshy" style="display: none;">
	<div class="zs_right">
		<div class="nav_title">
			<p>续费</p>
		</div>
		<div class="form-horizontal">
			<div class="control-group">
				<span class="control-label">付费模式：</span>
				<div class="controls-option">
					<label class="selected" _value="month" _type="mode_radio"
						id="mode_month"> <a class="radio-box" href="###"> 按月付费
							<i class="icon-check"></i>
					</a>
					</label> <label class="" _value="year" _type="mode_radio" id="mode_year">
						<a class="radio-box" href="###">按年付费<i class="icon-check"></i>
					</a>
					</label>
					<input type="hidden" id="payMode" value="1" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">开通时长：</label>
				<div class="controls">
					<input id="amount_input" class="input-min" type="text"
						data-value="其它" maxlength="3" style="width: 17%" value="1"> <span
						id="amount_unit">月</span> <span
						style="margin-left: 10px; display: none;" id="amountError">
						<font color="red" id="errorTips">*必须填数字！</font>
					</span>
				</div>
			</div>
			<div class="control-group">
				<span id="channel_field_label" class="control-label">开通方式：</span>
				<div class="controls">
					<label _value="qdqb" _type="channel_radio" class="selected">
						<a class="radio-box" href="javascript:void(0);"> 纳币支付 <i
							class="icon-check"></i>
					</a>
					</label>
				</div>
			</div>
			<div class="control-group ">
				<label class="control-label">应付纳币：</label>
				<div class="controls">
					<h5 class="title-primary">
						<em id="pay_fee"
							style="margin-top: 7px; display: inline-block; height: 20px; color: #ff6363;">${month_cost}</em>
						纳币
					</h5>
				</div>
			</div>
			<div class="form-actions">
				<button class="btn-primary" type="button" data-type="submit_button" id="submitGive">
				</button>
			</div>
			<div class="close fr" id="closeDiv">关闭</div>
		</div>
	</div>
	<!--zshy end-->