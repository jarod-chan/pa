<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
</head>
<body>
<div style="margin-left: 200px;margin-top: 20px;"><h2>${ques.name}</h2></div>
<c:choose>
<c:when test="${key.state=='nouse'}">
<%@ include file="begpart/start.jsp"%>
</c:when>

<c:when test="${key.state=='used'}">
<%@ include file="begpart/continue.jsp"%>
</c:when>

<c:when test="${key.state=='finish'}">
<%@ include file="begpart/restart.jsp"%>
</c:when>
</c:choose>

</body>
</html>
