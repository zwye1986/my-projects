$(document).ready(function() {

	// When a link is clicked
	$("a.tab").click(function() {

		// switch all tabs off
		$(".active").removeClass("active");

		// switch this tab on
		$(this).addClass("active");

		// slide all content up
		$(".content").slideUp();

		// slide this content up
		var content_show = $(this).attr("mark");
		$("#" + content_show).slideDown();

	});
	
	//全部
	$("#all").click(function() {
		var param = {
			type : 'all',
			page : '1'
		};
		$.get("showGame", param, function(data) {
			$("#content_1").html(data);
		});
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

		//	$("#content_6").html(data);
		});
	});

});

function depositselect(type){
	gamePagination(type, 1,'','');
}

function openReward(str,type){
	var className = document.getElementById("dd_reward").className;
	if(className == "fanli"){
		$("#sortseq").val("down");
		document.getElementById("dd_reward").className="fanlidown";
		
	}else if(className == "fanlidown"){
		$("#sortseq").val("up");
		document.getElementById("dd_reward").className="fanliup";
	}else{
		$("#sortseq").val("down");
		document.getElementById("dd_reward").className="fanlidown";
	}
	$("#sort").val("reward");
	document.getElementById("dd_clicks").className = "fanli";
	document.getElementById("dd_hot").className = "fanli";
	document.getElementById("dd_deposit").className = "fanli";
	
	gamePagination(type, 1,'','');

}

function openClicks(str,type){
	var className = document.getElementById("dd_clicks").className;
	if(className == "fanli"){
		$("#sortseq").val("down");
		document.getElementById("dd_clicks").className="fanlidown";
	}else if(className == "fanlidown"){
		$("#sortseq").val("up");
		document.getElementById("dd_clicks").className="fanliup";
	}else{
		$("#sortseq").val("down");
		document.getElementById("dd_clicks").className="fanlidown";
	}
	$("#sort").val("clicks");
	document.getElementById("dd_reward").className = "fanli";
	document.getElementById("dd_hot").className = "fanli";
	document.getElementById("dd_deposit").className = "fanli";
	
	gamePagination(type, 1,'','');
}

function openHot(str,type){
	var className = document.getElementById("dd_hot").className;
	if(className == "fanli"){
		$("#sortseq").val("down");
		document.getElementById("dd_hot").className="fanlidown";
	}else if(className == "fanlidown"){
		$("#sortseq").val("up");
		document.getElementById("dd_hot").className="fanliup";
	}else{
		$("#sortseq").val("down");
		document.getElementById("dd_hot").className="fanlidown";
	}
	$("#sort").val("clickNum");
	document.getElementById("dd_clicks").className = "fanli";
	document.getElementById("dd_reward").className = "fanli";
	document.getElementById("dd_deposit").className = "fanli";
	
	gamePagination(type, 1,'','');
}

function openDeposit(str,type){
	var className = document.getElementById("dd_deposit").className;
	if(className == "fanli"){
		$("#sortseq").val("down");
		document.getElementById("dd_deposit").className="fanlidown";
	}else if(className == "fanlidown"){
		$("#sortseq").val("up");
		document.getElementById("dd_deposit").className="fanliup";
	}else{
		$("#sortseq").val("down");
		document.getElementById("dd_deposit").className="fanlidown";
	}
	$("#sort").val("deposit");
	document.getElementById("dd_clicks").className = "fanli";
	document.getElementById("dd_hot").className = "fanli";
	document.getElementById("dd_reward").className = "fanli";
	
	gamePagination(type, 1,'','');
}


//会员游戏特权分页
//游戏中心分页
function gamePaginationForVip(type, page,searchtype,keyword) {
	
	var param = {
		type : type,
		page : page,
		searchtype:searchtype,
		keyword:encodeURI(keyword),
		sort:$("#sort").val(),
		sortseq:$("#sortseq").val(),
		beginDeposit:$("#beginDeposit_"+type).val(),
		endDeposit:$("#endDeposit_"+type).val()
	
	};
	
	$.get("showVipGame", param, function(data) {
		if ($("#sort").val() == 'reward') {
			$("#conn_one_12").html(data);
		} else if ($("#sort").val() == 'clicks') {
			$("#conn_one_13").html(data);
		} else if ($("#sort").val() == 'deposit') {
			$("#conn_one_14").html(data);
		}else {
			$("#conn_one_11").html(data);
		}

	});
}

//上一页、下一页
function gamePaginationNextForVip(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page + 1,
		searchtype:searchtype,
		keyword:encodeURI(keyword),
		sort:$("#sort").val(),
		sortseq:$("#sortseq").val(),
		beginDeposit:$("#beginDeposit_"+type).val(),
		endDeposit:$("#endDeposit_"+type).val()
	};
	$.get("showVipGame", param, function(data) {
		if ($("#sort").val() == 'reward') {
			$("#conn_one_12").html(data);
		} else if ($("#sort").val() == 'clicks') {
			$("#conn_one_13").html(data);
		} else if ($("#sort").val() == 'deposit') {
			$("#conn_one_14").html(data);
		}else {
			$("#conn_one_11").html(data);
		}

	});
}

function gamePaginationPrevForVip(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page - 1,
		searchtype:searchtype,
		keyword:encodeURI(keyword),
		sort:$("#sort").val(),
		sortseq:$("#sortseq").val(),
		beginDeposit:$("#beginDeposit_"+type).val(),
		endDeposit:$("#endDeposit_"+type).val()
	};
	$.get("showVipGame", param, function(data) {
		if ($("#sort").val() == 'reward') {
			$("#conn_one_12").html(data);
		} else if ($("#sort").val() == 'clicks') {
			$("#conn_one_13").html(data);
		} else if ($("#sort").val() == 'deposit') {
			$("#conn_one_14").html(data);
		}else {
			$("#conn_one_11").html(data);
		}

	});
}

// 游戏中心分页
function gamePagination(type, page,searchtype,keyword) {
	
	var param = {
		type : type,
		page : page,
		searchtype:searchtype,
		keyword:encodeURI(keyword),
		sort:$("#sort").val(),
		sortseq:$("#sortseq").val(),
		beginDeposit:$("#beginDeposit_"+type).val(),
		endDeposit:$("#endDeposit_"+type).val()
	
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
function gamePaginationNext(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page + 1,
		searchtype:searchtype,
		keyword:encodeURI(keyword),
		sort:$("#sort").val(),
		sortseq:$("#sortseq").val(),
		beginDeposit:$("#beginDeposit_"+type).val(),
		endDeposit:$("#endDeposit_"+type).val()
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

function gamePaginationPrev(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page - 1,
		searchtype:searchtype,
		keyword:encodeURI(keyword),
		sort:$("#sort").val(),
		sortseq:$("#sortseq").val(),
		beginDeposit:$("#beginDeposit_"+type).val(),
		endDeposit:$("#endDeposit_"+type).val()
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

function searchSubmit(){
	$("#searchform").submit();
}

function gameDetailBox(id) {
	var rootpath = getRootPath();
	//更新游戏点击次数
	$.post(rootpath+"/updateClickNum", {id:id}, function(
			data) {
	
	});
	window.open(rootpath+"/showGameDetail.html?id="+id,"_blank");
}

function getRootPath(){
    var strFullPath=window.document.location.href;
    var strPath=window.document.location.pathname;
    var pos=strFullPath.indexOf(strPath);
    var prePath=strFullPath.substring(0,pos);
    var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
    return(prePath+postPath);
  }