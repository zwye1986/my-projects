<%--
  Created by IntelliJ IDEA.
  User: zhangwenyun
  Date: 9/11/14
  Time: 2:31 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" scope="request"/>

<tiles:insertAttribute name="Header" />
<link href="${ctx}/css/level.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/css/new-global-min.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/css/account.css" type="text/css" rel="stylesheet"/>
<div id="wrapper">
        <tiles:insertAttribute name="Sidebar" />
        <tiles:insertAttribute name="Content" />
</div>
<tiles:insertAttribute name="Footer" />



