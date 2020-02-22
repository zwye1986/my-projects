<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
 <c:forEach items="${recommendGames }" var="item" varStatus="s">
    <c:if test="${s.index<6 && s.index>1 }">
    <li onclick="javascript:window.open('${ctx }/${item.id}/showGameDetail.html','_blank');">
    <img src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" width="210" height="129" />
  <div class="hotlist">
            	<h3>${item.gameName }</h3>
	            <dl >
                <dd>押金：${item.deposit }纳币</dd>
                <dd>周期：${item.clicks }次</dd>
                <dd>返利：${item.reward }纳币</dd>
                </dl>
            </div>
    </li>
    </c:if>
    </c:forEach>