<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
 <script type="text/javascript">
$(function(){
	$('#marquee6').kxbdSuperMarquee({
		isMarquee:true,
		isEqual:false,
		scrollDelay:20,
		controlBtn:{up:'#goUM',down:'#goDM'},
		direction:'up'
	});
});
</script>
 <div class="announce_title">
        <h4 class="fl">充值记录</h4></div>
    <div id="marquee6" style="height: 300px;overflow: hidden;">
        <ul id="rechargeRecord">
  <c:forEach items="${rechargeRecordList }" var="item">
        <li><span class="name">&nbsp;${item.user.nickName }</span> <span class="money"><em>充值:</em>￥<fmt:formatNumber value="${item.amount }" pattern="#"/></span> </li>
        </c:forEach>
          </ul>
        </div>