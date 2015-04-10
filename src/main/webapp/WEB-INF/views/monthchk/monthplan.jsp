<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
$(function() {
	$("#queryform select").bind("change",function(){
		$("#queryform").submit();
	});
});
</script>
</head>

<c:set target="${pagefunc}" property="name" value="部门计划查看" /> 
<c:set target="${pagefunc}" property="url" value="/${ctx}/monthsmy/monthplan" />

<c:set var="pagesize" value="720" scope="request"/> 
<body>
<form id="queryform" action="" method="post">
<div class="headdiv" >
<div class="headleft" >
年份：
<select name="year">
	<c:forEach var="item" items="${dateTool.allYears}">
		<option value="${item}" <c:if test="${item==monthsmy_monthplan.year}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
月份：
<select name="month">
	<c:forEach var="item" items="${dateTool.allMonths}">
		<option value="${item}" <c:if test="${item==monthsmy_monthplan.month}">selected="true"</c:if> >${item}</option>
	</c:forEach>
</select>
</div>
<div class="headright">
</div>
<div  class="headnone"></div>
</div>


</form>
<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
