<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>

<script type="text/javascript">

	var summaryCss={"width":"530","vertical-align":"top"};
	var summaryLength={"maxlength":"500"};
	var summaryOption={minHeight:60,extraSpace:3};
	
	$(document).ready(function() { 
		$("textarea[name='idrTasks_summary']").css(summaryCss).attr(summaryLength).autoResize(summaryOption);
	});
	
</script>
</head>

<body>
<h2>部门月度工作计划【历史】</h2>
分管经理:${person.name}&nbsp;&nbsp;<input type="button" value="<<当前计划" onclick="javascript:window.open('/${ctx}/gmange/${person.id}/idrmonthplan','_self')"/>
<br>
<c:if test="${message!=null}">
	<font id="msg" style="color:red;" >${message}</font>
</c:if>
<br>

<table border=1 style="table-layout:fixed;width:700px;" class="tbldef">
<thead>
	<tr>
		<td style="width:50px;">序号</td>
		<td style="width:650px;">工作计划</td>
	</tr>
</thead>
<tbody id="tbl">
	<c:forEach var="bill" items="${idrMonthPlanBills}" varStatus="status">
		<tr>
			<td rowspan="2">${status.count}</td>
			<td>${bill.year}年${bill.month}【${bill.department.name}】月度工作计划</td>
		</tr>
		<tr>
			<td>
				<ol>
				<c:forEach var="item" items="${bill.idrTasks}">
					<li>
						<div>计划：${item.context}</div>
						<div>总结：<textarea style="border-style:none;background:transparent;"  readonly="readonly" name="idrTasks_summary">${item.summary}</textarea></div>
					</li>
				</c:forEach>
				</ol>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>



</body>
</html>
