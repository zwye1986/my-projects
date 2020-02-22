<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
            <div class="model-box">
              <table class="assets-table">
                <tbody id="tr1">
                  <tr>
									<th>兑换时间</th>
									<th>兑换积分</th>
									<th>兑换物品</th>
									<th>兑换数量</th>
									<th>发货状态</th>
								</tr>
                 <c:forEach items="${convertCreditsList}" var="obtainCredits">
									<tr>
										<td><fmt:formatDate value="${obtainCredits.actionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
									    <td>${obtainCredits.expendCredits}  </td>
									    <td>${obtainCredits.creditsGoodsName}
									        <c:if test="${(obtainCredits.mobileNumber!= '0')&&(obtainCredits.mobileNumber!='')&&(obtainCredits.mobileNumber!=null)}">
									        (${obtainCredits.mobileNumber})
									        </c:if>
									    </td>
									    <td>${obtainCredits.num}  </td>
									    
									    <c:set var="tradeStatus" value="${obtainCredits.status}" />
										
										<c:if test="${not empty tradeStatus }">
										
											<c:choose>
												<c:when test="${tradeStatus eq 0}">
											<td>
											<img alt="" src="${ctx}/images/credits/03.png"><% for(int i=0;i<8;i++){ %><img alt="" src="${ctx}/images/credits/02.png"><%} %><img alt="" src="${ctx}/images/credits/01.png"><% for(int i=0;i<8;i++){ %><img alt="" src="${ctx}/images/credits/02.png"><%} %><img alt="" src="${ctx}/images/credits/01.png">
												<br>
											<font style="color: #f16244;">兑换成功</font>
												 邮寄中
												发货成功
													
											</td>
												</c:when>
												<c:when test="${tradeStatus eq 1 }">
													<td>
										           <img alt="" src="${ctx}/images/credits/21.png"><% for(int i=0;i<8;i++){ %><img alt="" src="${ctx}/images/credits/02.png"><%} %><img alt="" src="${ctx}/images/credits/01.png">
												<br>
													<font style="color: #f16244;">兑换成功</font>
													<font style="color: #f16244;"> 邮寄中</font>
													 发货成功
												 </td>
												</c:when>
												<c:when test="${tradeStatus eq 2 }">
												<td>
										           <img alt="" src="${ctx}/images/credits/4.png">
												<br>
													<font style="color: #f16244;">兑换成功</font>
													<font style="color: #f16244;"> 邮寄中</font>
													<font style="color: #f16244;"> 发货成功</font>
												 </td>
												</c:when>
												<c:when test="${tradeStatus eq 3 }">
												
												<td>
										           <img alt="" src="${ctx}/images/credits/5.png">
												<br>
													<font style="color: #f16244;">兑换成功</font>
													<font style="color: #f16244;"> 邮寄中</font>
													<font style="color: #f16244;"> 发货成功</font>
													<font style="color: #f16244;"> 退货成功</font>
												 </td>
												</c:when>
												<c:otherwise></c:otherwise>
											</c:choose>
									
										</c:if>
									
									
									
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

