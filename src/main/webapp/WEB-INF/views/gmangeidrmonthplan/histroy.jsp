<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>
<c:set var="pagefunc" value="部门月度工作任务执行" scope="request"/> 
<c:set var="pagetitle" value="部门月度工作计划历史" scope="request"/> 
<c:set var="pagesize" value="720" scope="request"/>
<body>

<div class="headdiv" >
<div class="headleft" >
</div>
<div class="headright" >
<input type="button" value="<<当前计划" onclick="javascript:window.open('/${ctx}/gmange/${person.id}/idrmonthplan','_self')"/>
</div>
<div  class="headnone"></div>
</div>

<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
