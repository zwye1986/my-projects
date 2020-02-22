<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/account.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/level.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
function setTab2(name,cursel,n){
	for(i=1;i<=n;i++){
	var menu=document.getElementById(name+i);
	var con=document.getElementById("con_"+name+"_"+i);
	menu.className=i==cursel?"hover":"";
	con.style.display=i==cursel?"block":"none";
	}
	}
	
function gamePagination(type, page,searchtype,keyword) {
	
	var param = {
		type : type,
		page : page
	};
	if(type=='mycode'){
		$.get("${ctx}/user/account/getMyInviteCode", param, function(data) {
				$("#con_one_9").html(data);
		});
	}else if(type=='myInviteUser'){
		$.get("${ctx}/user/account/getMyInviteUser", param, function(data) {
				$("#con_one_7").html(data);
		});
	}else if(type=='getMyInviteList'){
		$.get("${ctx}/user/account/getMyInviteList", param, function(data) {
			$("#con_one_8").html(data);
		  
	});
	}else{
		$.get("${ctx}/user/account/getMyInviteList", param, function(data) {
			$("#con_one_8").html(data);
	});
	}
} 

function gamePaginationNext(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page + 1
	};
	if(type=='mycode'){
		$.get("${ctx}/user/account/getMyInviteCode", param, function(data) {
				$("#con_one_9").html(data);
		});
	}else if(type=='myInviteUser'){
		$.get("${ctx}/user/account/getMyInviteUser", param, function(data) {
				$("#con_one_7").html(data);
			  
		});
	}else{
		$.get("${ctx}/user/account/getMyInviteList", param, function(data) {
			$("#con_one_8").html(data);
	});
	}
}

function gamePaginationPrev(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page - 1
	};
	if(type=='mycode'){
		$.get("${ctx}/user/account/getMyInviteCode", param, function(data) {
				$("#con_one_9").html(data);
		});
	}else if(type=='myInviteUser'){
		$.get("${ctx}/user/account/getMyInviteUser", param, function(data) {
				$("#con_one_7").html(data);
			  
		});
	}else{
		$.get("${ctx}/user/account/getMyInviteList", param, function(data) {
			$("#con_one_8").html(data);
	});
	}
}

$(function(){
	var type=getQueryString("type");
	if(!type){
		$("#con_one_9").show();
		gamePagination("mycode", 1,"","");
		$("#con_one_7").hide();
		$("#con_one_8").hide();
		}else if(type=='mycode'){
			$("#con_one_9").show();
			gamePagination("mycode", 1,"","");
			$("#con_one_7").hide();
			$("#con_one_8").hide();
		}else if(type=='getMyInviteList'){
			$("#con_one_8").show();
			$("#con_one_7").hide();
			$("#con_one_9").hide();
			gamePagination("getMyInviteList", 1,"","");
		}else if(type=='myInviteUser'){
			$("#con_one_7").show();
			$("#con_one_8").hide();
			$("#con_one_9").hide();
			gamePagination("myInviteUser", 1,"","");
		}
	
	
	});
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }
    	
</script>
 
  <!--main-->
  <div id="main">
    <div class="title-items">
     	<h2>邀请奖励：¥${total}</h2></div>
    <div class="model-box"> 
      <!--选项卡开始-->
      <div class="tab_box">
          <div class="hover" id="con_one_9" style="display:block">
          </div>
          <div  id="con_one_8" style="display:none">
          
          </div>
          <div  id="con_one_7" style="display:none">
          </div>
        <div class="clear"></div>
      </div>
    </div>
    <!--选项卡结束--> 
  </div>
