<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(function(){
	$("#back").click(function(){
		  window.location.href="${ctx}/user/feedbackList.html";
	});	
});

</script>
<div id="main">
   
   <div class="main-inner">
    <div class="title-items">
      <h2><em style="color: #3C3C3C; margin-left:5px;">蛙宝网意见反馈</em></h2>
    </div>
    <div class="bid-info" style=" clear:both;">
    <h2 class="sucess-title"><i class="success—icon icons"></i>提交成功！</h2>
                <ul class="items" style="padding-bottom:40px; margin-left:170px">
                  <li>尊敬的用户，您反馈的问题已经提交，我们将会在2个工作日内处理。</li>
                  <li>请耐心等待。</li>
                  <li>感谢您对蛙宝网的支持！</li>
                </ul>
                <div class="save-button" style="margin-left:40px;">
                  <input type="button" value="确定" id="back" name="确定">
                </div>
              </div>
   </div>
  </div>