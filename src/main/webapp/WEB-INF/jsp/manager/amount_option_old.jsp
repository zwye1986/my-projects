<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>报表管理</title>
	    <link href="${ctx }/css/ec_alerts.css" rel="stylesheet" />
	    <script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
	    <script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
		<style type="text/css">
			body {
				margin-left: 3px;
				margin-top: 0px;
				margin-right: 3px;
				margin-bottom: 0px;
				font-size: 12px;
			}
		</style>
		<script type="text/javascript">
		 var imgPath_session = '${ctx}' + '/images/alerts';
		 
		 $(document).ready(function(){
			 $("#checkAll").click(function(){
				 if(jQuery(this).attr("checked"))
				 {
				 	jQuery("input[name='id']").attr("checked", true);
				 }else{
					 jQuery("input[name='id']").attr("checked", false);
				 }
				 jQuery("#updateBtn").hide();
			 });
			 
			 $("input[name='ids']").click(function(){
				 var checkedCount = jQuery("input[name='ids']:checked").length;
				 if(checkedCount == 1){
					 jQuery("#updateBtn").show();
				 }else{
					 jQuery("#updateBtn").hide();
				 }
			 });
			 
			 $("#deleteBtn").click(function(){
				 var checkedCount = jQuery("input[name='ids']:checked").length;
				 if(checkedCount == 0){
					 $.alerts.alert("请至少选中一条记录!");
					 return ;
				 }
				 $("#mainForm").attr("action","${ctx }/manager/amount/option/delete").submit();
			 });
			 
			 $("#addBtn").click(function(){
				 $("#mainForm").attr("action","${ctx }/manager/amount/option/save").attr("method","get").submit();
			 });
			 
			 $("#updateBtn").click(function(){
				 var checkedCount = jQuery("input[name='ids']:checked").length;
				 if(checkedCount == 0){
					 $.alerts.alert("请至少选中一条记录!");
					 return ;
				 }
				 $("#mainForm").attr("action","${ctx }/manager/amount/option/save").attr("method","get").submit();
			 });
		 });
		</script>
	</head>

	<body>
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	            <td height="30">
	                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                    <tr>
	                        <td height="30">
	                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                                <tr>
	                                    <td height="70" bgcolor="#f1f1f1" style="border-bottom:1px solid  #cbcbcb;">
	                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                                            <tr>
	                                                <td>
	                                                    <div style="height:54px; line-height:54px;">
	                                                    	<span style="font-size: 16px;">
			            										 <a style="float:left; margin-left:15px;cursor:pointer;" id="addBtn">
			            										 	<img src="${ctx}/images/manager/images/xz.png" width="54" height="54"  />
																	<em style="float:left;cursor:pointer;"> 新增</em>   &nbsp; 
			            										 </a>
																 <a style="float:left;margin-left:15px;cursor:pointer;" id="updateBtn"> 
																 	<img src="${ctx}/images/manager/images/xg.png" width="54" height="54" />
																	<em style="float:left;cursor:pointer;">修改 </em> &nbsp;&nbsp;  
																 </a>
															 	 <a style="float:left;margin-left:15px;cursor:pointer;" id="deleteBtn">
																	<img src="${ctx}/images/manager/images/sc.png" width="54" height="54" />
																 	<em style="float:left; cursor:pointer;"> 删除 </em>  &nbsp; 
																 </a>
															</span>
	                                                    </div>
	                                                </td>
	                                            </tr>
	                                        </table>
	                                    </td>
	                                </tr>
	                            </table>
	                        </td>
	                    </tr>
	                </table>
	            </td>
	        </tr>
	        <tr>
	            <td>
               		<form action="${ctx }/manager/system/config/delete" method="post" target="_self" id="mainForm">
	                <table width="100%" border="0" cellspacing="1" bgcolor="#e2e2e2" style="text-align: center;" >
                	   <tr bgcolor="e2dfd8">
	                        <td width="30" height="46" style="border-right: 1px solid #e2e2e2;" >
	                            <input type="checkbox" id="checkAll" />
	                        </td>
	                        <td width="*" height="40" >
	                            金额
	                        </td>
                 	   </tr>
                    	<c:forEach var="option" items="${options }">
	                    <tr bgcolor="#FFFFFF" >
                    		<td height="30">
	                            <input type="checkbox" name="ids" value="${option.id }" />
	                        </td>
	                        <td>
	                            ${option.optionValue }
	                        </td>
	                    </tr>
                    	</c:forEach>
	                </table>
	                </form>
	            </td>
	        </tr>
	    </table>
	</body>
</html>