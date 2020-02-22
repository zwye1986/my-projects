<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/17/14
  Time: 9:51 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#province")
                .change(
                function () {

                    $
                            .ajax({
                                type: "POST",
                                async: false,
                                cache: false,
                                dataType: "json",
                                url: "${ctx}/"
                                        + $(this).val()
                                        + "/getBankCityJson",
                                success: function (data) {
                                    $("#city option")
                                            .remove();
                                    $("#city")
                                            .append(
                                            "<option value='0'>请选择</option>");
                                    $("#bankid option")
                                            .remove();
                                    $("#bankid")
                                            .append(
                                            "<option value='0'>请选择开户银行</option>");
                                    $("#subBank option")
                                            .remove();
                                    $("#subBank")
                                            .append(
                                            "<option value='0'>请选择开户支行</option>");
                                    for (var i = 0; i < data.length; i++) {
                                        $("#city")
                                                .append(
                                                        "<option value=" + data[i].id + ">"
                                                        + data[i].name
                                                        + "</option>");
                                    }
                                }
                            });
                });

        $("#city")
                .change(
                function () {
                    $("#bankid option").remove();
                    $("#bankid")
                            .append(
                            "<option value='0'>请选择开户银行行</option>");
                    $("#subBank option").remove();
                    $("#subBank")
                            .append(
                            "<option value='0'>请选择开户支行</option>");
                    $
                            .post(
                                    "${ctx}/"
                                    + $(this)
                                    .val()
                                    + "/getBankJson",
                            {},
                            function (data) {
                                for (var i = 0; i < data.length; i++) {
                                    $("#bankid")
                                            .append(
                                                    "<option value=" + data[i].bankId + ">"
                                                    + data[i].bankName
                                                    + "</option>");
                                }
                            });
                });

        $("#bankid")
                .change(
                function () {
                    $("#subBank option").remove();
                    var cityid = $("#city").val();
                    $
                            .post(
                                    "${ctx}/"
                                    + $(this)
                                    .val()
                                    + "/"
                                    + cityid
                                    + "/getSubBankJson",
                            {},
                            function (data) {
                                for (var i = 0; i < data.length; i++) {
                                    $(
                                            "#subBank")
                                            .append(
                                                    "<option value=" + data[i].id + ">"
                                                    + data[i].name
                                                    + "</option>");
                                }
                            });
                });


        $("#bind").click(function () {
            $.ajax({
                cache: false,
                async: true,
                type: "POST",
                url: $("#bindCard").attr("action"),
                data: $("#bindCard").serialize(),// 你的formid

                error: function () {
                    alert("请求失败，请重试！");
                },
                success: function (data) {

                    if (data.resCode == 0) {
                        alert(data.resMsg);
                        return;
                    } else {
                        if (data.resCode == 1) {
                            alert(data.resMsg);
                            window.location.href = "${ctx}/user/manager/bindCard";
                        }
                    }
                }
            });
        });
    });

    function delBankCard(id){
        if(confirm("确定要删除此银行卡吗？")){
            window.location.href = "${ctx }/user/manager/"+id+"/delUserCardById";
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
                        <li onclick="window.location.href='${ctx}/user/manager/bindCard'" class="hover">银行卡绑定</li>
                        <li onclick="window.location.href='${ctx}/user/manager/resetPassword'">修改密码</li>
                        <li onclick="window.location.href='${ctx}/user/manager/setMessageRule'">短信提醒</li>
                        <li onclick="window.location.href='${ctx}/user/manager/upload'">上传头像</li>
                        <li onclick="window.location.href='${ctx}/user/security/center'">会员中心</li>
                    </ul>
                </div>
            </div>
            <div class="lyz_tab_right">
                <div style="display: block;" id="con_one_2" class="hover">
                    <div class="bt-top">
                        <div class="bt-title">
                            <h2>已绑定 <em class="txt-2"> ${fn:length(bankCards) } </em> 张银行卡，您还能绑定<em
                                    class="txt-2"> ${5 - fn:length(bankCards) } </em> 张银行卡</h2>
                        </div>
                        <div class="bd-wrapper">
                            <c:forEach items="${bankCards}" var="item">
                                <div class="bd-mes">${item.bankName}${item.branchBankName }&nbsp;&nbsp;&nbsp;&nbsp;${item.cardNumber }
                                    <i class="icon-close" onclick="delBankCard('${item.id}')"></i></div>
                            </c:forEach>
                        </div>

                        <%--<div class="bank-add-button"> <a href="javascript:;"><i class="icons add-blue"></i>+绑定银行卡</a> </div>--%>
                    </div>
                    <c:if test="${fn:length(bankCards) < 5 }">
                        <div class="bid-opt clearfix">
                            <form class="forms_span" name="bindCard" action="${ctx }/user/manager/dealBindCard" id="bindCard"
                                  method="post">
                                <ul class="items">
                                    <li class="bank txt">开户行所在省份／直辖市：</li>
                                    <li>
                                        <select id="province" name="provinceid">
                                            <option value="0">请选择</option>
                                            <c:forEach var="p" items="${bankProvince }">
                                                <option value="${p.bankProvinceId }">${p.province }</option>
                                            </c:forEach>
                                        </select>
                                    </li>
                                </ul>
                                <ul class="items">
                                    <li class="bank txt">开户行所在城市：</li>
                                    <li>
                                        <select id="city" name="cityid">
                                            <option value="0">请选择</option>
                                        </select>
                                    </li>
                                </ul>
                                <ul class="items">
                                    <li class="bank txt">请选择开户银行：</li>
                                    <li><select id="bankid" name="bankid" style="width: 300px">
                                        <option value="0">请选择</option>
                                    </select></li>
                                </ul>
                                <ul class="items">
                                    <li class="bank txt">请选择开户支行：</li>
                                    <li><select id="subBank" name="subBankid" style="width: 300px">
                                        <option value="0">请选择开户支行</option>
                                    </select></li>
                                </ul>

                                <ul class="items">
                                    <li class="bank txt">请输入银行卡号：</li>
                                    <li>
                                        <input type="text" id="prompt" class="input input-w" maxlength="20" name="cardNumber">
                                        <span class="tip"></span></li>
                                </ul>
                                <ul class="items">
                                    <li class="bank txt">开户姓名：</li>
                                    <li class="bank txt" style="text-align: left;width: 450px">
                                        <%--<input type="text" id="minInvest" class="input" value="50">--%>
                                        <%--<span class="tip">大于等于50元，小于等于每次投资金额的整数，设置为50可以增加您的资金利用率</span>--%>
                                            <c:choose>
                                                <c:when test="${not empty name }">
                                                    <%--<input id="name" class="input" type="text" name="name"--%>
                                                           <%--tabindex="1" style="width: 250px" autocomplete="off"--%>
                                                           <%--readonly="readonly" value="${name }" />--%>
                                                    ${name }
                                                </c:when>
                                                <c:otherwise>
                                                    <%--<input id="name" class="input" type="text" name="name"--%>
                                                           <%--tabindex="1" style="width: 400px" autocomplete="off"--%>
                                                           <%--readonly="readonly" value="您还没有设置姓名，请完善“基本资料”,姓名必须和持卡人姓名一致！" />--%>
                                                    您还没有设置姓名，请完善“基本资料”,姓名必须和持卡人姓名一致！
                                                </c:otherwise>
                                            </c:choose>
                                    </li>
                                </ul>
                                <ul class="items">
                                    <li class="bank txt">我的银行卡是：</li>
                                    <li>
                                        <input type="radio" class="ra-sex" value="1" checked="checked" name="cardType"/>
                                        <label class="sex">储蓄卡（借记卡）</label>
                                        <%--<input type="radio" class="ra-sex" value="">--%>
                                        <%--<label class="sex">信用卡（贷记卡）</label>--%>
                                        <%--<em style=" line-height:40px;" class="txt-c">*暂不支持</em>--%>
                                    </li>
                                </ul>
                                <div class="bd-button">
                                     <c:if test="${not empty name}">
                                         <input type="button" name="绑定" value="绑定" id="bind"/>
                                     </c:if>
                                </div>
                            </form>
                        </div>
                    </c:if>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
