<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/vip_privilege.css" rel="stylesheet" />
<link href="${ctx }/css/game_privilege.css" rel="stylesheet" />
<!--幻灯片-->
<script type="text/javascript" src="${ctx }/js/jquery.Xslider.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	// 焦点图片水平滚动
	$("#slider1").Xslider({
		// 默认配置
		affect: 'scrollx', //效果  有scrollx|scrolly|fade|none
		speed: 800, //动画速度
		space: 6000, //时间间隔
		auto: true, //自动滚动
		trigger: 'mouseover', //触发事件 注意用mouseover代替hover
		conbox: '.conbox', //内容容器id或class
		ctag: 'div', //内容标签 默认为<a>
		switcher: '.switcher', //切换触发器id或class
		stag: 'a', //切换器标签 默认为a
		current:'cur', //当前切换器样式名称
		rand:false //是否随机指定默认幻灯图片
	});
	
	
});
</script>
<script type="text/javascript">
$(document).ready(function(){
	
	// 焦点图片水平滚动
	$("#slider").Xslider({
		// 默认配置
		affect: 'scrollx', //效果  有scrollx|scrolly|fade|none
		speed: 800, //动画速度
		space: 6000, //时间间隔
		auto: true, //自动滚动
		trigger: 'mouseover', //触发事件 注意用mouseover代替hover
		conbox: '.conbox', //内容容器id或class
		ctag: 'div', //内容标签 默认为<a>
		switcher: '.switcher', //切换触发器id或class
		stag: 'a', //切换器标签 默认为a
		current:'cur', //当前切换器样式名称
		rand:false //是否随机指定默认幻灯图片
	});
	
	
});
</script>

