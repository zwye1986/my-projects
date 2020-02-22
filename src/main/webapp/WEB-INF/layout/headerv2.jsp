<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description"
	content="蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以轻松的拿到返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。" />
<meta name="keywords"
	content="蛙宝网,蛙宝,赚钱,注册返现,wowpower,挖宝,网赚,注册提现,	理财,返利,积分活动,打码,网上挣零花钱,赚钱,点击游戏,网赚项目,注册网赚" />
<title>蛙宝网-轻轻松松拿返利</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${pageTitle }</title>
<link rel="icon" href="${ctx }/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctx }/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>
<link href="${ctx}/css/account-settings.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="${ctx}/js/regist.js"></script>
<link href="${ctx}/css/account-common.css" type="text/css"
	rel="stylesheet">
<script src="${ctx}/js/global-1.1.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
function addFavorite2() {
    var url = window.location;
    var title = document.title;
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("360se") > -1) {
        alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
    }
    else if (ua.indexOf("msie 8") > -1) {
        window.external.AddToFavoritesBar(url, title); //IE8
    }
    else if (document.all) {
  try{
   window.external.addFavorite(url, title);
  }catch(e){
   alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
  }
    }
    else if (window.sidebar) {
        window.sidebar.addPanel(title, url, "");
    }
    else {
  alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
    }
}
	</script>
</head>
<body>
	<div id="header">
		<div class="inner">
			<ul class="service">
				<li class="hotline"><i class="icons"></i> <b>客服热线</b> <em>400
						891 6233</em></li>
				<li class="concerns"><b>关注我们</b> <a
					href="https://twitter.com/venadasoftware" target="_blank"
					class="icons twitter">twitter</a> <a
					href="https://www.facebook.com/vndsoftware" target="_blank"
					class="icons facebook">facebook</a> <a
					href="http://t.qq.com/wowpower" target="_blank"
					class="icons tengxun">腾讯官方微博</a> <a
					href="http://weibo.com/u/3848323436" target="_blank"
					class="icons sina" rel="nofollow">新浪官方微博</a>
			</ul>
			<div class="loginbar">
				<div class="for">
					<sec:authentication property="principal" var="currentUsr"
						scope="page" />
					<c:choose>
						<c:when
							test="${currentUsr eq '' or currentUsr eq null or currentUsr eq 'anonymousUser'}">

						</c:when>
						<c:otherwise>
							<a href="#" style="text-decoration: none; color: #fff;"> 欢迎你！<em
								style="font-style: normal; color: #fa9d0a;">${currentUsr.nickName}</em>
							</a>
							<a href="${ctx}/j_spring_security_logout" style="color: #fff;">［退出］</a>
						</c:otherwise>

					</c:choose>
					<i class="icons scbz"></i> <a target="_blank"
						style="color: #fff; margin-right: 20px; margin-left: 40px;"
						href="##" onclick="addFavorite2();">收藏本站 </a> <a id="userReg" href="${ctx}/regist.html"
						target="_blank">注册 </a> <a style="color: #349cd8;"
						href="${ctx}/login.html" target="_blank">登录</a>
				</div>
			</div>
			<div class="logo">
				<h2>
					<a href="${ctx}/index.html"><img width="232" height="71"
						src="${ctx }/img/common/logo.png" alt="蛙宝网"></a>
				</h2>
			</div>
		</div>
		<div class="b"></div>
	</div>