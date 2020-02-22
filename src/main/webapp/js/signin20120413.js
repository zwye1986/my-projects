var selectArea="";
var selectCity="";

var myDate = new Date();
var year=myDate.getFullYear();
var month=myDate.getMonth()+1;
var day=myDate.getDate();


var date=year+""+month+""+day;


var todayweek=getWeekth(myDate.getDay());

var tommorrow=new Date(); 
tommorrow.setDate(tommorrow.getDate()+1); 
var tomorrowweek=getWeekth(tommorrow.getDay());
var todaydate=year+"/"+month+"/"+day;

var tyear=tommorrow.getFullYear();
var tmonth=tommorrow.getMonth()+1;
var tday=tommorrow.getDate();
var tomorrowdate=tyear+"/"+tmonth+"/"+tday;


var weatherflag = {
		"晴":"0",
		"多云":"1",
		"阴":"2",
		"阵雨":"3",
		"雨夹雪":"6",
		"小雨":"7",
		"中雨":"8",
		"大雨":"9","暴雨":"9","雷阵雨":"9","大到暴雨":"9",
		"阵雪":"13",
		"小雪":"14",
		"中雪":"15",
		"大雪":"16","暴雪":"16",
		"雾":"18",
		"冻雨":"19",
		"小到中雨":"21",
		"中到大雨":"8",
		"小到中雪":"26",
		"中到大雪":"27",
		"大到暴雪":"28",
		"浮尘":"29",
		"扬沙":"30"
		}

var weathermode="nologin";

var ifweather=true;

function getWeekth(weekth){
	var str="";
	if(weekth == 0){
		str = "周日";
	}else if(weekth == 1){
		str = "周一";
	}else if(weekth == 2){
		str = "周二";
	}else if(weekth == 3){
		str = "周三";
	}else if(weekth == 4){
		str = "周四";
	}else if(weekth == 5){
		str = "周五";
	}else if(weekth == 6){
		str = "周六";
	}
	return str;
}

function fGetCookie(sName) {
	var sSearch = sName + "=";
	if(document.cookie.length > 0) {
	var sOffset = document.cookie.indexOf(sSearch);
	if(sOffset != -1) {
	sOffset += sSearch.length;
	sEnd = document.cookie.indexOf(";", sOffset);
	if(sEnd == -1) sEnd = document.cookie.length;
	return document.cookie.substring(sOffset, sEnd);
	}
	else return "";
	}
	}
	function fGetUrsCity(){
	var sPInfo = fGetCookie("P_INFO");
	var sReturn = "";
	try{
	if(sPInfo){
	var sUrsCity = sPInfo.split("|")[5].split("#")[1].split("&")[1];
	if(urs2cityid){
	if(urs2cityid[sUrsCity]){
	sReturn = urs2cityid[sUrsCity];
	}else{
	sUrsCity = sUrsCity.substr(0,2)+"0000";
	if(urs2cityid[sUrsCity]){
	sReturn = urs2cityid[sUrsCity];
	}
	}
	}
	}
	}catch(e){
	}
	return sReturn;
	}

function fCommonGetScript(sUrl, sCharset){
	var oJs = document.createElement("script");
	oJs.setAttribute("src", sUrl);
	oJs.setAttribute("charset", sCharset || "utf-8");
	oJs.setAttribute("type", "text/javascript");
	document.body.appendChild(oJs);
	return true;
}


function checkifgetCitymap(){
 	if(citymap){
 		clearInterval(checktimer);  
 		for(var item in citymap){
 			$("#areaSelect").append("<option  value=\""+item+"\">"+item+"</option>");
 			$("#selectAreahuoxing").append("<option  value=\""+item+"\">"+item+"</option>");
 		}
 	}
}   

function changeArea(me,sid){
	//alert(me.value);
	//clear selectcity
	$("#selectCity").empty();
	$("#selectCity").append("<option id=\"defaultCity2\" >请选择城市</option>");
	$("#selectCityhuoxing").append("<option id=\"defaultCity1\" >请选择城市</option>");
	if(citymap){
		selectArea=me.value;
		
		for(var item in citymap[me.value]){
			
			$("#selectCity").append("<option  value=\""+item+"\">"+item+"</option>");
			$("#selectCityhuoxing").append("<option   value=\""+item+"\">"+item+"</option>");
		}
	}
		
}

function changeCity(me){
	selectCity=me.value;
	
	
}

