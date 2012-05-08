<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set var="pagefunc" value="月度工作任务" scope="request"/> 
<c:set var="pagetitle" value="部门月度工作计划查看" scope="request"/> 
<c:set var="pagesize" value="720" scope="request"/> 
<body>
<form action="" method="get">
<div class="headdiv" >
<div class="headleft" >
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
&nbsp;&nbsp;
<input type="submit" value="查询" />
</div>
<div class="headright">
<input type="button" value="<<返回" onclick="javascript:window.open('/${ctx}/person/${person.id}/monthchk','_self')"/>
</div>
<div  class="headnone"></div>
</div>


</form>
<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
