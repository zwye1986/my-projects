<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/vip_privilege.css" rel="stylesheet" />
<link href="${ctx }/css/game_privilege.css" rel="stylesheet" />
<link href="${ctx }/css/page.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/js/game.js"></script>
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

function closeAdv(){
	$(".grid_c1_close").hide();
}
</script>
<!--选项卡2-->
<script language="JavaScript">
function setTab(name,cursel,n){
	for(i=11;i<=n;i++){
	var menu=document.getElementById(name+i);
	var con=document.getElementById("conn_"+name+"_"+i);
	menu.className=i==cursel?"hover":"";
	con.style.display=i==cursel?"block":"none";
	
	 }
	//选项卡切换
	if(cursel==11){
		$("#sort").val("");
	}else if(cursel==12){
		$("#sort").val("reward");
	}else if(cursel==13){
		$("#sort").val("clicks");
	}else if(cursel==14){
		$("#sort").val("deposit");
	}
	gamePaginationForVip("all", 1,"",""); 
	//结束切换
	}
</script>

 <!--vip_content start-->
  <div class="vip_content"> 
    <!--grid_c2f start-->
    <div class="grid_c1">
      <div class="grid_c1_close"><img src="${ctx }/images/vip/grid_c1_close.jpg"/>
        <div class="close_btn"><a style="cursor: pointer;" onclick="closeAdv();" ><img src="${ctx }/images/vip/close_btn.png"/></a></div>
      </div>
      <div class="game_mod_tab"> 
        <!--选项卡开始-->
        <div class="tab_box">
          <div class="lyz_tab_left">
            <div class="pro_con111">
              <ul >
                <li class="hover" id="one1" onclick="setTab2(&#39;one&#39;,1,6)">推荐</li>
                <li id="one2" onclick="setTab2(&#39;one&#39;,2,6)">全部</li>
                <li id="one3" onclick="setTab2(&#39;one&#39;,3,6)">角色扮演</li>
                <li id="one4" onclick="setTab2(&#39;one&#39;,4,6)">战争策略</li>
                <li id="one5" onclick="setTab2(&#39;one&#39;,5,6)">休闲竞技</li>
                <li id="one6" onclick="setTab2(&#39;one&#39;,6,6)">模拟经营</li>
              </ul>
            </div>
          </div>
          <div class="lyz_tab_right">
           
            <div class="hover" id="con_one_1">
              <ul class="clearfix">
              <c:forEach items="${randomlist }" var="item" varStatus="s">
               <li> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html"> 
              <c:choose>
              <c:when test="${s.index%2==0 }">
              <span class="c_tx1">
              </c:when>
              <c:otherwise>
               <span class="c_tx5">
              </c:otherwise>
              </c:choose>
            
               ${item.gameName }</span> </a> </li>
              </c:forEach>
              </ul>
            </div>
            <div class="hover" id="con_one_2" style="display: none">
               <c:forEach items="${alllist }" var="item" varStatus="s">
               <li> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html"> 
              <c:choose>
              <c:when test="${s.index%2==0 }">
              <span class="c_tx1">
              </c:when>
              <c:otherwise>
               <span class="c_tx5">
              </c:otherwise>
              </c:choose>
            
               ${item.gameName }</span> </a> </li>
              </c:forEach>
            </div>
            <div class="hover" id="con_one_3" style="display: none">
                <c:forEach items="${rolelist }" var="item" varStatus="s">
               <li> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html"> 
              <c:choose>
              <c:when test="${s.index%2==0 }">
              <span class="c_tx1">
              </c:when>
              <c:otherwise>
               <span class="c_tx5">
              </c:otherwise>
              </c:choose>
            
               ${item.gameName }</span> </a> </li>
              </c:forEach>
            </div>
            <div class="hover" id="con_one_4" style="display: none">
               <c:forEach items="${warlist }" var="item" varStatus="s">
               <li> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html"> 
              <c:choose>
              <c:when test="${s.index%2==0 }">
              <span class="c_tx1">
              </c:when>
              <c:otherwise>
               <span class="c_tx5">
              </c:otherwise>
              </c:choose>
            
               ${item.gameName }</span> </a> </li>
              </c:forEach>
            </div>
            <div class="hover" id="con_one_5" style="display: none">
               <c:forEach items="${leisuretournamentlist }" var="item" varStatus="s">
               <li> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html"> 
              <c:choose>
              <c:when test="${s.index%2==0 }">
              <span class="c_tx1">
              </c:when>
              <c:otherwise>
               <span class="c_tx5">
              </c:otherwise>
              </c:choose>
            
               ${item.gameName }</span> </a> </li>
              </c:forEach>
            </div>
            <div class="hover" id="con_one_6" style="display: none">
                <c:forEach items="${businesssimulationlist }" var="item" varStatus="s">
               <li> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html"> 
              <c:choose>
              <c:when test="${s.index%2==0 }">
              <span class="c_tx1">
              </c:when>
              <c:otherwise>
               <span class="c_tx5">
              </c:otherwise>
              </c:choose>
            
               ${item.gameName }</span> </a> </li>
              </c:forEach>
            </div>
          </div>
          <div class="clear"></div>
        </div>
      </div>
      <!--选项卡结束--> 
    </div>
  </div>
  <!--grid_c2f end--> 
  <!--grid_c2f start-->
  <div class="grid_c2f"> 
    <!--mainbar start-->
    <div class="mainbar"  style="width:995px; float:left; display:inline-block;">
      <div class="mainbar_wrap"  style="float:left;">
        <div class="v_mod_panel_em_con clearfix">
          <div class="game_privilege">
            <div class="game_privilege_hd">
              <h3 >蛙宝特权</h3>
            </div>
            <div class="game_privilege_bd">
              <p>VIP会员可享众多VIP特权，参与精彩活动！蛙宝会员有什么？不止</p>
              <div class="game_privilege_control"  onclick="javascript:window.location.href='${ctx }/user/4/manager'" style="cursor: pointer;">
              
              </div>
              <p>这么简单！</p>
            </div>
            <div class="privilege_recommend_content" style="margin-top:80px;">
              <p > <a style="color:#FD6B6F;display:inline-block; float:left;" >【月费${month_cost }元】</a>
                <a> <input type="checkbox" checked="checked" class="recommend_check" />
                到期自动续费</a></p>
            </div>
          </div>
          <div class="v_mod_slider">
            <div id="slider1" class="slider">
              <div class="conbox">
                <div><a href="${ctx }/17a53edd-50a3-4b58-8890-d5097d01b47a/showGameDetail.html" title="QQ三国" target="_blank"><img width="730" height="260" alt="jquery" src="${ctx }/images/vip/pic04.jpg"></a></div>
                <div><a href="${ctx }/26dd00ee-3b6a-4e47-a54f-4ed1557819dd/showGameDetail.html" title="灵游记" target="_blank"><img width="730" height="260" alt="jquery" src="${ctx }/images/vip/pic05.jpg"></a></div>
                <div><a href="${ctx }/d507cc05-f2f2-4cbb-b894-fde24c264bf8/showGameDetail.html" title="侠义水浒传" target="_blank"><img width="730" height="260" alt="jquery" src="${ctx }/images/vip/pic06.jpg"></a></div>
              </div>
              <div class="switcher" style="position: absolute;bottom: 5px;right: 10px;
	float: right;z-index: 99;"> <a href="javascript:void(0)" class="cur"></a> <a href="javascript:void(0)"></a> <a href="javascript:void(0)"></a></div>
            </div>
          </div>
        </div>
        <div class="v_mod_wrap3">
          <div class="v_mod_wrap3_hd">
            <h3 class="v_mod_wrap3_game_tit"><a href="javascript:void(0)">会员游戏</a></h3>
          </div>
          <div class="v_mod_wrap3_c"  style="padding-left:0px;">
            <div class="hy_game_mod_tq">
              <div class="hy_game_mod_tq_fl fl">
                <div class="hy_game_mod_top fl">
                  <div id="slider" class="slider" style="height:300px;">
                    <div class="conbox">
                      <div><a href="${ctx }/090da3d6-a808-4144-8ac7-9cd5846dcf87/showGameDetail.html" title="大侠传" target="_blank"><img width="245" height="300" alt="jquery" src="${ctx}/images/vip/pic07.jpg"></a></div>
                      <div><a href="${ctx }/376ff761-a863-40df-b16f-d91a2f455194/showGameDetail.html" title="蜀山传"><img width="245" height="300" alt="jquery" src="${ctx }/images/vip/pic08.jpg"></a></div>
                      <div><a href="${ctx }/032c904f-6261-4d67-8936-809a98ac8623/showGameDetail.html" title="魔兽争霸"><img width="245" height="300" alt="jquery" src="${ctx }/images/vip/pic09.jpg"></a></div>
                    </div>
                    <div class="switcher" style="bottom: 5px;float: right;position: absolute;right:500px;z-index: 99;"> <a href="javascript:void(0)" class="cur"></a> <a href="javascript:void(0)"></a> <a href="javascript:void(0)"></a></div>
                  </div>
                </div>
                <div class="hy_game_mod_bottom fl">
                  <ul class="game_mod_newslist">
                  <c:forEach items="${looklist }" var="item">
                  
                  <li>
                      <p class="tit"> <span class="hy_game_list_l c_tx7">[${item.typeName }]</span> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html" class="hy_game_list">${item.gameName }</a> </p>
                      <a class="more" target="_blank" href="${ctx }/${item.id}/showGameDetail.html">详情>></a> 
                      </li>
                  </c:forEach>
                  </ul>
                </div>
              </div>
              <div class="hy_game_mod_tq_fr fr"> 
                <!--tab start-->
                <div id="Tab1">
                  <div class="Menubox">
                    <ul>
                      <li id="one11" onclick="setTab('one',11,14)"  class="hover">全部</li>
                      <li id="one12" onclick="setTab('one',12,14)" >按返利</li>
                      <li id="one13" onclick="setTab('one',13,14)">按周期</li>
                      <li id="one14" onclick="setTab('one',14,14)">按押金</li>
                    </ul>
                  </div>
                  <div class="Contentbox">
                  <input type="hidden" name="sort" id="sort" value="${sort }">
                    <div id="conn_one_11" class="hover">
                      <ul>
                      <c:forEach items="${gamelist }" var="item" varStatus="s">
                       <li>
                          <dl>
                          </dl>
                          <dt><span><img src="${ctx }/images/vip/v01_icon.png"/></span></span><img  width="210" height="129" src="${ctx }/showRectangleGamePic?id=${item.rectangle}" onerror="this.src='${ctx }/images/game/rectangle.png'"/></dt>
                          <dd class="game_name">${item.gameName }</dd>
                          <dd class="game_fl">返利:${item.reward}纳币</dd>
                          <dd class="game_yj">押金:${item.deposit}纳币</dd>
                          <dd class="game_zq">周期:${item.clicks}次</dd>
                          <dd class="game_lq"><a href="${ctx }/${item.id}/showGameDetail.html" target="_blank">领游戏</a></dd>
                        </li>
                      </c:forEach>
                       
                      </ul>
                      <div class="page">
                      <div id="page_turner" class="qz_page_nav">
							<div class="mod_pagenav">
								<p class="mod_pagenav_main">
									<span class="mod_pagenav_disable"> <span>上一页</span>
									</span>
									<c:if test="${ gamesPage <= 4}">
										<c:forEach begin="1" end="${ gamesPage}" step="1" var="item">
											<c:if test="${item == 1 }">
												<span class="current"> <span>1</span>
												</span>
											</c:if>
											<c:if test="${item != 1 }">
												<a id="pager_num_0_2" class="c_tx" title="${item }"
													onclick="gamePaginationForVip('all',${item},'${searchtype }','${keyword }')"
													href="javascript:void(0);"> <span>${item }</span>
												</a>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${ gamesPage > 4}">
										<span class="current"> <span>1</span>
										</span>
										<a id="pager_num_0_2" class="c_tx" title="2"
											onclick="gamePaginationForVip('all',2,'${searchtype }','${keyword }')" href="javascript:void(0);">
											<span>2</span>
										</a>
										<a id="pager_num_0_3" class="c_tx" title="3"
											onclick="gamePaginationForVip('all',3,'${searchtype }','${keyword }')" href="javascript:void(0);">
											<span>3</span>
										</a>
										<span class="mod_pagenav_more"> <span>…</span>
										</span>
										<a id="pager_last_0" class="c_tx" title="${gamesPage }"
											onclick="gamePaginationForVip('all',${gamesPage },'${searchtype }','${keyword }')"
											href="javascript:void(0);"> <span>${gamesPage }</span>
										</a>
									</c:if>


									<a id="pager_next_0" class="c_tx" title="下一页"
										onclick="gamePaginationNextForVip('all',1,'${searchtype }','${keyword }')" href="javascript:void(0);"> <span>下一页</span>
									</a>
								</p>
							</div>
						</div>
						</div>
                    </div>
                    <div id="conn_one_12" style="display:none">
                      
                    </div>
                    <div id="conn_one_13" style="display:none">
                      
                    </div>
                    <div id="conn_one_14" style="display:none">
                      
                    </div>
                  </div>
                </div>
                
                <!--tab end--> 
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--mainbar end--> 
    <!--sidebar start-->
    <div class="sidebar"  style="display:inline-block; margin-top:20px; float:right;">
      <div class="mod_userinfo_gb">
        <div  class="mod_userinfo_login" style="margin-top:0px;">
          <div class="mod_loginBox  " >
          <sec:authentication property="principal" var="currentUsr"
					scope="page" />
				<c:if
					test="${currentUsr eq '' or currentUsr eq null or currentUsr eq 'anonymousUser'}">
						<a class="mod_userinfo_login_entry " title="用户登录" href="${ctx }/login.html" style="margin-top: 0px;margin-left: 0px"></a>
				</c:if>

				<sec:authorize access="isAuthenticated()">
					<div class="profile_basic_info clearfix" >
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
				</sec:authorize>
          </div>
          <div class="mod_loginBox_title">最新特权</div>
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
            <li>
              <dl>
                <dt><img src="${ctx }/images/vip/pri_icon03.png"></dt>
                <dd>等级个性符号</dd>
              </dl>
            </li>
            <li>
              <dl>
                <dt><img src="${ctx }/images/vip/pri_icon04.png"></dt>
                <dd>短信提醒免费接收</dd>
              </dl>
            </li>
            <li>
              <dl>
                <dt><img src="${ctx }/images/vip/pri_icon05.png"></dt>
                <dd>无需点击游戏</dd>
              </dl>
            </li>
          </ul>
          <div class="v_mod_userinfo_slogan">
            <p> <span class="c_tx3"><a href="${ctx }/all_pri_vip.html" target="_blank" style="color:#FD6B6F; text-decoration:underline;">查看全部会员特权>></a></span> </p>
          </div>
        </div>
      </div>
      <div class="v_mod_wrap2">
        <div class="v_mod_wrap2_hd" style="margin-top:50px;">
          <h3 class="f_tx5">最热VIP游戏</h3>
        </div>
        <div class="v_mod_wrap2_bd">
          <div class="qq_rank">
            <ul class="qq_rank_list clearfix">
            <c:forEach items="${hotlist }" var="item">
             <li class="qq_rank_item qq_rank_item_1">
                <div class="qq_rank_order"> <span><img src="${ctx }/images/vip/v01.png"/></span> </div>
                <div class="qq_rank_photo"> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html"> <img alt="${item.gameName }" width="90" height="90" src="${ctx }/showSquareGamePic?id=${item.square}" onerror="this.src='${ctx }/images/game/square.png'"> </a> </div>
                <div class="qq_rank_info">
                  <p> <strong>${item.gameName }</strong> </p>
                  <p ><a target="_blank" href="javascript:void(0)" class="c_tx6">${item.players }人</a>也在玩 </p>
                  <p class="c_tx4"> <a target="_blank" href="${ctx }/${item.id}/showGameDetail.html">去加入</a> </p>
                </div>
              </li>
            </c:forEach>
            
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!--sidebar end--> 
  </div>
</div>
<!--grid_c2f end--> 

<!--vip_content end--> 