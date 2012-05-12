<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>

<head>
<%@ include file="../common/head.jsp"%>

<script type="text/javascript">
</script>

</head>
<body>
<h2>部门KPI指标分配</h2>
年度：${year}
<%@ include file="../common/message.jsp"%>


<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th>序号</th>
				<th>部门</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
	  		<c:forEach var="item" items="${listBeans}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${item.department.name}</td>
				<td>
					<c:choose>
						<c:when test="${item.deptIndicator==null}">
						<input type="button" value="分配" onclick="window.open('/${ctx}/admin/deptindicator/${year}/department/${item.department.id}','_self');"/>
						</c:when>
						<c:otherwise>
						<input type="button" value="重新分配" onclick="window.open('/${ctx}/admin/deptindicator/${year}/department/${item.department.id}','_self');"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:forEach> 
		</tbody>
</table>

</body>
</html>
