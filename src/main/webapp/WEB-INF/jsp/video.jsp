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
				<li id="one2" onclick="setTab('one',2,7)">新手帮助</li>
				<li class="hover" id="one3" onclick="setTab('one',3,7)">蛙宝视频</li>
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
				<em>蛙宝网*wowpower.cn</em>是一个为【非游戏】玩家量身打造的在线返利平台，以只需点击链接的方式转化价值,蛙宝网致力于搭建一个广大用户与游戏、广告共赢的媒介平台，通过点游戏，用户可以拿到轻松的返利。对于广大的用户
				，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，我们在成长过程中可能会遇到一
				些困难，如果有更好的建议请告诉我们，蛙宝网的发展离不开你们的支持。 我们的目标是让大家以轻松的方式来创造巨大的价值!
			</p>
			<p>
				纳币是一种虚拟货币，等值于人民币，比率是1 : 1，即1元等值于1纳币。纳币可使用第三方支付平台进行充值、
				也可以提现转化成现金。纳币可以用来拿返利，也可以用于便民服务。详情可见<a href="${ctx }/help.html">帮助中心</a>
			</p>
			<p>蛙宝网在很大程度上改变了传统的游戏网站，也改变了人们的生活消费方式和投资类型。崇尚自由和个性、灵活多变的生活态度以及理性的思维，成为蛙宝网上重要特征。不一样的投资方式，让蛙宝民们乐在其中：点游戏、 拿返利、赶时髦。</p>
		</div>
		<div class="logo_T"></div>
		<div class="logo_C">
			<p style="float: left; width: 298px; height: 235px;">
				<img src="${ctx }/images/about/logo01.png" />
			</p>
			<p style="float: right;">
				<img src="${ctx }/images/about/logo02.png" />
			</p>
		</div>
	</div>
	<div class="hover" id="con_one_2" style="display: none">
		<div class="help_C">
			<P>纳币是什么？有什么作用？</P>
			<span>纳币是一种虚拟货币，等值于人民币，比率是1 : 1，即1元等值于1纳币。纳币可使用第三方支付平台进行充值、也可以
				提现转化成现金。纳币可以用来点游戏拿返利、购物、转换积分换礼品。<em class="zd fr">TOP</em>
			</span>
			<P>蛙宝网是什么？</P>
			<span>蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以拿到轻松的返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，我们在成长过程中可能会遇到一些困难，
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
			<P>账号注销后，想要恢复账号信息怎么办？</P>
			<span>账号注销后，用同一手机号码注册，系统会自动检测出,并发送是否恢复数据的确认信息界面，如果您想要恢复数据，根据提示操作即可
				<em class="zd fr">TOP</em>
			</span>
			<P>忘记密码怎么办？</P>
			<span>如果您忘记了密码，可以通过忘记密码功能回答预设置的密码问题找回您的密码。<em class="zd fr">TOP</em></span>
			<P>如何开始玩游戏？</P>
			<span>注册并登录蛙宝网，充值纳币，选择任意一款游戏，并交纳一定的纳币作为保证金，在规定日期完成点击任务后，系统自动给您计算返利报酬。
				<em class="zd fr">TOP</em>
			</span>
			<P>如何获得返利？</P>
			<span>返利其实非常简单！
				例如游戏的保证金是300纳币，任务周期是10次，并要求在10天内完成，返利100纳币，但是每天无论这个游戏点过几次都按1次计算，
				系统会记录您的游戏次数；当您交付押金后并在10天内完成10次的游戏记录，蛙宝网将会连同您的押金一起退回，给您返利
				100纳币，等同100人民币哦！ <em class="zd fr">TOP</em>
			</span>
			<P>如何获得更多的游戏返利？</P>
			<span>游戏的领取是没有上限的，可同时玩多个游戏。只要在限定时间内完成任务，收益非常可观。注册会员一天内可玩一个
				游戏，也可以选择多个游戏。领取游戏的多少主要取决于注册会员的个人情况。建议新手按照自己的实际情况选择，以
				免出现不能完成游戏任务，被扣罚金的情况产生。<em class="zd fr">TOP</em>
			</span>
			<P>返利规则？惩罚规则？</P>
			<span>点游戏G需要交X个纳币，限制需要在D天内点满N次能够得到P个纳币。用户会得到X+P个纳币；如果在D天内未点满N
				次，则扣除罚金Y，最后用户得到X-Y个纳币。<em class="zd fr">TOP</em>
			</span>
			<P>关于提现</P>
            <span>前期蛙宝网委托第三方支付公司将用户发起的提现金额于T+3至T+5个工作日内（由第三方支付公司安排），汇入用户指定的银行账户内。如因卡号以及所属银行问题，会
