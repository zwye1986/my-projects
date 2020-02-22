<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
	<style>
		table tr{
			height : 5px
		}
</style>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/user.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert.js"></script>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var linkList = window.parent.document.getElementsByTagName("link"); //获取父窗口link标签对象列表
		var head = document.getElementsByTagName("head").item(0);
		//外联样式
		for ( var i = 0; i < linkList.length; i++) {
			var l = document.createElement("link");
			l.rel = 'stylesheet';
			l.type = 'text/css';
			l.href = linkList[i].href;
			head.appendChild(l);
		}
		
		$("input[name='nickName']").mouseout(function(){
			var nickName = $("input[name='nickName']").val();
			$.post("${ctx}/checkNickNameUnique", {nickName:nickName}, function(data) {
				if(data.resCode == 1){
					top.$.alerts.alert(data.resMsg);
				}
			});
		});

		$("#submitButton").click(function() {
			var name = $("input[name='name']").val();
			if ($.type(name) != 'undefined' && $.trim(name) != '') {
				if (!checkString(name)) {
					top.$.alerts.alert("姓名只能包含下划线、字母、数字、汉字！");
					return;
				}
			}

			$.ajax({
                cache: false,
                async: true,
                type: "POST",
                url: $("#iform").attr("action"),
                data:$("#iform").serialize(),// 你的formid
                
                error: function(request) {
                	top.$.alerts.alert("请求失败，请重试！");
                },
                success: function(data) {
                    if(data.resCode == 0){
                    	top.$.alerts.alert(data.resMsg);
                    	return;
                    }else{
                    	if(data.resCode == 1){
                    		top.$.alerts.alert(data.resMsg);
                    		window.location.href="${ctx}/user/manager/userDetail";
                    	}
                    }
                }
            });
		});
		var date = $("#birthday").val();
		$("#birthday").datepicker({
	    	 changeMonth: true,
	    	 changeYear: true
	    	     });
		$("#birthday" ).datepicker("option","dateFormat","yy-mm-dd");
		$("#birthday").val(date);
	});
</script>


<form name="userDetail" action="${ctx}/user/manager/updateUserDetail" id="iform"
	method="post">
	<div style="padding-left: 30px;">
		 <div class="item"  >
			<div class="iteml fl">
				<label><b class="ftx04">*</b>手机号码:</label><em style="height: 30px;
    width: 260px;">${user.mobileNumber }</em>
			</div>
		</div>
		
		<div class="user">
			<div class="userL fl">
				<label>
					<b class="ftx04">*</b>姓名:</label>
				<c:choose>
					<c:when test="${user.name != null}">
                            ${user.name }
                        </c:when>
					<c:otherwise>
						<input class="text" id="name" type="text" name="name"
							autocomplete="off" value="${user.name }" maxlength="30" />
					</c:otherwise>
				</c:choose>

			</div>
			<div id="name_error" style="line-height:40px;">（姓名一旦录入不能修改,请务必填写您的真实姓名！）</div>
		</div>
		<div class="user">
			<div class="userL fl" >
				<label>
					<b class="ftx04">*</b>昵称:</label>
					<em style="height: 30px;
    width: 260px;">
						<input class="text" id="nickName" type="text" name="nickName"
							 tabindex="1" style="ime-mode: disabled"
							autocomplete="off" value="${user.nickName }" maxlength="30"  />
