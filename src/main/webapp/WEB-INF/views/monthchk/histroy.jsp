<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
 
</head>
<body>
<h2>员工月度工作任务完成历史</h2>
<br/>
<form action="" method="get">
年份：
<select name="year">
	<c:forEach var="item" items="${dateTool.allYears}">
		<option value="${item}" <c:if test="${item==queryBean.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="查询" />
<input type="button" value="<<当前考核" onclick="javascript:window.open('/${ctx}/person/${person.id}/monthchk','_self')"/>
</form>

<%@ include file="../common/message.jsp"%>
<table border=1 style="table-layout:fixed;width:800px;">
<thead>
	<tr>
		<th style="width:50px;">序号</th>
		<th style="width:500px;">工作完成情况表</th>
	</tr>
</thead>
		<tbody>
			<c:forEach var="item" items="${monthChks}" varStatus="status">
				<tr>
					<td rowspan="2">${status.count}</td>
					<td>
						${item.year}年${item.month}月份${item.person.name}工完成情况【${item.state.name}】
					</td>
				</tr>
				<tr>
					<td>
					<ol>
						<c:forEach var="chkitem" items="${item.monthChkItems}">
							<li>
							[${chkitem.workType.worktype}]${chkitem.task}&nbsp;&nbsp;&nbsp;&nbsp;用时:${chkitem.workhour}&nbsp;&nbsp;评价:
							<c:choose>
				     			<c:when test="${chkitem.point=='5'}">优秀</c:when>
				     			<c:when test="${chkitem.point=='4'}">良好</c:when>
				     			<c:when test="${chkitem.point=='3'}">尽职</c:when>
				     			<c:when test="${chkitem.point=='2'}">需改进</c:when>
				     			<c:when test="${chkitem.point=='1'}">差</c:when>
							</c:choose>
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
