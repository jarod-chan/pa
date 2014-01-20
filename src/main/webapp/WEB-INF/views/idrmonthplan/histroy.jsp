<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>

<c:set target="${pagefunc}" property="name" value="部门月度计划历史" />
<c:set target="${pagefunc}" property="url" value="/${ctx}/mange/${person.id}/idrmonthplan/history" /> 

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
</div>
<div  class="headnone"></div>
</div>
</form>

<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
