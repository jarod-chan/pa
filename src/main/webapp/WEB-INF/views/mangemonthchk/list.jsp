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
<h3>经理${mange.name}对${mange.department}员工的月工作评价</h3>
<c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:300px">${msg}</div>
</c:if>

<input type="button" value="退出" " onclick="javascript:window.open('/${ctx}','_self')"/>
<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:500px;">工作完成情况表</th>
		<th style="width:150px;">员工</th>
		<th style="width:100px;">操作</th>
	</tr>
</thead>
<tbody>
<c:forEach var="item" items="${monthChks}"  varStatus="status">
	<tr>
	<td>
		${status.count}
	</td>
	<td>
		${item.year}年${item.month}月份${item.person.name}工完成情况【${item.state.name}】
	</td>
	<td>
		${item.person.name}
	</td>
	<td>
		<input type="button" value="评价" onclick="javascript:window.open('/${ctx}/mange/${mange.id}/monthchk/${item.id}','_self')"/>
	</td>
				
   </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
