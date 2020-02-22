<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="${ctx }/css/game.css" rel="stylesheet" />
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/game.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript">
var account= 0;
var maxnum = 0;

$.ajaxSetup({  
    async : false,
    cache : false
}); 

$(function(){
	
	//用户余额
	$.get("${ctx}/getUserAmount.html",  function(data) {	
	$("#account").html(data);
	});
});





function calNum(){
	 $.get("${ctx}/getUserAmount.html",  function(data) {
	    	account = data;
	    	maxnum = parseInt(account/${game.deposit });
	    	$("#account").html(data);
		});
	 return maxnum;
}

function getUser(){
	var restr = 1;
	 $.get("${ctx}/getUser.html",  function(data) {
		 restr = data;
		 
		});
	 return restr;
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
	if(num < 1 ){
		num = 0;
	}
	document.getElementById("J_IptAmount").value = num;
}

function closeDialog(){
	parent.$.fancybox.close();
}

$(document).ready(function() {
	$.post("${ctx}/getRest",{},function(data){
		
		$("#lqyx_total").empty();
		$("#lqyx_total").html(data.rest);
	});
	$(".qr").click(function(){
		if(getUser()==0){
			window.open("${ctx}/login.html");
			return;
		}
		if($("#J_IptAmount").val()==0){
			top.$.alerts.alert("游戏领取数量不为0");
			return;
		}
		maxnum = calNum();
		if(maxnum < 1){
			document.getElementById("J_IptAmount").value = 0;
		}else{
			 var param = {
						id : '${game.id}'
					};
			$.get("${ctx}/isMember.html?time="+new Date(),param,function(data) {
				
				if(data == 0){
					top.$.alerts.alert("会员游戏需要开通会员领取");
					return false;
				}else{
		
		$("a#fancyBox").attr('href', '${ctx}/confirmPlay?id=${game.id}&num='+$("#J_IptAmount").val()+'&time='+new Date()).fancybox({
			'padding' :  10,
			'margin' : 10,
			'width'				:  815,
			'height'			: 410,
			'autoScale'			: false,
			 'transitionIn'  : 'elastic',  
		      'transitionOut' : 'elastic'  ,
			'type'				: 'iframe',
			'scrolling' : 'no',
			'onClosed' :function(){$.post("${ctx}/getRest",{},function(data){
				$("#lqyx_total").empty();
				$("#lqyx_total").html(data.rest);
			});}

		    
		}).click();
		
				}
			});
		
		}
		
	});
});

</script>
 <div class="gTitlewrapper">
 <div class="gTitle_t">
 <p>领取游戏</p>
 </div>
 
 </div>
    <!--content-->
    <div id="content">
      <div class="contentL fl"> 
        <!--推荐游戏-->
        
        <div class="subnav commbox fl" style="margin-bottom: 10px">
          <div class="GRankTop"></div>
          <div class="commbox_hd clearfix">推荐游戏 </div>
          <ul class="commbox_bd" id="subnav_list">
          <c:forEach items="${leftGameRanks }" var="item" varStatus="s">
          <c:if test="${s.index < 10 }">
          <c:if test="${s.index < 3 }">
          <li> <span class="fl" style="background:#F16244;">
          </c:if>
          <c:if test="${s.index > 2 && s.index < 9 }">
          <li> <span class="fl">
          </c:if>
          <c:if test="${s.index == 9 }">
          <li  style="border-bottom:none;"> <span class="fl">
          </c:if>
          ${s.index+1 }</span>
              <p class="fl" style="cursor: pointer;"  ><a style="color:#555;" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank">${item.gameName }</a></p>
              <em class="fr">&gt;</em>
              <div class="subnav_hover"> <img class="fl" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${game.gameName }" /> <a class="subnav_home fl" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank" >${item.gameName }</a> <a class="subnav_go fl" href="${ctx }/${item.id}/showGameDetail.html"  target="_blank">＊返利${item.reward }</a> </div>
            </li>
            </c:if>
          </c:forEach>
           
           
          </ul>
          <div class="GRankB" style="margin-bottom: 10px"></div>
        </div>
        
      </div>
      <!--右侧-->
      <div class="contentR fr"> 
        <!-- 游戏详情页面-->
        
        <div class="RTitle"></div>
        <div class="RC_detial">
          <div class="Gdetial_top">帐户余额:<em style="color:#f16244; margin-left:5px;" ><a id="account">0</a></em>纳币 <a style="color:#f16244" class="fr"></a></div>
          <div class="game_detial">
          <div class="game_img fl"> <a href="#" target="_blank"> <img width="180" height="115" src="${ctx }/showRectangleGamePic?id=${game.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${game.gameName }"> </a>
            <p class="game_title">${game.gameName }</p>
          </div>
          <dl>
            <dt class="game_title">
              <p href="#" >需要扣除押金：<em>${game.deposit }</em>纳币</p>
            </dt>
            <dd class="game_content">
              <p href="#" >游戏需要点满：<em>${game.clicks }</em>次／（限定${game.clicks }天内）</p>
            </dd>
            <dd >
              <p href="#" >您的返利将是：<em >${game.reward }</em>纳币</p>
            </dd>
            <dd >
              <p href="#">领取游戏数量：<span><a class="tb-stock" onclick="minusCount()">-</a>
