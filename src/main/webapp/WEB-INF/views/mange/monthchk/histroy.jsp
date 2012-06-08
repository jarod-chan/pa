<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<html>
<head>
<%@ include file="../../common/head.jsp"%>
 
</head>
  
<c:set target="${pagetitle}" property="name" value="员工工作评价历史" /> 
<c:set target="${pagetitle}" property="url" value="/${ctx}/mange/${mange.id}/monthchk/histroy" /> 

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
</div>
<div  class="headnone"></div>
</div>

</form>

<%@ include file="../../common/message.jsp"%>
<%@ include file="../../fragment/monthchktable.jsp"%>
</body>
</html>
