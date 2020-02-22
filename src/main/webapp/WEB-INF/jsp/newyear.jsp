<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${ctx }/style/year.css" />
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script type="text/javascript">
$(function(){
	 $.ajax({
         cache: false,
         async: true,
         type: "Get",
         url: "${ctx}/getRestNum?seq=1",
         success: function(data) {
             $(".btn-total1").html(data);
         }
     });
	 
	 $.ajax({
         cache: false,
         async: true,
         type: "Get",
         url: "${ctx}/getRestNum?seq=2",
         success: function(data) {
             $(".btn-total2").html(data);
         }
     });
	 
	 $.ajax({
         cache: false,
         async: true,
         type: "Get",
         url: "${ctx}/getRestNum?seq=3",
         success: function(data) {
             $(".btn-total3").html(data);
         }
     });
	 
	
	 var str1 ='2015/02/09 00:00:00';
	 var str2 ='2015/02/18 23:59:59';

	
	 $(".btn-task1").click(function(){
		 var starttime = new Date(Date.parse(str1));
		 var endtime = new Date(Date.parse(str2));
		 var curtime = new Date();
		 if(curtime<starttime){
			 layer.msg("活动还未开始");			
		 }else if(curtime>endtime){
			 layer.msg("活动时间已过");
		 }else{
			 window.open('${ctx }/${id1 }/showGameDetail.html','_blank');
		 }
	 });
	 
	 $(".btn-task2").click(function(){
		 var starttime = new Date(Date.parse(str1));
		 var endtime = new Date(Date.parse(str2));
		 var curtime = new Date();
		 if(curtime<starttime){
			 layer.msg("活动还未开始");			
		 }else if(curtime>endtime){
			 layer.msg("活动时间已过");
		 }else{
			 window.open('${ctx }/${id2 }/showGameDetail.html','_blank');
		 }
	 });
	 
	 $(".btn-task3").click(function(){
		 var starttime = new Date(Date.parse(str1));
		 var endtime = new Date(Date.parse(str2));
		 var curtime = new Date();
		 if(curtime<starttime){
			 layer.msg("活动还未开始");			
		 }else if(curtime>endtime){
			 layer.msg("活动时间已过");
		 }else{
			 window.open('${ctx }/${id3 }/showGameDetail.html','_blank');
		 }
	 });
	 
	 $(".btn-detail").click(function(){
		 window.open("${ctx}/75/showNews.html","_blank");
	 });
	 
});
</script>
<div class="wrapper">
  <div class="content">
    <div class="top"><img src="${ctx }/images/year/top.png"/></div>
    <div class="content-c">
      <div class="title1"><img src="${ctx }/images/year/title1.png"/></div>
      <div class="c-top">
        <div class="title"></div>
        <div class="gf">
        <a class="btn-task1" href="javascript:void(0)" >领取1个月任务</a><a class="btn-total1" href="javascript:void(0)"></a><br/>
        <a class="btn-task2" href="javascript:void(0)" >领取2个月任务</a><a class="btn-total2" href="javascript:void(0)"></a>
        <a class="btn-task3" href="javascript:void(0)" >领取3个月任务</a><a class="btn-total3" href="javascript:void(0)"></a>
        </div>
       
      </div>
       <div class="title2"><img src="${ctx }/images/year/title2.png"/></div>
      <div class="c-center">
        <div class="title"></div>
        <div class="sign"><a class="btn-detail" href="javascript:void(0)">查看详情</a></div>
      </div>
      <div class="title3"><img src="${ctx }/images/year/title3.png"/></div>
      <div class="c-bottom">
         <div class="title"></div>
         <div class="zs"></div>
      </div>
    </div>
  </div>
</div>
