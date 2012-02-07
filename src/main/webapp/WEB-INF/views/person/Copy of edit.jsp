<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>  
<body>
<h1>所有员工</h1>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>
<form id="form" action="/pa/person/${person.id}" method="post">
<input type="hidden" name="_method" value="put"/>
用户名：<input type="text" name="name" value="${person.name}"/> <br/>
密码：<input type="text" name="chkstr" value="${person.chkstr}"/> <br/>
职务类型：
<select name="type">
		<c:forEach var="type" items="${typeEnum}" >
			<option value="${type}" <c:if test="${person.type==type}">selected="true"</c:if> >${type.name}</option>
		</c:forEach>
</select> 
<br/>
部门：<input type="text" name="department" value="${person.department}"/> <br/>
是否经理：
<select name="manage">
		<c:forEach var="manage" items="${manageEnum}" >
			<option value="${manage}" <c:if test="${person.manage==manage}">selected="true"</c:if> >${manage.name}</option>
		</c:forEach>
</select> 
 <br/>

<input type="submit" value="保存"/>
<input type="button" value="返回" onclick="javascript:window.open('/pa/person','_self')"/>
</form>


<h2>spring form</h2>

<form:form method="post" commandName="person" action="/pa/person/${person.id}">
	<form:errors path="*" cssClass="errorblock" element="div" />
id：
<form:input path="id" /> <br/>
用户名：
<form:input path="name" /> <br/>
密码：
<form:input path="chkstr" /><br/>
职务类型：
<form:select path="type" items="${typeEnum}"></form:select><br/>
部门：
<form:input path="department" /><br/>
是否经理：
<select name="manage">
		<c:forEach var="manage" items="${manageEnum}" >
		<option value="${manage}">${manage.name}</option>
	</c:forEach>
</select> 
 <br/>

<input type="submit" value="保存"/>
<input type="button" value="返回" onclick="javascript:window.open('/pa/person','_self')"/>
</form:form>
</body>
</html>
