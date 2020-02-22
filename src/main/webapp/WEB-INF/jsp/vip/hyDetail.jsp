<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以拿到轻松的返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。"/>
<meta name="keywords" content="蛙宝网,蛙宝,赚钱,注册返现,wowpower,挖宝,网赚,注册提现,	理财,返利,积分活动,打码,网上挣零花钱,赚钱,点击游戏,网赚项目,注册网赚" />
<title>蛙宝网-网络金融理财新选择</title>
<link href="${ctx }/css/hy_detail.css" rel="stylesheet" />
<link href="${ctx }/css//base.css" rel="stylesheet"/>
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<script type="text/javascript">
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
<div class="content_t_wrapper">
    <div class="content_t">
      <div class="detail_share">
       <!-- Baidu Button BEGIN -->
<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare" data="{'url':http://www.wowpower.cn/hy_detail.html,'text':加入蛙宝会员中心，享受更多会员特权}">
<a class="bds_qzone"></a>
<a class="bds_tsina"></a>
<a class="bds_tqq"></a>
<a class="bds_renren"></a>
<a class="bds_t163"></a>
<span class="bds_more"></span>
</div>
<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=0" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script>
<!-- Baidu Button END -->
      </div>
      <div class="detail_collect"  onclick="addfavorite();"></div>
      <div class="detail_hy" onclick="javascript:window.location.href='${ctx }/user/4/manager'"></div>
    </div>
  </div>
  <div class="content_c_wrapper">
    <div class="content_c">
      <div class="content_c_banner"></div>
      <div class="list_banner"></div>
    </div>
  </div>
  <div class="content_b_wrapper">
    <div class="content_b">
      <div class="content_b_l fl">
        <div class="content_b_ll fl"></div>
        <div class="content_b_lr fl">
          <ul>
            <li>每天登录额外获得会员积分<em class="txt1">5</em>积分。</li>
            <li>等级<em class="txt1">个性</em>符号，彰显您的特别之处。</li>
            <li>设置<em class="txt1">支付密码</em>，账户安全保护到位。</li>
            <li>会员提现<em class="txt1">T+0到账</em>，无需等待。</Li>
            <li>短信提醒<em class="txt1">免费接收</em>，绝不遗漏财富。</li>
          </ul>
        </div>
      </div>
      <div class="content_b_r fr">
        <div class="content_b_rl fl"> </div>
        <div class="content_b_rr fr">
          <ul>
            <li>会员<em class="txt1">无需点击</em>游戏即可没完成每日任务。</li>
            <li>领取会员游戏<em class="txt1">利率更高</em>。</li>
            <li><em class="txt1">安全邮箱</em>可以帮您找回您的信息。</li>
            <li><em class="txt1">绑定手机NO.2</em>，不怕遗失重要信息。</li>
            <li>会员<em class="txt1">升级加速</em>，更多增值业务敬请期待。</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>