<!--选项卡-->
<script language="javascript"> 
<!--
function setTab2(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}
//-->
</script>
<!--选项卡2-->
<script language="JavaScript" src="${ctx }/js/tab.js"></script>
 <!--vip_content start-->
  <div class="vip_content"> 
    <!--grid_c2f start-->
    <div class="grid_c2f"> 
      <!--mainbar start-->
      <div class="mainbar">
        <div class="mainbar_wrap">
          <div class="v_mod_panel_em_con clearfix">
            <div class="wb_privilege">
              <div class="wb_privilege_hd">
                <h3 >蛙宝特权</h3>
              </div>
              <div class="wb_privilege_bd">
                <p>VIP会员可享众多VIP特权，参与精彩活动！蛙宝会员有什么？不止这么简单！</p>
              </div>
              <div class="wb_privilege_control" onclick="javascript:window.location.href='${ctx }/user/4/manager'" style="cursor: pointer;">
              	
              </div>
              <div class="privilege_recommend_content">
                <p> <a style="color:#FD6B6F;" >【月费${month_cost }元】</a>
                  <input type="checkbox" checked="checked" class="recommend_check"/>
                  到期自动续费 </p>
              </div>
            </div>
            <div class="v_mod_slider">
              <div id="slider1" class="slider">
                <div class="conbox">
                  <div><a href="#" title=""><img width="730" height="260" alt="jquery" src="${ctx }/images/vip/pic01.jpg"></a></div>
                  <div><a href="#" title=""><img width="730" height="260" alt="jquery" src="${ctx }/images/vip/pic02.jpg"></a></div>
                  <div><a href="#" title=""><img width="730" height="260" alt="jquery" src="${ctx }/images/vip/pic03.jpg"></a></div>
                </div>
                <div class="switcher"> <a href="###" class="cur"></a> <a href="###"></a> <a href="###"></a></div>
              </div>
            </div>
          </div>
          <div class="v_mod_wrap3">
            <div class="v_mod_wrap3_hd">
              <h3 class="v_mod_wrap3_tit"><a href="${ctx }/all_pri_vip.html" target="_blank">实用特权</a></h3>
            </div>
            <div class="v_mod_wrap3_c">
              <div class="qq_mod_tq">
                <ul class="qq_mod_tq_list">
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_01.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2"> 会员短信免费接收，任何咨询不再错过，
                        蛙宝帮你谨记您的财富动向。</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_02.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2">个性图标，个性等级，一键加入，让别
                        人看见你的与众不同！</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_03.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2">任务无需动手，轻松拿返利！让你无压
                        力完成理财！</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_04.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2"> 安全邮箱功能为您提供找回重要信息，
                        让你不为记忆所烦。</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_05.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2"> 支付密码让您不再为账户安全而烦恼，
                        随时随地完全随心所欲赚大钱。</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_06.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2">手机丢失，账户安全怎么办？蛙宝会员
                        绑定第二个手机，你可以随时接管您的
                        账户。</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_07.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2"> 蛙宝会员升级加速！快来加速你的等级！</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_08.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2"> 更高利率游戏让你更多财富聚集！加入
                        会员专享会员游戏哦！</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_09.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2">会员登录每日额外增加积分5积分！，积
                        分越滚越多！</p>
                    </div>
                  </li>
                  <li class="qq_mod_tq_item">
                    <div class="qq_mod_tq_photo"><img  src="${ctx }/images/vip/pri_10.jpg"></div>
                    <div class="qq_mod_tq_info">
                      <p class="c_tx2">提现一定要等待吗？只要加入会员，不
                        管何时，时刻想提就提！</p>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--mainbar end--> 
      <!--sidebar start-->
      <div class="sidebar">
        <div class="mod_userinfo_gb">
          <div  class="mod_userinfo_login">
            <div class="mod_loginBox " style="">
            <sec:authentication property="principal" var="currentUsr"
					scope="page" />
				<c:if
					test="${currentUsr eq '' or currentUsr eq null or currentUsr eq 'anonymousUser'}">
					<div class="loginBox" >
						<a class="mod_userinfo_login_entry" style="margin-top:28px;" title="用户登录" href="${ctx }/login.html" style="margin-left: 0px;margin-top: 55px"></a>
					</div>
				</c:if>

				<sec:authorize access="isAuthenticated()">
					<div class="profile_basic_info clearfix  " style="margin-top: 55px;margin-left: -9px" >
						<div class="vip_mod_user_avatar fl">
							<img width="60px" height="60px" alt="用户头像"
								src="${ctx }/${PHOTO_PATH == null ? 'images/people.png' : PHOTO_PATH}">
						</div>
						<div class="vip_mod_login_content fl">
							<div class="account_info clearfix">
								<sec:authentication property="principal.nickName" />
								
								<a class="account_quit fr"
									href="${ctx }/j_spring_security_logout">退出</a>
							</div>
							<div class="profile_level">
								<yma:LevelConvertIcoTag level='${LEVEL }' vipTag="${VIPTAG}"/>
							</div>
						</div>
					</div>
				</sec:authorize></div>
            <ul class="mod_pri_list">
              <li>
                <dl>
                  <dt><img src="${ctx }/images/vip/pri_icon01.png"></dt>
                  <dd>会员提现T+0到账</dd>
                </dl>
              </li>
              <li>
                <dl>
                  <dt><img src="${ctx }/images/vip/pri_icon02.png"></dt>
                  <dd>会员游戏利率更高</dd>
                </dl>
              </li>
            </ul>
            <div class="v_mod_userinfo_slogan">
              <p> <span class="c_tx3">VIP特权、游戏等更多专属VIP服务！<a href="${ctx}/all_pri_vip.html" style="color:#FD6B6F; text-decoration:underline;">查看全部会员特权>></a></span> </p>
            </div>
            <div class="android_down_btn"><a href="#"><img src="images/vip/android_down_btn.png"/></a></div>
          </div>
        </div>
        <div class="v_mod_wrap2">
          <div class="v_mod_wrap2_hd" style="margin-top:50px;">
            <h3 class="f_tx5">会员最喜欢的特权排行</h3>
          </div>
          <div class="v_mod_wrap2_bd">
            <div class="qq_rank">
              <ul class="qq_rank_list clearfix">
                <li class="qq_rank_item qq_rank_item_1">
                  <div class="qq_rank_order"> <span>1</span> </div>
                  <div class="qq_rank_photo"><img alt="表情漫游" src="${ctx }/images/vip/mod_list01.png"></div>
                  <div class="qq_rank_info">
                    <p> <strong>高利率游戏</strong> </p>
                    <p class="c_tx4"> <a target="_blank" href="#">查看详情</a> </p>
                  </div>
                </li>
                <li class="qq_rank_item qq_rank_item_1">
                  <div class="qq_rank_order"> <span>2</span> </div>
                  <div class="qq_rank_photo"><img alt="表情漫游" src="${ctx }/images/vip/mod_list02.png"></div>
                  <div class="qq_rank_info">
                    <p> <strong>提现T+0到账</strong> </p>
                    <p class="c_tx4"> <a target="_blank" href="#">查看详情</a> </p>
                  </div>
                </li>
                <li class="qq_rank_item qq_rank_item_1">
                  <div class="qq_rank_order"> <span>3</span> </div>
                  <div class="qq_rank_photo"> <a target="_blank" href="#"> <img alt="表情漫游" src="${ctx }/images/vip/mod_list03.png"> </a> </div>
                  <div class="qq_rank_info">
                    <p> <strong>额外积分</strong> </p>
                    <p class="c_tx4"> <a target="_blank" href="#">查看详情</a> </p>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="qq_user_entry">
        <a target="_blank" href="#">
<img  src="${ctx }/images/vip/za.jpg">
</a>
        </div>
        <div class="v_mod_wrap2">
        <img  src="${ctx }/images/vip/pri_ewm.png"/>
        </div>
      </div>
      <!--sidebar end--> 
    </div>
  </div>
  <!--grid_c2f end--> 
  
  <!--vip_content end--> 