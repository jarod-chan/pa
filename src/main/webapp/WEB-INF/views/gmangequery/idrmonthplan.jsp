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
<h2>公司部门月度工作查询</h2>

<form action="" method="get">
年份：
<select name="year">
	<c:forEach var="item" items="${years}">
		<option value="${item}" <c:if test="${item==queryPage.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
月份：
<select name="month">
	<c:forEach var="item" items="${months}">
		<option value="${item}" <c:if test="${item==queryPage.month}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="查询" />
</form>
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
