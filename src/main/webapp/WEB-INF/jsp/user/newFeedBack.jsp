<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${ctx}/js/regist.js"></script>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#feedBackSave").click(function(){
		var name = $.trim($("#name").val());
		if(name.length == 0){
			layer.msg('请填写姓名！');
			return;
		}
		
		var advice = $("#advice").val();
		if(advice == null || $.trim(advice) == '' || $.trim(advice).length == 0){
			layer.msg('请填写反馈内容！');
			return;
		}else{
			if($.trim(advice).length > 200){
				layer.msg('反馈内容不能超过200字！');
				return;
			}
		}
		
		var contact = $.trim($("#contact").val());
		
		if(contact.length < 8 || contact.length > 12){
			layer.msg('请正确填写联系方式！');
			return;
		}
		
		var checkCode = $.trim($("#fbvalidateCode").val());
		if(checkCode.length != 4){
			layer.msg('验证码错误！');
			return;
		}
		
		$.post("${ctx}/dealFeedBack", {name : name,advice : advice,checkCode:checkCode,contact : contact}, function(
				data) {
			if(data.code == '0'){
				layer.msg(data.meg);
				return;
			}else{
				window.location.href = "${ctx}/feedBackMessage";
			}
			
		});
	});
});
</script>
	<div id="main">
   <div class="main-inner">
   <div class="bid-opt">
              <div class="bid-info">
                <ul class="items">
                  <li style="font-size:16px;">尊敬的用户:</li>
                  <li>您在浏览网站的过程中遇到了哪些问题，有什么样的感受，请告诉我们，您的意见是我们不断改进的动力， 请留下您遇到的
                    问题或提出宝贵的意见；这样有助于我们进行网站的完善和优化，力求为您营造最佳的体验流程。</li>
                </ul>
              </div>
              <form class="forms_span">
                <ul class="items">
                  <li class="txt"><em>*</em>反馈内容：</li>
                  <li>
                    <textarea name="advice" id="advice" class="site"></textarea>
                    <span class="tip txt-a"></span> </li>
                </ul>
                <ul style="clear:both;" class="items">
                  <li class="txt"><em class="txt-c">*</em>姓名：</li>
                  <li>
                    <input type="text" name="name" id="name" value="" class="input input-w" id="prompt">
                    <span class="tip"></span> </li>
                </ul>
                <ul class="items">
                  <li class="txt"><em class="txt-c">*</em>联系方式：</li>
                  <li>
                    <input type="text" name="contact" id="contact" value="" class="input focus" >
                    <span class="tip"></span> </li>
                </ul>
                <ul class="items">
                  <li class="txt"><em class="txt-c">*</em>验证码：</li>
                  <li>
                    <input type="text" value="" class="input input-yzm" name="fbvalidateCode" id="fbvalidateCode">
                    <a style="display: inline-block; margin-top: 3px; margin-left:10px; cursor:pointer;"> <img
									id="feedbackcheckcodeimag"
									src="${ctx}/getCheckCode?codeName=feedBackCode"
									onclick="changecode('${ctx}/getCheckCode?codeName=feedBackCode','feedbackcheckcodeimag')" /> </a> <span class="tip"></span> </li>
                </ul>
                <div class="save-button">
                  <input type="button" id="feedBackSave" name="保存" value="保存">
                </div>
              </form>
              <div style=" clear:both;" class="bid-info">
                <ul class="items">
                  <li>感谢您对蛙宝网的支持！我们相信用户的反馈能让蛙宝网日趋完善！</li>
                </ul>
              </div>
            </div>
   </div>
  </div>