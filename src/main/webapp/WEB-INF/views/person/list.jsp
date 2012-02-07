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
<h1>所有员工</h1>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>
<form id="form" action="/pa/person/password" method="post">
密码长度：
<input type="text" name="passlen" value="3"/>
<input type="hidden" id="type" name="type" value="init"/>
<input type="button" value="新增" onclick="javascript:window.open('person/new','_self');"/> 
<input type="submit" value="初始密码"/>
<input type="button" value="重置密码"  onclick="javascript:$('#type').val('reset');$('#form').submit();"/>
<input type="button" value="退出"  onclick="javascript:window.open('/pa/admin/all','_self')"/>
</form>
<table border=1>
<tr>
	<td>id</td><td>用户名</td><td>密码</td><td>部门</td><td>是否经理</td><td>职能部室</td><td>操作</td>
</tr>
<c:forEach var="person" items="${persons}" >
	<tr>
	<td>${person.id}</td> <td>${person.name}  </td><td>${person.chkstr}</td><td>${person.department}</td><td>${person.manage.name}</td><td>${person.type.name}</td>
	<td>
	<input type="button" value="修改" onclick="javascript:window.open('person/${person.id}','_self')"/>
	<input type="button" value="删除" onclick="  if(confirm('确定删除？')){
												 $('<form/>',{action:'person/${person.id}',method:'post'})
												.append($('<input/>',{type:'hidden',name:'_method',value:'delete'})) 
												.submit();
												} "/>
	</td>
	</tr>
</c:forEach>
</table>
</body>
</html>
