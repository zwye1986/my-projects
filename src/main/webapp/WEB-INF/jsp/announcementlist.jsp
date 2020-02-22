<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"
       scope="request"/>
<link href="${ctx}/css/base.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/list.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/page.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
    function back(n){
        if(n > 1){
            var page = n-1;
            window.location.href="${ctx}/"+page+"/showAllNews";
        }
    }

    function next(n){
        var page = parseInt('${page.totalPages}');
        if(n < page){
            page = n + 1;
            window.location.href="${ctx}/"+page+"/showAllNews";
        }
    }
</script>
<div id="wrapper">
    <h2 style="border-bottom: 2px solid #349CD8;"><img src="${ctx}/images/common/an_title.png"></h2>
    <ul class="an-list">
        <c:forEach var="item" items="${newsList}">
        <li>
            <a href="${ctx}/${item.id}/showNews.html" target="_blank">
                <dl>
                    <dt class="an-title">
                        <i class="dian-icons">
                            <img src="${ctx}/images/common/dian.png">
                        </i>${item.newsTitle}
                    </dt>
                    <dd class="date"><fmt:formatDate value="${item.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
                </dl>
            </a>
        </li>
        </c:forEach>
        <!--page start-->
        <c:if test="${page.totalPages > 1 }">
            <div class="yPager" id="loanInvsetPager">
                <div class="inner">
                	<span class="prev" onclick="back(${page.currentPage})">
        		<em class="page_p">
                    <img src="${ctx }/img/common/page_p.png" />
                </em>
        		Prev
        	</span>
                    <c:choose>
                        <c:when test="${page.totalPages < 9 }">
                            <c:forEach var="i" begin="1" end="${page.totalPages }" >
                                <c:choose>
                                    <c:when test="${i eq page.currentPage }">
                                        <span class="current" >${i }</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${ctx }/${i}/showAllNews">${i }</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>
                        <c:when test="${page.currentPage < 8 }">
                            <c:forEach var="i" begin="1" end="7">
                                <c:choose>
                                    <c:when test="${i eq page.currentPage }">
                                        <span class="current" >${i }</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${ctx }/${i}/showAllNews">${i }</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <span>...</span>
                            <a href="${ctx }/${page.totalPages}/showAllNews">${page.totalPages }</a>
                        </c:when>
                        <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages-1 }">
                            <c:forEach var="i" begin="1" end="5">
                                <a href="${ctx }/${i}/showAllNews">${i }</a>
                            </c:forEach>
                            <span>...</span>
                            <span class="current" >${page.currentPage }</span>
                            <span>...</span>
                            <a href="${ctx }/${page.totalPages}/showAllNews">${page.totalPages }</a>
                        </c:when>
                        <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages }">
                            <c:forEach var="i" begin="1" end="6">
                                <a href="${ctx }/${i}/showAllNews">${i }</a>
                            </c:forEach>
                            <span>...</span>
                            <span class="current" >${page.totalPages-1 }</span>
                            <a href="${ctx }/${page.totalPages}/showAllNews">${page.totalPages }</a>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="1" end="7">
                                <a href="${ctx }/${i}/showAllNews">${i }</a>
                            </c:forEach>
                            <span>...</span>
                            <span class="current" >${page.totalPages }</span>
                        </c:otherwise>
                    </c:choose>
        	<span class="next" onclick="next(${page.currentPage})">
        		Next
        		<em class="page_n">
                    <img src="${ctx }/img/common/page_n.png" />
                </em>
        	</span>
                </div>
            </div></c:if>
    </ul>
</div>
