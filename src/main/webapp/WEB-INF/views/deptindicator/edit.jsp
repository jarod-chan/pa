<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>

<head>
<%@ include file="../common/head.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	$(".tbldef :checkbox").bind("click",function(){
		$(this).next().val(this.checked);
	});	
});

function save(){
	var actionFrom=$("form");
	var oldAction=actionFrom.attr("action");
	$(".tbldef tbody").formatName();
	actionFrom.attr("action",oldAction+"/save").submit();
}
</script>

</head>
<body>
<h2>部门KPI指标分配</h2>
年度：${deptIndicator.year}&nbsp;&nbsp;部门:${deptIndicator.department.name}
<%@ include file="../common/message.jsp"%>

<form action="/${ctx}/admin/deptindicator/${deptIndicator.year}/department/${deptIndicator.department.id}" method="post">
<input type="hidden" name="id" value="${deptIndicator.id}" />
<input type="hidden" name="year" value="${deptIndicator.year}" />
<input type="hidden" name="department.id" value="${deptIndicator.department.id}" />
<table border="1" class="tbldef" >
		<thead>
			<tr>
				<th>序号</th>
				<th>公司指标编码</th>
				<th>公司指标类别</th>
				<th>公司指标类容</th>
				<th>是否必选指标</th>
			</tr>
		</thead>
		<tbody>
	  		<c:forEach var="item" items="${deptIndicator.indiactorOptions}" >
			<tr>
				<td>${item.sn} <input type="hidden" name="indiactorOptions_sn" value="${item.sn}"/><input type="hidden" value="${item.idrCompany.id}" name="indiactorOptions_idrCompany.id"/></td>
				<td>${item.idrCompany.number}</td>
				<td>${item.idrCompany.idrTypeWeight.idrType.name}</td>
				<td>${item.idrCompany.context}</td>
				<td><input type="checkbox" <c:if test="${item.must}">checked="checked"</c:if>  /><input type="hidden" name="indiactorOptions_must" value="${item.must}"/></td>
			</tr>
			</c:forEach> 
		</tbody>
</table>
<br/>
<input type="button" value="保存" onclick="save()"/>
<input type="button" value="返回" onclick="window.open('/${ctx}/admin/deptindicator/${deptIndicator.year}','_self');"/>
</form>
</body>
</html>
