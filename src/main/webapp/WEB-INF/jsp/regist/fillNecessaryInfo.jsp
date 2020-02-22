<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script src="${ctx}/js/carousel.js" type="text/javascript"></script>
<script src="${ctx}/js/account.set.js" type="text/javascript"></script> 
<link href="${ctx}/css/account-settings.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(function(){
		$("#tj").click(function(){
			$("#tip").hide();
			$("#submitForm").submit();
		});
		
	});
</script>
<div id="wrap">
<form action="${ctx}/dealSetNewPassword" method="post" id="submitForm">
	<div class="login-entry">
		<div class="head">
			<h2>填写必要资料</h2>
			<b class="line"></b>
		</div>
		<div class="login-error" style="font-size: 12px; <c:if test="${not empty error }">display:block</c:if>  ">
			<table>
				<tbody>
					<tr>
						<td id="tip">${error }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<ul class="login-items">
			<li>
				<label>昵称</label>
				<input type="text" id="nickName" name="nickName" class="input" maxlength="20" value="${nickName }"/>
				<input type="hidden" name="mobileNumber" value="${mobileNumber }" />
				<input type="hidden" name="code" value="${code }" />
			</li>
			<li>
				<label>密码</label>
				<input id="newPwd" class="input" type="password" maxlength="20"  name="newPwd" value="${newPwd }" autocomplete="off" />
			</li>
			<li>
				<label>确认新密码</label> <input id="confirmPwd" class="input" type="password" maxlength="20" name="newPwdRepeat" value="${newPwdRepeat }"  />
			</li>
			<li>
				<label>安全问题</label>
				<select id="question" name="question">
					<c:forEach var="data" items="${securityQuestions }">
						<option value="${data.id }" <c:if test="${data.id eq questionId }">selected="selected"</c:if>>${data.name }</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<label>答案</label>
				<input class="input" maxlength="20" name="answer" value="${answer }" />
			</li>
		</ul>
		<div class="login-button">
			<input name="提交" type="button" value="提交" id="tj"  />
		</div>
	</div>
	</form>
	<div class="adSwitch">
		<div class="switch-wrap">
			<a href="" class="icons prev">prev</a><a href="" class="icons next">next</a>
			<div class="switch-container"
				style="position: relative; width: 300px; overflow: hidden;">
				<ul class="items" id="adSwitch"
					style="width: 999999px; position: relative; left: -300px;">
					<li style="float: left; list-style: none; margin-right: 0px;">
						<div class="img">
							<img src="${ctx }/img/home/003.png" width="110" height="110">
						</div>
						<h2>到期拿回返利及本金</h2>
						<p>返利次日到账！提现最快 当天到账</p>
						<p>
							<a href="/">了解更多&gt;</a>
						</p>
					</li>
					<li style="float: left; list-style: none; margin-right: 0px;">
						<div class="img">
							<img src="${ctx }/img/home/001.png" width="110" height="110">
						</div>
						<h2>点点游戏就得高收益</h2>
						<p>50 倍银行存款利息，100 纳币起步，0 手续费</p>
						<p>
							<a href="/">了解更多&gt;</a>
						</p>
					</li>
					<li style="float: left; list-style: none; margin-right: 0px;">
						<div class="img">
							<img src="${ctx }/img/home/002.png" width="110" height="110">
						</div>
						<h2>网上理财安全保障</h2>
						<p>VIP会员 特权多多，安全支付 全额保障</p>
						<p>
							<a href="/">了解更多&gt;</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>