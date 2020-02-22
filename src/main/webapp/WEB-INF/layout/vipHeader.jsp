<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以拿到轻松的返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。"/>
<meta name="keywords" content="蛙宝网,蛙宝,赚钱,注册返现,wowpower,挖宝,网赚,注册提现,	理财,返利,积分活动,打码,网上挣零花钱,赚钱,点击游戏,网赚项目,注册网赚" />
<title>蛙宝网-网络金融理财新选择</title>
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/vipindex.css" rel="stylesheet" />
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<link href="${ctx }/css/top.css" rel="stylesheet"  type="text/css"/>
<link rel="stylesheet" href="${ctx }/css/animate.css" type="text/css" media="all">
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<link rel="icon" href="${ctx }/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx }/favicon.ico" type="image/x-icon">
<link href="${ctx }/css/zshy.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript" src="${ctx}/js/regist.js"></script>
<script type="text/javascript" src="${ctx}/js/slide.js"></script>

<script type="text/javascript">
tf_script={"img_number":5,"slideShowDelayOption":"5000","easeEffect":"random","disableDragOption":true}
</script>
<!--CSS3 Html5动画-->


<script type="text/javascript">
if (window != top){
	top.location.href = location.href;
}
	
	var imgPath_session = '${ctx}' + '/images/alerts';

$(function(){
	$.cookie.defaults = { path: '/'};
	
	
	$("a[name ='vipmenu']").click(function(){
		$.cookie('vipindexHref',null);
		$.cookie('vipindexHref',this.id);
	});
	
	$("#vip_privilege").click(function(){
		$.cookie('vipindexHref',null);
		$.cookie('vipindexHref','vipprivilege');
	});
	
	$("#game_privilege").click(function(){
		$.cookie('vipindexHref',null);
		$.cookie('vipindexHref','gameprivilege');
	});
	
	  $('#mod_btn_gray_s').mousemove(function(){
		  $("#map").slideDown();//可以设置切换时间 
		  });
		  $('#mod_btn_gray_s').mouseleave(function(){
		  $("#map").slideUp("fast");
		  });
	
	$("#vipindex").removeClass("now");
	if($.cookie('vipindexHref') == " " || typeof($.cookie('vipindexHref')) == "undefined"){
		$("#vipindex").addClass("now");
	}else{
		$("#"+$.cookie('vipindexHref')).addClass("now");
	}
	
	
});
	
	
	function addfavorite()
	{
	   if (document.all)
	   {
	      window.external.addFavorite('http://www.wowpower.cn','蛙宝网');
	   }
	   else if (window.sidebar)
	   {
	      window.sidebar.addPanel('蛙宝网', 'http://www.wowpower.cn', "");
	   }
	} 

		  	
				
	
