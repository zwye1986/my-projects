<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <link href="${ctx }/style/game.css" rel="stylesheet" />
<link href="${ctx }/css/wealthrank.css" rel="stylesheet" />
<div class="contentR fr"> 
      <!--排行榜详情-->
      
      <div class="gameR_t">
        <label>财富前100名</label>
      </div>
      <div class="wealth_c">
        <ul class="wealth_c_list">
        <c:forEach items="${result }" var="res" varStatus="s">
        	<c:if test="${(s.index+1)%3 == 1 }">
        		<li>
        	</c:if>
        	<dl class="fl">
              <dt <c:choose>
              <c:when test="${s.index == 0 }">class="Riches_list01 fl"</c:when>
              <c:when test="${s.index == 1 }">class="Riches_list02 fl"</c:when>
              <c:otherwise>class="Riches_list fl"</c:otherwise>
              </c:choose> ><label>${s.index+1 }</label></dt>
              <dd class="Riches_name fl">${res.nickName }</dd>
              <dd class="Riches_pic fl" ><c:choose>
									<c:when test="${not empty res.photo}">
										<img src="${ctx }/${res.id }/getPhoto" width="32px"
											height="32px" alt="用户头像" />
									</c:when>
									<c:otherwise>
										<img src="${ctx }/images/people.png" alt="用户头像" width="32px" height="32px"/>
									</c:otherwise>
								</c:choose></dd>
            </dl>
        	<c:if test="${(s.index+1)%3 == 0 || (s.index + 1) == fn:length(result) }">
        		</li>
        	</c:if>
        </c:forEach>
            
        </ul>
      </div>
      <div class="RB_detial"> </div>
    </div>