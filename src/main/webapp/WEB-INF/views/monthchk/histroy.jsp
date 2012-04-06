<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
 
</head>
<body>
<h2>员工月度工作任务完成历史</h2>
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
<%@ include file="../fragment/monthchktable.jsp"%>
</body>
</html>
