<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set var="pagefunc" value="用户账户设置" scope="request"/> 
<c:set var="pagetitle" value="密码修改" scope="request"/> 
<c:set var="pagesize" value="400" scope="request"/> 
<body>
<%@ include file="../common/message.jsp"%>
<input type="button" value="点此重新登录系统" onclick="javascript:window.open('/${ctx}/login','_self')"/>
</body>
</html>
