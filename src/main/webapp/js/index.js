$(document).ready(function() {
	//点击搜索
	$("#searchSubmit").click(function(){
		$("#searchform").submit();
	});
	
	// 点击游戏中心角色扮演
	$("#roleplay").click(function() {
		var param = {
			type : 'roleplay',
			page : '1'
		};
		$.get("showGame", param, function(data) {
			$("#content_2").html(data);
		});
	});

	// 点击游戏中心战争策略
	$("#warstrategy").click(function() {
		var param = {
			type : 'warstrategy',
			page : '1'
		};

		$.get("showGame", param, function(data) {

			$("#content_3").html(data);
		});
	});

	// 点击游戏中心休闲竞技
	$("#leisuretournament").click(function() {
		var param = {
			type : 'leisuretournament',
			page : '1'
		};

		$.get("showGame", param, function(data) {

			$("#content_4").html(data);
		});
	});

	// 点击游戏中心模拟经营
	$("#businesssimulation").click(function() {
		var param = {
			type : 'businesssimulation',
			page : '1'
		};

		$.get("showGame", param, function(data) {

			$("#content_4").html(data);
		});
	});

	// 点击游戏中心模拟经营
	$("#businesssimulation").click(function() {
		var param = {
			type : 'businesssimulation',
			page : '1'
		};

		$.get("showGame", param, function(data) {

			$("#content_5").html(data);
		});
	});
	
	// 点击游戏中心其他
	$("#other").click(function() {
		var param = {
			type : 'other',
			page : '1'
		};

		$.get("showGame", param, function(data) {

			$("#content_6").html(data);
		});
	});
	
	showDetail();
	
	
	$("#uin").click(function() {
		if("请输入登录号"==$("#uin").val()){
			$("#uin").val("");
		}
		
		
		$(".ts_message").hide();
	});
	
	$("#uin").focus(function(){
		if("请输入登录号"==$("#uin").val()){
			$("#uin").val("");
		}
		$(".ts_message").hide();
		$("#uin").css("color","#000");
	});
	
	$("#uin").blur(function() {
		var mobileNumber = $(this).val();
		if(typeof(mobileNumber) == undefined || "" == $.trim(mobileNumber)){
			$("#uin").css("color","#767676");
			$("#uin").val("请输入登录号");
			
			
		}
	});
	
	$("#yzm").focus(function(){
		$("#yzm").val("");
		$("#yzm").css("color","#000");
	});
	
	
	$("#yzm").click(function() {
		$("#yzm").val("");
	});
	
	$("#yzm").blur(function() {
		var yzm = $("#yzm").val();
		if(typeof(yzm) == undefined || "" == $.trim(yzm)){
			$("#yzm").css("color","#767676");
			$("#yzm").val("验证码");
		}
	});
	
    $("input[name=pwd]").focus(function () { 
        $("input[name=pwd]").hide(); 
        $("input[name=password]").show().focus(); 
        
        $("input[name=password]").css("color","#000");

    }); 
    $("input[name=password]").blur(function () { 
        
        if ($(this).val() == "") { 
            $("input[name=pwd]").show(); 
            $("input[name=password]").hide(); 
        } 
    });

    //角色扮演下一个
    $("#banner_next_roleplay").click(function(){
    	
    	var page = $("#roleplaypage").val();
    	page = Number(page) + 1;
    	
    	 $("#roleplaypage").val(page);
    	var param = {
    			type : 'roleplay',
    			page : page
    		};
    		$.get("showIndexGame", param, function(data) {
    			var banner = $("#roleplay_banner").html();
    			banner = "<div class='bannerT' id='roleplay_banner'>角色扮演</div>";
    			$("#roleplay").html(banner+data);
    			showDetail();
    		});
    });
    //角色扮演上一个
    $("#banner_prev_roleplay").click(function(){
    	var page = $("#roleplaypage").val();
    	
    	page = Number(page) - 1;
    	 $("#roleplaypage").val(page);
    	var param = {
    			type : 'roleplay',
    			page : page
    		};
    		$.get("showIndexGame", param, function(data) {
    			
    			var banner = $("#roleplay_banner").html();
    			banner = "<div class='bannerT' id='roleplay_banner'>角色扮演</div>";
    			$("#roleplay").html(banner+data);
    			showDetail();
    		});
    });
    //战争策略下一个
    $("#banner_next_warstrategypage").click(function(){
    	var page = $("#warstrategypage").val();
    	page = Number(page) + 1;
    	 $("#warstrategypage").val(page);
    	var param = {
    			type : 'warstrategy',
    			page : page
    		};
    
    		$.get("showIndexGame", param, function(data) {
    			var banner = $("#roleplay_banner").html();
    			banner = "<div class='bannerT' id='warstrategy_banner'>战争策略</div>";
    			$("#warstrategy").html(banner+data);
    			showDetail();
    		});
    });
    //战争策略上一个
    $("#banner_prev_warstrategypage").click(function(){
    	var page = $("#warstrategypage").val();
    	
    	page = Number(page) - 1;
    	 $("#warstrategypage").val(page);
    	var param = {
    			type : 'warstrategy',
    			page : page
    		};
    		$.get("showIndexGame", param, function(data) {
    			
    			var banner = $("#warstrategy_banner").html();
    			banner = "<div class='bannerT' id='warstrategy_banner'>战争策略</div>";
    			$("#warstrategy").html(banner+data);
    			showDetail();
    		});
    });
  //休闲竞技下一个
    $("#banner_next_leisuretournament").click(function(){
    	var page = $("#leisuretournamentpage").val();
    	page = Number(page) + 1;
    	 $("#leisuretournamentpage").val(page);
    	var param = {
    			type : 'leisuretournament',
    			page : page
    		};
    
    		$.get("showIndexGame", param, function(data) {
    			var banner = $("#leisuretournament_banner").html();
    			banner = "<div class='bannerT' id='leisuretournament_banner'>休闲竞技</div>";
    			$("#leisuretournament").html(banner+data);
    			showDetail();
    		});
    });
    //休闲竞技上一个
    $("#banner_prev_leisuretournament").click(function(){
    	var page = $("#leisuretournamentpage").val();
    	page = Number(page) - 1;
    	 $("#leisuretournamentpage").val(page);
    	var param = {
    			type : 'leisuretournament',
    			page : page
    		};
    
    		$.get("showIndexGame", param, function(data) {
    			var banner = $("#leisuretournament_banner").html();
    			banner = "<div class='bannerT' id='leisuretournament_banner'>休闲竞技</div>";
    			$("#leisuretournament").html(banner+data);
    			showDetail();
    		});
    });
  //模拟经营下一个
    $("#banner_next_businesssimulation").click(function(){
    	var page = $("#businesssimulationpage").val();
    	page = Number(page) + 1;
    	 $("#businesssimulationpage").val(page);
    	var param = {
    			type : 'businesssimulation',
    			page : page
    		};
    
    		$.get("showIndexGame", param, function(data) {
    			var banner = $("#businesssimulation_banner").html();
    			banner = "<div class='bannerT' id='businesssimulation_banner'>模拟经营</div>";
    			$("#businesssimulation").html(banner+data);
    			showDetail();
    		});
    });
    //模拟经营上一个
    $("#banner_prev_businesssimulation").click(function(){
    	var page = $("#businesssimulationpage").val();
    	page = Number(page) - 1;
    	 $("#businesssimulationpage").val(page);
    	var param = {
    			type : 'businesssimulation',
    			page : page
    		};
    
    		$.get("showIndexGame", param, function(data) {
    			var banner = $("#businesssimulation_banner").html();
    			banner = "<div class='bannerT' id='businesssimulation_banner'>模拟经营</div>";
    			$("#businesssimulation").html(banner+data);
    			showDetail();
    		});
    		
    });
    
   

var windowWidth = $(document).width();
    
    var popupWidth = $("#lp_container").width();   
    var windowHeight = $(window).height(); 
	   var top = windowHeight*0.2;
   
    $("#lp_container").css({
  
     "left": (windowWidth-popupWidth)/2 ,
     "top":top
    }); 
    
  
    
  

/**  
    var active = $.cookie('active');
    if(active != 'active'){
    	
    	var divheight = document.body.offsetHeight;
    
    	  $("#BgDiv").css({ display: "block", height:3075,width:$(document).width()  });
    	$("#lp_container").slideDown();
    	 $.cookie('active','active',{ expires: 1 });
   }
   
$("#lp_close").click(function(){
	$("#lp_container").slideUp();
	 $("#BgDiv").css("display", "none");
});
  
//浏览器窗口大小变动时重新触发遮罩及广告div大小
   $(window).resize(function() {
	$("#BgDiv").css({  height:3075,width:$(document).width()  });
	  $("#lp_container").css({	  
		     "left": ($(document).width()-popupWidth)/2 ,
		     "top":top
		    }); 
	});
**/
});


