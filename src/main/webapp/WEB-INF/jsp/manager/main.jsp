<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>蛙宝网后台管理系统</title>
</head>

<frameset rows="89,*,11" frameborder="no" border="0" framespacing="0">
  <frame src="${ctx }/manager/top" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
  <frame src="${ctx }/manager/center" name="mainFrame" id="mainFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes></html>
