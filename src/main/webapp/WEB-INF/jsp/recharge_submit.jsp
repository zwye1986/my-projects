<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/css/tenpay/css_col4.css" />
<script type="text/javascript">
	/*初始化加载*/
	$(document)
			.ready(
					function() {
						/*单选选择充值对象事件*/
						$("input[name='amountOption']")
								.click(
										function() {
											var value = $(
													"input[name='amountOption']:checked")
													.val();
											if (value == "manual") {
												$("#spanManual").show();
												$("#b_amount").focus();
											} else {
												$("#spanManual").hide();
											}
										});

						$("#recharge_ok")
								.click(
										function() {
											var value = $(
													"input[name='amountOption']:checked")
													.val();
											if (!value) {
												top.$.alerts
														.alert("你尚未选择充值的金额！请选择后，再充值.");
												return;
											}

											var rType = $(
													"input[name='rechargeMethod']:checked")
													.val();
											if (!rType) {
												top.$.alerts.alert("请选择充值渠道.");
												return;
											}

											var flag = false;
											if ("${mobileNumber == null}") {
												$
														.ajax({
															type : "POST",
															async : false,
															cache : false,
															dataType : "json",
															url : "${ctx }/"
																	+ $(
																			"#_mobileNumber")
																			.val()
																	+ "/user",
															success : function(
																	data) {
																if (data != 0) {
																	flag = true;
																	top.$.alerts
																			.alert("对不起，根据您输入的手机号码，未查到该用户信息，请核实后，重新输入！");
																}
															}
														});
											}
											if (flag) {
												return;
											}
											var bankCardId = '${bankCard.id}';
											if (value == "manual") {
												value = $("#b_amount").val();
												if (value == "") {
													top.$.alerts
															.alert("选择手动输入后，必须输入充值的金额！");
													return;
												} else if (!/^[1-9]\d*$/
														.test(value)) {
													top.$.alerts
															.alert("输入的金额必须为正整数");
													return;
												}
											}
											if(rType==4){
											dynamicPost(
													"${ctx}/user/account/recharge/done",
													{
														rechargeMethod : rType,
														bankCardId : bankCardId,
														bank_type : $(
																"#bank_type_value")
																.val(),
														amount : value,
														mobileNumber : $("#_mobileNumber").val()
													});
											}else{
												$.ajax({
													type : "POST",
													async : false,
													cache : false,
													dataType : "json",
													data:{rechargeMethod : rType,
														bankCardId : bankCardId,
														bank_type : $("#bank_type_value").val(),
														amount : value,
														mobileNumber : $("#_mobileNumber").val()
														},
													url : "${ctx}/user/account/recharge/doneByChinaBank",
													success : function(data) {
														$('#payForm').html(data.data);
														$('#payForm form').submit();
													}
												});
											}
										});

					});
