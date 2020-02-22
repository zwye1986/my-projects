<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/i18N/CN/common.i18n_resource_zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/vldt/vldt.js"></script>

<script type="text/javascript">
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
/*初始化加载*/
$(document).ready(function(){
	vadty('','N');/*加载验证框架*/
	
	$("input[name='bankCard']").click(function(){
		$("li").removeClass("selectedB");
		var value = $("input[name='bankCard']:checked").val();
		$("#"+value).addClass("selectedB");
	});
	$("input[name='bankCard']:checked").click();
	
	//提现请求操作
	$("#qd_Btn").click(function(){
		
		if(document.getElementById("mainForm").onsubmit()){
			
			var bankCard = $("input[name='bankCard']:checked").val();
			if(!bankCard){
				top.$.alerts.alert("您尚未选择绑定的银行卡！");
				return ;
			}
			
			var withdrawMoney=$("#amount_").val();
			
			if(withdrawMoney < 100 || withdrawMoney > 50000){
				top.$.alerts.alert("单笔最低提现100，最高50000.");
				return ;
			}
			
			
			if(checkInt(withdrawMoney)==false){
				top.$.alerts.alert("提现金额必须为整数");
				return ;
			} 
			
			var flag = false ;
			
			
			$.ajax({
				   type: "POST",
				   async: false,
				   cache: false,
				   dataType: "json",
				   url: "${ctx }/user/account/"+$("#password_").val()+"/password",
				   success: function(data){
					  if(data != 0){
						  flag= true;
						 top.$.alerts.alert("您输入的支付密码不正确");
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
						  top.$.alerts.alert("充值的金额必须用于领取游戏");
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
			   data: 'amount='+$("#amount_").val()+"&password="+$("#password_").val(),
			   success: function(data){
				   if(data.resultCode == 0){
					   dynamicPost("${ctx }/user/account/withdrawal/done",{bankCardId:bankCard,amount:$("#amount_").val()});
				   }else{
					   top.$.alerts.alert(data.resultMsg);
				   }
			   }
			});
		}
	});
});
</script>

<div class="contentR fr">
    <div class="RTitle"></div>
    <div class="rechargeC">
        <div class="balance">
            <span class="balanceL">账户提现  余额 <em><c:out value="${userWallet.amount }" /></em>纳币</span>
            <span class="balanceR "></span>
            <span class="balanceLB "></span>
            <span class="balanceB"></span>
        </div>
        <div class="bank">
            <p class="bank_title">选择银行帐户：</p>
            <ul>
                <c:forEach var="bankCard" items="${bankCards }">
	            	<li  id="${bankCard.id }">
	                    <input type="radio" name="bankCard" value="${bankCard.id }" ${bankCard.isDefault == 0 ? 'checked' : '' } id="bank">
	                    <span style="margin-right:20px;"> ${bankCard.bankName }</span>
	                    <span> 尾号${bankCard.cardNumber }</span>
	                </li>
            	</c:forEach>
                <li>
                    <a style=" color:#f16244;" href="${ctx }/user/3/manager">＋绑定银行卡管理</a>
                </li>
            </ul>
        </div>
        <form  method="post" id="mainForm">
	        <div class="money">
	            <p class="bank_title">提现金额：</p>
	            <input type="text" id="amount_"   vr="0&&33" min="1" max="50000" value="">元 <span id="span_amount_">&nbsp;</span>
	            <font style="font-size: 12px;">(提示：最低提现金额为100。<c:if test="${maxAmount>=100}">本次最多可提现<c:out value="${maxAmount}"/></c:if>)</font>
	            <p class="bank_title">登录密码：</p>
	            <input type="password" id="password_" vr="0" value="">　 <span id="span_password_"></span>
	        </div>
	                <div class="wid_ts">
        <div class="wid_ts_top"></div>
          <div class="wid_ts_title">Tips:</div>
          <ul>
            <li>如果您是<span>VIP会员</span>,请输入支付密码.</li>
            <li>VIP会员<span>当日12点前提现</span>,当日到账.</li>
            <li>按照央行规定,使用小额支付系统,蛙宝网支持每笔<span>最多不超过50000</span>,<span>不限笔数</span>.</li>
            <li>VIP会员提现申请日期不限,周一至周日都可提现(央行支付系统停运除外).周六、周日提现,将顺延至周一到账.</li>
            <li>为了更快速的到账,建议网站用户使用建行、工行、招行、农行、交行等国有大型银行的借记卡进行提现. </li>
            <li>为避免沦为信用卡套现的平台,当天充值金额不能当天取出.蛙宝网建议您领取任务后到期再提现,从而形成良性循环. </li>
            <li>如有疑问,请使用QQ在线客服或者加入<span>官方QQ群:305322529</span>.</li>
          </ul>
        </div>
	        <div class="qd_Btn" style=" margin-top:5px;">
	            <input type="button" value=" " id="qd_Btn">
	        </div>
        </form>
    </div>
    <div class="RB"></div>
</div>