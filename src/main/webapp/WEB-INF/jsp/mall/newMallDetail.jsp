<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/23/14
  Time: 3:00 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${ctx}/css/new-global-min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/css/mall.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/cloud-zoom.1.0.2.min.js"></script>
<script type="text/javascript">
    function changeImg(id){
        $("#bigImg").attr("src","${ctx }/showMediumPic?id="+id);
    }

    $(document).ready(function(){
        $("#duiBtn").click(function(){
            var c = $("#J_IptAmount").val();
            if(isNaN(c) || c < 1){
                alert("兑换数量只能为数字！");
                return;
            }
            if($("#dmobilePhone").val()!=$("#mobilePhone").val()){
                alert("两次填写的手机号码不一致");
                return ;
            }
            $.ajax({
                cache: false,
                async: true,
                type: "POST",
                url: $("#mainForm").attr("action"),
                data:$("#mainForm").serialize(),// 你的formid

                error: function(request) {
                    alert("请求失败，请重试！");
                },
                success: function(data) {
                    if(data.resCode == 0){
                        alert(data.resMsg);
                        return;
                    }else if(data.resCode == 1){
                        alert(data.resMsg);
                        window.location.href="${ctx}/user/credits/obtainlist.html?type=over";
                    }else if(data.resCode == 2){
                        window.location.href="${ctx}/login";
                    }else if(data.resCode == 3){
                        alert(data.resMsg);
                        window.location.href="${ctx}/user/manager/userDetail";
                    }
                }
            });
        });
    });
</script>
<div id="wrapper" style="margin-bottom:1px">
    <div class="title-sub">
        <h2>兑换商品详情<i class="title icons"></i></h2>

    </div>
    <div class="model-box">
        <div class="content_t">
            <div class="content_tl fl">
                <div class="demo">
                    <div class="zoom-section">
                        <div class="zoom-small-image r3">
                            <div style="top:0px;z-index:9999;position:relative; width:100% " id="wrap">
                                <a id="zoom1" class="cloud-zoom" href="${ctx }/showPic?id=${list[0].id}" target="_blank">
                                    <img id="bigImg" alt="" src="${ctx }/showMediumPic?id=${list[0].id }" style="width: 100%" />
                                </a>
                            </div>
                        </div>
                        <div class="zoom-desc">
                            <p>
                                <c:forEach var="p" items="${list }" >
                                    <a  class="cloud-zoom-gallery" onclick="changeImg('${p.id}')" style="cursor: pointer;">
                                        <img class="zoom-tiny-image" src="${ctx }/showMediumPic?id=${p.id}" >
                                    </a>
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                    <!--zoom-section end-->

                </div>
            </div>

            <div class="content_tr fr r3">
                <div class="table-plan">
                    <form action="${ctx }/mall/exchange" id="mainForm" method="post">
                    <table>
                        <tbody>
                        <tr>
                            <td class="th">商品名称</td>
                            <td>${commodity.model }${commodity.name }</td>
                        </tr>
                        <tr>
                            <td class="th">原价</td>
                            <td>¥${commodity.price }</td>
                        </tr>
                        <tr>
                            <td class="th">兑换价</td>
                            <td>${commodity.integral }积分</td>
                        </tr>
                        <tr>
                            <td class="th">库存</td>
                            <td>${commodity.num }</td>
                        </tr>
                        <c:if test="${commodityCategory.name eq '充值卡'}">
                            <tr>
                                <td class="th">充值手机号码</td>
                                <td><input id="mobilePhone"  name="mobilePhone" value="${user.mobileNumber}" maxlength="11"  type="text" class="input" /></td>
                            <tr>
                            <tr>
                                <td class="th">确认手机号码</td>
                                <td><input id="dmobilePhone"   value="${user.mobileNumber}" maxlength="11"  type="text" class="input" /></td>
                            </tr>
                        </c:if>
                        <tr>
                            <td class="th">我要兑换</td>
                            <td><input name="id" type="hidden" value="${commodity.id }"/>
                                <input id="J_IptAmount" class="input" type="text" maxlength="2"  name="count" value="1" />件
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="border-bottom:none">
                                <div class="dh-button">
                                    <input type="button" value="立即兑换" name="立即兑换" id="duiBtn" /></div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
</form>
                </div>
            </div>

        </div>
        <!--content_b start-->
        <div class="content_b">
            <div class="jTabs">
                <ul><li class="jTab current">礼品总览</li></ul>
            </div>
            <div class="detail_c">
                ${commodity.commodityDesc }
            </div>

        </div>
        <!--content_b end-->
    </div>
</div>
