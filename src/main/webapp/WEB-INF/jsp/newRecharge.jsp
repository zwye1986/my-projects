<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#rType").val("5");
	$("#recharge_ok").click(function(){
		var _mobileNumber = $("#_mobileNumber").val();
		if(!checkMobileNumber(_mobileNumber)){
			$("#_mobileNumber_error").show();
			return;
		}else{
			$("#_mobileNumber_error").hide();
		}
		var _amount = $("#b_amount").val();
		
		if(!checkInt(_amount)){
			$("#_amount_error").show();
			return;
		}else{
			$("#_amount_error").hide();
		}
		var rType=$("#rType").val();
        
		if (_mobileNumber.length!=0) {
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
								layer.msg('对不起，未查到该用户信息，请核实后，重新输入！');
								return;
							}
						}
					});
		}
		var bankCardId = '${bankCard.id}';
		var value = $("#b_amount").val();
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

function myFun(id){
	$(".banks-dir").each(function(){
		$(this).removeClass("selected");
	});
	$("#"+id).addClass("selected");
    if(id=="netBanks"){
    	
    	$("#rType").val("5");
    	$("#netbankselect").show();
    	$("#sinaselect").hide();
    }else if(id=="sinapay"){
    	$("#rType").val("6");
    	
    	$("#netbankselect").hide();
    	$("#sinaselect").show();
    }
}
</script>
<div style="display: none" id="payForm"> </div>
<div id="main">
  <div class="main-inner">
   <div class="title-items">
      <h2> 帐户充值</h2>
    </div>
     <div class="rech-opts rech-pad recharge-input">
              <div class="rech-items">
                <h2>充值号码:</h2>
                <ul class="rech-money">
                  <li>
                    <div class="sum-input">
                      <input type="text" name="bank" value="${mobileNumber }" id="_mobileNumber"
				${mobileNumber != null ? 'readonly' : '' }  class="input">
                    </div>
                  </li>
                </ul>
                <div id="_mobileNumber_error" style="display: none;"  class="msg-error">请输入正确的手机号码</div>
              </div>
              <div class="rech-items">
                <h2>充值金额:</h2>
                <ul class="rech-money">
                  <li>
                    <div class="sum-input">
                      <input type="text" name="bank" value="" id="b_amount"  class="input">
                    </div>
                  </li>
                </ul>
                <div id="_amount_error" style="display: none;" class="msg-error">请输入正确的充值金额</div>
              </div>
              <div class="rech-items">
                <h2>充值方式</h2>
                <ul class="rech-mode">
                  <li id="netBanks" onclick="myFun(this.id)" class="banks-dir netBank  selected"><i class="logo-bank bank-wyzx">网银在线</i><i id="netbankselect" class="icons"></i></li>
                  <li id="sinapay" onclick="myFun(this.id)"  class="banks-dir  "><i class="logo-bank bank-ybzf">新浪支付</i><i style="display:none" id="sinaselect" class="icons"></i></li>
                </ul>
              </div>
              <div class="save-button recharge-button">
                 <input type="hidden" name="rType" id="rType" value="5" />
                <input type="button" id="recharge_ok" name="确认充值" value="确认充值">
              </div>
            </div>
            <div class="block_list r3">
              <h5><em style="font-size:16px;">人工汇款充值:</em>您可以直接将需要的充值额度打入蛙宝网的公司账户</h5>
              <div class="rech-opts rech-pad recharge-input" >
                <div class="rech-items">
                  <h2>户名:</h2>
                  <ul class="rech-money">
                    <li>
                      <div class="sum-input">
                        <input type="text" class="input"  value="江苏维纳达软件技术有限公司">
                      </div>
                    </li>
                  </ul>
                </div>
                <input type="hidden" name="bank_type_value" id="bank_type_value"
				value="0">
                <div class="rech-items">
                  <h2>开户行:</h2>
                  <ul class="rech-money">
                    <li>
                      <div class="sum-input">
                        <input type="text" class="input"  name="tranAmt" value="中国工商银行南京市玄武支行">
                      </div>
                    </li>
                  </ul>
                </div>
                <div class="rech-items">
                  <h2>帐号:</h2>
                  <ul class="rech-money">
                    <li>
                      <div class="sum-input">
                        <input type="text" class="input"  name="tranAmt" value="4301 0159 0910 0579 180">
                      </div>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="warm-prompt r3">
              <h2>温馨提示</h2>
              <p>1. 请准确填写蛙宝网的户名、开户行以及账号。</p>
              <p>2. 汇款时请填写清楚您在蛙宝网的账号以及昵称，以便我们准确无误的将等值的纳币(蛙宝网虚拟货币名称)充值到您的账户中。</p>
              <p>3.您也可以发邮件到 service@venada.com.cn ，告知我们您已汇款。</p>
              <p>4. 您也可以通过拨打客户服务热线 400 891 6233 电话告知我们。</p>
            </div>
  </div>
  </div>