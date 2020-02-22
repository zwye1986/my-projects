//开服列表滚动插件
(function(g) {
	g.fn.carousel = function(q) {
		var q = g.extend({
			direction : "horizontal",
			loop : false,
			dispItems : 1,
			pagination : false,
			paginationStyle : "mouseenter",
			paginationPosition : "inside",
			nextBtn : '<input type="button" value="" />',
			prevBtn : '<input type="button" value="" />',
			btnsPosition : "inside",
			nextBtnInsert : "insertAfter",
			prevBtnInsert : "insertBefore",
			nextBtnInsertFn : false,
			prevBtnInsertFn : false,
			autoSlide : false,
			autoSlideInterval : 3000,
			delayAutoSlide : false,
			combinedClasses : false,
			effect : "slide",
			slideEasing : "swing",
			animSpeed : 300,
			equalWidths : "true",
			verticalMargin : 0,
			callback : function() {
			},
			useAddress : false,
			adressIdentifier : "carousel",
			tabLabel : function(r) {
				return r;
			},
			showEmptyItems : true,
			ajaxMode : false,
			ajaxUrl : "",
			stopSlideBtn : false,
			stopSlideTextPause : "Pause",
			stopSlideTextPlay : "Play"
		}, q);
		if (q.btnsPosition == "outside") {
			q.prevBtnInsert = "insertBefore";
			q.nextBtnInsert = "insertAfter";
		}
		;
		q.delayAutoSlide = 0 + q.delayAutoSlide;
		return this
				.each(function() {
					var r = {
						$elts : {},
						params : q,
						launchOnLoad : []
					};
					r.$elts.carousel = g(this).addClass("js");
					r.$elts.content = g(this).children().css({
						position : "absolute",
						top : 0
					});
					r.$elts.wrap = r.$elts.content.wrap(
							'<div class="carousel-wrap"></div>').parent().css({
						overflow : "hidden",
						position : "relative"
					});
					r.steps = {
						first : 0,
						count : r.$elts.content.children().length
					};
					r.$elts.loader = g('<div class="loader"></div>').css({
						position : "absolute"
					});
					r.steps.last = r.steps.count - 1;
					if (r.params.pagination) {
						o(r);
					}
					;
					if (g.isFunction(r.params.prevBtnInsertFn)) {
						r.$elts.prevBtn = r.params.prevBtnInsertFn(r.$elts);
					} else {
						if (q.btnsPosition == "outside") {
							r.$elts.prevBtn = g(q.prevBtn)[q.prevBtnInsert]
									(r.$elts.carousel);
						} else {
							r.$elts.prevBtn = g(q.prevBtn)[q.prevBtnInsert]
									(r.$elts.wrap);
						}
					}
					;
					if (g.isFunction(r.params.nextBtnInsertFn)) {
						r.$elts.nextBtn = r.params.nextBtnInsertFn(r.$elts);
					} else {
						if (q.btnsPosition == "outside") {
							r.$elts.nextBtn = g(q.nextBtn)[q.nextBtnInsert]
									(r.$elts.carousel);
						} else {
							r.$elts.nextBtn = g(q.nextBtn)[q.nextBtnInsert]
									(r.$elts.wrap);
						}
					}
					;
					r.$elts.nextBtn
							.addClass("carousel-control next carousel-next");
					r.$elts.prevBtn
							.addClass("carousel-control previous carousel-previous");
					r.lastItemsToLoad;
					d(r);
					r.$elts.carousel.attr("tabindex", 0).add(
							r.$elts.carousel.children()).bind({
						focus : function(s) {
							g(document).bind("keypress", function(t) {
								switch (t.keyCode) {
								case 39:
									r.$elts.nextBtn.click();
									break;
								case 37:
									r.$elts.prevBtn.click();
									break;
								}
								;
								switch (t.charCode) {
								case 110:
									r.$elts.nextBtn.click();
									break;
								case 112:
									r.$elts.prevBtn.click();
									break;
								}
							});
						},
						blur : function() {
							g(document).unbind("keypress");
						}
					});
					n(r);
					g(function() {
						c(r);
						g.each(r.launchOnLoad, function(s, t) {
							t();
						});
						if (r.params.autoSlide) {
							f(r);
						}
						;
						if (q.stopSlideBtn == true) {
							r.$elts.stopSlideBtn = g('<button type="button" class="slide-control play">'
									+ q.stopSlideTextPause + "</button>");
							a(r);
						}
					});
				});
	};
	function c(s) {
		var t = s.$elts.content.children();
		var r = 0;
		t.each(function() {
			$item = g(this);
			$itemHeight = $item.outerHeight();
			if ($itemHeight > r) {
				r = $itemHeight;
			}
		});
		if (s.params.verticalMargin > 0) {
			r = r + s.params.verticalMargin;
		}
		;
		t.height(r);
		var q = s.$elts.content.children(":first");
		s.itemWidth = q.outerWidth();
		if (s.params.direction == "vertical") {
			s.contentWidth = s.itemWidth;
		} else {
			if (s.params.equalWidths) {
				s.contentWidth = s.itemWidth * s.steps.count;
			} else {
				s.contentWidth = (function() {
					var u = 0;
					s.$elts.content.children().each(function() {
						u += g(this).outerWidth();
					});
					return u;
				})();
			}
		}
		;
		s.$elts.content.width(s.contentWidth);
		s.itemHeight = r;
		if (s.params.direction == "vertical") {
			s.$elts.content.css({
				height : s.itemHeight * s.steps.count + "px"
			});
			s.$elts.content.parent().css({
				height : s.itemHeight * s.params.dispItems + "px"
			});
		} else {
			s.$elts.content.parent().css({
				height : s.itemHeight + "px"
			});
		}
		;
		i(s);
	}
	;
	function d(q) {
		q.$elts.nextBtn.add(q.$elts.prevBtn).bind(
				"enable",
				function() {
					var r = g(this).unbind("click").bind(
							"click",
							function() {
								if (q.params.ajaxMode && r.is(".next")
										&& b(q) == (p(q) - 1)
										&& !q.lastItemsToLoad) {
									h(q);
									q.$elts.content.ajaxSuccess(function() {
									});
								} else {
									e(q,
											j(q, (r.is(".next") ? "next"
													: "prev")));
									if (q.params.stopSlideBtn == true) {
										q.$elts.stopSlideBtn.trigger("pause");
									} else {
										m(q);
									}
								}
							}).removeClass("disabled").removeAttr("disabled");
					if (q.params.combinedClasses) {
						r.removeClass("next-disabled previous-disabled")
								.removeAttr("disabled");
					}
				}).bind(
				"disable",
				function() {
					var r = g(this).unbind("click").addClass("disabled").attr(
							"disabled", "disabled");
					if (q.params.combinedClasses) {
						if (r.is(".next")) {
							r.addClass("next-disabled");
						} else {
							if (r.is(".previous")) {
								r.addClass("previous-disabled");
							}
						}
					}
				}).hover(function() {
			g(this).toggleClass("hover");
		});
	}
	;
	function o(q) {
		q.$elts.pagination = g('<div class="center-wrap"><div class="carousel-pagination"><p></p></div></div>')[((q.params.paginationPosition == "outside") ? "insertAfter"
				: "appendTo")](q.$elts.carousel).find("p");
		q.$elts.paginationBtns = g([]);
		q.$elts.content.find("ul").each(function(r) {
			if (r % q.params.dispItems == 0) {
				k(q, r);
			}
		});
	}
	;
	function k(r, q) {
		if (r.params.pagination) {
			r.$elts.paginationBtns = r.$elts.paginationBtns
					.add(
							g(
									'<a role="button"><span>'
											+ r.params
													.tabLabel(r.$elts.paginationBtns.length + 1)
											+ "</span></a>").data("firstStep",
									q)).appendTo(r.$elts.pagination);
			r.$elts.paginationBtns.slice(0, 1).addClass("active");
			if (r.params.paginationStyle == "mouseenter") {
				r.$elts.paginationBtns.mouseenter(function(s) {
					e(r, g(this).data("firstStep"));
					if (r.params.stopSlideBtn == true) {
						r.$elts.stopSlideBtn.trigger("pause");
					} else {
						m(r);
					}
				});
			} else if (r.params.paginationStyle == "click") {
				r.$elts.paginationBtns.click(function(s) {
					e(r, g(this).data("firstStep"));
					if (r.params.stopSlideBtn == true) {
						r.$elts.stopSlideBtn.trigger("pause");
					} else {
						m(r);
					}
				});
			}
			;
		}
	}
	;
	function n(q) {
		if (q.params.useAddress && g.isFunction(g.fn.address)) {
			g.address.init(function(s) {
				var r = g.address.pathNames();
				if (r[0] === q.params.adressIdentifier && !!r[1]) {
					e(q, r[1] - 1);
				} else {
					g.address.value("/" + q.params.adressIdentifier + "/1");
				}
			}).change(function(s) {
				var r = g.address.pathNames();
				if (r[0] === q.params.adressIdentifier && !!r[1]) {
					e(q, r[1] - 1);
				}
			});
		} else {
			q.params.useAddress = false;
		}
	}
	;
	function e(q, r) {
		q.params.callback(r);
		l(q, r);
		q.steps.first = r;
		i(q);
		if (q.params.useAddress) {
			g.address.value("/" + q.params.adressIdentifier + "/" + (r + 1));
		}
	}
	;
	function j(r, q) {
		if (q == "prev") {
			if (!r.params.showEmptyItems) {
				if (r.steps.first == 0) {
					return ((r.params.loop) ? (r.steps.count - r.params.dispItems)
							: false);
				} else {
					return Math.max(0, r.steps.first - r.params.dispItems);
				}
			} else {
				if ((r.steps.first - r.params.dispItems) >= 0) {
					return r.steps.first - r.params.dispItems;
				} else {
					return ((r.params.loop) ? (r.steps.count - r.params.dispItems)
							: false);
				}
			}
		} else {
			if (q == "next") {
				if ((r.steps.first + r.params.dispItems) < r.steps.count) {
					if (!r.params.showEmptyItems) {
						return Math.min(r.steps.first + r.params.dispItems,
								r.steps.count - r.params.dispItems);
					} else {
						return r.steps.first + r.params.dispItems;
					}
				} else {
					return ((r.params.loop) ? 0 : false);
				}
			}
		}
	}
	;
	function l(q, r) {
		switch (q.params.effect) {
		case "no":
			if (q.params.direction == "vertical") {
				q.$elts.content.css("top", -(q.itemHeight * r) + "px");
			} else {
				q.$elts.content.css("left", -(q.itemWidth * r) + "px");
			}
			;
			break;
		case "fade":
			if (q.params.direction == "vertical") {
				q.$elts.content.hide().css("top", -(q.itemHeight * r) + "px")
						.fadeIn(q.params.animSpeed);
			} else {
				q.$elts.content.hide().css("left", -(q.itemWidth * r) + "px")
						.fadeIn(q.params.animSpeed);
			}
			;
			break;
		default:
			if (q.params.direction == "vertical") {
				q.$elts.content.stop().animate({
					top : -(q.itemHeight * r) + "px"
				}, q.params.animSpeed, q.params.slideEasing);
			} else {
				q.$elts.content.stop().animate({
					left : -(q.itemWidth * r) + "px"
				}, q.params.animSpeed, q.params.slideEasing);
			}
			;
			break;
		}
	}
	;
	function i(q) {
		if (j(q, "prev") !== false) {
			q.$elts.prevBtn.trigger("enable");
		} else {
			q.$elts.prevBtn.trigger("disable");
		}
		;
		if (j(q, "next") !== false) {
			q.$elts.nextBtn.trigger("enable");
		} else {
			q.$elts.nextBtn.trigger("disable");
		}
		;
		if (q.params.pagination) {
			q.$elts.paginationBtns.removeClass("active").filter(function() {
				return (g(this).data("firstStep") == q.steps.first);
			}).addClass("active");
		}
	}
	;
	function f(q) {
		q.delayAutoSlide = window.setTimeout(function() {
			q.autoSlideInterval = window.setInterval(function() {
				e(q, j(q, "next"));
			}, q.params.autoSlideInterval);
		}, q.params.delayAutoSlide);
	}
	;
	function m(q) {
		window.clearTimeout(q.delayAutoSlide);
		window.clearInterval(q.autoSlideInterval);
		q.params.delayAutoSlide = 0;
	}
	;
	function a(r) {
		var q = r.$elts.stopSlideBtn;
		q.bind({
			play : function() {
				f(r);
				q.removeClass("pause").addClass("play").html(
						r.params.stopSlideTextPause);
			},
			pause : function() {
				m(r);
				q.removeClass("play").addClass("pause").html(
						r.params.stopSlideTextPlay);
			}
		});
		q.click(function(s) {
			if (q.is(".play")) {
				q.trigger("pause");
			} else {
				if (q.is(".pause")) {
					q.trigger("play");
				}
			}
		});
		q.prependTo(r.$elts.wrap);
	}
	;
	function p(q) {
		return q.$elts.pagination.children().length;
	}
	;
	function b(q) {
		return q.steps.first / q.params.dispItems;
	}
	;
	function h(q) {
		q.$elts.carousel.prepend(q.$elts.loader);
		g.ajax({
			url : q.params.ajaxUrl,
			dataType : "json",
			success : function(r) {
				q.lastItemsToLoad = r.bLastItemsToLoad;
				g(q.$elts.content).append(r.shtml);
				q.steps = {
					first : q.steps.first + q.params.dispItems,
					count : q.$elts.content.children().length
				};
				q.steps.last = q.steps.count - 1;
				c(q);
				k(q, q.steps.first);
				e(q, q.steps.first);
				if (q.params.stopSlideBtn == true) {
					q.$elts.stopSlideBtn.trigger("pause");
				} else {
					m(q);
				}
				;
				q.$elts.loader.remove();
			}
		});
	}
})(jQuery);
// 幻灯片插件
;
(function($) {
	$.fn
			.extend({
				XYcarousel : function(params) {
					var p = $.extend({
						prev : "#buttonPrev",
						next : "#buttonNext",
						frame : this.children(),
						child : "",
						direction : "horizontal",
						width : "",
						height : "",
						auto : true,
						autoTime : 3000,
						animateTime : "normal"
					}, params);
					var _btnNext = $(p.next), _btnPrev = $(p.prev), _imgFrame = $(p.frame), _child = p.child == "" ? _imgFrame
							.children().get(0).tagName
							: p.child, _width = p.width == "" ? _imgFrame
							.children(_child).width() : p.width, _height = p.height == "" ? _imgFrame
							.children(_child).height()
							: p.height, _distance = p.direction != "vertical" ? _width
							: _height, _direction = p.direction != "vertical" ? "marginLeft"
							: "marginTop", _css = p.direction != "vertical" ? "margin-left"
							: "margin-top", _animate = {}, _direction, _auto = p.auto, _click = true, _timeout, _itv;
					_btnPrev.click(function() {
						if (_click == true) {
							_click = false;
							if (_auto) {
								autoStop();
							}
							;
							_animate[_direction] = 0;
							_imgFrame.find(_child + ":last").prependTo(
									_imgFrame);
							_imgFrame.css(_css, -_distance);
							_imgFrame.stop().animate(_animate, p.animateTime,
									function() {
										_click = true;
										if (_auto) {
											autoPlay();
										}
										;
									});
						}
						;
					});
					_btnNext.click(function() {
						if (_click == true) {
							_click = false;
							if (_auto) {
								autoStop();
							}
							;
							_animate[_direction] = -_distance;
							_imgFrame.stop().animate(
									_animate,
									p.animateTime,
									function() {
										_imgFrame.find(_child + ":first")
												.appendTo(_imgFrame);
										_imgFrame.css(_css, 0);
										_click = true;
										if (_auto) {
											autoPlay();
										}
										;
									});
						}
						;
					});
					var autoPlay = function() {
						_itv = window.setInterval(function() {
							_btnNext.click();
						}, p.autoTime);
					};
					var autoStop = function() {
						window.clearInterval(_itv);
					};
					if (_auto) {
						autoPlay();
						_imgFrame.hover(function() {
							if (_imgFrame.is(":animated")) {
								_timeout = setTimeout(function() {
									_click = false;
								}, p.animateTime);
							} else {
								_click = false;
							}
							;
						}, function() {
							clearTimeout(_timeout);
							_click = true;
						});
					}
					;
				}
			});
})(jQuery);
/*---------------------------------------------------------------------------------------------------*/
var my_url = 'http://my.51wan.com/';
var user_url = 'http://user.51wan.com/';
// 随机背投广告
var imgUrl = [ "http://pic.51wan.com/web/v1/index/images/spec/12/jjsg0425.jpg",
		"http://pic.51wan.com/web/v1/index/images/spec/10/5030422.jpg" ];
