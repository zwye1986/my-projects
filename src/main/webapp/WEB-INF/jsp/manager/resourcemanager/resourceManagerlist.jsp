<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理</title>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<link href="${ctx }/css/manager/main.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<link href="${ctx }/css/jquery.msgbox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<script type="text/javascript" src="${ctx }/js/vanadium.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.msgbox.js"></script>
<style type="text/css">
<!--
.info {position:relative;background:#F4F4F4;color:#666; text-decoration:none;font-size:12px;width:150px;text-align:center; #ccc;height:25px;line-height:25px;}
.info:hover {background:#eee;color:#333;}
.info span {display: none }
.info:hover span {display:block;position:absolute;top:30px;left:160px;width:130px;border:1px solid #ff0000; background:#fff; color:#000;padding:5px;text-align:left;}

body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
	background-color: #f1f1f2;
	 color:#5c5c5c;
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
input[type="submit"]{widows:100px;}
fieldset{padding:10px; border:1px solid #000;}
input.rightformcss,select.rightformcss,textarea.rightformcss{border:1px solid green;padding:1px;}
.failmsg{color:#a40000;font-size:4px}
.msgvaluecss{font-style:italic;}
input.failformcss,select.failformcss,textarea.failformcss{border:1px solid #a40000;padding:1px;}
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
.cx{ width:59px; height:22px; background:url(${ctx}/images/admin/cx_btn.png) no-repeat; cursor:pointer; border:none; color:#fff;}
a.jsxg{ width:15px; text-align:center; cursor:pointer; color:#0cfec2; margin-left:5px;}
.selected td{ background:#e1f6f1;}
.tck{ width:363px; height:268px; position:absolute; background:#fff; right:180px; border:1px solid #cacaca; z-index:2; top:265px;}
.tck .tck_t{width:363px; height:40px; background:#3b3a3f; margin-top:0px; float:left; }
.tck_xx{ width:120px; color:#fff;font-size:16px; float:left;}
.del{ float:right; height:40px; width:40px; background:url(images/del.png); cursor:pointer;}
.tck form{ width:343px; text-align:left; color:#3b3a3f; font-size:12px;}
.tck_list{width:343px; line-height:18px; }
.tck_la{ width:80px; text-align:right; float:left; height:18px; line-height:18px; margin-right:5px;}

.czmm{ cursor:pointer;}
.czmm:hover{ text-decoration:underline;}


#alert {border:1px solid #369;width:300px;height:150px;background:#F4F4F4;z-index:1000;position:absolute;display:none;} 
#alert h4 {height:20px;background:#BABCC2;padding:5px 0 0 5px;} 
#alert h4 span {float:left;} 
#alert h4 span#close {margin-left:210px;font-weight:500;cursor:pointer;} 
#alert p {padding:12px 0 0 30px;} 
#alert p input {width:120px;margin-left:20px;} 
#alert p input.myinp {border:1px solid #ccc;height:16px;} 
#alert p input.sub {width:60px;margin-left:30px;} 

#updateRescform {border:1px solid #369;width:300px;height:150px;background:#F4F4F4;z-index:1000;position:absolute;display:none;} 
#updateRescform h4 {height:20px;background:#BABCC2;padding:5px 0 0 5px;} 
#updateRescform h4 span {float:left;} 
#updateRescform h4 span#close {margin-left:210px;font-weight:500;cursor:pointer;} 
#updateRescform p {padding:12px 0 0 30px;} 
#updateRescform p input {width:120px;margin-left:20px;} 
#updateRescform p input.myinp {border:1px solid #ccc;height:16px;} 
#updateRescform p input.sub {width:60px;margin-left:30px;} 
.news_add_tj,.news_add_qx{ width:59px; height:22px; border:none; background:#4797f1; cursor:pointer; margin-right:5px; color:#fff;}
.news_add_tj:hover,.news_add_qx:hover{ width:59px; height:22px; background:#3b3a3f; }
ul.ztree {margin-top: 10px;margin-left: 20px; border: 1px solid #617775;background: #f0f6e4;width:210px;height:300px;overflow-y:scroll;overflow-x:auto;}
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
-->
</style>

<script type="text/javascript">
var imgPath_session = '${ctx}' + '/images/alerts';
function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getCheckedNodes(true), 
			v = "";
			vid="";
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	var cityObj = $("#resSel");
	cityObj.attr("value", v);
	
	
	for ( var j= 0, il = nodes.length; j < il; j++) {
		vid += nodes[j].id + ",";
	}
	if (vid.length > 0)
		vid= vid.substring(0, vid.length - 1);
	var idObj = $("#resSelid");
	idObj.attr("value", vid);
}


function ubeforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("utreeDemo");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function uonCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("utreeDemo"), nodes = zTree
			.getCheckedNodes(true), 
			v = "";
			vid="";
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	var cityObj = $("#uresSel");
	cityObj.attr("value", v);
	
	
	for ( var j= 0, il = nodes.length; j < il; j++) {
		vid += nodes[j].id + ",";
	}
	if (vid.length > 0)
		vid= vid.substring(0, vid.length - 1);
	var idObj = $("#uresSelid");
	idObj.attr("value", vid);
}

var zNodes ="";
	
	var code;		

	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}
	
	
	function showMenu(){
		var setting = {
				check: {
					enable: true,
					chkStyle: "radio",
					radioType: "level"
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback : {
					beforeClick : beforeClick,
					onCheck : onCheck
				}
			};
		
		var str = "[";
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "${ctx}/manager/getResourceTree",//请求的action路径
			error : function() {//请求失败处理函数
				alert('请求失败');
			},
			success : function(data) { //请求成功后处理函数。
				//console.log(data);//此处的data是JSON对象
				$.each(data, function(i, item) {
					str = str + "{ id:" + item.id + ", pId:" + item.parentId
							+ ", checked:" + item.checked + ", name:\""
							+ item.modelName + "\"},";
				});
				str = str.substring(0, str.length - 1);
				str = str + "]";//此时str是JSON字符串
				//console.log(str);
				zNodes = eval("(" + str + ")");//这货到底是做神马用的啊?  把JSON字符串转换成JSON对象
				//console.log(oStr);
			}
		});
		
		var type = $("#level").attr("checked")? "level":"all";
		setting.check.radioType = type;
		showCode('setting.check.radioType = "' + type + '";');
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$("#treeDemo").show();
	}
	

	function ushowMenu(){
		var setting = {
				check: {
					enable: true,
					chkStyle: "radio",
					radioType: "level"
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback : {
					beforeClick : ubeforeClick,
					onCheck : uonCheck
				}
			};
		var str = "[";
		var resourceId=$("#uresc_id").val();
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			url : "${ctx}/manager/getResourceTreeByRId",//请求的action路径
			data:{
				resourceId:resourceId
			},
			error : function() {//请求失败处理函数
				alert('请求失败');
			},
			success : function(data) { //请求成功后处理函数。
				//console.log(data);//此处的data是JSON对象
				$.each(data, function(i, item) {
					str = str + "{ id:" + item.id + ", pId:" + item.parentId
							+ ", checked:" + item.checked + ", name:\""
							+ item.modelName + "\"},";
				});
				str = str.substring(0, str.length - 1);
				str = str + "]";//此时str是JSON字符串
				//console.log(str);
				zNodes = eval("(" + str + ")");//这货到底是做神马用的啊?  把JSON字符串转换成JSON对象
				//console.log(oStr);
			}
		});
		
		var type = $("#level").attr("checked")? "level":"all";
		setting.check.radioType = type;
		showCode('setting.check.radioType = "' + type + '";');
		$.fn.zTree.init($("#utreeDemo"), setting, zNodes);
		$("#utreeDemo").show();
	}
	
	
$(function(){

	 $("input[name='ids']").click(function(){
		 var checkedCount = jQuery("input[name='ids']:checked").length;
		 if(checkedCount == 1){
			 jQuery("#modifyresc").show();
		 }else{
			 jQuery("#modifyresc").hide();
		 }
	 });
	 

	 

	 
 $("#value").blur(function() {
	var value = $("#value").val();
	if(value.length!=0){
	$.ajax({
		type : "POST",
		async : false,
		cache : false,
		dataType : "json",
		data : {
			value:value
		},
		url : "${ctx}/manager/resourceExist",
		success: function(result){
			if(result.isExists=='true'){
				$("#isexists").show();
				$("#isnotexists").hide();
			}else{
				$("#isexists").hide();
				$("#isnotexists").show();
			}
			}
		});
	}
}); 

 $("#submit").click(function(){
		var value = $("#value").val();
		var modelname=$("#modelname").val();
		var resSelid=$("#resSelid").val();
		if(value.length!=0){
		$.ajax({
			type : "POST",
			async : false,
			cache : false,
			dataType : "json",
			data : {
				value:value,
				modelName:modelname,
				parentId:resSelid
			},
			url : "${ctx}/manager/saveResource",
			success: function(result){
				if(result.msgcode=='true'){
					window.location.reload();
					$.msgbox(result.msg);
				}else{
					$.msgbox(result.msg);
				}
				}
			});
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
		
		modifysingle(resId);
		
	});
	
	 $("#usubmit").click(function(){
			
			var uvalue = $("#uvalue").val();
			var umodelname=$("#umodelname").val();
			var id=$("#uresc_id").val();
			var pid=$("#uresSelid").val(); 
			if(uvalue.length!=0){
			$.ajax({
				type : "POST",
				async : false,
				cache : false,
				dataType : "json",
				data : {
					value:uvalue,
					modelName:umodelname,
					id:id,
					parentId:pid
				},
				url : "${ctx}/manager/updateResource",
				success: function(result){
					if(result.msgcode=='true'){
						window.location.reload();
						$.msgbox(result.msg);
					}else{
						$.msgbox(result.msg);
					}
					}
				});
			}
	 });
	


$("#add_resc").click(function(){
	regclick();
});

$("#close").click(function(){
	mCloseonclick();
});

$("#uclose").click(function(){
	ucloseonclick();
});

$("#user").click(function(){
	window.location.href="${ctx}/manager/userList";
});

$("#role").click(function(){
	window.location.href="${ctx}/manager/roleList";
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

});

function modifysingle(resId){
	$.ajax({
		   type: "POST",
		   async: false,
		   cache: false,
		   dataType: "json",
		   data:{Id:resId},
		   url: "${ctx}/manager/getResourceById",
		   success: function(data){
			   $("#uvalue").val(data.value);
			   $("#umodelname").val(data.modelName);
			   $("#uresc_id").val(data.id);
			   $("#uresSelid").val(data.parentId);
			   $("#uresSel").val(data.umodeName);
			   
		   }
		});
	
	updateRescformFun();
};
function deletesingle(resId){
	if(confirm("您确定要删除选中菜单纪录吗？")){
		$.ajax({
			   type: "POST",
			   async: false,
			   cache: false,
			   dataType: "json",
			   data:{resourceId:resId},
			   url: "${ctx}/manager/delResource",
			   success: function(data){
				   if(data.msgcode=='true'){
					   window.location.href="${ctx}/manager/resourceList";
				   }else{
					   $.msgbox(data.msg);
					   return;
				   }
			   }
			});
	}
}

function deletem(resId){
		$.ajax({
			   type: "POST",
			   async: false,
			   cache: false,
			   dataType: "json",
			   data:{resourceId:resId},
			   url: "${ctx}/manager/delResource",
			   success: function(data){
				   if(data.msgcode=='true'){
					   window.location.href="${ctx}/manager/resourceList";
				   }else{
					   $.msgbox(data.msg);
					   return;
				   }
			   }
			});
}
 var myAlert = document.getElementById("alert"); 
 var updateRescform = document.getElementById("updateRescform"); 

function updateRescformFun(){ 
	var updateRescform = document.getElementById("updateRescform"); 
	updateRescform.style.display = "block"; 
	updateRescform.style.position = "absolute"; 
	updateRescform.style.top = "30%";
	updateRescform.style.left = "40%";
	updateRescform.style.marginTop = "-75px"; 
	updateRescform.style.marginLeft = "-150px";
	mybg = document.createElement("div"); 
	mybg.setAttribute("id","mybg"); 
	mybg.style.background = "#000"; 
	mybg.style.width = "100%"; 
	mybg.style.height = "100%"; 
	mybg.style.position = "absolute"; 
	mybg.style.top = "0"; 
	mybg.style.left = "0"; 
	mybg.style.zIndex = "500"; 
	mybg.style.opacity = "0.3"; 
	mybg.style.filter = "Alpha(opacity=30)"; 
	document.body.appendChild(mybg);
	document.body.style.overflow = "hidden"; 
}


	function regclick() {
		var myAlert = document.getElementById("alert");
		myAlert.style.display = "block";
		myAlert.style.position = "absolute";
		myAlert.style.top = "30%";
		myAlert.style.left = "40%";
		myAlert.style.marginTop = "-75px";
		myAlert.style.marginLeft = "-150px";
		mybg = document.createElement("div");
		mybg.setAttribute("id", "mybg");
		mybg.style.background = "#000";
		mybg.style.width = "100%";
		mybg.style.height = "100%";
		mybg.style.position = "absolute";
		mybg.style.top = "0";
		mybg.style.left = "0";
		mybg.style.zIndex = "500";
		mybg.style.opacity = "0.3";
		mybg.style.filter = "Alpha(opacity=30)";
		document.body.appendChild(mybg);
		document.body.style.overflow = "hidden";
	}

	function mCloseonclick() {
		var myAlert = document.getElementById("alert");
		myAlert.style.display = "none";
		mybg.style.display = "none";
		myAlert.reset();
	}

	function ucloseonclick() {
		var updateRescform = document.getElementById("updateRescform");
		updateRescform.style.display = "none";
		mybg.style.display = "none";
		updateRescform.reset();
	}
	

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
		 
		 
		</script>
</head>
		
</head>

<body style="padding:25px;">
<form action="${ctx }/manager/resourceList" method="get" target="_self" id="mainForm" name="mainForm">
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
    <input type="hidden" name="pageSize" id="page.pageSize"
					value="${page.pageSize }" /> <input type="hidden"
					name="currentPage" id="page.currentPage"
					value="${page.currentPage }" />
        <tr>
         <td  width="1%" height="46" bgcolor="e5e6e6" class="STYLE10"><div align="center"></td>
          <td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">菜单名称</span></div></td>
          <td width="6%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">菜单地址</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">创建人</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">创建时间</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">修改时间</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
		 </tr>
         <tbody>
        <c:forEach items="${rescList }"  var="resource"  varStatus="s" begin="0">
        <tr>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><input type="checkbox" name="ids" value="${resource.id}"/>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${resource.modelName }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${resource.value }</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${resource.createBy }</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" ><fmt:formatDate value="${resource.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><fmt:formatDate value="${resource.lastUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center" class="chao_z_btn"><input type="button"  onclick="modifysingle('${resource.id}');" value="修改"> <input type="button" value="删除" onclick="deletesingle('${resource.id}');"></div></td>
        </tr>
        </c:forEach>
        </tbody>
<tr bgcolor="#FFFFFF">
	<td colspan="10" height="30"><%@include file="../../../PaginationMore.inc"%></td>
</tr>
</table>
<table width="100%" style="line-height:48px;">
  <tr>
    <td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td>
  </tr>
</table>
</td>
</tr></table>

</form>

		
	
</body>

<div id="alert" style="width:470px;height:380px">
		<div style="height:20px;background-color: rgb(59, 58, 63);padding:5px 0 0 5px;">
		<span><label class="tck_xx">新增菜单</label></span><span style="float:right" id="close"><label style="color:#fff;">关闭</label></span></div>
		
		<p class="tck_list">
		<label class="tck_la" for="checkempty">菜单地址:</label>
		 <input id="value" style="width:135px;"  class=":required" />
		 <span id="isnotexists" style="display: none " class="failmsg failformcss">地址可以使用</span><span id="isexists" style="display: none" class="failmsg failformcss">地址已经存在!</span> </p>
		 <p class="tck_list">
        	<label class="tck_la" for="checkempty">菜单名称:</label><input id="modelname"  style="width:135px;" class=":required" />
        </p>
        <p class="tck_list" ><label class="tck_la">上级菜单:</label> <input  id="resSel" type="text"  readonly value=""  style="width:180px;" onclick="showMenu();" ></input>
		       <a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
		</p>
	    <input id="resSelid" type="hidden"  value="" />
		
        <div class="zTreeDemoBackground left" >
		<ul id="treeDemo" class="ztree" style="display: none;width: 300px;height: 100px" ></ul>
		</div>
		
		
		<div class="confirm" style="margin-left: 100px">
			<ul>
							<input type="button"  class="news_add_tj" id="submit" value="确定"/>
							<input type="button"  class="news_add_tj" id="reset" value="取消"/>
						</ul>
		</div>
	</div>
	
	<div id="updateRescform" style=" width:470px;height:380px">
		<div style="height:20px;background-color: rgb(59, 58, 63);padding:5px 0 0 5px;">
		<span><label class="tck_xx">修改菜单</label></span><span style="float:right" id="uclose"><label style="color:#fff;">关闭</label></span></div>
		
		
		<p class="tck_list"><input type="hidden" id="uresc_id"/>
		<label class="tck_la">菜单地址:</label> <input id="uvalue"  style="width:135px;"  class=":required" />	<span id="isexists" style="display: none" class="failmsg failformcss">地址已经存在!</span><span id="isnotexists" style="display: none"class="failmsg failformcss">地址可以使用!</span></p>
		<p class="tck_list"><label class="tck_la" for="checkempty">菜单名称:</label><input id="umodelname"  style="width:180px;" class=":required" /></p>
		 <p class="tck_list"><label class="tck_la">上级菜单:</label><input  id="uresSel" type="text"  readonly value="" style="width:180px;" onclick="ushowMenu();" ></input>
		       <a id="menuBtn" href="#" onclick="ushowMenu(); return false;">选择</a>
		</p>
	    <input id="uresSelid" type="hidden"  value="" />
		
        <div class="zTreeDemoBackground left" >
		<ul id="utreeDemo" class="ztree" style="display: none;width: 300px;height: 100px" ></ul>
		</div>
		<div class="confirm" style="margin-left: 100px">
						<ul>
							<input type="button"  class="news_add_tj" id="usubmit" value="保存"/>
							<input type="button"  class="news_add_tj" id="ureset" value="取消"/>
						</ul>
		</div>
	</div>
</html>


