<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div id="content">
			<div id="header">
				<h1 id="logo" class="fl">
					<a target="_top" href="${ctx }/index.html"> <img id="wb_Logo"
						src="${ctx }/images/help/wb_Logo.png">
					</a>
				</h1>
				<em
					style="height: 140px; line-height: 140px; color: #aaa; font-size: 38px;">.找回密码</em>
			</div>
			<form
				action="${pageContext.request.contextPath}/getValidateCode.html"
				method="post">
				<div id="reset_pwd">
					<ul class="xgmm">
						<li><b class="ftx04">*</b>请输入手机号: <input type="text"
							id="xg_reset" name="mobileNumber" value="" /></li>
					</ul>
					<c:if test="${not empty error}">
						<p style="color: red">
							<b class="ftx04">*</b>${error }
						</p>
					</c:if>
					<P>
						<input id="xgmm_tj" type="submit" value=" ">
					</P>
					<p style="color: #eb5412; margin-top: 20px;">提交后30秒内发送至您的手机，请注意查收！</p>
				</div>
			</form>
		</div>