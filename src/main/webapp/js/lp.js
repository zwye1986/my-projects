$(document).ready(function() {
	
    $.ajax({
        type: "POST",
        async: false,
       // data:params,
        cache: false,
      
        url: getRootPath()+"/ifpayment.html?time="+new Date(),
        success: function(data){
      	
      	 if(data == "repeat"){
      		
      		 $("#payment").css("background","url('images/lp/btn03.png') no-repeat scroll 0 0 transparent");
      	 }

      	 
        }
 });
  //结束领取
    
    $.ajax({
        type: "POST",
        async: false,
       // data:params,
        cache: false,
      
        url: getRootPath()+"/ifconfirm.html?time="+new Date(),
        success: function(data){
        
      	 if(data == "repeat"){
      		
      		 $("#demo").css("background","url('images/lp/btn03.png') no-repeat scroll 0 0 transparent");
      	 }

      	 
        }
 });
	

$("#payment").click(function(){
	//领取奖励
//	var params = { id:treeNode.id};
	
      $.ajax({
          type: "POST",
          async: false,
         // data:params,
          cache: false,
        
          url: getRootPath()+"/payment.html?time="+new Date(),
          success: function(data){
        	 if(data == "0"){
        		 window.location.href="login.html";
        	 }
        	 if(data == "repeat"){
        		 $.alerts.alert("您已经领取过该奖励");
        		 $("#payment").css("background","url('images/lp/btn03.png') no-repeat scroll 0 0 transparent");
        	 }
        	 if(data == "fail"){
        		 $.alerts.alert("领取发生异常，请联系我们解决");
        	 }
        	 if(data == "success"){
        		 $.alerts.alert("领取成功,赶紧试玩游戏吧");
        		 $("#payment").css("background","url('images/lp/btn03.png') no-repeat scroll 0 0 transparent");
        	 }
        	 if(data == "over"){
        		 $.alerts.alert("活动已结束");
        		 $("#payment").css("background","url('images/lp/btn03.png') no-repeat scroll 0 0 transparent");
        	 }
        	 
          }
   });
    //结束领取
});

$("#demo").click(function(){
	window.location.href=getRootPath()+"/showGameDetail?id=953b65d9-a322-4b57-abc9-33137b8c346c";
});

});

function getRootPath(){
    var strFullPath=window.document.location.href;
    var strPath=window.document.location.pathname;
    var pos=strFullPath.indexOf(strPath);
    var prePath=strFullPath.substring(0,pos);
    var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
    return(prePath+postPath);
  }