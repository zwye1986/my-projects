<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>充值管理</title>
<link href="${ctx }/css/manager/main.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<link href="${ctx }/css/jquery.msgbox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.msgbox.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" />
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
.cx{ width:59px; height:22px; background:url(${ctx}/images/admin/cx_btn.png) no-repeat; cursor:pointer; border:none; color:#fff;}

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
.selected td{ background:#e1f6f1;}

.date{height:22px; width:25%;}
.money{ height:22px;  width:15%;}
#btn {
	width: 86px;
	height: 31px;
	background: url(images/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}

-->
</style>

<script type="text/javascript">
$(document).ready(function(){
	 
	 $("#sb").click(function(){
		 $("input[name='currentPage']").val(1);
		 
		 $("#mainForm").attr("method","post").submit();
	 });
	 
	 var date1 = $("#actionStartTime").val();
	 var date2 = $("#actionEndTime").val();
	    
		$("#actionStartTime").datepicker({
			changeMonth : true,
			changeYear : true
		});
		$("#actionStartTime").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#actionEndTime").datepicker({
			changeMonth : true,
			changeYear : true
		});
		$("#actionEndTime").datepicker("option", "dateFormat", "yy-mm-dd");

		$("#actionStartTime").val(date1);
		$("#actionEndTime").val(date2);
		
	 $("#data tbody tr").hover(
			  function(){
			   $(this).addClass('selected');
			  },
			  function() 
			  {
			   $(this).removeClass('selected');
			  }
			 );
});


function onPageSizeChange(value)
{
	document.getElementById("page.pageSize").value=value;
	document.all.mainForm.submit();
}

//分页验证
function paginate(forward){
	if(forward)	{
		document.getElementById("page.currentPage").value=forward;
	}else{
		var temp=document.getElementById("forwardPage").value;		
		if(temp=="")
		{
			top.jQuery.alerts.alert('输入的页数为空。','提示');
			return false;
		}
    	var reg = /^[0-9]\d*$/;
    	if(!reg.test(temp)) {
    		top.jQuery.alerts.alert('输入的页数必须为数字。','提示');
    		return false;
    	} 
    	if( temp < 1 || temp > parseInt('${page.totalPages}'))
    	{
    		top.jQuery.alerts.alert('输入的页数超过了显示的范围。','提示');
    		return false;
    	}
		document.getElementById("page.currentPage").value=document.getElementById("forwardPage").value;
	}
	document.all.mainForm.submit();
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
	$("#mainForm").submit();
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
	
	$("#mainForm").submit();
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
	$("#mainForm").submit();
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
	$("#mainForm").submit();
}
		 
		</script>
		
</head>

<body style="padding:25px;">
<form action="${ctx }/manager/gameRecord"   method="get" target="_self"
		id="mainForm" name="mainForm">
	<input type="hidden" name="pageSize" id="page.pageSize" value="${page.pageSize }" />
	<input type="hidden" name="currentPage" id="page.currentPage" value="${page.currentPage }" />
	<input type="hidden" name="ids" id="ids" />
		 <input type="hidden" name="sort" id="sort" value="${sort }">
					 <input type="hidden" name="sortseq" id="sortseq" value="${sortseq }">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="62" bgcolor="#3b3a3f" style="border-bottom:1px solid  #d5d1c8;border-top:1px solid  #d5d1c8; padding:0 20px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="39"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  </table></td>
                <td></td>
                  <td><label class="txt">手机号码：</label>
                  <input class="phone" type="text" value="${userId }" name="userId" ></td>
                   <td><label class="txt">状态</label>
                 <select name="status" >
                  <c:choose>
             <c:when test="${status != 1 && status != 2 }">
             <option value="" selected="selected">全部</option>
             </c:when>
             <c:otherwise>
               <option value="" >全部</option>
             </c:otherwise>
             </c:choose>
            
             <c:choose>
             <c:when test="${status == 1 }">
             <option value="1" selected="selected">进行中</option>
             </c:when>
             <c:otherwise>
              <option value="1" >进行中</option>
             </c:otherwise>
             </c:choose>
             <c:choose>
             <c:when test="${status == 2 }">
             <option value="2" selected="selected">已完成</option>
             </c:when>
             <c:otherwise>
              <option value="2" >已完成</option>
             </c:otherwise>
             </c:choose>
            
            </select>
							</td>
                  
                <td width="300"><div align="center" style="height:32px; line-height:32px;"><span class="STYLE1"> <em>领取时间：</em>
                    <input  class="date" size="3" type="text"  name="actionStartTime"
								id="actionStartTime" value="${actionStartTime}" />－<input type="text" 
								 class="date" name="actionEndTime" id="actionEndTime" size="3"
								value="${actionEndTime}"  />
                    </span></div></td>
               
                    <td><input type="button" value="查询" class="cx" id="sb"/></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="10"></td>
  </tr>
  <tr>
    <td><table id="data" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e2e2e2" >
        <tr >
          <td width="3%" height="46" bgcolor="e5e6e6" class="STYLE10"><div align="center">
              序号
            </div></td>
          <td width="15%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">手机号码</span></div></td>
          <td width="6%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">游戏名称</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openDeposit()">游戏押金</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openReward()">游戏返利</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openPunish()">游戏罚金</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="javascript:openClicks();">游戏周期</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
         
        </tr>
         <tbody>
        <c:forEach items="${list }" var="item" varStatus="s">
        <tr>
          <td height="40" bgcolor="#FFFFFF"><div align="center">
             ${s.index+1 }
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${item.userId }</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.ecGame.gameName }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.deposit }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">${item.reward }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.punish }</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >${item.clicks }</div></td>
          
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div></td>
           
        </tr>
</c:forEach>
        </tbody>
<tr bgcolor="#FFFFFF">
	<td colspan="10" height="30"><%@include file="../../PaginationMore.inc"%></td>
</tr>
</table>
<table width="100%" style="line-height:48px;">
  <tr>
    <td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td>
  </tr>
</table>

</form>
</body>
</html>
