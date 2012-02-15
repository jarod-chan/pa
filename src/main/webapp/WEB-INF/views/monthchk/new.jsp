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
员工月度工作计划完成情况表

<table border=1>
<thead>
	<tr>
		<th>姓名</th>
		<th></th>
		<th>部门</th>
		<th></th>
		<th>月度</th>
		<th></th>
	</tr>
	<tr>
		<th>序号</th>
		<th colspan="5">工作内容</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td>1</td>
		<td colspan="5">2</td>
	</tr>
</tbody>
</table>


</body>
</html>
