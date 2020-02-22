<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="yma" uri="/yma"%>
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script type="text/javascript">
<!--
function setTab2(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}
//-->


$(function(){
	gamePagination("ing",1,"","");
	
	$("#one2").click(function(){
		gamePagination("over",1,"","");
	});
	
	$("#one1").click(function(){
		gamePagination("ing",1,"","");
	});
	
	
	$("#closeDiv").click(function() {
		$(".zshy").hide();
	});
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
	
	
	$("#cancelAutoRenew").click(function(){
		$.post("${ctx}/user/account/cancelAutoRenew", {}, function(data) {
			if(data.resultCode=="0"){
				layer.msg("取消会员自动学费成功!",2,1);
				$("#cancelAutoRenew").hide();
			}
		});
	});
	
});

function gamePagination(type, page,searchtype,keyword) {
	
	var param = {
		type : type,
		page : page
	};
	
	$.get("showTaskDetail?time="+new Date(), param, function(data) {
		if (type == 'ing') {
			$("#con_one_1").html(data);
		} else if (type == 'over') {
			$("#con_one_2").html(data);
		} 
	});
}
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
function renewal() {
$(".zshy").show();
}

</script>


<div id="main">
	<div class="model-box">
		<div class="zh_c mr20">
			<div class="zh_top">
				<div class="zh_top_l fl">
					<div class="zh_top_ll fl">
						<p>
							帐户余额:<em>${walletAmount.amount}</em>纳币
						</p>
						<p>
							<label style="float: left;">用户等级：</label>
							<yma:LevelConvertIcoTag level="${level}" vipTag="${vipTag}" />
							 <c:choose>
								<c:when test="${isAutoRenew eq 0 }">
										 <a id="cancelAutoRenew" href="###" style="float:right;margin-right:18px;">取消会员自动续费</a>
								</c:when>
							</c:choose>
						</p>
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
									<a href="javascript:void(0)" style="display:inline-block;position:absolute; z-index:22;" onclick="renewal()">会员续费</a>
								</p>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${isExpire eq 1}">
										<p>
											<input type="button" style="display:inline-block;position:absolute; z-index:22;" value="" class="vip_hy_dq" />会员已到期<a
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
						<%-- <p>
							<label style="float: left;">注册日期：</label>
							<fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd" />
						</p> --%>
							<p>		
							<c:choose>
								<c:when test="${nextlevel gt 97029901}">
                                      您已经满级
                                   </c:when>
								<c:otherwise>
									<label style="float: left;">您还差<em>${nextLevelCredits}</em>积分就可以升级到
									</label>
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
										src="${ctx }/images/common/icon_01.png">
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
										src="${ctx }/images/common/icon_02.png">
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
										src="${ctx }/images/common/icon_03.png">
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
										src="${ctx }/images/common/icon_04.png">
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
										src="${ctx }/images/common/icon_05.png">
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
										src="${ctx }/images/common/icon_06.png">
								</dd>
								<dd class="acc_money">
									<em>${gameBenefit.reward}</em>纳币
								</dd>
							</dl>
					</a></li>
				</ul>
			</div>
		</div>

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
				<button class="btn-primary" type="button" data-type="submit_button" id="submitGive">确认无误，我要续费</button>
			</div>
			<div class="close fr" id="closeDiv">关闭</div>
		</div>
	</div>
	<!--zshy end-->
  </div>
		<!--选项卡开始-->
		<div class="tab_box">
			<div class="lyz_tab_left">
				<div class="pro_con111">
					<ul>
						<li onclick="setTab2('one',1,2)" id="one1" class="hover">进行中的任务</li>
						<li onclick="setTab2('one',2,2)" id="one2" class="">已完成的任务</li>
					</ul>
				</div>
			</div>
			<div class="lyz_tab_right">
				<div style="border: none; padding-top: 0" id="con_one_1"
					class="hover"></div>
				<div style="display: none; border: none; padding-top: 0"
					id="con_one_2" class="hover"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>