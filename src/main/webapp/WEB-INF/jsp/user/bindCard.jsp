<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"
	scope="request" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var linkList = window.parent.document
								.getElementsByTagName("link"); //获取父窗口link标签对象列表
						var head = document.getElementsByTagName("head")
								.item(0);
						//外联样式
						for (var i = 0; i < linkList.length; i++) {
							var l = document.createElement("link");
							l.rel = 'stylesheet';
							l.type = 'text/css';
							l.href = linkList[i].href;
							head.appendChild(l);
						}
						
						

						$("#province")
								.change(
										function() {

											$
													.ajax({
														type : "POST",
														async : false,
														cache : false,
														dataType : "json",
														url : "${ctx}/"
																+ $(this).val()
																+ "/getBankCityJson",
														success : function(data) {
															$("#city option")
																	.remove();
															$("#city")
																	.append(
																			"<option value='0'>请选择</option>");
															$("#subBank option")
																	.remove();
															$("#subBank")
																	.append(
																			"<option value='0'>请选择开户支行</option>");
															for (var i = 0; i < data.length; i++) {
																$("#city")
																		.append(
																				"<option value="+data[i].id+">"
																						+ data[i].name
																						+ "</option>");
															}
														}
													});
										});

						$("#city")
								.change(
										function() {
											$("#subBank option").remove();
											$("#subBank")
													.append(
															"<option value='0'>请选择开户支行</option>");
											$.post("${ctx}/"
													+ $(this).val()
													+ "/getBankJson", {},
													function(data) {
														$("#bankList").html(
																data);
														$("#hb").css("display",
																"block");
													});

										});
					});

	function setTab2(name, cursel, n) {
		for (i = 1; i <= n; i++) {
			var menu = document.getElementById(name + i);
			var con = document.getElementById("con_" + name + "_" + i);
			menu.className = i == cursel ? "hover" : "";
			con.style.display = i == cursel ? "block" : "none";
		}
	}
	
	function delBankCard(id){
			if(confirm("确定要删除此银行卡吗？")){
				window.location.href = "${ctx }/user/manager/"+id+"/delUserCardById";
			}
	}
</script>
<div class="bdT">
	<div class="bdjl">
		<ul class="bdjl_list">
			<li><span><img src="${ctx }/images/user/yhk.png" alt="绑定银行卡"/></span><span>已绑定</span><span><em>${fn:length(bankCards) }</em></span>
                <span>张银行卡,您还能绑定</span><span><em>${5 - fn:length(bankCards) }</em></span><span>张银行卡</span></li>
		</ul>
	</div>
	<div class="bdc">
		<ul class="bdc_list">
			<c:forEach var="item" items="${bankCards }">
				<li style="margin-top:10px;">
					<span>
						<em style="color: #F16244; float: left; margin-right: 5px;">${item.bankName}${item.branchBankName }</em>
						&emsp;&emsp;<em style="float:left;">银行卡号：</em><em style="color: #F16244; float: left;">${item.cardNumber }</em>
						&emsp;&emsp;<em><a id="del" href="###" >
                        <img style=" margin-top: -5px;"  src="${ctx }/images/del02.png" alt="删除银行卡" onclick="delBankCard('${item.id}')"></a></em>
					</span>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${fn:length(bankCards) < 5 }">
	<form name="bindCard" action="${ctx }/user/manager/dealBindCard"
		method="post">
		<div class="long-logo">
			<div class="account">
				开户行所在省份／直辖市：<select id="province" name="provinceid">
					<option value="0">请选择</option>
					<c:forEach var="p" items="${bankProvince }">
						<option value="${p.bankProvinceId }">${p.province }</option>
					</c:forEach>
				</select>
			</div>
			<div class="account">
				开户行所在城市：<select id="city" name="cityid">
					<option value="0">请选择</option>
				</select>
			</div>
			<div id="hb" style="display: none;">
				<p class="bankT bankTR">
					请选择银行：<input id="banktext" name="banktext" class="banktext"
						type="text" readonly="readonly" /> <input id="_banktext"
						name="bankid" type="hidden" readonly="readonly" />
				</p>
				<div>
					<ul class="fn-clear" id="bankList"></ul>
				</div>
			</div>
			<div class="account">
				请选择开户支行：<select id="subBank" name="subBankid"><option
						value="0">请选择开户支行</option></select>
			</div>
			<div class="account">
				请输入银行卡号： <input id="cardNumber" class="text" type="text" sta="0"
					name="cardNumber" tabindex="1"
					style="ime-mode: disabled; width: 250px" maxlength="20"
					autocomplete="off">
			</div>
			<div class="account">
				开&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;名&nbsp;：
				<c:choose>
					<c:when test="${not empty name }">
						<input id="name" class="text" type="text" sta="0" name="name"
							tabindex="1" style="ime-mode: disabled; width: 250px"
							maxlength="20" autocomplete="off" readonly="readonly"
							value="${name }" />
					</c:when>
					<c:otherwise>
						<font style="font-size: 12px; color: red">您还没有设置姓名,请完善“基本资料”,姓名必须和持卡人姓名一致</font>
					</c:otherwise>
				</c:choose>

			</div>
			<div class="account">
				我的银行卡是：<input type="radio" checked="checked" name="cardType"
					value="1" id="yh" />储蓄卡(借记卡)&emsp;<input type="radio"
					name="cardType" value="2" id="yh" disabled="disabled"/>信用卡(贷记卡)<font color="red">＊暂不支持</font>
			</div>
			<div class="accountB">
				<input id="accountB" type="submit" value=" "
					<c:if test="${empty name}">disabled="disabled"</c:if> /><font
					style="font-size: 12px; color: red">${error }</font>
			</div>
		</div>
	</form>
</c:if>