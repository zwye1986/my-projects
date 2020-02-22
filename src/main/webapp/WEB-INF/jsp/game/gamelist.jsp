<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
	 <div class="filter">
					 <dl class="order">
        <dt><em>默认排序：</em></dt>
        <c:if test="${sort == 'reward' && sortseq == 'down' }">
         <dd class="fanlidown" id="dd_reward" onclick="openReward('down','${type }')">
        </c:if>
         <c:if test="${sort == 'reward' && sortseq == 'up' }">
         <dd class="fanliup" id="dd_reward" onclick="openReward('down','${type }')">
        </c:if>
          <c:if test="${sort != 'reward'}">
         <dd class="fanli" id="dd_reward" onclick="openReward('down','${type }')">
        </c:if>
        
        <a href="javascript:void(0)" >返利</a>
        </dd>
        
        
        <c:if test="${sort == 'clicks' && sortseq == 'down' }">
        <dd class="fanlidown" id="dd_clicks" onclick="openClicks('down','${type }')">
        </c:if>
         <c:if test="${sort == 'clicks' && sortseq == 'up' }">
        <dd class="fanliup" id="dd_clicks" onclick="openClicks('down','${type }')">
        </c:if>
         <c:if test="${sort != 'clicks' }">
        <dd class="fanli" id="dd_clicks" onclick="openClicks('down','${type }')">
        </c:if>
       
        <a href="javascript:void(0)">周期</a>
        </dd>
        
        
         <c:if test="${sort == 'clickNum' && sortseq == 'down' }">
          <dd class="fanlidown" id="dd_hot" onclick="openHot('down','${type }')">
         </c:if>
           <c:if test="${sort == 'clickNum' && sortseq == 'up' }">
          <dd class="fanliup" id="dd_hot" onclick="openHot('down','${type }')">
         </c:if>
           <c:if test="${sort != 'clickNum'}">
          <dd class="fanli" id="dd_hot" onclick="openHot('down','${type }')">
         </c:if>
       
        <a href="javascript:void(0)">人气</a>
        </dd>
        
        
         <c:if test="${sort == 'deposit' && sortseq == 'down' }">
         <dd class="fanlidown" id="dd_deposit" onclick="openDeposit('down','${type }')">
         </c:if>
           <c:if test="${sort == 'deposit' && sortseq == 'up' }">
         <dd class="fanliup" id="dd_deposit" onclick="openDeposit('down','${type }')">
         </c:if>
           <c:if test="${sort != 'deposit'}">
         <dd class="fanli" id="dd_deposit" onclick="openDeposit('down','${type }')">
         </c:if>
       
        <a href="javascript:void(0)">押金</a>
        </dd>
        <dd class="zhou_q"></dd>
        <dd class="ren_q"></dd>
        <dd class="ya_j"></dd>
        </dl>
         <div class="qj_text"><input type="text" id="beginDeposit_${type }" value="${beginDeposit }" class="txt"/>－<input type="text" id="endDeposit_${type }" value="${endDeposit }" class="txt"/></div>
<div class="search_no_t"><input  type="text" name="keyword" value="" class="search_txt"/><input type="button" onclick="searchSubmit();" value="" class="search_btn"/></div>
<div class="qj_btn"><input type="button" onclick="depositselect('${type }')" class="filter_btn"/></div>
					</div>
<ul>
	<c:forEach items="${gamelist }" var="item" varStatus="s">
		<li>
			<div class="doit_img fl">
				<a target="_blank" href="${ctx }/${item.id}/showGameDetail.html"> <img alt="${item.gameName }"
					width="180" height="115" onerror="this.src='${ctx }/images/game/rectangle.png'" src="${ctx }/showRectangleGamePic?id=${item.rectangle}">
				</a>
			</div>
			<dl style=" width: 800px; float: right;">
				<dt class="doit_title">
					<a  href="${ctx }/${item.id}/showGameDetail.html"  target="_blank" style="cursor: pointer;" >${item.gameName }</a>
				</dt>
				<dd class="doit_content">
					<span style="font-size: 16px;">游戏简介：</span>${item.gameDescrip }
				</dd>
				<dd class="Hot_content">
					<span>押金:</span> <strong>${item.deposit }纳币</strong> <span>周期:</span>
					<strong>${item.clicks }次</strong> <span>返利:</span> <strong>${item.reward
						}纳币</strong>
				</dd>
				<dd class="doit_wan">${item.players }人在玩</dd>
				<dd class="Hot_Btn">
					<a href="###" onclick="gameBox('${item.id}')"></a>
				</dd>
			</dl>
		</li>
	</c:forEach>

</ul>
<div id="page_turner" class="qz_page_nav" style="">
	<div class="mod_pagenav">
		<p class="mod_pagenav_main">
		<c:if test="${page != 1 }">
		<a id="pager_next_0" class="c_tx" title="下一页" onclick="gamePaginationPrev('${type }',${page},'${searchtype }','${keyword }')"
				href="javascript:void(0);"> <span>上一页</span>
			</a>
		</c:if>
		<c:if test="${page == 1 }">
		<span class="mod_pagenav_disable"> <span>上一页</span> </span>
		</c:if>
			
			
			
			<c:forEach begin="1" end="${gamesPage }" step="1" var="item">
			
			<c:if test="${item == 1 || item == gamesPage}">
			<c:if test="${item != page }">
			<a id="pager_num_0_3" class="c_tx" title="${item }" onclick="gamePagination('${type }',${item},'${searchtype }','${keyword }')" href="javascript:void(0);">
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
			<a id="pager_num_0_3" class="c_tx" title="${item }" onclick="gamePagination('${type }',${item},'${searchtype }','${keyword }')" href="javascript:void(0);">
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
<a id="pager_next_0" class="c_tx" title="下一页" onclick="gamePaginationNext('${type }',${page},'${searchtype }','${keyword }')"
				href="javascript:void(0);"> <span>下一页</span>
			</a>
</c:if>
<c:if test="${page == gamesPage }">
<span>下一页</span>
</c:if>

		
		</p>
	</div>
</div>