</em>
			</div>
		</div>
		<div class="user">
			<div class="userL fl">
				<label><b class="ftx04">*</b>身份证号:</label><input id="idCard" type="text" name="idCard" maxlength="18"
					class="text" tabindex="1" style="ime-mode: disabled"
					autocomplete="off" value="${userDetail.idCard }" />
			</div>
		</div>
		
		<div class="address">
			<label><b class="ftx04">*</b>居住地:</label> <select
				id="liveProvince" name="liveProvince" style="text-align: center;"
				onchange="moveCity('${pageContext.request.contextPath}/user/manager/getCityJson');">
				<option value="0">选择省／直辖市</option>
				<c:forEach var="item" items="${liveProvince }">
					<option
						<c:if test="${userDetail.liveProvince == item.provinceid}">selected="selected"</c:if>
						value="${item.provinceid }">${item.province }</option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;&nbsp; <select id="liveCity" name="liveCity"
				style="text-align: center;"
				onchange="moveArea('${pageContext.request.contextPath}/user/manager/getAreaJson');">
				<option value="0">选择城市</option>
				<c:choose>
					<c:when test="${not empty liveCity }">
						<c:forEach var="item" items="${liveCity}">
							<option value="${item.cityid }"
								<c:if test="${userDetail.liveCity == item.cityid }">selected="selected"</c:if>>
								${item.city }</option>
						</c:forEach>
					</c:when>
				</c:choose>
			</select>&nbsp;&nbsp;&nbsp;&nbsp; <select id="liveArea" name="liveArea"
				style="text-align: center;">
				<option value="0">选择区县</option>
				<c:choose>

					<c:when test="${not empty liveArea }">
						<c:forEach var="item" items="${liveArea}">
							<option value="${item.areaid }"
								<c:if test="${userDetail.liveArea == item.areaid }">selected="selected"</c:if>>
								${item.area }</option>
						</c:forEach>
					</c:when>
				</c:choose>
			</select>

		</div>
		<div class="address" style="margin-left: 20px ">
			<span class="label"></span><span style="margin-left: 105px">
			<input type="text" name="address"
				id="address" style="width: 500px" maxlength="150"   value="${userDetail.address }"/></span>
			
		</div>
		
		<div class="user">
			<div class="userL fl">
				<label>QQ号码:</label><input class="text" id="qq" type="text" name="qq"
					tabindex="1" style="width: 260px; height: 30px; background-color: #eee; border-top: 1px solid #ccc; border-left: 1px solid #ccc; color: #505050;" autocomplete="off"
					maxlength="40" value="${userDetail.qq }" />

			</div>
		</div>

		<div class="user">
			<div class="userL fl">
				<label>电子邮箱:</label><input class="text" id="email" type="text" name="email"
					tabindex="1" style="ime-mode: disabled" autocomplete="off"
					maxlength="40" value="${userDetail.email }" />

			</div>
		</div>


		<div class="sex">
			<label>
				性别:</label><input type="radio"
				name="gender" value="1"
				<c:if test="${userDetail.gender == 1}">checked="checked"</c:if> />男
			<input type="radio" name="gender" value="2"
				<c:if test="${userDetail.gender == 2}">checked="checked"</c:if> />女
		</div>

		<div class="sex">
			<label>婚姻状况:</label><input type="radio"
				name="isMarried" value="0"
				<c:if test="${userDetail.isMarried == 0}">checked="checked"</c:if> />未婚
			<input type="radio" name="isMarried" value="1"
				<c:if test="${userDetail.isMarried == 1}">checked="checked"</c:if> />已婚
		</div>

		

		<div class="birthday">
			<label>出生日期:</label><input type="text" id="birthday" name="birthday" value="<fmt:formatDate value='${userDetail.birthday }'
										pattern='yyyy-MM-dd' />"  />

		</div>
		
		<div class="degree">
			<label>最高学历:</label><select id="eduLevel"
				name="eduLevel">
				<option value="1" ${userDetail.eduLevel==1 ? 'selected': ''}>初中</option>
				<option value="2" ${userDetail.eduLevel==2 ? 'selected': ''}>高中</option>
				<option value="3" ${userDetail.eduLevel==3 ? 'selected': ''}>中技</option>
				<option value="4" ${userDetail.eduLevel==4 ? 'selected': ''}>中专</option>
				<option value="5" ${userDetail.eduLevel==5 ? 'selected': ''}>大专</option>
				<option value="6" ${userDetail.eduLevel==6 ? 'selected': ''}>本科</option>
				<option value="7" ${userDetail.eduLevel==7 ? 'selected': ''}>硕士</option>
				<option value="8" ${userDetail.eduLevel==8 ? 'selected': ''}>博士</option>
				<option value="9" ${userDetail.eduLevel==9 ? 'selected': ''}>MBA</option>
				<option value="10" ${userDetail.eduLevel==10 ? 'selected': ''}>其他</option>
			</select>
		</div>
		<div class="graduated">
			<label>毕业学校:</label><input id="graduated" class="text"
				type="text" autocomplete="off"
				tabindex="1" name="graduated" value="${userDetail.graduated }"
				>
		</div>
		<div class="job">
			<label>职业:</label>
			<input id="profession" class="text" type="text"
				value="${userDetail.profession }" autocomplete="off"
				style="ime-mode: disabled" tabindex="1" name="profession">
		</div>
		<div class="income">
			<label>收入状况:</label><select id="incomeScope"
				name="incomeScope" style="margin-top:10px;">
				<option value="3000以下"
					${userDetail.incomeScope=='3000以下' ? 'selected': ''}>3000以下</option>
				<option value="3000－6000"
					${userDetail.incomeScope=='3000－6000' ? 'selected': ''}>3000－6000</option>
				<option value="6000-10000"
					${userDetail.incomeScope=='6000-10000' ? 'selected': ''}>6000-10000</option>
				<option value="1万以上"
					${userDetail.incomeScope=='1万以上' ? 'selected': ''}>1万以上</option>
			</select>
		</div>
		<div class="button">
			<input class="btns" type="button" value="" id="submitButton" style="margin-left:120px"/><span
				style="color: red"></span>
		</div>
	</div>
</form>