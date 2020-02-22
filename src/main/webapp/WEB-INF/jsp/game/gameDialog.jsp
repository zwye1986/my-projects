<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<html>
<head>
<meta charset="UTF-8" />
<title>wan商城</title>
<link href="${ctx }/css/tc.css" rel="stylesheet" />
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript">
var imgPath_session = '${ctx}' + '/images/alerts';

var account= 0;
var maxnum = 0;

$.ajaxSetup({  
    async : false  
}); 

function calNum(){
	 $.get("getUserAmount",  function(data) {
	    	account = data;
	    	maxnum = parseInt(account/${game.deposit });
		});
	 return maxnum;
}

function addCount(){
	maxnum = calNum();
	var num = document.getElementById("J_IptAmount").value;
	num = Number(num) + 1;
	if(num > maxnum){
		num = maxnum;
	}
	document.getElementById("J_IptAmount").value = num;
}

function minusCount(){
	var num = document.getElementById("J_IptAmount").value;
	num = Number(num) - 1;
	if(num < 1){
		num = 0;
	}
	document.getElementById("J_IptAmount").value = num;
}

function closeDialog(){
	parent.$.fancybox.close();
}

$(document).ready(function() {
	$(".qr").click(function(){
		if($("#J_IptAmount").val()==0){
			$.alerts.alert("游戏领取数量不为0");
			return;
		}
		window.location.href = "confirmPlay?id=${game.id}&num="+$("#J_IptAmount").val();
	});
});

</script>

</head>
<body style="width:815px; height:400px;padding: 0;margin: 0;">
	<div class="wrap">
		<ul style="float: left;">
			<li style="font-size: 24px;">游戏名称：${game.gameName }</li>
			<li>需要扣除押金:<em>${game.deposit }</em>纳币
			</li>
			<li>游戏需要点满:<em>${game.clicks }</em>次/(<em>限定${game.clicks }天内</em>)
			</li>
			<li>您的返利将是：<em>${game.reward }</em>纳币
			</li>
			<li>领取游戏数量：<span><a class="tb-stock" onclick="minusCount()">-</a> <input
					id="J_IptAmount" class="tb-text" type="text" title="请输入领取游戏数量"
					maxlength="8" value="1" > <a class="tb-increase" onclick="addCount()">﹢</a></span>

			</li>
			<li>注意：只需在用户中心点游戏，不用玩。如果没有完成任务 将会惩罚您<em>${game.punish }</em>纳币哦!
			</li>
		</ul>
		<div class="game_c" style="float: right;">游戏内容：${game.gameDescrip }</div>
		<div class="game_btn">
			<a class="qr" >确认</a><a onclick="closeDialog()" class="tc">退出</a>
		</div>
	</div>
</body>
</html>
