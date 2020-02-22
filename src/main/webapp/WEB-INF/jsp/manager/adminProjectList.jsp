<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${ctx }/css/manager/main.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript">
//分页验证
function paginate(forward){
	if(forward)	{
		document.getElementById("page.currentPage").value=forward;
	}else{
		var temp=document.getElementById("forwardPage").value;		
		if(temp=="")
		{
			top.jQuery.alerts.alert($um_tp_pageNumIsNull,$um_role_hint);
			return false;
		}
    	var reg = /^[0-9]\d*$/;
    	if(!reg.test(temp)) {
    		top.jQuery.alerts.alert($um_tp_pageNumIsNotNumber,$um_role_hint);
    		return false;
    	} 
    	if( temp < 1 || temp > ${page.totalPages})
    	{
    		top.jQuery.alerts.alert($um_tp_pageNumOutOfScope,$um_role_hint);
    		return false;
    	}
		document.getElementById("page.currentPage").value=document.getElementById("forwardPage").value;
	}
	document.all.queryListForm.submit();
}

function openReward(){
	var sortseq = $("#sortseq").val();
	if(sortseq == "down"){
		$("#sortseq").val("up");
	}else if(sortseq == "up"){
		$("#sortseq").val("down");
	}else{
		$("#sortseq").val("down");
	}
	$("#sort").val("reward");
	$("#queryListForm").submit();
}

function openClicks(){
	var sortseq = $("#sortseq").val();
	if(sortseq == "down"){
		$("#sortseq").val("up");
	}else if(sortseq == "up"){
		$("#sortseq").val("down");
	}else{
		$("#sortseq").val("down");
	}
	$("#sort").val("clicks");
	$("#queryListForm").submit();
}

function openPunish(){
	var sortseq = $("#sortseq").val();
	if(sortseq == "down"){
		$("#sortseq").val("up");
	}else if(sortseq == "up"){
		$("#sortseq").val("down");
	}else{
		$("#sortseq").val("down");
	}
	$("#sort").val("punish");
	$("#queryListForm").submit();
}

function openDeposit(){
	var sortseq = $("#sortseq").val();
	if(sortseq == "down"){
		$("#sortseq").val("up");
	}else if(sortseq == "up"){
		$("#sortseq").val("down");
	}else{
		$("#sortseq").val("down");
	}
	$("#sort").val("deposit");
	$("#queryListForm").submit();
}

function add(){
	window.location.href="adminProjectAdd";
}

function recommend(){
	window.location.href="adminRecommendGameList";
}

function changeStatus(a){
	if(confirm("确定删除所选记录吗？")){
		$("#ids").val(a);
		$("#queryListForm").attr("action","${ctx}/manager/adminProjectDel");
		$("#queryListForm").submit();
	}else{
		return false;
	}

}

function addRecommend(id){
	var params = { id:id};
	   $.ajax({
	          type: "POST",
	          async: true,
	          data:params,
	          cache: false,
	          url: "${ctx}/manager/addRecommend",
	          success: function(data){
	          alert("推荐成功");
	          }
	   });
}
</script>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
	background-color: #f1f1f2;
	 color:#5c5c5c;
}
em {
	font-style: normal;
	color: #fff;
}
.txt {
	color: #fff;
}
.STYLE1 {
	color: #fff;
	font-size: 16px;
}
.STYLE6 {
	color: #000000;
	font-size: 12;
}
.STYLE10 {
	color: #000000;
	font-size: 12px;
}
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
	position:relative;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.id,.phone,.user{
	width:45%;
	height:22px;
	

}
.date{height:22px; width:15%;}
.money{ height:22px;  width:15%;}
#btn {
	width: 86px;
	height: 31px;
	background: url(${ctx}/images/admin/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}
