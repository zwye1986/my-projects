<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no">
<script src="${ctx}/js/jquery/jquery-1.6.4.js" type="text/javascript"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js"type="text/javascript" charset="utf-8"></script>
<title>蛙宝网预计结算明细</title>
<style>
body{ background-color:#}
.sign a{ width:35%; height:270px; display:inline-block; float:left; }
.sign_ed  a{ width:35%; height:270px; float:left;}
.sign img,.sign_ed img{ width:100%; height:70%}
.sign_total{ width:65%; height:270px;   float:left; background:url(../images/weixin/sign_r_bg.png) no-repeat; position:relative;}
.person{ position:absolute;width:100%; height:74px; top:80px; left:65px;}
.person span{ width:100%; height:50px; line-height:50px; font-size:14px; font-weight:bold; display:block; margin-bottom:20px;}
 em{ font-style:normal; font-size:20px;   color: #349CD8;}
 table {
    border-collapse: collapse;
    border-spacing: 0;
}
.assets-table {
    border: 1px solid #DCE1E3;
    font-size: 12px;
    width: 100%;
}
.assets-table th, .assets-table td {
    height: 40px;
    text-align: center;
    vertical-align: middle;
}
.ast-indent th, .ast-indent td {
    text-indent: 30px;
	 border-bottom: 1px solid #DCE1E3;
}
.assets-table th {
    background:  #F0F4F7;
    border-bottom: 1px solid #DCE1E3;
}
</style> 
</head>

<body>
<section class="formbox">
    <img alt="" width="100%" height="200"  src="${ctx}/images/weixinsub.png">
	<div>
 		<table class="assets-table mr20">
                <tbody>
                  <tr>
                    <th>结算日期</th>
                    <th>结算金额</th>
                  </tr>
                  <tr>
                  <c:choose>
                    <c:when test="${not empty list  }">
						<c:forEach items="${list }" var="item">
                           <tr>  <td>${item.invalidate } </td> <td>${item.reward}纳币</td></tr>
                        </c:forEach>
					</c:when>
					 <c:otherwise> <tr> <td colspan="2">您当前尚未领取任务！</td></tr></c:otherwise>
				</c:choose> 
                  </tr>
                </tbody>
              </table>
 </div>
	
    </div> 
</section>
</body>
</html>