导致提现金额延迟到账。蛙宝网收到回退通知后会第一时间通知用户。同时，用户也可留意个人中心提现记录状态。<em class="zd fr">TOP</em>
            </span>
            <br/>
		</div>
	</div>
	<div class="hover" id="con_one_3" >
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
					<dt>第一步 注册帐户</dt>
					<dd>点击网站首页右上角的“注册”按钮，输入手机号及验证码，手机短信领取密码。</dd>
					<dd>
						<img src="${ctx }/images/about/process_1.png" />
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>第二步 完善帐户</dt>
					<dd>登录之后进入用户中心，点击个人中心，完善个人资料（包含基本资料、添加头像、绑定银行卡、
						会员中心以及修改密码）;点击账户管理，进行账户充值以便领取游戏。</dd>
					<dd>
						<img src="${ctx }/images/about/process_2.png" />
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>第三步 领取游戏</dt>
					<dd>进入游戏页面，选择自己喜欢的游戏，点击【进入游戏】或【领取游戏】</dd>
					<dd>
						<img src="${ctx }/images/about/process_3.png" />
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>第四步 确认交纳押金并领取游戏</dt>
					<dd>进入领取游戏的页面，会显示详细内容，点击确认，系统自行扣除押金，游戏领取成功。
						转入游戏页面，游戏就可以开始啦！任务明细可在用户中心查看。</dd>
					<dd>
						<img src="${ctx }/images/about/process_4.png" />
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>第五步 点游戏，领取返利</dt>
					<dd>按规定时间完成游戏任务，游戏任务完成即获得相应的纳币返利及返还游戏押金。</dd>
					<dd>
						<img src="${ctx }/images/about/process_5.png" />
					</dd>
				</dl>
			</li>
			<li>
				<dl>
					<dt>第六步 提现</dt>
					<dd>进入用户中心，点击【提现】，填写相关信息，点击确认即可提现到账。</dd>
					<dd>
						<img src="${ctx }/images/about/process_6.png" />
					</dd>
				</dl>
			</li>
		</ul>
	</div>
	<div class="hover" id="con_one_5" style="display: none">
		<div class="partner_T"></div>
		<ul class="partner_C">
			<li><a href="https://www.tenpay.com" target="_blank"
				style="cursor: pointer;"><img src="${ctx }/images/cft_logo.png" /></a></li>
			<li><a href="https://www.paypal.com" target="_blank"
				style="cursor: pointer;"><img
					src="${ctx }/images/paypal_logo.png" /></a></li>
			<li><a href="http://www.coolpos.cn/cn/Index.aspx"
				target="_blank" style="cursor: pointer;"><img
					src="${ctx }/images/ks.jpg" /></a></li>

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
			<li><a href="https://www.tenpay.com" target="_blank"
				style="cursor: pointer;"><img src="${ctx }/images/cft_logo.png" /></a></li>
			<li><a href="https://www.paypal.com" target="_blank"
				style="cursor: pointer;"><img
					src="${ctx }/images/paypal_logo.png" /></a></li>
			<li><a href="http://www.coolpos.cn/cn/Index.aspx"
				target="_blank" style="cursor: pointer;"><img
					src="${ctx }/images/ks.jpg" /></a></li>
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
			<p>江苏维纳达软件技术有限公司</p>
			<p>电话：025-84533055</p>
			<p>传真：025-84533050</p>
			<p>邮箱：service@venada.com.cn</p>
			<p>公司地址：江苏省南京市玄武区珠江路88号新世界中心A座21层</p>
		</div>
		<div class="contact_share">
			<em>关注我们:</em><a href="http://t.qq.com/venada" target="_blank"><img
				src="${ctx }/images/about/tengxun.png" /></a><a
				href="http://weibo.com/venada" target="_blank"><img
				src="${ctx }/images/about/sina.png" /></a><a
				href="https://twitter.com/venadasoftware" target="_blank"><img
				src="${ctx }/images/about/twitter.png" /></a><a
				href="https://www.facebook.com/vndsoftware" target="_blank"><img
				src="${ctx }/images/about/facebook.png" /></a>
		</div>
		<div class="phone_T"></div>
		<div class="phone_C"></div>
	</div>
</div>
<div class="clear"></div>
<!--选项卡结束-->

</div>
