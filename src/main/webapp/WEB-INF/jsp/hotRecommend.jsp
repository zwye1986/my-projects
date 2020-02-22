<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
 <script type="text/javascript">
$(function(){
	$(".item1 li").hover(
			function(){
				var that=this;	
				item1Timer=setTimeout(function(){
					$(that).find("div").animate({"top":0,"height":129},300,function(){
						$(that).find("p").fadeIn(200);
					});
				},100);
			},
			function(){
				var that=this;	
				clearTimeout(item1Timer);
				$(that).find("p").fadeOut(200);
				$(that).find("div").animate({"top":100,"height":30},300);
			}
		);
});
</script>
  <c:forEach items="${recommendGames }" var="item" varStatus="s">
       <c:if test="${s.index < 2 }">
        <li>
          <dl>
            <dt><a href="${ctx }/${item.id}/showGameDetail.html" target="_blank"> <img width="110" height="110" alt="${item.gameName }" src="${ctx }/showSquareGamePic?id=${item.square}" onerror="this.src='${ctx }/images/game/square.png'"></a></dt>
            <dd>
              <h5>${item.gameName }</h5>
            </dd>
            <dd>押金：${item.deposit }纳币</dd>
            <dd>周期：${item.clicks }次</dd>
            <dd>返利：${item.reward }纳币</dd>
          </dl>
        </li>
        </c:if>
        </c:forEach>