// 游戏中心分页
function gamePagination(type, page) {
	var param = {
		type : type,
		page : page
	};
	$.get("showGame", param, function(data) {
		if (type == 'roleplay') {
			$("#content_2").html(data);
		} else if (type == 'warstrategy') {
			$("#content_3").html(data);
		} else if (type == 'leisuretournament') {
			$("#content_4").html(data);
		} else if (type == 'businesssimulation') {
			$("#content_5").html(data);
		} else if (type == 'other') {
			$("#content_6").html(data);
		} else {
			$("#content_1").html(data);
		}

	});
}
// 上一页、下一页
function gamePaginationNext(type, page) {
	var param = {
		type : type,
		page : page + 1
	};
	$.get("showGame", param, function(data) {
		if (type == 'roleplay') {
			$("#content_2").html(data);
		} else if (type == 'warstrategy') {
			$("#content_3").html(data);
		} else if (type == 'leisuretournament') {
			$("#content_4").html(data);
		} else if (type == 'businesssimulation') {
			$("#content_5").html(data);
		} else if (type == 'other') {
			$("#content_6").html(data);
		} else {
			$("#content_1").html(data);
		}
	});
}

function gamePaginationPrev(type, page) {
	var param = {
		type : type,
		page : page - 1
	};
	$.get("showGame", param, function(data) {
		if (type == 'roleplay') {
			$("#content_2").html(data);
		} else if (type == 'warstrategy') {
			$("#content_3").html(data);
		} else if (type == 'leisuretournament') {
			$("#content_4").html(data);
		} else if (type == 'businesssimulation') {
			$("#content_5").html(data);
		} else if (type == 'other') {
			$("#content_6").html(data);
		} else {
			$("#content_1").html(data);
		}
	});
}

