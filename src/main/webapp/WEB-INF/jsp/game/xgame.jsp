<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${ctx }/css/game.css" rel="stylesheet" />
<link href="${ctx }/css/wealthrank.css" rel="stylesheet" />
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/game.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
<!--content-->

  <!--content-->
  

    <!--右侧-->
    <div class="contentR fr"> 
      <!--排行榜详情-->
      
      <div class="gameR_t">
        <label>${rankname }前100名</label>
      </div>
      <div class="wealth_c">
        <ul class="game_c_list">
        <c:forEach items="${list }" var="item" varStatus="s">
        <c:if test="${(s.index+1)%3 ==1 }">
         <li>
        </c:if>
       
            <dl class="fl">
            <c:choose>
            <c:when test="${s.index <3 }">
            <dt class="xgame_list01 fl">
            </c:when>
            <c:otherwise>
              <dt class="xgame_list fl">
            </c:otherwise>
            </c:choose>
              
              
              <label>${s.index+1 }</label></dt>
              <dd class="Riches_name fl" style="cursor: pointer;"  ><a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank" style="color: #555;">${item.gameName }</a></dd>
              
            </dl>
         <c:if test="${(s.index+1)%3 == 0 || (s.index+1)==fn:length(list) }">
         </li>
        </c:if>   
         
          </c:forEach>
         
        </ul>
      </div>
      <div class="RB_detial"> </div>
    </div>
 
