/*!
 * Date Set Account
 * Version: 1.0.0
 * Modify: By Wang Ping
 * Date: 07-03-2013 15:42:48 (GMT Time)
 */
var tagEle = {
    authCode: $('#authCode'),
    signButton: $('#signSubmit'),
    signName: $('#signName'),
    signPwd: $('#signPwd'),
    signPwdRepeat: $('#signPwdRepeat'),
    signPwdStrength: $('#pwdstrength'),
    input: $('input.input'),
    protocol: $("#protocol"),
    myMail: $('#myMail'),
    myMobile: $('#mobile')
};
var tagEleTip = {
        signNameTip: $('#signNameTip'),
        imgnkname: $('#imgnkname'),
        signPwdTip: $('#signPwdTip'),
        imgepassword: $('#imgepassword'),
        signPwdRepeatTip: $('#signPwdRepeatTip'),
        imgepasswordsure: $('#imgepasswordsure'),
        signAuthCodeTip: $("#signAuthCodeTip"),
        protocolTip: $('#protocolTip'),
        mobileMessage:$('#mobileMessage'),
        emailMessageTip:$('#emailMessage')
};
var authRegExp = {
    integer: "^-?[1-9]\\d*$", //整数
    integer1: "^[1-9]\\d*$", //正整数
    integer2: "^-[1-9]\\d*$", //负整数
    number: "^([+-]?)\\d*\\.?\\d+$", //数字
    number1: "^[1-9]\\d*|0$", //正数（正整数 + 0）
    number2: "^-[1-9]\\d*|0$", //负数（负整数 + 0）
    decimal: "^([+-]?)\\d*\\.\\d+$", //浮点数
    decimal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
    decimal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
    decimal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
    decimal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
    decimal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
    ascii: "^[\\x00-\\xFF]+$", //仅ACSII字符
    chinese: "^[\\u4e00-\\u9fa5]+$", //仅中文
    color: "^[a-fA-F0-9]{6}$", //颜色
    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
    letter: "^[A-Za-z]+$", //字母
    letterL: "^[a-z]+$", //小写字母
    letterU: "^[A-Z]+$", //大写字母
    mobile: "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$", //手机
    password: "^.*[A-Za-z0-9\\w_-]+.*$", //密码
    fullNumber: "^[0-9]+$", //数字
    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
    qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
    rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
    tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //用户名
    realname: "^[A-Za-z\\u4e00-\\u9fa5]+$", // 真实姓名
    zipcode: "^\\d{6}$", //邮编
    notempty: "^\\S+$" //非空
};
//验证规则
var authRules = {
    isNull: function (str) {
        return (str == "" || typeof str != "string");
    },
    betweenLength: function (str, _min, _max) {
        return (str.length >= _min && str.length <= _max);
    },
    isUid: function (str) {
        return new RegExp(authRegExp.username).test(str);
    },
    fullNumberName: function (str) {
        return new RegExp(authRegExp.fullNumber).test(str);
    },
    isPwd: function (str) {
        return /^.*([\W_a-zA-z0-9-])+.*$/i.test(str);
    },
    isPwdRepeat: function (str1, str2) {
        return (str1 == str2);
    },
    isEmail: function (str) {
        return new RegExp(authRegExp.email).test(str);
    },
    isTel: function (str) {
        return new RegExp(authRegExp.tel).test(str);
    },
    isMobile: function (str) {
        return new RegExp(authRegExp.mobile).test(str);
    },
    checkType: function (element) {
        return (element.attr("type") == "checkbox" || element.attr("type") == "radio" || element.attr("rel") == "select");
    },
    isRealName: function (str) {
        return new RegExp(authRegExp.realname).test(str);
    }
};
//验证文本提示
var authPrompt = {
    signName: {
        onFocus: "请输入字母、数字或中文",
        succeed: "",
        isNull: "请输入字母、数字或中文",
        error: {
            beUsed: "该用户名已被使用，请重新输入！",
            badLength: "用户名长度只能在4-20位字符之间",
            badFormat: "用户名只能由中文、英文、数字及“_”、“-”组成",
            fullNumberName: "用户名不能是纯数字，请确认输入的是手机号或者重新输入"
        },
        onFocusExpand: function () {
            
        }
    },
    pwd: {
        onFocus: "6-16位字符，可使用字母、数字或符号的组合，不建议使用纯数字，纯字母，纯符号",
        succeed: "",
        isNull: "请输入密码",
        error: {
            badLength: "密码长度只能在6-16位字符之间",
            badFormat: "密码只能由英文、数字及标点符号组成",
            simplePwd: "该密码比较简单，有被盗风险，建议您更改为复杂密码，如字母+数字的组合"
        },
        onFocusExpand: function () {
            
        }
    },
    pwdRepeat: {
        onFocus: "请再次输入密码",
        succeed: "",
        isNull: "请输入密码",
        error: {
            badLength: "密码长度只能在6-16位字符之间",
            badFormat1: "密码只能由英文、数字及标点符号组成",
            badFormat2: "两次输入密码不一致"
        }
    },
    mobileCode: {
        onFocus: "",
        succeed: "",
        isNull: "请输入短信验证码",
        error: "验证码错误"
    },
    protocol: {
        onFocus: "",
        succeed: "",
        isNull: "请先阅读并同意《有利网用户注册协议》",
        error: ""
    },
    empty: {
        onFocus: "",
        succeed: "",
        isNull: "",
        error: ""
    }
};
$(function(){
    if(typeof($('#adSwitch')[0]) != 'undefined'){
        $('#adSwitch').switchCarousel();
    };
    Util.jTab('#bindTabs','#bindPanel','click');
    tagEle.input.focus(function(){
        $(this).addClass('focus')
    }).blur(function(){
        $(this).removeClass('focus')
    });
    
    if(typeof(authModeType) != "undefined"){
        if(authModeType.email!=null&&authModeType.email!=''){
            $('#verFind').attr('data-rel','email');
            var emailInput = $('.email-verify').find('#email');
            emailInput.val(authModeType.email);
            emailInput.attr('readonly',true);
        }else if(authModeType.mobile!=null&&authModeType.mobile!=''){
            $('#verFind').attr('data-rel','phone');
            var mobileInput = $('.phone-verify').find('#pwdMobile');
            mobileInput.val(authModeType.mobile);
            mobileInput.attr('readonly',true);
        }
    }
    authMode($('#verFind'),'使用邮箱找回&gt;','使用手机找回&gt;');
    authMode($('#verifyMode'),'使用邮箱验证&gt;','使用手机验证&gt;');
	
    //邮箱及用户名自动提示
    tagEle.myMail.TipMail({
        onSelect: function(){
        	chekEmailfor();
        }
    });
    //注册名默认提示文字
    tagEle.signName.val('请输入字母、数字或中文').css({color:'#999'}).attr('autoComplete','off');
    tagEle.signName.focus(function(){
        if($(this).val()=='请输入字母、数字或中文'){
            $(this).val('');
            $(this).css({color:'#333'});
            tagEleTip.signNameTip.css({top:0})
            tagEleTip.signNameTip.html("4-18个字符，一个汉字为两个<br>字符，推荐使用中文会员名");
            tagEleTip.signNameTip.css("color","#8a949c");
        }
    }).blur(function(){
        if($(this).val()==''){
            $(this).val('请输入字母、数字或中文');
            $(this).css({color:'#999'})
        }
    });
     //手机验证获取验证码弹出层
    var phoneCode = $('#phoneCode');
    $('#phoneCodeButton').click(function(){
        sendVerifycodeByMobile();
    });
    phoneCode.click(function(){
        Util.Countdown(phoneCode);
    });
	$('#getCodeButton').click(function(){
		Util.Countdown($('#getCodeButton'));
	});
	
});
//选择手机或邮箱的验证方式 
function authMode(vm,txt,txt2){
	var vde = $('.v-mode')
	//var vm = vde.find('.verify-mode');
	var email = 'email';
	var phone = 'phone';
	var text = {
		em: txt,//'使用邮箱验证&gt;',
		ph: txt2//'使用手机验证&gt;'
	};
	var pTar = $('.'+phone+'-verify');
	var eTar = $('.'+email+'-verify');
	//eTar.show();
	//vm.html(text.ph);
	//vm.attr('data-rel',phone);
	var tart = vm.attr('data-rel');
	if(tart == phone){
		vm.attr('data-rel',email);
		vm.html(text.em);
		pTar.show();
		eTar.hide();
	} else if(tart == email){
		vm.attr('data-rel',phone);
		vm.html(text.ph);
		pTar.hide();
		eTar.show();
	};
    vde.show();
    vm.click(function(){
        var _this = $(this)
        var rel = _this.attr('data-rel');
        if(rel == phone){
            _this.attr('data-rel',email);
            _this.html(text.em);
            pTar.show();
            eTar.hide();
        } else if(rel == email){
            _this.attr('data-rel',phone);
            _this.html(text.ph);
            pTar.hide();
            eTar.show();
        };
    })
};

