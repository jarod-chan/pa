<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<style type="text/css">
.center{
margin-left:auto;
margin-right:auto;
text-align:center;
} 
</style>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>  

</head>
<body>

<div class="center"><h1>方远房产2011年度横向考核系统</h1></div>
<div class="center">
<form action="login" method="post">
<img src="resources/img/logo.jpg">
<br/>
用户：<input type="text" name="name"  value="${username}"/> 
<br/>

密码：<input type="password" name="chkstr" value="${password}" />
 <br/>
 <c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:200px;margin-left:auto;margin-right:auto;">${msg}</div>
 </c:if>
 <input type="submit" value="登录"/>
</form>
</div> 
</body>
</html>
