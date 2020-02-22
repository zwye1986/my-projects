<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>

<!-- uploadify -->
<link rel="stylesheet" type="text/css" href="${ctx }/js/uploadify/uploadify.css">
<script type="text/javascript" src="${ctx }/js/uploadify/jquery.uploadify.min.js"></script>

<script type="text/javascript" src="${ctx }/js/uploadify/json2.js"></script>

<!-- 上传图片开始 -->
<script type="text/javascript">
var idName="";
$(document)
.ready(
		function() {
			    $("#file_upload_square").uploadify({  
			        'height'        : 27,   
			        'width'         : 80,    
			        'buttonText'    : '添加附件',  
			        'swf'           : '${pageContext.request.contextPath}/js/uploadify/uploadify.swf?ver=' + Math.random(),  
			        'uploader'      : '${pageContext.request.contextPath}/upload.do;jsessionid='+$("#sessionUID").val()+'?gameId=${game.id}&type=1',  
			        'auto'          : false, 
			        'fileSizeLimit' : '30720KB', 
			        'fileTypeExts'  : ' *.jpg;*.jpeg; *.png;*.gif', 
			        'cancelImg' 	: '${pageContext.request.contextPath}/js/uploadify/uploadify-cancel.png',
			      //  'uploadLimit' : 3, 
			       // 'formData'      : {'userName':'','content':''},  
			        'onUploadStart' : function(file) {
			        	
			        },  
			        'onUploadSuccess':function(file, data, response){  
			             $("#tempFileName").val(file.name);
			             
			             if($("#"+idName).val().length==0){
			            	 $("#"+idName).val( data);
			             }else{
			             $("#"+idName).val( $("#"+idName).val()+","+data);
			             }
			          
			             if(idName == 'square'){
				        	 $("#picList_"+idName).html($("#picList_"+idName).html()+" <img src='${pageContext.request.contextPath}/showSquareGamePic?id="+data+"' width='80' height='80' /> ");  
				         }else{
				        	 $("#picList_"+idName).html($("#picList_"+idName).html()+" <img src='${pageContext.request.contextPath}/showRectangleGamePic?id="+data+"' width='80' height='80' /> "); 
				         }
			            
			        },  
			        'onUploadComplete':function(){  
			        	
			           // $('#importLispDialog').window('close');  
			        }  
			    });  
			    
			    
			    $("#file_upload_rectangle").uploadify({  
			        'height'        : 27,   
			        'width'         : 80,    
			        'buttonText'    : '添加附件',  
			        'swf'           : '${pageContext.request.contextPath}/js/uploadify/uploadify.swf?ver=' + Math.random(),  
			        'uploader'      : '${pageContext.request.contextPath}/upload.do;jsessionid='+$("#sessionUID").val()+'?gameId=${game.id}&type=2',  
			        'auto'          : false, 
			        'fileSizeLimit' : '30720KB', 
			        'fileTypeExts'  : ' *.jpg;*.jpeg; *.png;*.gif', 
			        'cancelImg' 	: '${pageContext.request.contextPath}/js/uploadify/uploadify-cancel.png',
			      //  'uploadLimit' : 3, 
			       // 'formData'      : {'userName':'','content':''},  
			        'onUploadStart' : function(file) {
			        	
			        },  
			        'onUploadSuccess':function(file, data, response){  
			             $("#tempFileName").val(file.name);
			             
			             if($("#"+idName).val().length==0){
			            	 $("#"+idName).val( data);
			             }else{
			             $("#"+idName).val( $("#"+idName).val()+","+data);
			             }
			          
			             if(idName == 'square'){
				        	 $("#picList_"+idName).html($("#picList_"+idName).html()+" <img src='${pageContext.request.contextPath}/showSquareGamePic?id="+data+"' width='80' height='80' /> ");  
				         }else{
				        	 $("#picList_"+idName).html($("#picList_"+idName).html()+" <img src='${pageContext.request.contextPath}/showRectangleGamePic?id="+data+"' width='80' height='80' /> "); 
				         }
			       
			            
			        },  
			        'onUploadComplete':function(){  
			        	
			           // $('#importLispDialog').window('close');  
			        }  
			    });  
			    
			    

			    
			    
			    
		});
		
function startUpload(name){  
    idName=name;   
    $("#picList_"+name).empty();
 $('#file_upload_'+name).uploadify('upload','*');  
}
</script>
<!-- 上传图片结束 -->

