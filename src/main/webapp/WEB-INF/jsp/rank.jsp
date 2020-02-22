<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 10/31/14
  Time: 10:09 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${ctx}/css/global-min.css" type="text/css" rel="stylesheet">
<link href="${ctx}/css/stats.css" type="text/css" rel="stylesheet">
<link href="${ctx}/css/level.css" type="text/css" rel="stylesheet">
<%@ taglib prefix="yma" uri="/WEB-INF/tld/yma.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    function toPage(url, page, id) {
        $("#" + url).val(page);
        $.post("${ctx}/" + page + "/" + url, {}, function (data) {
            $("#" + id).empty();
            $("#" + id).html(data);
        });
    }
</script>
<div id="wrapper">
    <h2 style="border-bottom:2px solid #349cd8"><img src="${ctx}/images/common/rank_title.png"></h2>

    <div class="rank-content" style="height:700px;">
        <!--sidebar-->
        <div id="sidesub" class="sidesub_f sidesub_first">
            <div id="thisWeekNewStar" class="ranking-chart model-box model-box-c">
                <div class="head">
                    <h2>财富排行榜<em>TOP50</em></h2>
                </div>

                <ul class="ranking-post ranking-weeky ranking-title double">
                    <li class="col_1">排名</li>
                    <li class="col_2">用户名</li>
                    <li class="col_3"> 用户头像</li>
                </ul>
                <div id="cfList">
                    <c:forEach items="${cfList }" var="item" varStatus="s">
                        <ul class="ranking-post ranking-weeky">
                            <li class="col_1"><span class="num">${10*(cfCurrentPage - 1) + s.index + 1 }</span></li>
                            <li class="col_2">${item.nickName }</li>
                            <li class="col_3">
                                <c:choose>
                                    <c:when test="${not empty item.photo}">
                                        <img src="${ctx }/${item.id }/getPhoto" width="40" height="40" alt="蛙宝网用户头像"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img width="40" height="40" src="${ctx }/images/people.png" alt="蛙宝网用户头像"/>
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
                </div>
            </div>
        </div>
        <!--/sidebar-->
        <!--sidebar-->
        <div id="sidesub" class="sidesub_f">
            <div id="thisWeekNewStar" class="ranking-chart model-box model-box-c">
                <div class="head">
                    <h2>收入排行榜<em>TOP50</em></h2>
                </div>
                <ul class="ranking-post ranking-weeky ranking-title double">
                    <li class="col_1">排名</li>
                    <li class="col_2">用户名</li>
                    <li class="col_3">用户头像</li>
                </ul>
                <div id="srList">
                    <c:forEach items="${srList }" var="item" varStatus="s">
                        <ul class="ranking-post ranking-weeky">
                            <li class="col_1"><span class="num">${10*(srCurrentPage - 1) + s.index + 1 }</span></li>
                            <li class="col_2">${item.nickName }</li>
                            <li class="col_3">
                                <c:choose>
                                    <c:when test="${not empty item.photo}">
                                        <img src="${ctx }/${item.id }/getPhoto" width="46" height="46" alt="蛙宝网用户头像"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img width="46" height="46" src="${ctx }/images/people.png" alt="蛙宝网用户头像"/>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </c:forEach>
                <div id="rankingListPager" class="yPager yPager_b">
                    <div class="inner">

                        <c:forEach begin="1" end="5" var="i">
                            <c:choose>
                                <c:when test="${srCurrentPage eq i}"><span class="current">${i}</span></c:when>
                                <c:otherwise> <a href="###" onclick="toPage('srPage','${i}','srList')">${i}</a></c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <input type="hidden" id="srPage" value="${srCurrentPage}"/>
                </div>
                </div>
            </div>
        </div>
        <!--/sidebar-->
        <!--sidebar-->
        <div id="sidesub" class="sidesub_f">
            <div id="thisWeekNewStar" class="ranking-chart model-box model-box-c">
                <div class="head">
                    <h2>级别排行榜<em>TOP50</em></h2>
                </div>
                <ul class="ranking-post ranking-weeky ranking-title double">
                    <li class="col_1">排名</li>
                    <li class="col_2">用户名</li>
                    <li class="col_3">用户等级</li>
                </ul>
                <div id="jbList">
                    <c:forEach items="${jbList }" var="item" varStatus="s">
                        <ul class="ranking-post ranking-weeky">
                            <li class="col_1"><span class="num">${10*(srCurrentPage - 1) + s.index + 1 }</span></li>
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
            </div>
        </div>
    </div>
    <!--/sidebar-->


</div>
</div>
