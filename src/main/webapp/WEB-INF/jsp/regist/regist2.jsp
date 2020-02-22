<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" >
$(document).ready(function(){
	countTime();
	$("#validateCode").click(function(){
		var code = $("#code").val();
		if(isEmpty(code)){
			$("#codeTip").empty();
			$("#codeTip").html("请输入短信验证码！");
			return;
		}else{
			$.post("${ctx}/${mobileNumber}/"+code+"/validateMessageCode",{},function(data){
				if(data.resCode == 0){
					$("#codeTip").empty();
					$("#codeTip").html(data.resMsg);
				}else{
					$("#submitForm").submit();
				}
			});
		}
	});
});
function countTime() {
	$("#sp").empty();
	$("#sp").html("<a class=\"cz_pwd\" id=\"secondCount\" href=\"###\">91</a>");
 	var s = 90;//用来记录秒,因为需求是从1开始的。
	var w = setInterval(function () {
     						s--;
     						$("#secondCount").empty();
     						$("#secondCount").html(s);
     						if (s == 0) { 
     							clearInterval(w); 
     							$("#sp").empty();
     							$("#sp").html("<a class=\"cz_pwd\" id=\"resend\" href=\"###\" onclick=\"resend('${mobileNumber}');\">重新发送</a>");
     						} 
 						}, 1000); 
	}
	
	function resend(mobileNumber){
		$.post("${ctx}/"+mobileNumber+"/resendCode",{},function(data){
		});
        countTime();
	}
</script>
<style type="text/css">
a:link {color: #FFF}
a:visited {color: #FFF}
</style>
<div id="wrapper">
  <div class="reg-title">
    <h2>用户注册</h2>
    <b class="line"></b></div>
  <div class="reg-steps r3">
        <ol class="reg-step-2">
            <li class="step-1"><span class="txt"><i class="icons dot24">1</i>填写手机号</span></li>
            <li class="step-2"><span class="txt"><i class="icons dot24">2</i>手机短信领取密码</span></li>
            <li class="step-3"><span class="txt"><i class="icons dot24">3</i>注册成功</span></li>
        </ol>
    </div>
    <form action="${ctx}/${mobileNumber}/regist3" method="post" id="submitForm">
  <div class="reg-items">
        <div class="phone-verify">
        <div class="phone-top">用户注册－短信验证码</div>
        <p class="phone_ts">短信验证码已发送，请输入短信验证码。</p>
        <p class="phone_ts"><em style="display:inline-block; margin-right:10px;">您的手机号码:</em><a>${mobileNumber }</a></p>
            <ul class="items" style="margin-left:30px;">
                <li class="txt">短信验证码:</li>
                <li>
                    <input type="text" id="code" name="code" class="input"  maxlength="6" />
                     <span id="sp">
                     	
                     </span>
                     <span id="codeTip" class="tip" style="color:#D35352; margin-left:80px;"></span>
                     <!-- <em>60秒后还未接收到短信，请<a href="/">返回上一步</a>重新注册</em> -->
                    </li>
            </ul>
            <div class="next-step"> <a href="###" class="gbtn" id="validateCode">确定</a> </div>
        </div>
    </div>
    </form>
</div>