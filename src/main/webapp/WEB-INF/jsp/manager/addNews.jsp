<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加公告</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/page.js"></script>
<!-- kindEditor -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.js"></script>
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

.id,.phone,.user {
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

.news_c {
	width: 100%;
}

.news_c form {
	width: 800px;
	height: 500px;
	text-align: center;
}

.kv_item {
	line-height: 36px;
	padding: 20px;
}

.news_add_list {
	line-height: 36px;
	width: 85px;
	text-align: right;
	float: left;
	margin-right: 5px;
	color: #3c3c3c;
	font-size: 16px;
}

.news_txt {
	width: 235px;
	height: 24px;
	line-height: 36px;
	background: #fff;
	border: none;
	border: 1px solid #cacaca;
	float: left;
}

.txt_area {
	width: 700px;
	height: 250px;
	background: #fff;
	border: 1px solid #ccccca;
	margin-left: 110px;
}

.news_checkbox {
	float: left;
	margin-top: 10px;
}

.news_check {
	float: left;
	color: #3b3a3f;
	float: left;
}

.news_add_btn {
	width: 700px;
	height: 24px;
	line-height: 48px;
	margin-top: 10px;
	float: left;
}

.news_add_tj,.news_add_qx {
	width: 59px;
	height: 22px;
	border: none;
	background: #4797f1;
	cursor: pointer;
	margin-right: 5px;
	color: #fff;
}

.news_add_tj:hover,.news_add_qx:hover {
	width: 59px;
	height: 22px;
	background: #3b3a3f;
}
-->
</style>
<script>
	KindEditor
			.ready(function(K) {
				var editor1 = K
						.create(
								'textarea[name="content1"]',
								{
									cssPath : '${pageContext.request.contextPath}/js/kindeditor/plugins/code/prettify.css',
									uploadJson : '${pageContext.request.contextPath}/js/kindeditor/upload_json.jsp',
									fileManagerJson : '${pageContext.request.contextPath}/js/kindeditor/file_manager_json.jsp',
									allowFileManager : true,
									items : [ 'source', '|', 'undo', 'redo',
											'|', 'preview', 'print',
											'template', 'cut', 'copy', 'paste',
											'plainpaste', 'wordpaste', '|',
											'justifyleft', 'justifycenter',
											'justifyright', 'justifyfull',
											'insertorderedlist',
											'insertunorderedlist', 'indent',
											'outdent', 'subscript',

											'superscript', 'clearhtml',
											'quickformat', 'selectall', '|',
											'fullscreen', '|',

											'formatblock', 'fontname',
											'fontsize', '|', 'forecolor',
											'hilitecolor', 'bold',

											'italic', 'underline',
											'strikethrough', 'lineheight',
											'removeformat', '|', 'image',

											'table', 'hr', 'emoticons',
											'pagebreak', 'anchor', 'link',
											'unlink', '|', 'about' ],
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.forms['addNews'].submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.forms['addNews'].submit();
										});
									}

								});
				prettyPrint();
			});

	function dealAddNews() {

		if ($("#newsTitle").val().trim().length == 0) {
			$("#newsTitleSpan").show();
			$("#newsTitle").focus();
			return false;
		}

		var typecount = 0;
		$("input[name='type']:checkbox:checked").each(function() {
			typecount = typecount + 1;
		});
		if (typecount == 0) {
			$("#typeSpan").show();
			$("#typeSpan").focus();
			return false;
		}

		if ($("#newsid").val().length > 0) {
			$("#addNewsForm")
					.attr("action",
							"${pageContext.request.contextPath}/manager/dealUpdateNews");
		} else {
			$("#addNewsForm").attr("action",
					"${pageContext.request.contextPath}/manager/dealAddNews");
		}
		$("#addNewsSubmitButton").click();
		// $("#addNewsForm").submit();
	}
</script>

