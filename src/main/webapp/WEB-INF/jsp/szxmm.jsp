<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript" src="${ctx }/js/venadaUtil.js"></script>

<div class="header_w">
  <div class="header">
    <h1><a href="${ctx }/index">蛙宝网 </a></h1>
  </div>
</div>
<div class="main">
  <form class="registerform" action="${ctx}/dealSetNewPassword" method="post">
   <input type="hidden" name="mobileNumber" value="${mobileNumber }" />
   <input type="hidden" name="inviteCode" value="<c:out value='${param.inviteCode}'/>">
    <div class="reg_title">设置新密码</div>
    <div class="reg_list">
      <h4>完善信息，安全使用蛙宝网！</h4>
    </div>
    <table width="100%" style="table-layout:fixed;">
      <tbody>
        <tr>
          <td class="need" style="width:10px;">*</td>
          <td style="width:95px;">昵称：</td>
          <td><input type="text" value="${nickName }" name="nickName" class="inputxt" datatype="s2-18" nullmsg="请设置昵称！"  errormsg="昵称至少2个字符,且不能包含特殊字符！">
            <div class="Validform_checktip">昵称为2~18个字符</div></td>
            <c:if test="${oldPwdError != null }">
			<em class="ts_img"><img style="margin-left:200px;" src="${ctx }/images/error.png"></em>
			<a class="Validform_checktip">${oldPwdError}</a>
		</c:if>
        </tr>
        <tr>
          <td class="need">*</td>
          <td>请输入新密码：</td>
          <td>
          <input type="password"  name="newPwd" value="${newPwd }" class="inputxt" plugin="passwordStrength" datatype="password" nullmsg="请设置密码！" errormsg="密码范围在8~20位之间！">
             <div class="Validform_checktip" id="Validform_checktip">密码范围在8~20位之间！</div>
          <div class="passwordStrength" style="display: none"><b>密码强度：</b> <span>弱</span><span>中</span><span class="last">强</span></div>   
          <div>
          <c:choose><c:when test="${newPwdError != null}">
			<em class="ts_img"><img style="margin-top: 5px" src="${pageContext.request.contextPath }/images/user/com_bg.png"></em>
			<a class="ts_text"><font style="font-size: small;">${newPwdError}</font></a></c:when>
			<c:otherwise><font style="font-size: small;">（提示：最少8位，必须包含数字、字母。）</font></c:otherwise>
			</c:choose>
			</div>
         </td>
            
        </tr>
        <tr>
          <td class="need">*</td>
          <td>请确认新密码：</td>
          <td><input type="password" value="" name="newPwdRepeat" value="${newPwdRepeat }" class="inputxt" datatype="*" recheck="newPwd" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！">
            <div class="Validform_checktip">两次输入密码需一致</div></td>
        </tr>
        <tr>
          <td class="need" style="width:10px;">*</td>
          <td style="width:95px;">安全问题：</td>
         <td>
<select errormsg="请选择安全问题！" nullmsg="请选择安全问题！" datatype="*" id="question" name="question">
<c:forEach var="data" items="${securityQuestions }">
<option value="${data.id }" <c:if test="${data.id eq questionId }">selected="selected"</c:if> >${data.name }</option>
</c:forEach>
</select>
<c:if test="${questionError != null }">
			<em class="ts_img"> <img style="margin-top: 5px" src="${ctx }/images/user/com_bg.png"></em>
			<a class="ts_text">${questionError}</a>
		</c:if>
</td>
        </tr>
        <tr>
          <td class="need" style="width:10px;">*</td>
          <td style="width:95px;">答案：</td>
          <td><input type="text" name="answer" value="${answer }" class="inputxt" datatype="s1-18"  errormsg="确认答案正确！">
            <div class="Validform_checktip">请输入正确答案！</div></td>
        </tr>
        <tr>
          <td class="need"></td>
          <td></td>
          <td style="padding:10px 0 18px 0;"><input type="submit" id="xgmm_tj2" value=" " class="tj_btn" >
            <span id="msgdemo" style="margin-left:30px;"></span></td>
        </tr>
      </tbody>
    </table>
  </form>
</div>

<script type="text/javascript" src="${ctx }/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${ctx }/js/passwordStrength-min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#xgmm_tj").click(function(){
		
		$("form").submit();
	});
});

</script>
<script type="text/javascript">
$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
		
	$(".registerform").Validform({
		tiptype:function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip=o.obj.siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}else{
				var objtip=o.obj.find("#msgdemo");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		},
		
		usePlugin:{
			passwordstrength:{
				minLen:8,//设置密码长度最小值，默认为0;
				maxLen:20,//设置密码长度最大值，默认为30;
				trigger:function(obj,error){
					//该表单元素的keyup和blur事件会触发该函数的执行;
					//obj:当前表单元素jquery对象;
					//error:所设密码是否符合验证要求，验证不能通过error为true，验证通过则为false;
					

					if(error){
					//	$("#Validform_checktip").hide();
						$(".passwordStrength").hide();
					}else{
						$("#Validform_checktip").hide();
						$(".passwordStrength").show();	
						
					}
				}
			}
		},
		datatype:{
			"password":function(gets,obj,curform,regxp){
				//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
				var reg1=/^(?![^a-zA-Z]+$)(?!\D+$).{8,20}$/;
				if(reg1.test(gets)){return true;}
				return false;
	 
				//注意return可以返回true 或 false 或 字符串文字，true表示验证通过，返回字符串表示验证失败，字符串作为错误提示显示，返回false则用errmsg或默认的错误提示;
			}
		}
	});
})
</script>
