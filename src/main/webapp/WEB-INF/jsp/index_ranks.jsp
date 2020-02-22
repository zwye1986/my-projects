<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/25/14
  Time: 3:42 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<div class="announce_title">
    <h4 class="fl">排行榜</h4>
    <a class="more icons" target="_blank" href="${ctx}/rank.html" rel="nofollow">更多</a>
</div>
<!-- rank start -->
<div class="rank_wrapper clearfix">
    <div class="cf_rank  fl">
        <h5>
            <i class="cf_icons icons"></i>
            财富排行榜
        </h5>
        <c:forEach var="tops" items="${wealthTop}">
            <ul>
                <li class="col_1">
                    ${tops.nickName == "" ? tops.mobileNumber : tops.nickName}
                </li>
                <li class="col_2">
                  
                       
                            <img src="${ctx }/${tops.id }/getPhoto" width="32px" onerror="this.src='${ctx }/images/people.png'"
                                 height="32px" />
                      
                          
                  
                </li>
            </ul>
        </c:forEach>

    </div>
    <div class="sr_rank  fl">
        <h5> <i class="sr_icons icons"></i>收入排行榜</h5>
        <c:forEach items="${incomeTop}" var="tops">
            <ul>
                <li class="col_1">${tops.nickName == "" ? tops.mobileNumber : tops.nickName}</li>
                <li class="col_2">
                   
                            <img src="${ctx }/${tops.id }/getPhoto" width="32px" alt="头像" onerror="this.src='${ctx }/images/people.png'"
                                 height="32px" />
                    
                </li>
            </ul>
        </c:forEach>

    </div>
    <div class="jb_rank  fl" >
        <h5> <i class="jb_icons icons"></i>级别排行榜</h5>
        <c:forEach var="tops" items="${levelTop }">
            <ul>
                <li class="col_1 mr50">${tops.nickName == "" ? tops.mobileNumber : tops.nickName}</li>
                <li class="col_3 col_c">
                    <ul class="col_ff" style="margin: 0">
                        <yma:LevelConvertIcoTag level="${tops.level}" vipTag="${tops.vipTag} " />
                    </ul>
                </li>
            </ul>
        </c:forEach>

    </div>

     <div class="tj_rank  fl" >
        <h5> <i class="tj_icons icons"></i>活跃排行榜</h5>
        <c:forEach var="tops" items="${activeTop}">
            <ul>
                <li class="col_1 fl">${tops.nickName}</li>
                <li class="col_3 fl" style="width:120px">
                     <span class="tj_total">
                      <em>登陆次数:${tops.loginCount }次</em>
                      </span>
                </li>
            </ul>
        </c:forEach>
    </div> 

    <div class="btn_ctrl"></div>
</div>
