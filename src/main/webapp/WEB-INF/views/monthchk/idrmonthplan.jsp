<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<%@ include file="../common/head.jsp"%>
<body>
<h2>部门月度工作计划【查看】</h2>
<form action="" method="get">
年份：
<select name="year">
	<c:forEach var="item" items="${dateTool.allYears}">
		<option value="${item}" <c:if test="${item==queryBean.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
月份：
<select name="month">
	<c:forEach var="item" items="${dateTool.allMonths}">
		<option value="${item}" <c:if test="${item==queryBean.month}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="查询" />
<input type="button" value="<<当前考核" onclick="javascript:window.open('/${ctx}/person/${person.id}/monthchk','_self')"/>
</form>
<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
