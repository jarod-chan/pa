<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<%@ include file="../common/head.jsp"%>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>  

</head>
<body>
<h1>密码修改</h1>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<form action="/${ctx}/person/${person.id}/initpassword" method="post">
用户名：<br/>
新密码：
<input   name= "chkstr"   type= "password"   style="width:200px"> 
<br/>
重复新密码：
<input   name= "confirmchkstr"   type= "password"    style="width:200px"> 
<br/>


<input type="submit" value="保存"/> 
<input type="button" value="退出" onclick="javascript:window.open('/${ctx}','_self')"/>
</form>
</body>
</html>
