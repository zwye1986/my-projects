<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/11/14
  Time: 4:05 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="${ctx }/js/user.js"></script>
<script type="text/javascript" src="${ctx}/js/venadaUtil.js" ></script>
<link href="${ctx }/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"/>
<script type="text/javascript">
    $(document).ready(function () {
        $(document).tooltip();
        var date = $("#birthday").val();
        $("#birthday").datepicker({
            changeMonth: true,
            changeYear: true
        });
        $("#birthday" ).datepicker("option","dateFormat","yy-mm-dd");
        $("#birthday").val(date);

        $("#submitButton").click(function() {
            var name = $("input[name='name']").val();
            if ($.type(name) != 'undefined' && $.trim(name) != '') {
                if (!checkString(name)) {
                    $("#nameErr").empty();
                    $("#nameErr").html("姓名只能包含下划线、字母、数字、汉字！");
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
                   alert("请求失败，请重试！");
                },
                success: function(data) {
                    if(data.resCode == 0){
                        $("#"+data.tip_id).empty();
                        $("#"+data.tip_id).html(data.resMsg);
                        $("html,body").animate({scrollTop:$("#"+data.tip_id).offset().top},1000)
                        return;

                    }else{
                        if(data.resCode == 1){
                            alert(data.resMsg);
                            window.location.href="${ctx}/user/manager/userDetail";
                        }else if(data.resCode ==2){
                            alert(data.resMsg);
                            return;
                        }
                    }
                }
            });
        });
    });
