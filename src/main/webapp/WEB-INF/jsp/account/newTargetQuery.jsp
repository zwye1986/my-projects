<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/18/14
  Time: 4:22 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" />
<script type="text/javascript">
    $(document).ready(function(){
        var date1 = $("#startTime").val();
        var date2 = $("#endTime").val();
        $("#startTime").datepicker({
            changeMonth: true,
            changeYear: true
        });

        $("#endTime").datepicker({
            changeMonth: true,
            changeYear: true
        });
        $("#endTime" ).datepicker("option","dateFormat","yy-mm-dd");
        $("#startTime" ).datepicker("option","dateFormat","yy-mm-dd");

        $("#startTime").val(date1);

        $("#endTime").val(date2);
    });

    function submitForm(){
        $("input[name='currentPage']").val(1);
        $("#mainForm").submit();
    }
    function submitFormToPage(currentPage){
        $("input[name='currentPage']").val(currentPage);
        $("#mainForm").submit();
    }
    function back(n){
        if(n > 1){
            var page = n-1;
            $("input[name='currentPage']").val(page);
            $("#mainForm").submit();
        }
    }

    function next(n){
        var page = parseInt('${page.totalPages}');
        if(n < page){
            page = n + 1;
            $("input[name='currentPage']").val(page);
            $("#mainForm").submit();
        }
    }
</script>
<div id="main">
    <div class="title-items">
        <h2>交易明细</h2>
        <b class="line"></b></div>
    <div class="model-box">
        <!--选项卡开始-->
        <div class="tab_box">
            <div class="lyz_tab_left">
                <div class="pro_con111">
                    <ul>
                        <li id="one1" onclick="javascript:window.location.href='${ctx}/user/1/dealDetail'">收支明细</li>
                        <li id="one2" onclick="javascript:window.location.href='${ctx}/user/1/rechargeList'" >充值记录</li>
                        <li id="one3" onclick="javascript:window.location.href='${ctx}/user/1/withDrawList'" >提现记录</li>
                        <li id="one4" onclick="javascript:window.location.href='${ctx}/user/targetList'" class="hover">目标查询</li>
                    </ul>
                </div>
            </div>
            <div class="lyz_tab_right">
                <div class="hover" id="con_one_4" style="display: block;">
                    <form action="${ctx }/user/targetList" method="post" id="mainForm">
                        <div class="model-box trade-filter model-box-b" style="border: none">
                            <dl class="filter-items">
                                <dt>资金流向:</dt>
                                <dd>
                                    <input class="ra-sex" type="radio" value="1" name="$.type" <c:if test="${condition.type == 1 }"> checked="checked" </c:if> />
                                    <label class="sex">收入</label>
                                    <input class="ra-sex" type="radio" value="2" name="$.type" <c:if test="${condition.type == 2 }"> checked="checked" </c:if> />
                                    <label class="sex">支出</label>
                                </dd>
                            </dl>
                            <dl class="filter-items">
                                <dt>类型:</dt>
                                <dd>
                                    <input class="ra-sex" type="radio" name="$.dealType" value="6" <c:if test="${condition.dealType == 6 }"> checked="checked" </c:if> >
                                    <label class="sex">充值</label>
                                    <input class="ra-sex" type="radio" name="$.dealType" value="7" <c:if test="${condition.dealType == 7 }"> checked="checked" </c:if> >
                                    <label class="sex">提现</label>
                                    <input class="ra-sex" type="radio" name="$.dealType" value="0" <c:if test="${condition.dealType == 0 }"> checked="checked" </c:if> >
                                    <label class="sex">其他</label>
                                </dd>
                            </dl>
                            <dl class="filter-items">
                                <dt>金额区间：</dt>
                                <dd>
                                    <div class="query-input">
                                        <input type="text" autocomplete="off" name="$.minAmount" value="${condition.minAmount }" id="minAmount" />
                                        <label>-</label>
                                        <input type="text" autocomplete="off" name="$.maxAmount" value="${condition.maxAmount }" id="maxAmount" />
                                    </div>

                                </dd>
                            </dl>
                            <dl class="filter-items">
                                <dt>关键字：</dt>
                                <dd>
                                    <div class="query-input">
                                        <input type="text" name="$.keyword" value="${condition.keyword }" autocomplete="off" style="width:260px;" />
                                    </div>
                                </dd>
                            </dl>
                            <dl class="filter-items">
                                <dt>起至日期：</dt>
                                <dd>
                                    <div class="query-input">
                                        <input type="text" name="$.startTime" id="startTime"  value="${fn:substring(condition.startTime,0,10) }" autocomplete="off" />
                                        <label>-</label>
                                        <input type="text" id="endTime" name="$.endTime" value="${fn:substring(condition.endTime,0,10) }"  autocomplete="off"/>
                                    </div>
                                </dd>
                            </dl>
                            <dl class="filter-items">
                                <dt></dt>
                                <dd>
                                    <div class="search-button">
                                        <input type="hidden" name="pageSize" id="page.pageSize" value="${page.pageSize }" />
                                        <input type="hidden" name="currentPage" id="page.currentPage" value="${page.currentPage }" />
                                        <input type="button" name="search" value="搜索" onclick="submitForm()" />
                                    </div>
                                </dd>
                            </dl>
                        </div>
                    </form>
                    <div class="model-box" >
                        <table class="assets-table" style="border: none">
                            <tbody>
                            <tr>
                                <th>交易号</th>
                                <th>时间</th>
                                <th>名称</th>
                                <th>金额</th>
                                <th>状态</th>
                            </tr>
                            <c:forEach var="data" items="${target}">
                                <tr>
                                    <td>${data.serialNumber }</td>
                                    <td><fmt:formatDate value="${data.time}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${data.description }</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${data.type == 1 }">+${data.amount } 纳币</c:when>
                                            <c:when test="${data.type == 2 }">-${data.amount } 纳币</c:when>
                                        </c:choose>
                                    </td>
                                    <td><c:choose><c:when test="${data.status == 0 }">交易成功</c:when>
                                        <c:when test="${data.status == 1 }">交易失败</c:when>
                                        <c:when test="${data.status == 2 }">正在处理</c:when></c:choose></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
                                                        <a href="###" onclick="submitFormToPage('${i}')">${i }</a>
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
                                                        <a href="###" onclick="submitFormToPage('${i}')">${i }</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <span>...</span>
                                            <a href="###" onclick="submitFormToPage('${page.totalPages }')">${page.totalPages }</a>
                                        </c:when>
                                        <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages-1 }">
                                            <c:forEach var="i" begin="1" end="5">
                                                <a href="###" onclick="submitFormToPage('${i}')">${i }</a>
                                            </c:forEach>
                                            <span>...</span>
                                            <span class="current" >${page.currentPage }</span>
                                            <span>...</span>
                                            <a href="###" onclick="submitFormToPage('${page.totalPages }')">${page.totalPages }</a>
                                        </c:when>
                                        <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages }">
                                            <c:forEach var="i" begin="1" end="6">
                                                <a href="###" onclick="submitFormToPage('${i}')">${i }</a>
                                            </c:forEach>
                                            <span>...</span>
                                            <span class="current" >${page.totalPages-1 }</span>
                                            <a href="###" onclick="submitFormToPage('${page.totalPages }')">${page.totalPages }</a>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="i" begin="1" end="7">
                                                <a href="###" onclick="submitFormToPage('${i}')">${i }</a>
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
                        <!--page end-->
                    </div>

                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--选项卡结束-->
</div>