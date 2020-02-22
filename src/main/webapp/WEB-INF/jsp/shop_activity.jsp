<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>蛙宝网探店活动详情页面</title>
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/shop_activity.css" rel="stylesheet" />
</head>

<body>
<div class="top_w"></div>
<div class="center_w">
  <div class="center">
    <div class="center_t">
      <div class="center_t_left"> </div>
      <div class="center_t_right">
        <div class="shop_title">拿返利，大额充值返不停</div>
        <div class="fl_c">
          <div class="fl_left"><span>活动规则</span></div>
          <div class="fl_right">
            <div class="fl_right_ts"> 为答谢广大用户对蛙宝网的支持和信任，特此回馈广大用户，即日起我们将举办蛙宝用户返利活
              动！活动期间，符合累计标准用户，在完成游戏任务后，都可得到任务返利！ </div>
            <ul class="fl_list">
              <li>活动期限内领取游戏累计达到 10000元 可获赠  10  纳币。</li>
              <li>活动期限内领取游戏累计达到 50000元 可获赠  50 纳币。</li>
              <li>活动期限内领取游戏累计达到 100000元 可获赠  200 纳币。</li>
              <li>活动期限内领取游戏累计达到 500000元 可获赠  1500 纳币。</li>
              <li>活动期限内领取游戏累计达到 1000000元 可获赠  3000 纳币。 </li>
            </ul>
            <span class="fl_txt">提示：获赠纳币将在活动结束后十天内分批返还，每天返还获赠金额的十分之一。每用户获赠金额最高为3000纳币。</span> </div>
        </div>
      </div>
    </div>
    <div class="center_c">
      <div class="center_c_left"> </div>
      <div class="center_c_right">
        <div class="shop_title">铁杆用户，欢乐享不停</div>
        <div class="share_c">
          <div class="share_left"><span>活动规则</span></div>
          <div class="share_right">
            <div class="fl_right_ts"> 自蛙宝网上线以来，得到广大用户支持，为答谢老用户的厚爱，蛙宝网将举办一期铁杆用户返利活动，只要你是蛙宝网的忠实用户，将得到比一般会员更多的返利！
              <h2> 参与资格：</h2>
            </div>
            <ul class="fl_list">
              <li> 以活动开始日为截止时间，蛙宝注册会员，且帐号注册时间 超过 3个月</li>
              <li>以活动开始日为截止时间，蛙宝帐号累积充值 超过 10W<a class="game_btn" target="_blank" href="${ctx }/2/showGameDetail.html"></a></li>
            </ul>
            <span class="fl_txt">提示：上述条件符合其一者，在活动期间领取指定游戏任务将享受高额返利。
            
            指定游戏任务到期，分批返还押金和返利，每天返还【（押金+返利）/10】个纳币。</span> </div>
        </div>
      </div>
    </div>
  </div>
  <div class="center_b">
    <div class="center_b_left"> </div>
    <div class="center_b_right">
      <div class="shop_title">与联合创始人面对面</div>
      <div class="face_c">
        <div class="face_left"><span>活动规则</span></div>
        <div class="face_right">
          <div class="fl_right_ts"> 自蛙宝网成立以来，好多用户对我们产生好奇，都想一探蛙宝网运营以及盈利的奥秘。现在，为了蛙
            宝网的长远发展，以及展示公司形象，我们将举办会员探店活动。 </div>
          <ul class="fl_list">
            <li> 领取游戏任务金额 满 10000 纳币，我们将随机抽取 5名 会员  (活动期间：4.19~5.3) 
              报销探店活动全程所有费用。具体探店时间，我们将另行通知。</li>
            <li>我们将开放在线报名探店活动
              如果您未被抽取到，也可通过 <a class="bm_btn" href="${ctx }/viewSignUp" target="_blank"></a> <em style="margin-left:130px;">方式参与，我们将选取部分用户</em></li>
          </ul>
          <span class="fl_txt">提示：所有参与探店活动的用户，都将获得精美大礼包一份！ </span> </div>
      </div>
      
       <div class="lucky">
      <h2>幸运名单</h2>
      <ul>
     <c:forEach items="${signUplist}" var="signUp">
       <li><dt><c:choose><c:when test="${not empty signUp.photo}">
										<img src="${ctx }/${signUp.userid }/getPhoto" width="46" height="46" alt="蛙宝网用户头像" />
									</c:when>
									<c:otherwise>
										<img width="46" height="46" src="${ctx }/images/people.png" alt="蛙宝网用户头像" /> 
									</c:otherwise>
								</c:choose></dt><dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${signUp.name}</dd></li>
      </c:forEach>
      </ul>
      </div>
    </div>
  </div>
</div>
<div class="bottom_w">
  <div class="bottom_top"><div class="bottom_top_c"><span>感谢蛙宝网金牌合作伙伴<em style="font-size:48px;">网银在线的全程赞助 !</em></span> </div></div>
  <div class="bottom_center_w">
  
    <div class="bottom_center"> 
    <div  class="partner">
    <div class="partner_c">
    <span> &nbsp;&nbsp;&nbsp;&nbsp;网银在线（北京）科技有限公司（以下简称网银在线）为京东集团全资子公司，是国内领先的电子支付解决方案提供商，专注于为各行业提供安全、便捷的综合电子支付服务。公司于2011年5月3日首批荣获央行《支付业务许可证》的支付企业，并在中国支付清算协会中任常务理事单位。</span>
    <span>&nbsp;&nbsp;&nbsp;&nbsp;网银在线与包括五大国有银行、银联在内的国内主要金融机构建立了长期的战略合作关系，支持互联网、POS、手机、电话等多种线上线下的终端支付形式，以及银行卡、外卡、电信卡等各种支付工具形成了银行卡网银支付、银行卡快捷支付、信用卡无卡支付、手机充值卡支付、电话支付、银行卡POS收单、预付费卡发卡及受理等业内领先产品。</span>
    <span>&nbsp;&nbsp;&nbsp;&nbsp;网银在线已拥有逾5万商业合作伙伴，其中既包括中国联通、中科院、香港航空、澳门航空、深圳航空等传统大型企事业单位，也覆盖到好利来、7天酒店、格林豪泰等大型民营连锁企业，以及去哪网、窝窝团等知名电商平台。</span>
    <a class="wy_logo"><img width="195" height="60" src="${ctx }/images/shops/wy_logo.png"/></a>
    </div>
    <div class="ts" style="float:right; color:#fff;">*本活动最终解释权归蛙宝网所有</div>
    </div>
    </div>
  </div>
</div>
</body>
</html>
