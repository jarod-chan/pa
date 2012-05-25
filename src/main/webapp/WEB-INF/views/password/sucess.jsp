<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set target="${pagefunc}" property="name" value="密码修改" />
<c:set target="${pagefunc}" property="url" value="#" />  

<c:set var="pagesize" value="400" scope="request"/> 
<body>
<%@ include file="../common/message.jsp"%>
<input type="button" value="点此重新登录系统" onclick="javascript:window.open('/${ctx}/login','_self')"/>
</body>
</html>
