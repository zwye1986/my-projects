<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request" />
<script type="text/javascript" src="${ctx }/js/jquery/jquery-1.6.4.js"></script>
<script>
window.onload =
    function () {
        var linkList = window.parent.document.getElementsByTagName("link"); //获取父窗口link标签对象列表
        var head = document.getElementsByTagName("head").item(0);
        //外联样式
        for (var i = 0; i < linkList.length; i++) {
            var l = document.createElement("link");
            l.rel = 'stylesheet';
            l.type = 'text/css';
            l.href = linkList[i].href;
            head.appendChild(l);
        }
};

function enterGame(a,b){
	window.open(b,"_target");	
}

function onPageSizeChange(value)
{
	document.getElementById("page.pageSize").value=value;
	document.all.queryListForm.submit();
}

//分页验证
function paginate(forward){
	if(forward)	{
		document.getElementById("page.currentPage").value=forward;
	}else{
		var temp=document.getElementById("forwardPage").value;		
		var temp=document.getElementById("forwardPage").value;		
 		if(temp=="")
 		{
 			top.jQuery.alerts.alert('输入的页数为空。','提示');
 			return false;
 		}
     	var reg = /^[0-9]\d*$/;
     	if(!reg.test(temp)) {
     		top.jQuery.alerts.alert('输入的页数必须为数字。','提示');
     		return false;
     	} 
     	if( temp < 1 || temp > ${page.totalPages})
     	{
     		top.jQuery.alerts.alert('输入的页数超过了显示的范围。','提示');
     		return false;
     	}
		document.getElementById("page.currentPage").value=document.getElementById("forwardPage").value;
	}
	document.all.queryListForm.submit();
}

</script>
<form id="queryListForm" name="queryListForm" target="_self" action="${ctx }/taskDetail" method="post">
	<input type="hidden" name="pageSize" id="page.pageSize" value="${page.pageSize }" />
	<input type="hidden" name="type"  value="${type }" />
	<input type="hidden" name="currentPage" id="page.currentPage" value="${page.currentPage }" />
<table class="table">
  <thead>
    <tr>
                        <th>时间</th>
                        <c:if test="${type == 'over' || type == 'record' }">
                         <th>名称</th>
                        </c:if>
                        <c:if test="${type != 'over' && type != 'record' }">
                         <th>操作</th>
                        </c:if>
                        <th>截止时间</th>
                         <th>押金</th>
                         <%-- 
                        <th>收入</th>
                      --%>
    </tr>
  </thead>
   <tbody id="tab">
   <c:set var="currentday">
<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd" type="date"/>
</c:set>
   <c:forEach items="${list }" var="item" varStatus="s">
    <tr>
                        <td width="20%"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td style="position:relative; text-align: left; padding-left:35px;" >
                        <c:if test="${item.status  == 1}">
                        
                        <c:set var="myday">
<fmt:formatDate value="${item.clickDate }" pattern="yyyy-MM-dd" type="date"/>
</c:set>
  <c:set var="invalidday">
<fmt:formatDate value="${item.invalidTime }" pattern="yyyy-MM-dd" type="date"/>
</c:set>


 <em style="width:30px; float:left;" ><img
					src="${ctx }/images/v.png" width="20" height="20" /></em>
					<em style="width:350px; float: left;"><a href="javascript:void(0)" onclick="enterGame('${item.id}','${item.gameUrl }')">进入游戏</a>

             
                    
                        
                        </c:if>
                       
                        <c:if test="${item.status  != 1  }">
                        <em style="width:30px; float:left;" ><img
					src="${ctx }/images/temp.png" width="20" height="20" /></em>
                        <a>进入游戏</a>
                        </c:if>
                        [${item.gameName }]<em style="color:#f16244;"></em><em style="color:#f16244; font-size:12px; position:absolute; margin-left:-85px; margin-top:16px; ">返利${item.reward }纳币</em></em></td>
                        <td><fmt:formatDate value="${item.invalidTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>${item.deposit} 纳币</td>
                        <%-- 
                        <c:choose>
                        <c:when test="${item.realReward >= 0 && item.punish == 0}">
                        
                          <td>${item.realReward} 纳币</td>
                        </c:when>
                        <c:otherwise>
                        <td>-${item.punish} 纳币</td>
                        </c:otherwise>
                        </c:choose>
                     --%>
                      
                      </tr>
   </c:forEach>
   		
    </tbody>
    
    <tfoot>
	    <tr>
	      <td colspan="8">
	      	 <%@include file="../PaginationMore.inc"%>
	   	  </td>
	    </tr>
	        <c:if test="${type != 'over' && type != 'record' }">
	      <tr>
	      <td colspan="8" align="left">
	      
	      	图示说明：<img
					src="${ctx }/images/v.png" width="20" height="20" />:当日任务已完成 <img
					src="${ctx }/images/x.png" width="20" height="20" />:当日任务未完成
	   	  </td>
	    </tr>
	   </c:if>
    </tfoot>
    
</table>
</form>
<form action="${ctx }/startGame" id="form" name="form" target="_blank" method="post">
<input type = "hidden" name="ids" id="ids" value="">
<input type = "hidden" name="gameUrl" id="gameUrl" value="">
</form>
  