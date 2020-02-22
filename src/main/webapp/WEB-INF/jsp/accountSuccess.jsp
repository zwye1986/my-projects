<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yma" uri="/yma"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${ctx }/css/sucess.css" />
<script type="text/javascript">
	
</script>


 <div class="contentR fr">
      <div class="RTitle"></div>
      <div class="recharge_ts">
      <div class="recharge_ts_c">
      <div class="recharge_ts_cl fl">
      <img  src="${ctx }/images/user/sucess.png"/>
      </div>
      <div class="recharge_ts_cr fl">
      <div class="recharge_cr_title"><img src="${ctx }/images/user/ts_sucess.png" />恭喜，充值成功！</div>
      <ul>
      <li>充值帐号：<em>${mobilenumber }</em></li>
      <li>充值金额：<em>${money }</em>元</li>
      <li>您可能需要:<a href="${ctx }/user/account/recharge">继续充值</a>｜<a href="${ctx }/usertaskDetail.html">查看帐户余额</a></li>
      </ul>
      
      </div> 
       
      </div>
      </div>
      <div class="RB"></div>
    </div>