var linkHref = [ "http://user.51wan.com/login.html?gamename=jjsg&link=bt1",
		"http://user.51wan.com/login.html?gamename=503&link=bt1" ];
var randomInfo = parseInt(Math.random() * imgUrl.length);
// $(".wrap").css("background-image", "url(" + imgUrl[randomInfo] + ")");
$("#screen_ad").attr("href", linkHref[randomInfo]);
$("#header_ad").attr("href", linkHref[randomInfo]);
// 左侧游戏子导航
$("#subnav_list li")
		.hover(
				function() {
					var hoverobject = $(this).children(".subnav_hover"), hoverimg = hoverobject
							.children("img"), hoverloadsrc = hoverimg
							.attr("loadsrc"), hoversrc = hoverimg.attr("src");
					hoverobject.show();
					if (hoversrc == "http://pic.51wan.com/web/v1/index/images/transparent.png") {
						hoverimg.attr("src", hoverloadsrc);
					}
					;
				}, function() {
					$(this).children(".subnav_hover").hide();
				});
// 首屏焦点banner鼠标经过效果
var timeHover;
$(".banner_three")
		.hover(
				function() {
					var $this = $(this);
					var gameMin = $this.attr("gamemin");
					var hasserver = $this.find(".banner_go").html();
					var banner_server_a = $this.find(".banner_server:eq(0)");
					var banner_server_b = $this.find(".banner_server:eq(1)");
					timeHover = setTimeout(
							function() {
								if (hasserver == undefined) {
									// 获取最新最热服务器
									$
											.getJSON(
													my_url
															+ "interface/gameAPI/newServer.php?update=1&game="
															+ gameMin
															+ '&jsoncallback=?',
													function(json) {
														var jsonArray = [];
														for ( var i = 0; i < json.length; i++) {
															jsonArray[i] = [
																	json[i].id,
																	json[i].name,
																	json[i].time,
																	json[i].num ];
														}
														;
														if (json.length == 2) {
															banner_server_a
																	.html(
																			jsonArray[0][1]
																					+ '<span class="banner_go"></span>')
																	.attr(
																			{
																				"href" : 'http://user.51wan.com/reg.html?gamename='
																						+ gameMin
																						+ '&serverid='
																						+ jsonArray[0][0],
																				"target" : "_blank"
																			});
															banner_server_b
																	.html(
																			jsonArray[1][1]
																					+ '<span class="banner_go"></span>')
																	.attr(
																			{
																				"href" : 'http://user.51wan.com/reg.html?gamename='
																						+ gameMin
																						+ '&serverid='
																						+ jsonArray[1][0],
																				"target" : "_blank"
																			});
														} else if (json.length == 1) {
															banner_server_a
																	.html(
																			jsonArray[0][1]
																					+ '<span class="banner_go"></span>')
																	.attr(
																			{
																				"href" : 'http://user.51wan.com/reg.html?gamename='
																						+ gameMin
																						+ '&serverid='
																						+ jsonArray[0][0],
																				"target" : "_blank"
																			});
															banner_server_b
																	.html(
																			jsonArray[0][1]
																					+ '<span class="banner_go"></span>')
																	.attr(
																			{
																				"href" : 'http://user.51wan.com/reg.html?gamename='
																						+ gameMin
																						+ '&serverid='
																						+ jsonArray[0][0],
																				"target" : "_blank"
																			});
														} else {
															banner_server_a
																	.html(
																			"暂无区服")
																	.css(
																			"cursor",
																			"default");
															banner_server_b
																	.html(
																			"暂无区服")
																	.css(
																			"cursor",
																			"default");
														}
														;
													});
								}
								;
							}, 300);
					$this.children(".banner_mask,.banner_hover").show();
				}, function() {
					clearTimeout(timeHover);
					$(this).children(".banner_mask,.banner_hover").hide();
				});