.cx{ width:59px; height:22px; background:url(${ctx}/images/admin/cx_btn.png) no-repeat; cursor:pointer; border:none; color:#fff;}
a.jsxg{ width:15px; text-align:center; cursor:pointer; color:#0cfec2; margin-left:5px;}
.selected td{ background:#e1f6f1;}
.tck{ width:363px; height:268px; position:absolute; background:#fff; right:180px; border:1px solid #cacaca; z-index:2; top:265px;}
.tck .tck_t{width:363px; height:40px; background:#3b3a3f; margin-top:0px; float:left; }
.tck_xx{ width:120px; color:#fff; height:40px; line-height:40px;font-size:16px; float:left;}
.del{ float:right; height:40px; width:40px; background:url(${ctx}/images/admin/del.png); cursor:pointer;}
.tck form{ width:343px; text-align:left; color:#3b3a3f; font-size:12px;}
.tck_list{width:343px; line-height:18px; }
.tck_la{ width:80px; text-align:right; float:left; height:18px; line-height:18px; margin-right:5px;}
.qx_btn{ width:55px; height:34px; background:url(${ctx}/images/admin/qx_btn.png) no-repeat; color:#00ce9b; border:none; cursor:pointer; font-size:16px;}
.qx_btn2{ width:95px; height:34px; background:url(${ctx}/images/admin/qx_btn01.png) no-repeat; color:#00ce9b; border:none; cursor:pointer; font-size:16px;}
.czmm{ cursor:pointer;}
.czmm:hover{ text-decoration:underline;}
-->
</style>
</head>

<body style="padding:25px;">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
     
        <tr>
          <td height="62" bgcolor="#3b3a3f" style="border-bottom:1px solid  #d5d1c8;border-top:1px solid  #d5d1c8; padding:0 20px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <form id="queryListForm" name="queryListForm" target="_self" action="${ctx }/manager/adminProjectList.html" method="post">
              <input type="hidden" name="pageSize" id="page.pageSize" value="${page.pageSize }" />
	<input type="hidden" name="currentPage" id="page.currentPage" value="${page.currentPage }" />
	<input type="hidden" name="ids" id="ids" />
		 <input type="hidden" name="sort" id="sort" value="${sort }">
					 <input type="hidden" name="sortseq" id="sortseq" value="${sortseq }">
              <table >
              <tr>
                <td height="39"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  </table></td>
                <td><label class="txt">项目名称：</label>
                  <input class="id" type="text" value="${keyword }" name="keyword"></td>
                  <td><input type="button" onclick="add()"  value="新增" class="qx_btn"/></td>
                <td width="215" align="right"></td>
                <td width="215"></td>
                    <td><input type="submit" value="查询" class="cx"/></td>
              </tr>
            </table>
            </form>
            </td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="10"></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e2e2e2" >
        <tr >
          <td width="3%" height="46" bgcolor="e5e6e6" class="STYLE10"><div align="center">
              序号
            </div></td>
          <td width="15%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">项目名称</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openDeposit()">目标金额</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openReward()">发起地</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openPunish()">项目说明</span></div></td>
         
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
          <td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
        </tr>
        
         <c:forEach items="${list }" var="item" varStatus="s">
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
             ${s.index+1 }
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${item.name }</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.goalMoney }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.addr }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">${item.nameDesc }</div></td>   
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd"/></div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="jsxg"style="color:#00ce9b;" onclick="changeStatus('${item.id}')">删除</a> <a onclick="javascript:window.open('${ctx}/manager/adminProjectAdd?id=${item.id}','rightFrame')"  class="jsxg"style="color:#00ce9b;">修改</a> <a  onclick="javascript:window.open('${ctx}/manager/${item.id}/projectInvestLists','rightFrame')" class="jsxg"style="color:#00ce9b;">支持设置</a> </div></td>
        </tr>
</c:forEach>
      </table></td>
  </tr>
  <tr>
    <td height="50" style="background:#fafafb; border:1px solid #e5e6e6;border-top:none; padding-left:20px;"><table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
          
          <td><%@include file="../../PaginationMore.inc"%></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="100%" style="line-height:48px;">
  <tr>
    <td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td>
  </tr>
</table>
</body>
</html>
