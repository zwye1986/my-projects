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
	if(type=='ing'){
		$.get("${ctx}/user/credits/getObtainlist.html", param, function(data) {
				$("#con_one_9").html(data);
		});
	}else{
		$.get("${ctx}/user/credits/getConvertlist.html", param, function(data) {
				$("#con_one_8").html(data);
			
		});
	}
}

function gamePaginationNext(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page + 1
	};
	if(type=='ing'){
		$.get("${ctx}/user/credits/getObtainlist.html", param, function(data) {
				$("#con_one_9").html(data);
		});
	}else{
		$.get("${ctx}/user/credits/getConvertlist.html", param, function(data) {
				$("#con_one_8").html(data);
			
		});
	}
}

function gamePaginationPrev(type, page,searchtype,keyword) {
	var param = {
		type : type,
		page : page - 1
	};
	if(type=='ing'){
		$.get("${ctx}/user/credits/getObtainlist.html", param, function(data) {
				$("#con_one_9").html(data);
		});
	}else{
		$.get("${ctx}/user/credits/getConvertlist.html", param, function(data) {
				$("#con_one_8").html(data);
			
		});
	}
}

$(function(){
	var type=getQueryString("type");
	if(!type){
    	$("#one1").addClass("hover");
		gamePagination("ing",1,"","");
		$("#one2").click(function(){
		gamePagination("over",1,"","");
	    });
		}else if(type=='ing'){
			$("#one1").addClass("hover");
			gamePagination("ing",1,"","");
			
			$("#one2").click(function(){
			gamePagination("over",1,"","");
		    });
	}else{
		$("#con_one_9").hide();
		$("#one2").addClass("hover");
	    $("#con_one_8").show();
		   gamePagination("over",1,"","");
		$("#one1").click(function(){
		   gamePagination("ing",1,"","");
	    });
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
      <h2> 当前积分：${credits }</h2>
     </div>
      <!--选项卡开始-->
      <div class="tab_box">
        <div class="lyz_tab_right">
          <div class="hover" id="con_one_9" style="display:block">
            
          </div>
          <div class="hover" id="con_one_8" style="display:none">
            
          </div>
        </div>
        <div class="clear"></div>
      </div>
    <!--选项卡结束--> 
  </div>
