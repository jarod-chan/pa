<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>

<body>
<h2>账户设置-密码修改</h2>
<%@ include file="../common/message.jsp"%>
<form action="/${ctx}/common/settings/person/${person.id}/password?backurl=${backurl}" method="post">
用户名：${person.name}<br/>
原密码：<br/>
<input name="oldcsr" type="password" /><br/>
新密码：<br/>
<input name="newcsr" type="password" /><br/>
重复新密码：<br/>
<input name="confirmcsr" type="password" /><br/>
<input type="submit" value="重置密码"/>
<input type="button" value="<<返回" onclick="javascript:window.open('${backurl}','_self')"/>
</form>



</body>
</html>
