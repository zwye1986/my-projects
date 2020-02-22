<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${ctx}/js/common.js"></script>

<script type="text/javascript">
/*初始化加载*/
$(document).ready(function(){

$("#qd_Btn").click(function(){
	if($(".dhm_txt").val().length == 0){
		alert("请输入兑换码");
		return;
	}
	
	$.ajax({
		type : "GET",
		async : true,
		cache : false,
		data : {
			exchangeCode:$(".dhm_txt").val()
		},
		dataType : "json",
		url : "${ctx}/exchange.html?time"+new Date(),
		success: function(result){
			
			if(result == -1){
				window.location.href = "${ctx}/login.html";
				return;
			}else if(result == 1){
				alert("您已经使用过兑换码");
			}else if(result == 2){
				alert("该兑换码无效或已经被使用");
				
			}else if(result == 3){
				alert("兑换发生错误");
			}else if(result == 0){
				alert("兑换成功");
				window.location.href = "${ctx}/user/account/manager";
			}
		  }
		});
	 
});
	
	
});
</script>


    <div class="contentR fr">
      <div class="RTitle"></div>
      <div class="rechargeC" style="min-height:800px; height:auto;">
        <div class="balance"> <span class="balanceL" style="margin-left:40px;">兑换码</span> <span class="balanceLB "></span> <span class="balanceB"></span> </div>
        <div class="dhm"><label>兑换码:</label><input type="text" class="dhm_txt"/></div>
        <div class="qd_Btn" style="text-align:center;" >
          <input id="qd_Btn" type="button" value="" style="cursor:pointer;">
        </div>
                <div class="dhq_zs">
        <p>1.如何注册蛙宝网？</p>
<span>
您只要根据我们的注册界面提示，填写您真实的手机...码，再设置密码就很便捷的注册成功了。

</span>
<p>2.兑换码有什么用？</p>
<span>每个兑换码面值5元人民币，您可以将兑换码输入上方的输入框中，并按[确定]按钮，系统将会为您的蛙宝账号充值。您也可以选择提现到您的银行卡。</span>
<p>3.兑换码长什么样子？</p>
<span><img src="${ctx }/images/user/sgq.jpg"/></span>
<p>4.我在哪里能够得到兑换码？</p>
<span class="tab">
目前，活动只限于以下门店：
<table>
<thead>
<tr>
<th>区域</th>
<th>零售商</th>
<th>省份</th>
<th>城市</th>
<th>门店名</th>
</tr>
</thead>
<tbody id="tab">
<tr>
<td>南京</td><td>苏果超市</td><td>江苏</td><td>南京</td><td>苏果大厂钻石星城购物广场</td></tr>

<tr><td>南京</td><td>苏果超市</td><td>江苏</td><td>南京</td><td>苏果清凉门购物中心</td></tr>
<tr><td>南京</td><td>苏果超市</td><td>江苏</td><td>南京</td><td>苏果华侨城购物广场</td></tr>
<tr><td>南京</td><td>苏果超市</td><td>江苏</td><td>南京</td><td>苏果六合紫晶购物广场</td></tr>
<tr><td>南京</td><td>苏果超市</td><td>江苏</td><td>南京</td><td>苏果中山北路购物广场</td></tr>
<tr><td>南京</td><td>苏果超市</td><td>江苏</td><td>南京</td><td>苏果迈皋桥平价店</td></tr>
</tr>
</tbody>
</table>
</span>
        </div>
        
      </div>
      <div class="RB"></div>
    </div>