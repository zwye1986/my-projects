<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/feslogo.css" rel="stylesheet" />
<script language="javascript"> 
<!--
function setTab(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}
//-->
</script>


<!--content-->
<div class="content">
  <div class="tab_box">
    <div class="lyz_tab_left">
      <div class="pro_con111 content_t">
        <ul>
          <li  id="one1" onclick="setTab(&#39;one&#39;,1,2)">2013</li>
          <li id="one2" class="hover" onclick="setTab(&#39;one&#39;,2,2)">2014</li>
        </ul>
      </div>
    </div>
    <div class="lyz_tab_right">
      <div class="hover content_c" id="con_one_1" style="display: none">
        <ul class="clearfix">
          <li><img src="${ctx }/images/help/gq_logo.png"/></li>
          <li><img src="${ctx }/images/help/ws_logo.png"/></li>
          <li><img src="${ctx }/images/help/cy_logo.png"/></li>
          <li><img src="${ctx }/images/help/sd_logo.jpg"/></li>
          <li><img src="${ctx }/images/help/yd_logo.jpg"/></li>
          <li><img src="${ctx }/images/help/xn_logo.jpg"/></li>
        </ul>
      </div>
      <div class="hover content_c" id="con_one_2" >
        <ul>
          <li><img src="${ctx }/images/help/yx_logo.jpg"/></li>
          <li><img src="${ctx }/images/help/sb_logo.jpg"/></li>
          <li><img src="${ctx }/images/help/zs_logo.jpg"/></li>
          <li><img src="${ctx }/images/help/yr_logo.jpg"/></li>
          <li><img src="${ctx }/images/help/qm_logo.jpg"/></li>
          <li><img src="${ctx }/images/help/qixi.jpg"/></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="content_b"></div>
</div>