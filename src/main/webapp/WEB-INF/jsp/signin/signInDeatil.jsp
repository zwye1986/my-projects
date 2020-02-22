<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="蛙宝网致力于搭建一个广大用户与游戏共赢的媒介平台，通过【点】游戏，用户可以拿到轻松的返利。对于广大的用户，我们会长期为你们提供更好的服务，我们会尽量多为用户提供创造收益的渠道，蛙宝网的发展离不开你们的支持。我们的目标是让大家以轻松的游戏来获取更大的收益。"/>
<meta name="keywords" content="蛙宝网,蛙宝,赚钱,注册返现,wowpower,挖宝,网赚,注册提现,	理财,返利,积分活动,打码,网上挣零花钱,赚钱,点击游戏,网赚项目,注册网赚" />

<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/user.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>
<link href="${ctx}/css/base.css" rel="stylesheet" />
<link href="${ctx}/css/coolpos.css" rel="stylesheet" />
<link href="${ctx}/css/user.css" rel="stylesheet" />
<script type="text/javascript">

var $ = function (id) {
    return "string" == typeof id ? document.getElementById(id) : id;
};

function formatstr(num){
	if(num<10){
		return 0+""+num;
	}  
	return num;
  };

var Class = {
  create: function() {
    return function() {
      this.initialize.apply(this, arguments);
    }
  }
}

var Extend = function(destination, source) {
    for (var property in source) {
        destination[property] = source[property];
    }
    return destination;
}


var Calendar = Class.create();
Calendar.prototype = {
  initialize: function(container, options) {
	this.Container = $(container);//容器(table结构)
	this.Days = [];//日期对象列表
	
	this.SetOptions(options);
	
	this.Year = this.options.Year || new Date().getFullYear();
	this.Month = this.options.Month || new Date().getMonth() + 1;
	this.SelectDay = this.options.SelectDay ? new Date(this.options.SelectDay) : null;
	this.onSelectDay = this.options.onSelectDay;
	this.onToday = this.options.onToday;
	this.onFinish = this.options.onFinish;	
	
	this.Draw();
  },
  

  //设置默认属性
  SetOptions: function(options) {
	this.options = {//默认值
		Year:			0,//显示年
		Month:			0,//显示月
		SelectDay:		null,//选择日期
		onSelectDay:	function(){},//在选择日期触发
		onToday:		function(){},//在当天日期触发
		onFinish:		function(){}//日历画完后触发
	};
	Extend(this.options, options || {});
  },
  //上一月
  PreMonth: function() {
	 var d= new Date(this.Year, this.Month - 2, 1);
     this.PreDraw(d);
     document.getElementById("selectMonth").value=d.getFullYear()+""+formatstr((d.getMonth()+1))+""+formatstr(d.getDate());
  },
  //下一月
  NextMonth: function() {
	
	var d=new Date(this.Year, this.Month, 1);
	this.PreDraw(d);
	 document.getElementById("selectMonth").value=d.getFullYear()+""+formatstr((d.getMonth()+1))+""+formatstr(d.getDate());
  },
 
 
  //根据日期画日历
  PreDraw: function(date) {
	//再设置属性
	this.Year = date.getFullYear(); this.Month = date.getMonth() + 1;
	//重新画日历
	this.Draw();
  },
  //画日历
  Draw: function() {
	//用来保存日期列表
	var arr = [];
	//用当月第一天在一周中的日期值作为当月离第一天的天数
	for(var i = 1, firstDay = new Date(this.Year, this.Month - 1, 1).getDay(); i <= firstDay; i++){ arr.push(0); }
	//用当月最后一天在一个月中的日期值作为当月的天数
	for(var i = 1, monthDay = new Date(this.Year, this.Month, 0).getDate(); i <= monthDay; i++){ arr.push(i); }
	//清空原来的日期对象列表
	this.Days = [];
	//插入日期
	var frag = document.createDocumentFragment();
	while(arr.length){
		//每个星期插入一个tr
		var row = document.createElement("tr");
		//每个星期有7天
		for(var i = 1; i <= 7; i++){
			var cell = document.createElement("td"); cell.innerHTML = "&nbsp;";
			if(arr.length){
				var d = arr.shift();
				if(d){
					cell.innerHTML = d;
					this.Days[d] = cell;
					var on = new Date(this.Year, this.Month - 1, d);
					//判断是否今日
					this.IsSame(on, new Date()) && this.onToday(cell);
					//判断是否选择日期
					this.SelectDay && this.IsSame(on, this.SelectDay) && this.onSelectDay(cell);
				}
			}
			row.appendChild(cell);
		}
		frag.appendChild(row);
	}
	//先清空内容再插入(ie的table不能用innerHTML)
	while(this.Container.hasChildNodes()){ this.Container.removeChild(this.Container.firstChild); }
	this.Container.appendChild(frag);
	//附加程序
	this.onFinish();
  },
  //判断是否同一日
  IsSame: function(d1, d2) {
	return (d1.getFullYear() == d2.getFullYear() && d1.getMonth() == d2.getMonth() && d1.getDate() == d2.getDate());
  } 
}
</script>
<style type="text/css">

