<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${ctx }/css/base.css" rel="stylesheet" />
  <link href="${ctx }/css/user.css" rel="stylesheet" />
  <link href="${ctx }/css/feedback_tj.css" rel="stylesheet" />
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css" rel="stylesheet" />
  <script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
  <script type="text/javascript" src="${ctx }/js/jquery.cookie.js"></script>
  <script type="text/javascript" src="${ctx }/js/game.js"></script>
  <script type="text/javascript" src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
	  
	  $("#back").click(function(){
		  top.location.href="${ctx}/index";
	  });
  });
  </script>
<div class="RTitle"></div>
<div class="feedback_C">
	<h5>蛙宝网意见反馈系统</h5>
	<div class="fd_top"></div>
	<div class="fd_center">
		<p>
			<img
				style="width: 130px; height: 130px; float: left; margin-left: 300px;"
				src="${ctx }/images/feedback/success.png"><a>提交成功！</a>
		</p>
		<dl>
			<dd>尊敬的用户，您反馈的问题已经提交，我们将会在2个工作日内处理。</dd>
			<dd>请耐心等待。</dd>
			<dd>感谢您对蛙宝网的支持！</dd>
		</dl>
		<div class="fd_submit">
			<input value="确定" type="button" id="back">
		</div>

	</div>

	<div class="fd_bottom"></div>
	<div class="fd_games">
		<div class="fd_games_t">推荐游戏</div>
		<ul>
		<c:forEach var="item" items="${games }">
		<li>
				<dl>
					<dt>
						<img src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'">
					</dt>
					<dd>${item.gameName }</dd>
					<dd>押金：${item.deposit }纳币</dd>
					<dd>周期：${item.clicks }次</dd>
					<dd>返利：${item.reward }纳币</dd>
					<dd>
						<input value="" type="button" onclick="gameBox('${item.id}')">
					</dd>
				</dl>
			</li>
		</c:forEach>
			
		</ul>
	</div>
</div>
<div class="RB"></div>
