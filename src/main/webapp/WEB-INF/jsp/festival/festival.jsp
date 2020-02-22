<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>蛙宝网圣诞/元旦感恩回馈活动</title>
<link href="${ctx }/css/base.css" rel="stylesheet" />
<link href="${ctx }/css/footer.css" rel="stylesheet" />
<link href="${ctx }/css/festival.css" rel="stylesheet" />
<link href="${ctx }/css/hn.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/jqueryui.css" />
<link href="${ctx }/css/top.css" rel="stylesheet"  type="text/css"/>
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/jqueryui.js"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>

<!--获奖名单无缝滚动-->
<script>
window.onload=function()
{
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];
	var aLi=oDiv.getElementsByTagName('li');
	
	if(aLi.length > 0){
	
	oUl.innerHTML=oUl.innerHTML+oUl.innerHTML;//ul内部东西复制一份
	oUl.style.width=aLi[0].offsetWidth*aLi.length+'px';
	setInterval(function(){      //开启定时器
	  if(oUl.offsetLeft<-oUl.offsetWidth/2)
	  {
		  oUl.style.left='0';
		  
		}
		oUl.style.left=oUl.offsetLeft-2+'px';
		
		},30);
   }
}

</script>
<!--鼠标放上后出现暗影效果-->

<script type="text/javascript">

Date.prototype.format = function(format)
{
 var o = {
 "M+" : this.getMonth()+1, //month
 "d+" : this.getDate(),    //day
 "h+" : this.getHours(),   //hour
 "m+" : this.getMinutes(), //minute
 "s+" : this.getSeconds(), //second
 "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
 "S" : this.getMilliseconds() //millisecond
 }
 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
 for(var k in o)if(new RegExp("("+ k +")").test(format))
 format = format.replace(RegExp.$1,
 RegExp.$1.length==1 ? o[k] :
 ("00"+ o[k]).substr((""+ o[k]).length));
 return format;
}
 
function isEmpty(s) {
	return ((s == undefined || s == null || s == "") ? true : false);
}
 
