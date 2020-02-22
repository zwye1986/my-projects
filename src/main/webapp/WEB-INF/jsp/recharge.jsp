<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${ctx}/js/common.js"></script>

<script type="text/javascript">
/*初始化加载*/
$(document).ready(function(){
	/*单选选择充值对象事件*/
	$("input[name='whoRdo']").click(function(){
		var value = $("input[name='whoRdo']:checked").val();
		switch(value)
		{
			case '0': //给自己充值
				$("#selfLi").addClass("selectedB");
				$("#otherLi").removeClass("selectedB");
				break;
			case '1': //给他人充值
				$("#otherLi").addClass("selectedB");
				$("#selfLi").removeClass("selectedB");
				break;
			default:
				break;			
		}
	});
	$("input[name='whoRdo']:checked").click();
	
	$("input[name='bankCard']").click(function(){
		var value = $("input[name='bankCard']:checked").val();
		$("#bankCardList").find("li").removeClass("selectedB");
		$("#"+value).addClass("selectedB");
	});
	$("input[name='bankCard']:checked").click();
	
	
	$("#money_Btn").click(function(){<%--
		var bankCard = $("input[name='bankCard']:checked").val();--%>
		var payTo = $("input[name='whoRdo']:checked").val();
		<%--if(!bankCard || bankCard == ""){
			top.$.alerts.alert("请选择已绑定的银行卡！");
			return ;
		}--%>
		dynamicPost("${ctx}/user/account/recharge", { /**bankCardId: bankCard,*/payTo:payTo });
	});
	
	
});
</script>


<div class="contentR fr">
    <div class="RTitle"></div>
    <div class="rechargeC">
        <div class="balance">
            <span class="balanceL">账户充值</span>
            <span class="balanceR "></span>
            <span class="balanceLB "></span>
            <span class="balanceB"></span>
        </div>
        <div class="bank">
            <ul>
                <li  id="selfLi">
                    <input type="radio" name="whoRdo" value="0" checked="checked" />
                    <span style="margin-right:20px;"> 给自己充值</span> 
                </li><%--
                <li id="otherLi">
                    <input type="radio" name="whoRdo" value="1" />
                    <span style="margin-right:20px; margin-left:5px;">给他人充值</span>
                </li> --%>
            </ul>
            <%--
            <p class="bank_title">选择银行帐户：</p>
            <ul id="bankCardList">
            	<c:forEach var="bankCard" items="${bankCards }">
	            	<li  id="${bankCard.id }">
	                    <input type="radio" name="bankCard" value="${bankCard.id }"  ${bankCard.isDefault == 0 ? 'checked' : '' }>
	                    <span style="margin-right:20px;"> ${bankCard.bankName }</span>
	                    <span> 尾号${bankCard.cardNumber }</span>
	                </li>
            	</c:forEach>

                <li>
                    <a style=" color:#f16244; text-decoration:underline;" href="${ctx }/user/3/manager">＋银行卡绑定管理</a>
                </li>
            </ul> --%>
            <div style="display:none; height:450px;">
                <p class="bank_title">网上银行：<em class="choice">选择您拥有的银行卡</em>
                </p>
                <ul class="choice_C">
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <p class="qtbank_title">其他银行卡</p>
                </ul>

                <ul class="qtchoice_C">
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                    <li>
                        <input type="radio" name="bank" value="1" id="bank_C"><span><img src="${ctx}/images/user/gsyh.png"></span>
                    </li>
                </ul>
            </div>

        </div>
        <div class="money_Btn">
                <input type="button" value=" " id="money_Btn" >
        </div>
    </div>
    <div class="RB"></div>
</div>