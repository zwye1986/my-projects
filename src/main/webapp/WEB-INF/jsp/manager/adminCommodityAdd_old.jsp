<%@page import="com.ckeditor.CKEditorConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://ckeditor.com" prefix="ckeditor" %>
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
			        'uploader'      : '${pageContext.request.contextPath}/commodityUpload.do;jsessionid='+$("#sessionUID").val()+'?commodityId=${game.id}',  
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
				        	 $("#picList_"+idName).html($("#picList_"+idName).html()+" <img id='img"+data+"' src='${pageContext.request.contextPath}/showSmallPic?id="+data+"' width='80' height='80' /><a id='a"+data+"' onclick=\"delpic('"+data+"');\">删除</a><input type='hidden' id='t"+data+"' name='picid' value='"+data+"' />&emsp;&emsp;&emsp; ");  
				         }else{
				        	 $("#picList_"+idName).html($("#picList_"+idName).html()+" <img src='${pageContext.request.contextPath}/showSmallPic?id="+data+"' width='80' height='80' /> "); 
				         }
			            
			        },  
			        'onUploadComplete':function(){  
			        	
			           // $('#importLispDialog').window('close');  
			        }  
			    });  
			    
			    
			 
			    
			    
		});
		
function startUpload(name){  
    idName=name;   
  
   
 $('#file_upload_'+name).uploadify('upload','*');  
}

function checkNum(obj)
{
 var re = /^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
     if (!re.test(obj.value))
    {
        alert("非法数字");
		obj.value="";
        obj.focus();
        return false;
     }
}  

function checkInt(obj)
{
	 var re = /^[0-9]*[1-9][0-9]*$/ ;
     if (!re.test(obj.value))
    {
        alert("非法数字");
		obj.value="";
        obj.focus();
        return false;
     }
}  

function delpic(id){
	if(confirm("确定要删除该图片吗？")){
		var params = { id:id};
		   $.ajax({
		          type: "POST",
		          async: true,
		          data:params,
		          cache: false,
		          url: "${ctx}/delPic",
		          success: function(data){
		           $("#img"+id).remove();
		           $("#a"+id).remove();
		           $("#t"+id).remove();
		          }
		   });
	}
	
}

</script>
<!-- 上传图片结束 -->

<script type="text/javascript">
$(function(){
	$("#tj").click(function(){
		$("#gameForm").attr("action","${ctx}/manager/adminCommodityDealAdd");
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
<input type="hidden" name="id"  value="${commodity.id }"/>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" background="${ctx }/images/xzbg.jpg">
<tr height="45" ><td width="100"><img src="${ctx }/images/xzbtn.png"/></td><td width="100%"></td><td><img src="${ctx }/images/tjbtn.png" id="tj" style="cursor:pointer;"/></td></tr>
<tr height="45" ><td width="100">商品名称</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input type="text" name="name" value="${commodity.name }"/></td></tr>
<tr height="45"  ><td width="100">商品类别</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
<select name="category">
<c:forEach items="${typeList }" var="item">
<c:choose>
<c:when test="${commodity.category == item.id  }">
<option value="${item.id }" selected="selected">${item.name }</option>
</c:when>
<c:otherwise>
<option value="${item.id }">${item.name }</option>
</c:otherwise>
</c:choose>

</c:forEach>
</select>
</td></tr>
<tr height="45"  ><td width="100">商品库存</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input name="num" onblur="checkInt(this)" value="${commodity.num }" type="text"/></td></tr>
<tr height="45"  ><td width="100">商品原价</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input name="price" onblur="checkNum(this)" value="${commodity.price }" type="text"/></td></tr>
<tr height="45"  ><td width="100">兑换积分</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input onblur="checkInt(this)" name="integral" value="${commodity.integral }" type="text"/></td></tr>
<tr height="45"  ><td width="100">商品型号</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input  name="model" value="${commodity.model }" type="text"/></td></tr>
<tr height="45" ><td width="100">商品备注</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%"><input  name="remark" value="${commodity.remark }" type="text"/></td></tr>

<tr height="45" ><td width="100">商品描述</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">


<textarea name="editor1" id="editor1" cols="20" rows="50">${commodity.commodityDesc }
</textarea> </td></tr>
<% 
	CKEditorConfig settings = new CKEditorConfig();
	settings.addConfigValue("height","400");
	settings.addConfigValue("width","1035");
%>
<ckeditor:replace replace="editor1" basePath="${ctx }/ckeditor/" config="<%=settings %>" />


<tr height="45"  ><td width="100">商品图片</td><td width="100%"></td></tr>
<tr ><td height="33"  width="100%">
	<input id="square" name="square" type="hidden" value="" />
				<input id='sessionUID' value='<%=session.getId()%>' type="hidden"/>
			    <input type="file" name="uploadify" id="file_upload_square" />
                <a class="easyui-linkbutton" onclick="startUpload('square');" href="javascript:void(0);">开始上传</a>   
                <a href="javascript:$('#file_upload_square').uploadify('cancel', '*')" class="easyui-linkbutton">取消所有上传</a>   
</td></tr>
<tr height="45"  ><td colspan="2" style="text-align: center" id="picList_square">
<c:forEach items="${list }" var="item">
<img id="img${item.id}" src='${pageContext.request.contextPath}/showSmallPic?id=${item.id}' width='80' height='80' /><a id="a${item.id }" onclick="delpic('${item.id}');">删除</a>&emsp;&emsp;&emsp; 
</c:forEach>

</td></tr>


</table>
</form>
<table width="100%"><tr><td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td></tr></table>
</body>
</html>
