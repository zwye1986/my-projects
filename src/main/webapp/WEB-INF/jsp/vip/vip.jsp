<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>
<link rel="stylesheet" href="${ctx }/css/jquery.onebyone.css"
	type="text/css" media="all">
<script type="text/javascript" src="${ctx}/js/jquery.onebyone.js"></script>
<script type="text/javascript" src="${ctx}/js/onebyone.js"></script>
<script type="text/javascript">
	$(function() {

		$(".theButton").each(function() {
			$(this).hasClass("");
		});
		
		$("#vip_privilegeMore").click(function(){
			$.cookie('vipindexHref',null);
			$.cookie('vipindexHref','vipprivilege');
		});
		
		$("#game_privilegeMore").click(function(){
			$.cookie('vipindexHref',null);
			$.cookie('vipindexHref','gameprivilege');
		});

	});
</script>


<!--vip_content start-->
<div class="vip_content">
	<div class="headernewslide headernewslide_mid" id="indexflash"
		style="background: #edecec;">
		<div class="headernewslide_inner" id="subindexflash" style="background: #000;">
			<!-- headernewslide slider -->
			<div class="headernewslide_slider">
				<div class="oneByOne1"
					style="overflow-x: hidden; overflow-y: hidden;">
					<div class="topSlider" style="display: block; left: 0px;">
						<div class="oneByOne_slide" style="left: 0px; display: block;">
							<div class="slide_image animate0 rotateIn"
								style="display: block;">
								<img src="${ctx }/images/vip/slider_img_1.png" alt="">
							</div>
							<div class="slide_text animate1 rotateIn" style="display: block;">
								<div class="slide_title">
									<a href="#" target="_self" hidefocus="true"
										style="outline-style: none; outline-width: initial; outline-color: initial;">
										<img src="${ctx }/images/vip/slider_img_01.png" />
									</a>
								</div>
							</div>
						</div>
						<div class="oneByOne_slide" style="left: 940px; display: none;">
							<div class="slide_image animate0" style="display: none;">
								<img src="${ctx }/images/vip/slider_img_2.png" alt="">
							</div>
							<div class="slide_text animate1" style="display: none;">
								<div class="slide_title">
									<a href="#" target="_self" hidefocus="true"
										style="outline-style: none; outline-width: initial; outline-color: initial;">
										<img src="${ctx }/images/vip/slider_img_02.png" />
									</a>
								</div>
							</div>
						</div>
						<div class="oneByOne_slide" style="left: 3760px; display: none;">
							<div class="slide_image animate0" style="display: none;">
								<img src="${ctx }/images/vip/slider_img_3.png" alt="">
							</div>
							<div class="slide_text animate1" style="display: none;">
								<div class="slide_title">
									<a href="#" target="_self" hidefocus="true"
										style="outline-style: none; outline-width: initial; outline-color: initial;">
										<img src="${ctx }/images/vip/slider_img_03.png" />
									</a>
								</div>
								<div class="slide_description">
									<a href="#" target="_self" hidefocus="true"
										style="outline-style: none; outline-width: initial; outline-color: initial;">
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--vip_mod_login_wrapper start-->
			<div class="vip_mod_login_wrapper">
				<sec:authentication property="principal" var="currentUsr"
					scope="page" />
				<c:if
					test="${currentUsr eq '' or currentUsr eq null or currentUsr eq 'anonymousUser'}">
					<div class="loginBox">
						<a class="btn_login" title="用户登录" href="${ctx }/login.html"></a>
					</div>
				</c:if>

				<sec:authorize access="isAuthenticated()">
					<div class="profile_basic_info clearfix " style="margin-top: 10px;margin-left: 8px">
						<div class="vip_mod_user_avatar fl">
							<img width="60px" height="60px" alt="用户头像"
								src="${ctx }/${PHOTO_PATH == null ? 'images/people.png' : PHOTO_PATH}">
						</div>
						<div class="vip_mod_login_content fl">
							<div class="account_info clearfix">
								<sec:authentication property="principal.nickName" />
								
								<a class="account_quit fr"
									href="${ctx }/j_spring_security_logout">退出</a>
							</div>
							<div class="profile_level">
								<yma:LevelConvertIcoTag level='${LEVEL }' vipTag="${VIPTAG}"/>
							</div>
						</div>
					</div>
				</sec:authorize>

				<div class="login_text_area clearfix">
					<p class="fs_14">蛙宝VIP会员尽享:</p>
					<p>VIP特权、游戏特权等更多专属 VIP服务！</p>
					<a class="link_view_more fr" href="${ctx}/all_pri_vip.html" target="_blank">查看全部会员特权>></a>
				</div>
				<a class="vip_btn_join_em" href="${ctx }/user/security/center" target="_blank" id="open_security"> <span
					class="hide_text">开通会员</span>
				</a>
				<div class="login_recommend_content">
					<p>
						<a style="color: #FD6B6F;">【会员工作日12点之前发起提现当天到账】</a>
					</p>
				</div>
			</div>
			<!--vip_mod_login_wrapper end-->

		</div>
	</div>
	<!--main_content_wrapper start-->
	<div class="main_content_wrapper">
		<div class="main_content_l fl">
			<h2 class="vip_mod_title">
				<a target="_blank" id="vip_privilegeMore" href="${ctx}/all_pri_vip.html"></a>
			</h2>
			<ul class="mod_show_list clearfix">
				<li><img alt="等级个性图标"
						src="${ctx }/images/vip/level_icon.jpg">
						<div class="show_info">
							<h3>等级个性图标</h3>
							<p>新意揽财富</p>
						</div>
				</li>
				<li><img alt="短信免费接收"
						src="${ctx }/images/vip/free_icon.jpg">
						<div class="show_info">
							<h3>短信免费接收</h3>
							<p>财富不溜，荷包满满！</p>
						</div>
				</li>
				<li><img alt="自动点击，无需动手"
						src="${ctx }/images/vip/dj_icon.jpg">
						<div class="show_info">
							<h3>自动点击，无需动手</h3>
							<p>轻松拿返利</p>
						</div>
				</li>
				<li><img alt="提现T+0到账"
						src="${ctx }/images/vip/dz_icon.jpg">
						<div class="show_info">
							<h3>提现T+0到账</h3>
							<p>想要提现立即到账吗？</p>
						</div>
				</li>
				<li><img alt="额外积分"
						src="${ctx }/images/vip/jf_icon.jpg">
						<div class="show_info">
							<h3>额外积分</h3>
							<p>多多积分，开心多多！</p>
						</div>
				</li>
				<li><img alt="设置支付密码"
						src="${ctx }/images/vip/password_icon.jpg">
						<div class="show_info">
							<h3>设置支付密码</h3>
							<p>安全到位不用怕</p>
						</div></li>
				<li><img alt="安全邮箱"
						src="${ctx }/images/vip/email_icon.jpg">
						<div class="show_info">
							<h3>安全邮箱</h3>
							<p>找回你的信息易如反掌</p></li>
				<li><img alt="绑定手机NO.2"
						src="${ctx }/images/vip/no_icon.jpg">
						<div class="show_info">
							<h3>绑定手机NO.2</h3>
							<p>第二个保障安全到底</p>
						</div>
				</li>
				<li><img alt="高利率游戏"
						src="${ctx }/images/vip/high_icon.jpg">
						<div class="show_info">
							<h3>高利率游戏</h3>
							<p>比普通用户等多利润</p>
						</div>
				</li>
				<li><img alt="升级加速"
						src="${ctx }/images/vip/sj_icon.jpg">
						<div class="show_info">
							<h3>升级加速</h3>
							<p>级别越高，享受越多！</p>
						</div>
				</li>
			</ul>
			<h2 class="game_mod_title">
				<a target="_blank" href="${ctx}/game_privilege.html" id="game_privilegeMore"></a>
			</h2>
			<ul class="mod_show_list clearfix">
				<li><img alt="游戏特权"
						src="${ctx }/images/vip/game_icon.jpg">
						<div class="show_info">
							<h3>游戏特权</h3>
							<p>会员专享更高利率的游戏</p>
						</div>
				</li>
				<li><img alt="敬请期待"
						src="${ctx }/images/vip/qd_icon.jpg">
						<div class="show_info">
							<h3>敬请期待</h3>
							<p>更多特权，敬请期待！</p>
						</div>
				</li>
			</ul>
		</div>
		<div class="main_content_r fr">
			<ul class="column_act_recommend_list">
				<li class="official_wechat clearfix"><a class="recommend_link"
					title="新浪微博" href="http://weibo.com/venada" target="_blank">
						<div class="recommend_img fl">
							<img alt="新浪微博" src="${ctx }/images/vip/ewm_sina.png">
						</div>
						<div class="recommend_text fl">
							<h3>新浪微博</h3>
							<p>关注微博获悉最新情况，每日签到送人民币！</p>
						</div>
				</a></li>
				<li class="official_wechat clearfix"><a class="recommend_link"
					title="QQ微博" href="http://t.qq.com/venada" target="_blank" >
						<div class="recommend_img fl">
							<img alt="QQ微博" src="${ctx }/images/vip/ewm_sina.png">
						</div>
						<div class="recommend_text fl">
							<h3>QQ微博</h3>
							<p>你知道蛙宝网的热门游戏吗？最新活动惊喜更多哦！</p>
						</div>
				</a></li>
				<li class="official_wechat clearfix"><a class="recommend_link"
					title="蛙宝会员" href="${ctx }/hy_detail.html" target="_blank">
						<div class="recommend_img fl">
							<img alt="蛙宝会员" src="${ctx }/images/vip/wb_icon.png">
						</div>
						<div class="recommend_text fl">
							<h3>蛙宝会员</h3>
							<p>会员更多特权敬请期待！</p>
						</div>
				</a></li>
			</ul>
			<ul class="vip_mod_side_nav column_entrance_links_list">
				<li class="icon_list_jf"><a href="###">每天额外获得5积分</a></li>
				<li class="icon_list_fh"><a href="###">等级个性符号</a></li>
				<li class="icon_list_pwd"><a href="###">设置支付密码</a></li>
				<li class="icon_list_dz"><a href="###">会员实现T+0到账</a></li>
				<li class="icon_list_dx"><a href="###">短信提醒免费接收</a></li>
				<li class="icon_list_dj"><a href="###">无需点击游戏</a></li>
				<li class="icon_list_ll"><a href="###">会员游戏利率更高</a></li>
				<li class="icon_list_email"><a href="###">安全邮箱</a></li>
				<li class="icon_list_phone"><a href="###">绑定手机NO.2</a></li>
				<li class="icon_game_speedup"><a href="###">会员升级加速</a></li>
			</ul>
		</div>
	</div>
</div>
<!--vip_mod_login_wrapper end-->
<!--vip_content end-->