$(document).ready(function(){
	
	$(".section ul li .rsp").hide();
	
	$(".section	 ul li").hover(function(){
		$(this).find(".rsp").stop().fadeTo(500,0.5)
		$(this).find(".text").stop().animate({left:'0'}, {duration: 500})
	},function(){
		$(this).find(".rsp").stop().fadeTo(500,0)
		$(this).find(".text").stop().animate({left:'318'}, {duration: "fast"})
		$(this).find(".text").animate({left:'-318'}, {duration: 0})
	});
	
	$("#predate").click(function(){
		var str =$("#gettaskListDate").html();
		str = str.replace(/-/g,"/");
		var date = new Date(str );
		date.setDate(date.getDate()-1); 
		var datestr=date.format('yyyy-MM-dd');
		if(date<new Date("2013-12-24")){
			return false;
		}else{
		   $("#gettaskListDate").html(date.format('yyyy-MM-dd'));
		
		   getTopTaskList(datestr);
		}
	});
	
	if(new Date()>new Date("2014-01-01")){
		var str="2014-01-01";
		str = str.replace(/-/g,"/");
		var date = new Date(str );
		var datestr=date.format('yyyy-MM-dd');
		getTopTaskList(datestr);
	}
	
	$("#nextdate").click(function(){
		var str =$("#gettaskListDate").html();
		str = str.replace(/-/g,"/");
		var date = new Date(str );
		date.setDate(date.getDate()+1); 
		var datestr=date.format('yyyy-MM-dd');
		
		if(date>new Date("2014-01-01")){
			return false;
		}else{
		    $("#gettaskListDate").html(datestr);
		    getTopTaskList(datestr);
		}
	});
	
	$.post("${ctx}/christmasActive.html?R="+ Math.random(), null, function(data) {
		  var str = '';
		   if (!isEmpty(data.data)) {
		  $.each(data.data, function(i, item) {
			  var classstr='';
			  if(i==0){
				 classstr="<dt class='list_01'><span>"+(i+1)+"</span></dt>";
			  }else{
				 classstr="<dt class='list'><span>"+(i+1)+"</span></dt>";
			  }
			   str+="<li><dl>"+classstr+"<dd class='name fl'>"+item.nickname+"</dd><dd class=\"total fr\">"+item.sumdeposit+"</dd></dl></li>";
			 $("#gettaskList").html(str);
	         });
	      } 

    });
	
	$.post("${ctx}/getPrizeTask.html?R="+ Math.random(), null, function(data) {
          	
		  var str = '';
		   if (!isEmpty(data.data)) {
		  $.each(data.data, function(i, item) {
			  str+="<li><dl><dd class='name fl'>"+item.nickName+"</dd><dd class='jp fl'>"+item.prizeName+"</dd><dd class='hj_date fl'>"+new Date(parseInt(item.createTime)).format('yyyy-MM-dd') +"</dd></dl></li>";
			 $("#getPrizeByGetMostTask").html(str);
	         });
	      } 

    });
	
	$.post("${ctx}/getPrizeInvite.html?R="+ Math.random(), null, function(data) {
      	
		  var str = '';
		  if(data.msg!=""){
		       if(data.msg=="1"){
			  			$("#getPrizeInviteuser").html("<li>推荐获将在2014-01-02揭晓</li>"); 
		       }
		  }
	      if (!isEmpty(data.data)) {
		  $.each(data.data, function(i, item) {
			  str+="<li><dl><dd class='name fl'>"+item.nickName+"</dd><dd class='jp fr'>"+item.prizeName+"</dd></dl></li>";
			 $("#getPrizeInviteuser").html(str);
	         });
	      } 

    });
	
	$.post("${ctx}/getActiveTime?R="+ Math.random(), {format:"yyyy-MM-dd HH:mm"}, function(data) {
		if (data != "") {
			 if(data=="1"){
				 $("#inviteuserDate").html("活动尚未开始 ");
			 }else if(data=="2"){
				 $("#inviteuserDate").html("活动已经结束");
			 }else{
				 $("#inviteuserDate").html(data);
			 }
			
		}
	});
	
	$.post("${ctx}/getActiveTime?R="+Math.random(), {format:"yyyy-MM-dd"}, function(data) {
		if (data != "") {
			if(data=="1"){
				 $("#getPrizeInviteuserDate").html("活动尚未开始.");
			 }else if(data=="2"){
				 $("#getPrizeInviteuserDate").html("活动已经结束.");
			 }else{
				 $("#getPrizeInviteuserDate").html(data);
			 }
		}
	});

	$.post("${ctx}/getActiveTimeTask?R="+ Math.random(), {format:"yyyy-MM-dd"}, function(data) {
		if (data != "") {
			$("#gettaskListDate").html(data);
		}
	});
	
	$.post("${ctx}/christmasActiveInvite.html?R="+ Math.random(), null, function(data) {
		  var str = '';
		  if (!isEmpty(data.data)) {
		  $.each(data.data, function(i, item) {
			  var classstr='';
			  if(i==0){
				 classstr="<dt class='list_01'><span>"+(i+1)+"</span></dt>";
			  }else{
				 classstr="<dt class='list'><span>"+(i+1)+"</span></dt>";
			  }
			   str+="<li><dl>"+classstr+"<dd class='name fl'>"+item.nickname+"</dd><dd class=\"total fr\">"+item.invitenum+"人</dd></dl></li>";
			   $("#inviteList").html(str);
	         });
	      } 

});

});
function getTopTaskList(datestr){
	$("#gettaskList").html("");
	$.post("${ctx}/christmasActive.html?R="+ Math.random(), {searchTime:datestr}, function(data) {
		  var str = '';
		   if (!isEmpty(data.data)) {
		  $.each(data.data, function(i, item) {
			  var classstr='';
			  if(i==0){
				 classstr="<dt class='list_01'><span>"+(i+1)+"</span></dt>";
			  }else{
				 classstr="<dt class='list'><span>"+(i+1)+"</span></dt>";
			  }
			   str+="<li><dl>"+classstr+"<dd class='name fl'>"+item.nickname+"</dd><dd class=\"total fr\">"+item.sumdeposit+"</dd></dl></li>";
			 $("#gettaskList").html(str);
	         });
	      }
});
	
}
</script>
<script type="text/javascript">
(function($){
	$.fn.extend({
        Scroll:function(opt,callback){
                //参数初始化
                if(!opt) var opt={};
                var _btnUp = $("#"+ opt.up);//Shawphy:向上按钮
                var _btnDown = $("#"+ opt.down);//Shawphy:向下按钮
                var timerID;
                var _this=this.eq(0).find("ul:first");
                var     lineH=_this.find("li:first").height(), //获取行高
                        line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
                        speed=opt.speed?parseInt(opt.speed,10):500; //卷动速度，数值越大，速度越慢（毫秒）
                        timer=opt.timer //?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
                if(line==0) line=1;
                var upHeight=0-line*lineH;
                //滚动函数
                var scrollUp=function(){
                        _btnUp.unbind("click",scrollUp); //Shawphy:取消向上按钮的函数绑定
                        _this.animate({
                                marginTop:upHeight
                        },speed,function(){
                                for(i=1;i<=line;i++){
                                        _this.find("li:first").appendTo(_this);
                                }
                                _this.css({marginTop:0});
                                _btnUp.bind("click",scrollUp); //Shawphy:绑定向上按钮的点击事件
                        });

                }
                //Shawphy:向下翻页函数
                var scrollDown=function(){
                        _btnDown.unbind("click",scrollDown);
                        for(i=1;i<=line;i++){
                                _this.find("li:last").show().prependTo(_this);
                        }
                        _this.css({marginTop:upHeight});
                        _this.animate({
                                marginTop:0
                        },speed,function(){
                                _btnDown.bind("click",scrollDown);
                        });
                }
               //Shawphy:自动播放
                var autoPlay = function(){
                        if(timer)timerID = window.setInterval(scrollUp,timer);
                };
                var autoStop = function(){
                        if(timer)window.clearInterval(timerID);
                };
                 //鼠标事件绑定
                _this.hover(autoStop,autoPlay).mouseout();
                _btnUp.css("cursor","pointer").click( scrollUp ).hover(autoStop,autoPlay);//Shawphy:向上向下鼠标事件绑定
                _btnDown.css("cursor","pointer").click( scrollDown ).hover(autoStop,autoPlay);

        }      
	})
})(jQuery);

