<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
      <ul class="game-rank">
      <c:forEach items="${gamelist }" var="item" varStatus="s">
      <li>
      <dl>
      <dt class="ml20">${item.gameName }</dt>
      <dd class="ml20">押金：${item.deposit }纳币<dd>
      <dd class="ml20">周期：${item.clicks }次</dd>
      <dd class="ml20">返利：${item.reward}纳币</dd>
      <dd class="gm-img"><a href="${ctx }/${item.id}/showGameDetail.html" target="_blank">
<img width="210" height="129" onerror="this.src='${ctx }/images/game/rectangle.png'" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" alt="${item.gameName }">
</a></dd>
<dd class="gm-btn"><div class="save-button">
                  <input type="button" onclick="javascript:window.open('${ctx }/${item.id}/showGameDetail.html','_blank')" value="点游戏" name="点游戏">
                </div></dd>
      </dl>
      
      </li>
      </c:forEach>
      </ul>
      <div class="yPager" id="loanInvsetPager">
                <div class="inner">
                <c:forEach begin="1" end="${gamesPage }" step="1" var="item">
                <c:if test="${item == 1 || item == gamesPage}">
			<c:if test="${item != page }">
			<a href="javascript:void(0)" onclick="gamePagination('${sort }',${item})">${item }</a>
			</c:if>
			<c:if test="${item == page }">
			<span class="current">${item }</span>
			</c:if>		
			</c:if>
			<c:if test="${page >= 1 && item >= (page-2) &&  item <= (page+2)  && item !=1 && item != gamesPage}">
			<c:if test="${item == (page-2) && item >2 }">
			<span>...</span>
			</c:if>
			<c:if test="${item != page }">
			<a href="javascript:void(0)" onclick="gamePagination('${sort }',${item})">${item }</a>
			</c:if>
			<c:if test="${item == page }">
			<span class="current">${item }</span>
			</c:if>		
			</c:if>
				<c:if test="${item == (page+2) && item < (gamesPage-1) }">
			<span>...</span>
			</c:if>
                </c:forEach>
             
                </div>
              </div>
