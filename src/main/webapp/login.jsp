<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript">
var environment = {
	userVo : '',
	role : '',
	userId : '',
	basePath : '',
	userCash : '',
	globalPath : 'http://www.wowpower.cn/'
};
</script>
<link href="${ctx}/css/account-settings.css" type="text/css" rel="stylesheet">
 <script src="${ctx}/js/global-1.1.0.min.js" type="text/javascript"></script>
<script src="${ctx}/js/carousel.js" type="text/javascript"></script>
<script src="${ctx}/js/account.set.js" type="text/javascript"></script> 
<script type="text/javascript" src="${ctx}/js/regist.js"></script>

	<script type="text/javascript">
	if (window != top){
		top.location.href = location.href;
	}

	document.onkeydown = function(event_e){  
        if(window.event)  
         event_e = window.event;  
         var int_keycode = event_e.charCode||event_e.keyCode;  
         if(int_keycode ==13){ 
        	 checkValidateCode('${ctx}');
   		}
  	} ;
	
	 function chekPwdfor_login() {
	        $("#messagePwd").hide();
	    }
	
	 function chekNkNamefor() {
		 $("#messageUserName").hide();
	    }
	
	</script>
<body>
<div id="wrap">
  <div class="login-entry">
    <div class="head">
      <h2>用户登录</h2>
      <b class="line"></b> </div>
    <div class="login-error" style="font-size:12px;display: block;color: #349CD8;">
      <table>
        <tr>
          <td id="tip">${loginMsg }</td>
        </tr>
      </table>
    </div>
    <form action="${ctx}/userlogin" id="loginForm" name="loginForm" method="post" >
      
    <ul class="login-items">
      <li>
        <label>用户名</label>
        <input type="text" id="uin" name="mobileNumber" class="input" onblur="chekNkNamefor();" maxlength="32">
    	<input id="inviteCode" type="hidden" name="inviteCode" value="<c:out value='${sessionScope.inviteCode}'/>">
        <div class="error" id="messageUserName" style="display: block">
            <c:choose>
			   <c:when test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION }">
			         <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION == '账号有误.'}">
						<div class="ts_message" id="ts_message">
							<em class="ts_img"><img src="${ctx }/images/error.png" /></em><a class="ts_text">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION}</a>
						</div>
					 </c:if>
				</c:when>
			   </c:choose>
        </div>
      </li>
      <li>
        <label>密码</label>
        <input type="password" name="password" id="password" class="input" onblur="chekPwdfor_login();"  maxlength="20">
        <div class="error" id="messagePwd" style="display: block">
        <c:choose>
			   <c:when test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION }">
					<c:choose>
			   			<c:when test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION }">
				         <c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION == '密码有问题.'}">
							<div class="ts_message" id="ts_message">
								<em class="ts_img"><img src="${ctx }/images/error.png" /></em><a class="ts_text">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION}</a>
							</div>
						 </c:if>
					</c:when>
			   </c:choose>
				</c:when>
			   <c:when test="${not empty error }">
			               用户名或者密码错误！
			   </c:when>
			   </c:choose>
        </div>
      </li>
      <li  id="verifycodeLi">
        <label>验证码</label>
        <input type="text" id="yzm" name="yzm" class="input" maxlength="4" style="width:100px" >
        <a style="display: inline-block; margin-top: 3px; margin-right: 35px;"> &nbsp;&nbsp;<img
					id="logincheckcodeimag" style="height: 40px"
					src="${ctx}/getCheckCode?codeName=loginCode"
					onclick="changecode('${ctx}/getCheckCode?codeName=loginCode','logincheckcodeimag')" ></a>
	 	<div class="error" id="messagePwd">
			    <c:choose>
				    <c:when test="${not empty error }"> </c:when>
				     <c:otherwise>
							<div class="ts_message" id="ts_message" style="display: none;">
								<em class="ts_img"><img src="${ctx }/images/error.png" /></em><a class="ts_text">验证码不正确!</a>
							</div>
					 </c:otherwise>
						</c:choose>
			     </div>			
		</li>
    </ul>
    <div class="login-check">
      <input type="checkbox" id="checkWeekly" disabled="disabled">
      <label for="checkWeekly">SVIP登陆</label>
      <input type="checkbox" id="checkWeekly" checked="checked">
      <label for="checkWeekly">普通用户登陆</label>
    </div>
    <div class="login-button">
      <input name="登录" type="button" value="登录" onclick="checkValidateCode('${ctx}','logincheckcodeimag');">
    </div>
    </form>
    <div class="login-server"> <a href="${ctx }/findPwd.html" class="weibo" style="color:#8a949c" target="_self" >忘记密码？</a><a href="${ctx}/regist.html" class="reg">马上注册</a> </div>
  </div>
  <div class="adSwitch">
    <ul class="items" id="adSwitch">
      <li>
        <div class="img"><img src="${ctx}/img/home/001.png" width="110" height="110"></div>
        <h2>点点游戏就得高收益</h2>
        <p>50 倍银行存款利息，100 纳币起步，0 手续费</p>
        <p><a href="${ctx }/help.html" target="_blank">了解更多></a></p>
      </li>
      <li>
        <div class="img"><img src="${ctx}/img/home/002.png" width="110" height="110"></div>
        <h2>网上理财安全保障</h2>
        <p>VIP会员 特权多多，安全支付 全额保障</p>
        <p><a href="${ctx }/help.html" target="_blank">了解更多></a></p>
      </li>
      <li>
        <div class="img"><img src="${ctx}/img/home/003.png" width="110" height="110"></div>
        <h2>到期拿回返利及本金</h2>
        <p>返利次日到账！提现最快 当天到账</p>
        <p><a href="${ctx }/help.html" target="_blank">了解更多></a></p>
      </li>
    </ul>
  </div>
</div>
</body>
</html>