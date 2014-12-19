<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
 
</head>
<body>

<div style="margin-left:20px;margin-top: 150px;">
<h2>输入你的真实姓名，密码将自动发送到你的公司邮箱<font style="color:red;" >[登录系统后可以重新修改密码]</font></h2>
<br>
<%@ include file="../common/message.jsp"%>
<form action="/${ctx}/fetchcsr" method="post">
用户名：<input type="text" name="username" value="${usernameBean.username}" />
<br>
<br>
<input type="submit" value="取回密码" />
<input type="button" value="重新登录" onclick="javascript:window.open('/${ctx}','_self')"/>
<br>
</form>
</div>

</body>
</html>