$(document).ready(function(){
	 var date=new Date;
	 var month=date.getMonth()+1;
	   changeMonth(month);	   
 /*  if(ifsignin=="true"){
	   $("#signinBtn").attr("class","btn sign-btn sign-btn-succ");
   }*/
 //天气预报相关
   

	
   
//签到按钮
$("#signinBtn").bind("click",function(){
	if(uid==""){
		window.parent.gPrompt.showLogin(); 
		return false;
	}
	if(ifsignin=="true")
		return ;
	var params={
			ajaxurlwithparam:"signin.do?from=index&random="+Math.random(),
			method:"GET",
			data:{}
	};
	var result=ajaxRequest(params);
	if(result.retCode="200"){
		var dataa=result.data;
		if(dataa==401){
			alert("已经签到过，请明天再来签到");
			return false;
		}
		
	
	window.parent.gPrompt.showDialog("success","<strong>"+dataa+"</strong> 个邮箱积分已自动添加至本帐号上~ <a  href=\"http://club.mail.163.com/jifen/ucenter/jifen.do\" target=\"_blank\">查看积分明细>></a><br /><span class=\"txt-info\">温馨提示：您还可换个网易邮箱帐号继续签到哦！</span>","","签到成功");	
	$("#signinBtn").attr("class","btn sign-btn sign-btn-succ");
	window.location.reload();
	}
	
	return false;
});
	
//日历相关

//切换账号
$("#dvAccountLnk").bind("click",function(){
	if(uid==""){
		window.parent.gPrompt.showLogin(); 
		return false;
	}
	AccountRelate.showMenu();
	fAccountRelateWrap();
		
});

///////查看天气按钮
$("#checkWeather").bind("click",function(){
	 
	  if(selectArea==""||selectCity==""){
		alert("您还未选择地点！请选择地点后再进行下一步操作～");
		
			return false;
	} else{
		  if(citymap){
			  citycode=citymap[selectArea][selectCity];
			  
			  checkWeatheOfCity(citycode+"")
		  }
	  }
	  return false;
}); 

$("#changeCity3").click(function(){
	$("#changeCityBox").show();
});

$("#changeCity2").click(function(){
	$("#changeCityBox").show();
});

$("#cancelBtn").click(function(){
	$("#changeCityBox").hide();
});

$("#tdate").html("（"+todaydate+"，"+todayweek+" ）");
$("#mdate").html("（"+tomorrowdate+"，"+tomorrowweek+" ）");


//设置为默认按钮
$("#setdefalut").bind("click",function(){
	 if(selectArea==""||selectCity==""){
		 alert("您还未选择地点！请选择地点后再进行下一步操作～");
			return false;
			  }
		$.cookie.defaults = { path: '/'};
		$.cookie('citycodecookie',citymap[selectArea][selectCity]);
		 $("#huoxingbox").hide();
		  $("#zhengchangbox").show();
		  $("#errorbox").hide();
		  checkWeatheOfCity(citymap[selectArea][selectCity]+"");
}); 

//默认进来

var sUrsCity = $.cookie('citycodecookie');
    if (typeof(sUrsCity) == "undefined" || sUrsCity == null) {
    	sUrsCity=58238;
    	 $("#huoxingbox").hide();
		  $("#zhengchangbox").show();
		  $("#errorbox").hide();
		  checkWeatheOfCity(sUrsCity+"");
	} else{
		  $("#huoxingbox").hide();
		  $("#zhengchangbox").show();
		  $("#errorbox").hide();
		  checkWeatheOfCity(sUrsCity+"");
	}  
 
	

 });

/////////////////////////////// 


/////////////////////////////// //////////////////
//切换月份
function changeMonth(cmonth){
   if(cmonth>month)	
	   return ;
   $("#monthselect").attr("class","sign-wMonth-list sign-wMonth-"+cmonth);
   $("#month").text(cmonth);
   var month;
   if(cmonth<10){
	   month="0"+cmonth;
   }else{
	   month=cmonth;
   }   
   var getYear=new Date;
   
   var year=getYear.getFullYear();
   var params={
			ajaxurlwithparam:"signInDetailSerach.do?year="+year+"&month="+month+"&random="+Math.random(),
			method:"POST",
			data:{}
	};
	var result=ajaxRequest(params);
	var index=1;
	var dataitem=result.data;
	var ifthismonth=false;
	var signincount=0;
	for(index=0;index<42;index++){
		var item=index+1;
		$("#date"+item).text("");
		$("#date"+item).attr("class","");
		$("#date"+item).text(dataitem[index].day);
		if(dataitem[index].month==cmonth){
		    if(dataitem[index].num==2){
		    	$("#date"+item).append("<b class=\"ico date-ico-hook\"></b>");
            	$("#date"+item).attr("class","signed");
            	signincount++;
		    }else if(dataitem[index].num==8){
		    	$("#date"+item).append("<b class=\"ico date-ico-hook\"></b><b class=\"ico date-ico-eight\"></b>");
		    	$("#date"+item).attr("class","signed");
		    	signincount++;
		    } 	
		}else{
            if(dataitem[index].num==2){
            	$("#date"+item).append("<b class=\"ico date-ico-hook\"></b>");
            	$("#date"+item).attr("class","off signed");
		    }else if(dataitem[index].num==8){
		    	$("#date"+item).append("<b class=\"ico date-ico-hook\"></b><b class=\"ico date-ico-eight\"></b>");
		    	$("#date"+item).attr("class","off signed");
		    }else{
		    	$("#date"+item).attr("class","off");
		    } 	
		}
	}
	$("#signincount").text(signincount);
}



//公用function
/**
 * ajax请求，返回JSON对象
 */
