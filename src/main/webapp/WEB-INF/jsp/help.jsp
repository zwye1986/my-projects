<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="${ctx }/css/about.css" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(function() {
		$(".btn-slide").click(function() {
			$("#panel").slideToggle("slow");
			$(this).toggleClass("active");
			return false;
		});

		var param = getQueryString("t");
		if ("about" == param) {
			setTab('one', 1, 7);
		} else if ("help" == param) {
			setTab('one', 2, 7);
		} else if ("href" == param) {
			setTab('one', 5, 7);
		} else if ("partner" == param) {
			setTab('one', 6, 7);
		} else if ("call" == param) {
			setTab('one', 7, 7);
		} else if ("video" == param) {
			setTab('one', 3, 7);
		} else if ("process" == param) {
			setTab('one', 4, 7);
		}
		

		$("a.tab").click(function() {
			// switch all tabs off
			$(".active").removeClass("active");
			// switch this tab on
			$(this).addClass("active");
			// slide all content up
			$(".content").slideUp();
			// slide this content up
			var content_show = $(this).attr("title");
			$("#" + content_show).slideDown();
		});

		$("em").click(function() {
			$("html,body").animate({
				scrollTop : $(".wrap").offset().top
			}, 1000);
		});

	});

	function setTab(name, cursel, n) {
		for (i = 1; i <= n; i++) {
			var menu = document.getElementById(name + i);
			var con = document.getElementById("con_" + name + "_" + i);
			menu.className = i == cursel ? "hover" : "";
			con.style.display = i == cursel ? "block" : "none";
		}
	}

	//获得url参数
	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
</script>

<div class="content_Top"></div>
<!--选项卡开始-->
<div class="tab_box">
	<div class="lyz_tab_left">
		<div class="pro_con111">
			<ul>
				<li id="one1" onclick="setTab('one',1,7)">关于我们</li>
				<li class="hover" id="one2" onclick="setTab('one',2,7)">新手帮助</li>
				<li id="one3" onclick="setTab('one',3,7)">蛙宝视频</li>
				<li id="one4" onclick="setTab('one',4,7)">蛙宝流程</li>
				<li id="one5" onclick="setTab('one',5,7)">友情链接</li>
				<li id="one6" onclick="setTab('one',6,7)">合作伙伴</li>
				<li id="one7" onclick="setTab('one',7,7)">联系我们</li>
			</ul>
		</div>
	</div>
