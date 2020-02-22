<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>找回密码－设置新密码</title>
<link href="${ctx }/css/szxmm_old.css" rel="stylesheet" />
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#xgmm_tj").click(function() {
			var nickName = $("input[name='nickName']").val();
			if (!checkString(nickName)) {
				top.$.alerts.alert("昵称只能包含下划线、字母、数字、汉字！");
				return;
			}
			$("form").submit();
		});
	});
</script>
</head>

<div class="headerblack">
	<div id="header">
		<h1 id="logo" class="fl">
			<a target="_top" href="${ctx }/index.html"> <img id="wb_Logo"
				src="${ctx }/images/help/wb_Logo.png">
			</a>
		</h1>
		<em
			style="height: 140px; width: 300px; text-align: left; line-height: 140px; color: #09a0ff; font-size: 18px;">.找回密码</em>
	</div>
</div>
<div class="cnt">
	<div class="cnt_title">找回密码</div>
	<div class="cnt_c">
		<div class="cnt_c_title"></div>

		<div id="reset_pwd fl">
			<form
				action="${pageContext.request.contextPath }/validateCellMessage.html"
				method="post">
				<ul class="xgmm">
					<li><em style="Letter-spacing: 3px; float: left;">短信验证码:</em>
						<input type="text" id="xg_reset" value="${messageCode }" name="messageCode"
						maxlength="20" /> <c:if test="${oldPwdError != null }">
							<em class="ts_img"><img style="margin-top: 5px"
								src="${pageContext.request.contextPath }/images/user/com_bg.png"></em>
							<a class="ts_text">${oldPwdError}</a>
						</c:if></li>
					<li><em style="Letter-spacing: 3px; float: left;">输入新密码:</em>
						<input type="password" id="xg_reset" name="newPwd" value="${ newPwd}"
						maxlength="20" /> <c:choose >
							<c:when test="${newPwdError != null }"><em class="ts_img"><img style="margin-top: 5px"
								src="${pageContext.request.contextPath }/images/user/com_bg.png"></em>
							<a class="ts_text">${newPwdError}</a></c:when>
							<c:otherwise>
							<font style="font-size: small;">（提示：密码长度为8～20位,且必须包含数字、字母。）</font>
							</c:otherwise>
							
						</c:choose></li>
					<li><em style="Letter-spacing: 3px; float: left;">确认新密码:</em>
						<input type="password" id="xg_reset" name="newPwdRepeat" value="${newPwdRepeat }"
						maxlength="20" /> <c:if test="${newPwdRepeatError != null }">
							<em class="ts_img"> <img style="margin-top: 5px"
								src="${pageContext.request.contextPath }/images/user/com_bg.png">
							</em>
							<a class="ts_text">${newPwdRepeatError}</a>
						</c:if></li>

					<li><em style="Letter-spacing: 3px; float: left;">&nbsp;&nbsp;安全问题:</em>${userQuestion.name }</li>

					<li><em style="Letter-spacing: 3px; float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答案:</em>
						<input type="text" id="xg_reset" name="answer" value="${answer }"
						maxlength="20" /> <c:if test="${answerError != null }">
							<em class="ts_img"> <img style="margin-top: 5px"
								src="${pageContext.request.contextPath }/images/user/com_bg.png">
							</em>
							<a class="ts_text">${answerError}</a>
						</c:if></li>
				</ul>
				<P class="tj">
						<input id="xgmm_tj" type="submit" value=" ">
						<c:if test="${success != null }">
							<em style="color: #eb5412; margin-left: 5px;">${sucess }<a
								href="${pageContext.request.contextPath }/login.html"
								style="color: #eb5412; text-decoration: underline;">登录</a></em>
						</c:if>

					</P>
			</form>
		</div>
		<div class=" cnt_r fr">
			<img src="${ctx }/images/pwd_bg.png">
		</div>
	</div>
</div>