// 首屏焦点banner滚动效果
if ($("#banner_wrap ul").length > 1) {
	$("#banner_wrap").XYcarousel({
		prev : "#banner_prev",
		next : "#banner_next",
		autoTime : 4000,
		animateTime : 600
	});
};
// 今日活动礼包滚动效果
if ($("#giftbag_wrap li").length > 1) {
	$("#giftbag_prev").addClass("giftbag_prev").show();
	$("#giftbag_next").addClass("giftbag_next").show();
	$("#giftbag_wrap").XYcarousel({
		prev : "#giftbag_prev",
		next : "#giftbag_next",
		auto : false
	});
} else {
	$("#giftbag_prev").addClass("giftbag_prev_no");
	$("#giftbag_next").addClass("giftbag_next_no");
};
// 大家都在干什么随机调取4条
var dolen = $("#doit_bd li").length < 50 ? $("#doit_bd li").length : 50;// 极限50
var arraylen;
var doarray = [];
for ( var i = 0; i < dolen; i++) { // 生成li数组
	doarray[i] = i;
};
arraylen = doarray.length;
for ( var i = arraylen; i > 4; i--) { // 随机选取4个
	var randomVal = parseInt(Math.random() * doarray.length);
	doarray.splice(randomVal, 1);
};
doarray.sort(function() { // 打乱顺序
	return 0.5 - Math.random();
});
for ( var i = 0; i < 4; i++) { // 显示数据
	$("#doit_bd li:eq(" + doarray[i] + ")").clone().appendTo($("#doit_bd"))
			.show();
};
$("#doit_bd li:hidden").remove(); // 删除多余元素
// 全部游戏数量
$(".allgames_box_category").each(
		function(n, v) {
			if (n == 0) {
				return;
			}
			;
			var $this = $(this), gameCount = $this.siblings(
					".allgames_box_name").find("li").length;
			$this.children("span").html("(" + gameCount + ")");
		});