</script>
</head>
<body>
<div class="wrapper"> 
 <!--header start-->
  <div class="vip_header">
    <div class="vip_mod_mini_nav">
      <ul class="mini_nav_list clearfix fr">
      	<sec:authentication property="principal" var="currentUsr" scope="page"/>
			<c:choose>
			   <c:when test="${currentUsr eq '' or currentUsr eq null or currentUsr eq 'anonymousUser'}">
			    <li  class="mininav_welcome"> 您还未登录，请 <a title="登录" href="${ctx}/login.html">登录</a> </li>
			   </c:when>
			   <c:otherwise>
			   		<li class="mininav_welcome"> 欢迎回来 ！<sec:authentication property="principal.nickName" /> </li>
			     	<li> <img id="user_icon" alt="用户头像" src="${ctx }/${PHOTO_PATH == null ? 'images/people.png' : PHOTO_PATH}" width="25" height="25" border="0" style="width:25px;height: 25px;margin-top: 5px" /> </li>
       			    <li><yma:LevelConvertIcoTag level='${LEVEL }' vipTag="${VIPTAG}"/> </li>
       			    <c:choose>
       			       <c:when test="${VIPTAG eq 0 }">
       			       		     <li class="mod_btn_common_sub"> <a stag="xufei" href="${ctx }/user/4/manager">开通会员</a> </li>
       			       </c:when>
       			      
       			    </c:choose> 
			   </c:otherwise>
			</c:choose>
			<li class="split_line" style="margin-left:10px;">|</li>
		         <sec:authorize access="isAuthenticated()">
							<li class="fr nav_last"><a
								href="${ctx }/j_spring_security_logout">退&nbsp;出</a></li>
				 </sec:authorize>	
       
        <li class="service_area"> <a target="_blank" href="###"  onclick="addfavorite()">收藏</a> </li>
        <li class="split_line ">|</li>
         
        <li class="map_list" id="mod_btn_gray_s"> <a  target="_blank" href="#">网站地图<i class="map_icon"></i> </a>
          <dl class="map" id="map" style="display: none">
            <div class="map_left fl">
              <dt><a href="${ctx}/user/account/manager.html?cate=task"><img src="images/vip/user_icon.png"/></a></dt>
              <dd><a href="${ctx}/user/account/manager.html?cate=task">用户中心</a></dd>
              <dt><a href="${ctx}/game.html"><img src="images/vip/game_icon.png"/></a></dt>
              <dd><a href="${ctx}/game.html">游戏中心</a></dd>
              <dt><a href="${ctx}/mall.html"><img src="images/vip/jf_icon.png"/></a></dt>
              <dd><a href="${ctx}/mall.html">积分商城</a></dd>
              <dt><a href="${ctx}/activity.html"><img src="images/vip/hd_icon.png"/></a></dt>
              <dd><a href="${ctx}/activity.html">活动频道</a></dd>
              <dt><a href="${ctx}/user/account/recharge.html"><img src="images/vip/cz_icon.png"/></a></dt>
              <dd><a href="${ctx}/user/account/recharge.html">充值</a></dd>
              <dt><a href="${ctx}/user/account/withdrawal.html"><img src="images/vip/tx_icon.png"/></a></dt>
              <dd><a href="${ctx}/user/account/withdrawal.html">提现</a></dd>
            </div>
            <div class="map_left fr">
              <dt><a href="${ctx}/help.html"><img src="images/vip/help_icon.png"/></a></dt>
              <dd><a href="${ctx}/help.html">帮助中心</a></dd>
              <dt><a href="${ctx}/signInDetail.html"><img src="images/vip/sign_icon.png"/></a></dt>
              <dd><a href="${ctx}/signInDetail.html">签到中心</a></dd>
              <dt><a href="${ctx}/feedBackIndex.html"><img src="images/vip/yj_icon.png"/></a></dt>
              <dd><a href="${ctx}/feedBackIndex.html">意见反馈</a></dd>
              <dt><a href="${ctx}/user/account/getMyInviteUser.html"><img src="images/vip/yq_icon.png"/></a></dt>
              <dd><a href="${ctx}/user/account/getMyInviteUser.html">我的邀请</a></dd>
              <dt><a href="${ctx}/order/manager/orderlist.html"><img src="images/vip/dd_icon.png"/></a></dt>
              <dd><a href="${ctx}/order/manager/orderlist.html">我的订单</a></dd>
              <dt><a href="${ctx}/vip_index.html"><img src="images/vip/vip_icon.png"/></a></dt>
              <dd><a href="${ctx}/vip_index.html">vip会员</a></dd>
              
            </div>
          </dl>
        </li>

      </ul>
    </div>
    <div class="vip_mod_header_wrapper">
        <div class="vip_mod_header_inner">
    
      <div class="vip_logo_w">
        <div class="vip_logo fl" style="cursor: pointer;" onclick="javascript:window.location.href='${ctx}/index'"> </div><div class="banner fr"><img src="${ctx }/images/vip/banner.jpg"/></div>
      </div>
      
      <div class="nav">
        <ul id="nav">
          <li class="mainlevel " id="mainlevel_01"><a name="vipmenu" id="vipindex" href="${ctx }/vip_index.html" class="now" >首页</a> </li>
          <li class="mainlevel" id="mainlevel_02"><a name="vipmenu" id="vipprivilege" href="${ctx }/vip_privilege.html">VIP特权</a>
            <ul id="sub_03" style="width:111px; height:33px; background:#fff;position:absolute; z-index:9999;">
              <li><a href="vip_privilege.html" id="vip_privilege">VIP特权首页</a></li>
            </ul>
          </li>
          <li class="mainlevel" id="mainlevel_03" style="margin-right:310px;"><a id="gameprivilege" name="vipmenu" href="${ctx }/game_privilege.html">游戏特权</a>
            <ul id="sub_03" style="width:111px; height:33px; background:#fff; position:absolute; z-index:9999;">
              <li><a href="${ctx }/game_privilege.html" id="game_privilege">游戏特权首页</a></li>
            </ul>
          </li>
          <li class="mainlevel" id="mainlevel_04"><a href="${ctx }/giveVip.html">赠送好友会员</a> </li>
          <li class="mainlevel" id="mainlevel_04"><a href="${ctx }/grow_system.html">成长体系</a>
            <ul id="sub_03" style="width:111px; height:73px; background:#fff; position:absolute; z-index:9999;">
              <li><a href="${ctx }/grow_system.html">成长体系介绍</a></li>
              <li><a href="${ctx }/all_pri_vip.html">全部会员特权</a></li>
            </ul>
          </li>
          <li class="mainlevel" id="mainlevel_05"><a href="${ctx }/user/4/manager">支付密码</a> </li>
          <li class="mainlevel" id="mainlevel_06"><a href="${ctx }/user/4/manager">安全邮箱</a> </li>
          <li class="mainlevel" id="mainlevel_06"><a href="${ctx }/user/4/manager">绑定手机</a> </li>
        </ul>
      </div>
    </div>
        </div>
    
  </div>
  <!--header end--> 