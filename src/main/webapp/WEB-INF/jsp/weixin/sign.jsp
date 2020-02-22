<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<title>蛙宝网签到</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no,  minimal-ui">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta name="format-detection" content="telephone=no, email=no">
<style>
* {
	margin: 0;
	padding: 0
}

body {
	background: #ecf0f1;
	color: #3c3c3c;
}

img {
	border: 0;
	vertical-align: middle;
}

html,body {
	height: 100%;
}

html {
	overflow-y: visibile
}

h2.guide-title {
	width: 100%;
	height: 60px;
	line-height: 60px;
	text-align: center;
	font-size: 16px;
	font-weight: normal
}

.btn {
	width: 100%;
	height: 15%;
	text-align: center;
	margin-top: 5%
}

.total {
	width: 100%;
	height: 48px;
	line-height: 48px;
	text-align: center;
	font-size: 16px;
}

em {
	font-style: normal;
	color: #3d72ad;
}

.list {
	width: 80%;
	height: 40%;
	margin: 0 auto;
	background: #fff;
	border-radius: 5px;
	padding: 2%;
	font-size: 0.8rem;
	line-height: 1.8;
}

ul,li {
	list-style-type: none;
}
#container div {
	padding: 10px;
	position: absolute;
	border: 0px dotted brown;
	width: 0px;
	color: #FF0000;
	cursor: pointer;
}
.sign{ width:40%; margin:0 auto;}
</style>
<script src="${ctx}/js/jquery/jquery-1.6.4.js" type="text/javascript"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var goldFallInterval;  
var goldFallStart=0;  
function initGoldFall(){  
    var length = $('div','#container').length;  
      
    if(length<4){  
        for(i=0;i<3;i++){
         $("#container").show();
         $('div','#container').clone().prependTo('#container');  
        }  
    }  
      
}  
function startGoldFall(){  
    clearInterval(goldFallInterval);  
    endCount = 0;  
    range();  
    goldFallStart = new Date().getTime();  
    goldFallInterval= setInterval(dropGoldFall,100);  
}  
  
//排列  
function range()  
{  
 var num = 1;  
 $('div','#container').each(function(i)  
 {  
  var ww = $(window).width();//窗口宽度  
  var wh = $(window).height();  
  
  var ot = -20;//从头部以上开始  
  
  $(this).css({"left":(i*(ww/5)) +"px","top":"-50px"});//距左距离保持，距上距离变化  
  num ++;  
 });  
}  
  
//降落  
function dropGoldFall()  
{  
    var now =  new Date().getTime();  
    if(now - goldFallStart >3000){  
          
        clearInterval(goldFallInterval);  
    }  
 var $objs =  $('div','#container');  
 $objs.each(function(i)  
 {  
  var wh   = $(window).height();  
  var ol   = $(this).offset().left;  
  var ot   = $(this).offset().top;  
  var rnd  = Math.round(Math.random()*100);  
  var rnd2 = Math.round(Math.random()*50);  
    
 //降落的速度  
  if(ot<=wh)//如果掉到窗口以下  
  {      
    $(this).css({"top":(ot+rnd+rnd2) +"px"});  
  }  
 });  
}  
  
  
function viewHide(){
	  $("#container").hide();
}

function fn(){  
  initGoldFall();  
  startGoldFall();  
  setInterval(viewHide, 1000);
}  

	$(document).ready(function() {
		$.post("${ctx}/getSignInByAllCount", {}, function(data) {
			$("#todayHadSignIn").html(data.dayHasSignIn);
		});

		$.post("${ctx}/weixin/${openid}/issignIn", {}, function(data) {
			if (data.flag == "havesignIn") {
				$("#ysigninstate").show();
			} else {
				$("#nsigninstate").show();
			}
		});
		
		//用户签到
		$("#nsigninstate").click(
				function() {$.post("${ctx}/weixin/${openid}/signIn",{},
									function(data) {
										if (data.flag == "havesignIn"|| data.flag == "signInsuccess") {
											fn();
											$("#nsigninstate").hide();
											$("#ysigninstate").show();
											$.post("${ctx}/getSignInByAllCount",{},
															function(data) {
																$("#todayHadSignIn").html(data.dayHasSignIn);
															});
										} else {
											$("#ysigninstate").hide();
											$("#nsigninstate").show();
										}
									});
				});
	});
</script>
</head>
<body>
	<header class="header">
		<img src="${ctx}/images/weixin/sign/title.png"
			style="text-align: center; vertical-align: middle; width: 100%">
	</header>
	<div id="main" class="wrap-page">
		<section class="page">
			<h2 class="guide-title">
				<img src="${ctx}/images/weixin/sign/icon.png" width="20" height="20" />签到次数越多，人民币领取越多
			</h2>
		</section>
		<section class="btn">

			<div class="sign" id="nsigninstate" style="display: none">
				<img src="${ctx}/images/weixin/sign/btn_n.png"
					style="margin: 0 auto; vertical-align: middle; width:100%; cursor: pointer" />
			</div>
			<div class="sign_no" style="display: none" id="ysigninstate" >
				<img src="${ctx}/images/weixin/sign/btn_wc.png" style="width: 50%; cursor: pointer" />
			</div>
		</section>
		<section class="total">
			<div class="total_sum">
				今日签到 <em id="todayHadSignIn">  </em> 人
			</div>
		</section>
		<section class="list">
			<ul class="list_c">
				<li>任务名称：每日签到</li>
				<li>任务简介：用户登录后可每天签到一次</li>
				<li>玩法简介：点击“立即签到”按钮即可</li>
				<li>
				<table style="width: 100%;" cellpadding="2" cellspacing="0"
									border="1" bordercolor="#595959">
									<tbody>
										<tr>
											<td><strong><span style="font-size: 16px;">账户总资产</span></strong>
											</td>
											<td><strong><span style="font-size: 16px;">签到奖励（%）</span></strong>
											</td>
										</tr>
										<tr>
											<td>总资产≤1万纳币</td>
											<td>&nbsp;&nbsp;0.067</td>
										</tr>
										<tr>
											<td>1万纳币&lt;总资产<span>≤2万纳币</span>
											</td>
											<td>&nbsp;&nbsp;0.073</td>
										</tr>
										<tr>
											<td>2万纳币&lt;总资产<span>≤6万纳币</span>
											</td>
											<td>&nbsp;&nbsp;0.085</td>
										</tr>
										<tr>
											<td>6万纳币&lt;总资产<span>≤10万纳币</span>
											</td>
											<td>&nbsp;&nbsp;0.094</td>
										</tr>
										<tr>
											<td>总资产&gt;10万纳币</td>
											<td>&nbsp;&nbsp;0.100</td>
										</tr>
									</tbody>
								</table>
				</li>
			</ul>
		</section>
	</div>
	
	<div id="container" style="display: none">
			<div>
				<img src="${ctx}/images/weixin/gold.png">
			</div>
		</div>
</body>