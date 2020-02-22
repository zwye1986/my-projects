<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>
<input type = "hidden" id="${type }page" value="${page }">
<c:forEach items="${gamelist }" var="item">
	<li gamemin="503" class="banner_three ">
		<div class="banner_img">
			<img  style="width:210px; height:129px;" onerror="this.src='${ctx }/images/game/rectangle.png'" src="${ctx }/showRectangleGamePic?id=${item.rectangle}"/>
			<p class="banner_info">
				${item.gameName }<em>${item.players }</em>人在玩
			</p>
		</div>
		<div class="banner_mask"></div>
		<div class="banner_hover">
			<dl>
				<dd>
					<a href="###"><span>押金:</span> <strong>${item.deposit
							}纳币</strong></a>
				</dd>
				<dd>
					<a href="###"><span>周期:</span> <strong>${item.clicks
							}次</strong></a>
				</dd>
				<dd>
					<a href="###"> <span>返利:</span> <strong>${item.reward }纳币</strong></a>
				</dd>
				<dd class="hover_btn"  onclick="gameBox('${item.id}')">
					<a href="###"></a>
				</dd>
			</dl>
		</div>
	</li>
</c:forEach>