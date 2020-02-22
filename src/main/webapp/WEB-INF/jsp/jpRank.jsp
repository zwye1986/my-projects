<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>--%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
    <%@ taglib prefix="yma" uri="/WEB-INF/tld/yma.tld" %>

<c:forEach items="${jbList }" var="item" varStatus="s">
    <ul class="ranking-post ranking-weeky">
        <li class="col_1"><span class="num">${10*(jbCurrentPage - 1) + s.index + 1 }</span></li>
        <li class="col_2">${item.nickName }</li>
        <li class="col_3 col_c">
            <ul class="col_ff">
                <yma:LevelConvertIcoTag vipTag="${item.vipTag}"
                                        level="${item.level}"></yma:LevelConvertIcoTag>
            </ul>
        </li>
    </ul>
</c:forEach>

<div id="rankingListPager" class="yPager yPager_b">
    <div class="inner">
        <c:forEach begin="1" end="5" var="i">
            <c:choose>
                <c:when test="${jbCurrentPage eq i}"><span class="current">${i}</span></c:when>
                <c:otherwise> <a href="###" onclick="toPage('jbPage','${i}','jbList')">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>
<input type="hidden" id="jbPage" value="${jbCurrentPage}"/>
</div>