.Calendar {
	font-family:Verdana;
	font-size:12px;
	background-color:#e0ecf9;
	margin-left:auto;
	margin-right:auto;
	width:200px;
	height:320px;
	padding:10px;
	line-height:1.5em;
}
.Calendar a{
	color:#1e5494;
}

.Calendar table{
	width:100%; 
	border:0;
}

.Calendar table thead{color:#acacac;}

.Calendar table td {
	font-size: 11px;
	padding:1px;
}
#idCalendarPre{
	cursor:pointer;
	float:left;
	padding-right:5px;
}
#idCalendarNext{
	cursor:pointer;
	float:right;
	padding-right:5px;
}
#idCalendar td.onToday {
	font-weight:bold;
	color:#C60;
}
#idCalendar td.onSelect {
	font-weight:bold;
}
</style>
<div class="Calendar">
  <div id="idCalendarPre">&lt;&lt;</div>
  <div id="idCalendarNext">&gt;&gt;</div>
  <input id="daysself" type="hidden" value="${dayHasSignIn}"/>
   <input id="selectMonth" type="hidden" value=""/>
  <span id="idCalendarYear"></span>年 <span id="idCalendarMonth"></span>月
  <table cellspacing="0">
    <thead>
      <tr>
        <td>日</td>
        <td>一</td>
        <td>二</td>
        <td>三</td>
        <td>四</td>
        <td>五</td>
        <td>六</td>
      </tr>
    </thead>
    <tbody id="idCalendar">
    </tbody>
  </table>
</div>
<script language="JavaScript">

var cale = new Calendar("idCalendar", {
	SelectDay: new Date().setDate(10),
	onSelectDay: function(o){ o.className = "onSelect"; },
	onToday: function(o){ o.className = "onToday"; },
	onFinish: function(){
		$("idCalendarYear").innerHTML = this.Year; $("idCalendarMonth").innerHTML = this.Month;
		var flag = new Array();
		var arr = document.getElementById("daysself").value;
		if (arr==null ||arr==""){
			   
	        }else{
	        	flag=arr.split(",");
			    for(var i = 0, len = flag.length; i < len; i++){
				this.Days[flag[i]].innerHTML = "<font color='red'>" + flag[i] + "</font>";
	        }
		    }
	}
});

/**
 * 得到ajax对象
 */
function getajaxHttp() {
	var xmlHttp;
	try {
		// Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("您的浏览器不支持AJAX！");
				return false;
			}
		}
	}
	return xmlHttp;
}
/**
 * 发送ajax请求
 * url--url
 * methodtype(post/get)
 * con (true(异步)|false(同步))
 * parameter(参数)
 * functionName(回调方法名，不需要引号,这里只有成功的时候才调用)
 * (注意：这方法有二个参数，一个就是xmlhttp,一个就是要处理的对象)
 * obj需要到回调方法中处理的对象
 */
function ajaxrequest(url,methodtype,con,functionName){
	var xmlhttp=getajaxHttp();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4){
			 if( xmlhttp.status == 200 )
		     {
			//HTTP响应已经完全接收才调用
			functionName(xmlhttp);
			 
		     }
		}
	};
	xmlhttp.open(methodtype,url,con);
	xmlhttp.send();
}

function c(xmlhttp){
	var cale = new Calendar("idCalendar", {
		onFinish: function(){
	    var flag = new Array();
	    var arr = xmlhttp.responseText;
	    if (arr==null ||arr==""){
	   	 	
	    }else{
	    	flag=arr.split(",");
	    	for(var i = 0, len = flag.length; i < len; i++){
			this.Days[flag[i]].innerHTML = "<font color='red'>" + flag[i] + "</font>";
	    }
		}
		}
	});
}
//测试

$("idCalendarPre").onclick = function(){ 
	cale.PreMonth(); 
	var pre=document.getElementById("selectMonth").value;
	ajaxrequest("${ctx}/user/account/signInDetailSerach?searchTime="+pre,"get",true,c);
};
$("idCalendarNext").onclick = function(){ 
	cale.NextMonth(); 
	var next=document.getElementById("selectMonth").value;
	ajaxrequest("${ctx}/user/account/signInDetailSerach?searchTime="+next,"get",true,c);
};
</script>
</body>
</html>