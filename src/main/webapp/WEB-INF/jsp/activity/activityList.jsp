<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
 <c:set var="nowDate" value="<%=new Date()%>"></c:set> 
      <ul id="activity">
       <c:forEach items="${gamelist }" var="item" varStatus="s">
        <li class="model-box-c">
          <dl class="clearfix">
            <dt><img width="530" height="230" style="cursor: pointer;" onclick="javascript:window.open('${ctx}/${item.url }','_target');" src="${ctx }/showActivityPic?id=${item.id}"></dt>
            <dd class="hd-icon">
             <c:if test="${item.endTime >= nowDate }">
            <img width="60" height="48" src="${ctx}/images/common/hd_s.png">
            </c:if>
             <c:if test="${item.endTime < nowDate }">
             <img width="60" height="48" src="${ctx}/images/common/hd_e.png">
             </c:if>
            </dd>
            <dd class="title">
              <h2>${item.name }</h2>
            </dd>
            <dd class="hd-c">${item.descrip }</dd>
            <dd class="date">活动时间：<fmt:formatDate value="${item.startTime }" pattern="yyyy.MM.dd"/>~<fmt:formatDate value="${item.endTime }" pattern="yyyy.MM.dd"/></dd>
            
          </dl>
        </li>
        </c:forEach>
      
      </ul>
      <div class="yPager" id="loanInvsetPager">
        <div class="inner">
                <c:forEach begin="1" end="${gamesPage }" step="1" var="item">
                <c:if test="${item == 1 || item == gamesPage}">
			<c:if test="${item != page }">
			<a href="javascript:void(0)" onclick="gamePagination(${item})">${item }</a>
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
			<a href="javascript:void(0)" onclick="gamePagination(${item})">${item }</a>
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