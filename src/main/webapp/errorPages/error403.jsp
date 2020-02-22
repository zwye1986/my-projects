<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>蛙宝网</title>
<link rel="icon" href="${ctx }/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="${ctx }/favicon.ico" type="image/x-icon">
<style type="text/css">

* {
	margin: 0;
	padding: 0;
}
ol, ul {
	list-style: none;
}
.fl {
	float: left;
}
.fr {
	float: right;
}
.break {
	word-wrap: break-word;
	width: inherit;
}
.linkhidden {
	text-indent: -9999em;
	overflow: hidden;
}
.hidden {
	display: none;
}
a {
	text-decoration: none;
}
/*layout*/
.wrap {
	width: 100%;
}
.wrap a {
	text-decoration: none;
}
.headerbox {
	width: 100%;
}
/*icon*/
.navbox, .navbox .nav a, .navbox .nav span {
	background-image: url(${ctx }/images/icon.png);
	_background-image: url(${ctx }/images/icon.png);
	background-repeat: no-repeat;
}
/*linkhidden*/
.header .logo, .header .header_ad, .banner_prev, .banner_next, .serveropen .carousel-control {
	text-indent: -9999em;
	overflow: hidden;
}

/*navbox*/
.nav_top {
	width: 100%;
	height: 3px;
	background: #000;
}
.navbox, .navbox .nav a, .navbox .nav span {
	background-image: url(${ctx }/images/icon.png);
	_background-image: url(${ctx }/images/icon.png);
	background-repeat: no-repeat;
}
.navbox {
	width: 100%;
	height: 55px;
	background-position: 0 -307px;
	background-repeat: repeat-x;
	position: relative;
}
#nav {
	width: 1257px;
	margin: 0 auto;
	position: relative;
}
.navbox .nav {
	margin: 0 auto;
	width: 700px;
	height: 55px;
	float: left;
}
.wblogo {
	width: 210px;
	height: 55px;
	background: url(${ctx }/images/wblogo.png) no-repeat;
}
.navbox .nav a {
	display: block;
	padding: 0 1px 0 0;
	width: 99px;
	height: 55px;
	line-height: 55px;
	text-align: center;
	background-position: 0 0;
	font: 700 16px/38px microsoft;
	color: #FFF;
	text-decoration: none;
	line-height: 55px;
}
.navL {
	width: 200px;
}
.navL li {
	display: inline-block;
	width: 95px;
	height: 55px;
	background: url(${ctx }/images/nav_L.png) no-repeat;
	line-height: 55px;
	text-align: center;
}
.navL a {
	color: #fff;
}
.navbox .nav a:hover, .navbox .nav_now a {
	background: #913b29;
}
.navbox .nav_first a {
	width: 98px;
	background-position: -1px 0;
}
.navbox .nav_last a {
	width: 96px;
}
.navbox .nav_new a, .navbox .nav_hot a {
	padding: 0 7px 0 0;
	width: 89px;
	position: relative;
}
.navbox .nav_new span, .navbox .nav_hot span {
	position: relative;
	top: 12px;
	right: 18px;
	display: block;
	width: 13px;
	height: 11px;
	overflow: hidden;
}
.navbox .nav_new span {
	background-position: -76px -242px;
}
.navbox .nav_hot span {
	background-position: -76px -253px;
}
.username {
	position: absolute;
	left: 840px;
	top: 13px;
	cursor: pointer;
	width: 200px;
	height: 22px;
}
.username #user_icon {
	position: absolute;
	top: 0px;
}
.username_h {
	width: 240px;
	position: absolute;
	left: 800px;
	top: 4px;
	padding-left: 7px;
	padding-top: 5px;
	z-index: 5;
}
.username_h p {
}
.username_list {
	background: #EF634A;
	padding-left: 25px;
	position: absolute;
	margin-top: -2px;
	display: block;
	width: 230px;
	padding-bottom: 15px;
	margin-top: -5px;
}
.username_list li {
	line-height: 28px;
	height: 28px;
	color: #fff;
	font-size: 13px;
}
.user_h_center a {
	padding-right: 10px;
	color: #00b7ee;
}

