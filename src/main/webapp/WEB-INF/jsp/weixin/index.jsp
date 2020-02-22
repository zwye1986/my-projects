<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/> 
<link href='${ctx}/css/weixinstyle.css' rel='stylesheet'/>
<style>

/* Swipe 2 required styles */

.swipe {
  overflow: hidden;
  visibility: hidden;
  position: relative;
}
.swipe-wrap {
  overflow: hidden;
  position: relative;
}
.swipe-wrap > div {
  float:left;
  width:100%;
  position: relative;
}
img{width:100%;text-align:center; vertical-align:middle; }
/* END required styles */

</style>

</head>
<body>
<div id='mySwipe' style='max-width:500px;margin:0 auto' class='swipe'>
<div class='swipe-wrap' style="width:100%; height:100% ;margin-top:2%">
    <div><img src="${ctx}/images/weixin/b1.png" /></div>
    <div><img src="${ctx}/images/weixin/b2.png" /></div>
    <div><img src="${ctx}/images/weixin/b3.png"  /></div>
    <div><img src="${ctx}/images/weixin/b4.png" /></div>
    <div><img src="${ctx}/images/weixin/b5.png" /></div>
    <div><img src="${ctx}/images/weixin/b6.png" /></div>
    
  </div>
</div>


<script src="${ctx}/js/jquery/jquery-1.9.1.min.js"></script> 
<script src='${ctx}/js/weixin/swipe.js'></script>
<script>

// pure JS
var elem = document.getElementById('mySwipe');
window.mySwipe = Swipe(elem, {
   startSlide: 0,
   /* auto: 3000, */
   continuous: false,
   disableScroll: true,
   stopPropagation: true,
   callback: function(index, element) {},
   transitionEnd: function(index, element) {}
});

// with jQuery
// window.mySwipe = $('#mySwipe').Swipe().data('Swipe');

</script>