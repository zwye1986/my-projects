<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/23/14
  Time: 11:15 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${ctx}/css/new-global-min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/mall.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
    function gotoPage(p){
        $("input[name='currentPage']").val(p);
        $("#mainForm").submit();
    }

    function back(p){
        if(p > 1){
            $("input[name='currentPage']").val(p-1);
            $("#mainForm").submit();
        }
    }

    function next(p){
        var page = parseInt('${page.totalPages}');
        if(p < page){
            p++;
            $("input[name='currentPage']").val(p);
            $("#mainForm").submit();
        }
    }

    function changeScope(s){
        $("input[name='currentPage']").val(1);
        $("#integralScope").val(s);
        $("#mainForm").submit();
    }

    function changeType(t){
        $("input[name='currentPage']").val(1);
        $("#integralScope").val(0);
        $("#category").val(t);
        $("#mainForm").submit();
    }
</script>
<div id="wrapper">
    <form action="${ctx }/mall.html" method="post" id="mainForm"
          name="mainForm">
        <input type="hidden" name="pageSize" id="page.pageSize" value="${page.pageSize }" />
        <input type="hidden" name="currentPage" id="page.currentPage" value="<c:out value="${page.currentPage }"></c:out>" />
        <input type="hidden" name="$.integralScope" id="integralScope" value="<c:out value="${condition.integralScope }"></c:out>" />
        <input type="hidden" name="$.category" id="category" value="<c:out value="${condition.category }"></c:out>" />
    </form>
    <div class="other-infos">
        <div class="jTabs">
            <ul id="investTabs">
                <c:forEach var="c" items="${commodityCategories }" varStatus="s">
                    <li class="jTab<c:if test="${c.id eq condition.category }"> current</c:if> <c:if test="${s.index + 1 eq fn:length(commodityCategories)}"> last</c:if>"
                        id="${c.id }" onclick="changeType('${c.id}')">
                        ${c.name }</li>
                </c:forEach>

            </ul>
        </div>
        <div id="investPanel" class="model-box">
            <div class="jPanel contentin">
                <div class="j_search">
                    <ul>
                        <li
                                <c:if test="${condition.integralScope eq 0 }"> class="selected"</c:if>
                                onclick="changeScope('0')">
                            全部商品
                        </li>
                        <c:forEach items="${commoditiesConfigs }" var="cc" varStatus="s">
                            <c:choose>
                                <c:when test="${cc.minIntegral eq 0 }">
                                    <li id="${cc.id }"
                                        <c:if test="${condition.integralScope eq cc.id }"> class="selected"</c:if>
                                        onclick="changeScope('${cc.id}')">
                                        ${cc.maxIntegral }积分以下
                                    </li>
                                </c:when>
                                <c:when test="${cc.maxIntegral eq 0 }">
                                    <li id="${cc.id }" <c:if
                                            test="${condition.integralScope eq cc.id }"> class="selected"</c:if>
                                        onclick="changeScope('${cc.id}')">${cc.minIntegral }积分以上
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li id="${cc.id }" <c:if
                                            test="${condition.integralScope eq cc.id }"> class="selected"</c:if>
                                        onclick="changeScope('${cc.id}')">${cc.minIntegral }-${cc.maxIntegral }积分
                                    </li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </ul>
                </div>
                <div class="mall-list">
                    <c:if test="${fn:length(commodities) == 0 }">
                        <div class="no-search">抱歉，没有找到相应的商品！</div>
                    </c:if>
                    <c:forEach var="commodity" items="${commodities }">
                        <dl>
                            <dt class="r3">
                                <a href="${ctx }/${commodity.id }/mallDetail.html" target="_blank">
                                    <c:choose>
                                        <c:when test="${not empty commodity.coverPic }">
                                            <img src="${ctx }/showMediumPic?id=${commodity.coverPic }"
                                                 alt="${commodity.name}" width="220" height="180"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${ctx }/images/mall/zwtp.jpg" alt="暂无图片" width="220"
                                                 height="180"/>
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </dt>
                            <dd class="p-name">
                                <a href="${ctx }/${commodity.id }/mallDetail.html" target="_blank"
                                   title="${commodity.name }">${commodity.model }<em>${commodity.remark }</em></a>
                            </dd>
                            <dd class="p-price">
                                <strong>原价:￥${commodity.price }</strong>
                            </dd>
                            <dd class="integration">
                                <strong>积分:${commodity.integral }</strong>
                            </dd>
                            <dd class="dh_btn"
                                onclick="javascript:window.open('${ctx }/${commodity.id }/mallDetail.html','_blank')">兑换
                            </dd>
                        </dl>
                    </c:forEach>
                    <!--page start-->
                    <c:if test="${page.totalPages > 1 }">
                        <div class="yPager" id="loanInvsetPager">
                            <div class="inner">
                	<span class="prev" onclick="back('${page.currentPage}')">
        		<em class="page_p">
                    <img src="${ctx }/img/common/page_p.png"/>
                </em>
        		Prev
        	</span>
                                <c:choose>
                                    <c:when test="${page.totalPages < 9 }">
                                        <c:forEach var="i" begin="1" end="${page.totalPages }">
                                            <c:choose>
                                                <c:when test="${i eq page.currentPage }">
                                                    <span class="current">${i }</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <a onclick="gotoPage('${i}')">${i }</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${page.currentPage < 8 }">
                                        <c:forEach var="i" begin="1" end="7">
                                            <c:choose>
                                                <c:when test="${i eq page.currentPage }">
                                                    <span class="current">${i }</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <a onclick="gotoPage('${i}')">${i }</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <span>...</span>
                                        <a onclick="gotoPage('${page.totalPages}')">${page.totalPages }</a>
                                    </c:when>
                                    <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages-1 }">
                                        <c:forEach var="i" begin="1" end="5">
                                            <a onclick="gotoPage('${i}')">${i }</a>
                                        </c:forEach>
                                        <span>...</span>
                                        <span class="current">${page.currentPage }</span>
                                        <span>...</span>
                                        <a onclick="gotoPage('${page.totalPages}')">${page.totalPages }</a>
                                    </c:when>
                                    <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages }">
                                        <c:forEach var="i" begin="1" end="6">
                                            <a onclick="gotoPage('${i}')">${i }</a>
                                        </c:forEach>
                                        <span>...</span>
                                        <span class="current">${page.totalPages-1 }</span>
                                        <a onclick="gotoPage('${page.totalPages}')">${page.totalPages }</a>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="i" begin="1" end="7">
                                            <a onclick="gotoPage('${i}')">${i }</a>
                                        </c:forEach>
                                        <span>...</span>
                                        <span class="current">${page.totalPages }</span>
                                    </c:otherwise>
                                </c:choose>
        	<span class="next" onclick="next('${page.currentPage}')">
        		Next
        		<em class="page_n">
                    <img src="${ctx }/img/common/page_n.png"/>
                </em>
        	</span>
                            </div>
                        </div>
                    </c:if>
                    <!--page end-->
                </div>
            </div>
        </div>
    </div>
</div>