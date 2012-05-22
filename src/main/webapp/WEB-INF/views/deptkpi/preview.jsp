<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>

<head>
<%@ include file="../common/head.jsp"%>

</head>
<body>
<h2>部门KPI分解</h2>
年度：${previewPage.year}&nbsp;&nbsp;部门:${previewPage.department.name}<br/>
<%@ include file="../common/message.jsp"%>

<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th>序号</th>
				<th>公司KPI指标分解</th>
			</tr>
		</thead>
		<tbody>
	  		 <c:forEach var="item" items="${previewPage.previewItems}" varStatus="status">
			<tr>
				<td>
					${status.count}
				</td>
				<td>
					<div>公司KPI指标:${item.idrCompany.context }</div>
					<div>
						<c:forEach var="item2" items="${item.deptKpiItems}">
						<div>${item2.sn}.${item2.context}</div>
						</c:forEach>
					</div>
				</td>
			</tr>
			</c:forEach> 
		</tbody>
</table>

</body>
</html>
