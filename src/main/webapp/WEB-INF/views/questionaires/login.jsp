<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
</head>
<body>

<div style="margin-left:20px;margin-top: 150px;">
<h2>请按格式输入满意度调查认证码：</h2>
<%@ include file="common/message_nohide.jsp"%>
<br>
<form action="/${ctx}/qs/login" method="post">
认证码：<input type="text" name="keypart1" size="10" value="${keyBean.keypart1}" />&nbsp;-&nbsp;
	   <input type="text" name="keypart2" size="10" value="${keyBean.keypart2}" />
<br>
<br>
<div style="text-align: right; width: 450px;">
	<input type="submit" value="确认" />
</div>
<br>
</form>
</div>

</body>
</html>