</div>
<div class="lyz_tab_right">
	<div class="hover" id="con_one_1" style="display: none">
		<div class="profile_T"></div>
		<div class="profile_C">
			<p>
				<em>蛙宝网*wowpower.cn</em>是一个为【非游戏】玩家量身打造的在线返利平台，以只需领取游戏的方式转化价值,蛙宝网致力于搭建一个广大用户与游戏、广告共赢的媒介平台，通过领取游戏，用户可以轻松的拿到返利。对于广大的用户
				，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，我们在成长过程中可能会遇到一
				些困难，如果有更好的建议请告诉我们，蛙宝网的发展离不开你们的支持。 我们的目标是让大家以轻松的方式来创造巨大的价值!
			</p>
			<p>
				纳币是一种虚拟货币，等值于人民币，比率是1 : 1，即1元等值于1纳币。纳币可使用第三方支付平台进行充值、
				也可以提现转化成现金。纳币可以用来拿返利，也可以用于便民服务。详情可见<a href="${ctx }/help.html">帮助中心</a>
			</p>
			<p>蛙宝网在很大程度上改变了传统的游戏网站，也改变了人们的生活消费方式和投资类型。崇尚自由和个性、灵活多变的生活态度以及理性的思维，成为蛙宝网上重要特征。不一样的投资方式，让蛙宝民们乐在其中：领游戏、 拿返利、赶时髦。</p>
		</div>
		<div class="logo_T"></div>
		<div class="logo_C">
			<p style="float: left; width: 298px; height: 235px;">
				<img src="${ctx }/images/about/logo01.png" alt="蛙宝网log" />
			</p>
			<p style="float: right;">
				<img src="${ctx }/images/about/logo02.png"  alt="蛙宝网log"/>
			</p>
		</div>
	</div>
	<div class="hover" id="con_one_2">
		<div class="help_C">
			<P>纳币是什么？有什么作用？</P>
			<span>纳币是一种虚拟货币，等值于人民币，比率是1 : 1，即1元等值于1纳币。纳币可使用第三方支付平台进行充值、也可以
				提现转化成现金。纳币可以用来领游戏拿返利、购物、转换积分换礼品。<em class="zd fr">TOP</em>
			</span>
			<P>蛙宝网是什么？</P>
			<span>蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【领】游戏，用户可以拿到轻松的返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，我们在成长过程中可能会遇到一些困难，
				如果有更好的建议请告诉我们，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。<em
				class="zd fr">TOP</em>
			</span>
			<P>如何注册蛙宝网？</P>
			<span>您只要根据我们的注册界面提示，填写您真实的手机号码，接收短信验证提示并输入验证码，再设置密码就很便捷的注册成功了。
				<em class="zd fr">TOP</em>
			</span>
			<P>如何修改密码和账户相关资料？</P>
			<span>登录用户中心，进入基本资料页面即可修改密码；会员资料亦在用户中心中修改，修改完后请保存。 <em
				class="zd fr">TOP</em></span>
			<P>怎样领取任务，任务需要点击吗？</P>
			<span>用户通过网站充值渠道充值，然后领取自己想要领取的任务，领取完任务可以在用户中心任务明细里查看任务截止时间，不用每天点击，只要等到截止日期到了，第二天凌晨1点前系统会自动给予本金和返利。
				<em class="zd fr">TOP</em>
			</span>
			<P>忘记密码怎么办？</P>
			<span>如果您忘记了密码，可以通过忘记密码功能回答预设置的密码问题找回您的密码。<em class="zd fr">TOP</em></span>
			<P>关于普通会员和VIP会员的费用及区别？</P>
			<span>普通会员没有费用，VIP会员每个月39元的费用，VIP会员提现每天中午12点之前提现当天到账，12点之后提现第二天到账。普通会员提现要过T+3个工作日到账（例如周一提现周四到）。
				<em class="zd fr">TOP</em>
			</span>
			<P>能直接充值后提现吗？</P>
			<span>充值的金额要全部用于领取游戏，否则直接提现会被系统识别视为套现，提现不成功。 <em class="zd fr">TOP</em>
			</span>
			<P>关于签到，签到是怎么计算收益的？签到和投资额有关联吗？</P>
			<span>以连续签到7天为周期，单日签到可获得0.1元，连续6天签到后，第7天签到即可获得0.5元，签到和投资额没有关联。<em class="zd fr">TOP</em>
			</span>
			<P>积分商城兑换及处理时间？</P>
			<span>积分商城处理商品兑换的时间现已改为每月的15日和26日前统一处理(上月26日至本月14日的积分兑换将在本月15日前处理；本月15日至本月25日的积分兑换将在本月26日前处理)，如临近处理时间兑换，可能会因商品缺货而到下一次时间再处理。<em class="zd fr">TOP</em>
			</span>
			<P>关于提现</P>
            <span>VIP会员提现每天中午12点之前提现当天到账，12点之后提现第二天到账。普通会员提现要过T+3个工作日到账（例如周一提现周四到）。蛙宝网禁止套现行为，充值后不领取游戏任务就发起提现的行为，都将被认定为套现行为。<em class="zd fr">TOP</em>
            </span>
            <P>关于积分兑换</P>
            <span> 在<a href="${ctx}/mall.html">积分商城</a>中,用户可以根据自己已经获取的积分选择相应的商品进行兑换。用户可以在【用户中心】->【积分管理】-><a href="${ctx}/user/credits/obtainlist.html?type=over">【兑换记录】</a>查看兑换记录。<em class="zd fr">TOP</em>
            </span>
            <br/>
		</div>
	</div>
	<div class="hover" id="con_one_3" style="display: none">
		<div class="video_T"></div>
		<div class="video_C">
			<embed
				src="http://player.youku.com/player.php/sid/XNTkwNTY3OTk2/v.swf"
				allowFullScreen="true" quality="high" width="500" height="300"
				align="middle" allowScriptAccess="always"
				type="application/x-shockwave-flash"></embed>
		</div>
	</div>
	<div class="hover" id="con_one_4" style="display: none">
		<div class="process_T"></div>
		<ul class="process_C">
			<li>
              <dl>
              <dt>第一步  注册帐户</dt>
              <dd>点击网站首页右上角的“注册”按钮，输入手机号及验证码；推荐码（可选），手机短信领取密码。</dd>
              <dd><img src="${ctx}/images/about/process_1.png"  alt="操作流程1"/></dd>
              </dl>
            </li>
            <li>
              <dl>
              <dt>第二步  完善帐户</dt>
              <dd>进入用户中心，点击个人中心，完善个人资料；点击账户管理，进行账户充值以便领取游戏。</dd>
              <dd><img src="${ctx}/images/about/process_2.png" alt="操作流程2"/></dd>
              </dl>
            </li>
            <li>
              <dl>
              <dt>第三步  充值帐户</dt>
              <dd>点击账户管理，进行账户充值开始理财之旅。</dd>
              <dd><img src="${ctx}/images/about/process_3.png" alt="操作流程3"/></dd>
              <dd><img src="${ctx}/images/about/process_4.png" alt="操作流程4"/></dd>
              <dd><img src="${ctx}/images/about/process_5.png" alt="操作流程5"/></dd>
              </dl>
            </li>
            <li>
              <dl>
              <dt>第四步  领取游戏</dt>
              <dd>选择自己喜欢的游戏，点击【点游戏】或【领取游戏】</dd>
              <dd><img src="${ctx}/images/about/process_6.png" alt="操作流程6"/></dd>
              </dl>
            </li>
            <li>
              <dl>
              <dt>第五步  确定交纳押金开始游戏</dt>
              <dd>确认游戏参数，系统将自行扣除押金，游戏领取成功后游戏就可以啦！</dd>
              <dd><img src="${ctx}/images/about/process_7.png" alt="操作流程7"/></dd>
              <dd><img src="${ctx}/images/about/process_8.png" alt="操作流程8"/></dd>
              </dl>
            </li>
            <li>
              <dl>
              <dt>第六步  查看任务及返利详情</dt>
              <dd>在规定时间内完成任务后，即可获得相应的纳币奖励并且返还游戏押金。</dd>
             <dd><img src="${ctx}/images/about/process_9.png" alt="操作流程9"/></dd>
              </dl>
            </li>    
            <li>
              <dl>
              <dt>第七步  提现</dt>
              <dd>进入用户中心，点击【提现】，填写相关信息，点击确认即可提现到账。</dd>
              <dd><img src="${ctx}/images/about/process_10.png" alt="操作流程10"/></dd>
              <dd><img src="${ctx}/images/about/process_11.png" alt="操作流程11"/></dd>
              </dl>
            </li> 
		</ul>
	</div>
	<div class="hover" id="con_one_5" style="display: none">
		<div class="partner_T"></div>
		<ul class="partner_C">
