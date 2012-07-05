<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>

<head>
<%@ include file="../../common/head.jsp"%>

</head>
<body>
<h2>部门KPI列表</h2>
<br/>
<a href="/${ctx}/admin/deptkpi/${year}">部门KPI列表</a>

<%@ include file="../../common/message.jsp"%>



<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th>序号</th>
				<th style="width:400px;">部门</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
	  		<c:forEach var="item" items="${departments}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${item.name}</td>
				<td>
					<input type="button" value="分解" onclick="window.open('/${ctx}/admin/deptkpi/${year}/department/${item.id}','_self');"/>
					&nbsp;&nbsp;
					<input type="button" value="评价" onclick="window.open('/${ctx}/admin/deptkpi/${year}/department/${item.id}/evaluate','_self');"/>
					&nbsp;&nbsp;
					<input type="button" value="计划" onclick="window.open('/${ctx}/admin/deptkpi/${year}/department/${item.id}/planmonth','_self');"/>
					&nbsp;&nbsp;
					<input type="button" value="预览" onclick="window.open('/${ctx}/admin/deptkpi/${year}/department/${item.id}/preview','_self');"/>
				</td>
			</tr>
			</c:forEach> 
		</tbody>
</table>

<br/>
<input type="button" value="返回管理员页面&lt;&lt;" onclick="window.open('/${ctx}/admin/all','_self');"/>

</body>
</html>