var rsEmail = false;
var bEmail;
function chekEmailfor() {
    var email = tagEle.myMail.val();
    if(bEmail!=email) {
        rsEmail = false;
    }
    if(rsEmail) return true;
    var re = false;
    bEmail = email;
	var myReg = /^([-_A-Za-z0-9\.]+)@([-_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
    if (email == "" || email == null) {
    	tagEleTip.emailMessageTip.html("请输入Email");
    	tagEleTip.emailMessageTip.css("color","#FF0000");
        return re;
    } else if (email.length>32) {
    	tagEleTip.emailMessageTip.html("Email不正确");
    	tagEleTip.emailMessageTip.css("color","#FF0000");
        return re;
    } else if (!myReg.test(email)) {
    	tagEleTip.emailMessageTip.html("Email不正确");
    	tagEleTip.emailMessageTip.css("color","#FF0000");
        return re;
    } else {
    	tagEleTip.emailMessageTip.html("");
        re = true;
    }
    var rs = restEmail(email);
    if(rs == true){
    	tagEleTip.emailMessageTip.html("邮箱已存在");
    	tagEleTip.emailMessageTip.css("color","#FF0000");
        return false;
    }else{
    	tagEleTip.emailMessageTip.html("");
    	tagEleTip.emailMessageTip.append("<i class='icons green-proper'></i>");
        rsEmail = true;
        return true;
    }
};

//验证邮箱是否已经激活
function restEmail(str){    // 判断邮箱是否重名
     $.ajaxSetup({
               async: false
               });
    var rs;
    //var email = tagEle.myMail.val();
    $.get(environment.basePath+"/rest/user/checkmail/"+str+"?d="+ new Date().getTime(), function(result){
        rs = result;
    });
    return rs;
};

function restUserEmail(nickName,email){    // 判断邮箱是否重名
     $.ajaxSetup({
               async: false
               });
    var rs;
    $.get(environment.basePath+"/rest/user/checkmail/"+nickName+"/"+email+"?d="+ new Date().getTime(), function(result){
        rs = result;
    });
    return rs;
};

//邮箱验证下一步
function activeByMail(){
    if(chekEmailfor()){
        var url = environment.basePath + "/secure/sendVerifycodeByMail.action";
        var email = tagEle.myMail.val();
        $.post(url, {
            "email" : email
        }, function(data, varStatus) {
            if(data=="0"){
                return false;
            }else if(data>0){
                var callbackUrl = environment.basePath+"/secure/register/verify-mail/";
                window.location.href=decodeURIComponent(callbackUrl);
            }
        }, "text"); 
    }
};

// 判断手机是否重名
function restmobile(str){
     $.ajaxSetup({
               async: false
               });
    var rs;
    //var mobile = tagEle.myMail.val();
    $.get(environment.basePath+"/rest/user/checkmobile/"+str+"?d="+ new Date().getTime(), function(result){
        rs = result;
    });
    return rs;
};


function restUserMobile(nickName,mobile){
     $.ajaxSetup({
               async: false
               });
    var rs;
    //var mobile = tagEle.myMail.val();
    $.get(environment.basePath+"/rest/user/checkmobile/"+nickName+"/"+mobile+"?d="+ new Date().getTime(), function(result){
        rs = result;
    });
    return rs;
};

//判断输入手机号是否合法
function checkMobilefor () {
    var rsMobile = false;
    var mobile=tagEle.myMobile.val();
    if (mobile == "" || mobile == null) {
        tagEleTip.mobileMessage.html("请输入您的手机号码");
        tagEleTip.mobileMessage.css("color","#FF0000");
        return rsMobile;
    }
    if (mobile.length!=11) {
        tagEleTip.mobileMessage.html("请输入11位手机号码");
        tagEleTip.mobileMessage.css("color","#FF0000");
        return rsMobile;
    }else if (!authRules.isMobile(mobile)) {
        tagEleTip.mobileMessage.html("手机号码不正确");
        tagEleTip.mobileMessage.css("color","#FF0000");
        return rsMobile;
    }else if(restmobile(mobile)){
        tagEleTip.mobileMessage.html("该手机号已被注册");
        tagEleTip.mobileMessage.css("color","#FF0000");
        return rsMobile;
    }else{
        tagEleTip.mobileMessage.html("");
        tagEleTip.mobileMessage.append("<i class='icons green-proper'></i>");
        rsMobile = true;
        return rsMobile;
    }
}

function sendVerifycodeByMobile  () {
    var url = environment.basePath + "/secure/sendVerifycodeByMobile.action";
    var mobile = $('#mobile').val();
    if(checkMobilefor()){
        $('#phoneCodeEntry #phoneNumber').html(mobile);
        $.post(url, {
            "mobile" : mobile
        }, function(data, varStatus) {
            if(Number(data) > 0 ){
                Util.jDialog.Modal('phoneCodeDialog','phoneCodeEntry');
                Util.Countdown($('#phoneCode'));
            }else if(Number(data) == 0){
                tagEleTip.mobileMessage.html("验证码发送失败");
                tagEleTip.mobileMessage.css("color","#FF0000");
            }else if(Number(data) < 0){
                tagEleTip.mobileMessage.html("一分钟只能获取一次验证码");
                tagEleTip.mobileMessage.css("color","#FF0000");
                Util.jDialog.Modal('phoneCodeDialog','phoneCodeEntry');
            }
        }, "text"); 
    }
};

function activeByMobile () {
    var url = environment.basePath + "/secure/activeByMobile.action";
    validteCde = $('#mobileValidateCode').val();
    $.post(url, {
            "validteCde" : validteCde
        }, function(data, varStatus) {
            if(data=='-1'){
                $('#mobileCodeMessage').html("验证码错误");
                return false;
            }else if(data=='0'){
                $('#mobileCodeMessage').html("验证出现错误，请重新验证");
                return false;
            }else if(data > 0){
                var callbackUrl = environment.basePath+"/secure/activeSuccess.action";
                window.location.href=decodeURIComponent(callbackUrl);
            }
        }, "text"); 
};
//邮件输入自动提示
(function($) {
    //字符串转正则函数
    function parseRegExp(pattern, attributes) {
        var imp = /[\^\.\\\|\(\)\*\+\-\$\[\]\?]/g;
        var imp_c = {};
        imp_c['^'] = '\\^';
        imp_c['.'] = '\\.';
        imp_c['\\'] = '\\\\';
        imp_c['|'] = '\\|';
        imp_c['('] = '\\(';
        imp_c[')'] = '\\)';
        imp_c['*'] = '\\*';
        imp_c['+'] = '\\+';
        imp_c['-'] = '\\-';
        imp_c['$'] = '\$';
        imp_c['['] = '\\[';
        imp_c[']'] = '\\]';
        imp_c['?'] = '\\?';
        pattern = pattern.replace(imp,function(o) {
            return imp_c[o];
        });
        return new RegExp(pattern, attributes);
    };
    //创建提示列表项
    var createLists = function(value, mails) {
        var html = '';
        var hasAt = /@/.test(value);
        if (hasAt) {
            var arr = value.split('@');
            if (arr.length > 2) return html;
            value = arr[0];
            var regx = parseRegExp('@' + arr[1], 'i');
        };
        value = hasAt ? value.split('@')[0] : value;
        //html += '<a href="javascript:;">' + value + '</a>';
        for (var i = 0, len = mails.length; i < len; i++) {
            if (hasAt && !regx.test(mails[i])) continue;
            html += '<a href="javascript:;">' + value + mails[i] + '</a>';
        };
        return html.replace(/^<a([^>]*)>/, '<a$1 class="hover">');
    };
    //改变列表激活状态
    var changeActive = function(panle, up) {
        //如果提示框隐藏跳出执行
        if (panle.css('display') === 'none') return;
        var liActive = panle.find('a.hover');
        if (up) {
            var liPrev = liActive.prev();
            liPrev = liPrev.length ? liPrev: panle.find('a:last');
            liActive.removeClass('hover');
            liPrev.addClass('hover');
        } else {
            var liNext = liActive.next();
            liNext = liNext.length ? liNext: panle.find('a:first');
            liActive.removeClass('hover');
            liNext.addClass('hover');
        }
    };
    //展示隐藏提示
    var toggleTip = function(val, panle, mails) {
        //如果输入为空，带空格，中文字符，英文逗号，@开头，或者两个以上@直接隐藏提示
        if (!val || /[,]|[\u4e00-\u9fa5]|\s|^@/.test(val) | val.split('@').length > 2 | val.length > 26) {
            panle.hide();
        } else {
            var lists = createLists(val, mails);
            //如果返回的有列表项展开提示，否则隐藏。
            if (lists) {
                panle.html(lists).show();
            } else {
                panle.hide();
            }
        }
    };
    //调用接口
    $.fn.TipMail = function(opt) {
        var defaults = {
            mails: ['@163.com', '@126.com', '@qq.com', '@sina.com', '@gmail.com', '@sohu.com', '@vip.163.com', '@vip.126.com', '@188.com', '@139.com', '@yeah.net'],
            onSelect: null,
            width: null,
            zindex: 1000
        };
        opt = $.extend({},defaults, opt);
        opt.onSelect = typeof opt.onSelect === 'function' ? opt.onSelect: defaults.onSelect;
        return this.each(function() {
            var input = $(this);
            input.attr('autocomplete', 'off');
            //绑定事件
            input.bind('keyup',function(e) {
                var _this = $(this);
                var list = null;
                var keyCode = e.keyCode;
                //只在第一次按键时生成列表
                if (!_this.data('data-mail')) {
                    list = $('<div class="emailtip" style="display:none"></div>');
                    //放入DOM树
                    $('body').append(list);
                    list.css({
                        top:_this.offset().top + _this.outerHeight(),
                        left:_this.offset().left,
                        width: opt.width || input.outerWidth() - list.outerWidth() + list.width(),
                        zIndex:opt.zindex
                    });
                    //绑定鼠标事件
                    list.delegate('a', 'mouseenter mouseleave click',function(e) {
                        switch (e.type) {
                        case 'mouseenter':
                            $(this).addClass('hover').siblings().removeClass('hover');
                            break;
                        case 'click':
                            var text = $(this).text();
                            input.val(text).focus();
                            opt.onSelect.call(this, text);
                            break;
                        case 'mouseleave':
                            break;
                        default:
                            break;
                        }
                    });
                    //点击其它地方关闭提示框
                    $(document).bind('click',function(e) {
                        if (e._this === input[0]) return;
                        list.hide();
                    });
                    _this.data('data-mail', list);
                };
                //获取提示框
                list = _this.data('data-mail');
                //获取文本框内容
                var val = _this.val();
                //根据不同按键做处理
                switch (keyCode) {
                case 9://tab键
                    break;
                case 13://回车键
                    break;
                case 38://向上键
                    break;
                case 40://向下键
                    break;
                default://默认
                    toggleTip(val, list, opt.mails);
                    break;
                }
            }).bind('keydown',function(e) {
                var list = $(this).data('data-mail');
                //如果提示框不存在跳出
                if (!list) return;
                //根据按键执行不同操作
                switch (e.keyCode) {
                case 9://按tab键隐藏列表
                    list.hide();
                case 38://向上键
                    changeActive(list, true);
                    break;
                case 40://向下键
                    changeActive(list);
                    break;
                case 13://回车键
                    //如果提示框隐藏跳出执行
                    if (list.css('display') === 'none') return;
                    e.preventDefault();
                    var text = list.find('a.hover').text();
                    input.val(text).focus();
                    list.hide();
                    opt.onSelect.call(this, text);
                    break;
                default:
                    break;
                }
            });
        });
    };
})(jQuery);