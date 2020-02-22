<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>积分兑换管理</title>
<link href="${ctx }/css/manager/main.css" rel="stylesheet" />
<link href="${ctx }/css/ec_alerts.css" rel="stylesheet" />
<link href="${ctx }/css/jquery.msgbox.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery/jquery.msgbox.js"></script>
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
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
}

.STYLE22 {
	font-size: 12px;
	color: #295568;
}

#search {
	width: 280px;
	height: 31px;
	background: url(${ctx}/images/manager/images/search.png) no-repeat;
	border: none;
}

#btn {
	width: 86px;
	height: 31px;
	background: url(${ctx}/images/manager/images/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}

.selectItemcont {
	padding: 8px;
}

#selectItem {
	background: #FFF;
	position: absolute;
	top: 0px;
	left: center;
	border: 1px solid #000;
	overflow: hidden;
	width: 240px;
	z-index: 1000;
}

#selectItem2 {
	background: #FFF;
	position: absolute;
	top: 0px;
	left: center;
	border: 1px solid #000;
	overflow: hidden;
	width: 240px;
	z-index: 1000;
}

.selectItemtit {
	line-height: 20px;
	height: 20px;
	margin: 1px;
	padding-left: 12px;
}

.bgc_ccc {
	background: #929B9E;
}

.selectItemleft {
	float: left;
	margin: 0px;
	padding: 0px;
	font-size: 12px;
	font-weight: bold;
	color: #fff;
}

.selectItemright {
	float: right;
	cursor: pointer;
	font-size: 12px;
	font-weight: bold;
	color: #fff;
}

.selectItemcls {
	clear: both;
	font-size: 0px;
	height: 0px;
	overflow: hidden;
}

.selectItemhidden {
	display: none;
}

.chao_z_btn input {
	background: #4797f1;
	color: #fff;
	width: 59px;
	height: 22px;
	border: none;
	margin-right: 5px;
	cursor: pointer;
}

.chao_z_btn input:hover {
	background: #3b3a3f;
}
-->
.selected td{ background:#e1f6f1;}

.info {position:relative;background:#fff;color:#666; text-decoration:none;font-size:14px;width:250px;text-align:center; #ccc;height:25px;line-height:25px;}
.info:hover {background:#eee;color:#333;}
.info span {display: none }
.info:hover span {display:inline;position:absolute;top:30px;right:250px;width:130px;border:1px solid #ff0000; background:#fff; color:#000;padding:5px;text-align:left;}
</style>

<script type="text/javascript">

$(function(){
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
	document.mainForm.submit();
}





function modifyDetail(id) {
	 $.showPanel("回复用户反馈",
				"${ctx }/manager/"+id+"/feedbackView",
				600, 450, true, "${ctx}");
	return;
}

	

		 
		</script>
		
</head>

<body style="padding:25px;">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="10"></td>
  </tr>
  <tr>
    <td><table id="data" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e2e2e2" >
        <tr >
        <form action="${ctx }/manager/feedBackList" method="get" target="_self"
					id="mainForm" name="mainForm">
					
        <input type="hidden" name="pageSize" id="page.pageSize"
					value="${page.pageSize }" /> <input type="hidden"
					name="currentPage" id="page.currentPage"
					value="${page.currentPage }" />
          <td width="5%" height="46" bgcolor="e5e6e6" class="STYLE10"><div align="center">序号</div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">姓名</span></div></td>
          <td width="6%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">联系方式</span></div></td>
          <td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">反馈内容</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">反馈时间</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">回复时间</span></div></td>
          <td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">回复内容</span></div></td>
          <td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">回复</span></div></td>
        </tr>
         <tbody>
        <c:forEach items="${feedBackList }" var="item" varStatus="s" begin="0">
        <tr id="tr" >
          <td height="40" bgcolor="#FFFFFF" ><div align="center">
            ${(page.currentPage-1)*page.pageSize+(s.index+1) }
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.name}</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.contact}</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19">${fn:substring(item.advice,0,16)}</td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" ><fmt:formatDate value="${item.replytime}" pattern="yyyy-MM-dd HH:mm:ss"/> </div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19">${fn:substring(item.replyContent,0,16)} </td>
            
             <td height="40" bgcolor="#FFFFFF" class="STYLE19">
            <div align="center" class="chao_z_btn">
                  <c:if test="${item.replayStatus eq 0}">
				    <input type="button" onclick='modifyDetail("${item.id}")'  value="待回复" >
			     </c:if> 
			     <c:if test="${item.replayStatus eq 1}">
			        <input type="button"  onclick='modifyDetail("${item.id}")' value="已回复" >
			     </c:if>
			     
			</div></td>
			
        </tr>
        </c:forEach>
        </tbody>
<tr bgcolor="#FFFFFF">
	<td colspan="10" height="30"><%@include file="../../PaginationMore.inc"%></td>
</tr>
</form>
</table>
<table width="100%" style="line-height:48px;">
  <tr>
    <td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td>
  </tr>
</table>



</body>
<div id="selectItem" class="selectItemhidden" style="width:300px">

	<div id="selectItemAd" class="selectItemtit bgc_ccc">
		<h2 id="selectItemTitle" class="selectItemleft">请填写回复内容</h2>
		<div id="selectItemClose" class="selectItemright">关闭</div>
	</div>
	<div id="selectItemCount" class="selectItemcont">
		<div id="selectSub">
		<input type="hidden" id="replyid"/>
			<p><label>姓&nbsp;&nbsp;&nbsp;&nbsp;名</label>:<input type="text" readonly="readonly" id="replyname"/></p>
			 <p><label>手机号码</label>:<input type="text" readonly="readonly" id="replycontact"/></p>
			 <p><lable>回复内容</lable>:<textarea id ="replycontext" name="cr01" rows="7" cols="20"></textarea></p>
		<input type="button" value="回复" onclick="submit()"/>
	    </div>
	</div>
</div>


</html>
