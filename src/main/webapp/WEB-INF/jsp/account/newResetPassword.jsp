<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/17/14
  Time: 4:02 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" />
<script type="text/javascript">
    $(document).ready(function(){
        $(document ).tooltip();
        $("#xgmm_tj").click(function(){
            var oldPwd = $("#oldPwd").val();
            var newPwd = $("#newPwd").val();
            var newPwdRepeat = $("#newPwdRepeat").val();
            $.post("${ctx }/user/manager/dealResetPassword", {oldPwd : oldPwd,newPwd : newPwd,newPwdRepeat:newPwdRepeat}, function(
                    data) {
                if(data.code == "s"){
                    alert("修改密码成功！");
                    top.location.href = "${ctx }/j_spring_security_logout";
                }else{
                    $("#"+data.tip_id).empty();
                    $("#"+data.tip_id).html(data.msg);
                }
            });
        });
    });
</script>
<div id="main">
<div class="model-box">
<!--选项卡开始-->
<div class="tab_box">
<div class="lyz_tab_left">
    <div class="pro_con111">
        <ul>
            <li onclick="window.location.href='${ctx}/user/manager/userDetail'"  >基本资料</li>
            <li onclick="window.location.href='${ctx}/user/manager/bindCard'" >银行卡绑定</li>
            <li onclick="window.location.href='${ctx}/user/manager/resetPassword'" class="hover">修改密码</li>
            <li onclick="window.location.href='${ctx}/user/manager/setMessageRule'">短信提醒</li>
            <li onclick="window.location.href='${ctx}/user/manager/upload'">上传头像</li>
            <li onclick="window.location.href='${ctx}/user/security/center'">会员中心</li>
        </ul>
    </div>
</div>
<div class="lyz_tab_right">
<div  id="con_one_3" class="hover">
    <div class="bid-opt">
        <form class="forms_span" action="${ctx }/user/manager/dealResetPassword" method="post" id="resetPassWord">

            <ul class="items">
                <li class="txt"><em class="txt-c">*</em>原始密码：</li>
                <li>
                    <input type="password"  class="input input-w" id="oldPwd" name="oldPwd" />
                    <span class="tip" id="oldPwdErr" style="padding-left: 0px;"></span> </li>
            </ul>
            <ul class="items">
                <li class="txt"><em class="txt-c">*</em>输入新密码：</li>
                <li>
                    <input type="password" id="newPwd" name="newPwd" value="" class="input"  title="提示：密码长度为8～20位,且必须包含数字、字母。"/>
                    <span class="tip" id="newPwdErr" style="padding-left: 0px;"></span>
                </li>
            </ul>
            <ul class="items">
                <li class="txt"><em class="txt-c">*</em>确认新密码：</li>
                <li>
                    <input class="input" type="password" id="newPwdRepeat" name="newPwdRepeat" value=""  />
                    <span class="tip" id="newPwdRepeatErr" style="padding-left: 0px;"></span>
                </li>
            </ul>
            <div class="sub-button">
                <input type="button" value="提交" name="提交" id="xgmm_tj"> <input type="reset" class="reset" value="重置" name="重置">
            </div>

        </form>
    </div>
</div>
<div class="clear"></div>
</div>
</div>
</div>
</div>
