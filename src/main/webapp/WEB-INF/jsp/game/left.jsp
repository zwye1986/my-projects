<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
    $.cookie.defaults = { path: '/'};
    $(".gameR_list").find("li").click(function(){
	    $.cookie('currentRank',null);
	    $.cookie('currentRank',this.id);
    });

    $("#"+$.cookie('currentRank')).addClass("currentRank_now");
});
</script>

    <div class="contentL fl"> 
      <!--排行榜-->
      <div class="gameRank">
       <div class="gamelist_t"></div>
        <ul class="gameR_list">
          <li id="_moreWealthTop"><img src="${ctx }/images/rank/cf.png"/><a href="${ctx }/rank.html">财富排行榜</a></li>
          <li id="_moreIncomeTop"><img src="${ctx }/images/rank/sr.png"/><a href="${ctx }/rank.html">收入排行榜</a></li>
          <li id="moreActiveTop"><img src="${ctx }/images/rank/hy.png"/><a href="${ctx }/rank.html">活跃排行榜</a></li>
          <li id="moreLevelTop"><img src="${ctx }/images/rank/jb.png"/><a href="${ctx }/rank.html">级别排行榜</a></li>
          <li id="moreInviteTop"><img src="${ctx }/images/rank/1.png"/><a href="${ctx }/rank.html">推荐奖励排行榜</a></li>
          <li id="moreInviteUserTop"><img src="${ctx }/images/rank/2.png"/><a href="${ctx }/rank.html">推荐人数排行榜</a></li>
         <!--  <li id="xgameFlash"><img src="${ctx }/images/rank/xyx.png"/><a href="${ctx }/xgame?type=flash">小游戏排行榜</a></li>
          <li id="xgameWeb"><img src="${ctx }/images/rank/wy.png"/><a href="${ctx }/xgame?type=web">网页游戏排行榜</a></li> -->
        </ul>
        <div class="gameRank_b"></div>
      </div>
      <!--小游戏排行榜-->
      <div class="GRankC" style="margin-top:8px;">
        <div class="GRankTop"></div>
        <div class="GRankTitle" style="width: 194px;border-left:1px solid #d1d1d1;border-right:1px solid #d1d1d1;">小游戏排行榜</div>
        <dl class="chartsRecommend">
          <dt> <a   style="padding-left:5px;" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank"> <img src="${ctx }/showRectangleGamePic?id=${bigFlashRank.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${bigFlashRank.gameName }"></a> </dt>
          <dd style="border-bottom:1px dashed #ccc;"> <strong>${bigFlashRank.gameName }</strong> </dd>
          <dd> <span class="label">返利：</span> <span class="rpg">${policy.reward
						}纳币</span> </dd>
          <dd> <span class="levy">${gamePlayCounts }人在玩</span> </dd>
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
					<c:if test="${s.index == 11 }">
						<li style="border-bottom: none;"><span class="fl spanlist">${s.index+1
								}</span>
							<p class="fl">
								<a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank"
									title="${item.gameName }">${item.gameName }</a>
							</p> <em class="fr">></em></li>
					</c:if>
				</c:forEach>
        </ul>
       
        <div class="GRankB"></div>
      </div>
    </div>