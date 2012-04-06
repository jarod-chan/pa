<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>
<head>
<%@ include file="../common/head.jsp"%>
</head>

<body>
<h2>部门月度工作计划【历史】</h2>
部门经理:${person.name}&nbsp;&nbsp;部门:${person.department}&nbsp;&nbsp;<input type="button" value="<<当前计划" onclick="javascript:window.open('/${ctx}/mange/${person.id}/idrmonthplan','_self')"/>
<%@ include file="../common/message.jsp"%>
<%@ include file="../fragment/idrmonthplantable.jsp"%>
</body>
</html>
