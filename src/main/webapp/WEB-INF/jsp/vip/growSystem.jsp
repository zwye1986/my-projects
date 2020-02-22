<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yma" uri="/yma"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<link href="${ctx }/css/grow_system.css" rel="stylesheet" />



   <!--vip_content start-->
  <div class="vip_content"> 
    <!--grid_c2f start-->
    <div class="grid_c2f">
      <div class="gr_mod_box2">
        <div class="gr_sys_title"> 等级体系 </div>
        <div class="gr_sys_c"></div>
        <ul class="gr_sys_b">
          <li>等级有1到100级；</li>
          <li>用户积分主要是通过领取游戏获取，即1元换算为10积分；</li>
          <li>用户每天登录获得10积分，开启会员中心的用户将获得额外5积分！</li>
        </ul>
      </div>
      <div class="gr_mod_box2 gr_value_about">
        <div class="mod_btn_common_sub sys_kthy fr"><a href="${ctx}/user/4/manager" stag="xufei">开通会员</a></div>
      </div>
      <div class="gr_mod_box2 hy_jf">
        <div class="sys_hy_list">
          <div class="sys_hy_list_title">
            <h3>会员积分列表</h3>
          </div>
          <div class="sys_hy_list_l fl">
            <div class="sys_hy_list_ll fl">
              <table width="100%" cellpadding="0" border="0" align="center" cellspacing="0">
                <tbody>
                  <tr bgcolor="fee998" align="center" height="40">
                    <th>等级</th>
                    <th>积分数</th>
                  </tr>
                  <tr>
                    <td>1</td>
                    <td>1-100</td>
                  </tr>
                  <tr>
                    <td>2</td>
                    <td>101-800</td>
                  </tr>
                  <tr>
                    <td>3</td>
                    <td>801-2700</td>
                  </tr>
                  <tr>
                    <td>4</td>
                    <td>2701-6400</td>
                  </tr>
                  <tr>
                    <td>5</td>
                    <td>6401-12500</td>
                  </tr>
                  <tr>
                    <td>6</td>
                    <td>12501-19600</td>
                  </tr>
                  <tr>
                    <td>7</td>
                    <td>19601-34300</td>
                  </tr>
                  <tr>
                    <td>8</td>
                    <td>34301-51200</td>
                  </tr>
                  <tr>
                    <td>9</td>
                    <td>51201-72900</td>
                  </tr>
                  <tr>
                    <td>10</td>
                    <td>72901-100000</td>
                  </tr>
                  <tr>
                    <td>11</td>
                    <td>100001-133100</td>
                  </tr>
                  <tr>
                    <td>12</td>
                    <td>133101-172800</td>
                  </tr>
                  <tr>
                    <td>13</td>
                    <td>172801-219700</td>
                  </tr>
                  <tr>
                    <td>14</td>
                    <td>219701-274400</td>
                  </tr>
                  <tr>
                    <td>15</td>
                    <td>274401-337500</td>
                  </tr>
                  <tr>
                    <td>16</td>
                    <td>337501-409600</td>
                  </tr>
                  <tr>
                    <td>N</td>
                    <td>(N-1)^3*100+1-N^3*100</td>
                  </tr>
                  <tr>
                    <td>99</td>
                    <td>94119201-97029900</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>97029901-1000000000</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <div class="sys_hy_list_lr fr">
              <div class="level_rule">
                <h3>等级规则：</h3>
                <div class="rule_icon fr"></div>
                <ul class="level_rule_list">
                  <li>等级有 <em class="rule_txt1">1 </em>到<em class="rule_txt1"> 100</em> 级；</li>
                  <li>用户积分主要是通过<em class="rule_txt2">领取游戏获取</em>，即 <em class="rule_txt1">1</em> 元换算为 <em class="rule_txt1">10</em> 积分;</li>
                  <li>用户<em class="rule_txt2">每天登录</em>获得 <em class="rule_txt1">10</em> 积分，<em class="rule_txt2">开启会员中心</em>的用户将获得额外 <em class="rule_txt1">5 </em>积分！</li>
                  <li>用户可以在积分商城通过<a style="color:#ffb108; text-decoration:underline;">积分去兑换相应的商品</a>。</li>
                </ul>
                <h3 style="margin-top:20px;">个性符号：</h3>
                <div class="rule_icon fr"></div>
                <div class="level_icon"></div>
                <h3 style=" height:20px;line-height:120px; overflow:hidden;">用户图标：</h3>
                <div class="rule_icon fr"></div>
                <div class="sys_user_icon">
                  <div class="sys_user_icon_n"> <span>［普通用户]</span> </div>
                  <div class="sys_user_icon_v"> <span>［VIP用户］</span> </div>
                </div>
              </div>
            </div>
          </div>
          <div class="sys_hy_list_r fr">
            <ul>
              <li>
                <dl>
                  <dt><img width="255" height="189" src="images/vip/grow_login_jf.png"/></dt>
                  <dd>成长奖励：<em class="rule_txt1">10</em>积分</dd>
                </dl>
              </li>
              <li>
                <dl>
                  <dt><img width="255" height="189" src="images/vip/grow_hy_jf.png"/></dt>
                  <dd>成长奖励：<em class="rule_txt1">5</em>积分</dd>
                </dl>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!--grid_c2f end--> 
  </div>
  <!--vip_content end--> 