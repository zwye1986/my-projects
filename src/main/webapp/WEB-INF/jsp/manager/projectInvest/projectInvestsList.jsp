<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看项目投资设置</title>
<link href="${ctx }/css/manager/main.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<link href="${ctx }/css/jquery.msgbox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/css/zTreeStyle.css" type="text/css">
<script type="text/javascript"
	src="${ctx}/js/uuid.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-1.9.1.min.js"></script>
<!-- uploadify -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/upload/uploadify.css"></link>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.msgbox.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.excheck-3.5.js"></script>
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
.selected td{ background:#e1f6f1;}

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
.qx_btn {
	width: 55px;
	height: 34px;
	background: url(${ctx}/images/admin/qx_btn.png) no-repeat;
	color: #00ce9b;
	border: none;
	cursor: pointer;
	font-size: 16px;
}
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
.news_add_t {
	color: #3c3c3c;
	font-size: 18px;
}
.news_c{ width:100%;}
.news_c form{ width:800px; height:500px; }
.kv_item{ line-height:36px; padding:20px;}
.news_add_list{ line-height:36px; width:85px; text-align:right; float:left; margin-right:5px; color:#3c3c3c; font-size:16px;}
.news_txt{ width:235px; height:24px; line-height:36px; background:#fff; border:none; border:1px solid #cacaca; float:left;}
.txt_area{ width:700px; height:120px; background:#fff; border:1px solid #ccccca; margin-left:110px; margin-top:-30px; }
.news_checkbox{ float:left; margin-top:10px;}
.news_check{ float:left; color:#3b3a3f; float:left;}
.news_add_btn{ width:700px; height:24px; line-height:48px; margin-top:10px; float:left;}
.news_add_tj,.news_add_qx{ width:59px; height:22px; border:none; background:#4797f1; cursor:pointer; margin-right:5px; color:#fff;}
.news_add_tj:hover,.news_add_qx:hover{ width:59px; height:22px; background:#3b3a3f; }
.news_select{width:235px; height:24px; line-height:36px; background:#fff; border:none; border:1px solid #cacaca; float:left; }
-->
</style>
<script type="text/javascript"
	src="${ctx}/js/upload/jquery.uploadify.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	 $("#sb").click(function(){
		 $("input[name='currentPage']").val(1);
		 $("#mainForm").attr("method","post").submit();
	 });
	 
	 $("#data tbody tr").hover(
			  function(){
			   $(this).addClass('selected');
			  },
			  function() 
			  {
			   $(this).removeClass('selected');
			  }
			 );
	 
	 $("#add_resc").click(function(){
		 var projectId=$("#projectId").val();
		 $.showPanel("新增项目支持",
					"${ctx }/manager/projectInvest/view?projectId="+projectId,
					800, 540, true, "${ctx}");
	 });
	 
	 $("input[name='ids']").click(function(){
		 var checkedCount = jQuery("input[name='ids']:checked").length;
		 if(checkedCount == 1){
			 jQuery("#modifyresc").show();
		 }else{
			 jQuery("#modifyresc").hide();
		 }
	 });
	 
	 $("#delresc").click(function(){
			var str="";
	        $("input[name='ids']:checkbox").each(function(){ 
	            if($(this).attr("checked")){
	                str += $(this).val()+",";
	            }
	        });
	        
	        var resId=str.split(",")[0];
			if($.type(resId) === 'undefined' || $.type(resId) === 'null'||resId==''){
				$.showPanel("提示",
						"${ctx}/manager/delTips",
						300, 100, true, "${ctx}");
				return;
			}
			if(confirm("您确定要删除选中的纪录吗？")){
				for(var i=0;i<str.split(",").length;i++){
					deletem(str.split(",")[i]);
				}
			}
		});
		
		$("#modifyresc").click(function(){
			
			var str="";
	        $("input[name='ids']:checkbox").each(function(){ 
	            if($(this).attr("checked")){
	                str += $(this).val()+",";
	            }
	        });
	        
	        var resId=str.split(",")[0];
			if($.type(resId) === 'undefined' || $.type(resId) === 'null'||resId==''){
				$.showPanel("提示",
						"${ctx}/manager/delTips",
						300, 100, true, "${ctx}");
				return;
			}
			
			modifyDetail(resId);
			
		});
		
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
			 $.showPanel("新增项目支持",
						"${ctx }/manager/projectInvest/view?ids="+id,
						800, 650, true, "${ctx}");
			return;
	}
		
		function deletesingle(resId){
			var projectId=$("#projectId").val();
			if(confirm("您确定要删除选中的纪录吗？")){
				$.ajax({
					   type: "POST",
					   async: false,
					   cache: false,
					   dataType: "json",
					   data:{ids:resId},
					   url: "${ctx}/manager/projectInvest/delete"
					});
				 window.location.href="${ctx}/manager/"+projectId+"/projectInvestLists";
			}
		}
		
		
		function deletem(resId){
			var projectId=$("#projectId").val();
				$.ajax({
					   type: "POST",
					   async: false,
					   cache: false,
					   dataType: "json",
					   data:{ids:resId},
					   url: "${ctx}/manager/projectInvest/delete"
					});
				 window.location.href="${ctx}/manager/"+projectId+"/projectInvestLists";
		}
		 
		
		
		
		</script>
		
</head>

<body style="padding:25px;">
<form action="${ctx }/manager/projectInvestLists" method="post" target="_self"
		id="mainForm" name="mainForm">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
								<td height="62" bgcolor="#3b3a3f"
									style="border-bottom: 1px solid #d5d1c8; border-top: 1px solid #d5d1c8; padding: 0 20px;"><table
										width="100%" cellspacing="0" cellpadding="0" border="0">
										<tbody>
											<tr>
											<td>
	                                             <div align="left" style="height:54px; line-height:54px;">
	                                                    	<span class="STYLE1">
			            							<input id="add_resc" type="button" class="qx_btn" value="新增">&nbsp;&nbsp;&nbsp;&nbsp;
													<input id="modifyresc" type="button" class="qx_btn" value="修改" >&nbsp;&nbsp;&nbsp;&nbsp;
													<input id="delresc" type="button" class="qx_btn" value="删除">
															</span>
	                                                    </div>
	                                                </td>
												<td height="39">
												<table width="100%" cellspacing="0"
														cellpadding="0" border="0">
														</table>
												
											</tr>
										</tbody>
									</table></td>
							</tr>
							
      </table></td>
  </tr>
  <tr>
    <td height="10"></td>
  </tr>
  <tr>
    <td><table id="data" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#e2e2e2" >
        <tr ><input type="hidden" name="pageSize" id="page.pageSize"
					value="${page.pageSize }" /> <input type="hidden"
					name="currentPage" id="page.currentPage"
					value="${page.currentPage }" /> 
					<input type="hidden" id="projectId" name="projectId" value='<c:out value="${projectId}" />'/>
		  <td width="3%" height="46" bgcolor="e5e6e6" class="STYLE10"><div align="center"></div></td>
          <td width="3%" height="46" bgcolor="e5e6e6" class="STYLE10"><div align="center">序号</div></td>
          <td width="13%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">项目名称</span></div></td>
          <td width="13%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">支持套餐名称</span></div></td>
          <td width="6%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">支持金额</span></div></td>
          <td width="6%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">支持次数</span></div></td>
          <td width="8%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">已经支持次数</span></div></td>
          <td width="8%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">成功筹款收益</span></div></td>
          <td width="8%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">未成功筹款收益</span></div></td>
          <td width="18%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">内容描述</span></div></td>
          <td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
          
         <tbody>
        <c:forEach items="${projectInvests}" var="projectInvest" varStatus="s" begin="0">
        <tr>
        <td height="40" bgcolor="#FFFFFF" class="STYLE19">
          <input type="checkbox" name="ids" value="${projectInvest.id }" /></td>
          <td height="40" bgcolor="#FFFFFF" ><div align="center">
            ${(page.currentPage-1)*page.pageSize+(s.index+1) }
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"> ${projectName}</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${projectInvest.invest_name }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${projectInvest.amount }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">${projectInvest.support_num }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">${projectInvest.already_support_num }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">${projectInvest.benefit_amount}</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">${projectInvest.miss_benefit_amount}</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center">${projectInvest.content}</div></td>
			<td height="40" bgcolor="#FFFFFF" class="STYLE21">
			<div align="center" class="chao_z_btn">
			<input type="button" value="修改" onclick="modifyDetail('${projectInvest.id}')">
			<input type="button"  onclick="deletesingle('${projectInvest.id}')"  value="删除"> 
			</div></td>
			
        </tr>
        </c:forEach>
        </tbody>
<tr bgcolor="#FFFFFF">
	<td colspan="11" height="30"><%@include file="../../../PaginationMore.inc"%></td>
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
