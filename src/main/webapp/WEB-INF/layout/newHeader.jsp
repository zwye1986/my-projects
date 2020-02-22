<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以轻松的拿到返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。"/>
<meta name="keywords" content="蛙宝网,蛙宝,赚钱,注册返现,wowpower,挖宝,网赚,注册提现,	理财,返利,积分活动,打码,网上挣零花钱,赚钱,点击游戏,网赚项目,注册网赚" />
<title>蛙宝网-轻轻松松拿返利</title>
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/newCommon.css" rel="stylesheet" />
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<link rel="icon" href="${ctx }/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx }/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
<!-- WPA Button Begin -->
<script charset="utf-8" src="http://wpa.b.qq.com/cgi/wpa.php"></script>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<!-- WPA Button End -->
<script type="text/javascript">
$(function(){
	$.cookie.defaults = { path: '/'};
	
	$("#headMenu").find("li").click(function(){
		$.cookie('indexHref',null);
		$.cookie('indexHref',this.id);
	});
	
	var docwidth=$(window).width();
	var realwidth = (docwidth-1000)/2;
	if(realwidth<135){
		realwidth=135;
	}
	$(".right").css("right",realwidth-95);
//	$(".left").css("left",realwidth-135);
    $(".left").css("right",realwidth-95);
	
	var str = $(document).scrollTop(); 
	if(str<=168){
	
		$(".right").css("top",168-str);
	
	}else{
		$(".right").css("top",0);
	
	}
	if(str<=170){
		$(".left").css("top",170-str);
	
	}else{
		$(".left").css("top",0);
	
	}
	
	$(window).bind("scroll", function(){ 
		var str = $(document).scrollTop(); 
		if(str<=168){
		
			$(".right").css("top",168-str);
		//	$(".right").scrollTop(str);
		}else{
			$(".right").css("top",0);
		//	$(".right").scrollTop(150);
		}
		if(str<=170){
			$(".left").css("top",170-str);
		//	$(".right").scrollTop(str);
		}else{
			$(".left").css("top",0);
		//	$(".right").scrollTop(150);
		}
		}); 
	
	//当天签到人数
	$.post("${ctx}/getSignInByAllCount", {},
			function(data) {
				$("#todayHadSignIn").html(data.dayHasSignIn);
			});

	//判断用户当天是否已经签到 
	$.post("${ctx}/issignIn", {}, function(data) {
		if (data.flag == "havesignIn") {
			$("#nsigninstate").hide();
			$("#ysigninstate").show();
		} else {
			$("#ysigninstate").hide();
			$("#nsigninstate").show();
		}
	});

	//用户签到
	$("#nsigninstate").click(
					function() {
						$.post("${ctx}/user/account/signIn",{},
										function(data) {
											if (data.flag == "havesignIn"
													|| data.flag == "signInsuccess") {
												$("#nsigninstate").hide();
												$("#ysigninstate").show();
												$.post("${ctx}/getSignInByAllCount",{},
																function(data) {
																	$("#todayHadSignIn").html(data.dayHasSignIn);
																});
											}else if(data.flag=="signInnotTime"){
												layer.msg(data.msg);
											}else {
												$("#ysigninstate").hide();
												$("#nsigninstate").show();
											}
										});
					});
	
	
	$(".sh").click(function(){
		$(".road_list").hide();
		$(".app_title").hide();
		$(".right").hide();
		$(".zk").show();
		$.cookie('isclose',0);
	});
	
	$(".zk").click(function(){
		$(".road_list").show();
		$(".app_title").show();
		$(".right").show();
		$(".zk").hide();
		$.cookie('isclose',1);
	});
	
	if($.cookie('isclose')==0){
		$(".road_list").hide();
		$(".app_title").hide();
		$(".right").hide();
		$.cookie('isclose',0);
	}else{
		$(".road_list").show();
		$(".app_title").show();
		$(".right").show();
		$(".zk").hide();
		$.cookie('isclose',1);
	}
	
	
});

