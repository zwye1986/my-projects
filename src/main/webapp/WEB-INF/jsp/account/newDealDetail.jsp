<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/18/14
  Time: 2:27 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
    function back(n){
        if(n > 1){
            var page = n-1;
            window.location.href="${ctx}/user/"+page+"/dealDetail";
        }
    }

    function next(n){
        var page = parseInt('${page.totalPages}');
        if(n < page){
            page = n + 1;
            window.location.href="${ctx}/user/"+page+"/dealDetail";
        }
    }
</script>
<div id="main">
<!--选项卡开始-->
<div class="tab_box">
<div class="lyz_tab_left">
    <div class="pro_con111">
        <ul>
            <li id="one1" onclick="javascript:window.location.href='${ctx}/user/1/dealDetail'" class="hover">收支明细</li>
            <li id="one2" onclick="javascript:window.location.href='${ctx}/user/1/rechargeList'">充值记录</li>
            <li id="one3" onclick="javascript:window.location.href='${ctx}/user/1/withDrawList'">提现记录</li>
            <li id="one4" onclick="javascript:window.location.href='${ctx}/user/targetList'">目标查询</li>
        </ul>
    </div>
</div>
<div class="lyz_tab_right">
<div style="display:block;border:none;padding-top:0" id="con_one_1" class="hover">
    <div class="model-box">
        <table class="assets-table">
            <tbody>
            <tr>
                <th>交易号</th>
                <th>时间</th>
                <th>名称</th>
                <th>收入／支出</th>
                <th>状态</th>
            </tr>
            <c:forEach items="${transactionDetails}" var="data">
                <tr>
                    <td>${data.serialNumber }</td>
                    <td><fmt:formatDate value="${data.time}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${data.description }</td>
                    <td>
                        <c:choose>
                        <c:when test="${data.type == 1 }">
                            +${data.amount } 纳币
                        </c:when>
                        <c:when test="${data.type == 2  }">
                            -${data.amount } 纳币
                        </c:when>
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
                                        <a href="${ctx }/user/${i}/dealDetail">${i }</a>
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
                                        <a href="${ctx }/user/${i}/dealDetail">${i }</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <span>...</span>
                            <a href="${ctx }/user/${page.totalPages}/dealDetail">${page.totalPages }</a>
                        </c:when>
                        <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages-1 }">
                            <c:forEach var="i" begin="1" end="5">
                                <a href="${ctx }/user/${i}/dealDetail">${i }</a>
                            </c:forEach>
                            <span>...</span>
                            <span class="current" >${page.currentPage }</span>
                            <span>...</span>
                            <a href="${ctx }/user/${page.totalPages}/dealDetail">${page.totalPages }</a>
                        </c:when>
                        <c:when test="${page.currentPage > 7 && page.currentPage < page.totalPages }">
                            <c:forEach var="i" begin="1" end="6">
                                <a href="${ctx }/user/${i}/dealDetail">${i }</a>
                            </c:forEach>
                            <span>...</span>
                            <span class="current" >${page.totalPages-1 }</span>
                            <a href="${ctx }/user/${page.totalPages}/dealDetail">${page.totalPages }</a>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="1" end="7">
                                <a href="${ctx }/user/${i}/dealDetail">${i }</a>
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
