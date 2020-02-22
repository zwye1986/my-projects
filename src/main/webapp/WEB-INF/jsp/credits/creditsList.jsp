<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
            <div class="model-box">
              <table class="assets-table">
                <tbody id="tr1">
                  <tr>
                    <th>获取积分时间</th>
					<th>获取积分</th>
					<th>获取积分类型</th>
                  </tr>
                 <c:forEach items="${obtainCreditslist}" var="obtainCredits">
									<tr>
										<td><fmt:formatDate value="${obtainCredits.actionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
										<td>${obtainCredits.credits} </td>
									    <td style="color: #f16244;">    
									    	<c:choose>
												<c:when test="${obtainCredits.actionType eq 0}">
													登录
												</c:when>
												<c:when test="${obtainCredits.actionType eq 1}">
													领取任务
												</c:when>
												<c:when test="${obtainCredits.actionType eq 2}">
													活动送积分
												</c:when>
											</c:choose> 
									    </td>
									</tr>
					</c:forEach>
                </tbody>
              </table>
              </div>
              
          
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
    <!--选项卡结束--> 

