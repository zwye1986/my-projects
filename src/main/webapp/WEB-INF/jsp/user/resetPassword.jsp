<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/user.js"></script>
	<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<script type="text/javascript">
$(document).ready(function() {
		var linkList = window.parent.document.getElementsByTagName("link"); //获取父窗口link标签对象列表
		var head = document.getElementsByTagName("head").item(0);
		//外联样式
		for ( var i = 0; i < linkList.length; i++) {
			var l = document.createElement("link");
			l.rel = 'stylesheet';
			l.type = 'text/css';
			l.href = linkList[i].href;
			head.appendChild(l);
		}
		
		$("#xgmm_tj").click(function(){
			var oldPwd = $("#oldPwd").val();
			var newPwd = $("#newPwd").val();
			var newPwdRepeat = $("#newPwdRepeat").val();
			$.post("${ctx }/user/manager/dealResetPassword", {oldPwd : oldPwd,newPwd : newPwd,newPwdRepeat:newPwdRepeat}, function(
					data) {
				top.$.alerts.alert(data.msg);
				if(data.code == "s"){
					top.location.href = "${ctx }/j_spring_security_logout";
				}
			});
		});
	});
</script>
<form action="${ctx }/user/manager/dealResetPassword"
	method="post" id="resetPassWord">
		<ul class="xgmm">
			<li><b class="ftx04">*</b>原&nbsp;始&nbsp;密&nbsp;码&nbsp;: <input
				class="xg_reset" type="password" id="oldPwd" name="oldPwd" value="" />
				</li>
			<li><b class="ftx04">*</b>输入新密码: <input class="xg_reset"
				type="password" id="newPwd" name="newPwd" value="" />（提示：密码长度为8～20位,且必须包含数字、字母。）
				</li>
			<li><b class="ftx04">*</b>确认新密码: <input class="xg_reset"
				type="password" id="newPwdRepeat" name="newPwdRepeat" value="" />
				</li>
		</ul>
		<P style="margin-left: 85px;">
			<input id="xgmm_tj" type="button" value=" "> <input
				id="xgmm_cz" type="reset" value=" ">
		</P>
		

</form>