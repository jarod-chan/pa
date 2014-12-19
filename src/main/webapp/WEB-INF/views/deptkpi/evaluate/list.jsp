<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>

<head>
<%@ include file="../../common/head.jsp"%>

</head>
<body>
<h2>部门KPI评价</h2>
<br/>
<a href="/${ctx}/admin/deptkpi/${pageBean.year}">部门KPI列表</a>
&gt;<a href="/${ctx}/admin/deptkpi/${pageBean.year}/department/${pageBean.department.id}/evaluate">${pageBean.department.name}KPI评价列表</a>
<br/>
<br/>
年度：${pageBean.year}&nbsp;&nbsp;部门:${pageBean.department.name}

<%@ include file="../../common/message.jsp"%>


<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th>序号</th>
				<th>公司KPI指标</th>
				<th>已分解项数</th>
				<th>已评价项数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
	  		<c:forEach var="item" items="${pageBean.pageItems}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${item.idrCompany.context}</td>
				<td>${item.deptKpiItemNum}</td>
				<td>${item.hasEvaluateItemNum}</td>
				<td>
					<input type="button" value="评价" onclick="window.open('/${ctx}/admin/deptkpi/${pageBean.year}/department/${pageBean.department.id}/idrcompany/${item.idrCompany.id}/evaluate','_self');"/>
				</td>
			</tr>
			</c:forEach> 
		</tbody>
</table>

<br/>

</body>
</html>
