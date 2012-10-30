<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
</head>
<body>

<div style="margin-left:20px;margin-top: 150px;">
<h2>请按格式输入满意度调查认证码：<span style="color:red">[请按顺序输入8位字符]</span></h2>
<%@ include file="common/message_nohide.jsp"%>
<br>
<form action="/${ctx}/qs/login" method="post">
认证码：<input type="text" name="key" size="20" value="${keyBean.key}" />&nbsp;&nbsp;<input type="submit" value="确认" />
<br>
</form>
</div>

</body>
</html>
