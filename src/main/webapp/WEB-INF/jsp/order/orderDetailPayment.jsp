<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/css/base.css" rel="stylesheet" />
<link href="${ctx}/css/coolpos.css" rel="stylesheet" />
<style>


ul.doit_bd li {
	float: left;
	text-align: center;
	line-height: 50px;
	font-size: 20px;
}
ul.doit_bd li a.order_tj {
	width: 391px;
	height: 48px;
	display: inline-block;
	background: url(${ctx }/images/coolpos/order01_tj.png) no-repeat;
	cursor: pointer;
	color: #444;
}

ul.doit_bd li a.order_tjhover {
	background: url(${ctx }/images/coolpos/order02_tj.png) no-repeat;
	color: #fff;
}

ul.doit_bd li a.order_money {
	width: 400px;
	height: 48px;
	display: inline-block;
	background: url(${ctx }/images/coolpos/order_money01.png) no-repeat;
	cursor: pointer;
	color: #444;
}
ul.doit_bd li a.hoverorder_money {
	background: url(${ctx }/images/coolpos/order_money02.png) no-repeat;
	color: #fff;
}
ul.doit_bd li a.order_sucess {
	width: 371px;
	height: 48px;
	display: inline-block;
	background: url(${ctx }/images/coolpos/order_sucess01.png) no-repeat;
	cursor: pointer;
	color: #444;
}
ul.doit_bd li a.hoverorder_sucess {
	background: url(${ctx }/images/coolpos/order_sucess02.png) no-repeat;
	color: #fff;
}

</style>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/user.js"></script>

<script type="text/javascript">

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }
    
    
$(document).ready(function(){
	
	
	$("#title_content_1").removeClass("tab order_tjhover");
	$("#title_content_2").addClass("tab active hoverorder_money");
	$("#content_1").hide();
	$("#payButton").show();
	$("#content_2").show();
	
	$(".btn-slide").click(function(){
		$("#panel").slideToggle("slow");
		$(this).toggleClass("active"); return false;
	}); 
	
	
	
		$("a.tb-stock").click(function(){
			var num=$("#J_IptAmount").val();
			num--;
			if(num<=1){
				$("#J_IptAmount").val(1);
			}else{
				$("#J_IptAmount").val(num);
			}
			goodspayment(num);
		});
		
		
		$("#J_IptAmount").blur(function() {
			var reg1 = /^\d+$/;
			var num = $("#J_IptAmount").val();
			if (num.match(reg1) == null) {
				alert("数据输入有误！");
				return false;
			};
			goodspayment(num);
		});
		
		$("#mobilePhone").blur(function() {
			var mobliePhone = $("#mobilePhone").val();
			$.ajax({
				type : "POST",
				async : false,
				cache : false,
				dataType : "json",
				data : {
					mobileNumber:mobliePhone
				},
				url : "${ctx}/order/manager/validateMobilePhone",
				success: function(result){
					if(result.resultCode=='t'){
						$("#submit").show();
					}else{
					  alert(result.resultMsg);
					  return false;
					}
					}
				});
		});
		
		
		$("a.tb-increase").click(function() {
			var num = $("#J_IptAmount").val();
			num++;
			if (num <= 1) {
				$("#J_IptAmount").val(1);
			} else {
				$("#J_IptAmount").val(num);
			}
			goodspayment(num);
		});
		
		var goodpriceGoble = $("#goodsprice").html();
		function goodspayment(num){
			if(num>=5){
				$("#totalprice").html("面议");
				$("#payment").html("面议");
				$("#goodsprice").html("面议");
			}else{
			$("#totalprice").html((num * goodpriceGoble).toFixed(2));
			$("#payment").html((num * goodpriceGoble).toFixed(2));
			$("#goodsprice").html(goodpriceGoble);
			}
		}
		
		$("#payButton").click(function(){
			  $("#content_2").hide();
			  var id= getQueryString("id");
			  var payforMethod=$("input[name=payforMethod]:checked").val(); 
			  $.ajax({
					type : "POST",
					async : false,
					cache : false,
					dataType : "json",
					data : {
						Id:id,
						payforMethod:payforMethod
					},
					url : "${ctx}/order/manager/payorder",
					success: function(result){
						if(result.success){
							
							if(result.msg=="余额不足,请充值！"){
								 setTimeout(alert(result.msg),1000);
								 window.location.href="${ctx}"+result.url;
							}else if(result.msg=="纳币支付成功！"){
								 setTimeout(alert(result.msg),1000);
								 window.location.href="${ctx}"+result.url;
								 $("#title_content_2").removeClass("tab active hoverorder_money");
								 $("#title_content_3").addClass("tab hoverorder_sucess");
								 $("#content_3").show();
							}else{
								window.location.href=result.url;
							}
						  
						}else{
						   window.location.href="${ctx}"+result.url;
						   alert("付款失败");
						}
						}
					});
			 
		});
		
		
		 $.ajax({
				type : "GET",
				async : false,
				cache : false,
				dataType : "json",
				data : {
					Id: getQueryString("id")
				},
				url : "${ctx}/order/manager/getorderById",
				success: function(data){
						if(data.success){
						$("#content_1").hide();
						 $("#paymentResult").html("￥"+data.data.payment+"元");
					    $("#goodsNumResult").html(data.data.num);
					 	$("#goodsNameResult").html(data.data.goodsName);
					   var customerInfo=data.data.attentionName+"/"+data.data.attentionAddress+"/"+data.data.mobilePhone;
					    $("#customerInfo").html(customerInfo);
					 	$('a[href="orderlist.html"]').attr('href', 'orderlist.html?id='+id);
					 	$("#orderId").val(id);
					 		if(goodsprice=='面议'){
					 			location.href="${ctx}/order/manager/orderlist.html?id="+id;
					 		}else{
					 			$("#title_content_1").removeClass("tab order_tjhover");
         	    			    $("#title_content_2").addClass("tab active hoverorder_money");
					 			$("#payButton").show();
             	    		$("#content_2").show();
             	    		
					 		}
						}else{
						 alert("订单提交失败！");
						}
					}
				});
		

	});
