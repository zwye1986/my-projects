<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>"
<link href="${ctx}/css/account-common.css" type="text/css" rel="stylesheet">
<link href="${ctx}/css/global-min.css" type="text/css" rel="stylesheet">
<link href="${ctx}/css/account-settings.css" type="text/css" rel="stylesheet">
<script src="${ctx}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx}/js/global-1.1.0.min.js" type="text/javascript"></script>
<script src="${ctx}/js/carousel.js" type="text/javascript"></script>
<script src="${ctx}/js/account.set.js" type="text/javascript"></script>
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript">
    $(document).tooltip();
    $(document).ready(function(){

        $("#getCode").click(function(){
            $("#tip").empty();
            sendCode();
        });

        $("#submit").click(function(){
            $("#sp").empty();
            $.ajax({
                cache: false,
                async: false,
                type: "post",
                url: $("#mainForm").attr("action"),
                data:$("#mainForm").serialize(),// 你的formid

                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    if(data.resCode == 0){
                        $("#tip").empty();
                        $("#tip").html(data.resMsg);
                        $("#error").show();
                    }else if(data.resCode == 1){
                        window.location.href="${ctx}/login.html";
                    }
                }
            });
        });
    });
    function countTime() {
        $("#sp").empty();
        $("#sp").html("<a style=\"color:#fff; text-decoration:none; margin-left:85px;\" class=\"cz_pwd\" id=\"secondCount\" href=\"###\">91</a>");
        var s = 90;//用来记录秒,因为需求是从1开始的。
        var w = setInterval(function () {
            s--;
            $("#secondCount").empty();
            $("#secondCount").html(s);
            if (s == 0) {
                clearInterval(w);
                $("#sp").empty();
                $("#sp").html("<a style=\"color:#fff; text-decoration:none; margin-left:85px;\" class=\"cz_pwd\" id=\"getCode\" href=\"###\" onclick=\"sendCode('${mobileNumber}');\">重新发送</a>");
            }
        }, 1000);
    }
    function sendCode(){
        var mobileNumber = $("#mobileNumber").val();
        if(isEmpty(mobileNumber)){
            $("#tip").empty();
            $("#tip").html("请输入手机号码！");
            $("#error").show();
            return;
        }else if(!checkMobileNumber(mobileNumber)){
            $("#tip").empty();
            $("#tip").html("请输入正确的手机号码！");
            $("#error").show();
            return;
        }else{
            $.post("${ctx}/"+mobileNumber+"/setCode",{},function(data){
                if(data.resCode == 0){
                    $("#tip").empty();
                    $("#tip").html(data.resMsg);
                }else{
                    countTime();
                }
            });
        }
    }
</script>
<div id="wrap">
    <div class="login-entry">
        <form action="${ctx }/validateCellMessage.html" method="post" id="mainForm" >
            <div class="head">
                <h2>找回密码</h2>
                <b class="line"></b> </div>
            <div class="login-error" style="font-size:12px;" id="error">
                <table>
                    <tbody>
                    <tr>
                        <td id="tip"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <ul class="login-items">
                <li>
                    <label>输入手机号</label>
                    <input type="text"  class="input"  maxlength="12" id="mobileNumber" name="mobileNumber"/>
                </li>
                <li >
       		<span id="sp">
       			<a class="cz_pwd" href="###" style="color:#fff; text-decoration:none; margin-left:85px;" id="getCode">获取短信验证码</a>
       		</span>
                </li>
                <li>
                    <label>短信验证码</label>
                    <input class="input" maxlength="6" id="code" name="code" />
                </li>
                <li>
                    <label>输入新密码</label>
                    <input id="newPwd" class="input" type="password" maxlength="20" name="newPwd" autocomplete="off"  title="提示：密码长度为8～20位,且必须包含数字、字母。"/>
                </li>
                <li>
                    <label>确认新密码</label>
                    <input id="newPwdRepeat" class="input" type="password" maxlength="20" name="newPwdRepeat" />
                </li>

            </ul>
            <div class="login-button">
                <input name="提交" type="button" value="提交" id="submit" />
            </div>
        </form>
    </div>
    <div class="adSwitch">
        <div class="switch-wrap">
            <a href="" class="icons prev">prev</a>
            <a href="" class="icons next">next</a>
            <div class="switch-container" style="position: relative; width: 300px; overflow: hidden;">
                <ul class="items" id="adSwitch" style="width: 999999px; position: relative; left: -300px;">
                    <li style="float: left; list-style: none; margin-right: 0px;">
                        <div class="img"><img src="${ ctx}/img/home/002.png" width="110" height="110"></div>
                        <h2>网上理财安全保障</h2>
                        <p>VIP会员 特权多多，安全支付 全额保障</p>
                        <p><a href="${ctx}/help.html" target="_blank">了解更多&gt;</a></p>
                    </li>
                    <li style="float: left; list-style: none; margin-right: 0px;">
                        <div class="img"><img src="${ ctx}/img/home/003.png" width="110" height="110"></div>
                        <h2>到期拿回返利及本金</h2>
                        <p>返利次日到账！提现最快 当天到账</p>
                        <p><a href="${ctx}/help.html" target="_blank">了解更多&gt;</a></p>
                    </li>
                    <li style="float: left; list-style: none; margin-right: 0px;">
                        <div class="img"><img src="${ ctx}/img/home/001.png" width="110" height="110"></div>
                        <h2>点点游戏就得高收益</h2>
                        <p>50 倍银行存款利息，100 纳币起步，0 手续费</p>
                        <p><a href="${ctx}/help.html" target="_blank">了解更多&gt;</a></p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>