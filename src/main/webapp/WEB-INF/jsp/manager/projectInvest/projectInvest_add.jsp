<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    <c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加广告</title>
<script type="text/javascript"
	src="${ctx}/js/uuid.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-1.9.1.min.js"></script>
<!-- uploadify -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/upload/uploadify.css"></link>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
	background-color: #f1f1f2;
	color: #5c5c5c;
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
	position: relative;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.id, .phone, .user {
	width: 45%;
	height: 22px;
}
.date {
	height: 22px;
	width: 15%;
}
.money {
	height: 22px;
	width: 15%;
}
#btn {
	width: 86px;
	height: 31px;
	background: url(images/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}
.cx {
	width: 59px;
	height: 22px;
	background: url(images/cx_btn.png) no-repeat;
	cursor: pointer;
	border: none;
	color: #fff;
}
a.jsxg {
	width: 15px;
	text-align: center;
	cursor: pointer;
	color: #0cfec2;
	margin-left: 5px;
}
.selected td {
	background: #e1f6f1;
}
.tck {
	width: 363px;
	height: 268px;
	position: absolute;
	background: #fff;
	right: 180px;
	border: 1px solid #cacaca;
	z-index: 2;
	top: 265px;
}
.tck .tck_t {
	width: 363px;
	height: 40px;
	background: #3b3a3f;
	margin-top: 0px;
	float: left;
}
.tck_xx {
	width: 120px;
	color: #fff;
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	float: left;
}
.del {
	float: right;
	height: 40px;
	width: 40px;
	background: url(images/del.png);
	cursor: pointer;
}
.tck form {
	width: 343px;
	text-align: left;
	color: #3b3a3f;
	font-size: 12px;
}
.tck_list {
	width: 343px;
	line-height: 18px;
}
.tck_la {
	width: 80px;
	text-align: right;
	float: left;
	height: 18px;
	line-height: 18px;
	margin-right: 5px;
}
.qx_btn {
	width: 55px;
	height: 34px;
	background: url(images/qx_btn.png) no-repeat;
	color: #00ce9b;
	border: none;
	cursor: pointer;
	font-size: 16px;
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
.news_c form{ width:800px; height:480px; }
.kv_item{ line-height:33px; padding:20px;}
.news_add_list{ line-height:36px; width:185px; text-align:right; float:left; margin-right:5px; color:#3c3c3c; font-size:16px;}
.news_txt{ width:235px; height:24px; line-height:36px; background:#fff; border:none; border:1px solid #cacaca; float:left;}
.txt_area{ width:500px; height:80px; background:#fff; border:1px solid #ccccca; margin-left:-80px; margin-top:-30px; }
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
<link href="${ctx }/css/ec_alerts_new.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>	
<script type="text/javascript">
$(document) .ready( 
		function() {
			var project_id=$("#project_id").val();
			$.ajax({
				type : "POST",
				async : false,
				cache : false,
				dataType : "json",
				data : {
					project_id:project_id
				},
				url : "${ctx}/manager/projectInvest/getSumProjectInvest",
				success: function(result){
					if(result.code=='0'){
						$("#remainamount").val(result.amount);
					}else{
					  alert(result.resultMsg);
					  return false;
					}
					}
				});
			
			
			$("#support_num").blur(function() {
				var reg1 = /^\d+$/;
				var num = $("#support_num").val();
				if (num.match(reg1) == null) {
					alert("数据输入有误！");
					$("#support_num").val(0);
					return false;
				}else{
					$.ajax({
						type : "POST",
						async : false,
						cache : false,
						dataType : "json",
						data : {
							project_id:project_id
						},
						url : "${ctx}/manager/projectInvest/getSumProjectInvest",
						success: function(result){
							if(result.code=='0'){
								$("#remainamount").val(result.amount);
							}else{
							  alert(result.resultMsg);
							  return false;
							}
							}
						});
					var remainamount=$("#remainamount").val();
					var amount=$("#amount").val();
					if((amount*num)>new Number(remainamount)){
						alert("你已经超过最大可支持金额!");
						$("#support_num").val(0);
					}else{
						$("#remainamount").val(remainamount-amount*num);
					}
				};
			});
			
			
			
			$("#amount").blur(function() {
			  var re=/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
			  var amount=$("#amount").val();
			  var project_id=$("#project_id").val();
			  if(amount!=""){
				  if(!re.test(amount)){
					  alert("数据输入有误！");
					  $("#amount").val(0);
					  return false;
				  }else{
						$.ajax({
							type : "POST",
							async : false,
							cache : false,
							dataType : "json",
							data : {
								amount:amount,
								project_id:project_id
							},
							url : "${ctx}/manager/projectInvest/getProjcetAmount",
							success: function(result){
								if(result.code=='0'){
									$("#benefit_amount").val(result.benefit_amount);
									$("#miss_benefit_amount").val(result.miss_benefit_amount);
								}else{
								  alert(result.resultMsg);
								  return false;
								}
								}
							});
						
						
						$.ajax({
							type : "POST",
							async : false,
							cache : false,
							dataType : "json",
							data : {
								project_id:project_id
							},
							url : "${ctx}/manager/projectInvest/getSumProjectInvest",
							success: function(result){
								if(result.code=='0'){
									$("#remainamount").val(result.amount);
								}else{
								  alert(result.resultMsg);
								  return false;
								}
								}
							});
						
						var remainamount=$("#remainamount").val();
						var support_num=$("#support_num").val();
						if((amount*support_num)>new Number(remainamount)){
							alert("你已经超过最大可支持金额!");
							$("#amount").val(0);
						}else{
							$("#remainamount").val(remainamount-amount*support_num);
						}
						
					  
				  };
				  
			  }
			});
			
			$("#benefit_amount").blur(function() {
				  var re=/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
				  var amount=$("#benefit_amount").val();
				  if(amount!=""){
					  if(!re.test(amount)){
						  alert("数据输入有误！");
						  $("#benefit_amount").val(0);
						  return false;
					  }
				  }
				});
			$("#miss_benefit_amount").blur(function() {
				  var re=/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
				  var amount=$("#miss_benefit_amount").val();
				  if(amount!=""){
					  if(!re.test(amount)){
						  alert("数据输入有误！");
						  $("#miss_benefit_amount").val(0);
						  return false;
					  }
				  }
				});
				
			
			    var advertiseId = Math.uuidFast();
			    if($("#advertiseId").val().length > 0){
			    	advertiseId = $("#advertiseId").val();
			    }
			    $("#uuid").val(advertiseId);
			    $("#file_upload").uploadify({  
			        'height'        : 27,   
			        'width'         : 80,    
			        'buttonText'    : '添加附件',  
			        'swf'           : '${ctx}/js/upload/uploadify.swf?ver=' + Math.random(),  
			        'uploader'      : '${ctx}/manager/dealUpload;jsessionid='+$("#sessionUID").val()+'?advertiseId='+advertiseId,  
			        'auto'          : true, 
			        'fileSizeLimit' : '30720KB', 
			        'fileTypeExts'  : ' *.jpg; *.png;*.gif', 
			        'onUploadSuccess':function(file, data, response){  
			         	$("#picList").html($("#picList").html()+" <img src='${ctx}/manager/showImag?id="+data+"' width='200' height='80' /> "); 
			        } 
			    });  
		});
		
	

function dealAdd() {
	var projectId=$("#project_id").val();
   if($("#advertiseId").val().length > 0){
	   $("#uuid").remove();
	   $("#addProjectInvestForm").attr("action",  "${pageContext.request.contextPath}/manager/projectInvest/update");
   }else{
	   $("#advertiseId").remove();
	   $("#addProjectInvestForm").attr("action",  "${pageContext.request.contextPath}/manager/projectInvest/save");
   }
	window.parent.location.href = "${ctx}/manager/"+projectId+"/projectInvestLists";
    window.parent.$.closePanel();
	$("#SubmitButton").click();
}

</script>


</head>
<body>

<table  align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
        <tr>
          <td height="480"><div class="news_c" align="center">
         <form name="addProjectInvest" id="addProjectInvestForm" method="post">
			<input type="hidden" id="advertiseId" name="id" value="${projectInvest.id }">
   	     <div class="kv_item"><label class="news_add_list">项目ID:</label>
   	     <c:choose >
   	         <c:when test="${not empty projectInvest.project_id}">
   	          <input readonly="readonly" class="news_txt" type="text" name="project_id" id="project_id" value="${projectInvest.project_id }" /></div>
   	          </c:when>
   	          <c:otherwise>
   	           <input readonly="readonly" class="news_txt" type="text" name="project_id" id="project_id" value="${projectId}" /></div>
   	          </c:otherwise>
   	     </c:choose>
   	       <div class="kv_item"><label class="news_add_list">项目名称:</label>
   	      <input readonly="readonly" class="news_txt" type="text"  value="${projectName}" /></div>
   	      <div class="kv_item"><label class="news_add_list">项目支持套餐名称:</label><input class="news_txt" type="text" name="invest_name" id="invest_name"  value="${projectInvest.invest_name }"/></div>
          <div class="kv_item"><label class="news_add_list">支持金额:</label><input class="news_txt" type="text" name="amount" id="amount" value="${projectInvest.amount }" /></div> 
          <div class="kv_item"><label class="news_add_list">剩余可支持金额:</label><input  class="news_txt" readonly="readonly" id="remainamount"  /></div> 
          <div class="kv_item"><label class="news_add_list">支持次数:</label><input class="news_txt" type="text" name="support_num" id="support_num"  value="${projectInvest.support_num }"/></div>
          <div class="kv_item"><label class="news_add_list">筹款成功预计收益:</label><input class="news_txt" type="text" name="benefit_amount" id="benefit_amount"  value="${projectInvest.benefit_amount}"/></div>
          <div class="kv_item"><label class="news_add_list">筹款未成功预计收益:</label><input class="news_txt" type="text" name="miss_benefit_amount" id="miss_benefit_amount"  value="${projectInvest.miss_benefit_amount}"/></div>
          <div class="kv_item"><label class="news_add_list">支持描述:</label></div>
          <div><textarea class="txt_area"  id="content" name="content" >${projectInvest.content }</textarea></div>
          <div class="kv_item" >
          	<label class="news_add_list">图片:</label>
          	<input  type="file" name="uploadify" id="file_upload" />
			<input id='sessionUID' value='<%=session.getId() %>' type="hidden"/>
			<input id="uuid" name="id"  type="hidden"/>
          </div>
         
          <div class="news_add_btn">
          <!-- 隐藏提交按钮 -->
					<input type="submit" id="SubmitButton"
						style="display: none" />
          	<input class="news_add_tj" value="::提交::" onclick="dealAdd();" type="button" />
          </div>
          </form>
           </div></td>
           
        </tr>
      </tbody></table>




</body>
</html>