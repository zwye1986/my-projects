<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/hd.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/index.js"></script>
<div class="content_top_w">
  <div class="content_Top"><img src="${ctx }/images/hd/hd_tbg.png" alt="蛙宝精彩活动"/></div>
</div>
<!--content-->
<div id="content">
  <div class="content_c">
    <div class="content_l fl">
      <div class="GLogin" <c:choose><c:when test="${not empty user }">style="display: none;"</c:when><c:otherwise>style="display: block;"</c:otherwise></c:choose>  >
        <div class="GLoginCenter">
          
          <div class="GLoginContent">
          <div class="GLoginTitle"></div>
          <div class="ts_message" id="ts_message" style="display: none;">
							<em class="ts_img"> <img
								src="${ctx }/images/user/com_bg.png" alt="验证码错误">
							</em> <a class="ts_text">验证码不正确</a>
						</div>
            
         <form action="${ctx}/userlogin" id="loginForm" name="loginForm"
							method="post">
							<p id="pwdp" style="margin-top: 0px">
								<input type="text" name="mobileNumber" class="dl" id="uin"
									value="请输入手机号码">
							</p>
							<p id="pwdp" style="margin-top: 5px">
								<input id="pwd" class="dl" type="text" name="pwd"
									value="请输入密码(区分大小写)"> <input type="password"
									name="password" class="dl" id="password" style="display: none"
									autocomplete="off" />
							</p>
							<p>
								<input type="text" class="yzm" id="yzm" name="checkCode"
									value="验证码">
							</p>
							<a style="display: inline-block; margin-top: 5px;"><img style="height: 26px; width:80px;"
								id="logincheckcodeimag"
								src="${pageContext.request.contextPath}/getCheckCode?codeName=loginCode"
								onclick="changecode('${pageContext.request.contextPath}/getCheckCode?codeName=loginCode','logincheckcodeimag')" alt="校验验证码"></a>
							<p id="btn" style=" display:inline-block; height:36px; padding-top:20px;">
								<input type="button" class="tj" value=""
									onclick="checkValidateCode('${ctx}','logincheckcodeimag');" />
							</p>
							 <div class="wjmm">
								<p id="wjmm">
									<a
										href="${pageContext.request.contextPath}/inputMobileNumber.html"
										target="_blank"> 忘记密码？</a>
								</p>
							</div> 
						</form>
          </div>
        </div>
        
      </div>
        <!--登陆后信息显示--><c:if test="${not empty user }">
       <div class="p_info">
       <div class="p_infoTitle"></div>
       <div class="p_pic"><img width="64px" height="64px" src="${ctx }/${PHOTO_PATH == null ? 'images/people.png' : PHOTO_PATH}" alt="用户头像" /></div>
       <ul class="user_list" >
              <li><sec:authentication property="principal.nickName" /></li>
               <li>纳币：${WALLETAMOUNT.amount == null ? 0 : WALLETAMOUNT.amount }纳币</li>
              <li>收入：${DEALDETAILAMOUNT == null ? 0 : DEALDETAILAMOUNT }纳币</li>
			  <li><label style="float:left;">等级：</label><yma:LevelConvertIcoTag level='${LEVEL }' vipTag="${VIPTAG}"/></li>
             
            </ul> 
       <!--  <ul class="username_list" >
			<li><label style="float:left;">等级：</label><yma:LevelConvertIcoTag level='${LEVEL }' vipTag="${VIPTAG}"/></li>
			<li id="walletAmount">纳币：${WALLETAMOUNT.amount == null ? 0 : WALLETAMOUNT.amount }纳币</li>
			<li>收入：${DEALDETAILAMOUNT == null ? 0 : DEALDETAILAMOUNT }纳币</li>
		</ul>     -->
       </div></c:if>
      <!--快捷通道-->
      <div class="FTrack">
        
        <div class="FTrackTitle"></div>
        <div class="FTrackContent">
          <div class="FTrackContentT"><a href="${ctx }/user/account/recharge"><img src="${ctx }/images/FTrack01.png" alt="快捷通道1"/></a></div>
          <div class="FTrackContentB"><a href="${ctx }/user/account/withdrawal"><img src="${ctx }/images/FTrack02.png" alt="快捷通道2"/></a></div>
        </div>
      
      </div>
    </div>
<div class="content_r fr">
      <div class="content_rt"></div>
      <c:forEach items="${list }" var="item" varStatus="s">
        <div class="content_rc">
        <div class="content_rcl">
         <p class="list">${s.index+1 }</p>
         <p class="game_c"><img src="${ctx }/showActivityPic?id=${item.id}" alt="${item.name }" style="cursor: pointer;" onclick="javascript:window.open('${ctx}/${item.url }','_target');"/> </p>
         </div>
        <div class="content_rcr">
        <h4 style="cursor: pointer;" onclick="javascript:window.open('${ctx}/${item.url }','_target');">${item.name }</h4>
        <span>${item.descrip }</span>
        <div class="date" style="color: #767676; position:absolute; margin-top:15px;"><fmt:formatDate value="${item.startTime }" pattern="yyyy.MM.dd"/>-<fmt:formatDate value="${item.endTime }" pattern="yyyy.MM.dd"/></div>
        <c:set var="nowDate" value="<%=new Date()%>"></c:set> 
        <c:if test="${item.endTime >= nowDate }">
          <div class="cj">
          <div class="cj_btn" onclick="javascript:window.open('${ctx}/${item.url }','_target');"><a></a></div>
         </div>
         </c:if>
         
         <c:if test="${item.endTime < nowDate }">
          <div class="cj">
          <div class="js_btn" onclick="javascript:window.open('${ctx}/${item.url }','_target');" ><a></a></div>
         </div>
         </c:if>
         
         </div>
        
      </div>
      </c:forEach>
     
      
       
      
      
    
    </div>
  </div>