$(document).ready(function(){
	$("div[name=scrollDiv]").each(function(){
		$(this).Scroll({line:4,speed:400,timer:2000,up:"btn1",down:"btn2"});
	});
	
	$("#change1").click(function(){
		 var pos = $(".turntable").offset().top;
         $("html,body").animate({
             scrollTop : pos
         }, 1000);
	});
	
	$("#change2").click(function(){
		 var pos = $(".bottom_list_a2").offset().top;
        $("html,body").animate({
            scrollTop : pos
        }, 1000);
	});
	
	$("#change3").click(function(){
		 var pos = $(".bottom_list_a3").offset().top;
       $("html,body").animate({
           scrollTop : pos
       }, 1000);
	});
	
	$("#change4").click(function(){
		 var pos = $(".bottom_list_a4").offset().top;
      $("html,body").animate({
          scrollTop : pos
      }, 1000);
	});
	
	$("#change5").click(function(){
		 var pos = $(".bottom_list_a5").offset().top;
     $("html,body").animate({
         scrollTop : pos
     }, 1000);
	});
	
	$("#change6").click(function(){
		 var pos = $(".turntable").offset().top;
        $("html,body").animate({
            scrollTop : pos
        }, 1000);
	});
});

</script>
</head>

<body>
<div class="top_nav_wrapper">
<div class="top_nav">提示:为了使您能更快速的抢到圣诞特别版高利率游戏[圣诞礼物到处飞],建议使用Chrome或Firefox浏览器.</div>
</div>
<a id="returnTop" href="javascript:;" style="bottom: -200px;">回到顶部</a> 
<script type="text/javascript" src="${ctx }/js/wowpower.min.js"></script>
<div class="wrapper">
<!--top_wrapper start-->
<div class="top_wrapper">
  <div class="top_c">
    <div class="hls_list">
      <div class="hls_first"><a id="change1">欢乐颂1</a></div>
      <div class="hls_second"><a id="change2">欢乐颂2</a></div>
      <div class="hls_third"><a id="change3">欢乐颂3</a></div>
      <div class="hls_fourth"><a id="change4">欢乐颂4</a></div>
      <div class="hls_five"><a id="change5">欢乐颂5</a></div>
      <div class="hls_dzp"><a id="change6">欢乐颂大转盘</a></div>
      <div class="jsq_wowpower">*本活动最终解释权归蛙宝网所有</div>
    </div>
  </div>
</div>
<!--top_wrapper end--> 
<!--priz_wrapper start-->
<div class="prize_wrapper">
  <div class="prize_c">
    <div class="activity_date">活动时间</div>
    <div class="name_list">
      <div id="div1">
        <ul>
        <c:forEach items="${allList }" var="item">
         <li>${item.nickName }　获得${item.prizeName }　</li>
        </c:forEach>
        </ul>
      </div>
    </div>
  </div>
