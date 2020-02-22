<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
            <div class="model-box">
              <table class="assets-table mr20">
                <tbody>
                   <tr>
                    <th>姓名</th>
                    <th>联系方式</th>
                    <th>反馈内容</th>
                    <th>反馈时间</th>
                    <th>回复时间</th>
                    <th>回复内容</th>
                    <th>是否回复</th>
                  </tr>
                  <c:set var="max" value="${fn:length(list) }"></c:set>
                 <c:choose>
                 <c:when test="${max>0 }">
                  <c:forEach items="${list}" var="advice">
									<tr>
									    <td>${advice.name}  </td>
									     <td>${advice.contact}  </td>
									     <td><a href="###" onclick="viewAdvice('${advice.id}');">${fn:substring(advice.advice,0,10)}  </a> </td>
									     <td><fmt:formatDate value="${advice.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
									     <td><fmt:formatDate value="${advice.replytime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
									     <td><a href="###" onclick="viewReplay('${advice.id}');">${fn:substring(advice.replyContent,0,10)}</a> </td>
									     <td> <c:choose>
									             <c:when test="${advice.replayStatus eq 0}">否</c:when>
									             <c:otherwise>是</c:otherwise>
									         </c:choose>  </td>
									    </tr>
				</c:forEach> 
                 </c:when>
                 <c:otherwise>
                   <tr>
                    <td colspan="7">暂无内容！</td>
                    </tr>
                 </c:otherwise>
                 </c:choose>
                </tbody>
              </table>
              <!--page start-->
              <div class="yPager" id="loanInvsetPager">
                <div class="inner">
               
                <c:forEach begin="1" end="${gamesPage }" step="1" var="item">
                <c:if test="${item == 1 || item == gamesPage}">
                <c:if test="${item != page }">
                <a href="javascript:void(0);" onclick="gamePagination(${item})">${item }</a>
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
                <a href="javascript:void(0);" onclick="gamePagination(${item})">${item }</a>
                </c:if>
                <c:if test="${item == page }">
                <span class="current">${item }</span>
                </c:if>
                </c:if>
                <c:if test="${item == (page+2) && item < (gamesPage-1) }">
                <span>...</span>
                </c:if>
                </c:forEach>

                </div>
              </div>
              <!--page end--> 
            </div>