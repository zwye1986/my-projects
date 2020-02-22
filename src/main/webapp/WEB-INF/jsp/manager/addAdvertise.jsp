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
$(document) .ready( 
		function() {
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
   if($("#advertiseId").val().length > 0){
	   $("#uuid").remove();
	   $("#addAdvertiseForm").attr("action",  "${pageContext.request.contextPath}/manager/dealUpdateAdvertise");
   }else{
	   $("#advertiseId").remove();
	   $("#addAdvertiseForm").attr("action",  "${pageContext.request.contextPath}/manager/saveAdvertising");
   }
	$("#addAdvertiseSubmitButton").click();
}

</script>


</head>
<body>

<table style="padding:25px;" align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr>
    <td height="30"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
        <tr>
          <td style="border-bottom:2px solid  #d2d2d2;padding:0 20px;" height="62"><table border="0" cellpadding="0" cellspacing="0" width="100%">
              <tbody><tr>
                <td height="39"><table border="0" cellpadding="0" cellspacing="0" width="100%">
                  </table></td>
                <td class="news_add_t">添加广告位</td>
              </tr>
            </tbody></table></td>
        </tr>
      </tbody></table></td>
  </tr>
  <tr>
    <td height="10"></td>
  </tr>

        <tr>
          <td height="500"><div class="news_c" align="center">
         <form name="addAdvertise" id="addAdvertiseForm" method="post" action="${pageContext.request.contextPath}/manager/saveAdvertising">
			<input type="hidden" id="advertiseId" name="id" value="${advertising.id }">
          <div class="kv_item"><label class="news_add_list">广告名称:</label><input class="news_txt" type="text" name="name" id="name" value="${advertising.name }"/></div>
          <div class="kv_item"><label class="news_add_list">媒介类型:</label>
          	<select name="type" style="float: left; height:24px; line-height:24px;margin-top: 5px; ">
          		<option value="1" <c:if test="${advertising.type eq 1 }">selected="selected"</c:if> >网页</option>
          		<option value="2" <c:if test="${advertising.type eq 2 }">selected="selected"</c:if>>android客户端</option>
          		<option value="3" <c:if test="${advertising.type eq 3 }">selected="selected"</c:if>>ios客户端</option>
          	</select>
          </div>
           <div class="kv_item">
               <label class="news_add_list">广告位置:</label>
               <select name="location" style="float: left; height:24px; line-height:24px;margin-top: 5px; ">
                   <c:forEach items="${advertisePositions}" var="item">
                       <option value="${item.id}" <c:if test="${advertising.location eq item.id}">selected="selected" </c:if> >${item.adv_position}</option>
                   </c:forEach>
               </select>
           </div>
             <div class="kv_item"><label class="news_add_list">广告序号:</label><input class="news_txt" type="text" name="index" id="index" value="${advertising.index == null ? 0 :  advertising.index}"/></div>
          <div class="kv_item"><label class="news_add_list">广告图片宽:</label><input class="news_txt" type="text" name="width" id="width" value="${advertising.width }" /></div>
          <div class="kv_item"><label class="news_add_list">广告图片高:</label><input class="news_txt" type="text" name="height" id="height" value="${advertising.height }" /></div>
          <div class="kv_item"><label class="news_add_list">广告链接:</label><input class="news_txt" type="text" name="href" id="href"  value="${advertising.href }"/></div>
          
          <div class="kv_item"><label class="news_add_list">备注:</label></div>
          <div><textarea class="txt_area"  name="remark" >${advertising.remark }</textarea></div>
          <div class="kv_item" >
          	<label class="news_add_list">广告图片:</label>
          	<input  type="file" name="uploadify" id="file_upload" />
			<input id='sessionUID' value='<%=session.getId() %>' type="hidden"/>
			<input id="uuid" name="id"  type="hidden"/>
          </div>
           <div class="kv_item" style="float:left; margin-left:90px;" id="picList"></div>
          <div class="news_add_btn">
          <!-- 隐藏提交按钮 -->
					<input type="submit" id="addAdvertiseSubmitButton"
						style="display: none" />
          	<input class="news_add_tj" value="::提交::" onclick="dealAdd();" type="button" />
          	<input class="news_add_qx" value="取消" type="reset"  />
          </div>
          </form>
           </div></td>
           
        </tr>
      </tbody></table>
<table style="line-height:48px;" width="100%">
  <tbody><tr>
    <td style="color:#666;" align="center">苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td>
  </tr>
</tbody></table>



</body>
</html>