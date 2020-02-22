<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<title>蛙宝网探店活动入口</title>
<link href="${ctx}/css/shop.css" rel="stylesheet" />
<link href="${ctx}/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<script type="text/javascript">
var imgPath_session = '${ctx}' + '/images/alerts';
</script>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript" src="${ctx}/js/regist.js"></script>
<script type="text/javascript" src="${ctx }/js/user.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#signUpSave").click(function(){
		var nickName = $.trim($("#nickName").val());
		if(nickName.length == 0){
			top.$.alerts.alert("请填写姓名！");
			return;
		}
		var liveProvince= $.trim($("#liveProvince").val());
		var liveCity= $.trim($("#liveCity").val());
		var liveArea= $.trim($("#liveArea").val());
		var liveaddress=$.trim($("#liveaddress").val());
		if(liveProvince=='0'|liveCity=='0'||liveArea=='0'){
			top.$.alerts.alert("请选择您所居住的城市和地区！");
			return;
		}
		
		var question = $("#question").val();
			if($.trim(question).length > 400){
				top.$.alerts.alert("提出的问题不能超过400字！");
				return;
			}
		var mobileNumber = $.trim($("#mobileNumber1").val());
		
		if(mobileNumber.length < 8 || mobileNumber.length > 12){
			top.$.alerts.alert("请正确填写蛙宝账号！");
			return;
		}
		
		var checkCode = $.trim($("#verifycode").val());
		if(checkCode.length != 4){
			top.$.alerts.alert("验证码错误！");
			return;
		}
		
		$.post("${ctx}/dealSignUp", {nickName : nickName,question : question,checkCode:checkCode,mobileNumber:mobileNumber,liveProvince:liveProvince,liveCity:liveCity,liveArea:liveArea,liveaddress:liveaddress}, function(
				data) {
			if(data.code == '0'){
				top.$.alerts.alert(data.meg);
				return;
			}else{
				top.$.alerts.alert("报名成功!");
				$("#nickName").val("");
				$("#mobileNumber1").val("");
				$("#liveaddress").val("");
				$("#question").val("");
				$("#verifycode").val("");
				changecode('${ctx}/getCheckCode?codeName=signUpCode','feedbackcheckcodeimag');
			}
			
		});
	});
});
</script>
</head>

<body>
<div class="wrap">
  <div class="content">
    <div class="shop_logo"><span><a href="${ctx}/index"><img width="170px" height="60" src="${ctx}/images/shops/hd_logo.png"/></a></span></div>
    <div class="date"><span>2014.05.03</span></div>
    <div class="message">
      <div class="message_title">填写报名信息</div>
      <div class="message_c bid-opt">
        <div class="message_cl">
          <div class="reg-items">
            <ul class="items">
              <li class="txt"><em>*</em>蛙宝账号</li>
              <li>
                <input type="text" maxlength="18" class="input" name="mobileNumber1" id="mobileNumber1"/>
              </li>
            </ul>
            <ul class="items">
              <li class="txt"><em>*</em>姓名</li>
              <li>
                <input type="text" maxlength="18" class="input" name="nickName"  id="nickName"/>
              </li>
            </ul>
            
            <ul class="items">
             <li class="txt"><em>*</em>居住地:</li>
              <li>
                <select
				id="liveProvince" name="liveProvince" style="text-align: center;"
				onchange="moveCity('${pageContext.request.contextPath}/getCityJson');">
				<option value="0">选择省／直辖市</option>
				<c:forEach var="item" items="${liveProvince }">
					<option value="${item.provinceid }">${item.province }</option>
				</c:forEach>
			</select>
               <select id="liveCity" name="liveCity"
				style="text-align: center;"
				onchange="moveArea('${pageContext.request.contextPath}/getAreaJson');">
				<option value="0">选择城市</option>
			</select>
                <select id="liveArea" name="liveArea"
				style="text-align: center;">
				<option value="0">选择区县</option>
			</select>
                <textarea class="site" id="liveaddress"></textarea>
              </li> 
            </ul>
          </div>
        </div>
        <div class="message_cr">
          <div class="reg-items">
            <ul class="items">
              <li class="txt" style="display:inline-block; width:50%; text-align:left; margin-left:40px; margin-top:-10px;"><em>*</em>您对蛙宝网最想提出的问题?</li>
              <li style="clear:both;">
                <textarea class="site" style="margin-left:50px; height:57px;" id="question"></textarea>
              </li>
            </ul>
            <ul class="items">
              <li class="txt"> <em>*</em> 验证码 </li>
              <li>
              <input id="verifycode" class="input" type="text"  style="width:70px;margin-right:10px;" maxlength="4" name="verifycode">
              </input>
              <img
									id="feedbackcheckcodeimag"
									src="${ctx}/getCheckCode?codeName=signUpCode"
									onclick="changecode('${ctx}/getCheckCode?codeName=signUpCode','feedbackcheckcodeimag')" /> <a class="refreshcode r3" onclick="changecode('${ctx}/getCheckCode?codeName=signUpCode','feedbackcheckcodeimag')">换一张？ </a> </li>
            </ul>
            <div class="tj-button"> <a  class="gbtn" id="signUpSave" ></a> </div>
          </div>
        </div>
      </div>
    </div>
    <div class="ts">*本活动最终解释权归蛙宝网所有</div>
  </div>
</div>
</body>
</html>
