<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/11/14
  Time: 2:22 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 <script type="text/javascript">

$(document).ready(function() {
	var pathname=window.location.pathname;
    if(pathname.indexOf("getMyInvite", 0)>0){
		if(getQueryString("type")=="mycode"){
			$("#mycode").addClass("current");
		}else if(getQueryString("type")=="myInviteUser"){
			$("#myInviteUser").addClass("current");
		}else if(getQueryString("type")=="getMyInviteList"){
			$("#getMyInviteList").addClass("current");
		}else{
			$("#mycode").addClass("current");
		}
    	
	}else if(pathname.indexOf("obtainlist", 0)>0){
		if(getQueryString("type")=="ing"){
			$("#ing").addClass("current");
		}else if(getQueryString("type")=="over"){
			$("#over").addClass("current");
		}else if(getQueryString("type")=="lottery"){
			$("#lottery").addClass("current");
		}else{
			$("#ing").addClass("current");
		}
    	
	}else if(pathname.indexOf("getSignedDate", 0)>0){
			$("#getSignedDate").addClass("current");
	}
	
});

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }
</script>
<div id="sidebar">
    <div class="sidebar-item account-status">
        <div class="mrtx">
            <a>
                <img width="90" height="90"  src="${ctx }/${PHOTO_PATH == null ? 'images/people.png' : PHOTO_PATH}" />
            </a>
        </div>
        <h2 title="用户名" style="width:73px; float:right; text-align:center;overflow: hidden;white-space: nowrap;">
            <sec:authentication property="principal.nickName" />
        </h2>

        <div class="tx_new">
            <a href="###" >
                <input type="button" value="上传新头像" name="上传新头像" class="btn" onclick="javascript:window.location.href='${ctx}/user/manager/upload'"/>
            </a>
            <a href="###">
                <input type="button" value="完善个人资料" name="完善个人资料" class="btn" onclick="javascript:window.location.href='${ctx}/user/manager/userDetail'"/>
            </a>
        </div>
    </div>
    <dl class="apply-menu">
        <dt>个人管理</dt>
        <dd <c:if test="${item eq 1}">class="current"</c:if>> <a href="${ctx}/user/manager/userDetail" target="_self"><i class="nav-icons icons"></i>个人中心</a> </dd>

    </dl>
    <dl class="apply-menu">
        <dt>我的邀请</dt>
        <dd id="mycode"> <a href="${ctx}/user/account/getMyInvite?type=mycode"><i class="nav-icons icons"></i>我要邀请</a> </dd>
        <dd id="getMyInviteList"> <a href="${ctx}/user/account/getMyInvite?type=getMyInviteList"><i class="nav-icons icons"></i>邀请奖励</a> </dd>
        <dd id="myInviteUser"> <a href="${ctx}/user/account/getMyInvite?type=myInviteUser"><i class="nav-icons icons"></i>被邀请人</a> </dd>
    </dl>
    <dl class="apply-menu">
        <dt>帐户管理</dt>
        <dd <c:if test="${item eq 3}">class="current"</c:if>> <a href="${ctx }/usertaskDetail.html"><i class="nav-icons icons"></i>任务明细</a> </dd>
        <dd <c:if test="${item eq 4}">class="current"</c:if>> <a href="${ctx}/user/1/dealDetail" target="_self"><i class="nav-icons icons"></i>帐户明细</a> </dd>
        <dd <c:if test="${item eq 5}">class="current"</c:if>> <a href="${ctx }/user/account/recharge"><i class="nav-icons icons"></i>充值</a> </dd>
        <dd <c:if test="${item eq 6}">class="current"</c:if>> <a href="${ctx }/user/account/withdrawal"><i class="nav-icons icons"></i>提现</a> </dd>
    </dl>
    <dl class="apply-menu">
        <dt>意见反馈</dt>
        <dd <c:if test="${item eq 7}">class="current"</c:if>> <a href="${ctx }/feedBack"><i class="nav-icons icons"></i>意见反馈</a> </dd>
        <dd <c:if test="${item eq 8}">class="current"</c:if>> <a href="${ctx }/user/feedbackList.html"><i class="nav-icons icons"></i>我的意见</a> </dd>
    </dl>
    <dl class="apply-menu">
        <dt>积分管理</dt>
        <dd id="ing"> <a href="${ctx}/user/credits/obtainlist.html?type=ing"><i class="nav-icons icons"></i>获取记录</a> </dd>
        <dd id="over"> <a href="${ctx}/user/credits/obtainlist.html?type=over"><i class="nav-icons icons"></i>兑换记录</a> </dd>
        <dd id="lottery"> <a href="${ctx}/user/integralLotteryLog.html?type=lottery"><i class="nav-icons icons"></i>抽奖记录</a> </dd>
    </dl>
    <dl class="apply-menu">
        <dt>签到管理</dt>
        <dd id="getSignedDate"> <a href="${ctx}/user/account/getSignedDate"><i class="nav-icons icons"></i>签到查询</a> </dd>

    </dl>
</div>
