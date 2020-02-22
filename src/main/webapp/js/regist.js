
function checkValidateCode(path,id) {
    var checkCode = $("#yzm").val();
    var inviteCode = $("#inviteCode").val();
    $.ajax({
        type : "POST",
        async : false,
        cache : false,
        dataType : "json",
        url : path + "/" + checkCode + "/validateLoginCheckCode",
        success : function(data) {
            if (data.resultCode == 0) {
                //判断是否第一次登录
                var mobileNumber = $.trim($("#uin").val());
                $.ajax({
                    type : "POST",
                    async : false,
                    cache : false,
                    dataType : "json",
                    url : path + "/" + mobileNumber + "/checkIsFirstLogin",
                    success : function(data) {
                        if (data.resCode == 0) {
                            $("#loginForm").submit();
                        } else {
                            if (isEmpty(inviteCode)){
                                $("#loginForm").attr("action",
                                        path + "/setNewPassword").submit();
                            }else{
                                $("#loginForm").attr("action",
                                        path + "/setNewPassword?inviteCode="+inviteCode).submit();
                            }
                        }

                    }
                });

            } else {
                $("#ts_message").css("display", "block");
                document.getElementById(id).src = document.getElementById(id).src + "&now=" + new Date();
            }
        }
    });
}

function changecode(path, id) {
    document.getElementById(id).src = path + "&now=" + new Date();
}

function doRegist(path,id) {
    var mobileNumber = $("#mobileNumber").val();
    var checkCode = $("#checkCode").val();
    var inviteCode = $("#inviteCode").val();
    if (!$("input[type='checkbox']").is(':checked')) {
        top.$.alerts.alert("注册本网站需要同意协议内容，请勾选！");
        return;
    }
    $.post(path + "/doRegist", {
        mobileNumber : mobileNumber,
        checkCode : checkCode,
        inviteCode : inviteCode
    }, function(data) {
        if (data.resultCode == 'f') {
            top.$.alerts.alert(data.resultMsg);
            document.getElementById(id).src = document.getElementById(id).src + "&now=" + new Date();
            return;
        }else if (data.resultCode == 's') {
            if (isEmpty(inviteCode)){
                window.location.href = path + "/login.html?times=0";
            }else{
                window.location.href = path + "/login.html?times=0&inviteCode="+ inviteCode;
            }
        }
    });
}

function doRegister(path,id) {
    var mobileNumber = $("#mobileNumber").val();
    var checkCode = $("#checkCode").val();
    var inviteCode = $("#inviteCode").val();
    if (!$("input[type='checkbox']").is(':checked')) {
        top.$.alerts.alert("注册本网站需要同意协议内容，请勾选！");
        return;
    }
    $.post(path + "/doRegist", {
        mobileNumber : mobileNumber,
        checkCode : checkCode,
        inviteCode : inviteCode
    }, function(data) {
        if (data.resultCode == 'f') {
            top.$.alerts.alert(data.resultMsg);
            document.getElementById(id).src = document.getElementById(id).src + "&now=" + new Date();
            return;
        }else if (data.resultCode == 's') {
            if (isEmpty(inviteCode)){
                window.location.href = path + "/login.html?times=0";
            }else{
                window.location.href = path + "/login.html?times=0&inviteCode="+ inviteCode;
            }
        }
    });
}
