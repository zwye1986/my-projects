<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

	<script type="text/javascript" src="${ctx }/js/game.js"></script>

<link href="${ctx }/css/game.css" rel="stylesheet" />
<div style="margin-top:8px;" class="GRankC">
        <div class="GRankTop"></div>
        <div style="width: 194px;border-left:1px solid #d1d1d1;border-right:1px solid #d1d1d1;" class="GRankTitle">小游戏排行榜</div>
        <dl class="chartsRecommend">
            <dt> <a style="padding-left:5px;" href="${ctx }/${bigFlashRank.id}/showGameDetail.html"  target="_blank" > <img alt="${bigFlashRank.gameName }" onerror="this.src='${ctx }/images/game/rectangle.png'" src="${ctx }/showRectangleGamePic?id=${bigFlashRank.rectangle }"></a> </dt>
            <dd style="border-bottom:1px dashed #ccc;"> <strong>${bigFlashRank.gameName }</strong> </dd>
            <dd> <span class="label">返利：</span> <span class="rpg">${policy.reward}纳币</span> </dd>
            <dd> <span class="levy">${gamePlayCounts }人在玩</span> </dd>
          </dl>
        <ul class="GRankList">
				<c:forEach items="${flashRank }" var="item" varStatus="s">
					<c:if test="${s.index < 3 }">
						<li><span class="fl spanlist" style="background: #F16244;">${s.index+1
								}</span>
							<p class="fl">
								<a  href="${ctx }/${bigFlashRank.id}/showGameDetail.html"  target="_blank" 
									title="${item.gameName }" style="color: #f16244;">${item.gameName
									}</a>
							</p> <em class="fr">></em></li>
					</c:if>
					<c:if test="${s.index >=3 && s.index < 11 }">
						<li><span class="fl spanlist">${s.index+1 }</span>
							<p class="fl">
								<a  href="${ctx }/${bigFlashRank.id}/showGameDetail.html"  target="_blank" 
									title="${item.gameName }">${item.gameName }</a>
							</p> <em class="fr">></em></li>
					</c:if>
					<c:if test="${s.index == 11 }">
						<li style="border-bottom: none;"><span class="fl spanlist">${s.index+1
								}</span>
							<p class="fl">
								<a  href="${ctx }/${bigFlashRank.id}/showGameDetail.html"  target="_blank" 
									title="${item.gameName }">${item.gameName }</a>
							</p> <em class="fr">></em></li>
					</c:if>
				</c:forEach>

			</ul>
        <div class="GRankB"></div>
      </div>