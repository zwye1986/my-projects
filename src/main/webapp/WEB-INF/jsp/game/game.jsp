<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${ctx }/style/game.css" rel="stylesheet" />
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/game.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript">
$(function(){
	$("#headMenu").find("li").click(function(){
		$("#"+this.id).removeClass("nav_now");
	});
	$("#_game").addClass("nav_now");
});


</script>
<!--content-->
<div id="content">
	<div class="contentL fl">
		<div class="contentLPic"><img src="${ctx }/images/game/contentLPic.png" alt="流程说明"/></div>
		<!--小游戏排行榜-->
		<div class="GRankC">
			<div class="GRankTop"></div>
			<div class="GRankTitle">小游戏排行榜</div>
			<dl class="chartsRecommend">
				<dt>
					<a href="${ctx }/${bigFlashRank.id}/showGameDetail.html"  target="_blank"> <img
						src="${ctx }/showRectangleGamePic?id=${bigFlashRank.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${bigFlashRank.gameName }"></a>
				</dt>
				<dd style="border-bottom: 1px dashed #ccc;">
					<strong>${bigFlashRank.gameName }</strong>
				</dd>
				<dd>
					<span class="label">返利：</span> <span class="rpg">${policy.reward
						}纳币</span>
				</dd>
				<dd>
					<span class="levy">${gamePlayCounts }人在玩</span>
				</dd>
			</dl>
			<ul class="GRankList">
				<c:forEach items="${flashRank }" var="item" varStatus="s">
					<c:if test="${s.index < 3 }">
						<li><span class="fl spanlist" style="background: #F16244;">${s.index+1
								}</span>
							<p class="fl">
								<a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank"
									title="${item.gameName }" style="color: #f16244;">${item.gameName
									}</a>
							</p> <em class="fr">></em></li>
					</c:if>
					<c:if test="${s.index >=3 && s.index < 11 }">
						<li><span class="fl spanlist">${s.index+1 }</span>
							<p class="fl">
								<a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank" 
									title="${item.gameName }">${item.gameName }</a>
							</p> <em class="fr">></em></li>
					</c:if>
				
				</c:forEach>

			</ul>
			 <div class="hotB">
				<a href="${ctx }/xgame?type=flash" target="_blank">更多>></a>
			</div>
		</div>
		
		

		<!--网页游戏排行榜-->

		<div class="subnav commbox fl">
			<div class="GRankTop"></div>
			<div class="commbox_hd clearfix">网页游戏排行</div>
			<ul class="commbox_bd" id="subnav_list">
				<c:forEach items="${webRank }" var="item" varStatus="s">
					<c:if test="${s.index<3 }">
						<li><span class="fl" style="background: #F16244;">${s.index+1
								}</span>
							<p class="fl" style="color: #f16244;">
								<a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank"
									title="${item.gameName }" style="color: #555;">${item.gameName
									}</a>
							</p> <em class="fr">&gt;</em>
							<div class="subnav_hover">
								<a
									class="subnav_home fl" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank" >${item.gameName }</a> <a
									class="subnav_go fl" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank">＊返利${item.reward }</a>
							</div></li>
					</c:if>
					<c:if test="${s.index >=3 && s.index < fn:length(webRank)-1 }">
						<li><span class="fl">${s.index+1 }</span>
							<p class="fl">
								<a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank"
									title="${item.gameName }" style="color: #555;">${item.gameName
									}</a>
							</p> <em class="fr">&gt;</em>
							<div class="subnav_hover">
								 <a
									class="subnav_home fl" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank" >${item.gameName }</a> <a
									class="subnav_go fl" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank">＊返利${item.reward }</a>
							</div></li>
					</c:if>
					
				</c:forEach>

			</ul>
			  <div class="hotB">
