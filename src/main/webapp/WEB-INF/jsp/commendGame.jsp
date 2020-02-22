<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
<li onclick="javascript:window.open('${ctx }/${item.id}/showGameDetail.html','_blank');">
			<img width="210" height="129" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'">
			<div class="hotlist" style="top: 100px; height: 30px;">
            	<h3>${item.gameName }</h3>
	            <dl>
               <dd>押金：${item.deposit }纳币</dd>
            <dd>周期：${item.clicks }次</dd>
            <dd>返利：${item.reward }纳币</dd>
                </dl>
            </div>
		</li>
		</c:forEach>