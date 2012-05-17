<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>

<head>
<%@ include file="../common/head.jsp"%>
</head>
<body>
<h2>部门KPI分解</h2>
年度：${year}&nbsp;&nbsp;部门:${department.name}
<%@ include file="../common/message.jsp"%>

<br/>
<input type="button" value="保存" onclick="save()"/>
<input type="button" value="返回" onclick="window.open('/${ctx}/admin/deptindicator/${deptIndicator.year}','_self');"/>
</form>
</body>
</html>
