<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
	
                      <ul>
                      <c:forEach items="${gamelist }" var="item" varStatus="s">
                       <li>
                          <dl>
                          </dl>
                          <dt><span><img src="${ctx }/images/vip/v01_icon.png"/></span></span><img  width="210" height="129" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'"/></dt>
                          <dd class="game_name">${item.gameName }</dd>
                          <dd class="game_fl">返利:${item.reward}纳币</dd>
                          <dd class="game_yj">押金:${item.deposit}纳币</dd>
                          <dd class="game_zq">周期:${item.clicks}次</dd>
                          <dd class="game_lq"><a href="${ctx }/${item.id}/showGameDetail.html" target="_blank">领游戏</a></dd>
                        </li>
                      </c:forEach>
                       
                      </ul>
                      <div class="page">
                      <div id="page_turner" class="qz_page_nav" >
	<div class="mod_pagenav">
		<p class="mod_pagenav_main">
		<c:if test="${page != 1 }">
		<a id="pager_next_0" class="c_tx" title="下一页" onclick="gamePaginationPrevForVip('all',${page},'${searchtype }','${keyword }')"
				href="javascript:void(0);"> <span>上一页</span>
			</a>
		</c:if>
		<c:if test="${page == 1 }">
		<span class="mod_pagenav_disable"> <span>上一页</span> </span>
		</c:if>
			
			
			
			<c:forEach begin="1" end="${gamesPage }" step="1" var="item">
			
			<c:if test="${item == 1 || item == gamesPage}">
			<c:if test="${item != page }">
			<a id="pager_num_0_3" class="c_tx" title="${item }" onclick="gamePaginationForVip('all',${item},'${searchtype }','${keyword }')" href="javascript:void(0);">
			<span>${item }</span>
			</a>
			</c:if>
			<c:if test="${item == page }">
			<span>${item }</span>
			</c:if>		
			</c:if>
			
			<c:if test="${page >= 1 && item >= (page-2) &&  item <= (page+2)  && item !=1 && item != gamesPage}">
			<c:if test="${item == (page-2) && item >2 }">
			<span class="mod_pagenav_more"> <span>…</span></span>	
			</c:if>
			<c:if test="${item != page }">
			<a id="pager_num_0_3" class="c_tx" title="${item }" onclick="gamePaginationForVip('all',${item},'${searchtype }','${keyword }')" href="javascript:void(0);">
			<span>${item }</span>
			</a>
			</c:if>
			<c:if test="${item == page }">
			<span>${item }</span>
			</c:if>		
			</c:if>
			<c:if test="${item == (page+2) && item < (gamesPage-1) }">
			<span class="mod_pagenav_more"> <span>…</span></span>	
			</c:if>
			</c:forEach>
		
			


<c:if test="${page != gamesPage }">
<a id="pager_next_0" class="c_tx" title="下一页" onclick="gamePaginationNextForVip('all',${page},'${searchtype }','${keyword }')"
				href="javascript:void(0);"> <span>下一页</span>
			</a>
</c:if>
<c:if test="${page == gamesPage }">
<span>下一页</span>
</c:if>

		
		</p>
	</div>
</div>
       </div>          