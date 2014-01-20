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
<style type="text/css">
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style> 
</head>
<body>
<h1>员工修改</h1>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<form:form method="put" commandName="person" action="/${ctx}/admin/person/${person.id}">
用户名：
<form:input path="name" />
<form:errors path="name" cssClass="error" /><br/>
密码：
<form:input path="chkstr" />
<form:errors path="chkstr" cssClass="error" /><br/>
职务类型：
<form:select path="type" items="${typeEnum}"></form:select><br/>
部门：
<form:input path="department" /><br/>
是否经理：
<form:select path="manage" items="${manageEnum}"></form:select><br/>

<input type="submit" value="保存"/>
<input type="button" value="返回" onclick="javascript:window.open('/${ctx}/admin/person','_self')"/>
</form:form>
</body>
</html>
