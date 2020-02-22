<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 8/22/14
  Time: 3:34 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"  scope="request"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <c:forEach var="item" begin="0" end="9" items="${news }">
        <li>
            <i class="chevron-left icons"></i>
            <a target="_blank" href="${ctx }/${item.id}/showNews.html">
                <c:choose>
                    <c:when test="${item.isImp == 1 }">
                        <span style="color: red; ">${item.newsTitle}</span>
                    </c:when>
                    <c:otherwise>
                        ${item.newsTitle}
                    </c:otherwise>
                </c:choose>
            </a>
        </li>
    </c:forEach>