function checkInt(obj)
{
	 var re = /^[0-9]*[1-9][0-9]*$/ ;
     if (!re.test(obj))
    {
        return false;
     }else{
    	 return true;
     }
}  

function checkNum(obj)
{
 var re = /^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
     if (!re.test(obj))
    {
        return false;
     }else{
    	 return true;
     }
}  
</script>
</head>
<body>
<!--header start-->
<div id="header">
  <div class="inner">
    <div class="loginbar">
      <div class="for">
        <ul>
						<li>
						<sec:authorize access="isAuthenticated()">
						  <a href="#" style="text-decoration: none; color: #475058;">欢迎你！<em
								style="font-style: normal; color: #ea5413;">
								<sec:authentication property="principal.nickName" /></em>
								</a>
						</sec:authorize>		
						<sec:authorize access="isAuthenticated()">
							<a href="${ctx }/j_spring_security_logout" style="color: #475058;">［退出］</a> |&nbsp;&nbsp;
						</sec:authorize>
						</li>
						<li class="concerns"><em style="float: left">关注我们：</em><a
							href="https://twitter.com/venadasoftware" target="_blank" class="icons twitter">twitter</a><a
							href="https://www.facebook.com/vndsoftware" target="_blank" class="icons facebook">facebook</a><a
							href="http://t.qq.com/wowpower" target="_blank" class="icons tengxun">腾讯官方微博</a> <a
							href="http://weibo.com/wowpower" target="_blank" class="icons sina"
							rel="nofollow">新浪官方微博</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li> <a id="userReg" href="${ctx}/regist.html" target="_blank">注册 </a>
						<sec:authentication property="principal" var="currentUsr" scope="page"/>
						 <c:if test="${currentUsr eq '' or currentUsr eq null or currentUsr eq 'anonymousUser'}">
						 						/<a href="${ctx }/login.html">登&nbsp;录</a>
						 
						 </c:if>
						 </li>
					</ul>
        <!--
     <ul class="service">

      <li class="concerns"><b>关注我们</b><a href="/" target="_blank" class="icons twitter">twitter</a><a href="/" target="_blank" class="icons facebook">facebook</a><a href="/" target="_blank" class="icons tengxun">腾讯官方微博</a> <a href="http://www.wowpower.cn/" target="_blank" class="icons sina" rel="nofollow">新浪官方微博</a></li>
    </ul> <a href="register.html" style="color:#349cd8;" rel="nofollow">注册/</a><a href="login.html" style="color:#349cd8;">登录</a>
      --></div>
    </div>
    <div class="logo">
      <h2><a href="${ctx}/index.html"><img alt="蛙宝网" src="${ctx}/images/common/logo.png" width="211" height="70"></a></h2>
    </div>
    <div class="userbar">
      <ul class="nav" id="headMenu">
        <li><a href="${ctx }/index.html">首页</a></li>
        <li id="_game"><a href="${ctx }/game.html">游戏中心</a></li>
        <li id="_user"><a href="${ctx }/usertaskDetail.html">用户中心</a></li>
        <li id="_mall"><a href="${ctx }/mall.html">积分商城</a></li>
        <li id="_activity"><a href="${ctx }/activity.html">活动频道</a></li>
        <li id="_zhouchou"><a href="${ctx }/vip_index.html">VIP会员</a></li>
        <li id="_help"><a href="${ctx }/help.html">帮助中心</a></li>
      </ul>
      <div class="use"> <a style="cursor: pointer;" rel="nofollow"><img title="客户热线" width="226" height="41" src="${ctx}/images/common/hotline.png"/> </a> </div>
    </div>
  </div>
  <div class="b"></div>
</div>
<!--header end--> 

