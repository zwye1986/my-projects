<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/regist.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#xgmm_tj").click(function() {
			doRegist('${ctx}','checkcodeimag');
		});
	});
</script>
<%
	String inviteCode = request.getParameter("inviteCode");
	if (inviteCode == null) {
		inviteCode = (String) session.getAttribute("inviteCode");
		if (inviteCode == null) {
			inviteCode = "";
		}
	}
%>

<div class="headerblack">
	<div id="header">
		<h1 id="logo" class="fl">
			<a target="_top" href="${ctx }/index.html"> <img id="wb_Logo"
				src="${ctx }/images/help/wb_Logo.png">
			</a>
		</h1>
		<em
			style="height: 140px; width: 300px; text-align: left; line-height: 170px; color: #09a0ff; font-size: 18px;">.网络金融理财新选择.</em>
	</div>
</div>
<div class="cnt">
	<div class="cnt_title">获取返利步骤：1.注册蛙宝网&gt;2.开心“点”游戏&gt;3.轻松“拿”人民币！</div>
	<div class="cnt_c">
		<div class="cnt_c_title">
			<p
				style="width: 200px; margin-left: 60px; font-size: 18px; color: #6B6B6B;">手机注册</p>
		</div>
		<div id="reset_pwd fl">
			<ul class="xgmm">
				<li><em
					style="width: 106px; display: inline-block; text-align: right; font-style: normal;">推荐码(可选):</em>
					<input type="text"  class="xg_reset" id="inviteCode" maxlength="6" value="<%=inviteCode%>" name="inviteCode">
				</li>

				<li><b class="ftx04">*</b>请输入手机号: <input type="text" value="" class="xg_reset" name="mobileNumber" id="mobileNumber" maxlength="11" /></li>
				<%--<li style="position: relative;"><b class="ftx04">*</b>请输入验证码:
					
					<input type="text" value="" class="xg_reset" id="checkCode" maxlength="4" name="checkCode" />
				</li>--%>
			</ul>
			<p style="margin-left: 140px; margin-top: 30px;">
				<input id="checkbox" name="checkbox" type="checkbox" checked="checked"><a
					href="${ctx }/agreement.html" style="color: #444;">阅读并同意协议内容</a>
			</p>
			<p>
				<input type="button" style="background:url(${ctx}/images/user/register_btn.png); " id="xgmm_tj"  />

			</p>
			<p style="margin-top: -195px; margin-left: 470px;">
				<img id ="checkcodeimag" src="${ctx}/getCheckCode?codeName=registCode" onclick="changecode('${ctx}/getCheckCode?codeName=registCode','checkcodeimag')" style="height: 30px">
			</p>
		</div>
		<div class="cnt_r fr">
			<img src="${ctx }/images/pwd_bg.png">
		</div>
	</div>
</div>
