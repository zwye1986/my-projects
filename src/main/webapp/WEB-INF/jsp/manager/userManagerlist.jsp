<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.venada.efinance.enumtype.UserStatus" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理</title>
<link href="${ctx }/css/manager/main.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
<link href="${ctx }/css/jquery.msgbox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.msgbox.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css"rel="stylesheet" />
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
.selectedout td{ background:#fff;}
.tck{ width:363px; height:268px; position:absolute; background:#fff; right:180px; border:1px solid #cacaca; z-index:2; top:265px;}
.tck .tck_t{width:363px; height:40px; background:#3b3a3f; margin-top:0px; float:left; }
.tck_xx{ width:120px; color:#fff;font-size:16px; float:left;}
.del{ float:right; height:40px; width:40px; background:url(images/del.png); cursor:pointer;}
.tck form{ width:343px; text-align:left; color:#3b3a3f; font-size:12px;}
.tck_list{width:343px; line-height:18px; }
.tck_la{ width:80px; text-align:right; float:left; height:18px; line-height:18px; margin-right:5px;}

.czmm{ cursor:pointer;}
.czmm:hover{ text-decoration:underline;}
.news_add_tj,.news_add_qx{ width:59px; height:22px; border:none; background:#4797f1; cursor:pointer; margin-right:5px; color:#fff;}
.news_add_tj:hover,.news_add_qx:hover{ width:59px; height:22px; background:#3b3a3f; }

#updateuserform {border:1px solid #369;width:310px;height:150px;background:#fff;z-index:1000;position:absolute;display:none;} 
#updateuserform h4 span {float:left;} 
#updateuserform h4 span#close {margin-left:210px;font-weight:500;cursor:pointer;} 
#updateuserform p {padding:12px 0 0 30px;} 
#updateuserform p input {width:120px;margin-left:20px;} 
#updateuserform p input.myinp {border:1px solid #ccc;height:16px;} 
#updateuserform p input.sub {width:60px;margin-left:30px;} 
ul.ztree {margin-top: 10px;margin-left: 20px; border: 1px solid #617775;background: #f0f6e4;width:210px;height:300px;overflow-y:scroll;overflow-x:auto;}
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
		 
		 $(document).ready(function(){
			 
			 $("tbody>tr:has(:checked)").addClass("selected");   
			 
			 $("#sb").click(function(){
				 $("input[name='currentPage']").val(1);
				 $("#mainForm").attr("method","post").submit();
			 });
			 
			 var date1 = $("#startTime").val();
			    var date2 = $("#endTime").val();
				
			 $("#startTime").datepicker({
					changeMonth : true,
					changeYear : true
				});
				
				$("#endTime").datepicker({
					changeMonth : true,
					changeYear : true
				});
				$("#startTime").datepicker("option", "dateFormat", "yy-mm-dd");
				$("#endTime").datepicker("option", "dateFormat", "yy-mm-dd");

				$("#startTime").val(date1);
				$("#endTime").val(date2);
					
			 
			 $("#startAmount").blur(function() {
					var num = $("#startAmount").val();
					if (isNaN(num)) {
						alert("数据输入有误！");
						return false;
					};
				});

			 $("#endAmount").blur(function() {
					var num = $("#endAmount").val();
					if (isNaN(num) == null) {
						alert("数据输入有误！");
						return false;
					};
				});
			 
		
				 $("#submit").click(function(){
						var mobileNumber = $("#mobileNumber").val();
						var password=$("#password").val();
						var name=$("#name").val();
						var resSelid=$("#resSelid").val();
						if(mobileNumber.length!=0){
						$.ajax({
							type : "POST",
							async : false,
							cache : false,
							dataType : "json",
							data : {
								mobileNumber:mobileNumber,
								password:password,
								name:name,
								roleSelid:resSelid
							},
							url : "${ctx}/manager/saveUser",
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
					
					
					 $("#usubmit").click(function(){
							
							var mobileNumber = $("#umobileNumber").val();
							var name=$("#uname").val();
							var id=$("#uuser_id").val();
							var uresSelid=$("#uresSelid").val();
							if(mobileNumber.length!=0){
							$.ajax({
								type : "POST",
								async : false,
								cache : false,
								dataType : "json",
								data : {
									mobileNumber:mobileNumber,
									name:name,
									id:id,
									roleSelid:uresSelid
									
								},
								url : "${ctx}/manager/updateUser",
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
					


				$("#add_user").click(function(){
					regclick();
				});

				$("#close").click(function(){
					mCloseonclick();
				});

				$("#uclose").click(function(){
					ucloseonclick();
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
		 
		 function resetPassword(userid){
			 if(confirm("您确定要重置该用户的密码为其手机号码后6位吗？")){
				 $.post("${ctx}/manager/resetPassword", {
					 userid : userid
					}, function(data) {
						alert(data.msg);
					});
				 
			 }
		 }
		 
		  function modifyUser(userId){
				if($.type(userId) === 'undefined' || $.type(userId) === 'null'){
					alert("请选择一个用户纪录进行修改!");
					return;
				}
				 
				$.ajax({
					   type: "POST",
					   async: false,
					   cache: false,
					   dataType: "json",
					   data:{Id:userId},
					   url: "${ctx}/manager/getUserById",
					   success: function(data){
						   $("#umobileNumber").val(data.mobileNumber);
						   $("#uname").val(data.name);
						   $("#uuser_id").val(data.id);
						   $("#uresSel").val(data.roleName);
						   $("#uresSelid").val(data.roleId);
					   }
					});
				
				updateUserForm();
				
			}
		  
		  function deleteUser(userId){
				if($.type(userId) === 'undefined' || $.type(userId) === 'null'){
					alert("请选择一个用户纪录进行删除!");
					return;
				}
				$.ajax({
					   type: "POST",
					   async: false,
					   cache: false,
					   dataType: "json",
					   data:{Id:userId},
					   url: "${ctx}/manager/deleteUserAllInfoById",
					   success: function(data){
						  alert(data.msg);
						  window.location.href="${ctx}/manager/userList";
						  }
					});
			}
		  function freedomUser(userId,type){
				if($.type(userId) === 'undefined' || $.type(userId) === 'null'){
					alert("请选择一个用户纪录进行设置!");
					return;
				}
				$.ajax({
					   type: "POST",
					   async: false,
					   cache: false,
					   dataType: "json",
					   data:{Id:userId,type:type},
					   url: "${ctx}/manager/freedomUserById",
					   success: function(data){
						  alert(data.msg);
						  window.location.href="${ctx}/manager/userList";
						  }
					});
			}
		  

			function updateUserForm(){ 
				var updateuserform = document.getElementById("updateuserform"); 
				updateuserform.style.display = "block"; 
				updateuserform.style.position = "absolute"; 
				updateuserform.style.top = "30%"; 
				updateuserform.style.left = "40%"; 
				updateuserform.style.marginTop = "-75px"; 
				updateuserform.style.marginLeft = "-150px";
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
			
				function ucloseonclick() {
					var updateuserform = document.getElementById("updateuserform");
					updateuserform.style.display = "none";
					mybg.style.display = "none";
				}

				function beforeClick(treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.checkNode(treeNode, !treeNode.checked, null, true);
					return false;
				}

				
				function ushowMenu() {
					var setting = {
						check : {
							enable : true,
							chkboxType : {
								"Y" : "",
								"N" : ""
							}
						},
						view : {
							dblClickExpand : false
						},
						data : {
							simpleData : {
								enable : true
							}
						},
						callback : {
							beforeClick : ubeforeClick,
							onCheck : uonCheck
						}
					};
					var cityObj = $("#uresSel");
					var cityOffset = $("#uresSel").offset();
					var zNodes;
					var userId = $("#uuser_id").val();
					var str = "[";
					$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						dataType : "json",
						url : "${ctx}/manager/getRoleTree",//请求的action路径
						data : {
							userId : userId
						},
						error : function() {//请求失败处理函数
							alert('请求失败');
						},
						success : function(data) { //请求成功后处理函数。
							//console.log(data);//此处的data是JSON对象
							$.each(data, function(i, item) {
								str = str + "{ id:" + item.id + ", pId:" + item.parentId
										+ ", checked:" + item.checked + ", name:\""
										+ item.description + "\"},";
							});
							str = str.substring(0, str.length - 1);
							str = str + "]";//此时str是JSON字符串
							//console.log(str);
							zNodes = eval("(" + str + ")");//这货到底是做神马用的啊?  把JSON字符串转换成JSON对象
							//console.log(oStr);
						}
					});
					$.fn.zTree.init($("#utreeDemo"), setting, zNodes);
					$("#umenuContent").css({
						left : cityOffset.left + "px",
						top : cityOffset.top + cityObj.outerHeight() + "px"
					}).slideDown("fast");

					$("body").bind("mousedown", onBodyDown);
				}
				function uhideMenu() {
					$("#umenuContent").fadeOut("fast");
					$("body").unbind("mousedown", onBodyDown);
				}
				function onBodyDown(event) {
					if (!(event.target.id == "umenuBtn" || event.target.id == "uresSel"
							|| event.target.id == "umenuContent" || $(event.target)
							.parents("#umenuContent").length > 0)) {
						uhideMenu();
					}
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
		 
		 
		/*  function showUser(id) {
			 location.href="${ctx }/manager/user/showDetail?id="+id;
		} */
		
		 function showUser(id) {
			$.showPanel("查看用户明细",
					"${ctx }/manager/user/showDetail?id="+id,
					700, 500, true, "${ctx}");
			return;
	}
		
		function openClicks(cloumn){
			var sortseq = $("#sortseq").val();
			if(sortseq == "desc"){
				$("#sortseq").val("asc");
			}else if(sortseq == "asc"){
				$("#sortseq").val("desc");
			}else{
				$("#sortseq").val("desc");
			}
			$("#sort").val(cloumn);
			$("#mainForm").submit();
		}
		 
		</script>
		
</head>

<body style="padding:25px;">
<form action="${ctx }/manager/userList" method="get" target="_self"
		id="mainForm" name="mainForm">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="80"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td height="90" bgcolor="#3b3a3f"
								style="border-bottom: 1px solid #d5d1c8; border-top: 1px solid #d5d1c8; padding: 0 20px;"><table
									width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="30"><table width="100%" border="0"
												cellspacing="0" cellpadding="0">
											</table></td>
										<td><label class="txt">用户ID：</label> <input class="id"
											type="text" name="$.id" id="id" value="${condition.id}"></td>
										<td><label class="txt">手机号码：</label> <input class="phone"
											type="text" name="$.mobileNumber" id="mobileNumber"
											value="${condition.mobileNumber}"></td>

										<td width="270"><div align="center"
												style="height: 32px; line-height: 32px;">
												<span class="STYLE1"> <em>创建时间：</em> <input
													class="date" size="3" type="text" name="$.startTime"
													id="startTime" value="${condition.startTime }" />－<input
													type="text" class="date" name="$.endTime" id="endTime"
													size="3" value="${condition.endTime }" />
												</span>
											</div></td>
										
										
									</tr>
									
									<tr>
										<td height="30"><table width="100%" border="0"
												cellspacing="0" cellpadding="0">
											</table></td>
										<td><label class="txt">姓名：</label> <input class="id"
											type="text" name="$.name" id="name" value="${condition.name}"></td>
										
										<td><label class="txt">用户名：</label> <input class="user"
											type="text" name="$.nickName" id="nickName"
											value="${condition.nickName}"></td>

										
										<td width="215">
											<div align="center" style="height: 32px; line-height: 32px;">
												<span class="STYLE1"> <em>余额：</em> <input type="text"
													size="2" name="$.startAmount" id="startAmount"
													value="${condition.startAmount }" class="money" />－<input
													type="text" size="2" name="$.endAmount" id="endAmount"
													value="${condition.endAmount }" class="money" />
												</span>
											</div>
										</td>
										
										 <td><input type="button" value="查询" class="cx" id="sb" /></td>
									</tr>
									
									
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
          <td width="3%" height="46" bgcolor="e5e6e6" class="STYLE10"><div align="center">序号</div></td>
          <td width="20%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">用户ID</span></div></td>
          <td width="6%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">姓名</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">手机号码</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">密码</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">用户名</span></div></td>
          <td width="7%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openClicks('balance')">当前金额</span></div></td>
               <td width="8%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openClicks('credits')">积分</span></div></td>
               <td width="4%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">是否vip</span></div></td>
          <td width="10%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10" style="cursor: pointer;" onclick="openClicks('createTime')">创建时间</span></div></td>
          <td width="30%" height="40" bgcolor="e5e6e6" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>
           <input type="hidden" name="sort" id="sort" value="${condition.sort}">
		   <input type="hidden" name="sortseq" id="sortseq" value="${condition.sortseq}">
        </tr>
         <tbody>
        <c:forEach items="${userList }" var="item" varStatus="s" begin="0">
        <tr>
          <td height="40" bgcolor="#FFFFFF" ><div align="center">
            ${(page.currentPage-1)*page.pageSize+(s.index+1) }
            </div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${item.id}</span></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.name}</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.mobileNumber}</div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE21"><div align="center"><a  class="czmm"><a onclick="resetPassword('${item.id}');" href="###">重置密码</a></a></div></td>
          <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.nickName}</div></td>
          <td height="40" bgcolor="#FFFFFF"><div align="center" class="STYLE19" >${item.balance}元</div></td>
           <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${item.credits}</div></td>
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><c:choose><c:when test="${ item.vipTag eq 1}">是</c:when><c:otherwise>否</c:otherwise></c:choose>  </div></td>
             <td height="40" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div></td>
            <td height="40" bgcolor="#FFFFFF" class="STYLE21">
            <div align="center" class="chao_z_btn">
            <input type="button"  onclick="showUser('${item.id}');" value="用户详情"> 
            <input type="button"  onclick="modifyUser('${item.id}');" value="角色修改"> 
            <c:set var="status" value="<%=UserStatus.freedom.getIndex() %>"></c:set>
											<c:choose>
												<c:when test="${item.status eq status}">
													<input type="button" style="background: #f797f1"  onclick="freedomUser('${item.id}','unfreedom');"value="特权用户" > 
												</c:when>
												<c:otherwise>
												    <input type="button"  onclick="freedomUser('${item.id}','freedom');"  value="普通用户"> 
												</c:otherwise>
											</c:choose>
											
            </div></td>
        </tr>
        </c:forEach>
        </tbody>
<tr bgcolor="#FFFFFF">
	<td colspan="11" height="30"><%@include file="../../PaginationMore.inc"%></td>
</tr>
</table>
<table width="100%" style="line-height:48px;">
  <tr>
    <td align="center"  style="color:#666;" >苏ICP备13017605号 2013江苏维纳达软件技术有限公司</td>
  </tr>
</table>

</form>
<div id="updateuserform" style=" width:400px;height:380px">
		<div style="height:20px;background-color: rgb(59, 58, 63);padding:5px 0 0 5px;"><span><label class="tck_xx">修改用户</label></span><span style="float:right" id="uclose"><label style="color:#fff;">关闭</label></span></div>
		<p class="tck_list"><input type="hidden" id="uuser_id"/>
		<label  class="tck_la">用户名:&nbsp;&nbsp;</label> 
		  <input id="umobileNumber"  style="width:180px;" readonly="readonly"  />
		<div id="isexists" style="display: none" class="failmsg failformcss"><label>用户已经存在!</label></div>
		<div id="isnotexists" style="display: none"class="failmsg failformcss"><label>用户可以使用!</label></div>
		<p class="tck_list"><label for="checkempty" class="tck_la">用户姓名:</label>
		   <input id="uname" readonly="readonly" style="width:180px;"  /></p>
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
		    <p class="tck_list"><label class="tck_la">角色列表:</label>&nbsp;&nbsp;<textarea id="uresSel" style="width:180px;"  readonly cols="3" rows="2" value="" style="width:220px;" onclick="ushowMenu();" ></textarea>
		       <a id="umenuBtn" href="#" onclick="ushowMenu(); return false;"><label>选择</label></a>
		       <input id="uresSelid" type="hidden"  value="" />
		    </p>
		</div>
		</div>
		<div id="umenuContent" class="menuContent" style="display:none;">
			<ul id="utreeDemo" class="ztree" style="margin-top:0; width:340px; height: 100px;"></ul>
		</div>
		<div class="confirm" style="margin-left: 100px">
						<ul>
							<input type="button"  class="news_add_tj" id="usubmit" value="确定"/>
							<input type="button"   class="news_add_tj"
								id="ureset" value="取消"/>
						</ul>
		</div>
</body>
</html>
