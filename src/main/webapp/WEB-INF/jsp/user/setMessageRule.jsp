<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="${ctx }/js/layer/layer/layer.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#mobileNumber").blur(function() {
		var mobileNumber = $("#mobileNumber").val();
		$.ajax({
			type : "POST",
			async : false,
			cache : false,
			dataType : "json",
			data : {
				mobileNumber:mobileNumber
			},
			url : "${ctx}/order/manager/validateMobilePhone",
			success: function(result){
				if(result.resultCode=='t'){
				
				}else{
				  alert(result.resultMsg);
				  return false;
				}
				}
			});
	});
	
		
		$("#xgmm_tj").click(function(){
			var mobileNumber = $("#mobileNumber").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var switchTag= $('input:radio[name=switchTag]:checked').val(); 
			$.post("${ctx }/user/manager/saveOrUpdateMessageRule", {mobileNumber : mobileNumber,startTime : startTime,endTime:endTime,switchTag:switchTag}, function(
					data) {
				alert('保存成功！');
				window.location.href="${ctx}/user/manager/setMessageRule";
			});
		});
		
		$.post("${ctx }/user/manager/queryMessageRule", {}, function(data) {
			if (data.count>0) {
				for(var i=Number(data.endTime);i<25;i++){
					 if(i==Number(data.endTime)){
						 $("#endTime").append("<option value='"+i+"'  selected='selected'>"+i+":00</option>"); 
					 }else{
					 $("#endTime").append("<option value='"+i+"'>"+i+":00</option>"); 
					 }
					}
			} else {
				 $("#endTime").empty();
					for(var i=1;i<25;i++){
						 if(i==24){
							 $("#endTime").append("<option value='"+i+"'  selected='selected'>"+i+":00</option>"); 
						 }else{
						 $("#endTime").append("<option value='"+i+"'>"+i+":00</option>"); 
						 }
						}
					/* var checkValue=24;
					for(var i=0;i<checkValue;i++){
						 $("#endTime option[value='"+i+"']").remove();   
					} */
				
			}
		});
		
		
		
		
	});
	
function isEmpty(s) {
	return ((s == undefined || s == null || s == "") ? true : false);
}

function changetxt(ele){
	        $("#endTime").empty();
		for(var i=1;i<25;i++){
		 $("#endTime").append("<option value='"+i+"'>"+i+":00</option>"); 
		}
	
		var checkValue=Number($(ele).val())+1;
		for(var i=0;i<checkValue;i++){
			 $("#endTime option[value='"+i+"']").remove();   
		}
}
</script>
<div id="main">
	<div class="model-box">
		<!--选项卡开始-->
		<div class="tab_box">
			<div class="lyz_tab_left">
				<div class="pro_con111">
					<ul>
						<li onclick="window.location.href='${ctx}/user/manager/userDetail'">基本资料</li>
              			<li onclick="window.location.href='${ctx}/user/manager/bindCard'" >银行卡绑定</li>
          			    <li onclick="window.location.href='${ctx}/user/manager/resetPassword'">修改密码</li>
         		        <li  class="hover"  onclick="window.location.href='${ctx}/user/manager/setMessageRule'">短信提醒</li>
              			<li onclick="window.location.href='${ctx}/user/manager/upload'">上传头像</li>
              			<li onclick="window.location.href='${ctx}/user/security/center'">会员中心</li>
					</ul>
				</div>
			</div>
			<div class="lyz_tab_right">
				<div class="hover" id="con_one_4" style="display: block;">
					<div class="bid-opt">
						<form class="forms_span">

							<ul class="items">
								<li class="txt"><em class="txt-c">*</em>请输入手机号：</li>
								<li><input type="text" id="mobileNumber"
									name="mobileNumber" value="${mobileNumber}"
									class="input input-w"></li>
							</ul>
							<ul class="items">
								<li class="txt">开始时间：</li>
								<li><select id="startTime" name="startTime"
									onchange="changetxt(this)">
										<%
											Integer startTime = Integer.parseInt(String.valueOf(request
													.getAttribute("startTime")));
											for (int i = 0; i < 24; i++) {
												if (i == startTime) {
										%>
										<option value="<%=i%>" selected="selected"><%=i%>:00
										</option>
										<%
											} else {
										%>
										<option value="<%=i%>"><%=i%>:00
										</option>
										<%
											}
											}
										%>
								</select></li>
							</ul>
							<ul class="items">
								<li class="txt">结束时间：</li>
								<li> <select id="endTime" name="endTime" ></select></li>
							</ul>
							<ul class="items">
								<li class="txt">短信提醒配置开关：</li>
								<c:choose>
									<c:when test="${switchTag eq 1}">
										<input type="radio" id="switchTag" class="ra-sex"
											name="switchTag" value="0" />
										<label class="sex">关闭</label>
										<input type="radio" id="switchTag" class="ra-sex"
											checked="checked" name="switchTag" value="1" />
										<label class="sex">开启</label>
									</c:when>
									<c:otherwise>
										<input type="radio" id="switchTag" class="ra-sex"
											name="switchTag" checked="checked" value="0" />
										<label class="sex">关闭</label>
										<input type="radio" id="switchTag" class="ra-sex"
											name="switchTag" value="1" />
										<label class="sex">开启</label>
									</c:otherwise>
								</c:choose>
							</ul>
							<div class="mes-ts">（短信提醒配置开关为关闭状态，24小时都会接收短信；当设置为开启状态，短信只会在设置的时间段接收）</div>
							<div class="sub-button">
								<input id="xgmm_tj" type="button" name="提交" value="提交">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<!--选项卡结束-->
