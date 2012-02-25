<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>
 
</head>
<body>
输入用户名，密码将自动发送到你的公司邮箱<font style="color:red;" >[系统暂时不支持密码重置功能]</font>
<br>
<c:if test="${msg!=null}">
	<font id="msg" style="color:red;" >${msg}</font>
</c:if>
<form action="/${ctx}/fetchcsr" method="post">
用户名：<input type="text" name="username" />
<br>
<input type="submit" value="取回密码" />
</form>

</body>
</html>
