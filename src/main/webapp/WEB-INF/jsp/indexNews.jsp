<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<ul>
	<c:forEach var="item" begin="0" end="5" items="${news }">
		<li>
			<img src="${ctx }/images/news.gif" />
			<a href="${ctx }/${item.id}/showNews.html" target="_blank">
				<c:choose>
					<c:when test="${item.isImp == 1 }">
						<font color="red">${item.newsTitle}</font>
					</c:when>
					<c:otherwise>
						${item.newsTitle}
					</c:otherwise>
				</c:choose>
				
			</a>
		</li>
	</c:forEach>
</ul>
