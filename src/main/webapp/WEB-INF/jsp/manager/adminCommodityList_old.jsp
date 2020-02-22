<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>wan商城</title>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript">
function onPageSizeChange(value)
{
	document.getElementById("page.pageSize").value=value;
	document.all.queryListForm.submit();
}

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


$(function(){
	$("#checkbox").click(function(){
		
		 if($("#checkbox").attr("checked")=="checked"){
			 $("input[name=id]").each(function(){
	
					 $(this).attr("checked","checked");
				
			 });
		 }else{
			 $("input[name=id]").each(function(){
				 $(this).attr("checked",false);
			
		 });
		 }
	});
	
	
	
	$("#add").click(function(){
		add();
	});
	
	$("#add2").click(function(){
		add();
	});
	
	
	
	function add(){
		window.location.href="adminCommodityAdd";
	}
	

		
});


function changeStatus(a){
	if(confirm("确定删除所选记录吗？")){
		$("#ids").val(a);
		$("#queryListForm").attr("action","${ctx}/manager/adminCommodityDel");
		$("#queryListForm").submit();
	}else{
		return false;
	}

}


</script>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #000;
	font-size: 16px;
	
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
#search{ width:280px; height:31px; background:url(${ctx}/images/manager/images/search.png) no-repeat; border:none; }
#btn{ width:86px;height:31px; background:url(${ctx}/images/manager/images/search_btn.png) no-repeat; border:none; cursor:pointer; margin-left:5px;}
-->

</style>
</head>
<body >
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">

<tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      

  
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="70" bgcolor="#f1f1f1" style="border-bottom:1px solid  #cbcbcb;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
           
            <td><div align="right" style="height:54px; line-height:54px;"><span class="STYLE1">
             <a id="add" style="float:left; margin-left:15px;cursor:pointer;"><img src="${ctx}/images/manager/images/xz.png" width="54" height="54" /></a> <em id="add2" style="float:left;cursor:pointer;"> 新增</em></span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
 <form id="queryListForm" name="queryListForm" target="_self" action="${ctx }/manager/commodity" method="post">
	<input type="hidden" name="pageSize" id="page.pageSize" value="${page.pageSize }" />
	<input type="hidden" name="currentPage" id="page.currentPage" value="${page.currentPage }" />
	<input type="hidden" name="ids" id="ids" />
	
  <tr>
        <td height="42" bgcolor="#e2dfd8" style="border-bottom:1px solid  #d5d1c8;border-top:1px solid  #d5d1c8;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="39"><table width="100%" border="0" cellspacing="0" cellpadding="0">
             
            </table></td>
            <td><div align="center" style="height:32px; line-height:32px;"><span class="STYLE1">
             <em>商品类别：</em><select name="category" >
             <option value="" selected="selected">全部</option>
<c:forEach items="${typeList }" var="item">
<c:choose>
<c:when test="${category == item.id  }">
<option value="${item.id }" selected="selected">${item.name }</option>
</c:when>
<c:otherwise>
<option value="${item.id }">${item.name }</option>
</c:otherwise>
</c:choose>

</c:forEach>
</select>
            <em>商品名称：</em><input type="text" name="keyword" value="${keyword }" id="search" />
            <em>积分：</em><input type="text" name="minIntegral" style="width: 100"  value="${keyword }" id="search" />--<input type="text" style="width: 100" name="maxIntegral" value="${keyword }" id="search" /><input type="submit" value="" name="text" id="btn" />
            
            </span></div></td>
          </tr>
        </table></td>
      </tr>
 </form>
    </table></td>
  </tr>


  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" >
      <tr>
        <td width="4%" height="40" bgcolor="e2dfd8" class="STYLE10"><div align="center">
          <input type="checkbox" name="checkbox" id="checkbox" />
        </div></td>
        <td width="10%" height="40" bgcolor="e2dfd8" class="STYLE6"><div align="center"><span class="STYLE10">商品名称</span></div></td>
       <td width="10%" height="40" bgcolor="e2dfd8" class="STYLE6"><div align="center"><span class="STYLE10">商品价格</span></div></td>
        <td width="10%" height="40" bgcolor="e2dfd8" class="STYLE6"><div align="center"><span class="STYLE10">商品类别</span></div></td>
<td width="10%" height="40" bgcolor="e2dfd8" class="STYLE6"><div align="center"><span class="STYLE10">兑换积分</span></div></td>
        <td width="14%" height="40" bgcolor="e2dfd8" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <c:forEach items="${list }" var="item">
       <tr>
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="id" id="checkbox2"  value="${item.id }"/>
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${item.name }</span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${item.price }</span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${item.categoryName }</span></div></td>
 <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${item.integral }</span></div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center"   class="STYLE21"><a style="cursor: pointer;" onclick="changeStatus('${item.id}')">删除</a> | <a style="cursor: pointer;" href="${ctx}/manager/adminCommodityAdd?id=${item.id}"> 修改</a>  </div></td>
      </tr>
      </c:forEach>
     
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="25%">
         <%@include file="../../PaginationMore.inc"%>
        </td>
      </tr>
    
    </table></td>
  </tr>
     <tr><td style="text-align:right; color:#666;">苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td></tr>
</table>

</body>
</html>
