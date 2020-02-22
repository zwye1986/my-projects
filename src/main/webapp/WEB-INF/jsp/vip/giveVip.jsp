<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<script type="text/javascript">
$(document).ready(function(){
	
	$("#submitGive").click(function(){
		var mobileNumber = $("#givenNumber").val();
		if(mobileNumber == ""){
			$("#givenNumberError").show();
			return false;
		}
		var status = 0;
		$.post("${ctx}/"+mobileNumber+"/checkMobileNumber", {}, function(data) {
			if(data == 1){//验证失败
				$("#givenNumberError").show();
				status = 1;
			}else if(data == 2){
				$("#errorTips").empty();
				$("#errorTips").html("＊用户不存在！");
				$("#givenNumberError").show();
				status = 1;
			}else{
				$("#givenNumberError").hide();
			}
		});
		
		if(status == 1){
			return false;
		}
		
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
		
		$.showPanelVip("操作提示", "${ctx }/user/vip/"+mobileNumber+"/"+mode+"/"+times+"/payForGivenVip", 600,
				300, true, "${ctx}");
		return;
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
	
	$("#givenNumber").focus(function(){
		$("#givenNumberError").hide();
	});
	
	$("#givenNumber").mouseout(function(){
		var mobileNumber = $("#givenNumber").val();
		if(mobileNumber == ""){
			$("#givenNumberError").show();
			return false;
		}
		$.post("${ctx}/"+mobileNumber+"/checkMobileNumber", {}, function(data) {
			if(data == 1){//验证失败
				$("#givenNumberError").show();
			}else{
				$("#givenNumberError").hide();
			}
		});
	});
	
	
	$("#amount_input").focus(function(){
		$("#amountError").hide();
	});
	
	$("#amount_input").mouseout(function(){
		countMoney();
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

</script>
<div class="vip_content">
	<!--grid_c2f start-->
	<div class="grid_c2f">
		<div class="gr_mod_box2">
			<div class="wb_priv_top">
				<div class="wb_priv_top_tit fl">蛙宝会员特权</div>
				<span class="wb_priv_top_vip">好特权送给亲密朋友，独乐乐不如众乐乐！</span>
				<div style="margin-right: 30px; margin-top: 15px;"
					class="mod_btn_common_sub sys_kthy fr">
					<a href="${ctx }/user/4/manager" class="xufei">开通会员</a>
				</div>
			</div>
		</div>
		<div class="gr_mod_box2">
			<div class="wb_priv_center">
				<div class="zs_left">
					<div class="pay-info">
						<div class="bu-logo">
							<a target="_blank" href="#" stype="home"> <img alt="VIP会员"
								src="${ctx }/images/vip/logo_l.png" stype="logo">
							</a>
						</div>
						<p stype="price" class="price">${month_cost }元/月</p>
					</div>
					<div class="wrap-padding">
						<div class="about-info">
							<ul>
								<h3 class="vip_title">VIP特权</h3>
								<li>每天额外获得5积分</li>
								<li>等级个性符号</li>
								<li>设置支付密码</li>
								<li>会员实现T+0到账</li>
								<li>短信提醒免费接收</li>
								<li>无需点击游戏</li>
								<li>会员游戏利率更高</li>
								<li>安全邮箱</li>
								<li>绑定手机NO.2</li>
								<li>会员升级加速</li>
								<h3 class="all_high_game_title">VIP特权</h3>
								<li>会员专享更高利率的游戏</li>
							</ul>


						</div>
					</div>
				</div>
				<div class="zs_right">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label">开通账号：</label>
							<div class="controls">
								<input type="text" maxlength="11" class="input-user" id="givenNumber" value="">
								<span style="margin-left: 10px;display: none;" id="givenNumberError" ><font color="red" id="errorTips">*手机号码有误！</font></span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">付费模式：</span>
							<div class="controls-option">
								<label _type="mode_radio" _value="month" class="selected" id="mode_month">
									<a href="###" class="radio-box"> 按月付费 <i class="icon-check"></i>
								</a>
								</label>
								<label _type="mode_radio" _value="year" class="" id="mode_year"> <a
									href="###" class="radio-box"> 按年付费 <i class="icon-check"></i>
								</a>
								</label>
								<input type="hidden" id="payMode" value="1" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开通时长：</label>
							<div class="controls">
								<input type="text" style="width: 17%" maxlength="2" class="input-min" id="amount_input" value="1"/>
								<span id="amount_unit">月</span>
								<span style="margin-left: 10px;display: none;" id="amountError" ><font color="red" id="errorTips">*必须填数字！</font></span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label" id="channel_field_label">开通方式：</span>
							<div class="controls">
								<label class="selected" _type="channel_radio" _value="qdqb">
									<a href="###" class="radio-box"> 纳币支付 <i class="icon-check"></i></a>
								</label>
							</div>
						</div>
						<div class="control-group ">
							<label class="control-label">应付纳币：</label>
							<div class="controls">
								<h5 class="title-primary">
									<em id="pay_fee">0</em> 纳币
								</h5>

							</div>
						</div>
						<div class="form-actions">
							<button data-type="submit_button" type="button"
								class="btn-primary" id="submitGive">
								<span>确认无误,立即赠送</span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>