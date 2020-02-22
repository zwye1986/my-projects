<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/i18N/CN/common.i18n_resource_zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/vldt/vldt.js"></script>

<script type="text/javascript">

$(document).ready(function(){ 
	 vadty('','N');/*加载验证框架*/
	
	 var linkList = window.parent.document.getElementsByTagName("link"); //获取父窗口link标签对象列表
     var head = document.getElementsByTagName("head").item(0);
     //外联样式
     for (var i = 0; i < linkList.length; i++) {
         var l = document.createElement("link");
         l.rel = 'stylesheet'
         l.type = 'text/css';
         l.href = linkList[i].href;
         head.appendChild(l);
     }
	
	
	$(".reset").click(function(){
		 $("a#fancyBox").attr('href','#'+this.alt).fancybox({
			 'scrolling' : 'no'
		 }).click();
	}) ;
	
    $(".reset1").click(function(){
		 $("a#fancyBox").attr('href','#'+this.alt).fancybox({
			 'scrolling' : 'no'
		 }).click();
	}) ; 
	
	$("#btnQuestion").click(function(){
		if(document.getElementById("question_form").onsubmit()){
			$.ajax({
			   type: "POST",
			   async: false,
			   cache: false,
			   dataType: "json",
			   data:{id:$("#securityCenterId").val(),question:$("#problem").val(),answer:$("#answer").val()},
			   url: "${ctx }/user/security/center/set",
			   success:function(data){
				   if(data == 0){
					   $.fancybox.close();
					   $('#mainForm', parent.document).submit();
				   }
			   }
			});
		}
	});
	
	$("#btnPassword").click(function(){
		if(document.getElementById("password_form").onsubmit()){
			$.ajax({
			   type: "POST",
			   async: false,
			   cache: false,
			   dataType: "json",
			   data:{id:$("#securityCenterId").val(),password:$("#payment").val()},
			   url: "${ctx }/user/security/center/set",
			   success:function(data){
				   if(data == 0){
					   $.fancybox.close();
					   $('#mainForm', parent.document).submit();
				   }
			   }
			});
		};
	});
	
	checkRePassword = function(){
		var showId = document.getElementById("confirm_password");
		var _showId = document.getElementById("payment");
		var _showId2 = document.getElementById("oldPayment");
		if($("#payment").val() != $("#confirm_password").val()){
			showMsg(showId,'两次密码输入不一致');
			return false;
		}else{
			var s = 0;
			$.ajax({
				   type: "POST",
				   async: false,
				   cache: false,
				   dataType: "json",
				   data:{password:$("#payment").val(),oldPassword:$("#oldPayment").val()},
				   url: "${ctx }/user/security/center/set/checkPassword",
				   success:function(data){
					   if(data == 0){
							s = 1;
					   }else if(data == 2){
						   s = 2;
					   }else if(data == 3){
						   s = 3;
					   }
				   }
				});
			if(s == 1){
				showMsg(_showId,'密码长度为8～20位,且必须包含数字、字母。');
				return false;
			}else if(s == 2){
				showMsg(_showId,'支付密码不能和登录密码相同！');
				return false;
			}else if(s == 3){
				showMsg(_showId2,'原密码输入错误！');
				return false;
			}
		}
		hideMsg(showId);
		return true ;
	}
	
	$("#btnMail").click(function(){
		if(document.getElementById("mail_form").onsubmit()){
			$.ajax({
			   type: "POST",
			   async: false,
			   cache: false,
			   dataType: "json",
			   data:{id:$("#securityCenterId").val(),mail:$("#mail_").val()},
			   url: "${ctx }/user/security/center/set",
			   success:function(data){
				   if(data == 0){
					   $.fancybox.close();
					   $('#mainForm', parent.document).submit();
				   }
			   }
			});
		}
	});
	
	$("#btnMobile").click(function(){
		if(document.getElementById("mobile_form").onsubmit()){
			$.ajax({
			   type: "POST",
			   async: false,
			   cache: false,
			   dataType: "json",
			   data:{id:$("#securityCenterId").val(),mobile:$("#mobile_").val()},
			   url: "${ctx }/user/security/center/set",
			   success:function(data){
				   if(data == 0){
					   $.fancybox.close();
					   $('#mainForm', parent.document).submit();
				   }
			   }
			});
		}
	});
	
	 checkCode =function(){
		var code  ;
		$.ajax({
		   type: "POST",
		   async: false,
		   cache: false,
		   url: "${ctx }/user/security/center/generate/code",
		   success:function(data){
			  code = data ;
		   }
		});
		var validateCode = document.getElementById("validate_code");
		if($("#validate_code").val() != code){
			showMsg(validateCode,'验证码输入不正确');
			return false;
		}
		hideMsg(validateCode);
		return true ;
	}
	
	
	var count = 60;
	var timer ;
	$("#hqyzm").click(function(){
		
		var mobileNumber = $.trim($("#mobile_").val().toString());
		if(mobileNumber == '' || mobileNumber.length < 11){
			showMsg(document.getElementById('mobile_'), '手机号码输入有误');
			return false;
		}else{
			
			$.ajax({
				   type: "GET",
				   async: false,
				   cache: false,
				   data:{mobileNumber:mobileNumber},
				   url: "${ctx }/user/security/center/generate/code",
				   success:function(data){
					   if(data == 0){
						   $(this).attr('disabled',true) ;
						   timer = setInterval(countDown,1000);
					   }else if(data == 1){
						   showMsg(document.getElementById('mobile_'), '手机号码有误！');
					   }else if(data == 2){
						   showMsg(document.getElementById('mobile_'), '手机号码不能与账户号码一致！');
					   }
				   }
				});
			
		}
		
	});
	
	var countDown = function(){
		$("#hqyzm").val("获取验证码("+count+")");
		count-- ;
		if( count == 0){
			clearInterval(timer);
			count = 60 ;
			$("#hqyzm").attr('disabled',false) ;
			$("#hqyzm").val("获取验证码") ;
		}
	}
	
	$("#closeSecurityCenter").click(function(){
		top.jQuery.alerts.confirm("确定关闭会员中心吗？关闭后将不享有会员中心提供的优惠和保证服务!","警告",function(r){
			 dynamicPost("${ctx }/user/security/center/close",{});
		});
	});
	
	$("input[name=pwd]").focus(function () { 
        $("input[name=pwd]").hide(); 
        $("input[name=password]").show().focus(); 
        
        $("input[name=password]").css("color","#000");

    }); 
    $("input[name=password]").blur(function () { 
        
        if ($(this).val() == "") { 
            $("input[name=pwd]").show(); 
            $("input[name=password]").hide(); 
        } 
    });
});
//-->
</script>
<a style="display: none" id="fancyBox"></a>

