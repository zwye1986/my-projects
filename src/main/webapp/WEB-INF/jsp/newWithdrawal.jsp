<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>

<script type="text/javascript">
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
 }

function checkInt(obj)
{
	 var re = /^[0-9]*[1-9][0-9]*$/ ;
     if (!re.test(obj))
    {
        return false;
     }else{
    	 return true;
     }
} 

function myWithDrawal(id){
	$(".bd-mes").removeClass("selected");
	$(".bd-icon-select").remove();
	$("#"+id).addClass("selected");
	$("#"+id).html($("#"+id).html()+"<i class='bd-icon-select'></i>");
	$("#bankCard").val(id);
}

function clearText(){
	
	var str = $("#amount_").val();
	if(str=="最低提现为100元"){
		$("#amount_").val("");
	}
}

function fillText(){
	var str = $("#amount_").val();
	if(str.trim().length == 0){
		$("#amount_").val("最低提现为100元");
	}
}

$(function(){
	
	
	
	
	$(".adds-card").click(function(){
		window.open("${ctx}/user/manager/bindCard","_blank");
	});
	
	
	//提现请求操作
	$("#qd_Btn").click(function(){

			var bankCard = $("#bankCard").val();
			if(bankCard.trim().length == 0){
				layer.msg('您尚未选择绑定的银行卡！');
				return ;
			}
        /**
			var withdrawMoney=$("#amount_").val();
			if(!checkInt(withdrawMoney)){
				layer.msg('提现金额必须为整数');
				return;
			}
			if(withdrawMoney < 100 || withdrawMoney > 50000){
				layer.msg('单笔最低提现100，最高50000.'); 
				return ;
			}
			if($("#password_").val().trim().length == 0){
				layer.msg('请输入登陆密码，VIP会员输入支付密码'); 
				return;
			}
			var flag = false ;
			
			var mypassword = encodeURIComponent($("#password_").val());
			
			
			
			$.ajax({
			   type: "POST",
			   async: false,
			   cache: false,
			   dataType: "json",
			   url: "${ctx }/user/account/"+mypassword+"/password",
			   success: function(data){
				  if(data != 0){
					  flag= true;
					  layer.msg('您输入的密码不正确'); 
				  }
			   }
			});
			
			if(flag){
				return ;
			}
			$.ajax({
				   type: "GET",
				   async: false,
				   cache: false,
				   url: "${ctx }/user/account/checkWithDrawal",
				   success: function(data){
					  if(data == false){
						  flag = true;
						  layer.msg('充值的金额必须用于领取游戏');
					  }
				   }
				});
			if(flag){
				return ;
			}
			
			$.ajax({
			   type: "POST",async: false,cache: false,
			   dataType: "json",
			   url: "${ctx }/user/account/withdrawal",
			   data: 'amount='+$("#amount_").val(),
			   success: function(data){
				   if(data.resultCode == 0){
					   dynamicPost("${ctx }/user/account/withdrawal/done",{password:$("#password_").val(),bankCardId:bankCard,amount:$("#amount_").val()});
				   }else{
					   layer.msg(data.resultMsg); 
				   }
			   }
			});**/

            dynamicPost("${ctx }/user/account/withdrawal/done",{password:$("#password_").val(),bankCardId:bankCard,amount:$("#amount_").val()});
		
	});
	
});

</script>


<div id="main">
    <div class="main-inner">
    <div class="title-items">
      <h2> 帐户提现  <em style="color:#666; margin-left:20px;">余额<c:out value="${userWallet.amount }" />纳币</em></h2>
    </div>
    <div class="model-box withdraw-post recharge-input">
              <ul class="items">
                <li class="txt">持卡人:</li>
                <li>${name }</li>
              </ul>
              <ul class="items">
                <li class="txt"><b>提现至银行卡:</b></li>
                <li>
                  <ul class="adds-bankcard">
                  
                   <c:forEach var="bankCard" items="${bankCards }" varStatus="s">
                   <c:if test="${s.index==0 }">
                   <c:set value="${bankCard.id }" var="firstbank"></c:set>
                   </c:if>
                   <li id="${bankCard.id }" onclick="myWithDrawal(this.id)" class="bd-mes <c:if test="${s.index == 0 }">selected</c:if>">${bankCard.bankName } 尾号${bankCard.cardNumber }<c:if test="${s.index==0 }"><i class="bd-icon-select"></i></c:if></li>               
                   </c:forEach>
                   <input type="hidden" id="bankCard" name="bankCard" value="${firstbank }" />
                    <li class="adds-card"><span><i class="icons add-blue "></i>添加提现银行卡</span></li>
                  </ul>
                </li>
              </ul>
              <ul class="items">
                <li class="txt">可提现金额:</li>
                <li class="light-org">￥${maxAmount }</li>
              </ul>
              <ul class="items">
                <li class="txt"><b>提现金额:</b></li>
                <li>
                  <div class="sum-input">
                   <form method="post">
                      <input type="text" id="amount_" onblur="fillText();"  onclick="clearText();" class="input" value="最低提现为100元">
                    </form>
                  </div>
                  <span class="tip err" style="display:none;" id="withdrawCashErr"><i class="icons reg-error"></i>余额不足</span> </li>
              </ul>
               <ul class="items">
                <li class="txt"><b>登录密码:</b></li>
                <li><div class="sum-input">
                    <form method="post">
                      <input type="password" id="password_" class="input" >
                    </form>
                  </div></li>
                </ul>
              <div style="padding:20px 0" class="bd-button">
                <input type="button" id="qd_Btn" value="确认提现" name="确认提现"  style="margin-left:173px;">
              </div>
            </div>
            <div class="warm-prompt r3">
              <h2>温馨提示</h2>
              <p>1. 如果您是VIP会员,请输入支付密码。</p>
              <p>2. VIP会员当日12点前提现,当日到账。</p>
              <p>3. 按照央行规定,使用小额支付系统,蛙宝网支持每笔最多不超过50000,不限笔数。</p>
              <p>4. VIP会员提现申请日期不限,周一至周日都可提现(央行支付系统停运除外)。周六、周日提现,将顺延至周一到账。</p>
              <p>5. 为了更快速的到账,建议网站用户使用建行、工行、招行、农行、交行等国有大型银行的借记卡进行提现。</p>
              <p>6. 为避免沦为信用卡套现的平台,当天充值金额不能当天取出.蛙宝网建议您领取任务后到期再提现,从而形成良性循环。</p>
              <p>7. 如有疑问,请使用QQ在线客服或者加入官方QQ群:305322529.</p>
            </div>
    </div>
  </div>