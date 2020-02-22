<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="${ctx }/css/account.css" rel="stylesheet" />	
<link href="${ctx }/css/new-global-min.css" rel="stylesheet" />	
<script type="text/javascript">
$(function(){
	$(".top").click(function() {
		$("html,body").animate({
			scrollTop : $("#header").offset().top
		}, 1000);
	});
});
</script>
<div id="wrapper"> 
      <!--sidebar-->
      <div id="sidebar">
    <dl class="apply-menu">
          <dt>帮助中心</dt>
           <dd> <a href="${ctx }/help_about.html"><i class="nav-icons icons"></i>关于我们</a> </dd>
          <dd class="current"> <a href="${ctx }/help.html"><i class="nav-icons icons"></i>新手帮助</a> </dd>
          <dd> <a href="${ctx }/process.html"><i class="nav-icons icons"></i>蛙宝流程</a> </dd>
          <dd> <a href="${ctx }/help_partner.html"><i class="nav-icons icons"></i>合作伙伴</a> </dd>
          <dd> <a href="${ctx }/help_contact.html"><i class="nav-icons icons"></i>联系我们</a> </dd>
        </dl>
        <div class="wb_vip"><a href="${ctx }/vip_index.html" target="_blank"><img width="190" height="70" src="${ctx }/images/newhelp/wb_vip.png"/></a></div>
  </div>
      <!--/sidebar--> 
      <!--main-->
     <div id="main">
    <div class="title-items">
      <h2  style="color:#3c3c3c">新手帮助</h2>
     </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>纳币是什么？有什么作用？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">纳币是一种虚拟货币，等值于人民币，比率是1 : 1，即1元等值于1纳币。纳币可使用第三方支付平台进行充值、也可以 提现转化成现金。
        纳币可以用来点游戏拿返利、购物、转换积分换礼品。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>蛙宝网是什么？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以拿到轻松的返利。对于广大的用户，我们会长期为你
        们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，我们在成长过程中可能会遇到一些困难， 如果有更好的建议请告诉我们，
        蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>如何注册蛙宝网？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">您只要根据我们的注册界面提示，填写您真实的手机号码，接收短信验证提示并输入验证码，再设置密码就很便捷的注册成功了。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>如何修改密码和账户相关资料？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">登录用户中心，进入基本资料页面即可修改密码；会员资料亦在用户中心中修改，修改完后请保存。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>账号注销后，想要恢复账号信息怎么办？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">账号注销后，用同一手机号码注册，系统会自动检测出,并发送是否恢复数据的确认信息界面，如果您想要恢复数据，根据提示操作即可。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>忘记密码怎么办？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">如果您忘记了密码，可以通过忘记密码功能回答预设置的密码问题找回您的密码。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>关于普通会员和VIP会员的费用及区别？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">普通会员没有费用，VIP会员每个月39元的费用，VIP会员提现每天中午12点之前提现当天到账，12点之后提现第二天到
账。普通会员提现要过T+3个工作日到账（例如周一提现周四到。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>能直接充值后提现吗？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">充值的金额要全部用于领取游戏，否则直接提现会被系统识别视为套现，提现不成功。<a class="top">TOP</a></p>
    </div>
     <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>任务到期，什么时候本金和返利可以入账？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">任务到期后的第二天，本金和返利会自动结算至用户账户内。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>关于签到，签到是怎么计算收益的？签到和投资额有关联吗？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">以连续签到7天为周期，单日签到可获得0.1元，连续6天签到后，第7天签到即可获得0.5元，签到和投资额没有关联。<a class="top">TOP</a></p>
    </div>
    <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>积分商城兑换及处理时间？</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">积分商城处理商品兑换的时间现已改为每月的15日和26日前统一处理(上月26日至本月14日的积分兑换将在本月15日前
处理；本月15日至本月25日的积分兑换将在本月26日前处理)，如临近处理时间兑换，可能会因商品缺货而到下一次时
间再处理。<a class="top">TOP</a></p>
    </div>
     <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>关于提现</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">VIP会员提现每天中午12点之前提现当天到账，12点之后提现第二天到账。普通会员提现要过T+3个工作日到账（例如周一提现周四到）。蛙宝网禁止套现行为，充值后不领取游戏任务就发起提现的行为，都将被认定为套现行为。<a class="top">TOP</a></p>
    </div>
     <div class="hepl_title"><i class="icon"><img src="${ctx }/images/newhelp/help_icon.png"></i>
      <h3>关于积分兑换</h3>
    </div>
    <div class="block_list hepl_txt">
      <p class="rb">在积分商城中,用户可以根据自己已经获取的积分选择相应的商品进行兑换。用户可以在【用户中心】->【积分管理】->【兑换记录】查看兑换记录。<a class="top">TOP</a></p>
    </div>
  </div>
      <!--/main--> 
    </div>