<a href="${ctx }/xgame?type=web" target="_blank">更多>></a>
</div>
		</div>
		
		
	</div>
	<!--右侧-->
	<div class="contentR fr">
		<!--玩游戏拿返利-->
		<div class="RTop">
			<ul class="listBox fl">
			<c:forEach items="${rewardList }" var="item">
				<li>
					<dl>
						<dt>
							<a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank" > <img width="115" height="87" alt="${item.gameName }"
								src="${ctx }/showSquareGamePic?id=${item.square}" onerror="this.src='${ctx }/images/game/square.png'"> <span class="h_v2">返利${item.reward }</span>
							</a>
						</dt>
						<dd>
							<a class="convert" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank">${item.gameName }</a>
						</dd>
					</dl>
				</li>
				</c:forEach>
				
			</ul>
		</div>
		<div class="RTitle"></div>
		<div class="RC">
			<div id="tabbed_box_1" class="tabbed_box">
				<div class="tabbed_area">
					<ul class="tabs doit_bd">
					<c:choose>
					<c:when test="${searchtype == 'search' }">
					<li><a href="###" title="搜索结果" mark="content_1" class="tab active">搜索结果</a></li>
					</c:when>
					<c:otherwise>
					<li><a href="javascript:void(0)" id="all" title="全部" mark="content_1" class="tab active">全部</a></li>
						<li><a href="javascript:void(0)" title="角色扮演"  id="roleplay" mark="content_2" class="tab">角色扮演</a></li>
						<li><a href="javascript:void(0)" title="战争策略"  id="warstrategy" mark="content_3" class="tab">战争策略</a></li>
						<li><a href="javascript:void(0)" title="休闲竞技"  id="leisuretournament" mark="content_4" class="tab">休闲竞技</a></li>
						<li><a href="javascript:void(0)" title="模拟经营"  id="businesssimulation" mark="content_5" class="tab">模拟经营</a></li>
						
					</c:otherwise>
					</c:choose>
						
					</ul>
					
					<form action="${ctx }/game" id="searchform" method="post" target="_self">
					<input type="hidden" name="searchtype" value="search">
					 <input type="hidden" name="sort" id="sort">
					 <input type="hidden" name="sortseq" id="sortseq">
					
					
					<div style="display: block;" id="content_1" class="content">
					
					<c:choose>
					<c:when test="${fn:length(gamelist) == 0 && searchtype == 'search'}">
					<div class="search_no">
					   <div class="search_no_re">
                <div class="search_no_t"><input  type="text" name="keyword" value="" class="search_txt"/><input type="button" onclick="searchSubmit();" value="" class="search_btn"/></div>
                <div class="search_no_l fl"></div>
                <div class="search_no_r fr">抱歉，没有找到"<em style="color:#f16244;"><c:out value="${keyword}" /></em>"的游戏!</div>
                </div>
              
                <div class="Rgame_T">我们推荐您尝试以下游戏</div>
                <ul class="game_playC" style="width:980px; border:none; margin-left:-20px;">
                <c:forEach items="${commendList }" var="item">
                 <li><a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank" > <img width="180" height="115" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${item.gameName }"> </a><em>${item.gameName }</em></li>
                </c:forEach>
                 
                  </ul>
                  <div class="search_more"><a href="${ctx }/game">查看更多游戏</a></div>
              </div>
					</c:when>
					<c:otherwise>
					 <div class="filter">
					
					 <dl class="order">
        <dt><em>默认排序：</em></dt>
        <dd class="fanli" id="dd_reward" onclick="openReward('down','all')">
        <a href="javascript:void(0)" >返利</a>
        </dd>
        <dd class="fanli" id="dd_clicks" onclick="openClicks('down','all')">
        <a href="javascript:void(0)">周期</a>
        </dd>
        <dd class="fanli" id="dd_hot" onclick="openHot('down','all')">
        <a href="javascript:void(0)">人气</a>
        </dd>
        <dd class="fanli" id="dd_deposit" onclick="openDeposit('down','all')">
        <a href="javascript:void(0)">押金</a>
        </dd>
        <dd class="zhou_q"></dd>
        <dd class="ren_q"></dd>
        <dd class="ya_j"></dd>
        </dl>
         <div class="qj_text"><input type="text" id="beginDeposit_all" class="txt"/>－<input type="text" id="endDeposit_all" class="txt"/></div>
					<div class="search_no_t"><input  type="text" name="keyword" value="" class="search_txt"/><input type="button" onclick="searchSubmit();" value="" class="search_btn"/></div>
					<div class="qj_btn"><input type="button" onclick="depositselect('all')" class="filter_btn"/></div>
					</div>
					 
					<ul>
							<c:forEach items="${gamelist }" var="item" varStatus="s">
								<li>
									<div class="doit_img fl">
										<a target="_blank" href="${ctx }/${item.id}/showGameDetail.html" style="z-index: 333;"> <img alt="${item.gameName }"  
											width="180" height="115" onerror="this.src='${ctx }/images/game/rectangle.png'" 
											src="${ctx }/showRectangleGamePic?id=${item.rectangle}">
										</a>
									</div>
									<dl style=" width: 800px; float: right;">
										<dt class="doit_title">
											<a  style="cursor: pointer;"  href="${ctx }/${item.id}/showGameDetail.html"  target="_blank">${item.gameName
												}</a>
										</dt>
										<dd class="doit_content">
											<span style="font-size: 16px;">游戏简介：</span>${item.gameDescrip
											}
										</dd>
										<dd class="Hot_content">
											<span>押金:</span> <strong>${item.deposit }纳币</strong> <span>周期:</span>
											<strong>${item.clicks }次</strong> <span>返利:</span> <strong>${item.reward
												}纳币</strong>
										</dd>
										<dd class="doit_wan">${item.players }人在玩</dd>
										<dd class="Hot_Btn">
											<a href="###" onclick="gameBox('${item.id}')" ></a>
										</dd>
									</dl>
								</li>
							</c:forEach>

						</ul>
							<div id="page_turner" class="qz_page_nav" style="">
							<div class="mod_pagenav">
								<p class="mod_pagenav_main">
									<span class="mod_pagenav_disable"> <span>上一页</span>
									</span>
									<c:if test="${ gamesPage <= 4}">
										<c:forEach begin="1" end="${ gamesPage}" step="1" var="item">
											<c:if test="${item == 1 }">
												<span class="current"> <span>1</span>
												</span>
											</c:if>
											<c:if test="${item != 1 }">
												<a id="pager_num_0_2" class="c_tx" title="${item }"
													onclick="gamePagination('all',${item},'${searchtype }','${keyword }')"
													href="javascript:void(0);"> <span>${item }</span>
												</a>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${ gamesPage > 4}">
										<span class="current"> <span>1</span>
										</span>
										<a id="pager_num_0_2" class="c_tx" title="2"
											onclick="gamePagination('all',2,'${searchtype }','${keyword }')" href="javascript:void(0);">
											<span>2</span>
										</a>
										<a id="pager_num_0_3" class="c_tx" title="3"
											onclick="gamePagination('all',3,'${searchtype }','${keyword }')" href="javascript:void(0);">
											<span>3</span>
										</a>
										<span class="mod_pagenav_more"> <span>…</span>
										</span>
										<a id="pager_last_0" class="c_tx" title="${gamesPage }"
											onclick="gamePagination('all',${gamesPage },'${searchtype }','${keyword }')"
											href="javascript:void(0);"> <span>${gamesPage }</span>
										</a>
									</c:if>


									<a id="pager_next_0" class="c_tx" title="下一页"
										onclick="gamePaginationNext('all',1,'${searchtype }','${keyword }')" href="javascript:void(0);"> <span>下一页</span>
									</a>
								</p>
							</div>
						</div>
					</c:otherwise>
					</c:choose>
						
					
					</div>
					</form>
					<div style="display: none;" id="content_2" class="content">
						
						<div id="page_turner" class="qz_page_nav" style="">
							<div class="mod_pagenav">
								<p class="mod_pagenav_main">
									<span class="mod_pagenav_disable"> <span>上一页</span>
									</span> <span class="current"> <span>1</span>
									</span> <a id="pager_num_0_2" class="c_tx" title="2"
										onclick="return false;" href="javascript:void(0);"> <span>2</span>
									</a> <a id="pager_num_0_3" class="c_tx" title="3"
										onclick="return false;" href="javascript:void(0);"> <span>3</span>
									</a> <span class="mod_pagenav_more"> <span>…</span>
									</span> <a id="pager_last_0" class="c_tx" title="末页"
										onclick="return false;" href="javascript:void(0);"> <span>19</span>
									</a> <a id="pager_next_0" class="c_tx" title="下一页"
										onclick="return false;" href="javascript:void(0);"> <span>下一页</span>
									</a>
								</p>
							</div>
						</div>
						</div>
						<div id="content_3" style="display: none;" class="content">
							
						</div>
						<div style="display: none;" id="content_4" class="content">
							
						</div>
						<div style="display: none;" id="content_5" class="content">
							
							<div id="page_turner" class="qz_page_nav" style="">
								<div class="mod_pagenav">
									<p class="mod_pagenav_main">
										<span class="mod_pagenav_disable"> <span>上一页</span>
										</span> <span class="current"> <span>1</span>
										</span> <a id="pager_num_0_2" class="c_tx" title="2"
											onclick="return false;" href="javascript:void(0);"> <span>2</span>
										</a> <a id="pager_num_0_3" class="c_tx" title="3"
											onclick="return false;" href="javascript:void(0);"> <span>3</span>
										</a> <span class="mod_pagenav_more"> <span>…</span>
										</span> <a id="pager_last_0" class="c_tx" title="末页"
											onclick="return false;" href="javascript:void(0);"> <span>19</span>
										</a> <a id="pager_next_0" class="c_tx" title="下一页"
											onclick="return false;" href="javascript:void(0);"> <span>下一页</span>
										</a>
									</p>
								</div>
							</div>
						</div>
          </div>
          </div>
      </div>
        <div class="RB"></div>
		</div>
	</div>