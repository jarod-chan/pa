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
<h3>经理${currPerson.name}对${currPerson.department}人员的考核</h3>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<div>
  <input type="button" value="退出" " onclick="javascript:window.open('../','_self')"/>
</div>

<table border=1>
<tr>
	<td>序号</td><td>员工</td><td>得分</td><td>考评</td>
</tr>
<c:forEach items="${tabData}" var="item"  varStatus="stauts">
	<tr>
	<td>${stauts.count} </td>
	<td>${item.person.name} </td>
	<td>${item.getPoint}</td>
	<td><input type="button" value="考评" " onclick="javascript:window.open('../mangechk/list?mangeId=${currPerson.id}&personId=${item.person.id}','_self')"/></td>
	</tr>
</c:forEach>
</table>

</body>
</html>