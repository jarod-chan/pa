<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>

<head>
<%@ include file="../common/head.jsp"%>

<script type="text/javascript">

function commit(){
	var actionFrom=$("form");
	var oldAction=actionFrom.attr("action");
	var msg="确定提交？";
	if(confirm(msg)){
		actionFrom.attr("action",oldAction+"/commit").submit();
	}
}

function preview(){
	window.open('/${ctx}/admin/deptkpi/${listPage.year}/department/${listPage.department.id}/preview','_blank');
}

</script>

</head>
<body>
<h2>${listPage.department.name}KPI分解列表</h2>
<br/>
<a href="/${ctx}/admin/deptkpi/${listPage.year}">部门KPI列表</a>&gt;<a href="/${ctx}/admin/deptkpi/${listPage.year}/department/${listPage.department.id}">${listPage.department.name}KPI分解列表</a>
<br/>
<br/>
年度：${listPage.year}&nbsp;&nbsp;部门:${listPage.department.name}

<%@ include file="../common/message.jsp"%>

<form action="/${ctx}/admin/deptkpi/${listPage.year}/department/${listPage.department.id}" method="post">
</form>

<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th>序号</th>
				<th>公司KPI指标</th>
				<th>分解要求</th>
				<th>部门分解项数</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
	  		<c:forEach var="item" items="${listPage.pageItems}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${item.idrCompany.context}</td>
				<td>
					<c:if test="${item.mustSelect}">
						必选
					</c:if>
				</td>
				<td>${item.deptKpiItemNum}</td>
				<td>
					<input type="button" value="分解" onclick="window.open('/${ctx}/admin/deptkpi/${listPage.year}/department/${listPage.department.id}/idrcompany/${item.idrCompany.id}','_self');"/>
				</td>
			</tr>
			</c:forEach> 
		</tbody>
</table>

<br/>
<input type="button" value="提交" onclick="commit()"/>
<input type="button" value="预览" onclick="preview()"/>
</body>
</html>
