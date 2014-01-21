<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set target="${pagefunc}" property="name" value="密码修改" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/password" />  


<c:set var="pagesize" value="400" scope="request"/> 
<body>
<div class="headdiv" >
<div class="headleft" ></div>
<div class="headright">
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../common/message.jsp"%>
<form action="/${ctx}/password" method="post">
用户名：${person.name}<br/>
原密码：<br/>
<input name="oldcsr" type="password" /><br/>
新密码：<br/>
<input name="newcsr" type="password" /><br/>
重复新密码：<br/>
<input name="confirmcsr" type="password" /><br/>
<br/>
<input type="submit" value="重置密码"/>
</form>



</body>
</html>