</script>
<div style="display: none" id="payForm"> </div>
<div class="contentR fr">
	<div class="RTitle"></div>
	<div class="rechargeC">
		<div class="balance">
			<span class="balanceL">账户充值</span> <span class="balanceR "> </span> <span
				class="balanceLB "></span> <span class="balanceB"></span>
		</div>
		<%-- 
	    <div class="rechargeS">
	        充值方式：<span class="selectedB" style="margin-right:20px;"> 
	                ${bankCard.bankName }<em style="margin-left:15px;">尾号${bankCard.cardNumber }</em></span>
	    </div>--%>
		<div class="R_amount">
			充值号码:<span style="margin-right: 20px;"> &nbsp;&nbsp;<input
				type="text" name="bank" value="${mobileNumber }" id="_mobileNumber"
				${mobileNumber != null ? 'readonly' : '' }>
			</span>
		</div>
		<div class="amount" style=" height:54px; line-height:64px;">
			充值金额:<span style="margin-right: 20px;"> <c:forEach var="data"
					items="${amountOptions }">
					<input type="radio" name="amountOption"
						value="${data.optionValue }" />&nbsp;${data.optionValue }元&nbsp;&nbsp;&nbsp;&nbsp;
			        	</c:forEach> <input type="radio" name="amountOption" value="manual" />&nbsp;手动输入
				<span id="spanManual" style="display: none;"> <input
					type="text" name="bank" value="" id="b_amount">元
			</span> <em style="color: #f16244">(1元＝1纳币)</em>
			</span>
		</div>
		<div class="amount" style="text-align:left; height:82px; line-height:82px;" >
		<em style="display:inline-block; width:72px; height:42px; line-height:62px; float:left;">
			充值渠道:</em><span style="margin-right: 20px;">
				<%-- <input style="margin-top:-20px;" type="radio" name="rechargeMethod" value="1" onclick="javascript:$('#tenpayBankList').hide();$('#bank_type_value').val('');" />&nbsp;
					<img style=" border:1px solid #ccc;" src="${ctx }/images/user/cft.jpg"/>
				<input style="margin-top:-20px;" type="radio" name="rechargeMethod" value="2" onclick="javascript:$('#tenpayBankList').show();" />&nbsp;
					<img style=" border:1px solid #ccc;" src="${ctx }/images/user/wyzl.jpg"/>
				<input type="radio" name="rechargeMethod" value="3" onclick="javascript:$('#tenpayBankList').hide();" />&nbsp;网银在线  --%>
				<input  style="margin-top:-20px;"  type = "radio" name="rechargeMethod" value="6" onclick="javascript:$('#tenpayBankList').hide();$('#bank_type_value').val('');"/>&nbsp;
					<img style=" border:1px solid #ccc;" width="82" height="31" src="${ctx }/images/user/wqb.png"/>
				<input  style="margin-top:-20px;"  type = "radio" name="rechargeMethod" value="5" onclick="javascript:$('#tenpayBankList').hide();$('#bank_type_value').val('');"/>&nbsp;
					<img style=" border:1px solid #ccc;" src="${ctx }/images/user/wyzx.jpg"/>
				<%--
				<input  style="margin-top:-20px;"  type = "radio" name="rechargeMethod" value="4" onclick="javascript:$('#tenpayBankList').hide();$('#bank_type_value').val('');"/>&nbsp;
					<img style=" border:1px solid #ccc;"  src="${ctx }/images/user/ybzf.jpg"/>
					--%>
			</span>
		</div>
		<div class="bank_li">
			<div id="tenpayBankList" style="display: none;"></div>
			<input type="hidden" name="bank_type_value" id="bank_type_value"
				value="0">
			<script type="text/javascript" src="${ctx}/js/tenpay/bank.js"></script>
		</div>
		<div class="P_confirm">
			<input type="button" value=" " id="recharge_ok">
		</div>
	</div>
	<div class="RB"></div>
	<div class="people_r">
		<div class="RTitle"></div>
		<div class="people_c">
			<p>
				<img src="${ctx}/images/min_logo.jpg" /><em style="color: #f16244;">人工汇款充值:</em>您可以直接将需要的充值额度打入蛙宝网的公司账户
			</p>
			<p>
				<label>户名: </label><span>江苏维纳达软件技术有限公司</span>
			</p>
			<p>
				<label>开户行: </label><span>中国工商银行南京市玄武支行</span>
			</p>
			<p>
				<label>账号: </label><span>4301015909100579180</span>
			</p>
		</div>
		<div class="people_tipt"></div>
		<div class="people_tipc">
			<p>Tips：</p>
			<ul>
				<li>请准确填写蛙宝网的户名、开户行以及账号。</li>
				<li>汇款时请填写清楚您在蛙宝网的账号以及昵称，以便我们准确无误的将等值的纳币(蛙宝网虚拟货币名称)充值到您的账户中。</li>
				<li>您也可以发邮件到<span>service@venada.com.cn</span> ，告知我们您已汇款。
				</li>
				<li>您也可以通过拨打客户服务热线<span>025-84533055</span> 电话告知我们。
				</li>
			</ul>
		</div>
		<div class="people_tipb"></div>
	</div>
</div>