</div>
<!--priz_wrapper end--> 
<!--center_wrapper start-->
<div class="center_wrapper">
  <div class="center_inner">
    <div class="center_c">
      <div class="lp_c">
        <div class="lp_title"></div>
        <div class="jpff_date">【奖品发放】蛙宝网将在活动结束后一周之内联系中奖者本人，核对确认用户信息，并发放奖品。所有税费统一由蛙宝网承担。</div>
        <div class="lp_list">
          <div class="section">
            <ul class="clearfix">
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a1.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>iphone5s土豪金／1部</h3>
                  </a></div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a2.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>小米3/3部</h3>
                  </a></div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a3.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>红米／21部</h3>
                  </a></div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a4.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>iPad air/2部</h3>
                  </a></div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a5.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>iPad mini retina
                    / 2部</h3>
                  </a> </div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a6.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>酷刷刷卡器
                    / 10台</h3>
                  </a></div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a7.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>500纳币/1个</h3>
                  </a></div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a8.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>蛙宝会员一年
                    / 10个</h3>
                  </a> </div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a9.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>游戏利率更高</h3>
                  </a></div>
              </li>
              <li>
                <div class="photo"><img src="${ctx}/images/festival/li_a10.png" width="200" height="200" /></div>
                <div class="rsp"></div>
                <div class="text"><a href="javascript:void(0)">
                  <h3>7天VIP会员</h3>
                  </a></div>
              </li>
            </ul>
            <div class="clear"></div>
          </div>
        </div>
      </div>
      <div class="turntable">
        <div class="turntable_l fl">
          <div id="top-menu-wrapper" class="dn bgfix">
            <div id="top-menu">
              <div class="l"><a class="user bgfix" href="http://www.jq-school.com"> </a> <span>今天还有</span><span class="lottery-times" style=" font-size:16px; color:#F34314;">30</span><span>次抽奖机会</span></div>
            </div>
          </div>
          <div id="header">
            <div id="turnplatewrapper" onselectstart="return false;" class="bgfix">
              <div id="turnplate" class="bgfix">
                <div id="awards" class="bgfix"> </div>
                <div id="platebtn" class="bgfix"> </div>
                <p id="msg"> </p>
                <p id="init" class="dn"> 初始化中，请稍后...<span></span> </p>
              </div>
            </div>
            <div id="gift" class="bgfix"> </div>
          </div>
          <div id="content"> </div>
          <div id="lotteryMsg" class="dn">
            <p class="msg"></p>
            <hr class="sp" />
            <div class="content mv5"> 你抽中<span class="option"></span> </div>
          </div>
        </div>
        <div class="turntable_r fr">
          <div class="turntable_title"></div>
          <div class="turntable_list">
            <div id="scrollDiv" name = "scrollDiv" class="hjmd turntable_scroll" >
              <ul >
               <c:forEach items="${lotteryList }" var="item">
                <li>
                  <dl>
                    <dd class="name fl">${item.nickName }</dd>
                    <dd class="jp fl">${item.prizeName }</dd>
                  </dl>
                </li>
               </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--center_wrapper end--> 
