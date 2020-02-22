

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看兑换详细</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.maklon.tools.js"></script>
<link rel="stylesheet" href="${ctx }/css/jquery_maklon_tools.css" />
	    <link href="${ctx }/css/ec_alerts.css" rel="stylesheet" />
	    <script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
	    <script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
	    <script type="text/javascript" src="${ctx}/js/i18N/CN/common.i18n_resource_zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/js/vldt/vldt.js"></script>

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
.news_c form{ width:600px; height:500px; text-align:center; }
.kv_item{ line-height:36px; padding:20px;}
.news_add_list{ line-height:36px; width:85px; text-align:right; float:left; margin-right:5px; color:#3c3c3c; font-size:16px;}
.news_txt{ width:235px; height:24px; line-height:36px; background:#fff; border:none; border:1px solid #cacaca; float:left;}
.txt_area{ width:462px; height:80px; background:#fff; border:1px solid #ccccca;  }
.news_checkbox{ float:left; margin-top:10px;}
.news_check{ float:left; color:#3b3a3f; float:left;}
.news_add_btn{ width:600px; height:24px; line-height:48px; margin-top:10px; float:left;}
.news_add_tj,.news_add_qx{ width:59px; height:22px; border:none; background:#4797f1; cursor:pointer; margin-right:5px; color:#fff;}
.news_add_tj:hover,.news_add_qx:hover{ width:59px; height:22px; background:#3b3a3f; }
-->
</style>
<script type="text/javascript">
$(document)
.ready(
		function() {
			
			$("#save").click(function(){
				window.parent.$.closePanel();
				window.parent.location.href = "${pageContext.request.contextPath}/manager/system/config";
			});
			
		});
</script>
</head>

<body >
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="padding:25px;">
        <tr>
          <td height="500" ><div align="center" class="news_c">
        <form name="mainForm"  id="mainForm" method="post"  action="${pageContext.request.contextPath}/manager/system/config/save">
	      <input type="hidden" name = "paramId" value="${systemConfig.paramId }" />		   
	      <div class="kv_item"><label class="news_add_list">参数名称：</label><input type="text" class="news_txt" name="paramName" id="paramName"  value="${systemConfig.paramName }" vr="0"/></div>
          <div class="kv_item"><label class="news_add_list">参数值:</label><input type="text" class="news_txt" name="paramValue" id="paramValue" value="${systemConfig.paramValue }" vr="0"/></div>
          <div class="kv_item"><label class="news_add_list">参数描述:</label><input type="text" class="news_txt" name="paramDesc"  id="paramDesc" value="${systemConfig.paramDesc }" vr="0" /></div>
          <div class="news_add_btn">
          <input type="submit" class="news_add_tj" value="保存" id="save"/>
          </form>
           </div></td>
           
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>