<div class="security" id="security">
	<input type="hidden" value="${securityCenter.id }" id="securityCenterId"/>
    <div class="securityT">帐号安全度： 
        <img src="${ctx }/images/user/aqd${percent }.png" style="width:392px; height:22px;"><em style="color:#f16244; font-size:45px; margin-left:5px;">${percent }%</em> 
    </div>
    <div class="securityB">
        <div class="securityB_title">您的帐号存在<em style="color:#f16244; font-size:45px;">${count }</em>项风险!</div>
        <ul class="securityB_content">
            <li> <span><img src="${ctx }${securityCenter.mail == null ? '/images/user/security02.png' : '/images/user/security01.png' } "></span><span class="bj">安全邮箱：<em>忘记密码时，安全邮箱可以帮您找回您的信息。</em></span>
                <input type="button" value="" ${securityCenter.mail == null ? "class='reset'" : "class='reset1'" } alt="divMail">
            </li>
            <li> <span><img src="${ctx }${securityCenter.password == null ? '/images/user/security02.png' : '/images/user/security01.png' }"></span><span class="bj">支付密码：<em>设置支付密码能够很好的保障你的资金安全。</em></span>
                <input type="button" value=" " ${securityCenter.password == null ? "class='reset'" : "class='reset1'" } alt="divPassword">
            </li>
            <li> <span><img src="${ctx }${securityCenter.answer == null ? '/images/user/security02.png' : '/images/user/security01.png' }"></span><span class="bj">安全问题：<em>完善您的安全问题，保证您的资料不外泄。</em></span>
                <input type="button" value=" " ${securityCenter.answer == null ? "class='reset'" : "class='reset1'" } alt="divQuestion" >
            </li>
            <li> <span><img src="${ctx }${securityCenter.mobile == null ? '/images/user/security02.png' : '/images/user/security01.png' }"></span><span class="bj">绑定手机NO2：<em>如果您的手机号码丢失，绑定的第二个手机号将会接管您的账户。</em></span>
                <input type="button" value=" " ${securityCenter.mobile == null ? "class='reset'" : "class='reset1'" } alt="divMobile">
            </li>
            <li style="background-image: url('${ctx}/images/user/security_date.jpg'); height: 30px;line-height: 30px;">
            	<em style="color: white; float: left;padding-left: 10px;">功能截止日期：<fmt:formatDate value="${securityCenter.expiryDate}"  pattern="yyyy-MM-dd"/></em>
           		<!-- <em style="color: white; float: right;padding-right: 10px; cursor: pointer;" id="closeSecurityCenter">关闭会员中心</em>-->
            </li>
        </ul>
    </div>
    <div style="display: none;">
	    <div  class="problem_security" id="divQuestion" style="width:460px; height:250px; overflow:auto">
	    	 <form  method="post" id="question_form">
	    	 		<div class="problem_securityT">安全问题</div>
				    <div class="problem"> <span class="label">问题：</span>
				        <select name="problem" id="problem">
				        	<c:forEach var="data" items="${securityQuestions }">
				        		<option value="${data.name }" ${data.name == securityCenter.question ? 'selected' : '' }>${data.name }</option>
				        	</c:forEach>
				        </select>　 
				    </div>
				    <div class="answer"> <span class="label">答案：</span>
				        <input type="text"   name="answer" value="${securityCenter.answer }" class="text" id="answer" vr="0&&2" lth="64">
				        <span id="span_answer">　 </span>
				    </div>
				    <div class="answer_btn">
				        <input type="button" class="security_btn" id="btnQuestion">
				    </div>
	    	 </form>
		</div>
		<div class="payment_pwd" id="divPassword" style="width:460px; height:300px; overflow:auto">
			<form  method="post" id="password_form">
			    <div class="payment_pwdT">设置支付密码<font style="font-size: 10px;">（提示：密码长度为8～20位,且必须包含数字、字母。）</font></div>
			    <div class="payment"> 
			    <div style="margin-left: -15px">
			    	<span class="label">原支付密码：</span>
			         <input type="text" id="oldPaymentText" name="pwd" value="初次设置不需要填写该项！">
			         <input type="password" id="oldPayment" name="password" vr="0" style="display: none;">
			        <span id="span_oldPayment">　 </span></div>
			    </div>
			    <div class="payment"> 
			    	<span class="label">支付密码：</span>
			        <input type="password" id="payment" vr="0"><span id="span_payment">　 </span>
			    </div>
			     <div class="payment"> 
			     	<span class="label">重复密码：</span>
			        <input type="password" id="confirm_password" vr="0&&1&&23" lth="24" code="checkRePassword();"><span id="span_confirm_password">　 </span>
			    </div>
			    <div class="answer_btn">
			        <input type="button" class="security_btn" id="btnPassword">
			    </div>
			</form>
		</div>
		<div class="secure_mailbox" id="divMail" style="width:460px; height:210px; overflow:auto">
		    <form  method="post" id="mail_form">
			    <div class="payment_pwdT">设置安全邮箱</div>
			     <div class="payment"> 
			     	<span class="label">邮箱地址：</span>
			        <input type="text" id="mail_" vr="0&&11" ><span id="span_mail_">　 </span>
			    </div>
			    <div class="answer_btn">
			        <input type="button" class="security_btn" id="btnMail">
			    </div>
			</form>
		</div>
		<div class="payment_pwd" id="divMobile" style="width:460px; height:250px; overflow:auto">
		    <form  method="post" id="mobile_form">
			    <div class="payment_pwdT">绑定第二个手机</div>
			    <div class="payment"> 
			    	<span class="label">手机号：</span>
			        <input type="text" id="mobile_" vr="0&&6"><span id="span_mobile_"">　 </span>
			    </div>
			     <div class="payment"> 
			     	<span class="label">验证码：</span>
			        <input type="text" id="validate_code" vr="0&&23" code="checkCode();" size="6">
			        <input type="button" id="hqyzm" class="hqyzm" value="获取验证码"/><span id="span_validate_code">　 </span>
			    </div>
			    <div class="answer_btn">
			        <input type="button" class="security_btn" id="btnMobile">
			    </div>
			</form>
		</div>
	</div>
	
</div>