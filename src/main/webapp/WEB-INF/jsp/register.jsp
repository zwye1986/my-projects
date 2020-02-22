<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery-1.6.4.js"></script>
	<script type="text/javascript" src="${ctx}/js/regist.js"></script>
<script type="text/javascript" src="${ctx }/js/ec_alert_new.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#xgmm_tj").click(function() {
			doRegister('${ctx}','checkcodeimag');
		});
		
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
			ajaxPost:true
		});
		
	});
</script>
<%
	String inviteCode = request.getParameter("inviteCode");
	if (inviteCode == null) {
		inviteCode = (String) session.getAttribute("inviteCode");
		if (inviteCode == null) {
			inviteCode = "";
		}
	}
%>

<div class="header_w">
  <div class="header">
    <h1><a href="${ctx }/index">蛙宝网 </a></h1>
  </div>
</div>
<div class="main">

    
    <form class="registerform" >
    <div class="reg_title">手机注册</div>
    <div class="reg_list"><h4>获取返利步骤：1.注册蛙宝网 > 2.开心“点”游戏 > 3.轻松“拿”人民币</h4></div>
      <table width="100%" style="table-layout:fixed;">
        <tbody>
         
          <tr>
            <td class="need"style="width:10px;">*</td>
            <td style="width:95px;">请输入手机号：</td>
            <td><input type="text" value="" name="mobileNumber" id="mobileNumber" maxlength="11" class="inputxt" datatype="n11-11" nullmsg="请输入手机号！" errormsg="手机号码为11位数字！">
              <div class="Validform_checktip">请正确输入11位手机号码</div></td>
          </tr>
          <tr>
            <td class="need">*</td>
            <td>请输入验证码：</td>
            <td><input type="text" value="" name="checkCode" id="checkCode" class="inputxt"  style="width:50px; margin-right:5px;">
              <a style=" text-decoration:none;"><img id ="checkcodeimag" src="${ctx}/getCheckCode?codeName=registCode" onclick="changecode('${ctx}/getCheckCode?codeName=registCode','checkcodeimag')" style="height: 30px"><em style="cursor:pointer; font-style:normal;" onclick="changecode('${ctx}/getCheckCode?codeName=registCode','checkcodeimag')">换一张？</em></a>
              <div class="Validform_checktip">请正确输入验证码</div></td>
          </tr>
           <tr>
            <td class="need" style="width:10px;"></td>
            <td style="width:95px;">推荐号(可选)：</td>
            <td><input type="text" value="<%=inviteCode%>" id="inviteCode" name="inviteCode" class="inputxt" >
              <div class="Validform_checktip"></div></td>
          </tr>
          <tr>
            <td class="need"></td>
            <td></td>
            <td style="width:280px; display:inline-block;"><em>
              <input type="checkbox" checked="checked" value="1"/>
              </em><a href="${ctx }/agreement.html" target="_blank" style=" color:#555; text-decoration:underline;">阅读并同意协议内容</a>
              </td>
          </tr>
          <tr>
            <td class="need"></td>
            <td></td>
            <td style="padding:10px 0 18px 0;"><input type="button" id="xgmm_tj" value=" " class="login_btn" >
             
              <span id="msgdemo" style="margin-left:30px;"></span></td>
          </tr>
        </tbody>
      </table>
    </form>
 </div>