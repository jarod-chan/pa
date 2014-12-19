<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%> 
</head>
<body>

	<div style="margin-left:20px;margin-top: 150px;">
		<h2>${username},你好！</h2>
		<h2>你的用户密码已经发送到你的公司邮箱<font style="color:red;" >${email}</font>中。</h2>
		<h2>请取回后，重新登录系统。</h2>
		<br>
		<%@ include file="../common/message.jsp"%>
		<input type="button" value="重新登录" onclick="javascript:window.open('/${ctx}','_self')"/>
		<br>
	</div>
	
</body>
</html>