// 全部游戏鼠标经过效果
var timeHoverAll;
$("#allgames_bd li")
		.hover(
				function() {
					var $this = $(this), gameMin = $this.attr("gamemin"), hasserver = $this
							.find(".allgames_hover_server a").html(), hoverobject = $this
							.children(".allgames_hover"), hoverimg = hoverobject
							.find("img"), hoverloadsrc = hoverimg
							.attr("loadsrc"), hoversrc = hoverimg.attr("src");
					if (hoversrc == "http://pic.51wan.com/web/v1/index/images/transparent.png") {
						hoverimg.attr("src", hoverloadsrc);
					}
					;
					timeHoverAll = setTimeout(
							function() {
								if (hasserver == undefined) {
									// 获取最新最热服务器
									$
											.getJSON(
													my_url
															+ "interface/gameAPI/newServer.php?update=1&game="
															+ gameMin
															+ '&jsoncallback=?',
													function(json) {
														var jsonArray = [];
														var data = [];
														for ( var i = 0; i < json.length; i++) {
															jsonArray[i] = [
																	json[i].id,
																	json[i].name,
																	json[i].time,
																	json[i].num ];
														}
														;
														data = jsonArray[0][2]
																.split("-");
														$this
																.find(
																		".allgames_hover_server")
																.html(
																		'<strong>'
																				+ data[1]
																				+ '月'
																				+ data[2]
																				+ '日</strong><a href="http://user.51wan.com/reg.html?gamename='
																				+ gameMin
																				+ '&serverid='
																				+ jsonArray[0][0]
																				+ '" target="_blank">'
																				+ jsonArray[0][1]
																				+ '<span></span></a>');
													});
								}
								;
							}, 500);
					$("#allgames_bd li").css("z-index", "20");
					$(this).css("z-index", "21").children(".allgames_hover")
							.show();
				}, function() {
					clearTimeout(timeHoverAll);
					$(this).children(".allgames_hover").hide();
				});
