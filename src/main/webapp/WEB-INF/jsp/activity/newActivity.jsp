<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/static.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/page.css" rel="stylesheet">
<script type="text/javascript">
gamePagination(1);
function gamePagination(page) {
	
	var param = {
		page : page
	};
	
	$.get("showActivity", param, function(data) {
		$(".hd-box").html(data);
	});
}
</script>
<div id="wrapper">
  <!--sidebar-->
  <div id="sidesub">
    <div class="static_nav">
      <div class="reg_btn"> <a href="${ctx }/registSingle.html" target="_blank">马上注册</a> </div>
      <ul>
        <li class="current"><a href="${ctx }/help.html" target="_blank" style="font-size:14px; font-weight:bold; margin-bottom:15px; padding-bottom:5px;"><i></i>帮助中心</a>
          <ul class="static-list">
            <li><a href="${ctx }/help.html" target="_blank"><i class="t_icons ico"></i>新手帮助</a></li>
            <li><a href="${ctx }/process.html" target="_blank"><i class="ico"></i>蛙宝流程</a></li>
            <li><a href="${ctx }/help_partner.html" target="_blank"><i class="ico"></i>合作伙伴</a></li>
            <li><a href="${ctx }/help_contact.html" target="_blank"><i class="ico"></i>联系我们</a></li>
          </ul>
        </li>
      </ul>
      <div class="clear"></div>
    </div>
    <div class="m_lt_code">
      <div class="hot_phone">
        <p class="txt3">客服热线</p>
        <p class="txt4">400 891 6233</p>
        <p class="txt3">官方QQ群</p>
        <p class="txt4">305322529</p>
      </div>
      <div class="mobile_client">
        <p class="ewm_down">扫一扫<br />关注蛙宝网微信</p>
        <p class="ewm_c"><img width="100" height="80" src="${ctx}/images/national/weixin.png"><a style="clear:both; display:inline-block;">蛙宝网微信</a></p>
      
      </div>
    </div>
  </div>
  <!--/sidebar--> 
  <!--main-->
  <div id="mainsub">
    <div class="hd-box">

    </div>
  </div>
  <!--/main--> 
</div>