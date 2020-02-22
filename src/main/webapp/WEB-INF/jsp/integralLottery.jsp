<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${ctx }/style/integral.css" type="text/css" rel="stylesheet">
<link href="${ctx }/style/hn.css" rel="stylesheet" />
<link href="${ctx }/style/jqueryui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jqueryui.js"></script>
<script type="text/javascript" src="${ctx }/js/kxbdSuperMarquee.js"></script>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<!--鼠标放上后出现暗影效果-->

<script type="text/javascript">
	$(document).ready(function() {

		$(".section ul li .rsp").hide();

		$(".section	 ul li").hover(function() {
			$(this).find(".rsp").stop().fadeTo(500, 0.5);
			$(this).find(".text").stop().animate({
				left : '0'
			}, {
				duration : 500
			});
		}, function() {
			$(this).find(".rsp").stop().fadeTo(500, 0);
			$(this).find(".text").stop().animate({
				left : '318'
			}, {
				duration : "fast"
			});
			$(this).find(".text").animate({
				left : '-318'
			}, {
				duration : 0
			});
		});

	});
</script>
<script type="text/javascript">
	(function($) {
		$.fn.extend({
			Scroll : function(opt, callback) {
				//参数初始化
				if (!opt)
					var opt = {};
				var _btnUp = $("#" + opt.up);//Shawphy:向上按钮
				var _btnDown = $("#" + opt.down);//Shawphy:向下按钮
				var timerID;
				var _this = this.eq(0).find("ul:first");
				var lineH = _this.find("li:first").height(), //获取行高
				line = opt.line ? parseInt(opt.line, 10) : parseInt(this
						.height()
						/ lineH, 10), //每次滚动的行数，默认为一屏，即父容器高度
				speed = opt.speed ? parseInt(opt.speed, 10) : 500; //卷动速度，数值越大，速度越慢（毫秒）
				timer = opt.timer //?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
				if (line == 0)
					line = 1;
				var upHeight = 0 - line * lineH;
				//滚动函数
				var scrollUp = function() {
					_btnUp.unbind("click", scrollUp); //Shawphy:取消向上按钮的函数绑定
					_this.animate({
						marginTop : upHeight
					}, speed, function() {
						for (i = 1; i <= line; i++) {
							_this.find("li:first").appendTo(_this);
						}
						_this.css({
							marginTop : 0
						});
						_btnUp.bind("click", scrollUp); //Shawphy:绑定向上按钮的点击事件
					});

				}
				//Shawphy:向下翻页函数
				var scrollDown = function() {
					_btnDown.unbind("click", scrollDown);
					for (i = 1; i <= line; i++) {
						_this.find("li:last").show().prependTo(_this);
					}
					_this.css({
						marginTop : upHeight
					});
					_this.animate({
						marginTop : 0
					}, speed, function() {
						_btnDown.bind("click", scrollDown);
					});
				}
				//Shawphy:自动播放
				var autoPlay = function() {
					if (timer)
						timerID = window.setInterval(scrollUp, timer);
				};
				var autoStop = function() {
					if (timer)
						window.clearInterval(timerID);
				};
				//鼠标事件绑定
				_this.hover(autoStop, autoPlay).mouseout();
				_btnUp.css("cursor", "pointer").click(scrollUp).hover(autoStop,
						autoPlay);//Shawphy:向上向下鼠标事件绑定
				_btnDown.css("cursor", "pointer").click(scrollDown).hover(
						autoStop, autoPlay);

			}
		})
	})(jQuery);

	$(document).ready(function() {
		$('#marquee6').kxbdSuperMarquee({
			isMarquee : true,
			isEqual : false,
			scrollDelay : 20,
			controlBtn : {
				up : '#goUM',
				down : '#goDM'
			},
			direction : 'up'
		});

	});
