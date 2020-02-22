<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<c:forEach items="${cfList }" var="item" varStatus="s">
    <ul class="ranking-post ranking-weeky">
        <li class="col_1"><span class="num">${10*(cfCurrentPage - 1) + s.index + 1 }</span></li>
        <li class="col_2">${item.nickName }</li>
        <li class="col_3">
            <c:choose>
                <c:when test="${not empty item.photo}">
                    <img src="${ctx }/${item.id }/getPhoto" width="40" height="40" alt="蛙宝网用户头像" />
                </c:when>
                <c:otherwise>
                    <img width="40" height="40" src="${ctx }/images/people.png" alt="蛙宝网用户头像" />
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</c:forEach>
<div id="rankingListPager" class="yPager yPager_b">
    <div class="inner">
        <c:forEach begin="1" end="5" var="i">
            <c:choose>
                <c:when test="${cfCurrentPage eq i}"><span class="current">${i}</span></c:when>
                <c:otherwise> <a href="###" onclick="toPage('cfPage','${i}','cfList')">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <input type="hidden" id="cfPage" value="${cfCurrentPage}"/>
</div>