// 开服切换效果
hoverSwitch("click", "#serveropen .serveropen_hd li", "serveropen_now",
		[ "#serveropen .serveropen_bd>div" ]);
// 攻略切换效果
hoverSwitch("", "#notice .notice_hd li", "curr",
		[ "#notice .notice_bd>.notice_list" ]);
// 获取开服信息
$
		.getJSON(
				my_url + 'interface/openserver.php?limit=20&jsoncallback=?',
				function(list) {
					$(".serveropen_wrap").css("background", "none");// 清除loading
					var yd = list.yesterday['date'].split('-'), td = list.today['date']
							.split('-'), tm = list.tomorrow['date'].split('-');
					$("#serveropen_hd li:eq(0) span").html(
							'（' + yd[1] + '月' + yd[2] + '日）');
					$("#serveropen_hd li:eq(1) span").html(
							'（' + td[1] + '月' + td[2] + '日）');
					$("#serveropen_hd li:eq(2) span").html(
							'（' + tm[1] + '月' + tm[2] + '日）');
					if (list.yesterday.total > 0) {
						serverInfo('yesterday', list.yesterday.info,
								list.yesterday.total);
					} else {
						$("#serveropen_yesterday .serveropen_wrap").html(
								'<p>无开服记录</p>');
					}
					;
					if (list.today.total > 0) {
						serverInfo('today', list.today.info, list.today.total);
					} else {
						$("#serveropen_today .serveropen_wrap").html(
								'<p>玩家大大，今天暂时未有新服哟！</p>');
					}
					;
					if (list.tomorrow.total > 0) {
						serverInfo('tomorrow', list.tomorrow.info,
								list.tomorrow.total);
					} else {
						$("#serveropen_tomorrow .serveropen_wrap").html(
								'<p>暂无开服消息，新服开启，马上通知。</p>');
					}
					;
				});