</script>
<body>
	<div class="wrap">
<input id="orderId" type="hidden" value=""/>
		<!--content-->
		<div id="cntwrapper">
			<div class="content_Titlewrapper">
				<div class="content_Title">
					<h5>蛙宝团购</h5>
					<img src="${ctx}/images/coolpos/coolpos_logo.png" />
				</div>
			</div>
			<!--content-->
			<div id="content">
				<div class="content_top">
					<div class="content_top_l fl">
						<img src="${ctx}/images/coolpos/pos_l.png" />
					</div>
					<div class="content_top_r fr">
						<div class="cool_top fl">
							<img src="${ctx}/images/coolpos/pos_r.png" />
						</div>
						<div class="cool_cnt fl">转账汇款，余额查询，信用卡还款，手机充值</div>
						<div class="cool_btm fl">移动金融刷卡机（器）是一种采用无线方式的支付终端，在手机电话功能的基础上，
							集成了刷卡功能，为商户及个人用户提供POS机收款、银行卡转账、账单支付、卡帐户余额查询、
							信用卡还款、公共事业缴费等多项交易功能的终端设备、并能实现实时处理，资金实时到账。</div>
					</div>
				</div>
				<div class="content_c">
					<div id="tabbed_box_1" class="tabbed_box">
						<div class="tabbed_area">
							<ul class="tabs doit_bd">
								<li><a title="content_1" id="title_content_1" class="tab order_tj">1.提交订单</a></li>
								<li><a title="content_2" id="title_content_2" class="tab active order_money">2.选择支付方式</a></li>
								<li><a title="content_3" id="title_content_3" class="tab order_sucess">3.购买成功</a></li>
							</ul>
							<div style="display: block;" id="content_1" class="content">
								<table>
									<thead>
										<tr>
											<th>名称</th>
											<th>数量</th>
											<th>单价</th>
											<th>总价</th>
										</tr>
									</thead>
									<tbody id="tab">
										<tr>
											<td id="goodsName">${goods.goodsName}</td>
											<td><span><a class="tb-stock">-</a> <input
													id="J_IptAmount" class="tb-text" type="text"
													title="请输入领取游戏数量" maxlength="8" value="${goods.num}">
													<a class="tb-increase">﹢</a> </span></td>
											<td id="goodsprice" style="color: #f16244;">${goods.price}</td>
											<td id="totalprice" style="color: #f16244;">${goods.totalPrice}</td>
										</tr>
										<tr>
											<td style="border-right: none;"></td>
											<td
												style="border-top: none; border-left: none; border-right: none;"></td>
											<td
												style="border-top: none; border-left: none; border-right: none;"></td>
											<td style="border-left: none;">应付金额：<em id="payment"
												style="color: #f16244; font-size: 32px;">${goods.payment}</em></td>
										</tr>
									</tbody>
								</table>
								<table>
									<thead>
										<tr>
											<th>收件信息</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="tab">
										<tr>
											<td style="width: 120px; border-right: none;"><em
												style="color: #f16244;">*</em>收件人姓名：</td>
											<td style="border-left: none; text-align: left;"><input
												id="attentionName" type="text"
												style="width:201px; height:32px; background:url(${ctx}/images/coolpos/text.png); border:none;" /></td>
										</tr>
										<tr>
											<td style="width: 120px; border-right: none;"><em
												style="color: #f16244;">*</em>收件地址：</td>
											<td style="border-left: none; text-align: left;">
												<div class="address">
													<span class="label">居&nbsp;&nbsp;住&nbsp;&nbsp;地：</span> <select
														id="liveProvince" name="liveProvince"
														style="text-align: center;"
														onchange="moveCity('${pageContext.request.contextPath}/user/manager/getCityJson');">
														<option value="0">选择省／直辖市</option>
														<c:forEach var="item" items="${liveProvince }">
															<option
																<c:if test="${userDetail.liveProvince == item.provinceid}">selected="selected"</c:if>
																value="${item.provinceid }">${item.province }</option>
														</c:forEach>
													</select>&nbsp;&nbsp;&nbsp;&nbsp; <select id="liveCity"
														name="liveCity" style="text-align: center;"
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
													</select>&nbsp;&nbsp;&nbsp;&nbsp; <select id="liveArea"
														name="liveArea" style="text-align: center;">
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
											</td>
										</tr>
										<tr>
											<td style="width: 120px; border-right: none;"><em
												style="color: #f16244;">*</em>详细地址：</td>
											<td style="border-left: none; text-align: left;"><textarea
													id="attentionAddress"
													style="width: 530px; height: 100px; margin: 10px 0;"></textarea></td>
										</tr>
										<tr>
											<td style="width: 120px; border-right: none;"><em
												style="color: #f16244;">*</em>邮政编码：</td>
											<td style="border-left: none; text-align: left;"><input
												id="postCode" type="text"
												style="width:201px; height:32px; background:url(${ctx}/images/coolpos/text.png); border:none;" /></td>
										</tr>
										<tr>
											<td style="width: 120px; border-right: none;"><em
												style="color: #f16244;">*</em>手机号码：</td>
											<td style="border-left: none; text-align: left;"><input
												id="mobilePhone" type="text"
												style="width:201px; height:32px; background:url(${ctx}/images/coolpos/text.png); border:none;" /></td>
										</tr>
									</tbody>
								</table>
								<p class="tj_btn">
									<input id="submit" type="button" value=""
										style="width:105px; height:35px; cursor:pointer;background: url(${ctx}/images/coolpos/order_btn.jpg) no-repeat; border:none; float:right;" />
								</p>
							</div>


							<div style="display: none;" id="content_2" class="content">
								<p>
									<label>团购名称：</label><em id="goodsNameResult">${goods.goodsName}</em>
								</p>
								<p>
									<label>数量：</label><em id="goodsNumResult">${goods.num}</em>
								</p>
								<p>
									<label>顾客信息：</label><em id="customerInfo"> </em>
								</p>
								<p>
									团购成功后，蛙宝网将会向您的手机发送购买成功的提示信息，购买信息在<a href="orderlist.html">【用户中心—团购订单】</a>中可以及时查看！
								</p>
								<p>
									<label>应付金额:</label><em id="paymentResult"
										style="font-size: 28px; color: #f16244;">¥${goods.payment}元</em>
								</p>
								<div class="money_c">
									<table class="money_tab">
										<tr>
											<td><input name="payforMethod"  type="radio" value="1"  checked="checked"/></td>
											<td style="text-align: left; cursor: pointer;"><a
												href="###" style="color: #000; cursor: pointer;">纳币余额支付</a></td>
										</tr>
										<tr>
											<td><input name="payforMethod" type="radio" value="2" /></td>
											<td style="text-align: left;">财付通支付</td>
										</tr>
										<tr>
											<td></td>
											<td style="text-align: left;"><img
												style="border: 2px solid #d1d1d1; cursor: pointer;"
												src="${ctx}/images/cft_logo.png" /></td>
										</tr>
									</table>
								</div>
								<div class="order_bottom">
									<input id="payButton" type="button" value=""
										style="width:105px; height:35px; cursor:pointer;background: url(${ctx}/images/coolpos/order_btn02.jpg) no-repeat; border:none; float:right;" />
								</div>
							</div>


							<div style="display: none;" id="content_3" class="content">
								<p
									style="width: 335px; height: 88px; line-height: 88px; margin: 0 auto;">
									<img src="${ctx}/images/user/security01.png"
										style="display: inline-block; float: left;" /><em
										style="font-size: 32px;">订单支付成功!</em>
								</p>
								<p>
									尊敬的用户，订单支付成功！您可以进入<a href="orderlist.html">用户中心—团购订单</a>中查看详情。
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>