function ajaxRequest(params){
	var randnum = Math.random();
	var ajaxurl = params.ajaxurlwithparam;
	var ifasync = params.ifasyncinre;
	if(ifasync!=true||ifasync!=false){
		ifasync=false;
	}
	var re=null;
	var method=params.method;
	if(method==null||method=="")
		method="GET";
	var data=params.data;
	if(data==null||data=="")
		data="";
	$.ajax({
		type : method,
		url : ajaxurl,
		async : ifasync,
		data : data,
		dataType : 'json',
		success : function(result) {
			re=result;
		}
	});
	return re;
}
function getCitybyCityCode(citymap,citycode){
	if(citymap){
 		
 		for(var item in citymap){
 			for(var city in item){
 				if(item[city]==citycode){
 					return city
 				}
 			}
 		}
 	}else{
 		return "";
 	}
}


function logReg(logdata){
	ifsendlog=true;
	if(ifsendlog==false)
		return;
	var randnum = Math.random();
	logdata.rand=randnum+"";
	 var params={
				ajaxurlwithparam:"forlog.do",
				method:"GET",
				data:logdata,
				ifasyncinre:true
		};
	    var ajaxre=ajaxRequest(params);
}


/**
 * 计算字符数，一个中文2个字符
 */
function fLen(Obj){
  var nCNLenth = 0;
  var nLenth = Obj.length;
  for (var i=0; i<nLenth; i++){
    if(Obj.charCodeAt(i)>255){
      nCNLenth += 2; 
    }else{
      nCNLenth++;
    }
  }
  return nCNLenth;
}

/////////////////////////////// 
//查看具体城市天气函数
function checkWeatheOfCity(citycode){
	
	var requesturl="http://mimg.127.net/weather/"+date+"/"+citycode.substring(0,2)+"/"+citycode.substring(2,4)+"/"+citycode.substring(4,citycode.length)+".js";
   
	if(citycode.substring(4,citycode.length)==""){
	 requesturl="http://mimg.127.net/weather/"+date+"/"+citycode.substring(0,2)+"/"+citycode.substring(2,4)+".js";
		
	}
	
		//kuayu
	var ifacc=true;
  if(ifacc){
  var requestData = document.createElement("script") ;  
  requestData.type = "text/javascript";  
  requestData.charset="GBK";
  requestData.src = requesturl
  document.getElementsByTagName('head')[0].appendChild(requestData);
  
  }

  checkweathertimer = setInterval("checkifcitydataOk()",500);
	//kuayu
  
  
  
	
}

function checkifcitydataOk(){
	if(city){
		clearInterval(checkweathertimer);  
	//	if(city.name==selectCity){
	 	if(true){	
	 		//设置天气内容
			$("#changeCityBox").hide();
			$("#futureSeven").attr("href",city.exturl)
			if(fLen(city.name)>8){
				$("#forbigfont").attr("class","sign-mulUser sign-mulUser-small");
			}else{
				$("#forbigfont").attr("class","sign-mulUser");
			}
			
			$("#changeCity3").text(city.name);
			$("#todayt").text(city.wendu[0]+"~"+city.wendu[1]);
			$("#todayweather").text(city.tianqi[0]);
			
			if(city.tianqi[0]!=""&&city.tianqi[1]!=""){
				$("#todaymark").attr("src","http://mimg.127.net/p/js35/lib/img/weather/"+weatherflag[city.tianqi[0]]+".png");
				$("#todaymarknight").attr("src","http://mimg.127.net/p/js35/lib/img/weather/"+weatherflag[city.tianqi[1]]+".png");
				$("#tomorrowweather").text(city.tianqim[0]);
			}
			
			
		
			if(city.tianqim[0]!=""&&city.tianqim[1]!=""){
				$("#tomorrowt").text(city.wendum[0]+"~"+city.wendum[1]);
				$("#tomorrowmark").attr("src","http://mimg.127.net/p/js35/lib/img/weather/"+weatherflag[city.tianqim[0]]+".png");
				$("#tomorrowmarknight").attr("src","http://mimg.127.net/p/js35/lib/img/weather/"+weatherflag[city.tianqim[1]]+".png");
			}
			
			//设置分享内容
			var shareTitle=city.name+"天气预报：";
			var shareDesc="今天（"+todaydate+"，"+todayweek+"）："+city.tianqi[0]+"，温度："+city.wendu[0]+"～"+city.wendu[1]+"°C；明天（"+tomorrowdate+"，"+tomorrowweek+"）："+city.tianqi[1]+"，温度："+city.wendum[0]+"～"+city.wendum[1]+"°C ";
			$("#bdshare").attr("data","{'url':http://www.wowpower.cn/signInDetail,'text':"+shareTitle+shareDesc+"}");
			
		}
	 	
		clearInterval(checkweathertimer);  
		
		
		refreshSelect();
	}
	
	function refreshSelect(){
		selectArea="";
		selectCity="";
		$("#defaultArea2").attr("selected",true);
		$("#defaultArea1").attr("selected",true);
		$("#defaultCity2").attr("selected",true);
		$("#defaultCity1").attr("selected",true);
	}
	
	
	
}

