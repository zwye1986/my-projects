<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yma" uri="/yma"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	
</script>

<div id="main">
   <div class="re-sucess">
    <div class="re-sucess-l">
    <img width="140" height="180" src="${ctx }/images/common/ts_sucess.png"/>
   </div>
     <div class="re-sucess-r">
   <span class="re-sucess-ts">恭喜，您已充值成功！</span>
   <ul>
   <li>充值账号：${mobilenumber }</li>
   <li>充值金额：${money } 元</li>
   <li>您可能需要：<a class="btn-sucess" href="${ctx }/user/account/recharge">继续充值</a> <a class="btn-ball" href="${ctx }/usertaskDetail.html">查看帐户余额</a></li>
   </ul>
   </div>
   </div>
    
  </div>