<input id="J_IptAmount" class="tb-text" type="text" title="请输入领取游戏数量"  maxlength="8" value="1">
<a class="tb-increase" onclick="addCount()">﹢</a>

</span></p>
            </dd>
            <dd>
              <p href="javascript:void(0);">未完成游戏任务惩罚：<em>${game.punish }</em>纳币</p>
            </dd>
            <dd class="detial_btn"><a class="qr" href="javascript:void(0);" ></a> </dd>
            <c:if test="${game.id == xnid }">
             <dd class="lqyx_total" id="lqyx_total" style="position:absolute;">0</dd>
            </c:if>
          </dl>
           <c:choose>
          <c:when test="${game.id == '76019bc0-d4bf-4e87-852a-b9c54e15256f'}">
           <div class="gdetial_c"><em style=" font-size:18px;font-size: 30px;">贺：</em>
          </c:when>
          <c:otherwise>
           <div class="gdetial_c"><em style=" font-size:18px;">游戏内容：</em>
          </c:otherwise>
          </c:choose>
         
          <c:choose>
          <c:when test="${game.id == '76019bc0-d4bf-4e87-852a-b9c54e15256f'}">
          <a style="color:#555;font-size: 30px;">蛙宝网一周岁啦！</a>
          </c:when>
          <c:otherwise>
          ${game.gameDescrip }
          </c:otherwise>
          </c:choose>
          </div>
           <div class="gdetial_pic"><em style=" font-size:18px;"></em></div>
           
           <c:if test="${game.id != '76019bc0-d4bf-4e87-852a-b9c54e15256f'}">
           <div class="player_game">
          <div class="player_gameT">玩过这个游戏的玩家</div>
          <ul class="player_gameC">
          <c:forEach items="${userList }" var="item" varStatus="s">
          <c:if test="${s.index < 26 }">
          <li><a href="javascript:void(0);"><img width="64" height="64" src="${ctx }/${item.id }/getPhoto" onerror="this.src='${ctx }/images/user/touxiang.png'" alt="用户头像"/></a><em>${item.nickName }</em></li>
          </c:if>
          </c:forEach>
         
          </ul>
           </div>
           <div class="game_play">
           <div class="game_playT">玩家还玩过</div>
           <ul class="game_playC">
           <c:forEach items="${gameList }" var="item">
             <li><a href="${ctx }/${item.id}/showGameDetail.html"  target="_blank">
<img width="180" height="115" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${item.gameName }">
</a><em>${item.gameName }</em></li>
           </c:forEach>
        
            
          </ul>
           </div>
           </c:if>
           
          </div>
        </div>
        <div class="RB_detial"> </div>
      </div>
    </div>