<!--bottom_wrapper start-->
<div class="bottom_wrapper">
  <div class="bottom_c"> 
    <!--bottom_list_a1 start-->
    <div class="bottom_list_a1">
      <div class="bottom_list_a1_title">欢乐送1幸运大转盘</div>
      <div class="bottom_list_a1_c_w">
        <div class="bottom_list_a1_c">
          <div class="a1_c_left">
            <ul>
              <h4>活动规则</h4>
              <li>在活动结束后，系统将统计用户在活动期间内领取游戏的押金总和，并将总和除以10000，
                计算出蛙宝用户具有的抽奖次数。</li>
              <li>押金总和大于0且小于10000的用户也将获得一次抽奖机会。</li>
              <li>必须完善蛙宝网头像等信息。</li>
              <li>用户点击大转盘进行抽奖，系统将结果实时公布在蛙宝网站。</li>
            </ul>
            <ul>
              <h4>抽奖时间</h4>
              <p>2014-01-02 12:00 – 20:00 提前、过期抽奖无效</p>
            </ul>
          </div>
          <div class="a1_c_right">
            <ul>
              <h4>奖品设置</h4>
              <li>iPhone 5S土豪金1部</li>
              <li>iPad air 2部</li>
              <li>iPad mini retina 2部</li>
              <li>小米3  2部</li>
              <li>红米 4台</li>
              <li> 500纳币 1个</li>
              <li>酷刷刷卡器  10台</li>
              <li>蛙宝会员一年 10 个</li>
              <li>普惠奖  所有参与活动但未中奖用户都将获得7天VIP会员资格</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    
    <!--bottom_list_a1 end--> 
    <!--bottom_list_a2 start-->
    <div class="bottom_list_a2">
      <div class="bottom_list_a2_title">欢乐送1幸运大转盘</div>
      <div class="bottom_list_a2_c_w">
        <div class="bottom_list_a2_c">
          <div class="a2_c_left">
            <ul>
              <h4>活动规则</h4>
              <li>注册蛙宝网，并关注蛙宝网新浪官方微博。<wb:follow-button uid="3848323436" type="red_3" width="100%" height="24"></wb:follow-button></li>
              <li>转发蛙宝网新浪官方微博的有奖活动微博并@ 至少3位好友，均有机会获得奖品。</li>
            </ul>
            <ul>
              <h4>奖品设置</h4>
              <li> 小米3  （1部）、红米 （1部）、酷刷刷卡器（5个）（每个价值人民币398）</li>
            </ul>
            <ul>
              <h4>开奖时间</h4>
              <p>2013-12-28</p>
              <li>结果将在蛙宝网新浪官方微博公布。</li>
              <li>本次抽奖将通过第三方平台抽取，操作透明、公正、公平。</li>
            </ul>
          </div>
          <div class="a2_c_right">
            <ul>
              <li><img width="135" height="135" src="${ctx}/images/festival/img1.png"/></li>
              <li><img width="135" height="135" src="${ctx}/images/festival/img2.png"/></li>
              <li><img width="135" height="135" src="${ctx}/images/festival/img3.png"/></li>
            </ul>
            <p class="sina_share">新浪微博分享</p>
          </div>
        </div>
      </div>
    </div>
    
    <!--bottom_list_a2 end--> 
    <!--bottom_list_a3 start-->
    <div class="bottom_list_a3">
      <div class="bottom_list_a3_title">欢乐送1幸运大转盘</div>
      <div class="bottom_list_a3_c_w">
        <div class="bottom_list_a3_c">
          <div class="a3_c_left">
            <ul>
              <h4>活动规则</h4>
              <li>在活动期间内，推荐用户最多者且被推荐人为真实用户（真实用户标准：
                活动期间领取过游戏）将获得蛙宝网送出的iPad mini一部</li>
              <li>推荐人链接可进入用户中心-我要邀请获得</li>
            </ul>
            <ul>
              <h4>奖品设置</h4>
              <li>iPad mini  1部</li>
            </ul>
            <ul>
              <h4>开奖时间</h4>
              <p>2014-01-02</p>
            </ul>
          </div>
          <div class="a3_c_right fr">
            <div class="a3_c_right_l fl">
              <div class="a3_c_right_l_tit"> 推荐用户排名</div>
              <div class="a3_c_right_l_cnt">
                <p class="date" id="inviteuserDate"></p>
                <ul id="inviteList">
                </ul>
              </div>
            </div>
            <div class="a3_c_right_r fr">
              <div class="a3_c_right_r_tit">获奖情况</div>
              <div class="a3_c_right_r_cnt">
                <p class="date" id="getPrizeInviteuserDate"></p>
                <ul class="hj_name" id="getPrizeInviteuser">
                  
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!--bottom_list_a3 end--> 
    <script type="text/javascript" src="${ctx }/js/game.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
	
    <script type="text/javascript">
    var maxnum = 20;
    
    $(document).ready(function() {
    	$.post("${ctx}/getRest",{},function(data){
			$("#lqyx_total").empty();
			$("#lqyx_total").html(data.rest);
		});
    	$("#qr").click(function(){
    		if(getUser()==0){
    			window.open("${ctx}/login.html");
    			return;
    		}
    		if(isNaN($("#J_IptAmount").val())){
    			alert("游戏领取数量只能为数字！");
    			return;
    		}
    		if($("#J_IptAmount").val()==0){
    			/* top.$.alerts. */alert("游戏领取数量不为0");
    			return;
    		}
    		if(maxnum < 1){
    			document.getElementById("J_IptAmount").value = 0;
    		}else if(maxnum > 20){
    			alert("此游戏每次最多领20个！");
    			return;
    		}else{
    			
    			$("a#fancyBox").attr('href', '${ctx}/confirmPlay?id=42f5cdcd-ecbe-4638-a46d-1eca35941158&num='+$("#J_IptAmount").val()+'&time='+new Date()).fancybox({
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
    });
    
    function getUser(){
    	var restr = 1;
    	 $.get("${ctx}/getUser.html",  function(data) {
    		 restr = data;
    		 
    		});
    	 return restr;
    }
    
	function addCount(){
		
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
</script>
    
    <!--bottom_list_a4 start-->
    <div class="bottom_list_a4">
      <div class="bottom_list_a4_title">圣诞特别版游戏领取</div>
      <div class="bottom_list_a4_c_w">
        <div class="bottom_list_a4_c">
          <div class="a4_c_left">
            <ul>
              <h4>活动规则</h4>
              <li>在活动期间内，蛙宝网将推出限量版游戏，总计可领取次数999次，
                每次押金1000纳币，月返利30。</li>
            </ul>
            <ul>
              <h4>奖品设置</h4>
              <li>总计比正常返利多送出人民币14985</li>
              <p style="margin-top:15px;"><img width="345" height="75" src="${ctx }/images/festival/high_a.png"/></p>
            </ul>
          </div>
          <div class="a4_c_center"><img width="210" width="170" src="${ctx }/images/festival/hls_total.png"/></div>
          <div class="a4_c_right">
            <ul>
              <li>
                <dt class="fes_game"><img width="210" height="129" src="${ctx }/images/festival/fes_game.jpg"/></dt>
                <dd class="fes_name">
                  <圣诞礼物到处飞>
                </dd>
                <dd class="game_icon"><a style="display: none" id="fancyBox"></a><img width="73" height="25" src="${ctx }/images/festival/game_icon.png"/></dd>
                 <dd class="game_input">领取游戏数量：
<span>
<a class="tb-stock" onclick="minusCount()">-</a>
<input id="J_IptAmount" class="tb-text" type="text" value="1" maxlength="8" title="请输入领取游戏数量" />
<a class="tb-increase" onclick="addCount()">﹢</a>
</span>
</dd>

                <dd class="game_fl">返利30.0纳币</dd>
                <dd class="game_btn" id="qr">领取游戏</dd>
                <dd class="lqyx_total" id="lqyx_total">999</dd>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    
    <!--bottom_list_a4 end--> 
    <!--bottom_list_a5 start-->
    <div class="bottom_list_a5">
      <div class="bottom_list_a5_title">欢乐送1幸运大转盘</div>
      <div class="bottom_list_a5_c_w">
        <div class="bottom_list_a5_c">
          <div class="a5_c_left">
            <ul>
              <h4>活动规则</h4>
              <li>活动期间，每天登录蛙宝网并领取游戏任务，第二天凌晨系统自动统计昨日领
                取游戏金额最多的前10名用户，第一名将获得红米手机1部。</li>
              <li>同时会在第二至第十名中产生一名幸运用户获得红米手机1部。</li>
            </ul>
            <ul>
              <h4>奖品设置</h4>
              <li>每天送出2部红米手机</li>
              <li>活动期间总计送出16部红米手机</li>
            </ul>
            <ul>
              <h4>开奖时间</h4>
              <p>次日凌晨2:00前</p>
            </ul>
          </div>
          <div class="a5_c_right fr">
            <div class="a5_c_right_l fl">
              <div class="a5_c_right_l_tit">领取游戏排行榜</div>
              <div class="a5_c_right_l_cnt">
                <div class="a5_title"><p class="pre fl" id="predate"></p><p class="date fl" id="gettaskListDate"></p><p class="next fl" id="nextdate"></p></div>
                <ul id="gettaskList">
                </ul>
              </div>
            </div>
            
            <div class="a5_c_right_r fl">
              <div class="a5_c_right_r_tit">获奖情况</div>
              <div class="a5_c_right_r_cnt">
                <p class="date"> </p>
                <div id="scrollDiv" name = "scrollDiv" class="hjmd">
                  <ul id="getPrizeByGetMostTask">
                    <li>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!--bottom_list_a5 end--> 
    <div class="btm_wrapper">
    <div class="btm_inner"></div>
    </div>
  </div>
  <!--bottom_wrapper end--> 
  
  <!--center_wrapper end--> 
</div>
<!--footer-->
<script>
    var turnplate = {
        turnplateBox : $('#turnplate'),
        turnplateBtn : $('#platebtn'),
        lightDom : $('#turnplatewrapper'),
       
        msgBox : $('#msg'),
       
        height : '592', //帧高度
        lightSwitch : 0, //闪灯切换开关. 0 和 1间切换
        minResistance : 5, //基本阻力
        Cx : 0.01, //阻力系数 阻力公式：  totalResistance = minResistance + curSpeed * Cx;	
        accSpeed : 15, //加速度
        accFrameLen : 40, //加速度持续帧数
        maxSpeed : 250, //最大速度 
        minSpeed : 20, //最小速度 
        frameLen : 10, //帧总数
        totalFrame : 0, //累计帧数,每切换一帧次数加1
        curFrame : 0, //当前帧
        curSpeed : 20, //上帧的速度
        lotteryTime : 1, //抽奖次数
        lotteryIndex : 1, //奖品index
        errorIndex : 1, //异常时的奖品指针
        initBoxEle : $('#turnplate #init'),
        progressEle : $('#turnplate #init span'),
        initProgressContent : '~~~^_^~~~', //初始化进度条的内容
        initFreshInterval : 500, //进度条刷新间隔
        virtualDistance : 10000, //虚拟路程,固定值，速度越快，切换到下帧的时间越快: 切换到下帧的时间 = virtualDistance/curSpeed
        isStop : false, //抽奖锁,为true时，才允许下一轮抽奖
        timer2 : undefined, //计时器
        initTime : undefined,
        showMsgTime : 2000, //消息显示时间
        lotteryChannel: '',
        myStop : false,
        channelList: {
            'login': '登录',
            'consume': '啊哦'
        },lotteryType : {
            'iPad mini retina' : 0,	
            'empty' : 1,
            'iPad air' : 2,
            'iPhon5s土豪金' : 3,
            '500纳币' : 4,
            '酷刷刷卡器' : 5,
            'empty' : 6,
            '蛙宝会员' : 7,
            '红米手机' : 8,
            '小米3手机' : 9
        },lotteryList : [
            'iPad mini retina 1部',
            '继续攒人品',		
            'iPad air 1部',		
            'iPhone5s土豪金1部',		
            '500纳币',		
            '酷刷刷卡器一台',		
            '继续攒人品',		
            '蛙宝会员一年',		
            '红米手机一部',		
            '小米3手机一部',
        ],lotteryDes : [
            '手气一般般，幸运指数4颗星！',
            '手气不错呢，幸运指数0颗星！',
            '手气无敌了，幸运指数4颗星！',
            '手气很好呢，幸运指数5颗星！',
            '手气很好呢，幸运指数3颗星！',
            '手气还凑合，幸运指数3颗星！',
            '手气太差了，幸运指数0颗星！',
            '手气太好了，幸运指数3颗星！',
            '手气还可以，幸运指数3颗星！',
            '手气爆棚了，幸运指数3颗星！'
        ],init : function() {
            this.initAnimate()
            this.freshLottery();
            this.turnplateBtn.click($.proxy(function(){
                this.click();	
            },this));	
        },initAnimate : function() {
            this.initBoxEle.show();
            clearTimeout(this.initTimer);
            var curLength = this.progressEle.text().length,
                totalLength = this.initProgressContent.length;
    
            if (curLength < totalLength) {
                this.progressEle.text(this.initProgressContent.slice(0, curLength + 1));
            }else{
                this.progressEle.text('');
            }
            this.initTimer = setTimeout($.proxy(this.initAnimate, this), this.initFreshInterval);
        },stopInitAnimate : function() {
            clearTimeout(this.initTimer);
            this.initBoxEle.hide();
        },freshLotteryTime : function() {
            $('#top-menu').find('.lottery-times').html(this.lotteryTime);
        },freshLottery : function() {
            this.stopInitAnimate();
            this.setBtnHover();
            this.isStop = true;
            this.lotteryTime = ${lotterTime};
            this.freshLotteryTime();
    
        },setBtnHover : function() {
            $('#platebtn').hover(function(){
                $(this).addClass('hover');
            },function() {
                $(this).removeClass('hover click');
            }).mousedown(function(){
                $(this).addClass('click');
            }).mouseup(function(){
                $(this).removeClass('click');
            });	
        },lottery : function() {
            if(this.lotteryTime>0){
            	 this.lotteryTime--;
            }
           
            this.freshLotteryTime();
            this.totalFrame = 0;
            this.curSpeed = this.minSpeed;
            this.turnAround();
            this.lotteryIndex = typeof this.lotteryIndex !== 'undefined' ? this.lotteryIndex : this.errorIndex;
            this.lotteryChannel = typeof this.channelList[1] !== 'undefined' ? this.channelList[1] : '';	
        },click : function() {
            if(this.isStop == false) {
                this.showMsg('现在还不能抽奖哦');
                return;
            } 
		$.ajax({
			type : "GET",
			async : true,
			cache : false,
			dataType : "json",
			url : "${ctx}/lottery.html?time"+new Date(),
			success: function(result){
				
				if(result == -1){
					window.location.href = "${ctx}/login.html";
					return;
				}else if(result == -2){
					alert("很抱歉，您的抽奖次数已用完");
				}else if(result == -3){
					alert("请先完善您的头像哦！中奖后要拉出去溜溜的");
					window.location.href = "${ctx}/user/2/manager";
				}else if(result == -5){
					alert("很抱歉，抽奖活动还未开始");
				}else if(result == -6){
					alert("很抱歉，抽奖活动已结束");
				}else if(result == -4){
					alert("很抱歉，您现在不能参加抽奖");
				}else{
					 turnplate.lotteryIndex = result;
					 turnplate.lottery();
				}
			  }
			});
        },freshSpeed : function() {
            var totalResistance = this.minResistance + this.curSpeed * this.Cx;
            var accSpeed = this.accSpeed;
            var curSpeed = this.curSpeed;
            if(this.totalFrame >= this.accFrameLen) {
                accSpeed = 0;
            }
            curSpeed = curSpeed - totalResistance + accSpeed;
    
            if(curSpeed < this.minSpeed){
                curSpeed = this.minSpeed;
            }else if(curSpeed > this.maxSpeed){
                curSpeed = this.maxSpeed;
            }
    
            this.curSpeed  = curSpeed;
        },switchLight : function() {
            this.lightSwitch = this.lightSwitch === 0 ? 1 : 0;
            var lightHeight = -this.lightSwitch * this.height;
            this.lightDom.css('backgroundPosition','0 ' + lightHeight.toString() + 'px');	
        },turnAround : function() {
            this.isStop = false;
            var intervalTime = parseInt(this.virtualDistance/this.curSpeed);		
            this.timer = window.setTimeout('turnplate.changeNext()', intervalTime);		
        },showAwards : function(){
            $('#lotteryMsg').dialog('open');
        },showMsg : function(msg, isFresh) {
            isFresh = typeof isFresh == 'undefined' ? false : true;
            if(typeof msg != 'string'){
                msg = '今天已经抽过奖了，明天再来吧';
            }
            var msgBox = this.msgBox;
            var display = msgBox.css('display');
    
            msgBox.html(msg);	
    
            window.clearTimeout(this.timer2);
            msgBox.stop(true,true).show();
            var fadeOut = $.proxy(function() {
                this.msgBox.fadeOut('slow');
            }, this);
            this.timer2 = window.setTimeout(fadeOut, this.showMsgTime);
        },changeNext : function() {
            //判断是否应该停止
            if(this.lotteryIndex !== undefined && this.curFrame == this.lotteryIndex && (this.curSpeed <= this.minSpeed+10) && (this.totalFrame > this.accFrameLen)) {
                this.isStop = true;
                this.showAwards();
                return; 		
            }
    
            var nextFrame =  this.curFrame+1 < this.frameLen ? this.curFrame+1 : 0;
            var bgTop = - nextFrame * this.height;		
            this.turnplateBox.css('backgroundPosition','0 ' + bgTop.toString() + 'px');	
            this.switchLight();
            this.curFrame = nextFrame;
            this.totalFrame ++;
            this.freshSpeed();
            this.turnAround();
        }
    }
    
    </script> 
    <script>
    
	    $('#lotteryMsg').dialog({
	        modal: true,
	        width: 500,
	        height: 350,
	        resizable: false,
	        title: '获奖信息',
	        autoOpen: false,
	        open: function(){
	                var showMsg = '您抽中了:' + turnplate.lotteryList[turnplate.lotteryIndex];
	                var options =  turnplate.lotteryList[turnplate.lotteryIndex] + ',' + turnplate.lotteryDes[turnplate.lotteryIndex];
	                $('#lotteryMsg').find('.msg').html(showMsg);
	                $('#lotteryMsg').find('.option').html(options);
	        }
	    });
	
	    turnplate.init();
	    
    </script>
</div>