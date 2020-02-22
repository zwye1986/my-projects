<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/26/14
  Time: 4:02 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<script type="text/javascript">
    $(document).ready(function(){
        SlideShow(1000);
    });
</script>
<div class="comiis_wrapad" id="slideContainer">
    <div id="frameHlicAe" class="frame cl">
        <div class="temp"></div>
        <div class="block">
            <div class="cl">
                <ul class="slideshow" id="slidesImgs">
                    <c:forEach items="${advertisings}" var="item">
                        <li>
                            <a <c:choose>
                                <c:when test="${not empty item.href}">
                                    href="${item.href }"
                                </c:when>
                                <c:otherwise>
                                    href="###"
                                </c:otherwise>
                            </c:choose>  target="_blank">
                                <img src="${ctx }/${item.id }/getAdvertiseImg" alt="${item.name}">
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="slidebar" id="slideBar">
                <ul>
                    <c:forEach items="${advertisings}" var="item" varStatus="s">
                        <li <c:if test="${s.index eq 0}">class="on"</c:if>>${s.index + 1}</li>
                    </c:forEach>

                </ul>
            </div>
        </div>
    </div>
</div>
