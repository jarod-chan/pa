<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set var="pagefunc" value="部门工作执行" scope="request"/> 
<c:set var="pagetitle" value="部门月度工作计划历史" scope="request"/> 
<c:set var="pagesize" value="715" scope="request"/> 
<body>

<form action="" method="get">
<div class="headdiv" >
<div class="headleft" >
	部门:${person.department}&nbsp;&nbsp;
	年份：
	<select name="year">
		<c:forEach var="item" items="${dateTool.allYears}">
			<option value="${item}" <c:if test="${item==queryBean.year}">selected="true"</c:if> >${item}</option>
		</c:forEach>
	</select>
	&nbsp;&nbsp;
	<input type="submit" value="查询" />
</div>
<div class="headright">
	<input type="button" value="<<返回" onclick="javascript:window.open('/${ctx}/mange/${person.id}/idrmonthplan','_self')"/>
</div>
<div  class="headnone"></div>
</div>
</form>

<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
