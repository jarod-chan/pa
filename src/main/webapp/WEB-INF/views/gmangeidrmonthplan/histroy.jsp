<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	var selChange=function(){
		$("#queryform").submit();
	}
	
	$(document).ready(function(){
		$("#queryform select").bind("change",selChange);
	})
	
</script>
</head>

<c:set target="${pagefunc}" property="name" value="部门月度计划历史" /> 
<c:set target="${pagefunc}" property="url" value="/${ctx}/monthplan/gm/history" />

<c:set var="pagesize" value="720" scope="request"/>
<body>

<div class="headdiv" >
<div class="headleft" >
<form id="queryform" action="/${ctx}/monthplan/gm/history" method="post">
	年份：
	<select name="year">
		<c:forEach var="item" items="${dateTool.allYears}">
			<option value="${item}" <c:if test="${item==monthplan_gm_history.year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>
	月份：
	<select name="month">
		<c:forEach var="item" items="${dateTool.allMonths}">
			<option value="${item}" <c:if test="${item==monthplan_gm_history.month}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>
</form>
</div>
<div class="headright" >
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
