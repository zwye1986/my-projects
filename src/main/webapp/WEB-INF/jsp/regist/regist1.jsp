<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" >
$.ajaxSetup({ 
    async : false 
});   
	$(document).ready(function(){
		
		$("#mobileNumber").focus(function(){
			$("#mobileNumberTips").empty();
		});
		
		$("#mobileNumber").mouseout(function(){
			validateMobileNumber();
		});
		
		$("#checkCode").focus(function(){
			$("#checkCodeTip").empty();
		});
		
		$("#checkCode").mouseout(function(){
			validateCheckCode();
		});
		
		$("#nextStep").click(function(){
			var a = validateMobileNumber();
			var b = validateCheckCode();
			var c = validateAgreement();
			if(a&&b&&c){
				$.ajax({
	                cache: false,
	                async: false,
	                type: "post",
	                url: $("#registForm").attr("action"),
	                data:$("#registForm").serialize(),// 你的formid
	                
	                error: function(request) {
	                    alert("Connection error");
	                },
	                success: function(data) {
	                	if(data.resCode == 0){
	                		$("#mobileNumberTips").html(data.resMsg);
	                	}else if(data.resCode == 1){
	                		$("#checkCodeTip").html(data.resMsg);
	                	}else if(data.resCode == 2){
	                		if($("#inviteCode").val().length>0){
	                			window.location.href="${ctx}/"+data.mobileNumber+"/regist2?inviteCode="+$("#inviteCode").val();
	                		}else{
	                			window.location.href="${ctx}/"+data.mobileNumber+"/regist2";	
	                		}
	                	}else if(data.resCode == 3){
	                		alert(data.resMsg);
	                	}
	                }
	            });
			}else{
				return false;
			}
			
		});
		$("#protocol").attr("checked",true);
		$("#protocol").change(function(){
			if(document.getElementById("protocol").checked == false){
				$("#protocolTip").html("同意蛙宝网协议后才能进入下一步！");
				return false;
			}else{
				$("#protocolTip").empty();
			}
		});
	});
	
	function validateMobileNumber(){
		var mobileNumber = $("#mobileNumber").val();
		if(isEmpty(mobileNumber)){
			$("#mobileNumberTips").html("手机号码不能为空！");
			return false;
		}else{
			var v = 0;
			if(checkMobileNumber(mobileNumber)){
				$.post("${ctx}/"+mobileNumber+"/checkMobileNumber",{},function(data){
					v = data.resCode;
					if(data.resCode == 0){//后台校验失败
						$("#mobileNumberTips").html(data.resMsg);
					}else if(data.resCode == 1){
						$("#mobileNumberTips").html("<i class=\"icons green-proper\"></i>");
					}
				});
				if(v == 1){
					return true;
				}
				return false;
			}else{//js校验不通过
				$("#mobileNumberTips").html("请正确填写手机号码！");
				return false; 
			}
			return false;
		}
		return false;
	}
	
	function validateCheckCode(){
		var checkCode = $("#checkCode").val();
		if(isEmpty(checkCode)){
			$("#checkCodeTip").html("验证码错误！");
			return false;
		}else{
			var v = 0;
			$.post("${ctx}/"+checkCode+"/registCode/validateRegistCheckCode",{},function(data){
				v = data.resCode;
				if(data.resCode == 0){
					$("#checkCodeTip").html(data.resMsg);
				}else if(data.resCode == 1){
					$("#checkCodeTip").html("<i class=\"icons green-proper\"></i>");
				}
			});
			if(v == 1){
				return true;
			}
		}
		return false;
	}
	function validateAgreement(){
		if(document.getElementById("protocol").checked == false){
			$("#protocolTip").html("同意蛙宝网协议后才能进入下一步！");
			return false;
		}
		return true;
	}
</script>
<div id="wrapper">
  <div class="reg-title">
    <h2>账户注册</h2>
    <b class="line"></b></div>
  <div class="reg-steps r3">
    <ol class="reg-step-1">
      <li class="step-1"><span class="txt"><i class="icons dot24">1</i>填写手机号</span></li>
      <li class="step-2"><span class="txt"><i class="icons dot24">2</i>手机短信领取密码</span></li>
      <li class="step-3"><span class="txt"><i class="icons dot24">3</i>注册成功</span></li>
    </ol>
  </div>
  <form action="${ctx }/regist1" method="post" id="registForm" name="registForm">
  <div class="reg-items">
    <ul class="items">
      <li class="txt"><em>*</em>手机号</li>
      <li>
        <input type="text" id="mobileNumber" name="mobileNumber"  class="input" maxlength="12" autocomplete="off" style="color: rgb(153, 153, 153);" />
        <span class="tip" id="signNameTip"></span> 
        <span id="mobileNumberTips" class="tip" style="color:#D35352;"></span>
        </li>
    </ul>
    <%
	String inviteCode = request.getParameter("inviteCode");
	if (inviteCode == null) {
		inviteCode = (String) session.getAttribute("inviteCode");
		if (inviteCode == null) {
			inviteCode = "";
		}
	}
%>
    <ul class="items">
      <li class="txt">推荐码</li>
      <li>
        <input type="text"  id="inviteCode" name="inviteCode" value="<%=inviteCode%>" class="input" maxlength="10" autocomplete="off" />
        <span id="signPwdTip" class="tip" ></span>
	  </li>
       
    </ul>
    
    <ul class="items">
      <li class="txt"><em>*</em>验证码</li>
      <li>
        <input type="text" id="checkCode" name="checkCode" class="input" maxlength="4" style="width:70px;margin-right:10px;" />
        <img id ="checkcodeimag" 
        	 src="${ctx}/getCheckCode?codeName=registCode" 
        	 onclick="changecode('${ctx}/getCheckCode?codeName=registCode','checkcodeimag')" 
        	 width="100" height="40" alt="验证码" title="验证码" class="authcode" style="margin-top:-18px;" />
        <a class="refreshcode r3" href="###" onclick="changecode('${ctx}/getCheckCode?codeName=registCode','checkcodeimag')" >换一张？</a>
        <span id="checkCodeTip" class="tip" style="color:#D35352;"></span>
      </li>
    </ul>
    <div class="agreement">
      <input type="checkbox" id="protocol" />
      <label>阅读并同意《<a href="${ctx}/agree.html" target="_blank">蛙宝网协议</a>》</label>
      <span id="protocolTip" style="color:#D35352;"></span>
    </div>
    <div class="next-step"> <a href="###" class="gbtn" id="nextStep">下一步</a> </div>
  </div>
  </form>
</div>