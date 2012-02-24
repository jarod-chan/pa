<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>
 
</head>
<body>
经理${mange.name}评价历史
<input type="button" value="<<当前考核" onclick="javascript:window.open('/${ctx}/mange/${mange.id}/monthchk','_self')"/>
<br>
<c:if test="${msg!=null}">
	<font id="msg" style="color:red;" >${msg}</font>
</c:if>
<br>
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
							[${chkitem.workType.worktype}]${chkitem.task}--
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
