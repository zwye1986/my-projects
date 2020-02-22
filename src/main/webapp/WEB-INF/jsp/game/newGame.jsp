<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${ctx }/css/game.css" rel="stylesheet" />
<link href="${ctx }/css/page.css" rel="stylesheet">
<!--选项卡-->
<script language="javascript"> 
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
	
	
	
	
	$("#one1").click(function(){
		$("#con_one_1").html("<div class=\"yPager\" id=\"loanInvsetPager\"><div class=\"refresh\"><img src=\"${ctx }/images/common/refresh.gif\"/></div></div>");
		var param = {
				"sort":"reward",
				"sortseq" :"down"
			};
		$.get("showNewGame", param, function(data) {			
				$("#con_one_1").html(data);
		});
	});
	
	$("#one2").click(function(){
		$("#con_one_2").html("<div class=\"yPager\" id=\"loanInvsetPager\"><div class=\"refresh\"><img src=\"${ctx }/images/common/refresh.gif\"/></div></div>");
		var param = {
				"sort":"clicks",
				"sortseq" :"down"
			};
		$.get("showNewGame", param, function(data) {			
				$("#con_one_2").html(data);
		});
	});
	
	$("#one3").click(function(){
		$("#con_one_3").html("<div class=\"yPager\" id=\"loanInvsetPager\"><div class=\"refresh\"><img src=\"${ctx }/images/common/refresh.gif\"/></div></div>");
		var param = {
				"sort":"looks",
				"sortseq" :"down"
			};
		$.get("showNewGame", param, function(data) {			
				$("#con_one_3").html(data);
		});
	});
	
	$("#one4").click(function(){
		$("#con_one_4").html("<div class=\"yPager\" id=\"loanInvsetPager\"><div class=\"refresh\"><img src=\"${ctx }/images/common/refresh.gif\"/></div></div>");
		var param = {
				"sort":"deposit",
				"sortseq" :"down"
			};
		$.get("showNewGame", param, function(data) {			
				$("#con_one_4").html(data);
		});
	});
	
	$("#one1").trigger("click");
	
});

gamecommend();

function gamecommend(){
	
	$.get("showCommendGame",function(data) {
		$("#commend").html(data);
});
}

//游戏中心分页
function gamePagination(sort, page) {
	
	var param = {
		sort : sort,
		sortseq : "down",
		page : page
	};
	
	$.get("showNewGame", param, function(data) {
		$("#con_one_1").html("<div class=\"yPager\" id=\"loanInvsetPager\"><div class=\"refresh\"><img src=\"${ctx }/images/common/refresh.gif\"/></div></div>");
		$("#con_one_2").html("<div class=\"yPager\" id=\"loanInvsetPager\"><div class=\"refresh\"><img src=\"${ctx }/images/common/refresh.gif\"/></div></div>");
		$("#con_one_3").html("<div class=\"yPager\" id=\"loanInvsetPager\"><div class=\"refresh\"><img src=\"${ctx }/images/common/refresh.gif\"/></div></div>");
		$("#con_one_4").html("<div class=\"yPager\" id=\"loanInvsetPager\"><div class=\"refresh\"><img src=\"${ctx }/images/common/refresh.gif\"/></div></div>");
		if (sort == 'reward') {	
			$("#con_one_1").html(data);
		} else if (sort == 'clicks') {
			$("#con_one_2").html(data);
		}  else if (sort == 'looks') {
			$("#con_one_3").html(data);
		}  else if (sort == 'deposit') {
			$("#con_one_4").html(data);
		} 

	});
}
</script>
<div class="content"> 
<!--game-top start-->
<div class="game-top">
<div class="game-top-c clearfix">
<div class="ctr-left"><img width="27" height="43" onclick="gamecommend()" src="${ctx }/images/common/ctr-left-icon.png"/></div>
<div class="ctr-center">
 <ul id="commend" class="item1 itemlist">
    	
        </ul>
