<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
	/*初始化加载*/
	$(document).ready(function() {
		$.cookie.defaults = {
			path : '/'
		};

		$(".GRankList").find("li").click(function() {
			$.cookie('leftHref', null);
			$.cookie('leftHref', this.id);
		});

		$("#" + $.cookie('leftHref')).addClass("now");
	});
</script>


<div class="contentL fl">
	<div class="contentLPic">
		<img
			src="${ctx }/${PHOTO_PATH == null ? 'images/people.png' : PHOTO_PATH}"
			width="160px" height="160px" alt="用户头像">
		<p class=" pc_nc"><sec:authentication
										property="principal.nickName" />
		</p>
		<p
				class="pc_tx"><a href="${ctx }/user/2/manager" style="color: #555">上传新头像</a></p>
				<p class="pc_zl"><a href="${ctx }/user/1/manager"
			style="color: #555">完善个人资料</a></p>
	</div>
	<!--用户中心-->

	<div class="GRankC">
		<div class="GRankTop"></div>
		<div class="GRankTitle">用户中心</div>
		<div class="pcTitle">
			<a><img src="${ctx }/images/user/pc.png"
				style="float: left; display: inline-block; padding-top: 5px;" alt="个人管理" >个人管理</a>
		</div>
		<ul class="GRankList">
			<li id="personLi"><a href="${ctx }/user/1/manager" id="personLi">
					<img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;">个人中心
			</a></li>
		</ul>
		
		
		<div class="feedTitle">
			<a href="${ctx }/user/account/getSignedDate"> <img
				style="float: left; display: inline-block; padding-top: 5px;"
				src="${ctx }/images/user/share.png"  alt="我的邀请"/>我的邀请
			</a>
		</div>
		<ul class="GRankList">
			<li id="myinvite"><a href="${ctx }/user/account/getMyInvite"
				id="myinvite"><img src="${ctx }/images/user/icon01.png" alt="我要邀请"
					style="float: left; display: inline-block; padding-top: 5px;">
					我要邀请</a></li>

		</ul>
		<ul class="GRankList">
		
			<li id="myinviteList"><a href="${ctx }/user/account/getMyInviteList"
				id="myinviteList"><img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="邀请奖励">
					邀请奖励</a></li>

		</ul>
		
		<ul class="GRankList">
		
			<li id="getMyInviteUser"><a href="${ctx }/user/account/getMyInviteUser"
				id="getMyInviteUser"><img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="被邀请人">
					被邀请人</a></li>

		</ul>
		
		<div class="taskTitle">
			<a> <img src="${ctx }/images/user/account.png"
				style="float: left; display: inline-block; padding-top: 5px;" alt="帐户管理" >帐户管理
			</a>
		</div>
		<ul class="GRankList">
			<li id="taskLi"><a href="${ctx }/user/account/manager?cate=task"
				id="taskLi"> <img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="任务明细">任务明细
			</a></li>
			<li id="accountLi"><a href="${ctx }/user/account/manager"
				id="accountLi"> <img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="帐户明细">帐户明细
			</a></li>
			<li id="rechargeLi"><a href="${ctx }/user/account/recharge"
				id="rechargeLi"> <img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="充值" >充值
			</a></li>
			<li id="withdrawalLi"><a href="${ctx }/user/account/withdrawal"
				id="withdrawalLi"> <img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="提现">提现
			</a></li>
			<li id="exchangeLi"><a href="${ctx }/user/account/exchange"
				id="exchangeLi"> <img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="我要兑换">我要兑换
			</a></li>

		</ul>
		<div class="feedTitle">
			<a href="${ctx }/feedBack">
			<img src="${ctx }/images/user/feedback.png"
				style="float: left; display: inline-block; padding-top: 5px;" alt="意见反馈">
				意见反馈</a>
		</div>
		<ul class="GRankList">
			<li  id="fbLi"><a href="${ctx }/feedBack" id="fbLi"><img
					src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="意见反馈">
					意见反馈</a></li>

		</ul>
		<ul class="GRankList">
			<li  id="myfk"><a href="${ctx }/user/feedbackList.html" id="myfk"><img
					src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="我的反馈">
					我的反馈</a></li>

		</ul>
		<div class="feedTitle">
		  <a href="${ctx }/order/manager/orderlist.html">
		  <img  style="float:left; display:inline-block; padding-top:5px;"src="${ctx }/images/coolpos/order.png" alt="团购订单"/>
		      团购订单</a>
		      </div>
        <ul class="GRankList">
			<li  id="myorder"><a href="${ctx }/order/manager/orderlist.html" 
			     id="myorder"><img
					src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="我的订单">
					我的订单</a></li>

		</ul>
        	<div class="feedTitle"><a href="${ctx }/user/credits/obtainlist.html">
		<img  style="float:left; display:inline-block; padding-top:5px;"src="${ctx }/images/jf.png" alt="积分管理"/>积分管理</a></div>
        <ul class="GRankList">
			<li  id="obtainlist"><a href="${ctx }/user/credits/obtainlist.html" id="obtainlist"><img
					src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="获取记录">
					获取记录</a></li>
			<li  id="convertlist"><a href="${ctx }/user/credits/obtainlist.html?type=over" id="convertlist"><img
					src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="兑换记录">
					兑换记录</a></li>		

		</ul>
		
		<div class="pcTitle">
			<a><img src="${ctx }/images/user/pc.png"
				style="float: left; display: inline-block; padding-top: 5px;" alt="我的众筹" >我的众筹</a>
		</div>
		<ul class="GRankList">
			<li id="myProjectIvest"><a href="${ctx }/user/project/manager" id="myProjectIvest">
					<img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="项目支持" >项目支持
			</a></li>
		</ul>
		
		
		<div class="feedTitle">
			<a href="${ctx }/user/account/getSignedDate"> <img
				style="float: left; display: inline-block; padding-top: 5px;"
				src="${ctx }/images/user/sign.png" alt="签到管理" />签到管理
			</a>
		</div>
		<ul class="GRankList">
			<li id="getSignedDate"><a href="${ctx }/user/account/getSignedDate"
				id="getSignedDate"><img src="${ctx }/images/user/icon01.png"
					style="float: left; display: inline-block; padding-top: 5px;" alt="签到查询">
					签到查询</a></li>

		</ul>
		
		
		
		
		
	</div>



	<div class="GRankBottom"></div>
</div>