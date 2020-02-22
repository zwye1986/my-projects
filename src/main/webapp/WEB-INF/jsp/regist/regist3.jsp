<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	countTime();
});
function countTime() {
 	var s = 2;//用来记录秒,因为需求是从1开始的。
	var w = setInterval(function () {
     						s--;
     						if (s == 0) { 
     							$("#successForm").submit();
     						} 
 						}, 1000); 
	}
</script>
<div id="wrapper">
  <div class="reg-title">
    <h2>账户注册</h2>
    <b class="line"></b></div>
  <div class="reg-steps r3">
    <ol class="reg-step-3">
      <li class="step-1"><span class="txt"><i class="icons dot24">1</i>填写手机号</span></li>
      <li class="step-2"><span class="txt"><i class="icons dot24">2</i>手机短信领取密码</span></li>
      <li class="step-3"><span class="txt"><i class="icons dot24">3</i>注册成功</span></li>
    </ol>
  </div>
  <form action="${ctx}/fillNecessaryInfo" method="post" id="successForm">
  <div class="reg-items">
    <div class="reg-success"> <b class="icons success-green60"></b>
      <p class="e">${mobileNumber }，恭喜你注册成功！稍后请设置密码，确保您的账户安全！</p>
        <input name="mobileNumber" type="hidden" value="${mobileNumber}" />
        <input name="code" type="hidden" value="${code}" />
    </div>
  </div>
  </form>
</div>