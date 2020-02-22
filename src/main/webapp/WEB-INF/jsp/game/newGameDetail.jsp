<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link href="${ctx }/css/game.css" rel="stylesheet" />
<link href="${ctx }/css/tab.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/kxbdSuperMarquee.js"></script>
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
    <!--选项卡-->
<script language="javascript"> 
var account= 0;
var maxnum = 0;
$.ajaxSetup({  
    async : false,
    cache : false
}); 
<!--
function setTab2(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}
//-->
$(function(){
	$('#marquee6').kxbdSuperMarquee({
		isMarquee:true,
		isEqual:false,
		scrollDelay:20,
		controlBtn:{up:'#goUM',down:'#goDM'},
		direction:'up'
	});
	
	//用户余额
	$.get("${ctx}/getUserAmount.html",  function(data) {	
	$("#account").html(data);
	});
	
	$("#qr").click(function(){
		if(getUser()==0){
			window.open("${ctx}/login.html");
			return;
		}
		if($("#J_IptAmount").val()==0){
			layer.msg('任务领取数量不能为0');
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
					layer.msg('会员游戏需要开通会员领取');
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
	
	$(".item1 li").hover(
			function(){
				var that=this;	
				item1Timer=setTimeout(function(){
					$(that).find("div").animate({"top":0,"height":129},300,function(){
						$(that).find("p").fadeIn(200);
					});
				},100);
			},
			function(){
				var that=this;	
				clearTimeout(item1Timer);
				$(that).find("p").fadeOut(200);
				$(that).find("div").animate({"top":100,"height":30},300);
			}
		);
	
	
});

function minusCount(){
	
	var num = document.getElementById("J_IptAmount").value;
	num = Number(num) - 1;
	if(num < 1 ){
		num = 0;
	}
	document.getElementById("J_IptAmount").value = num;
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

function checkInt(obj)
{
	 var re = /^[0-9]*[1-9][0-9]*$/ ;
     if (!re.test(obj))
    {
    	layer.msg('领取数量错误');
    	maxnum = calNum();
    	if(maxnum>1){
    		document.getElementById("J_IptAmount").value = 1;
    	}else{
    		document.getElementById("J_IptAmount").value = 0;
    	}
    	
        return false;
     }else{
    	 return true;
     }
}  



</script>       
<!--content start-->
<div class="content">
<!--content top start-->
<div class="game_title">游戏详情<i class="title icons"></i></div>
<div class="game_c clearfix">
<div class="game_cl fl">
<div class="game_img  fl">
<a href="/" >
<img width="210" height="129" alt="${game.gameName } " src="${ctx }/showRectangleGamePic?id=${game.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${game.gameName }">
</a>
<div class="game_tit">${game.gameName }</div>
</div>
<dl class="fr">
<dd class="ye">帐户余额:<em class="ye_money" id="account">10000</em>纳币</dd>
<dd>扣除押金:<em>${game.deposit }</em>纳币</dd>
<dd>游戏周期:<em>${game.clicks } </em>次 / （限定${game.clicks }天内）</dd>
<dd>即将返利:<em style="font-size:24px">${game.reward }</em> 纳币</dd>
<dd class="total btm_no">领取数量:<input id="J_IptAmount" class="input" type="text" name="userName" onkeyup="checkInt(this.value)"  maxlength="32" value="1"><div class="ctr_spinner "><i class="spinner_add selected" onclick="addCount()"></i><i class="spinner_plus" onclick="minusCount()"></i></div></dd>
<dd class="btm_no"><div class="dh-button">
<input type="button" id="qr" name="领取游戏" value="领取游戏">
</div></dd>
</dl>
</div>
<div class="game_cr fr">
<div class="players">玩过此游戏的玩家</div>
<div id="marquee6" style="height:300px; overflow:hidden">
<ul >
<c:forEach items="${otherUsers }" var="item">
<li><dl><dt><img width="64" height="64" src="${ctx }/${item.id }/getPhoto" onerror="this.src='${ctx }/images/people.png'" alt="${item.nickName }"></dt><dd class="col_1">${item.nickName }</dd></dl></li>
</c:forEach>
</ul>
</div>
</div>
</div>
<!--content top end-->
<!--tab_start-->
<div class="tab_box">
<div class="lyz_tab_title">
    <div class="pro_con111">
      <ul>
        <li onclick="setTab2('one',1,2)" id="one1" class="hover">推荐游戏</li>
        
      </ul>
    </div>
  </div>
  
  <div class="lyz_tab_c clearfix">
  <div id="con_one_1" class="hover">
  <ul class="item1">
  <c:forEach items="${recommendGames }" var="item" varStatus="s">
  <c:if test="${s.index<8 }">
    	<li >
			<img onclick="javascript:window.open('${ctx }/${item.id}/showGameDetail.html','_blank');" width="210" height="129" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${item.gameName }">
			<div onclick="javascript:window.open('${ctx }/${item.id}/showGameDetail.html','_blank');" class="hotlist" style="top: 100px; height: 30px;">
            	<h3>${item.gameName }</h3>
	            <dl>
                <dd>押金：${item.deposit }纳币</dd>
                <dd>周期：${item.clicks }次</dd>
                <dd>返利：${item.reward }纳币</dd>
                </dl>
            </div>
		</li>
		</c:if>
		</c:forEach>
        </ul>
  </div>
  <div id="con_one_2" class="hover" style="display: none">
  <ul class="item1">
  <c:forEach items="${otherGames }" var="item">
    	<li>
			<img onclick="javascript:window.open('${ctx }/${item.id}/showGameDetail.html','_blank');" width="210" height="129" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'" alt="${item.gameName }">
			<div onclick="javascript:window.open('${ctx }/${item.id}/showGameDetail.html','_blank');" class="hotlist" style="top: 100px; height: 30px;">
            	<h3>${item.gameName }</h3>
	            <dl>
                <dd>押金：${item.deposit }纳币</dd>
                <dd>周期：${item.clicks }次</dd>
                <dd>返利：${item.reward }纳币</dd>
                </dl>
            </div>
		</li>
		</c:forEach>
        </ul>
  </div>
  
  </div>
</div>
<!--tab_end-->
</div>
<!--content end-->
<a style="display: none" id="fancyBox"></a>