<li><a href="http://www.chinabank.com.cn/"
                                target="_blank" style="cursor: pointer;"><img
                                        src="${ctx }/images/i_bank_logo.png" alt="网银在线" /></a></li>
<li><a href="https://pay.sina.com.cn/index" target="_blank"
				style="cursor: pointer;"><img src="${ctx }/images/wqblogo.jpg"  alt="新浪支付支付"/></a></li>
			<li><a href="https://www.paypal.com" target="_blank"
				style="cursor: pointer;"><img
					src="${ctx }/images/paypal_logo.png" /></a></li>
			<li><a href="http://www.coolpos.cn/index"
				target="_blank" style="cursor: pointer;"><img
					src="${ctx }/images/ks.jpg" alt="酷刷" /></a></li>

			<%--  <a href="https://www.tenpay.com" target="_blank" style="cursor: pointer;"> <img
			src="${ctx }/images/cft_logo.png" /></a> <a href="https://www.paypal.com" target="_blank"
			style="cursor: pointer;"> <img
			src="${ctx }/images/paypal_logo.png" /></a> <a href="https://www.alipay.com" target="_blank"
			style="cursor: pointer;"> <img src="${ctx }/images/hz01.png" /></a>
			<a href="http://www.coolpos.cn/cn/Index.aspx" target="_blank"
			style="cursor: pointer;"> <img src="${ctx }/images/ks.jpg" /></a> --%>
		</ul>
		<div class="link_T"></div>
		<div class="link_C">
			<a target="_blank" href="http://mg.51.com"> 暮光 ┊</a> <a
				target="_blank" href="http://www.52pk.com"> 52PK网页游戏 ┊</a> <a
				target="_blank" href="http://lhzs.37wan.com"> 烈火战神 ┊</a> <a
				target="_blank" href="http://game.people.com.cn"> 人民网游戏 ┊</a> <a
				target="_blank" href="http://game.mop.com"> 猫扑游戏 ┊</a> <a
				target="_blank" href="http://ly.9377.com"> 烈焰 ┊</a> <a
				target="_blank" href="http://www.51wan.com"> 51wan网页游戏 ┊</a> <a
				target="_blank" href="http://www.3366.com"> 3366网页游戏 ┊</a> <a
				target="_blank" href="http://wan.renren.com"> 人人网页游戏</a>
		</div>
	</div>
	<div class="hover" id="con_one_6" style="display: none">
		<div class="partner_T"></div>
		<ul class="partner_C">
