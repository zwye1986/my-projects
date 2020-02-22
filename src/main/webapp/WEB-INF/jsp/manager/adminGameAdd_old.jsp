<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>wan商城</title>
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
			    
			    
			    
			    $("#file_upload_other").uploadify({  
			        'height'        : 27,   
			        'width'         : 80,    
			        'buttonText'    : '添加附件',  
			        'swf'           : '${pageContext.request.contextPath}/js/uploadify/uploadify.swf?ver=' + Math.random(),  
			        'uploader'      : '${pageContext.request.contextPath}/upload.do;jsessionid='+$("#sessionUID").val()+'?gameId=${game.id}&type=3',  
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
	margin-bottom: 30px;
	background-color:#3a3a3a;
}
table{padding-bottom:30px; padding-left:15px;}
input{ height:33px; width:100%;}
textarea{height:100px;width:100%;}
-->
</style>
</head>
<body >
<form name="gameForm" id="gameForm" action="" method="post">
<input type="hidden" name="id"  value="${game.id }"/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="${ctx }/images/xzbg.jpg">
<tr height="45" ><td width="100"><img src="${ctx }/images/xzbtn.png"/></td><td width="100%"></td><td><img src="${ctx }/images/tjbtn.png" id="tj" style="cursor:pointer;"/></td></tr>
<tr height="45" ><td width="100">游戏名称</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input type="text" name="gameName" value="${game.gameName }"/></td></tr>
<tr height="45"  ><td width="100">游戏类别</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
<select name="type">
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
</select>
</td></tr>
<tr height="45"  ><td width="100">游戏类型</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
<select name="category">
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

</select>
</td></tr>
<tr height="45"  ><td width="100">游戏星级</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input name="stars" value="${game.stars }" type="text"/></td></tr>
<tr height="45" ><td width="100">游戏描述</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><textarea name="gameDescrip" cols="30" rows="5">${game.gameDescrip }
</textarea> </td></tr>

<tr height="45"  ><td width="100">游戏地址</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input name="gameUrl" value="${game.gameUrl }" type="text"/></td></tr>

<c:if test="${game!=null }">

<tr height="45"  ><td width="100">游戏方形图片</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
	<input id="square" name="square" type="hidden" />
				<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_square" />  
                <a class="easyui-linkbutton" onclick="startUpload('square');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_square').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
</td></tr>
<tr height="45"  ><td colspan="2" style="text-align: center" id="picList_square">
<c:forEach items="${list1 }" var="item">
<img src='${pageContext.request.contextPath}/showRectangleGamePic?id=${item.id}' width='80' height='80' />
</c:forEach>
</td></tr>

<tr height="45"  ><td width="100">游戏矩形图片</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
		<input id="rectangle" name="rectangle" type="hidden" />
				<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_rectangle" />  
                <a class="easyui-linkbutton" onclick="startUpload('rectangle');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_rectangle').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
</td></tr>
<tr height="45"  ><td colspan="2" style="text-align: center" id="picList_rectangle">
<c:forEach items="${list2 }" var="item">
<img src='${pageContext.request.contextPath}/showRectangleGamePic?id=${item.id}' width='80' height='80' />
</c:forEach>
</td></tr>

<tr height="45"  ><td width="100">游戏截图</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
	<input id="other" name="other" type="hidden" />
				<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_other" />  
                <a class="easyui-linkbutton" onclick="startUpload('other');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_other').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
</td></tr>
<tr height="45"  ><td colspan="2" style="text-align: center" id="picList_other"></td></tr>

</c:if>

</table>
</form>
<table width="100%"><tr><td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td></tr></table>
</body>
</html>