function gameBox(id) {
	
	 var param = {
				id : id
			};
			$.get("isMember.html?time="+new Date(),param,function(data) {
				
				if(data == 0){
					top.$.alerts.alert("会员游戏需要开通会员领取");
					return false;
				}else if(data == 3){
					window.location.href = getRootPath()+"/login.html";
				}else{
					//更新游戏点击次数
					$.post("updateClickNum", {id:id}, function(
							data) {
					
					});
					
					$("a#fancyBox").attr('href', 'showGameDialog.html?id='+id+'&time='+new Date()).fancybox({
						'padding' :  10,
						'margin' : 10,
						'width'				:  815,
						'height'			: 410,
						'autoScale'			: false,
						  'transitionIn'  : 'elastic',  
					        'transitionOut' : 'elastic'  ,
						'type'				: 'iframe',
						'scrolling' : 'no'
							
					}).click();
				}
			});
}

function getRootPath(){
    var strFullPath=window.document.location.href;
    var strPath=window.document.location.pathname;
    var pos=strFullPath.indexOf(strPath);
    var prePath=strFullPath.substring(0,pos);
    var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
    return(prePath+postPath);
  }

function gameDetailBox(id) {
	//更新游戏点击次数
	$.post("updateClickNum", {id:id}, function(
			data) {
	
	});
	window.open("showGameDetail.html?id="+id,"_blank");
}


function showDetail(){
	$(".banner_three")
	.hover(
			function() {
				$(this).children(".banner_mask,.banner_hover").show();
			}, function() {
				$(this).children(".banner_mask,.banner_hover").hide();
			});
}





