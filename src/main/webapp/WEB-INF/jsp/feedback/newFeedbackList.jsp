<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<script type="text/javascript">
function gamePagination(page) {
	
	var param = {
		page : page
	};
	
	$.get("${ctx}/showList", param, function(data) {
			$("#main").html(data);
	});
}

$(function(){
	gamePagination(1);
});
</script>
<div id="main">
  <div class="model-box">
              <table class="assets-table mr20">
                <tbody>
                  <tr>
                    <th>姓名</th>
                    <th>联系方式</th>
                    <th>反馈内容</th>
                    <th>反馈时间</th>
                    <th>回复时间</th>
                    <th>回复内容</th>
                    <th>是否回复</th>
                  </tr>
                
                  
                </tbody>
              </table>
              <!--page start-->
              <div id="loanInvsetPager" class="yPager">
                <div class="inner"><span class="current">1</span> <a href="/">2</a> <a href="/">3</a> <a href="/">4</a> <span>...</span> <a href="/">52</a> </div>
              </div>
              <!--page end--> 
            </div> 
  </div>