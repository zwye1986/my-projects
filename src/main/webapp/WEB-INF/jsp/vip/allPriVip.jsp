<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/all_pri_vip.css" rel="stylesheet" />
<script language="javascript"> 
<!--
function setTab(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}
//-->
</script>

  <!--vip_content start-->
  <div class="vip_content"> 
    <!--grid_c2f start-->
    <div class="grid_c2f">
      <div class="gr_mod_box2">
        <div class="wb_priv_top">
          <div class="wb_priv_top_tit fl">蛙宝会员特权 </div>
          <span class="wb_priv_top_vip">为您理财增值，VIP您的全部</span>
          <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:30px; margin-top:15px;"> <a  class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
        </div>
      </div>
      <div class="gr_mod_box2">
        <div class="wb_priv_center"> 
          <!--tab_start-->
          <div class="tab_box">
            <div class="lyz_tab_left">
              <div class="pro_con111">
                <ul>
                  <h3 class="vip_title">VIP特权</h3>
                  <li class="hover icon_list_jf" id="one1" onclick="setTab(&#39;one&#39;,1,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon01.png"/></span><em>每天额外获得5积分</em></a></li>
                  <li class="icon_list_fh" id="one2" onclick="setTab(&#39;one&#39;,2,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon02.png"/></span><em>等级个性符号</em></a></li>
                  <li class="icon_list_pwd" id="one3" onclick="setTab(&#39;one&#39;,3,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon03.png"/></span><em>设置支付密码</em></a></li>
                  <li class="icon_list_dz" id="one4" onclick="setTab(&#39;one&#39;,4,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon04.png"/></span><em>会员实现T+0到账</em></a></li>
                  <li class="icon_list_dx" id="one5" onclick="setTab(&#39;one&#39;,5,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon05.png"/></span><em>短信提醒免费接收</em></a></li>
                  <li class="icon_list_dj"  id="one6" onclick="setTab(&#39;one&#39;,6,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon06.png"/></span><em>无需点击游戏</em></a></li>
                  <li class="icon_list_ll" id="one7" onclick="setTab(&#39;one&#39;,7,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon07.png"/></span><em>会员游戏利率更高</em></a></li>
                  <li class="icon_list_email" id="one8" onclick="setTab(&#39;one&#39;,8,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon08.png"/></span><em>安全邮箱</em></a></li>
                  <li  class="icon_list_phone" id="one9" onclick="setTab(&#39;one&#39;,9,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon09.png"/></span><em>绑定手机NO.2</em></a></li>
                  <li class="icon_game_speedup" id="one10" onclick="setTab(&#39;one&#39;,10,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon10.png"/></span><em>会员升级加速</em></a></li>
                  <h3 class="all_high_game_title">VIP特权</h3>
                  <li class="icon_game_more_high" id="one11" onclick="setTab(&#39;one&#39;,11,11)"><a href="javascript:void(0)"><span><img src="images/vip/all_icon11.png"/></span><em>会员专享更高利率的游戏</em></a></li>
                </ul>
              </div>
            </div>
            <div class="lyz_tab_right">
              <div class="hover" id="con_one_1">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>每天额外获得5积分</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner01.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员，每天登录蛙宝网页面，系统自动给予额外会员积分5积分。</span>
                  </ul>
                  <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_2" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>等级个性符号</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner02.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员，会员等级符号区别普通用户，等多等级符号等待您的探索，体现您个人的魅力。</span>
                  </ul>
                  <div class="level_rule">
                    <div class="sys_user_icon">
                      <div class="sys_user_icon_n"> <span>［普通用户]</span> </div>
                      <div class="sys_user_icon_v"> <span>［VIP用户］</span> </div>
                    </div>
                  </div>
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_3" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>设置支付密码</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner03.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员，提供支付密码设置服务，保护您的账户不受侵害；设置支付密码能够很好的保障你的资金安全。如果您忘记设置的支付密码，可在修改支付密码项中<a href="#" class="rule_txt2">找回密码</a>，密码将已短信形式发送至您的手机。</span>
                  </ul>
                
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_4" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>会员实现T+0到账</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner04.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员，提现无需等待—T+0到账，让提现旅途更舒心。</span>
                  </ul>
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_5" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>短信提醒免费接收</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner05.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员，短信提醒免费接收，绝不遗漏财富。主要发送项目：提现通知，充值通知,返利通知,惩罚通知及一切短信业务通知。</span>
                  </ul>
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_6" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>无需点击游戏</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner06.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员之后，返利不再需要用户每日点击游戏项目；系统自动帮您完成游戏任务，不受到网络影响和人为原因遗漏游戏任务。轻松愉快赢得人民币！</span>
                  </ul>
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_7" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>会员游戏利率更高</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner07.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员之后，可以专享会员游戏，会员游戏利率更高，较于普通用户用相同时间获得更高返利。</span>
                  </ul>
                  <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_8" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>安全邮箱</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner08.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>安全邮箱可以帮您找回您的重要信息，是你蛙宝信息可靠的守卫者。</span>
                  </ul>
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_9" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>绑定手机NO.2</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner09.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>如果您的手机号码丢失，绑定的第二个手机号将会接管您的账户。</span>
                  </ul>
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_10" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>会员升级加速</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner10.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员之后，会员升级加速，更多增值业务敬请期待。</span>
                  </ul>
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
              <div class="hover" id="con_one_11" style="display: none">
                <div class="other_jf_title"><em style="color:#FD6B6F;">></em>会员专享更高利率游戏</div>
                <div class="other_jf_content">
                  <div class="other_jf_content_banner"> <img width="960" height="135" src="images/vip/jf_banner11.png"/> </div>
                  <ul class="other_jf_content_list">
                    <li>所面向用户</li>
                    <span>所有蛙宝会员</span>
                    <li>服务说明</li>
                    <span>加入会员之后，会员专享更高利率的游戏。</span>
                  </ul>
                   <div class="sys_kthy_w">
                  <div class="mod_btn_common_sub sys_kthy fr" style="margin-right:450px; margin-top:20px;"> <a class="xufei" href="${ctx}/user/4/manager">开通会员</a> </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!--tab_end--> 
        </div>
      </div>
    </div>
  </div>
</div>
<!--grid_c2f end-->
</div>
<!--vip_content end--> 