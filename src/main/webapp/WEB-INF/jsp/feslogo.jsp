<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/account.css" type="text/css" rel="stylesheet">
   <!--选项卡-->
<script language="javascript"> 
<!--
function setTab2(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}
//-->
</script>         
<div id="wrapper">
  <div class="title-sub tit_pad title-fes">
    <h2 style="border-bottom:2px solid #349cd8"><img src="${ctx }/images/common/fes_title.png"/></h2>
    <b class="line"></b> </div>
  <div class="model-box pad-fes"> 
    <!--选项卡开始-->
    <div class="tab_box">
      <div class="lyz_tab_left">
        <div class="pro_con111">
          <ul >
            <li class="hover" id="one1" onclick="setTab2(&#39;one&#39;,1,2)">2014年</li>
            <li id="one2" onclick="setTab2(&#39;one&#39;,2,2)">2013年</li>
          </ul>
        </div>
      </div>
      <div class="lyz_tab_right">
        <div class="hover" id="con_one_1" style="display:block; border:none">
          <div class="model-box">
            <ul class="clearfix fes-cnt">
              <li> <img width="284" height="157" src="${ctx }/images/help/gq_logo.png"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/ws_logo.png"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/cy_logo.png"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/sd_logo.jpg"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/yd_logo.jpg"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/xn_logo.jpg"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/wsj_logo.jpg"> </li>
            </ul>
            </div>
            </div>
            <div class="hover" id="con_one_2" style="display:none;border:none">
              <div class="model-box">
              <ul class="clearfix fes-cnt">
              <li> <img width="284" height="157" src="${ctx }/images/help/yx_logo.jpg"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/sb_logo.jpg"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/zs_logo.jpg"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/yr_logo.jpg"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/qm_logo.jpg"> </li>
              <li> <img width="284" height="157" src="${ctx }/images/help/qixi.jpg"> </li>
            </ul>
               </div>
            </div>
            <div class="clear"></div>
            <!--选项卡结束--> 
          </div>
        </div>
      </div>
    </div>