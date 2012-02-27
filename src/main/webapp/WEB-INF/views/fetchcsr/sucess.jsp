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

<br>
<c:if test="${msg!=null}">
	<font id="msg" style="color:red;" >${msg}</font>
</c:if>
${username},你好！<br>
你的用户密码已经发送到你的公司邮箱<font style="color:red;" >${email}</font>中。<br>
请取回后，重新登录系统！<br>
<input type="button" value="重新登录" onclick="javascript:window.open('/${ctx}','_self')"/>
</body>
</html>
