<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>蛙宝网</title>
<%
	String path = request.getContextPath();
%>
<!-- Le styles -->
<link href="<%=path%>/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/assets/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link rel="shortcut icon" href="assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<%=path%>/assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<%=path%>/assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<%=path%>/assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="<%=path%>/assets/ico/apple-touch-icon-57-precomposed.png">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="<%=path%>/assets/js/bootstrap.min.js"></script>



<!-- uploadify -->
<link rel="stylesheet" type="text/css" href="<%=path%>/js/uploadify/uploadify.css">
<script type="text/javascript" src="<%=path%>/js/uploadify/jquery.uploadify.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/uploadify/json2.js"></script>



<style type="text/css">
.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
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
			        'fileTypeExts'  : ' *.jpg; *.png;*.gif', 
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
			        'fileTypeExts'  : ' *.jpg; *.png;*.gif', 
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
			        'fileTypeExts'  : ' *.jpg; *.png;*.gif', 
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
</head>
<body>
<form name="addJobs" id="addPicWallForm" method="post" action="http://www.baidu.com">
	<table class="table">
		<tbody>
			<tr></tr>
		</tbody>
	</table>
	<h1 align="center">游戏修改</h1>
	<br>
	<table class="table">
		<tbody>
			<tr>
				<td class="" style="text-align: right" width="45%">游戏名称：</td>
				<td class="" style="text-align: left"><div class="controls">
						<input type="text" name="name" value="${game.gameName }">
					</div></td>

			</tr>
			<tr>
				<td class="" style="text-align: right" width="45%">游戏方形图片：</td>
				<td class="" style="text-align: left">
				
				<input id="square" name="square" type="hidden" />
				<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_square" /><hr>  
                <a class="easyui-linkbutton" onclick="startUpload('square');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_square').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
			    </td>
 
			</tr>
			
			<tr>
			<td colspan="2" style="text-align: center" id="picList_square">
			
			</td>
			</tr>
			
			<tr>
				<td class="" style="text-align: right" width="45%">游戏矩形图片：</td>
				<td class="" style="text-align: left">
				
				<input id="rectangle" name="rectangle" type="hidden" />
				<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_rectangle" /><hr>  
                <a class="easyui-linkbutton" onclick="startUpload('rectangle');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_rectangle').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
			    </td>
 
			</tr>
			
			<tr>
			<td colspan="2" style="text-align: center" id="picList_rectangle">
			
			</td>
			</tr>
			
			
			<tr>
				<td class="" style="text-align: right" width="45%">游戏截图：</td>
				<td class="" style="text-align: left">
				
				<input id="other" name="other" type="hidden" />
				<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_other" /><hr>  
                <a class="easyui-linkbutton" onclick="startUpload('other');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_other').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
			    </td>
 
			</tr>
			
			<tr>
			<td colspan="2" style="text-align: center" id="picList_other">
			
			</td>
			</tr>
			
			
			
			<tr>
			
				<td class="" style="text-align: right" width="45%"> <a class="btn pull-right btn-primary btn-small jetstrap-selected" id="addPicWallSubmit" >确定</a></td>
				<td class="" style="text-align: left"> <a class="btn  btn-primary btn-small jetstrap-selected" onclick="javascript:history.go(-1);">取消</a></td>

			</tr>
		</tbody>
	</table>
</form>
</body>
</html>
