<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Untitled Document</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	color:#333;
	background:#fafafb;
}

img { border:none;}
-->
</style>
<script language="javascript" type="text/javascript">
function logout() {
	window.parent.location.href="${ctx}/j_spring_security_logout";
}
</script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0"  style="border-bottom:1px solid #cfcfd0;">
  <tr>
    <td height="48"><table width="100%" border="0" cellspacing="0" cellpadding="0" height="50%">
        <tr>
          <td width="225" height="88" style="background: url(${ctx}/images/admin/wb_logo.png) no-repeat #3b3a3f;"></td>
          <td width="254" style="color:#5c5c5c ; font-size:24px; text-align:center;" >后台管理系统</td>
          <td width="*" >&nbsp;</td>
          <td width="230"><table width="100%" cellspacing="0" cellpadding="0" border="0">
              <tr >
                <td height="88" style="font-size:18px; color:#5c5c5c; border-right:1px solid #cfcfd0;">欢迎你！  管理员<label>[<sec:authentication
										property="principal.nickName" />]</label>  </td>
              </tr>
              
            </table></td>
          <td width="80"><table width="100%" cellspacing="0" cellpadding="0" border="0" style="text-align:center;">
              
              <tr>
                <td> <a href="javascript:void(0)" onclick="logout();return false;" ><img src="${ctx }/images/admin/quit.png" style="cursor:pointer;"/></a></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
