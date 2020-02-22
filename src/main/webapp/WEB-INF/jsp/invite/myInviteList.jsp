<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>

	<div class="model-box">
		<table class="assets-table">
			<tbody>
				<tr>
					<th>领取时间</th>
					<th>奖励金额</th>
					<th>内容</th>
				</tr>
				<c:forEach items="${inviteBenefitList}" var="inviteBenefit">
					<tr>
						<td><fmt:formatDate value="${inviteBenefit.actiontime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${inviteBenefit.benefit}</td>
						<td>${inviteBenefit.content}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		 <!--page start-->
              <div class="yPager" id="loanInvsetPager">
                <div class="inner">
<%--                 <span class="prev" onclick="gamePaginationPrev('${type }',${page},'','')"><em class="page_p"><img src="${ctx}/images/common/page_p.png"/></em> Prev</span>
 --%>                <c:forEach begin="1" end="${gamesPage }" step="1" var="item">
                <c:if test="${item == 1 || item == gamesPage}">
                <c:if test="${item != page }">
                <a href="javascript:void(0);" onclick="gamePagination('${type }',${item},'','')">${item }</a>
                </c:if>
                <c:if test="${item == page }">
                <span class="current">${item }</span>
                </c:if>
                </c:if>
                <c:if test="${page >= 1 && item >= (page-2) &&  item <= (page+2)  && item !=1 && item != gamesPage}">
                <c:if test="${item == (page-2) && item >2 }">
                <span>...</span>
                </c:if>
                <c:if test="${item != page }">
                <a href="javascript:void(0);" onclick="gamePagination('${type }',${item},'','')">${item }</a>
                </c:if>
                <c:if test="${item == page }">
                <span class="current">${item }</span>
                </c:if>
                </c:if>
                <c:if test="${item == (page+2) && item < (gamesPage-1) }">
                <span>...</span>
                </c:if>
                </c:forEach>
<%--                 <span class="next" onclick="gamePaginationNext('${type }',${page},'','')">Next<em class="page_n"><img src="${ctx}/images/common/page_n.png"/></em></span>
 --%>                </div>
              </div>
              <!--page end--> 
	</div>