</script>
<div class="wrapper">
	<div class="top"></div>
	<div class="center-w">
		<div class="center">
			<div class="center-l fl">
				<div class="winners-title">
					<h2>
						<i><img width="28" height="28"
							src="${ctx }/images/Integral/title-icon.png" /></i>看看大家都中了什么？
					</h2>

				</div>
				<div id="marquee6" class="winners-c">
					<ul>
						<c:forEach items="${lotteryList }" var="item">
							<li><dl>
									<dt class="winners-name fl">${item.nickName }</dt>
									<dd class="prize fr">抽中${item.prizeName }</dd>
								</dl></li>
						</c:forEach>

					</ul>
				</div>
			</div>
			<div class="center-r fr">
				<div class="turntable">
					<div class="turntable_l fl">
						<div id="top-menu-wrapper" class="dn bgfix">
							<div id="top-menu">
								<div class="l">
									<span>【系统自动扣除积分】</span>
								</div>
							</div>
						</div>
						<div id="header">
							<div id="turnplatewrapper" onselectstart="return false;"
								class="bgfix">
								<div id="turnplate" class="bgfix">
									<div id="awards" class="bgfix"></div>
									<div id="platebtn" class="bgfix"></div>
									<p id="msg"></p>
									<p id="init" class="dn">
										初始化中，请稍后<span></span>
									</p>
								</div>
							</div>
							<div id="gift" class="bgfix"></div>
						</div>
						<div id="content"></div>
						<div id="lotteryMsg" class="dn">
							<p class="msg"></p>
							<hr class="sp" />
							<div class="content mv5">
								恭喜你抽中<span class="option">iphone5s土豪金1部</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="center-btm">
			<div class="center-btm-l fl">
				<div class="share-title">
					<h2>
						<i class="share-icons"><img width="32" height="32"
							src="${ctx }/images/Integral/share-icon.png" /></i>分享
					</h2>
				</div>
				<div class="share-c" style="margin-top: 10px;margin-left: 28px;">
					<div class="bdsharebuttonbox">
						<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a
							href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a
							href="#" class="bds_tieba" data-cmd="tieba" title="分享到百度贴吧"></a><a
							href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a
							href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a
							href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
					</div>
					<script>
						window._bd_share_config = {
							"common" : {
								"bdSnsKey" : {},
								"bdText" : "蛙宝积分抽奖转起来,Iphone6 Plus、Iphone6等你来拿",
								"bdMini" : "1",
								"bdMiniList" : false,
								"bdPic" : "",
								"bdStyle" : "0",
								"bdSize" : "32"
							},
							"share" : {},
							"image" : {
								"viewList" : [ "tsina", "weixin", "tieba",
										"qzone", "tqq", "sqq" ],
								"viewText" : "分享到：",
								"viewSize" : "32"
							},
							"selectShare" : {
								"bdContainerClass" : null,
								"bdSelectMiniList" : [ "tsina", "weixin",
										"tieba", "qzone", "tqq", "sqq" ]
							}
						};
						with (document)
							0[(getElementsByTagName('head')[0] || body)
									.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
									+ ~(-new Date() / 36e5)];
					</script>

				</div>

				<div class="center-btm-r fr">- 本活动最终解释权归蛙宝网所有 -</div>
			</div>
		</div>
	</div>
	<script>
		//新登录
		$('#new-login').dialog({
			modal : true,
			width : 400,
			heigth : 220,
			resizable : false,
			autoOpen : false,
			title : '请先登录'
		});
	</script>
	<script>
		var isLogin = false;
	</script>
	<script>
		var turnplate = {
			turnplateBox : $('#turnplate'),
			turnplateBtn : $('#platebtn'),
			lightDom : $('#turnplatewrapper'),

			msgBox : $('#msg'),

			height : '592', //帧高度
			lightSwitch : 0, //闪灯切换开关. 0 和 1间切换
			minResistance : 5, //基本阻力
			Cx : 0.01, //阻力系数 阻力公式：  totalResistance = minResistance + curSpeed * Cx;	
			accSpeed : 15, //加速度
			accFrameLen : 40, //加速度持续帧数
			maxSpeed : 250, //最大速度 
			minSpeed : 20, //最小速度 
			frameLen : 10, //帧总数
			totalFrame : 0, //累计帧数,每切换一帧次数加1
			curFrame : 0, //当前帧
			curSpeed : 20, //上帧的速度
			lotteryTime : 1, //抽奖次数
			lotteryIndex : 1, //奖品index
			errorIndex : 6, //异常时的奖品指针
			initBoxEle : $('#turnplate #init'),
			progressEle : $('#turnplate #init span'),
			initProgressContent : '~~~^_^~~~', //初始化进度条的内容
			initFreshInterval : 500, //进度条刷新间隔
			virtualDistance : 10000, //虚拟路程,固定值，速度越快，切换到下帧的时间越快: 切换到下帧的时间 = virtualDistance/curSpeed
			isStop : false, //抽奖锁,为true时，才允许下一轮抽奖
			timer2 : undefined, //计时器
			initTime : undefined,
			showMsgTime : 2000, //消息显示时间
			lotteryChannel : '',
			myStop : false,
			channelList : {
				'login' : '登录',
				'consume' : '啊哦'
			},

			lotteryType : {
				'会员六个月' : 0,
				'会员三个月' : 1,
				'会员一个月' : 2,
				'三星电视' : 3,
				'iphone6' : 4,
				'iphone6 plus' : 5,
				'empty' : 6,
				'100纳币' : 7,
				'10纳币' : 8,
				'1纳币' : 9
			},

			lotteryList : [ '蛙宝会员六个月', '蛙宝会员三个月', '蛙宝会员一个月', '三星液晶电视',
					'苹果iphone6 16G', '苹果iphone6 plus 16G', '再接再厉', '蛙宝网100纳币',
					'蛙宝网10纳币', '蛙宝网1纳币', ],

			lotteryDes : [ '手气不错呢，幸运指数4颗星！', '手气不错呢，幸运指数3颗星！',
					'手气不错呢，幸运指数3颗星！', '手气太好了，幸运指数5颗星！', '手气太好了，幸运指数5颗星！',
					'手气太好了，幸运指数5颗星！', '手气太差了，幸运指数0颗星！', '手气还可以，幸运指数4颗星！',
					'手气还可以，幸运指数3颗星！', '手气还可以，幸运指数2颗星！' ],
			init : function() {
				this.initAnimate();
				this.freshLottery();
				this.turnplateBtn.click($.proxy(function() {
					this.click();
				}, this));
			},
			initAnimate : function() {
				this.initBoxEle.show();
				clearTimeout(this.initTimer);
				var curLength = this.progressEle.text().length, totalLength = this.initProgressContent.length;

				if (curLength < totalLength) {
					this.progressEle.text(this.initProgressContent.slice(0,
							curLength + 1));
				} else {
					this.progressEle.text('');
				}
				this.initTimer = setTimeout($.proxy(this.initAnimate, this),
						this.initFreshInterval);
			},
			stopInitAnimate : function() {
				clearTimeout(this.initTimer);
				this.initBoxEle.hide();
			},
			freshLotteryTime : function() {
				$('#top-menu').find('.lottery-times').html(this.lotteryTime);
			},
			freshLottery : function() {
				this.stopInitAnimate();
				this.setBtnHover();
				this.isStop = true;
				this.lotteryTime = 1;
				this.freshLotteryTime();

			},
			setBtnHover : function() {
				$('#platebtn').hover(function() {
					$(this).addClass('hover');
				}, function() {
					$(this).removeClass('hover click');
				}).mousedown(function() {
					$(this).addClass('click');
				}).mouseup(function() {
					$(this).removeClass('click');
				});
			},
			lottery : function() {
				if (this.lotteryTime > 0) {
					this.lotteryTime--;
				}

				this.freshLotteryTime();
				this.totalFrame = 0;
				this.curSpeed = this.minSpeed;
				this.turnAround();
				this.lotteryIndex = typeof this.lotteryIndex !== 'undefined' ? this.lotteryIndex
						: this.errorIndex;
				this.lotteryChannel = typeof this.channelList[1] !== 'undefined' ? this.channelList[1]
						: '';
			},
			click : function() {
				if (this.isStop == false) {
					this.showMsg('现在还不能抽奖哦');
					return;
				}
				$.ajax({
					type : "GET",
					async : true,
					cache : false,
					dataType : "json",
					url : "${ctx}/doIntegralLottery.html?time" + new Date(),
					success : function(result) {

						if (result == -1) {
							layer.msg('您还没有登陆，请先登录', 1, 10, function test() {
								window.location.href = "${ctx}/login.html";
							});

							return;
						} else if (result == -4) {
							layer.msg('您的积分不足');
						} else if (result == -3) {
							alert("请先完善您的头像哦！中奖后要拉出去溜溜的");
							window.location.href = "${ctx}/user/2/manager";
						} else if (result == -5) {
							alert("很抱歉，抽奖活动还未开始");
						} else if (result == -6) {
							alert("很抱歉，抽奖活动已结束");
						} else if (result == -4) {
							alert("很抱歉，您现在不能参加抽奖");
						} else {
							turnplate.lotteryIndex = result;
							turnplate.lottery();
						}
					}
				});
			},
			freshSpeed : function() {
				var totalResistance = this.minResistance + this.curSpeed
						* this.Cx;
				var accSpeed = this.accSpeed;
				var curSpeed = this.curSpeed;
				if (this.totalFrame >= this.accFrameLen) {
					accSpeed = 0;
				}
				curSpeed = curSpeed - totalResistance + accSpeed;

				if (curSpeed < this.minSpeed) {
					curSpeed = this.minSpeed;
				} else if (curSpeed > this.maxSpeed) {
					curSpeed = this.maxSpeed;
				}

				this.curSpeed = curSpeed;
			},
			switchLight : function() {
				this.lightSwitch = this.lightSwitch === 0 ? 1 : 0;
				var lightHeight = -this.lightSwitch * this.height;
				this.lightDom.css('backgroundPosition', '0 '
						+ lightHeight.toString() + 'px');
			},
			turnAround : function() {
				this.isStop = false;
				var intervalTime = parseInt(this.virtualDistance
						/ this.curSpeed);
				this.timer = window.setTimeout('turnplate.changeNext()',
						intervalTime);
			},
			showAwards : function() {
				$('#lotteryMsg').dialog('open');
			},
			showMsg : function(msg, isFresh) {
				isFresh = typeof isFresh == 'undefined' ? false : true;
				if (typeof msg != 'string') {
					msg = '今天已经抽过奖了，明天再来吧';
				}
				var msgBox = this.msgBox;
				var display = msgBox.css('display');

				msgBox.html(msg);

				window.clearTimeout(this.timer2);
				msgBox.stop(true, true).show();
				var fadeOut = $.proxy(function() {
					this.msgBox.fadeOut('slow');
				}, this);
				this.timer2 = window.setTimeout(fadeOut, this.showMsgTime);
			},
			changeNext : function() {
				//判断是否应该停止
				if (this.lotteryIndex !== undefined
						&& this.curFrame == this.lotteryIndex
						&& (this.curSpeed <= this.minSpeed + 10)
						&& (this.totalFrame > this.accFrameLen)) {
					this.isStop = true;
					this.showAwards();
					return;
				}

				var nextFrame = this.curFrame + 1 < this.frameLen ? this.curFrame + 1
						: 0;
				var bgTop = -nextFrame * this.height;
				this.turnplateBox.css('backgroundPosition', '0 '
						+ bgTop.toString() + 'px');
				this.switchLight();
				this.curFrame = nextFrame;
				this.totalFrame++;
				this.freshSpeed();
				this.turnAround();
			}
		}
	</script>
	<script>
		$('#lotteryMsg')
				.dialog(
						{
							modal : true,
							width : 500,
							height : 350,
							resizable : false,
							title : '获奖信息',
							autoOpen : false,
							open : function() {
								var showMsg = '您抽中了:'
										+ turnplate.lotteryList[turnplate.lotteryIndex];
								var options = turnplate.lotteryList[turnplate.lotteryIndex]
										+ ','
										+ turnplate.lotteryDes[turnplate.lotteryIndex];
								$('#lotteryMsg').find('.msg').html(showMsg);
								$('#lotteryMsg').find('.option').html(options);
							}
						});

		turnplate.init();
	</script>