/*-------------------------------------------------------------------------------------------------------*/
// 写入开服信息
function serverInfo(day, data, total) {
	var server_count = parseInt(total);
	var server_html = '<ul class="fl">';
	var server_page = Math.ceil(server_count / 6);
	var i = 0;
	$
			.each(
					data,
					function(n, v) {
						var game = v.game || ''; // 游戏简写
						var gameName = v.gameName ? str_cut(
								decodeURI(v.gameName), 14) : ''; // 游戏名称（截取）
						var server = v.server || {}; // 开放服务器信息
						$
								.each(
										server,
										function(sn, sv) {
											i++;
											j = i % 6;
											var gift = v.hasGift == 1
													&& sv.power == 1 ? '<a target="_blank" href="'
													+ my_url
													+ 'app_giftnew_getXsk_0.html?game='
													+ game
													+ '" class="serveropen_gift fl"></a>'
													: '<em class="serveropen_gift_no fl"></em>'; // 礼包
											var serverNum = sv.serverNum != 0 ? sv.serverNum
													: "测试"; // 服务器数字名称
											var d = sv.openTime.split(' '); // 开服时间
											var t = d[1].split(':');
											var liHref = sv.power == 1 ? '<a class="serveropen_sv fl" href="'
													+ user_url
													+ 'reg.html?gamename='
													+ game
													+ '&serverid='
													+ sv.serverId
													+ '" target="_blank">'
													+ serverNum + '区</a>'
													: '<em class="serveropen_sv_no fl">'
															+ serverNum
															+ '区</em>';
											server_html += '<li class="fl"><span class="fl">'
													+ t[0]
													+ ':'
													+ t[1]
													+ '</span><a class="serveropen_name fl" href="http://'
													+ game
													+ '.51wan.com/" target="_blank">'
													+ gameName
													+ '</a>'
													+ liHref + gift + '</li>';
										});
						if (j == server_count) {
							server_html += '</ul>';
						} else if (j == 0 && j != server_count) {
							server_html += '</ul><ul class="fl">';
						}
						;
					});
	$("#serveropen_" + day + " .serveropen_box").html(server_html).show();// 写入服务器信息，并显示该层
	if (server_page > 1) {
		$("#serveropen_" + day + " .serveropen_wrap").carousel({
			pagination : true,
			paginationPosition : "outside",
			paginationStyle : "click",
			btnsPosition : "outside",
			combinedClasses : true
		});
		$(".carousel-previous").css("left", 240 - server_page * 20);// 调整左右按钮
		$(".carousel-next").css("right", 214 - server_page * 20);
		$(".serveropen_wrap .carousel-wrap").height(135);// 防止cnzz缓慢，提前写出父层高度
	}
	;
	$("#serveropen_" + day + " .serveropen_info span").html(server_count);
};
// 字符串截取
function str_cut(str, len) {
	var returnStr = '';
	for ( var i = 0, length = str.length; i < length; i++) {
		if (str.charCodeAt(i) > 255 || str.charCodeAt(i) < 0) {
			len = len - 2;
		} else {
			len--;
		}
		;
		if (len >= 0) {
			returnStr = returnStr + str.substr(i, 1);
		}
		;
	}
	;
	return returnStr;
};
// 鼠标滑过切换
function hoverSwitch(way, hoverThis, hoverClass, switchObject) {
	if (way == "click") {
		$(hoverThis).click(function() {
			switchNow($(this));
		});
	} else {
		$(hoverThis).mouseenter(function() {
			switchNow($(this));
		});
	}
	;
	function switchNow($this) {
		var NowLi = $this.index();
		$this.addClass(hoverClass).siblings().removeClass(hoverClass);
		for ( var i = 0; i < switchObject.length; i++) {
			$(switchObject[i] + ":eq(" + NowLi + ")").show().siblings().hide();
		}
		;
	}
	;
};