</head>
<body>

	<table style="padding: 25px;" align="center" border="0" cellpadding="0"
		cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td height="30"><table border="0" cellpadding="0"
						cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td height="10"></td>
							</tr>
							<tr>
								<td style="border-bottom: 2px solid #d2d2d2; padding: 0 20px;"
									height="62"><table border="0" cellpadding="0"
										cellspacing="0" width="100%">
										<tbody>
											<tr>
												<td height="39"><table border="0" cellpadding="0"
														cellspacing="0" width="100%">
													</table></td>
												<td class="news_add_t">新闻添加</td>
											</tr>
										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>

			<tr>
				<td height="500"><div class="news_c" align="center">
						<form name="addNews" id="addNewsForm" method="post" action="">
							<input type="hidden" id="newsid" name="newsid" value="${news.id }">
							<div class="kv_item">
								<label class="news_add_list"> 新闻标题:</label><input
									class="news_txt" type="text" name="newsTitle" id="newsTitle"
									value="${news.newsTitle }"> <span id="newsTitleSpan"
									style="color: red; display: none">新闻名称不能为空</span>
							</div>
							<div class="kv_item">
								<label class="news_add_list"> 新闻类别:</label>
								<c:forEach items="${newsCategoriesList }" var="item">
							 		<c:choose>
						 				<c:when test="${fn:indexOf(news.type,item.id) != -1 }">
						 					<input class="news_checkbox" type="checkbox" checked="checked" value="${item.id }" name="type" /><em class="news_check">${item.name }</em>
						 				</c:when>
						 			<c:otherwise>
						 				<input class="news_checkbox" type="checkbox" value="${item.id }" name="type" /><em class="news_check">${item.name }</em>
						 			</c:otherwise>
						 			</c:choose>
								 </c:forEach>
							</div>
							<div class="kv_item">
								<label class="news_add_list">引用自:</label><input class="news_txt" name="newsRef"
									type="text" value="${news.newsRef }">
							</div>
							<div class="kv_item">
								<label class="news_add_list">新闻链接:</label><input name="newsLink"
									class="news_txt" type="text" value="${news.newsLink }">
							</div>
							<div class="kv_item">
								<label class="news_add_list">文字编辑:</label><input name="author"
									class="news_txt" type="text" value="${news.author }">
							</div>
							<div class="kv_item">
								<label class="news_add_list">优先级:</label><input class="news_txt" name="priority"
									type="text" value="<c:choose><c:when test="${not empty news.priority  }">${news.priority }</c:when><c:otherwise>999999</c:otherwise></c:choose>">
							</div>
							<div class="kv_item">
								<label class="news_add_list">是否高亮:</label>
								<span style="margin-left: -600px"><input  name="isImp" type="radio" value="1" <c:if test="${news.isImp == 1 }">checked="checked"</c:if>/>是</span>
								<span><input  name="isImp" type="radio" value="0" <c:if test="${news.isImp == 0 }">checked="checked"</c:if> />否</span>
								
							</div>
							<div style="margin-top: 20px;margin-left:20px: left">
								<textarea class="txt_area" id="content1"  name="content1" >${news.content }</textarea><span id="content1Span" style="color: red;display: none">请填写新闻内容</span>
							</div>
							<div class="news_add_btn">
								<!-- 隐藏提交按钮 -->
								<input type="submit" id="addNewsSubmitButton" style="display: none" />
								<input class="news_add_tj" id="addNewsSubmit" value="::添加::" onclick="dealAddNews();" type="button" >
								<input class="news_add_qx" value="取消" type="reset">
							</div>
						</form>
					</div></td>

			</tr>
		</tbody>
	</table>

	<table style="line-height: 48px;" width="100%">
		<tbody>
			<tr>
				<td style="color: #666;" align="center">苏ICP备13017605号
					2013江苏维纳达软件技术有限公司</td>
			</tr>
		</tbody>
	</table>



</body>
</html>