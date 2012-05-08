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

<div class="headdiv" >
<div class="headleft" >
	部门:${person.department}&nbsp;&nbsp;
</div>
<div class="headright">
	<input type="button" value="<<返回" onclick="javascript:window.open('/${ctx}/mange/${person.id}/idrmonthplan','_self')"/>
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
