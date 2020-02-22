<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/user.css" rel="stylesheet" />
<link href="${ctx }/css/feedback.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<script type="text/javascript" src="${ctx}/js/regist.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#feedBackSave").click(function(){
		var name = $.trim($("#name").val());
		if(name.length == 0){
			top.$.alerts.alert("请填写姓名！");
			return;
		}
		
		var advice = $("#advice").val();
		if(advice == null || $.trim(advice) == '' || $.trim(advice).length == 0){
			top.$.alerts.alert("请填写反馈内容！");
			return;
		}else{
			if($.trim(advice).length > 200){
				top.$.alerts.alert("反馈内容不能超过200字！");
				return;
			}
		}
		var contact = $.trim($("#contact").val());
		
		if(contact.length < 8 || contact.length > 12){
			top.$.alerts.alert("请正确填写联系方式！");
			return;
		}
		
		var checkCode = $.trim($("#fbvalidateCode").val());
		if(checkCode.length != 4){
			top.$.alerts.alert("验证码错误！");
			return;
		}
		
		$.post("${ctx}/dealFeedBack", {name : name,advice : advice,checkCode:checkCode,contact : contact}, function(
				data) {
			if(data.code == '0'){
				top.$.alerts.alert(data.meg);
				return;
			}else{
				window.location.href = "${ctx}/feedBackMessage";
			}
			
		});
	});
});
</script>
	<div class="RTitle"></div>
	<div class="feedback_C">
		<h5>意见反馈</h5>
		<p>
			尊敬的用户:<br> 您在浏览网站的过程中遇到了哪些问题，有什么样的感受，请告诉我们，您的意见是我们不断改进的动力，
			请留下您遇到的问题或提出宝贵的意见；这样有助于我们进行网站的完善和优化，力求为您营造最佳的体验流程。
		</p>
		<div class="feedback_Content">
				<div class="fd">
					<span class="label"><em
						style="display: inline-block; float: left; line-height: 30px;margin-left: 10px"><b
							class="ftx04">*</b>反馈内容:</em>
					</span>
					<textarea class="fd_c" name="advice" id="advice" style="resize : none;" ></textarea>
				</div>
				<div class="item" style="padding-top: 20px">

					<div class="iteml fl">
						<span style="margin-left: 30px;" class="label"> <b
							class="ftx04">*</b><em>姓名:</em>
						</span> <input type="text" value="" sta="0" name="name" tabindex="1" maxlength="20"
							autocomplete="off" class="text" id="name">
					</div>
					<div style="width: 400px;display: none;" class="focus  fl" id="nameError">
						<em class="com_bg"><img src="${ctx }/images/user/com_bg.png"></em><span style="margin-left: 20px;margin-top: 5px" id="nameSpan"></span>
					</div>
				</div>
				<div class="item">

					<div class="iteml fl">
						<span style="margin-right: -12px;" class="label"> <b
							class="ftx04">*</b>联系方式：
						</span> <input type="text" value="" sta="0" name="contact" tabindex="1" maxlength="12"
							autocomplete="off" class="text" id="contact">
					</div>
					<div style="width: 400px; display: none;" class="focus  fl" id="contactError" >
						<em class="com_bg"><img src="${ctx }/images/user/com_bg.png"></em><span style="margin-left: 20px;margin-top: 5px" id="contactSpan"></span>
					</div>
				</div>



				<div class="ver">
					<span class="label"><em
						style="display: inline-block; float: left; line-height: 30px;margin-left:5px;"><b
							class="ftx04">*</b>验证码:</em>
					</span>&nbsp;&nbsp;&nbsp;<input type="text" class="verT" name="fbvalidateCode" id="fbvalidateCode" maxlength="4" /><img
									id="feedbackcheckcodeimag"
									src="${ctx}/getCheckCode?codeName=feedBackCode"
									onclick="changecode('${ctx}/getCheckCode?codeName=feedBackCode','feedbackcheckcodeimag')" />
				</div>
				<div class="button">
					<input type="button" value=" " class="save" id="feedBackSave">
				</div>
			<p style="border-top: 1px dashed #f16244">感谢您对蛙宝网的支持！我们相信用户的反馈能让蛙宝网日趋完善！</p>
		</div>
	</div>
	<div class="RB"></div>
