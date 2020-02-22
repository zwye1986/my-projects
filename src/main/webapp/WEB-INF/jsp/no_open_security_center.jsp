<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery/jquery.fancybox-1.3.4.js"></script>
<link href="${ctx }/css/fancy/jquery.fancybox-1.3.4.css" rel="stylesheet" />
<link href="${ctx }/css/user_hy_enter.css" rel="stylesheet" />
<script type="text/javascript">

$(document).ready(function(){
	
	 var linkList = window.parent.document.getElementsByTagName("link"); //获取父窗口link标签对象列表
     var head = document.getElementsByTagName("head").item(0);
     //外联样式
     for (var i = 0; i < linkList.length; i++) {
         var l = document.createElement("link");
         l.rel = 'stylesheet'
         l.type = 'text/css';
         l.href = linkList[i].href;
         head.appendChild(l);
     }
     
     $("#open_security").click(function(){
    	 top.jQuery.alerts.confirm('确定开启会员中心吗？', '提示', function(r){
    		 if(r){
    				var isAutoRenew = $("input[name='isAutoRenew']:checked").val() || 1;
    		 		$.ajax({
    		 		   type: "POST",
    		 		   async: false,
    		 		   cache: false,
    		 		   dataType: "json",
    		 		   data:{isAutoRenew:isAutoRenew},
    		 		   url: "${ctx }/user/security/center/open",
    		 		   success:function(data){
    		 			  if(data.resCode == 0){
    		 			  	 $('#mainForm', parent.document).submit();
    		 			   }else{
    		 				   top.$.alerts.alert(data.resMsg);
    		 			   }
    		 		   }
    		 		});
    		 }
    	 });
 	});
});
//-->
</script>
 <div class="user_hy_enter">
                    <div class="hy_top"></div>
                    <div class="hy_banner"></div>
                    <ul class="hy_list">
                    <li class="hy_txt">会员中心能够使您拥有更多特权；您的账户资金安全保障更上一层！</li>
                    <li class="hy_btn" id="open_security"></li>
                    <li ><a style="color:#fff;">【月费${month_cost }元】</a> <a style="color:#fff;" target="_blank">
                      &nbsp;&nbsp;<input type="checkbox" value="0" name="isAutoRenew" checked="checked" id = "isAutoRenew" />到期自动续费 </a></li>
                  </ul>
 </div>