<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
            <div class="model-box">
              <table class="assets-table">
                <tbody>
                  <tr>
                    <th>时间</th>
                    <th>项目</th>
                    <th>截止日期</th>
                    <th>投资金额</th>
                    <th>预计收入</th>
                  </tr>
                  <c:forEach items="${list }" var="item" varStatus="s">
                  <tr>
                    <td ><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd"/></td>
                    <td >${item.gameName }</td>
                    <td ><fmt:formatDate value="${item.invalidTime }" pattern="yyyy-MM-dd"/></td>
                    <td>￥${item.deposit }</td>
                    <td>￥${item.reward }</td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
              <!--page start-->
              <div class="yPager" id="loanInvsetPager">
                <div class="inner">
               
                <c:forEach begin="1" end="${gamesPage }" step="1" var="item">
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

                </div>
              </div>
              <!--page end--> 
            </div>