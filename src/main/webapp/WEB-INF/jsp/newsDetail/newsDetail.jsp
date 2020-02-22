<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request"></c:set>
<link href="${ctx}/css/base.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/list.css" type="text/css" rel="stylesheet" />
<div id="wrapper">
    <h2><img src="${ctx}/images/common/andetail_title.png"></h2>
    <div id="sidebar-list">
        <ul>
            <c:forEach var="item" items="${otherNews}">
                <li title="${item.newsTitle}">
                    <i class="dian-icons"><img src="${ctx}/images/common/dian.png"></i>
                    <a target="_blank" href="${ctx}/${item.id}/showNews.html" >${item.newsTitle}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="main-c">
        <div class="clearfix"></div>
        <div class="notice_pd">
            <h1>${news.newsTitle }</h1>
            <div class="news_ts_ly" style="width:650px;">
                <div style="float:left; height:18px; line-height:18px; width: 100%;text-align: center">
                    <fmt:formatDate value="${news.addTime }" pattern="yyyy-MM-dd" />
                    <span>|</span> 引用自：${news.newsRef }
                    <span>|</span> 编辑：${news.author }
                </div>
            </div>
        </div>
        <div class="notice_con">
            ${news.content }
        </div>
    </div>
</div>