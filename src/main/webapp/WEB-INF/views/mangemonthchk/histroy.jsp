<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
 
</head>
<c:set var="pagefunc" value="员工工作评价" scope="request"/> 
<c:set var="pagetitle" value="员工月度工作任务评价历史" scope="request"/> 
<c:set var="pagesize" value="800" scope="request"/>  
<body>
<form action="" method="get">

<div class="headdiv" >
<div class="headleft" >
	部门:${mange.department}&nbsp;&nbsp;
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
	<input type="button" value="<<返回" onclick="javascript:window.open('/${ctx}/mange/${mange.id}/monthchk','_self')"/>
</div>
<div  class="headnone"></div>
</div>

</form>

<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/monthchktable.jsp"%>
</body>
</html>
