<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>

<c:set target="${pagefunc}" property="name" value="公司考核情况查询" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/gmange/${person.id}/totalreport" />  

<c:set target="${pagetitle}" property="name" value="公司部门月度工作查询" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/gmange/${person.id}/query/idrmonthplan" /> 

<body>


<form action="" method="get">
年份：
<select name="year">
	<c:forEach var="item" items="${dateTool.allYears}">
		<option value="${item}" <c:if test="${item==queryPage.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
月份：
<select name="month">
	<c:forEach var="item" items="${dateTool.allMonths}">
		<option value="${item}" <c:if test="${item==queryPage.month}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="查询" />
</form>
<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
