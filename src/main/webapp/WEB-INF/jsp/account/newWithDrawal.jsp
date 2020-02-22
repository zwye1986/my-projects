<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
    function back(n){
        if(n > 1){
            var page = n-1;
            window.location.href="${ctx}/user/"+page+"/withDrawList";
        }
    }

    function next(n){
        var page = parseInt('${page.totalPages}');
        if(n < page){
            page = n + 1;
            window.location.href="${ctx}/user/"+page+"/withDrawList";
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
                        <li id="one3" onclick="javascript:window.location.href='${ctx}/user/1/withDrawList'" class="hover">提现记录</li>
                        <li id="one4" onclick="javascript:window.location.href='${ctx}/user/targetList'">目标查询</li>
                    </ul>
                </div>
            </div>
            <div class="lyz_tab_right">
                <div class="hover" id="con_one_3" style="display: block; border: none">
                    <div class="model-box">
                        <table class="assets-table">
                            <tbody>
                            <tr>
                                <th>交易号</th>
                                <th>提现时间</th>
                                <th>提现金额</th>
                                <th>预计到账时间</th>
                                <th>VIP预计到账时间</th>
                                <th>状态</th>
                            </tr>
                            <c:forEach var="data" items="${withdrawList}">
                                <tr>
                                    <td>${data.serialNumber }</td>
                                    <td><fmt:formatDate value="${data.time}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><c:if test="${data.type == 2 }">-${data.amount } 纳币</c:if></td>
                                    <td><fmt:formatDate value="${data.cashDay}"  pattern="yyyy-MM-dd"/></td>
                                    <td><fmt:formatDate value="${data.vipCashDay}"  pattern="yyyy-MM-dd"/></td>
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
                                                        <a href="${ctx }/user/${i}/withDrawList">${i }</a>
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
                                                        <a href="${ctx }/user/${i}/withDrawList">${i }</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <span>...</span>
                                            <a href="${ctx }/user/${page.totalPages}/withDrawList">${page.totalPages }</a>
                                        </c:when>
                                        <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages-1 }">
                                            <c:forEach var="i" begin="1" end="5">
                                                <a href="${ctx }/user/${i}/withDrawList">${i }</a>
                                            </c:forEach>
                                            <span>...</span>
                                            <span class="current" >${page.currentPage }</span>
                                            <span>...</span>
                                            <a href="${ctx }/user/${page.totalPages}/withDrawList">${page.totalPages }</a>
                                        </c:when>
                                        <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages }">
                                            <c:forEach var="i" begin="1" end="6">
                                                <a href="${ctx }/user/${i}/withDrawList">${i }</a>
                                            </c:forEach>
                                            <span>...</span>
                                            <span class="current" >${page.totalPages-1 }</span>
                                            <a href="${ctx }/user/${page.totalPages}/withDrawList">${page.totalPages }</a>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="i" begin="1" end="7">
                                                <a href="${ctx }/user/${i}/withDrawList">${i }</a>
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
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!--选项卡结束-->
</div>