<!--left   start -->
<%-- 
    <div class="left">
    <div class="road_title"><img src="${ctx }/images/common/road_title.png"/></div>
     <div class="zk"><img src="${ctx }/images/common/zk.png" style="margin-left:7px; cursor:pointer" /></div>
    <ul class="road_list">
    <li class="road_list1"><a href="${ctx }/user/account/recharge" target="_blank"><i class="cz_icons icons"></i></a></li>
    <li class="road_list2"><a href="${ctx }/user/account/withdrawal" target="_blank"><i class="tx_icons icons"></i></a></li>
    <li class="road_list3"><a href="${ctx }/usertaskDetail.html" target="_blank"><i class="qb_icons icons"></i></a></li>
    <li class="road_list4"><a id="BizQQWPA" style="cursor: pointer;"><i class="zx_icons icons"></i></a></li>
      <script type="text/javascript">
      BizQQWPA.addCustom({aty: '0', a: '0', nameAccount: 800049764, selector: 'BizQQWPA'});
      </script> 
    </ul>
    <div class="app_down">
    <div class="app_title"><a href="${ctx }/ios.html" target="_blank"><i class="apptitle_icons icons"></i></a></div>
    </div>
    </div>
    --%>
     <!--left   end -->
     
     <!--right   start -->
      <div class="right" style="margin-left: 410px" >
      <sec:authentication property="principal" var="currentUsr" scope="page"/>
      <div class="sign_btn_h" style="display:none" ><img width="90" height="105" src="${ctx}/images/common/sign_btn_h.png"/></div>
      					
						<c:choose>
							<c:when test="${currentUsr eq '' or currentUsr eq null or currentUsr eq 'anonymousUser'}">
								<div class="sign_btn">
								<a href="${ctx}/signInDetail.html">
								 <img id="signinstate" width="90" height="105" src="${ctx}/images/common/sign_btn_n.png"/>
								</a>
								</div>
							</c:when>
							<c:otherwise>
							<div class="sign_btn" id="nsigninstate" style="display: none"><img  width="90" height="105" src="${ctx}/images/common/sign_btn_n.png"/></div>
									<div class="sign_btn_h" style="display: none;"  id="ysigninstate"  ><img width="90" height="105" src="${ctx}/images/common/sign_btn_h.png"/></div>
							</c:otherwise>
						</c:choose>
						
						
      
      <div class="sign_title"><img src="${ctx}/images/common/sign_title.png"/></div>
      <div class="sign_c">
      <div class="today_sign"><span >今日签到</span><span><em style="color:#64c7ee" id="todayHadSignIn"></em>人</span></div>
      <div style="margin-left:8px; margin-top:10px">	
      <wb:follow-button uid="3848323436" type="red_1" width="67" height="24" ></wb:follow-button></div>
       <div class="sign_detail"><a href="${ctx}/signInDetail.html">签到拿人民币</a></div>
      </div>
      <div class="zxkh"><img id="BizQQWPA" style="margin-left:5px; margin-top:-1px;cursor: pointer;" src="${ctx }/images/common/kf.gif"/>
       <span style=" margin-top:-27px;z-index:2; position:absolute; margin-left:20px; width:75px;">在线客服</span>
      </div>
      
         <script type="text/javascript">
      BizQQWPA.addCustom({aty: '0', a: '0', nameAccount: 4008916233, selector: 'BizQQWPA'});
      </script> 
        <div class="sh"><img onclick="javascript:window.open('${ctx}/integralLottery.html','_blank')" src="${ctx }/images/roll.gif" style="margin-left:-9px; cursor:pointer;margin-top: 10px;" />
        <span style=" margin-top:-20px;z-index:2; position:absolute; margin-left:20px; width:75px;">积分抽奖</span>
        </div>
        <div class="sh"><img src="${ctx }/images/common/sh .png" style="margin-left:8px; cursor:pointer" /></div>
          
      </div>
      <div class="left">
      <div class="zk"><img src="${ctx }/images/common/zk.png" style="margin-left:7px; cursor:pointer" /></div>
      </div>
      
     <!--right   end -->