<script type="text/javascript">
$(function(){
	$("#tj").click(function(){
		$("#gameForm").attr("action","${ctx}/manager/adminGameDealAdd");
		$("#gameForm").submit();
	});
});
</script>
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
	background: url(${ctx}/images/admin/search_btn.png) no-repeat;
	border: none;
	cursor: pointer;
	margin-left: 5px;
}
.cx {
	width: 59px;
	height: 22px;
	background: url(${ctx}/images/admin/cx_btn.png) no-repeat;
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
	background: url(${ctx}/images/admin/del.png);
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
	background: url(${ctx}/images/admin/qx_btn.png) no-repeat;
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
.news_c form{ width:800px; height:500px;  }
.kv_item{ line-height:36px; padding:20px;}
.news_add_list{ line-height:36px; width:85px; text-align:right; float:left; margin-right:5px; color:#3c3c3c; font-size:16px;}
.news_txt{ width:235px; height:24px; line-height:36px; background:#fff; border:none; border:1px solid #cacaca; float:left;}
.txt_area{ width:700px; height:250px; background:#fff; border:1px solid #ccccca; margin-left:110px; margin-top:-30px; }
.news_checkbox{ float:left; margin-top:10px;}
.news_check{ float:left; color:#3b3a3f; float:left;}
.news_add_btn{ width:700px; height:24px; line-height:48px; margin-top:10px; float:left;}
.news_add_tj,.news_add_qx{ width:59px; height:22px; border:none; background:#4797f1; cursor:pointer; margin-right:5px; color:#fff;}
.news_add_tj:hover,.news_add_qx:hover{ width:59px; height:22px; background:#3b3a3f; }
.news_select{width:235px; height:24px; line-height:36px; background:#fff; border:none; border:1px solid #cacaca; float:left; }
-->
</style>
</head>

<body >
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="padding:25px;">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      
        <tr>
          <td height="62"  style="border-bottom:2px solid  #d2d2d2;padding:0 20px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="39"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  </table></td>
                <td class="news_add_t">游戏添加</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="10"></td>
  </tr>

        <tr>
          <td height="500" ><div align="center" class="news_c">
          <form name="gameForm" id="gameForm" action="" method="post">
          <input type="hidden" name="id"  value="${game.id }"/>
          <div class="kv_item"><label class="news_add_list"> 游戏名称:</label><input type="text" class="news_txt" name="gameName" value="${game.gameName }" /></div>
          <div class="kv_item"><label class="news_add_list"> 游戏类别:</label><select class="news_select" name="type">
         <c:forEach items="${gameTypeList }" var="item">
<c:choose>
<c:when test="${game.type == item.id  }">
<option value="${item.id }" selected="selected">${item.typeName }</option>
</c:when>
<c:otherwise>
<option value="${item.id }">${item.typeName }</option>
</c:otherwise>
</c:choose>

</c:forEach>
          </select></div>
          <div class="kv_item"><label class="news_add_list">游戏类型:</label><select class="news_select" name="category">
<c:choose>
<c:when test="${game.category == 1  }">
<option value="1" selected="selected">Flash游戏</option>
<option value="2">网页游戏</option>
</c:when>
<c:otherwise>
<option value="1" >Flash游戏</option>
<option value="2" selected="selected">网页游戏</option>
</c:otherwise>
</c:choose>
          </select></div>
          <div class="kv_item"><label class="news_add_list">游戏星级:</label><input type="text" name="stars" value="${game.stars }" class="news_txt" /></div>
           <div class="kv_item"><label class="news_add_list">会员游戏:</label>
<select class="news_select" name="isMember">
<c:choose>
<c:when test="${game.isMember == 1  }">
<option value="0" >普通游戏</option>
<option value="1" selected="selected">会员游戏</option>
</c:when>
<c:otherwise>
<option value="0" selected="selected">普通游戏</option>
<option value="1" >会员游戏</option>
</c:otherwise>
</c:choose>
          </select>
          </div>
          <div class="kv_item"><label class="news_add_list">游戏地址:</label><input type="text" name="gameUrl" value="${game.gameUrl }" class="news_txt" /></div>
        <div class="kv_item"><label class="news_add_list">游戏描述:</label></div>
          <div><textarea class="txt_area" name="gameDescrip">${game.gameDescrip }</textarea></div>
          <c:if test="${game!=null }">
           <div class="kv_item"><label class="news_add_list">正方形图片:</label>
         
         <input id="square" name="square" type="hidden" />
			 	<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_square" />  
                <a class="easyui-linkbutton" onclick="startUpload('square');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_square').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
             
           </div>
           
            <div class="kv_item" id="picList_square">
             <c:forEach items="${list1 }" var="item">
<img src='${pageContext.request.contextPath}/showRectangleGamePic?id=${item.id}' width='80' height='80' />
</c:forEach>
           </div>
           
            <div class="kv_item"><label class="news_add_list">矩形图片:</label>
         
			 <input id="rectangle" name="rectangle" type="hidden" />
				<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_rectangle" />  
                <a class="easyui-linkbutton" onclick="startUpload('rectangle');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_rectangle').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
             
           </div>
           
                <div class="kv_item" id="picList_rectangle">
          <c:forEach items="${list2 }" var="item">
<img src='${pageContext.request.contextPath}/showRectangleGamePic?id=${item.id}' width='80' height='80' />
</c:forEach>
           </div>
       </c:if>    
          
          <div class="news_add_btn"><input type="button" id="tj" class="news_add_tj" value="添加"/><input type="button" onclick="javascript:history.go(-1)" class="news_add_qx" value="取消"/> </div>
          </form>
           </div></td>
           
        </tr>
        
      
        
      </table>
  
 

<table width="100%" style="line-height:48px;">
  <tr>
    <td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td>
  </tr>
</table>
</body>
</html>