</script>
<div id="main">
    <div class="model-box">
        <!--选项卡开始-->
        <div class="tab_box">
            <div class="lyz_tab_left">
                <div class="pro_con111">
                    <ul>
                        <li onclick="window.location.href='${ctx}/user/manager/userDetail'" class="hover" >基本资料</li>
                        <li onclick="window.location.href='${ctx}/user/manager/bindCard'" >银行卡绑定</li>
                        <li onclick="window.location.href='${ctx}/user/manager/resetPassword'">修改密码</li>
                        <li onclick="window.location.href='${ctx}/user/manager/setMessageRule'">短信提醒</li>
                        <li onclick="window.location.href='${ctx}/user/manager/upload'">上传头像</li>
                        <li onclick="window.location.href='${ctx}/user/security/center'">会员中心</li>
                    </ul>
                </div>
            </div>
            <div class="lyz_tab_right">
                <div id="con_one_1" class="hover">
                    <div class="bid-opt">
                        <form class="forms_span" name="userDetail" action="${ctx}/user/manager/updateUserDetail" id="iform"
                              method="post">
                            <ul class="items bid-txt">
                                <li class="txt">
                                    <em class="txt-c">*</em>手机号码：
                                </li>
                                <li>${user.mobileNumber}<span class="tip"></span></li>
                            </ul>

                            <ul class="items <c:if test="${not empty user.name}"> bid-txt</c:if> " >
                                <li class="txt"><em class="txt-c">*</em>姓名：</li>
                                    <c:choose>
                                        <c:when test="${not empty user.name}">
                                            <li>${user.name}</li>
                                        </c:when>
                                        <c:otherwise>
                                            <li>
                                                <input name="name" type="text" class="input input-w" value="" title="姓名一旦录入不能修改,请务必填写您的真实姓名！"/>
                                                <span class="tip" id="nameErr"></span>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                            </ul>
                            <ul class="items">
                                <li class="txt"><em class="txt-c">*</em>昵称：</li>
                                <li>
                                    <input type="text" class="input"  name="nickName" value="${user.nickName}"/>
                                    <span class="tip" id="nickNameErr">

                                    </span>
                                </li>
                            </ul>
                            <ul class="items">
                                <li class="txt"><em class="txt-c">*</em>身份证号码：</li>
                                <li>
                                    <input type="text" class="input" value="${userDetail.idCard}" name="idCard"/>
                                    <span class="tip" id="idCardErr"></span></li>
                            </ul>
                            <ul class="items">
                                <li class="txt"><em>*</em>居住地：</li>
                                <li>
                                    <select id="liveProvince"
                                            name="liveProvince"
                                            onchange="moveCity('${pageContext.request.contextPath}/user/manager/getCityJson');">
                                        <option value="0">选择省／直辖市</option>
                                        <c:forEach var="item" items="${liveProvince }">
                                            <option
                                                    <c:if test="${userDetail.liveProvince == item.provinceid}">selected="selected"</c:if>
                                                    value="${item.provinceid }">${item.province }
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <select id="liveCity" name="liveCity"
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
                                    </select>
                                    <select id="liveArea" name="liveArea"
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
                                    <textarea class="site" name="address" >${userDetail.address }</textarea>
                                    <span style="top:80px;left:350px;" class="tip" id="addressErr"></span>
                                </li>
                            </ul>
                            <ul style="clear:both;" class="items">
                                <li class="txt">QQ号码：</li>
                                <li>
                                    <input type="text" class="input" value="${userDetail.qq}" name="qq"/>
                                    <span class="tip" id="qqErr"></span></li>
                            </ul>
                            <ul class="items">
                                <li class="txt">电子邮箱：</li>
                                <li>
                                    <input type="text" class="input" value="${userDetail.email}" name="email"/>
                                    <span class="tip" id="emailErr"></span></li>
                            </ul>
                            <ul class="items">
                                <li class="txt">性别：</li>
                                <li>
                                    <input type="radio" class="ra-sex" value="1" name="gender" <c:if test="${userDetail.gender == 1}">checked="checked"</c:if>/><label class="sex">男</label>
                                    <input type="radio" class="ra-sex" value="2" name="gender" <c:if test="${userDetail.gender == 2}">checked="checked"</c:if>/><label class="sex">女</label>
                                </li>
                            </ul>
                            <ul class="items">
                                <li class="txt">婚姻状况：</li>
                                <li>
                                    <input type="radio" class="ra-sex" value="0" name="isMarried" <c:if test="${userDetail.isMarried == 0}">checked="checked"</c:if>/>
                                    <label class="sex">未婚</label>
                                    <input type="radio" class="ra-sex" value="1" name="isMarried" <c:if test="${userDetail.isMarried == 1}">checked="checked"</c:if>/>
                                    <label class="sex">已婚</label>
                                </li>
                            </ul>
                            <ul class="items">
                                <li class="txt">出生日期：</li>
                                <li>
                                    <input class="input"
                                           type="text"
                                           id="birthday"
                                           name="birthday"
                                           value="<fmt:formatDate value='${userDetail.birthday }'
										pattern='yyyy-MM-dd' />"  />
                                    <span class="tip" id="birthdayErr"></span></li>
                            </ul>
                            <%--<ul class="items">--%>
                                <%--<li class="txt">最高学历：</li>--%>
                                <%--<li><select onchange="changerelative()">--%>
                                    <%--<option>大专</option>--%>
                                    <%--<option selected="selected">本科</option>--%>
                                    <%--<option>研究生</option>--%>
                                <%--</select></li>--%>


                            <%--</ul>--%>
                            <ul class="items">
                                <li class="txt">毕业学校：</li>
                                <li>
                                    <input type="text"  class="input" name="graduated" value="${userDetail.graduated }" />
                                    <span class="tip" id="graduatedErr"></span></li>
                            </ul>
                            <ul class="items">
                                <li class="txt">职业：</li>
                                <li>
                                    <input type="text" value="${userDetail.profession }" name="profession" class="input" />
                                    <span class="tip" id="professionErr"></span></li>
                            </ul>

                            <div style="margin-left:-50px;" class="bd-button">
                                <input type="button" value="保存" name="保存" id="submitButton">
                            </div>
                        </form>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
