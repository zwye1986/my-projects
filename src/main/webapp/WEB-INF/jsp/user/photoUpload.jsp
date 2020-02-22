<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript"
	src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx}/js/user.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<!-- uploadify -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/upload/uploadify.css">

<script type="text/javascript"
	src="${ctx}/js/upload/jquery.uploadify.min.js"></script>
<!-- Jcrop -->
<link rel="stylesheet" href="${ctx}/css/Jcrop/jquery.Jcrop.css"
	type="text/css" />
<script type="text/javascript" src="${ctx}/js/upload/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/Jcrop/jquery.Jcrop.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var linkList = window.parent.document
								.getElementsByTagName("link"); //获取父窗口link标签对象列表
						var head = document.getElementsByTagName("head")
								.item(0);
						//外联样式
						for ( var i = 0; i < linkList.length; i++) {
							var l = document.createElement("link");
							l.rel = 'stylesheet';
							l.type = 'text/css';
							l.href = linkList[i].href;
							head.appendChild(l);
						}

						$("#local")
								.uploadify(
										{
											'height' : 35,
											'width' : 120,
											'multi' : false,//不允许用户添加多个文件到上传队列。
											'buttonImage' : '${ctx}/images/upload.png',
											'swf' : '${ctx}/js/upload/uploadify.swf?v='
													+ Math.random(),
											'uploader' : '${ctx}/user/manager/dealUpload;jsessionid='
													+ $("#sessionUID").val()
													+ '?method=upload',
											'auto' : true,
											'fileSizeLimit' : '60720KB',
											'fileTypeExts' : ' *.jpg; *.png;*.gif',
											'onUploadSuccess' : function(file,
													data, response) {
												var msg = $.parseJSON(data);
												if (msg.resultCode == 0) {
													alert(msg.resultMsg);
													return;
												}
												$("#target").attr("src",
														"${ctx}/getTmpImg?fileName="+msg.resultMsg);
												$("div .jcrop-holder img").attr("src",
														"${ctx}/getTmpImg?fileName="+msg.resultMsg);
												
												$("#preview").attr("src",
														"${ctx}/getTmpImg?fileName="+msg.resultMsg);
												$("#preview2").attr("src",
														"${ctx}/getTmpImg?fileName="+msg.resultMsg);
												$("#preview3").attr("src",
														"${ctx}/getTmpImg?fileName="+msg.resultMsg);

												$('#target')
														.Jcrop(
															{ 
															  setSelect : [ 0, 0, 220, 220 ],
															  onChange : updatePreview,
															  onSelect : updateCoords,
															  allowResize:true,
															  aspectRatio:1,
															  allowSelect:false,
															  maxSize : [310,310],
															  bgOpacity : 0.45,
															  minSize : [200,200],
															 /*  boxWidth : 315,
															  boxHeight : 315, */
															  aspectRatio : 1
															 },
															 function() {
																// Use the API to get the real image size
																var bounds = this.getBounds();
																	boundx = bounds[0];
																	boundy = bounds[1];
																	// Store the API in the jcrop_api variable
																	jcrop_api = this;
																});
											}
										});
	
		$("#save").click(function(){
		var x = $("#x").val();
		var y = $("#y").val();
		var w = $("#w").val();
		var h = $("#h").val();
		if( checkCoords() ){
			$.post("${ctx}/user/manager/cutImag", {x:x,y:y,w:w,h:h}, function(data) {
				if(data.resultCode==1){
					top.$.alerts.alert(data.resultMsg);
					top.location.href = '${ctx }/user/account/manager?cate=task';
					return;
				}else{
					top.$.alerts.alert(data.resultMsg);
					return;
				}
			});
		}
	});
});
	
	 //头像裁剪
	var jcrop_api, boundx, boundy;
	
	function updateCoords(c)
	{
		$('#x').val(c.x);
		$('#y').val(c.y);
		$('#w').val(c.w);
		$('#h').val(c.h);
	};
	function checkCoords()
	{
		if (parseInt($('#w').val())) return true;
		alert('请选择图片上合适的区域');
		return false;
	};
	function updatePreview(c){
		if (parseInt(c.w) > 0){
			var rx = 32 / c.w;
			var ry = 32 / c.h;
			$('#preview').css({
				width: Math.round(rx * boundx) + 'px',
            	height: Math.round(ry * boundy) + 'px',
            	marginLeft: '-' + Math.round(rx * c.x) + 'px',
            	marginTop: '-' + Math.round(ry * c.y) + 'px'
			});
		}
		{
			var rx = 64 / c.w;
			var ry = 64 / c.h;
			$('#preview2').css({
            	width: Math.round(rx * boundx) + 'px',
            	height: Math.round(ry * boundy) + 'px',
            	marginLeft: '-' + Math.round(rx * c.x) + 'px',
            	marginTop: '-' + Math.round(ry * c.y) + 'px'
			});
		}
		{
			var rx = 160 / c.w;
			var ry = 160 / c.h;
			$('#preview3').css({
				width: Math.round(rx * boundx) + 'px',
				height: Math.round(ry * boundy) + 'px',
				marginLeft: '-' + Math.round(rx * c.x) + 'px',
				marginTop: '-' + Math.round(ry * c.y) + 'px'
			});
		}
	};
</script>


<div style="margin-left: -20px;">
	<div class="txL fl">
		<label class="upload"><input id="local" type="button"><input
			id='sessionUID' value='<%=session.getId()%>' type="hidden" /></label>
			<span class="local_pic01">
				<a href="###">
					<img id="target"  src="${ctx }/images/user/local_pic01.png" alt="蛙宝网，用户，照片上传" />
				</a>
				
			</span>
	</div>
	<div
		style="width: 160px; height: 160px; margin: 50px 30px 0px 30px; overflow: hidden; float: left;">
		<img class="preview" id="preview3"  src="${ ctx}/images/user/160icon.png"/>
	</div>
	<div
		style="width: 64px; height: 64px; margin: 50px 0px 0px 0; overflow: hidden; float: left;">
		<img class="preview" id="preview2"  src="${ ctx}/images/user/64icon.png"/>
	</div>
	<div
		style="width: 32px; height: 32px; margin: 150px 0px 0px -65px; overflow: hidden; float: left;">
		<img class="preview" id="preview"  src="${ ctx}/images/user/32icon.png"/>
	</div>
</div>

<div id="px">
	<em id="w_160">160px*160px</em><em id="w_64">64px*64px</em><em id="w_32">32px*32px</em>
</div>
<label class="save">
			<input type="hidden" id="x" name="x" />
			<input type="hidden" id="y" name="y" />
			<input type="hidden" id="w" name="w" />
			<input type="hidden" id="h" name="h" />
		<input id="save" type="button" />

</label>
