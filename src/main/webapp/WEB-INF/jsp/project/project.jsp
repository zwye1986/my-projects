<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>

<link rel="stylesheet" type="text/css" href="${ctx }/css/global.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/global_projects.min.css"/>
<script type="text/javascript">
$(function(){
var type = "${type}";
$("#type"+type).addClass("current");
// 项目分类处理
$(".projects_type a").css("width", parseInt(1024/$(".projects_type a").size()));
$(".projects_type").slideDown(500);

	$.cookie('ly_flash',null);
	$.cookie('ly_flash',this.id);

$("#"+$.cookie('ly_flash')).hide();


$("#ly_del").click(function(){
	$("#ly_flash").hide();
});
$("#ly_list1").click(function(){
	$("#ly_list1").hide();
	$("#ly_list2").show();
});

$("#ly_list2").click(function(){
	$("#ly_list2").hide();
	$("#ly_list3").show();
});

$("#ly_list3").click(function(){
	$("#ly_list3").hide();
	$("#ly_list4").show();
});
$("#ly_list4").click(function(){
	$("#ly_list4").hide();
	$("#ly_list5").show();
});

$("#ly_list5").click(function(){
	$("#ly_list5").hide();
	$("#ly_list6").show();
});

$("#ly_list6").click(function(){
	$("#ly_flash").hide();
});
$("#projectLearn").click(function(){
	$("#ly_flash").show();
});

var exp=$.cookie('_zhouchou');

if (isEmpty(exp)){
	$.cookie('_zhouchou','zhouchou',{ expires: 1 });
	$("#ly_flash").show();
}else{
	$("#ly_flash").hide();
}

});

function isEmpty(s) 
{ 
return ((s == undefined || s == null || s == "") ? true : false); 
} 
</script>
  <div class="projects_type hide" style="display: none;">
    <div class="w_content"><a id="typeall" href="?type=all">全部</a><c:forEach items="${typeList }" var="item"><a id="type${item.id }" href="?type=${item.id }" class="">${item.name }</a></c:forEach></div>
  </div>
  <div class="plists" style="">
    <ul style="width:1044px;">
    <c:forEach items="${list }" var="item">
    <li class="brd_4">
        <div class="plists_status status_3 brd_0400"></div>
        <div class="plists_thumb"> <a href="${ctx }/${item.id }/projectDetail.html"  target="_blank"><img src="${ctx }/showProjectPic?id=${item.id}" onerror="this.src='${ctx }/images/zcpt/zc1.jpg'"   class="lazy brd_4" alt="梦中的莲花之地——《靖藏》创刊号等你来阅！" title="梦中的莲花之地——《靖藏》创刊号等你来阅！" width="220" height="150" /></a> </div>
        <div class="plists_middle">
          <div class="plists_title"><a href="javascript:void(0)" >${item.name }</a></div>
          <div class="plists_summarys">${item.nameDesc }</div>
          <div class="plists_author">
            <div class="from"><i></i>${item.addr }</div>
            <div class="name"><a href="javascript:void(0)" >蛙宝网</a></div>
          </div>
        </div>
        <div class="plists_bottom">
          <div class="plists_speed" title="当前项目进度为${item.scale }%">
            <div class="plists_speed_bg" style="width:${item.scale }%"></div>
          </div>
          <div class="plists_total">
            <div class="plists_total_money" title="当前已筹集${item.sumAmount }元资金"><em class="money"></em>${item.sumAmount }</div>
            <div class="plists_total_people" title="当前已有${item.persons }次支持"><em class="people"></em>${item.persons }</div>
            <div class="plists_total_time" title="离项目结束仅剩${item.endDays }天"><em class="time"></em>${item.endDays }</div>
            <div class="clear"></div>
          </div>
        </div>
      </li>
    </c:forEach>
      <div class="clear"></div>
    </ul>
    <div class="pages" style="margin:20px 0 0 0; _padding-left:25px;">
    <c:if test="${page != 1 && page !=0 }">
    <a href='${ctx }/project.html?type=${type }&page=${page-1 }'>上一页</a>
    </c:if>
    <c:forEach begin="${beginPage }" end="${endPage }" step="1" var="item">
    <c:choose>
    <c:when test="${page == item }">
    <span class='current'>${item }</span>
    </c:when>
    <c:otherwise>
    <a href='${ctx }/project.html?type=${type }&page=${item }'>${item }</a>
    </c:otherwise>
    </c:choose>
    
    </c:forEach>
    <c:if test="${page != endPage }">
    <a href='${ctx }/project.html?type=${type }&page=${page+1 }'>下一页</a>
    </c:if>
     </div>
  </div>
  <div class="clear"></div>
  
 <div class="ly_flash" id="ly_flash" style="display: none;">
<a  class="ly_del" id="ly_del">X</a>
<div class="ly_list1" style="display:block;" id="ly_list1"><img src="images/zcpt/1.gif"/><p></p></div>
<div class="ly_list2" id="ly_list2"><img src="${ctx}/images/zcpt/2.gif"/><p></p></div>
<div class="ly_list3" id="ly_list3"><img src="${ctx}/images/zcpt/3.gif"/><p></p></div>
<div class="ly_list4" id="ly_list4"><img src="${ctx}/images/zcpt/4.gif"/><p></p></div>
<div class="ly_list5" id="ly_list5"><img src="${ctx}/images/zcpt/5.gif"/><p></p></div>
<div class="ly_list6" id="ly_list6"><img src="${ctx}/images/zcpt/6.gif"/><p></p></div>
</div>
<div class="projectLearn" id="projectLearn"><a href="###"><img src="${ctx }/images/zcpt/zclc.png" /></a></div>
  