/*footerwrapper*/
.footwrapper {
	width: 100%;
	background-color: #e1e1e1;
	border-top: 3px solid #f16244;
	display: inline-block;
}
.footer {
	width: 1257px;
	height: 140px;
	margin: 0 auto;
}
.footerL {
	width: 173px;
	height: 140px;
}
.footerL img {
	width: 173px;
	height: 65px;
}
.footerC {
	width: 860px;
	height: 140px;
}
.footerC_T {
	text-align: center;
	line-height: 36px;
}
.footerC_T a {
	color: #979797;
	font-size: 16px;
}
.footerC_T a:hover {
	color: #f16244;
}
.footerC_B p {
	width: 860px;
	margin: 0 auto;
}
.footerC_B span {
	width: 860px;
	height: 57px;
	margin: 0 auto;
}
.footerC_B span a {
	margin: 0 7px;
}
.footerC_B {
	text-align: center;
	line-height: 24px;
	color: #979797;
}
.footerC_B a {
	color: #979797;
	font-size: 16px;
}
.footerC_B a:hover {
	color: #f16244;
}
.footerR {
	width: 220px;
	height: 140px;
}
.footerR P {
	height: 80px;
	margin-top: 20px;
	cursor: pointer;
}
#content {
	width:864px;
	height:480px;
	margin: 0 auto;
	z-index: 3;
	margin-top: 35px;
	background:url(${ctx }/images/403.png) no-repeat;
}
#content a{ display:inline-block; width:150px; height:45px; background:url(${ctx }/images/error_back.png) no-repeat; margin-top:410px; margin-left:690px; cursor:pointer;}
#content a:hover{ opacity:0.8;}
</style>
</head>
<body>
<div class="wrap"> 
  <div class="nav_top"></div>
  <div  class="navbox" style="position:relative;">
    <div id="nav">
      <ul class="nav clearfix">
        <p class="wblogo fl"></p>
        <li class="nav_first  fl"><a href="${ctx}">首&nbsp;页</a></li>
        <li class="fl"><a href="${ctx }/game">游戏中心</a></li>
        <li class="fl"><a href="${ctx }/user/account/manager?cate=task">用户中心</a></li>
        <li class="fl nav_last"><a href="${ctx }/help">帮助中心</a></li>
      </ul>
    </div>
  </div>
</div>
</div>
<!--content-->
<div id="cntwrapper" > 
  <!--content-->
  <div id="content"><a href="${ctx }/"><img src="${ctx }/images/error_back.png"/></a></div>
</div>
<!--footer-->
<div class="footwrapper" style="position:absolute;bottom:0px;">
<div class="footer">
  <div class="footerL fl"><img src="${ctx }/images/footer_logo.png"/></div>
  <div class="footerC fl">
    <div class="footerC_T"><a href="${ctx }/help">关于我们   ｜</a> <a href="${ctx }/help">新手帮助  ｜ </a> <a href="${ctx }/help">友情链接  ｜ </a><a href="${ctx }/help">合作伙伴 ｜ </a> <a href="${ctx }/help">联系我们</a></div>
    <div class="footerC_B"> <a href="http://www.miitbeian.gov.cn" target="_blank" rel="nofollow">苏ICP备13017605 号 </a> Copyright ©2013 蛙宝网wowpower.cn版权所有
      <p>增值电信业务经营许可证 A2.B1.B2-201306018 网络文化经营许可证京网文[2013]0168-061号</p>
      <span><a><img src="${ctx }/images/foot01.png"/></a><a><img src="${ctx }/images/foot02.png"/></a><a><img src="${ctx }/images/foot03.png"/></a><a><img src="${ctx }/images/foot04.png"/></a></span> </div>
  </div>
  <div class="footerR fr">
    <p><a><img src="${ctx }/images/icon01.png"/></a><a><img src="${ctx }/images/icon02.png"/></a><a><img src="${ctx }/images/icon03.png"/></a><a><img src="${ctx }/images/icon04.png"/></a></p>
  </div>
</div>
</body>
</html>