<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<html>

<head>
<%@ include file="../common/head.jsp"%>
</head>
<body>
<h2>类别编辑</h2>
<%@ include file="../common/message.jsp"%> 
<form action="/${ctx}/admin/idrtype/save" method="post">
<input type="hidden" name="id" value="${idrType.id}" /> 
编码：<br/>
<input type="text" name="number" value="${idrType.number}"/><br/>
名称：<br/>
<input type="text" name="name" value="${idrType.name}"/><br/>
<input type="submit" value="保存"/>
<input type="button" value="返回" onclick="javascript:window.open('/${ctx}/admin/idrtype','_self')"/>
</form>
</body>
</html>