<li><a href="http://www.chinabank.com.cn/"
                                target="_blank" style="cursor: pointer;"><img
                                        src="${ctx }/images/i_bank_logo.png" alt="网银在线"/></a></li>
			<li><a href="https://pay.sina.com.cn/index" target="_blank"
				style="cursor: pointer;"><img src="${ctx }/images/wqblogo.jpg"  alt="新浪支付支付"/></a></li>
			<li><a href="https://www.paypal.com" target="_blank"
				style="cursor: pointer;"><img
					src="${ctx }/images/paypal_logo.png"  alt="paypal" /></a></li>
			<li><a href="http://www.coolpos.cn/index"
				target="_blank" style="cursor: pointer;"><img
					src="${ctx }/images/ks.jpg" alt="酷刷"/></a></li>
		</ul>
		<div class="link_T"></div>
		<div class="link_C">
			<a target="_blank" href="http://mg.51.com"> 暮光 ┊</a> <a
				target="_blank" href="http://www.52pk.com"> 52PK网页游戏 ┊</a> <a
				target="_blank" href="http://lhzs.37wan.com"> 烈火战神 ┊</a> <a
				target="_blank" href="http://game.people.com.cn"> 人民网游戏 ┊</a> <a
				target="_blank" href="http://game.mop.com"> 猫扑游戏 ┊</a> <a
				target="_blank" href="http://ly.9377.com"> 烈焰 ┊</a> <a
				target="_blank" href="http://www.51wan.com"> 51wan网页游戏 ┊</a> <a
				target="_blank" href="http://www.3366.com"> 3366网页游戏 ┊</a> <a
				target="_blank" href="http://wan.renren.com"> 人人网页游戏</a>
		</div>
	</div>
	<div class="hover" id="con_one_7" style="display: none">
		<div class="contact_T"></div>
		<div class="contact_C">
			<p>江苏蛙宝网络技术有限公司</p>
			<p>电话：400-891-6233</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;025-84533055</p>
			<p>传真：025-84533050</p>
			<p>邮箱：service@venada.com.cn</p>
			<p>公司地址：江苏省南京市建邺区江东中路303号奥体名座E座9楼</p>
		</div>
		<div class="contact_share">
			<em>关注我们:</em><a href="http://t.qq.com/venada" target="_blank"><img
				src="${ctx }/images/about/tengxun.png"  alt="腾讯"/></a><a
				href="http://weibo.com/venada" target="_blank"><img
				src="${ctx }/images/about/sina.png"  alt="新浪" /></a><a
				href="https://twitter.com/venadasoftware" target="_blank"><img
				src="${ctx }/images/about/twitter.png"  alt="twitter"/></a><a
				href="https://www.facebook.com/vndsoftware" target="_blank"><img
				src="${ctx }/images/about/facebook.png" alt="facebook" /></a>
		</div>
		<div class="phone_T"></div>
		<div class="phone_C"></div>
	</div>
</div>
<div class="clear"></div>
<!--选项卡结束-->

</div>
