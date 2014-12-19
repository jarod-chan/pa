<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>

<script type="text/javascript">
	$(function(){
		$("#selDepartment").change(function(){
			var selDepartmentid=$(this).val();
			window.open("/${ctx}/yearpk/gm/department/"+selDepartmentid,"_self");
		});
	});

</script>

</head> 

<c:set target="${pagefunc}" property="name" value="年度考核评分查询" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/yearpk/gm" />  



<c:set var="pagesize" value="600" scope="request"/>
<body>

<div class="headdiv" >
<div class="headleft"  >
考核年份:${year}&nbsp;&nbsp;
部门:
<select id="selDepartment">
	<c:forEach var="dept" items="${departments}" >
		<option value="${dept.id}" <c:if test="${dept.id==selDepartment.id}">selected="true"</c:if> >${dept.name}</option>
	</c:forEach>	
</select>
</div>
<div class="headright" >
</div>
<div  class="headnone"></div>
</div>
<%@ include file="../../common/message.jsp"%>

<table border=1 style="table-layout:fixed;width:600px;">
<tr>
	<td>序号</td><td>员工</td><td>得分</td><td>操作</td>
</tr>
<c:forEach  var="item"  items="${personPointList}" varStatus="stauts">
	<tr>
	<td>${stauts.count} </td>
	<td>${item.person.name} </td>
	<td>${item.getPoint}</td>
	<td><input type="button" value="详细"  onclick="javascript:window.open('/${ctx}/yearpk/gm/person/${item.person.id}/year/${year}','_self')"/></td>
	</tr>
</c:forEach>
</table>

</body>
</html>