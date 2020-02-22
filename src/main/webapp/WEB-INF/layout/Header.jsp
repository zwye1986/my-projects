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
<meta name="description" content="蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以轻松的拿到返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。"/>
<meta name="keywords" content="蛙宝网,蛙宝,赚钱,注册返现,wowpower,挖宝,网赚,注册提现,	理财,返利,积分活动,打码,网上挣零花钱,赚钱,点击游戏,网赚项目,注册网赚" />
<title>蛙宝网-轻轻松松拿返利</title>
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<link href="${ctx }/css/top.css" rel="stylesheet"  type="text/css"/>
<link href="${ctx }/css/bottom.css" rel="stylesheet" />
<link href="${ctx }/css/level.css" rel="stylesheet"  type="text/css"/>
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<link href="${ctx }/css/page2.css" rel="stylesheet"  type="text/css"/>
<link rel="icon" href="${ctx }/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx }/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript" src="${ctx}/js/regist.js"></script>
<script type="text/javascript" src="${ctx}/js/tj.js"></script>
<!-- WPA Button Begin -->
<script charset="utf-8" src="http://wpa.b.qq.com/cgi/wpa.php"></script>
<!-- WPA Button End -->
<script type="text/javascript">
if (window != top){
	top.location.href = location.href;
}
	
	var imgPath_session = '${ctx}' + '/images/alerts';

	$(document).ready(function() {
		$(".btn-slide").click(function() {
			$("#panel").slideToggle("slow");
			$(this).toggleClass("active");
			return false;
		});

		$("#userReg").click(function() {
		//	$("#panel").slideToggle("slow");
			//$(this).toggleClass("active");
			window.open("${ctx}/registSingle.html","_blank");
			return false;
		});

		//显示用户详细信息
		$('#username_h').hover(function() {
			
			$.ajax({
				   type: "POST",
				   async: false,
				   cache: false,
				   dataType: "json",
				   url: "${ctx}/user/reCount",
				   success: function(data){
					   $("#walletAmount").empty();
					   $("#walletAmount").html("纳币："+data.WALLETAMOUNT+"纳币");
				   }
				});
			$("#userDetailDiv").slideDown();
			$("#pointerImg").attr("src", "${ctx }/images/white-arrow01.png");
		}, function() {
			$("#userDetailDiv").slideUp();
			$("#pointerImg").attr("src", "${ctx }/images/white-arrow02.png");
		});
		
		$.cookie.defaults = { path: '/'};
		
		$("#headMenu").find("li").click(function(){
			$.cookie('indexHref',null);
			$.cookie('indexHref',this.id);
		});
		
		$("#_index").removeClass("nav_now");
		
		$("#"+$.cookie('indexHref')).addClass("nav_now");
		

		
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
<script type="text/javascript">
 $(document).ready(function(){  
	 var str = $(".floatR").width();
	    if(str>1){
	    	$("#fixbg").css("width","0px");
	    }
  $('#floatL,#kfclose').click(function(){
	  
   $("#fixbg").css("width",$(document).width());
	    
	  
    $(".floatR").animate({
   width: 'toggle', opacity: 'toggle'
      }, { duration: "slow" }); 
    
    var str = $(".floatR").width();
    if(str>1){
    	$("#fixbg").css("width","0px");
    }
  
  })
  
  $(".close").click(function(){
	 
    $(".floatR").animate({
   width: 'toggle', opacity: 'toggle'
      }, { duration: "slow" }); 
    var str = $(".floatR").width();
    if(str>1){
    	$("#fixbg").css("width","0px");
    }
  })
 }); 
</script>
</head>
<body>
<!--bottom start-->
<div id="fixbg">
  <div id="floatL" class="floatL" style="background:url(${ctx}/images/bottom/uka_icon1.png) no-repeat; cursor:pointer;" > <a  title="在线客服" class="btnCtn" id="aFloatTools_Hide"></a> </div>
  <div class="floatR" style="display:none;background-color:#000;opacity:0.6;filter:alpha(opacity=60);">
    <div class="close" id="fclose"> <a href="JavaScript:;" title="关闭在线客服" class="btnCtn" id="aFloatTools_Hide"> <img  src="${ctx }/images/bottom/fclose.png"/></a> </div>
    <div class="qiatan"> <span> <a id="BizQQWPA" style="cursor: pointer;">在线客服</a>|<a target="_blank" href="${ctx }/feedBackIndex.html" >意见反馈</a> |<a style="cursor: pointer;" onclick="javascript:window.open('http://wp.qq.com/wpa/qunwpa?idkey=fd6a918c0795347a0f125e8699fe31303d1a37c93d69f436b73aadfb0f9c76fe','_blank')">官方QQ群:305322529</a></span> <samp class="phone" style="background:url(${ctx}/images/bottom/phone3.png) no-repeat; z-index:9999;"></samp> <samp class="fenxiang"> 
       <script type="text/javascript">
      BizQQWPA.addCustom({aty: '0', a: '0', nameAccount: 800049764, selector: 'BizQQWPA'});
      </script> 
     <!-- Baidu Button BEGIN -->
<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare" data="{'url':http://www.wowpower.cn/index.html,'text':蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以拿到轻松的返利}">
<a class="bds_qzone"></a>
<a class="bds_tsina"></a>
<a class="bds_tqq"></a>
<a class="bds_renren"></a>

<span class="bds_more"></span>
</div>
<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=0" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script>
<!-- Baidu Button END -->
      </samp> </div>
  </div>
</div>
</div>
<!--bottom end-->
<a id="returnTop" href="javascript:;" style="bottom: -200px;">回到顶部</a> 

	<script type="text/javascript" src="${ctx }/js/wowpower.min.js"></script>


	<a style="display: none" id="fancyBox"></a>
	<div class="wrap">
		<div class="headerbox">
			<!-- <div class="nav_top_wrapper">
			<div class="nav_top"></div>
			</div> -->
			 <div id="navbox_wrapper">
			<div class="navbox" style="position: relative;">
				<div id="nav">
					<ul class="nav clearfix" id="headMenu">
						<p class="wblogo fl"></p>
						<li class="nav_first nav_now  fl" id="_index"><a href="${ctx }/index.html">首&nbsp;页</a></li>
						<li class="fl" id="_game"><a href="${ctx }/game.html" >游戏中心</a></li>

						<li class="fl" id="_user"><a href="${ctx }/usertaskDetail.html">用户中心</a></li>

						<li class="fl" id="_mall"><a href="${ctx }/mall.html">积分商城</a></li>
						<li class="fl" id="_activity"><a href="${ctx }/activity.html">活动频道</a></li>
						<li class="fl" id="_zhouchou"><a href="${ctx }/vip_index.html">VIP会员</a></li>
						<li class="fl nav_last" id="_help"><a href="${ctx }/help.html">帮助中心</a></li>
					</ul>
					<sec:authorize access="isAuthenticated()">
						<div class="username_h" style="display: block; cursor: pointer;"
							id="username_h">

							<p
								style="height:46px; line-height:46px; width:200px; overflow:hidden; letter-spacing:0.1em; margin-top:-5px;">
								<img style="float: left; margin-top: 19px; cursor: pointer;margin-right: 5px;"
									src="${ctx }/images/white-arrow02.png" alt="欢迎回来" id="pointerImg"/> <em
									style="color: #fff; float: left;font-size: 13px">欢迎回来！</em> <em
									style="color: #fff; font-size: 13px; float: left; display: inline-block; 
									max-width: 70px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"><sec:authentication
										property="principal.nickName" /></em><img id="user_icon" alt="用户头像"
									src="${ctx }/${PHOTO_PATH == null ? 'images/people.png' : PHOTO_PATH}" width="35" height="35" border="0" style="width=35px;height: 35px;margin-top: 5px" />
							</p>


							<ul class="username_list" style="display: none;" id="userDetailDiv">
								<li><label style="float:left;">等级：</label><yma:LevelConvertIcoTag level='${LEVEL }' vipTag="${VIPTAG}"/></li>
								<li id="walletAmount">纳币：${WALLETAMOUNT.amount == null ? 0 :
									WALLETAMOUNT.amount }纳币</li>
								<li>收入：${DEALDETAILAMOUNT == null ? 0 : DEALDETAILAMOUNT
									}纳币</li>
							</ul>
						</div>
					</sec:authorize>

					<ul class="navL clearfix fr">
					 <li class="fr black"><a href="#" onclick="addfavorite()">加入收藏</a></li>
						<sec:authorize access="isAuthenticated()">
							<li class="fr nav_last"><a
								href="${ctx }/j_spring_security_logout">退&nbsp;出</a></li>
						</sec:authorize>
						<sec:authentication property="principal" var="currentUsr" scope="page"/>
						 <c:if test="${currentUsr eq '' or currentUsr eq null or currentUsr eq 'anonymousUser'}">

						  <li class="fr black">
						     <a id="userReg" href="###">注册 </a>
						     
							<%-- <p style="margin-top: -55px;"><img src="${ctx }/images/white-arrow.png" alt="箭头" /></p></li> --%>

							
							<li class="fr black"><a href="${ctx }/login.html">登&nbsp;录</a>
							</li>
						 </c:if>
							
						</ul> 
					</div>
			</div>
		</div>
	</div>
	</div>