</div>
<div class="ctr-right"><img width="27" height="43" onclick="gamecommend()" src="${ctx }/images/common/ctr-right-icon.png"/></div>
</div>
</div>
<!--game-top end-->
  <!--tab start-->
  <div class="tab_box">
    <div class="lyz_tab_title">
      <div class="pro_con111">
        <ul>
          <li onclick="setTab2('one',1,4)" id="one1" class="hover">返利</li>
          <li onclick="setTab2('one',2,4)" id="one2">周期</li>
          <li onclick="setTab2('one',3,4)" id="one3">人气</li>
          <li onclick="setTab2('one',4,4)" id="one4" class="last">押金</li>
        </ul>
      </div>
    </div>
    <div class="lyz_tab_c clearfix">
      <div id="con_one_1" class="hover">

     

       </div>
      <div id="con_one_2" class="hover" style="display: none">
      <ul class="game-rank">
      <li>
      <dl>
      <dt class="ml20">传动DNF 2</dt>
      <dd class="ml20">押金：10000纳币<dd>
      <dd class="ml20">周期：15次</dd>
      <dd class="ml20">返利：140.0纳币</dd>
      <dd class="gm-img"><a href="/">
<img width="210" height="129" src="${ctx }/images/picfl01.jpg" alt="半月存 ">
</a></dd>
<dd class="gm-btn"><div class="save-button">
                  <input type="button" value="点游戏" name="点游戏">
                </div></dd>
      </dl>
      
      </li></ul>
      </div>
      <div id="con_one_3" class="hover" style="display: none">
      <ul class="game-rank">
      <li>
      <dl>
      <dt class="ml20">传动DNF 2</dt>
      <dd class="ml20">押金：10000纳币<dd>
      <dd class="ml20">周期：15次</dd>
      <dd class="ml20">返利：140.0纳币</dd>
      <dd class="gm-img"><a href="/">
<img width="210" height="129" src="${ctx }/images/picfl01.jpg" alt="半月存 ">
</a></dd>
<dd class="gm-btn"><div class="save-button">
                  <input type="button" value="点游戏" name="点游戏">
                </div></dd>
      </dl>
      
      </li>
      <li>
      <dl>
      <dt class="ml20">传动DNF 2</dt>
      <dd class="ml20">押金：10000纳币<dd>
      <dd class="ml20">周期：15次</dd>
      <dd class="ml20">返利：140.0纳币</dd>
      <dd class="gm-img"><a href="/">
<img width="210" height="129" src="${ctx }/images/picfl01.jpg" alt="半月存 ">
</a></dd>
<dd class="gm-btn"><div class="save-button">
                  <input type="button" value="点游戏" name="点游戏">
                </div></dd>
      </dl>
      
      </li>
      <li>
      <dl>
      <dt class="ml20">传动DNF 2</dt>
      <dd class="ml20">押金：10000纳币<dd>
      <dd class="ml20">周期：15次</dd>
      <dd class="ml20">返利：140.0纳币</dd>
      <dd class="gm-img"><a href="/">
<img width="210" height="129" src="${ctx }/images/picfl01.jpg" alt="半月存 ">
</a></dd>
<dd class="gm-btn"><div class="save-button">
                  <input type="button" value="点游戏" name="点游戏">
                </div></dd>
      </dl>
      
      </li>
      </ul>
      </div>
      <div id="con_one_4" class="hover" style="display: none">
      <ul class="game-rank">
      <li>
      <dl>
      <dt class="ml20">传动DNF 2</dt>
      <dd class="ml20">押金：10000纳币<dd>
      <dd class="ml20">周期：15次</dd>
      <dd class="ml20">返利：140.0纳币</dd>
      <dd class="gm-img"><a href="/">
<img width="210" height="129" src="${ctx }/images/picfl01.jpg" alt="半月存 ">
</a></dd>
<dd class="gm-btn"><div class="save-button">
                  <input type="button" value="点游戏" name="点游戏">
                </div></dd>
      </dl>
      
      </li>
      <li>
      <dl>
      <dt class="ml20">传动DNF 2</dt>
      <dd class="ml20">押金：10000纳币<dd>
      <dd class="ml20">周期：15次</dd>
      <dd class="ml20">返利：140.0纳币</dd>
      <dd class="gm-img"><a href="/">
<img width="210" height="129" src="${ctx }/images/picfl01.jpg" alt="半月存 ">
</a></dd>
<dd class="gm-btn"><div class="save-button">
                  <input type="button" value="点游戏" name="点游戏">
                </div></dd>
      </dl>
      
      </li>
      </ul>
      </div>
    </div>
  </div>
  <!--tab end--> 
</div>
